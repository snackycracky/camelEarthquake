import org.apache.camel.Exchange;
import org.apache.camel.processor.aggregate.AggregationStrategy;

public class XMLAggregationStrategy implements AggregationStrategy {

  public Exchange aggregate(Exchange oldExchange, Exchange newExchange) {
    if (oldExchange == null) {
      return newExchange;
    }
    String oldBody = oldExchange.getIn().getBody(String.class);
    String newBody = newExchange.getIn().getBody(String.class);
    String body = oldBody + newBody;

    body = body
        .replaceAll("<\\?xml version=\"1\\.0\" encoding=\"UTF-8\"\\?>",
            "")
        .replaceAll("</earthquakes>(.*)<earthquakes>", "")
        .replaceAll(
            "</earthquakes><earthquakes xmlns:geo=\"http://www\\.w3\\.org/2003/01/geo/wgs84_pos#\">",
            "").replaceAll("</earthquakes><earthquakes>", "");

    oldExchange.getIn().setBody(body);
    return oldExchange;
  }
}
