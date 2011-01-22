package edu.fhb.softarch.medialib;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "Meldung", namespace = "x")
public class Earthpart {

	private Integer id;
	
	private String name;
	
	@XmlElement(name="erathquake", namespace="x")
	private Earthquake erathquake;

	public Earthpart() {
	}

	public Earthpart(Integer id, String name) {
		this.id = id;
		this.name = name;
	}
	public Earthpart(Integer id, String name,Earthquake el) {
		this.id = id;
		this.name = name;
		this.erathquake = el;
	}
	
	@XmlElement(name="id", namespace="x")
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public void setName(String name) {
		this.name = name;
	}

	@XmlElement(name="name", namespace="x")
	public String getName() {
		return name;
	}

	@Override
	public String toString() {
		return String.format("{id=%s,meldung=%s}", id, name);
	}

}