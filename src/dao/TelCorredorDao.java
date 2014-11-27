package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

import modelo.TelCorredor;
import conexao.FabricaDeConexao;

public class TelCorredorDao {

	private Connection conexao;

	public TelCorredorDao() {
		this.conexao = FabricaDeConexao.obterInstancia().obterConexao();
	}

	public void adiciona(TelCorredor telCorredor) {
		String sql = "insert into telcorredor " + "(id_corredor,telefone)"
				+ " values (?,?)";
		try {

			Statement stat1 = conexao.createStatement();
			stat1.execute("set search_path to corridausp");

			PreparedStatement stmt = conexao.prepareStatement(sql);

			stmt.setInt(1, telCorredor.getIdCorredor());
			stmt.setString(2, telCorredor.getNumTelefone());

			stmt.execute();
			stmt.close();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
}
