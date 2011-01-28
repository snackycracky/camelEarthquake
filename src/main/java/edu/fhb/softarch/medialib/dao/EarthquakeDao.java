package edu.fhb.softarch.medialib.dao;

import edu.fhb.softarch.medialib.model.Earthquake;

public class EarthquakeDao {
	public static Earthquake findById(Integer id) {
		for (Earthquake iterable_element : JaxBUtil.unmarshall().getEntries()) {
			if (iterable_element.getId() == id) {
				return iterable_element;
			}
		}
		return null;
	}
}
