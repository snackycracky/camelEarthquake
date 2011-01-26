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
		JAXBContext jaxbContext;
		try {
			jaxbContext = JAXBContext.newInstance(EarthquakeCollection.class);

			FileInputStream xml = new FileInputStream(
					GlobalConstants.IntermediateResult);
			Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();

			EarthquakeCollection l = (EarthquakeCollection) unmarshaller
					.unmarshal(xml);
			assert l.getEntries() != null;
			System.out.println("returning an entry now; " + l.getEntries());

			return l.getEntries().get(id);
		} catch (JAXBException e) {
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

		if (id == 1) {
			// return new Earthquake(1.1f,1);
		} else {
			// return new Earthquake(1.2f,1);
		}
		return null;
	}
}
