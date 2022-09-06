package Main;

import net.dv8tion.jda.api.entities.User;

public class Chest {
	private int number_loots;
	
	public Chest(SQLRequest req, User user, int number_loots) {
		this.number_loots = number_loots;
	}
	
}
