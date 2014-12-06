package controller;

import javax.faces.bean.ManagedBean;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import modelo.Treinador;
import modelo.Treino;
import dao.TreinoDao;

@ManagedBean
public class TreinoBean {

	private Treino treino =  new Treino();
	private Treino treinos = new Treino();
	
	private String situacoes[] = {"encerrado","inscricoes","ativo", "desativado"};

	public Treino getTreino() {
		return treino;
	}

	public void setTreino(Treino treino) {
		this.treino = treino;
	}
	
	public Treino getTreinos() {
		FacesContext fc = FacesContext.getCurrentInstance();
		ExternalContext ec = fc.getExternalContext();
		HttpSession session = (HttpSession) ec.getSession(false);
		Treino treinos = (Treino) session.getAttribute("treino");
		return treinos;
	}

	public void setTreinos(Treino treinos) {
		this.treinos = treinos;
	}
	
	public String inscreve() {
		return "/treino/inscreve?faces-redirect=true";
	}
	
	public String inscreveTreino() {
		FacesContext fc = FacesContext.getCurrentInstance();
		TreinoDao dao = new TreinoDao();
		ExternalContext ec = fc.getExternalContext();
		HttpSession session = (HttpSession) ec.getSession(false);
		Treinador treinador = (Treinador)session.getAttribute("treinador");
		this.treino.setIdTreinador(treinador.getId());
		dao.adiciona(this.treino);
		this.treino = new Treino();
		return "/treinador/home?faces-redirect=true";
	}

	public String[] getSituacoes() {
		return situacoes;
	}
	
	public String remove(Treino treino){
		TreinoDao dao = new TreinoDao();
		dao.remove(treino);
		return "/treinador/home?faces-redirect=true";
	}
	
	public String editar(Treino treino){
		FacesContext fc = FacesContext.getCurrentInstance();
		ExternalContext ec = fc.getExternalContext();
		HttpSession session = (HttpSession) ec.getSession(false);
		session.setAttribute("treino", treino);
		return "/treino/editar?faces-redirect=true";
	}
	
	public String atualizar(){
		TreinoDao dao = new TreinoDao();
		FacesContext fc = FacesContext.getCurrentInstance();
		ExternalContext ec = fc.getExternalContext();
		HttpSession session = (HttpSession) ec.getSession(false);
		treinos = (Treino)session.getAttribute("treino");
		dao.altera(treinos);
		return "/treinador/home?faces-redirect=true";
	}
}