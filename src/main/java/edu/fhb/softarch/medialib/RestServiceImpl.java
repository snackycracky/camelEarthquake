package edu.fhb.softarch.medialib;

import javax.ws.rs.GET;
import javax.ws.rs.Path;

//@Path("/example")
//public class ExampleResource {
//
//    @GET
//    public void ping() {
//        //strangely, this method is not called, only serves to configure the endpoint
//    }
//    
//    
//}  

import java.util.HashMap;
import java.util.Map;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import edu.fhb.softarch.medialib.dao.EarthpartDao;
import edu.fhb.softarch.medialib.dao.EarthquakeDao;
import edu.fhb.softarch.medialib.model.EarthPartCollection;
import edu.fhb.softarch.medialib.model.Earthpart;
import edu.fhb.softarch.medialib.model.Earthquake;
import edu.fhb.softarch.medialib.model.EarthquakeXLink;

@Path("/myservice/")
@Produces("application/xml")
public class RestServiceImpl {

	public RestServiceImpl() {
	}

	@GET
	@Path("/Earthparts")
	public EarthPartCollection getAllEarthparts() {
		return new EarthPartCollection(EarthpartDao.all());
	}

	@GET
	@Path("/Earthpart/findById/{id}")
	public Earthpart getEarthpart(@PathParam("id") Integer id) {
		return EarthpartDao.findById(id);
	}

	@GET
	@Path("/Earthquake/findById/{id}")
	public Earthquake getEarthquake(@PathParam("id") Integer id) {
		return EarthquakeDao.findById(id);
	}

	// @GET
	// @Path("/Meldungs/bad")
	// public Response getBadRequest() {
	// return Response.status(Status.BAD_REQUEST).build();
	// }

}