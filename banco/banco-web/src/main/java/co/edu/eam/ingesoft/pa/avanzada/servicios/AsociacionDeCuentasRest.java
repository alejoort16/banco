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

	/**
	 * asocia una cuenta a la base de datos
	 * @param numeroCuenta
	 * @param nombreAsoc
	 * @param nombreTit
	 * @param tipoDoc
	 * @param cedula
	 * @param estado
	 * @param codigoBanco
	 * @param cedCliente
	 * @param tipo
	 * @return mensaje indicando lo ocurrido
	 */
	@POST
	@Path("/asociarCuenta")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	public RespuestaDTO asociarCuenta(@FormParam("numeroCuenta") String numeroCuenta,
			@FormParam("nombreAsociacion") String nombreAsoc, @FormParam("nombreTitular") String nombreTit,
			@FormParam("tipo") int tipoDoc, @FormParam("identificacionTitular") String cedula,
			@FormParam("estado") String estado, @FormParam("banco") String codigoBanco,
			@FormParam("cedCliente") String cedCliente, @FormParam("tipoDocCliente") int tipo) {

		TipoDocumento tipoDocTitular = null;
		if (tipoDoc == 1) {
			tipoDocTitular = TipoDocumento.CC;
		} else if (tipoDoc == 2) {
			tipoDocTitular = TipoDocumento.PAS;
		} else if (tipoDoc == 3) {
			tipoDocTitular = TipoDocumento.TI;
		} else if (tipoDoc == 4) {
			tipoDocTitular = TipoDocumento.CE;
		}

		TipoDocumento tipoDocCliente = null;
		if (tipo == 1) {
			tipoDocCliente = TipoDocumento.CC;
		} else if (tipo == 2) {
			tipoDocCliente = TipoDocumento.PAS;
		} else if (tipo == 3) {
			tipoDocCliente = TipoDocumento.TI;
		} else if (tipo == 4) {
			tipoDocCliente = TipoDocumento.CE;
		}

		Banco banco = bancoEJB.buscarBanco(codigoBanco);
		Cliente cli = clinteEJB.buscarCliente(tipoDocCliente, cedCliente);
		if (banco != null) {
			if (cli != null) {
				CuentaAsociada cuenta = new CuentaAsociada(numeroCuenta, nombreAsoc, nombreTit, tipoDocTitular, cedula,
						estado, banco, cli);
				cuentaEJB.crearAsociacion(cuenta);
				return new RespuestaDTO("cuenta asociada y pendiente para ser verificada", 0, true);
			} else {
				return new RespuestaDTO("el cliente no se encontro", 1, null);
			}
		} else {
			return new RespuestaDTO("no se encontro el banco", 1, null);

		}
	}
}
