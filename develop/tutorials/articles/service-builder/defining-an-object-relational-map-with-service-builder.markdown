# Defining an Object-Relational Map with Service Builder

In this tutorial, you'll learn how to define an object relational map for your
application so that your application can persist data. The example code in this
tutorial, as well as the example code in the other Service Builder and Services
tutorials, comes from the Event Listing project, which you can find on Github
here:
[https://github.com/liferay/liferay-docs/tree/master/develop/tutorials/code/svc-build/event-listing-portlet](https://github.com/liferay/liferay-docs/tree/master/develop/tutorials/code/svc-build/event-listing-portlet).
The Event Listing project is an example portlet project that an organization can
use to schedule social events. The event-listing-portlet project allows
administrators to manage and list these events. The project defines two
entities, or model types, to represent an organization's events and the
locations at which the events can take place. These entities are called *events*
and *locations*. The event entity represents a social event that can be
scheduled, while the location entity represents a location at which a social
event can take place. Since an event must have a location, the event entity
references a location entity as one of its attributes. 

If you want to display entity data in a Liferay application, you're free to
create any kind of user interface that you can imagine. The following image
shows a simple example. To learn how to create simple user interfaces for
Liferay Service Builder applications, please see the
[Implementing a UI with Liferay Taglibs](https://dev.liferay.com/develop/learning-paths/-/knowledge_base/6-2/implementing-a-ui-with-liferay-taglibs)
learning path.

![Figure 1: The Event Listing and Location Listing portlets let you add and modify social events and locations. The portlets rely on the event and location entities and the service infrastructure that Liferay Service Builder builds around them.](../../images/service-builder-view-events.png)

+$$$

**Note:** If you're looking for a fully-functional portlet application that can
manage events, please use Liferay's Calendar portlet instead. The example
described in this section is only intended to demonstrate how to use Service
Builder. The Calendar portlet provides many more features than the simple
example application described here. For information about the Calendar portlet,
please refer to the chapter on Liferay's collaboration suite here:
[https://dev.liferay.com/discover/portal/-/knowledge_base/6-2/managing-events-and-calendar-resources-with-liferays-c](https://dev.liferay.com/discover/portal/-/knowledge_base/6-2/managing-events-and-calendar-resources-with-liferays-c).

$$$

As with any portlet project, the event-listing-portlet project's Java sources
reside in the `docroot/WEB-INF/src` folder. Notice the
`EventListingPortlet.java` and `LocationListingPortlet.java` files in the
`com.nostester.portlet.eventlisting` package. These portlet classes extend
Liferay's `MVCPortlet` class. They act as the controllers in the MVC pattern.
These classes contain the business logic that invokes the Service Builder
generated event and location services that you'll learn how to create in this
section. The application's view layer is implemented in the JSPs in the
`docroot/html` folder.

The first step in using Service Builder is to define your model classes and
their attributes in a `service.xml` file in your project's `docroot/WEB-INF`
folder. In Service Builder terminology, your model classes (events and
locations) are called entities. The requirements for the event and location
entities are fairly simple. Events should have the following attributes: 

**Event Attributes** (Example)

Attribute | Attribute Type | Attribute Description
:------------: | :------------: | :-------------------
`name` | String | The name of the event
`description` | String | A description of the event
`date` | Date | The date and time the event takes place
`locationId` | long | An event takes place at a location and this location ID specifies the event's location

Locations should have the following attributes: 

**Location Attributes** (Example)

Attribute | Attribute Type | Attribute Description
:------------: | :------------: | :-------------------
`name` | String | The name of the location
`description` | String | A description of the location
`streetAddress` | String | The street address of the location
`city` | String | The city of the location
`stateOrProvince` | String | The state or province of the location
`country` | String | The country of the location

Service Builder reads a single file called `service.xml` that's used for
defining entities. Once you create the file, you can then define your entities.
Liferay IDE makes it very easy to define entities in your application's
`service.xml` file. To define a custom entity, follow these steps: 

1.  Create the `service.xml` file in your project's `docroot/WEB-INF` folder, if
    one does not already exist there. 

2.  Define global information for the service.

3.  Define service entities. 

4.  Define the columns (attributes) for each service entity.

5.  Define relationships between entities.

6.  Define a default order for the entity instances to be retrieved from the
    database. 

7.  Define finder methods that retrieve objects from the database based on
    specified parameters.

Let's examine these steps in detail, starting with creating a `service.xml`
file.

## Creating the `service.xml` File

To define a service for your portlet project, you must create a `service.xml`
file. The DTD (Document Type Declaration) file
[http://www.liferay.com/dtd/liferay-service-builder_6_2_0.dtd](http://www.liferay.com/dtd/liferay-service-builder_6_2_0.dtd)
specifies the format and requirements of the XML to use. You can create your
`service.xml` file manually, following the DTD, or you can use Liferay IDE.
Liferay IDE helps you build the `service.xml` file piece-by-piece, taking the
guesswork out of creating XML that adheres to the DTD.

If a default `service.xml` file already exists in your `docroot/WEB-INF/src`
folder, check to see if it has an `<entity />` element named "Foo". If it has
the Foo entity, remove the entire `<entity name="Foo" ...> ... </entity>`
element. The Liferay IDE project wizard creates the Foo entity as an example. It
has no practical use for you. 

If you don't already have a `service.xml` file, it's easy to create one using
Liferay IDE. Simply select your `event-listing-portlet` project in the Package
Explorer and then select *File* &rarr; *New* &rarr; *Liferay Service Builder*.
Liferay IDE creates a `service.xml` file in your `docroot/WEB-INF/src` folder
and displays the file in *Overview* mode.

Liferay IDE also provides a *Diagram* mode and a *Source* mode to give you
different perspectives of the service information in your `service.xml` file.
Diagram mode is helpful for creating and visualizing relationships between
service entities. Source mode brings up the `service.xml` file's raw XML content
in the editor. You can switch between these modes as you wish.

Next, you can start filling out the global information for your service. 

## Defining Global Service Information

A service's global information applies to all of its entities, so it's a good
place to start. In Liferay IDE, select the *Service Builder* node in the upper
left corner of the Overview mode of your `service.xml` file. The main section of
the view now shows the Service Builder form in which you can enter your
service's global information. The fields include the service's package path,
author, and namespace options. Here are the values used for the event-listing
project's service:

- **Package path:** *com.nosester.portlet.eventlisting*
- **Auto namespace tables:** *no*
- **Author:** [your name]
- **Namespace:** *Event*

The *package path* specifies the package in which the service and persistence
classes are generated. The package path defined above ensures that the
service classes are generated in the `com.nosester.portlet.eventlisting`
package under the `docroot/WEB-INF/service` folder. The persistence classes are
generated in a package of that name under the `docroot/WEB-INF/src` folder. The
complete file paths for the service and persistence classes are 
`docroot/WEB-INF/service/com/nosester/portlet/eventlisting` and
`docroot/WEB-INF/src/com/nosester/portlet/eventlisting`, respectively. Please
refer to next section, *Generating the Services*, for a description of the
contents of these packages. 

Service Builder uses the service *namespace* in naming the database tables it
generates for the service. Enter *Event* as the namespace for your example
service. Service Builder uses the namespace in the following SQL scripts it
generates in your `docroot/WEB-INF/sql` folder: 

- `indexes.sql`
- `sequences.sql`
- `tables.sql`

Liferay Portal uses these scripts to create database tables for all the entities
defined in the `service.xml` file. Service Builder prepends the namespace
to the database table names. Since the namespace value above is `Event`, the
names of the database tables created for the entities start with `Event_` as
their prefix. The namespace for each Service Builder project must be unique.
Separate plugins should use separate namespaces and should not use a namespace
already used by Liferay (such as `Users` or `Groups`). Check the table names in
Liferay's database if you're wondering which namespaces are already in use.

As the last piece of global information, enter your name as the service's
*author* in your `service.xml` file. Service Builder adds `@author` annotations
with the specified name to all of the generated Java classes and interfaces.
Save your `service.xml` file to preserve the information you added. Next, you'll
add entities for your service's events and locations. 

## Defining Service Entities

Entities are the heart and soul of a service. Entities represent the map between
the model objects in Java and the fields and tables in your database. Once your
entities are defined, Service Builder handles the mapping automatically, giving
you a facility for taking Java objects and persisting them. For this example,
you'll create two entities--one for events and one for locations. 

Here's a summary of the information you'll enter for the Event entity:

- **Name:** *Event*
- **Local service:** *yes*
- **Remote service:** *yes* 

And here's what you'll enter for the Location entity:

- **Name:** *Location*
- **Local service:** *yes*
- **Remote service:** *yes* 

To create these entities using Liferay IDE, select the *Entities* node under the
Service Builder node in the outline on the left side of the `service.xml` editor
in Overview mode. In the main part of the view, notice that the Entities table
is empty. Create an entity by clicking on the *Add Entity* icon (a green plus
sign) to the right of the table. Enter *Event* for your entity's name and select
both the *Local Service* and the *Remote Service* options. Create a second
entity named *Location* and select the *Local Service* and the *Remote Service*
options for it too. 

![Figure 2: Adding service entities is easy with Liferay IDE's *Overview* mode of your `service.xml` file.](../../images/service-add-entity.png)

An entity's name is used to name the database table for persisting instances
of the entity. The actual name of the database table is prefixed with the
namespace; the Event Listing example creates one database table named
`Event_Event` and another named `Event_Location`. 

Setting the *local service* attribute to `true` instructs Service Builder to
generate local interfaces for the entity's services. The default value for local
service is `false`. Local services can only be invoked from the Liferay server
on which they're deployed. The Event Listing portlet will be deployed to your
Liferay server. So the service will be local from your Liferay server's point of
view.

Setting the *remote service* attribute to `true` instructs Service Builder to
generate remote interfaces for the service. The default value for remote service
is `true`. You could build a fully-functional event listing application without
generating remote services. In that case, you could set local service to `true`
and remote service to `false` for both of your entities. If, however, you want
to enable (secure) remote access to your application's services, you should set
both local service and remote service to `true`.

+$$$

**Tip:** Suppose you have an existing DAO service for an entity built using some
other framework such as JPA. You can set local service to `false` and remote
service to `true` so that the methods of your remote `-Impl` class can call the
methods of your existing DAO. This enables your entity to integrate with
Liferay's permission-checking system and provides access to the web service APIs
generated by Service Builder. This is a very handy, quite powerful, and often
used feature of Liferay. 

$$$

Now that you've seen how to create the Event and Location entities, you'll learn
how to describe their attributes using entity *columns*. 

## Defining the Columns (Attributes) for Each Service Entity

Each entity is described by its columns, which represent an entity's attributes.
These attributes map on the one side to fields in a table and on the other side
to attributes of a Java object. To add attributes for the Event entity, you
need to drill down to its columns in the Overview mode outline of the
`service.xml` file. From the outline, expand the *Entities* node and expand the
new *Event* entity node. Then select the *Columns* node. Liferay IDE displays a
table of the Event entity's columns. 

Service Builder creates a database field for each column you add to the
`service.xml` file. It maps a database field type appropriate to the Java type
specified for each column, and it does this across all the databases Liferay
supports. Once Service Builder runs, it generates a Hibernate
configuration that handles the object-relational mapping. Service Builder
automatically generates accessor methods in the model class for these
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
columns for the entities as follows:

**Event attribute columns**

  Name        | Type   | Primary
:-----------: | :----: | :------:
`eventId`     | long   | yes
`name`        | String | no
`description` | String | no
`date`        | Date   | no

**Location attribute columns**

  Name            | Type   | Primary
:---------------: | :----: | :------:
`locationId`      | long   | yes
`name`            | String | no
`description`     | String | no
`streetAddress  ` | String | no
`city`            | String | no
`stateOrProvince` | String | no
`country`         | String | no

Create each attribute by clicking on the *add* icon. Then fill in the name of
the attribute, select its type, and specify whether it is a primary key for the
entity. While your cursor is in a column's *Type* field, an option icon appears.
Click this icon to select the appropriate type for the column. Create a column
for each attribute of your Event entity. Repeat the steps to create columns for
each attribute of your Location entity. 

In addition to columns for your entity's primary key and attributes, it's
recommended to add columns for portal instance ID and site ID. They allow your
portlet to support the multi-tenancy features of Liferay, so that each portal
instance and each site in a portal instance can have independent sets of portlet
data. To hold the site's ID, add a column called `groupId` of type `long`. To
hold the portal instance's ID, add a column called `companyId` of type `long`.
Add both of these columns to your Event and Location entities.

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

Lastly, add columns to help audit both of the Event and Location entities. Add
a column named `createDate` of type `Date` to note the date an entity instance
was created. And add a column named `modifiedDate` of type `Date` to track the
last time an entity instance was modified.

**Audit columns**

  Name         | Type   | Primary
:------------: | :----: | :------:
`userId`       | long   | no
`createDate`   | Date   | no
`modifiedDate` | Date   | no

Great! Your entities are set with the columns that not only represent their
attributes, but also support multi-tenancy and entity auditing. Next, you'll
specify the relationship between the Event entity and the Location entity. 

## Defining Relationships Between Service Entities

Often you'll want to reference one type of entity in the context of another
entity. That is, you'll want to *relate* the entities. We'll show you how to do
this in the Event Listing project example. 

As mentioned earlier, each event must have a location. Therefore, each Event
entity must relate to a Location entity. The good news is that Liferay IDE's
Diagram mode for `service.xml` makes relating entities easy. First, select
Diagram mode for the `service.xml` file. Then select the *Relationship* option
under *Connections* in the palette on the right side of the view. This
relationship tool helps you draw relationships between entities in the diagram.
Click the *Event* entity and move your cursor over the Location entity. Liferay
IDE draws a dashed line from the Event entity to the cursor. Click the
*Location* entity to complete drawing the relationship. Liferay IDE turns the
dashed line into a solid line, with an arrow pointing to the Location entity.
Save the `service.xml` file. 

Congratulations! You've related the entities. Their relationship should show
in Diagram mode and look similar to that of the figure below. 

![Figure 3: Relating entities is a snap in Liferay IDE's *Diagram* mode for `service.xml`.](../../images/service-builder-relate-entities.png)

Switch to *Source* mode in the editor for your `service.xml` file and note that
Liferay IDE created a column element in the Event entity to hold the ID of the
Location entity instance reference:

     <column name="locationId" type="long"></column>

Now that your entity columns are in place, you can specify the default order in
which the entity instances are retrieved from the database. 

## Defining Ordering of Service Entity Instances

Often, you want to retrieve multiple instances of a given entity and list them
in a particular order. Liferay lets you specify the default order of the
entities in your `service.xml` file. 

Suppose you want to return Event entities in order by date, earliest to latest,
and you want to return Location entities alphabetically by name. It's easy to
specify these default orderings using Liferay IDE. Switch back to *Overview*
mode in the editor for your `service.xml` file. Then select the *Order* node
under the Event entity node in the outline on the left side of the view. The IDE
displays a form for ordering the Event entity. Check the *Specify ordering*
checkbox to show the form for specifying the ordering. Create an order column by
clicking the *add icon* (a green plus sign) to the right of the table. Enter
*date* for the column name to use in ordering the Event entity. Click the
*Browse icon* to the right of the *By* field and choose the *asc* option. This
orders the Event entity by ascending date. To specify ordering for Location
entity instances, follow similar steps but specify *name* as the column and
*asc* as the *select by* value. 

The last thing to do is to define the finder methods for retrieving entity
instances from the database. 

## Defining Service Entity Finder Methods

Finder methods retrieve entity objects from the database based on specified
parameters. You'll probably want to create at least one finder method for each
entity you create in your services. Service Builder generates several methods
based on each finder you create for an entity. It creates methods to fetch,
find, remove, and count entity instances based on the finder's parameters. 

For the Event Listing example, it's important to be able to find Event and
Location entities per site. You can specify these finders using Liferay IDE's
Overview mode of `service.xml`. Select the *Finders* node under the Event entity
node in the outline on the left side of the screen. The IDE displays an empty
*Finders* table in the main part of the view. Create a new finder by clicking
the *add icon* (a green plus sign) to the right of the table. Name the finder
*GroupId* and enter *Collection* as its return type. Use the Java camel-case
naming convention when naming finders since the finder's name is used to name
the methods that Service Builder creates. The IDE creates a new *GroupId* node
under the *Finders* node in the outline. Next, you'll specify the finder column
for this group ID node. 

Under the new *GroupId* node, the IDE created a *Finder Columns* node. Select
the *Finder Columns* node to specify the columns for your finder's parameters.
Create a new finder column by clicking the *add icon* (a green plus sign) and
specifying *groupId* as the column's name. Keep in mind that you can specify
multiple parameters (columns) for a finder; this first example is kept simple.
Follow similar steps to create a finder to retrieve Location entities by
`groupId`. Save the `service.xml` file to preserve the finders you defined. 

When you run Service Builder, it generates finder-related methods
(`fetchByGroupId`, `findByGroupId`, `removeByGroupId`, `countByGroupId`) for the
Event and Location entities in `-Persistence` and `-PersistenceImpl` classes.
The first of these classes is the interface; the second is its implementation.
The Event and Location entity's finder methods are generated in the
`-Persistence` classes found in your
`/docroot/WEB-INF/service/com/nosester/portlet/eventlisting/service/persistence`
folder and the `-PersistenceImpl` classes found in your
`/docroot/WEB-INF/src/com/nosester/portlet/eventlisting/service/persistence`
folder.

Terrific! You've created the example service and its Event and Location entities
for the Event Listing example project. For your convenience, you can view the
complete `service.xml` content here. We've added some comments to highlight the
service's various elements. Other than that, your `service.xml` file's contents
should look similar to this: 

    <?xml version="1.0" encoding="UTF-8"?>
    <!DOCTYPE service-builder PUBLIC "-//Liferay//DTD Service Builder 6.2.0//EN"
    "http://www.liferay.com/dtd/liferay-service-builder_6_2_0.dtd">
    <service-builder package-path="com.nosester.portlet.eventlisting">
        <author>Joe Bloggs</author>
        <namespace>Event</namespace>

        <entity name="Event" local-service="true" remote-service="true">

            <!-- PK fields -->

            <column name="eventId" type="long" primary="true" />

            <!-- Audit fields -->

            <column name="companyId" type="long" />
            <column name="groupId" type="long" />
            <column name="userId" type="long" />
            <column name="createDate" type="Date" />
            <column name="modifiedDate" type="Date" />

            <!-- Other fields -->

            <column name="name" type="String" />
            <column name="description" type="String" />
            <column name="date" type="Date" />
            <column name="locationId" type="long" />

            <!-- Order -->

            <order by="asc">
                    <order-column name="date" />
            </order>

            <!-- Finder methods -->

            <finder name="GroupId" return-type="Collection">
                    <finder-column name="groupId" />
            </finder>
        </entity>

        <entity name="Location" local-service="true" remote-service="true">

            <!-- PK fields -->

            <column name="locationId" type="long" primary="true" />

            <!-- Audit fields -->

            <column name="companyId" type="long" />
            <column name="groupId" type="long" />
            <column name="userId" type="long" />
            <column name="createDate" type="Date" />
            <column name="modifiedDate" type="Date" />

            <!-- Other fields -->

            <column name="name" type="String" />
            <column name="description" type="String" />
            <column name="streetAddress" type="String" />
            <column name="city" type="String" />
            <column name="stateOrProvince" type="String" />
            <column name="country" type="String" />
            
            <!-- Order -->

            <order by="asc">
                    <order-column name="name" />
            </order>

            <!-- Finder methods -->

            <finder name="GroupId" return-type="Collection">
                    <finder-column name="groupId" />
            </finder>
        </entity>
    </service-builder>

<!--
We should include the entity element's uuid attribute here and in the SDK
project. See that the sample-service-builder-portlet-master project includes it.
Also, we should describe the uuid and the other audit fields - Jim
-->

Remember that you can view the complete Event Listing portlet project on Github
here: [https://github.com/liferay/liferay-docs/tree/master/develop/tutorials/code/svc-build/event-listing-portlet](https://github.com/liferay/liferay-docs/tree/master/develop/tutorials/code/svc-build/event-listing-portlet).

Now that you've specified the service for the Event Listing example project,
you're ready to *build* the service by running Service Builder. To learn how to
run Service Builder and to learn about the code that Service Builder generates,
please refer to the next tutorial.
