package org.cisecurity.oval.fact

import org.cisecurity.oval.collector.ind.EnvironmentVariableCollector
import org.cisecurity.oval.collector.ind.FamilyCollector

@Singleton
class IndependentCollectorFactory implements ICollectorFactory {

	ICollector getCollector(String objectClassname) {
		switch (objectClassname) {
			case "EnvironmentvariableObject":
				return new EnvironmentVariableCollector()
			case "FamilyObject":
				return new FamilyCollector()
			case "FilehashObject":
				break
			case "LdapObject":
				break
			case "ProcessenvironmentObject":
				break
			case "SqlObject":
				break
			case "TextfilecontentObject":
				break
			case "VariableObject":
				break
			case "XmlfilecontentObject":
				break
		}
	}
}
