package commands;

import java.util.ArrayList;

import Main.CommandParameters;
import net.dv8tion.jda.api.entities.MessageChannel;

public class Use implements DiscordCommands {

	private MessageChannel channel;
	private ArrayList<String> args;
	private char prefix;
	
	public Use(CommandParameters params) {
		this.prefix = params.getPrefix();
		this.channel = params.getChannel();
		this.args = params.getArgs();
	}
	
	public void build() {
		if (args.size() < 2) {
			String command = prefix + "use ";
			String message_content = "```\n" + command + "nombre exp"
					+ "\n" + command + "nombre exp+ numero_familier"
					+ "\n" + command + "nombre potion numero_familier"
					+ "\n" + command + "nombre potion+ numero_familier"
					+ "\n" + command + "nombre potion++ numero_familier"
					+ "\n" + command + "nombre ether numero_familier"
					+ "\n" + command + "nombre ether+ numero_familier"
					+ "\n" + command + "nombre ether++ numero_familier"
					+ "\n" + command + "revive numero_familier"
					+ "\n" + command + "revive+ numero_familier"
					+ "\n" + command + "binoculars @mention_cible"
					+ "\n" + command + "destabilizer"
					+ "\n" + command + "chest"
					+ "\n" + command + "army"
					+ "\n" + command + "egg"
					+ "\n" + command + "stone @mention_cible"
					+ "\n" + command + "lightning @mention_cible```";
			channel.sendMessage(message_content).queue();
			return;
		}
	}
}
