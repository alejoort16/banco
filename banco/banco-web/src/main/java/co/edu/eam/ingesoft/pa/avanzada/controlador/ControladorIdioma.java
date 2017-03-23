package co.edu.eam.ingesoft.pa.avanzada.controlador;

import java.util.Locale;

import javax.inject.Named;

import org.omnifaces.util.Faces;

@Named("controllerIdioma")
public class ControladorIdioma {
	
	/**
	 * metodo que cambia las constantes al ingles
	 */
	public void cambiarIngles(){
		Faces.getViewRoot().setLocale(Locale.ENGLISH);
	}
	
	/**
	 * metodo que cambia las constantes
	 */
	public void cambiarEspañol(){
		Faces.getViewRoot().setLocale(Locale.ROOT);
	}

}
