from("direct:normalizedMsg")
  .aggregate(header("visited"), new XMLAggregationStrategy())
  .completionSize(2).delay(3000)
  .to("direct:filterBiggestEarthquakes")
  .to("direct:UnmarshallMergedSources");