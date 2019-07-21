package org.cisecurity.xmpp

import groovy.xml.XmlUtil
import org.cisecurity.oval.collection.OvalObjects
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import rocks.xmpp.addr.Jid
import spock.lang.Shared
import spock.lang.Specification

import javax.xml.bind.JAXBContext

class XmppProxyTest extends Specification {
	@Shared
	Logger log = LoggerFactory.getLogger("org.cis.xmpp")

	@Shared
	XmppProxy ubuntu
	@Shared
	XmppProxy orchestrator

	@Shared
	def ucf = { fm ->
		log.info "[UBUNTU CALLBACK FUNCTION] ${fm}${System.lineSeparator()}"
	}
	@Shared
	def ocf = { fm ->
		log.info "[ORCHESTRATOR CALLBACK FUNCTION] ${fm}${System.lineSeparator()}"
	}

	void setupSpec() {
		ubuntu = new XmppProxy(callback: ucf, xmppDomain: "ip-0a1e0af4")
		ubuntu.connect(new User(username: "ubuntu", password: "Pt3ttcs2h!", resource: "resource"))

		orchestrator = new XmppProxy(callback: ocf, xmppDomain: "ip-0a1e0af4")
		orchestrator.connect(new User(username: "orchestrator", password: "Pt3ttcs2h!", resource: "resource"))
	}

	void cleanupSpec() {
		ubuntu.close()
		orchestrator.close()
	}

	def "SendMessage"() {
		given: "A bogus chat message"
			Chat chat = new Chat(to: "ubuntu", msg: "doesnt matter")
		when: "The IQ is sent from 'orchestrator' to 'ubuntu'"
			Jid u = ubuntu.xmppClient.connectedResource
			orchestrator.discoverServices(u)
		then: "the callback function is called, setting the var"
			false
	}

	def "File Transfer"() {
		given: ""
		when: "the orchestrator offers a file to ubuntu"
			Jid u = ubuntu.xmppClient.connectedResource
			def future = orchestrator.offerFile(u)
			Thread.sleep(15000)
		then: "the file is transfered and exists in c-temp"
			assert new File("C:\\Temp\\Win10-xccdf.xml").exists()
	}

	def "Last Activity"() {
		given: ""
		when: "the orchestrator requests ubuntu's last activity time"
			Jid u = ubuntu.xmppClient.connectedResource
			def la = orchestrator.lastActivity(u)
		then: "a time is returned"
			assert la
	}

	def "Create the PubSub Nodes"() {
		given: ""
		when: ""
			orchestrator.createNodes()
		then: ""
		true
	}

	def "OVAL Collections"() {
		given: "A blob of XML"
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

			def jaxbContext= JAXBContext.newInstance(OvalObjects.class)
			def unmarshaller = jaxbContext.createUnmarshaller()
			def sr =  new StringReader(xmltext)

			OvalObjects jaxbOvalObjects = unmarshaller.unmarshal(sr)
		when: "the orchestrator requests ubuntu's to collect something"
			Jid u = ubuntu.xmppClient.connectedResource
			def osc = orchestrator.ovalCollection(u, jaxbOvalObjects)
		then: "a time is returned"
			assert osc
	}
}
