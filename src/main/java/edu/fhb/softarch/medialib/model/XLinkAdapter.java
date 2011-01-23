package edu.fhb.softarch.medialib.model;

import javax.xml.bind.annotation.adapters.XmlAdapter;

public class XLinkAdapter extends XmlAdapter<EarthquakeXLink,Earthquake > {

	@Override
	public EarthquakeXLink marshal(Earthquake arg0) throws Exception {
		// TODO Auto-generated method stub
		return new EarthquakeXLink("http://theUrl:9000","quaki");
	}

	@Override
	public Earthquake unmarshal(EarthquakeXLink arg0) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}


}
