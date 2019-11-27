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
 *         &lt;element name="admin_approval_mode" type="{http://oval.cisecurity.org/XMLSchema/oval-collections-6}FilterBoolType" minOccurs="0"/>
 *         &lt;element name="elevation_prompt_admin" type="{http://oval.cisecurity.org/XMLSchema/oval-collections-6}FilterStringType" minOccurs="0"/>
 *         &lt;element name="elevation_prompt_standard" type="{http://oval.cisecurity.org/XMLSchema/oval-collections-6}FilterStringType" minOccurs="0"/>
 *         &lt;element name="detect_installations" type="{http://oval.cisecurity.org/XMLSchema/oval-collections-6}FilterBoolType" minOccurs="0"/>
 *         &lt;element name="elevate_signed_executables" type="{http://oval.cisecurity.org/XMLSchema/oval-collections-6}FilterBoolType" minOccurs="0"/>
 *         &lt;element name="elevate_uiaccess" type="{http://oval.cisecurity.org/XMLSchema/oval-collections-6}FilterBoolType" minOccurs="0"/>
 *         &lt;element name="run_admins_aam" type="{http://oval.cisecurity.org/XMLSchema/oval-collections-6}FilterBoolType" minOccurs="0"/>
 *         &lt;element name="secure_desktop" type="{http://oval.cisecurity.org/XMLSchema/oval-collections-6}FilterBoolType" minOccurs="0"/>
 *         &lt;element name="virtualize_write_failures" type="{http://oval.cisecurity.org/XMLSchema/oval-collections-6}FilterBoolType" minOccurs="0"/>
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
    "adminApprovalMode",
    "elevationPromptAdmin",
    "elevationPromptStandard",
    "detectInstallations",
    "elevateSignedExecutables",
    "elevateUiaccess",
    "runAdminsAam",
    "secureDesktop",
    "virtualizeWriteFailures"
})
public class UacFilter
    extends FilterType
{

    @XmlElement(name = "admin_approval_mode")
    protected FilterBoolType adminApprovalMode;
    @XmlElement(name = "elevation_prompt_admin")
    protected FilterStringType elevationPromptAdmin;
    @XmlElement(name = "elevation_prompt_standard")
    protected FilterStringType elevationPromptStandard;
    @XmlElement(name = "detect_installations")
    protected FilterBoolType detectInstallations;
    @XmlElement(name = "elevate_signed_executables")
    protected FilterBoolType elevateSignedExecutables;
    @XmlElement(name = "elevate_uiaccess")
    protected FilterBoolType elevateUiaccess;
    @XmlElement(name = "run_admins_aam")
    protected FilterBoolType runAdminsAam;
    @XmlElement(name = "secure_desktop")
    protected FilterBoolType secureDesktop;
    @XmlElement(name = "virtualize_write_failures")
    protected FilterBoolType virtualizeWriteFailures;

    /**
     * Gets the value of the adminApprovalMode property.
     * 
     * @return
     *     possible object is
     *     {@link FilterBoolType }
     *     
     */
    public FilterBoolType getAdminApprovalMode() {
        return adminApprovalMode;
    }

    /**
     * Sets the value of the adminApprovalMode property.
     * 
     * @param value
     *     allowed object is
     *     {@link FilterBoolType }
     *     
     */
    public void setAdminApprovalMode(FilterBoolType value) {
        this.adminApprovalMode = value;
    }

    /**
     * Gets the value of the elevationPromptAdmin property.
     * 
     * @return
     *     possible object is
     *     {@link FilterStringType }
     *     
     */
    public FilterStringType getElevationPromptAdmin() {
        return elevationPromptAdmin;
    }

    /**
     * Sets the value of the elevationPromptAdmin property.
     * 
     * @param value
     *     allowed object is
     *     {@link FilterStringType }
     *     
     */
    public void setElevationPromptAdmin(FilterStringType value) {
        this.elevationPromptAdmin = value;
    }

    /**
     * Gets the value of the elevationPromptStandard property.
     * 
     * @return
     *     possible object is
     *     {@link FilterStringType }
     *     
     */
    public FilterStringType getElevationPromptStandard() {
        return elevationPromptStandard;
    }

    /**
     * Sets the value of the elevationPromptStandard property.
     * 
     * @param value
     *     allowed object is
     *     {@link FilterStringType }
     *     
     */
    public void setElevationPromptStandard(FilterStringType value) {
        this.elevationPromptStandard = value;
    }

    /**
     * Gets the value of the detectInstallations property.
     * 
     * @return
     *     possible object is
     *     {@link FilterBoolType }
     *     
     */
    public FilterBoolType getDetectInstallations() {
        return detectInstallations;
    }

    /**
     * Sets the value of the detectInstallations property.
     * 
     * @param value
     *     allowed object is
     *     {@link FilterBoolType }
     *     
     */
    public void setDetectInstallations(FilterBoolType value) {
        this.detectInstallations = value;
    }

    /**
     * Gets the value of the elevateSignedExecutables property.
     * 
     * @return
     *     possible object is
     *     {@link FilterBoolType }
     *     
     */
    public FilterBoolType getElevateSignedExecutables() {
        return elevateSignedExecutables;
    }

    /**
     * Sets the value of the elevateSignedExecutables property.
     * 
     * @param value
     *     allowed object is
     *     {@link FilterBoolType }
     *     
     */
    public void setElevateSignedExecutables(FilterBoolType value) {
        this.elevateSignedExecutables = value;
    }

    /**
     * Gets the value of the elevateUiaccess property.
     * 
     * @return
     *     possible object is
     *     {@link FilterBoolType }
     *     
     */
    public FilterBoolType getElevateUiaccess() {
        return elevateUiaccess;
    }

    /**
     * Sets the value of the elevateUiaccess property.
     * 
     * @param value
     *     allowed object is
     *     {@link FilterBoolType }
     *     
     */
    public void setElevateUiaccess(FilterBoolType value) {
        this.elevateUiaccess = value;
    }

    /**
     * Gets the value of the runAdminsAam property.
     * 
     * @return
     *     possible object is
     *     {@link FilterBoolType }
     *     
     */
    public FilterBoolType getRunAdminsAam() {
        return runAdminsAam;
    }

    /**
     * Sets the value of the runAdminsAam property.
     * 
     * @param value
     *     allowed object is
     *     {@link FilterBoolType }
     *     
     */
    public void setRunAdminsAam(FilterBoolType value) {
        this.runAdminsAam = value;
    }

    /**
     * Gets the value of the secureDesktop property.
     * 
     * @return
     *     possible object is
     *     {@link FilterBoolType }
     *     
     */
    public FilterBoolType getSecureDesktop() {
        return secureDesktop;
    }

    /**
     * Sets the value of the secureDesktop property.
     * 
     * @param value
     *     allowed object is
     *     {@link FilterBoolType }
     *     
     */
    public void setSecureDesktop(FilterBoolType value) {
        this.secureDesktop = value;
    }

    /**
     * Gets the value of the virtualizeWriteFailures property.
     * 
     * @return
     *     possible object is
     *     {@link FilterBoolType }
     *     
     */
    public FilterBoolType getVirtualizeWriteFailures() {
        return virtualizeWriteFailures;
    }

    /**
     * Sets the value of the virtualizeWriteFailures property.
     * 
     * @param value
     *     allowed object is
     *     {@link FilterBoolType }
     *     
     */
    public void setVirtualizeWriteFailures(FilterBoolType value) {
        this.virtualizeWriteFailures = value;
    }

}
