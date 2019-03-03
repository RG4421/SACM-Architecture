package org.cis.xmpp.extensions.collection.sacm

import org.cis.xmpp.extensions.collection.sacm.handler.SacmCollectionHandler
import org.cis.xmpp.extensions.collection.sacm.model.Collections
import org.slf4j.LoggerFactory
import rocks.xmpp.core.session.Manager
import rocks.xmpp.core.session.XmppSession
import rocks.xmpp.core.stanza.IQHandler

class SacmCollectionManager extends Manager {
	def log = LoggerFactory.getLogger(SacmCollectionManager.class)

	private final IQHandler iqHandler

	private SacmCollectionManager(XmppSession xmppSession) {
		super(xmppSession)
		log.info "Constructing SACM Collection Manager Extension"
		iqHandler = new SacmCollectionHandler()
	}

	@Override
	protected void onEnable() {
		super.onEnable()
		log.info "Enabling SACM Collection Manager Extension"
		xmppSession.addIQHandler(Collections.class, iqHandler)
	}

	@Override
	protected void onDisable() {
		super.onDisable()
		log.info "Disabling SACM Collection Manager Extension"
		xmppSession.removeIQHandler(Collections.class)
	}

	@Override
	protected void initialize() {
		log.info "Initializing SACM Collection Manager Extension"
	}
}
