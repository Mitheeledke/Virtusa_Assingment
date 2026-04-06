package Services;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBConnection {
	
	public static final String url = "jdbc:mysql://localhost:3306/";
	public static final String user = "root";
	public static final String pass = "1234";
	
	public static Connection getConnection() throws Exception {
		return DriverManager.getConnection(url, user, pass);
	}

}
