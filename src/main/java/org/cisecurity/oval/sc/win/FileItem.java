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
import org.cisecurity.oval.sc.CollectedItemIntType;
import org.cisecurity.oval.sc.CollectedItemStringType;
import org.cisecurity.oval.sc.CollectedItemType;
import org.cisecurity.oval.sc.CollectedItemVersionType;


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
 *         &lt;element name="filepath" type="{http://oval.cisecurity.org/XMLSchema/oval-system-characteristics-6}CollectedItemStringType" minOccurs="0"/>
 *         &lt;element name="path" type="{http://oval.cisecurity.org/XMLSchema/oval-system-characteristics-6}CollectedItemStringType" minOccurs="0"/>
 *         &lt;element name="filename" type="{http://oval.cisecurity.org/XMLSchema/oval-system-characteristics-6}CollectedItemStringType" minOccurs="0"/>
 *         &lt;element name="owner" type="{http://oval.cisecurity.org/XMLSchema/oval-system-characteristics-6}CollectedItemStringType" minOccurs="0"/>
 *         &lt;element name="size" type="{http://oval.cisecurity.org/XMLSchema/oval-system-characteristics-6}CollectedItemIntType" minOccurs="0"/>
 *         &lt;element name="a_time" type="{http://oval.cisecurity.org/XMLSchema/oval-system-characteristics-6}CollectedItemIntType" minOccurs="0"/>
 *         &lt;element name="c_time" type="{http://oval.cisecurity.org/XMLSchema/oval-system-characteristics-6}CollectedItemIntType" minOccurs="0"/>
 *         &lt;element name="m_time" type="{http://oval.cisecurity.org/XMLSchema/oval-system-characteristics-6}CollectedItemIntType" minOccurs="0"/>
 *         &lt;element name="ms_checksum" type="{http://oval.cisecurity.org/XMLSchema/oval-system-characteristics-6}CollectedItemStringType" minOccurs="0"/>
 *         &lt;element name="version" type="{http://oval.cisecurity.org/XMLSchema/oval-system-characteristics-6}CollectedItemVersionType" minOccurs="0"/>
 *         &lt;element name="type" type="{http://oval.cisecurity.org/XMLSchema/oval-system-characteristics-6#windows}EntityItemFileTypeType" minOccurs="0"/>
 *         &lt;element name="attribute" type="{http://oval.cisecurity.org/XMLSchema/oval-system-characteristics-6#windows}EntityItemFileAttributeType" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="development_class" type="{http://oval.cisecurity.org/XMLSchema/oval-system-characteristics-6}CollectedItemStringType" minOccurs="0"/>
 *         &lt;element name="company" type="{http://oval.cisecurity.org/XMLSchema/oval-system-characteristics-6}CollectedItemStringType" minOccurs="0"/>
 *         &lt;element name="internal_name" type="{http://oval.cisecurity.org/XMLSchema/oval-system-characteristics-6}CollectedItemStringType" minOccurs="0"/>
 *         &lt;element name="language" type="{http://oval.cisecurity.org/XMLSchema/oval-system-characteristics-6}CollectedItemStringType" minOccurs="0"/>
 *         &lt;element name="original_filename" type="{http://oval.cisecurity.org/XMLSchema/oval-system-characteristics-6}CollectedItemStringType" minOccurs="0"/>
 *         &lt;element name="product_name" type="{http://oval.cisecurity.org/XMLSchema/oval-system-characteristics-6}CollectedItemStringType" minOccurs="0"/>
 *         &lt;element name="product_version" minOccurs="0">
 *           &lt;complexType>
 *             &lt;simpleContent>
 *               &lt;restriction base="&lt;http://oval.cisecurity.org/XMLSchema/oval-system-characteristics-6>CollectedItemAnySimpleType">
 *                 &lt;attribute name="datatype" default="version">
 *                   &lt;simpleType>
 *                     &lt;restriction base="{http://oval.cisecurity.org/XMLSchema/oval-common-6}SimpleDatatypeEnumeration">
 *                       &lt;enumeration value="version"/>
 *                       &lt;enumeration value="string"/>
 *                     &lt;/restriction>
 *                   &lt;/simpleType>
 *                 &lt;/attribute>
 *               &lt;/restriction>
 *             &lt;/simpleContent>
 *           &lt;/complexType>
 *         &lt;/element>
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
    "filepath",
    "path",
    "filename",
    "owner",
    "size",
    "aTime",
    "cTime",
    "mTime",
    "msChecksum",
    "version",
    "type",
    "attribute",
    "developmentClass",
    "company",
    "internalName",
    "language",
    "originalFilename",
    "productName",
    "productVersion",
    "windowsView"
})
public class FileItem
    extends CollectedItemType
{

    protected CollectedItemStringType filepath;
    protected CollectedItemStringType path;
    @XmlElementRef(name = "filename", namespace = "http://oval.cisecurity.org/XMLSchema/oval-system-characteristics-6#windows", type = JAXBElement.class, required = false)
    protected JAXBElement<CollectedItemStringType> filename;
    protected CollectedItemStringType owner;
    protected CollectedItemIntType size;
    @XmlElement(name = "a_time")
    protected CollectedItemIntType aTime;
    @XmlElement(name = "c_time")
    protected CollectedItemIntType cTime;
    @XmlElement(name = "m_time")
    protected CollectedItemIntType mTime;
    @XmlElement(name = "ms_checksum")
    protected CollectedItemStringType msChecksum;
    protected CollectedItemVersionType version;
    protected EntityItemFileTypeType type;
    protected List<EntityItemFileAttributeType> attribute;
    @XmlElement(name = "development_class")
    protected CollectedItemStringType developmentClass;
    protected CollectedItemStringType company;
    @XmlElement(name = "internal_name")
    protected CollectedItemStringType internalName;
    protected CollectedItemStringType language;
    @XmlElement(name = "original_filename")
    protected CollectedItemStringType originalFilename;
    @XmlElement(name = "product_name")
    protected CollectedItemStringType productName;
    @XmlElement(name = "product_version")
    protected FileItem.ProductVersion productVersion;
    @XmlElement(name = "windows_view")
    protected EntityItemWindowsViewType windowsView;

    /**
     * Gets the value of the filepath property.
     * 
     * @return
     *     possible object is
     *     {@link CollectedItemStringType }
     *     
     */
    public CollectedItemStringType getFilepath() {
        return filepath;
    }

    /**
     * Sets the value of the filepath property.
     * 
     * @param value
     *     allowed object is
     *     {@link CollectedItemStringType }
     *     
     */
    public void setFilepath(CollectedItemStringType value) {
        this.filepath = value;
    }

    /**
     * Gets the value of the path property.
     * 
     * @return
     *     possible object is
     *     {@link CollectedItemStringType }
     *     
     */
    public CollectedItemStringType getPath() {
        return path;
    }

    /**
     * Sets the value of the path property.
     * 
     * @param value
     *     allowed object is
     *     {@link CollectedItemStringType }
     *     
     */
    public void setPath(CollectedItemStringType value) {
        this.path = value;
    }

    /**
     * Gets the value of the filename property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link CollectedItemStringType }{@code >}
     *     
     */
    public JAXBElement<CollectedItemStringType> getFilename() {
        return filename;
    }

    /**
     * Sets the value of the filename property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link CollectedItemStringType }{@code >}
     *     
     */
    public void setFilename(JAXBElement<CollectedItemStringType> value) {
        this.filename = value;
    }

    /**
     * Gets the value of the owner property.
     * 
     * @return
     *     possible object is
     *     {@link CollectedItemStringType }
     *     
     */
    public CollectedItemStringType getOwner() {
        return owner;
    }

    /**
     * Sets the value of the owner property.
     * 
     * @param value
     *     allowed object is
     *     {@link CollectedItemStringType }
     *     
     */
    public void setOwner(CollectedItemStringType value) {
        this.owner = value;
    }

    /**
     * Gets the value of the size property.
     * 
     * @return
     *     possible object is
     *     {@link CollectedItemIntType }
     *     
     */
    public CollectedItemIntType getSize() {
        return size;
    }

    /**
     * Sets the value of the size property.
     * 
     * @param value
     *     allowed object is
     *     {@link CollectedItemIntType }
     *     
     */
    public void setSize(CollectedItemIntType value) {
        this.size = value;
    }

    /**
     * Gets the value of the aTime property.
     * 
     * @return
     *     possible object is
     *     {@link CollectedItemIntType }
     *     
     */
    public CollectedItemIntType getATime() {
        return aTime;
    }

    /**
     * Sets the value of the aTime property.
     * 
     * @param value
     *     allowed object is
     *     {@link CollectedItemIntType }
     *     
     */
    public void setATime(CollectedItemIntType value) {
        this.aTime = value;
    }

    /**
     * Gets the value of the cTime property.
     * 
     * @return
     *     possible object is
     *     {@link CollectedItemIntType }
     *     
     */
    public CollectedItemIntType getCTime() {
        return cTime;
    }

    /**
     * Sets the value of the cTime property.
     * 
     * @param value
     *     allowed object is
     *     {@link CollectedItemIntType }
     *     
     */
    public void setCTime(CollectedItemIntType value) {
        this.cTime = value;
    }

    /**
     * Gets the value of the mTime property.
     * 
     * @return
     *     possible object is
     *     {@link CollectedItemIntType }
     *     
     */
    public CollectedItemIntType getMTime() {
        return mTime;
    }

    /**
     * Sets the value of the mTime property.
     * 
     * @param value
     *     allowed object is
     *     {@link CollectedItemIntType }
     *     
     */
    public void setMTime(CollectedItemIntType value) {
        this.mTime = value;
    }

    /**
     * Gets the value of the msChecksum property.
     * 
     * @return
     *     possible object is
     *     {@link CollectedItemStringType }
     *     
     */
    public CollectedItemStringType getMsChecksum() {
        return msChecksum;
    }

    /**
     * Sets the value of the msChecksum property.
     * 
     * @param value
     *     allowed object is
     *     {@link CollectedItemStringType }
     *     
     */
    public void setMsChecksum(CollectedItemStringType value) {
        this.msChecksum = value;
    }

    /**
     * Gets the value of the version property.
     * 
     * @return
     *     possible object is
     *     {@link CollectedItemVersionType }
     *     
     */
    public CollectedItemVersionType getVersion() {
        return version;
    }

    /**
     * Sets the value of the version property.
     * 
     * @param value
     *     allowed object is
     *     {@link CollectedItemVersionType }
     *     
     */
    public void setVersion(CollectedItemVersionType value) {
        this.version = value;
    }

    /**
     * Gets the value of the type property.
     * 
     * @return
     *     possible object is
     *     {@link EntityItemFileTypeType }
     *     
     */
    public EntityItemFileTypeType getType() {
        return type;
    }

    /**
     * Sets the value of the type property.
     * 
     * @param value
     *     allowed object is
     *     {@link EntityItemFileTypeType }
     *     
     */
    public void setType(EntityItemFileTypeType value) {
        this.type = value;
    }

    /**
     * Gets the value of the attribute property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the attribute property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getAttribute().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link EntityItemFileAttributeType }
     * 
     * 
     */
    public List<EntityItemFileAttributeType> getAttribute() {
        if (attribute == null) {
            attribute = new ArrayList<EntityItemFileAttributeType>();
        }
        return this.attribute;
    }

    /**
     * Gets the value of the developmentClass property.
     * 
     * @return
     *     possible object is
     *     {@link CollectedItemStringType }
     *     
     */
    public CollectedItemStringType getDevelopmentClass() {
        return developmentClass;
    }

    /**
     * Sets the value of the developmentClass property.
     * 
     * @param value
     *     allowed object is
     *     {@link CollectedItemStringType }
     *     
     */
    public void setDevelopmentClass(CollectedItemStringType value) {
        this.developmentClass = value;
    }

    /**
     * Gets the value of the company property.
     * 
     * @return
     *     possible object is
     *     {@link CollectedItemStringType }
     *     
     */
    public CollectedItemStringType getCompany() {
        return company;
    }

    /**
     * Sets the value of the company property.
     * 
     * @param value
     *     allowed object is
     *     {@link CollectedItemStringType }
     *     
     */
    public void setCompany(CollectedItemStringType value) {
        this.company = value;
    }

    /**
     * Gets the value of the internalName property.
     * 
     * @return
     *     possible object is
     *     {@link CollectedItemStringType }
     *     
     */
    public CollectedItemStringType getInternalName() {
        return internalName;
    }

    /**
     * Sets the value of the internalName property.
     * 
     * @param value
     *     allowed object is
     *     {@link CollectedItemStringType }
     *     
     */
    public void setInternalName(CollectedItemStringType value) {
        this.internalName = value;
    }

    /**
     * Gets the value of the language property.
     * 
     * @return
     *     possible object is
     *     {@link CollectedItemStringType }
     *     
     */
    public CollectedItemStringType getLanguage() {
        return language;
    }

    /**
     * Sets the value of the language property.
     * 
     * @param value
     *     allowed object is
     *     {@link CollectedItemStringType }
     *     
     */
    public void setLanguage(CollectedItemStringType value) {
        this.language = value;
    }

    /**
     * Gets the value of the originalFilename property.
     * 
     * @return
     *     possible object is
     *     {@link CollectedItemStringType }
     *     
     */
    public CollectedItemStringType getOriginalFilename() {
        return originalFilename;
    }

    /**
     * Sets the value of the originalFilename property.
     * 
     * @param value
     *     allowed object is
     *     {@link CollectedItemStringType }
     *     
     */
    public void setOriginalFilename(CollectedItemStringType value) {
        this.originalFilename = value;
    }

    /**
     * Gets the value of the productName property.
     * 
     * @return
     *     possible object is
     *     {@link CollectedItemStringType }
     *     
     */
    public CollectedItemStringType getProductName() {
        return productName;
    }

    /**
     * Sets the value of the productName property.
     * 
     * @param value
     *     allowed object is
     *     {@link CollectedItemStringType }
     *     
     */
    public void setProductName(CollectedItemStringType value) {
        this.productName = value;
    }

    /**
     * Gets the value of the productVersion property.
     * 
     * @return
     *     possible object is
     *     {@link FileItem.ProductVersion }
     *     
     */
    public FileItem.ProductVersion getProductVersion() {
        return productVersion;
    }

    /**
     * Sets the value of the productVersion property.
     * 
     * @param value
     *     allowed object is
     *     {@link FileItem.ProductVersion }
     *     
     */
    public void setProductVersion(FileItem.ProductVersion value) {
        this.productVersion = value;
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


    /**
     * <p>Java class for anonymous complex type.
     * 
     * <p>The following schema fragment specifies the expected content contained within this class.
     * 
     * <pre>
     * &lt;complexType>
     *   &lt;simpleContent>
     *     &lt;restriction base="&lt;http://oval.cisecurity.org/XMLSchema/oval-system-characteristics-6>CollectedItemAnySimpleType">
     *       &lt;attribute name="datatype" default="version">
     *         &lt;simpleType>
     *           &lt;restriction base="{http://oval.cisecurity.org/XMLSchema/oval-common-6}SimpleDatatypeEnumeration">
     *             &lt;enumeration value="version"/>
     *             &lt;enumeration value="string"/>
     *           &lt;/restriction>
     *         &lt;/simpleType>
     *       &lt;/attribute>
     *     &lt;/restriction>
     *   &lt;/simpleContent>
     * &lt;/complexType>
     * </pre>
     * 
     * 
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "")
    public static class ProductVersion
        extends CollectedItemAnySimpleType
    {


    }

}
