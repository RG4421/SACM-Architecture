package org.cisecurity.oval.collector.ind

import org.cisecurity.oval.collection.ObjectType
import org.cisecurity.oval.collector.BaseCollector
import org.cisecurity.oval.collector.Session
import org.cisecurity.oval.fact.ICollector
import org.cisecurity.oval.sc.CollectedObjectType
import org.cisecurity.oval.sc.ReferenceType
import org.cisecurity.oval.sc.ind.EntityItemFamilyType
import org.cisecurity.oval.sc.ind.FamilyItem

class FamilyCollector extends BaseCollector implements ICollector {

	/**
	 * Collect the required system characteristics
	 * @param object
	 */
	def collect(Session session, ObjectType object) {
		def family = isWindows() ? "windows" : (isMac() ? "macos" : "unix")

		log.debug "Family Object --> ${family}"

		FamilyItem familyItem =
			new FamilyItem(
				id: session.getNextItemId(),
				family: new EntityItemFamilyType(datatype: "string", value: family))

		CollectedObjectType fcot =
			new CollectedObjectType(
				id: object.id,
				version: object.version,
				comment: object.comment)
		fcot.reference << new ReferenceType(itemRef: familyItem.id)

		return [fcot, [familyItem]]
	}

	boolean isWindows() {
		return System.properties."os.name".toLowerCase().contains("windows")
	}

	boolean isMac() {
		return System.properties."os.name".toLowerCase().contains("mac")
	}

}
