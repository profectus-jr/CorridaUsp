package dao;

import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

import modelo.Corredor;
import conexao.FabricaDeConexao;


public class CorredorDao {
	
	// a conexão com o banco de dados
	private Connection conexao;

	public CorredorDao() {
		this.conexao = FabricaDeConexao.obterInstancia().obterConexao();
	}

	public void adiciona(Corredor corredor) {
		String sql = "insert into Corredor "
				+ "(nome,senha,email,data_nascimento,peso,altura,sexo,atestado_medico)" + " values (?,?,?,?,?,?,?,?,?)";

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

	/*public void altera(Corredor corredor) {
		String sql = "update contatos set email=?, senha=?, peso=?, altura=?"
				+ "where email=?";
		try {
			PreparedStatement stmt = conexao.prepareStatement(sql);
			stmt.setString(1, corredor.getEmail());
			stmt.setString(2, corredor.getSenha());
			stmt.setFloat(3, corredor.getPeso());
			stmt.setFloat(4, corredor.getAltura());
			stmt.setString(4, corredor.getEmail());
			stmt.execute();
			stmt.close();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}*/

	/*public void remove(Contato contato) {
		try {
			PreparedStatement stmt = conexao.prepareStatement("delete "
					+ "from contatos where id=?");
			stmt.setLong(1, contato.getId());
			stmt.execute();
			stmt.close();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

*/
}