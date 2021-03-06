//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.8-b130911.1802 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2019.07.20 at 09:27:07 AM EDT 
//


package org.cisecurity.oval.sc.ind;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.*;

import org.cisecurity.oval.sc.CollectedItemAnySimpleType;
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
 *         &lt;element name="var_ref" type="{http://oval.cisecurity.org/XMLSchema/oval-system-characteristics-6#independent}EntityItemVariableRefType" minOccurs="0"/>
 *         &lt;element name="value" type="{http://oval.cisecurity.org/XMLSchema/oval-system-characteristics-6}CollectedItemAnySimpleType" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlRootElement(name = "variable_item")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "varRef",
    "value"
})
public class VariableItem
    extends CollectedItemType
{

    @XmlElement(name = "var_ref")
    protected EntityItemVariableRefType varRef;
    protected List<CollectedItemAnySimpleType> value;

    /**
     * Gets the value of the varRef property.
     * 
     * @return
     *     possible object is
     *     {@link EntityItemVariableRefType }
     *     
     */
    public EntityItemVariableRefType getVarRef() {
        return varRef;
    }

    /**
     * Sets the value of the varRef property.
     * 
     * @param value
     *     allowed object is
     *     {@link EntityItemVariableRefType }
     *     
     */
    public void setVarRef(EntityItemVariableRefType value) {
        this.varRef = value;
    }

    /**
     * Gets the value of the value property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the value property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getValue().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link CollectedItemAnySimpleType }
     * 
     * 
     */
    public List<CollectedItemAnySimpleType> getValue() {
        if (value == null) {
            value = new ArrayList<CollectedItemAnySimpleType>();
        }
        return this.value;
    }

}
