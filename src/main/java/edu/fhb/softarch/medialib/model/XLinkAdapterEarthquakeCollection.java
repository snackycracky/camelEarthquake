package edu.fhb.softarch.medialib.model;

import javax.xml.bind.annotation.adapters.XmlAdapter;

public class XLinkAdapterEarthquakeCollection extends XmlAdapter<EarthquakeCollectionXLink,EarthquakeCollection > {

	@Override
	public EarthquakeCollectionXLink marshal(EarthquakeCollection arg0) throws Exception {
		// TODO Auto-generated method stub
		return new EarthquakeCollectionXLink(arg0);
	}

	@Override
	public EarthquakeCollection unmarshal(EarthquakeCollectionXLink arg0) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}
}
