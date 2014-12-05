package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;

import modelo.SessaoTreino;
import modelo.Treino;
import conexao.FabricaDeConexao;

public class SessaoTreinoDao {
	
	private Connection conexao;

	public SessaoTreinoDao() {
		this.conexao = FabricaDeConexao.obterInstancia().obterConexao();
	}
	
	public void adiciona(SessaoTreino sessaotreino) {
		String sql = "insert into sessaotreino "
				+ "(id_treino,dia_semana,hora,duracao)" + " values (?,?,?,?)";

		try {
			Statement stat1 = conexao.createStatement();
			stat1.execute("set search_path to corridausp"); 
			
			PreparedStatement stmt = conexao.prepareStatement(sql);
						
			// seta os valores
			stmt.setInt(1, sessaotreino.getIdTreino());
			stmt.setString(2, sessaotreino.getDiaSemana());
			stmt.setTimestamp(3, new java.sql.Timestamp(sessaotreino.getHora().getTime()));
			stmt.setFloat(4, sessaotreino.getDuracao());
			
			// executa
			stmt.execute();
			stmt.close();
		} catch (SQLException e) {
			// A SQLException é "encapsulada" em uma RuntimeException
			// para desacoplar o código da API de JDBC
			throw new RuntimeException(e);
		}
	}
	
	public void remove(SessaoTreino sessao) {
		try {
			Statement stat1 = conexao.createStatement();
			stat1.execute("set search_path to corridausp"); 
			PreparedStatement stmt = conexao.prepareStatement("delete "
					+ "from sessaotreino where id=?");
			stmt.setLong(1, sessao.getId());
			stmt.execute();
			stmt.close();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	public ArrayList<SessaoTreino> listaSessoesDoTreino(Treino treino){
		ArrayList<SessaoTreino> streinos = new ArrayList<SessaoTreino>();
		try {
			Statement stat1 = conexao.createStatement(); 
			stat1.execute("set search_path to corridausp");
			PreparedStatement stat = conexao.prepareStatement("SELECT id,dia_semana,hora,duracao FROM sessaotreino WHERE id_treino = ?");
			stat.clearParameters(); 
			stat.setInt(1, treino.getId());
			ResultSet resp = stat.executeQuery();
			while (resp.next()) {
				SessaoTreino sessaotreino = new SessaoTreino();
				sessaotreino.setId(resp.getInt(1));
				sessaotreino.setDiaSemana(resp.getString(2));
				System.out.println("2 hora:" + resp.getTime(3));
				System.out.println("21 hora:" + (new Date(resp.getTime(3).getTime())));
				sessaotreino.setHora(new Date(resp.getTime(3).getTime()));
				sessaotreino.setDuracao(resp.getFloat(4));
				streinos.add(sessaotreino);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return streinos;
	}
}
