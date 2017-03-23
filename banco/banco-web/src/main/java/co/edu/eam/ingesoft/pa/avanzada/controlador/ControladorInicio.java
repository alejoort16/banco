package co.edu.eam.ingesoft.pa.avanzada.controlador;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.inject.Inject;
import javax.inject.Named;

import org.omnifaces.cdi.ViewScoped;

import co.edu.eam.ingesoft.pa.negocio.beans.OperacionesCuentaAhorros;
import entidades.CuentaAhorros;
import enumeraciones.TipoDocumento;

@Named("controladorInicio")
@ViewScoped
public class ControladorInicio implements Serializable {

	@EJB
	OperacionesCuentaAhorros operacionesejb;

	@Inject
	ControladorSesion sesionController;

	List<CuentaAhorros> cuentas;

	@PostConstruct
	public void inicializar() {
		TipoDocumento tipo = sesionController.getUsuario().getCliente().getIdentificationType();
		String documentoId = sesionController.getUsuario().getCliente().getIdentificationNumber();
		cuentas = operacionesejb.listarCuentaAhorros(tipo, documentoId);
	}

	public OperacionesCuentaAhorros getOperacionesEJB() {
		return operacionesejb;
	}

	public void setOperacionesEJB(OperacionesCuentaAhorros operacionesEJB) {
		this.operacionesejb = operacionesEJB;
	}

	public ControladorSesion getSesionEJB() {
		return sesionController;
	}

	public void setSesionEJB(ControladorSesion sesionEJB) {
		this.sesionController = sesionEJB;
	}

	public List<CuentaAhorros> getCuentas() {
		return cuentas;
	}

	public void setCuentas(List<CuentaAhorros> cuentas) {
		this.cuentas = cuentas;
	}

}
