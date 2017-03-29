package co.edu.eam.ingesoft.pa.negocio.beans;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import co.edu.eam.ingesoft.pa.negocio.excepciones.ExcepcionNegocio;
import co.edu.eam.ingesoft.pa.negocio.interfaces.IProductoRemote;
import entidades.Cliente;
import entidades.ClientePK;
import entidades.CuentaAhorros;
import entidades.Franquicia;
import entidades.Producto;
import entidades.TarjetaCredito;

@LocalBean
@Stateless
@Remote(IProductoRemote.class)
public class ProductoEJB {

	@PersistenceContext
	private EntityManager em;

	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void crearTarjetaCredito(TarjetaCredito tar) {
		TarjetaCredito bus = buscarTarjeta(tar.getNumber());
		if (bus == null) {
			em.persist(tar);
			
		} else {
			throw new ExcepcionNegocio("Ya esta este numero de tarjeta registrado");
		}
	}
	
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public boolean editarCuentaAhorros(CuentaAhorros cu){
		try{
			em.merge(cu);
			return true;
		}catch(Exception e){
			return false;
		}
	}
	
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public boolean editarTC(TarjetaCredito tc){
		try{
			em.merge(tc);
			return true;
		}catch(Exception e){
			return false;
		}
	}



	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void crearCuentaAhorros(CuentaAhorros cu) {
		CuentaAhorros bus = buscarCuentaAhorros(cu.getNumber());
		if (bus == null) {
			em.persist(cu);
		} else {
			throw new ExcepcionNegocio("Ya esta este numero de cuenta registrado");
		}
	}

	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public TarjetaCredito buscarTarjeta(String numero) {
		TarjetaCredito tj = em.find(TarjetaCredito.class, numero);
		return tj;
	}

	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public CuentaAhorros buscarCuentaAhorros(String numero) {
		CuentaAhorros cu = em.find(CuentaAhorros.class, numero);
		return cu;
	}

	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public List<Franquicia> listarFranquicias() {
		List<Franquicia> list = em.createNamedQuery(Franquicia.findAll).getResultList();
		return list;
	}

	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public Franquicia buscarPorNombreFranquicia(String nombre) {
		Franquicia fran = (Franquicia) em.createNamedQuery(Franquicia.findByName).setParameter(1, nombre)
				.getSingleResult();
		return fran;
	}

	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public String generarCuentaAhorro() {
		String total = "";
		int acum = 1;
		String ultimoNum;
		String finall;
		for (int i = 0; i < 10; i++) {
			int valorx = (int) ((Math.random() * 9) + 1);
			total = total + valorx;
			acum *= valorx;
		}
		String ultimoNumero = acum + "";
		System.out.println("Numero 10 digitos aleatorio: " + total);
		System.out.println("multiplicacion entre los 10 digitos: " + acum);
		ultimoNum = ultimoNumero.charAt(ultimoNumero.length() - 1) + "";
		System.out.println("ultimo digito de la multiplicacion: " + ultimoNum);

		finall = total + ultimoNum;
		System.out.println("Numero final concatenado: " + finall);
		return finall;
	}

	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public Date generarFechaExpiracion() {
		Date nuevaFecha = new Date();
		java.util.Date fecha = new Date();
		System.out.println("fecha de hoy: " + fecha);

		Calendar cal = Calendar.getInstance();
		cal.setTime(nuevaFecha);
		cal.add(Calendar.MONTH, 48);
		nuevaFecha = cal.getTime();

		System.out.println("fecha de expiración: " + nuevaFecha);

		return nuevaFecha;

	}

	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public String generarTarjetaCredito() {
		String total = "";
		for (int i = 0; i < 16; i++) {
			int valorx = (int) ((Math.random() * 9) + 1);
			total = total + valorx;
		}
		System.out.println("Numero 10 digitos aleatorio: " + total);
		return total;
	}

	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public String generarCVC() {
		String total = "";
		for (int i = 0; i < 3; i++) {
			int valorx = (int) ((Math.random() * 9) + 1);
			total = total + valorx;
		}
		System.out.println(total);
		return total;
	}

	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public int verificarCantidadTC(Cliente cli) {
		Long cantidad = 0l;
		 cantidad = (Long) em.createNamedQuery(TarjetaCredito.contarCantidadTCCliente).setParameter(1, cli)
				.getSingleResult();
		return cantidad.intValue();
	}
	
	public boolean verificarCantidadPro(Cliente cli){
		int cantidad = CantidadPro(cli);
		if(cantidad<=4){
			return true;
		}else{
			return false;
		}
	}
	public int CantidadPro(Cliente cli) {
		Long cantidad = 0l;
		 cantidad = (Long) em.createNamedQuery(Producto.contarCantidadProcli).setParameter(1, cli)
				.getSingleResult();
		return cantidad.intValue();
	}
	
	
	
	
}
