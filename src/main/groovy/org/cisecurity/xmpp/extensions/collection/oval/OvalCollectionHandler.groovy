package org.cisecurity.xmpp.extensions.collection.oval

import org.cisecurity.oval.OvalCollectionSystem
import org.cisecurity.oval.collection.ObjectStringType
import org.cisecurity.oval.collection.OvalObjects
import org.cisecurity.oval.collector.Session
import org.cisecurity.oval.collector.cmp.StringComparator
import org.cisecurity.oval.common.GeneratorType
import org.cisecurity.oval.common.OperationEnumeration
import org.cisecurity.oval.common.SchemaVersionType
import org.cisecurity.oval.fact.CollectorFactoryFactory
import org.cisecurity.oval.fact.ICollector
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

		// Marshall the collectionRequest to a groovy node
		// Provide the groovy node as input to the OVAL collector
		// Invoke the OVAL collector using a local session
		// Unmarshall the returned system characteristics
		// Create the IQ result stanza

		OvalSystemCharacteristics osc = new OvalCollectionSystem(collectionRequest: collectionRequest).collect()

		return iq.createResult(osc)

//		def timestamp = DatatypeFactory.newInstance().newXMLGregorianCalendar(new GregorianCalendar())
//		def osc = new OvalSystemCharacteristics(collectionRef: collectionRequest.collectionId)
//		osc.generator =
//			new GeneratorType(
//				productName: "OVAL XMPP",
//				productVersion: "0.0.1",
//				schemaVersion: [new SchemaVersionType (value: "6.0.0")],
//				timestamp: timestamp)
//
//		osc.systemInfo =
//			new SystemInfoType(
//				osName: System.properties["os.name"],
//				osVersion: System.properties["os.version"],
//				architecture: System.properties["os.arch"],
//				interfaces: new InterfacesType(),
//				primaryHostName: System.env["COMPUTERNAME"]
//			)
//
//		if (collectionRequest.objects.object.size() > 0) {
//			Session session = new Session()
//
//			osc.collectedObjects = new CollectedObjectsType()
//			osc.systemData       = new SystemDataType()
//
//			// Basic stuff to do collection before all the node parsing and eval is coded.
//			collectionRequest.objects.object.each { o ->
//				log.debug "Current object class name = ${o.class.simpleName}"
//				log.debug "Current object package    = ${o.class.package.name}"
//
//				ICollector collector =
//					CollectorFactoryFactory.getCollectorFactory(o.class.package.name)
//						.getCollector(o.class.simpleName)
//
//				if (collector) {
//					def (collectedObject, collectedItems) = collector.collect(session, o)
//
//					osc.collectedObjects.collectedObject << collectedObject
//					osc.systemData.collectedItem.addAll(collectedItems)
//				}
//			}
//		}
//		return iq.createResult(osc)
	}
}
