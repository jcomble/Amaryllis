package Main;

import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.MessageChannel;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public class Commands extends ListenerAdapter {
	@Override
    public void onMessageReceived(MessageReceivedEvent event) {
    	String prefix = "!";
        Message msg = event.getMessage();
        if (msg.getContentRaw().equals(prefix + "nya")) {
            MessageChannel channel = event.getChannel();
            channel.sendMessage("Pong!").queue();
        }
    }
}
