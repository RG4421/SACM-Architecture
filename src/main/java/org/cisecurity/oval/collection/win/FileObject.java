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
 *             &lt;element name="behaviors" type="{http://oval.cisecurity.org/XMLSchema/oval-collections-6#windows}FileBehaviors" minOccurs="0"/>
 *             &lt;choice>
 *               &lt;element name="filepath" type="{http://oval.cisecurity.org/XMLSchema/oval-collections-6}ObjectStringType"/>
 *               &lt;sequence>
 *                 &lt;element name="path" type="{http://oval.cisecurity.org/XMLSchema/oval-collections-6}ObjectStringType"/>
 *                 &lt;element name="filename" type="{http://oval.cisecurity.org/XMLSchema/oval-collections-6}ObjectStringType"/>
 *               &lt;/sequence>
 *             &lt;/choice>
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
    "filepath",
    "path",
    "filename",
    "objectFilter"
})
public class FileObject
    extends ObjectType
{

    @XmlElement(namespace = "http://oval.cisecurity.org/XMLSchema/oval-collections-6")
    protected Set set;
    protected FileBehaviors behaviors;
    protected ObjectStringType filepath;
    protected ObjectStringType path;
    @XmlElementRef(name = "filename", namespace = "http://oval.cisecurity.org/XMLSchema/oval-collections-6#windows", type = JAXBElement.class, required = false)
    protected JAXBElement<ObjectStringType> filename;
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
     *     {@link FileBehaviors }
     *     
     */
    public FileBehaviors getBehaviors() {
        return behaviors;
    }

    /**
     * Sets the value of the behaviors property.
     * 
     * @param value
     *     allowed object is
     *     {@link FileBehaviors }
     *     
     */
    public void setBehaviors(FileBehaviors value) {
        this.behaviors = value;
    }

    /**
     * Gets the value of the filepath property.
     * 
     * @return
     *     possible object is
     *     {@link ObjectStringType }
     *     
     */
    public ObjectStringType getFilepath() {
        return filepath;
    }

    /**
     * Sets the value of the filepath property.
     * 
     * @param value
     *     allowed object is
     *     {@link ObjectStringType }
     *     
     */
    public void setFilepath(ObjectStringType value) {
        this.filepath = value;
    }

    /**
     * Gets the value of the path property.
     * 
     * @return
     *     possible object is
     *     {@link ObjectStringType }
     *     
     */
    public ObjectStringType getPath() {
        return path;
    }

    /**
     * Sets the value of the path property.
     * 
     * @param value
     *     allowed object is
     *     {@link ObjectStringType }
     *     
     */
    public void setPath(ObjectStringType value) {
        this.path = value;
    }

    /**
     * Gets the value of the filename property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link ObjectStringType }{@code >}
     *     
     */
    public JAXBElement<ObjectStringType> getFilename() {
        return filename;
    }

    /**
     * Sets the value of the filename property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link ObjectStringType }{@code >}
     *     
     */
    public void setFilename(JAXBElement<ObjectStringType> value) {
        this.filename = value;
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
