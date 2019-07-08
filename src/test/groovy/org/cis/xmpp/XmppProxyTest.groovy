package org.cis.xmpp

import org.slf4j.Logger
import org.slf4j.LoggerFactory
import rocks.xmpp.addr.Jid
import spock.lang.Shared
import spock.lang.Specification

class XmppProxyTest extends Specification {
	@Shared
	Logger log = LoggerFactory.getLogger("org.cis.xmpp")

	@Shared
	XmppProxy ubuntu
	@Shared
	XmppProxy orchestrator


	def ucf = { fm ->
		log.info "[UBUNTU CALLBACK FUNCTION] ${fm}${System.lineSeparator()}"
	}
	def ocf = { fm ->
		log.info "[ORCHESTRATOR CALLBACK FUNCTION] ${fm}${System.lineSeparator()}"
	}

	void setup() {
		ubuntu = new XmppProxy(callback: ucf, xmppDomain: "ip-0a1e0af4")
		ubuntu.connect(new User(username: "ubuntu", password: "Pt3ttcs2h!", resource: "resource"))

		orchestrator = new XmppProxy(callback: ocf, xmppDomain: "ip-0a1e0af4")
		orchestrator.connect(new User(username: "orchestrator", password: "Pt3ttcs2h!", resource: "resource"))
	}

	void cleanup() {
		ubuntu.close()
		orchestrator.close()
	}

	def "SendMessage"() {
		given: "A bogus chat message"
			Chat chat = new Chat(to: "ubuntu", msg: "doesnt matter")
		when: "The IQ is sent from 'orchestrator' to 'ubuntu'"
			Jid u = ubuntu.xmppClient.connectedResource
			orchestrator.discoverServices(u)
		then: "the callback function is called, setting the var"
			false
	}

	def "File Transfer"() {
		given: ""
		when: "the orchestrator offers a file to ubuntu"
			Jid u = ubuntu.xmppClient.connectedResource
			def future = orchestrator.offerFile(u)
			Thread.sleep(15000)
		then: "the file is transfered and exists in c-temp"
			assert new File("C:\\Temp\\Win10-xccdf.xml").exists()
	}

	def "Last Activity"() {
		given: ""
		when: "the orchestrator requests ubuntu's last activity time"
			Jid u = ubuntu.xmppClient.connectedResource
			def la = orchestrator.lastActivity(u)
		then: "a time is returned"
			assert la
	}

	def "Create the PubSub Nodes"() {
		given: ""
		when: ""
			orchestrator.createNodes()
		then: ""
		true
	}
}
