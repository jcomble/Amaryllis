package Main;

import java.sql.SQLException;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.MessageEmbed;
import net.dv8tion.jda.api.entities.MessageReaction;
import net.dv8tion.jda.api.entities.User;

public class PageUpdater {
	private User user;
	private Message message;
	private int page;
	private String emoji_name;
	private SQLRequest req;
	private FamiliersEmojis emojis;
	private MessageReaction reaction;
	
	public PageUpdater(User user, Message message, int page, String emoji_name, SQLRequest req, FamiliersEmojis emojis, MessageReaction reaction) {
		this.user = user;
		this.message = message;
		this.page = page;
		this.emoji_name = emoji_name;
		this.req = req;
		this.emojis = emojis;
		this.reaction = reaction;
	}
	
	public void update() throws SQLException {
		if (emoji_name.equals("⬅️")) {
			page -= 1;
		} else {
			page +=1;
		}
		page = Math.max(1, Math.min(20, page));
		System.out.println("STEP 1");
		System.out.println("UPDATE FamiliersEmbeds SET page = " + String.valueOf(page) + " WHERE id_message = " + message.getId() + ";");
		req.update("UPDATE FamiliersEmbeds SET page = " + String.valueOf(page) + " WHERE id_message = " + message.getId() + ";");
		System.out.println("STEP 2");
		message.editMessage(emojis.get_emojis()[page - 1]).queue();
		EmbedBuilder embed = new EmbedBuilder();
		embed.setDescription("-");
		MessageEmbed msgemb = embed.build();
		message.editMessageEmbeds(msgemb).queue();
		reaction.removeReaction(user).queue();
	}
}
