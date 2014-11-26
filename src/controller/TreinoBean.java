package controller;

import javax.faces.bean.ManagedBean;

import modelo.Treino;
import dao.TreinoDao;

@ManagedBean
public class TreinoBean {

	private Treino treino =  new Treino();

	public Treino getTreino() {
		return treino;
	}

	public void setTreino(Treino treino) {
		this.treino = treino;
	}
	
	public String inscreve() {
		return "/treino/inscreve?faces-redirect=true";
	}
	
	public String inscreveTreino() {
		TreinoDao dao = new TreinoDao();
		dao.adiciona(this.treino);
		this.treino = new Treino();
		return "/home/home?faces-redirect=true";
	}	
}