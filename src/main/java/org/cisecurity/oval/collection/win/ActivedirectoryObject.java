//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.8-b130911.1802 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2019.11.21 at 11:38:45 AM EST 
//


package org.cisecurity.oval.collection.win;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlType;
import org.cisecurity.oval.collection.ObjectStringType;
import org.cisecurity.oval.collection.ObjectType;
import org.cisecurity.oval.collection.Set;


/**
 * <p>Java class for anonymous complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;extension base="{http://oval.cisecurity.org/XMLSchema/oval-collections-6}ObjectType">
 *       &lt;sequence>
 *         &lt;choice>
 *           &lt;element ref="{http://oval.cisecurity.org/XMLSchema/oval-collections-6}set"/>
 *           &lt;sequence>
 *             &lt;element name="naming_context" type="{http://oval.cisecurity.org/XMLSchema/oval-collections-6#windows}ObjectNamingContextType"/>
 *             &lt;element name="relative_dn" type="{http://oval.cisecurity.org/XMLSchema/oval-collections-6}ObjectStringType"/>
 *             &lt;element name="attribute" type="{http://oval.cisecurity.org/XMLSchema/oval-collections-6}ObjectStringType"/>
 *           &lt;/sequence>
 *         &lt;/choice>
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
    "set",
    "namingContext",
    "relativeDn",
    "attribute"
})
public class ActivedirectoryObject
    extends ObjectType
{

    @XmlElement(namespace = "http://oval.cisecurity.org/XMLSchema/oval-collections-6")
    protected Set set;
    @XmlElement(name = "naming_context")
    protected ObjectNamingContextType namingContext;
    @XmlElementRef(name = "relative_dn", namespace = "http://oval.cisecurity.org/XMLSchema/oval-collections-6#windows", type = JAXBElement.class, required = false)
    protected JAXBElement<ObjectStringType> relativeDn;
    @XmlElementRef(name = "attribute", namespace = "http://oval.cisecurity.org/XMLSchema/oval-collections-6#windows", type = JAXBElement.class, required = false)
    protected JAXBElement<ObjectStringType> attribute;

    /**
     * Gets the value of the set property.
     * 
     * @return
     *     possible object is
     *     {@link Set }
     *     
     */
    public Set getSet() {
        return set;
    }

    /**
     * Sets the value of the set property.
     * 
     * @param value
     *     allowed object is
     *     {@link Set }
     *     
     */
    public void setSet(Set value) {
        this.set = value;
    }

    /**
     * Gets the value of the namingContext property.
     * 
     * @return
     *     possible object is
     *     {@link ObjectNamingContextType }
     *     
     */
    public ObjectNamingContextType getNamingContext() {
        return namingContext;
    }

    /**
     * Sets the value of the namingContext property.
     * 
     * @param value
     *     allowed object is
     *     {@link ObjectNamingContextType }
     *     
     */
    public void setNamingContext(ObjectNamingContextType value) {
        this.namingContext = value;
    }

    /**
     * Gets the value of the relativeDn property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link ObjectStringType }{@code >}
     *     
     */
    public JAXBElement<ObjectStringType> getRelativeDn() {
        return relativeDn;
    }

    /**
     * Sets the value of the relativeDn property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link ObjectStringType }{@code >}
     *     
     */
    public void setRelativeDn(JAXBElement<ObjectStringType> value) {
        this.relativeDn = value;
    }

    /**
     * Gets the value of the attribute property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link ObjectStringType }{@code >}
     *     
     */
    public JAXBElement<ObjectStringType> getAttribute() {
        return attribute;
    }

    /**
     * Sets the value of the attribute property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link ObjectStringType }{@code >}
     *     
     */
    public void setAttribute(JAXBElement<ObjectStringType> value) {
        this.attribute = value;
    }

}
