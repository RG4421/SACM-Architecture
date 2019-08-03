package org.cisecurity.xmpp.extensions.collection.oval

import org.cisecurity.oval.collection.OvalObjects
import org.cisecurity.oval.sc.OvalSystemCharacteristics
import org.slf4j.LoggerFactory
import rocks.xmpp.core.stanza.AbstractIQHandler
import rocks.xmpp.core.stanza.model.IQ

class OvalCollectionHandler extends AbstractIQHandler {
	def log = LoggerFactory.getLogger(OvalCollectionHandler.class)

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
	}
}
