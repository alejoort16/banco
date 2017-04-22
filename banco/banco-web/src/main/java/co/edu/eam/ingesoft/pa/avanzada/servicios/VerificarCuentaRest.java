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
	CuentaAsociadaEJB cuentaEJB;

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

		CuentaAsociada cuenta = cuentaEJB.buscarCuentaAsociada(numeroCuenta);
		if (cuenta != null) {
			if ((cuenta.getTipoDocumento().equals(tipod)) && (cuenta.getNumeroDocumento().equals(numeroDocum))) {
				return new RespuestaDTO("esta cuenta se ha verificado correctamente", 0, true);
			} else {
				return new RespuestaDTO("los datos de esta cuenta son invalidos", 1, false);
			}
		} else {
			return new RespuestaDTO("cuenta in existente", 1, false);
		}
	}

	

}
