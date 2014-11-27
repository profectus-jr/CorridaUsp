package controller;

import javax.faces.bean.ManagedBean;

import conexao.Cryptography;
import modelo.Corredor;
import dao.CorredorDao;

@ManagedBean
public class CorredorBean {
	
	private Corredor corredor =  new Corredor();

	public Corredor getCorredor() {
		return corredor;
	}

	public void setCorredor(Corredor corredor) {
		this.corredor = corredor;
	}
	
	public String inscreve() {
		return "/corredor/inscreve?faces-redirect=true";
	}
	
	public String login() {
		return "/corredor/login?faces-redirect=true";
	}
	
	public String inscreveCorredor() {
		CorredorDao dao = new CorredorDao();
		this.corredor.setSenha(new Cryptography().geraMd5(this.corredor.getSenha()));
		dao.adiciona(this.corredor);
		this.corredor = new Corredor();
		return "/home/home?faces-redirect=true";
	}
}
