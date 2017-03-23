package co.edu.eam.ingesoft.pa.negocio.interfaces;

import java.util.Date;
import java.util.List;

import entidades.Cliente;
import entidades.CuentaAhorros;
import entidades.Franquicia;
import entidades.TarjetaCredito;

public interface IProductoRemote {
	
	
	public void crearTarjetaCredito(TarjetaCredito tar);
	public TarjetaCredito buscarTarjeta(String numero);
	public List<Franquicia> listarFranquicias();
	public Franquicia buscarPorNombreFranquicia(String nombre);
	public CuentaAhorros buscarCuentaAhorros(String numero);
	public void crearCuentaAhorros(CuentaAhorros cu);
	public String generarCVC();
	public String generarCuentaAhorro();
	public Date generarFechaExpiracion();
	public String generarTarjetaCredito();
	public int verificarCantidadTC(Cliente cli);
	public int CantidadPro(Cliente cli);
	public boolean verificarCantidadPro(Cliente cli);
	public boolean editarCuentaAhorros(CuentaAhorros cu);
	public boolean editarTC(TarjetaCredito tc);
	
	
	

}
