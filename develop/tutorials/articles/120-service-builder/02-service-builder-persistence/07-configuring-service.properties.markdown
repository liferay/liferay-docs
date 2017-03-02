# Configuring service.properties [](id=configuring-service-properties)

In this tutorial, you'll learn how to use and edit the `service.properties`
file. You'll also learn about the properties included in this file and how to
set them to fit your needs.

Service Builder generates a `service.properties` file in your `*-service`
module's `src/main/resources` folder. @product@ uses the properties in this file
to alter your service's database schema. You should not modify this file, but
rather make any necessary overrides in a `service-ext.properties` file in that
same folder.

Here are some of the properties included in the `service.properties` file:

- `build.namespace`: This is the namespace you defined in your `service.xml`.
  Liferay distinguishes different plugins from each other using their
  namespaces.
- `build.number`: Liferay distinguishes different builds of your plugin. Each
  time a distinct build of your plugin is deployed to Liferay, Liferay
  increments this number.
- `build.date`: This is the time of the latest build of your plugin.
- `include-and-override`: The default value of this property defines
  `service-ext.properties` as an override file for `service.properties`.

+$$$

**Note:** The `build.auto.upgrade` property is available for WAR-style Service
Builder applications. This property determines whether or not Liferay should
automatically apply changes to the database model when a new version of the
plugin is deployed. This is `true` by default. This property is not necessary
for module-style applications.

It's sometimes useful to override the `build.auto.upgrade` property in legacy
projects from `service.properties`. Setting `build.auto.upgrade=false` in your
`service-ext.properties` file prevents Liferay from trying automatically to
apply any changes to the database model when a new version of the plugin is
deployed. This is needed in projects to manually manage the changes to the
database (recommended) or in which the SQL schema has intentionally been
modified manually after generation by Service Builder.

$$$

Awesome! You now have all the tools necessary to set up your own
`service-ext.properties` file.

## Related Topics [](id=related-topics)

[What is Service Builder?](/develop/tutorials/-/knowledge_base/7-0/what-is-service-builder)

[Creating Local Services](/develop/tutorials/-/knowledge_base/7-0/creating-local-services)
