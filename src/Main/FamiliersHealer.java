package Main;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class FamiliersHealer {

	public FamiliersHealer() {
	}
	
	public String heal(ResultSet res) throws SQLException {
		ArrayList<String> line = new ArrayList<String>();
		line.add(res.getString("id_server"));
		line.add(res.getString("id_member"));
		for (int iterator = 0; iterator < 20; iterator++) {
			line.add(res.getString("expf" + String.valueOf(iterator + 1)));
			line.add(res.getString("pvf" + String.valueOf(iterator + 1)));
			line.add(res.getString("pmf" + String.valueOf(iterator + 1)));
			line.add(res.getString("versionf" + String.valueOf(iterator + 1)));
		}
		line.add(res.getString("touchable"));
		return "";
	}
}
