package de.database.mysql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MySQL {

	private static Connection connection;
	private static String host, port, database, user, password;
	
	public MySQL() {
	
		this.host = "";
		this.port = "3306";
		this.database = "";
		this.user = "";
		this.password = "";
		
		try {
			openConnection();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void close() throws SQLException {
		connection.close();
	}
	
	public Connection openConnection() throws SQLException {
		return connection = DriverManager.getConnection("jdbc:mysql://" + host + ":" + port + "/" + database + "?autoReconnect=true", user, password);
	}
	
	public boolean isConnected() {
		return connection == null ? false : true;
	}

	public Connection getConnection() {
		return connection;
	}
  
  	/* 
	 * Example:
	 * 
	 * query = Permission
	 * condition = PlayerUUID(18238912739812)
	 * tablename = PermissionTable
	 * 
	 * IF MySQL finds a permission with the key of the players uuid it will return 'true' else 'false'
	 * 
	 * */
	public boolean isSet(String query, String condition, String tablename) throws SQLException {
		PreparedStatement ps = getConnection().prepareStatement("SELECT ? FROM ? WHERE UUID = ?");
		try {
			ps.setString(1, query);
			ps.setString(2, tablename);
			ps.setString(3, condition);
			ResultSet rs = ps.executeQuery();
			return rs.next();
		} finally {
			ps.close();
		}
	}
	
	public void createTable(String tablename) throws SQLException {
		PreparedStatement ps = getConnection().prepareStatement("CREATE TABLE IF NOT EXISTS ? (value1 VARCHAR(100), value2 VHARCAHR(100))");
		try {
			ps.setString(1, tablename);
			ps.executeUpdate();
		} finally {
			ps.close();
		}
	}
	
}
