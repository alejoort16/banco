
package co.edu.eam.pa.bancows;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para registrarCuentaAsociadaResponse complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="registrarCuentaAsociadaResponse">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="respuestaServicio" type="{http://www.eam.edu.co/interbancario}respuestaServicio" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "registrarCuentaAsociadaResponse", propOrder = {
    "respuestaServicio"
})
public class RegistrarCuentaAsociadaResponse {

    protected RespuestaServicio respuestaServicio;

    /**
     * Obtiene el valor de la propiedad respuestaServicio.
     * 
     * @return
     *     possible object is
     *     {@link RespuestaServicio }
     *     
     */
    public RespuestaServicio getRespuestaServicio() {
        return respuestaServicio;
    }

    /**
     * Define el valor de la propiedad respuestaServicio.
     * 
     * @param value
     *     allowed object is
     *     {@link RespuestaServicio }
     *     
     */
    public void setRespuestaServicio(RespuestaServicio value) {
        this.respuestaServicio = value;
    }

}
