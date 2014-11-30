package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

import modelo.Corrida;
import conexao.FabricaDeConexao;

public class CorridaDao {


	// a conexão com o banco de dados
	private Connection conexao;

	public CorridaDao() {
		this.conexao = FabricaDeConexao.obterInstancia().obterConexao();
	}

	public void adiciona(Corrida corrida) {
		String sql = "insert into corrida "
				+ "(nome, descricao,id_sessao)" + " values (?,?,?)";

		try {

			Statement stat1 = conexao.createStatement(); 
			stat1.execute("set search_path to corridausp"); 
			// prepared statement para inserção
			PreparedStatement stmt = conexao.prepareStatement(sql);

			// seta os valores
			stmt.setString(1, corrida.getNome());
			stmt.setString(2, corrida.getDescricao());
			stmt.setInt(3, corrida.getId_sessao());

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