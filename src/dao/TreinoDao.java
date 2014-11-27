package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

import modelo.Treino;
import conexao.FabricaDeConexao;

public class TreinoDao {

	// a conexão com o banco de dados
		private Connection conexao;

		public TreinoDao() {
			this.conexao = FabricaDeConexao.obterInstancia().obterConexao();
		}

		public void adiciona(Treino treino) {
			String sql = "insert into treino "
					+ "(descricao,situacao,vaga_maxima,vaga_minima)" + " values (?,?,?,?)";

			try {

				Statement stat1 = conexao.createStatement(); 
				stat1.execute("set search_path to corridausp"); 
				// prepared statement para inserção
				PreparedStatement stmt = conexao.prepareStatement(sql);

				// seta os valores
				stmt.setString(1, treino.getDescricao());
				stmt.setString(2, treino.getSituacao());
				stmt.setInt(3, treino.getVaga_maxima());
				stmt.setInt(4, treino.getVaga_minima());

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