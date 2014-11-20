package controller;

import javax.faces.bean.ManagedBean;

import modelo.Treinador;
import conexao.Cryptography;
import dao.TreinadorDao;

@ManagedBean
public class TreinadorBean {
	
	private Treinador treinador =  new Treinador();

	public Treinador getTreinador() {
		return treinador;
	}

	public void setTreinador(Treinador treinador) {
		this.treinador = treinador;
	}
	
	public String inscreve() {
		return "/treinador/inscreve?faces-redirect=true";
	}
	
	public String inscreveTreinador() {
		TreinadorDao dao = new TreinadorDao();
		this.treinador.setSenha(new Cryptography().geraMd5(this.treinador.getSenha()));
		dao.adiciona(this.treinador);
		this.treinador = new Treinador();
		return "/home/home?faces-redirect=true";
	}

}
