package controller;

import java.util.List;

import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import dao.CorridaDao;
import dao.SessaoTreinoDao;
import modelo.Corrida;
import modelo.SessaoTreino;
import modelo.Treinador;

public class CorridaBean {
	
	private Corrida corrida = new Corrida();
	private String corridas[] = {"Volta USP","Rua Matao","Praca relogio", "Raia"};
	private List<SessaoTreino> sessoes;

	public Corrida getCorrida() {
		return corrida;
	}

	public void setCorrida(Corrida corrida) {
		this.corrida = corrida;
	}

	public String[] getCorridas() {
		return corridas;
	}

	public String inscreve() {
		return "/treino/inscreve?faces-redirect=true";
	}
	
	public String inscreveCorrida() {
		CorridaDao dao = new CorridaDao();
		dao.adiciona(this.corrida);
		this.corrida = new Corrida();
		return "/treinador/home?faces-redirect=true";
	}
	
	public List<SessaoTreino> getListaSessoes(){
		if(this.sessoes == null){
			FacesContext fc = FacesContext.getCurrentInstance();
			ExternalContext ec = fc.getExternalContext();
			HttpSession session = (HttpSession) ec.getSession(false);
			Treinador treinador = (Treinador)session.getAttribute("treinador");
			SessaoTreinoDao dao = new SessaoTreinoDao();
			this.sessoes = dao.buscaSessoesTreinador(treinador.getId());
		}
		return this.sessoes;
	}	
}