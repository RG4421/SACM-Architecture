package org.cisecurity.xmpp.extensions.collection.oval

import groovy.xml.XmlUtil
import org.cisecurity.oval.collection.ObjectStringType
import org.cisecurity.oval.collection.OvalObjects
import org.cisecurity.oval.collection.ind.EnvironmentvariableObject
import org.cisecurity.oval.collection.ind.FamilyObject
import org.cisecurity.oval.common.GeneratorType
import org.cisecurity.oval.common.OperationEnumeration
import org.cisecurity.oval.common.SchemaVersionType
import org.cisecurity.oval.sc.CollectedItemAnySimpleType
import org.cisecurity.oval.sc.CollectedItemStringType
import org.cisecurity.oval.sc.CollectedObjectType
import org.cisecurity.oval.sc.CollectedObjectsType
import org.cisecurity.oval.sc.InterfacesType
import org.cisecurity.oval.sc.OvalSystemCharacteristics
import org.cisecurity.oval.sc.ReferenceType
import org.cisecurity.oval.sc.SystemDataType
import org.cisecurity.oval.sc.SystemInfoType
import org.cisecurity.oval.sc.ind.EntityItemFamilyType
import org.cisecurity.oval.sc.ind.EnvironmentvariableItem
import org.cisecurity.oval.sc.ind.FamilyItem
import org.slf4j.LoggerFactory
import rocks.xmpp.core.stanza.AbstractIQHandler
import rocks.xmpp.core.stanza.model.IQ

import javax.xml.bind.JAXBContext
import javax.xml.bind.JAXBElement
import javax.xml.datatype.DatatypeFactory

class OvalCollectionHandler extends AbstractIQHandler {
	def log = LoggerFactory.getLogger(OvalCollectionHandler.class)

	def packages = [
		"org.cisecurity.oval.collection.ind",
		"org.cisecurity.oval.collection",
		"org.cisecurity.oval.common",
		"org.cisecurity.oval.var",
		"org.cisecurity.xmldsig",
		"org.cisecurity.oval.sc.ind",
		"org.cisecurity.oval.sc"
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

//		def jaxbContext= JAXBContext.newInstance(packages)
//		def m = jaxbContext.createMarshaller()
//		def u = jaxbContext.createUnmarshaller()
//
//		def psw = new StringWriter()
//		m.marshal(collectionRequest, psw)
//
//		def xml = new XmlParser(false, true).parseText(psw.toString())
//		def xml2unmarshall = XmlUtil.serialize(xml)
//		def sr =  new StringReader(xml2unmarshall)//(xmltext)
//
//		//OvalObjects oo = JaxbExtensions.unmarshal(jaxbContext, xmltext, OvalObjects.class)
//		OvalObjects jaxbOvalObjects = u.unmarshal(sr)
//
//		//def ooNode = new org.cisecurity.oval.mdl.coll.OvalObjects().parse(collectionRequest)

		def timestamp = DatatypeFactory.newInstance().newXMLGregorianCalendar(new GregorianCalendar())
		def osc = new OvalSystemCharacteristics()
		osc.generator =
			new GeneratorType(
				productName: "OVAL XMPP",
				productVersion: "0.0.1",
				schemaVersion: [new SchemaVersionType (value: "6.0.0")],
				timestamp: timestamp)

		osc.systemInfo =
			new SystemInfoType(
				osName: System.properties["os.name"],
				osVersion: System.properties["os.version"],
				architecture: System.properties["os.arch"],
				interfaces: new InterfacesType()
			)

		if (collectionRequest.objects.object.size() > 0) {
			def itemCounter = 0

			osc.collectedObjects = new CollectedObjectsType()
			osc.systemData       = new SystemDataType()

			// Basic stuff to do collection before all the node parsing and eval is coded.
			collectionRequest.objects.object.each { o ->
				log.debug "Current object class name = ${o.class.simpleName}"
					switch (o.class.simpleName) {
						case "FamilyObject":
							def family = isWindows() ? "windows" : (isMac() ? "macos" : "unix")
							log.debug "Family Object --> ${family}"

							def itemId = ++itemCounter
							FamilyItem familyItem = new FamilyItem(id: itemId)
							EntityItemFamilyType t = new EntityItemFamilyType()
							t.setValue("windows")
							t.datatype = "string"
							familyItem.family = t

							CollectedObjectType fcot =
								new CollectedObjectType(
									id: o.id,
									version: o.version,
									comment: o.comment)
							fcot.reference << new ReferenceType(itemRef: itemId)

							osc.collectedObjects.collectedObject << fcot
							osc.systemData.collectedItem << familyItem

							break
						case "EnvironmentvariableObject":
							def matches = collectEnv(o.name)
							log.debug "Environment Variable Object --> ${matches}"

							if (matches.size() > 0) {
								CollectedObjectType ecot =
									new CollectedObjectType(
										id: o.id,
										version: o.version,
										comment: o.comment)

								matches.keySet().each { evn ->
									def evv = matches[evn]

									def itemId = ++itemCounter
									ecot.reference << new ReferenceType(itemRef: itemId)

									EnvironmentvariableItem evi =
										new EnvironmentvariableItem(
											id: itemId,
											name: new CollectedItemStringType(value: evn),
											value: new CollectedItemAnySimpleType(value: evv))
									osc.systemData.collectedItem << evi
								}

								osc.collectedObjects.collectedObject << ecot
							}
							break
						default:
							log.error "Unknown Object Type --> ${o.class.simpleName}"
							break
					}
			}
		}
		return iq.createResult(osc)//rtnOSC)
	}

