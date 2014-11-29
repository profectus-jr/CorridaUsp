package controller;

import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import modelo.SessaoTreino;
import modelo.Treinador;
import modelo.Treino;
import conexao.Cryptography;
import dao.SessaoTreinoDao;
import dao.TreinadorDao;

@ManagedBean
public class TreinadorBean {
	
	private Treinador treinador =  new Treinador();
	private Treino treino;
	
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
	
	public List<Treino> getListaTreinos(){
		TreinadorDao dao = new TreinadorDao();
		FacesContext fc = FacesContext.getCurrentInstance();
		ExternalContext ec = fc.getExternalContext();
		HttpSession session = (HttpSession) ec.getSession(false);
		treinador = (Treinador)session.getAttribute("treinador");
		return dao.listaTreinosTreinador(treinador);
	}
	
	public List<SessaoTreino> getSessoesTreino(){
		SessaoTreinoDao dao = new SessaoTreinoDao();
		FacesContext fc = FacesContext.getCurrentInstance();
		ExternalContext ec = fc.getExternalContext();
		HttpSession session = (HttpSession) ec.getSession(false);
		treino = (Treino)session.getAttribute("treino");
		System.out.println("esse eh o id:" + treino.getId()+ "esse eh o id treinador:");
		
		return dao.listaSessoesDoTreino(treino);
	}
	
	public String inscreveTreino() {
		/*FacesContext fc = FacesContext.getCurrentInstance();
		TreinoDao dao = new TreinoDao();
		ExternalContext ec = fc.getExternalContext();
		HttpSession session = (HttpSession) ec.getSession(false);
		String sessionId = session.getId();
		System.out.println("s id:" + sessionId);
		Treinador treinador = (Treinador)session.getAttribute("treinador");
		System.out.println("id treinador:" + treinador.getId());
		this.treino.setIdTreinador(treinador.getId());
		dao.adiciona(this.treino);
		this.treino = new Treino();*/
		return "/treinador/visualizarSessaoTreinohome?faces-redirect=true";
	}

	public String retornarAoMenuTreinador(){
		System.out.println("cliquei para retornar ao menu de treinos");
		return "/treinador/home?faces-redirect=true";
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
			ExternalContext ec = fc.getExternalContext();
			HttpSession session = (HttpSession) ec.getSession(false);
			session.setAttribute("treinador", this.treinador);
			

			return "/treinador/home?faces-redirect=true";
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
		session.removeAttribute("treinador");
		return "/home/home?faces-redirect=true";
	}
	
	public String visualizarSessao(Treino treino) {
		System.out.println("cliquei para vizualizar as sessoes");

		this.setTreino(treino);
		FacesContext fc = FacesContext.getCurrentInstance();
		ExternalContext ec = fc.getExternalContext();
		System.out.println("esperando para obter a sessao");

		HttpSession session = (HttpSession) ec.getSession(false);
		System.out.println("sessao recebida");

		session.setAttribute("treino", treino);
		return "/treinador/visualizarSessaoTreino?faces-redirect=true";
	}

	public Treino getTreino() {
		return treino;
	}

	public void setTreino(Treino treino) {
		this.treino = treino;
	}
}