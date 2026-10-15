package newagents;

import java.sql.*;

public class DatabaseConnection {

	private static final String URL = "jdbc:mysql://localhost:3306/newsagents_db";
	
	private static final String USERNAME = "root";
	
	private static final String PASSWORD = "admin";
	
	public static Connection getConnection() throws SQLException {
			return DriverManager.getConnection(URL,USERNAME,PASSWORD);
	}
	
}
