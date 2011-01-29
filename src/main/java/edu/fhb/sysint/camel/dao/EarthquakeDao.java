package edu.fhb.sysint.camel.dao;

import edu.fhb.sysint.camel.model.Earthquake;

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
