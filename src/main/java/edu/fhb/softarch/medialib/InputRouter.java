package edu.fhb.softarch.medialib;

import java.io.File;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.converter.jaxb.JaxbDataFormat;
import org.apache.camel.spi.DataFormat;

import edu.fhb.softarch.GlobalConstants;
import edu.fhb.softarch.medialib.model.EarthquakeCollection;

public class InputRouter extends RouteBuilder {

	@Override
	public void configure() throws Exception {

		final Fileops file = new Fileops();
		final DataFormat jaxb = new JaxbDataFormat("edu.fhb.softarch.medialib.model");

//		context.addRoutes(new RouteBuilder() {
//			public void configure() {
//				from("http://geofon.gfz-potsdam.de/db/eqinfo.php?fmt=rss&splitEntries=false")
			
		
		
		
		from("http://localhost/eqInfo.xml")
						.setHeader("visited", constant(true))
						.to("xslt:data/xsl/transformation.xsl")
						.to("direct:start").delay(1000);
			
			
			
			
			
			
//			}
//		});
//		context.addRoutes(new RouteBuilder() {
//			public void configure() {

//				from("http://earthquake.usgs.gov/eqcenter/catalogs/eqs1day-M2.5.xml?splitEntries=false")
				from("http://localhost/eqs1dat.xml")
						.setHeader("visited", constant(true))
						.to("xslt:data/xsl/transformation2.xsl")
						.to("direct:start").delay(1000);
//			}
//		});
	

//		context.addRoutes(new RouteBuilder() {
//			public void configure() {
				from("direct:start")
						.aggregate(header("visited"),
								new MyAggregationStrategy())
						.completionSize(2)
						.completionTimeout(20000)
						.delay(3000)
						.process(new Processor() {
							public void process(Exchange exchange)
									throws Exception {
								
								String body = exchange.getIn().getBody(
										String.class);
								
								String resultFilepath = GlobalConstants.IntermediateResult;
				 				File f = new File(
										resultFilepath);
								if (f.delete() || !f.exists()) {
									file.writeToFile(
											resultFilepath,
											"\n" + body, true);
								}
								
							}
						})
						.to("direct:UnmarshallMergedSources")
						.to("direct:filterBiggestEarthquakes")
						;
//			}
//		});
		
//		context.addRoutes(new RouteBuilder() {
//			public void configure() {
   
				from("direct:UnmarshallMergedSources")
				.unmarshal(jaxb)
				.delay(3000)
				.process(new Processor() { 
					public void process(Exchange exchange) throws Exception {
						EarthquakeCollection ec = exchange.getIn().getBody(
								EarthquakeCollection.class);
						System.out.println("found something!\n\n\n\n"+ec);
						//Thread.sleep(10000);
					}
				});
//			}
//		});
		
//		context.addRoutes(new RouteBuilder() {
//			public void configure() {
				from("direct:filterBiggestEarthquakes").delay(3000)
				.filter()
                .xpath("/daten/eintrag/size/text()>5.5]")//
				.to("direct:NotifyByEmail")
				.process(new Processor() { 
					public void process(Exchange exchange) throws Exception {
						//System.out.println(exchange.getIn());
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
				}).delay(3000);
//			} 
//		});
//		
		
		Thread.sleep(5000);
	}

}
