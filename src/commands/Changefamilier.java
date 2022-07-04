package commands;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import Main.SQLRequest;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.MessageChannel;
import net.dv8tion.jda.api.entities.User;

public class Changefamilier {
	private MessageChannel channel;
	private ArrayList<String> args;
	private SQLRequest req;
	private User user;
	private Guild guild;
	private Message message;
	private char prefix;
	
	public Changefamilier(char prefix, MessageChannel channel, ArrayList<String> args, SQLRequest req, User user, Guild guild, Message message){
		this.prefix = prefix;
		this.channel = channel;
		this.args = args;
		this.req = req;
		this.user = user;
		this.guild = guild;
		this.message = message;
	}
	
	private boolean is_number(String number) {
		for (int iterator = 0; iterator < number.length(); iterator++) {
			char letter = number.charAt(iterator);
			if ('0' > letter || letter > '9') {
				return false;
			}
		}
		return true;
	}
	
	public void build() throws SQLException, ClassNotFoundException {
		if (args.size() != 2) {
			channel.sendMessageFormat("`" + prefix + "changefamilier numero` seulement!").queue();
			return;
		}
		String number = args.get(1);
		if (!is_number(number)) {
			channel.sendMessageFormat("Ce n'est pas un nombre entier naturel!").queue();
			return;
		}
		int int_number = Integer.parseInt(number);
		if (1 > int_number || int_number > 20) {
			channel.sendMessageFormat("Il n'y a que 20 familiers, le numéro doit être compris entre 1 et 20!").queue();
			return;
		}
		ResultSet res = req.request("SELECT * FROM Familiers WHERE id_server = " + guild.getId().toString() + " AND id_member = " + user.getId().toString() + ";");
		int version = res.getInt("versionf" + String.valueOf(int_number));
		res.close();
		if (version == 0) {
			channel.sendMessageFormat("Vous n'avez pas ce familier!").queue();
			return;
		}
		message.delete().queue();
		req.update("UPDATE Carte SET numero_familier = '" + String.valueOf(int_number) + "' WHERE id_server = " + guild.getId().toString() + " AND id_member = " + user.getId().toString() + ";");
		channel.sendMessageFormat("Vous avez changé de familier!").queue();
	}
}
