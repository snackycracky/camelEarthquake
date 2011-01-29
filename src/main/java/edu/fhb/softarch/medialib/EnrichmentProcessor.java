package edu.fhb.softarch.medialib;

import java.util.ArrayList;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;

import edu.fhb.softarch.medialib.model.Earthquake;
import edu.fhb.softarch.medialib.model.EarthquakeCollection;

final class EnrichmentProcessor implements Processor {
	

	public void process(Exchange exchange) throws Exception {
		EarthquakeCollection ec = exchange.getIn().getBody(
				EarthquakeCollection.class);
		ArrayList<Earthquake> listClone = new ArrayList<Earthquake>();
		int i = 1;
		for (Earthquake e : ec.getEntries()) {
			String additionalInfo = CommonUtils
					.findAdditionalInfo(e.getLocation());

			e.setCountry(additionalInfo.contains("not found") ? "undefined"
					: additionalInfo);
			e.setId(i++);

			listClone.add(e);
		}
		ec.setEntries(listClone);
		exchange.getIn()
				.setBody(ec, EarthquakeCollection.class);
	}
}