<?xml version='1.0' encoding="UTF-8" ?>
<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
                xmlns:Map="java:java.util.HashMap"
                version="2.0"
                exclude-result-prefixes="Map">
    <xsl:output media-type="xml" indent="yes" encoding="UTF-8"/>
    <xsl:param name="map"/>

    <xsl:template match="/">
        <xsl:variable name="root" select="node()"/>
        <xsl:element name="Document" xmlns="http://cxf.apache.org/bindings/soap">
            <!--            <xsl:choose>-->
            <!--                <xsl:when test="'301'=Map:get($map,'Type')">-->
            <!--                </xsl:when>-->
            <!--            </xsl:choose>-->
            <xsl:element name="AcctSwtchInfReq">
                <xsl:element name="AccountSwitchDetails">
                    <xsl:element name="UniqueReferenceNumber">
                        <xsl:value-of select="Map:get($map,'UniqueReferenceNumber')"/>
                    </xsl:element>

                </xsl:element>
            </xsl:element>
        </xsl:element>
    </xsl:template>

    <xsl:template match="Document">
        <xsl:element name="Document">
        </xsl:element>
    </xsl:template>

</xsl:stylesheet>