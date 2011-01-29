package edu.fhb.sysint.camel.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

@XmlRootElement(namespace = "", name = "earthquake")
@XmlAccessorType(XmlAccessType.FIELD)
public class Earthquake {
	
	@XmlElement
	private String title = "";
	@XmlElement
	private Float size = 0.0f;
	@XmlElement
	private String date = "";
	@XmlElement
	private String location = "";

	@XmlElement
	private Weather weather;
	@XmlElement
	private String country = "";
	

	@XmlAttribute
	private Integer id ;

	public Earthquake() {
	}

	/**
	 * @return the title
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * @param title
	 *            the title to set
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(Integer id) {
		this.id = id;
	}
	/**
	 * @return the size
	 */
	public Float getSize() {
		return size;
	}

	/**
	 * @param size
	 *            the size to set
	 */
	public void setSize(Float size) {
		this.size = size;
	}

	/**
	 * @return the date
	 */
	public String getDate() {
		return date;
	}

	/**
	 * @param date
	 *            the date to set
	 */
	public void setDate(String date) {
		this.date = date;
	}

	/**
	 * @return the weather
	 */
	public Weather getWeather() {
		return weather;
	}

	/**
	 * @param weather
	 *            the weather to set
	 */
	public void setWeather(Weather weather) {
		this.weather = weather;
	}

	/**
	 * @return the counrty
	 */
	public String getCounrty() {
		return country;
	}

	/**
	 * @param location
	 *            the location to set
	 */
	public void setLocation(String location) {
		this.location = location;
	}

	/**
	 * @param city
	 *            the city to set
	 */
	public void setCountry(String counrty) {
		this.country = counrty;
	}

	public Earthquake(final Float size, final String title) {
		this.size = size;
		this.title = title;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "\n\tEarthquake [title=" + title + ", counrty=" + country + "]";
	}

	public String getLocation() {
		return location;
	}

	public Integer getId() {
		return id;
	}

}