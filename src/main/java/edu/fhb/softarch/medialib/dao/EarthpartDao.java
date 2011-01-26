package edu.fhb.softarch.medialib.dao;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import edu.fhb.softarch.medialib.model.EarthPartCollection;
import edu.fhb.softarch.medialib.model.Earthpart;
import edu.fhb.softarch.medialib.model.Earthquake;

public class EarthpartDao {

	public static Earthpart findById(Integer id) {
		if (id == 1) {
			return new Earthpart(1, "Erdbeben Alter!", new Earthquake(1.1f, 1));
		} else {
			return new Earthpart(2, "Alles Stabil!");
		}
	}

	public static Collection<Earthpart> all() {

		List<Earthpart> l = new ArrayList<Earthpart>();
		l.add(new Earthpart(1, "Erdbeben Alter!", new Earthquake(2.1f, 1)));
		l.add(new Earthpart(2, "Erdbeben 1!", new Earthquake(3.1f, 1)));

		return new EarthPartCollection(l).getEarthparts();
	}
}
