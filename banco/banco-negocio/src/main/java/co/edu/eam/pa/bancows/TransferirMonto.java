
package co.edu.eam.pa.bancows;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para transferirMonto complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="transferirMonto">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="idbanco" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="numerocuenta" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="monto" type="{http://www.w3.org/2001/XMLSchema}double"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "transferirMonto", propOrder = {
    "idbanco",
    "numerocuenta",
    "monto"
})
public class TransferirMonto {

    protected String idbanco;
    protected String numerocuenta;
    protected double monto;

    /**
     * Obtiene el valor de la propiedad idbanco.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIdbanco() {
        return idbanco;
    }

    /**
     * Define el valor de la propiedad idbanco.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIdbanco(String value) {
        this.idbanco = value;
    }

    /**
     * Obtiene el valor de la propiedad numerocuenta.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNumerocuenta() {
        return numerocuenta;
    }

    /**
     * Define el valor de la propiedad numerocuenta.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNumerocuenta(String value) {
        this.numerocuenta = value;
    }

    /**
     * Obtiene el valor de la propiedad monto.
     * 
     */
    public double getMonto() {
        return monto;
    }

    /**
     * Define el valor de la propiedad monto.
     * 
     */
    public void setMonto(double value) {
        this.monto = value;
    }

}
