package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import modelo.TelTreinador;
import conexao.FabricaDeConexao;

public class TelTreinadorDao {

	private Connection conexao;

	public TelTreinadorDao() {
		this.conexao = FabricaDeConexao.obterInstancia().obterConexao();
	}

	public void adiciona(TelTreinador telTreinador) {
		String sql = "insert into teltreinador " + "(id_treinador,telefone)"
				+ " values (?,?)";
		try {

			Statement stat1 = conexao.createStatement();
			stat1.execute("set search_path to corridausp");

			PreparedStatement stmt = conexao.prepareStatement(sql);

			stmt.setInt(1, telTreinador.getIdTreinador());
			stmt.setString(2, telTreinador.getNumTelefone());

			stmt.execute();
			stmt.close();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
}
