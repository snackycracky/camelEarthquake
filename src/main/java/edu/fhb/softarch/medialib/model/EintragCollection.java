package edu.fhb.softarch.medialib.model;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "daten", namespace="")
@XmlAccessorType(XmlAccessType.FIELD)
public class EintragCollection {

	
	@XmlElement(name = "eintrag", namespace="")
	private List<Eintrag> entries =  new ArrayList<Eintrag>();;

	public EintragCollection() {
	}

	public EintragCollection(List<Eintrag> parts) {
		this.entries = parts;
	}

	public List<Eintrag> getEntries() {
		return entries;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "EintragCollection [entries=" + entries + "]";
	}

	

	
}