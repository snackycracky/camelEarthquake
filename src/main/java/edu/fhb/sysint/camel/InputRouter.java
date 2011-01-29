package edu.fhb.sysint.camel;

import java.io.File;
import java.util.Map;

import org.apache.camel.CamelContext;
import org.apache.camel.Consumer;
import org.apache.camel.Endpoint;
import org.apache.camel.Exchange;
import org.apache.camel.ExchangePattern;
import org.apache.camel.PollingConsumer;
import org.apache.camel.Processor;
import org.apache.camel.Producer;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.builder.xml.Namespaces;
import org.apache.camel.converter.jaxb.JaxbDataFormat;
import org.apache.camel.spi.DataFormat;

import edu.fhb.sysint.camel.model.Earthquake;
import edu.fhb.sysint.camel.model.EarthquakeCollection;

public class InputRouter extends RouteBuilder {
	private static final String HTTP_WWW_W3_ORG_2003_01_GEO_WGS84_POS = "http://www.w3.org/2003/01/geo/wgs84_pos";

	@Override
	public void configure() throws Exception {

		
		
		

		final CommonUtils file = new CommonUtils();
		final DataFormat jaxb = new JaxbDataFormat(
				"edu.fhb.sysint.camel.model");

		from(//rss:
		"http://geofon.gfz-potsdam.de/db/eqinfo.php?fmt=rss&splitEntries=false")//.marshal().rss()
		.log("retrieve")
		.to("direct:collectorChannel");

		from(//rss:
		"http://earthquake.usgs.gov/eqcenter/catalogs/eqs1day-M2.5.xml?fmt=rss&splitEntries=false")//.marshal().rss()
		.log("retrieve")
		.to("direct:collectorChannel");
		
		from("direct:collectorChannel")
		.choice()
		.when().xpath("/rss/channel/item/pubDate")
			.to("xslt:data/xsl/transformation2.xsl")
			.setHeader("visited", constant(true))
			.log("true")
			.to("direct:normalizedMsg")
		.otherwise()
			.to("xslt:data/xsl/transformation.xsl")
			.setHeader("visited", constant(true))
			.log("false")
			.to("direct:normalizedMsg");
		
		from("direct:normalizedMsg")
				.aggregate(header("visited"), new XMLAggregationStrategy())
				.completionSize(2).delay(3000)
				.to("direct:filterBiggestEarthquakes")
				.to("direct:UnmarshallMergedSources");

		from("direct:UnmarshallMergedSources")//
				.unmarshal(jaxb)
				.process(new EnrichmentProcessor())
				.process(new RemoveXMLHeaderProcessor())
				.marshal(jaxb)
				// .to("file://"+GlobalConstants.IntermediateResult+"?append=false");
				.to("file:/Users/nils/Desktop/result.xml").delay(3000);

		from("direct:filterBiggestEarthquakes")
				.split(xpath("/earthquakes/earthquake[size>5.4]"))
				.setHeader("splitted", constant(true))
				.aggregate(header("splitted"), new SimpleAggregationStrategy())
				.completionInterval(2000)
				.process(new Processor() {
					public void process(Exchange exchange) throws Exception {
						String body = exchange.getIn().getBody(String.class);
						body = "<earthquakes>" + body + "</earthquakes>";
						exchange.getIn().setBody(body, String.class);
					}
				})
				.unmarshal(jaxb)
				.process(new Processor() {

					public void process(Exchange exchange) throws Exception {
						EarthquakeCollection ec = exchange.getIn().getBody(
								EarthquakeCollection.class);

						String emailBody = "<b>Notification - Heavy Earthquakes</b><p/>"
								+ "<ul>";

						for (Earthquake e : ec.getEntries()) {
							emailBody += "<li><a href=\""
									+ GlobalConstants.PROTOCOL_HOST
									+ GlobalConstants.REST_SERVICE_RELATIVE_PATH
									+ e.getId() + "\">" + e.getTitle()
									+ " - M " + e.getSize() + "</a>" + "</li>";
						}

						emailBody += "</ul>";
						System.out.println(emailBody);
						exchange.getIn().setBody(emailBody, String.class);
					}
				})
				.to("smtps://camelfhb@smtp.gmail.com?password=camelfhb31&to=camelfhb@googlemail.com")
				.delay(120000);
		// .to("direct:filterBiggestEarthquakes")
		// .to("file:/Users/nils/Desktop/true").delay(1000)
		// .to("smtps://camelfhb@smtp.gmail.com?password=camelfhb31&to=camelfhb@googlemail.com&contentType=text/html");

		Thread.sleep(5000);
	}
}
