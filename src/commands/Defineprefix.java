package commands;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.EnumSet;
import java.util.concurrent.TimeUnit;

import Main.CommandParameters;
import Main.SQLRequest;
import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.MessageChannel;
import net.dv8tion.jda.api.entities.User;

public class Defineprefix implements DiscordCommands {
	private MessageChannel channel;
	private ArrayList<String> args;
	private SQLRequest req;
	private User user;
	private Guild guild;
	private Message message;
	private char prefix;
	
	public Defineprefix(CommandParameters params) {
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
			channel.sendMessageFormat("`" + prefix + "defineprefix prefix` seulement").queue();
			return;
		}
		String s_prefix = args.get(1);
		if (s_prefix.length() != 1) {
			channel.sendMessageFormat("Un préfixe de taille 1 s'il-te-plaît!").queue();
			return;
		}
		guild.retrieveMemberById(user.getIdLong()).queue(
			(member)-> {
				EnumSet<Permission> permissions = member.getPermissions();
				if (!permissions.contains(Permission.ADMINISTRATOR)) {
					channel.sendMessageFormat("Tu n'es pas administrateur").queue();
					return;
				}
				message.delete().queue();
				char prefix = s_prefix.charAt(0);
				try {
					req.update("UPDATE prefixes SET prefixe = " + String.valueOf((int) prefix) + " WHERE id_server = " + guild.getId() + ";");
				} catch (SQLException e) {
					e.printStackTrace();
				}
				channel.sendMessage("Le préfixe de ce serveur est `" + prefix + "` désormais").queue(msg -> msg.delete().queueAfter(5, TimeUnit.SECONDS));
			}
		);
	}
}
