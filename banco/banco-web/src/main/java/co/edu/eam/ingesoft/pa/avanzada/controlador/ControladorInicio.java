package co.edu.eam.ingesoft.pa.avanzada.controlador;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.inject.Inject;
import javax.inject.Named;

import org.omnifaces.cdi.ViewScoped;

import co.edu.eam.ingesoft.avanzada.banco.dtos.InicioDTO;
import co.edu.eam.ingesoft.pa.negocio.beans.ConsumoTCEJB;
import co.edu.eam.ingesoft.pa.negocio.beans.OperacionesCuentaAhorros;
import entidades.CuentaAhorros;
import enumeraciones.TipoDocumento;

@Named("controladorInicio")
@ViewScoped
public class ControladorInicio implements Serializable {

	@EJB
	OperacionesCuentaAhorros operacionesejb;

	@EJB
	ConsumoTCEJB consumoejb;

	@Inject
	ControladorSesion sesionController;

	List<CuentaAhorros> cuentas;

	List<InicioDTO> listaDTO;

	@PostConstruct
	public void inicializar() {

		cargarMatrizCuentas();
		cargarMatrizTarjetas();
	}

	public void cargarMatrizCuentas() {
		TipoDocumento tipo = sesionController.getUsuario().getCliente().getIdentificationType();
		String documentoId = sesionController.getUsuario().getCliente().getIdentificationNumber();
		cuentas = operacionesejb.listarCuentaAhorros(tipo, documentoId);

	}

	public void cargarMatrizTarjetas() {
		TipoDocumento tipo = sesionController.getUsuario().getCliente().getIdentificationType();
		String documentoId = sesionController.getUsuario().getCliente().getIdentificationNumber();
		listaDTO = consumoejb.llenarDTO(documentoId, tipo);

	}

	public List<InicioDTO> getListaDTO() {
		return listaDTO;
	}

	public void setListaDTO(List<InicioDTO> listaDTO) {
		this.listaDTO = listaDTO;
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
