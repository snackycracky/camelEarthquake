import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import edu.fhb.sysint.camel.dao.EarthpartDao;
import edu.fhb.sysint.camel.dao.EarthquakeDao;
import edu.fhb.sysint.camel.model.EarthPartCollection;
import edu.fhb.sysint.camel.model.Earthpart;
import edu.fhb.sysint.camel.model.Earthquake;

@Path("/earthquakeService/")
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