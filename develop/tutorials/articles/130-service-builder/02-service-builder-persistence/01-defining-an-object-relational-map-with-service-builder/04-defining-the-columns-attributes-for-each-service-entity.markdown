## Step 4: Defining the Columns (Attributes) for Each Service Entity [](id=step-4-defining-the-columns-attributes-for-each-service-entity)

Each entity is described by its columns, which represent an entity's attributes.
These attributes map on the one side to fields in a table and on the other side
to attributes of a Java object. To add attributes for your entity, you need to
drill down to its columns in the Overview mode outline of the `service.xml`
file. From the outline, expand the *Entities* node and expand an entity node.
Then select the *Columns* node. Liferay @ide@ displays a table of the entity's
columns. 

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
primary key for the entity. An entity's primary key is a unique identifier for
the entity. If only one column has Primary set to `true`, then that column
represents the entire primary key for the entity. This is the case in the Event
Listing example. However, it's possible to use multiple columns as the primary
key for an entity. In this case, the combination of columns makes up a compound
primary key for the entity.

Similar to the way you used the form table for adding entities, add attribute
columns for each of your entities. Create each attribute by clicking on the Add
icon (![Add](../../../images/icon-add-ide.png)). Then fill in the name of the
attribute, select its type, and specify whether it is a primary key for the
entity. While your cursor is in a column's *Type* field, an option icon appears.
Click this icon to select the appropriate type for the column. Create a column
for each attribute of your entity or entities.

In addition to columns for your entity's primary key and attributes, it's
recommended to add columns for portal instance ID and site ID. They allow your
portlet to support the multi-tenancy features of Liferay, so that each portal
instance and each site in a portal instance can have independent sets of portlet
data. To hold the site's ID, add a column called `groupId` of type `long`. To
hold the portal instance's ID, add a column called `companyId` of type `long`.
If you'd like to add these columns to your entities, follow the table below.

**Portal and site scope columns**

  Name      | Type   | Primary
:---------: | :----: | :------:
`companyId` | long   | no
`groupId`   | long   | no

You'll also want to know who owns each entity instance. To keep track of that,
add a column called `userId` of type `long`. 

**User column**

  Name   | Type   | Primary
:------: | :----: | :------:
`userId` | long   | no

Lastly, you can add columns to help audit your entities. For example, you could
create a column named `createDate` of type `Date` to note the date an entity
instance was created. And add a column named `modifiedDate` of type `Date` to
track the last time an entity instance was modified.

**Audit columns**

  Name         | Type   | Primary
:------------: | :----: | :------:
`userId`       | long   | no
`createDate`   | Date   | no
`modifiedDate` | Date   | no

Great! Your entities are set with the columns that not only represent their
attributes, but also support multi-tenancy and entity auditing. Next, you'll
learn how to specify the relationship service entities. 
