package org.cis.xmpp.svc

import groovy.xml.StreamingMarkupBuilder
import org.ietf.sacm.collection.Collections
import rocks.xmpp.core.session.XmppClient
import rocks.xmpp.core.stanza.IQHandler
import rocks.xmpp.core.stanza.model.IQ

class CollectionsService implements IIQHandlerService {

	/**
	 * Add the IQ handler to the XMPP client to process Assessment Content related custom IQs
	 * @param xmppClient
	 * @return
	 */
	def addIQHandler(XmppClient xmppClient) {
		xmppClient.addIQHandler(Collections.class, new IQHandler() {
			@Override
			IQ handleRequest(IQ iq) {
				println "[ADD IQ HANDLER] ${iq}"
				if (iq.hasExtension(Collections.class)) {
					Collections collections = iq.getExtension(Collections.class)
					if (collections) {
						// What is the TODO here?
						// Once the collections instance is marshalled to a groovy node, it needs to fork
						// off and perform collection.  Once collection is complete, the collected information
						// needs to be sent back.
						return iq.createResult(collections)
					}
				}
				//return iq.createResult()
			}
		})
	}

	/**
	 * Marshall the object to an XML node, groovy-style
	 * @param collections
	 * @return the collections instance parsed to a groovy node
	 */
	def toNode(Collections collections) {
		def xml = new StreamingMarkupBuilder().bind { smb ->
			namespaces << ["": Collections.NAMESPACE]

			smb."collections" {
				collections.collection.each { c ->
					"collection"("collection-id": c.collectionId) {
						"collection-type"("family": c.collectionType.family, "type": c.collectionType.type)
						if (c.collectionBehaviors) {
							"collection-behaviors" {
								c.collectionBehaviors.behavior.each { b ->
									"behavior"("name": b.name, "value": b.value)
								}
							}
						}

						if (c.set) {
							mkp.yieldUnescaped(setToNode(c.set))
						} else {
							"collection-fields" {
								c.collectionFields.collectionField.each { cf ->
									"collection-field"("name": cf.name, "datatype": cf.datatype, "operation": cf.operation, cf.value)
								}
							}
						}

						if (c.collectionFilters) {
							"collection-filters" {
								c.collectionFilters.collectionFilter.each { f ->
									"collection-filter"("action": f.action) {
										f.filterField.each { ff ->
											"filter-field"("name": ff.name, "datatype": ff.datatype, "operation": ff.operation, ff.value)
										}
									}
								}
							}
						}
					}
				}
			}
		}
		return new XmlParser(false, false).parseText(xml.toString())
	}

	/**
	 * Marshall any <set> elements, which can contain other <set> elements
	 * @param set
	 * @return XML serialized to a String
	 */
	def setToNode(org.ietf.sacm.collection.Set set) {
		def xml = new StreamingMarkupBuilder().bind { smb ->
			smb."set" ("set-combination": set.setCombination) {
				set.set.each { s -> mkp.yieldUnescaped(setToNode(s)) }

				set.collectionRef.each { cr ->
					"collection-ref" ("collection-idref": cr.collectionIdref)
				}
			}
		}
		return xml.toString()
	}
}
