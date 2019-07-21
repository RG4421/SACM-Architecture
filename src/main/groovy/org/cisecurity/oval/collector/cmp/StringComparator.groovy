package org.cisecurity.oval.collector.cmp

import org.cisecurity.oval.common.OperationEnumeration
import org.slf4j.LoggerFactory

class StringComparator {
	def log = LoggerFactory.getLogger(StringComparator.class)

	boolean compareForBoolean(String iv, String sv, OperationEnumeration op) {

		// Massage NULL to empty strings...
		if (iv == null) {
			iv = ""
		}
		if (sv == null) {
			sv = ""
		}

		return { i, s, o ->
			boolean rez = false

			switch (o) {
				case OperationEnumeration.EQUALS:
					rez = (i == s)
					break
				case OperationEnumeration.NOT_EQUAL:
					rez = (i != s)
					break
				case OperationEnumeration.CASE_INSENSITIVE_EQUALS:
					rez = i.equalsIgnoreCase(s)
					break
				case OperationEnumeration.CASE_INSENSITIVE_NOT_EQUAL:
					rez = !(i.equalsIgnoreCase(s))
					break
				case OperationEnumeration.PATTERN_MATCH:
					// groovy "match" operator is ==~
					// groovy "find" operator is =~
					//  The object's <name> is the regular expression
					rez = (i ==~ s || i =~ s)
					break
				default:
					rez = false
			}
			//log.info("StringComparator --> Comparing '${i}' to '${s}' using operator '${o}' yielded a result of '${rez}'")
			return rez
		}.call(iv, sv, op)
	}
}
