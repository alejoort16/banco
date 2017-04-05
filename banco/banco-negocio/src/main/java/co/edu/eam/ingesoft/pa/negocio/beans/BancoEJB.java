package co.edu.eam.ingesoft.pa.negocio.beans;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import co.edu.eam.ingesoft.pa.negocio.excepciones.ExcepcionNegocio;
import entidades.Banco;
import entidades.Cliente;
import entidades.ConsumoTarjetaCredito;

@LocalBean
@Stateless
public class BancoEJB {
	
	@PersistenceContext
	private EntityManager em;
	
	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public Banco buscarBanco(String num) {
		Banco consumo = em.find(Banco.class, num);
		return consumo;
	}
	
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void crearBanco(Banco cli) {
			em.persist(cli);
	}

}
