package modelo;

public class Treino {
	
	private int id;
	private int idTreinador;
	private String descricao;
	private String situacao;
	private int vaga_maxima;
	private int vaga_minima;
	
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
	public int getVaga_maxima() {
		return vaga_maxima;
	}
	public void setVaga_maxima(int vaga_maxima) {
		this.vaga_maxima = vaga_maxima;
	}
	public int getVaga_minima() {
		return vaga_minima;
	}
	public void setVaga_minima(int vaga_minima) {
		this.vaga_minima = vaga_minima;
	}
	public int getIdTreinador() {
		return idTreinador;
	}
	public void setIdTreinador(int idTreinador) {
		this.idTreinador = idTreinador;
	}
}
