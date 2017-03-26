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

import co.edu.eam.ingesoft.pa.negocio.beans.ClienteEJB;
import co.edu.eam.ingesoft.pa.negocio.beans.PagoConsumoEJB;
import co.edu.eam.ingesoft.pa.negocio.beans.ProductoEJB;
import co.edu.eam.ingesoft.pa.negocio.excepciones.ExcepcionNegocio;
import entidades.Cliente;
import entidades.ConsumoTarjetaCredito;
import entidades.CuentaAhorros;
import entidades.PagoCunsumoTarCredito;
import entidades.TarjetaCredito;
import enumeraciones.TipoDocumento;

@Named("ControladorPagos")
@ViewScoped
public class ControladorPago implements Serializable {
	
	@Inject
	private ControladorSesion sesion;

	/**
	 * tipo de documento del cliente
	 */
	private TipoDocumento tipoSeleccionado;

	/**
	 * numero de la cuenta, lo escogido por el combo se guardara en este valor
	 */
	private String numeroCuenta;

	/**
	 * numero de la tarjeta de credito, lo escogido por el combo de tc se
	 * guardara en este valor
	 */
	private String numeroTc;

	/**
	 * id del cliente
	 */
	@Pattern(regexp = "[0-9]*", message = "solo numeros")
	@Length(min = 4, max = 15, message = "longitud entre 4 y 15")
	private String id_cliente;

	/**
	 * valor del consumo
	 */
	private double ammount;

	/**
	 * valor que se abonara al capital del consumo
	 */
	private double capitalAmmount;

	/**
	 * interes del consumo
	 */
	private double interestAmmount;

	/**
	 * fecha en que se saco el consumo o avance
	 */
	private Date paymentDate;

	/**
	 * numero de cuotas restantes para pagar todo el consumo
	 */
	private int share;

	/**
	 * id del consumo que se va a pagar o abonar
	 */
	private int id_consumo;

	/**
	 * valor a pagar en caso de pagar la cuota
	 */
	private double valorPagar;

	/**
	 * deuda total que se debe, suma de todos los consumos y todas las cuotas
	 */
	private double valorDeuda;

	/**
	 * lista de consumos en la tabla
	 */
	private List<ConsumoTarjetaCredito> consumos;

	/**
	 * lista de tarjetas, combo tc
	 */
	private List<TarjetaCredito> tarjetas;

	/**
	 * lista de cuentas de ahorro, combo cuentas
	 */
	private List<CuentaAhorros> cuentasAhorro;

	/**
	 * tarjeta de credito seleccionada
	 */
	private String tcSeleccionada;

	// EJB'S

	@EJB
	PagoConsumoEJB pagoejb;

	@EJB
	ClienteEJB clienteejb;

	@EJB
	ProductoEJB productoejb;

	/**
	 * getTipos, para capturas que tipo de documento se escogio
	 * 
	 * @return
	 */
	public TipoDocumento[] getTipos() {
		return TipoDocumento.values();
	}

	@PostConstruct
	public void inicializar() {
		tipoSeleccionado = sesion.getUsuario().getCliente().getIdentificationType();
		id_cliente = sesion.getUsuario().getCliente().getIdentificationNumber();
		buscarCliente();
	}

	/**
	 * paga todo un consumo esocgido en la tabla
	 * 
	 * @param consumo,
	 *            consumo escogido
	 */
	public void pagarTodoConsumo(ConsumoTarjetaCredito consumo) {
		pagoejb.pagarUnConsumoWeb(consumo, numeroCuenta);
		listar();
		Messages.addGlobalInfo("Consumo pagado");

	}

	/**
	 * buscar un cliente por identificacion y tipo, si lo encuentra
	 * automaticamente carga los combos de tc y cuentas con sus respectivas
	 */
	public void buscarCliente() {
		try {
			tarjetas = pagoejb.listarTCCliente(id_cliente, tipoSeleccionado);
			cuentasAhorro = pagoejb.listarCuentaAhorros(tipoSeleccionado, id_cliente);
			if (tarjetas.isEmpty()) {
				Messages.addGlobalError("No tiene tarjetas de credito");
			} else if (cuentasAhorro.isEmpty()) {

				Messages.addGlobalError("No tiene cuentas de ahorros");
			} else {
				numeroTc = tarjetas.get(0).getNumber();
				numeroCuenta = cuentasAhorro.get(0).getNumber();
			}
		} catch (ExcepcionNegocio e) {
			Messages.addGlobalError(e.getMessage());
		}
	}

	/**
	 * metodo para seleccionar la tarjeta de credito del combo de tc y darle ese
	 * valor a numeroTc
	 */
	public void seleccionarTarjeta() {
		if (tcSeleccionada != null) {
			numeroTc = tcSeleccionada;

		}
	}

	/**
	 * listar todos los consumos de la tarjeta seleccionada en el combo
	 */
	public void listar() {
		try {
			if(numeroTc==null){
				Messages.addGlobalError("Este cliente No tiene tarjeta de credito");
			}else{
			consumos = pagoejb.listar(numeroTc, id_cliente);
			if (consumos.size() == 0) {
				Messages.addGlobalError("No tiene consumos con esta tarjeta de credito"); 
			}else {
				Messages.addGlobalInfo("Consumos Listados");
			}
			}
		} catch (ExcepcionNegocio e) {
			Messages.addGlobalError(e.getMessage());
		}
	}

