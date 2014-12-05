package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import conexao.FabricaDeConexao;

public class TreinoCorredorDao {
	
	private Connection conexao;
	
	public TreinoCorredorDao(){
		this.conexao = FabricaDeConexao.obterInstancia().obterConexao();

	}

	public void inscreve(int idtreino, int idcorredor) {
		String sql = "insert into TreinoCorredor "
				+ "(idtreino,idcorredor,data,situacao_inscricao)" + " values (?,?,?,?)";

		try {

			Statement stat1 = conexao.createStatement(); 
			stat1.execute("set search_path to corridausp"); 
			// prepared statement para inserção
			PreparedStatement stmt = conexao.prepareStatement(sql);

			// seta os valores
			DateFormat dateFormat = new SimpleDateFormat();
			Date date = new Date();
			java.sql.Date sqlDate = new java.sql.Date(date.getTime());
			stmt.setInt(1, idtreino);
			stmt.setInt(2, idcorredor);
			stmt.setDate(3, sqlDate);

			stmt.setString(4,"inscrito" );
		
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
