package co.edu.eam.ingesoft.pa.negocio.beans;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import co.edu.eam.ingesoft.pa.negocio.excepciones.ExcepcionNegocio;
import co.edu.eam.ingesoft.pa.negocio.interfaces.IPagoConsumoRemote;
import entidades.Cliente;
import entidades.ConsumoTarjetaCredito;
import entidades.CuentaAhorros;
import entidades.PagoCunsumoTarCredito;
import entidades.Producto;
import entidades.TarjetaCredito;
import enumeraciones.TipoDocumento;

@LocalBean
@Stateless
@Remote(IPagoConsumoRemote.class)
public class PagoConsumoEJB {

	@PersistenceContext
	EntityManager em;

	@EJB
	ProductoEJB productosejb;
	@EJB
	ClienteEJB clienteejb;
	@EJB
	OperacionesCuentaAhorros operacionejb;

	/**
	 * metodo para calcular la cuota de un solo consumo con el interes incluido
	 * 
	 * @param con
	 *            consumo al cual se le calculara la cuora
	 * @return valor de la cuota del consumo con interes
	 */
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public double calcularConsumo(ConsumoTarjetaCredito con) {
		double valorConsumo = con.getRemainingAmmount() * 0.036;
		// double valorInteres = con.getInterest()/100;
		// double abonoInteres = valorConsumo * valorInteres;
		double abonoCapital = con.getAmmount() / con.getNumberShares();
		double valorCuota = abonoCapital + valorConsumo;
		return valorCuota;
	}

	/**
	 * calcular un consumo sin interes
	 * 
	 * @param con,
	 *            consumo que se le calculara la cuota
	 * @return valor de la cuota de ese consumo sin interes
	 */
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public double calcularConsumoSinInteres(ConsumoTarjetaCredito con) {
		// double valorInteres = con.getInterest()/100;
		// double abonoInteres = valorConsumo * valorInteres;
		double abonoCapital = con.getAmmount() / con.getNumberShares();
		double valorCuota = abonoCapital;
		return valorCuota;
	}

	/**
	 * calcular la cuota de una tarjeta de credito, suma de todos los consumos
	 * sin interes
	 * 
	 * @param tc,
	 *            tarjta de credito que se le calculara la cuota
	 * @return valor de la cuota sin interes
	 */
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public double calcularCuotaSinInteres(TarjetaCredito tc) {
		ArrayList<ConsumoTarjetaCredito> consumos = buscarConsumos(tc.getNumber());
		double valorCuota = 0;
		for (int i = 0; i < consumos.size(); i++) {
			valorCuota += calcularConsumoSinInteres(consumos.get(i));
		}
		return valorCuota;
	}

	/**
	 * calcular el valor de la cuota de la tc, con interes, suma de todos los
	 * consumos
	 * 
	 * @param tc,
	 *            tc que se le calculara su cuota
	 * @return el valor de la cuota con interes
	 */
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public double calcularCuota(TarjetaCredito tc) {
		ArrayList<ConsumoTarjetaCredito> consumos = buscarConsumos(tc.getNumber());
		double valorCuota = 0;
		for (int i = 0; i < consumos.size(); i++) {
			valorCuota += calcularConsumo(consumos.get(i));
		}
		return valorCuota;
	}

	/**
	 * calcular el valor de la deuda, cuanto se debe para quedar a paz y salvo
	 * 
	 * @param tc,
	 *            tc que se le hara el calculo
	 * @return valor total de toda la dedua con interes incluido
	 */
	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public double valorDeuda(TarjetaCredito tc) {
		ArrayList<ConsumoTarjetaCredito> consumos = buscarConsumos(tc.getNumber());
		double valorDeuda = 0;
		for (int i = 0; i < consumos.size(); i++) {
			valorDeuda += consumos.get(i).getAmmount() + consumos.get(i).getAmmount() * 0.036;
		}
		return valorDeuda;
	}

