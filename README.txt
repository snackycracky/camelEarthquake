The System is deployed via the Apache Servicemix OSGI Runtime Container. “Apache
ServiceMix is an open source ESB (Enterprise Service Bus) that combines the functionality
of a Service Oriented Architecture (SOA) and an Event Driven Architecture (EDA) to create
an agile, enterprise ESB.[sm] ”
The Camel Version used is “2.4.0-fuse-02-00” from the repository “http://repo.fusesource.
com/maven2/”.

1. download fuse 4-3-0 file and unpack to /downloadedFuse http://fusesource.com/
product_download/fuse-esb-apache-servicemix/4-3-0-fuse-03-00/unix

2. replace /downloadedFuse/etc/org.apache.karaf.features.cfg key value pair "features-
Boot=..." with
featuresBoot=config,activemq-broker,camel,camel-http,camel-jaxb,jbi-cluster,war,
servicemix-cxf-bc,servicemix-file,servicemix-ftp,servicemix-http,servicemix-jms,
servicemix-mail,servicemix-bean,servicemix-camel,servicemix-cxf-se,servicemix-drools,
servicemix-eip,servicemix-osworkflow,servicemix-quartz,servicemix-scripting,
servicemix-validation,servicemix-saxon,servicemix-wsn2005,camel,camel-spring-osgi,
cxf,camel-cxf,camel-jetty,web,cxf-jaxrs,camel-rss,activemq-camel,rome-osgi,camel-mail

3. please modify the Constants in GlobalConstants.java for path settings.

4. in the project root do “mvn install”

5. start fuse with /downloadedFuse/bin/servicemix

6. on the karaf shell type: “install -s mvn:edu.fhb.sysint.camel/ApacheCamelEarthquakeService”

7. browse to http://localhost:9000/earthquakeService/Earthparts