package controller;

import java.util.List;

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
	private List<Treino> treinos;
	
	//private String situacao;
	private String situacoes[] = {"encerrado","inscricoes","ativo", "desativado"};

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
		FacesContext fc = FacesContext.getCurrentInstance();
		TreinoDao dao = new TreinoDao();
		ExternalContext ec = fc.getExternalContext();
		HttpSession session = (HttpSession) ec.getSession(false);
		String sessionId = session.getId();
		System.out.println("s id:" + sessionId);
		Treinador treinador = (Treinador)session.getAttribute("treinador");
		System.out.println("id treinador:" + treinador.getId());
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
		System.out.println(treino.getDescricao());
		this.treinos.add(treino);
		return "/treino/editar?faces-redirect=true";
	}

	public List<Treino> getTreinos() {
		return treinos;
	}

	public void setTreinos(List<Treino> treinos) {
		this.treinos = treinos;
	}
}