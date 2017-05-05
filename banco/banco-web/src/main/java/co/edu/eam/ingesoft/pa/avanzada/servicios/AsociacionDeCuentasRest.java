package co.edu.eam.ingesoft.pa.avanzada.servicios;

import java.util.List;

import javax.ejb.EJB;
import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import co.edu.eam.ingesoft.pa.negocio.beans.BancoEJB;
import co.edu.eam.ingesoft.pa.negocio.beans.ClienteEJB;
import co.edu.eam.ingesoft.pa.negocio.beans.CuentaAsociadaEJB;
import entidades.Banco;
import entidades.Cliente;
import entidades.CuentaAsociada;
import enumeraciones.TipoDocumento;

@Path("/asociacion")
public class AsociacionDeCuentasRest {

	@EJB
	CuentaAsociadaEJB cuentaEJB;

	@EJB
	ClienteEJB clinteEJB;

	@EJB
	BancoEJB bancoEJB;

	/**
	 * lista todos los bancos
	 * 
	 * @return la lista de los bancos
	 */
	@GET
	@Path("/listarBancos")
	@Produces(MediaType.APPLICATION_JSON)
	public RespuestaDTO listarBancos() {
		List<Banco> bancos = cuentaEJB.listarBancos();
		return new RespuestaDTO("lista de bancos", 0, bancos);
	}

	/**
	 * lista todas las cuentas asociadas de un cliente
	 * 
	 * @param cedula
	 *            cedula del cliente
	 * @param tipo
	 *            tipo de documento de identificacion del cliente
	 * @return la lista de las cuuentas asociadas del cliente, o si el cliente
	 *         no existe en la bd
	 */
	@GET
	@Path("/cuentasAsociadas")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.APPLICATION_JSON)
	public RespuestaDTO listarCuentasAsociadas(@QueryParam("cedula") String cedula, @QueryParam("tipo") int tipo) {

		TipoDocumento tipod = null;
		if (tipo == 1) {
			tipod = TipoDocumento.CC;
		} else if (tipo == 2) {
			tipod = TipoDocumento.PAS;
		} else if (tipo == 3) {
			tipod = TipoDocumento.TI;
		} else if (tipo == 4) {
			tipod = TipoDocumento.CE;
		}

		Cliente cli = clinteEJB.buscarCliente(tipod, cedula);
		if (cli != null) {
			List<CuentaAsociada> lista = cuentaEJB.listarCuentasAsociadas(cli);
			return new RespuestaDTO("lista de cuentas asociadas", 0, lista);
		} else {
			return new RespuestaDTO("el cliente con esta cedula no existe en la base de datos", 1, false);

		}
	}

}
