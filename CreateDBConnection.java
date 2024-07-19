package bankAccount;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class CreateDBConnection {

	private static final String PATH = "com.mysql.cj.jdbc.Driver" ;
	private static final String URL = "jdbc:mysql://localhost:3306/bankdb" ;
	private static final String USER = "root";
	private static final String PASS = "root" ;
	
	public static Connection createConnection()
	{
		try {
			Class.forName(PATH) ;		//register driver
		} catch (ClassNotFoundException e) {
			System.out.println("Issue while registering the server!");
		}
		
		try {
			
			return DriverManager.getConnection(URL, USER, PASS) ;		//make connection
		} catch (SQLException e) {
			
			System.out.println("Issue in connecting DB");
			return null ;
		}
	}
	
}
