package controller;

import modelo.TelTreinador;
import dao.TelTreinadorDao;

public class TelTreinadorBean {
	private TelTreinador telTreinador = new TelTreinador();
	public TelTreinador getTelTreinador() {
		return telTreinador;
	}
	public void setTelTreinador(TelTreinador telTreinador) {
		this.telTreinador = telTreinador;
	}
	public void novoTelTreinador() {
		TelTreinadorDao dao = new TelTreinadorDao();
		dao.adiciona(this.telTreinador);
		this.telTreinador = new TelTreinador();
	}
}
