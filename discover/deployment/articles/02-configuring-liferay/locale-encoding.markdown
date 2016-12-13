
@product@ offers the ability to display content based on language, different time zones, "right to left" (that is, languages such as Hebrew, Arabic, and Persian), and localizing user names and titles. Administrators can localize the specific core UI messages so that the messages display in certain languages.

### Time Zones

There are certain levels where the time zones can be set: in the JVM and in the Control Panel. 

#### Setting Time Zones in the Control Panel

To customize the @product@'s default languages and time zones, administrators can make changes at the Instance level.    
1. Navigate to the Control Panel > Configuration    
1. Click Instance Settings    
1. Click on the Miscellaneous tab    

![instance-locales](../../../deployment/images/instance-locales.png)

Using the central left and right arrows, administrators can add or remove available languages and locales. These properties can also be found in the "portal.properties" file located at `…/portal-impl/classes/portal.properties`. To modify the default values of these properties, create a `portal-ext.properties file`. As a best practice, users should not modify the `portal.properties` directly. Lastly, changes made in the UI override `portal.properties` and `portal-ext.properties`.


	#
	# This sets the default locale of the portal, overriding the properties
	# "user.language" and "user.country" specified in system.properties.
	#
	# company.default.locale=en_US    
  
#### Setting Time Zones in the JVM.
Another possible way is to do so at the JVM level. However, users will encounter issues such as assets such as Calendar Events and Web Content articles displaying the wrong dates. This is because each date stored in the database is stored in GMT time. When the system needs to display one stored date to the end users, the @product@ then calculates the display date based on the current date of the _application server_. This date is affected by the configured JVM level time zone and the stored GMT format date. In order to make sure the display date is calculated correctly, the time zone must be configured to GMT at the JVM level. Otherwise, it will result in incorrect time zone offset and cause the display date to be wrongly calculated and displayed. For more information, see [this article](https://customer.liferay.com/documentation/knowledge-base/-/kb/27931) for more information.
  
  
### Friendly URLs and Locales

In additional to configuring @Product@'s instance settings, users can also provide unique URLs for specific languages using the `I18nServlet`. Go to `../ROOT/WEB-INF/web.xml`.

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
	

Be sure to make the changes by first stopping @product@ so that the changes will be applied. Do this for each node if clustered.

### Core UI messages
Administrators can add or modify certain core UI messages (e.g. "Your request completed successfully.") by changing the Language_xx.properties for the respective languages. As best practice, do not modify this file directly; instead create an EXT.properties file such as language-ext.fr.properties. There are three rules of priority when modifying language properties files:

1. EXT versions take precedence over the non-ext versions
1. Language specific versions take precedence over the non-language specific versions
1. Location specific versions take precedence over the non-location specific versions

Lastly, because the "Terms of Use" page is not stored in the `language.xx.properties` files but in a jsp file, see this article on [How to Change the Language for Your Terms of Use Page](https://www.liferay.com/community/wiki/-/wiki/Main/Terms+of+Use#section-Terms+of+Use-How+to+change+the+language+for+your+).

### "Right to Left"
For languages that are displayed right to left, modify the `language.properties` using the following:

	lang.dir=rtl
	lang.line.begin=right
	lang.line.end=left

### Localizing User Names

Beginning in @product@, users have the ability to change the prefix and suffix values for a locale. For example, for Spanish, find the `language_es.properties` in the `portal-impl.jar` 

	lang.user.name.field.names=prefix,first-name,last-name
	lang.user.name.prefix.values=Sr,Sra,Sta,Dr,Dra
	lang.user.name.required.field.names=last-name

For more information, see [Using Liferay Language Settings Tutorial](https://customer.liferay.com/documentation/7.0/develop/tutorials/-/official_documentation/tutorials/using-liferays-language-settings).