package co.edu.eam.ingesoft.pa.negocio.beans;

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
import javax.swing.JOptionPane;

import co.edu.eam.ingesoft.pa.negocio.excepciones.ExcepcionNegocio;
import co.edu.eam.ingesoft.pa.negocio.interfaces.ITransaccionesRemote;
import entidades.Cliente;
import entidades.ClientePK;
import entidades.CuentaAhorros;
import entidades.TarjetaCredito;
import entidades.Transaccion;
import enumeraciones.TipoDocumento;

@LocalBean
@Stateless
@Remote(ITransaccionesRemote.class)
public class OperacionesCuentaAhorros {

	@PersistenceContext
	private EntityManager em;

	@EJB
	private ProductoEJB producto;

	@EJB
	private ClienteEJB clienteejb;

	@EJB
	NotificacionesEJB notificaciones;

	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void GuardarTransaccion(Transaccion tra) {

		Transaccion bus = buscarTransaccion(tra.getId());
		if (bus == null) {
			em.persist(tra);
		} else {
			throw new ExcepcionNegocio("Ya existe el cliente");
		}
	}

	/**
	 * 
	 * @param num,
	 *            numero de la transaccion a buscar
	 * @return
	 */
	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public Transaccion buscarTransaccion(int num) {
		Transaccion tr = em.find(Transaccion.class, num);
		return tr;
	}

	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public boolean consignacion(CuentaAhorros cuenta, double cantidad, Date fechaTransaccion, String fuenteTr,
			String tipo) {
		double nuevoValor = 0;
		nuevoValor = cuenta.getAmmount() + cantidad;

		CuentaAhorros cu = new CuentaAhorros(cuenta.getNumber(), cuenta.getExpedition_date(), cuenta.getCliente(),
				cuenta.getSavingInterest(), nuevoValor);

		producto.editarCuentaAhorros(cu);

		notificaciones
				.enviarCorreo(
						"ud ha hecho una consignacion bancaria con un saldo de: " + cantidad
								+ "\n Fecha de consignacion : " + fechaTransaccion,
						"cualquiera@hotmail.com", "romanleon2010@hotmail.com", "Consignacion");

		return true;
	}

	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public boolean retiro(CuentaAhorros cuenta, double cantidad, Date fechaTransaccion, String fuenteTr, String tipo) {
		double nuevoValor = 0;
		nuevoValor = cuenta.getAmmount() - cantidad;
		if (nuevoValor <= 0) {
			return false;
		} else {
			Transaccion tr;
			CuentaAhorros cu2 = em.find(CuentaAhorros.class, cuenta.getNumber());
			cu2.setAmmount(nuevoValor);
			producto.editarCuentaAhorros(cu2);
			tr = new Transaccion(0, cantidad, fechaTransaccion, fuenteTr, cuenta, tipo);
			GuardarTransaccion(tr);

			notificaciones.enviarCorreo(
					"ud ha hecho un retiro bancario con un saldo de: " + cantidad + "\n Fecha de consignacion : "
							+ fechaTransaccion,
					"cualquiera@hotmail.com", "romanleon2010@hotmail.com", "Retiro bancario");
			return true;

		}
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

	/**
	 * metodo para transferir saldo de una cuenta a otra
	 * 
	 * @param cuentaOrig
	 *            cuenta deorigen
	 * @param cuentaDest
	 *            cuenta de destino
	 * @param monto
	 *            saldo
	 */
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void transferir(String cuentaActu, String cuentaDest, double monto) {

		CuentaAhorros cuentaActual = producto.buscarCuentaAhorros(cuentaActu);
		CuentaAhorros cuentaDestino = producto.buscarCuentaAhorros(cuentaDest);

		double amm = cuentaActual.getAmmount();

		if (cuentaActual.getCliente().getIdentificationNumber()
				.equals(cuentaDestino.getCliente().getIdentificationNumber())) {
			if ((cuentaActual != null) && (cuentaDestino != null)) {

				if (monto > amm) {
					throw new ExcepcionNegocio(
							"No tiene el monto suficiente en la cuenta de destino para hacer la transferencia");
				} else {

					double saldoOrigen = cuentaActual.getAmmount();
					double resta = saldoOrigen - monto;
					CuentaAhorros cuen = em.find(CuentaAhorros.class, cuentaActu);
					cuen.setAmmount(resta);
					producto.editarCuentaAhorros(cuen);

					double saldoAct = cuentaDestino.getAmmount();
					double saldoFinal = saldoAct + monto;
					CuentaAhorros cuentaDos = em.find(CuentaAhorros.class, cuentaDest);
					cuentaDos.setAmmount(saldoFinal);
					producto.editarCuentaAhorros(cuentaDos);

					Transaccion transaccion = new Transaccion();
					transaccion.setSavingAccountNumber(cuentaActual);
					transaccion.setAmmount(monto);
					transaccion.setId(0);
					transaccion.setSourceTransaction("cajero automatico");
					Date trans = new Date();
					transaccion.setTransactionDate(trans);
					transaccion.setType("Transferencia");
					GuardarTransaccion(transaccion);

					notificaciones
							.enviarCorreo(
									"ud ha hecho una transferencia bancaria con un saldo de: " + monto
											+ "\nCuenta Origen : " + cuentaActu + "\nCuenta Destino: " + cuentaDest
											+ "\n Fecha de consignacion : " + trans,
									"cualquiera@hotmail.com", "romanleon2010@hotmail.com", "Consignacion");
				}
			} else {
				throw new ExcepcionNegocio("verifique que haya escrito bien los numeros de las cuentas");
			}
		} else {
			throw new ExcepcionNegocio("solo se permite hacer transferencias a cuentas del mismo cliente");
		}
	}

	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void transferenciaACH(String numeroCuenta, String cuentaExtranjera, double cantidad) {
		double nuevoValor = 0;
		CuentaAhorros cuenta = em.find(CuentaAhorros.class, numeroCuenta);
		if (cuenta == null) {
			throw new ExcepcionNegocio("NO HAY CUENTA DE AHORRO");
		}
		nuevoValor = cuenta.getAmmount() - cantidad;
		if (nuevoValor <= 0) {
			// NO SE PUEDE PONER MENSAJES EN EL EJB, EN YA QUE VA AL SERVIDOR, I
			// AM IDIOT
			// JOptionPane.showMessageDialog(null,
			// "No es posible la transacción, la cuenta no puede quedar en $0");
			throw new ExcepcionNegocio("No tiene el dinero suficiente en su cuenta para esta transferencia");

		} else {
			Transaccion tr;
			cuenta.setAmmount(nuevoValor);
			producto.editarCuentaAhorros(cuenta);
			tr = new Transaccion(0, cantidad, new Date(), "PAGINA WEB(Tranferencia ACH)", cuenta, "Transferencia ACH");
			GuardarTransaccion(tr);
		}
	}

}
