package edu.fhb.softarch.medialib;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import edu.fhb.softarch.medialib.dao.EarthpartDao;
import edu.fhb.softarch.medialib.dao.EarthquakeDao;
import edu.fhb.softarch.medialib.model.EarthPartCollection;
import edu.fhb.softarch.medialib.model.Earthpart;
import edu.fhb.softarch.medialib.model.Earthquake;

@Path("/myservice/")
@Produces("application/xml")
public class RestServiceImpl {

	public RestServiceImpl() {
	}

	@GET
	@Path("/Earthparts")
	public EarthPartCollection getAllEarthparts() {
		return EarthpartDao.all();
	}

	@GET
	@Path("/Earthquake/findById/{id}")
	public Earthquake getEarthquake(@PathParam("id") Integer id) {
		return EarthquakeDao.findById(id);
	}


}