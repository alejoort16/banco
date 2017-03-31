package co.edu.eam.ingesoft.pa.negocio.beans;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.xml.ws.BindingProvider;

import co.edu.eam.pa.clientews.Mail;
import co.edu.eam.pa.clientews.Notificaciones;
import co.edu.eam.pa.clientews.NotificacionesService;
import co.edu.eam.pa.clientews.RespuestaNotificacion;
import co.edu.eam.pa.clientews.Sms;

@Stateless
public class NotificacionesEJB {
	
	public void enviarCorreo(String mensaje,String cualquiera,String correoDestinatario,String asunto){
		
		NotificacionesService cliente = new NotificacionesService();
		//el que tiene @webservices
		Notificaciones servicio = cliente.getNotificacionesPort();
		
		String endpointURL = "http://104.197.238.134:8080/notificaciones/notificacionesService";
		BindingProvider bp = (BindingProvider)servicio;
		bp.getRequestContext().put(BindingProvider.ENDPOINT_ADDRESS_PROPERTY, endpointURL);
		
		Mail correo = new Mail();
		correo.setBody(mensaje);
		correo.setFrom(cualquiera);
		correo.setTo(correoDestinatario);
		correo.setSubject(asunto);
		
		RespuestaNotificacion resp = servicio.enviarMail(correo);
		System.out.println(resp.getMensaje());
		
	}
	
	public void enviarSms(String mensaje, String destinatario){
		

		NotificacionesService cliente = new NotificacionesService();
		//el que tiene @webservices
		Notificaciones servicio = cliente.getNotificacionesPort();
		
		String endpointURL = "http://104.197.238.134:8080/notificaciones/notificacionesService";
		BindingProvider bp = (BindingProvider)servicio;
		bp.getRequestContext().put(BindingProvider.ENDPOINT_ADDRESS_PROPERTY, endpointURL);
		
		Sms sms = new Sms();
		sms.setTexto(mensaje);
		sms.setTo(destinatario);
		
		RespuestaNotificacion resp = servicio.enviarSMS(sms);
		System.out.println(resp.getMensaje());
	}

}
