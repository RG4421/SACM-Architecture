package org.cisecurity.xmpp

import org.cisecurity.xmpp.exc.XmppConnectionInvalidCredentialsException
import rocks.xmpp.core.sasl.AuthenticationException
import rocks.xmpp.core.session.XmppSession
import spock.lang.Shared
import spock.lang.Specification

class XmppClientApplicationTest extends Specification {
	@Shared
	XmppProxy app

	def setup() {
		app = new XmppProxy(xmppDomain: "ip-0a1e0af4")
	}

	def cleanup() {
		app.close()
	}

	def "Connect"() {
		given: "the application"
		when: "connection information is supplied"
			Properties properties = new Properties()
			properties.load(getClass().getResourceAsStream("/test-conn.properties"))

			def u = new User(username: properties.user, password: properties.cred)
			app.connect(u)
		then: "the xmpp connection is made"
			app.getStatus() == XmppSession.Status.AUTHENTICATED
	}

	def "Connect Exception on bad Creds"() {
		given: "the application"
		when: "connection information is supplied"
			def u = new User(username: "nobody", password: "blerg")
			app.connect(u)
		then: "the exception is thrown"
			thrown(AuthenticationException.class)
	}

	def "Exception on missing Creds"() {
		given: "the application"
		when: "connection information is supplied"
			def u = new User(username: "nobody")
			app.connect(u)
		then: "the exception is thrown"
			thrown(XmppConnectionInvalidCredentialsException.class)
	}
}
