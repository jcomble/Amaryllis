package commands;

import java.sql.SQLException;
import java.util.ArrayList;

import Main.SQLRequest;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.MessageChannel;
import net.dv8tion.jda.api.entities.User;

public class Changefetiche {
	private MessageChannel channel;
	private ArrayList<String> args;
	private SQLRequest req;
	private User user;
	private Guild guild;
	private Message message;
	private char prefix;
	
	private String corriger(String fetiche) {
		String tmp_return = "";
		int length = fetiche.length();
		for (int iterator = 0; iterator < length; iterator++) {
			char letter = fetiche.charAt(iterator);
			if (letter == '\'') {
				tmp_return = tmp_return + '\'';
			}
			tmp_return = tmp_return + letter;
		}
		return tmp_return;
	}
	
	public Changefetiche(char prefix, MessageChannel channel, ArrayList<String> args, SQLRequest req, User user, Guild guild, Message message){
		this.prefix = prefix;
		this.channel = channel;
		this.args = args;
		this.req = req;
		this.user = user;
		this.guild = guild;
		this.message = message;
	}
	
	public void build() throws SQLException {
		if (args.size() != 2) {
			channel.sendMessageFormat("`" + prefix + "changefetiche \"phrase\"` seulement").queue();
			return;
		}
		String fetiche = args.get(1);
		fetiche = corriger(fetiche);
		if (fetiche.length() > 200) {
			channel.sendMessageFormat("Ta phrase fétiche est trop longue").queue();
			return;
		}
		message.delete().queue();
		req.update("UPDATE Carte SET fetiche = '" + fetiche + "' WHERE id_server = " + guild.getId().toString() + " AND id_member = " + user.getId().toString() + ";");
	}
}
