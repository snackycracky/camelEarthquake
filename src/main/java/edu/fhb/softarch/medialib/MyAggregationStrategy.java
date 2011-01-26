package edu.fhb.softarch.medialib;

import org.apache.camel.Exchange;
import org.apache.camel.processor.aggregate.AggregationStrategy;

public class MyAggregationStrategy implements AggregationStrategy {

	public Exchange aggregate(Exchange oldExchange, Exchange newExchange) {
		if (oldExchange == null) {
			return newExchange;
		}
		String oldBody = oldExchange.getIn().getBody(String.class);
		String newBody = newExchange.getIn().getBody(String.class);
		String body = oldBody + newBody;

		body = body
		.replaceAll("<\\?xml version=\"1\\.0\" encoding=\"UTF-8\"\\?>", "")
		.replaceAll("</daten>(.*)<daten>", "")
		.replaceAll("</daten><daten xmlns:geo=\"http://www\\.w3\\.org/2003/01/geo/wgs84_pos#\">", "")
		.replaceAll("</daten><daten>", "");
//		.replaceAll("<daten>", "")
//		.replaceAll("<daten(.*)>","")
		

		oldExchange.getIn().setBody(body);
		return oldExchange;
	}
}
