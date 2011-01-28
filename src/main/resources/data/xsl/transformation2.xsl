<?xml version='1.0'?>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform" xmlns:geo="http://www.w3.org/2003/01/geo/wgs84_pos#">
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
			<xsl:variable name = "E" ><xsl:value-of select="description" /></xsl:variable> 
			<xsl:variable name = "D" ><xsl:value-of select="geo:long" /></xsl:variable> 
			<xsl:variable name = "C" ><xsl:value-of select="geo:lat" /></xsl:variable> 
			<xsl:variable name = "A" ><xsl:value-of select="contains($C, '-')" /></xsl:variable> 
			<xsl:variable name = "B" ><xsl:value-of select="title" /></xsl:variable> 
			<xsl:variable name = "Z" ><xsl:value-of select="substring-after($E,',')" /></xsl:variable> 
			<xsl:variable name = "Y" ><xsl:value-of select="substring-before($E,',')" /></xsl:variable> 
			<xsl:variable name = "X" ><xsl:value-of select="substring($Z,0,4)" /></xsl:variable> 
			
			
			 <xsl:variable name="withoutM">
			    <xsl:call-template name="replaceCharsInString">
			      <xsl:with-param name="stringIn" select="substring-before($B,',')"/>
			      <xsl:with-param name="charsIn" select="'M '"/>
			      <xsl:with-param name="charsOut" select="''"/>
			    </xsl:call-template>
			  </xsl:variable>
			
			<earthquake>
				<size> <xsl:value-of select="$withoutM " /> </size>  
				<title> <xsl:value-of select="substring-after($B,',') " /> </title> 
				<date>  
					<xsl:value-of select="substring($Z,1,5)"/>
					<xsl:choose>
						<xsl:when test="contains($Y, 'January') = 'true'"><xsl:text>-01-</xsl:text> </xsl:when>
						<xsl:when test="contains($Y, 'February') = 'true'"><xsl:text>-02-</xsl:text></xsl:when>
						<xsl:when test="contains($Y, 'March') = 'true'"><xsl:text>-03-</xsl:text></xsl:when>
						<xsl:when test="contains($Y, 'April') = 'true'"><xsl:text>-04-</xsl:text></xsl:when>
						<xsl:when test="contains($Y, 'May') = 'true'"><xsl:text>-05-</xsl:text></xsl:when>
						<xsl:when test="contains($Y, 'June') = 'true'"><xsl:text>-06-</xsl:text></xsl:when>
						<xsl:when test="contains($Y, 'July') = 'true'"><xsl:text>-07-</xsl:text></xsl:when>
						<xsl:when test="contains($Y, 'August') = 'true'"><xsl:text>-08-</xsl:text></xsl:when>
						<xsl:when test="contains($Y, 'September') = 'true'"><xsl:text>-09-</xsl:text></xsl:when>
						<xsl:when test="contains($Y, 'October') = 'true'"><xsl:text>-10-</xsl:text></xsl:when>
						<xsl:when test="contains($Y, 'November') = 'true'"><xsl:text>-11-</xsl:text></xsl:when>
						<xsl:when test="contains($Y, 'December') = 'true'"><xsl:text>-12-</xsl:text></xsl:when>				
						<xsl:otherwise><xsl:text>XX</xsl:text></xsl:otherwise>						
					</xsl:choose>
					<xsl:value-of select="substring-after($Y,' ')" /> <xsl:value-of select="substring($Z,6,14)" /></date>
				<location> <xsl:value-of select="geo:lat" /><xsl:text>,</xsl:text><xsl:value-of select="geo:long" />	</location>
				<weather> </weather>
				<link> <xsl:value-of select="link" /> </link>
			</earthquake>
			
		</xsl:for-each>
		
	</earthquakes>

</xsl:template>

</xsl:stylesheet>
