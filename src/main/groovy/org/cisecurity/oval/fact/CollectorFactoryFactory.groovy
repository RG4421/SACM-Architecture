package org.cisecurity.oval.fact

class CollectorFactoryFactory {
	static ICollectorFactory getCollectorFactory(String objectPackage) {
		switch (objectPackage) {
			case "org.cisecurity.oval.collection.ind":
				return IndependentCollectorFactory.instance
			default:
				break
		}
	}
}
