package edu.fhb.softarch.medialib.model;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(namespace = "xlink")
public class EarthquakeXLink {

	@XmlAttribute(namespace = "http://www.w3.org/1999/xlink")
	private String href = "http://www.example.org";

	@XmlAttribute(namespace = "http://www.w3.org/1999/xlink")
	private String type = "simple";

	@XmlAttribute(namespace = "http://www.w3.org/1999/xlink")
	private String show = "new";

	@XmlElement(namespace = "")
	private String name;

	public EarthquakeXLink() {
	}

	public EarthquakeXLink(String href, String name) {
		super();
		this.href = href;
		this.name = name;
	}

}