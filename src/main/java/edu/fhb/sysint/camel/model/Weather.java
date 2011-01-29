package edu.fhb.sysint.camel.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(namespace = "", name = "current_condition")
@XmlAccessorType(XmlAccessType.FIELD)
public class Weather {
	public Float temp_C;
	public String weatherDesc;
	public Integer windspeedKmph;
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Weather [temp_C=" + temp_C + ", weatherDesc=" + weatherDesc
				+ ", windspeedKmph=" + windspeedKmph + "]";
	}
}
