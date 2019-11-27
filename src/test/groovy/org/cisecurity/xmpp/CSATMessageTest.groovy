package org.cisecurity.xmpp

import org.cisecurity.csat.Csat
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import rocks.xmpp.core.stanza.model.IQ
import spock.lang.Shared
import spock.lang.Specification

import javax.xml.bind.JAXBContext

class CSATMessageTest extends Specification {
	@Shared
	Logger log = LoggerFactory.getLogger("org.cis.xmpp")

	@Shared
	XmppProxy orchestrator
	@Shared
	XmppProxy recipient


	@Shared
	def ocf = { fm ->
		log.info "[CALLBACK FUNCTION] ${fm}${System.lineSeparator()}"
	}

	void setupSpec() {
		orchestrator = new XmppProxy(callback: ocf, xmppDomain: "ip-0a1e0af4")
		orchestrator.connect(new User(username: "orchestrator", password: "Pt3ttcs2h!", resource: "resource"))

		recipient = new XmppProxy(callback: ocf, xmppDomain: "ip-0a1e0af4")
		recipient.connect(new User(username: "wmunyan", password: "Pt3ttcs2h!", resource: "resource"))
	}

	void cleanupSpec() {
		orchestrator.close()
		recipient.close()
	}

	Csat createIndustryAverageRequest() {
		def xmltext = """
<csat xmlns="http://cisecurity.org/csat">
    <average industry="CYBERSECURITY" license-key="d31f4355-ad3a-4214-ac69-b3625ffe4612"/>
</csat>
"""

		def jaxbContext= JAXBContext.newInstance(Csat.class)
		def unmarshaller = jaxbContext.createUnmarshaller()
		def sr =  new StringReader(xmltext)

		return unmarshaller.unmarshal(sr)
	}

	Csat createLicenseValidationRequest(def key) {
		def xmltext = """
<csat xmlns="http://cisecurity.org/csat">
    <validation license-key="${key}"/>
</csat>
"""

		def jaxbContext= JAXBContext.newInstance(Csat.class)
		def unmarshaller = jaxbContext.createUnmarshaller()
		def sr =  new StringReader(xmltext)

		Csat csat = unmarshaller.unmarshal(sr)
		return csat
	}

	def "Send the CSAT average scores request via standard Message stanza"() {
		given: "A blob of XML"
			Csat csat = createIndustryAverageRequest()
		when: "the orchestrator sends the message to the recipient"
			orchestrator.sendCSATAverageRequest(recipient.xmppClient.connectedResource, csat)
		then: "you can check system.out to see if the industry/key is logged"
			assert true
	}
	def "Send the CSAT validation request via standard Message stanza"() {
		given: "A blob of XML"
			Csat csat = createLicenseValidationRequest("d31f4355-ad3a-4214-ac69-b3625ffe4612")
		when: "the orchestrator sends the message to the recipient"
			orchestrator.sendCSATValidationRequest(recipient.xmppClient.connectedResource, csat)
		then: "you can check system.out to see if the industry/key is logged"
			assert true
	}

	def "Send an IQ requesting current industry averages and get the response"() {
		given: "The CSAT industry average request"
			Csat csat = createIndustryAverageRequest()
		when: "The IQ is sent"
			IQ resultIQ = orchestrator.xmppClient.query(IQ.get(recipient.xmppClient.connectedResource, csat)).result
			Csat result = resultIQ.getExtension(Csat.class)
		then: "The response contains the current industry averages per control/sub-control"
			assert result.average.control.size() == 2
			assert result.average.control[0].id == 1
			assert result.average.control[1].average == 2.3
	}
	def "Send an IQ requesting license key validation and get the response (valid)"() {
		given: "The CSAT license key validation request"
			Csat csat = createLicenseValidationRequest("d31f4355-ad3a-4214-ac69-b3625ffe4612")
		when: "The IQ is sent"
			IQ resultIQ = orchestrator.xmppClient.query(IQ.get(recipient.xmppClient.connectedResource, csat)).result
			Csat result = resultIQ.getExtension(Csat.class)
		then: "The response contains the validation result"
			assert result.validation.valid
	}
	def "Send an IQ requesting license key validation and get the response (invalid)"() {
		given: "The CSAT license key validation request"
			Csat csat = createLicenseValidationRequest("INVALID-ad3a-4214-ac69-b3625ffe4612")
		when: "The IQ is sent"
			IQ resultIQ = orchestrator.xmppClient.query(IQ.get(recipient.xmppClient.connectedResource, csat)).result
			Csat result = resultIQ.getExtension(Csat.class)
		then: "The response contains the validation result"
			assert result.validation.invalid.reason[0] == "Invalid license key"
	}
}


