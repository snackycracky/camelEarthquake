<?xml version='1.0'?>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
<xsl:output method="xml"/>

<xsl:template match="/">
	<daten>
		<xsl:for-each select="rss/channel/item">
			<xsl:variable name = "A" ><xsl:value-of select="description" /></xsl:variable> 
			<xsl:variable name = "B" ><xsl:value-of select="title" /></xsl:variable> 
			<xsl:variable name = "C" ><xsl:value-of select="substring-after($A,'   ') " /> </xsl:variable> 
			<xsl:variable name = "D" ><xsl:value-of select="substring-after($C,'   ') " /> </xsl:variable> 
			
			<eintrag>
				<size> <xsl:value-of select="substring-before($B,',') " /> </size> 
				<title> <xsl:value-of select="substring-after($B,',') " /> </title> 
				<date> <xsl:value-of select="substring-before($A,'   ') " /> </date>
				<location> 					
					<xsl:choose> 
						<xsl:when test="contains($C, 'N') = 'true'"> <xsl:value-of select="substring-before($C,' N')"/><xsl:text>,</xsl:text></xsl:when>
						<xsl:when test="contains($C, 'S') = 'true'"> <xsl:text>-</xsl:text><xsl:value-of select="substring-before($C,' S')"/><xsl:text>,</xsl:text></xsl:when>
						<xsl:otherwise>  XX  </xsl:otherwise>						
					</xsl:choose> 	
					<xsl:choose> 
						<xsl:when test="contains($D, 'E') = 'true'"> <xsl:value-of select="substring-before($D,'E')" /></xsl:when>
						<xsl:when test="contains($D, 'W') = 'true'"> <xsl:text>-</xsl:text><xsl:value-of select="substring-before($D,'W')" /></xsl:when>
						<xsl:otherwise>  XX  </xsl:otherwise>						
					</xsl:choose> 						
				</location>
				<weather> </weather>
				<link> <xsl:value-of select="link" /> </link>
			</eintrag>
			
		</xsl:for-each>
		
	</daten>

</xsl:template>

</xsl:stylesheet>
