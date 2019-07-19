//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.8-b130911.1802 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2019.02.08 at 01:36:11 PM EST 
//


package org.cisecurity.xmpp.extensions.collection.sacm.model;

import javax.xml.bind.annotation.*;
import java.math.BigInteger;


/**
 * <p>Java class for anonymous complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element ref="{http://cisecurity.org/sacm/sacm-collection}collection-type"/>
 *         &lt;element ref="{http://cisecurity.org/sacm/sacm-collection}collection-behaviors" minOccurs="0"/>
 *         &lt;choice>
 *           &lt;element ref="{http://cisecurity.org/sacm/sacm-collection}set"/>
 *           &lt;element ref="{http://cisecurity.org/sacm/sacm-collection}collection-fields"/>
 *         &lt;/choice>
 *         &lt;element ref="{http://cisecurity.org/sacm/sacm-collection}collection-filters" minOccurs="0"/>
 *       &lt;/sequence>
 *       &lt;attribute name="collection-id" use="required" type="{http://cisecurity.org/sacm/sacm-collection}CollectionIDPattern" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "collectionType",
    "collectionBehaviors",
    "set",
    "collectionFields",
    "collectionFilters"
})
@XmlRootElement(name = "collection", namespace = Collections.NAMESPACE)
public class Collection {

    @XmlElement(name = "collection-type", required = true)
    protected CollectionType collectionType;
    @XmlElement(name = "collection-behaviors")
    protected CollectionBehaviors collectionBehaviors;
    protected Set set;
    @XmlElement(name = "collection-fields")
    protected CollectionFields collectionFields;
    @XmlElement(name = "collection-filters")
    protected CollectionFilters collectionFilters;
    @XmlAttribute(name = "collection-id", required = true)
    protected BigInteger collectionId;

    /**
     * Gets the value of the collectionType property.
     * 
     * @return
     *     possible object is
     *     {@link CollectionType }
     *     
     */
    public CollectionType getCollectionType() {
        return collectionType;
    }

    /**
     * Sets the value of the collectionType property.
     * 
     * @param value
     *     allowed object is
     *     {@link CollectionType }
     *     
     */
    public void setCollectionType(CollectionType value) {
        this.collectionType = value;
    }

    /**
     * Gets the value of the collectionBehaviors property.
     * 
     * @return
     *     possible object is
     *     {@link CollectionBehaviors }
     *     
     */
    public CollectionBehaviors getCollectionBehaviors() {
        return collectionBehaviors;
    }

    /**
     * Sets the value of the collectionBehaviors property.
     * 
     * @param value
     *     allowed object is
     *     {@link CollectionBehaviors }
     *     
     */
    public void setCollectionBehaviors(CollectionBehaviors value) {
        this.collectionBehaviors = value;
    }

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
     * Gets the value of the collectionFields property.
     * 
     * @return
     *     possible object is
     *     {@link CollectionFields }
     *     
     */
    public CollectionFields getCollectionFields() {
        return collectionFields;
    }

    /**
     * Sets the value of the collectionFields property.
     * 
     * @param value
     *     allowed object is
     *     {@link CollectionFields }
     *     
     */
    public void setCollectionFields(CollectionFields value) {
        this.collectionFields = value;
    }

    /**
     * Gets the value of the collectionFilters property.
     * 
     * @return
     *     possible object is
     *     {@link CollectionFilters }
     *     
     */
    public CollectionFilters getCollectionFilters() {
        return collectionFilters;
    }

    /**
     * Sets the value of the collectionFilters property.
     * 
     * @param value
     *     allowed object is
     *     {@link CollectionFilters }
     *     
     */
    public void setCollectionFilters(CollectionFilters value) {
        this.collectionFilters = value;
    }

    /**
     * Gets the value of the collectionId property.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getCollectionId() {
        return collectionId;
    }

    /**
     * Sets the value of the collectionId property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setCollectionId(BigInteger value) {
        this.collectionId = value;
    }

}