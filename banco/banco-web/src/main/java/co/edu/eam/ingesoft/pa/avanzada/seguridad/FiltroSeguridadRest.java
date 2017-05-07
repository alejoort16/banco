package co.edu.eam.ingesoft.pa.avanzada.seguridad;

import java.io.IOException;

import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.Provider;

import co.edu.eam.ingesoft.pa.avanzada.servicios.RespuestaDTO;

@Secured
@Provider
public class FiltroSeguridadRest {

	/**
	 * Metodo que hace filtro para los servicios por
	 * medio de el token saca el user y revisa si tiene algun acceso al servicio
	 * @param ctxReq
	 * @throws IOException
	 */
	public void filter(ContainerRequestContext ctxReq) throws IOException {
		String token = ctxReq.getHeaderString("Authorization");
		System.out.println(token + "hh");
		
		// System.out.println(metod);
		// TODO: revisar el usuario del token y el permiso de acceso al servicio
		if (!LoginRest.usuarios.containsKey(token)) {
			RespuestaDTO dto = new RespuestaDTO("No autorizado", -403, null);
			Response res = Response.status(403).entity(dto).build();
			ctxReq.abortWith(res);
		}

	}
}
