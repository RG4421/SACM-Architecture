package org.cis.xmpp

import groovy.beans.Bindable

/**
 *
 */
@Bindable
class Chat {
	String to, msg, rl
	String toString() { "Chat[to=$to; msg=$msg]" }
}
