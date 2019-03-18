---
header-id: portal-properties
---

# Portal Properties

[TOC levels=1-4]

Portal properties let you configure and override @product@ features. Your
installation's `portal-impl.jar` embeds the default properties file: 

<p>
<span style="font-size:18px;">
<a href="@platform-ref@/7.2-latest/propertiesdoc/portal.properties.html">
<code>portal.properties</code>
<span class="opens-new-window-accessible"> (Opens New Window)</span>
</a>
</span>
</p>

Overriding a portal property requires creating an *extension* portal properties
file that specifies the properties you're overriding. 

| **Note:** In a portal properties extension file, specify only the properties
| you're overriding. 

Here's an example of setting Portal's data source to a MySQL database by adding
override properties in a `[Liferay Home]/portal-ext.properties` file:

```properties
jdbc.default.driverClassName=com.mysql.jdbc.Driver
jdbc.default.url=jdbc:mysql://localhost/myportal?characterEncoding=UTF-8&dontTrackOpenResources=true&holdResultsOpenOverStatementClose=true&useFastDateParsing=false&useUnicode=true
jdbc.default.username=jbloggs
jdbc.default.password=pass123
```

The
[`include-and-override`](@platform-ref@/7.2-latest/propertiesdoc/portal.properties.html#Properties Override)
property specifies portal property files that override the defaults. It
specifies the order the files are read---the last file read takes highest
priority. 

Properties file prioritization (highest to lowest):

1. `[Liferay Home]/portal-setup-wizard.properties`
2. `[user home]/portal-setup-wizard.properties`
3. `[Liferay Home]/portal-ext.properties`
4. `[user home]/portal-ext.properties`
5. `[Liferay Home]/portal-bundle.properties`
6. `[user home]/portal-bundle.properties`
7. `[Liferay Home]/portal.properties`
8. `portal-impl.jar/portal.properties`

`[Liferay Home]/portal-ext.properties` is the most commonly used file for
overriding the defaults. 
