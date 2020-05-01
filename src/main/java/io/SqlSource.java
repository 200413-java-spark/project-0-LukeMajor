package io;

/**
 * A singleton to keep access to the DB restricted.
 */

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class SqlSource {
	private String url = System.getProperty("database.url", "jdbc:postgresql://52.15.170.98:5432/luke");
	private String username = System.getProperty("databse.username", "luke");
	private String password = System.getProperty("database.password", "luke");
	private static SqlSource instance;
	
	public SqlSource() {
		
	}
	
	public static SqlSource getInstance() {
		if (instance == null) {
			instance = new SqlSource();
		}
		return instance;
	}
	
	public Connection getConnection() throws SQLException {
		return DriverManager.getConnection(url, username, password);
	}

}
