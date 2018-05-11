# Locales and Encoding Configuration [](id=locales-and-encoding-configuration)

You can display content based on language, time zone, "right to left" (that is,
languages such as Hebrew, Arabic, and Persian), and you can localize user names
and titles. Administrators can localize specific core UI messages so that the
messages display in certain languages.

## Time Zones [](id=time-zones)

You can set time zones in the Control Panel and theoretically in the JVM (but
this must be set to GMT: see below). 

Time zone configuration and default language customization are done in the
Control Panel, at the Instance level. 

1.  Navigate to the *Control Panel* &rarr; *Configuration*. 

2.  Click *Instance Settings*. 

3.  Click on the *Miscellaneous* tab. 

![Figure 1: You can change the default and available languages and the time zone in Instance Settings.](../../../images/instance-locales.png)

The central left and right arrows let you add or remove available languages and
locales. You can also set these as properties in your `portal-ext.properties`
file in your
[Liferay Home](/discover/deployment/-/knowledge_base/7-1/installing-product#liferay-home)
folder. The `portal.properties` reference document's 
[Company](@platform-ref@/7.1-latest/propertiesdoc/portal.properties.html#Company) 
section defines the default locale. The 
[Languages and Time Zones](@platform-ref@/7.1-latest/propertiesdoc/portal.properties.html#Languages%20and%20Time%20Zones)
section defines the available and current locales.

	company.default.locale=en_GB 

As an example, the above property changes the locale to English, Great Britain. 
 
## Set the JVM Time Zone to GMT [](id=set-the-jvm-time-zone-to-gmt)

If you set the time zone in the JVM, it causes issues such as Calendar Events
and Web Content articles displaying the wrong dates. This happens because the
system assumes each date stored in the database is stored in GMT time. When the
system needs to display one stored date to the end users, the display date is
calculated by the application server's current date. This date is affected by
the configured JVM level time zone and the stored GMT format date. To make sure
the display date is calculated correctly, the time zone must be configured to
GMT at the JVM level. Otherwise, an incorrect time zone offset at the JVM level
causes the display date to be wrongly calculated and displayed. 

## Friendly URLs and Locales [](id=friendly-urls-and-locales)

In addition to configuring instance settings, you can also define unique URLs
for specific languages using the `I18nServlet` by editing Portal's `web.xml`
file: 

	<servlet-mapping>
		<servlet-name>I18n Servlet</servlet-name>
		<url-pattern>/ar/*</url-pattern>
	</servlet-mapping>
    .
    .
	.
	<servlet-mapping>
		<servlet-name>I18n Servlet</servlet-name>
		<url-pattern>/de/*</url-pattern>
	</servlet-mapping>
	
The defaults should be sufficient for nearly all circumstances. Because
`web.xml` changes require stopping and possibly redeploying @product@ (depending
on your app server), test the defaults and make sure you really need to modify
these settings. If you're clustered, you must make these changes on all nodes.

## Modifying Language Keys [](id=modifying-language-keys)

Developers can add or modify certain core UI messages (e.g. *Your request
completed successfully.*) by 
[modifying the language keys](/develop/tutorials/-/knowledge_base/7-1/overriding-language-keys) 
that ship by default. 

### Right to Left [](id=right-to-left)

For languages that are displayed right to left, use the following language
properties settings:

	lang.dir=rtl
	lang.line.begin=right
	lang.line.end=left

To display right to left by default,
[override these properties globally](/develop/tutorials/-/knowledge_base/7-1/overriding-global-language-keys). 

### Localizing User Names [](id=localizing-user-names)

Users can change the prefix and suffix values for a locale. For example, for
Spanish, the `language_es.properties` file contains these values: 

	lang.user.name.field.names=prefix,first-name,last-name
	lang.user.name.prefix.values=Sr,Sra,Sta,Dr,Dra
	lang.user.name.required.field.names=last-name

For more information, see 
[Using Liferay Language Settings](/develop/tutorials/-/knowledge_base/7-1/using-liferays-language-settings).

## Related Topics [](id=related-topics)

[Using Liferay Language Settings](/develop/tutorials/-/knowledge_base/7-1/using-liferays-language-settings)

[Overriding Global Language Keys](/develop/tutorials/-/knowledge_base/7-1/overriding-global-language-keys)

[Overriding a Module's Language Keys](/develop/tutorials/-/knowledge_base/7-1/overriding-a-modules-language-keys)
