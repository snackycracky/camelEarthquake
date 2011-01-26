package edu.fhb.softarch.medialib;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;

import org.apache.camel.CamelContext;
import org.apache.camel.Exchange;
import org.apache.camel.Message;
import org.apache.camel.Processor;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.builder.xml.Namespaces;
import org.apache.camel.component.cxf.CxfConstants;
import org.apache.camel.converter.jaxb.JaxbDataFormat;
import org.apache.camel.impl.DefaultCamelContext;
import org.apache.camel.spi.DataFormat;

import edu.fhb.softarch.medialib.dao.EarthpartDao;
import edu.fhb.softarch.medialib.dao.EarthquakeDao;
import edu.fhb.softarch.medialib.model.Earthpart;
import edu.fhb.softarch.medialib.model.Earthquake;
import edu.fhb.softarch.medialib.model.EintragCollection;



public class Driver {
	public static void main(String args[]) throws Exception {
		CamelContext context = new DefaultCamelContext();

		final Fileops file = new Fileops();
		final DataFormat jaxb = new JaxbDataFormat("edu.fhb.softarch.medialib.model");

		context.addRoutes(new RouteBuilder() {
			public void configure() {
				from("file:/Users/nils/Desktop/in?noop=true")
						.setHeader("visited", constant(true))
						.to("xslt:file:src/main/resources/data/xsl/transformation.xsl")
						.to("direct:start");
			}
		});
		context.addRoutes(new RouteBuilder() {
			public void configure() {

				from("file:/Users/nils/Desktop/in1?noop=true")
						.setHeader("visited", constant(true))
						.to("xslt:file:src/main/resources/data/xsl/transformation2.xsl")
						.to("direct:start");
			}
		});
	

		context.addRoutes(new RouteBuilder() {
			public void configure() {
				from("direct:start")
						.aggregate(header("visited"),
								new MyAggregationStrategy())
						.completionSize(3)
						.completionTimeout(20000)
						.process(new Processor() {
							public void process(Exchange exchange)
									throws Exception {
								String body = exchange.getIn().getBody(
										String.class);
								
								System.out.println(body);
								file.writeToFile(
										"/Users/nils/Desktop/zwischenergebnis.xml",
										"\n" + body, true);
							}
						})
						.to("direct:UnmarshallMergedSources")
						.to("direct:filterBiggestEarthquakes");
			}
			
			
			
			
			
			
		});
		
		context.addRoutes(new RouteBuilder() {
			public void configure() {
   
				from("direct:UnmarshallMergedSources")
				.unmarshal(jaxb)
				.process(new Processor() { 
					public void process(Exchange exchange) throws Exception {
						
						EintragCollection ec = exchange.getIn().getBody(
								EintragCollection.class);
						
						System.out.println("\n\nfound something!\n\n");
						
					}
				});
			}
		});
		
		context.addRoutes(new RouteBuilder() {
			public void configure() {
				from("direct:filterBiggestEarthquakes")
				.filter()
                .xpath("/daten/eintrag[size>5.5]")//
				.to("direct:NotifyByEmail")
				.process(new Processor() { 
					public void process(Exchange exchange) throws Exception {
//						System.out.println(exchange.getIn());
					} 
				});
			}
		});
		context.addRoutes(new RouteBuilder() {
			public void configure() {
				from("direct:NotifyByEmail")
				.to("smtps://camelfhb@smtp.gmail.com?password=camelfhb31&to=camelfhb@googlemail.com")
				.process(new Processor() { 
					public void process(Exchange exchange) throws Exception {
//						EintragCollection ec = exchange.getIn().getBody(
//								EintragCollection.class);
					}
				});
			}
		});
		
		context.start();
		Thread.sleep(35000);
	}

	protected static void syso(Object string) {
		System.out.println(string);

	}
}
