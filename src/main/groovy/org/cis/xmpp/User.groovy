package org.cis.xmpp

import groovy.beans.Bindable

@Bindable
class User {
	String username
	String password
	String domain
}
