package modelo;

public class Corrida {
	
	private int id;
	private int id_sessao;
	private String nome;
	private String descricao;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}

	public int getId_sessao() {
		return id_sessao;
	}
	public void setId_sessao(int id_sessao) {
		this.id_sessao = id_sessao;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
}
