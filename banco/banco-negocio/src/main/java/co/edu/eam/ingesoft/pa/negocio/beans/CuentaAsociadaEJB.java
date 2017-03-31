/**
 * 
 */
package co.edu.eam.ingesoft.pa.negocio.beans;

import java.util.ArrayList;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import co.edu.eam.ingesoft.pa.negocio.excepciones.ExcepcionNegocio;
import entidades.Banco;
import entidades.Cliente;
import entidades.ClientePK;
import entidades.ConsumoTarjetaCredito;
import entidades.CuentaAsociada;
import entidades.TarjetaCredito;
import enumeraciones.TipoDocumento;

/**
 * @author Alejandro Ortega
 *
 */
@LocalBean
@Stateless
public class CuentaAsociadaEJB {

	@PersistenceContext
	EntityManager em;
	
	@EJB
	NotificacionesEJB notificaciones;
	
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void crearAsociacion(CuentaAsociada cuenta) {

		CuentaAsociada bus = buscarCuentaAsociada(cuenta.getNumeroCuenta());
		if (bus == null) {
			em.persist(cuenta);
			notificaciones.enviarCorreo("A su perfil en el banco Concordia se le asocio la cuenta "+cuenta.getNumeroCuenta()+" del"
					+ " banco "+cuenta.getBanco().getName()+" "
					+ "pertenecienta a "+cuenta.getNombreTitular(), "infobanco@hotmail.com", 
					cuenta.getClientePrincipal().getCorreo(), "ASOCIACION DE CUENTA EXTRANJERA");
			
			//notificaciones.enviarSms("A su perfil en el banco Concordia se le asocio la cuenta "+cuenta.getNumeroCuenta()+" del"
				//	+ " banco "+cuenta.getBanco().getName()+" "
					//+ "pertenecienta a "+cuenta.getNombreTitular(), cuenta.getClientePrincipal().getCelular());
			
		}else{
			throw new ExcepcionNegocio("Este numero de cuenta ya esta asociado");
		}

	}
	
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void eliminarCuentaAsociada(CuentaAsociada cuenta) {
						cuenta = buscarCuentaAsociada(cuenta.getNumeroCuenta());
		//if (bus != null) {
			em.remove(cuenta);
		//}else{
			//throw new ExcepcionNegocio("Este numero de cuenta no existe");
		//}

	}
	
	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public CuentaAsociada buscarCuentaAsociada(String numeroCuenta) {
		CuentaAsociada cu = em.find(CuentaAsociada.class, numeroCuenta);
		return cu;
	}
	
	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public ArrayList<Banco> listarBancos() {
		ArrayList<Banco> list;
		list = (ArrayList<Banco>) em.createNamedQuery(Banco.findAll).getResultList();
		return list;
	}
	
	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public ArrayList<CuentaAsociada> listarCuentasAsociadas(Cliente cli) {
		ArrayList<CuentaAsociada> list;
		list = (ArrayList<CuentaAsociada>) em.createNamedQuery(CuentaAsociada.findbyCliente)
				.setParameter(1, cli).getResultList();
		return list;
	}

}
