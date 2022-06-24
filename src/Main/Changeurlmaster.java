package Main;

import java.sql.SQLException;
import java.util.ArrayList;

import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.MessageChannel;
import net.dv8tion.jda.api.entities.User;

public class Changeurlmaster {
	private String corriger(String url) {
		String tmp_return = "";
		int length = url.length();
		for (int iterator = 0; iterator < length; iterator++) {
			char letter = url.charAt(iterator);
			if (letter == '\'') {
				tmp_return = tmp_return + '\'';
			}
			tmp_return = tmp_return + letter;
		}
		return tmp_return;
	}
	
	public Changeurlmaster(MessageChannel channel, ArrayList<String> args, SQLRequest req, User user, Guild guild) throws ClassNotFoundException, SQLException {
		if (args.size() != 2) {
			channel.sendMessageFormat("`?changeurlmaster url` seulement").queue();
			return;
		}
		String url = args.get(1);
		url = corriger(url);
		req.update("UPDATE Carte SET url_maitre = '" + url + "' WHERE id_server = " + guild.getId().toString() + " AND id_member = " + user.getId().toString() + ";");
	}
}
