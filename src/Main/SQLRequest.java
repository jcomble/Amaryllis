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
	
	private void initialise_boss() throws SQLException {
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
	}
	
	private void initialise_carte() throws SQLException {
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
	}
	
	private void initialise_couleurs() throws SQLException {
		update("CREATE TABLE \"Couleurs\" (\r\n"
				+ "	\"id_server\"	INTEGER NOT NULL,\r\n"
				+ "	\"couleur\"	INTEGER NOT NULL\r\n"
				+ ");");
	}
	
	private void initialise_emojis() throws SQLException {
		update("CREATE TABLE \"Emojis\" (\r\n"
				+ "	\"id_server\"	INTEGER NOT NULL DEFAULT 0,\r\n"
				+ "	\"emoji1\"	TEXT NOT NULL DEFAULT '<:babyyoshi:990618756428488714>',\r\n"
				+ "	\"emoji2\"	TEXT NOT NULL DEFAULT '<a:luma:990618540748984371>',\r\n"
				+ "	\"emoji3\"	TEXT NOT NULL DEFAULT '<a:bobomb:990633474622881807>',\r\n"
				+ "	\"emoji4\"	TEXT NOT NULL DEFAULT '<a:chabloc:990618429771882627>',\r\n"
				+ "	\"emoji5\"	TEXT NOT NULL DEFAULT '<a:blooper:990618901232619612>',\r\n"
				+ "	\"emoji6\"	TEXT NOT NULL DEFAULT '<a:boo:990626121265586217>',\r\n"
				+ "	\"emoji7\"	TEXT NOT NULL DEFAULT '<a:Cheepcheep:990618537984946266>',\r\n"
				+ "	\"emoji8\"	TEXT NOT NULL DEFAULT '<a:plantepiranha:990618544263790642>',\r\n"
				+ "	\"emoji9\"	TEXT NOT NULL DEFAULT '<a:Bloc:990618388684476557>',\r\n"
				+ "	\"emoji10\"	TEXT NOT NULL DEFAULT '<a:poochy:990630895541186570>',\r\n"
				+ "	\"emoji11\"	TEXT NOT NULL DEFAULT '<a:yoshi:990627733585403934>',\r\n"
				+ "	\"emoji12\"	TEXT NOT NULL DEFAULT '<a:Booxeur:990637040016162868>',\r\n"
				+ "	\"emoji13\"	TEXT NOT NULL DEFAULT '<a:wiggler:990627354890088478>',\r\n"
				+ "	\"emoji14\"	TEXT NOT NULL DEFAULT '<a:thwomp:990631522061148240>',\r\n"
				+ "	\"emoji15\"	TEXT NOT NULL DEFAULT '<a:Tocolan:990627947813683281>',\r\n"
				+ "	\"emoji16\"	TEXT NOT NULL DEFAULT '<:stingby:990628202521169950>',\r\n"
				+ "	\"emoji17\"	TEXT NOT NULL DEFAULT '<a:ninji:990626580076310628>',\r\n"
				+ "	\"emoji18\"	TEXT NOT NULL DEFAULT '<a:MegaMole:990629454688714782>',\r\n"
				+ "	\"emoji19\"	TEXT NOT NULL DEFAULT '<a:goomba:990634177714073601>',\r\n"
				+ "	\"emoji20\"	TEXT NOT NULL DEFAULT '<a:chainchomp:990618536340779048>'\r\n"
				+ ");");
	}
	
	private void initialise_familiers() throws SQLException {
		update("CREATE TABLE \"Familiers\" (\r\n"
				+ "	\"id_server\"	INTEGER NOT NULL DEFAULT 0,\r\n"
				+ "	\"id_member\"	INTEGER NOT NULL DEFAULT 0,\r\n"
				+ "	\"expf1\"	INTEGER NOT NULL DEFAULT 0,\r\n"
				+ "	\"pvf1\"	INTEGER NOT NULL DEFAULT 20,\r\n"
				+ "	\"pmf1\"	INTEGER NOT NULL DEFAULT 50,\r\n"
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
	}
	
	private void initialise_familiersembeds() throws SQLException {
		update("CREATE TABLE \"FamiliersEmbeds\" (\r\n"
			+ "	\"id_server\"	INTEGER NOT NULL,\r\n"
			+ "	\"id_member\"	INTEGER NOT NULL,\r\n"
			+ "	\"id_message\"	INTEGER NOT NULL,\r\n"
			+ "	\"page\"	INTEGER NOT NULL\r\n"
			+ ");");
	}
	
	private void initialise_inventaire() throws SQLException {
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
	}
	
	private void initialise_participants() throws SQLException {
		update("CREATE TABLE \"Participants\" (\r\n"
				+ "	\"id_server\"	INTEGER NOT NULL,\r\n"
				+ "	\"id_membre\"	INTEGER NOT NULL\r\n"
				+ ");");
	}
	
	private void initialise_prefixe() throws SQLException {
		update("CREATE TABLE \"Prefixes\" (\r\n"
				+ "	\"id_server\"	INTEGER NOT NULL,\r\n"
				+ "	\"prefixe\"	INTEGER NOT NULL\r\n"
				+ ");");
	}
	
	private void initialise() throws SQLException {
		DatabaseMetaData dbm = con.getMetaData();
		ResultSet tables = dbm.getTables(null, null, "Carte", null);
		if (!tables.next()) {
			initialise_boss();
			initialise_carte();
			initialise_couleurs();
			initialise_emojis();
			initialise_familiers();
			initialise_familiersembeds();
			initialise_inventaire();
			initialise_participants();
			initialise_prefixe();
		}
	}
}
