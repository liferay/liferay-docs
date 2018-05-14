# Defining the Columns (Attributes) for Each Service Entity [](id=defining-the-columns-attributes-for-each-service-entity)

An entity's columns represent its attributes. These attributes map table fields
to Java object fields. To add attributes for your entity, drill down to its
columns in the Overview mode outline of the `service.xml` file. From the
outline, expand the *Entities* node and expand an entity node. Then select the
*Columns* node. Liferay @ide@ displays a table of the entity's columns. 

![Figure 1: Liferay @ide@ facilitates defining table columns for entities.](../../../../images/service-builder-entity-columns.png)

Service Builder creates a database field for each column you add to the
`service.xml` file. It maps a database field type appropriate to the Java type
specified for each column, and it does this across all the databases Liferay
supports. Once Service Builder runs, it generates a Hibernate
configuration that handles the object-relational mapping. Service Builder
automatically generates getter/setter methods in the model class for these
attributes. The column's Name specifies the name used in the getters and setters
that are created for the entity's Java field. The column's Type indicates the
Java type of this field for the entity. If a column's Primary (i.e., primary
key) attribute value is set to `true`, then the column becomes part of the
primary key for the entity. An entity's primary key uniquely identifies
the entity. If only one column has Primary set to `true`, then that column
represents the entire primary key for the entity. This is the case in the
Bookmarks application. However, it's possible to use multiple columns as the
primary key for an entity. In this case, the combination of columns makes up
a compound primary key for the entity.

## Create Entity Columns [](id=create-entity-columns)

Similar to the way you used the form table for adding entities, add attribute
columns for each of your entities.

1.  Create each attribute by clicking on the Add icon
    (![Add](../../../../images/icon-add-ide.png)).

2.  Fill in the attribute's name

3.  Select the attribute's type. While your cursor is in a column's *Type* 
    field, an option icon appears. Click this icon to select the appropriate
    type for the column.

4.  Specify whether the attribute is a primary key for the entity.

**Note**: On deploying a `*service` module, Service Builder automatically 
generates indexes for all entity primary keys. 

Create a column for each attribute of your entity or entities.

## Support Multi-tenancy [](id=support-multi-tenancy)

In addition to columns for your entity's primary key and attributes, add portal
instance ID and site ID columns. They let your portlet support Liferay's
multi-tenancy features, so that each portal instance and each site in a portal
instance can have independent sets of portlet data. To hold the site's ID, add a
column called `groupId` of type `long`. To hold the portal instance's ID, add a
column called `companyId` of type `long`. To add these columns to your entities,
follow the table below.

**Portal and site scope columns**

  Name      | Type   | Primary
:---------: | :----: | :------:
`companyId` | long   | no
`groupId`   | long   | no

## Track Ownership [](id=track-ownership)

To track each entity instance's owner, add a column called `userId` of type
`long`. 

**User column**

  Name   | Type   | Primary
:------: | :----: | :------:
`userId` | long   | no

## Audit Entities [](id=audit-entities)

Lastly, you can add columns to help audit your entities. For example, you could
create a column named `createDate` of type `Date` to note an entity instance's
creation date. And add a column named `modifiedDate` of type `Date` to track
the last time an entity instance was modified.

**Audit columns**

  Name         | Type   | Primary
:------------: | :----: | :------:
`userId`       | long   | no
`createDate`   | Date   | no
`modifiedDate` | Date   | no

Great! Your entities have columns that not only represent their attributes, but
also support multi-tenancy and entity auditing. Next, you'll learn how to
specify the relationship service entities. 
