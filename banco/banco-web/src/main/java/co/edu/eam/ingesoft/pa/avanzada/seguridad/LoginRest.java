package co.edu.eam.ingesoft.pa.avanzada.seguridad;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import javax.ejb.EJB;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import co.edu.eam.ingesoft.avanzada.banco.dtos.LoginDTO;
import co.edu.eam.ingesoft.avanzada.banco.dtos.LoginRespuestaDTO;
import co.edu.eam.ingesoft.pa.avanzada.servicios.RespuestaDTO;
import co.edu.eam.ingesoft.pa.negocio.beans.SeguridadEJB;
import entidades.Usuario;

@Path("/LoginRest")
public class LoginRest {

	public static Map<String, Usuario> usuarios = new HashMap<String, Usuario>();
	
	@EJB
	SeguridadEJB seguridad;
	
	@POST
	@Path("/login")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public RespuestaDTO loginRest(LoginDTO dto) {

		Usuario usuario = seguridad.buscarUsuario(dto.getUser());
		if ((usuario != null)&& (usuario.getPassword().equals(dto.getPassword()))) {
			String token = UUID.randomUUID().toString();
			usuarios.put(token, usuario);
			return new RespuestaDTO("EXITO", 0, new LoginRespuestaDTO(token, usuario.getCliente().getIdentificationNumber()));
		}else{
			return new RespuestaDTO("Credenciales erroneas", -403, null);
		}

	}
}
