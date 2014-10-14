# Defining Your Object-Relational Map [](id=defining-your-object-relational-map)

In order to demonstrate how to use Service Builder, let's create an example
portlet project that Nose-ster, a fictitious organization, can use to schedule
social events. For our example, we'll create a new Liferay portlet project for
managing and listing these events. We'll define two entities: *events* and
*locations*. The event entity represents a social event that can be scheduled
for Nose-ster, while the location entity represents a location at which a social
event can take place. Since an event must have a location, the event entity will
reference a location entity as one of its attributes.

If you'd like to examine the finished example project, it's a part of our *Dev
Guide SDK* which you can browse at
[https://github.com/liferay/liferay-docs/tree/6.1.x/devGuide/code/devGuide-sdk](https://github.com/liferay/liferay-docs/tree/6.1.x/devGuide/code/devGuide-sdk). 
The project is in the SDK's
[portlets/event-listing-portlet](https://github.com/liferay/liferay-docs/tree/6.1.x/devGuide/code/devGuide-sdk/portlets/event-listing-portlet)
folder.

+$$$

**Note:** If you're looking for a fully-functional portlet application that can
manage events, please use Liferay's Calendar portlet instead. The example
described in this section is only intended to demonstrate how to use Service
Builder. The Calendar portlet provides many more features than the simple
example application described here. For information about the Calendar portlet,
please refer to the chapter on Liferay's collaboration suite in
[Using Liferay Portal](/discover/portal/-/knowledge_base/6-1/organizing-and-sharing-events-with-the-calendar).

$$$

Liferay portlet projects can contain multiple portlets. We'll create two
portlets in our Event Listing portlet project: the Event Listing portlet and the
Location Listing portlet. These portlets will allow users to add, edit, or
remove events or locations, display lists of events or locations, search for
particular events or locations, and view the details of individual events or
locations.

![Figure 4.1: The Event Listing portlet lets you add and modify Nose-ster social events. The portlet relies on its event and location entities and the service infrastructure that Liferay Service Builder builds around them.](../../images/service-builder-view-events.png)

We'll start by creating the Location Listng portlet and Event Listing portlet in
a new portlet plugin project called the Event Listing portlet project:

1.  Create the Event Listing portlet project in your Liferay Plugins SDK using
    Liferay IDE or Developer Studio, following the example in the [Creating New
    Liferay Projects](/develop/tutorials/-/knowledge_base/6-1/creating-new-liferay-projects)
    section. 

2.  Create the the Location Listng portlet and Event Listing portlet in the
    project you just created by following the example in the [Creating
    Plugins](/develop/tutorials/-/knowledge_base/6-1/creating-plugins) section. 

Now that you've finished creating the example project and portlets, expand your
project's `docroot/WEB-INF/src` folder and the
`com.nosester.portlet.eventlisting` package. Notice that Liferay IDE created the
`EventListingPortlet.java` and `LocationListingPortlet.java` files in this
package. We'll add some business logic to these portlet classes after using
Service Builder to create a service layer for our event and location entities.

The first step in using Service Builder is to define your model classes and
their attributes in a `service.xml` file in your project's `docroot/WEB-INF`
folder. In Service Builder terminology, your model classes (events and
locations) are called entities. We've kept the requirements for our event and
location entities fairly simple. Events should have the following attributes: 

**Event Attributes**

Attribute | Attribute Type | Attribute Description
:------------: | :------------: | :-------------------
`name` | String | The name of the event
`description` | String | A description of the event
`date` | Date | The date and time the event takes place
`locationId` | long | An event takes place at a location and we use a location Id to specify the location

Locations should have the following attributes: 

**Location Attributes**

Attribute | Attribute Type | Attribute Description
:------------: | :------------: | :-------------------
`name` | String | The name of the location
`description` | String | A description of the location
`streetAddress` | String | The street address of the location
`city` | String | The city of the location
`stateOrProvince` | String | The state or province of the location
`country` | String | The country of the location

Service Builder defines a single file called `service.xml` for describing
entities. Once you create the file, you can then define your entities. We'll
walk you through the whole process for the entities we've defined above, using
Liferay IDE, which makes it easy. It'll only take seven steps to do it: 

1.  Create the `service.xml` file in your project's `docroot/WEB-INF` folder.

2.  Define global information for the service.

3.  Define service entities. 

4.  Define the columns (attributes) for each service entity.

5.  Define relationships between entities.

6.  Define a default order for the entity instances to be retrieved from the
    database. 

7.  Define finder methods that retrieve objects from the database based on
    specified parameters.

Let's start creating our service by using Liferay IDE to create your
`service.xml` file. 

## Create the service.xml File [](id=create-the-service-xml-file)

To define a service for your portlet project, you must create a `service.xml`
file. The DTD (Document Type Declaration) file
[http://www.liferay.com/dtd/liferay-service-builder_6_1_0.dtd](http://www.liferay.com/dtd/liferay-service-builder_6_1_0.dtd)
specifies the format and requirements of the XML to use. You can create your
`service.xml` file manually, following the DTD, or you can use Liferay IDE.
Liferay IDE helps you build the `service.xml` file piece-by-piece, taking the
guesswork out of creating XML that adheres to the DTD. For our tutorial, we'll
use Liferay IDE to build the `service.xml` file.

To create the `service.xml` file using Liferay IDE, select your
`event-listing-portlet` project in the Package Explorer and then select *File*
&rarr; *New* &rarr; *Liferay Service Builder*. Liferay IDE creates a
`service.xml` file in your `docroot/WEB-INF/src` folder and displays the file in
*Overview* mode.

Liferay IDE also provides a *Diagram* mode and a *Source* mode to give you
different perspectives of the service information in your `service.xml` file.
Diagram mode is helpful for creating and visualizing relationships between
service entities. Source mode brings up the `service.xml` file's raw XML content
in the editor. You can switch between these modes as you wish. Since *Overview*
mode facilitates creating service elements, we'll use it while creating our
service.

Let's start filling out the global information for our service. 

## Define Global Service Information [](id=define-global-service-information)

A service's global information applies to all of its entities, so let's specify
this information first. Select the *Service Builder* node in the upper left
corner of the Overview mode of your `service.xml` file. The main section of the
view now shows the Service Builder form in which we can enter our service's
global information. The fields include the service's package path, author, and
namespace options. Here are the values we'll use for our example service:

- **Package path:** *com.nosester.portlet.eventlisting*
- **Auto namespace tables:** *no*
- **Author:** [your name]
- **Namespace:** *Event*

The *package path* specifies the package in which the service and persistence
classes are generated. The package path we defined above ensures that the
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
to the database table names. Since our namespace value is `Event`, the names of
the database tables created for our entities start with `Event_` as their
prefix. The namespace for each Service Builder project must be unique. Separate
plugins should use separate namespaces and should not use a namespace already
used by Liferay (such as `Users` or `Groups`). Check the table names in
Liferay's database if you're wondering which namespaces are already in use.

As the last piece of global information, enter your name as the service's
*author* in your `service.xml` file. Service Builder adds `@author` annotations
with the specified name to all of the generated Java classes and interfaces.
Save your `service.xml` file to preserve the information you added. Next, we'll
add entities for your service's events and locations. 

## Define Service Entities [](id=define-service-entities)

Entities are the heart and soul of a service. Entities represent the map between
the model objects in Java and the fields and tables in your database. Once your
entities are defined, Service Builder handles the mapping automatically, giving
you a facility for taking Java objects and persisting them. For this example,
you'll create two entities--one for events and one for locations. 

Here's a summary of the information we'll enter for the Event entity:

- **Name:** *Event*
- **Local service:** *yes*
- **Remote service:** *yes* 

And here's what we'll enter for the Location entity:

- **Name:** *Location*
- **Local service:** *yes*
- **Remote service:** *yes* 

To create these entities, select the *Entities* node under the Service
Builder node in the outline on the left side of the `service.xml` editor in
Overview mode. In the main part of the view, notice that the Entities table is
empty. Create an entity by clicking on the *Add Entity* icon (a green plus sign)
to the right of the table. Enter *Event* for your entity's name and select both
the *Local Service* and the *Remote Service* options. Create a second entity
named *Location* and select the *Local Service* and the *Remote Service*
options for it too. 

![Figure 4.2: Adding service entities is easy with Liferay IDE's *Overview* mode of your `service.xml` file.](../../images/service-add-entity.png)

An entity's name is used to name the database table for persisting instances
of the entity. The actual name of the database table is prefixed with the
namespace; for our example, we'll have one database table named `Event_Event`
and another named `Event_Location`. 

Setting the *local service* attribute to `true` instructs Service Builder to
generate local interfaces for our entity's service. The default value for
local service is `false`. The design of this portlet, however, dictates that we
be able to call the service, and it resides in our portlet's `.war` file. Our
portlet will be deployed to our Liferay server. So the service will be local
from our Liferay server's point of view.

Setting the *remote service* attribute to `true` instructs Service Builder to
generate remote interfaces for the service. The default value for remote service
is `true`. We could build a fully-functional event listing application without
generating remote services, so we could set local service to `true` and remote
service to `false` for both of our entities. Since, however, we want to
demonstrate how to use the web services that Service Builder generates for our
entities, we'll set both local service and remote service to `true`.

+$$$

**Tip:** Suppose you have an existing DAO service for an entity built using some
other framework such as JPA. You can set local service to `false` and remote
service to `true` so that the methods of your remote `-Impl` class can call the
methods of your existing DAO. This enables your entity to integrate with
Liferay's permission-checking system and provides access to the web service APIs
generated by Service Builder. This is a very handy, quite powerful, and often
used feature of Liferay. 

$$$

<!-- What was the impetus for mentioning JingDAO? Since I'd never heard of it, I
looked it up, and its web site hasn't been updated since 2004. Its wiki is gone.
I think, therefore, that it's a "dead" project, and we shouldn't mention it.
AFAIK, there are now only two Java persistence frameworks worth mentioning: JPA
and Hibernate. I did, however, find a page full of them, but several of them
were dead (iBatis, OJB, etc.). Here's the list:
http://java-source.net/open-source/persistence 
-Rich -->

Now that we've created our Event and Location entities, let's describe their
attributes using entity *columns*. 

## Defining the Columns (Attributes) for Each Service Entity [](id=defining-the-columns-attributes-for-each-service-entity)

Each entity is described by its columns, which represent an entity's attributes.
These attributes map on the one side to fields in a table and on the other side
to attributes of a Java object. To add attributes for the Event entity, you
need to drill down to its columns in the Overview mode outline of the
`service.xml` file. From the outline, expand the *Entities* node and expand the
new *Event* entity node. Then select the *Columns* node. Liferay IDE displays a
table of the Event entity's columns. 

Service Builder creates a database field for each column we add to the
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
represents the entire primary key for the entity. This is the case in our
example. However, it's possible to use multiple columns as the primary key for
an entity. In this case, the combination of columns makes up a compound primary
key for the entity.

Similar to the way you used the form table for adding entities, add attribute
columns for the entities as follows:

**Event attribute columns**

  Name        | Type   | Primary
:-----------: | :----: | :------:
`eventId`     | long   | yes
`name`        | String | no
`description` | String | no
`date`        | Date   | no
`locationId`  | long   | no

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

Create each attribute by clicking the *add* icon. Then fill in the name of the
attribute, select its type, and specify whether it is a primary key for the
entity. While your cursor is in a column's *Type* field, an option icon
appears. Click this icon to select the appropriate type for the column. Create a
column for each attribute of your Event entity. Repeat the steps to create
columns for each attribute of your Location entity. 

In addition to columns for your entity's primary key and attributes, we
recommend including columns for site ID and portal instance ID. They allow your
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

Lastly, add columns to help audit both of the Event and Location entities. Add
a column named `createDate` of type `Date` to note the date an entity instance
was created. And add a column named `modifiedDate` of type `Date` to track the
last time an entity instance was modified.

**Audit columns**

  Name         | Type   | Primary
:------------: | :----: | :------:
`createDate`   | Date   | no
`modifiedDate` | Date   | no

Great! Our entities are set with the columns that not only represent their
attributes, but also support multi-tenancy and entity auditing. Next, we'll
specify the relationship between our Event entity and Location entity. 

## Defining Relationships Between Service Entities [](id=defining-relationships-between-service-entities)

Often you'll want to reference one type of entity in the context of another
entity. That is, you'll want to *relate* the entities. We'll show you how to do
this in our example Event Listing portlet project. 

As we mentioned earlier for the example, each event must have a location.
Therefore, each Event entity must relate to a Location entity. The good news is
that Liferay IDE's Diagram mode for `service.xml` makes relating entities easy.
First, select Diagram mode for the `service.xml` file. Then select the
*Relationship* option under *Connections* in the palette on the right side of
the view. This relationship tool helps you draw relationships between entities
in the diagram. Click the *Event* entity and move your cursor over the Location
entity. Liferay IDE draws a dashed line from the Event entity to the cursor.
Click the *Location* entity to complete drawing the relationship. Liferay IDE
turns the dashed line into a solid line, with an arrow pointing to the Location
entity. Save the `service.xml` file. 

Congratulations! You've related the entities. Their relationship should show
in Diagram mode and look similar to that of the figure below. 

![Figure 4.3: Relating entities is a snap in Liferay IDE's *Diagram* mode for `service.xml`.](../../images/service-builder-relate-entities.png)

Switch to *Source* mode in the editor for your `service.xml` file and note that
Liferay IDE created a column element to hold the ID of the Location entity
instance related to the Event:

     <column name="locationId" type="long"></column>

Now that our entity columns are in place, let's specify the default order in
which the entity instances are retrieved from the database. 

## Defining Ordering of Service Entity Instances [](id=defining-ordering-of-service-entity-instances)

Often, you want to retrieve multiple instances of a given entity and list them
in a particular order. Liferay lets you specify the default order of the
entities in your `service.xml` file. 

Say you want to return Event entities in order by date, earliest to latest, and
you want to return Location entities alphabetically by name. It's easy to
specify these default orderings using Liferay IDE. Switch back to *Overview*
mode in the editor for your `service.xml` file. Then select the *Order* node
under the Event entity node in the outline on the left side of the view. The IDE
displays a form for ordering the Event entity. Select the *Specify ordering*
checkbox to show the form for specifying the ordering. Create an order column by
clicking the *add icon* (a green plus sign) to the right of the table. Enter
*date* for the column name to use in ordering the Event entity. Click the
*Browse icon* to the right of the *By* field and choose the *asc* option. This
orders the Event entity by ascending date. To specify ordering for Location
entity instances, follow similar steps but specify *name* as the column and
*asc* as the *select by* value. 

The last thing do is define the finder methods for retrieving their instances
from the database. 

## Defining Service Entity Finder Methods [](id=defining-service-entity-finder-methods)

Finder methods retrieve entity objects from the database based on specified
parameters. You'll probably want to create at least one finder method for each
entity you create in your services. Service Builder generates several methods
based on each finder you create for an entity. It creates methods to fetch,
find, remove, and count entity instances based on the finder's parameters. 

For our example, we want to find Event and Location entities per site. We'll
specify these finders using Liferay IDE's Overview mode of `service.xml`. Select
the *Finders* node under the Event entity node in the outline on the left side
of the screen. The IDE displays an empty *Finders* table in the main part of the
view. Create a new finder by clicking the *add icon* (a green plus sign) to the
right of the table. Name the finder *GroupId* and enter *Collection* as its
return type. We use the Java camel-case naming convention in naming finders
since the finder's name is used to name the methods Service Builder creates. The
IDE creates a new *GroupId* node under the *Finders* node in the outline. We'll
specify the finder column for this group ID node next. 

Under the new *GroupId* node, the IDE created a *Finder Columns* node. Select
*Finder Columns* node to specify the columns for our finder's parameters. Create
a new finder column by clicking the *add icon* (a green plus sign) and
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
for the Event Listing portlet project.

We've made the source code for the service and the entire Event Listing portlet
project available in the *Dev Guide SDK* which you can browse at
[https://github.com/liferay/liferay-docs/tree/6.1.x/devGuide/code/devGuide-sdk](https://github.com/liferay/liferay-docs/tree/6.1.x/devGuide/code/devGuide-sdk). 
The project is in the SDK's
[portlets/event-listing-portlet](https://github.com/liferay/liferay-docs/tree/6.1.x/devGuide/code/devGuide-sdk/portlets/event-listing-portlet).
folder.

We've also listed the `service.xml` content here for your convenience. We've
added some comments to highlight the service's various elements. Other than
that, your `service.xml` file's contents should look similar to this: 

    <?xml version="1.0" encoding="UTF-8"?>
    <!DOCTYPE service-builder PUBLIC "-//Liferay//DTD Service Builder 6.1.0//EN"
    "http://www.liferay.com/dtd/liferay-service-builder_6_1_0.dtd">
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
Should we include the entity element's uuid attribute? The
sample-service-builder-portlet-master project includes it. - Jim
-->

<!-- Yes, because it's required by various Liferay APIs. -Rich -->

Now that you've specified the service for the Event Listing portlet project,
let's *build* the service by running Service Builder. Then we'll look at the
code Service Builder generates.
