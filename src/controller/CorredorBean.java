package controller;

import javax.faces.application.FacesMessage;

import javax.faces.bean.ManagedBean;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

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
	
	public String autenticacao() {
		FacesContext fc = FacesContext.getCurrentInstance();
		CorredorDao dao = new CorredorDao();
		if (dao.isCorredor(this.corredor)) {
			ExternalContext ec = fc.getExternalContext();
			HttpSession session = (HttpSession) ec.getSession(false);
			session.setAttribute("corredor", this.corredor);
			return "/corredor/home?faces-redirect=true";
		} else {
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
		session.removeAttribute("corredor");
		return "/home/home?faces-redirect=true";
	}
}