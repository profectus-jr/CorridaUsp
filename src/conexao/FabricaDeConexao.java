/* Exemplo adaptado de:  http://www.caelum.com.br/apostila-java-web/bancos-de-dados-e-jdbc/  */

package conexao;

import java.sql.*;

public class FabricaDeConexao {

	public static final String NOME_DRIVER = "org.postgresql.Driver";
	public static final String URL_BD = "jdbc:postgresql://data.ime.usp.br:23001/bd_mac439_2014_grupo1";
	public static final String USUARIO_BD = "u5984327";
	public static final String SENHA_BD = "5984327";

	private static FabricaDeConexao fabricaDeConexao = null;

	private FabricaDeConexao() {
		try {
			Class.forName(NOME_DRIVER);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	public Connection obterConexao() {
		try {
			return DriverManager.getConnection(URL_BD, USUARIO_BD, SENHA_BD);
		} catch (SQLException e) {
			// A SQLException é "encapsulada" em uma RuntimeException
			// para desacoplar o código da API de JDBC
			throw new RuntimeException(e);
		}
	}
	
	public static FabricaDeConexao obterInstancia() {
		// A fábrica é um singleton
		if (fabricaDeConexao == null) {
			fabricaDeConexao = new FabricaDeConexao();
		}
		return fabricaDeConexao;
	}

}
