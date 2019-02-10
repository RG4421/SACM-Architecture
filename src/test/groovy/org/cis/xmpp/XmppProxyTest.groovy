package org.cis.xmpp

import spock.lang.Shared
import spock.lang.Specification

class XmppProxyTest extends Specification {
	@Shared
	XmppProxy ubuntu
	@Shared
	XmppProxy orchestrator


	def ucf = { fm ->
		println "[UBUNTU CALLBACK FUNCTION] ${fm}${System.lineSeparator()}"
	}
	def ocf = { fm ->
		println "[ORCHESTRATOR CALLBACK FUNCTION] ${fm}${System.lineSeparator()}"
	}

	void setup() {
		ubuntu = new XmppProxy(callback: ucf, xmppDomain: "ip-0a1e0af4")
		ubuntu.connect(new User(username: "ubuntu", password: "Pt3ttcs2h!"))

		orchestrator = new XmppProxy(callback: ocf, xmppDomain: "ip-0a1e0af4")
		orchestrator.connect(new User(username: "orchestrator", password: "Pt3ttcs2h!"))
	}

	void cleanup() {
		ubuntu.close()
		orchestrator.close()
	}

	def "SendMessage"() {
		given: "A bogus chat message"
			Chat chat = new Chat(to: "orchestrator", msg: "doesnt matter")
		when: "The IQ is sent from 'ubuntu' to 'orchestrator'"
			//Thread.sleep(10000)
			ubuntu.sendMessage(chat)
		then: "the callback function is called, setting the var"
			false
	}
}
