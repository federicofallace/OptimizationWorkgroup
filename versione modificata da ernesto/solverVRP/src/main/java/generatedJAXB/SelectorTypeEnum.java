//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.4-2 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2015.11.21 at 08:31:12 PM CET 
//


package generatedJAXB;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for selectorTypeEnum.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="selectorTypeEnum">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="selectBest"/>
 *     &lt;enumeration value="selectRandomly"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "selectorTypeEnum")
@XmlEnum
public enum SelectorTypeEnum {

    @XmlEnumValue("selectBest")
    SELECT_BEST("selectBest"),
    @XmlEnumValue("selectRandomly")
    SELECT_RANDOMLY("selectRandomly");
    private final String value;

    SelectorTypeEnum(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static SelectorTypeEnum fromValue(String v) {
        for (SelectorTypeEnum c: SelectorTypeEnum.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
