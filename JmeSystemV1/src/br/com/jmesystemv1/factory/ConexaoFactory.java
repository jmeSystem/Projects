package br.com.jmesystemv1.factory;

import java.sql.DriverManager;
import java.sql.SQLException;



import com.mysql.jdbc.Connection;

public class ConexaoFactory {
	// final - � pq vai ser constante e n�o vai mudar
	private static final String USUARIO = "root";
	private static final String SENHA = "root";
	private static final String URL = "jdbc:mysql://localhost:3306/jmesystemv1";

	public static Connection conectar() throws SQLException {
		DriverManager.registerDriver(new com.mysql.jdbc.Driver());
		Connection conexao = (Connection) DriverManager.getConnection(URL,
				USUARIO, SENHA);
		return conexao;
		
		
	}

//	public static void main(String[] args) {
//		try {
//			Connection conexao = ConexaoFactory.conectar();
//			System.out.println( "Conex�o realizada com sucesso!");
//		} catch (SQLException ex) {
//			// mostra o tipo do erro.
//			ex.printStackTrace();
//			System.out.println( "N�o foi possivel realizar a conex�o!");
//		}
//
//	}
}
