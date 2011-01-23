package edu.fhb.softarch.medialib.model;

import java.util.Collection;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;


@XmlRootElement
public class EarthPartCollection {

	private Collection<Earthpart> earthparts;

	public EarthPartCollection() {
	}

	public EarthPartCollection(Collection<Earthpart> parts) {
		this.earthparts = parts;
	}

	@XmlElement(name = "Earthpart")
	@XmlElementWrapper(name = "Earthparts")
	public Collection<Earthpart> getEarthparts() {
		return earthparts;
	}

}