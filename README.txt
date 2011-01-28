Verwendete patterns:

filter
enricher
splitter
aggregator
normalizer


schritte zum installieren:

1. download this file and unpack to ~
http://fusesource.com/product_download/fuse-esb-apache-servicemix/4-3-0-fuse-03-00/unix

2. replace ~/downloadedFuse/etc/org.apache.karaf.features.cfg key value pair "featuresBoot=..." with

featuresBoot=config,activemq-broker,camel,camel-http,camel-jaxb,jbi-cluster,war,servicemix-cxf-bc,servicemix-file,servicemix-ftp,servicemix-http,servicemix-jms,servicemix-mail,servicemix-bean,servicemix-camel,servicemix-cxf-se,servicemix-drools,servicemix-eip,servicemix-osworkflow,servicemix-quartz,servicemix-scripting,servicemix-validation,servicemix-saxon,servicemix-wsn2005,camel,camel-spring-osgi,cxf,camel-cxf,camel-jetty,web,cxf-jaxrs,camel-rss,activemq-camel,rome-osgi,camel-mail
  
3. in the project root do mvn install

4.start fuse with downloadedFuse/bin/servicemix

5. on the karaf shell type: "osgi:install -s mvn:edu.fhb.softarch.medialib/servicemixTest"

6. browse to http://localhost:9000/myservice/Earthparts




