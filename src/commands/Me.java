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

public class Me {
	private MessageChannel channel;
	private ArrayList<String> args;
	private SQLRequest req;
	private User user;
	private Guild guild;
	private Message message;
	private FamiliersEmojis emojis;
	
	public Me(MessageChannel channel, ArrayList<String> args, SQLRequest req, User user, Guild guild, Message message, FamiliersEmojis emojis) {
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
			channel.sendMessageFormat("`?me` seulement").queue();
			return;
		}
		message.delete().queue();
		ResultSet res = req.request("SELECT * FROM Carte WHERE id_member = " + user.getId().toString() + " AND id_server = " + guild.getId().toString() + ";");
		String nom_maitre = res.getString("nom_maitre");
		EmbedBuilder embed = new EmbedBuilder();
		embed.setTitle(nom_maitre);
		String url_maitre = res.getString("url_maitre");
		if (url_maitre == null) {
			url_maitre = "https://static.fnac-static.com/multimedia/Images/FD/Comete/135565/CCP_IMG_600x400/1777919.jpg";
		}
		String pseudo_switch = res.getString("pseudo_switch");
		if (pseudo_switch == null) {
			pseudo_switch = "Pas de pseudo switch défini";
		}
		String code_ami = res.getString("code_ami");
		if (code_ami == null) {
			code_ami = "Pas de code ami défini";
		}
		embed.addField("Infos Switch", "Pseudo Switch :\n" + pseudo_switch + "\n Code Ami :\n" + code_ami , true);
		embed.setImage(url_maitre);
		String fetiche = res.getString("fetiche");
		if (fetiche == null) {
			fetiche = "Pas de phrase fétiche";
		}
		embed.setDescription(fetiche);
		int nombre_boss = res.getInt("nombre_boss");
		embed.addField("Grades :", "Nombre de boss vaincus : " + String.valueOf(nombre_boss), true);
		String[] list_emojis = emojis.get_emojis();
		int numero_familier = res.getInt("numero_familier");
		String emoji = list_emojis[numero_familier - 1];
		embed.addField("Familier actuel :", emoji, true);
		embed.setFooter("?me");
		res.close();
		channel.sendMessageEmbeds(embed.build()).queue();
	}
}
