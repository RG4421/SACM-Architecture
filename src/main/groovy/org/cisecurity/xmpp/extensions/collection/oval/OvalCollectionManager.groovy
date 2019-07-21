package org.cisecurity.xmpp.extensions.collection.oval

import org.cisecurity.oval.collection.OvalObjects
import org.cisecurity.oval.sc.OvalSystemCharacteristics
import org.slf4j.LoggerFactory
import rocks.xmpp.addr.Jid
import rocks.xmpp.core.session.Manager
import rocks.xmpp.core.session.XmppSession
import rocks.xmpp.core.stanza.IQHandler
import rocks.xmpp.core.stanza.model.IQ
import rocks.xmpp.core.stanza.model.Stanza
import rocks.xmpp.util.concurrent.AsyncResult

class OvalCollectionManager extends Manager {
	def log = LoggerFactory.getLogger(OvalCollectionManager.class)

	private final IQHandler iqHandler

	private OvalCollectionManager(XmppSession session) {
		super(session)

		log.debug "Constructing OVAL Collection Manager"
		iqHandler = new OvalCollectionHandler()
	}

	@Override
	protected void onEnable() {
		super.onEnable()
		log.info "Enabling OVAL Collection Manager"
		xmppSession.addIQHandler(OvalObjects.class, iqHandler)
	}

	@Override
	protected void onDisable() {
		super.onDisable()
		log.info "Disabling OVAL Collection Manager"
		xmppSession.removeIQHandler(OvalObjects.class)
	}

	@Override
	protected void initialize() {
		log.info "Initializing OVAL Collection Manager"
	}

	AsyncResult<OvalSystemCharacteristics> collect(Jid jid, OvalObjects ovalObjects) {
		log.debug "Sending IQ.GET with OVAL Objects"
		IQ response = IQ.get(jid, ovalObjects)
		log.debug "Received IQ Response from OVAL Objects"
		log.debug response.toString()

		log.debug "Calling XMPPSession.query"
		AsyncResult<OvalSystemCharacteristics> rtn =
			xmppSession.query(response, OvalSystemCharacteristics.class)
		return rtn
	}
}
