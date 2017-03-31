
package co.edu.eam.pa.clientews;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para enviarSMS complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="enviarSMS">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="sms" type="{http://www.eam.edu.co/notificaciones}sms" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "enviarSMS", propOrder = {
    "sms"
})
public class EnviarSMS {

    protected Sms sms;

    /**
     * Obtiene el valor de la propiedad sms.
     * 
     * @return
     *     possible object is
     *     {@link Sms }
     *     
     */
    public Sms getSms() {
        return sms;
    }

    /**
     * Define el valor de la propiedad sms.
     * 
     * @param value
     *     allowed object is
     *     {@link Sms }
     *     
     */
    public void setSms(Sms value) {
        this.sms = value;
    }

}
