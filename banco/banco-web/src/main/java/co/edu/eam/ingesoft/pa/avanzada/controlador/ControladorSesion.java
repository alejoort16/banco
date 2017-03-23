package co.edu.eam.ingesoft.pa.avanzada.controlador;

import java.io.Serializable;

import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import javax.servlet.http.HttpSession;

import org.omnifaces.util.Faces;
import org.omnifaces.util.Messages;

import co.edu.eam.ingesoft.pa.negocio.beans.SeguridadEJB;
import entidades.Usuario;

@Named("seccionControl")
@SessionScoped
public class ControladorSesion implements Serializable {
	
	private String nickname;
	private String contra;
	
	private Usuario usuario;
	
	@EJB
	private SeguridadEJB seguidadejb;
	
	public String login(){
		Usuario usuarioTemporal = seguidadejb.buscarUsuario(nickname);
		
		if(usuarioTemporal!=null){
			
			if(usuarioTemporal.getPassword().equals(contra)){
				usuario = usuarioTemporal;
				Faces.setSessionAttribute("user", usuario);
				return "/paginas/seguro/inicio.xhtml?faces-redirect=true";
			}else{
				Messages.addFlashGlobalError("nombre de usuario o contraseña incorrecto");
			}
			
		}else{
			Messages.addFlashGlobalError("nombre de usuario o contraseña incorrecto");
		}
		return null;
	}
	
	public String cerrarSession(){
		usuario=null;
		HttpSession sesion;
		sesion=(HttpSession)Faces.getSession();
		sesion.invalidate();
		return "/paginas/publico/Login.xhtml?faces-redirect=true";

	}
	
	/**
	 * metodo para saber si hay sesion iniciada o no
	 * @return true si hay sesion iniciada false si no
	 */
	public boolean isSesion(){
		return usuario!=null;
	}
	
	
	
	

	/**
	 * @return the nickname
	 */
	public String getNickname() {
		return nickname;
	}

	/**
	 * @param nickname the nickname to set
	 */
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	/**
	 * @return the contra
	 */
	public String getContra() {
		return contra;
	}

	/**
	 * @param contra the contra to set
	 */
	public void setContra(String contra) {
		this.contra = contra;
	}

	/**
	 * @return the usuario
	 */
	public Usuario getUsuario() {
		return usuario;
	}

	/**
	 * @param usuario the usuario to set
	 */
	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	/**
	 * @return the seguidadejb
	 */
	public SeguridadEJB getSeguidadejb() {
		return seguidadejb;
	}

	/**
	 * @param seguidadejb the seguidadejb to set
	 */
	public void setSeguidadejb(SeguridadEJB seguidadejb) {
		this.seguidadejb = seguidadejb;
	}

	
}
