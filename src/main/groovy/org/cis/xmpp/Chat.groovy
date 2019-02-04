package org.cis.xmpp

import groovy.beans.Bindable

/**
 *
 */
@Bindable
class Chat {
	String to, msg
	String toString() { "Chat[to=$to; msg=$msg]" }
}
