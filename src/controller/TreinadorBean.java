package controller;

import javax.faces.bean.ManagedBean;
import javax.faces.application.FacesMessage;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

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
	
	public String login() {
		return "/treinador/login?faces-redirect=true";
	}
	
	public String inscreveTreinador() {
		TreinadorDao dao = new TreinadorDao();
		this.treinador.setSenha(new Cryptography().geraMd5(this.treinador.getSenha()));
		dao.adiciona(this.treinador);
		this.treinador = new Treinador();
		return "/home/home?faces-redirect=true";
	}
	
	
	public String autenticacao() {
		FacesContext fc = FacesContext.getCurrentInstance();
		TreinadorDao dao = new TreinadorDao();
		if (dao.isTreinador(this.treinador)) {
			System.out.println("OK");
			ExternalContext ec = fc.getExternalContext();
			HttpSession session = (HttpSession) ec.getSession(false);
			session.setAttribute("treinador", this.treinador);
			Treinador treinador = (Treinador)session.getAttribute("treinador");
			System.out.println("id treinador s:" + treinador.getEmail());
			return "/treinador/home?faces-redirect=true";
		} else {
			System.out.println("False");
			FacesMessage fm = new FacesMessage("usuário e/ou senha inválidos");
			fm.setSeverity(FacesMessage.SEVERITY_ERROR);
			fc.addMessage(null, fm);
			return "/home/home?faces-redirect=true";
		}
	}

	public String logout() {
		FacesContext fc = FacesContext.getCurrentInstance();
		ExternalContext ec = fc.getExternalContext();
		HttpSession session = (HttpSession) ec.getSession(false);
		session.removeAttribute("treinador");
		return "/home/home?faces-redirect=true";
	}	
}