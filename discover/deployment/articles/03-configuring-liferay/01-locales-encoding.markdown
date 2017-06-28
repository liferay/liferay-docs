# Locales and Encoding Configuration [](id=locales-encoding-configuration)

@product@ lets you display content based on language, time zone, "right to left"
(that is, languages such as Hebrew, Arabic, and Persian), and lets you localize
user names and titles. Administrators can localize specific core UI messages so
that the messages display in certain languages.

## Time Zones [](id=time-zones)

Time zones can be set in the Control Panel and theoretically in the JVM (but
this must be set to GMT: see below). 

To configure the time zone and customize the default language in the Control
Panel, administrators can make changes at the Instance level. 

1.  Navigate to the *Control Panel* &rarr; *Configuration*. 

2.  Click *Instance Settings*. 

3.  Click on the *Miscellaneous* tab. 

![Figure 1: You can change the default and available languages and the time zone in Instance Settings.](../../images/instance-locales.png)

Using the central left and right arrows, administrators can add or remove
available languages and locales. You can also modify these properties in your 
`portal-ext.properties` file. You'll find them in the 
[Company](@platform-ref@/7.0-latest/propertiesdoc/portal.properties.html#Company) 
section: 

	company.default.locale=en_GB

As an example, the above property changes the locale to English, Great Britain. 
 
## Set the JVM Time Zone to GMT [](id=set-the-jvm-time-zone-to-gmt)

It is possible to set time zones at the JVM level. However, users will encounter
issues such as Calendar Events and Web Content articles displaying the wrong
dates. This happens because the system assumes each date stored in the database
is stored in GMT time.  When the system needs to display one stored date to the
end users, @product@ calculates the display date based on the current date of
the _application server_. This date is affected by the configured JVM level time
zone and the stored GMT format date. In order to make sure the display date is
calculated correctly, the time zone must be configured to GMT at the JVM level.
Otherwise, it will result in incorrect time zone offset and cause the display
date to be wrongly calculated and displayed. 

## Friendly URLs and Locales [](id=friendly-urls-and-locales)

In addition to configuring @product@'s instance settings, users can also provide
unique URLs for specific languages using the `I18nServlet` by editing
@product@'s `web.xml` file: 

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
	
The defaults that Liferay provides should be sufficient for nearly all
circumstances. Because this requires stopping and possibly redeploying @product@
(depending on your app server), test the defaults and make sure you really need
to modify these settings. If you're clustered, you must make these changes on
all nodes.

## Modifying Language Keys [](id=modifying-language-keys)

Developers can add or modify certain core UI messages (e.g. *Your request
completed successfully.*) by 
[modifying the language keys](/develop/tutorials/-/knowledge_base/7-0/overriding-language-keys#modifying-liferays-language-keys) 
that ship by default. 

### Right to Left [](id=right-to-left)

For languages that are displayed right to left, modify the `language.properties`
using the following properties:

	lang.dir=rtl
	lang.line.begin=right
	lang.line.end=left

### Localizing User Names [](id=localizing-user-names)

Users can change the prefix and suffix values for a locale. For example, for
Spanish, the `language_es.properties` file contains these values: 

	lang.user.name.field.names=prefix,first-name,last-name
	lang.user.name.prefix.values=Sr,Sra,Sta,Dr,Dra
	lang.user.name.required.field.names=last-name

For more information, see 
[Using Liferay Language Settings](/develop/tutorials/-/knowledge_base/7-0/using-liferays-language-settings).

## Related Topics [](id=related-topics)

[Using Liferay Language Settings](/develop/tutorials/-/knowledge_base/7-0/using-liferays-language-settings)

[Modifying @product@'s Language Keys](/develop/tutorials/-/knowledge_base/7-0/overriding-language-keys#modifying-liferays-language-keys)

[Overriding a Module's Language Keys](/develop/tutorials/-/knowledge_base/7-0/overriding-language-keys#overriding-a-modules-language-keys)
