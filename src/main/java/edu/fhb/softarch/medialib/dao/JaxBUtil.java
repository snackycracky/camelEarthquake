package edu.fhb.softarch.medialib.dao;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import edu.fhb.softarch.GlobalConstants;
import edu.fhb.softarch.medialib.model.EarthquakeCollection;

public class JaxBUtil {
	public static EarthquakeCollection unmarshall() {
		JAXBContext jaxbContext;
		try {
			jaxbContext = JAXBContext.newInstance(EarthquakeCollection.class);

			FileInputStream xml = new FileInputStream(
					GlobalConstants.IntermediateResult);
			Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();

			EarthquakeCollection c = (EarthquakeCollection) unmarshaller
					.unmarshal(xml);
			assert c.getEntries() != null;
			System.out.println("returning an entry now; " + c.getEntries());

			return c;
		} catch (JAXBException e) {
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		return null;
	}
}
