package co.edu.eam.ingesoft.pa.negocio.interfaces;

import java.util.List;

import entidades.Cliente;
import entidades.Franquicia;
import enumeraciones.TipoDocumento;

/**
 * interface remota para acceder a las operaciones del ejb
 * @author Alejandro Ortega
 *
 */
public interface IClienteRemote {
	
	public void crearCliente(Cliente cli);
	public Cliente buscarCliente(TipoDocumento tipo, String documentoId);
	public List<Cliente> listar(); // falta en el controlador
	
	
}
