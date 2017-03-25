/**
 * 
 */
package co.edu.eam.ingesoft.pa.avanzada.controlador;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.inject.Inject;
import javax.inject.Named;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Length;
import org.omnifaces.cdi.ViewScoped;
import org.omnifaces.util.Messages;

import co.edu.eam.ingesoft.pa.negocio.beans.BancoEJB;
import co.edu.eam.ingesoft.pa.negocio.beans.ClienteEJB;
import co.edu.eam.ingesoft.pa.negocio.beans.CuentaAsociadaEJB;
import co.edu.eam.ingesoft.pa.negocio.excepciones.ExcepcionNegocio;
import entidades.Banco;
import entidades.Cliente;
import entidades.ConsumoTarjetaCredito;
import entidades.CuentaAsociada;
import enumeraciones.TipoDocumento;

/**
 * @author Alejandro Ortega
 *
 */
@Named("ControladorCuentasAsociadas")
@ViewScoped
public class ControladorCuentasAsociadas implements Serializable {

	/**
	 * tipo de documento a seleccionar
	 */
	private TipoDocumento tipoSeleccionado;

	// DATOS DEL QUE INICIO SESION
	private TipoDocumento tipoSeleccionadoPrincipal;
	private String id_cliente;

	private String bancoSeleccionado;
	
	private Banco banco;

	private List<Banco> bancos;
	
	private List<CuentaAsociada> cuentasAsociadas;

	@Pattern(regexp = "[0-9]*", message = "solo numeros")
	@Length(min = 4, max = 15, message = "longitud entre 4 y 15")
	private String numeroIdentificacionTitular;

	@Pattern(regexp = "[A-Za-z ]*", message = "solo Letras")
	@Length(min = 3, max = 50, message = "longitud entre 3 y 50")
	private String nombreTitular;

	@Pattern(regexp = "[0-9]*", message = "solo numeros")
	@Length(min = 10, max = 20, message = "longitud entre 10 y 20")
	private String numeroCuenta;

	@Pattern(regexp = "[A-Za-z ]*", message = "solo Letras")
	@Length(min = 3, max = 50, message = "longitud entre 3 y 50")
	private String nombreAsociacion;
	
	private String estado = "VERIFICADO";

	@EJB
	private CuentaAsociadaEJB asociadasejb;
	
	@EJB
	private ClienteEJB clienteejb;
	
	@EJB
	private BancoEJB bancoejb;
	
	@Inject
	private ControladorSesion sesion;


	@PostConstruct
	public void inicializar() {
		//tipoSeleccionadoPrincipal = sesion.getUsuario().getCliente().getIdentificationType();
		//id_cliente = sesion.getUsuario().getCliente().getIdentificationNumber();
	//	clienteejb.buscarCliente(tipoSeleccionadoPrincipal, id_cliente);
		bancos = asociadasejb.listarBancos();
		cuentasAsociadas =	asociadasejb.listarCuentasAsociadas(sesion.getUsuario().getCliente());

	}

	public TipoDocumento[] getTipos() {
		return TipoDocumento.values();
	}
	
	
	
	
	public void guardarAsociacion(){
		try{
			banco = bancoejb.buscarBanco(bancoSeleccionado);
			CuentaAsociada cu = new CuentaAsociada(numeroCuenta, nombreAsociacion, nombreTitular,
					tipoSeleccionado, numeroIdentificacionTitular, estado, banco, sesion.getUsuario().getCliente());
					asociadasejb.crearAsociacion(cu);
					cuentasAsociadas = asociadasejb.listarCuentasAsociadas(sesion.getUsuario().getCliente());
					
					// limpiar campos
					numeroCuenta ="";
					nombreAsociacion  ="";
					nombreTitular ="";
					numeroIdentificacionTitular ="";
					
					Messages.addFlashGlobalInfo("Cuenta de ahorros asociada");
			}catch(ExcepcionNegocio e){
				Messages.addGlobalError(e.getMessage());
			}
	}
	
