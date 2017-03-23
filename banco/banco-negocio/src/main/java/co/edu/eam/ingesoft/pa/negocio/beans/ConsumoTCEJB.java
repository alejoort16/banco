package co.edu.eam.ingesoft.pa.negocio.beans;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Remote;
import javax.ejb.Stateful;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import co.edu.eam.ingesoft.pa.negocio.excepciones.ExcepcionNegocio;
import co.edu.eam.ingesoft.pa.negocio.interfaces.IConsumoTCRemote;
import entidades.ConsumoTarjetaCredito;
import entidades.CuentaAhorros;
import entidades.Transaccion;

@LocalBean
@Stateless
@Remote(IConsumoTCRemote.class)
public class ConsumoTCEJB {

	@PersistenceContext
	private EntityManager em;

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


}
