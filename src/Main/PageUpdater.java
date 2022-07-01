package Main;

import java.sql.ResultSet;
import java.sql.SQLException;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.MessageEmbed;
import net.dv8tion.jda.api.entities.MessageReaction;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.requests.restaction.MessageAction;

public class PageUpdater {
	private User user;
	private Message message;
	private int page;
	private String emoji_name;
	private SQLRequest req;
	private FamiliersEmojis emojis;
	private MessageReaction reaction;
	private Guild guild;
	
	public PageUpdater(User user, Message message, int page, String emoji_name, SQLRequest req, FamiliersEmojis emojis, MessageReaction reaction, Guild guild) {
		this.user = user;
		this.message = message;
		this.page = page;
		this.emoji_name = emoji_name;
		this.req = req;
		this.emojis = emojis;
		this.reaction = reaction;
		this.guild = guild;
	}
	
	public void update() throws SQLException, ClassNotFoundException {
		if (emoji_name.equals("⬅️")) {
			page -= 1;
		} else {
			page +=1;
		}
		page = Math.max(1, Math.min(20, page));
		ResultSet res = req.request("SELECT * FROM Familiers WHERE id_member = " + user.getId().toString() + " AND id_server = " + guild.getId().toString() + ";");
		int experience = res.getInt("expf" + String.valueOf(page));
		String description = experience == -1 ? "-" : "niv. 1\n 20/20 PV\n 50/50 PM";
		res.close();
		System.out.println("STEP 1");
		System.out.println("UPDATE FamiliersEmbeds SET page = " + String.valueOf(page) + " WHERE id_message = " + message.getId() + ";");
		req.update("UPDATE FamiliersEmbeds SET page = " + String.valueOf(page) + " WHERE id_message = " + message.getId() + ";");
		System.out.println("STEP 2");
		EmbedBuilder embed = new EmbedBuilder();
		embed.setDescription(description);
		MessageEmbed msgemb = embed.build();
		MessageAction msgact = message.editMessage(emojis.get_emojis()[page - 1]);
		msgact.setEmbeds(msgemb);
		msgact.queue();
		reaction.removeReaction(user).queue();
	}
}
