package edu.fhb.softarch.medialib.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import edu.fhb.softarch.medialib.model.EarthPartCollection;
import edu.fhb.softarch.medialib.model.Earthpart;
import edu.fhb.softarch.medialib.model.Earthquake;
import edu.fhb.softarch.medialib.model.EarthquakeCollection;

public class EarthpartDao {

	public static EarthPartCollection all() {

		EarthquakeCollection quakeCollection = JaxBUtil.unmarshall();
		EarthPartCollection partCollection = new EarthPartCollection();
		List<Earthpart> partList = new ArrayList<Earthpart>();
		Set<String> distinctCountrys = new HashSet<String>();
		
		
		
		for (Earthquake iterable_element : quakeCollection.getEntries()) {
			distinctCountrys.add(iterable_element.getCounrty());
		}
		
		
		
		
		List<Earthquake> tmpList = new ArrayList<Earthquake>();
		for(String country : distinctCountrys){
			
			for (Earthquake iterable_element : quakeCollection.getEntries()) {
				if(iterable_element.getCounrty().equals(country)){
					tmpList.add(iterable_element);
				}
			}
			partList.add(new Earthpart(country,new EarthquakeCollection(tmpList)));
			

		}
		partCollection.setEarthparts(partList);
		System.out.println("\n\n\n collection:"+partCollection);
		return partCollection;
	}
}
