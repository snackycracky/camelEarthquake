package edu.fhb.sysint.camel.model;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "earthquakes", namespace = "")
@XmlAccessorType(XmlAccessType.FIELD)
public class EarthquakeCollection {

	@XmlElement(name = "earthquake", namespace = "")
	private List<Earthquake> entries = new ArrayList<Earthquake>();;

	public EarthquakeCollection() {
	}

	public EarthquakeCollection(List<Earthquake> parts) {
		this.entries = parts;
	}

	public List<Earthquake> getEntries() {
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
		return "EarthquakeCollection [ length(entries) = " + size + "]";
	}

	public void setEntries(List<Earthquake> entries) {
		this.entries = entries;
	}

}