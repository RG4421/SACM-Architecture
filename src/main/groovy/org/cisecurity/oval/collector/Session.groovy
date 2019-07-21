package org.cisecurity.oval.collector

import org.slf4j.LoggerFactory

class Session {
	def log = LoggerFactory.getLogger(Session.class)

	int currentItemCounter = 0

	/**
	 * Get the next <_item> id value.
	 * @return
	 */
	int getNextItemId() {
		currentItemCounter++
		return currentItemCounter
	}
}
