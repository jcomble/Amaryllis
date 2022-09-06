package commands;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import Main.CommandParameters;
import Main.SQLRequest;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.MessageChannel;
import net.dv8tion.jda.api.entities.User;

public class Changeurlmaster implements DiscordCommands {
	private MessageChannel channel;
	private ArrayList<String> args;
	private SQLRequest req;
	private User user;
	private Guild guild;
	private Message message;
	private char prefix;
	
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
	
	public Changeurlmaster(CommandParameters params) {
		this.prefix = params.getPrefix();
		this.channel = params.getChannel();
		this.args = params.getArgs();
		this.req = params.getReq();
		this.user = params.getUser();
		this.guild = params.getGuild();
		this.message = params.getMessage();
	}
	
	public void build() {
		if (args.size() != 2) {
			channel.sendMessageFormat("`" + prefix + "changeurlmaster url` seulement").queue();
			return;
		}
		message.delete().queue();
		String url = args.get(1);
		url = corriger(url);
		try {
			req.update("UPDATE Carte SET url_maitre = '" + url + "' WHERE id_server = " + guild.getId().toString() + " AND id_member = " + user.getId().toString() + ";");
		} catch (SQLException e) {
		}
		channel.sendMessage("L'image de présentation de votre Maître a été mis à jour").queue(msg -> msg.delete().queueAfter(5, TimeUnit.SECONDS));
	}
}
