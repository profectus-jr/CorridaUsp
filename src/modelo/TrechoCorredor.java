package modelo;

import java.util.Date;

public class TrechoCorredor {
	
	private int id_corredor;
	private int pontoUm;
	private int pontoDois;
	private int id_corrida;
	private int nota;
	private Date data;
	private String comentario;
	
	public int getId_corredor() {
		return id_corredor;
	}
	public void setId_corredor(int id_corredor) {
		this.id_corredor = id_corredor;
	}
	public int getPontoUm() {
		return pontoUm;
	}
	public void setPontoUm(int pontoUm) {
		this.pontoUm = pontoUm;
	}
	public int getPontoDois() {
		return pontoDois;
	}
	public void setPontoDois(int pontoDois) {
		this.pontoDois = pontoDois;
	}
	public int getId_corrida() {
		return id_corrida;
	}
	public void setId_corrida(int id_corrida) {
		this.id_corrida = id_corrida;
	}
	public int getNota() {
		return nota;
	}
	public void setNota(int nota) {
		this.nota = nota;
	}
	public Date getData() {
		return data;
	}
	public void setData(Date data) {
		this.data = data;
	}
	public String getComentario() {
		return comentario;
	}
	public void setComentario(String comentario) {
		this.comentario = comentario;
	}
}
