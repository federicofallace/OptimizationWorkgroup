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
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for prematureBreakType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="prematureBreakType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;choice>
 *         &lt;element name="pBreak_iteration_group" type="{http://www.w3schools.com}PrematureBreakIterationTypeGroup"/>
 *         &lt;element name="pBreak_time_group" type="{http://www.w3schools.com}PrematureBreakTimeTypeGroup"/>
 *         &lt;element name="pBreak_variationCoefficient_group" type="{http://www.w3schools.com}PrematureBreakVariationCoefficientTypeGroup"/>
 *       &lt;/choice>
 *       &lt;attribute name="basedOn" type="{http://www.w3schools.com}prematureBreakTypeEnum" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "prematureBreakType", propOrder = {
    "pBreakIterationGroup",
    "pBreakTimeGroup",
    "pBreakVariationCoefficientGroup"
})
public class PrematureBreakType {

    @XmlElement(name = "pBreak_iteration_group")
    protected PrematureBreakIterationTypeGroup pBreakIterationGroup;
    @XmlElement(name = "pBreak_time_group")
    protected PrematureBreakTimeTypeGroup pBreakTimeGroup;
    @XmlElement(name = "pBreak_variationCoefficient_group")
    protected PrematureBreakVariationCoefficientTypeGroup pBreakVariationCoefficientGroup;
    @XmlAttribute(name = "basedOn")
    protected PrematureBreakTypeEnum basedOn;

    /**
     * Gets the value of the pBreakIterationGroup property.
     * 
     * @return
     *     possible object is
     *     {@link PrematureBreakIterationTypeGroup }
     *     
     */
    public PrematureBreakIterationTypeGroup getPBreakIterationGroup() {
        return pBreakIterationGroup;
    }

    /**
     * Sets the value of the pBreakIterationGroup property.
     * 
     * @param value
     *     allowed object is
     *     {@link PrematureBreakIterationTypeGroup }
     *     
     */
    public void setPBreakIterationGroup(PrematureBreakIterationTypeGroup value) {
        this.pBreakIterationGroup = value;
    }

    /**
     * Gets the value of the pBreakTimeGroup property.
     * 
     * @return
     *     possible object is
     *     {@link PrematureBreakTimeTypeGroup }
     *     
     */
    public PrematureBreakTimeTypeGroup getPBreakTimeGroup() {
        return pBreakTimeGroup;
    }

    /**
     * Sets the value of the pBreakTimeGroup property.
     * 
     * @param value
     *     allowed object is
     *     {@link PrematureBreakTimeTypeGroup }
     *     
     */
    public void setPBreakTimeGroup(PrematureBreakTimeTypeGroup value) {
        this.pBreakTimeGroup = value;
    }

    /**
     * Gets the value of the pBreakVariationCoefficientGroup property.
     * 
     * @return
     *     possible object is
     *     {@link PrematureBreakVariationCoefficientTypeGroup }
     *     
     */
    public PrematureBreakVariationCoefficientTypeGroup getPBreakVariationCoefficientGroup() {
        return pBreakVariationCoefficientGroup;
    }

    /**
     * Sets the value of the pBreakVariationCoefficientGroup property.
     * 
     * @param value
     *     allowed object is
     *     {@link PrematureBreakVariationCoefficientTypeGroup }
     *     
     */
    public void setPBreakVariationCoefficientGroup(PrematureBreakVariationCoefficientTypeGroup value) {
        this.pBreakVariationCoefficientGroup = value;
    }

    /**
     * Gets the value of the basedOn property.
     * 
     * @return
     *     possible object is
     *     {@link PrematureBreakTypeEnum }
     *     
     */
    public PrematureBreakTypeEnum getBasedOn() {
        return basedOn;
    }

    /**
     * Sets the value of the basedOn property.
     * 
     * @param value
     *     allowed object is
     *     {@link PrematureBreakTypeEnum }
     *     
     */
    public void setBasedOn(PrematureBreakTypeEnum value) {
        this.basedOn = value;
    }

}
