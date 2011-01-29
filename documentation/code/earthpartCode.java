import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import edu.fhb.sysint.camel.model.Earthquake;
import edu.fhb.sysint.camel.model.adapter.XLinkAdapterEarthquake;

@XmlRootElement(name = "earthpart")
@XmlAccessorType(XmlAccessType.FIELD)
public class Earthpart {

	@XmlAttribute(name = "name")
	private String name = "";

	@XmlElementWrapper(name = "erathquakes")
	@XmlElement(name = "erathquake")
	@XmlJavaTypeAdapter(XLinkAdapterEarthquake.class)
	private List<Earthquake> earthquakes;

	...