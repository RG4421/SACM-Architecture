package org.cis.xmpp

import rocks.xmpp.core.session.XmppSession
import spock.lang.Specification

class XmppClientProxyTest extends Specification {

	def "Ensure the user can make an XMPP connection to the target XMPP server"() {
		given: "A username and password"
			Properties properties = new Properties()
			properties.load(getClass().getResourceAsStream("/test-conn.properties"))

			XmppClientProxy proxy = new XmppClientProxy(username: properties.user, password: properties.cred)
		when: "An XMPP connection is made"
			proxy.connect()
		then: "The user's connection status is AUTHENTICATED"
			assert proxy.getStatus() == XmppSession.Status.AUTHENTICATED
	}

	def "Ensure a connected XMPP client can send a message"() {
		given: "A connected XMPP client"
			Properties properties = new Properties()
			properties.load(getClass().getResourceAsStream("/test-conn.properties"))

			XmppClientProxy proxy = new XmppClientProxy(username: properties.user, password: properties.cred)
			proxy.connect()
		when: "A message is sent"
			proxy.sendMessage("bill", "Test Message")
		then: "Something good happens?"
			assert proxy.lastOutboundMessage == "Test Message; XMPP Rocks!"
	}
}
