package org.cisecurity.xmpp.extensions.collection.oval

import groovy.json.JsonSlurper
import groovy.xml.XmlUtil
import org.cisecurity.oval.collection.OvalObjects
import org.cisecurity.oval.sc.OvalSystemCharacteristics
import org.cisecurity.session.fact.SessionConfig
import org.slf4j.LoggerFactory

import javax.xml.bind.JAXBContext
import javax.xml.bind.Marshaller
import javax.xml.bind.helpers.DefaultValidationEventHandler

/**
 * The interface to the OVAL-Collector, based on usage of the
 * root <oval_objects> element
 */
class OvalCollectionSystem {
	def log = LoggerFactory.getLogger(OvalCollectionSystem.class)

	OvalObjects collectionRequest

	def sessionConfig =
		new SessionConfig(
			type: SessionConfig.Type.LOCAL,
			localScriptsDirPathname: "C:\\_Development\\Projects\\Assessor-Shared\\scripts") // TODO FIX

	/**
	 * TODO Instantiate a LOCAL session to send to the OvalObjects instance
	 * @return
	 */
	OvalSystemCharacteristics collect() {
		def ovalObjectsNode = marshallToNode(collectionRequest)

		org.cisecurity.collector.oval.coll.OvalObjects collectionDataModel =
			new org.cisecurity.collector.oval.coll.OvalObjects()

		collectionDataModel.connectSession(sessionConfig)
		collectionDataModel.parse(ovalObjectsNode)

		def systemCharacteristicsNode = collectionDataModel.collectSystemCharacteristics()
		collectionDataModel.disconnectSession()

		return unmarshallToObject(systemCharacteristicsNode)
	}

	/**
	 * Marshall a JAXB representation to a groovy Node
	 * @param jaxbInstance
	 */
	def marshallToNode(def jaxbInstance) {
		def json = new JsonSlurper().parse(getClass().getResourceAsStream("/packages.json"))
		def packages = json."packages".join(":")

		JAXBContext ctx = JAXBContext.newInstance(packages)
		Marshaller marshaller = ctx.createMarshaller()
		marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true)

		def sw = new StringWriter()
		marshaller.marshal(jaxbInstance, sw)

		def xmlParser = new XmlParser(false, false)
		xmlParser.setFeature("http://xml.org/sax/features/external-general-entities", false)

		def node = xmlParser.parseText(sw.toString())

		// [START] Just for logging
		def psw = new StringWriter()
		new XmlNodePrinter(
			new IndentPrinter(
				new PrintWriter(psw), "", false)).print(node)
		log.debug(psw.toString())
		// [ END ] Just for logging

		return node
	}

	/**
	 * Unmarshall a groovy Node to its JAXB representation
	 * @param node
	 * @return
	 */
	def unmarshallToObject(def node) {
		def json = new JsonSlurper().parse(getClass().getResourceAsStream("/packages.json"))
		def packages = json."sc-packages".join(":")

		def jaxbContext= JAXBContext.newInstance(packages)
		def unmarshaller = jaxbContext.createUnmarshaller()
		unmarshaller.setEventHandler(new DefaultValidationEventHandler())

		def xml2unmarshall = XmlUtil.serialize(node)
		log.info xml2unmarshall
		def sr =  new StringReader(xml2unmarshall)

		def unmarshalledObject = unmarshaller.unmarshal(sr)
		return unmarshalledObject
	}
}
