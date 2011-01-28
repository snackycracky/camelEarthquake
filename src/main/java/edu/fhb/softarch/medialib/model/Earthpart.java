package edu.fhb.softarch.medialib.model;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import edu.fhb.softarch.medialib.model.adapter.XLinkAdapterEarthquake;

@XmlRootElement(name = "earthpart")
@XmlAccessorType(XmlAccessType.FIELD)
public class Earthpart {

	@XmlAttribute(name = "name")
	private String name = "";

	@XmlElementWrapper(name = "erathquakes")
	@XmlElement(name = "erathquake")
	@XmlJavaTypeAdapter(XLinkAdapterEarthquake.class)
	private List<Earthquake> earthquakes;

	public Earthpart() {
	}

	public Earthpart(String name) {
		this.name = name;
	}

	public Earthpart(String name, EarthquakeCollection earthquakeCollection) {
		this.name = name;
		this.earthquakes = earthquakeCollection.getEntries();
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "\n\tEarthpart [name=" + name + ", earthquakes=" + earthquakes
				+ "]";
	}

}