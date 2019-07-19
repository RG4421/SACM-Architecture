package org.cisecurity.xmpp.svc

import groovy.xml.XmlUtil
import org.cisecurity.xmpp.extensions.collection.sacm.model.Set
import org.cisecurity.xmpp.extensions.collection.sacm.model.Collection
import org.cisecurity.xmpp.extensions.collection.sacm.model.CollectionFields
import org.cisecurity.xmpp.extensions.collection.sacm.model.CollectionRef
import org.cisecurity.xmpp.extensions.collection.sacm.model.CollectionType
import org.cisecurity.xmpp.extensions.collection.sacm.model.Collections
import org.cisecurity.xmpp.extensions.collection.sacm.model.DatatypeEnumeration
import org.cisecurity.xmpp.extensions.collection.sacm.model.FamilyEnumeration
import org.cisecurity.xmpp.extensions.collection.sacm.model.FieldType
import org.cisecurity.xmpp.extensions.collection.sacm.model.OperationEnumeration
import org.cisecurity.xmpp.extensions.collection.sacm.model.SetCombinationEnumeration
import org.cisecurity.xmpp.extensions.collection.sacm.model.Collection
import org.cisecurity.xmpp.extensions.collection.sacm.model.CollectionFields
import org.cisecurity.xmpp.extensions.collection.sacm.model.CollectionRef
import org.cisecurity.xmpp.extensions.collection.sacm.model.CollectionType
import org.cisecurity.xmpp.extensions.collection.sacm.model.Collections
import org.cisecurity.xmpp.extensions.collection.sacm.model.DatatypeEnumeration
import org.cisecurity.xmpp.extensions.collection.sacm.model.FamilyEnumeration
import org.cisecurity.xmpp.extensions.collection.sacm.model.FieldType
import org.cisecurity.xmpp.extensions.collection.sacm.model.OperationEnumeration
import org.cisecurity.xmpp.extensions.collection.sacm.model.Set
import org.cisecurity.xmpp.extensions.collection.sacm.model.SetCombinationEnumeration
import spock.lang.Shared
import spock.lang.Specification

class CollectionsServiceTest extends Specification {
	@Shared
	Collections fieldCollections1 = new Collections()
	@Shared
	Collections fieldCollections2 = new Collections()
	@Shared
	Collections setCollections = new Collections()
	@Shared
	def set = new Set()

	def setupSpec() {

		// FIELDS
		def collectionType = new CollectionType()
		collectionType.setFamily(FamilyEnumeration.WINDOWS)
		collectionType.setType("file")

		def collectionFields = new CollectionFields()
		def pathField = new FieldType()
		pathField.setName("path")
		pathField.setDatatype(DatatypeEnumeration.STRING)
		pathField.setOperation(OperationEnumeration.CASE_INSENSITIVE_EQUALS)
		pathField.setValue("C:\\Temp")

		def filenameField = new FieldType()
		filenameField.setName("filename")
		filenameField.setDatatype(DatatypeEnumeration.STRING)
		filenameField.setOperation(OperationEnumeration.CASE_INSENSITIVE_EQUALS)
		filenameField.setValue("out.html")

		collectionFields.collectionField.add(pathField)
		collectionFields.collectionField.add(filenameField)

		def sacmCollection = new Collection()
		sacmCollection.setCollectionId(100)
		sacmCollection.setCollectionType(collectionType)
		sacmCollection.setCollectionFields(collectionFields)

		fieldCollections1.collection.add(sacmCollection)

		//-----------------------------------------------------
		def collectionType1 = new CollectionType()
		collectionType1.setFamily(FamilyEnumeration.WINDOWS)
		collectionType1.setType("file")

		def collectionFields1 = new CollectionFields()
		def pathField1 = new FieldType()
		pathField1.setName("path")
		pathField1.setDatatype(DatatypeEnumeration.STRING)
		pathField1.setOperation(OperationEnumeration.CASE_INSENSITIVE_EQUALS)
		pathField1.setValue("C:\\Temp")

		def filenameField1 = new FieldType()
		filenameField1.setName("filename")
		filenameField1.setDatatype(DatatypeEnumeration.STRING)
		filenameField1.setOperation(OperationEnumeration.CASE_INSENSITIVE_EQUALS)
		filenameField1.setValue("out1.html")

		collectionFields1.collectionField.add(pathField)
		collectionFields1.collectionField.add(filenameField)

		def sacmCollection1 = new Collection()
		sacmCollection1.setCollectionId(101)
		sacmCollection1.setCollectionType(collectionType1)
		sacmCollection1.setCollectionFields(collectionFields1)

		fieldCollections2.collection.add(sacmCollection1)

		// SET
		def collectionType2 = new CollectionType()
		collectionType2.setFamily(FamilyEnumeration.WINDOWS)
		collectionType2.setType("file")


		set.setCombination = SetCombinationEnumeration.COMPLEMENT
		set.collectionRef << new CollectionRef(collectionIdref: 100)
		set.collectionRef << new CollectionRef(collectionIdref: 101)

		def sacmCollection2 = new Collection()
		sacmCollection2.setCollectionId(102)
		sacmCollection2.setCollectionType(collectionType2)
		sacmCollection2.setSet(set)

		setCollections.collection.add(sacmCollection)
		setCollections.collection.add(sacmCollection1)
		setCollections.collection.add(sacmCollection2)
	}

	def "Ensure that a Collections instance using fields is marshalled correctly to a groovy node"() {
		given: "A collection using fields and not sets"
		when: "The collection object is parsed to a node"
			def xml = new CollectionsService().toNode(fieldCollections1)
		then: "Normal groovy methods will find correct data"
			println XmlUtil.serialize(xml)
			assert xml.collection[0].@"collection-id".toString() == "100"
			assert xml.collection[0]."collection-type".@family[0].toString() == "WINDOWS"
			assert xml.collection[0]."collection-type".@type[0].toString() == "file"
			assert xml.collection[0]."collection-fields"."collection-field"[0].text() == "C:\\Temp"
	}

	def "Ensure that a Collections instance using sets is marshalled correctly to a groovy node"() {
		given: "A collection using fields and not sets"
		when: "The collection object is parsed to a node"
			def xml = new CollectionsService().toNode(setCollections)
		then: "Normal groovy methods will find correct data"
			println XmlUtil.serialize(xml)
			assert xml.collection[0].@"collection-id".toString() == "100"
			assert xml.collection[1].@"collection-id".toString() == "101"
			assert xml.collection[2].@"collection-id".toString() == "102"
			assert xml.collection[0]."collection-type".@family[0].toString() == "WINDOWS"
			assert xml.collection[0]."collection-type".@type[0].toString() == "file"
			assert xml.collection[2]."set"[0].@"set-combination".toString() == "COMPLEMENT"
			assert xml.collection[2]."set"[0]."collection-ref"[0].@"collection-idref".toString() == "100"
			assert xml.collection[2]."set"[0]."collection-ref"[1].@"collection-idref".toString() == "101"
	}

	def "Ensure that the 'set-to-node' parsing works correctly"() {
		given: "A collection set"
		when: "The set object is parsed to a node"
			def xml = new XmlParser(false, false).parseText(new CollectionsService().setToNode(set))
		then: "Normal groovy methods will find correct data"
			println XmlUtil.serialize(xml)
			assert xml.@"set-combination".toString() == "COMPLEMENT"
			assert xml."collection-ref"[0].@"collection-idref".toString() == "100"
			assert xml."collection-ref"[1].@"collection-idref".toString() == "101"
	}
}
