//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.8-b130911.1802 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2019.11.25 at 03:24:21 PM EST 
//


package org.cisecurity.csat;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the org.cisecurity.csat package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _Reason_QNAME = new QName("http://cisecurity.org/csat", "reason");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: org.cisecurity.csat
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link Valid }
     * 
     */
    public Valid createValid() {
        return new Valid();
    }

    /**
     * Create an instance of {@link Average }
     * 
     */
    public Average createAverage() {
        return new Average();
    }

    /**
     * Create an instance of {@link Control }
     * 
     */
    public Control createControl() {
        return new Control();
    }

    /**
     * Create an instance of {@link Subcontrol }
     * 
     */
    public Subcontrol createSubcontrol() {
        return new Subcontrol();
    }

    /**
     * Create an instance of {@link Invalid }
     * 
     */
    public Invalid createInvalid() {
        return new Invalid();
    }

    /**
     * Create an instance of {@link Csat }
     * 
     */
    public Csat createCsat() {
        return new Csat();
    }

    /**
     * Create an instance of {@link Validation }
     * 
     */
    public Validation createValidation() {
        return new Validation();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://cisecurity.org/csat", name = "reason")
    public JAXBElement<String> createReason(String value) {
        return new JAXBElement<String>(_Reason_QNAME, String.class, null, value);
    }

}
