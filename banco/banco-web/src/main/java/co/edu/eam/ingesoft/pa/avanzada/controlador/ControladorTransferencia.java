package co.edu.eam.ingesoft.pa.avanzada.controlador;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.inject.Inject;
import javax.inject.Named;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Length;
import org.omnifaces.cdi.ViewScoped;
import org.omnifaces.util.Messages;
import org.primefaces.context.RequestContext;

import co.edu.eam.ingesoft.pa.negocio.beans.CodigosEJB;
import co.edu.eam.ingesoft.pa.negocio.beans.CuentaAsociadaEJB;
import co.edu.eam.ingesoft.pa.negocio.beans.NotificacionesEJB;
import co.edu.eam.ingesoft.pa.negocio.beans.OperacionesCuentaAhorros;
import co.edu.eam.ingesoft.pa.negocio.beans.PagoConsumoEJB;
import co.edu.eam.ingesoft.pa.negocio.excepciones.ExcepcionNegocio;
import entidades.CuentaAhorros;
import entidades.CuentaAsociada;
import entidades.SegundaClave;
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

	@EJB
	private CodigosEJB codigosejb;
	
	@EJB
	private NotificacionesEJB webservice;

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

	private String segundaClave;

	@Pattern(regexp="[0-9]*",message="solo numeros")
	@Length(min=6,max=6,message="debe contener 6 digitos")
	private String codigo;

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

	public void generarCodigo() {
		transaccionejb.generarSegundaClave(sesion.getUsuario().getCliente().getCorreo(),
				sesion.getUsuario().getCliente().getCelular());
		Messages.addFlashGlobalInfo("Ingrese el codigo enviado al correo electronico antes de los proximos dos minutos"
				+ "\n o su codigo expirara");
	}

	public void realizarTransferencia() {
		try {
			SegundaClave segundaClave = codigosejb.buscarCodigo(codigo);
			long inicio = segundaClave.getFecha().getTime();
			long fin = new Date().getTime();

			long res = fin - inicio;
			res = res / (1000 * 60);			
			if(res<2){
				if(segundaClave.getCodigo().equals(codigo)){
					if(cantidad>0){
					CuentaAsociada cu	= asociadasejb.buscarCuentaAsociada(numeroCuentaAsociada);
			String msj =	webservice.transferirMonto(numeroCuentaOrigen, cu.getBanco().getId(), numeroCuentaAsociada, cantidad);
				Messages.addFlashGlobalInfo(msj);
					}else{
						Messages.addGlobalError("Ingrese valor de la transferencia");
					}
				}else{
					Messages.addGlobalError("Codigo incorrecto");
				}
			}else{
				Messages.addGlobalError("Este codigo ya expiro");

			}

			
			
			//cantidad = 0;
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

	/**
	 * @return the segundaClave
	 */
	public String getSegundaClave() {
		return segundaClave;
	}

	/**
	 * @param segundaClave
	 *            the segundaClave to set
	 */
	public void setSegundaClave(String segundaClave) {
		this.segundaClave = segundaClave;
	}

	/**
	 * @return the codigo
	 */
	public String getCodigo() {
		return codigo;
	}

	/**
	 * @param codigo
	 *            the codigo to set
	 */
	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

}
