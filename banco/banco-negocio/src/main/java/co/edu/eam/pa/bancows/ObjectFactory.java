
package co.edu.eam.pa.bancows;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the co.edu.eam.pa.bancows package. 
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

    private final static QName _TransferirMonto_QNAME = new QName("http://www.eam.edu.co/interbancario", "transferirMonto");
    private final static QName _ListarBancos_QNAME = new QName("http://www.eam.edu.co/interbancario", "listarBancos");
    private final static QName _RegistrarCuentaAsociadaResponse_QNAME = new QName("http://www.eam.edu.co/interbancario", "registrarCuentaAsociadaResponse");
    private final static QName _TransferirMontoResponse_QNAME = new QName("http://www.eam.edu.co/interbancario", "transferirMontoResponse");
    private final static QName _ListarBancosResponse_QNAME = new QName("http://www.eam.edu.co/interbancario", "listarBancosResponse");
    private final static QName _RegistrarCuentaAsociada_QNAME = new QName("http://www.eam.edu.co/interbancario", "registrarCuentaAsociada");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: co.edu.eam.pa.bancows
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link RegistrarCuentaAsociadaResponse }
     * 
     */
    public RegistrarCuentaAsociadaResponse createRegistrarCuentaAsociadaResponse() {
        return new RegistrarCuentaAsociadaResponse();
    }

    /**
     * Create an instance of {@link TransferirMontoResponse }
     * 
     */
    public TransferirMontoResponse createTransferirMontoResponse() {
        return new TransferirMontoResponse();
    }

    /**
     * Create an instance of {@link TransferirMonto }
     * 
     */
    public TransferirMonto createTransferirMonto() {
        return new TransferirMonto();
    }

    /**
     * Create an instance of {@link ListarBancos }
     * 
     */
    public ListarBancos createListarBancos() {
        return new ListarBancos();
    }

    /**
     * Create an instance of {@link ListarBancosResponse }
     * 
     */
    public ListarBancosResponse createListarBancosResponse() {
        return new ListarBancosResponse();
    }

    /**
     * Create an instance of {@link RegistrarCuentaAsociada }
     * 
     */
    public RegistrarCuentaAsociada createRegistrarCuentaAsociada() {
        return new RegistrarCuentaAsociada();
    }

    /**
     * Create an instance of {@link RespuestaServicio }
     * 
     */
    public RespuestaServicio createRespuestaServicio() {
        return new RespuestaServicio();
    }

    /**
     * Create an instance of {@link Banco }
     * 
     */
    public Banco createBanco() {
        return new Banco();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link TransferirMonto }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.eam.edu.co/interbancario", name = "transferirMonto")
    public JAXBElement<TransferirMonto> createTransferirMonto(TransferirMonto value) {
        return new JAXBElement<TransferirMonto>(_TransferirMonto_QNAME, TransferirMonto.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ListarBancos }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.eam.edu.co/interbancario", name = "listarBancos")
    public JAXBElement<ListarBancos> createListarBancos(ListarBancos value) {
        return new JAXBElement<ListarBancos>(_ListarBancos_QNAME, ListarBancos.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link RegistrarCuentaAsociadaResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.eam.edu.co/interbancario", name = "registrarCuentaAsociadaResponse")
    public JAXBElement<RegistrarCuentaAsociadaResponse> createRegistrarCuentaAsociadaResponse(RegistrarCuentaAsociadaResponse value) {
        return new JAXBElement<RegistrarCuentaAsociadaResponse>(_RegistrarCuentaAsociadaResponse_QNAME, RegistrarCuentaAsociadaResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link TransferirMontoResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.eam.edu.co/interbancario", name = "transferirMontoResponse")
    public JAXBElement<TransferirMontoResponse> createTransferirMontoResponse(TransferirMontoResponse value) {
        return new JAXBElement<TransferirMontoResponse>(_TransferirMontoResponse_QNAME, TransferirMontoResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ListarBancosResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.eam.edu.co/interbancario", name = "listarBancosResponse")
    public JAXBElement<ListarBancosResponse> createListarBancosResponse(ListarBancosResponse value) {
        return new JAXBElement<ListarBancosResponse>(_ListarBancosResponse_QNAME, ListarBancosResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link RegistrarCuentaAsociada }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.eam.edu.co/interbancario", name = "registrarCuentaAsociada")
    public JAXBElement<RegistrarCuentaAsociada> createRegistrarCuentaAsociada(RegistrarCuentaAsociada value) {
        return new JAXBElement<RegistrarCuentaAsociada>(_RegistrarCuentaAsociada_QNAME, RegistrarCuentaAsociada.class, null, value);
    }

}
