package org.cisecurity.xmpp

import org.cisecurity.oval.collection.ind.EnvironmentvariableObject
import org.cisecurity.oval.collection.ind.FamilyObject
import org.cisecurity.oval.sc.ind.EnvironmentvariableItem
import org.cisecurity.oval.sc.ind.FamilyItem
import org.cisecurity.xmpp.exc.XmppConnectionInvalidCredentialsException
import org.cisecurity.xmpp.exc.XmppConnectionInvalidException
import org.cisecurity.xmpp.extensions.collection.sacm.model.DatatypeEnumeration
import org.cisecurity.xmpp.svc.AdditionServiceDemo
import org.cisecurity.xmpp.svc.AssessmentContentService
import org.cisecurity.xmpp.svc.CollectionsService
import org.cisecurity.oval.collection.OvalObjects
import org.cisecurity.oval.sc.OvalSystemCharacteristics
import org.cisecurity.xmpp.addition.model.Addition
import org.cisecurity.xmpp.extensions.collection.oval.OvalCollectionManager
import org.cisecurity.xmpp.extensions.collection.sacm.SacmCollectionManager
import org.cisecurity.xmpp.extensions.collection.sacm.model.Collection
import org.cisecurity.xmpp.extensions.collection.sacm.model.CollectionFields
import org.cisecurity.xmpp.extensions.collection.sacm.model.CollectionType
import org.cisecurity.xmpp.extensions.collection.sacm.model.Collections
import org.cisecurity.xmpp.extensions.collection.sacm.model.FamilyEnumeration
import org.cisecurity.xmpp.extensions.collection.sacm.model.FieldType
import org.cisecurity.xmpp.extensions.collection.sacm.model.OperationEnumeration
import org.cisecurity.xmpp.trust.TrustAllX509TrustManager
import org.ietf.sacm.list.model.AssessmentContent
import rocks.xmpp.addr.Jid
import rocks.xmpp.core.net.client.SocketConnectionConfiguration
import rocks.xmpp.core.session.Extension
import rocks.xmpp.core.session.XmppClient
import rocks.xmpp.core.session.XmppSession
import rocks.xmpp.core.session.XmppSessionConfiguration
import rocks.xmpp.core.session.debug.ConsoleDebugger
import rocks.xmpp.core.stanza.model.IQ
import rocks.xmpp.core.stanza.model.Message
import rocks.xmpp.core.stanza.model.StanzaErrorException
import rocks.xmpp.core.stanza.model.errors.Condition
import rocks.xmpp.extensions.disco.ServiceDiscoveryManager
import rocks.xmpp.extensions.disco.model.info.InfoNode
import rocks.xmpp.extensions.filetransfer.FileTransfer
import rocks.xmpp.extensions.filetransfer.FileTransferManager
import rocks.xmpp.extensions.last.LastActivityManager
import rocks.xmpp.extensions.last.model.LastActivity
import rocks.xmpp.extensions.pubsub.PubSubManager
import rocks.xmpp.extensions.pubsub.PubSubNode
import rocks.xmpp.extensions.pubsub.PubSubService
import rocks.xmpp.im.roster.RosterManager
import javax.net.ssl.HostnameVerifier
import javax.net.ssl.SSLContext
import javax.net.ssl.SSLSession
import javax.net.ssl.TrustManager
import java.nio.file.Paths
import java.security.SecureRandom
import java.time.Duration
import java.util.concurrent.TimeUnit

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

	def discoverServices(Jid anotherEntity) {
		ServiceDiscoveryManager serviceDiscoveryManager = xmppClient.getManager(ServiceDiscoveryManager.class)
		//serviceDiscoveryManager.addFeature("http://cisecurity.org/sacm/sacm-collection")

		InfoNode infoNode = serviceDiscoveryManager.discoverInformation(anotherEntity).result

		println "---> DISCO#INFO"
		println "- FEATURES:"
		infoNode.features.each { f -> println f }
		println "- IDENTITIES:"
		infoNode.identities.each { i -> println "${i.name} (${i.type})" }
		println "- EXTENSIONS:"
		infoNode.extensions.each { e -> println e.items.collect() }

		//serviceDiscoveryManager.setItemProvider("sacm", ResultSetProvider.forItems([]))
//		println "DISCO#ITEMS"
//		ItemNode node = serviceDiscoveryManager.discoverItems(anotherEntity).result
//		println node.node
//		node.items.each { i -> println i.name}
		println "SACM? ${xmppClient.isSupported("http://cisecurity.org/sacm/sacm-collection", anotherEntity).result}"

		xmppClient.getEnabledFeatures().each { f -> println f}
	}

	def offerFile(Jid anotherEntity) {
		println "OFFERING FILE (I AM --> ${xmppClient.connectedResource})"
		def p = "C:\\_Development\\Projects\\Assessor-Shared\\benchmarks\\Microsoft Windows 10 Release 1709"
		def n = "${p}\\CIS_Microsoft_Windows_10_Enterprise_Release_1709_Benchmark_v1.4.0-xccdf.xml"
		//def n = "${p}\\CIS_Microsoft_Windows_10_Enterprise_Release_1709_Benchmark_v1.4.0-cpe-dictionary.xml"
		def f = new File(n)
		def d = Duration.ofSeconds(60, 0)
		FileTransferManager fileTransferManager = xmppClient.getManager(FileTransferManager.class)
		FileTransfer ft = fileTransferManager.offerFile(f, "Win10", anotherEntity, d).result
		return ft.transfer()
	}

	def lastActivity(Jid anotherEntity) {
		LastActivityManager lastActivityManager = xmppClient.getManager(LastActivityManager.class)
		LastActivity lastActivity = lastActivityManager.getLastActivity(anotherEntity).getResult()
		return lastActivity.seconds
	}

	def createNodes() {
		PubSubManager pubSubManager = xmppClient.getManager(PubSubManager.class)
		PubSubService pubSubService = pubSubManager.createPubSubService(Jid.of(xmppClient.connectedResource.domain))
		//Collection<PubSubFeature> pubSubFeatures = pubSubService.discoverFeatures().result
		PubSubNode policyNode = pubSubService.node("policy")
		policyNode.create()
		PubSubNode actualsNode = pubSubService.node("actuals")
		actualsNode.create()
		def b
	}

	def ovalCollection(Jid anotherEntity) {
		def oo = new OvalObjects()

		OvalCollectionManager m = xmppClient.getManager(OvalCollectionManager.class)
		OvalSystemCharacteristics ovalSystemCharacteristics = m.collect(anotherEntity, oo).getResult()
		return ovalSystemCharacteristics
	}

	def ovalCollection(Jid anotherEntity, OvalObjects ovalObjects) {
		OvalCollectionManager m = xmppClient.getManager(OvalCollectionManager.class)
		OvalSystemCharacteristics ovalSystemCharacteristics = m.collect(anotherEntity, ovalObjects).getResult()
		return ovalSystemCharacteristics
	}

	/**
	 * This entity tells the collector to collect.
	 * The collector returns system characteristics to this entity
	 * This entity sends the system characteristics to the repository entity
	 * @param collector
	 * @param ovalObjects
	 * @param repository
	 * @return
	 */
	def ovalCollection(Jid collector, OvalObjects ovalObjects, Jid repository) {
		OvalCollectionManager manager = xmppClient.getManager(OvalCollectionManager.class)
		OvalSystemCharacteristics ovalSystemCharacteristics =
			manager.collectAndForward(collector, ovalObjects, repository).getResult()

		return ovalSystemCharacteristics
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

		final String INDEPENDENT_COLLECTION_NS = "http://oval.cisecurity.org/XMLSchema/oval-collections-6#independent"
		final String INDEPENDENT_SYSCHAR_NS    = "http://oval.cisecurity.org/XMLSchema/oval-system-characteristics-6#independent"

		// Configure the XMPP session configuration
		XmppSessionConfiguration collectionsConfiguration = XmppSessionConfiguration.builder()
			.extensions(
				Extension.of(INDEPENDENT_COLLECTION_NS, false, FamilyObject.class, EnvironmentvariableObject.class),
				Extension.of(INDEPENDENT_SYSCHAR_NS, false, FamilyItem.class, EnvironmentvariableItem.class),
				Extension.of(OvalObjects.NAMESPACE, OvalCollectionManager.class, true, OvalObjects.class, OvalSystemCharacteristics.class), // Include OVAL-6 collections
				Extension.of(OvalSystemCharacteristics.NAMESPACE, OvalCollectionManager.class, true, OvalObjects.class, OvalSystemCharacteristics.class), // Include OVAL-6 system characteristics
				//Extension.of(Collections.NAMESPACE, SacmCollectionManager.class, true, Collections.class), // This includes the extension in a disco#info response
				//Extension.of(AssessmentContent.class),
				Extension.of(Addition.class))
			.debugger(ConsoleDebugger.class)
			//.defaultResponseTimeout(Duration.ofSeconds(30))
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

//		xmppClient.addOutboundIQListener{ e ->
//			println "[OUTBOUND IQ] ${e.IQ}"
//		}
//		xmppClient.addInboundIQListener { e ->
//			println "[INBOUND IQ] ${e.IQ}"
//		}

		// Listen for roster pushes
		xmppClient.getManager(RosterManager.class).addRosterListener { e ->
			// Roster has changed
		}


		xmppClient.getManager(FileTransferManager.class).addFileTransferOfferListener { e ->
			println "ACCEPTING FILE TRANSFER (I AM --> ${xmppClient.connectedResource})"
			FileTransfer ft = e.accept(Paths.get(e.getName())).result

			println "ADDING FILE TRANSFER STATUS LISTENER"
			ft.addFileTransferStatusListener { l ->
				if (l.status == FileTransfer.Status.COMPLETED) { println "${l.status}; ${l.bytesTransferred}" }
			}

			println "TRANSFERRING"
			ft.transfer().get(15, TimeUnit.SECONDS)

			println "EXCEPTION?"
			if (ft.exception) { println ft.exception.localizedMessage }

			println "File Transfer --> Status: ${ft.status}; Bytes: ${ft.bytesTransferred}"
		}

		xmppClient.connect()

//		Item
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
