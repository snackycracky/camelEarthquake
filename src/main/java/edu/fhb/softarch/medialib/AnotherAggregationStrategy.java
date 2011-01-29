package edu.fhb.softarch.medialib;

import org.apache.camel.Exchange;
import org.apache.camel.processor.aggregate.AggregationStrategy;

public class AnotherAggregationStrategy implements AggregationStrategy {

	public Exchange aggregate(Exchange oldExchange, Exchange newExchange) {
		if (oldExchange == null) {
			return newExchange;
		}
		String oldBody = oldExchange.getIn().getBody(String.class);
		String newBody = newExchange.getIn().getBody(String.class);
		String body = oldBody + newBody;

//		System.out.println("body now: ------------------------>\n"+body);

		oldExchange.getIn().setBody(body);
		return oldExchange;
	}

}
