package org.cisecurity.oval.fact

interface ICollectorFactory {
	ICollector getCollector(String objectClassname)
}
