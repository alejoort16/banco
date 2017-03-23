package co.edu.eam.ingesoft.pa.negocio.beans;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import co.edu.eam.ingesoft.pa.negocio.excepciones.ExcepcionNegocio;
import co.edu.eam.ingesoft.pa.negocio.interfaces.IClienteRemote;
import entidades.Cliente;
import entidades.ClientePK;
import enumeraciones.TipoDocumento;

@LocalBean
@Stateless
@Remote(IClienteRemote.class)
public class ClienteEJB {

	@PersistenceContext
	private EntityManager em;

	/**
	 * 
	 * @param cli
	 */
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void crearCliente(Cliente cli) {

		Cliente bus = buscarCliente(cli.getIdentificationType(), cli.getIdentificationNumber());
		if (bus == null) {
			em.persist(cli);
		}else{
			throw new ExcepcionNegocio("Ya existe el cliente");
		}

	}

	/**
	 * 
	 * @param documentoId
	 * @return
	 */
	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public Cliente buscarCliente(TipoDocumento tipo, String documentoId) {
		ClientePK pk = new ClientePK(tipo, documentoId);
		Cliente cli = em.find(Cliente.class, pk);
		if(cli != null){
			cli.setProductos(null);
		}
		return cli;
	}
	
	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public List<Cliente> listar(){
		return em.createNamedQuery(Cliente.listar).getResultList();
	}
		
	
}
