package edu.fhb.sysint.camel.model;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "earthparts", namespace = "")
@XmlAccessorType(XmlAccessType.FIELD)
public class EarthPartCollection {

	@XmlElement(name = "earthpart", namespace = "")
	private List<Earthpart> earthparts = new ArrayList<Earthpart>();

	public EarthPartCollection() {
	}

	public EarthPartCollection(List<Earthpart> parts) {
		this.earthparts = parts;
	}

	public EarthPartCollection(Earthpart part) {
		this.earthparts.add(part);
	}

	// @XmlElement(name = "Earthpart")
	// @XmlElementWrapper(name = "Earthparts")
	public List<Earthpart> getEarthparts() {
		return earthparts;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "\n\tEarthPartCollection [earthparts=" + earthparts + "]";
	}

	/**
	 * @param earthparts
	 *            the earthparts to set
	 */
	public void setEarthparts(List<Earthpart> earthparts) {
		this.earthparts = earthparts;
	}
}