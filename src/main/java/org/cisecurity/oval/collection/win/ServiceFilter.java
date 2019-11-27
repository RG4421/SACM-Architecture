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
 *         &lt;element name="service_name" type="{http://oval.cisecurity.org/XMLSchema/oval-collections-6}FilterStringType" minOccurs="0"/>
 *         &lt;element name="display_name" type="{http://oval.cisecurity.org/XMLSchema/oval-collections-6}FilterStringType" minOccurs="0"/>
 *         &lt;element name="description" type="{http://oval.cisecurity.org/XMLSchema/oval-collections-6}FilterStringType" minOccurs="0"/>
 *         &lt;element name="service_type" type="{http://oval.cisecurity.org/XMLSchema/oval-collections-6#windows}FilterServiceTypeType" minOccurs="0"/>
 *         &lt;element name="start_type" type="{http://oval.cisecurity.org/XMLSchema/oval-collections-6#windows}FilterServiceStartTypeType" minOccurs="0"/>
 *         &lt;element name="current_filter" type="{http://oval.cisecurity.org/XMLSchema/oval-collections-6#windows}FilterServiceCurrentStateType" minOccurs="0"/>
 *         &lt;element name="controls_accepted" type="{http://oval.cisecurity.org/XMLSchema/oval-collections-6#windows}FilterServiceControlsAcceptedType" minOccurs="0"/>
 *         &lt;element name="start_name" type="{http://oval.cisecurity.org/XMLSchema/oval-collections-6}FilterStringType" minOccurs="0"/>
 *         &lt;element name="path" type="{http://oval.cisecurity.org/XMLSchema/oval-collections-6}FilterStringType" minOccurs="0"/>
 *         &lt;element name="pid" type="{http://oval.cisecurity.org/XMLSchema/oval-collections-6}FilterIntType" minOccurs="0"/>
 *         &lt;element name="service_flag" type="{http://oval.cisecurity.org/XMLSchema/oval-collections-6}FilterBoolType" minOccurs="0"/>
 *         &lt;element name="dependencies" type="{http://oval.cisecurity.org/XMLSchema/oval-collections-6}FilterStringType" minOccurs="0"/>
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
    "serviceName",
    "displayName",
    "description",
    "serviceType",
    "startType",
    "currentFilter",
    "controlsAccepted",
    "startName",
    "path",
    "pid",
    "serviceFlag",
    "dependencies"
})
public class ServiceFilter
    extends FilterType
{

    @XmlElement(name = "service_name")
    protected FilterStringType serviceName;
    @XmlElement(name = "display_name")
    protected FilterStringType displayName;
    protected FilterStringType description;
    @XmlElement(name = "service_type")
    protected FilterServiceTypeType serviceType;
    @XmlElement(name = "start_type")
    protected FilterServiceStartTypeType startType;
    @XmlElement(name = "current_filter")
    protected FilterServiceCurrentStateType currentFilter;
    @XmlElement(name = "controls_accepted")
    protected FilterServiceControlsAcceptedType controlsAccepted;
    @XmlElement(name = "start_name")
    protected FilterStringType startName;
    protected FilterStringType path;
    protected FilterIntType pid;
    @XmlElement(name = "service_flag")
    protected FilterBoolType serviceFlag;
    protected FilterStringType dependencies;

    /**
     * Gets the value of the serviceName property.
     * 
     * @return
     *     possible object is
     *     {@link FilterStringType }
     *     
     */
    public FilterStringType getServiceName() {
        return serviceName;
    }

    /**
     * Sets the value of the serviceName property.
     * 
     * @param value
     *     allowed object is
     *     {@link FilterStringType }
     *     
     */
    public void setServiceName(FilterStringType value) {
        this.serviceName = value;
    }

    /**
     * Gets the value of the displayName property.
     * 
     * @return
     *     possible object is
     *     {@link FilterStringType }
     *     
     */
    public FilterStringType getDisplayName() {
        return displayName;
    }

    /**
     * Sets the value of the displayName property.
     * 
     * @param value
     *     allowed object is
     *     {@link FilterStringType }
     *     
     */
    public void setDisplayName(FilterStringType value) {
        this.displayName = value;
    }

    /**
     * Gets the value of the description property.
     * 
     * @return
     *     possible object is
     *     {@link FilterStringType }
     *     
     */
    public FilterStringType getDescription() {
        return description;
    }

    /**
     * Sets the value of the description property.
     * 
     * @param value
     *     allowed object is
     *     {@link FilterStringType }
     *     
     */
    public void setDescription(FilterStringType value) {
        this.description = value;
    }

    /**
     * Gets the value of the serviceType property.
     * 
     * @return
     *     possible object is
     *     {@link FilterServiceTypeType }
     *     
     */
    public FilterServiceTypeType getServiceType() {
        return serviceType;
    }

    /**
     * Sets the value of the serviceType property.
     * 
     * @param value
     *     allowed object is
     *     {@link FilterServiceTypeType }
     *     
     */
    public void setServiceType(FilterServiceTypeType value) {
        this.serviceType = value;
    }

    /**
     * Gets the value of the startType property.
     * 
     * @return
     *     possible object is
     *     {@link FilterServiceStartTypeType }
     *     
     */
    public FilterServiceStartTypeType getStartType() {
        return startType;
    }

    /**
     * Sets the value of the startType property.
     * 
     * @param value
     *     allowed object is
     *     {@link FilterServiceStartTypeType }
     *     
     */
    public void setStartType(FilterServiceStartTypeType value) {
        this.startType = value;
    }

    /**
     * Gets the value of the currentFilter property.
     * 
     * @return
     *     possible object is
     *     {@link FilterServiceCurrentStateType }
     *     
     */
    public FilterServiceCurrentStateType getCurrentFilter() {
        return currentFilter;
    }

    /**
     * Sets the value of the currentFilter property.
     * 
     * @param value
     *     allowed object is
     *     {@link FilterServiceCurrentStateType }
     *     
     */
    public void setCurrentFilter(FilterServiceCurrentStateType value) {
        this.currentFilter = value;
    }

    /**
     * Gets the value of the controlsAccepted property.
     * 
     * @return
     *     possible object is
     *     {@link FilterServiceControlsAcceptedType }
     *     
     */
    public FilterServiceControlsAcceptedType getControlsAccepted() {
        return controlsAccepted;
    }

    /**
     * Sets the value of the controlsAccepted property.
     * 
     * @param value
     *     allowed object is
     *     {@link FilterServiceControlsAcceptedType }
     *     
     */
    public void setControlsAccepted(FilterServiceControlsAcceptedType value) {
        this.controlsAccepted = value;
    }

    /**
     * Gets the value of the startName property.
     * 
     * @return
     *     possible object is
     *     {@link FilterStringType }
     *     
     */
    public FilterStringType getStartName() {
        return startName;
    }

    /**
     * Sets the value of the startName property.
     * 
     * @param value
     *     allowed object is
     *     {@link FilterStringType }
     *     
     */
    public void setStartName(FilterStringType value) {
        this.startName = value;
    }

    /**
     * Gets the value of the path property.
     * 
     * @return
     *     possible object is
     *     {@link FilterStringType }
     *     
     */
    public FilterStringType getPath() {
        return path;
    }

    /**
     * Sets the value of the path property.
     * 
     * @param value
     *     allowed object is
     *     {@link FilterStringType }
     *     
     */
    public void setPath(FilterStringType value) {
        this.path = value;
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
     * Gets the value of the serviceFlag property.
     * 
     * @return
     *     possible object is
     *     {@link FilterBoolType }
     *     
     */
    public FilterBoolType getServiceFlag() {
        return serviceFlag;
    }

    /**
     * Sets the value of the serviceFlag property.
     * 
     * @param value
     *     allowed object is
     *     {@link FilterBoolType }
     *     
     */
    public void setServiceFlag(FilterBoolType value) {
        this.serviceFlag = value;
    }

    /**
     * Gets the value of the dependencies property.
     * 
     * @return
     *     possible object is
     *     {@link FilterStringType }
     *     
     */
    public FilterStringType getDependencies() {
        return dependencies;
    }

    /**
     * Sets the value of the dependencies property.
     * 
     * @param value
     *     allowed object is
     *     {@link FilterStringType }
     *     
     */
    public void setDependencies(FilterStringType value) {
        this.dependencies = value;
    }

}
