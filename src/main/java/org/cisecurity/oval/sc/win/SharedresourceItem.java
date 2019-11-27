//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.8-b130911.1802 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2019.11.21 at 11:38:58 AM EST 
//


package org.cisecurity.oval.sc.win;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import org.cisecurity.oval.sc.CollectedItemBoolType;
import org.cisecurity.oval.sc.CollectedItemIntType;
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
 *         &lt;element name="netname" type="{http://oval.cisecurity.org/XMLSchema/oval-system-characteristics-6}CollectedItemStringType" minOccurs="0"/>
 *         &lt;element name="shared_type" type="{http://oval.cisecurity.org/XMLSchema/oval-system-characteristics-6#windows}EntityItemSharedResourceTypeType" minOccurs="0"/>
 *         &lt;element name="max_uses" type="{http://oval.cisecurity.org/XMLSchema/oval-system-characteristics-6}CollectedItemIntType" minOccurs="0"/>
 *         &lt;element name="current_uses" type="{http://oval.cisecurity.org/XMLSchema/oval-system-characteristics-6}CollectedItemIntType" minOccurs="0"/>
 *         &lt;element name="local_path" type="{http://oval.cisecurity.org/XMLSchema/oval-system-characteristics-6}CollectedItemStringType" minOccurs="0"/>
 *         &lt;element name="access_read_permission" type="{http://oval.cisecurity.org/XMLSchema/oval-system-characteristics-6}CollectedItemBoolType" minOccurs="0"/>
 *         &lt;element name="access_write_permission" type="{http://oval.cisecurity.org/XMLSchema/oval-system-characteristics-6}CollectedItemBoolType" minOccurs="0"/>
 *         &lt;element name="access_create_permission" type="{http://oval.cisecurity.org/XMLSchema/oval-system-characteristics-6}CollectedItemBoolType" minOccurs="0"/>
 *         &lt;element name="access_exec_permission" type="{http://oval.cisecurity.org/XMLSchema/oval-system-characteristics-6}CollectedItemBoolType" minOccurs="0"/>
 *         &lt;element name="access_delete_permission" type="{http://oval.cisecurity.org/XMLSchema/oval-system-characteristics-6}CollectedItemBoolType" minOccurs="0"/>
 *         &lt;element name="access_atrib_permission" type="{http://oval.cisecurity.org/XMLSchema/oval-system-characteristics-6}CollectedItemBoolType" minOccurs="0"/>
 *         &lt;element name="access_perm_permission" type="{http://oval.cisecurity.org/XMLSchema/oval-system-characteristics-6}CollectedItemBoolType" minOccurs="0"/>
 *         &lt;element name="access_all_permission" type="{http://oval.cisecurity.org/XMLSchema/oval-system-characteristics-6}CollectedItemBoolType" minOccurs="0"/>
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
public class SharedresourceItem
    extends CollectedItemType
{

    protected CollectedItemStringType netname;
    @XmlElement(name = "shared_type")
    protected EntityItemSharedResourceTypeType sharedType;
    @XmlElement(name = "max_uses")
    protected CollectedItemIntType maxUses;
    @XmlElement(name = "current_uses")
    protected CollectedItemIntType currentUses;
    @XmlElement(name = "local_path")
    protected CollectedItemStringType localPath;
    @XmlElement(name = "access_read_permission")
    protected CollectedItemBoolType accessReadPermission;
    @XmlElement(name = "access_write_permission")
    protected CollectedItemBoolType accessWritePermission;
    @XmlElement(name = "access_create_permission")
    protected CollectedItemBoolType accessCreatePermission;
    @XmlElement(name = "access_exec_permission")
    protected CollectedItemBoolType accessExecPermission;
    @XmlElement(name = "access_delete_permission")
    protected CollectedItemBoolType accessDeletePermission;
    @XmlElement(name = "access_atrib_permission")
    protected CollectedItemBoolType accessAtribPermission;
    @XmlElement(name = "access_perm_permission")
    protected CollectedItemBoolType accessPermPermission;
    @XmlElement(name = "access_all_permission")
    protected CollectedItemBoolType accessAllPermission;

    /**
     * Gets the value of the netname property.
     * 
     * @return
     *     possible object is
     *     {@link CollectedItemStringType }
     *     
     */
    public CollectedItemStringType getNetname() {
        return netname;
    }

    /**
     * Sets the value of the netname property.
     * 
     * @param value
     *     allowed object is
     *     {@link CollectedItemStringType }
     *     
     */
    public void setNetname(CollectedItemStringType value) {
        this.netname = value;
    }

    /**
     * Gets the value of the sharedType property.
     * 
     * @return
     *     possible object is
     *     {@link EntityItemSharedResourceTypeType }
     *     
     */
    public EntityItemSharedResourceTypeType getSharedType() {
        return sharedType;
    }

    /**
     * Sets the value of the sharedType property.
     * 
     * @param value
     *     allowed object is
     *     {@link EntityItemSharedResourceTypeType }
     *     
     */
    public void setSharedType(EntityItemSharedResourceTypeType value) {
        this.sharedType = value;
    }

    /**
     * Gets the value of the maxUses property.
     * 
     * @return
     *     possible object is
     *     {@link CollectedItemIntType }
     *     
     */
    public CollectedItemIntType getMaxUses() {
        return maxUses;
    }

    /**
     * Sets the value of the maxUses property.
     * 
     * @param value
     *     allowed object is
     *     {@link CollectedItemIntType }
     *     
     */
    public void setMaxUses(CollectedItemIntType value) {
        this.maxUses = value;
    }

    /**
     * Gets the value of the currentUses property.
     * 
     * @return
     *     possible object is
     *     {@link CollectedItemIntType }
     *     
     */
    public CollectedItemIntType getCurrentUses() {
        return currentUses;
    }

    /**
     * Sets the value of the currentUses property.
     * 
     * @param value
     *     allowed object is
     *     {@link CollectedItemIntType }
     *     
     */
    public void setCurrentUses(CollectedItemIntType value) {
        this.currentUses = value;
    }

    /**
     * Gets the value of the localPath property.
     * 
     * @return
     *     possible object is
     *     {@link CollectedItemStringType }
     *     
     */
    public CollectedItemStringType getLocalPath() {
        return localPath;
    }

    /**
     * Sets the value of the localPath property.
     * 
     * @param value
     *     allowed object is
     *     {@link CollectedItemStringType }
     *     
     */
    public void setLocalPath(CollectedItemStringType value) {
        this.localPath = value;
    }

    /**
     * Gets the value of the accessReadPermission property.
     * 
     * @return
     *     possible object is
     *     {@link CollectedItemBoolType }
     *     
     */
    public CollectedItemBoolType getAccessReadPermission() {
        return accessReadPermission;
    }

    /**
     * Sets the value of the accessReadPermission property.
     * 
     * @param value
     *     allowed object is
     *     {@link CollectedItemBoolType }
     *     
     */
    public void setAccessReadPermission(CollectedItemBoolType value) {
        this.accessReadPermission = value;
    }

    /**
     * Gets the value of the accessWritePermission property.
     * 
     * @return
     *     possible object is
     *     {@link CollectedItemBoolType }
     *     
     */
    public CollectedItemBoolType getAccessWritePermission() {
        return accessWritePermission;
    }

    /**
     * Sets the value of the accessWritePermission property.
     * 
     * @param value
     *     allowed object is
     *     {@link CollectedItemBoolType }
     *     
     */
    public void setAccessWritePermission(CollectedItemBoolType value) {
        this.accessWritePermission = value;
    }

    /**
     * Gets the value of the accessCreatePermission property.
     * 
     * @return
     *     possible object is
     *     {@link CollectedItemBoolType }
     *     
     */
    public CollectedItemBoolType getAccessCreatePermission() {
        return accessCreatePermission;
    }

    /**
     * Sets the value of the accessCreatePermission property.
     * 
     * @param value
     *     allowed object is
     *     {@link CollectedItemBoolType }
     *     
     */
    public void setAccessCreatePermission(CollectedItemBoolType value) {
        this.accessCreatePermission = value;
    }

    /**
     * Gets the value of the accessExecPermission property.
     * 
     * @return
     *     possible object is
     *     {@link CollectedItemBoolType }
     *     
     */
    public CollectedItemBoolType getAccessExecPermission() {
        return accessExecPermission;
    }

    /**
     * Sets the value of the accessExecPermission property.
     * 
     * @param value
     *     allowed object is
     *     {@link CollectedItemBoolType }
     *     
     */
    public void setAccessExecPermission(CollectedItemBoolType value) {
        this.accessExecPermission = value;
    }

    /**
     * Gets the value of the accessDeletePermission property.
     * 
     * @return
     *     possible object is
     *     {@link CollectedItemBoolType }
     *     
     */
    public CollectedItemBoolType getAccessDeletePermission() {
        return accessDeletePermission;
    }

    /**
     * Sets the value of the accessDeletePermission property.
     * 
     * @param value
     *     allowed object is
     *     {@link CollectedItemBoolType }
     *     
     */
    public void setAccessDeletePermission(CollectedItemBoolType value) {
        this.accessDeletePermission = value;
    }

    /**
     * Gets the value of the accessAtribPermission property.
     * 
     * @return
     *     possible object is
     *     {@link CollectedItemBoolType }
     *     
     */
    public CollectedItemBoolType getAccessAtribPermission() {
        return accessAtribPermission;
    }

    /**
     * Sets the value of the accessAtribPermission property.
     * 
     * @param value
     *     allowed object is
     *     {@link CollectedItemBoolType }
     *     
     */
    public void setAccessAtribPermission(CollectedItemBoolType value) {
        this.accessAtribPermission = value;
    }

    /**
     * Gets the value of the accessPermPermission property.
     * 
     * @return
     *     possible object is
     *     {@link CollectedItemBoolType }
     *     
     */
    public CollectedItemBoolType getAccessPermPermission() {
        return accessPermPermission;
    }

    /**
     * Sets the value of the accessPermPermission property.
     * 
     * @param value
     *     allowed object is
     *     {@link CollectedItemBoolType }
     *     
     */
    public void setAccessPermPermission(CollectedItemBoolType value) {
        this.accessPermPermission = value;
    }

    /**
     * Gets the value of the accessAllPermission property.
     * 
     * @return
     *     possible object is
     *     {@link CollectedItemBoolType }
     *     
     */
    public CollectedItemBoolType getAccessAllPermission() {
        return accessAllPermission;
    }

    /**
     * Sets the value of the accessAllPermission property.
     * 
     * @param value
     *     allowed object is
     *     {@link CollectedItemBoolType }
     *     
     */
    public void setAccessAllPermission(CollectedItemBoolType value) {
        this.accessAllPermission = value;
    }

}
