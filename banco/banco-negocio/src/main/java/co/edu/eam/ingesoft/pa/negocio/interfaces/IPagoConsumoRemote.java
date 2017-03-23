package co.edu.eam.ingesoft.pa.negocio.interfaces;

import java.util.ArrayList;
import java.util.List;

import entidades.ConsumoTarjetaCredito;
import entidades.CuentaAhorros;
import entidades.PagoCunsumoTarCredito;
import entidades.TarjetaCredito;
import enumeraciones.TipoDocumento;

public interface IPagoConsumoRemote {
	
	public void GuardarPagoConsumo(PagoCunsumoTarCredito pago);
	public PagoCunsumoTarCredito buscarPago(int num);
	public double calcularConsumo(ConsumoTarjetaCredito con);
	public ArrayList<ConsumoTarjetaCredito> buscarConsumos(String tc);
	public void pagarCuota(double valorIngresado, TarjetaCredito tc, double valorCuota, double deudatotal);
	public double calcularCuota(TarjetaCredito tc);
	public double valorDeuda(TarjetaCredito tc);
	public double calcularCuotaSinInteres(TarjetaCredito tc);
	public ArrayList<ConsumoTarjetaCredito> buscarConsumosParaWeb(String tc,String idCliente);
	public List<CuentaAhorros> listarCuentaAhorros(TipoDocumento tipo, String documentoId);
	

}
