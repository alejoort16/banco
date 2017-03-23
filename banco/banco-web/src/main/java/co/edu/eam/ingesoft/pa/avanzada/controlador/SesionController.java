package co.edu.eam.ingesoft.pa.avanzada.controlador;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import javax.servlet.http.HttpSession;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Length;
import org.omnifaces.util.Faces;
import org.omnifaces.util.Messages;

import co.edu.eam.ingesoft.pa.negocio.beans.SeguridadEJB;
import entidades.Usuario;

@Named("sesionController")
@SessionScoped
public class SesionController implements Serializable {

	@Pattern(regexp = "[A-Za-z ]*", message = "solo Letras")
	@Length(min = 3, max = 10, message = "longitud entre 3 y 10")
	private String user;

	private String password;

	private Usuario usuario;

	@EJB
	SeguridadEJB seguridadEJB;

	@PostConstruct
	public void inicializar() {
	}

	/**
	 * metodo que da acceso al banco
	 */
	public String loguin() {
		Usuario usu = seguridadEJB.buscarUsuario(user);
		if (usu != null) {
			if (usu.getPassword().equals(password)) {
				usuario = usu;
				Faces.setSessionAttribute("user", usuario);
				return "/paginas/seguro/inicio.xhtml?faces-redirect=true";
			} else {
				Messages.addFlashGlobalError("Usuario o password incorrectos");
			}
		} else {
			Messages.addFlashGlobalError("Usuario o password incorrectos");
		}
		return null;

	}

	/**
	 * metodo que destrulle la sesion
	 */
	public String cerrarSesion() {
		usuario = null;
		HttpSession sesion;
		sesion = (HttpSession) Faces.getSession();
		sesion.invalidate();
		return "/paginas/publico/loguin.xhtml?faces-redirect=true";
	}

	public boolean isSesion() {
		return usuario != null;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public SeguridadEJB getSeguridadEJB() {
		return seguridadEJB;
	}

	public void setSeguridadEJB(SeguridadEJB seguridadEJB) {
		this.seguridadEJB = seguridadEJB;
	}

}
