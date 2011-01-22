package edu.fhb.softarch.medialib;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;

import org.apache.camel.Exchange;
import org.apache.camel.Message;
import org.apache.camel.Processor;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.cxf.CxfConstants;

public class ExampleRouter extends RouteBuilder {

	@Override
	public void configure() throws Exception {

		from(
				"cxfrs://http://localhost:9000?resourceClasses="
						+ ExampleResource.class.getName()).process(
				new Processor() {
					public void process(Exchange exchange) throws Exception {
						// custom processing here
						Message inMessage = exchange.getIn();
						String operationName = inMessage.getHeader(CxfConstants.OPERATION_NAME, String.class);
						System.out.println("the operation name is: "+operationName);
	                    if ("getMeldung".equals(operationName)) {
	                        String httpMethod = inMessage.getHeader(Exchange.HTTP_METHOD, String.class);
	                       // assertEquals("Get a wrong http method", "GET", httpMethod);
	                        String path = inMessage.getHeader(Exchange.HTTP_PATH, String.class);
	                        // The parameter of the invocation is stored in the body of in message
	                        String id = (String) inMessage.getBody(String.class);
	                        if ("/myservice/Meldung/1".equals(path)) {                            
	                            Meldung customer = new Meldung(1,"Willem");
	                            System.out.println("ok! setting body for response now");
	                            // We just put the response Object into the out message body
	                            exchange.getOut().setBody(customer);
	                        } else {
		                    	System.out.println("the path was: "+ path);
	                            Response r = Response.status(404).entity("Can't find the customer with uri " + path).build();
	                            throw new WebApplicationException(r);
	                        }
	                    }else{
	                    	System.out.println("the actual operation name was: "+operationName);
	                    }
					}
				});
	}
}
