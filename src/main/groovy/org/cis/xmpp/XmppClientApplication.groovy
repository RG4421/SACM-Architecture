package org.cis.xmpp

import groovy.swing.SwingBuilder
import org.cis.xmpp.exc.XmppConnectionInvalidCredentialsException
import org.cis.xmpp.exc.XmppConnectionInvalidException
import org.cis.xmpp.trust.TrustAllX509TrustManager
import rocks.xmpp.addr.Jid
import rocks.xmpp.core.net.client.SocketConnectionConfiguration
import rocks.xmpp.core.session.XmppClient
import rocks.xmpp.core.session.XmppSession
import rocks.xmpp.core.stanza.model.IQ
import rocks.xmpp.core.stanza.model.Message
import rocks.xmpp.core.stanza.model.Presence
import rocks.xmpp.extensions.disco.ServiceDiscoveryManager
import rocks.xmpp.extensions.time.EntityTimeManager
import rocks.xmpp.im.roster.RosterManager
import rocks.xmpp.im.roster.model.Contact

import javax.net.ssl.HostnameVerifier
import javax.net.ssl.SSLContext
import javax.net.ssl.SSLSession
import javax.net.ssl.TrustManager
import javax.swing.*
import java.awt.*
import java.awt.event.WindowAdapter
import java.security.SecureRandom

/**
 * Basic GUI that connects to XMPP
 */
class XmppClientApplication {

	final String DOMAIN = "ip-0a1e0af4"

	XmppClient xmppClient

	static void main(String[] args) {
		new XmppClientApplication().start()
	}

	def swing = new SwingBuilder()

	/**
	 * GO!
	 */
	void start() {

		def chat = new Chat(to: "bill", msg: "")
		def user = new User(username: "ubuntu", domain: DOMAIN)



		swing.edt {
			frame (id: "logon", title: "Connect to XMPP", size: [300, 250], locationRelativeTo: null, show: false) {
				borderLayout(vgap: 5)

				panel(constraints: BorderLayout.CENTER,
					border: compoundBorder([emptyBorder(10), titledBorder("Logon")])) {
					tableLayout {
						tr {
							td { label "Username:"}
							td { textField([id: "fUsername", columns: 15, text: user.username]) }
						}

						tr {
							td { label "Password:"}
							td { passwordField([id: "fCreds", columns: 15, text: user.password])}
						}

						tr {
							td { label "Domain:"}
							td { textField([id: "fDomain", columns: 15, text: user.domain])}
						}
					}
				}

				panel(constraints: BorderLayout.SOUTH) {
					button text: "Connect", actionPerformed: {
						connect(user)
						swing["connectedAs"].text = "Connected As: ${user.username}"
						swing["logon"].visible = false
					}
					button text: "Cancel", actionPerformed: {
						swing["logon"].visible = false
					}
				}

				bean user,
					user: bind { fUsername.text  },
					password: bind { fCreds.text },
					domain: bind { fDomain.text }
			}

			frame(id: "main", title: 'XMPP Chat', size: [650, 500], locationRelativeTo: null,
				defaultCloseOperation: JFrame.EXIT_ON_CLOSE, show: true) {

				menuBar {
					menu(text: "Connection") {
						menuItem(text: "Connect", actionPerformed: {
							swing["logon"].visible = true
						})
						menuItem(text: "Exit", actionPerformed: {
							System.exit(0)
						})
					}
				}

				borderLayout(vgap: 5)

				panel(constraints: BorderLayout.NORTH,
					border: compoundBorder([emptyBorder(10), titledBorder("XMPP Chat")])) {

					vbox {
						hbox(alignmentX: Component.LEFT_ALIGNMENT) {
							button text: "Connect", actionPerformed: {
								swing["logon"].visible = true
							}
							label id: "connectedAs", text: "Connected As: "
						}



						hbox(alignmentX: Component.LEFT_ALIGNMENT) {
							label text: "To: "
							textField([id: "toField", columns: 50, text: chat.to])
						}
						hbox(alignmentX: Component.LEFT_ALIGNMENT) {
							label text: "Msg:"
							textField([id: "msgField", columns: 40, text: chat.msg])
							button text: 'Send', actionPerformed: {
								sendMessage(chat)

								// Clear the message field
								msgField.text = ""
							}
						}
					}
				}

				scrollPane(constraints: BorderLayout.CENTER,
					border: compoundBorder([emptyBorder(10), titledBorder("Conversation")])) {
					vbox {
						button text: 'Clear Conversation', alignmentX: Component.LEFT_ALIGNMENT, actionPerformed: {
							received.text = ""
						}
						editorPane(id: "received", editable: false, alignmentX: Component.LEFT_ALIGNMENT)
					}
				}

				// Binding of textfield's to address object.
				bean chat,
					 to: bind { toField.text  },
					msg: bind { msgField.text }
			}
		}

		swing["main"].addWindowListener([windowClosing: { evt -> close() }] as WindowAdapter)
	}


	// ----------------------------------------------------------------------------------
	// XMPP-related methods
	// ----------------------------------------------------------------------------------

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

	def close() {
		if (xmppClient) {
			println "Closing XMPP client..."
			xmppClient.close()
		}
	}

	def sendMessage(Chat chat) {
		if (getStatus() == XmppSession.Status.AUTHENTICATED) {
			println chat
			xmppClient.sendMessage(
				new Message(
					Jid.of("${chat.to}@${DOMAIN}"),
					Message.Type.CHAT,
					chat.msg)
			)
			//xmppClient.query(IQ.get(ServiceDiscoveryManager.g))
		} else {
			println getStatus()
			throw new XmppConnectionInvalidException("XMPP Session Invalid")
		}
	}

	def getStatus() {
		return xmppClient ? xmppClient.getStatus() : XmppSession.Status.DISCONNECTED
	}

	def initializeXmpp() {
		println "Initialize XMPP"
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
		xmppClient.addInboundMessageListener { e -> m2g(e) }

		// Listen for messages
		xmppClient.addOutboundMessageListener { e -> m2g(e) }



		// Listen for roster pushes
		xmppClient.getManager(RosterManager.class).addRosterListener { e ->
			// Roster has changed
		}

		xmppClient.connect()
	}

	def m2g = { e ->
		Message message = e.getMessage()
		def from = message.from ?: xmppClient.getConnectedResource().toString()
		def fm = "[From]: ${from}; [BODY]: ${message.body}"

		println fm

		swing."received".text += fm + System.lineSeparator()
	}
}
