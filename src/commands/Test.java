package commands;

import java.util.concurrent.TimeUnit;

import Main.CommandParameters;
import net.dv8tion.jda.api.entities.MessageChannel;

public class Test implements DiscordCommands {
	private MessageChannel channel;

	
	public Test(CommandParameters params) {
		this.channel = params.getChannel();
	}
	
	private void method_test() {
		System.out.println("Coucou hehe");
	}
	public void build() {
		channel.sendMessage("coucou").queue((msg) -> msg.delete().queueAfter(10, TimeUnit.SECONDS, t -> method_test()));
	}
}
