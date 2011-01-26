package edu.fhb.softarch.medialib.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "daten",namespace="http://www.w3.org/1999/xlink")
@XmlAccessorType(XmlAccessType.FIELD)
public class Eintrag {
	@XmlElement
	private String daten;
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

}
