---
header-id: service-builder-persistence
---

# Service Builder Persistence

[TOC levels=1-4]

Liferay's Service Builder can generate your project's persistence layer by
automating the creation of interfaces and classes. Your application's
persistence layer persists data represented by your configured entities to a
database. In fact, your local service implementation classes are responsible for
calling the persistence layer to retrieve and store your application's data. So
instead of taking the time-consuming route of writing your own persistence
layer, you can use Service Builder to quickly define your entities and
generate the layer instantaneously.

Here's what this section covers:

- [Defining an object-relational map and generating your persistence layer from that map](/docs/7-2/appdev/-/knowledge_base/a/defining-an-object-relational-map-with-service-builder)
- [Running Service Builder](/docs/7-2/appdev/-/knowledge_base/a/running-service-builder)
- [Understanding and using local and remote services Service Builder generates](/docs/7-2/appdev/-/knowledge_base/a/understanding-the-code-generated-by-service-builder)
- [Using the `ServiceContext` class](/docs/7-2/appdev/-/knowledge_base/a/understanding-servicecontext)
- [Customizing model entities with Model Hints](/docs/7-2/appdev/-/knowledge_base/a/customizing-model-entities-with-model-hints)
- [SQL queries](/docs/7-2/appdev/-/knowledge_base/a/custom-sql)
- [Using Hibernate's criteria API](/docs/7-2/appdev/-/knowledge_base/a/dynamic-query)
- [Configuring `service.properties`](/docs/7-2/appdev/-/knowledge_base/a/configuring-service-properties)
- [Connecting Service Builder to external data sources](/docs/7-2/appdev/-/knowledge_base/a/connecting-service-builder-to-external-databases)

Start with defining an object-relational map. 
