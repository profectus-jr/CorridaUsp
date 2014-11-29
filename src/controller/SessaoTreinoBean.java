package controller;

import javax.faces.bean.ManagedBean;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import modelo.SessaoTreino;
import modelo.Treinador;
import modelo.Treino;
import dao.TreinoDao;

@ManagedBean
public class SessaoTreinoBean {
	
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
		return "/treinador/home?faces-redirect=true";
	}
	

}
