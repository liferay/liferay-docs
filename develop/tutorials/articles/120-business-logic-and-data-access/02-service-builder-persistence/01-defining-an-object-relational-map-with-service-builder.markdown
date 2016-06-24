# Defining an Object-Relational Map with Service Builder [](id=defining-an-object-relational-map-with-service-builder)

In this tutorial, you'll learn how to define an object relational map for your
application so that it can persist data. As an example, you'll examine the
existing Liferay Bookmarks application that uses Service Builder.

The Bookmarks application is an example portlet project that an organization can
use to bookmark assets in Liferay. The application defines two entities, or
model types, to represent an organization's bookmarks and their folders. These
entities are called *bookmark entries* and *bookmark folders*. Since a bookmark
must have a folder (even if it's a root folder), the entry entity references a
folder entity as one of its attributes. 

You can design your application's modules anyway you like, but for the Bookmarks
application, its Java sources reside in the `bookmarks-api`,
`bookmarks-service`, and `bookmarks-web` modules. Notice the
`BookmarksAdminPortlet.java` and `BookmarksPortlet.java` files in the
`com.liferay.bookmarks.web.portlet` package in the `bookmarks-web` module. These
portlet classes extend Liferay's `MVCPortlet` class. They act as the controllers
in the MVC pattern. These classes contain the business logic that invokes the
Service Builder generated bookmarks services that you'll learn how to create in
this section. The application's view layer is implemented in the JSPs in the
`bookmarks-web/src/main/resources/META-INF/resources` folder.

You can learn how to generate a generic modular application from scratch that
includes the `*api`, `*service`, and `*web` modules by default in the
[Modularizing an Existing Portlet](/develop/tutorials/-/knowledge_base/7-0/modularizing-an-existing-portlet)
tutorial. This tutorial assumes you've assembled your application's modules
similarly to the linked tutorial above. Be sure to also visit the
[OSGi and Modularity](/develop/tutorials/-/knowledge_base/7-0/osgi-and-modularity)
tutorial for additional info on the `*api`, `*service`, and `*web` modules.

The first step in using Service Builder is to define your model classes and
their attributes in a `service.xml` file. This file's location typically resides
in the root folder of the `*-service` module, although you can configure your
build tool to recognize it from other directories. In Service Builder
terminology, your model classes are called entities. For example, the Bookmarks
application has two entities: `BookmarksEntry` and `BookmarksFolder`. The
requirements for each of these entities are defined in the `bookmarks-service`
module's
[service.xml](https://github.com/liferay/liferay-portal/blob/master/modules/apps/collaboration/bookmarks/bookmarks-service/service.xml)
listed in the `<column />` elements. 

Once Service Builder reads the `service.xml` file, you can define your entities.
Liferay IDE makes it very easy to define entities in your application's
`service.xml` file. To define a custom entity, follow these steps:

1. Create the `service.xml` file in your project's `*-service` module. It
   resides in the root folder of that module, if one does not already exist
   there.

2. Define global information for the service.

3. Define service entities. 

4. Define the columns (attributes) for each service entity.

5. Define relationships between entities.

6. Define a default order for the entity instances to be retrieved from the
   database. 

7. Define finder methods that retrieve objects from the database based on
   specified parameters.

You'll examine these steps in detail next, starting with creating a
`service.xml` file.

## Step 1: Creating the service.xml File [](id=step-1-creating-the-service-xml-file)

To define a service for your portlet project, you must create a `service.xml`
file. The DTD (Document Type Declaration) file
[http://www.liferay.com/dtd/liferay-service-builder_7_0_0.dtd](http://www.liferay.com/dtd/liferay-service-builder_7_0_0.dtd)
specifies the format and requirements of the XML to use. You can create your
`service.xml` file manually, following the DTD, or you can use Liferay IDE.
Liferay IDE helps you build the `service.xml` file piece-by-piece, taking the
guesswork out of creating XML that adheres to the DTD.

If a default `service.xml` file already exists in your `*-service` module
folder, check to see if it has an `<entity />` element named *Foo*. If it has
the Foo entity, remove the entire `<entity name="Foo" ...> ... </entity>`
element. The Liferay IDE project wizard creates the Foo entity as an example. It
has no practical use for you. 

<!-- 
If you don't already have a `service.xml` file, it's easy to create one using
Liferay IDE. Simply select your `event-listing-portlet` project in the Package
Explorer and then select *File* &rarr; *New* &rarr; *Liferay Service Builder*.
Liferay IDE creates a `service.xml` file in your `docroot/WEB-INF/src` folder
and displays the file in *Overview* mode. -->

<!-- The above paragraph is not accurate at this time because IDE does not
currently support the Liferay Service Builder functionality. This tutorial will
use the working parts of IDE's Service Builder feature, and will do other parts
a different way. Full support for the Service Builder functionality in IDE is
expected for IDE 3.1. -Cody --> 

If you don't already have a `service.xml` file, create one in your `*-service`
module. Once it's created, open it. Liferay IDE provides a Diagram mode and a
Source mode to give you different perspectives of the service information in
your `service.xml` file. Diagram mode is helpful for creating and visualizing
relationships between service entities. Source mode brings up the `service.xml`
file's raw XML content in the editor. You can switch between these modes as you
wish.

Next, you can start filling out the global information for your service. 

## Step 2: Defining Global Service Information [](id=step-2-defining-global-service-information)

A service's global information applies to all of its entities, so it's a good
place to start. In Liferay IDE, select the *Service Builder* node in the upper
left corner of the Overview mode of your `service.xml` file. The main section of
the view now shows the Service Builder form in which you can enter your
service's global information. The fields include the service's package path,
author, and namespace options.

![Figure 1: This is the Service Builder form from a fictitious Event Listing application's `service.xml`.](../../../images/service-builder-main-form.png)

The package path specifies the package in which the service and persistence
classes are generated. The package path defined above ensures that the service
classes are generated in the `com.liferay.docs.eventlisting` package in the
`*-api` module. The persistence classes are generated in a package of the same
name in the `*-service` module. For examples, you can look in the Bookmarks
application's
[bookmarks-api](https://github.com/liferay/liferay-portal/tree/master/modules/apps/collaboration/bookmarks/bookmarks-api)
and
[bookmarks-service](https://github.com/liferay/liferay-portal/tree/master/modules/apps/collaboration/bookmarks/bookmarks-service)
modules to see an example of how these are automatically generated for you.
Refer to the
[Running Service Builder and Understanding the Generated Code](/develop/tutorials/-/knowledge_base/7-0/running-service-builder-and-understanding-the-generated-code)
tutorial for a description of the contents of these packages. 

Service Builder uses the service namespace in naming the database tables it
generates for the service. For example, *Event* could serve as the namespace for
an Event Listing portlet service. Service Builder uses the namespace in the
following SQL scripts it generates in your `src/main/resources/sql` folder:

- `indexes.sql`
- `sequences.sql`
- `tables.sql`

+$$$

**Note:** The folder location for holding your generated SQL scripts is
configurable. For example, if you're using Gradle, you can define the `sqlDir`
attribute in the app's `build.gradle` file. Likewise, those using Ant can
configure an argument in their `build.xml` similar to the following:

    <arg value="service.sql.dir=${basedir}/../sql"/>

$$$

@product@ uses these scripts to create database tables for all the entities
defined in the `service.xml` file. Service Builder prepends the namespace
to the database table names. Since the namespace value above is `Event`, the
names of the database tables created for the entities start with `Event_` as
their prefix. The namespace for each Service Builder project must be unique.
Separate plugins should use separate namespaces and should not use a namespace
already used by Liferay (such as `Users` or `Groups`). Check the table names in
Liferay's database if you're wondering which namespaces are already in use.

+$$$

**Warning:** Use caution when assigning the namespace value. Some databases have
strong restrictions on database table name lengths.

$$$

As the last piece of global information, enter your name as the service's
*author* in your `service.xml` file. Service Builder adds `@author` annotations
with the specified name to all of the generated Java classes and interfaces.
Save your `service.xml` file to preserve the information you added. Next, you'll
add entities for your service's events and locations. 

## Step 3:  Defining Service Entities [](id=step-3-defining-service-entities)

Entities are the heart and soul of a service. Entities represent the map between
the model objects in Java and the fields and tables in your database. Once your
entities are defined, Service Builder handles the mapping automatically, giving
you a facility for taking Java objects and persisting them. For the Bookmarks
application, two entities are created according to its
[service.xml](https://github.com/liferay/liferay-portal/blob/master/modules/apps/collaboration/bookmarks/bookmarks-service/service.xml)
--one for bookmark entries and one for bookmark folders.

Here's a summary of the information used for the BookmarksEntry entity:

- **Name:** *BookmarksEntry*
- **Local service:** *yes*
- **Remote service:** *yes* 

And here's what was used for the BookmarksFolder entity:

- **Name:** *BookmarksFolder*
- **Local service:** *yes*
- **Remote service:** *yes* 

To create your entities using Liferay IDE, select the *Entities* node under the
Service Builder node in the outline on the left side of the `service.xml` editor
in Overview mode. In the main part of the view, notice that the Entities table
is empty. Create an entity by clicking on the *Add Entity* icon
(![Add](../../../images/icon-add-ide.png)) to the right of the table. Enter your
entity's name and if you'd like to generate local and remote services for that
entity. Add as many entities as you need.

![Figure 2: Adding service entities is easy with Liferay IDE's *Overview* mode of your `service.xml` file.](../../../images/service-add-entity.png)

An entity's name is used to name the database table for persisting instances
of the entity. The actual name of the database table is prefixed with the
namespace; the Bookmarks example creates one database table named
`Bookmarks_BookmarksEntry` and another named `Bookmarks_BookmarksFolder`. 

Setting the *local service* attribute to `true` instructs Service Builder to
generate local interfaces for the entity's services. The default value for local
service is `false`. Local services can only be invoked from the Liferay server
on which they're deployed. Therefore, if your application will be deployed to
Liferay, the service will be local from your Liferay server's point of view.

Setting the *remote service* attribute to `true` instructs Service Builder to
generate remote interfaces for the service. The default value for remote service
is `true`. You could build a fully-functional application without generating
remote services. In that case, you could set local service to `true` and remote
service to `false` for your entities. If, however, you want to enable remote
access to your application's services, you should set both local service and
remote service to `true`.

+$$$

**Tip:** Suppose you have an existing DAO service for an entity built using some
other framework such as JPA. You can set local service to `false` and remote
service to `true` so that the methods of your remote `-Impl` class can call the
methods of your existing DAO. This enables your entity to integrate with
Liferay's permission-checking system and provides access to the web service APIs
generated by Service Builder. This is a very handy, quite powerful, and often
used feature of Liferay. 

$$$

Now that you've seen how to create your application's entities, you'll learn
how to describe their attributes using entity *columns*. 

## Step 4: Defining the Columns (Attributes) for Each Service Entity [](id=step-4-defining-the-columns-attributes-for-each-service-entity)

Each entity is described by its columns, which represent an entity's attributes.
These attributes map on the one side to fields in a table and on the other side
to attributes of a Java object. To add attributes for your entity, you need to
drill down to its columns in the Overview mode outline of the `service.xml`
file. From the outline, expand the *Entities* node and expand an entity node.
Then select the *Columns* node. Liferay IDE displays a table of the entity's
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

## Step 5: Defining Relationships Between Service Entities [](id=step-5-defining-relationships-between-service-entities)

Often you'll want to reference one type of entity in the context of another
entity. That is, you'll want to *relate* the entities. Liferay's Bookmarks
application defines a relationship between an entry and its folder.

As mentioned earlier, each bookmark must have a folder. Therefore, each
`BookmarksEntry` entity must relate to a `BookmarksFolder` entity. Liferay IDE's
Diagram mode for `service.xml` makes relating entities easy. First, select
Diagram mode for the `service.xml` file. Then select the *Relationship* option
under *Connections* in the palette on the right side of the view. This
relationship tool helps you draw relationships between entities in the diagram.
Click your first entity and move your cursor over to the entity you'd like to
relate it with. Liferay IDE draws a dashed line from your selected entity to the
cursor. Click the second entity to complete drawing the relationship. Liferay
IDE turns the dashed line into a solid line, with an arrow pointing to the
second entity. Save the `service.xml` file. 

Congratulations! You've related two entities. Their relationship should show in
Diagram mode and look similar to that of the figure below. 

![Figure 3: Relating entities is a snap in Liferay IDE's *Diagram* mode for `service.xml`.](../../../images/service-builder-relate-entities.png)

Switch to *Source* mode in the editor for your `service.xml` file and note that
Liferay IDE created a column element in the first selected entity to hold the ID
of the corresponding entity instance reference. For example:

    <column name="folderId" type="long" />

Now that your entity columns are in place, you can specify the default order in
which the entity instances are retrieved from the database. 

## Step 6: Defining Ordering of Service Entity Instances [](id=step-6-defining-ordering-of-service-entity-instances)

Often, you want to retrieve multiple instances of a given entity and list them
in a particular order. Liferay lets you specify the default order of the
entities in your `service.xml` file. 

Suppose you want to return `BookmarksEntry` entities alphabetically by name.
It's easy to specify these default orderings using Liferay IDE. Switch back to
*Overview* mode in the editor for your `service.xml` file. Then select the
*Order* node under the entity node in the outline on the left side of the
view. The IDE displays a form for ordering the chosen entity. Check the *Specify
ordering* checkbox to show the form for specifying the ordering. Create an order
column by clicking the *Add* icon (![Add](../../../images/icon-add-ide.png)) to
the right of the table. Enter the column name (e.g., *name*, *date*, etc.) to
use in ordering the entity. Click the *Browse icon*
(![Browse](../../../images/icon-browse-ide.png)) to the right of the *By* field
and choose the *asc* or *desc* option. This orders the entity in ascending or
descending order. 

Now that you know how to order your service entities, the last thing to do is to
define the finder methods for retrieving entity instances from the database. 

## Step 7: Defining Service Entity Finder Methods [](id=step-7-defining-service-entity-finder-methods)

Finder methods retrieve entity objects from the database based on specified
parameters. You'll probably want to create at least one finder method for each
entity you create in your services. Service Builder generates several methods
based on each finder you create for an entity. It creates methods to fetch,
find, remove, and count entity instances based on the finder's parameters. 

For many applications, it's important to be able to find its entities per site.
You can specify these finders using Liferay IDE's Overview mode of
`service.xml`. Select the *Finders* node under the entity node in the Outline on
the left side of the screen. The IDE displays an empty *Finders* table in the
main part of the view. Create a new finder by clicking the *Add* icon
(![Add](../../../images/icon-add-ide.png)) to the right of the table. Give your
finder a name and return type. Use the Java
camel-case naming convention when naming finders since the finder's name is used
to name the methods that Service Builder creates. The IDE creates a new
finder sub-node under the *Finders* node in the outline. Next, you'll learn how
to specify the finder column for this node. 

Under the new finder node, the IDE created a *Finder Columns* node. Select the
*Finder Columns* node to specify the columns for your finder's parameters.
Create a new finder column by clicking the *Add* icon and specifying the
column's name. Keep in mind that you can specify multiple parameters (columns)
for a finder.

![Figure 4: Creating Finder entities is easy with Liferay IDE.](../../../images/service-builder-finders.png)

If you're creating site-scoped entities (entities whose data should be unique to
each site), you should follow the steps described above to create finders by
`groupId` for retrieving your entities. Remember to save your `service.xml` file
after editing it to preserve the finders you define. 

When you run Service Builder, it generates finder-related methods
(e.g., `fetchByGroupId`, `findByGroupId`, `removeByGroupId`, `countByGroupId`) for the
your entities in the `*Persistence` and `*PersistenceImpl` classes.
The first of these classes is the interface; the second is its implementation.
For example, Liferay's Bookmarks application generates its entity finder methods
in the
`-Persistence` classes found in the
`/bookmarks-api/src/main/java/com/liferay/bookmarks/service/persistence`
folder and the `-PersistenceImpl` classes in the
`/bookmarks-service/src/main/java/com/liferay/bookmarks/service/persistence/impl`
folder.

Now you know to configure Service Builder to create finder methods for your
entity. Terrific!

Now that you've specified the service for your project, you're ready to *build*
the service by running Service Builder. To learn how to run Service Builder and
to learn about the code that Service Builder generates, please refer to the
[Running Service Builder and Understanding the Generated
Code](/develop/tutorials/-/knowledge_base/7-0/running-service-builder-and-understanding-the-generated-code)
tutorial. 

## Related Topics [](id=related-topics)

[What is Service Builder?](/develop/tutorials/-/knowledge_base/7-0/what-is-service-builder)

[Running Service Builder and Understanding the Generated Code](/develop/tutorials/-/knowledge_base/7-0/running-service-builder-and-understanding-the-generated-code)
