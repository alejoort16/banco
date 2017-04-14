package co.edu.eam.ingesoft.pa.negocio.beans;

import javax.ejb.LocalBean;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import co.edu.eam.ingesoft.pa.negocio.excepciones.ExcepcionNegocio;
import co.edu.eam.ingesoft.pa.negocio.interfaces.IClienteRemote;
import entidades.ConsumoTarjetaCredito;
import entidades.SegundaClave;

@LocalBean
@Stateless
public class CodigosEJB {
	
	@PersistenceContext
	private EntityManager em;
	
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void GuardarCodigo(SegundaClave codigo) {
			em.persist(codigo);
	}
	
	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public SegundaClave buscarCodigo(String num) {
		SegundaClave codigo = em.find(SegundaClave.class, num);
		return codigo;
	}

}
