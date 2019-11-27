//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.8-b130911.1802 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2019.11.21 at 11:38:58 AM EST 
//


package org.cisecurity.oval.sc.win;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import org.cisecurity.oval.sc.CollectedItemIPAddressStringType;
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
 *         &lt;element name="domain_name" type="{http://oval.cisecurity.org/XMLSchema/oval-system-characteristics-6}CollectedItemStringType" minOccurs="0"/>
 *         &lt;element name="ttl" type="{http://oval.cisecurity.org/XMLSchema/oval-system-characteristics-6}CollectedItemIntType" minOccurs="0"/>
 *         &lt;element name="ip_address" type="{http://oval.cisecurity.org/XMLSchema/oval-system-characteristics-6}CollectedItemIPAddressStringType" maxOccurs="unbounded" minOccurs="0"/>
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
    "domainName",
    "ttl",
    "ipAddress"
})
public class DnscacheItem
    extends CollectedItemType
{

    @XmlElement(name = "domain_name")
    protected CollectedItemStringType domainName;
    protected CollectedItemIntType ttl;
    @XmlElement(name = "ip_address")
    protected List<CollectedItemIPAddressStringType> ipAddress;

    /**
     * Gets the value of the domainName property.
     * 
     * @return
     *     possible object is
     *     {@link CollectedItemStringType }
     *     
     */
    public CollectedItemStringType getDomainName() {
        return domainName;
    }

    /**
     * Sets the value of the domainName property.
     * 
     * @param value
     *     allowed object is
     *     {@link CollectedItemStringType }
     *     
     */
    public void setDomainName(CollectedItemStringType value) {
        this.domainName = value;
    }

    /**
     * Gets the value of the ttl property.
     * 
     * @return
     *     possible object is
     *     {@link CollectedItemIntType }
     *     
     */
    public CollectedItemIntType getTtl() {
        return ttl;
    }

    /**
     * Sets the value of the ttl property.
     * 
     * @param value
     *     allowed object is
     *     {@link CollectedItemIntType }
     *     
     */
    public void setTtl(CollectedItemIntType value) {
        this.ttl = value;
    }

    /**
     * Gets the value of the ipAddress property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the ipAddress property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getIpAddress().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link CollectedItemIPAddressStringType }
     * 
     * 
     */
    public List<CollectedItemIPAddressStringType> getIpAddress() {
        if (ipAddress == null) {
            ipAddress = new ArrayList<CollectedItemIPAddressStringType>();
        }
        return this.ipAddress;
    }

}
