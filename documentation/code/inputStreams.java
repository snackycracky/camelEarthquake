from("http://geofon.gfz-potsdam.de/db/eqinfo.php?fmt=rss&splitEntries=false")
  .setHeader("visited", constant(true))
  .to("xslt:data/xsl/transformation.xsl").to("direct:start")
  .delay(1000);

from("http://earthquake.usgs.gov/eqcenter/catalogs/eqs1day-M2.5.xml?splitEntries=false")
  .setHeader("visited", constant(true))
  .to("xslt:data/xsl/transformation2.xsl").to("direct:start")
  .delay(1000);