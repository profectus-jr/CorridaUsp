package conexao;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


public class Filtro implements Filter{
	
	@Override
	public void init(FilterConfig filterconfig) throws ServletException {
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {	
		
		HttpServletRequest req = (HttpServletRequest)request;
		HttpSession session = req.getSession();
		
		if (session.getAttribute("treinador") != null
				|| session.getAttribute("corredor") != null
				|| req.getRequestURI().endsWith("/treinador/inscreve.xhtml")
				|| req.getRequestURI().endsWith("/treinador/login.xhtml")
				|| req.getRequestURI().endsWith("/corredor/inscreve.xhtml")
				|| req.getRequestURI().endsWith("/corredor/login.xhtml")) {
			
			chain.doFilter(request ,response);
			
		} else {
			HttpServletResponse res = (HttpServletResponse)response;
			res.sendRedirect("/home/home.xhtml");
		}
	}
	
	@Override
	public void destroy() {
	}
}