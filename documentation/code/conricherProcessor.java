final class EnrichmentProcessor implements Processor {
  public void process(Exchange exchange) throws Exception {
    EarthquakeCollection ec = exchange.getIn().getBody(
        EarthquakeCollection.class);
    ArrayList<Earthquake> listClone = new ArrayList<Earthquake>();
    int i = 1;
    for (Earthquake e : ec.getEntries()) {
      String additionalInfo = CommonUtils
          .findAdditionalInfo(e.getLocation());

      e.setCountry(additionalInfo.contains("not found") ? "undefined"
          : additionalInfo);
      
      Weather findWeatherInfo = CommonUtils.findWeatherInfo(e.getLocation());

      e.setWeather(findWeatherInfo);
      
      e.setId(i++);

      listClone.add(e);
    }
    ec.setEntries(listClone);
    System.out.println("setting earthquakes now");
    exchange.getIn()
        .setBody(ec, EarthquakeCollection.class);
  }
}