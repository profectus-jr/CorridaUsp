package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

import conexao.FabricaDeConexao;
import modelo.SessaoTreino;

public class SessaoTreinoDao {
	
	private Connection conexao;

	public SessaoTreinoDao() {
		this.conexao = FabricaDeConexao.obterInstancia().obterConexao();
	}
	
	public void adiciona(SessaoTreino sessaotreino) {
		String sql = "insert into sessaotreino "
				+ "(id_treino,hora,data)" + " values (?,?,?)";

		try {
			Statement stat1 = conexao.createStatement(); 
			stat1.execute("set search_path to corridausp"); 
			
			PreparedStatement stmt = conexao.prepareStatement(sql);

			// seta os valores
			stmt.setInt(1, sessaotreino.getIdTreino());
			stmt.setString(2, sessaotreino.getHora().toString());
			stmt.setString(3, sessaotreino.getData().toString());

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
