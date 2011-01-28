package edu.fhb.softarch.medialib.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement( namespace="",name="eintrag")
@XmlAccessorType(XmlAccessType.FIELD)
public class Earthquake {

	@XmlElement 
	private String title = "";
	@XmlElement
	private Float size= 0.0f;
	@XmlElement
	private String date= "";
	@XmlElement
	private String location= "";
	@XmlElement
	private String weather= "";
	@XmlElement
	private String country= "";

	public Earthquake() {
	}

	/**
	 * @return the title
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * @param title the title to set
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	/**
	 * @return the size
	 */
	public Float getSize() {
		return size;
	}

	/**
	 * @param size the size to set
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
	 * @param date the date to set
	 */
	public void setDate(String date) {
		this.date = date;
	}

	/**
	 * @return the weather
	 */
	public String getWeather() {
		return weather;
	}

	/**
	 * @param weather the weather to set
	 */
	public void setWeather(String weather) {
		this.weather = weather;
	}

	/**
	 * @return the counrty
	 */
	public String getCounrty() {
		return country;
	}

	/**
	 * @param location the location to set
	 */
	public void setLocation(String location) {
		this.location = location;
	}

	/**
	 * @param city the city to set
	 */
	public void setCountry(String counrty) {
		this.country = counrty;
	}

	public Earthquake(final Float size, final String title) {
		this.size = size;
		this.title = title;
	}


	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Earthquake [title=" + title + ", counrty=" + country + "]";
	}

	public String getLocation() {
		return location;
	}

}