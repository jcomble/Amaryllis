package commands;

import java.util.ArrayList;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.MessageChannel;

public class Help {
	private MessageChannel channel;
	private ArrayList<String> args;
	private Message message;
	
	public Help(MessageChannel channel, ArrayList<String> args, Message message) {
		this.channel = channel;
		this.args = args;
		this.message = message;
	}
	
	public void build() {
		if (args.size() != 1) {
			channel.sendMessage("`?help`seulement").queue();
			return;
		}
		message.delete().queue();
		EmbedBuilder embed = new EmbedBuilder();
		embed.setTitle("Aides");
		embed.addField("Carte de pr�sentation <a:hideri_hi:991710158386364437>", "`?me` : affiche ta carte de pr�sentation\n`?renamemaster nom` : renomme le nom de ton ma�tre\n`?changeurlmaster url` : change l'image du ma�tre\n`?changefetiche \"phrase\"` : change la phrase f�tiche du ma�tre\n`?changepseudosw \"pseudo\"` : change ton pseudo Switch\n `?changecasw codeami` : change ton code ami Switch", false);
		embed.addField("Possessions <:hideri_joie:991706755069399070>", "`?inventaire` : affiche ton inventaire\n`?familier` : affiche tes familiers", false);
		channel.sendMessageEmbeds(embed.build()).queue();
	}
}
