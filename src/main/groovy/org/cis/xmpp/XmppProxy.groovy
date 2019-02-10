package org.cis.xmpp

import org.apache.groovy.jaxb.extensions.JaxbExtensions
import org.cis.xmpp.exc.XmppConnectionInvalidCredentialsException
import org.cis.xmpp.exc.XmppConnectionInvalidException
import org.cis.xmpp.trust.TrustAllX509TrustManager
import org.ietf.sacm.collection.*
import rocks.xmpp.addr.Jid
import rocks.xmpp.core.net.client.SocketConnectionConfiguration
import rocks.xmpp.core.session.Extension
import rocks.xmpp.core.session.XmppClient
import rocks.xmpp.core.session.XmppSession
import rocks.xmpp.core.session.XmppSessionConfiguration
import rocks.xmpp.core.session.debug.ConsoleDebugger
import rocks.xmpp.core.stanza.IQHandler
import rocks.xmpp.core.stanza.model.IQ
import rocks.xmpp.core.stanza.model.Message
import rocks.xmpp.debug.gui.VisualDebugger
import rocks.xmpp.im.roster.RosterManager

import javax.net.ssl.HostnameVerifier
import javax.net.ssl.SSLContext
import javax.net.ssl.SSLSession
import javax.net.ssl.TrustManager
import javax.xml.bind.JAXBContext
import java.security.SecureRandom

class XmppProxy {
	XmppClient   xmppClient
	String       xmppDomain
	//SwingBuilder swingBuilder
	Closure callback

	/**
	 *
	 * @param user
	 * @return
	 */
	def connect(User user) {
		if (!xmppClient) {
			initializeXmpp()
		}

		if (user.username && user.password) {
			xmppClient.login(user.username, user.password)
		} else {
			throw new XmppConnectionInvalidCredentialsException("Invalid XMPP Credentials")
		}
	}

	/**
	 *
	 * @return
	 */
	def close() {
		if (xmppClient) {
			println "Closing XMPP client..."
			xmppClient.close()
		}
	}

	/**
	 *
	 * @param chat
	 * @return
	 */
	def sendMessage(Chat chat) {
		if (getStatus() == XmppSession.Status.AUTHENTICATED) {
			println chat
//			xmppClient.sendMessage(
//				new Message(
//					Jid.of("${chat.to}@${xmppDomain}"),
//					Message.Type.CHAT,
//					chat.msg)
//			)
			Collections sample = createSample()

			IQ resultIQ = xmppClient.query(IQ.get(Jid.of("${chat.to}@${xmppDomain}"), sample)).getResult()
			callback(resultIQ.getExtension(Collections.class))
		} else {
			println getStatus()
			throw new XmppConnectionInvalidException("XMPP Session Invalid")
		}
	}

	/**
	 *
	 * @return
	 */
	def getStatus() {
		return xmppClient ? xmppClient.getStatus() : XmppSession.Status.DISCONNECTED
	}

