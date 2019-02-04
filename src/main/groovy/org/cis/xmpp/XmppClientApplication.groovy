package org.cis.xmpp

import groovy.beans.Bindable
import groovy.swing.SwingBuilder
import org.cis.xmpp.trust.TrustAllX509TrustManager
import rocks.xmpp.addr.Jid
import rocks.xmpp.core.net.client.SocketConnectionConfiguration
import rocks.xmpp.core.session.XmppClient
import rocks.xmpp.core.stanza.model.Message
import rocks.xmpp.core.stanza.model.Presence
import rocks.xmpp.im.roster.RosterManager
import rocks.xmpp.im.roster.model.Contact
import rocks.xmpp.im.subscription.PresenceManager

import javax.net.ssl.HostnameVerifier
import javax.net.ssl.SSLContext
import javax.net.ssl.SSLSession
import javax.net.ssl.TrustManager
import javax.swing.JFrame
import java.awt.BorderLayout
import java.awt.event.WindowAdapter
import java.security.SecureRandom

/**
 * Basic GUI that connects to XMPP
 */
class XmppClientApplication {
	static void main(String[] args) {
		new XmppClientApplication().start()
	}

	XmppClientProxy xmppClientProxy

	/**
	 * GO!
	 */
	void start() {
		initialize()

		def chat = new Chat(to: "bill", msg: "")

		def swing = new SwingBuilder()

		swing.edt {
			frame(id: "main",
				title: 'XMPP Chat',
				size: [400, 350],
				locationRelativeTo: null,
				defaultCloseOperation: JFrame.EXIT_ON_CLOSE,
				show: true) {

				borderLayout(vgap: 5)

				panel(constraints: BorderLayout.CENTER,
					border: compoundBorder([emptyBorder(10), titledBorder('Simple XMPP Chat:')])) {
					tableLayout {
						tr {
							td { label "To:"}
							td { textField([id: "toField", columns: 25, text: chat.to]) }
						}

						tr {
							td { label "Msg:"}
							td { textArea([id: "msgField", rows: 7, columns: 25, text: chat.msg])}
						}
					}
				}

				panel(constraints: BorderLayout.SOUTH) {
					button text: 'Send', actionPerformed: {
						println chat
						xmppClientProxy.sendMessage(chat.to, chat.msg)
					}
				}

				// Binding of textfield's to address object.
				bean chat,
					to: bind { toField.text },
					msg: bind { msgField.text }
			}
		}
		swing["main"].addWindowListener([windowClosing: { evt ->
			if (xmppClientProxy) {
				println "Closing XMPP client..."
				xmppClientProxy.close()
			}
		}] as WindowAdapter)


	}

	def initialize() {
		Properties properties = new Properties()
		properties.load(getClass().getResourceAsStream("/conn.properties"))

		xmppClientProxy = new XmppClientProxy(username: properties.user, password: properties.cred)
		xmppClientProxy.connect()
	}
}
