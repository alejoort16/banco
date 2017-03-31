
package co.edu.eam.pa.clientews;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the co.edu.eam.pa.clientews package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _EnviarSMS_QNAME = new QName("http://www.eam.edu.co/notificaciones", "enviarSMS");
    private final static QName _EnviarSMSResponse_QNAME = new QName("http://www.eam.edu.co/notificaciones", "enviarSMSResponse");
    private final static QName _EnviarMail_QNAME = new QName("http://www.eam.edu.co/notificaciones", "enviarMail");
    private final static QName _EnviarMailResponse_QNAME = new QName("http://www.eam.edu.co/notificaciones", "enviarMailResponse");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: co.edu.eam.pa.clientews
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link EnviarSMSResponse }
     * 
     */
    public EnviarSMSResponse createEnviarSMSResponse() {
        return new EnviarSMSResponse();
    }

    /**
     * Create an instance of {@link EnviarSMS }
     * 
     */
    public EnviarSMS createEnviarSMS() {
        return new EnviarSMS();
    }

    /**
     * Create an instance of {@link EnviarMailResponse }
     * 
     */
    public EnviarMailResponse createEnviarMailResponse() {
        return new EnviarMailResponse();
    }

    /**
     * Create an instance of {@link EnviarMail }
     * 
     */
    public EnviarMail createEnviarMail() {
        return new EnviarMail();
    }

    /**
     * Create an instance of {@link Mail }
     * 
     */
    public Mail createMail() {
        return new Mail();
    }

    /**
     * Create an instance of {@link Sms }
     * 
     */
    public Sms createSms() {
        return new Sms();
    }

    /**
     * Create an instance of {@link RespuestaNotificacion }
     * 
     */
    public RespuestaNotificacion createRespuestaNotificacion() {
        return new RespuestaNotificacion();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link EnviarSMS }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.eam.edu.co/notificaciones", name = "enviarSMS")
    public JAXBElement<EnviarSMS> createEnviarSMS(EnviarSMS value) {
        return new JAXBElement<EnviarSMS>(_EnviarSMS_QNAME, EnviarSMS.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link EnviarSMSResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.eam.edu.co/notificaciones", name = "enviarSMSResponse")
    public JAXBElement<EnviarSMSResponse> createEnviarSMSResponse(EnviarSMSResponse value) {
        return new JAXBElement<EnviarSMSResponse>(_EnviarSMSResponse_QNAME, EnviarSMSResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link EnviarMail }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.eam.edu.co/notificaciones", name = "enviarMail")
    public JAXBElement<EnviarMail> createEnviarMail(EnviarMail value) {
        return new JAXBElement<EnviarMail>(_EnviarMail_QNAME, EnviarMail.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link EnviarMailResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.eam.edu.co/notificaciones", name = "enviarMailResponse")
    public JAXBElement<EnviarMailResponse> createEnviarMailResponse(EnviarMailResponse value) {
        return new JAXBElement<EnviarMailResponse>(_EnviarMailResponse_QNAME, EnviarMailResponse.class, null, value);
    }

}
