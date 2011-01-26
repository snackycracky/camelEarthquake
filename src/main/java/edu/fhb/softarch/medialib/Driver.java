package edu.fhb.softarch.medialib;

import java.util.List;

import org.apache.camel.CamelContext;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.converter.jaxb.JaxbDataFormat;
import org.apache.camel.impl.DefaultCamelContext;
import org.apache.camel.model.dataformat.XStreamDataFormat;
import org.apache.camel.spi.DataFormat;

import com.thoughtworks.xstream.XStream;

import edu.fhb.softarch.medialib.model.Eintrag;



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

//				from("file:/Users/nils/Desktop/in1?noop=true")
//						.setHeader("visited", constant(true))
//						.to("xslt:file:src/main/resources/data/xsl/transformation2.xsl")
//						.to("direct:start");
			}
		});
	

		context.addRoutes(new RouteBuilder() {
			public void configure() {
				from("direct:start")
						.aggregate(header("visited"),
								new MyAggregationStrategy())
						.completionSize(3)
						.completionTimeout(5000)
						// .completionPredicate(property("completionFromBatchConsumer").isEqualTo(true))
						// .to("file:resultSet?fileName=resultfile.xml");
						.log("Completed by ${property.CamelAggregatedCompletedBy}")
						// .to("xslt:file:src/main/resources/data/xsl/transformation2.xsl")
						
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
						.to("direct:x");

				// from("file:/Users/nils/Desktop/camelTest?noop=true")
				// .from("file:/Users/nils/Desktop/othercamelTest?noop=true")
				// .log("Sending ${body} id: ${header.id}")
				// .aggregate(xpath("/"), new MyAggregationStrategy())//
				// .completionSize(1).completionTimeout(500000)
				// .log("Sending out ${body} ")
				// .to("xslt:file:src/main/resources/data/xsl/transformation.xsl");

				// from("file:/Users/nils/Desktop/camelTest?noop=true")
				// .log("-------------------------------------Sending ${body} with correlation key ${header.myId}")
				// .aggregate(header("note"), new MyAggregationStrategy())
				// .completionSize(3)
				// .log("Sending out ${body}")
				// .to("mock:result");
				//

				// from("file:/Users/nils/Desktop/camelTest?noop=true" )
				// .aggregate(new MyAggregationStrategy()).header("id")
				// .completionInterval(3000)
				// .to("xslt:file:src/main/resources/data/xsl/transformation.xsl");
			}
			
			
			
			
			
			
		});
		
		context.addRoutes(new RouteBuilder() {
			public void configure() {
   
				from("direct:x")
				.unmarshal(jaxb)
				//.unmarshal()
				//.xstream()
				.split(body(List.class))
				.convertBodyTo(Eintrag.class)
//				.filter()
//		        .xpath("/daten/eintrag[size>5.5]")
		        .process(new Processor() {
					public void process(Exchange exchange) throws Exception {
						System.out.println("found something!\n\n\n\n");
					}
				});
			}
		});
		
		
		
		
		
		
		context.start();
		Thread.sleep(35000);
	}
}
