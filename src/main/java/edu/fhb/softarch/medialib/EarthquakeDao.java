package edu.fhb.softarch.medialib;

public class EarthquakeDao {
	public static Earthquake findById(Integer id) {
		if (id == 1) {
			return new Earthquake("http://www.google.com","QUAKI");
		} else {
			return new Earthquake("http://www.google.com","Little Quaki");
		}
	}
}
