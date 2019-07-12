---
header-id: defining-service-entity-finder-methods
---

# Defining Service Entity Finder Methods

[TOC levels=1-4]

Finder methods retrieve entity objects from the database based on specified
parameters. For each finder defined, Service Builder generates several methods
to fetch, find, remove, and count entity instances based on the finder's
parameters. 

When supporting Liferay's multi-tenancy, it's important to be able to find its
entities per Site. 

## Creating Finders

Finders are easy to create: 

```xml
<finder name="GroupId" return-type="Collection">
   <finder-column name="groupId" />
</finder> 
```

The example above is among the simplest of finders, and is one you should always
add if you're supporting multi-tenancy. This finder returns a collection of
objects that belong to the Site on which your application has been placed.
Service Builder generates finder-related methods (e.g., `fetchByGroupId`,
`findByGroupId`, `removeByGroupId`, `countByGroupId`) for the your entities in
the `*Persistence` and `*PersistenceImpl` classes. The first of these classes is
the interface; the second is its implementation. For example, the Guestbook
application generates its entity finder methods in the `-Persistence`
classes found in the
`/guestbook-api/src/main/java/com/liferay/docs/guestbook/service/persistence` folder
and the `-PersistenceImpl` classes in the
`/guestbook/src/main/java/com/liferay/docs/service/persistence/impl`
folder.

You're not limited to finding by one column, however; you can create
multi-column finders: 

```xml
<finder name="G_S" return-type="Collection">
	<finder-column name="groupId" />
	<finder-column name="status" />
</finder>
```

| **Important**: DO NOT create finders that use entity primary key as parameters.
| They're unnecessary as Service Builder automatically generates
| `findByPrimaryKey` and `fetchByPrimaryKey` methods for all entity primary keys.
| On deploying a `*service` module, Service Builder creates indexes for all entity
| primary key columns and finder columns. Adding finders that use entity primary
| keys results in attempts to create multiple indexes for the same
| columns---Oracle DB, for example, reports these attempts as errors.

Now you know to configure Service Builder to create finder methods for your
entity. Terrific!

Now that you've specified the service for your project, you're ready to *build*
the service by running Service Builder. It's time to
[run Service Builder and examine the code it generates](/docs/7-2/appdev/-/knowledge_base/a/running-service-builder). 
