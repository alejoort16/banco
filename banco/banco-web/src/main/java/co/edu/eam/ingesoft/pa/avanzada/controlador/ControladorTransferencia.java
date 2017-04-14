package co.edu.eam.ingesoft.pa.avanzada.controlador;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.inject.Inject;
import javax.inject.Named;
import javax.validation.constraints.Pattern;

import org.omnifaces.cdi.ViewScoped;
import org.omnifaces.util.Messages;
import org.primefaces.context.RequestContext;

import co.edu.eam.ingesoft.pa.negocio.beans.CuentaAsociadaEJB;
import co.edu.eam.ingesoft.pa.negocio.beans.OperacionesCuentaAhorros;
import co.edu.eam.ingesoft.pa.negocio.beans.PagoConsumoEJB;
import co.edu.eam.ingesoft.pa.negocio.excepciones.ExcepcionNegocio;
import entidades.CuentaAhorros;
import entidades.CuentaAsociada;
import enumeraciones.TipoDocumento;

@Named("controladorTrans")
@ViewScoped
public class ControladorTransferencia implements Serializable {

	@Inject
	private ControladorSesion sesion;

	@EJB
	private CuentaAsociadaEJB asociadasejb;

	@EJB
	private PagoConsumoEJB pagoejb;

	@EJB
	private OperacionesCuentaAhorros transaccionejb;

	/**
	 * tipo de documento del cliente
	 */
	private TipoDocumento tipoSeleccionado;

	private String id_cliente;

	private List<CuentaAhorros> cuentasAhorro;
	private String numeroCuentaOrigen;

	private List<CuentaAsociada> cuentasAsociadas;
	private String numeroCuentaAsociada;

	private double cantidad;

	@PostConstruct
	public void inicializar() {
		tipoSeleccionado = sesion.getUsuario().getCliente().getIdentificationType();
		id_cliente = sesion.getUsuario().getCliente().getIdentificationNumber();

		cuentasAhorro = pagoejb.listarCuentaAhorros(tipoSeleccionado, id_cliente);
		cuentasAsociadas = asociadasejb.listarCuentasAsociadasVerificadas(sesion.getUsuario().getCliente());
	}

	/**
	 * metodo que redirige a la pagina inicio
	 * 
	 * @return
	 */
	public String cancelar() {
		return "/paginas/seguro/inicio.xhtml";
	}

	public void realizarTransferencia() {
		try {
			transaccionejb.transferenciaACH(numeroCuentaOrigen, numeroCuentaAsociada, cantidad);
			System.out.println(cantidad + "**********************************");
			Messages.addFlashGlobalInfo("Transferencia realizada");
			cantidad = 0;
		} catch (ExcepcionNegocio e) {
			// TODO: handle exception
			Messages.addGlobalError(e.getMessage());
		}
	}
	

	/**
	 * @return the cuentasAhorro
	 */
	public List<CuentaAhorros> getCuentasAhorro() {
		return cuentasAhorro;
	}

	/**
	 * @param cuentasAhorro
	 *            the cuentasAhorro to set
	 */
	public void setCuentasAhorro(List<CuentaAhorros> cuentasAhorro) {
		this.cuentasAhorro = cuentasAhorro;
	}

	/**
	 * @return the cuentasAsociadas
	 */
	public List<CuentaAsociada> getCuentasAsociadas() {
		return cuentasAsociadas;
	}

	/**
	 * @param cuentasAsociadas
	 *            the cuentasAsociadas to set
	 */
	public void setCuentasAsociadas(List<CuentaAsociada> cuentasAsociadas) {
		this.cuentasAsociadas = cuentasAsociadas;
	}

	/**
	 * @return the cantidad
	 */
	public double getCantidad() {
		return cantidad;
	}

	/**
	 * @param cantidad
	 *            the cantidad to set
	 */
	public void setCantidad(double cantidad) {
		this.cantidad = cantidad;
	}

	/**
	 * @return the numeroCuentaOrigen
	 */
	public String getNumeroCuentaOrigen() {
		return numeroCuentaOrigen;
	}

	/**
	 * @param numeroCuentaOrigen
	 *            the numeroCuentaOrigen to set
	 */
	public void setNumeroCuentaOrigen(String numeroCuentaOrigen) {
		this.numeroCuentaOrigen = numeroCuentaOrigen;
	}

	/**
	 * @return the numeroCuentaAsociada
	 */
	public String getNumeroCuentaAsociada() {
		return numeroCuentaAsociada;
	}

	/**
	 * @param numeroCuentaAsociada
	 *            the numeroCuentaAsociada to set
	 */
	public void setNumeroCuentaAsociada(String numeroCuentaAsociada) {
		this.numeroCuentaAsociada = numeroCuentaAsociada;
	}

}
