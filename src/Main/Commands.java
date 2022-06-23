package Main;

import java.util.ArrayList;

import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.MessageChannel;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public class Commands extends ListenerAdapter {
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
		char prefix = '!'; // à modifier
        Message msg = event.getMessage();
        String content = msg.getContentRaw();
        ArrayList<String> args = getargs(content);
        if (args.size() == 0 || args.get(0).charAt(0) != prefix) {
        	return;
        }
        String suffixe = args.get(0).substring(1);
        switch (suffixe) {
	        case "ping":
	            channel.sendMessage("Pong!").queue();
	            return;
	        case "me":
	        	return;
        }
    }
}
