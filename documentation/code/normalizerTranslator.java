from(
	"http://geofon.gfz-potsdam.de/db/eqinfo.php?fmt=rss&splitEntries=false")
	.log("retrieve")
	.to("direct:collectorChannel");

from(
	"http://earthquake.usgs.gov/eqcenter/catalogs/eqs1day-M2.5.xml?fmt=rss&splitEntries=false")
	.log("retrieve")
	.to("direct:collectorChannel");

from("direct:collectorChannel")
	.choice()
	.when().xpath("/rss/channel/item/pubDate")
		.to("xslt:data/xsl/transformation2.xsl")
		.setHeader("visited", constant(true))
		.log("true: has /rss/channel/item/pubDate")
		.to("direct:normalizedMsg")
	.otherwise()
		.to("xslt:data/xsl/transformation.xsl")
		.setHeader("visited", constant(true))
		.log("false: has not /rss/channel/item/pubDate")
		.to("direct:normalizedMsg");