	/**
	 *
	 * @return
	 */
	def initializeXmpp() {
		println "Initialize XMPP"
		// Create a TLS context
		final SSLContext sc = SSLContext.getInstance("TLS")

		TrustManager[] tm = [new TrustAllX509TrustManager()]

		// Set our custom trust manager
		sc.init(null, tm, new SecureRandom())

		SocketConnectionConfiguration tcpConfiguration = SocketConnectionConfiguration.builder()
			.hostname(xmppDomain)
			.port(5222)
			.sslContext(sc)
			.hostnameVerifier(new HostnameVerifier() {
			boolean verify(String urlHostName, SSLSession session) {
				return true
			}
		}).build()

		// Register the SACM collections
		XmppSessionConfiguration collectionsConfiguration = XmppSessionConfiguration.builder()
			.extensions(Extension.of(Collections.class))
			.debugger(ConsoleDebugger.class)
			.build()

		xmppClient = XmppClient.create(xmppDomain, collectionsConfiguration, tcpConfiguration)

		xmppClient.addOutboundPresenceListener { e ->
			println "[OUTBOUND PRESENCE]: ${e.getPresence()}"
		}

		// Listen for presence changes
		xmppClient.addInboundPresenceListener { e ->
			println "[INBOUND PRESENCE]: ${e.getPresence()}"
//			Presence presence = e.getPresence()
//
//			// Deal with Presence updates
//			Contact contact = xmppClient.getManager(RosterManager.class).getContact(presence.getFrom())
//			if (contact) {
//				// contact's presence has updated.
//			}
//
//			// Presence Subscription requests
//			if (presence.getType() == Presence.Type.SUBSCRIBE) {
//				// presence.getFrom() wants to subscribe to your presence.
//
//				// Approve:
//				//xmppClient.getManager(PresenceManager.class).approveSubscription(presence.getFrom())
//
//				// Deny:
//				//xmppClient.getManager(PresenceManager.class).denySubscription(presence.getFrom())
//			}
		}

		// Listen for messages
		xmppClient.addInboundMessageListener { e -> fmt(e) }

		// Listen for messages
		xmppClient.addOutboundMessageListener { e -> fmt(e) }

		// IQ Handler
		xmppClient.addIQHandler(Collections.class, new IQHandler() {
			@Override
			IQ handleRequest(IQ iq) {
				println "[ADD IQ HANDLER] ${iq}"
				Collections collections = iq.getExtension(Collections.class)
				if (collections) {
					println "Marshalling IQ within 'addIQHandler'"
					println JaxbExtensions.marshal(JAXBContext.newInstance(Collections.class), collections)
					return iq.createResult(collections)
				}
				return iq.createResult()
			}
		})

		xmppClient.addInboundIQListener { e ->
			IQ iq = e.getIQ()
			println iq
			Collections collections = iq.getExtension(Collections.class)
			if (collections) {
				println "Marshalling IQ within 'addInboundIQListener'"
				println JaxbExtensions.marshal(JAXBContext.newInstance(Collections.class), collections)
				return iq.createResult(collections)
			}
			return iq.createResult()
		}

		// Listen for roster pushes
		xmppClient.getManager(RosterManager.class).addRosterListener { e ->
			// Roster has changed
		}

		xmppClient.connect()
	}

	/**
	 * Format a message and add it to the conversation
	 */
	def fmt = { e ->
		Message message = e.getMessage()
		def from = message.from ?: xmppClient.getConnectedResource().toString()
		def fm = "[From]: ${from}; [BODY]: ${message.body}"

		println fm

		//swingBuilder."conversation".text += fm + System.lineSeparator()
		callback(fm)
	}

	Collections createSample() {
		Collections sacmCollections = new Collections()

		def collectionType = new CollectionType()
		collectionType.setFamily(FamilyEnumeration.WINDOWS)
		collectionType.setType("file")

		def collectionFields = new CollectionFields()
		def pathField = new FieldType()
		pathField.setName("path")
		pathField.setDatatype(DatatypeEnumeration.STRING)
		pathField.setOperation(OperationEnumeration.CASE_INSENSITIVE_EQUALS)
		pathField.setValue("C:\\Temp")

		def filenameField = new FieldType()
		filenameField.setName("filename")
		filenameField.setDatatype(DatatypeEnumeration.STRING)
		filenameField.setOperation(OperationEnumeration.CASE_INSENSITIVE_EQUALS)
		filenameField.setValue("out.html")

		collectionFields.collectionField.add(pathField)
		collectionFields.collectionField.add(filenameField)

		def sacmCollection = new Collection()
		sacmCollection.setCollectionId(100)
		sacmCollection.setCollectionType(collectionType)
		sacmCollection.setCollectionFields(collectionFields)

		sacmCollections.collection.add(sacmCollection)

		return sacmCollections
	}
}
