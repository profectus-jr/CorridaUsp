package controller;

import javax.faces.bean.ManagedBean;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import dao.CorridaDao;
import modelo.Corrida;
import modelo.SessaoTreino;

@ManagedBean
public class CorridaBean {
	
	private Corrida corrida;

	public CorridaBean(){
		this.corrida = new Corrida();
		FacesContext fc = FacesContext.getCurrentInstance();
		ExternalContext ec = fc.getExternalContext();
		HttpSession session = (HttpSession) ec.getSession(false);
		SessaoTreino sessao = (SessaoTreino)session.getAttribute("streino");
		this.corrida.setId_sessao(sessao.getId());
	}

	public Corrida getCorrida() {
		return corrida;
	}

	public void setCorrida(Corrida corrida) {
		this.corrida = corrida;
	}

	public String inscreveCorrida() {
		CorridaDao dao = new CorridaDao();
		dao.adiciona(this.corrida);
		this.corrida = new Corrida();
		return "/SessaoTreino/home?faces-redirect=true";
	}	
}