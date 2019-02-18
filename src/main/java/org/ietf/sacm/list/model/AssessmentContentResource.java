package org.ietf.sacm.list.model;

import javax.xml.bind.annotation.XmlElement;

public class AssessmentContentResource {

	@XmlElement(name = "resource-uri")
	String resourceUri;

	public AssessmentContentResource() {}

	public AssessmentContentResource(String resourceUri) { this.resourceUri = resourceUri; }

	@Override
	public String toString() { return "Resource: " + resourceUri; }
}