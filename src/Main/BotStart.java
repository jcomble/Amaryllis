package Main;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import javax.security.auth.login.LoginException;

import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.requests.GatewayIntent;

public class BotStart {
	
	private static String readString(String path_map) {
		try {
			byte[] bytes = Files.readAllBytes(Paths.get(path_map));
			String content = "";
			for (byte tmp_byte : bytes) {
				content = content + (char) tmp_byte;
			}
			return content;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	
    public static void main(String[] args) throws LoginException {
    	String token = readString("src/Main/token.txt");
        JDABuilder.createLight(token, GatewayIntent.GUILD_MESSAGES, GatewayIntent.DIRECT_MESSAGES)
            .addEventListeners(new Commands())
            .setActivity(Activity.playing("Yamete Kudasai!"))
            .build();
    }
}