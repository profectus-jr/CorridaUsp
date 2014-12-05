package controller;

import javax.faces.bean.ManagedBean;

import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import modelo.SessaoTreino;
import modelo.Treino;
import dao.SessaoTreinoDao;

@ManagedBean
public class SessaoTreinoBean {
	
	private SessaoTreino sessaoTreino;
	
	private String diasSemana[] = {"segunda","terca","quarta", "quinta", "sexta", "sabado", "domingo"};
	
	public SessaoTreinoBean(){
		this.sessaoTreino = new SessaoTreino();
		FacesContext fc = FacesContext.getCurrentInstance();
		ExternalContext ec = fc.getExternalContext();
		HttpSession session = (HttpSession) ec.getSession(false);
		Treino treino = (Treino)session.getAttribute("treino");
		this.sessaoTreino.setIdTreino(treino.getId());
	}
	
	public String novaSessaoTreino(){
		SessaoTreinoDao sessaoTreinoDao = new SessaoTreinoDao();
		sessaoTreinoDao.adiciona(this.sessaoTreino);
		return "/SessaoTreino/home?faces-redirect=true";
	}
	
	public String remove(SessaoTreino sessao){
		SessaoTreinoDao dao = new SessaoTreinoDao();
		dao.remove(sessao);
		return "/SessaoTreino/home?faces-redirect=true";
	}
	
	public String novaSessaoTreinoPagina(){
		return "/SessaoTreino/novaSessao?faces-redirect=true";
	}
	
	public String retornarAHomeSessaoTreino(){
		return "/SessaoTreino/home?faces-redirect=true";
	}

	public String retornarAoMenuTreinador(){
		return "/treinador/home?faces-redirect=true";
	}

	public SessaoTreino getSessaoTreino() {
		return sessaoTreino;
	}

	public void setSessaoDeTreino(SessaoTreino sessaoTreino) {
		this.sessaoTreino = sessaoTreino;
	}

	public String[] getDiasSemana() {
		return diasSemana;
	}

	public void setDiasSemana(String diasSemana[]) {
		this.diasSemana = diasSemana;
	}
	
}
