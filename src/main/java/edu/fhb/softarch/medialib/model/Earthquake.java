package edu.fhb.softarch.medialib.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement( namespace="",name="eintrag")
@XmlAccessorType(XmlAccessType.FIELD)
public class Earthquake {

	@XmlElement 
	private String title;
	@XmlElement
	private Float size;
	@XmlElement
	private String date;
	@XmlElement
	private String location;
	@XmlElement
	private String weather;

	public Earthquake() {
	}

	public Earthquake(final Float size, final String title) {
		this.size = size;
		this.title = title;
	}

}