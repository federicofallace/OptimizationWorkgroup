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
 * <p>Java class for moduleType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="moduleType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;choice>
 *         &lt;element name="ruin_and_recreate_group" type="{http://www.w3schools.com}RuinAndRecreateGroupType"/>
 *         &lt;element name="gendreau_group" type="{http://www.w3schools.com}GendreauGroupType"/>
 *       &lt;/choice>
 *       &lt;attribute name="name" type="{http://www.w3schools.com}moduleTypeEnum" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "moduleType", propOrder = {
    "ruinAndRecreateGroup",
    "gendreauGroup"
})
public class ModuleType {

    @XmlElement(name = "ruin_and_recreate_group")
    protected RuinAndRecreateGroupType ruinAndRecreateGroup;
    @XmlElement(name = "gendreau_group")
    protected GendreauGroupType gendreauGroup;
    @XmlAttribute(name = "name")
    protected ModuleTypeEnum name;

    /**
     * Gets the value of the ruinAndRecreateGroup property.
     * 
     * @return
     *     possible object is
     *     {@link RuinAndRecreateGroupType }
     *     
     */
    public RuinAndRecreateGroupType getRuinAndRecreateGroup() {
        return ruinAndRecreateGroup;
    }

    /**
     * Sets the value of the ruinAndRecreateGroup property.
     * 
     * @param value
     *     allowed object is
     *     {@link RuinAndRecreateGroupType }
     *     
     */
    public void setRuinAndRecreateGroup(RuinAndRecreateGroupType value) {
        this.ruinAndRecreateGroup = value;
    }

    /**
     * Gets the value of the gendreauGroup property.
     * 
     * @return
     *     possible object is
     *     {@link GendreauGroupType }
     *     
     */
    public GendreauGroupType getGendreauGroup() {
        return gendreauGroup;
    }

    /**
     * Sets the value of the gendreauGroup property.
     * 
     * @param value
     *     allowed object is
     *     {@link GendreauGroupType }
     *     
     */
    public void setGendreauGroup(GendreauGroupType value) {
        this.gendreauGroup = value;
    }

    /**
     * Gets the value of the name property.
     * 
     * @return
     *     possible object is
     *     {@link ModuleTypeEnum }
     *     
     */
    public ModuleTypeEnum getName() {
        return name;
    }

    /**
     * Sets the value of the name property.
     * 
     * @param value
     *     allowed object is
     *     {@link ModuleTypeEnum }
     *     
     */
    public void setName(ModuleTypeEnum value) {
        this.name = value;
    }

}
