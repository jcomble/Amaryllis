package Main;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.MessageChannel;
import net.dv8tion.jda.api.entities.User;

public class Familiers {
	public Familiers(MessageChannel channel, ArrayList<String> args, SQLRequest req, User user, Guild guild) throws ClassNotFoundException, SQLException {
		if (args.size() != 1) {
			channel.sendMessageFormat("`?familiers` seulement").queue();
			return;
		}
		ResultSet res = req.request("SELECT * FROM Familiers WHERE id_member = " + user.getId().toString() + " AND id_server = " + guild.getId().toString() + ";");
		String[] list_emojis = new String[] {
			"<:babyyoshi:990618756428488714>",
			"<a:luma:990618540748984371>",
			"<a:bobomb:990633474622881807>",
			"<a:chabloc:990618429771882627>",
			"<a:blooper:990618901232619612>",
			"<a:boo:990626121265586217>",
			"<a:Cheepcheep:990618537984946266>",
			"<a:plantepiranha:990618544263790642>",
			"<a:Bloc:990618388684476557>",
			"<a:poochy:990630895541186570>",
			"<a:yoshi:990627733585403934>",
			"<a:Booxeur:990637040016162868>",
			"<a:wiggler:990627354890088478>",
			"<a:thwomp:990631522061148240>",
			"<a:Tocolan:990627947813683281>",
			"<:stingby:990628202521169950>",
			"<a:ninji:990626580076310628>",
			"<a:MegaMole:990629454688714782>",
			"<a:goomba:990634177714073601>",
			"<a:chainchomp:990618536340779048>"
		};
		EmbedBuilder embed = new EmbedBuilder();
		embed.setTitle("Familiers");
		for (int iterator = 0; iterator < list_emojis.length; iterator++) {
			int experience = res.getInt("expf" + String.valueOf(iterator + 1));
			String text = experience == -1 ? "-" : "niv. 1\n10/10 PV\n50/50 PM";
			embed.addField(list_emojis[iterator], text, true);
		}
		embed.setFooter("?familiers");
		channel.sendMessageEmbeds(embed.build()).queue();
	}
}
