package edu.fhb.softarch.medialib.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement( namespace="",name="eintrag")
@XmlAccessorType(XmlAccessType.FIELD)
public class Eintrag {

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
	
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Eintrag [title=" + title + "]";
	}

}
