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
//	private static final String HTTP_WWW_W3_ORG_2003_01_GEO_WGS84_POS = "http://www.w3.org/2003/01/geo/wgs84_pos";
//
//	public static void main(String args[]) throws Exception {
//		CamelContext context = new DefaultCamelContext();
//
//		final CommonUtils file = new CommonUtils();
//		final DataFormat jaxb = new JaxbDataFormat(
//				"edu.fhb.softarch.medialib.model");
//
//		context.addRoutes(new RouteBuilder() {
//			public void configure() {
//				from("file:/Users/nils/Desktop/in?noop=true")
//						.setHeader("visited", constant(true))
//						.to("xslt:file:src/main/resources/data/xsl/transformation.xsl")
//						.to("direct:start");
//			}
//		});
//		context.addRoutes(new RouteBuilder() {
//			public void configure() {
//
//				from("file:/Users/nils/Desktop/in1?noop=true")
//						.setHeader("visited", constant(true))
//						.to("xslt:file:src/main/resources/data/xsl/transformation2.xsl")
//						.to("direct:start");
//			}
//		});
//
//		context.addRoutes(new RouteBuilder() {
//			public void configure() {
//				from("direct:start")
//						.aggregate(header("visited"),
//								new MyAggregationStrategy())
//						// .completionSize(3)
//						.completionTimeout(1000).process(new Processor() {
//							public void process(Exchange exchange)
//									throws Exception {
//								String body = exchange.getIn().getBody(
//										String.class);
//								body=body.replaceFirst("<daten>", "<daten xmlns=\""+HTTP_WWW_W3_ORG_2003_01_GEO_WGS84_POS+"\">");
//								System.out.println(body);
//								String resultFilepath = GlobalConstants.IntermediateResult;
//								File f = new File(resultFilepath);
//								if (f.delete()) {
//									file.writeToFile(resultFilepath, "\n"
//											+ body, true);
//								}
//								exchange.getIn().setBody(body,String.class);
//							}
//						}).to("direct:UnmarshallMergedSources")
//						// .to("direct:rest")
//						.to("direct:filterBiggestEarthquakes");
//			}
//
//		});
//
//		context.addRoutes(new RouteBuilder() {
//			public void configure() {
//				Namespaces ns = new Namespaces("", HTTP_WWW_W3_ORG_2003_01_GEO_WGS84_POS);
//				// from("direct:UnmarshallMergedSources")
//				from("direct:UnmarshallMergedSources")
//				.filter(ns.xpath("/daten/eintrag[size>5.5]"))
//				.unmarshal(jaxb)
//						.process(new Processor() {
//							public void process(Exchange exchange)
//									throws Exception {
//								EarthquakeCollection ec = exchange.getIn()
//										.getBody(EarthquakeCollection.class);
//								System.out.println("found something!\n\n\n\n"
//										+ ec);
//
//								ArrayList<Earthquake> listClone = new ArrayList<Earthquake>();
//								int i = 1;
//								for (Earthquake e : ec.getEntries()) {
//									String additionalInfo = CommonUtils
//											.findAdditionalInfo(e.getLocation());
//
//									e.setCounrty(additionalInfo
//											.contains("not found") ? "nothing"
//											: additionalInfo);
//
//									listClone.add(e);
//									if (i++ > 20) {
//										break;// TODO timeout!
//									}
//								}
//								ec.setEntries(listClone);
//								exchange.getIn().setBody(ec,
//										EarthquakeCollection.class);
//							}
//						}).marshal(jaxb)
//						// .to("file://"+GlobalConstants.IntermediateResult+"?append=false");
//						.to("file:/Users/nils/Desktop/result.xml").delay(50000);
//			}
//		});
//
//		context.addRoutes(new RouteBuilder() {
//			public void configure() {
//				from("direct:filterBiggestEarthquakes").filter()
//						.xpath("/daten/eintrag[size>5.5]")
//						.to("direct:NotifyByEmail");
//			}
//		});
//		context.addRoutes(new RouteBuilder() {
//			public void configure() {
//				from("direct:NotifyByEmail")
//						.to("smtps://camelfhb@smtp.gmail.com?password=camelfhb31&to=camelfhb@googlemail.com")
//						.process(new Processor() {
//							public void process(Exchange exchange)
//									throws Exception {
//								// EintragCollection ec =
//								// exchange.getIn().getBody(
//								// EintragCollection.class);
//							}
//						});
//			}
//		});
//
//		context.start();
//		Thread.sleep(35000);
//	}
//
//	protected static void syso(Object string) {
//		System.out.println(string);
//
//	}
}
