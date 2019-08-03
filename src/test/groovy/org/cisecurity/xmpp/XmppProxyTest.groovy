package org.cisecurity.xmpp

import groovy.xml.XmlUtil
import org.cisecurity.oval.collection.OvalObjects
import org.cisecurity.oval.sc.OvalSystemCharacteristics
import org.cisecurity.oval.sc.ind.FamilyItem
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
	XmppProxy collector
	@Shared
	XmppProxy orchestrator
	@Shared
	XmppProxy recipient

	@Shared
	def ucf = { fm ->
		log.info "[UBUNTU CALLBACK FUNCTION] ${fm}${System.lineSeparator()}"
	}
	@Shared
	def ocf = { fm ->
		log.info "[ORCHESTRATOR CALLBACK FUNCTION] ${fm}${System.lineSeparator()}"
	}

	void setupSpec() {
		collector = new XmppProxy(callback: ucf, xmppDomain: "ip-0a1e0af4")
		collector.connect(new User(username: "ubuntu", password: "Pt3ttcs2h!", resource: "resource"))

		orchestrator = new XmppProxy(callback: ocf, xmppDomain: "ip-0a1e0af4")
		orchestrator.connect(new User(username: "orchestrator", password: "Pt3ttcs2h!", resource: "resource"))

		recipient = new XmppProxy(callback: ocf, xmppDomain: "ip-0a1e0af4")
		recipient.connect(new User(username: "wmunyan", password: "Pt3ttcs2h!", resource: "resource"))
	}

	void cleanupSpec() {
		collector.close()
		orchestrator.close()
		recipient.close()
	}

	def "Ensure the collector XMPP entity supports OVAL v6 collection capabilities"() {
		given: "A bogus chat message"
			Chat chat = new Chat(to: "collector", msg: "doesnt matter")
		when: "The IQ is sent from 'orchestrator' to 'collector'"
			Jid u = collector.xmppClient.connectedResource
			orchestrator.discoverServices(u)
		then: "the callback function is called, setting the var"
			assert orchestrator.xmppClient.isSupported("http://oval.cisecurity.org/XMLSchema/oval-collections-6", u).result
	}

	def "File Transfer"() {
		given: ""
		when: "the orchestrator offers a file to collector"
			Jid u = collector.xmppClient.connectedResource
			def future = orchestrator.offerFile(u)
			Thread.sleep(15000)
		then: "the file is transfered and exists in c-temp"
			assert new File("C:\\Temp\\Win10-xccdf.xml").exists()
	}

	def "Last Activity"() {
		given: ""
		when: "the orchestrator requests collector's last activity time"
			Jid u = collector.xmppClient.connectedResource
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

	def "Have the orchestrator tell an endpoint to collect and simply ensure some system characteristics are collected"() {
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

			OvalObjects ovalObjects = unmarshaller.unmarshal(sr)
		when: "the orchestrator requests collector's to collect something"
			Jid u = collector.xmppClient.connectedResource
			def osc = orchestrator.performCollection(u, ovalObjects)
		then: "system characteristics are returned"
			assert osc.systemData.collectedItem.size() == 2
			assert osc.systemData.collectedItem[0] instanceof FamilyItem
			assert ((FamilyItem)osc.systemData.collectedItem[0]).family.value == "windows"
	}

	def "Have the orchestrator tell an endpoint to collect and send its results to the pub-sub topic"() {
		given: "Some OVAL objects to be collected by the endpoint"
			def xmltext = """
<oval_objects collection-id="oval:org.cisecurity:collection:9999"
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

			OvalObjects ovalObjects = unmarshaller.unmarshal(sr)
		when: "The orchestrator requests that the collector gets the requested information"
			Jid collectorJid = collector.xmppClient.connectedResource
			def osc = orchestrator.performCollectionAndPublish(collectorJid, ovalObjects)
		then: "The orchestrator then sends the collected information to the 'repository'"
			assert osc
	}

	def "Have the orchestrator tell an endpoint to collect and send its results to the MAP graph endpoint"() {
		given: "Some OVAL objects to be collected by the endpoint"
			def xmltext = """
<oval_objects collection-id="oval:org.cisecurity:collection:9999" 
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

			OvalObjects ovalObjects = unmarshaller.unmarshal(sr)
		when: "The orchestrator requests that the collector gets the requested information"
			Jid collectorJid = collector.xmppClient.connectedResource
			Jid recipientJid = Jid.of("carl-heinz@ip-0a1e0af4/resource")
			//Jid recipientJid = recipient.xmppClient.connectedResource
			def osc = orchestrator.performCollection(collectorJid, ovalObjects, recipientJid)
		then: "The orchestrator then sends the collected information to the 'repository'"
			assert !osc // its an empty response IQ
	}

	def "Send the <oval_objects> via standard Message stanza"() {
		given: "A blob of XML"
			def xmltext = """
<oval_objects collection-id="oval:org.cisecurity:collection:12345" 
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

			OvalObjects ovalObjects = unmarshaller.unmarshal(sr)
		when: "the orchestrator sends the message to the collector"
			orchestrator.sendCollectionRequest(collector.xmppClient.connectedResource, ovalObjects)
		then: "you can check system.out to see if the collection-id is logged"
			Thread.sleep(30000)
			assert true
	}

	def "Send the <oval_system_characteristics via standard Message stanza"() {
		given: "The OVAL system characteristics"
			def xmltext = """
<oval_system_characteristics xmlns="http://oval.cisecurity.org/XMLSchema/oval-system-characteristics-6" collection-ref="oval:org.cisecurity:collection:9999">
    <generator>
        <product_name xmlns="http://oval.cisecurity.org/XMLSchema/oval-common-6">OVAL XMPP</product_name>
        <product_version xmlns="http://oval.cisecurity.org/XMLSchema/oval-common-6">0.0.1</product_version>
        <schema_version xmlns="http://oval.cisecurity.org/XMLSchema/oval-common-6">6.0.0</schema_version>
        <timestamp xmlns="http://oval.cisecurity.org/XMLSchema/oval-common-6">2019-07-21T16:39:52.451-04:00</timestamp>
    </generator>
    <system_info>
        <os_name>Windows 10</os_name>
        <os_version>10.0</os_version>
        <architecture>amd64</architecture>
        <primary_host_name>CIS-CAT-DEV</primary_host_name>
        <interfaces/>
    </system_info>
    <collected_objects>
        <collected_object id="oval:org.cisecurity:obj:1" version="1" comment="This family_object represents the family that the operating system belongs to." flag="complete">
            <reference item_ref="1"/>
        </collected_object>
        <collected_object id="oval:org.cisecurity:obj:2" version="1" comment="The HOME environment variable" flag="complete">
            <reference item_ref="2"/>
        </collected_object>
    </collected_objects>
    <system_data>
        <family_item xmlns="http://oval.cisecurity.org/XMLSchema/oval-system-characteristics-6#independent" id="1">
            <family datatype="string">windows</family>
        </family_item>
        <environmentvariable_item xmlns="http://oval.cisecurity.org/XMLSchema/oval-system-characteristics-6#independent" id="2">
            <name>COMPUTERNAME</name>
            <value>CIS-CAT-DEV</value>
        </environmentvariable_item>
    </system_data>
</oval_system_characteristics>"""

			def jaxbContext= JAXBContext.newInstance(OvalSystemCharacteristics.class)
			def unmarshaller = jaxbContext.createUnmarshaller()
			def sr =  new StringReader(xmltext)

			OvalSystemCharacteristics ovalSystemCharacteristics = unmarshaller.unmarshal(sr)
		when: "The collector sends the message to the orchestrator"
			Jid carlheinzJid = Jid.of("carl-heinz@ip-0a1e0af4/resource")
			collector.sendCollectionResponse(carlheinzJid, ovalSystemCharacteristics)
//			collector.sendCollectionResponse(orchestrator.xmppClient.connectedResource, ovalSystemCharacteristics)
		then: "you can check system.out to see if the collection-ref is logged"
			assert true
	}
}
