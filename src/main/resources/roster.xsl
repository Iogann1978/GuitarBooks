<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
	<xsl:output method="html" encoding="UTF-8"/>
	
	<xsl:template match="roster">
		<html>
			<head>
				<meta charset="utf-8"/>
			</head>
			<body>
				<xsl:apply-templates select="//books">
					<xsl:sort select="@book" />
				</xsl:apply-templates>
			</body>
		</html>
	</xsl:template>
	
	<xsl:template match="books">
		<h1><xsl:value-of select="@book"/></h1>
		<p>
			<b><xsl:text>Автор сборника: </xsl:text></b>
			<i><xsl:value-of select="@author"/></i>
			<br/>
			<b><xsl:text>Издательство: </xsl:text></b>
			<i><xsl:value-of select="@publisher"/></i>
			<br/>
			<b><xsl:text>Год издания: </xsl:text></b>
			<i><xsl:value-of select="@year"/></i>
			<br/>
			<b><xsl:text>Количество страниц: </xsl:text></b>
			<i><xsl:value-of select="@pages"/></i>
			<br/>
			<b><xsl:text>Имя файла: </xsl:text></b>
			<i><xsl:value-of select="@file"/></i>
			<br/>
		</p>
		<h2>Содержание сборника:</h2>
		<table border="1">
			<thead>
				<tr><th>Название произведения</th><th>Композитор</th><th>Страница</th></tr>
			</thead>
			<tbody>
				<xsl:apply-templates select="tracks">
					<xsl:sort select="@page" data-type="number" />
				</xsl:apply-templates>
			</tbody>
		</table>
	</xsl:template>

	<xsl:template match="tracks">
		<tr>
			<td><xsl:value-of select="@track"/></td>
			<td><xsl:value-of select="@composer"/></td>
			<td><xsl:value-of select="@page"/></td>
		</tr>
	</xsl:template>
			
</xsl:stylesheet>
