package controller;

import dao.TelCorredorDao;
import modelo.TelCorredor;

public class TelCorredorBean {
	private TelCorredor telCorredor = new TelCorredor();
	public TelCorredor getTelCorredor() {
		return telCorredor;
	}
	public void setTelCorredor(TelCorredor telCorredor) {
		this.telCorredor = telCorredor;
	}
	public void novoTelCorredor() {
		TelCorredorDao dao = new TelCorredorDao();
		dao.adiciona(this.telCorredor);
		this.telCorredor = new TelCorredor();
	}
}
