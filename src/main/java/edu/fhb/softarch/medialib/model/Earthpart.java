package edu.fhb.softarch.medialib.model;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

@XmlRootElement(name = "Meldung", namespace = "x")
@XmlAccessorType(XmlAccessType.FIELD)
public class Earthpart {

	@XmlElement(name = "id", namespace = "x")
	private Integer id;

	@XmlElement(name = "name", namespace = "x")
	private String name;
	@XmlElement(name = "counrty", namespace = "x")
	private String country;

	@XmlElement(name = "erathquake", namespace = "x")
	@XmlJavaTypeAdapter(XLinkAdapterEarthquake.class)
	private Earthquake earthquake;
	
	@XmlElementWrapper(name="erathquakes")
	@XmlElement(name = "erathquake", namespace = "x")
	@XmlJavaTypeAdapter(XLinkAdapterEarthquake.class)
	private List<Earthquake> erathquakes;
	
	public Earthpart() {
	}

	public Earthpart(Integer id, String name) {
		this.id = id;
		this.name = name;
	}

	public Earthpart(Integer id, String name, Earthquake el) {
		this.id = id;
		this.name = name;
		this.earthquake = el;
	}

	public Earthpart(Earthquake el) {
		this.country = el.getCounrty();
		this.earthquake = el;
	}

	public Earthpart(String country2) {
		this.country = country2;
	}

	public Earthpart(String country2, EarthquakeCollection earthquakeCollection) {
		this.country = country2;
		this.erathquakes = earthquakeCollection.getEntries();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	@Override
	public String toString() {
		return String.format("{id=%s,meldung=%s}", id, name);
	}

}