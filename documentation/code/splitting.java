from("direct:filterBiggestEarthquakes")
  .split(xpath("/earthquakes/earthquake[size>5.4]"))
  .setHeader("splitted", constant(true))
  .aggregate(header("splitted"), new SimpleAggregationStrategy())
  .completionInterval(2000)
  .process(new Processor() {
    public void process(Exchange exchange) throws Exception {
      String body = exchange.getIn().getBody(String.class);
      body = "<earthquakes>" + body + "</earthquakes>";
      exchange.getIn().setBody(body, String.class);
    }
  })
  .unmarshal(jaxb)
  .process(new EmailProcessor())
  .to("smtps://camelfhb@smtp.gmail.com?password=camelfhb31&to=camelfhb@googlemail.com")
  .delay(120000);