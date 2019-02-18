package org.ietf.sacm.list.model;

import javax.xml.bind.annotation.*;
import java.util.ArrayList;

/**
 * The implementation of the {@code <assessment-content/>} element in the {@code urn:xmpp:sacm:collection} namespace.
 */
@XmlRootElement(name = "assessment-content", namespace = "urn:xmpp:sacm:collection")
@XmlAccessorType(XmlAccessType.FIELD)
public class AssessmentContent {
	/**
	 * urn:xmpp:sacm:collection
	 */
//	public static final String NAMESPACE = "urn:xmpp:sacm:collection";

	@XmlElement(name = "command", namespace = "urn:xmpp:sacm:collection")
	private String command;

	private ArrayList<AssessmentContentResource> contentList;

	private AssessmentContent() { /* Private, no-arg constructor for JAXB */ }

	public AssessmentContent(String command) {
		this.command = command;
	}

	public String getCommand() {
		return command;
	}

	public void setCommand(String command) {
		this.command = command;
	}

	public ArrayList<AssessmentContentResource> getContentList() { return contentList; }

	public void setContentList(ArrayList<AssessmentContentResource> contentList1) {
		if (contentList == null) {
			contentList = new ArrayList<>();
		}
		contentList.clear();
		contentList.addAll(contentList1);
	}

	public void addContentResource(AssessmentContentResource assessmentContentResource) {
		if (contentList == null) {
			contentList = new ArrayList<>();
		}
		contentList.add(assessmentContentResource);
	}

	@Override
	public String toString() {
		return "Assessment Content :: Command -> " + command;
	}
}
