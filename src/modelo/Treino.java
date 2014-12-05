package modelo;

import java.util.Date;

public class Treino {
	
	private int id;
	private int idTreinador;
	private String descricao;
	private String situacao;
	private Date dataInicio;
	private Date dataFim;
	private int vagas;
	private int numVagas;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public String getSituacao() {
		return situacao;
	}
	public void setSituacao(String situacao) {
		this.situacao = situacao;
	}
	public int getIdTreinador() {
		return idTreinador;
	}
	public void setIdTreinador(int idTreinador) {
		this.idTreinador = idTreinador;
	}
	public Date getDataInicio() {
		return dataInicio;
	}
	public void setDataInicio(Date dataInicio) {
		this.dataInicio = dataInicio;
	}
	public Date getDataFim() {
		return dataFim;
	}
	public void setDataFim(Date dataFim) {
		this.dataFim = dataFim;
	}
	public int getVagas() {
		return vagas;
	}
	public void setVagas(int vagas) {
		this.vagas = vagas;
	}
	public int getNumVagas() {
		return numVagas;
	}
	public void setNumVagas(int numVagas) {
		this.numVagas = numVagas;
	}
}