	boolean isWindows() { return System.properties."os.name".toLowerCase().contains("windows") }
	boolean isMac() { return System.properties."os.name".toLowerCase().contains("mac") }
	def collectEnv(ObjectStringType name) {
		def rez = [:]

		StringComparator sc = new StringComparator()

		def env = System.env
		env.keySet().each { n ->
			def v = System.env[n]
			if (sc.compareForBoolean(n, String.valueOf(name.value), name.operation)) {
				rez[n] = v
			}
		}
		return rez
	}
}

class StringComparator {
	def log = LoggerFactory.getLogger(StringComparator.class)

	boolean compareForBoolean(String iv, String sv, OperationEnumeration op) {

		// Massage NULL to empty strings...
		if (iv == null) {
			iv = ""
		}
		if (sv == null) {
			sv = ""
		}

		return { i, s, o ->
			boolean rez = false

			switch (o) {
				case OperationEnumeration.EQUALS:
					rez = (i == s)
					break
				case OperationEnumeration.NOT_EQUAL:
					rez = (i != s)
					break
				case OperationEnumeration.CASE_INSENSITIVE_EQUALS:
					rez = i.equalsIgnoreCase(s)
					break
				case OperationEnumeration.CASE_INSENSITIVE_NOT_EQUAL:
					rez = !(i.equalsIgnoreCase(s))
					break
				case OperationEnumeration.PATTERN_MATCH:
					// groovy "match" operator is ==~
					// groovy "find" operator is =~
					//  The object's <name> is the regular expression
					rez = (i ==~ s || i =~ s)
					break
				default:
					rez = false
			}
			//log.info("StringComparator --> Comparing '${i}' to '${s}' using operator '${o}' yielded a result of '${rez}'")
			return rez
		}.call(iv, sv, op)
	}
}

//class NSM extends NamespacePrefixMapper {
//	def log = LoggerFactory.getLogger(NSM.class)
//
//	private static final String OVAL_COLL_PF = "oval-coll"
//	private static final String OVAL_COLL_NS = "http://oval.cisecurity.org/XMLSchema/oval-collections-6"
//	private static final String IND_COLL_PF = "ind-coll"
//	private static final String IND_COLL_NS = "http://oval.cisecurity.org/XMLSchema/oval-collections-6#independent"
//	private static final String OVAL_PF = "oval"
//	private static final String OVAL_NS = "http://oval.cisecurity.org/XMLSchema/oval-common-6"
//
//	@Override
//	String getPreferredPrefix(String namespaceUri, String suggestion, boolean requirePrefix) {
//		log.debug "URI=${namespaceUri}; Suggestion=${suggestion}; RequirePrefix=${requirePrefix}"
//
//		switch (namespaceUri) {
//			case OVAL_COLL_NS:
//				return OVAL_COLL_PF
//			case IND_COLL_NS:
//				return IND_COLL_PF
//			case OVAL_NS:
//				return OVAL_PF
//			default:
//				return suggestion
//		}
//	}
//
//	@Override
//	String[] getPreDeclaredNamespaceUris() {
//		return [OVAL_COLL_NS, IND_COLL_NS, OVAL_NS]
//	}
//}