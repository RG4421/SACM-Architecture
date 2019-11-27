//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.8-b130911.1802 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2019.11.21 at 11:38:45 AM EST 
//


package org.cisecurity.oval.collection.win;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlType;
import org.cisecurity.oval.collection.ObjectFilter;
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
 *             &lt;element name="behaviors" type="{http://oval.cisecurity.org/XMLSchema/oval-collections-6#windows}RegkeyEffectiveRightsBehaviors" minOccurs="0"/>
 *             &lt;element name="hive" type="{http://oval.cisecurity.org/XMLSchema/oval-collections-6#windows}ObjectRegistryHiveType"/>
 *             &lt;element name="key" type="{http://oval.cisecurity.org/XMLSchema/oval-collections-6}ObjectStringType"/>
 *             &lt;element name="trustee_sid" type="{http://oval.cisecurity.org/XMLSchema/oval-collections-6}ObjectStringType"/>
 *             &lt;element ref="{http://oval.cisecurity.org/XMLSchema/oval-collections-6}object_filter" maxOccurs="unbounded" minOccurs="0"/>
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
    "behaviors",
    "hive",
    "key",
    "trusteeSid",
    "objectFilter"
})
public class RegkeyeffectiverightsObject
    extends ObjectType
{

    @XmlElement(namespace = "http://oval.cisecurity.org/XMLSchema/oval-collections-6")
    protected Set set;
    protected RegkeyEffectiveRightsBehaviors behaviors;
    protected ObjectRegistryHiveType hive;
    @XmlElementRef(name = "key", namespace = "http://oval.cisecurity.org/XMLSchema/oval-collections-6#windows", type = JAXBElement.class, required = false)
    protected JAXBElement<ObjectStringType> key;
    @XmlElement(name = "trustee_sid")
    protected ObjectStringType trusteeSid;
    @XmlElement(name = "object_filter", namespace = "http://oval.cisecurity.org/XMLSchema/oval-collections-6")
    protected List<ObjectFilter> objectFilter;

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
     * Gets the value of the behaviors property.
     * 
     * @return
     *     possible object is
     *     {@link RegkeyEffectiveRightsBehaviors }
     *     
     */
    public RegkeyEffectiveRightsBehaviors getBehaviors() {
        return behaviors;
    }

    /**
     * Sets the value of the behaviors property.
     * 
     * @param value
     *     allowed object is
     *     {@link RegkeyEffectiveRightsBehaviors }
     *     
     */
    public void setBehaviors(RegkeyEffectiveRightsBehaviors value) {
        this.behaviors = value;
    }

    /**
     * Gets the value of the hive property.
     * 
     * @return
     *     possible object is
     *     {@link ObjectRegistryHiveType }
     *     
     */
    public ObjectRegistryHiveType getHive() {
        return hive;
    }

    /**
     * Sets the value of the hive property.
     * 
     * @param value
     *     allowed object is
     *     {@link ObjectRegistryHiveType }
     *     
     */
    public void setHive(ObjectRegistryHiveType value) {
        this.hive = value;
    }

    /**
     * Gets the value of the key property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link ObjectStringType }{@code >}
     *     
     */
    public JAXBElement<ObjectStringType> getKey() {
        return key;
    }

    /**
     * Sets the value of the key property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link ObjectStringType }{@code >}
     *     
     */
    public void setKey(JAXBElement<ObjectStringType> value) {
        this.key = value;
    }

    /**
     * Gets the value of the trusteeSid property.
     * 
     * @return
     *     possible object is
     *     {@link ObjectStringType }
     *     
     */
    public ObjectStringType getTrusteeSid() {
        return trusteeSid;
    }

    /**
     * Sets the value of the trusteeSid property.
     * 
     * @param value
     *     allowed object is
     *     {@link ObjectStringType }
     *     
     */
    public void setTrusteeSid(ObjectStringType value) {
        this.trusteeSid = value;
    }

    /**
     * Gets the value of the objectFilter property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the objectFilter property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getObjectFilter().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link ObjectFilter }
     * 
     * 
     */
    public List<ObjectFilter> getObjectFilter() {
        if (objectFilter == null) {
            objectFilter = new ArrayList<ObjectFilter>();
        }
        return this.objectFilter;
    }

}
