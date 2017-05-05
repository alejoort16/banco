package co.edu.eam.ingesoft.pa.avanzada.servicios;

import javax.ejb.EJB;
import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import co.edu.eam.ingesoft.avanzada.banco.dtos.AsociarCuentaDTO;
import co.edu.eam.ingesoft.avanzada.banco.dtos.TransferirDTO;
import co.edu.eam.ingesoft.pa.negocio.beans.BancoEJB;
import co.edu.eam.ingesoft.pa.negocio.beans.ClienteEJB;
import co.edu.eam.ingesoft.pa.negocio.beans.CuentaAsociadaEJB;
import co.edu.eam.ingesoft.pa.negocio.beans.OperacionesCuentaAhorros;
import co.edu.eam.ingesoft.pa.negocio.beans.ProductoEJB;
import entidades.Banco;
import entidades.Cliente;
import entidades.CuentaAhorros;
import entidades.CuentaAsociada;
import enumeraciones.TipoDocumento;

@Path("/restMoviles")
public class ServiciosRestMoviles {

	@EJB
	CuentaAsociadaEJB cuentaEJB;

	@EJB
	ClienteEJB clinteEJB;

	@EJB
	BancoEJB bancoEJB;
	
	@EJB
	private OperacionesCuentaAhorros cuentaejb;
	
	@EJB
	private ProductoEJB productosejb;
	
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
	@Consumes(MediaType.APPLICATION_JSON)
	public RespuestaDTO asociarCuenta(AsociarCuentaDTO dto) {
		
		TipoDocumento tipoDocTitular = null;
		if (dto.getTipoDoc() == 1) {
			tipoDocTitular = TipoDocumento.CC;
		} else if (dto.getTipoDoc() == 2) {
			tipoDocTitular = TipoDocumento.PAS;
		} else if (dto.getTipoDoc() == 3) {
			tipoDocTitular = TipoDocumento.TI;
		} else if (dto.getTipoDoc() == 4) {
			tipoDocTitular = TipoDocumento.CE;
		}

		TipoDocumento tipoDocCliente = null;
		if (dto.getTipo() == 1) {
			tipoDocCliente = TipoDocumento.CC;
		} else if (dto.getTipo() == 2) {
			tipoDocCliente = TipoDocumento.PAS;
		} else if (dto.getTipo() == 3) {
			tipoDocCliente = TipoDocumento.TI;
		} else if (dto.getTipo() == 4) {
			tipoDocCliente = TipoDocumento.CE;
		}

		Banco banco = bancoEJB.buscarBanco(dto.getCodigoBanco());
		Cliente cli = clinteEJB.buscarCliente(tipoDocCliente, dto.getCedCliente());
		if (banco != null) {
			if (cli != null) {
				CuentaAsociada cuenta = new CuentaAsociada(dto.getNumeroCuenta(), dto.getNombreAsoc(), dto.getNombreTit(), tipoDocTitular, dto.getCedCliente(),
						dto.getEstado(), banco, cli);
				cuentaEJB.crearAsociacion(cuenta);
				return new RespuestaDTO("cuenta asociada y pendiente para ser verificada", 0, true);
			} else {
				return new RespuestaDTO("el cliente no se encontro", 1, null);
			}
		} else {
			return new RespuestaDTO("no se encontro el banco", 1, null);

		}
	}
	
	
	@POST
	@Path("/transferir")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public RespuestaDTO transferir(TransferirDTO dto) {
		CuentaAhorros cuenta = productosejb.buscarCuentaAhorros(dto.getNumeroCuenta());
		if(cuenta!=null){
			cuentaejb.transferenciaACH(dto.getNumeroCuenta(), dto.getCantidad());
			return new RespuestaDTO("transferencia realizada con exito", 0, null);
		}else{
			return new RespuestaDTO("error al realizar el retiro de su cuenta", 1, null);
		}
	
	
}
	
}
