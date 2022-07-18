package Main;

import java.sql.ResultSet;
import java.sql.SQLException;

public class FamiliersHealer {

	public FamiliersHealer() {
	}
	
	public String heal(ResultSet res) throws SQLException {
		String result_request = "UPDATE Familiers SET ";
		for (int iterator = 0; iterator < 20; iterator++) {
			int EXP_familier_i = res.getInt("expf" + String.valueOf(iterator + 1));
			int PV_familier_i = res.getInt("pvf" + String.valueOf(iterator + 1));
			int PM_familier_i = res.getInt("pmf" + String.valueOf(iterator + 1));
			int VERSION_familier_i = res.getInt("versionf" + String.valueOf(iterator + 1));
			StatsReader stats = new StatsReader(VERSION_familier_i, EXP_familier_i, VERSION_familier_i, PV_familier_i, PM_familier_i);
			int PV_MAX_i = stats.get_PV_MAX();
			int PM_MAX_i = stats.get_PM_MAX();
			if (VERSION_familier_i != 0) {
				PV_familier_i = PV_familier_i + Math.min(2, PV_MAX_i - PV_familier_i);
				PM_familier_i = PM_familier_i + Math.min(2, PM_MAX_i - PM_familier_i);
			}
			result_request += "expf" + String.valueOf(iterator) + " = " + String.valueOf(EXP_familier_i) + ", ";
			result_request += "pvf" + String.valueOf(iterator) + " = " + String.valueOf(PV_familier_i) + ", ";
			result_request += "pmf" + String.valueOf(iterator) + " = " + String.valueOf(PM_familier_i) + ", ";
			result_request += "versionf" + String.valueOf(iterator) + " = " + String.valueOf(VERSION_familier_i);
			if (iterator != 19) {
				result_request += ", ";
			}
		}
		result_request += "WHERE id_server = " + res.getString("id_server") + " AND id_member = " + res.getString("id_member") + ";";
		return result_request;
	}
}
