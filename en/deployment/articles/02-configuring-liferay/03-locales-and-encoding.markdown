---
header-id: locales-and-encoding-configuration
---

# Locales and Encoding Configuration

[TOC levels=1-4]

<aside class="alert alert-info">
  <span class="wysiwyg-color-blue120">This document has been updated and ported to <a href="https://learn.liferay.com/dxp/latest/en/installation-and-upgrades/setting-up-liferay/initial-instance-localization.html">Liferay Learn</a> and is no longer maintained here.</span>
</aside>

You can display content based on language, time zone, "right to left" (that is,
languages such as Hebrew, Arabic, and Persian), and you can localize user names
and titles. Administrators can localize specific core UI messages so that the
messages display in certain languages.

## Time Zones

You can set time zones in the Control Panel and theoretically in the JVM (but
this must be set to GMT: see below). 

Time zone configuration and default language customization are done in the
Control Panel, at the Instance level. 

1.  Navigate to the *Control Panel* &rarr; *Configuration*. 

2.  Click *Instance Settings*. 

3.  Click on the *Miscellaneous* tab. 

![Figure 1: You can change the default and available languages and the time zone in Instance Settings.](../../images/instance-locales.png)

The central left and right arrows let you add or remove available languages and
locales. You can also set these as properties in your `portal-ext.properties`
file in your
[Liferay Home](/docs/7-2/deploy/-/knowledge_base/d/liferay-home)
folder. The `portal.properties` reference document's 
[Company](@platform-ref@/7.2-latest/propertiesdoc/portal.properties.html#Company) 
section defines the default locale. The 
[Languages and Time Zones](@platform-ref@/7.2-latest/propertiesdoc/portal.properties.html#Languages%20and%20Time%20Zones)
section defines the available and current locales.

```properties
company.default.locale=en_GB 
```

| **Note:** The `company.default.locale` portal property is only intended for 
| use on initial startup. To change the language settings on an existing 
| instance, open the Control Panel and navigate to *Configuration* &rarr; 
| *Instance Settings* and select the Localization category under the PLATFORM 
| heading. Under the Language entry you can change the default language, as well 
| as define the current locales. 

As an example, the above property changes the locale to English, Great Britain. 
 
## Set the JVM Time Zone to GMT

If you set the time zone in the JVM, it causes issues such as Calendar Events
and Web Content articles displaying the wrong dates. This happens because the
system assumes each date stored in the database is stored in GMT time. When the
system needs to display one stored date to the end users, the display date is
calculated by the application server's current date. This date is affected by
the configured JVM level time zone and the stored GMT format date. To make sure
the display date is calculated correctly, the time zone must be configured to
GMT at the JVM level. Otherwise, an incorrect time zone offset at the JVM level
causes the display date to be wrongly calculated and displayed. 

## Friendly URLs and Locales

In addition to configuring instance settings, you can also define unique URLs
for specific languages using the `I18nServlet` by editing Portal's `web.xml`
file: 

```xml
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
```

The defaults should be sufficient for nearly all circumstances. Because
`web.xml` changes require stopping and possibly redeploying @product@ (depending
on your app server), test the defaults and make sure you really need to modify
these settings. If you're clustered, you must make these changes on all nodes.

## Modifying Language Keys

Developers can add or modify certain core UI messages (e.g. *Your request
completed successfully.*) by 
[modifying the language keys](/docs/7-2/customization/-/knowledge_base/c/overriding-language-keys) 
that ship by default. 

### Right to Left

For languages that are displayed right to left, use the following language
properties settings:

```properties
lang.dir=rtl
lang.line.begin=right
lang.line.end=left
```

To display right to left by default,
[override these properties globally](/docs/7-2/customization/-/knowledge_base/c/overriding-global-language-keys). 

### Localizing User Names

Users can change the prefix and suffix values for a locale. For example, for
Spanish, the `language_es.properties` file contains these values: 

```properties
lang.user.name.field.names=prefix,first-name,last-name
lang.user.name.prefix.values=Sr,Sra,Sta,Dr,Dra
lang.user.name.required.field.names=last-name
```

For more information, see 
[Using Liferay Language Settings](/docs/7-2/frameworks/-/knowledge_base/f/using-liferays-localization-settings).

## Related Topics

[Using Liferay Language Settings](/docs/7-2/frameworks/-/knowledge_base/f/using-liferays-localization-settings)

[Overriding Global Language Keys](/docs/7-2/customization/-/knowledge_base/c/overriding-global-language-keys)

[Overriding a Module's Language Keys](/docs/7-2/customization/-/knowledge_base/c/overriding-a-modules-language-keys)
