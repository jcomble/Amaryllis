package Main;
import java.sql.SQLException;
import java.util.ArrayList;

import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.MessageChannel;
import net.dv8tion.jda.api.entities.User;

public class Changepseudosw {
	private String corriger(String nom) {
		String tmp_return = "";
		int length = nom.length();
		for (int iterator = 0; iterator < length; iterator++) {
			char letter = nom.charAt(iterator);
			if (letter == '\'') {
				tmp_return = tmp_return + '\'';
			}
			tmp_return = tmp_return + letter;
		}
		return tmp_return;
	}
	
	public Changepseudosw(MessageChannel channel, ArrayList<String> args, SQLRequest req, User user, Guild guild) throws ClassNotFoundException, SQLException {
		if (args.size() != 2) {
			channel.sendMessageFormat("`?changepseudosw pseudo` seulement").queue();
			return;
		}
		String nom = args.get(1);
		nom = corriger(nom);
		if (nom.length() > 12) {
			channel.sendMessageFormat("Ton pseudo est trop long!").queue();
			return;
		}
		req.update("UPDATE Carte SET pseudo_switch = '" + nom + "' WHERE id_server = " + guild.getId().toString() + " AND id_member = " + user.getId().toString() + ";");
	}
}
