package edu.fhb.softarch.medialib;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;

import edu.fhb.softarch.GlobalConstants;

class RemoveXMLHeaderProcessor implements Processor {
	

	public void process(Exchange exchange) throws Exception {

		String body = exchange.getIn().getBody(String.class);
		body = body.replaceAll("<\\?xml(.*)>", "");

		CommonUtils.writeToFile(
				GlobalConstants.IntermediateResult_ENRICHMENT,
				body, false);
	}
}