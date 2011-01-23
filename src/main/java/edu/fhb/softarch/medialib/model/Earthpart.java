package edu.fhb.softarch.medialib.model;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

@XmlRootElement(name = "Meldung", namespace = "x")
public class Earthpart {

	private Integer id;

	private String name;

	@XmlElement(name = "erathquake", namespace = "x")
	@XmlJavaTypeAdapter(XLinkAdapter.class)
	private Earthquake earthquake;

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

	@XmlElement(name = "id", namespace = "x")
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public void setName(String name) {
		this.name = name;
	}

	@XmlElement(name = "name", namespace = "x")
	public String getName() {
		return name;
	}

	@Override
	public String toString() {
		return String.format("{id=%s,meldung=%s}", id, name);
	}

}