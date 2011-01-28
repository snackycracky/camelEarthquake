<?xml version='1.0'?>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
<xsl:output method="xml"/>
<!-- here is the template that does the replacement -->
<xsl:template name="replaceCharsInString">
  <xsl:param name="stringIn"/>
  <xsl:param name="charsIn"/>
  <xsl:param name="charsOut"/>
  <xsl:choose>
    <xsl:when test="contains($stringIn,$charsIn)">
      <xsl:value-of select="concat(substring-before($stringIn,$charsIn),$charsOut)"/>
      <xsl:call-template name="replaceCharsInString">
        <xsl:with-param name="stringIn" select="substring-after($stringIn,$charsIn)"/>
        <xsl:with-param name="charsIn" select="$charsIn"/>
        <xsl:with-param name="charsOut" select="$charsOut"/>
      </xsl:call-template>
    </xsl:when>
    <xsl:otherwise>
      <xsl:value-of select="$stringIn"/>
    </xsl:otherwise>
  </xsl:choose>
</xsl:template>
 
<xsl:template match="/">
	<earthquakes>
		<xsl:for-each select="rss/channel/item">
			<xsl:variable name = "A" ><xsl:value-of select="description" /></xsl:variable> 
			<xsl:variable name = "B" ><xsl:value-of select="title" /></xsl:variable> 
			<xsl:variable name = "C" ><xsl:value-of select="substring-after($A,'   ') " /> </xsl:variable> 
			<xsl:variable name = "D" ><xsl:value-of select="substring-after($C,'   ') " /> </xsl:variable> 
			
			<earthquake>
			
			 <xsl:variable name="withoutM">
			    <xsl:call-template name="replaceCharsInString">
			      <xsl:with-param name="stringIn" select="substring-before($B,',')"/>
			      <xsl:with-param name="charsIn" select="'M '"/>
			      <xsl:with-param name="charsOut" select="''"/>
			    </xsl:call-template>
			  </xsl:variable>
			
				<size> <xsl:value-of select="$withoutM " /> </size> 
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
			</earthquake>
		</xsl:for-each>
	</earthquakes>

</xsl:template>

</xsl:stylesheet>
