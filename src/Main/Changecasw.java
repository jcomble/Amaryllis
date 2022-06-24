package Main;

import java.sql.SQLException;
import java.util.ArrayList;

import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.MessageChannel;
import net.dv8tion.jda.api.entities.User;

public class Changecasw {
	private boolean is_good_ca(String CA) {
		int length = CA.length();
		for (int iterator = 0; iterator < length; iterator++) {
			if ((iterator + 1) % 5 != 0 && (CA.charAt(iterator) < '0' || CA.charAt(iterator) > '9') || (iterator + 1) % 5 == 0 && CA.charAt(iterator) != '-') {
				return false;
			}
		}
		return true;
	}
	public Changecasw(MessageChannel channel, ArrayList<String> args, SQLRequest req, User user, Guild guild) throws ClassNotFoundException, SQLException {
		if (args.size() != 2) {
			channel.sendMessageFormat("`?changecasw CA` seulement").queue();
			return;
		}
		String CA = args.get(1);
		if (!is_good_ca(CA)) {
			channel.sendMessageFormat("Ton CA n'est pas dans le bon format!").queue();
			return;
		}
		req.update("UPDATE Carte SET code_ami = '" + CA + "' WHERE id_server = " + guild.getId().toString() + " AND id_member = " + user.getId().toString() + ";");
	}
}
