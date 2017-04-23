package co.edu.eam.ingesoft.pa.avanzada.servicios;

import javax.ejb.EJB;
import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import co.edu.eam.ingesoft.pa.negocio.beans.CuentaAsociadaEJB;
import co.edu.eam.ingesoft.pa.negocio.beans.OperacionesCuentaAhorros;
import co.edu.eam.ingesoft.pa.negocio.beans.ProductoEJB;
import entidades.CuentaAhorros;
import entidades.CuentaAsociada;
import enumeraciones.TipoDocumento;

//para invocar un servicio se necesita:
/*
 * 1. la url del servicio: http://ip:puerto/root/raizRest/pathClase/pathmetodo
 */
//pathclase = VerificarCuenta
@Path("/interbancario")
public class VerificarCuentaRest {

	@EJB
	ProductoEJB prosductosEJB;

	
	/**
	 * verifica si una cuenta de ahorros existe
	 * @param numeroCuenta numero de la cuenta de ahorros
	 * @param tipo tipo de documento del cliente
	 * @param numeroDocum numero de documento del cliente
	 * @return si la cuenta es existente, si es invalida o si no existe la cuenta
	 */
	@POST
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/verificarcuenta")
	public RespuestaDTO verificarCuenta(@FormParam("numeroCuenta") String numeroCuenta, @FormParam("tipo") int tipo,
			@FormParam("cedula") String numeroDocum) {

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

		CuentaAhorros cuenta = prosductosEJB.buscarCuentaAhorros(numeroCuenta);
		if (cuenta != null) {
			if ((cuenta.getCliente().getIdentificationType().equals(tipod))
					&& (cuenta.getCliente().getIdentificationNumber().equals(numeroDocum))) {
				return new RespuestaDTO("esta cuenta se ha verificado correctamente", 0, true);
			} else {
				return new RespuestaDTO("los datos de esta cuenta son invalidos", 1, false);
			}
		} else {
			return new RespuestaDTO("cuenta in existente", 1, false);
		}
	}

}
