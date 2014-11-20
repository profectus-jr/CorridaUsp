package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

import modelo.Treinador;
import conexao.FabricaDeConexao;

public class TreinadorDao {

	// a conexão com o banco de dados
	private Connection conexao;

	public TreinadorDao() {
		this.conexao = FabricaDeConexao.obterInstancia().obterConexao();
	}

	public void adiciona(Treinador treinador) {
		String sql = "insert into treinador "
				+ "(nome,senha,email,curriculo)" + " values (?,?,?,?)";

		try {

			Statement stat1 = conexao.createStatement(); 
			stat1.execute("set search_path to projfase2"); 
			// prepared statement para inserção
			PreparedStatement stmt = conexao.prepareStatement(sql);

			// seta os valores
			stmt.setString(1, treinador.getNome());
			stmt.setString(2, treinador.getSenha());
			stmt.setString(3, treinador.getEmail());
			stmt.setString(4, treinador.getCurriculo());

			// executa
			stmt.execute();
			stmt.close();
		} catch (SQLException e) {
			// A SQLException é "encapsulada" em uma RuntimeException
			// para desacoplar o código da API de JDBC
			throw new RuntimeException(e);
		}
	}
}
