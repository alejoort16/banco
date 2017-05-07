package co.edu.eam.ingesoft.pa.negocio.beans;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.xml.ws.BindingProvider;

import org.hibernate.validator.internal.util.logging.Messages;

import co.edu.eam.ingesoft.pa.negocio.excepciones.ExcepcionNegocio;
import co.edu.eam.pa.bancows.Banco;
import co.edu.eam.pa.bancows.InterbancarioWS;
import co.edu.eam.pa.bancows.InterbancarioWS_Service;
import co.edu.eam.pa.bancows.ListarBancos;
import co.edu.eam.pa.bancows.ListarBancosResponse;
import co.edu.eam.pa.bancows.RespuestaServicio;
import co.edu.eam.pa.bancows.TipoDocumentoEnum;
import co.edu.eam.pa.clientews.Mail;
import co.edu.eam.pa.clientews.Notificaciones;
import co.edu.eam.pa.clientews.NotificacionesService;
import co.edu.eam.pa.clientews.RespuestaNotificacion;
import co.edu.eam.pa.clientews.Sms;
import entidades.CuentaAsociada;
import enumeraciones.TipoDocumento;

@Stateless
public class NotificacionesEJB {

	@EJB
	CuentaAsociadaEJB cuentaAsociada;

	@EJB
	OperacionesCuentaAhorros operaciones;

	@EJB
	BancoEJB bancoEJB;

	@PersistenceContext
	private EntityManager em;

	public void enviarCorreo(String mensaje, String cualquiera, String correoDestinatario, String asunto) {

		NotificacionesService cliente = new NotificacionesService();
		// el que tiene @webservices
		Notificaciones servicio = cliente.getNotificacionesPort();

		String endpointURL = "http://104.197.238.134:8080/notificaciones/notificacionesService";
		BindingProvider bp = (BindingProvider) servicio;
		bp.getRequestContext().put(BindingProvider.ENDPOINT_ADDRESS_PROPERTY, endpointURL);

		Mail correo = new Mail();
		correo.setBody(mensaje);
		correo.setFrom(cualquiera);
		correo.setTo(correoDestinatario);
		correo.setSubject(asunto);

		RespuestaNotificacion resp = servicio.enviarMail(correo);
		System.out.println(resp.getMensaje());

	}

	public void enviarSms(String mensaje, String destinatario) {

		NotificacionesService cliente = new NotificacionesService();
		// el que tiene @webservices
		Notificaciones servicio = cliente.getNotificacionesPort();

		String endpointURL = "http://104.197.238.134:8080/notificaciones/notificacionesService";
		BindingProvider bp = (BindingProvider) servicio;
		bp.getRequestContext().put(BindingProvider.ENDPOINT_ADDRESS_PROPERTY, endpointURL);

		Sms sms = new Sms();
		sms.setTexto(mensaje);
		sms.setTo(destinatario);

		RespuestaNotificacion resp = servicio.enviarSMS(sms);
		System.out.println(resp.getMensaje());
	}

	public List<Banco> listarBancossss() {
		InterbancarioWS_Service banco = new InterbancarioWS_Service();
		InterbancarioWS servicio = banco.getInterbancarioWSPort();

		String endpointURL = "http://104.198.67.149:8080/interbancario/InterbancarioWS/InterbancarioWS";
		BindingProvider bp = (BindingProvider) servicio;
		bp.getRequestContext().put(BindingProvider.ENDPOINT_ADDRESS_PROPERTY, endpointURL);

		List<Banco> bancos;
		bancos = servicio.listarBancos();

		ArrayList<entidades.Banco> ban = new ArrayList<entidades.Banco>();

		for (int i = 0; i < bancos.size(); i++) {
			entidades.Banco bancoo = new entidades.Banco(bancos.get(i).getCodigo(), bancos.get(i).getNombre());
			ban.add(bancoo);
			entidades.Banco bancoBus = bancoEJB.buscarBanco(ban.get(i).getId());
			if(bancos.isEmpty()){
				throw new ExcepcionNegocio("no hay bancos en la lista");
			}else{
				if(bancoBus == null){
					bancoEJB.crearBanco(bancoo);
				}
			}		
		}

		return bancos;
	}

