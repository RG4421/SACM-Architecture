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
import rocks.xmpp.extensions.pubsub.PubSubManager
import rocks.xmpp.extensions.pubsub.PubSubNode
import rocks.xmpp.extensions.pubsub.PubSubService
import rocks.xmpp.util.concurrent.AsyncResult

class OvalCollectionManager extends Manager {
	def log = LoggerFactory.getLogger(OvalCollectionManager.class)

	private final IQHandler collectionHandler
	private final IQHandler systemCharacteristicsHandler

	private OvalCollectionManager(XmppSession session) {
		super(session)

		log.debug "Constructing OVAL Collection Manager"
		collectionHandler = new OvalCollectionHandler()
		systemCharacteristicsHandler = new OvalSystemCharacteristicsHandler()
	}

	@Override
	protected void onEnable() {
		super.onEnable()
		log.info "Enabling OVAL Collection Manager"
		xmppSession.addIQHandler(OvalObjects.class, collectionHandler)
		xmppSession.addIQHandler(OvalSystemCharacteristics.class, systemCharacteristicsHandler)
	}

	@Override
	protected void onDisable() {
		super.onDisable()
		log.info "Disabling OVAL Collection Manager"
		xmppSession.removeIQHandler(OvalObjects.class)
		xmppSession.removeIQHandler(OvalSystemCharacteristics.class)
	}

	@Override
	protected void initialize() {
		log.info "Initializing OVAL Collection Manager"
	}

	AsyncResult<OvalSystemCharacteristics> collect(Jid jid, OvalObjects ovalObjects) {
		log.debug "Calling XMPPSession.query"
		AsyncResult<OvalSystemCharacteristics> rtn =
			xmppSession.query(IQ.get(jid, ovalObjects), OvalSystemCharacteristics.class)
		return rtn
	}

	AsyncResult<OvalSystemCharacteristics> collectAndForward(Jid jid, OvalObjects ovalObjects, Jid recipient) {
		log.debug "Collecting System Characteristics"
		OvalSystemCharacteristics osc = collect(jid, ovalObjects).getResult()

		log.debug "Sending System Characteristics to Recipient JID"
		AsyncResult<OvalSystemCharacteristics> rtn =
			xmppSession.query(IQ.get(recipient, osc), OvalSystemCharacteristics.class)

		return rtn
	}
}
