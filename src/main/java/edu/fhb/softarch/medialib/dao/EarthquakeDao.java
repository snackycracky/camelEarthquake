package edu.fhb.softarch.medialib.dao;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import edu.fhb.softarch.GlobalConstants;
import edu.fhb.softarch.medialib.model.Earthquake;
import edu.fhb.softarch.medialib.model.EarthquakeCollection;

public class EarthquakeDao {
	public static Earthquake findById(Integer id) {
		
		System.out.println("EarthquakeDao.findById()");

		return JaxBUtil.unmarshall().getEntries().get(id);

		
	}
}
