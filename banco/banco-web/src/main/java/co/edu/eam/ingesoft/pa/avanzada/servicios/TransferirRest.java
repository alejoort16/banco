package co.edu.eam.ingesoft.pa.avanzada.servicios;

import java.util.Date;
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

import co.edu.eam.ingesoft.pa.negocio.beans.ClienteEJB;
import co.edu.eam.ingesoft.pa.negocio.beans.CuentaAsociadaEJB;
import co.edu.eam.ingesoft.pa.negocio.beans.OperacionesCuentaAhorros;
import co.edu.eam.ingesoft.pa.negocio.beans.ProductoEJB;
import entidades.Cliente;
import entidades.CuentaAhorros;
import entidades.CuentaAsociada;
import enumeraciones.TipoDocumento;

@Path("/transferencia")
public class TransferirRest {

	@EJB
	private OperacionesCuentaAhorros cuentaejb;

	@EJB
	private ClienteEJB clienteejb;

	@EJB
	private CuentaAsociadaEJB asociadasejb;
	
	@EJB
	private ProductoEJB productosejb;

	@GET
	@Path("/listarcuentaahorros")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.APPLICATION_JSON)
	public RespuestaDTO listarCuentasCliente(@QueryParam("tipo") int tipo, @QueryParam("cedula") String numeroDocum) {

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

		List<CuentaAhorros> lista = cuentaejb.listarCuentaAhorros(tipod, numeroDocum);

		if (lista.isEmpty()) {
			return new RespuestaDTO("No tiene cuentas", 1, null);
		} else {
			return new RespuestaDTO("Se listaron cuentas ", 0, lista);
		}

	}

	@GET
	@Path("/listarcuentasverificadas")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.APPLICATION_JSON)
	public RespuestaDTO listarCuentasAsociadasVerificadas(@QueryParam("tipo") int tipo,
			@QueryParam("cedula") String numeroDocum) {
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
		Cliente cli = clienteejb.buscarCliente(tipod, numeroDocum);

		List<CuentaAsociada> cuentas = asociadasejb.listarCuentasAsociadasVerificadas(cli);
		
		if(cuentas.isEmpty()){
			return new RespuestaDTO("No hay cuentas asociadas verificadas", 1, null);
		}else{
			return new RespuestaDTO("Cuentas verficadas listadas", 0, cuentas);
		}

	}
	

	@GET
	@Path("/generarsegundaclave")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.APPLICATION_JSON)
	public RespuestaDTO generarYenviarCodigo(@QueryParam("tipo") int tipo,
		@QueryParam("cedula") String numeroDocum) {
		
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

	Cliente cli = clienteejb.buscarCliente(tipod, numeroDocum);
	
	if(cli==null){
		return new RespuestaDTO("Este cliente no existe", 1, null);
	}else{
		cuentaejb.generarSegundaClave(cli.getCorreo(),cli.getCelular());
		return new RespuestaDTO("Codigo generado y enviado", 0, null);
	}	
	}
	
	@POST
	@Path("/recibirdinero")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.APPLICATION_JSON)
	public String recibirDinero(@FormParam("numerocuenta") String numeroCuenta,
			@FormParam("cantidad") double cantidad) {
		CuentaAhorros cuenta = productosejb.buscarCuentaAhorros(numeroCuenta);
		if(cuenta!=null){
			cuentaejb.consignacion(cuenta, cantidad, new Date(), "Transacción desde otro banco", "Consignación");
			return "EXITO";
		}else{
			return "ERROR";
		}
	}
	
	
	
	}


