package org.cisecurity.xmpp.extensions.collection.oval

import org.cisecurity.oval.sc.OvalSystemCharacteristics
import org.slf4j.LoggerFactory
import rocks.xmpp.core.stanza.AbstractIQHandler
import rocks.xmpp.core.stanza.model.IQ

class OvalSystemCharacteristicsHandler extends AbstractIQHandler {
	def log = LoggerFactory.getLogger(OvalSystemCharacteristicsHandler.class)

	OvalSystemCharacteristicsHandler() {
		super(IQ.Type.GET)
	}

	/**
	 * Process the System Characteristics IQ
	 * This can dummy for now
	 * @param iq
	 * @return
	 */
	@Override
	protected final IQ processRequest(IQ iq) {
		log.debug("INSIDE OvalSystemCharacteristicsHandler::processRequest")
		return iq.createResult(iq.getExtension(OvalSystemCharacteristics.class))
	}
}
