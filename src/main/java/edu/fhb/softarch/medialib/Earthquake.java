	package edu.fhb.softarch.medialib;


import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlNs;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSchema;
import javax.xml.bind.annotation.XmlSchemaType;

import org.apache.camel.builder.xml.Namespaces;

@XmlRootElement(namespace = "xlink",name = "Child_Element_With_XLink")//,
public class Earthquake {
	
	@XmlAttribute(namespace = "http://www.w3.org/1999/xlink")
	private String href = "http://www.example.org";
	
	@XmlAttribute(namespace = "http://www.w3.org/1999/xlink")
	private String type = "simple";
	
	@XmlAttribute(namespace = "http://www.w3.org/1999/xlink")
	private String show = "new";
	
	@XmlElement(namespace="")
	private String name;
	
	public Earthquake(){}
	public Earthquake(String href,String name) {
		super();
		this.href = href;
		this.name = name;
	}


}