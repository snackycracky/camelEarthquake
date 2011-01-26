package edu.fhb.softarch.medialib;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.converter.jaxb.JaxbDataFormat;
import org.apache.camel.spi.DataFormat;

import edu.fhb.softarch.medialib.model.EintragCollection;

public class InputRouter extends RouteBuilder {

	@Override
	public void configure() throws Exception {

	
		
		
		final Fileops file = new Fileops();
		final DataFormat jaxb = new JaxbDataFormat("edu.fhb.softarch.medialib.model");

//		context.addRoutes(new RouteBuilder() {
//			public void configure() {
				from("http://geofon.gfz-potsdam.de/db/eqinfo.php?fmt=rss&splitEntries=false")
						.setHeader("visited", constant(true))
						.to("xslt:data/xsl/transformation.xsl")
						.to("direct:start");
//			}
//		});
//		context.addRoutes(new RouteBuilder() {
//			public void configure() {

				from("http://earthquake.usgs.gov/eqcenter/catalogs/eqs1day-M2.5.xml?splitEntries=false")
						.setHeader("visited", constant(true))
						.to("xslt:data/xsl/transformation2.xsl")
						.to("direct:start");
//			}
//		});
	

//		context.addRoutes(new RouteBuilder() {
//			public void configure() {
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
//			}
//		});
		
//		context.addRoutes(new RouteBuilder() {
//			public void configure() {
   
				from("direct:UnmarshallMergedSources")
				.unmarshal(jaxb)
				.process(new Processor() { 
					public void process(Exchange exchange) throws Exception {
						EintragCollection ec = exchange.getIn().getBody(
								EintragCollection.class);
						System.out.println("found something!\n\n\n\n"+ec);
					}
				});
//			}
//		});
		
//		context.addRoutes(new RouteBuilder() {
//			public void configure() {
				from("direct:filterBiggestEarthquakes")
				.filter()
                .xpath("/daten/eintrag[size>5.5]")//
				.to("direct:NotifyByEmail")
				.process(new Processor() { 
					public void process(Exchange exchange) throws Exception {
						System.out.println(exchange.getIn());
					} 
				});
//			} 
//		});
//		context.addRoutes(new RouteBuilder() {
//			public void configure() {
				from("direct:NotifyByEmail")
				.to("smtps://camelfhb@smtp.gmail.com?password=camelfhb31&to=camelfhb@googlemail.com")
				.process(new Processor() { 
					public void process(Exchange exchange) throws Exception {
//						EintragCollection ec = exchange.getIn().getBody(
//								EintragCollection.class);
					} 
				});
//			} 
//		});
//		
		
		Thread.sleep(5000);
	}

}