	public String verificarCuenta(String idbanco, String tipodoc, String numerodoc, String nombre,
			String numerocuenta) {

		TipoDocumentoEnum tipo = null;

		if (tipodoc.equals("CC")) {
			tipo = TipoDocumentoEnum.CC;
		} else if (tipodoc.equals("CE")) {
			tipo = TipoDocumentoEnum.CE;
		} else if (tipodoc.equals("PAS")) {
			tipo = TipoDocumentoEnum.PAS;
		} else if (tipodoc.equals("TI")) {
			tipo = TipoDocumentoEnum.TI;
		}

		InterbancarioWS_Service banco = new InterbancarioWS_Service();
		InterbancarioWS servicio = banco.getInterbancarioWSPort();

		String endpointURL = "http:// 104.198.67.149:8080/interbancario/InterbancarioWS/InterbancarioWS";
		BindingProvider bp = (BindingProvider) servicio;
		bp.getRequestContext().put(BindingProvider.ENDPOINT_ADDRESS_PROPERTY, endpointURL);

		RespuestaServicio respuesta = servicio.registrarCuentaAsociada(idbanco, tipo, numerodoc, nombre, numerocuenta);

		System.out.println(respuesta.getCodigo() + "**************************");
		if (respuesta.getCodigo().equals("0000")) {

			CuentaAsociada cuen = cuentaAsociada.buscarCuentaAsociada(numerocuenta);
			if (cuen != null) {
				cuen.setEstado(respuesta.getMensaje());
				em.merge(cuen);
				return "Cuenta asociada";
			} else {
				throw new ExcepcionNegocio("no existe la cuenta asociada");
			}

		} else if (respuesta.getCodigo().equals("0001")) {
			CuentaAsociada cuen = cuentaAsociada.buscarCuentaAsociada(numerocuenta);
			if (cuen != null) {
				cuen.setEstado(respuesta.getMensaje());
				em.merge(cuen);
				return "Esta cuenta esta pendiente para ser verificada";
			} else {
				throw new ExcepcionNegocio("no existe la cuenta asociada");
			}
		} else if (respuesta.getCodigo().equals("0003")) {

			CuentaAsociada cuen = cuentaAsociada.buscarCuentaAsociada(numerocuenta);
			if (cuen != null) {
				cuen.setEstado(respuesta.getMensaje());
				cuentaAsociada.eliminarCuentaAsociada(cuen);
				return "Esta cuenta no es valida, por lo tanto se eliminara";

			} else {
				throw new ExcepcionNegocio("Esta cuenta no existe");
			}

		} else if (respuesta.getCodigo().equals("0010")) {
			CuentaAsociada cuen = cuentaAsociada.buscarCuentaAsociada(numerocuenta);
			if (cuen != null) {
				cuentaAsociada.eliminarCuentaAsociada(cuen);
				return "El banco de esta cuenta no existe, por lo tanto se eliminara";
			} else {
				throw new ExcepcionNegocio("Esta cuenta no existe");
			}
		} else {
			throw new ExcepcionNegocio("codigo no valido");
		}
	}

	public String transferirMonto(String cuentaOrigen, String idbanco, String numeroCuenta, double monto) {
		InterbancarioWS_Service banco = new InterbancarioWS_Service();
		InterbancarioWS servicio = banco.getInterbancarioWSPort();

		String endpointURL = "http:// 104.198.67.149:8080/interbancario/InterbancarioWS/InterbancarioWS";
		BindingProvider bp = (BindingProvider) servicio;
		bp.getRequestContext().put(BindingProvider.ENDPOINT_ADDRESS_PROPERTY, endpointURL);

		RespuestaServicio respuesta = servicio.transferirMonto(idbanco, numeroCuenta, monto);

		if (respuesta.getCodigo().equals("0000")) {
			operaciones.transferenciaACH(cuentaOrigen, monto);
			return respuesta.getMensaje();

		} else if (respuesta.getCodigo().equals("0002")) {
			return respuesta.getMensaje();

		} else if (respuesta.getCodigo().equals("0004")) {
			return respuesta.getMensaje();
		} else {
			return respuesta.getMensaje();
		}

	}
}
