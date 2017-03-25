package seguridad;


import java.io.IOException;

import javax.inject.Inject;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import co.edu.eam.ingesoft.pa.avanzada.controlador.ControladorSesion;
import entidades.Usuario;

@WebFilter(urlPatterns="/paginas/seguro/*")
public class FiltroSesion implements Filter{
	
	@Inject
	private ControladorSesion sesion;

	public void destroy() {
		// TODO Auto-generated method stub
		
	}

	public void doFilter(ServletRequest arg0, ServletResponse arg1, FilterChain chain)
			throws IOException, ServletException {
		System.out.println("Filtrando peticion!!!!!");
		
		
		
		
		HttpServletRequest req=(HttpServletRequest) arg0;
		HttpServletResponse res=(HttpServletResponse) arg1;
		
		Usuario usuario=sesion.getUsuario();
		System.out.println("usuario:"+usuario);
		
		if(usuario!=null){
			chain.doFilter(arg0, arg1);
		}else{
			res.sendRedirect(req.getContextPath()+"/paginas/publico/Login.xhtml");
		}		
		
	}

	public void init(FilterConfig arg0) throws ServletException {
		// TODO Auto-generated method stub
		
	}

}
