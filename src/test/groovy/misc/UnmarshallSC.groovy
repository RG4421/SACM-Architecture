package misc

import groovy.xml.XmlUtil
import javax.xml.bind.JAXBContext
import javax.xml.bind.helpers.DefaultValidationEventHandler

def xmltext = """
<oval_system_characteristics xmlns="http://oval.cisecurity.org/XMLSchema/oval-system-characteristics-6" xmlns:oval="http://oval.cisecurity.org/XMLSchema/oval-common-6">
    <generator>
        <oval:product_name>Manual Generation</oval:product_name>
        <oval:product_version>6.0.0</oval:product_version>
        <oval:schema_version>6.0.0</oval:schema_version>
        <oval:timestamp>2019-06-17T14:27:04.889-04:00</oval:timestamp>
    </generator>
    <system_info>
        <os_name>linux</os_name>
        <os_version>3.10.0-693.21.1.el7.x86_64</os_version>
        <architecture>x86_64</architecture>
        <primary_host_name>ip-10-3-0-20.us-west-1.compute.internal</primary_host_name>
        <interfaces>
            <interface>
                <interface_name>lo</interface_name>
                <ip_address>127.0.0.1</ip_address>
                <mac_address>00:00:00:00:00:00</mac_address>
            </interface>
            <interface>
                <interface_name>eth0</interface_name>
                <ip_address>10.3.0.20</ip_address>
                <mac_address>02:80:5a:89:48:1e</mac_address>
            </interface>
        </interfaces>
    </system_info>
    <collected_objects>
        <collected_object id="oval:org.cisecurity:obj:1" version="1" variable_instance="1" flag="complete" comment="singleton family object">
            <reference item_ref="1"/>
        </collected_object>
        <collected_object id="oval:org.cisecurity:obj:2" version="1" variable_instance="1" flag="complete" comment="HOME environment variable object">
            <reference item_ref="2"/>
        </collected_object>
    </collected_objects>
    <system_data>
        <family_item xmlns="http://oval.cisecurity.org/XMLSchema/oval-system-characteristics-6#independent" id="1">
            <family>windows</family>
        </family_item>
        <environmentvariable_item xmlns="http://oval.cisecurity.org/XMLSchema/oval-system-characteristics-6#independent" id="2">
            <name>COMPUTERNAME</name>
            <value>CIS-CAT-DEV</value>
        </environmentvariable_item>
    </system_data>
</oval_system_characteristics>
"""

def packages = [
	"org.cisecurity.oval.sc.ind",
	"org.cisecurity.oval.sc",
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