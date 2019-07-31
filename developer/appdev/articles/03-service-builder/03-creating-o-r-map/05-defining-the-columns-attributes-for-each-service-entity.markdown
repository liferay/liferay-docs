---
header-id: defining-the-columns-attributes-for-each-service-entity
---

# Defining the Columns (Attributes) for Each Service Entity

[TOC levels=1-4]

An entity's columns represent its attributes. These attributes map table fields
to Java object fields. To add attributes for your entity, add `<column />` tags
to your entity definition: 

```xml
<column name="guestbookId" primary="true" type="long" />
```

Service Builder creates a database field for each column you add to the
`service.xml` file. It maps a database field type appropriate to the Java type
specified for each column, and it does this across all the databases Liferay
supports. Once Service Builder runs, it generates a Hibernate
configuration that handles the object-relational mapping. Service Builder
automatically generates getter/setter methods in the model class for these
attributes. The column's name specifies the name used in the getters and setters
that are created for the entity's Java field. The column's type indicates the
Java type of this field for the entity. If a column's `primary` (i.e., primary
key) attribute is set to `true`, the column becomes part of the primary key for
the entity. If only one column has `primary` set to `true`, that column
represents the entire primary key for the entity. This is the case in the
Guestbook application. If you define multiple columns with the `primary`
attribute set to true, the combination of columns makes up a compound primary
key for the entity.

| **Note:** The
| [Implementing an Add Method](/docs/7-2/appdev/-/knowledge_base/a/implementing-an-add-method#generate-a-primary-key)
| article demonstrates how to generate unique primary keys for entity instances.

## Create Entity Columns

Define the columns you need for your first entity. The Guestbook entity is
simple: it has only two attributes; a primary key and a name: 

```xml
<column name="guestbookId" primary="true" type="long" />
<column name="name" type="String" />
```

**Note**: On deploying a `*service` module, Service Builder automatically 
generates indexes for all entity primary keys. 

Create a column for each attribute of your entity or entities, using the Java
type you'll use in your application. Service Builder handles mapping it to SQL
for you. 

## Support Multi-tenancy

In addition to columns for your entity's primary key and attributes, add portal
instance ID and site ID columns. Then you can support Liferay's multi-tenancy
features, so that each portal instance and each Site in a portal instance can
have independent sets of your application's data. To hold the site's ID, add
a column called `groupId` of type `long`. To hold the portal instance's ID, add
a column called `companyId` of type `long`: 

```xml
<!-- Group instance -->

<column name="groupId" type="long" />
<column name="companyId" type="long" />
```

## Workflow Fields

You can support Liferay's [workflow system](/docs/7-2/user/-/knowledge_base/u/workflow)
by adding the fields it needs to track an entity's progress: 

```xml
<!-- Status fields -->

<column name="status" type="int" />
<column name="statusByUserId" type="long" />
<column name="statusByUserName" type="String" />
<column name="statusDate" type="Date" />
```

## Audit Entities

Finally, you can add columns to help audit your entities. To track each entity
instance's owner, add a column called `userId` of type `long`. Create a column
named `createDate` of type `Date` to note an entity instance's creation date.
Add a column named `modifiedDate` of type `Date` to track the last time an
entity instance was modified.

```xml
<!-- Audit fields -->

<column name="userId" type="long" />
<column name="userName" type="String" />
<column name="createDate" type="Date" />
<column name="modifiedDate" type="Date" />
```

Great! Your entities have columns that not only represent their attributes, but
also support multi-tenancy, workflow, and auditing. Next, you'll learn how to
specify the relationship service entities. 
