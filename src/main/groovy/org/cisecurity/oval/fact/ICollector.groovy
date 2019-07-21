package org.cisecurity.oval.fact

import org.cisecurity.oval.collection.ObjectType
import org.cisecurity.oval.collector.Session

interface ICollector {
	def collect(Session session, ObjectType object)
}