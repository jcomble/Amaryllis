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

public class Changecasw implements DiscordCommands {
	private MessageChannel channel;
	private ArrayList<String> args;
	private SQLRequest req;
	private User user;
	private Guild guild;
	private Message message;
	private char prefix;
	
	private boolean is_good_ca(String CA) {
		int length = CA.length();
		for (int iterator = 0; iterator < length; iterator++) {
			if ((iterator + 1) % 5 != 0 && (CA.charAt(iterator) < '0' || CA.charAt(iterator) > '9') || (iterator + 1) % 5 == 0 && CA.charAt(iterator) != '-') {
				return false;
			}
		}
		return true;
	}
	
	public Changecasw(CommandParameters params) {
		this.prefix = params.getPrefix();
		this.channel = params.getChannel();
		this.args = params.getArgs();
		this.req = params.getReq();
		this.user = params.getUser();
		this.guild = params.getGuild();
		this.message = params.getMessage();
	}
	
	public void build(){
		if (args.size() != 2) {
			channel.sendMessageFormat("`" + prefix + "changecasw CA` seulement").queue();
			return;
		}
		String CA = args.get(1);
		if (!is_good_ca(CA)) {
			channel.sendMessageFormat("Ton CA n'est pas dans le bon format!").queue();
			return;
		}
		message.delete().queue();
		try {
			req.update("UPDATE Carte SET code_ami = '" + CA + "' WHERE id_server = " + guild.getId().toString() + " AND id_member = " + user.getId().toString() + ";");
		} catch (SQLException e) {
		}
		channel.sendMessage("Votre code ami a été changé!").queue(msg -> msg.delete().queueAfter(5, TimeUnit.SECONDS));
	}
}
