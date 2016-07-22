package br.com.contatos.helper;
import java.sql.Connection;
import java.sql.DriverManager;

public class MySqlConnect {

	public static Connection ConectarDb(){
		Connection con = null;

		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://10.107.134.48/contatos", "root", "root");

		} catch(Exception e){ System.out.println("ERROR!"); }

		return con;
	}
}