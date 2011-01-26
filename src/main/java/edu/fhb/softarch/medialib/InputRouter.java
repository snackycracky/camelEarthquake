package edu.fhb.softarch.medialib;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.builder.RouteBuilder;

public class InputRouter extends RouteBuilder {

	@Override
	public void configure() throws Exception {

		// our route is aggregating from the direct queue and sending the
		// response to the mock
		from("direct:start").from("direct:start")
		// aggregated by header id and use our own strategy how to aggregate
				.aggregate(new MyAggregationStrategy()).header("id")
				// wait for 0.5 seconds to aggregate
				// .batchTimeout(500L)
				.to("mock:result");

		from(
				"http://geofon.gfz-potsdam.de/db/eqinfo.php?fmt=rss&splitEntries=false")
				.to("xslt:data/xsl/transformation.xsl").process(
						new Processor() {
							public void process(Exchange exchange)
									throws Exception {
								System.out.println("first");
								String body = exchange.getIn().getBody(
										String.class);
								body = body.replaceFirst("</daten>", " ");

								// Daten in eine Textdatei schreiben
								String zeile;
								File res;
								FileWriter fw;
								BufferedWriter bw;
								try {

									res = new File(
											"/Users/nils/Desktop/resultPotsdam.xml");
									fw = new FileWriter(res);
									bw = new BufferedWriter(fw);

									bw.write(body);
									bw.close();

								} catch (ArrayIndexOutOfBoundsException aioobe) {
									System.out
											.println("Aufruf mit: java SchreibeDatei name");
									System.out
											.println("erzeugt eine Datei name.html");
								} catch (IOException ioe) {
									System.out.println("Habe gefangen: " + ioe);
								}

							}
						});

		from(
				"http://earthquake.usgs.gov/eqcenter/catalogs/eqs1day-M2.5.xml?splitEntries=false")
				.to("xslt:data/xsl/transformation2.xsl").process(
						new Processor() {
							public void process(Exchange exchange)
									throws Exception {
								System.out.println("second");
								String body = exchange.getIn().getBody(
										String.class);

								body = body.substring(
										body.indexOf("<eintrag>"),
										body.indexOf("</daten>"));
								body = body + "</daten>";

								// Daten in eine Textdatei schreiben
								String zeile;
								File res;
								FileWriter fw;
								BufferedWriter bw;

								try {

									res = new File(
											"/Users/nils/Desktop/resultUsgs.xml");
									fw = new FileWriter(res, true);
									bw = new BufferedWriter(fw);

									bw.write("\n\n" + body);
									bw.close();

								} catch (ArrayIndexOutOfBoundsException aioobe) {
									System.out
											.println("Aufruf mit: java SchreibeDatei name");
									System.out
											.println("erzeugt eine Datei name.html");
								} catch (IOException ioe) {
									System.out.println("Habe gefangen: " + ioe);
								}

							}
						});
		Thread.sleep(5000);
	}

}
