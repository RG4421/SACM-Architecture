package org.cisecurity.oval.collector.ind

import org.cisecurity.oval.collection.ObjectStringType
import org.cisecurity.oval.collection.ObjectType
import org.cisecurity.oval.collector.BaseCollector
import org.cisecurity.oval.collector.Session
import org.cisecurity.oval.collector.cmp.StringComparator
import org.cisecurity.oval.fact.ICollector
import org.cisecurity.oval.sc.CollectedItemAnySimpleType
import org.cisecurity.oval.sc.CollectedItemStringType
import org.cisecurity.oval.sc.CollectedObjectType
import org.cisecurity.oval.sc.ReferenceType
import org.cisecurity.oval.sc.ind.EnvironmentvariableItem

class EnvironmentVariableCollector extends BaseCollector implements ICollector {

	/**
	 * Collect the required system characteristics
	 * @param object
	 */
	def collect(Session session, ObjectType object) {
		def matches = collectEnv(object.name)

		log.debug "Environment Variable Object --> ${matches}"

		def collectedItems = []

		if (matches.size() > 0) {
			CollectedObjectType ecot =
				new CollectedObjectType(
					id: object.id,
					version: object.version,
					comment: object.comment)

			matches.keySet().each { evn ->
				def evv = matches[evn]

				EnvironmentvariableItem evi =
					new EnvironmentvariableItem(
						id: session.getNextItemId(),
						name: new CollectedItemStringType(value: evn),
						value: new CollectedItemAnySimpleType(value: evv))

				ecot.reference << new ReferenceType(itemRef: evi.id)

				collectedItems << evi
			}

			return [ecot, collectedItems]
		}
	}

	def collectEnv(ObjectStringType name) {
		def rez = [:]

		StringComparator sc = new StringComparator()

		def env = System.env
		env.keySet().each { n ->
			def v = System.env[n]
			if (sc.compareForBoolean(n, String.valueOf(name.value), name.operation)) {
				rez[n] = v
			}
		}
		return rez
	}
}
