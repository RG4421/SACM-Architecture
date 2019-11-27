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
import org.cisecurity.oval.collection.FilterIPAddressStringType;
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
 *         &lt;element name="local_address" type="{http://oval.cisecurity.org/XMLSchema/oval-collections-6}FilterIPAddressStringType" minOccurs="0"/>
 *         &lt;element name="local_port" type="{http://oval.cisecurity.org/XMLSchema/oval-collections-6}FilterIntType" minOccurs="0"/>
 *         &lt;element name="protocol" type="{http://oval.cisecurity.org/XMLSchema/oval-collections-6#windows}FilterProtocolType" minOccurs="0"/>
 *         &lt;element name="pid" type="{http://oval.cisecurity.org/XMLSchema/oval-collections-6}FilterIntType" minOccurs="0"/>
 *         &lt;element name="foreign_address" type="{http://oval.cisecurity.org/XMLSchema/oval-collections-6}FilterIPAddressStringType" minOccurs="0"/>
 *         &lt;element name="foreign_port" type="{http://oval.cisecurity.org/XMLSchema/oval-collections-6}FilterStringType" minOccurs="0"/>
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
    "localAddress",
    "localPort",
    "protocol",
    "pid",
    "foreignAddress",
    "foreignPort"
})
public class PortFilter
    extends FilterType
{

    @XmlElement(name = "local_address")
    protected FilterIPAddressStringType localAddress;
    @XmlElement(name = "local_port")
    protected FilterIntType localPort;
    protected FilterProtocolType protocol;
    protected FilterIntType pid;
    @XmlElement(name = "foreign_address")
    protected FilterIPAddressStringType foreignAddress;
    @XmlElement(name = "foreign_port")
    protected FilterStringType foreignPort;

    /**
     * Gets the value of the localAddress property.
     * 
     * @return
     *     possible object is
     *     {@link FilterIPAddressStringType }
     *     
     */
    public FilterIPAddressStringType getLocalAddress() {
        return localAddress;
    }

    /**
     * Sets the value of the localAddress property.
     * 
     * @param value
     *     allowed object is
     *     {@link FilterIPAddressStringType }
     *     
     */
    public void setLocalAddress(FilterIPAddressStringType value) {
        this.localAddress = value;
    }

    /**
     * Gets the value of the localPort property.
     * 
     * @return
     *     possible object is
     *     {@link FilterIntType }
     *     
     */
    public FilterIntType getLocalPort() {
        return localPort;
    }

    /**
     * Sets the value of the localPort property.
     * 
     * @param value
     *     allowed object is
     *     {@link FilterIntType }
     *     
     */
    public void setLocalPort(FilterIntType value) {
        this.localPort = value;
    }

    /**
     * Gets the value of the protocol property.
     * 
     * @return
     *     possible object is
     *     {@link FilterProtocolType }
     *     
     */
    public FilterProtocolType getProtocol() {
        return protocol;
    }

    /**
     * Sets the value of the protocol property.
     * 
     * @param value
     *     allowed object is
     *     {@link FilterProtocolType }
     *     
     */
    public void setProtocol(FilterProtocolType value) {
        this.protocol = value;
    }

    /**
     * Gets the value of the pid property.
     * 
     * @return
     *     possible object is
     *     {@link FilterIntType }
     *     
     */
    public FilterIntType getPid() {
        return pid;
    }

    /**
     * Sets the value of the pid property.
     * 
     * @param value
     *     allowed object is
     *     {@link FilterIntType }
     *     
     */
    public void setPid(FilterIntType value) {
        this.pid = value;
    }

    /**
     * Gets the value of the foreignAddress property.
     * 
     * @return
     *     possible object is
     *     {@link FilterIPAddressStringType }
     *     
     */
    public FilterIPAddressStringType getForeignAddress() {
        return foreignAddress;
    }

    /**
     * Sets the value of the foreignAddress property.
     * 
     * @param value
     *     allowed object is
     *     {@link FilterIPAddressStringType }
     *     
     */
    public void setForeignAddress(FilterIPAddressStringType value) {
        this.foreignAddress = value;
    }

    /**
     * Gets the value of the foreignPort property.
     * 
     * @return
     *     possible object is
     *     {@link FilterStringType }
     *     
     */
    public FilterStringType getForeignPort() {
        return foreignPort;
    }

    /**
     * Sets the value of the foreignPort property.
     * 
     * @param value
     *     allowed object is
     *     {@link FilterStringType }
     *     
     */
    public void setForeignPort(FilterStringType value) {
        this.foreignPort = value;
    }

}
