package br.com.jmesystemv1.factory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;





public final class Singleton {

	private static Singleton instance = null;

	private static final String USUARIO = "root";
	private static final String SENHA = "root";
	private static final String URL = "jdbc:mysql://localhost:3306/Drogaria";
	
	private Singleton() throws SQLException {
		DriverManager.registerDriver(new com.mysql.jdbc.Driver());
		Connection conexao = (Connection) DriverManager.getConnection(URL,USUARIO, SENHA);
		
		System.out.println( "Conectado");
	}

	public static Singleton getInstance() throws SQLException {

		if (instance == null) {
			instance = new Singleton();

		}

		return instance;
	}
	
	
	
}


