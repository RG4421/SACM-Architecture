package org.cis.xmpp.svc

import org.cis.xmpp.addition.model.Addition
import rocks.xmpp.core.session.XmppClient
import rocks.xmpp.core.stanza.IQHandler
import rocks.xmpp.core.stanza.model.IQ
import rocks.xmpp.core.stanza.model.StanzaError
import rocks.xmpp.core.stanza.model.errors.Condition

class AdditionServiceDemo implements IIQHandlerService {

	/**
	 * Add the IQ handler to the XMPP client to process Assessment Content related custom IQs
	 * @param xmppClient
	 * @return
	 */
	def addIQHandler(XmppClient xmppClient) {
		xmppClient.addIQHandler(Addition.class, new IQHandler() {
			@Override
			IQ handleRequest(IQ iq) {
				Addition addition = iq.getExtension(Addition.class);
				if (addition.getSummand1() == null) {
					// This is how you would return an error.
					return iq.createError(new StanzaError(Condition.BAD_REQUEST, "No summand provided."))
				}
				return iq.createResult(new Addition(addition.getSummand1() + addition.getSummand2()))
			}
		})
	}
}
