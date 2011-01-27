package edu.fhb.softarch.medialib.model;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;


@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class EarthPartCollection {

	private List<Earthpart> earthparts = new ArrayList<Earthpart>();

	public EarthPartCollection() {
	}

	public EarthPartCollection(List<Earthpart> parts) {
		this.earthparts = parts;
	}

//	@XmlElement(name = "Earthpart")
	//@XmlElementWrapper(name = "Earthparts")
	public List<Earthpart> getEarthparts() {
		return earthparts;
	}

	/**
	 * @param earthparts the earthparts to set
	 */
	public void setEarthparts(List<Earthpart> earthparts) {
		this.earthparts = earthparts;
	}
}