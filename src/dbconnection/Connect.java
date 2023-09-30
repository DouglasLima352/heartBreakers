package dbconnection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class Connect {
	
	//private static String caminho = "jdbc:mysql://localhost/heart_breakers";
	//private static String usuario = "root";
	//private static String senha = "";
	
	public static Connection fazer_conexao() throws SQLException{
		
		try {
			
			Class.forName("com.mysql.cj.jdbc.Driver");
			//return DriverManager.getConnection(caminho, usuario, senha);
			return DriverManager.getConnection("jdbc:mysql://localhost/heart_breakers","root","");
			
		} catch (ClassNotFoundException e) {
			throw new SQLException(e.getException());
		}	
	}
}