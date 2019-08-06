---
header-id: configuring-service-properties
---

# Configuring service.properties

[TOC levels=1-4]

Service Builder generates a `service.properties` file in your `*-service`
module's `src/main/resources` folder. @product@ uses this file's properties
to alter your service's database schema. You should not modify this file
directly, but rather make any necessary property overrides in a
`service-ext.properties` file in that same folder.

Here are some of the properties the `service.properties` file includes:

- `build.namespace`: This is the
  [namespace you defined in your `service.xml`](/docs/7-2/appdev/-/knowledge_base/a/defining-an-object-relational-map-with-service-builder).
  Liferay distinguishes different modules from each other using their
  namespaces.
- `build.number`: Liferay distinguishes your module's different Service Builder 
  builds. Each time you deploy a distinct Service Builder build to Liferay,
  Liferay increments this number.
- `build.date`: This is the time of your module's latest Service Builder build.
- `include-and-override`: The default value of this property defines
  `service-ext.properties` as an override file for `service.properties`.

| **Note**: In Liferay Portal 6.x Service Builder portlets, the
| `build.auto.upgrade` property in `service.properties` applies Liferay Service
| schema changes upon rebuilding services and redeploying the portlets. This
| property was deprecated in Liferay 7.0.
| 
| The Build Auto Upgrade feature is now different and is set in a global property
| `schema.module.build.auto.upgrade` in the file
| `[Liferay_Home]/portal-developer.properties`. 

Awesome! You now have all the tools necessary to set up your own
`service-ext.properties` file.

## Related Topics

[What is Service Builder?](/docs/7-2/appdev/-/knowledge_base/a/what-is-service-builder)

[Creating Local Services](/docs/7-2/appdev/-/knowledge_base/a/creating-local-services)
