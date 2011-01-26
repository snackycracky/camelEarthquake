package edu.fhb.softarch.medialib.dao;

import edu.fhb.softarch.medialib.model.Earthquake;

public class EarthquakeDao {
	public static Earthquake findById(Integer id) {
		if (id == 1) {
			return new Earthquake(1.1f,1);
		} else {
			return new Earthquake(1.2f,1);
		}
	}
}
