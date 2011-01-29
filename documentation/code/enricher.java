from("direct:UnmarshallMergedSources")
  .unmarshal(jaxb)
  .process(new EnrichmentProcessor())

  
  
  