package commands;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import Main.FamiliersEmojis;
import Main.SQLRequest;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.MessageChannel;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.entities.emoji.Emoji;

public class Familiers {
	private MessageChannel channel;
	private ArrayList<String> args;
	private SQLRequest req;
	private User user;
	private Guild guild;
	private Message message;
	private FamiliersEmojis emojis;
	private char prefix;
	private int couleur;
	
	public Familiers(char prefix, int couleur, MessageChannel channel, ArrayList<String> args, SQLRequest req, User user, Guild guild, Message message, FamiliersEmojis emojis) {
		this.couleur = couleur;
		this.prefix = prefix;
		this.channel = channel;
		this.args = args;
		this.req = req;
		this.user = user;
		this.guild = guild;
		this.message = message;
		this.emojis = emojis;
	}
	
	public void build() throws ClassNotFoundException, SQLException {
		if (args.size() != 1) {
			channel.sendMessageFormat("`" + prefix + "familiers` seulement").queue();
			return;
		}
		message.delete().queue();
		ResultSet res = req.request("SELECT * FROM Carte WHERE id_member = " + user.getId().toString() + " AND id_server = " + guild.getId().toString() + ";");
		int numero_familier = res.getInt("numero_familier");
		res.close();
		res = req.request("SELECT * FROM Familiers WHERE id_member = " + user.getId().toString() + " AND id_server = " + guild.getId().toString() + ";");
		int experience = res.getInt("expf" + String.valueOf(numero_familier));
		String[] list_emojis = emojis.get_emojis();
		EmbedBuilder embed = new EmbedBuilder();
		embed.setColor(couleur);
		embed.setTitle("Familier actuel");
		String description = experience == -1 ? "-" : "niv. 1\n 20/20 PV\n 50/50 PM";
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
	}
}
