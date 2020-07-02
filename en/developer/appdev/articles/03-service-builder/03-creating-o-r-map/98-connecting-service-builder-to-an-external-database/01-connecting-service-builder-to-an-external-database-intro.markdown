---
header-id: connecting-service-builder-to-an-external-database
---

# Connecting Service Builder to an External Database

[TOC levels=1-4]

If you want to use a database separate from @product@'s, follow these steps:

1. Specify your database and a data source name in your `service.xml`.

1. Create the database manually.

1. Define the data source.

1. Connect your Service Builder module to the data source. 

1. Run Service Builder. 

There are two different ways to create the connection:

1.  **`DataSourceProvider`:** This approach involves implementing a
    [`DataSourceProvider`](@platform-ref@/7.2-latest/javadocs/portal-kernel/com/liferay/portal/kernel/dao/jdbc/DataSourceProvider.html)
    [`ServiceProviderInterface`](https://docs.oracle.com/javase/tutorial/sound/SPI-intro.html)
    (SPI). This way requires the fewest files and steps and works regardless of
    whether your Service Builder module uses the `ds` or `spring`
    [dependency injector](/docs/7-2/appdev/-/knowledge_base/a/defining-global-service-information#dependency-injector).

2.  **Spring Beans:** Configure the connection using Spring XML files. This 
    approach only works with Service Builder modules that use the `spring` 
    [dependency injection option](/docs/7-2/appdev/-/knowledge_base/a/defining-global-service-information#dependency-injector). 

| **Note:** All entities defined in a Service Builder module's
| [`service.xml`](/docs/7-2/appdev/-/knowledge_base/a/creating-the-service-xml-file)
| file are bound to the same data source. Binding different entities to
| different data sources requires defining the entities in separate Service
| Builder modules and configuring each of the modules to use a different data
| source.

| **Warning:** If your Service Builder services require nested transactions, 
| using an external data source may not be appropriate. Transactions between
| separate data sources cannot be fully nested. Rollbacks may not propagate
| between services that use an external data source and @product@ services (or
| another app's services) that use a different data source. 

Since `DataSourceProvider` is the easiest, most versatile approach, it's
explained first. 
