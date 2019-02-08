package org.cis.xmpp

import org.cis.xmpp.exc.XmppConnectionInvalidCredentialsException
import rocks.xmpp.core.sasl.AuthenticationException
import rocks.xmpp.core.session.XmppSession
import spock.lang.Specification

class XmppClientApplicationTest extends Specification {
	def "Connect"() {
		given: "the application"
			def app = new XmppClientApplication()
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
			def app = new XmppClientApplication()
		when: "connection information is supplied"
			def u = new User(username: "nobody", password: "blerg")
			app.connect(u)
		then: "the exception is thrown"
			thrown(AuthenticationException.class)
	}

	def "Exception on missing Creds"() {
		given: "the application"
			def app = new XmppClientApplication()
		when: "connection information is supplied"
			def u = new User(username: "nobody")
			app.connect(u)
		then: "the exception is thrown"
			thrown(XmppConnectionInvalidCredentialsException.class)
	}
}
