package org.cisecurity.xmpp.svc

import rocks.xmpp.core.session.XmppClient

interface IIQHandlerService {
	/**
	 * Add the IQ handler to the XMPP client to process custom IQs
	 * @param xmppClient
	 * @return
	 */
	def addIQHandler(XmppClient xmppClient)
}