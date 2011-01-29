package edu.fhb.sysint.camel;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;

import edu.fhb.sysint.camel.model.Earthquake;
import edu.fhb.sysint.camel.model.EarthquakeCollection;

final class EmailProcessor implements Processor {
	public void process(Exchange exchange) throws Exception {
		EarthquakeCollection ec = exchange.getIn().getBody(
				EarthquakeCollection.class);

		String emailBody = "<b>Notification - Heavy Earthquakes</b><p/>"
				+ "<ul>";

		for (Earthquake e : ec.getEntries()) {
			emailBody += "<li><a href=\""
					+ GlobalConstants.PROTOCOL_HOST
					+ GlobalConstants.REST_SERVICE_RELATIVE_PATH
					+ e.getId() + "\">" + e.getTitle()
					+ " - M " + e.getSize() + "</a>" + "</li>";
		}

		emailBody += "</ul>";
		System.out.println(emailBody);
		exchange.getIn().setBody(emailBody, String.class);
	}
}