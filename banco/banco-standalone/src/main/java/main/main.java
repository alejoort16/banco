/**
 * 
 */
package main;

import javax.naming.NamingException;

import co.edu.eam.ingesoft.pa.negocio.interfaces.IClienteRemote;
import entidades.Cliente;
import enumeraciones.TipoDocumento;
import util.ServiceLocator;

/**
 * @author Alejandro Ortega
 *
 */
public class main {

	/**
	 * @param args
	 */
	public static void main(String[] args) throws NamingException {
		// TODO Auto-generated method stub
		IClienteRemote cliRe = (IClienteRemote) ServiceLocator.buscarEJB("ClienteEJB",
				IClienteRemote.class.getCanonicalName());
		
		Cliente cli = new Cliente();
		cli.setLastName("ortega");
		cli.setLastName("alejo");
		cli.setIdentificationNumber("1094964008");
		cli.setIdentificationType(TipoDocumento.CC);
		
		cliRe.crearCliente(cli);

	}

}
