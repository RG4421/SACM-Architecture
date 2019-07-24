package org.cisecurity.oval

import groovy.json.JsonSlurper
import groovy.xml.XmlUtil
import org.cisecurity.oval.collection.OvalObjects
import org.cisecurity.oval.sc.OvalSystemCharacteristics
import org.cisecurity.session.fact.SessionConfig
import org.cisecurity.session.fact.SessionFactoryFactory
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

		collectionDataModel.session =
			new SessionFactoryFactory().getSessionFactory().getSession(sessionConfig)

		if (collectionDataModel.session.isWindows()) {
			log.info "Connection type is Windows:"
			log.info "--> Unzipping [START]"
			def unzipCmd = "${collectionDataModel.session.connectionTmpPathname}unzip.exe"
			def zipfile  = "${collectionDataModel.session.connectionTmpPathname}scripts.zip"
			def fullCmd  = "${unzipCmd} -o -qq ${zipfile} -d ${collectionDataModel.session.connectionTmpPathname}"

			try {
				def rc = collectionDataModel.session.execute(fullCmd)

				log.info "--> Unzipping [ END ]   (rc = ${rc.exitValue})"
			} catch (Exception e) {
				log.error "Exception Unzipping ${zipfile}", e

				log.info "An exception was caught while attempting to unzip files within the Assessor's ephemeral directory."
				log.info "Ensure all WinRM and SMB permissions have been configured correctly."
				log.info "- Ensure the connecting user: ${sessionConfig.user} is granted administrator privileges;"
				log.info "- If connecting to a non-domain-joined endpoint, ensure UAC remote restrictions have been disabled;"
				log.info "- If connecting via WinRM over HTTP, ensure the WinRM service is configured with \"allowUnencrypted=true\";"
				log.info "- Ensure any WinRM IPv4/6 filters are configured to allow remote access from the Assessor host."
				log.info ""
				log.info "Consult the CIS-CAT Pro Assessor Configuration Guide for more information."
				log.info "The session will now be disconnected."
				log.info ""

				collectionDataModel.session.disconnect()

				return null
			}
		}
		log.info "Connection established."

		collectionDataModel.parse(ovalObjectsNode)

		def systemCharacteristicsNode = collectionDataModel.collectSystemCharacteristics()
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
