package edu.fhb.softarch.medialib;

import org.apache.camel.Exchange;
import org.apache.camel.Message;
import org.apache.camel.processor.aggregate.AggregationStrategy;

public class MyAggregationStrategy implements AggregationStrategy {

	public Exchange aggregate(Exchange oldExchange, Exchange newExchange) {
		if (oldExchange == null) {
			return newExchange;
		}
		String oldBody = oldExchange.getIn().getBody(String.class);
		String newBody = newExchange.getIn().getBody(String.class);
		String body = oldBody + newBody;
		
		body=body.replaceFirst("</daten>", " ");	
		
		oldExchange.getIn().setBody(body);
		return oldExchange;
	}
}
