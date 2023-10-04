package dbconnection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class Connect {
	
	//private static String path = "jdbc:mysql://localhost/heart_breakers";
	//private static String user = "root";
	//private static String pass = "";
	
	public static Connection fazer_conexao() throws SQLException{
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			//return DriverManager.getConnection(path, user, pass);
			return DriverManager.getConnection("jdbc:mysql://localhost/heart_breakers","root","");
			
		} catch (ClassNotFoundException e) {
			throw new SQLException(e.getException());
		}	
	}
}