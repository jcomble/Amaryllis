package commands;

import java.util.ArrayList;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.MessageChannel;

public class Help {
	private MessageChannel channel;
	private ArrayList<String> args;
	private Message message;
	private char prefix;
	private int couleur;
	
	public Help(char prefix, int couleur, MessageChannel channel, ArrayList<String> args, Message message) {
		this.couleur = couleur;
		this.prefix = prefix;
		this.channel = channel;
		this.args = args;
		this.message = message;
	}
	
	public void build() {
		if (args.size() != 1) {
			channel.sendMessage("`" + prefix + "help`seulement").queue();
			return;
		}
		message.delete().queue();
		EmbedBuilder embed = new EmbedBuilder();
		embed.setColor(couleur);
		embed.setTitle("Aides");
		embed.setDescription("Contacte DaSTOC-senpai pour des soucis techniques : <@931339802857586700> ");
		embed.addField("Carte de pr�sentation <a:hideri_hi:991710158386364437>", "`" + prefix + "me` : affiche ta carte de pr�sentation\n`" + prefix + "renamemaster nom` : renomme le nom de ton ma�tre\n`" + prefix + "changeurlmaster url` : change l'image du ma�tre\n`" + prefix  + "changefetiche \"phrase\"` : change la phrase f�tiche du ma�tre\n`" + prefix + "changepseudosw \"pseudo\"` : change ton pseudo Switch\n `" + prefix + "changecasw codeami` : change ton code ami Switch", false);
		embed.addField("Possessions <:hideri_joie:991706755069399070>", "`" + prefix + "inventory` : affiche ton inventaire\n`" + prefix + "familiers` : affiche tes familiers", false);
		embed.addField("Personnalisation <:hideri_ahegao:991706750413717526>", "`" + prefix + "defineprefix` : d�finit le pr�fixe dans ce serveur\n`" + prefix + "definecolor` : d�finit la couleur des embeds dans ce serveur", true);
		embed.setFooter(prefix + "help");
		channel.sendMessageEmbeds(embed.build()).queue();
	}
}
