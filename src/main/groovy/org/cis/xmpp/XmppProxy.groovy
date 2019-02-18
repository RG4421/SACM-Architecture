package org.cis.xmpp

import org.cis.xmpp.addition.model.Addition
import org.cis.xmpp.exc.XmppConnectionInvalidCredentialsException
import org.cis.xmpp.exc.XmppConnectionInvalidException
import org.cis.xmpp.svc.AdditionServiceDemo
import org.cis.xmpp.svc.AssessmentContentService
import org.cis.xmpp.svc.CollectionsService
import org.cis.xmpp.trust.TrustAllX509TrustManager
import org.ietf.sacm.collection.*
import org.ietf.sacm.list.model.AssessmentContent
import org.ietf.sacm.list.model.AssessmentContentResource
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
import rocks.xmpp.core.stanza.model.StanzaError
import rocks.xmpp.core.stanza.model.StanzaErrorException
import rocks.xmpp.core.stanza.model.errors.Condition
import rocks.xmpp.im.roster.RosterManager

import javax.net.ssl.HostnameVerifier
import javax.net.ssl.SSLContext
import javax.net.ssl.SSLSession
import javax.net.ssl.TrustManager
import java.security.SecureRandom

class XmppProxy {
	XmppClient   xmppClient
	String       xmppDomain
	//SwingBuilder swingBuilder
	Closure callback

	def iqHandlerServices = [
		new AssessmentContentService(),
		new AdditionServiceDemo(),
		new CollectionsService()
	]

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
			xmppClient.login(user.username, user.password, user.resource)
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
//			println chat
//			xmppClient.sendMessage(
//				new Message(
//					Jid.of("${chat.to}@${xmppDomain}"),
//					Message.Type.CHAT,
//					chat.msg)
//			)
			Collections sample = createSample()
			IQ resultIQ0 = xmppClient.query(IQ.get(Jid.of("${chat.to}@${xmppDomain}/resource"), sample)).getResult()
			callback(resultIQ0.getExtension(Collections.class))

			Jid to   = Jid.of("${chat.to}@${xmppDomain}/resource")
			Jid from = Jid.of("orchestrator@ip-0a1e0af4/resource")
			AssessmentContent assessmentContent = new AssessmentContent("list")
			IQ requestIQ = new IQ(
				to, // to JID
				IQ.Type.GET,                        // type
				assessmentContent,                  // extension
				null,                               // id (null == generate one)
				from,                               // from JID
				null,                               // Locale
				null)                               // StanzaError
			IQ resultIQ = xmppClient.query(requestIQ).getResult()
			callback(resultIQ.getExtension(AssessmentContent.class))

			try {
				Addition addition = new Addition(52, 22)

				// Request the sum of two values (52 + 22). The requester will calculate it for you and return a result.
				IQ resultIQ2 = xmppClient.query(IQ.get(Jid.of("${chat.to}@${xmppDomain}/resource"), addition)).getResult()

				callback(resultIQ2.getExtension(Addition.class))
			} catch (StanzaErrorException s) {
				if (s.getCondition() == Condition.SERVICE_UNAVAILABLE) {
					def b
				}
			}

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
			.extensions(
				Extension.of(Collections.class),
				Extension.of(AssessmentContent.class),
				Extension.of(Addition.class))
			.debugger(ConsoleDebugger.class)
			.build()

		xmppClient = XmppClient.create(xmppDomain, collectionsConfiguration, tcpConfiguration)

//		xmppClient.addOutboundPresenceListener { e ->
//			println "[OUTBOUND PRESENCE]: ${e.getPresence()}"
//		}
//
//		// Listen for presence changes
//		xmppClient.addInboundPresenceListener { e ->
//			println "[INBOUND PRESENCE]: ${e.getPresence()}"
////			Presence presence = e.getPresence()
////
////			// Deal with Presence updates
////			Contact contact = xmppClient.getManager(RosterManager.class).getContact(presence.getFrom())
////			if (contact) {
////				// contact's presence has updated.
////			}
////
////			// Presence Subscription requests
////			if (presence.getType() == Presence.Type.SUBSCRIBE) {
////				// presence.getFrom() wants to subscribe to your presence.
////
////				// Approve:
////				//xmppClient.getManager(PresenceManager.class).approveSubscription(presence.getFrom())
////
////				// Deny:
////				//xmppClient.getManager(PresenceManager.class).denySubscription(presence.getFrom())
////			}
//		}

		// Listen for messages
		xmppClient.addInboundMessageListener { e -> fmt(e) }

		// Listen for messages
		xmppClient.addOutboundMessageListener { e -> fmt(e) }

		// IQ HANDLERS
		iqHandlerServices.each { iqh -> iqh.addIQHandler(xmppClient) }

		xmppClient.addOutboundIQListener{ e ->
			println "[OUTBOUND IQ] ${e.IQ}"
		}
		xmppClient.addInboundIQListener { e ->
			println "[INBOUND IQ] ${e.IQ}"
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

	AssessmentContent createAssessmentContentRequest() {
		return new AssessmentContent()
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
