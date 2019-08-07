package misc

import groovy.json.JsonSlurper

import javax.xml.bind.JAXBContext
import javax.xml.bind.helpers.DefaultValidationEventHandler

def xmltext = """
<oval_objects collection-id="oval:org.cisecurity:collection:9999" 
    xmlns="http://oval.cisecurity.org/XMLSchema/oval-collections-6"
    xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" 
    xmlns:oval="http://oval.cisecurity.org/XMLSchema/oval-common-6"
    xmlns:oval-coll="http://oval.cisecurity.org/XMLSchema/oval-collections-6"
    xmlns:ind-coll="http://oval.cisecurity.org/XMLSchema/oval-collections-6#independent"
    xsi:schemaLocation="http://oval.cisecurity.org/XMLSchema/oval-collections-6 oval-collections-schema.xsd http://oval.cisecurity.org/XMLSchema/oval-collections-6#independent independent-collections-schema.xsd">
    <generator>
        <oval:product_name>OVAL Collections Generator</oval:product_name>
        <oval:product_version>0.0.1</oval:product_version>
        <oval:schema_version>6.0.0</oval:schema_version>
        <oval:timestamp>2019-07-20T10:41:00-05:00</oval:timestamp>
    </generator>
    <objects>
        <ind-coll:family_object id="oval:org.cisecurity:obj:1" version="1" 
            comment="This family_object represents the family that the operating system belongs to."/>
        
        <ind-coll:environmentvariable_object id="oval:org.cisecurity:obj:2" version="1" comment="The COMPUTERNAME environment variable, but using filters">
            <ind-coll:name operation="pattern match">.*</ind-coll:name>
            <oval-coll:object_filter action="include" filter_ref="oval:org.cisecurity:flt:2"/>
        </ind-coll:environmentvariable_object>
    </objects>
    
    <filters>
        <ind-coll:environmentvariable_filter id="oval:org.cisecurity:flt:2" version="1">
            <ind-coll:name>COMPUTERNAME</ind-coll:name>
        </ind-coll:environmentvariable_filter>
    </filters>
    
    <variables>
        <oval:constant_variable id="oval:org.cisecurity:var:1" version="1" datatype="string" comment="Foo">
            <oval:value>Foo</oval:value>
        </oval:constant_variable>
        <oval:constant_variable id="oval:org.cisecurity:var:2" version="1" datatype="string" comment="Bar">
            <oval:value>Bar</oval:value>
        </oval:constant_variable>
        <oval:local_variable id="oval:org.cisecurity:var:3" version="1" datatype="string" comment="concat foo and bar">
            <oval:concat>
                <oval:variable_component var_ref="oval:org.cisecurity:var:1"/>
                <oval:variable_component var_ref="oval:org.cisecurity:var:2"/>
            </oval:concat>
        </oval:local_variable>
    </variables>
</oval_objects>
"""

//def packages = [
//	"org.cisecurity.oval.collection.ind",
//	"org.cisecurity.oval.collection",
//	"org.cisecurity.oval.common",
//	"org.cisecurity.oval.var",
//	"org.cisecurity.xmldsig"
//].join(":")
def json = new JsonSlurper().parse(getClass().getResourceAsStream("/packages.json"))
def packages = json."packages".join(":")

def jaxbContext= JAXBContext.newInstance(packages)
def unmarshaller = jaxbContext.createUnmarshaller()
unmarshaller.setEventHandler(new DefaultValidationEventHandler())
def sr =  new StringReader(xmltext)
def ovalObjects = unmarshaller.unmarshal(sr)

def m = jaxbContext.createMarshaller()

m.marshal(ovalObjects, System.out)


