package commands;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.MessageChannel;

public class Test implements DiscordCommands {
	private MessageChannel channel;
	private ArrayList<String> args;
	private Message message;
	private char prefix;
	private int couleur;
	
	public Test(char prefix, int couleur, MessageChannel channel, ArrayList<String> args, Message message) {
		this.couleur = couleur;
		this.prefix = prefix;
		this.channel = channel;
		this.args = args;
		this.message = message;
	}
	
	private void method_test() {
		System.out.println("Coucou hehe");
	}
	public void build() {
		channel.sendMessage("coucou").queue((msg) -> msg.delete().queueAfter(10, TimeUnit.SECONDS, t -> method_test()));
	}
}
