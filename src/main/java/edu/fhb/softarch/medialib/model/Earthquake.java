package edu.fhb.softarch.medialib.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class Earthquake {

	private Float strength;
	private Integer length;

	public Earthquake() {
	}

	public Earthquake(final Float strength, final Integer length) {
		this.strength = strength;
		this.length = length;
	}

}