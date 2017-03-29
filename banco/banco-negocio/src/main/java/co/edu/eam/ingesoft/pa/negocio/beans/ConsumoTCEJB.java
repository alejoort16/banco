package co.edu.eam.ingesoft.pa.negocio.beans;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Remote;
import javax.ejb.Stateful;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import co.edu.eam.ingesoft.avanzada.banco.dtos.InicioDTO;
import co.edu.eam.ingesoft.pa.negocio.excepciones.ExcepcionNegocio;
import co.edu.eam.ingesoft.pa.negocio.interfaces.IConsumoTCRemote;
import entidades.ConsumoTarjetaCredito;
import entidades.CuentaAhorros;
import entidades.TarjetaCredito;
import entidades.Transaccion;
import enumeraciones.TipoDocumento;

@LocalBean
@Stateless
@Remote(IConsumoTCRemote.class)
public class ConsumoTCEJB {

	@PersistenceContext
	private EntityManager em;

	@EJB
	ProductoEJB productosejb;

	@EJB
	PagoConsumoEJB pagoConsumoejb;

	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void GuardarConsumo(ConsumoTarjetaCredito consumo) {

		ConsumoTarjetaCredito bus = buscarConsumo(consumo.getId());
		if (bus == null) {
			em.persist(consumo);
		} else {
			throw new ExcepcionNegocio("Ya existe el cliente");
		}
	}

	/**
	 * 
	 * @param num,
	 *            id del consumo a buscar a buscar
	 * @return
	 */
	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public ConsumoTarjetaCredito buscarConsumo(int num) {
		ConsumoTarjetaCredito consumo = em.find(ConsumoTarjetaCredito.class, num);
		return consumo;
	}

	/**
	 * metodo que retorna la sumatoria de los remainin ammount de la tarjeta
	 * 
	 * @param tarjeta
	 *            la terjeta
	 * @return la sumatoria
	 */
	public double sumatoriaRemaininAmmount(String tarjeta) {

		TarjetaCredito tarj = productosejb.buscarTarjeta(tarjeta);
		if (tarj != null) {
			System.out.println("entro");
			Query q = em.createNamedQuery(ConsumoTarjetaCredito.sumaRemainin);
			q.setParameter(1, tarj);
			List<Double> lista = q.getResultList();
			if (lista.get(0) == null) {
				return 0;
			} else {
				System.out.println(lista);
				double val = lista.get(0);
				return val;
			}
		} else {
			throw new ExcepcionNegocio("no existe la tarjeta de credito");
		}

	}

	/**
	 * metodo que carga la lista de tipo DTO con los datos requeridos
	 * 
	 * @param idCliente
	 *            identificacion del cliente
	 * @param tipodoc
	 *            tipo del documento del cliente
	 * @return una lista DTO con los datos requeridos
	 */
	public List<InicioDTO> llenarDTO(String idCliente, TipoDocumento tipodoc) {

		List<TarjetaCredito> tarjetas = pagoConsumoejb.listarTCCliente(idCliente, tipodoc);
		if (tarjetas != null) {
			List<InicioDTO> inicio = new ArrayList<InicioDTO>();
			for (int i = 0; i < tarjetas.size(); i++) {

				String numero = tarjetas.get(i).getNumber();
				String franquicia = tarjetas.get(i).getFranchise().getName();
				double deuda = sumatoriaRemaininAmmount(tarjetas.get(i).getNumber());
				double disponible = tarjetas.get(i).getAmmount();
				InicioDTO objeto = new InicioDTO(numero, franquicia, deuda, disponible);
				inicio.add(objeto);
			}
			return inicio;
		} else {
			throw new ExcepcionNegocio("no hay datos para mostrar");
		}

	}

}