	public void eliminarAsociacion(CuentaAsociada cuenta){
		try{
			asociadasejb.eliminarCuentaAsociada(cuenta);
			cuentasAsociadas = asociadasejb.listarCuentasAsociadas(sesion.getUsuario().getCliente());
			Messages.addFlashGlobalInfo("Cuenta de ahorros eliminada");
			
		}catch (ExcepcionNegocio e) {
			// TODO: handle exception
			Messages.addGlobalError(e.getMessage());
		}
	}

	/**
	 * @return the tipoSeleccionado
	 */
	public TipoDocumento getTipoSeleccionado() {
		return tipoSeleccionado;
	}

	/**
	 * @param tipoSeleccionado
	 *            the tipoSeleccionado to set
	 */
	public void setTipoSeleccionado(TipoDocumento tipoSeleccionado) {
		this.tipoSeleccionado = tipoSeleccionado;
	}

	

	/**
	 * @return the numeroIdentificacionTitular
	 */
	public String getNumeroIdentificacionTitular() {
		return numeroIdentificacionTitular;
	}

	/**
	 * @param numeroIdentificacionTitular
	 *            the numeroIdentificacionTitular to set
	 */
	public void setNumeroIdentificacionTitular(String numeroIdentificacionTitular) {
		this.numeroIdentificacionTitular = numeroIdentificacionTitular;
	}

	/**
	 * @return the nombreTitular
	 */
	public String getNombreTitular() {
		return nombreTitular;
	}

	/**
	 * @param nombreTitular
	 *            the nombreTitular to set
	 */
	public void setNombreTitular(String nombreTitular) {
		this.nombreTitular = nombreTitular;
	}

	/**
	 * @return the numeroCuenta
	 */
	public String getNumeroCuenta() {
		return numeroCuenta;
	}

	/**
	 * @param numeroCuenta
	 *            the numeroCuenta to set
	 */
	public void setNumeroCuenta(String numeroCuenta) {
		this.numeroCuenta = numeroCuenta;
	}

	/**
	 * @return the nombreAsociacion
	 */
	public String getNombreAsociacion() {
		return nombreAsociacion;
	}

	/**
	 * @param nombreAsociacion
	 *            the nombreAsociacion to set
	 */
	public void setNombreAsociacion(String nombreAsociacion) {
		this.nombreAsociacion = nombreAsociacion;
	}

	/**
	 * @return the bancos
	 */
	public List<Banco> getBancos() {
		return bancos;
	}

	/**
	 * @param bancos the bancos to set
	 */
	public void setBancos(List<Banco> bancos) {
		this.bancos = bancos;
	}

	/**
	 * @return the estado
	 */
	public String getEstado() {
		return estado;
	}

	/**
	 * @param estado the estado to set
	 */
	public void setEstado(String estado) {
		this.estado = estado;
	}

	/**
	 * @return the cuentasAsociadas
	 */
	public List<CuentaAsociada> getCuentasAsociadas() {
		return cuentasAsociadas;
	}

	/**
	 * @param cuentasAsociadas the cuentasAsociadas to set
	 */
	public void setCuentasAsociadas(List<CuentaAsociada> cuentasAsociadas) {
		this.cuentasAsociadas = cuentasAsociadas;
	}

	/**
	 * @return the bancoSeleccionado
	 */
	public String getBancoSeleccionado() {
		return bancoSeleccionado;
	}

	/**
	 * @param bancoSeleccionado the bancoSeleccionado to set
	 */
	public void setBancoSeleccionado(String bancoSeleccionado) {
		this.bancoSeleccionado = bancoSeleccionado;
	}

	/**
	 * @return the banco
	 */
	public Banco getBanco() {
		return banco;
	}

	/**
	 * @param banco the banco to set
	 */
	public void setBanco(Banco banco) {
		this.banco = banco;
	}

}
