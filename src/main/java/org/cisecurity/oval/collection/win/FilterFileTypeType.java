//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.8-b130911.1802 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2019.11.21 at 11:38:45 AM EST 
//


package org.cisecurity.oval.collection.win;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;
import org.cisecurity.oval.collection.FilterStringType;


/**
 * The EntityStateFileTypeType complex type restricts a string value to a specific set of values. These values describe the type of file being represented. For more information see the GetFileType and GetFileAttributesEx functions as defined by Microsoft. The empty string is also allowed to support empty element associated with variable references. Note that when using pattern matches and variables care must be taken to ensure that the regular expression and variable values align with the enumerated values.
 * 
 * <p>Java class for FilterFileTypeType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="FilterFileTypeType">
 *   &lt;simpleContent>
 *     &lt;restriction base="&lt;http://oval.cisecurity.org/XMLSchema/oval-collections-6>FilterStringType">
 *     &lt;/restriction>
 *   &lt;/simpleContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "FilterFileTypeType")
public class FilterFileTypeType
    extends FilterStringType
{


}
