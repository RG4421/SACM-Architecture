package org.cis.xmpp.addition.model;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.Objects;

@XmlRootElement(name = "addition")
public final class Addition {

	@XmlElement(name = "summand1")
	private Integer summand1;

	@XmlElement(name = "summand2")
	private Integer summand2;

	@XmlElement(name = "sum")
	private Integer sum;

	/**
	 * No-arg default constructor needed for JAXB.
	 */
	private Addition() {
	}

	public Addition(Integer summand1, Integer summand2) {
		this.summand1 = Objects.requireNonNull(summand1);
		this.summand2 = Objects.requireNonNull(summand2);
	}

	public Addition(Integer sum) {
		this.sum = Objects.requireNonNull(sum);
	}

	public Integer getSummand1() {
		return summand1;
	}

	public Integer getSummand2() {
		return summand2;
	}

	@Override
	public String toString() {
		if (summand1 != null && summand2 != null) {
			return summand1 + " + " + summand2 + " = ???";
		}
		return "Sum: " + sum;
	}
}