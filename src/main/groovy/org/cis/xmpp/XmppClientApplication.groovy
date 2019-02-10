package org.cis.xmpp

import groovy.swing.SwingBuilder

import javax.swing.*
import java.awt.*
import java.awt.event.WindowAdapter

/**
 * Basic GUI that connects to XMPP
 */
class XmppClientApplication {

	final String DOMAIN = "ip-0a1e0af4"

	XmppProxy xmppProxy

	static void main(String[] args) {
		new XmppClientApplication().start()
	}

	def swing = new SwingBuilder()

	def logonFrame
	def mainFrame

	def conversationCallback = { fm ->
		swing."conversation".text += fm + System.lineSeparator()
	}

	/**
	 * GO!
	 */
	void start() {

		def chat = new Chat(to: "bill", msg: "")
		def user = new User(username: "ubuntu", domain: DOMAIN)

		def closeLogon = { t ->
			swing."connectedAs".text = t
			swing."logon".visible = false
			swing."main".visible = true

		}

		xmppProxy = new XmppProxy(callback: conversationCallback, xmppDomain: DOMAIN)

		logonFrame = swing.frame (
			id: "logon", title: "Connect to XMPP", size: [300, 250], locationRelativeTo: null, show: false, defaultCloseOperation: JFrame.EXIT_ON_CLOSE) {

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
					xmppProxy.connect(user)
					closeLogon("Connected As: ${user.username}")
				}
				button text: "Cancel", actionPerformed: {
					closeLogon("Not Connected")
				}
			}

			bean user,
				username: bind { fUsername.text  },
				password: bind { fCreds.text },
				domain: bind { fDomain.text }
		}

		mainFrame = swing.frame(id: "main", title: 'XMPP Chat', size: [650, 500], locationRelativeTo: null,
			defaultCloseOperation: JFrame.EXIT_ON_CLOSE, show: false) {

			menuBar {
				menu(text: "Connection") {
					menuItem(text: "Connect", actionPerformed: {
						logonFrame.visible = true
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
							logonFrame.visible = true
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
							xmppProxy.sendMessage(chat)

							// Clear the message field
							msgField.text = ""
						}
					}
				}
			}

			scrollPane(constraints: BorderLayout.CENTER,
				border: compoundBorder([emptyBorder(10), titledBorder("Conversation")])) {
				vbox() {
					button text: 'Clear Conversation', alignmentX: Component.LEFT_ALIGNMENT, actionPerformed: {
						received.text = ""
					}
					editorPane(id: "conversation", editable: false, alignmentX: Component.LEFT_ALIGNMENT)
				}
			}

			// Binding of textfield's to address object.
			bean chat,
				to: bind { toField.text  },
				msg: bind { msgField.text }
		}

		swing.edt {
			// Logon here?
			logonFrame.visible = true
		}

		swing["main"].addWindowListener([windowClosing: { evt -> xmppProxy.close() }] as WindowAdapter)
	}
}
