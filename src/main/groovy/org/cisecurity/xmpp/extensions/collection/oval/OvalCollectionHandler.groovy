package org.cisecurity.xmpp.extensions.collection.oval

import com.sun.xml.internal.bind.marshaller.NamespacePrefixMapper
import groovy.xml.XmlUtil
import org.apache.groovy.jaxb.extensions.JaxbExtensions
import org.cisecurity.oval.collection.OvalObjects
import org.cisecurity.oval.common.GeneratorType
import org.cisecurity.oval.common.SchemaVersionType
import org.cisecurity.oval.sc.OvalSystemCharacteristics
import org.slf4j.LoggerFactory
import rocks.xmpp.core.stanza.AbstractIQHandler
import rocks.xmpp.core.stanza.model.IQ

import javax.xml.bind.JAXBContext
import javax.xml.bind.Marshaller
import javax.xml.bind.PropertyException
import javax.xml.datatype.DatatypeFactory

class OvalCollectionHandler extends AbstractIQHandler {
	def log = LoggerFactory.getLogger(OvalCollectionHandler.class)

	def packages = [
		"org.cisecurity.oval.collection.ind",
		"org.cisecurity.oval.collection",
		"org.cisecurity.oval.common",
		"org.cisecurity.oval.var",
		"org.cisecurity.xmldsig"
	].join(":")

	OvalCollectionHandler() {
		super(IQ.Type.GET)
	}

	/**
	 * Process a OVAL(6)-modeled collection request.  This probably requires an OVAL-6 collector, eh?
	 * @param iq
	 * @return
	 */
	@Override
	protected final IQ processRequest(IQ iq) {
		log.debug "Inside OvalCollectionHandler::processRequest(IQ)"

		OvalObjects collectionRequest = iq.getExtension(OvalObjects.class)

		JAXBContext ctx = JAXBContext.newInstance(packages)
		Marshaller marshaller = ctx.createMarshaller()
		marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true)
//		try {
//			marshaller.setProperty("com.sun.xml.internal.bind.namespacePrefixMapper", new NSM())
//			//m.setProperty("com.sun.xml.bind.namespacePrefixMapper", new MyNamespaceMapper());
//		} catch (PropertyException e) {
//			// In case another JAXB implementation is used
//		}

		def sw = new StringWriter()
		marshaller.marshal(collectionRequest, sw)

		def xmlParser = new XmlParser(false, false)
		xmlParser.setFeature("http://xml.org/sax/features/external-general-entities", false)

		def oo = xmlParser.parseText(sw.toString())

		def psw = new StringWriter()
		new XmlNodePrinter(
			new IndentPrinter(
				new PrintWriter(psw), "", false)).print(oo)


		log.debug(psw.toString())

		def timestamp = DatatypeFactory.newInstance().newXMLGregorianCalendar(new GregorianCalendar())
		def osc = new OvalSystemCharacteristics()
		osc.generator =
			new GeneratorType(
				productName: "OVAL XMPP",
				productVersion: "0.0.1",
				schemaVersion: [new SchemaVersionType (value: "6.0.0")],
				timestamp: timestamp)
		return iq.createResult(osc)
	}
}

class NSM extends NamespacePrefixMapper {
	def log = LoggerFactory.getLogger(NSM.class)

	private static final String OVAL_COLL_PF = "oval-coll"
	private static final String OVAL_COLL_NS = "http://oval.cisecurity.org/XMLSchema/oval-collections-6"
	private static final String IND_COLL_PF = "ind-coll"
	private static final String IND_COLL_NS = "http://oval.cisecurity.org/XMLSchema/oval-collections-6#independent"
	private static final String OVAL_PF = "oval"
	private static final String OVAL_NS = "http://oval.cisecurity.org/XMLSchema/oval-common-6"

	@Override
	String getPreferredPrefix(String namespaceUri, String suggestion, boolean requirePrefix) {
		log.debug "URI=${namespaceUri}; Suggestion=${suggestion}; RequirePrefix=${requirePrefix}"

		switch (namespaceUri) {
			case OVAL_COLL_NS:
				return OVAL_COLL_PF
			case IND_COLL_NS:
				return IND_COLL_PF
			case OVAL_NS:
				return OVAL_PF
			default:
				return suggestion
		}
	}

	@Override
	String[] getPreDeclaredNamespaceUris() {
		return [OVAL_COLL_NS, IND_COLL_NS, OVAL_NS]
	}
}