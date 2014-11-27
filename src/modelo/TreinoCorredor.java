package modelo;

import java.util.Date;

public class TreinoCorredor {
	
	private int id_corredor;
	private int id_treino;
	private Date data;
	private Date data_termino;
	private String situacao_inscricao;
	private int nota;
	private String comentario;
	
	public int getId_corredor() {
		return id_corredor;
	}
	public void setId_corredor(int id_corredor) {
		this.id_corredor = id_corredor;
	}
	public int getId_treino() {
		return id_treino;
	}
	public void setId_treino(int id_treino) {
		this.id_treino = id_treino;
	}
	public Date getData() {
		return data;
	}
	public void setData(Date data) {
		this.data = data;
	}
	public Date getData_termino() {
		return data_termino;
	}
	public void setData_termino(Date data_termino) {
		this.data_termino = data_termino;
	}
	public String getSituacao_inscricao() {
		return situacao_inscricao;
	}
	public void setSituacao_inscricao(String situacao_inscricao) {
		this.situacao_inscricao = situacao_inscricao;
	}
	public int getNota() {
		return nota;
	}
	public void setNota(int nota) {
		this.nota = nota;
	}
	public String getComentario() {
		return comentario;
	}
	public void setComentario(String comentario) {
		this.comentario = comentario;
	}
}
