package org.cisecurity

@Singleton
class Utilities {

	/**
	 * Determine the element name in case a namespace prefix is applied.
	 * For example, the basename of element <oval-sc:registry_item> is "registry_item"
	 *
	 * @param elementName
	 * @return the basename w/out the namespace prefix, if there is one
	 */
	def getElementBasename(String elementName) {
		def basename = elementName
		def pos = elementName.indexOf(":")
		if (pos >= 0) {
			basename = elementName.substring(pos + 1)
		}
		return basename
	}

	/**
	 * Determine the element namespace prefix in case a namespace prefix is applied.
	 * For example, the prefix of element <oval-sc:registry_item> is "oval-sc"
	 *
	 * @param elementName
	 * @return the namespace prefix, if there is one
	 */
	def getElementPrefix(String elementName) {
		def prefix = ""
		def pos = elementName.indexOf(":")
		if (pos >= 0) {
			prefix = elementName.substring(0, pos)
		}
		return prefix
	}

	/**
	 * Parse the content node for namespace attributes.
	 * @param content
	 * @return
	 */
	def parseForNamespaces(def content) {
		def namespaces = [:]

		def attributes = content.attributes()
		attributes.keySet().each { ak ->
			def av = attributes[ak]

			if (ak.startsWith("xmlns")) {
				def prefix
				if (ak == "xmlns") {
					// this is the default namespace...
					prefix = ""
				} else {
					prefix = ak.substring((ak.indexOf(":")) + 1)
				}
				//log.info "${prefix} --> ${av}"
				namespaces[prefix] = av
			}
		}
		return namespaces
	}
}
