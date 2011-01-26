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



/**
 * Zeigt einen Artikel an
 */
public class Fileops {
	
	public Fileops() {		
	
	}	
	
	public boolean writeToFile(String filename, String daten, boolean append) {
	
		String zeile; 
		File res; 
		FileWriter fw; 
		BufferedWriter bw; 
		
		try { 
			  
			  res = new File(filename);
			  fw = new FileWriter(res, append); 
			  bw = new BufferedWriter(fw); 
										  
			  bw.write(daten); 
			  bw.close();
			  
		} 
		catch (ArrayIndexOutOfBoundsException aioobe) { 
		  System.out.println("Aufruf mit: java SchreibeDatei name"); 
		  System.out.println("erzeugt eine Datei name.html"); 
		} 
		catch (IOException ioe) { 
		  System.out.println("Habe gefangen: "+ioe); 
		}
		return true;

	}
	
	public String readFile(String filename) {

		String zeile; 
		File res; 
		FileWriter fw; 
		BufferedWriter bw; 
	
		/*Dateien mit Informationen anreichern*/
		//Dazu die Datei komplett einlesen ...
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