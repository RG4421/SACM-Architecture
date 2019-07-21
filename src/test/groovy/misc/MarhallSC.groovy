package misc

import org.cisecurity.oval.common.GeneratorType
import org.cisecurity.oval.common.SchemaVersionType
import org.cisecurity.oval.sc.CollectedItemAnySimpleType
import org.cisecurity.oval.sc.CollectedItemStringType
import org.cisecurity.oval.sc.CollectedObjectType
import org.cisecurity.oval.sc.CollectedObjectsType
import org.cisecurity.oval.sc.InterfacesType
import org.cisecurity.oval.sc.OvalSystemCharacteristics
import org.cisecurity.oval.sc.ReferenceType
import org.cisecurity.oval.sc.SystemDataType
import org.cisecurity.oval.sc.SystemInfoType
import org.cisecurity.oval.sc.ind.EntityItemFamilyType
import org.cisecurity.oval.sc.ind.EnvironmentvariableItem
import org.cisecurity.oval.sc.ind.FamilyItem
import javax.xml.bind.JAXBContext
import javax.xml.datatype.DatatypeFactory

def timestamp = DatatypeFactory.newInstance().newXMLGregorianCalendar(new GregorianCalendar())

def osc = new OvalSystemCharacteristics()
osc.generator =
	new GeneratorType(
		productName: "OVAL XMPP",
		productVersion: "0.0.1",
		schemaVersion: [new SchemaVersionType (value: "6.0.0")],
		timestamp: timestamp)

osc.systemInfo =
	new SystemInfoType(
		osName: System.properties["os.name"],
		osVersion: System.properties["os.version"],
		architecture: System.properties["os.arch"],
		interfaces: new InterfacesType()
	)

def packages = [
	"org.cisecurity.oval.sc.ind",
	"org.cisecurity.oval.sc",
	"org.cisecurity.oval.common",
	"org.cisecurity.oval.var",
	"org.cisecurity.xmldsig"
].join(":")


def itemCounter = 0

def itemId = ++itemCounter

osc.collectedObjects = new CollectedObjectsType()
osc.systemData       = new SystemDataType()

FamilyItem familyItem =
	new FamilyItem(id: itemId, family: new EntityItemFamilyType(value: "windows"))

CollectedObjectType fcot =
	new CollectedObjectType(
		id: "oval:org.cisecurity:obj:1",
		version: 1,
		comment: "Test")
fcot.reference << new ReferenceType(itemRef: itemId)

osc.collectedObjects.collectedObject << fcot
osc.systemData.collectedItem << familyItem

def matches = ["COMPUTERNAME": "CIS-CAT-DEV"]
CollectedObjectType ecot =
	new CollectedObjectType(
		id: "oval:org.cisecurity:obj:2",
		version: 1,
		comment: "Test 2")

matches.keySet().each { evn ->
	def evv = matches[evn]

	itemId = ++itemCounter
	ecot.reference << new ReferenceType(itemRef: itemId)

	EnvironmentvariableItem evi =
		new EnvironmentvariableItem(
			id: itemId,
			name: new CollectedItemStringType(value: evn),
			value: new CollectedItemAnySimpleType(value: evv))
	osc.systemData.collectedItem << evi

}
osc.collectedObjects.collectedObject << ecot

def jaxbContext= JAXBContext.newInstance(packages)
def m = jaxbContext.createMarshaller()

m.marshal(osc, System.out)
