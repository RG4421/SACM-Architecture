package misc

import org.cisecurity.oval.collection.ObjectStringType
import org.cisecurity.oval.collection.ObjectsType
import org.cisecurity.oval.collection.OvalObjects
import org.cisecurity.oval.collection.ind.EnvironmentvariableObject
import org.cisecurity.oval.collection.ind.FamilyObject
import org.cisecurity.oval.common.GeneratorType
import org.cisecurity.oval.common.OperationEnumeration
import org.cisecurity.oval.common.SchemaVersionType

import javax.xml.bind.JAXBContext
import javax.xml.datatype.DatatypeFactory


def timestamp = DatatypeFactory.newInstance().newXMLGregorianCalendar(new GregorianCalendar())

def oo = new OvalObjects()
oo.generator =
	new GeneratorType(
		productName: "OVAL XMPP",
		productVersion: "0.0.1",
		schemaVersion: [new SchemaVersionType (value: "6.0.0")],
		timestamp: timestamp)

oo.objects = new ObjectsType()
def fo = new FamilyObject(id: "oval:org.cisecurity:obj:100", version: "1", comment: "Marshalled")
def eo = new EnvironmentvariableObject(id: "oval:org.cisecurity:obj:101", version: "1", comment: "Marshalled")
eo.name = new ObjectStringType(operation: OperationEnumeration.EQUALS, value: "HOME")
oo.objects.object << fo
oo.objects.object << eo

def packages = [
	"org.cisecurity.oval.collection.ind",
	"org.cisecurity.oval.collection",
	"org.cisecurity.oval.common",
	"org.cisecurity.oval.var",
	"org.cisecurity.xmldsig"
].join(":")

def jaxbContext= JAXBContext.newInstance(packages)
def m = jaxbContext.createMarshaller()

m.marshal(oo, System.out)
