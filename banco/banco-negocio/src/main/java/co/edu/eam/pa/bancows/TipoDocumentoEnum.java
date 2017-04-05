
package co.edu.eam.pa.bancows;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para tipoDocumentoEnum.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * <p>
 * <pre>
 * &lt;simpleType name="tipoDocumentoEnum">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="CC"/>
 *     &lt;enumeration value="TI"/>
 *     &lt;enumeration value="PAS"/>
 *     &lt;enumeration value="CE"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "tipoDocumentoEnum")
@XmlEnum
public enum TipoDocumentoEnum {

    CC,
    TI,
    PAS,
    CE;

    public String value() {
        return name();
    }

    public static TipoDocumentoEnum fromValue(String v) {
        return valueOf(v);
    }

}
