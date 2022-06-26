package Main;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.MessageChannel;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public class Commands extends ListenerAdapter {
	private SQLRequest req;
	
	private ArrayList<String> getargs(String content) {
		ArrayList<String> args = new ArrayList<String>();
		String tmp_string = "";
		int length = content.length();
		char last_char = ' ';
		char actual_char = ' ';
		boolean guillemet_ouvert = false;
		for (int iterator = 0; iterator < length; iterator++) {
			last_char = actual_char;
			actual_char = content.charAt(iterator);
			if (!guillemet_ouvert && (actual_char == ' ' || actual_char == '\n')) {
				if (!tmp_string.equals("")) {
					args.add(tmp_string);
					tmp_string = "";
				}
			} else if (last_char != '\\' && actual_char == '\"' && !guillemet_ouvert) {
				guillemet_ouvert = true;
			} else if (last_char != '\\' && actual_char == '\"' && guillemet_ouvert) {
				guillemet_ouvert = false;
				if (!tmp_string.equals("")) {
					args.add(tmp_string);
					tmp_string = "";
				}
			} else {
				tmp_string = tmp_string + actual_char;
			}
		}
		if (!tmp_string.equals("")) {
			args.add(tmp_string);
			tmp_string = "";
		}
		return args;
	}
	
	@Override
    public void onMessageReceived(MessageReceivedEvent event) {
		MessageChannel channel = event.getChannel();
		Guild guild = event.getGuild();
		char prefix = '?'; // à modifier
        Message msg = event.getMessage();
        User user = msg.getAuthor();
        String content = msg.getContentRaw();
        ArrayList<String> args = getargs(content);
        ResultSet res = null;
        try {
			res = this.req.request("SELECT * FROM Carte WHERE id_member = " + user.getId().toString() + " AND id_server = " + guild.getId().toString() + ";");
		} catch (ClassNotFoundException | SQLException e1) {
			e1.printStackTrace();
			return;
		}
        try {
			if (!res.next()) {
				try {
					this.req.update("INSERT INTO Carte VALUES (" + guild.getId().toString() + ", " + user.getId().toString() + ", 'Mario', NULL, NULL, NULL, 1, 0, NULL);");
					this.req.update("INSERT INTO Familiers VALUES (" + guild.getId().toString() + ", " + user.getId().toString() + ", 0, 0, 0, -1, 0, 0, -1, 0, 0, -1, 0, 0, -1, 0, 0, -1, 0, 0, -1, 0, 0, -1, 0, 0, -1, 0, 0, -1, 0, 0, -1, 0, 0, -1, 0, 0, -1, 0, 0, -1, 0, 0, -1, 0, 0, -1, 0, 0, -1, 0, 0, -1, 0, 0, -1, 0, 0, -1, 0, 0, 1);");
					this.req.update("INSERT INTO Inventaire VALUES (" + guild.getId().toString() + ", " + user.getId().toString() + ", 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0);");
				} catch (SQLException e) {
					e.printStackTrace();
					return;
				}
			}
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
        try {
			res.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
        if (args.size() == 0 || args.get(0).charAt(0) != prefix) {
        	return;
        }
        String suffixe = args.get(0).substring(1);
        switch (suffixe) {
	        case "ping":
	            channel.sendMessage("chocho!").queue();
	            return;
	        case "me":
				try {
					Me me_command = new Me(channel, args, this.req, user, guild);
				} catch (ClassNotFoundException | SQLException e) {
					e.printStackTrace();
				}
	        	return;
	        case "renamemaster":
				try {
					Renamemaster renamemaster_command = new Renamemaster(channel, args, this.req, user, guild);
				} catch (ClassNotFoundException | SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	        	return;
	        case "changefetiche":
	        	try {
					Changefetiche changefetiche_command = new Changefetiche(channel, args, this.req, user, guild);
				} catch (ClassNotFoundException | SQLException e) {
					e.printStackTrace();
				}
	        	return;
	        case "changepseudosw":
	        	try {
					Changepseudosw changepseudosw_command = new Changepseudosw(channel, args, this.req, user, guild);
				} catch (ClassNotFoundException | SQLException e) {
					e.printStackTrace();
				}
	        	return;
	        case "changecasw":
	        	try {
					Changecasw changecasw_command = new Changecasw(channel, args, this.req, user, guild);
				} catch (ClassNotFoundException | SQLException e) {
					e.printStackTrace();
				}
	        	return;
	        case "changeurlmaster":
	        	try {
					Changeurlmaster changeurlmaster_command = new Changeurlmaster(channel, args, this.req, user, guild);
				} catch (ClassNotFoundException | SQLException e) {
					e.printStackTrace();
				}
	        	return;
	        case "inventory":
	        	try {
					Inventory inventory_command = new Inventory(channel, args, this.req, user, guild);
				} catch (ClassNotFoundException | SQLException e) {
					e.printStackTrace();
				}
	        	return;
	        case "familiers":
	        	try {
					Familiers familiers_command = new Familiers(channel, args, this.req, user, guild);
				} catch (ClassNotFoundException | SQLException e) {
					e.printStackTrace();
				}
	        	return;
        }
    }
	
	public Commands(SQLRequest req) {
		this.req = req;
	}
}
