package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import modelo.Treinador;
import conexao.Cryptography;
import conexao.FabricaDeConexao;

public class TreinadorDao {

	// a conexão com o banco de dados
	private Connection conexao;

	public TreinadorDao() {
		this.conexao = FabricaDeConexao.obterInstancia().obterConexao();
	}
	
	public void adiciona(Treinador treinador) {
		String sql = "insert into treinador "
				+ "(nome,senha,email,curriculo,num_telefone)" + " values (?,?,?,?,?)";

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
	
	public boolean isTreinador(Treinador treinador) {
		Treinador trnd = new Treinador();
		int id = 0;
		try {
			Statement stat1 = conexao.createStatement(); 
			stat1.execute("set search_path to corridausp");
			PreparedStatement stat = conexao.prepareStatement("SELECT * FROM treinador WHERE email = ?");
			stat.clearParameters(); 
			System.out.println("mail: " + treinador.getEmail());
			stat.setString(1, treinador.getEmail());
			ResultSet resp = stat.executeQuery();
			while (resp.next()) {
				trnd.setSenha(resp.getString("senha"));
				id = resp.getInt(1);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("AB");
		String senhaCripto = new Cryptography().geraMd5(treinador.getSenha());
		if (trnd != null && trnd.getSenha().equals(senhaCripto)) {
			treinador.setId(id);
			return true;
		}				
		return false;
	}	
}
