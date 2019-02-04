package org.cis.xmpp

import org.cis.xmpp.exc.XmppConnectionInvalidCredentialsException
import org.cis.xmpp.exc.XmppConnectionInvalidException
import org.cis.xmpp.trust.TrustAllX509TrustManager
import rocks.xmpp.addr.Jid
import rocks.xmpp.core.net.client.SocketConnectionConfiguration
import rocks.xmpp.core.session.XmppClient
import rocks.xmpp.core.session.XmppSession
import rocks.xmpp.core.stanza.model.Message
import rocks.xmpp.core.stanza.model.Presence
import rocks.xmpp.im.roster.RosterManager
import rocks.xmpp.im.roster.model.Contact

import javax.net.ssl.HostnameVerifier
import javax.net.ssl.SSLContext
import javax.net.ssl.SSLSession
import javax.net.ssl.TrustManager
import java.security.SecureRandom

class XmppClientProxy {
	final def DOMAIN = "ip-0a1e0af4"

	XmppClient xmppClient

	def username
	def password

	def lastOutboundMessage = ""

	def connect() {
		if (!xmppClient) {
			initialize()
		}

		if (username && password) {
			xmppClient.login(username, password)
		} else {
			throw new XmppConnectionInvalidCredentialsException("Invalid XMPP Credentials")
		}
	}

	def close() {
		if (xmppClient) {
			xmppClient.close()
		}
	}

	def sendMessage(String to, String msg) {
		if (getStatus() == XmppSession.Status.AUTHENTICATED) {
			xmppClient.sendMessage(
				new Message(
					Jid.of("${to}@${DOMAIN}"),
					Message.Type.CHAT,
					msg)
			)
		} else {
			println getStatus()
			throw new XmppConnectionInvalidException("XMPP Session Invalid")
		}
	}

	def getStatus() {
		return xmppClient ? xmppClient.getStatus() : XmppSession.Status.DISCONNECTED
	}

	def initialize() {
		// Create a TLS context
		final SSLContext sc = SSLContext.getInstance("TLS")

		TrustManager[] tm = [new TrustAllX509TrustManager()]

		// Set our custom trust manager
		sc.init(null, tm, new SecureRandom())

		SocketConnectionConfiguration tcpConfiguration = SocketConnectionConfiguration.builder()
			.hostname(DOMAIN)
			.port(5222)
			.sslContext(sc)
			.hostnameVerifier(new HostnameVerifier() {
			boolean verify(String urlHostName, SSLSession session) {
				return true
			}
		})
			.build()

		xmppClient = XmppClient.create(DOMAIN, tcpConfiguration)

		xmppClient.addOutboundPresenceListener { e ->
			Presence presence = e.getPresence()
		}

		// Listen for presence changes
		xmppClient.addInboundPresenceListener { e ->
			Presence presence = e.getPresence()

			// Deal with Presence updates
			Contact contact = xmppClient.getManager(RosterManager.class).getContact(presence.getFrom())
			if (contact) {
				// contact's presence has updated.
			}

			// Presence Subscription requests
			if (presence.getType() == Presence.Type.SUBSCRIBE) {
				// presence.getFrom() wants to subscribe to your presence.

				// Approve:
				//xmppClient.getManager(PresenceManager.class).approveSubscription(presence.getFrom())

				// Deny:
				//xmppClient.getManager(PresenceManager.class).denySubscription(presence.getFrom())
			}
		}

		// Listen for messages
		xmppClient.addInboundMessageListener { e ->
			Message message = e.getMessage()
			println "[From]: ${message.from}; [BODY]: ${message.body}"
		}

		// Listen for messages
		xmppClient.addOutboundMessageListener { e ->
			e.getMessage().body += "; XMPP Rocks!"

			lastOutboundMessage = e.getMessage().body
		}

		// Listen for roster pushes
		xmppClient.getManager(RosterManager.class).addRosterListener { e ->
			// Roster has changed
		}

		xmppClient.connect()
	}
}