	/**
	 * pagar la cuota correspondiente a la tarjeta de credito, con la cuenta de
	 * ahorros del mismo cliente
	 */
	public void pagar() {
		try {
			if (valorPagar == 0) {
				Messages.addGlobalError("No tiene pagos pendientes");
			} else {
				pagoejb.pagarConsumoWeb(numeroCuenta, id_cliente, tipoSeleccionado, valorPagar, valorDeuda, numeroTc);
				Messages.addFlashGlobalInfo("Pago exitoso");
				valorPagar = 0;
				interestAmmount = 0;
				capitalAmmount = 0;
				listar();
			}
		} catch (ExcepcionNegocio e) {
			// TODO: handle exception
			Messages.addGlobalError(e.getMessage());
		}
	}

	/**
	 * generar pago de la tarjeta de credito seleccionada
	 */
	public void generarPago() {
		try {
			if (numeroTc==null) {
				Messages.addGlobalError("No tiene tarjeta de credito");
			} else {
				TarjetaCredito tc = productoejb.buscarTarjeta(numeroTc);
				if (tc == null) {
					Messages.addGlobalError("Esta tarjeta de credito no existe");
				} else {
					valorPagar = pagoejb.calcularCuota(tc);
					capitalAmmount = pagoejb.calcularCuotaSinInteres(tc);
					interestAmmount = 3.6;
					valorDeuda = pagoejb.valorDeuda(tc);
					Messages.addGlobalInfo("Cuota generada");
					consumos = pagoejb.listar(numeroTc, id_cliente);
				}
			}
		}catch(

	ExcepcionNegocio e)
	{
		Messages.addGlobalError(e.getMessage());
		// TODO: handle exception
	}
	}

	// getters and settes
	/**
	 * @return the ammount
	 */
	public double getAmmount() {
		return ammount;
	}

	/**
	 * @param ammount
	 *            the ammount to set
	 */
	public void setAmmount(double ammount) {
		this.ammount = ammount;
	}

	/**
	 * @return the capitalAmmount
	 */
	public double getCapitalAmmount() {
		return capitalAmmount;
	}

	/**
	 * @param capitalAmmount
	 *            the capitalAmmount to set
	 */
	public void setCapitalAmmount(double capitalAmmount) {
		this.capitalAmmount = capitalAmmount;
	}

	/**
	 * @return the interestAmmount
	 */
	public double getInterestAmmount() {
		return interestAmmount;
	}

	/**
	 * @param interestAmmount
	 *            the interestAmmount to set
	 */
	public void setInterestAmmount(double interestAmmount) {
		this.interestAmmount = interestAmmount;
	}

	/**
	 * @return the paymentDate
	 */
	public Date getPaymentDate() {
		return paymentDate;
	}

	/**
	 * @param paymentDate
	 *            the paymentDate to set
	 */
	public void setPaymentDate(Date paymentDate) {
		this.paymentDate = paymentDate;
	}

	/**
	 * @return the share
	 */
	public int getShare() {
		return share;
	}

	/**
	 * @param share
	 *            the share to set
	 */
	public void setShare(int share) {
		this.share = share;
	}

	/**
	 * @return the id_consumo
	 */
	public int getId_consumo() {
		return id_consumo;
	}

	/**
	 * @param id_consumo
	 *            the id_consumo to set
	 */
	public void setId_consumo(int id_consumo) {
		this.id_consumo = id_consumo;
	}

	/**
	 * @return the consumos
	 */
	public List<ConsumoTarjetaCredito> getConsumos() {
		return consumos;
	}

	/**
	 * @param consumos
	 *            the consumos to set
	 */
	public void setConsumos(List<ConsumoTarjetaCredito> consumos) {
		this.consumos = consumos;
	}

	/**
	 * @return the numeroTc
	 */
	public String getNumeroTc() {
		return numeroTc;
	}

	/**
	 * @param numeroTc
	 *            the numeroTc to set
	 */
	public void setNumeroTc(String numeroTc) {
		this.numeroTc = numeroTc;
	}

	/**
	 * @return the id_cliente
	 */
	public String getId_cliente() {
		return id_cliente;
	}

	/**
	 * @param id_cliente
	 *            the id_cliente to set
	 */
	public void setId_cliente(String id_cliente) {
		this.id_cliente = id_cliente;
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
	 * @return the valorPagar
	 */
	public double getValorPagar() {
		return valorPagar;
	}

	/**
	 * @param valorPagar
	 *            the valorPagar to set
	 */
	public void setValorPagar(double valorPagar) {
		this.valorPagar = valorPagar;
	}

	/**
	 * @return the valorDeuda
	 */
	public double getValorDeuda() {
		return valorDeuda;
	}

	/**
	 * @param valorDeuda
	 *            the valorDeuda to set
	 */
	public void setValorDeuda(double valorDeuda) {
		this.valorDeuda = valorDeuda;
	}

	/**
	 * @return the tarjetas
	 */
	public List<TarjetaCredito> getTarjetas() {
		return tarjetas;
	}

	/**
	 * @param tarjetas
	 *            the tarjetas to set
	 */
	public void setTarjetas(List<TarjetaCredito> tarjetas) {
		this.tarjetas = tarjetas;
	}

	/**
	 * @return the tcSeleccionada
	 */
	public String getTcSeleccionada() {
		return tcSeleccionada;
	}

	/**
	 * @param tcSeleccionada
	 *            the tcSeleccionada to set
	 */
	public void setTcSeleccionada(String tcSeleccionada) {
		this.tcSeleccionada = tcSeleccionada;
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
	
	

}
