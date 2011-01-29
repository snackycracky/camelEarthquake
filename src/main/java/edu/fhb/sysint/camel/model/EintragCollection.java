package edu.fhb.sysint.camel.model;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "earthquakes", namespace = "")
@XmlAccessorType(XmlAccessType.FIELD)
public class EintragCollection {

	@XmlElement(name = "earthquake", namespace = "")
	private List<Eintrag> entries = new ArrayList<Eintrag>();;

	public EintragCollection() {
	}

	public EintragCollection(List<Eintrag> parts) {
		this.entries = parts;
	}

	public List<Eintrag> getEntries() {
		return entries;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		Integer size = 0;
		if (entries != null) {
			size = entries.size();
		}
		return "EintragCollection [ length(entries) = " + size + "]";
	}

}