package org.cis.xmpp

import groovy.swing.SwingBuilder

import java.awt.*

class XmppLogon {
	//def sb = new SwingBuilder()

	XmppLogon(SwingBuilder sb) {
		def user = new User(username: "ubuntu", domain: "ip-0a1e0af4")

		sb.build {
			dialog(id: "logon", title: "Logon", size: [400, 400],
				locationRelativeTo: null, show: true, modal: true) {

				borderLayout(vgap: 5)

				panel(constraints: BorderLayout.CENTER,
					border: compoundBorder([emptyBorder(10), titledBorder("Logon to XMPP")])) {
					tableLayout {
						tr {
							td { label "Username:" }
							td { textField([id: "username", columns: 25, text: user.username]) }
						}

						tr {
							td { label "Password:" }
							td { passwordField([id: "creds", columns: 25]) }
						}

						tr {
							td { label "Domain:" }
							td { textField([id: "domain", columns: 25, text: user.domain]) }
						}
					}
				}

				panel(constraints: BorderLayout.SOUTH) {
					button text: "Log In", actionPerformed: {
						sb."main".lo(user.username, user.password)
						sb."logon".visible = false
					}
					button text: "Cancel", actionPerformed: {
						sb."logon".visible = false
					}
				}

				bean user,
					username: bind { username.text },
					password: bind { creds.text },
					domain: bind { domain.text }
			}
		}
	}
}
