package edu.fhb.softarch.medialib;

import java.io.File;
import java.util.ArrayList;

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
import org.springframework.util.FileSystemUtils;

import edu.fhb.softarch.GlobalConstants;
import edu.fhb.softarch.medialib.dao.EarthpartDao;
import edu.fhb.softarch.medialib.dao.EarthquakeDao;
import edu.fhb.softarch.medialib.model.Earthpart;
import edu.fhb.softarch.medialib.model.Earthquake;
import edu.fhb.softarch.medialib.model.EarthquakeCollection;
import edu.fhb.softarch.medialib.model.EintragCollection;

public class Driver {
	


	public static void main(String args[]) throws Exception {
		CamelContext context = new DefaultCamelContext();

		final CommonUtils file = new CommonUtils();
		final DataFormat jaxb = new JaxbDataFormat(
				"edu.fhb.softarch.medialib.model");

//		context.addRoutes(new RouteBuilder() {
//
//			public void configure() {
//
//				Namespaces ns = new Namespaces("ns",
//						"http://schemas.microsoft.com/dynamics/2006/02/documents/Message");

//				
//				
//				
//				from(
//				"rss:http://geofon.gfz-potsdam.de/db/eqinfo.php?fmt=rss&splitEntries=false").marshal().rss()
//				// from("http://localhost/eqInfo.xml")
////				.setHeader("visited", constant(true))
////				.to("xslt:data/xsl/transformation.xsl")
//				.to("direct:start");
////				.delay(1000);
//
//				from(
//				"rss:http://earthquake.usgs.gov/eqcenter/catalogs/eqs1day-M2.5.xml?fmt=rss&splitEntries=false").marshal().rss()
//				// from("http://localhost/eqs1dat.xml")
////				.setHeader("visited", constant(true))
////				.to("xslt:data/xsl/transformation2.xsl")
//				.to("direct:start");
////				.delay(1000);
//				
//				from("direct:start")
////				.process(new DebugProcessor())
//				.choice()
//				.when().xpath("/rss/channel/item/pubDate")
//					.to("xslt:file:src/main/resources/data/xsl/transformation2.xsl")
//					.setHeader("visited", constant(true))
//					.log("true")
//					.to("direct:normalizedMsg")
//				.otherwise()
//					.to("xslt:file:src/main/resources/data/xsl/transformation.xsl")
//					.setHeader("visited", constant(true))
//					.log("false")
//					.to("direct:normalizedMsg");
//				
//				from("direct:normalizedMsg")
//					.aggregate(header("visited"), new XMLAggregationStrategy())
//					.completionTimeout(500)
//					.process(new DebugProcessor());
//				
//			}});
				
				
				
				
				
				
				
				
				
				
//				
//				from("file:/Users/nils/Desktop/driverInput?noop=true")
//						.split(xpath("/earthquakes/earthquake[size>5.4]"))
//						.setHeader("splitted", constant(true))
////						.to("direct:splitted");
////
////				from("direct:splitted")
//						.aggregate(header("splitted"),
//								new SimpleAggregationStrategy())
//						.completionInterval(2000)
//						
//						.process(new Processor() {
//
//							public void process(Exchange exchange)
//									throws Exception {
//								String body = exchange.getIn().getBody(
//										String.class);
//
//								body = "<earthquakes>" + body
//										+ "</earthquakes>";
//
//								exchange.getIn().setBody(body, String.class);
//
//							}
//						})
//
////						// .unmarshal(jaxb)
////						.to("file:/Users/nils/Desktop/true")
////						// .to("direct:x");
//						.unmarshal(jaxb).process(new Processor() {
//
//							public void process(Exchange exchange)
//									throws Exception {
//								EarthquakeCollection ec = exchange.getIn()
//										.getBody(EarthquakeCollection.class);
//
//								String emailBody = "<b>Notification - Heavy Earthquakes</b><p/>"
//										+ "<ul>";
//
//								for (Earthquake e : ec.getEntries()) {
//									emailBody += "<li><a href=\""
//											+ GlobalConstants.PROTOCOL_HOST
//											+ GlobalConstants.REST_SERVICE_RELATIVE_PATH
//											+ e.getId() + "\">" + e.getTitle()
//											+ " - M " + e.getSize() + "</a>"
//											+ "</li>";
//								}
//
//								emailBody += "</ul>";
//								syso(emailBody);
//								exchange.getIn().setBody(emailBody,
//										String.class);
//							}
//						})
////						.to("direct:filterBiggestEarthquakes")
////						.to("file:/Users/nils/Desktop/true").delay(1000)
//						.to("smtps://camelfhb@smtp.gmail.com?password=camelfhb31&to=camelfhb@googlemail.com&contentType=text/html");
//						;
//				try {
//					Thread.sleep(1000);
//				} catch (InterruptedException e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				}
//			}
//		});
//
//		//
//		// context.addRoutes(new RouteBuilder() {
//		// public void configure() {
//		// from("direct:x")
//		// }
//		// });
//		// context.addRoutes(new RouteBuilder() {
//		// public void configure() {
//		//
//		// from("file:/Users/nils/Desktop/in1?noop=true")
//		// .setHeader("visited", constant(true))
//		// .to("xslt:file:src/main/resources/data/xsl/transformation2.xsl")
//		// .to("direct:start");
//		// }
//		// });
//
////		context.addRoutes(new RouteBuilder() {
////			public void configure() {
////				from("direct:start")
////						.aggregate(header("visited"),
////								new MyAggregationStrategy())
////						// .completionSize(3)
////						.completionTimeout(1000).process(new Processor() {
////							public void process(Exchange exchange)
////									throws Exception {
////								String body = exchange.getIn().getBody(
////										String.class);
////								body = body
////										.replaceFirst(
////												"<daten>",
////												"<daten xmlns=\""
////														+ HTTP_WWW_W3_ORG_2003_01_GEO_WGS84_POS
////														+ "\">");
////								System.out.println(body);
////								String resultFilepath = GlobalConstants.IntermediateResult;
////								File f = new File(resultFilepath);
////								if (f.delete()) {
////									file.writeToFile(resultFilepath, "\n"
////											+ body, true);
////								}
////								exchange.getIn().setBody(body, String.class);
////							}
////						}).to("direct:UnmarshallMergedSources")
////						// .to("direct:rest")
////						.to("direct:filterBiggestEarthquakes");
////			}
////
////		});
//
//		// context.addRoutes(new RouteBuilder() {
//		// public void configure() {
//		// // from("direct:UnmarshallMergedSources")
//		// from("direct:UnmarshallMergedSources")
//		// .unmarshal(jaxb).process(new Processor() {
//		// public void process(Exchange exchange) throws Exception {
//		// EarthquakeCollection ec = exchange.getIn().getBody(
//		// EarthquakeCollection.class);
//		// System.out.println("found something!\n\n\n\n" + ec);
//		//
//		// ArrayList<Earthquake> listClone = new ArrayList<Earthquake>();
//		// int i = 1;
//		// for (Earthquake e : ec.getEntries()) {
//		// String additionalInfo = CommonUtils
//		// .findAdditionalInfo(e.getLocation());
//		//
//		// e.setCountry(additionalInfo.contains("not found") ? "nothing"
//		// : additionalInfo);
//		// e.setId(i++);
//		//
//		// listClone.add(e);
//		// if (i > 5) {
//		// break;// TODO timeout!
//		// }
//		// }
//		// ec.setEntries(listClone);
//		// exchange.getIn()
//		// .setBody(ec, EarthquakeCollection.class);
//		// }
//		// }).process(new Processor() {
//		// public void process(Exchange exchange) throws Exception {
//		//
//		// String body = exchange.getIn().getBody(String.class);
//		// body = body.replaceAll("<\\?xml(.*)>", "");
//		//
//		// file.writeToFile(GlobalConstants.IntermediateResult_ENRICHMENT,
//		// body, false);
//		// }
//		// }).marshal(jaxb)
//		//
//		// // .to("file://"+GlobalConstants.IntermediateResult+"?append=false");
//		// .to("file:/Users/nils/Desktop/result.xml").delay(3000);
//		// }
//		// });
//		//
//		context.addRoutes(new RouteBuilder() {
//			public void configure() {
//				from("direct:filterBiggestEarthquakes")
////				.filter()
////						.xpath("/daten/eintrag[size>5.5]")
//						.to("direct:NotifyByEmail");
//			}
//		});
//		context.addRoutes(new RouteBuilder() {
//			public void configure() {
//				from("direct:NotifyByEmail")
//						.to("smtp://mail.berlin.de?username=post@berlin.de&password=&to=post@berlin.de&contentType=text/html")
//						.process(new Processor() {
//							public void process(Exchange exchange)
//									throws Exception {
//								syso("x");
////								 EintragCollection ec =
////								 exchange.getIn().getBody(
////								 EintragCollection.class);
//							}
//						});
//			}
//		});
//
		context.start();
	Thread.sleep(10000);
	}

}
