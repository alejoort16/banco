package co.eam.edu.ingesoft.pa.avanzada.controladoresGUI;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.naming.NamingException;

import co.edu.eam.ingesoft.pa.negocio.beans.OperacionesCuentaAhorros;
import co.edu.eam.ingesoft.pa.negocio.interfaces.IClienteRemote;
import co.edu.eam.ingesoft.pa.negocio.interfaces.IConsumoTCRemote;
import co.edu.eam.ingesoft.pa.negocio.interfaces.IPagoConsumoRemote;
import co.edu.eam.ingesoft.pa.negocio.interfaces.IProductoRemote;
import co.edu.eam.ingesoft.pa.negocio.interfaces.ITransaccionesRemote;
import entidades.Cliente;
import entidades.ConsumoTarjetaCredito;
import entidades.CuentaAhorros;
import entidades.Franquicia;
import entidades.PagoCunsumoTarCredito;
import entidades.TarjetaCredito;
import entidades.Transaccion;
import enumeraciones.TipoDocumento;
import util.ServiceLocator;

public class ControladorBancoPrincipal {

	private IClienteRemote clienteRemoto;
	private IProductoRemote productoRemoto;
	private ITransaccionesRemote transaccionesRemoto;
	private IConsumoTCRemote consumoRemoto;
	private IPagoConsumoRemote pagoRemoto;

	public ControladorBancoPrincipal() throws NamingException {
		clienteRemoto = (IClienteRemote) ServiceLocator.buscarEJB("ClienteEJB",
				IClienteRemote.class.getCanonicalName());

		productoRemoto = (IProductoRemote) ServiceLocator.buscarEJB("ProductoEJB",
				IProductoRemote.class.getCanonicalName());

		transaccionesRemoto = (ITransaccionesRemote) ServiceLocator.buscarEJB("OperacionesCuentaAhorros",
				ITransaccionesRemote.class.getCanonicalName());

		consumoRemoto = (IConsumoTCRemote) ServiceLocator.buscarEJB("ConsumoTCEJB",
				IConsumoTCRemote.class.getCanonicalName());
		
		pagoRemoto = (IPagoConsumoRemote) ServiceLocator.buscarEJB("PagoConsumoEJB",
				IPagoConsumoRemote.class.getCanonicalName());
	}

	public Cliente buscarCliente(TipoDocumento tipo, String num) {
		return clienteRemoto.buscarCliente(tipo, num);
	}

	public void crearCliente(Cliente cli) {
		clienteRemoto.crearCliente(cli);

	}

	public List<Franquicia> ListarFranquicia() {
		return productoRemoto.listarFranquicias();
	}

	/*
	 * oe
	 */
	public Franquicia buscarPorNombreFranquicia(String nombre) {
		return productoRemoto.buscarPorNombreFranquicia(nombre);
	}

	public void crearTarjetaCredito(TarjetaCredito tar) {
		productoRemoto.crearTarjetaCredito(tar);
	}

	public TarjetaCredito buscarTarjeta(String numero) {
		return productoRemoto.buscarTarjeta(numero);
	}

	public void crearCuentaAhorros(CuentaAhorros cu) {
		productoRemoto.crearCuentaAhorros(cu);
	}

	public CuentaAhorros buscarCuentaAhorros(String numero) {
		return productoRemoto.buscarCuentaAhorros(numero);
	}

	public String generarCVC() {
		return productoRemoto.generarCVC();
	}

	public String generarCuentaAhorro() {
		return productoRemoto.generarCuentaAhorro();
	}

	public Date generarFechaExpiracion() {
		return productoRemoto.generarFechaExpiracion();
	}

	public String generarTarjetaCredito() {
		return productoRemoto.generarTarjetaCredito();
	}

	public int verificarCantidadTC(Cliente cli) {
		return productoRemoto.verificarCantidadTC(cli);
	}

	public int CantidadPro(Cliente cli) {
		return productoRemoto.CantidadPro(cli);
	}

	public boolean verificarCantidadPro(Cliente cli) {
		return productoRemoto.verificarCantidadPro(cli);
	}

	public void GuardarTransaccion(Transaccion tra) {
		transaccionesRemoto.GuardarTransaccion(tra);
	}

	public Transaccion buscarTransaccion(int num) {
		return transaccionesRemoto.buscarTransaccion(num);
	}

	public boolean editarCuentaAhorros(CuentaAhorros cu) {
		return productoRemoto.editarCuentaAhorros(cu);
	}
	
	public boolean editarTC(TarjetaCredito tc){
		return productoRemoto.editarTC(tc);
	}

	public ConsumoTarjetaCredito buscarConsumo(int num) {
		return consumoRemoto.buscarConsumo(num);
	}

	public void GuardarConsumo(ConsumoTarjetaCredito consumo) {
		consumoRemoto.GuardarConsumo(consumo);
	}
	
	public void GuardarPagoConsumo(PagoCunsumoTarCredito pago){
		pagoRemoto.GuardarPagoConsumo(pago);
	}
	public PagoCunsumoTarCredito buscarPago(int num){
		return pagoRemoto.buscarPago(num);
	}
	public double calcularConsumo(ConsumoTarjetaCredito con){
		return pagoRemoto.calcularConsumo(con);
	}
	public ArrayList<ConsumoTarjetaCredito> buscarConsumos(String tc){
		return pagoRemoto.buscarConsumos(tc);
	}
	
	public boolean consignacion(CuentaAhorros cuenta, double cantidad,Date fechaTransaccion,
			String fuenteTr,String tipo){
		return transaccionesRemoto.consignacion(cuenta, cantidad, fechaTransaccion, fuenteTr, tipo);
	}

	public boolean retiro(CuentaAhorros cuenta, double cantidad,Date fechaTransaccion,
			String fuenteTr,String tipo){
		return transaccionesRemoto.retiro(cuenta, cantidad, fechaTransaccion, fuenteTr, tipo);
	}
	
	//public boolean transferencia(CuentaAhorros cuenta, double cantidad,Date fechaTransaccion,
		//	String fuenteTr,String tipo){
	//	return transaccionesRemoto.transferencia(cuenta, cantidad, fechaTransaccion, fuenteTr, tipo);
	//}
	public void transferir(String cuentaActu, String cuentaDest, double monto){
		transaccionesRemoto.transferir(cuentaActu, cuentaDest, monto);
	}
	
	public double valorDeuda(TarjetaCredito tc){
		return pagoRemoto.valorDeuda(tc);
	}
	
	public void pagarCuota(double valorIngresado, TarjetaCredito tc, double valorCuota, double deudatotal){
		pagoRemoto.pagarCuota(valorIngresado, tc, valorCuota, deudatotal);
	}
	
	public double calcularCuota(TarjetaCredito tc){
		return pagoRemoto.calcularCuota(tc);
	}
	
	public double calcularCuotaSinInteres(TarjetaCredito tc){
		return pagoRemoto.calcularCuotaSinInteres(tc);
	}
}
