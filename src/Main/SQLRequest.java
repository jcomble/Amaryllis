package Main;

import java.sql.Connection;
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
}
