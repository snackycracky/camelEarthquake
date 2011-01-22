package edu.fhb.softarch.medialib;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;


public class EarthpartDao {

	public static Earthpart findById(Integer id) {
		if (id == 1) {
			return new Earthpart(1, "Erdbeben Alter!", new Earthquake("http://www.google.com","QUAKI"));
		} else {
			return new Earthpart(2, "Alles Stabil!");
		}
	}

	public static Collection<Earthpart> all() {
		
		List<Earthpart> l = new ArrayList<Earthpart>();
		l.add(new Earthpart(1, "Erdbeben Alter!", new Earthquake("http://www.google.com","QUAKI")));
		l.add(new Earthpart(2, "Erdbeben 1!", new Earthquake("http://12","12")));
		
		return new EarthPartCollection(l).getEarthparts();
	}
}
