package co.edu.eam.ingesoft.pa.avanzada.controlador;

import java.io.Serializable;
import java.util.Locale;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

import org.omnifaces.util.Faces;

@SessionScoped
@Named("controllerIdioma")
public class ControladorIdioma implements Serializable{
	
	private Locale locate;
	
	@PostConstruct
	public void init(){
		locate = new Locale("es", "CO");
	}
	
	/**
	 * metodo que cambia las constantes al ingles
	 */
	public void cambiarIngles(){
		locate = Locale.ENGLISH;
		Faces.getViewRoot().setLocale(Locale.ENGLISH);
	}
	
	/**
	 * metodo que cambia las constantes
	 */
	public void cambiarEspañol(){
		locate =new Locale("es","CO");
		Faces.getViewRoot().setLocale(Locale.ROOT);
	}

	/**
	 * @return the locate
	 */
	public Locale getLocate() {
		return locate;
	}

	/**
	 * @param locate the locate to set
	 */
	public void setLocate(Locale locate) {
		this.locate = locate;
	}

	
}

