package commands;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.EnumSet;

import Main.SQLRequest;
import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.MessageChannel;
import net.dv8tion.jda.api.entities.User;

public class Definecolor {
	private MessageChannel channel;
	private ArrayList<String> args;
	private SQLRequest req;
	private User user;
	private Guild guild;
	private Message message;
	private char prefix;
	
	public Definecolor(char prefix, MessageChannel channel, ArrayList<String> args, SQLRequest req, User user, Guild guild, Message message) {
		this.prefix = prefix;
		this.channel = channel;
		this.args = args;
		this.req = req;
		this.user = user;
		this.guild = guild;
		this.message = message;
	}
	
	private boolean hexadecimal_representation(String word) {
		if (word.length() == 0 || word.length() > 6) {
			return false;
		}
		String tmp_word = word.toLowerCase();
		for (int iterator = 0; iterator < tmp_word.length(); iterator++) {
			char tmp_letter = tmp_word.charAt(iterator);
			if (('0' > tmp_letter || tmp_letter > '9') && ('a' > tmp_letter || tmp_letter > 'f')) {
				return false;
			}
		}
		return true;
	}
	
	private int convert(String hexa) {
		int s = 0;
		for (int iterator = 0; iterator < hexa.length(); iterator++) {
			s *= 16;
			char tmp_letter = hexa.charAt(iterator);
			if  ('0' <= tmp_letter && tmp_letter <= '9') {
				s += tmp_letter - '0';
			} else {
				s += tmp_letter - 'a' + 10;
			}
		}
		return s;
	}
	
	public void build() throws SQLException {
		if (args.size() != 2) {
			channel.sendMessageFormat("`" + prefix + "definecolor #Hexadecimal-color` seulement").queue();
			return;
		}
		String hexa = args.get(1);
		if (hexa.length() == 0 || hexa.charAt(0) == '#' && !hexadecimal_representation(hexa.substring(1)) || hexa.charAt(0) != '#' && !hexadecimal_representation(hexa)) {
			channel.sendMessageFormat("Mauvaise représentation en hexadécimal").queue();
			return;
		}
		hexa = hexa.charAt(0) == '#' ? hexa.substring(1) : hexa;
		int hexa_int = convert(hexa.toLowerCase());
		guild.retrieveMemberById(user.getIdLong()).queue(
			(member)-> {
				EnumSet<Permission> permissions = member.getPermissions();
				if (!permissions.contains(Permission.ADMINISTRATOR)) {
					channel.sendMessageFormat("Tu n'es pas administrateur").queue();
					return;
				}
				message.delete().queue();
				try {
					req.update("UPDATE Couleurs SET couleur = " + String.valueOf(hexa_int) + " WHERE id_server = " + guild.getId() + ";");
				} catch (SQLException e) {
					e.printStackTrace();
				}
				channel.sendMessageFormat("La couleur a été mis à jour!").queue();
			}
		);
	}
}
