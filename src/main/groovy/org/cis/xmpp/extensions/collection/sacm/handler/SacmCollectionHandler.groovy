package org.cis.xmpp.extensions.collection.sacm.handler

import rocks.xmpp.core.stanza.AbstractIQHandler
import rocks.xmpp.core.stanza.model.IQ

class SacmCollectionHandler extends AbstractIQHandler {
	SacmCollectionHandler() {
		super(IQ.Type.GET)
	}

	/**
	 * Process a SACM-modeled collection request.  This probably requires a SACM collector, eh?
	 * @param iq
	 * @return
	 */
	@Override
	protected final IQ processRequest(IQ iq) {

	}
}
