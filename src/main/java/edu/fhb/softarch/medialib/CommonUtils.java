// Paket
package edu.fhb.softarch.medialib;

// Includes
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.Locale;

/**
 * Zeigt einen Artikel an
 */
public class CommonUtils {

	public CommonUtils() {

	}

	public static String findAdditionalInfo(String location) {
		URL url;

		String result = null;
		try {
			url = new URL(
					"http://api.wunderground.com/auto/wui/geo/GeoLookupXML/index.xml?query="
							+ location);

			URLConnection conn = url.openConnection();

			// Get the response
			BufferedReader rd = new BufferedReader(new InputStreamReader(
					conn.getInputStream()));
			StringBuffer sb = new StringBuffer();

			String line;

			while ((line = rd.readLine()) != null) {
				sb.append(line);
			}

			rd.close();
			result = sb.toString();

			if (result.indexOf("<country>") != -1) {
				result = result.substring(result.indexOf("<country>"),
						result.indexOf("</country>"));
				result = result.replace("<country>", "");
				// entry=str.replace("<addInfo/>","<addInfo>"+result+"</addInfo>");
//				System.out.println("" + result + "");
				
				Locale[] locales = Locale.getAvailableLocales();
			    for (Locale locale : locales) {
			    	if(result.toUpperCase().trim().equals(locale.getCountry().toUpperCase().trim())){
			    		result = locale.getDisplayCountry();
			    	}
			    }
			    System.out.println("" + result + "");
				
			}
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}

	public boolean writeToFile(String filename, String daten, boolean append) {

		File res;
		FileWriter fw;
		BufferedWriter bw;

		try {

			res = new File(filename);
			fw = new FileWriter(res, append);
			bw = new BufferedWriter(fw);

			bw.write(daten);
			bw.close();

		} catch (ArrayIndexOutOfBoundsException aioobe) {
			System.out.println("Aufruf mit: java SchreibeDatei name");
			System.out.println("erzeugt eine Datei name.html");
		} catch (IOException ioe) {
			System.out.println("Habe gefangen: " + ioe);
		}
		return true;

	}

	public String readFile(String filename) {

		/* Dateien mit Informationen anreichern */
		// Dazu die Datei komplett einlesen ...
		String text = "";
		try {
			BufferedReader input = new BufferedReader(new FileReader(filename));
			String line;

			while ((line = input.readLine()) != null) {
				text = text + '\n' + line;
			}
			if (text.endsWith("\n"))
				text = text + "\n";

			input.close();

		} catch (FileNotFoundException ex) {
			System.out.println("FILE: Fehler");
			ex.printStackTrace();
		} catch (IOException ex) {
			ex.printStackTrace();
			System.out.println("FILE: Fehler");
		}
		return text;
	}
}