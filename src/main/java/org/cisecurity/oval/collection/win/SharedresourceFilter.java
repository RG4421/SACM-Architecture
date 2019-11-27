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
import org.cisecurity.oval.collection.FilterIntType;
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
 *         &lt;element name="shared_type" type="{http://oval.cisecurity.org/XMLSchema/oval-collections-6#windows}FilterSharedResourceTypeType" minOccurs="0"/>
 *         &lt;element name="max_uses" type="{http://oval.cisecurity.org/XMLSchema/oval-collections-6}FilterIntType" minOccurs="0"/>
 *         &lt;element name="current_uses" type="{http://oval.cisecurity.org/XMLSchema/oval-collections-6}FilterIntType" minOccurs="0"/>
 *         &lt;element name="local_path" type="{http://oval.cisecurity.org/XMLSchema/oval-collections-6}FilterStringType" minOccurs="0"/>
 *         &lt;element name="access_read_permission" type="{http://oval.cisecurity.org/XMLSchema/oval-collections-6}FilterBoolType" minOccurs="0"/>
 *         &lt;element name="access_write_permission" type="{http://oval.cisecurity.org/XMLSchema/oval-collections-6}FilterBoolType" minOccurs="0"/>
 *         &lt;element name="access_create_permission" type="{http://oval.cisecurity.org/XMLSchema/oval-collections-6}FilterBoolType" minOccurs="0"/>
 *         &lt;element name="access_exec_permission" type="{http://oval.cisecurity.org/XMLSchema/oval-collections-6}FilterBoolType" minOccurs="0"/>
 *         &lt;element name="access_delete_permission" type="{http://oval.cisecurity.org/XMLSchema/oval-collections-6}FilterBoolType" minOccurs="0"/>
 *         &lt;element name="access_atrib_permission" type="{http://oval.cisecurity.org/XMLSchema/oval-collections-6}FilterBoolType" minOccurs="0"/>
 *         &lt;element name="access_perm_permission" type="{http://oval.cisecurity.org/XMLSchema/oval-collections-6}FilterBoolType" minOccurs="0"/>
 *         &lt;element name="access_all_permission" type="{http://oval.cisecurity.org/XMLSchema/oval-collections-6}FilterBoolType" minOccurs="0"/>
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
    "sharedType",
    "maxUses",
    "currentUses",
    "localPath",
    "accessReadPermission",
    "accessWritePermission",
    "accessCreatePermission",
    "accessExecPermission",
    "accessDeletePermission",
    "accessAtribPermission",
    "accessPermPermission",
    "accessAllPermission"
})
public class SharedresourceFilter
    extends FilterType
{

    protected FilterStringType netname;
    @XmlElement(name = "shared_type")
    protected FilterSharedResourceTypeType sharedType;
    @XmlElement(name = "max_uses")
    protected FilterIntType maxUses;
    @XmlElement(name = "current_uses")
    protected FilterIntType currentUses;
    @XmlElement(name = "local_path")
    protected FilterStringType localPath;
    @XmlElement(name = "access_read_permission")
    protected FilterBoolType accessReadPermission;
    @XmlElement(name = "access_write_permission")
    protected FilterBoolType accessWritePermission;
    @XmlElement(name = "access_create_permission")
    protected FilterBoolType accessCreatePermission;
    @XmlElement(name = "access_exec_permission")
    protected FilterBoolType accessExecPermission;
    @XmlElement(name = "access_delete_permission")
    protected FilterBoolType accessDeletePermission;
    @XmlElement(name = "access_atrib_permission")
    protected FilterBoolType accessAtribPermission;
    @XmlElement(name = "access_perm_permission")
    protected FilterBoolType accessPermPermission;
    @XmlElement(name = "access_all_permission")
    protected FilterBoolType accessAllPermission;

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
     * Gets the value of the sharedType property.
     * 
     * @return
     *     possible object is
     *     {@link FilterSharedResourceTypeType }
     *     
     */
    public FilterSharedResourceTypeType getSharedType() {
        return sharedType;
    }

    /**
     * Sets the value of the sharedType property.
     * 
     * @param value
     *     allowed object is
     *     {@link FilterSharedResourceTypeType }
     *     
     */
    public void setSharedType(FilterSharedResourceTypeType value) {
        this.sharedType = value;
    }

    /**
     * Gets the value of the maxUses property.
     * 
     * @return
     *     possible object is
     *     {@link FilterIntType }
     *     
     */
    public FilterIntType getMaxUses() {
        return maxUses;
    }

    /**
     * Sets the value of the maxUses property.
     * 
     * @param value
     *     allowed object is
     *     {@link FilterIntType }
     *     
     */
    public void setMaxUses(FilterIntType value) {
        this.maxUses = value;
    }

    /**
     * Gets the value of the currentUses property.
     * 
     * @return
     *     possible object is
     *     {@link FilterIntType }
     *     
     */
    public FilterIntType getCurrentUses() {
        return currentUses;
    }

    /**
     * Sets the value of the currentUses property.
     * 
     * @param value
     *     allowed object is
     *     {@link FilterIntType }
     *     
     */
    public void setCurrentUses(FilterIntType value) {
        this.currentUses = value;
    }

    /**
     * Gets the value of the localPath property.
     * 
     * @return
     *     possible object is
     *     {@link FilterStringType }
     *     
     */
    public FilterStringType getLocalPath() {
        return localPath;
    }

    /**
     * Sets the value of the localPath property.
     * 
     * @param value
     *     allowed object is
     *     {@link FilterStringType }
     *     
     */
    public void setLocalPath(FilterStringType value) {
        this.localPath = value;
    }

    /**
     * Gets the value of the accessReadPermission property.
     * 
     * @return
     *     possible object is
     *     {@link FilterBoolType }
     *     
     */
    public FilterBoolType getAccessReadPermission() {
        return accessReadPermission;
    }

    /**
     * Sets the value of the accessReadPermission property.
     * 
     * @param value
     *     allowed object is
     *     {@link FilterBoolType }
     *     
     */
    public void setAccessReadPermission(FilterBoolType value) {
        this.accessReadPermission = value;
    }

    /**
     * Gets the value of the accessWritePermission property.
     * 
     * @return
     *     possible object is
     *     {@link FilterBoolType }
     *     
     */
    public FilterBoolType getAccessWritePermission() {
        return accessWritePermission;
    }

    /**
     * Sets the value of the accessWritePermission property.
     * 
     * @param value
     *     allowed object is
     *     {@link FilterBoolType }
     *     
     */
    public void setAccessWritePermission(FilterBoolType value) {
        this.accessWritePermission = value;
    }

    /**
     * Gets the value of the accessCreatePermission property.
     * 
     * @return
     *     possible object is
     *     {@link FilterBoolType }
     *     
     */
    public FilterBoolType getAccessCreatePermission() {
        return accessCreatePermission;
    }

    /**
     * Sets the value of the accessCreatePermission property.
     * 
     * @param value
     *     allowed object is
     *     {@link FilterBoolType }
     *     
     */
    public void setAccessCreatePermission(FilterBoolType value) {
        this.accessCreatePermission = value;
    }

    /**
     * Gets the value of the accessExecPermission property.
     * 
     * @return
     *     possible object is
     *     {@link FilterBoolType }
     *     
     */
    public FilterBoolType getAccessExecPermission() {
        return accessExecPermission;
    }

    /**
     * Sets the value of the accessExecPermission property.
     * 
     * @param value
     *     allowed object is
     *     {@link FilterBoolType }
     *     
     */
    public void setAccessExecPermission(FilterBoolType value) {
        this.accessExecPermission = value;
    }

    /**
     * Gets the value of the accessDeletePermission property.
     * 
     * @return
     *     possible object is
     *     {@link FilterBoolType }
     *     
     */
    public FilterBoolType getAccessDeletePermission() {
        return accessDeletePermission;
    }

    /**
     * Sets the value of the accessDeletePermission property.
     * 
     * @param value
     *     allowed object is
     *     {@link FilterBoolType }
     *     
     */
    public void setAccessDeletePermission(FilterBoolType value) {
        this.accessDeletePermission = value;
    }

    /**
     * Gets the value of the accessAtribPermission property.
     * 
     * @return
     *     possible object is
     *     {@link FilterBoolType }
     *     
     */
    public FilterBoolType getAccessAtribPermission() {
        return accessAtribPermission;
    }

    /**
     * Sets the value of the accessAtribPermission property.
     * 
     * @param value
     *     allowed object is
     *     {@link FilterBoolType }
     *     
     */
    public void setAccessAtribPermission(FilterBoolType value) {
        this.accessAtribPermission = value;
    }

    /**
     * Gets the value of the accessPermPermission property.
     * 
     * @return
     *     possible object is
     *     {@link FilterBoolType }
     *     
     */
    public FilterBoolType getAccessPermPermission() {
        return accessPermPermission;
    }

    /**
     * Sets the value of the accessPermPermission property.
     * 
     * @param value
     *     allowed object is
     *     {@link FilterBoolType }
     *     
     */
    public void setAccessPermPermission(FilterBoolType value) {
        this.accessPermPermission = value;
    }

    /**
     * Gets the value of the accessAllPermission property.
     * 
     * @return
     *     possible object is
     *     {@link FilterBoolType }
     *     
     */
    public FilterBoolType getAccessAllPermission() {
        return accessAllPermission;
    }

    /**
     * Sets the value of the accessAllPermission property.
     * 
     * @param value
     *     allowed object is
     *     {@link FilterBoolType }
     *     
     */
    public void setAccessAllPermission(FilterBoolType value) {
        this.accessAllPermission = value;
    }

}
