package org.cisecurity.xmpp.svc

import org.ietf.sacm.list.model.AssessmentContent
import org.ietf.sacm.list.model.AssessmentContentResource
import rocks.xmpp.core.session.XmppClient
import rocks.xmpp.core.stanza.IQHandler
import rocks.xmpp.core.stanza.model.IQ

class AssessmentContentService implements IIQHandlerService {

	/**
	 * Add the IQ handler to the XMPP client to process Assessment Content related custom IQs
	 * @param xmppClient
	 * @return
	 */
	def addIQHandler(XmppClient xmppClient) {
		xmppClient.addIQHandler(AssessmentContent.class, new IQHandler() {
			/**
			 * Handle an IQ request for Assessment Content.
			 * A "policy repository" would have available a set of resources that an
			 * endpoint could grab in order to perform collection of posture attributes.
			 * @param iq
			 * @return
			 */
			@Override
			IQ handleRequest(IQ iq) {
				if (iq.hasExtension(AssessmentContent.class)) {
					AssessmentContent assessmentContent = iq.getExtension(AssessmentContent.class)
					if (assessmentContent) {
						AssessmentContent rez = new AssessmentContent(assessmentContent.getCommand() + " :: RESULT")
						rez.addContentResource(new AssessmentContentResource("http://cisecurity.org/content/resource-uri"))
						return iq.createResult(rez)
					}
				}
			}
		})
	}
}
