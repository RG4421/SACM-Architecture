//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.8-b130911.1802 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2019.11.21 at 11:38:45 AM EST 
//


package org.cisecurity.oval.collection.win;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import org.cisecurity.oval.collection.FilterBoolType;
import org.cisecurity.oval.collection.FilterStringType;
import org.cisecurity.oval.collection.FilterType;


/**
 * <p>Java class for anonymous complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;extension base="{http://oval.cisecurity.org/XMLSchema/oval-collections-6}FilterType">
 *       &lt;sequence>
 *         &lt;element name="netname" type="{http://oval.cisecurity.org/XMLSchema/oval-collections-6}FilterStringType" minOccurs="0"/>
 *         &lt;element name="trustee_sid" type="{http://oval.cisecurity.org/XMLSchema/oval-collections-6}FilterStringType" minOccurs="0"/>
 *         &lt;element name="standard_delete" type="{http://oval.cisecurity.org/XMLSchema/oval-collections-6}FilterBoolType" minOccurs="0"/>
 *         &lt;element name="standard_read_control" type="{http://oval.cisecurity.org/XMLSchema/oval-collections-6}FilterBoolType" minOccurs="0"/>
 *         &lt;element name="standard_write_dac" type="{http://oval.cisecurity.org/XMLSchema/oval-collections-6}FilterBoolType" minOccurs="0"/>
 *         &lt;element name="standard_write_owner" type="{http://oval.cisecurity.org/XMLSchema/oval-collections-6}FilterBoolType" minOccurs="0"/>
 *         &lt;element name="standard_synchronize" type="{http://oval.cisecurity.org/XMLSchema/oval-collections-6}FilterBoolType" minOccurs="0"/>
 *         &lt;element name="access_system_security" type="{http://oval.cisecurity.org/XMLSchema/oval-collections-6}FilterBoolType" minOccurs="0"/>
 *         &lt;element name="generic_read" type="{http://oval.cisecurity.org/XMLSchema/oval-collections-6}FilterBoolType" minOccurs="0"/>
 *         &lt;element name="generic_write" type="{http://oval.cisecurity.org/XMLSchema/oval-collections-6}FilterBoolType" minOccurs="0"/>
 *         &lt;element name="generic_execute" type="{http://oval.cisecurity.org/XMLSchema/oval-collections-6}FilterBoolType" minOccurs="0"/>
 *         &lt;element name="generic_all" type="{http://oval.cisecurity.org/XMLSchema/oval-collections-6}FilterBoolType" minOccurs="0"/>
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
    "netname",
    "trusteeSid",
    "standardDelete",
    "standardReadControl",
    "standardWriteDac",
    "standardWriteOwner",
    "standardSynchronize",
    "accessSystemSecurity",
    "genericRead",
    "genericWrite",
    "genericExecute",
    "genericAll"
})
public class SharedresourceeffectiverightsFilter
    extends FilterType
{

    protected FilterStringType netname;
    @XmlElement(name = "trustee_sid")
    protected FilterStringType trusteeSid;
    @XmlElement(name = "standard_delete")
    protected FilterBoolType standardDelete;
    @XmlElement(name = "standard_read_control")
    protected FilterBoolType standardReadControl;
    @XmlElement(name = "standard_write_dac")
    protected FilterBoolType standardWriteDac;
    @XmlElement(name = "standard_write_owner")
    protected FilterBoolType standardWriteOwner;
    @XmlElement(name = "standard_synchronize")
    protected FilterBoolType standardSynchronize;
    @XmlElement(name = "access_system_security")
    protected FilterBoolType accessSystemSecurity;
    @XmlElement(name = "generic_read")
    protected FilterBoolType genericRead;
    @XmlElement(name = "generic_write")
    protected FilterBoolType genericWrite;
    @XmlElement(name = "generic_execute")
    protected FilterBoolType genericExecute;
    @XmlElement(name = "generic_all")
    protected FilterBoolType genericAll;

    /**
     * Gets the value of the netname property.
     * 
     * @return
     *     possible object is
     *     {@link FilterStringType }
     *     
     */
    public FilterStringType getNetname() {
        return netname;
    }

    /**
     * Sets the value of the netname property.
     * 
     * @param value
     *     allowed object is
     *     {@link FilterStringType }
     *     
     */
    public void setNetname(FilterStringType value) {
        this.netname = value;
    }

    /**
     * Gets the value of the trusteeSid property.
     * 
     * @return
     *     possible object is
     *     {@link FilterStringType }
     *     
     */
    public FilterStringType getTrusteeSid() {
        return trusteeSid;
    }

    /**
     * Sets the value of the trusteeSid property.
     * 
     * @param value
     *     allowed object is
     *     {@link FilterStringType }
     *     
     */
    public void setTrusteeSid(FilterStringType value) {
        this.trusteeSid = value;
    }

    /**
     * Gets the value of the standardDelete property.
     * 
     * @return
     *     possible object is
     *     {@link FilterBoolType }
     *     
     */
    public FilterBoolType getStandardDelete() {
        return standardDelete;
    }

    /**
     * Sets the value of the standardDelete property.
     * 
     * @param value
     *     allowed object is
     *     {@link FilterBoolType }
     *     
     */
    public void setStandardDelete(FilterBoolType value) {
        this.standardDelete = value;
    }

    /**
     * Gets the value of the standardReadControl property.
     * 
     * @return
     *     possible object is
     *     {@link FilterBoolType }
     *     
     */
    public FilterBoolType getStandardReadControl() {
        return standardReadControl;
    }

    /**
     * Sets the value of the standardReadControl property.
     * 
     * @param value
     *     allowed object is
     *     {@link FilterBoolType }
     *     
     */
    public void setStandardReadControl(FilterBoolType value) {
        this.standardReadControl = value;
    }

    /**
     * Gets the value of the standardWriteDac property.
     * 
     * @return
     *     possible object is
     *     {@link FilterBoolType }
     *     
     */
    public FilterBoolType getStandardWriteDac() {
        return standardWriteDac;
    }

    /**
     * Sets the value of the standardWriteDac property.
     * 
     * @param value
     *     allowed object is
     *     {@link FilterBoolType }
     *     
     */
    public void setStandardWriteDac(FilterBoolType value) {
        this.standardWriteDac = value;
    }

    /**
     * Gets the value of the standardWriteOwner property.
     * 
     * @return
     *     possible object is
     *     {@link FilterBoolType }
     *     
     */
    public FilterBoolType getStandardWriteOwner() {
        return standardWriteOwner;
    }

    /**
     * Sets the value of the standardWriteOwner property.
     * 
     * @param value
     *     allowed object is
     *     {@link FilterBoolType }
     *     
     */
    public void setStandardWriteOwner(FilterBoolType value) {
        this.standardWriteOwner = value;
    }

    /**
     * Gets the value of the standardSynchronize property.
     * 
     * @return
     *     possible object is
     *     {@link FilterBoolType }
     *     
     */
    public FilterBoolType getStandardSynchronize() {
        return standardSynchronize;
    }

    /**
     * Sets the value of the standardSynchronize property.
     * 
     * @param value
     *     allowed object is
     *     {@link FilterBoolType }
     *     
     */
    public void setStandardSynchronize(FilterBoolType value) {
        this.standardSynchronize = value;
    }

    /**
     * Gets the value of the accessSystemSecurity property.
     * 
     * @return
     *     possible object is
     *     {@link FilterBoolType }
     *     
     */
    public FilterBoolType getAccessSystemSecurity() {
        return accessSystemSecurity;
    }

    /**
     * Sets the value of the accessSystemSecurity property.
     * 
     * @param value
     *     allowed object is
     *     {@link FilterBoolType }
     *     
     */
    public void setAccessSystemSecurity(FilterBoolType value) {
        this.accessSystemSecurity = value;
    }

    /**
     * Gets the value of the genericRead property.
     * 
     * @return
     *     possible object is
     *     {@link FilterBoolType }
     *     
     */
    public FilterBoolType getGenericRead() {
        return genericRead;
    }

    /**
     * Sets the value of the genericRead property.
     * 
     * @param value
     *     allowed object is
     *     {@link FilterBoolType }
     *     
     */
    public void setGenericRead(FilterBoolType value) {
        this.genericRead = value;
    }

    /**
     * Gets the value of the genericWrite property.
     * 
     * @return
     *     possible object is
     *     {@link FilterBoolType }
     *     
     */
    public FilterBoolType getGenericWrite() {
        return genericWrite;
    }

    /**
     * Sets the value of the genericWrite property.
     * 
     * @param value
     *     allowed object is
     *     {@link FilterBoolType }
     *     
     */
    public void setGenericWrite(FilterBoolType value) {
        this.genericWrite = value;
    }

    /**
     * Gets the value of the genericExecute property.
     * 
     * @return
     *     possible object is
     *     {@link FilterBoolType }
     *     
     */
    public FilterBoolType getGenericExecute() {
        return genericExecute;
    }

    /**
     * Sets the value of the genericExecute property.
     * 
     * @param value
     *     allowed object is
     *     {@link FilterBoolType }
     *     
     */
    public void setGenericExecute(FilterBoolType value) {
        this.genericExecute = value;
    }

    /**
     * Gets the value of the genericAll property.
     * 
     * @return
     *     possible object is
     *     {@link FilterBoolType }
     *     
     */
    public FilterBoolType getGenericAll() {
        return genericAll;
    }

    /**
     * Sets the value of the genericAll property.
     * 
     * @param value
     *     allowed object is
     *     {@link FilterBoolType }
     *     
     */
    public void setGenericAll(FilterBoolType value) {
        this.genericAll = value;
    }

}
