# Modifying Liferay's Language Keys

Using language files with keys allows you the ultimate flexibility in
translating your application's user interface messages. But you can also modify
the language keys used by Liferay itself, which will change not only your own
application, but any messages in Liferay that rely on the core's language
properties files.

You can access the core default language properties from Liferay's source code
or your Liferay bundle.

-  From Liferay's source:

    liferay-portal/portal-impl/src/content/Language.properties

-  From a Liferay bundle:

    [Liferay Home]/tomcat-[version]/webapps/ROOT/WEB-INF/lib/portal-impl.jar

You'll find a lot of language keys there. So how do you modify them? Create a
module with your `Language.properties` edits and additions, and publish a
`ResourceBundle` implementation.

## Creating a Resource Bundle

In your module, create a class that extends `java.util.ResourceBundle` and
register it as an OSGi service with the property 
