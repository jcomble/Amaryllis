package Main;

import java.io.File;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.MessageBuilder;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.MessageChannel;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.requests.restaction.MessageAction;
import net.dv8tion.jda.api.utils.AttachedFile;
import net.dv8tion.jda.api.utils.AttachmentOption;

public class Me {
	private int numero_familier_actuel;
	
	public Me(MessageChannel channel, ArrayList<String> args, SQLRequest req, User user, Guild guild) throws ClassNotFoundException, SQLException {
		if (args.size() != 1) {
			channel.sendMessageFormat("`?me` seulement").queue();
			return;
		}
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
		embed.addField("Infos Switch", "Pseudo Switch : " + pseudo_switch + "\n Code Ami : " + code_ami , true);
		embed.setImage(url_maitre);
		String fetiche = res.getString("fetiche");
		if (fetiche == null) {
			fetiche = "Pas de phrase fétiche";
		}
		embed.setDescription(fetiche);
		int nombre_boss = res.getInt("nombre_boss");
		embed.addField("Grades :", "Nombre de boss vaincus : " + String.valueOf(nombre_boss), true);
		embed.setFooter("?me");
		MessageAction message = channel.sendMessageEmbeds(embed.build());
		System.out.println(user.getId());
		if (user.getId().equals("931339802857586700")) {
			message.addFile(new File("src/Main/Vergil.mp3"));
		}
		message.queue();
	}
}
