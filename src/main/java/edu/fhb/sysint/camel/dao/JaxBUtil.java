package edu.fhb.sysint.camel.dao;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.net.URL;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import edu.fhb.sysint.camel.GlobalConstants;
import edu.fhb.sysint.camel.model.EarthquakeCollection;
import edu.fhb.sysint.camel.model.Weather;
import edu.fhb.sysint.camel.model.WeatherWrapper;

public class JaxBUtil {
	public static EarthquakeCollection unmarshall() {
		JAXBContext jaxbContext;
		try {
			jaxbContext = JAXBContext.newInstance(EarthquakeCollection.class);

			FileInputStream xml = new FileInputStream(
					GlobalConstants.IntermediateResult_ENRICHMENT);
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
	
	public static WeatherWrapper umarshallWeather(URL url){
		JAXBContext jaxbContext;
		try {
			jaxbContext = JAXBContext.newInstance(WeatherWrapper.class);

			Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();

			WeatherWrapper c = (WeatherWrapper) unmarshaller
					.unmarshal(url);
//			System.out.println("wrapper: "+c);
			return c;
		} catch (JAXBException e) {
			e.printStackTrace();
		} 

		return null;
	}
	
}
