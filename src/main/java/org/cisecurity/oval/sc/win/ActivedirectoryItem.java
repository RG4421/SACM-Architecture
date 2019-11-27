//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.8-b130911.1802 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2019.11.21 at 11:38:58 AM EST 
//


package org.cisecurity.oval.sc.win;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlType;
import org.cisecurity.oval.sc.CollectedItemAnySimpleType;
import org.cisecurity.oval.sc.CollectedItemStringType;
import org.cisecurity.oval.sc.CollectedItemType;


/**
 * <p>Java class for anonymous complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;extension base="{http://oval.cisecurity.org/XMLSchema/oval-system-characteristics-6}CollectedItemType">
 *       &lt;sequence>
 *         &lt;element name="naming_context" type="{http://oval.cisecurity.org/XMLSchema/oval-system-characteristics-6#windows}EntityItemNamingContextType" minOccurs="0"/>
 *         &lt;element name="relative_dn" type="{http://oval.cisecurity.org/XMLSchema/oval-system-characteristics-6}CollectedItemStringType" minOccurs="0"/>
 *         &lt;element name="attribute" type="{http://oval.cisecurity.org/XMLSchema/oval-system-characteristics-6}CollectedItemStringType" minOccurs="0"/>
 *         &lt;element name="object_class" type="{http://oval.cisecurity.org/XMLSchema/oval-system-characteristics-6}CollectedItemStringType" minOccurs="0"/>
 *         &lt;element name="adstype" type="{http://oval.cisecurity.org/XMLSchema/oval-system-characteristics-6#windows}EntityItemAdstypeType" minOccurs="0"/>
 *         &lt;element name="value" type="{http://oval.cisecurity.org/XMLSchema/oval-system-characteristics-6}CollectedItemAnySimpleType" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "namingContext",
    "relativeDn",
    "attribute",
    "objectClass",
    "adstype",
    "value"
})
public class ActivedirectoryItem
    extends CollectedItemType
{

    @XmlElement(name = "naming_context")
    protected EntityItemNamingContextType namingContext;
    @XmlElementRef(name = "relative_dn", namespace = "http://oval.cisecurity.org/XMLSchema/oval-system-characteristics-6#windows", type = JAXBElement.class, required = false)
    protected JAXBElement<CollectedItemStringType> relativeDn;
    @XmlElementRef(name = "attribute", namespace = "http://oval.cisecurity.org/XMLSchema/oval-system-characteristics-6#windows", type = JAXBElement.class, required = false)
    protected JAXBElement<CollectedItemStringType> attribute;
    @XmlElement(name = "object_class")
    protected CollectedItemStringType objectClass;
    protected EntityItemAdstypeType adstype;
    protected List<CollectedItemAnySimpleType> value;

    /**
     * Gets the value of the namingContext property.
     * 
     * @return
     *     possible object is
     *     {@link EntityItemNamingContextType }
     *     
     */
    public EntityItemNamingContextType getNamingContext() {
        return namingContext;
    }

    /**
     * Sets the value of the namingContext property.
     * 
     * @param value
     *     allowed object is
     *     {@link EntityItemNamingContextType }
     *     
     */
    public void setNamingContext(EntityItemNamingContextType value) {
        this.namingContext = value;
    }

    /**
     * Gets the value of the relativeDn property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link CollectedItemStringType }{@code >}
     *     
     */
    public JAXBElement<CollectedItemStringType> getRelativeDn() {
        return relativeDn;
    }

    /**
     * Sets the value of the relativeDn property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link CollectedItemStringType }{@code >}
     *     
     */
    public void setRelativeDn(JAXBElement<CollectedItemStringType> value) {
        this.relativeDn = value;
    }

    /**
     * Gets the value of the attribute property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link CollectedItemStringType }{@code >}
     *     
     */
    public JAXBElement<CollectedItemStringType> getAttribute() {
        return attribute;
    }

    /**
     * Sets the value of the attribute property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link CollectedItemStringType }{@code >}
     *     
     */
    public void setAttribute(JAXBElement<CollectedItemStringType> value) {
        this.attribute = value;
    }

    /**
     * Gets the value of the objectClass property.
     * 
     * @return
     *     possible object is
     *     {@link CollectedItemStringType }
     *     
     */
    public CollectedItemStringType getObjectClass() {
        return objectClass;
    }

    /**
     * Sets the value of the objectClass property.
     * 
     * @param value
     *     allowed object is
     *     {@link CollectedItemStringType }
     *     
     */
    public void setObjectClass(CollectedItemStringType value) {
        this.objectClass = value;
    }

    /**
     * Gets the value of the adstype property.
     * 
     * @return
     *     possible object is
     *     {@link EntityItemAdstypeType }
     *     
     */
    public EntityItemAdstypeType getAdstype() {
        return adstype;
    }

    /**
     * Sets the value of the adstype property.
     * 
     * @param value
     *     allowed object is
     *     {@link EntityItemAdstypeType }
     *     
     */
    public void setAdstype(EntityItemAdstypeType value) {
        this.adstype = value;
    }

    /**
     * Gets the value of the value property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the value property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getValue().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link CollectedItemAnySimpleType }
     * 
     * 
     */
    public List<CollectedItemAnySimpleType> getValue() {
        if (value == null) {
            value = new ArrayList<CollectedItemAnySimpleType>();
        }
        return this.value;
    }

}