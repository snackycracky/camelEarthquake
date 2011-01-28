package edu.fhb.softarch.medialib.model.adapter;

import javax.xml.bind.annotation.adapters.XmlAdapter;

import edu.fhb.softarch.medialib.model.Earthquake;

public class XLinkAdapterEarthquake extends
		XmlAdapter<EarthquakeXLink, Earthquake> {

	@Override
	public EarthquakeXLink marshal(Earthquake arg0) throws Exception {
		return new EarthquakeXLink(arg0);
	}

	@Override
	public Earthquake unmarshal(EarthquakeXLink arg0) throws Exception {
		return null;
	}
}
