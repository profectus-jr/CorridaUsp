package modelo;

public class CorredorSessao {

	private String tempo_gasto;
	private float distancia_percorrida;
	private int id_sessao;
	private int id_corredor;
	  
	public int getId_sessao() {
		return id_sessao;
	}
	public void setId_sessao(int id_sessao) {
		this.id_sessao = id_sessao;
	}
	public int getId_corredor() {
		return id_corredor;
	}
	public void setId_corredor(int id_corredor) {
		this.id_corredor = id_corredor;
	}
	public String getTempo_gasto() {
		return tempo_gasto;
	}
	public void setTempo_gasto(String tempo_gasto) {
		this.tempo_gasto = tempo_gasto;
	}
	public float getDistancia_percorrida() {
		return distancia_percorrida;
	}
	public void setDistancia_percorrida(float distancia_percorrida) {
		this.distancia_percorrida = distancia_percorrida;
	}
}
