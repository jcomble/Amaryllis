package commands;

import java.sql.SQLException;
import java.util.ArrayList;

import Main.SQLRequest;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.MessageChannel;
import net.dv8tion.jda.api.entities.User;

public class Changeurlmaster {
	private MessageChannel channel;
	private ArrayList<String> args;
	private SQLRequest req;
	private User user;
	private Guild guild;
	private Message message;
	
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
	
	public Changeurlmaster(MessageChannel channel, ArrayList<String> args, SQLRequest req, User user, Guild guild, Message message) {
		this.channel = channel;
		this.args = args;
		this.req = req;
		this.user = user;
		this.guild = guild;
		this.message = message;
	}
	
	public void build() throws SQLException {
		if (args.size() != 2) {
			channel.sendMessageFormat("`?changeurlmaster url` seulement").queue();
			return;
		}
		message.delete().queue();
		String url = args.get(1);
		url = corriger(url);
		req.update("UPDATE Carte SET url_maitre = '" + url + "' WHERE id_server = " + guild.getId().toString() + " AND id_member = " + user.getId().toString() + ";");
	}
}