	/**
	 * pagar la cuota definida por el sistema de la tarjeta de credito
	 * 
	 * @param valorIngresado,
	 *            valor que se pagara, ni mas ni menos, este valor es = a
	 *            valorCuota
	 * @param tc,
	 *            tarjeta de credito que se le va a pagar la cuota
	 * @param valorCuota,
	 *            el valor de la cuota, este valor debe ser el mismo de valor
	 *            ingresado
	 * @param deudatotal,
	 *            el valor de la deuda total con el banco para pagar la tc
	 */
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void pagarCuota(double valorIngresado, TarjetaCredito tc, double valorCuota, double deudatotal) {
		ArrayList<ConsumoTarjetaCredito> consumos = buscarConsumos(tc.getNumber());
		if (valorIngresado < valorCuota) {
			throw new ExcepcionNegocio("Valor no paga la cuota");
		} else if (valorIngresado > deudatotal) {
			throw new ExcepcionNegocio("No puede quedar dinero sobrante");
		} else {
			for (int i = 0; i < consumos.size(); i++) {
				if (consumos.get(i).getCuotasRestantes() == 0) {
					consumos.get(i).setPayed(true);
					em.merge(consumos.get(i));
				} else if (consumos.get(i).isPayed() == true) {
					throw new ExcepcionNegocio("ESTE CONSUMO YA ESTA PAGO");
				} else if (consumos.get(i).getRemainingAmmount() <= 0) {
					consumos.get(i).setPayed(true);
					em.merge(consumos.get(i));
					throw new ExcepcionNegocio("ESTE CONSUMO YA ESTA PAGO");
				} else {
					double valorcon = calcularConsumo(consumos.get(i));
					valorIngresado = valorIngresado - valorcon;
					double abonoInteres = consumos.get(i).getInterest();
					double abonoCapital = calcularConsumoSinInteres(consumos.get(i));
					Date fecha = new Date();
					int cuota = consumos.get(i).getNumberShares() - consumos.get(i).getCuotasRestantes();
					PagoCunsumoTarCredito pago = new PagoCunsumoTarCredito(0, fecha, consumos.get(i), cuota + 1,
							consumos.get(i).getAmmount(), abonoCapital, abonoInteres);
					GuardarPagoConsumo(pago);
					tc.setAmmount(tc.getAmmount() + abonoCapital);
					em.merge(tc);
					int cout = consumos.get(i).getCuotasRestantes();
					consumos.get(i).setCuotasRestantes(cout - 1);
					if (consumos.get(i).getCuotasRestantes() == 0) {
						consumos.get(i).setPayed(true);
					}
					consumos.get(i).setRemainingAmmount(consumos.get(i).getRemainingAmmount() - abonoCapital);
					em.merge(consumos.get(i));
				}
			}
		}
	}

	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	/**
	 * 
	 * @param numero,
	 *            numero de la cuenta de ahorros del cliente
	 * @param documento,
	 *            numero de id del cliente
	 * @param tipoid,
	 *            tipo del documento del cliente
	 * @param valorCuota,
	 *            valor de la cuota (suma de todos los consumos con interes
	 *            incluido)
	 * @param valordeuda,
	 *            valor de toda la deuda(suma de todas las cuotas)
	 * @param tc,
	 *            numero de tarjeta de credito del cliente
	 */
	public void pagarConsumoWeb(String numero, String documento, TipoDocumento tipoid, double valorCuota,
			double valordeuda, String tc) {
		Cliente cl = clienteejb.buscarCliente(tipoid, documento);
		CuentaAhorros cu = productosejb.buscarCuentaAhorros(numero);
		TarjetaCredito tcc = productosejb.buscarTarjeta(tc);
		if (cl == null) {
			throw new ExcepcionNegocio("No se encontro el cliente");
		} else if (cu == null) {
			throw new ExcepcionNegocio("No se encontro la cuenta");
		} else if (tcc == null) {
			throw new ExcepcionNegocio("No se encontro la tarjeta");
		} else {
			if (cl.getIdentificationNumber().equalsIgnoreCase(cu.getCliente().getIdentificationNumber())) {
				if (cu.getAmmount() < valorCuota) {
					throw new ExcepcionNegocio("No tiene dinero en la cuenta para pagar ");
				}
				Date fecha = new Date();
				boolean retirado = operacionejb.retiro(cu, valorCuota, fecha, "Pagina web", "Retiro");
				if (retirado) {
					pagarCuota(valorCuota, tcc, valorCuota, valordeuda);
				} else {
					throw new ExcepcionNegocio("No se pudo completar el pago");
				}
			} else {
				throw new ExcepcionNegocio(
						"No se pudo completar el pago, esta cuenta pertenece a un cliente diferente");
			}
		}
	}

