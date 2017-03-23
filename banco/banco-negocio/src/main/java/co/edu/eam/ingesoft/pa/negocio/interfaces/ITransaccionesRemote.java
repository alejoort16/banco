package co.edu.eam.ingesoft.pa.negocio.interfaces;

import java.util.Date;

import entidades.CuentaAhorros;
import entidades.Transaccion;

public interface ITransaccionesRemote {
	
	public void GuardarTransaccion(Transaccion tra);
	public Transaccion buscarTransaccion(int num);
	public boolean consignacion(CuentaAhorros cuenta, double cantidad,Date fechaTransaccion,
			String fuenteTr,String tipo);
	public boolean retiro(CuentaAhorros cuenta, double cantidad,Date fechaTransaccion,
			String fuenteTr,String tipo);
	//public boolean transferencia(CuentaAhorros cuenta, double cantidad,Date fechaTransaccion,
	//		String fuenteTr,String tipo);
	public void transferir(String cuentaActu, String cuentaDest, double monto);

}
