package Main;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.MessageChannel;
import net.dv8tion.jda.api.entities.User;

public class Inventory {
	public Inventory(MessageChannel channel, ArrayList<String> args, SQLRequest req, User user, Guild guild) throws ClassNotFoundException, SQLException {
		if (args.size() != 1) {
			channel.sendMessageFormat("`?inventory` seulement").queue();
			return;
		}
		ResultSet res = req.request("SELECT * FROM Inventaire WHERE id_member = " + user.getId().toString() + " AND id_server = " + guild.getId().toString() + ";");
		EmbedBuilder embed = new EmbedBuilder();
		embed.setTitle("Inventaire");
		String description = "";
		description += res.getInt("pieces") + " <:piece:989856383870566410>\n";
		description += res.getInt("obj1") + " <:exp:989856371929387008>\n";
		description += res.getInt("obj2") + " <:exp:989856373670043719>\n";
		description += res.getInt("obj3") + " <:potion:989856385170825246>\n";
		description += res.getInt("obj4") + " <:potion:989856386462679083>\n";
		description += res.getInt("obj5") + " <:potion:989856387620274198>\n";
		description += res.getInt("obj6") + " <:ether:989856366766202912>\n";
		description += res.getInt("obj7") + " <:ether2:989856369563811880>\n";
		description += res.getInt("obj8") + " <:ether3:989856370650132510>\n";
		description += res.getInt("obj9") + " <:revive1:989856389243490314>\n";
		description += res.getInt("obj10") + " <:revive2:989856390690512976>\n";
		description += res.getInt("obj11") + " <:binocular:989856360453791774>\n";
		description += res.getInt("obj12") + " <:destabilizer:989856364035715154>\n";
		description += res.getInt("obj13") + " <:chest:989856361540104223>\n";
		description += res.getInt("obj14") + " <:army:989856359178723328>\n";
		description += res.getInt("obj15") + " <:egg:989856365516300289>\n";
		description += res.getInt("obj16") + " <:stone:989856391911047178>\n";
		description += res.getInt("obj17") + " <a:lightning:989856374731210752>\n";
		description += res.getInt("obj18") + " <:medaille1:989856376715083806>\n";
		description += res.getInt("obj19") + " <:medaille2:989856378317307925>\n";
		description += res.getInt("obj20") + " <:medaille3:989856380443824128>\n";
		description += res.getInt("def") + " <a:def:989856363104579604>\n";
		embed.setDescription(description);
		channel.sendMessageEmbeds(embed.build()).queue();
	}
}