	/**
	 * guardar el pago realizado de  un consumo
	 * @param pago, pago realizado
	 */
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void GuardarPagoConsumo(PagoCunsumoTarCredito pago) {

		PagoCunsumoTarCredito bus = buscarPago(pago.getId());
		if (bus == null) {
			em.persist(pago);
		} else {
			throw new ExcepcionNegocio("Ya existe el cliente");
		}
	}

	/**
	 * buscar un pago realizado
	 * @param num,
	 *            id del pago de consumo a buscar
	 * @return
	 */
	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public PagoCunsumoTarCredito buscarPago(int num) {
		PagoCunsumoTarCredito pago = em.find(PagoCunsumoTarCredito.class, num);
		return pago;
	}

	/**
	 * buscar todos los consumos de una tc en particular
	 * @param tc, la tarjeta de credito que traera los consumos
	 * @return una lista de todos los consumos de esa tc
	 */
	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public ArrayList<ConsumoTarjetaCredito> buscarConsumos(String tc) {
		ArrayList<ConsumoTarjetaCredito> list;
		TarjetaCredito tcc = productosejb.buscarTarjeta(tc);
		list = (ArrayList<ConsumoTarjetaCredito>) em.createNamedQuery(ConsumoTarjetaCredito.finByTc)
				.setParameter(1, tcc).getResultList();
		return list;
	}

	/**
	 * 
	 * @param tc
	 * @param idCliente
	 * @return
	 */
	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public ArrayList<ConsumoTarjetaCredito> buscarConsumosParaWeb(String tc, String idCliente) {
		ArrayList<ConsumoTarjetaCredito> list;
		TarjetaCredito tcc = productosejb.buscarTarjeta(tc);
		list = (ArrayList<ConsumoTarjetaCredito>) em.createNamedQuery(ConsumoTarjetaCredito.finByTc)
				.setParameter(1, tcc).setParameter(2, idCliente).getResultList();
		return list;
	}

	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public List<ConsumoTarjetaCredito> listar(String tc, String idCliente) {
		TarjetaCredito tcc = productosejb.buscarTarjeta(tc);
		return em.createNamedQuery(ConsumoTarjetaCredito.finByTcyWeb).setParameter(1, tcc).setParameter(2, idCliente)
				.getResultList();
	}

	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public List<TarjetaCredito> listarTCCliente(String idCliente, TipoDocumento tipodoc) {
		Cliente cli = clienteejb.buscarCliente(tipodoc, idCliente);
		ArrayList<TarjetaCredito> list;
		list = (ArrayList<TarjetaCredito>) em.createNamedQuery(TarjetaCredito.tarjetasCliente).setParameter(1, cli)
				.getResultList();
		return list;
	}

	/**
	 * metodo que lista las cuentas de ahorro de un cliente
	 * 
	 * @param cliente
	 *            el cliente que se le buscaran las cuentas
	 * @return lista de las cuentas
	 */
	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public List<CuentaAhorros> listarCuentaAhorros(TipoDocumento tipo, String documentoId) {
		Cliente cli = clienteejb.buscarCliente(tipo, documentoId);
		Query q = em.createNamedQuery(CuentaAhorros.listarCuentaAhorro);
		q.setParameter(1, cli);
		List<CuentaAhorros> lista = q.getResultList();
		return lista;
	}

	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void pagarUnConsumoWeb(ConsumoTarjetaCredito consu, String cuenta) {
		CuentaAhorros cu = productosejb.buscarCuentaAhorros(cuenta);
		Date fecha = new Date();
		double valorConsumo = consu.getAmmount();
		double capitalAmmount = consu.getRemainingAmmount();
		int cuota = consu.getNumberShares() - consu.getCuotasRestantes();
		PagoCunsumoTarCredito pago = new PagoCunsumoTarCredito(0, fecha, consu, cuota, valorConsumo, capitalAmmount,
				3.6);
		GuardarPagoConsumo(pago);
		cu.setAmmount(cu.getAmmount() - capitalAmmount + capitalAmmount * 0.036);
		em.merge(cu);
		consu.setPayed(true);
		consu.setCuotasRestantes(0);
		consu.setRemainingAmmount(consu.getRemainingAmmount() - capitalAmmount);
		TarjetaCredito tc = productosejb.buscarTarjeta(consu.getCreditCardNumber().getNumber());
		tc.setAmmount(tc.getAmmount() + capitalAmmount);
		em.merge(tc);
		em.merge(consu);

	}
}
