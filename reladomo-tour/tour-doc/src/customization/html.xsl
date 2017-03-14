<?xml version='1.0'?>

<!--
  Copyright 2017 Goldman Sachs.
  Licensed under the Apache License, Version 2.0 (the "License");
  you may not use this file except in compliance with the License.
  You may obtain a copy of the License at

      http://www.apache.org/licenses/LICENSE-2.0

  Unless required by applicable law or agreed to in writing,
  software distributed under the License is distributed on an
  "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
  KIND, either express or implied. See the License for the
  specific language governing permissions and limitations
  under the License.
  -->

<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform" version="1.0">

    <xsl:import href="../../target/docbook-lib/docbook/html/docbook.xsl"/>
    <xsl:import href="htmlHighlight.xsl"/>

    <xsl:param name="html.stylesheet" select="'tourdoc.css'"/>
    <xsl:param name="admon.graphics" select="1"/>
    <xsl:param name="section.autolabel" select="1" />
    <xsl:param name="chapter.autolabel" select="1" />
    <xsl:param name="appendix.autolabel" select="1" />
    <xsl:param name="section.label.includes.component.label" select="1" />
    <xsl:param name="highlight.source" select="1" />
    <xsl:param name="toc.section.depth" select="1" />
    <xsl:param name="linenumbering.everyNth" select="1" />
    <xsl:param name="ignore.image.scaling" select="1"/>

    <xsl:template name="user.head.content">
      <xsl:comment> (c) 2016 Copyright Goldman Sachs, Inc. </xsl:comment>
    </xsl:template>

    <!-- customize toc to exclude listing of tables, figures, examples -->
    <xsl:param name="generate.toc">
        appendix  toc,title
        article/appendix nop
        article toc,title
        book  toc,title
        chapter  toc,title
        part  toc,title
        preface   toc,title
        sect1 toc
        sect2 toc
        sect3 toc
        sect4 toc
    </xsl:param>
</xsl:stylesheet>
