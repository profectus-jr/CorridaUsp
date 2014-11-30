package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import modelo.Treinador;
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
					+ "(id_treinador,descricao,situacao,data_inicio,data_fim,vagas)" + " values (?,?,?,?,?,?)";

			try {

				Statement stat1 = conexao.createStatement(); 
				stat1.execute("set search_path to corridausp"); 
				// prepared statement para inserção
				PreparedStatement stmt = conexao.prepareStatement(sql);

				// seta os valores
				stmt.setInt(1, treino.getIdTreinador());
				stmt.setString(2, treino.getDescricao());
				stmt.setString(3, treino.getSituacao());
				stmt.setDate(4, new java.sql.Date(treino.getDataInicio().getTime()));
				stmt.setDate(5, new java.sql.Date(treino.getDataFim().getTime()));
				stmt.setInt(6, treino.getVagas());

				// executa
				stmt.execute();
				stmt.close();
			} catch (SQLException e) {
				// A SQLException é "encapsulada" em uma RuntimeException
				// para desacoplar o código da API de JDBC
				throw new RuntimeException(e);
			}
		}
		
		public ArrayList<Treino> listaTreinosPorTreinador(Treinador treinador){
			ArrayList<Treino> treinos = new ArrayList<Treino>();
			try {
				Statement stat1 = conexao.createStatement(); 
				stat1.execute("set search_path to corridausp");
				PreparedStatement stat = conexao.prepareStatement("SELECT id,descricao,situacao,data_inicio,data_fim,vagas FROM treino WHERE id_treinador = ?");
				stat.clearParameters(); 
				stat.setInt(1, treinador.getId());
				ResultSet resp = stat.executeQuery();
				while (resp.next()) {
					Treino treino = new Treino();
					treino.setId(resp.getInt(1));
					treino.setDescricao(resp.getString(2));
					treino.setSituacao(resp.getString(3));
					treino.setDataInicio(resp.getDate(4));
					treino.setDataFim(resp.getDate(5));
					treino.setVagas(resp.getInt(6));
					treinos.add(treino);
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return treinos;
		}
}