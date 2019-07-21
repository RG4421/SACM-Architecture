package misc

import groovy.xml.XmlUtil
import javax.xml.bind.JAXBContext
import javax.xml.bind.helpers.DefaultValidationEventHandler

def xmltext = """
<oval_objects  
    xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xmlns="http://oval.cisecurity.org/XMLSchema/oval-collections-6" 
    xmlns:ind-def="http://oval.cisecurity.org/XMLSchema/oval-definitions-6#independent" 
    xmlns:oval="http://oval.cisecurity.org/XMLSchema/oval-common-6"
    xmlns:oval-coll="http://oval.cisecurity.org/XMLSchema/oval-collections-6"
    xmlns:ind-coll="http://oval.cisecurity.org/XMLSchema/oval-collections-6#independent"
    xsi:schemaLocation="http://oval.cisecurity.org/XMLSchema/oval-collections-6 oval-collections-schema.xsd http://oval.cisecurity.org/XMLSchema/oval-collections-6#independent independent-collections-schema.xsd">
    <generator>
        <oval:schema_version>6.0.0</oval:schema_version>
        <oval:timestamp>2009-01-12T10:41:00-05:00</oval:timestamp>
        <terms_of_use>Copyright (c) 2002-2012, The MITRE Corporation. All rights reserved. The contents of this file are subject to the license described in terms.txt.</terms_of_use>
    </generator>
    <objects>
        <ind-coll:family_object 
            id="oval:org.cisecurity:obj:1" 
            version="1" 
            comment="This family_object represents the family that the operating system belongs to."/>
        
        <ind-coll:environmentvariable_object id="oval:org.cisecurity:obj:2" version="1" comment="The HOME environment variable">
            <ind-coll:name>COMPUTERNAME</ind-coll:name>
        </ind-coll:environmentvariable_object>
    </objects>
</oval_objects>
"""

def packages = [
	"org.cisecurity.oval.collection.ind",
	"org.cisecurity.oval.collection",
    "org.cisecurity.oval.common",
    "org.cisecurity.oval.var",
	"org.cisecurity.xmldsig"
].join(":")

def jaxbContext= JAXBContext.newInstance(packages)
//println jaxbContext.toString()
def unmarshaller = jaxbContext.createUnmarshaller()
unmarshaller.setEventHandler(new DefaultValidationEventHandler())

def xml = new XmlParser(false, true).parseText(xmltext)
def xml2unmarshall = XmlUtil.serialize(xml)
def sr =  new StringReader(xmltext)

//OvalObjects oo = JaxbExtensions.unmarshal(jaxbContext, xmltext, OvalObjects.class)
def jaxbOvalObjects = unmarshaller.unmarshal(sr)
def b