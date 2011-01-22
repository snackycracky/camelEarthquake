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

@Path("/myservice/")
@Produces("application/xml")
public class ExampleResource {

	private static Map<Integer, Meldung> Meldungen = new HashMap<Integer, Meldung>();

	static {
		Meldungen.put(1, new Meldung(1, "foo"));
		Meldungen.put(2, new Meldung(2, "bar"));
		Meldungen.put(3, new Meldung(3, "baz"));
	}

	public ExampleResource() {
	}

	// @GET
	// @Path("/Meldungs")
	// @Override
	// public MeldungCollection getMeldungs() {
	// return new MeldungCollection(Meldungen.values());
	// }

	@GET
	@Path("/Meldung/{id}")
	public Meldung getMeldung(@PathParam("id") Integer id) {
		return Meldungen.get(id);
	}

	@GET
	@Path("/Meldungs/bad")
	public Response getBadRequest() {
		return Response.status(Status.BAD_REQUEST).build();
	}

}