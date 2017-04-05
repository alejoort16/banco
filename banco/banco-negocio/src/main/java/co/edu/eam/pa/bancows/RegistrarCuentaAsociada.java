
package co.edu.eam.pa.bancows;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para registrarCuentaAsociada complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="registrarCuentaAsociada">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="idbanco" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="tipodoc" type="{http://www.eam.edu.co/interbancario}tipoDocumentoEnum" minOccurs="0"/>
 *         &lt;element name="numerodoc" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="nombre" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="numerocuenta" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "registrarCuentaAsociada", propOrder = {
    "idbanco",
    "tipodoc",
    "numerodoc",
    "nombre",
    "numerocuenta"
})
public class RegistrarCuentaAsociada {

    protected String idbanco;
    @XmlSchemaType(name = "string")
    protected TipoDocumentoEnum tipodoc;
    protected String numerodoc;
    protected String nombre;
    protected String numerocuenta;

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
     * Obtiene el valor de la propiedad tipodoc.
     * 
     * @return
     *     possible object is
     *     {@link TipoDocumentoEnum }
     *     
     */
    public TipoDocumentoEnum getTipodoc() {
        return tipodoc;
    }

    /**
     * Define el valor de la propiedad tipodoc.
     * 
     * @param value
     *     allowed object is
     *     {@link TipoDocumentoEnum }
     *     
     */
    public void setTipodoc(TipoDocumentoEnum value) {
        this.tipodoc = value;
    }

    /**
     * Obtiene el valor de la propiedad numerodoc.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNumerodoc() {
        return numerodoc;
    }

    /**
     * Define el valor de la propiedad numerodoc.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNumerodoc(String value) {
        this.numerodoc = value;
    }

    /**
     * Obtiene el valor de la propiedad nombre.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Define el valor de la propiedad nombre.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNombre(String value) {
        this.nombre = value;
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

}
