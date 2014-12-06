package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import modelo.Corredor;
import conexao.Cryptography;
import conexao.FabricaDeConexao;


public class CorredorDao {
	
	// a conexão com o banco de dados
	private Connection conexao;

	public CorredorDao() {
		this.conexao = FabricaDeConexao.obterInstancia().obterConexao();
	}

	public void adiciona(Corredor corredor) {
		String sql = "insert into Corredor "
				+ "(nome,senha,email,data_nascimento,peso,altura,sexo,atestado_medico,num_telefone)" + " values (?,?,?,?,?,?,?,?,?)";

		try {

			Statement stat1 = conexao.createStatement(); 
			stat1.execute("set search_path to corridausp"); 
			// prepared statement para inserção
			PreparedStatement stmt = conexao.prepareStatement(sql);

			// seta os valores

			stmt.setString(1, corredor.getNome());
			stmt.setString(2, corredor.getSenha());
			stmt.setString(3, corredor.getEmail());
			stmt.setDate(4, new java.sql.Date(corredor.getData_nascimento().getTime()));
			stmt.setFloat(5, corredor.getPeso());
			stmt.setFloat(6, corredor.getAltura());
			stmt.setString(7, corredor.getSexo()+"");
			stmt.setBoolean(8, corredor.isAtestado_medico());
			stmt.setString(9, corredor.getNumTelefone());
			
			// executa
			stmt.execute();
			stmt.close();
		} catch (SQLException e) {
			// A SQLException é "encapsulada" em uma RuntimeException
			// para desacoplar o código da API de JDBC
			throw new RuntimeException(e);
		}
	}
	
	public boolean isCorredor(Corredor corredor) {
		Corredor cor = new Corredor();
		int id = 0;
		try {
			Statement stat1 = conexao.createStatement(); 
			stat1.execute("set search_path to corridausp");
			PreparedStatement stat = conexao.prepareStatement("SELECT * FROM corredor WHERE email = ?");
			stat.clearParameters(); 
			stat.setString(1, corredor.getEmail());
			ResultSet resp = stat.executeQuery();
			while (resp.next()) {
				cor.setSenha(resp.getString("senha"));
				id = resp.getInt(1);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String senhaCripto = new Cryptography().geraMd5(corredor.getSenha());
		if (cor != null && cor.getSenha().equals(senhaCripto)) {
			corredor.setId(id);
			return true;
		}				
		return false;
	}
}