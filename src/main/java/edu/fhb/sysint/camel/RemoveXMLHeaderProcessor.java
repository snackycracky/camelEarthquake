package edu.fhb.sysint.camel;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;


class RemoveXMLHeaderProcessor implements Processor {
	

	public void process(Exchange exchange) throws Exception {

		String body = exchange.getIn().getBody(String.class);
		body = body.replaceAll("<\\?xml(.*)>", "");

		CommonUtils.writeToFile(
				GlobalConstants.IntermediateResult_ENRICHMENT,
				body, false);
	}
}