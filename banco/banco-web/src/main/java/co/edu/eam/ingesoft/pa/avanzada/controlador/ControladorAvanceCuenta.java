package co.edu.eam.ingesoft.pa.avanzada.controlador;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.inject.Inject;
import javax.inject.Named;

import org.omnifaces.cdi.ViewScoped;
import org.omnifaces.util.Messages;

import co.edu.eam.ingesoft.pa.negocio.beans.ConsumoTCEJB;
import co.edu.eam.ingesoft.pa.negocio.beans.OperacionesCuentaAhorros;
import co.edu.eam.ingesoft.pa.negocio.beans.PagoConsumoEJB;
import co.edu.eam.ingesoft.pa.negocio.excepciones.ExcepcionNegocio;
import entidades.CuentaAhorros;
import entidades.TarjetaCredito;
import enumeraciones.TipoDocumento;

@Named("controladorAvanceCuenta")
@ViewScoped
public class ControladorAvanceCuenta implements Serializable {

	/**
	 * campo cantidad
	 */
	private double cantidad;

	/**
	 * tarjeta de credito seleccionada
	 */
	private String tcSeleccionada;

	/**
	 * numero de la cuenta, lo escogido por el combo se guardara en este valor
	 */
	private String cuentaSeleccionada;

	/**
	 * lista donde se almacenaran las cuentas de ahorro del cliente
	 */
	private List<CuentaAhorros> cuentasAhorro;

	/**
	 * lista donde se almacenaran las tarjetas de credito
	 */
	private List<TarjetaCredito> tarjetas;

	/**
	 * ejb de operaciones de la cuenta Ahorros
	 */
	@EJB
	OperacionesCuentaAhorros operacionesejb;

	/**
	 * ejb de pago Consumo
	 */
	@EJB
	PagoConsumoEJB pagoejb;

	/**
	 * Ejb del consumo de la tarjeta
	 */
	@EJB
	ConsumoTCEJB consumo;

	@Inject
	private ControladorSesion sesion;

	@PostConstruct
	public void inicializar() {
		listarProductos();

	}

	/**
	 * buscar un cliente por identificacion y tipo, si lo encuentra
	 * automaticamente carga los prouctos combos de tc y cuentas con sus
	 * respectivas
	 */
	public void listarProductos() {
		try {

			TipoDocumento tipoSeleccionado = sesion.getUsuario().getCliente().getIdentificationType();
			String id_client = sesion.getUsuario().getCliente().getIdentificationNumber();
			tarjetas = pagoejb.listarTCCliente(id_client, tipoSeleccionado);
			cuentasAhorro = operacionesejb.listarCuentaAhorros(tipoSeleccionado, id_client);

			tcSeleccionada = tarjetas.get(0).getNumber();
			cuentaSeleccionada = cuentasAhorro.get(0).getNumber();

		} catch (ExcepcionNegocio e) {
			Messages.addGlobalError(e.getMessage());
		}
	}

	public void transferir() {

		try {
			consumo.avanceCuenta(tcSeleccionada, cantidad, cuentaSeleccionada);
			Messages.addFlashGlobalInfo("operacion exitoso");
		} catch (ExcepcionNegocio e) {
			Messages.addGlobalError(e.getMessage());
		}
	}

	/**
	 * metodo que redirige a la pagina inicio
	 * 
	 * @return
	 */
	public String cancelar() {
		return "/paginas/seguro/inicio.xhtml";
	}

	public String getTcSeleccionada() {
		return tcSeleccionada;
	}

	public void setTcSeleccionada(String tcSeleccionada) {
		this.tcSeleccionada = tcSeleccionada;
	}

	public String getCuentaSeleccionada() {
		return cuentaSeleccionada;
	}

	public void setCuentaSeleccionada(String cuentaSeleccionada) {
		this.cuentaSeleccionada = cuentaSeleccionada;
	}

	public List<CuentaAhorros> getCuentasAhorro() {
		return cuentasAhorro;
	}

	public void setCuentasAhorro(List<CuentaAhorros> cuentasAhorro) {
		this.cuentasAhorro = cuentasAhorro;
	}

	public List<TarjetaCredito> getTarjetas() {
		return tarjetas;
	}

	public void setTarjetas(List<TarjetaCredito> tarjetas) {
		this.tarjetas = tarjetas;
	}

	public ControladorSesion getSesion() {
		return sesion;
	}

	public void setSesion(ControladorSesion sesion) {
		this.sesion = sesion;
	}

	public double getCantidad() {
		return cantidad;
	}

	public void setCantidad(double cantidad) {
		this.cantidad = cantidad;
	}

}
