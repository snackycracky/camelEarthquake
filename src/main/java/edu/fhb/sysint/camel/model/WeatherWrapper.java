package edu.fhb.sysint.camel.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(namespace = "", name = "data")
@XmlAccessorType(XmlAccessType.FIELD)
public class WeatherWrapper {

	/**
	 * @return the current_condition
	 */
	public Weather getCurrent_condition() {
		return current_condition;
	}

	@XmlElement(name="current_condition")
	private Weather current_condition;

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "WeatherWrapper [current_condition=" + current_condition + "]";
	}
}
