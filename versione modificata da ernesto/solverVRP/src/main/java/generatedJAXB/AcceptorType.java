//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.4-2 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2015.11.21 at 08:31:12 PM CET 
//


package generatedJAXB;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for acceptorType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="acceptorType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="alpha" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
 *         &lt;choice>
 *           &lt;element name="warmup" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *           &lt;element name="initialThreshold" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
 *         &lt;/choice>
 *       &lt;/sequence>
 *       &lt;attribute name="name" type="{http://www.w3schools.com}acceptorTypeEnum" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "acceptorType", propOrder = {
    "alpha",
    "warmup",
    "initialThreshold"
})
public class AcceptorType {

    protected Double alpha;
    protected Integer warmup;
    protected Double initialThreshold;
    @XmlAttribute(name = "name")
    protected AcceptorTypeEnum name;

    /**
     * Gets the value of the alpha property.
     * 
     * @return
     *     possible object is
     *     {@link Double }
     *     
     */
    public Double getAlpha() {
        return alpha;
    }

    /**
     * Sets the value of the alpha property.
     * 
     * @param value
     *     allowed object is
     *     {@link Double }
     *     
     */
    public void setAlpha(Double value) {
        this.alpha = value;
    }

    /**
     * Gets the value of the warmup property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getWarmup() {
        return warmup;
    }

    /**
     * Sets the value of the warmup property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setWarmup(Integer value) {
        this.warmup = value;
    }

    /**
     * Gets the value of the initialThreshold property.
     * 
     * @return
     *     possible object is
     *     {@link Double }
     *     
     */
    public Double getInitialThreshold() {
        return initialThreshold;
    }

    /**
     * Sets the value of the initialThreshold property.
     * 
     * @param value
     *     allowed object is
     *     {@link Double }
     *     
     */
    public void setInitialThreshold(Double value) {
        this.initialThreshold = value;
    }

    /**
     * Gets the value of the name property.
     * 
     * @return
     *     possible object is
     *     {@link AcceptorTypeEnum }
     *     
     */
    public AcceptorTypeEnum getName() {
        return name;
    }

    /**
     * Sets the value of the name property.
     * 
     * @param value
     *     allowed object is
     *     {@link AcceptorTypeEnum }
     *     
     */
    public void setName(AcceptorTypeEnum value) {
        this.name = value;
    }

}
