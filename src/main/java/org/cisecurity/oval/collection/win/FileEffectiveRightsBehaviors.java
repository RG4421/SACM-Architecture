//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.8-b130911.1802 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2019.11.21 at 11:38:45 AM EST 
//


package org.cisecurity.oval.collection.win;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlType;


/**
 * The FileEffectiveRights53Behaviors extend the win-coll:FileBehaviors and therefore include the behaviors defined by that type.
 * 
 * <p>Java class for FileEffectiveRightsBehaviors complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="FileEffectiveRightsBehaviors">
 *   &lt;complexContent>
 *     &lt;extension base="{http://oval.cisecurity.org/XMLSchema/oval-collections-6#windows}FileBehaviors">
 *       &lt;attribute name="include_group" type="{http://www.w3.org/2001/XMLSchema}boolean" default="true" />
 *       &lt;attribute name="resolve_group" type="{http://www.w3.org/2001/XMLSchema}boolean" default="false" />
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "FileEffectiveRightsBehaviors")
public class FileEffectiveRightsBehaviors
    extends FileBehaviors
{

    @XmlAttribute(name = "include_group")
    protected Boolean includeGroup;
    @XmlAttribute(name = "resolve_group")
    protected Boolean resolveGroup;

    /**
     * Gets the value of the includeGroup property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public boolean isIncludeGroup() {
        if (includeGroup == null) {
            return true;
        } else {
            return includeGroup;
        }
    }

    /**
     * Sets the value of the includeGroup property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setIncludeGroup(Boolean value) {
        this.includeGroup = value;
    }

    /**
     * Gets the value of the resolveGroup property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public boolean isResolveGroup() {
        if (resolveGroup == null) {
            return false;
        } else {
            return resolveGroup;
        }
    }

    /**
     * Sets the value of the resolveGroup property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setResolveGroup(Boolean value) {
        this.resolveGroup = value;
    }

}
