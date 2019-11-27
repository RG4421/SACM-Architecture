//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.8-b130911.1802 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2019.11.21 at 11:38:58 AM EST 
//


package org.cisecurity.oval.sc.win;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlType;
import org.cisecurity.oval.sc.CollectedItemBoolType;
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
 *         &lt;element name="hive" type="{http://oval.cisecurity.org/XMLSchema/oval-system-characteristics-6#windows}EntityItemRegistryHiveType" minOccurs="0"/>
 *         &lt;element name="key" type="{http://oval.cisecurity.org/XMLSchema/oval-system-characteristics-6}CollectedItemStringType" minOccurs="0"/>
 *         &lt;element name="trustee_sid" type="{http://oval.cisecurity.org/XMLSchema/oval-system-characteristics-6}CollectedItemStringType" minOccurs="0"/>
 *         &lt;element name="trustee_name" type="{http://oval.cisecurity.org/XMLSchema/oval-system-characteristics-6}CollectedItemStringType" minOccurs="0"/>
 *         &lt;element name="standard_delete" type="{http://oval.cisecurity.org/XMLSchema/oval-system-characteristics-6}CollectedItemBoolType" minOccurs="0"/>
 *         &lt;element name="standard_read_control" type="{http://oval.cisecurity.org/XMLSchema/oval-system-characteristics-6}CollectedItemBoolType" minOccurs="0"/>
 *         &lt;element name="standard_write_dac" type="{http://oval.cisecurity.org/XMLSchema/oval-system-characteristics-6}CollectedItemBoolType" minOccurs="0"/>
 *         &lt;element name="standard_write_owner" type="{http://oval.cisecurity.org/XMLSchema/oval-system-characteristics-6}CollectedItemBoolType" minOccurs="0"/>
 *         &lt;element name="standard_synchronize" type="{http://oval.cisecurity.org/XMLSchema/oval-system-characteristics-6}CollectedItemBoolType" minOccurs="0"/>
 *         &lt;element name="access_system_security" type="{http://oval.cisecurity.org/XMLSchema/oval-system-characteristics-6}CollectedItemBoolType" minOccurs="0"/>
 *         &lt;element name="generic_read" type="{http://oval.cisecurity.org/XMLSchema/oval-system-characteristics-6}CollectedItemBoolType" minOccurs="0"/>
 *         &lt;element name="generic_write" type="{http://oval.cisecurity.org/XMLSchema/oval-system-characteristics-6}CollectedItemBoolType" minOccurs="0"/>
 *         &lt;element name="generic_execute" type="{http://oval.cisecurity.org/XMLSchema/oval-system-characteristics-6}CollectedItemBoolType" minOccurs="0"/>
 *         &lt;element name="generic_all" type="{http://oval.cisecurity.org/XMLSchema/oval-system-characteristics-6}CollectedItemBoolType" minOccurs="0"/>
 *         &lt;element name="key_query_value" type="{http://oval.cisecurity.org/XMLSchema/oval-system-characteristics-6}CollectedItemBoolType" minOccurs="0"/>
 *         &lt;element name="key_set_value" type="{http://oval.cisecurity.org/XMLSchema/oval-system-characteristics-6}CollectedItemBoolType" minOccurs="0"/>
 *         &lt;element name="key_create_sub_key" type="{http://oval.cisecurity.org/XMLSchema/oval-system-characteristics-6}CollectedItemBoolType" minOccurs="0"/>
 *         &lt;element name="key_enumerate_sub_keys" type="{http://oval.cisecurity.org/XMLSchema/oval-system-characteristics-6}CollectedItemBoolType" minOccurs="0"/>
 *         &lt;element name="key_notify" type="{http://oval.cisecurity.org/XMLSchema/oval-system-characteristics-6}CollectedItemBoolType" minOccurs="0"/>
 *         &lt;element name="key_create_link" type="{http://oval.cisecurity.org/XMLSchema/oval-system-characteristics-6}CollectedItemBoolType" minOccurs="0"/>
 *         &lt;element name="key_wow64_64key" type="{http://oval.cisecurity.org/XMLSchema/oval-system-characteristics-6}CollectedItemBoolType" minOccurs="0"/>
 *         &lt;element name="key_wow64_32key" type="{http://oval.cisecurity.org/XMLSchema/oval-system-characteristics-6}CollectedItemBoolType" minOccurs="0"/>
 *         &lt;element name="key_wow64_res" type="{http://oval.cisecurity.org/XMLSchema/oval-system-characteristics-6}CollectedItemBoolType" minOccurs="0"/>
 *         &lt;element name="windows_view" type="{http://oval.cisecurity.org/XMLSchema/oval-system-characteristics-6#windows}EntityItemWindowsViewType" minOccurs="0"/>
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
    "hive",
    "key",
    "trusteeSid",
    "trusteeName",
    "standardDelete",
    "standardReadControl",
    "standardWriteDac",
    "standardWriteOwner",
    "standardSynchronize",
    "accessSystemSecurity",
    "genericRead",
    "genericWrite",
    "genericExecute",
    "genericAll",
    "keyQueryValue",
    "keySetValue",
    "keyCreateSubKey",
    "keyEnumerateSubKeys",
    "keyNotify",
    "keyCreateLink",
    "keyWow6464Key",
    "keyWow6432Key",
    "keyWow64Res",
    "windowsView"
})
public class RegkeyeffectiverightsItem
    extends CollectedItemType
{

    protected EntityItemRegistryHiveType hive;
    @XmlElementRef(name = "key", namespace = "http://oval.cisecurity.org/XMLSchema/oval-system-characteristics-6#windows", type = JAXBElement.class, required = false)
    protected JAXBElement<CollectedItemStringType> key;
    @XmlElement(name = "trustee_sid")
    protected CollectedItemStringType trusteeSid;
    @XmlElement(name = "trustee_name")
    protected CollectedItemStringType trusteeName;
    @XmlElement(name = "standard_delete")
    protected CollectedItemBoolType standardDelete;
    @XmlElement(name = "standard_read_control")
    protected CollectedItemBoolType standardReadControl;
    @XmlElement(name = "standard_write_dac")
    protected CollectedItemBoolType standardWriteDac;
    @XmlElement(name = "standard_write_owner")
    protected CollectedItemBoolType standardWriteOwner;
    @XmlElement(name = "standard_synchronize")
    protected CollectedItemBoolType standardSynchronize;
    @XmlElement(name = "access_system_security")
    protected CollectedItemBoolType accessSystemSecurity;
    @XmlElement(name = "generic_read")
    protected CollectedItemBoolType genericRead;
    @XmlElement(name = "generic_write")
    protected CollectedItemBoolType genericWrite;
    @XmlElement(name = "generic_execute")
    protected CollectedItemBoolType genericExecute;
    @XmlElement(name = "generic_all")
    protected CollectedItemBoolType genericAll;
    @XmlElement(name = "key_query_value")
    protected CollectedItemBoolType keyQueryValue;
    @XmlElement(name = "key_set_value")
    protected CollectedItemBoolType keySetValue;
    @XmlElement(name = "key_create_sub_key")
    protected CollectedItemBoolType keyCreateSubKey;
    @XmlElement(name = "key_enumerate_sub_keys")
    protected CollectedItemBoolType keyEnumerateSubKeys;
    @XmlElement(name = "key_notify")
    protected CollectedItemBoolType keyNotify;
    @XmlElement(name = "key_create_link")
    protected CollectedItemBoolType keyCreateLink;
    @XmlElement(name = "key_wow64_64key")
    protected CollectedItemBoolType keyWow6464Key;
    @XmlElement(name = "key_wow64_32key")
    protected CollectedItemBoolType keyWow6432Key;
    @XmlElement(name = "key_wow64_res")
    protected CollectedItemBoolType keyWow64Res;
    @XmlElement(name = "windows_view")
    protected EntityItemWindowsViewType windowsView;

    /**
     * Gets the value of the hive property.
     * 
     * @return
     *     possible object is
     *     {@link EntityItemRegistryHiveType }
     *     
     */
    public EntityItemRegistryHiveType getHive() {
        return hive;
    }

    /**
     * Sets the value of the hive property.
     * 
     * @param value
     *     allowed object is
     *     {@link EntityItemRegistryHiveType }
     *     
     */
    public void setHive(EntityItemRegistryHiveType value) {
        this.hive = value;
    }

    /**
     * Gets the value of the key property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link CollectedItemStringType }{@code >}
     *     
     */
    public JAXBElement<CollectedItemStringType> getKey() {
        return key;
    }

    /**
     * Sets the value of the key property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link CollectedItemStringType }{@code >}
     *     
     */
    public void setKey(JAXBElement<CollectedItemStringType> value) {
        this.key = value;
    }

    /**
     * Gets the value of the trusteeSid property.
     * 
     * @return
     *     possible object is
     *     {@link CollectedItemStringType }
     *     
     */
    public CollectedItemStringType getTrusteeSid() {
        return trusteeSid;
    }

    /**
     * Sets the value of the trusteeSid property.
     * 
     * @param value
     *     allowed object is
     *     {@link CollectedItemStringType }
     *     
     */
    public void setTrusteeSid(CollectedItemStringType value) {
        this.trusteeSid = value;
    }

    /**
     * Gets the value of the trusteeName property.
     * 
     * @return
     *     possible object is
     *     {@link CollectedItemStringType }
     *     
     */
    public CollectedItemStringType getTrusteeName() {
        return trusteeName;
    }

    /**
     * Sets the value of the trusteeName property.
     * 
     * @param value
     *     allowed object is
     *     {@link CollectedItemStringType }
     *     
     */
    public void setTrusteeName(CollectedItemStringType value) {
        this.trusteeName = value;
    }

    /**
     * Gets the value of the standardDelete property.
     * 
     * @return
     *     possible object is
     *     {@link CollectedItemBoolType }
     *     
     */
    public CollectedItemBoolType getStandardDelete() {
        return standardDelete;
    }

    /**
     * Sets the value of the standardDelete property.
     * 
     * @param value
     *     allowed object is
     *     {@link CollectedItemBoolType }
     *     
     */
    public void setStandardDelete(CollectedItemBoolType value) {
        this.standardDelete = value;
    }

    /**
     * Gets the value of the standardReadControl property.
     * 
     * @return
     *     possible object is
     *     {@link CollectedItemBoolType }
     *     
     */
    public CollectedItemBoolType getStandardReadControl() {
        return standardReadControl;
    }

    /**
     * Sets the value of the standardReadControl property.
     * 
     * @param value
     *     allowed object is
     *     {@link CollectedItemBoolType }
     *     
     */
    public void setStandardReadControl(CollectedItemBoolType value) {
        this.standardReadControl = value;
    }

    /**
     * Gets the value of the standardWriteDac property.
     * 
     * @return
     *     possible object is
     *     {@link CollectedItemBoolType }
     *     
     */
    public CollectedItemBoolType getStandardWriteDac() {
        return standardWriteDac;
    }

    /**
     * Sets the value of the standardWriteDac property.
     * 
     * @param value
     *     allowed object is
     *     {@link CollectedItemBoolType }
     *     
     */
    public void setStandardWriteDac(CollectedItemBoolType value) {
        this.standardWriteDac = value;
    }

    /**
     * Gets the value of the standardWriteOwner property.
     * 
     * @return
     *     possible object is
     *     {@link CollectedItemBoolType }
     *     
     */
    public CollectedItemBoolType getStandardWriteOwner() {
        return standardWriteOwner;
    }

    /**
     * Sets the value of the standardWriteOwner property.
     * 
     * @param value
     *     allowed object is
     *     {@link CollectedItemBoolType }
     *     
     */
    public void setStandardWriteOwner(CollectedItemBoolType value) {
        this.standardWriteOwner = value;
    }

    /**
     * Gets the value of the standardSynchronize property.
     * 
     * @return
     *     possible object is
     *     {@link CollectedItemBoolType }
     *     
     */
    public CollectedItemBoolType getStandardSynchronize() {
        return standardSynchronize;
    }

    /**
     * Sets the value of the standardSynchronize property.
     * 
     * @param value
     *     allowed object is
     *     {@link CollectedItemBoolType }
     *     
     */
    public void setStandardSynchronize(CollectedItemBoolType value) {
        this.standardSynchronize = value;
    }

    /**
     * Gets the value of the accessSystemSecurity property.
     * 
     * @return
     *     possible object is
     *     {@link CollectedItemBoolType }
     *     
     */
    public CollectedItemBoolType getAccessSystemSecurity() {
        return accessSystemSecurity;
    }

    /**
     * Sets the value of the accessSystemSecurity property.
     * 
     * @param value
     *     allowed object is
     *     {@link CollectedItemBoolType }
     *     
     */
    public void setAccessSystemSecurity(CollectedItemBoolType value) {
        this.accessSystemSecurity = value;
    }

    /**
     * Gets the value of the genericRead property.
     * 
     * @return
     *     possible object is
     *     {@link CollectedItemBoolType }
     *     
     */
    public CollectedItemBoolType getGenericRead() {
        return genericRead;
    }

    /**
     * Sets the value of the genericRead property.
     * 
     * @param value
     *     allowed object is
     *     {@link CollectedItemBoolType }
     *     
     */
    public void setGenericRead(CollectedItemBoolType value) {
        this.genericRead = value;
    }

    /**
     * Gets the value of the genericWrite property.
     * 
     * @return
     *     possible object is
     *     {@link CollectedItemBoolType }
     *     
     */
    public CollectedItemBoolType getGenericWrite() {
        return genericWrite;
    }

    /**
     * Sets the value of the genericWrite property.
     * 
     * @param value
     *     allowed object is
     *     {@link CollectedItemBoolType }
     *     
     */
    public void setGenericWrite(CollectedItemBoolType value) {
        this.genericWrite = value;
    }

    /**
     * Gets the value of the genericExecute property.
     * 
     * @return
     *     possible object is
     *     {@link CollectedItemBoolType }
     *     
     */
    public CollectedItemBoolType getGenericExecute() {
        return genericExecute;
    }

    /**
     * Sets the value of the genericExecute property.
     * 
     * @param value
     *     allowed object is
     *     {@link CollectedItemBoolType }
     *     
     */
    public void setGenericExecute(CollectedItemBoolType value) {
        this.genericExecute = value;
    }

    /**
     * Gets the value of the genericAll property.
     * 
     * @return
     *     possible object is
     *     {@link CollectedItemBoolType }
     *     
     */
    public CollectedItemBoolType getGenericAll() {
        return genericAll;
    }

    /**
     * Sets the value of the genericAll property.
     * 
     * @param value
     *     allowed object is
     *     {@link CollectedItemBoolType }
     *     
     */
    public void setGenericAll(CollectedItemBoolType value) {
        this.genericAll = value;
    }

    /**
     * Gets the value of the keyQueryValue property.
     * 
     * @return
     *     possible object is
     *     {@link CollectedItemBoolType }
     *     
     */
    public CollectedItemBoolType getKeyQueryValue() {
        return keyQueryValue;
    }

    /**
     * Sets the value of the keyQueryValue property.
     * 
     * @param value
     *     allowed object is
     *     {@link CollectedItemBoolType }
     *     
     */
    public void setKeyQueryValue(CollectedItemBoolType value) {
        this.keyQueryValue = value;
    }

    /**
     * Gets the value of the keySetValue property.
     * 
     * @return
     *     possible object is
     *     {@link CollectedItemBoolType }
     *     
     */
    public CollectedItemBoolType getKeySetValue() {
        return keySetValue;
    }

    /**
     * Sets the value of the keySetValue property.
     * 
     * @param value
     *     allowed object is
     *     {@link CollectedItemBoolType }
     *     
     */
    public void setKeySetValue(CollectedItemBoolType value) {
        this.keySetValue = value;
    }

    /**
     * Gets the value of the keyCreateSubKey property.
     * 
     * @return
     *     possible object is
     *     {@link CollectedItemBoolType }
     *     
     */
    public CollectedItemBoolType getKeyCreateSubKey() {
        return keyCreateSubKey;
    }

    /**
     * Sets the value of the keyCreateSubKey property.
     * 
     * @param value
     *     allowed object is
     *     {@link CollectedItemBoolType }
     *     
     */
    public void setKeyCreateSubKey(CollectedItemBoolType value) {
        this.keyCreateSubKey = value;
    }

    /**
     * Gets the value of the keyEnumerateSubKeys property.
     * 
     * @return
     *     possible object is
     *     {@link CollectedItemBoolType }
     *     
     */
    public CollectedItemBoolType getKeyEnumerateSubKeys() {
        return keyEnumerateSubKeys;
    }

    /**
     * Sets the value of the keyEnumerateSubKeys property.
     * 
     * @param value
     *     allowed object is
     *     {@link CollectedItemBoolType }
     *     
     */
    public void setKeyEnumerateSubKeys(CollectedItemBoolType value) {
        this.keyEnumerateSubKeys = value;
    }

    /**
     * Gets the value of the keyNotify property.
     * 
     * @return
     *     possible object is
     *     {@link CollectedItemBoolType }
     *     
     */
    public CollectedItemBoolType getKeyNotify() {
        return keyNotify;
    }

    /**
     * Sets the value of the keyNotify property.
     * 
     * @param value
     *     allowed object is
     *     {@link CollectedItemBoolType }
     *     
     */
    public void setKeyNotify(CollectedItemBoolType value) {
        this.keyNotify = value;
    }

    /**
     * Gets the value of the keyCreateLink property.
     * 
     * @return
     *     possible object is
     *     {@link CollectedItemBoolType }
     *     
     */
    public CollectedItemBoolType getKeyCreateLink() {
        return keyCreateLink;
    }

    /**
     * Sets the value of the keyCreateLink property.
     * 
     * @param value
     *     allowed object is
     *     {@link CollectedItemBoolType }
     *     
     */
    public void setKeyCreateLink(CollectedItemBoolType value) {
        this.keyCreateLink = value;
    }

    /**
     * Gets the value of the keyWow6464Key property.
     * 
     * @return
     *     possible object is
     *     {@link CollectedItemBoolType }
     *     
     */
    public CollectedItemBoolType getKeyWow6464Key() {
        return keyWow6464Key;
    }

    /**
     * Sets the value of the keyWow6464Key property.
     * 
     * @param value
     *     allowed object is
     *     {@link CollectedItemBoolType }
     *     
     */
    public void setKeyWow6464Key(CollectedItemBoolType value) {
        this.keyWow6464Key = value;
    }

    /**
     * Gets the value of the keyWow6432Key property.
     * 
     * @return
     *     possible object is
     *     {@link CollectedItemBoolType }
     *     
     */
    public CollectedItemBoolType getKeyWow6432Key() {
        return keyWow6432Key;
    }

    /**
     * Sets the value of the keyWow6432Key property.
     * 
     * @param value
     *     allowed object is
     *     {@link CollectedItemBoolType }
     *     
     */
    public void setKeyWow6432Key(CollectedItemBoolType value) {
        this.keyWow6432Key = value;
    }

    /**
     * Gets the value of the keyWow64Res property.
     * 
     * @return
     *     possible object is
     *     {@link CollectedItemBoolType }
     *     
     */
    public CollectedItemBoolType getKeyWow64Res() {
        return keyWow64Res;
    }

    /**
     * Sets the value of the keyWow64Res property.
     * 
     * @param value
     *     allowed object is
     *     {@link CollectedItemBoolType }
     *     
     */
    public void setKeyWow64Res(CollectedItemBoolType value) {
        this.keyWow64Res = value;
    }

    /**
     * Gets the value of the windowsView property.
     * 
     * @return
     *     possible object is
     *     {@link EntityItemWindowsViewType }
     *     
     */
    public EntityItemWindowsViewType getWindowsView() {
        return windowsView;
    }

    /**
     * Sets the value of the windowsView property.
     * 
     * @param value
     *     allowed object is
     *     {@link EntityItemWindowsViewType }
     *     
     */
    public void setWindowsView(EntityItemWindowsViewType value) {
        this.windowsView = value;
    }

}
