package commands;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import Main.CommandParameters;
import Main.FamiliersEmojis;
import Main.SQLRequest;
import Main.StatsReader;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.MessageChannel;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.entities.emoji.Emoji;

public class Familiers implements DiscordCommands {
	private MessageChannel channel;
	private ArrayList<String> args;
	private SQLRequest req;
	private User user;
	private Guild guild;
	private Message message;
	private FamiliersEmojis emojis;
	private char prefix;
	private int couleur;
	
	public Familiers(CommandParameters params) {
		this.couleur = params.getCouleur();
		this.prefix = params.getPrefix();
		this.channel = params.getChannel();
		this.args = params.getArgs();
		this.req = params.getReq();
		this.user = params.getUser();
		this.guild = params.getGuild();
		this.message = params.getMessage();
		this.emojis = params.getEmojis();
	}
	
	public void build() {
		if (args.size() != 1) {
			channel.sendMessageFormat("`" + prefix + "familiers` seulement").queue();
			return;
		}
		message.delete().queue();
		try {
			ResultSet res = req.request("SELECT * FROM Carte WHERE id_member = " + user.getId().toString() + " AND id_server = " + guild.getId().toString() + ";");
			int numero_familier = res.getInt("numero_familier");
			res.close();
			res = req.request("SELECT * FROM Familiers WHERE id_member = " + user.getId().toString() + " AND id_server = " + guild.getId().toString() + ";");
			int experience = res.getInt("expf" + String.valueOf(numero_familier));
			int version = res.getInt("versionf" + String.valueOf(numero_familier));
			int pv = res.getInt("pvf" + String.valueOf(numero_familier));
			int pm = res.getInt("pmf" + String.valueOf(numero_familier));
			String[] list_emojis = emojis.get_emojis();
			EmbedBuilder embed = new EmbedBuilder();
			embed.setColor(couleur);
			embed.setTitle("Familier actuel");
			StatsReader stats = new StatsReader(numero_familier, experience, version, pv, pm);
			String description = experience == -1 ? "-" : stats.getstats();
			res.close();
			embed.setDescription(description);
			channel.sendMessage(list_emojis[numero_familier - 1]).setEmbeds(embed.build()).queue(
				(msg) -> {
					String message_id = msg.getId();
					try {
						req.update("INSERT INTO FamiliersEmbeds VALUES (" + guild.getId() + ", " + user.getId() + ", " + message_id + ", 0);");
					} catch (SQLException e) {
						e.printStackTrace();
					}
					for (String emoji : list_emojis) {
						msg.addReaction(Emoji.fromFormatted(emoji)).queue();
					}
				}
			);
		} catch (SQLException | ClassNotFoundException e){
		}
	}
}
