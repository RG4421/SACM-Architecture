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


	def ucf = { fm ->
		log.info "[UBUNTU CALLBACK FUNCTION] ${fm}${System.lineSeparator()}"
	}
	def ocf = { fm ->
		log.info "[ORCHESTRATOR CALLBACK FUNCTION] ${fm}${System.lineSeparator()}"
	}

	void setup() {
		ubuntu = new XmppProxy(callback: ucf, xmppDomain: "ip-0a1e0af4")
		ubuntu.connect(new User(username: "ubuntu", password: "Pt3ttcs2h!", resource: "resource"))

		orchestrator = new XmppProxy(callback: ocf, xmppDomain: "ip-0a1e0af4")
		orchestrator.connect(new User(username: "orchestrator", password: "Pt3ttcs2h!", resource: "resource"))
	}

	void cleanup() {
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
	xmlns="http://oval.cisecurity.org/XMLSchema/oval-collections-6"
    xmlns:oval="http://oval.cisecurity.org/XMLSchema/oval-common-6"
    xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance">
    <generator>
        <oval:schema_version>6.0.0</oval:schema_version>
        <oval:timestamp>2009-01-12T10:41:00-05:00</oval:timestamp>
    </generator>
    <objects>
        <family_object xmlns="http://oval.cisecurity.org/XMLSchema/oval-collections-6#independent"
            id="oval:org.cisecurity:obj:1" 
            version="1" 
            comment="This family_object represents the family that the operating system belongs to."/>
    </objects>
</oval_objects>
"""

			def jaxbContext= JAXBContext.newInstance(OvalObjects.class)
			def unmarshaller = jaxbContext.createUnmarshaller()

			def xml = new XmlParser(false, true).parseText(xmltext)
			def sr =  new StringReader(xmltext)

			def jaxbOvalObjects = unmarshaller.unmarshal(sr)
		when: "the orchestrator requests ubuntu's to collect something"
			Jid u = ubuntu.xmppClient.connectedResource
			def osc = orchestrator.ovalCollection(u, jaxbOvalObjects)
		then: "a time is returned"
			assert osc
	}
}
