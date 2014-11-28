package dao;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

import modelo.Treinador;

public class SessaoTreinoDao {
	/*
	public void adiciona(Treino treino) {
		String sql = "insert into sessaotreino "
				+ "(hora,data)" + " values (?,?)";

		try {

			Statement stat1 = conexao.createStatement(); 
			stat1.execute("set search_path to corridausp"); 
			// prepared statement para inserção
			PreparedStatement stmt = conexao.prepareStatement(sql);

			// seta os valores
			stmt.setString(1, treinador.getNome());
			stmt.setString(2, treinador.getSenha());
			stmt.setString(3, treinador.getEmail());
			stmt.setString(4, treinador.getCurriculo());
			stmt.setString(5, treinador.getNumTelefone());

			// executa
			stmt.execute();
			stmt.close();
		} catch (SQLException e) {
			// A SQLException é "encapsulada" em uma RuntimeException
			// para desacoplar o código da API de JDBC
			throw new RuntimeException(e);
		}
	}
	*/
}
