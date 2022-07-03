package Main;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class SQLRequest {

	private static Connection con;
	
	private void getConnection() throws ClassNotFoundException, SQLException {
		Class.forName("org.sqlite.JDBC");
		con = DriverManager.getConnection("jdbc:sqlite:src/Main/RaidAdventure.db");
		initialise();
	}
	
	public SQLRequest() throws ClassNotFoundException, SQLException {
		getConnection();
	}
	
	public ResultSet request(String request) throws SQLException, ClassNotFoundException {
		if (con == null) {
			getConnection();
		}
		Statement state = con.createStatement();
		ResultSet res = state.executeQuery(request);
		return res;
	}
	
	public void update(String request) throws SQLException {
		PreparedStatement prep = con.prepareStatement(request);
		prep.execute();
		prep.close();
	}
	
	private void initialise() {
		try {
			DatabaseMetaData dbm = con.getMetaData();
			ResultSet tables = dbm.getTables(null, null, "Carte", null);
			if (!tables.next()) {
				update("CREATE TABLE \"Boss\" (\r\n"
						+ "	\"id_server\"	INTEGER NOT NULL,\r\n"
						+ "	\"pv\"	INTEGER NOT NULL,\r\n"
						+ "	\"pv_max\"	INTEGER NOT NULL,\r\n"
						+ "	\"nom_attaque1\"	TEXT NOT NULL,\r\n"
						+ "	\"degats1\"	INTEGER NOT NULL,\r\n"
						+ "	\"nom_attaque2\"	TEXT NOT NULL,\r\n"
						+ "	\"degats2\"	INTEGER NOT NULL,\r\n"
						+ "	\"nom_attaque3\"	TEXT NOT NULL,\r\n"
						+ "	\"degats3\"	INTEGER NOT NULL,\r\n"
						+ "	\"url_etat1\"	TEXT NOT NULL,\r\n"
						+ "	\"url_etat2\"	TEXT NOT NULL,\r\n"
						+ "	\"url_etat3\"	TEXT NOT NULL,\r\n"
						+ "	\"anti_def\"	INTEGER NOT NULL,\r\n"
						+ "	\"gain0\"	INTEGER NOT NULL,\r\n"
						+ "	\"gain1\"	INTEGER NOT NULL,\r\n"
						+ "	\"gain2\"	INTEGER NOT NULL,\r\n"
						+ "	\"gain3\"	INTEGER NOT NULL,\r\n"
						+ "	\"gain4\"	INTEGER NOT NULL,\r\n"
						+ "	\"gain5\"	INTEGER NOT NULL,\r\n"
						+ "	\"gain6\"	INTEGER NOT NULL,\r\n"
						+ "	\"gain7\"	INTEGER NOT NULL,\r\n"
						+ "	\"gain8\"	INTEGER NOT NULL,\r\n"
						+ "	\"gain9\"	INTEGER NOT NULL,\r\n"
						+ "	\"gain10\"	INTEGER NOT NULL,\r\n"
						+ "	\"gain11\"	INTEGER NOT NULL,\r\n"
						+ "	\"gain12\"	INTEGER NOT NULL,\r\n"
						+ "	\"gain13\"	INTEGER NOT NULL,\r\n"
						+ "	\"gain14\"	INTEGER NOT NULL,\r\n"
						+ "	\"gain15\"	INTEGER NOT NULL,\r\n"
						+ "	\"gain16\"	NUMERIC NOT NULL,\r\n"
						+ "	\"gain17\"	INTEGER NOT NULL,\r\n"
						+ "	\"gain18\"	INTEGER NOT NULL,\r\n"
						+ "	\"gain19\"	INTEGER NOT NULL,\r\n"
						+ "	\"gain20\"	INTEGER NOT NULL,\r\n"
						+ "	\"id_message\"	INTEGER NOT NULL\r\n"
						+ ");");
				update("CREATE TABLE \"Carte\" (\r\n"
						+ "	\"id_server\"	INTEGER NOT NULL,\r\n"
						+ "	\"id_member\"	INTEGER NOT NULL,\r\n"
						+ "	\"nom_maitre\"	TEXT NOT NULL,\r\n"
						+ "	\"url_maitre\"	TEXT,\r\n"
						+ "	\"code_ami\"	TEXT,\r\n"
						+ "	\"pseudo_switch\"	TEXT,\r\n"
						+ "	\"numero_familier\"	INTEGER NOT NULL,\r\n"
						+ "	\"nombre_boss\"	INTEGER NOT NULL,\r\n"
						+ "	\"fetiche\"	TEXT\r\n"
						+ ");");
				update("CREATE TABLE \"Familiers\" (\r\n"
						+ "	\"id_server\"	INTEGER NOT NULL DEFAULT 0,\r\n"
						+ "	\"id_member\"	INTEGER NOT NULL DEFAULT 0,\r\n"
						+ "	\"expf1\"	INTEGER NOT NULL DEFAULT 0,\r\n"
						+ "	\"pvf1\"	INTEGER NOT NULL DEFAULT 0,\r\n"
						+ "	\"pmf1\"	INTEGER NOT NULL DEFAULT 0,\r\n"
						+ "	\"versionf1\"	INTEGER NOT NULL DEFAULT 1,\r\n"
						+ "	\"expf2\"	INTEGER NOT NULL DEFAULT 0,\r\n"
						+ "	\"pvf2\"	INTEGER NOT NULL DEFAULT 0,\r\n"
						+ "	\"pmf2\"	INTEGER NOT NULL DEFAULT 0,\r\n"
						+ "	\"versionf2\"	INTEGER NOT NULL DEFAULT 0,\r\n"
						+ "	\"expf3\"	INTEGER NOT NULL DEFAULT 0,\r\n"
						+ "	\"pvf3\"	INTEGER NOT NULL DEFAULT 0,\r\n"
						+ "	\"pmf3\"	INTEGER NOT NULL DEFAULT 0,\r\n"
						+ "	\"versionf3\"	INTEGER NOT NULL DEFAULT 0,\r\n"
						+ "	\"expf4\"	INTEGER NOT NULL DEFAULT 0,\r\n"
						+ "	\"pvf4\"	INTEGER NOT NULL DEFAULT 0,\r\n"
						+ "	\"pmf4\"	INTEGER NOT NULL DEFAULT 0,\r\n"
						+ "	\"versionf4\"	INTEGER NOT NULL DEFAULT 0,\r\n"
						+ "	\"expf5\"	INTEGER NOT NULL DEFAULT 0,\r\n"
						+ "	\"pvf5\"	INTEGER NOT NULL DEFAULT 0,\r\n"
						+ "	\"pmf5\"	INTEGER NOT NULL DEFAULT 0,\r\n"
						+ "	\"versionf5\"	INTEGER NOT NULL DEFAULT 0,\r\n"
						+ "	\"expf6\"	INTEGER NOT NULL DEFAULT 0,\r\n"
						+ "	\"pvf6\"	INTEGER NOT NULL DEFAULT 0,\r\n"
						+ "	\"pmf6\"	INTEGER NOT NULL DEFAULT 0,\r\n"
						+ "	\"versionf6\"	INTEGER NOT NULL DEFAULT 0,\r\n"
						+ "	\"expf7\"	INTEGER NOT NULL DEFAULT 0,\r\n"
						+ "	\"pvf7\"	INTEGER NOT NULL DEFAULT 0,\r\n"
						+ "	\"pmf7\"	INTEGER NOT NULL DEFAULT 0,\r\n"
						+ "	\"versionf7\"	INTEGER NOT NULL DEFAULT 0,\r\n"
						+ "	\"expf8\"	INTEGER NOT NULL DEFAULT 0,\r\n"
						+ "	\"pvf8\"	INTEGER NOT NULL DEFAULT 0,\r\n"
						+ "	\"pmf8\"	INTEGER NOT NULL DEFAULT 0,\r\n"
						+ "	\"versionf8\"	INTEGER NOT NULL DEFAULT 0,\r\n"
						+ "	\"expf9\"	INTEGER NOT NULL DEFAULT 0,\r\n"
						+ "	\"pvf9\"	INTEGER NOT NULL DEFAULT 0,\r\n"
						+ "	\"pmf9\"	INTEGER NOT NULL DEFAULT 0,\r\n"
						+ "	\"versionf9\"	INTEGER NOT NULL DEFAULT 0,\r\n"
						+ "	\"expf10\"	INTEGER NOT NULL DEFAULT 0,\r\n"
						+ "	\"pvf10\"	INTEGER NOT NULL DEFAULT 0,\r\n"
						+ "	\"pmf10\"	INTEGER NOT NULL DEFAULT 0,\r\n"
						+ "	\"versionf10\"	INTEGER NOT NULL DEFAULT 0,\r\n"
						+ "	\"expf11\"	INTEGER NOT NULL DEFAULT 0,\r\n"
						+ "	\"pvf11\"	INTEGER NOT NULL DEFAULT 0,\r\n"
						+ "	\"pmf11\"	INTEGER NOT NULL DEFAULT 0,\r\n"
						+ "	\"versionf11\"	INTEGER NOT NULL DEFAULT 0,\r\n"
						+ "	\"expf12\"	INTEGER NOT NULL DEFAULT 0,\r\n"
						+ "	\"pvf12\"	INTEGER NOT NULL DEFAULT 0,\r\n"
						+ "	\"pmf12\"	INTEGER NOT NULL DEFAULT 0,\r\n"
						+ "	\"versionf12\"	INTEGER NOT NULL DEFAULT 0,\r\n"
						+ "	\"expf13\"	INTEGER NOT NULL DEFAULT 0,\r\n"
						+ "	\"pvf13\"	INTEGER NOT NULL DEFAULT 0,\r\n"
						+ "	\"pmf13\"	INTEGER NOT NULL DEFAULT 0,\r\n"
						+ "	\"versionf13\"	INTEGER NOT NULL DEFAULT 0,\r\n"
						+ "	\"expf14\"	INTEGER NOT NULL DEFAULT 0,\r\n"
						+ "	\"pvf14\"	INTEGER NOT NULL DEFAULT 0,\r\n"
						+ "	\"pmf14\"	INTEGER NOT NULL DEFAULT 0,\r\n"
						+ "	\"versionf14\"	INTEGER NOT NULL DEFAULT 0,\r\n"
						+ "	\"expf15\"	INTEGER NOT NULL DEFAULT 0,\r\n"
						+ "	\"pvf15\"	INTEGER NOT NULL DEFAULT 0,\r\n"
						+ "	\"pmf15\"	INTEGER NOT NULL DEFAULT 0,\r\n"
						+ "	\"versionf15\"	INTEGER NOT NULL DEFAULT 0,\r\n"
						+ "	\"expf16\"	INTEGER NOT NULL DEFAULT 0,\r\n"
						+ "	\"pvf16\"	INTEGER NOT NULL DEFAULT 0,\r\n"
						+ "	\"pmf16\"	INTEGER NOT NULL DEFAULT 0,\r\n"
						+ "	\"versionf16\"	INTEGER NOT NULL DEFAULT 0,\r\n"
						+ "	\"expf17\"	INTEGER NOT NULL DEFAULT 0,\r\n"
						+ "	\"pvf17\"	INTEGER NOT NULL DEFAULT 0,\r\n"
						+ "	\"pmf17\"	INTEGER NOT NULL DEFAULT 0,\r\n"
						+ "	\"versionf17\"	INTEGER NOT NULL DEFAULT 0,\r\n"
						+ "	\"expf18\"	INTEGER NOT NULL DEFAULT 0,\r\n"
						+ "	\"pvf18\"	INTEGER NOT NULL DEFAULT 0,\r\n"
						+ "	\"pmf18\"	INTEGER NOT NULL DEFAULT 0,\r\n"
						+ "	\"versionf18\"	INTEGER NOT NULL DEFAULT 0,\r\n"
						+ "	\"expf19\"	INTEGER NOT NULL DEFAULT 0,\r\n"
						+ "	\"pvf19\"	INTEGER NOT NULL DEFAULT 0,\r\n"
						+ "	\"pmf19\"	INTEGER NOT NULL DEFAULT 0,\r\n"
						+ "	\"versionf19\"	INTEGER NOT NULL DEFAULT 0,\r\n"
						+ "	\"expf20\"	INTEGER NOT NULL DEFAULT 0,\r\n"
						+ "	\"pvf20\"	INTEGER NOT NULL DEFAULT 0,\r\n"
						+ "	\"pmf20\"	INTEGER NOT NULL DEFAULT 0,\r\n"
						+ "	\"versionf20\"	INTEGER NOT NULL DEFAULT 0,\r\n"
						+ "	\"touchable\"	INTEGER NOT NULL DEFAULT 0\r\n"
						+ ");");
				update("CREATE TABLE \"FamiliersEmbeds\" (\r\n"
						+ "	\"id_server\"	INTEGER NOT NULL,\r\n"
						+ "	\"id_member\"	INTEGER NOT NULL,\r\n"
						+ "	\"id_message\"	INTEGER NOT NULL,\r\n"
						+ "	\"page\"	INTEGER NOT NULL\r\n"
						+ ");");
				update("CREATE TABLE \"Inventaire\" (\r\n"
						+ "	\"id_server\"	INTEGER NOT NULL,\r\n"
						+ "	\"id_member\"	INTEGER NOT NULL,\r\n"
						+ "	\"pieces\"	INTEGER NOT NULL,\r\n"
						+ "	\"obj1\"	INTEGER NOT NULL,\r\n"
						+ "	\"obj2\"	INTEGER NOT NULL,\r\n"
						+ "	\"obj3\"	INTEGER NOT NULL,\r\n"
						+ "	\"obj4\"	INTEGER NOT NULL,\r\n"
						+ "	\"obj5\"	INTEGER NOT NULL,\r\n"
						+ "	\"obj6\"	INTEGER NOT NULL,\r\n"
						+ "	\"obj7\"	INTEGER NOT NULL,\r\n"
						+ "	\"obj8\"	INTEGER NOT NULL,\r\n"
						+ "	\"obj9\"	INTEGER NOT NULL,\r\n"
						+ "	\"obj10\"	INTEGER NOT NULL,\r\n"
						+ "	\"obj11\"	INTEGER NOT NULL,\r\n"
						+ "	\"obj12\"	INTEGER NOT NULL,\r\n"
						+ "	\"obj13\"	INTEGER NOT NULL,\r\n"
						+ "	\"obj14\"	INTEGER NOT NULL,\r\n"
						+ "	\"obj15\"	INTEGER NOT NULL,\r\n"
						+ "	\"obj16\"	INTEGER NOT NULL,\r\n"
						+ "	\"obj17\"	INTEGER NOT NULL,\r\n"
						+ "	\"obj18\"	INTEGER NOT NULL,\r\n"
						+ "	\"obj19\"	INTEGER NOT NULL,\r\n"
						+ "	\"obj20\"	INTEGER NOT NULL,\r\n"
						+ "	\"def\"	INTEGER NOT NULL\r\n"
						+ ");");
				update("CREATE TABLE \"Participants\" (\r\n"
						+ "	\"id_server\"	INTEGER NOT NULL,\r\n"
						+ "	\"id_membre\"	INTEGER NOT NULL\r\n"
						+ ");");
				update("CREATE TABLE \"Prefixes\" (\r\n"
						+ "	\"id_server\"	INTEGER NOT NULL,\r\n"
						+ "	\"prefixe\"	TEXT NOT NULL\r\n"
						+ ");");
			}
		} catch (SQLException e) {
		}
	}
}
