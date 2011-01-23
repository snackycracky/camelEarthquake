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

//		 from("rss:" +
//		 "http://geofon.gfz-potsdam.de/db/eqinfo.php?fmt=rss&splitEntries=false"
//		).marshal().rss()
		from("http://localhost/data/potsdam.xml")
				// .marshal().rss()
				.to("xslt:data/xsl/transformation.xsl")
				.process(new Processor() {
					public void process(Exchange exchange) throws Exception {

						String body = exchange.getIn().getBody(String.class);
						body = body.replaceFirst("</daten>", " ");

						// Daten in eine Textdatei schreiben
						String zeile;
						File res;
						FileWriter fw;
						BufferedWriter bw;
						try {

							res = new File("/Users/nils/Desktop/result1.xml");
							fw = new FileWriter(res);
							bw = new BufferedWriter(fw);

							bw.write(body);
							bw.close();

						} catch (ArrayIndexOutOfBoundsException aioobe) {
							System.out
									.println("Aufruf mit: java SchreibeDatei name");
							System.out.println("erzeugt eine Datei name.html");
						} catch (IOException ioe) {
							System.out.println("Habe gefangen: " + ioe);
						}
						Thread.sleep(5000);
					}
				});
	}

}
