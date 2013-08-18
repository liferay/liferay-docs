# Generating Your Service Layer 

The word *service* can mean many specific things, but the general dictionary
definition states that it's "an act of helpful activity." Everyone has
experienced this in some way. Whether it's an act of kindness from a
friend or stranger or a service you pay for, in all instances, you have a need,
and the service provides for that need. 

Data-driven applications by their nature need access to a service for storing
and retrieving their data. In a well-designed application, the application asks
for data, and the service fetches it. The application can then display this data
to the user, who reads it or modifies it. If the data is modified, the
application passes it back to the service, and the service stores it. The
application doesn't need to know anything about *how* the service does what it
does. It trusts the service to handle storing and retrieving the data, freeing
the application to be as robust as it needs to be. 

This is what is called *loose coupling*, and it's a hallmark of good application
design. If your application's service layer is self-contained, then you can
swap it out for a better service layer when something more robust comes along,
and you won't have to modify the application to take advantage of it. 

Well, something more robust has come along, and it's called Service Builder.
Using the Object-Relational Mapping engine provided by Hibernate along with a
sophisticated code generator, Service Builder can help you implement your
service layer in a fraction of the time it would normally take. But this isn't
just any ordinary service layer: Service Builder also optionally helps you with
remote services in multiple protocols, such as JSON and SOAP. And if you need to
do something really funky with the database, it gets out of your way and lets
you use whatever SQL queries you want. 

Intrigued? We hope so. We'll cover the following topics: 

- What is Service Builder? 
- Defining Your Object-Relational Map
- Writing the Local Service Class
- Calling Local Services
- Using Model Hints
- Enabling Remote Services
- Developing Custom SQL Queries
- Configuring `service.properties`

As you can see, there is a lot to cover, so let's start by describing Service
Builder in more detail. 

## What is Service Builder?

<!-- As I read through this essay, I wanted to take the role of a skeptical
developer who needs to be convinced to use Service Builder over his current
persistence framework of choice. He's interested enough to read our essay, but
needs be convinced by the essay to switch from what he's currently doing over to
Service Builder. Below, I'll mark arguments that I find uncompelling as well as
any other material that I think may tend to turn our skeptical developer away
from Service Builder. -Rich --> 

<!--
Outline:

- Introduction: What is Service Builder? Why should I (a Liferay developer) use
  it? At a basic level, SB is a code generator.
- Service Builder projects integrate well with Liferay since Liferay's services
  were themselves generated with Service Builder. Service Builder's use in
  Liferay and Liferay plugins demonstates it to be a reliable and robust tool.
- Service Builder creates a clean separation of concerns by generating distinct
  service, model, and persistence layers. Can also generate distinct local and
  remote services.
- SB uses Hibernate for ORM and Spring for DI and AOP (DB transaction
  management) but hides the complexities of using these tools. So developers
  benefit from these technologies without having to configure them.
- Service Builder provides projects with built-in entity caching support.
- Service Builder can generate both simple finders and finders that take Liferay
  permissions into account: filterFindBy and filterCountBy methods.
- Service Builder is database-agnostic: no need to write SQL for different
  databases.
- Also, Service Builder doesn't restrict the SQL queries you can
  make. Service Builder supports both Custom SQL queries and Dynamic Queries.
- Conclusion: All of the above are ways that Service Builder helps developers
  save time, both initial development time and time for maintaining, updating,
  or extending code (just create service.xml, run SB, and add custom methods to
  *LocalServiceImpl, *ServiceImpl, and *ModelImpl).
-->

Service Builder is a model-driven code generation tool built by Liferay that
allows developers to define custom object models called entities. Service
Builder generates a service layer through object-relational mapping (ORM)
technology that provides a clean separation between your object model and code
for the underlying database. This frees you to add the necessary business logic
for your application. Service Builder takes an XML file as input and generates
the necessary model, persistence, and service layers for your application. These
layers provide a clean separation of concerns. Service Builder generates most of
the common code needed to implement create, read, update, delete, and find
operations on the database, allowing you to focus on the higher level aspects of
service design. In this section, we'll discuss some of the main benefits of
using Service Builder:

- Integration with Liferay
- Automatically generated model, persistence, and service layers
- Automatically generated local and remote services
- Automatically generated Hibernate and Spring configurations
- Support for generating finder methods for entities and finder methods that
  account for permissions
- Built-in entity caching support
- Support for custom SQL queries and dynamic queries
- Saved development time

Liferay uses Service Builder to generate all of its internal database
persistence code. In fact, all of Liferay's services, both local and remote, are
generated by Service Builder. Additionally, the plugins' services in [Liferay's
Plugins SDK](http://github.com/liferay/liferay-plugins) are generated by
Service Builder. Service Builder's use in Liferay Portal and Liferay plugins
demonstrates it to be a robust and reliable tool. As we'll see throughout this
chapter, Service Builder is easy to use and can save developers *lots* of
development time. Although the number of files Service Builder generates can
seem intimidating at first, developers only need to work with a few files in
order to make customizations to their applications and add business logic.

---

 ![Tip](../../images/tip-pen-paper.png) **Note:** You don't have to use Service
 Builder for plugin or portlet development. It's entirely possible to develop
 Liferay plugins by writing custom code for database persistence using your
 persistence framework of choice, such as JPA or Hibernate.

---

One of the main ways Service Builder saves development time is by completely
eliminating the need to write and maintain database access code. To generate a
basic service layer, you only need to create a `service.xml` file and run
Service Builder. This generates a new service `.jar` file for your project. The
generated service `.jar` file includes a model layer, a persistence layer, a
service layer, and related infrastructure. These distinct layers represent a
healthy separation of concerns. The model layer is responsible for defining
objects to represent your project's entities, the persistence layer is
responsible for saving entities to and retrieving entities from the database,
and the service layer is responsible for exposing CRUD and related methods for
your entities as an API. The code Service Builder generates is
database-agnostic, as is Liferay itself.

Each entity generated by Service Builder contains a model implementation class,
a local service implementation class, and optionally a remote service
implementation class. Customizations and business logic can be implemented in
these classes; in fact, these are the only classes generated by Service Builder
that are intended to be customized. Ensuring that all customizations take place
in only a few classes makes Service Builder projects easy to maintain. The
local service implementation class is responsible for calling the persistence
layer to retrieve and store data entities. Local services contain the business
logic and access the persistence layer. They can be invoked by client code
running in the same Java Virtual Machine. Remote services usually have
additional code for permission checking and are meant to be accessible from
anywhere over the Internet or your local network. Service Builder automatically
generates the code necessary to allow access to the remote services. The remote
services generated by Service Builder include SOAP utilities and can be
accessed via SOAP or JSON.

Another way Service Builder saves development time is by providing Spring and
Hibernate configurations for your project. Service Builder uses Spring
dependency injection for making service implementation classes available at
runtime and uses Spring AOP for database transaction management. Service Builder
also uses the Hibernate persistence framework for object-relational mapping. As
a convenience to developers, Service Builder hides the complexities of using
these technologies. Developers can take advantage of Dependency Injection (DI),
Aspect Oriented Programming (AOP), and Object-Relational Mapping (ORM) in their
projects without having to manually set up a Spring or Hibernate environment or
make any configurations.

Another benefit of using Service Builder is that it provides support for
generating finder methods. Finder methods retrieve entity objects from the
database based on specified parameters. You just need to specify the kinds of
finder methods to be generated in the `service.xml` configuration file and
Service Builder does the rest. The generated finder methods allow you, for
example, to retrieve a list of all entities associated with a certain site or a
list of all entities associated with a certain site *and* a certain user.
Service Builder not only provides support for generating this kind of simple
finder method but also for finder methods that take Liferay's permissions into
account. For example, if you are using Liferay's permissions system to protect
access to your entities, Service Builder can generate a different kind of finder
method that returns a list of entities that the logged-in user has permission to
view.

Service Builder also provides built-in caching support. Liferay caches objects
at three levels: *entity*, *finder*, and *Hibernate*. By default, Liferay uses
Ehcache as an underlying cache provider for each of these cache levels but this
is configurable via portal properties. All you have to do to enable entity and
finder caching for an entity in your project is to set the `cache-enabled=true`
attribute of your entity's `<entity>` element in your `service.xml`
configuration file. Please refer to the [Liferay User
Guide](http://www.liferay.com/documentation/liferay-portal/6.1/user-guide/-/ai/liferay-clusteri-2)
for more details about Liferay caching.

<!-- Is the Clustering chapter of the User Guide the only place where we discuss
caching in the documentation? -JR -->

<!-- Yes. -->

Service Builder is a flexible tool. It automates many of the common tasks
associated with creating database persistence code but it doesn't prevent you
from creating custom SQL queries or custom finder methods. Service Builder
allows developers to define custom SQL queries in an XML file and to implement
custom finder methods to run the queries. This could be useful, for example, for
retrieving specific pieces of information from multiple tables via an SQL join.
Service Builder also supports retrieving database information via dynamic query.

In summary, we encourage developers to use Service Builder for portlet and
plugin development because it's a proven solution used by many Liferay plugins
and by Liferay Portal itself. It generates distinct model, persistence, and
service layers, local and remote services, Spring and Hibernate configurations,
and related infrastructure without requiring any manual intervention by
developers. It also allows basic SQL queries and finder methods to be generated
and ones that filter results, taking Liferay's permissions into account.
Service Builder also provides support for entity and query caching. Each of
these features can save lots of development time, both initial development time
and time that would have to be spent maintaining, extending, or customizing a
project. Finally, Service Builder is not a restrictive tool: it allows custom
SQL queries and finder methods to be added and it also supports dynamic query.
Next, let's roll up our sleeves and learn how to use Service Builder. 

## Defining Your Object-Relational Map

In order to demonstrate how to use Service Builder, let's create an example
portlet project that Nose-ster, a fictitious organization, can use to schedule
social events. For our example, we'll create a new Liferay portlet project for
managing and listing these events. We'll define two entities: *events* and
*locations*. The event entity represents a social event that can be scheduled
for Nose-ster, while the location entity represents a location at which a social
event can take place. Since an event must have a location, the event entity will
reference a location entity as one of its attributes.

If you'd like to examine the finished example project, it's a part of our *Dev
Guide SDK* which you can download as a `.zip` file from our Developer's Guide
page. 
[http://www.liferay.com/documentation/liferay-portal/6.1/development](http://www.liferay.com/documentation/liferay-portal/6.1/development)

---

 ![Tip](../../images/tip-pen-paper.png) **Note:** If you're looking for
 a fully-functional portlet application that can manage events, please 
 use Liferay's Calendar portlet instead. The example described in this section
 is only intended to demonstrate how to use Service Builder. The Calendar
 portlet provides many more features than the simple example application
 described here. For information about the Calendar portlet, please refer to
 the chapter on Liferay's collaboration suite in [Using Liferay
 Portal](http://www.liferay.com/documentation/liferay-portal/6.1/user-guide).

---

Liferay portlet projects can contain multiple portlets. We'll create two
portlets in our Event Listing portlet project: the Event Listing portlet and the
Location Listing portlet. These portlets will allow users to add, edit, or
remove events or locations, display lists of events or locations, search for
particular events or locations, and view the details of individual events or
locations.

![Figure 4.1: The Event Listing portlet lets you add and modify Nose-ster social events. The portlet relies on its event and location entities and the service infrastructure that Liferay Service Builder builds around them.](../../images/service-builder-view-events.png)

We'll start by creating the Event Listing portlet in a new portlet plugin
project. To follow this example, create a new Liferay portlet project in your
Liferay Plugins SDK using Liferay IDE or Developer Studio. Go to *File* &rarr;
*New Liferay Project* to bring up the Liferay project wizard. We'll use this
wizard to create both the Event Listing portlet project and the Event Listing
portlet itself. After we finish creating our project and our first portlet,
we'll use the new Liferay portlet wizard to create a second portlet in this
project: the Location Listing portlet. Here's some key information to specify
for the Event Listing portlet project in the first wizard: 

**Project name:** *event-listing-portlet*
**Display name:** *Event Listing*
**Project type:** *Portlet*
**Portlet framework:** *Liferay MVC*
**Create custom portlet class:** *yes*

After you select *Create custom portlet class*, the rest of the wizard lets you
specify details about the Event Listing portlet class:

**Portlet class:** *EventListingPortlet*
**Java package:** *com.nosester.portlet.eventlisting*
**Superclass:** *com.liferay.util.bridges.mvc.MVCPortlet*
**Portlet name:** *eventlisting*
**Display name:** *Event Listing Portlet*
**Title:** *Event Listing Portlet*
**Portlet modes:** *View*
**Create JSP files:** yes
**Create resources bundle file:** *yes*
**Category:** *Sample*

Selecting *Create resources bundle file* lets you add different language
translations for your portlet's textual display values. Click *Finish* to
create your Liferay portlet plugin project and the Event Listing portlet.

Next, we'll create the Location Listing portlet in the Event Listing portlet
project. Select the project in the Package Explorer and then select *File*
&rarr; *New* &rarr; *Liferay Portlet*. Creating the Location Listing portlet in
the IDE is similar to how you created the Event Listing portlet. Only the
portlet class name and a few other name values will be different.

Here is a summary of the values to specify for creating the Location Listing
portlet via the Liferay portlet wizard: 

**Portlet class:** *LocationListingPortlet*
**Java package:** *com.nosester.portlet.eventlisting*
**Superclass:** *com.liferay.util.bridges.mvc.MVCPortlet*
**Portlet name:** *locationlisting*
**Display name:** *Location Listing Portlet*
**Title:** *Location Listing Portlet*
**Portlet modes:** *View*
**Create JSP files:** *yes*
**Create resources bundle file:** *yes*
**Category:** *Sample*

After you've finished using the Liferay portlet wizard to create the Location
Listing portlet, expand your project's `docroot/WEB-INF/src` folder and the
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

### Create the `service.xml` File

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

### Define Global Service Information

A service's global information applies to all of its entities, so let's specify
this information first. Select the *Service Builder* node in the upper left
corner of the Overview mode of your `service.xml` file. The main section of the
view now shows the Service Builder form in which we can enter our service's
global information. The fields include the service's package path, author, and
namespace options. Here are the values we'll use for our example service:

**Package path:** *com.nosester.portlet.eventlisting*
**Auto namespace tables:** *no*
**Author:** [your name]
**Namespace:** *Event*

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

### Define Service Entities

Entities are the heart and soul of a service. Entities represent the map between
the model objects in Java and the fields and tables in your database. Once your
entities are defined, Service Builder handles the mapping automatically, giving
you a facility for taking Java objects and persisting them. For this example,
you'll create two entities--one for events and one for locations. 

Here's a summary of the information we'll enter for the Event entity:

**Name:** *Event*
**Local service:** *yes*
**Remote service:** *yes* 

And here's what we'll enter for the Location entity:

**Name:** *Location*
**Local service:** *yes*
**Remote service:** *yes* 

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

---

![Tip](../../images/tip-pen-paper.png) **Tip:** Suppose you have an existing
DAO service for an entity built using some other framework such as JPA or
JingDAO. You can set local service to `false` and remote service to `true` so
that the methods of your remote `-Impl` class can call the methods of your
existing DAO. This enables your entity to integrate with Liferay's
permission-checking system and provides access to the web service APIs
generated by Service Builder. This is a very handy, quite powerful, and often
used feature of Liferay. 

---

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

### Define the Columns (Attributes) for Each Service Entity

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

### Define Relationships Between Service Entities

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

### Define Ordering of Service Entity Instances

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

### Define Service Entity Finder Methods

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
project available in the *Dev Guide SDK* which you can download from our
Developer's Guide page at
[http://www.liferay.com/documentation/liferay-portal/6.1/development](http://www.liferay.com/documentation/liferay-portal/6.1/development).
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
            <column name="createDate" type="long" />
            <column name="modifiedDate" type="long" />

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

## Generating Services

To build a service from a `service.xml` file, you can use *Liferay IDE*,
*Liferay Developer Studio*, or use a terminal window. We'll demonstrate
generating the service for the example Event Listing portlet project we've been
developing throughout this chapter. The project resides in the
`portlets/event-listing-portlet` folder of your Plugins SDK. 

***Using Liferay IDE or Developer Studio:*** From the *Package Explorer*, open
the `service.xml` file found in your `event-listing-portlet/docroot/WEB-INF`
folder. By default, the file opens up in the *Service Builder Editor*. Make sure
you are in *Overview* mode. Then click the *Build Services* button near the
top-right corner of the view. The *Build Services* button has an image of a
document with the numerical sequence *0101* in front of it. 

Make sure to click the *Build Services* button and not the *Build WSDD* button
that appears next to it. Building the WSDDs won't hurt anything, but you'll
generate files for the remote service instead of the local one. For information
about WSDDs (web service deployment descriptors), please refer to the section on
remote Liferay services later in this chapter. 

![Figure 4.4: The *Overview* mode in the editor provides a nested outline which you can expand, a form for editing basic Service Builder attributes, and buttons for building services or building web service deployment descriptors.](../../images/service-xml-overview.png)

After running Service Builder, your Plugins SDK prints messages listing the
generated files and a message stating `BUILD SUCCESSFUL`. We'll discuss these
files in detail later on in this section. 

***Using the terminal:*** Open a terminal window, navigate to your
`portlets/event-listing-project-portlet` directory, and enter this command:

    ant build-service

When the service has been successfully generated, a `BUILD SUCCESSFUL` message
appears in your terminal window. You should also see that a large number of
files have been generated in your project.

Let's examine the files Service Builder generated for your Event entity. Note
that the files listed under Local Service and Remote Service below are only
generated for an entity that has `local-service` and `remote-service`
attributes set to `true`, respectively. Service Builder generates services for
these entities in two locations in your project. These locations use the package
path that you specified in your `service.xml` file:

- `docroot/WEB-INF/service/com/nosester/portlet/eventlisting`
- `docroot/WEB-INF/src/com/nosester/portlet/eventlisting`

The `docroot/WEB-INF/service/com/nosester/portlet/eventlisting/` package
contains utility classes and interfaces for the Event Listing project. All of
the classes and interfaces in the service folder are packaged in a `.jar` file
called `event-listing-project-portlet-service.jar`, in your
`docroot/WEB-INF/lib` folder. This `.jar` file is generated each time you run
Service Builder. It's possible to place this `.jar` file on your application
server's global classpath to make your project's services available to other
projects. This allows a portlet in different project, for example, to create,
update, and delete Events and Locations. Of course, you should consider the
security implications of placing your project's service `.jar` file on your
application server's global classpath: do you *really* want to allow other
plugins to access your project's services? 

The `docroot/WEB-INF/src/com/nosester/portlet/eventlisting` package contains the
implementation of the interfaces defined in the
`docroot/WEB-INF/service/com/nosester/portlet/eventlisting` package. It belongs
to the Event Listing project's classpath but is not available outside the Event
Listing project. Service Builder generates classes and interfaces belonging to
the persistence layer, service layer, and model layer in the
`docroot/WEB-INF/service/com/nosester/portlet/eventlisting` and
`docroot/WEB-INF/src/com/nosester/portlet/eventlisting` packages. Let's look at
the classes and interfaces generated for Events. The ones generated for
Locations are similar.

-   Persistence
    - `EventPersistence`: Event persistence interface that defines CRUD methods
      for the Event entity such as `create`, `remove`, `countAll`, `find`,
      `findAll`, etc. `@generated`
    - `EventPersistenceImpl`: Event persistence implementation class that
      implements `EventPersistence`. `@generated`
    - `EventUtil`: Event persistence utility class that wraps
      `EventPersistenceImpl` and provides direct access to the database for CRUD
      operations. This utility should only be used by the service layer; in your
      portlet classes, use `EventLocalServiceUtil` or `EventServiceUtil`
      instead. `@generated`

    ![Figure 4.5: Service Builder generates these persistence classes and interfaces. You shouldn't (and won't need to) customize any of these classes or interfaces.](../../images/service-builder-persistence-diagram.uml.png)

-   Local Service (only generated for an entity if an entity's `local-service`
    attribute is set to `true` in `service.xml`)
    - `EventLocalService`: Event local service interface. `@generated`
    - `EventLocalServiceImpl` (**LOCAL SERVICE IMPLEMENTATION**): Event local
      service implementation. This is the only class within the local service
      that you should modify manually. You can add custom business logic here.
      For any custom methods added here, Service Builder adds corresponding
      methods to the `EventLocalService` interface the next time you run it.
    - `EventLocalServiceBaseImpl`: Event local service base implementation. This
      is an abstract class. Service Builder injects a number of instances of
      various service and persistence classes into this class. `@generated`
      `@abstract`
    - `EventLocalServiceUtil`: Event local service utility class which wraps
      `EventLocalServiceImpl` and serves as the primary local access point to
      the service layer. `@generated`
    - `EventLocalServiceWrapper`: Event local service wrapper which implements
      `EventLocalService`. This class is designed to be extended and it allows
      developers to customize the local Event services. Customizing services
      should be done via a hook plugin. `@generated` 

    ![Figure 4.6: Service Builder generates these service classes and interfaces. Only EventLocalServiceImpl allows custom methods to be added to the service layer.](../../images/service-builder-service-diagram.uml.png)

-   Remote Service (only generated for an entity if an entity's `remote-service`
    attribute is set to `true` in `service.xml`)
    - `EventService`: Event remote service interface. `@generated`
    - `EventServiceImpl` (**REMOTE SERVICE IMPLEMENTATION**): Event remote
      service implementation. This is the only class within the remote service
      that you should modify manually. Here, you can write code that adds
      additional security checks and invokes the local services. For any custom
      methods added here, Service Builder adds corresponding methods to the
      `EventService` interface the next time you run it. 
    - `EventServiceBaseImpl`: Event remote service base implementation. This is
      an abstract class. `@generated` `@abstract`
    - `EventServiceUtil`: Event remote service utility class which wraps
      `EventServiceImpl` and serves as the primary remote access point to the
      service layer. `@generated`
    - `EventServiceWrapper`: Event remote service wrapper which implements
      `EventService`. This class is designed to be extended and it allows
      developers to customize the remote Event services.  Customizing services
      should be done in a hook plugin. `EventServiceImpl` `@generated`
    - `EventServiceSoap`: Event SOAP utility which the remote `EventServiceUtil`
      remote service utility can access. `EventServiceUtil` `@generated`
    - `EventSoap`: Event SOAP model, similar to `EventModelImpl`. `EventSoap` is
      serializable; it does not implement `Event`. `@generated`

-   Model
    - `EventModel`: Event base model interface. This interface and its
      `EventModelImpl` implementation serve only as a container for the default
      property accessors generated by Service Builder. Any helper methods and
      all application logic should be added to `EventImpl`. `@generated`
    - `EventModelImpl`: Event base model implementation. `@generated`
    - `Event`: Event model interface which extends `EventModel`. `@generated`
    - `EventImpl`:  (**MODEL IMPLEMENTATION**)Event model implementation. You
      can use this class to add helper methods and application logic to your
      model. If you don't add any helper methods or application logic, only the
      auto-generated field getters and setters will be available. Whenever you
      add custom methods to this class, Service Builder adds corresponding
      methods to the `Event` interface the next time you run it.
    - `EventWrapper`: Event wrapper, wraps `Event`. `@generated`

    ![Figure 4.7: Service Builder generates these model classes and interfaces. Only `EventImpl` allows custom methods to be added to the service layer.](../../images/service-builder-model-diagram.uml.png)

Each file that Service Builder generates is assembled from an associated
Freemarker template. You can find Service Builder's Freemarker templates in the
`com.liferay.portal.tools.servicebuilder.dependencies` package of Liferay's
`portal-impl/src` folder. For example, if you want to find out how a
`-ServiceImpl.java` file is generated, just look at the `service_impl.ftl`
template.

Of all the classes generated by Service Builder, only three should be
manually modified: `EventLocalServiceImpl`, `EventServiceImpl` and `EventImpl`.
If you manually modify the other classes, your changes will be overwritten the
next time you run Service Builder. Next, let's add some local service methods to
`EventLocalServiceImpl` and learn how to call them. After that, we'll add
some remote service methods to `EventServiceImpl` and learn how to call them.

## Writing the Local Service Class 

The heart of your service is its `-LocalServiceImpl` class. Within it, you put
core business logic for working with your model. Throughout this chapter, we've
been constructing services for the Nose-ster Event Listing example portlet
project. We'll start by examining the initial service classes Service Builder
generated for it. 

Note that Service Builder created an `EventLocalService` class which is is the
interface for the local service. It contains the signatures of every method in
`EventLocalServiceBaseImpl` and `EventLocalServiceImpl`.
`EventLocalServiceBaseImpl` contains a few automatically generated methods
providing common functionality. Since the `EventLocalService` class is
generated, you should never modify it. If you do, your changes will be
overwritten the next time you run Service Builder. Instead, all custom code
should be placed in `EventLocalServiceImpl`.

Open the `EventLocalServiceImpl.java` file found in your
`/docroot/WEB-INF/src/com/nosester/portlet/eventlisting/service/impl/` folder.

Add the following database interaction methods to the `EventLocalServiceImpl`
class:

    public Event addEvent(Event event) throws SystemException {
        
        long eventId = counterLocalService.increment(Event.class.getName());
        event.setEventId(eventId);
        
        Date now = new Date();
        event.setCreateDate(now);
        event.setModifiedDate(now);
        
        return super.addEvent(event);
    }

    public Event updateEvent(Event event) throws SystemException {
        
        Date now = new Date();
        event.setModifiedDate(now);
        
        return super.updateEvent(event);
    }

    public List<Event> getEventsByGroupId(long groupId) throws SystemException {
        
        return eventPersistence.findByGroupId(groupId);
    }

    public List<Event> getEventsByGroupId(long groupId, int start, int end)
    throws SystemException {
        
        return eventPersistence.findByGroupId(groupId, start, end);
    }

    public int getEventsCountByGroupId(long groupId) throws SystemException {
        
        return eventPersistence.countByGroupId(groupId);
    }	

Remember to import the required classes. These include the following:

    com.nosester.portlet.eventlisting.model.Event
    com.liferay.portal.kernel.exception.SystemException
    java.util.Date
    java.util.List

In order to add an Event to the database, we need an ID for the Event. Liferay
provides a counter service which we call to obtain a unique ID for each new
Event entity. It's possible to use the `increment` method of Liferay's
`CounterLocalServiceUtil` class but Service Builder already makes a
`CounterLocalService` instance available to `EventLocalServiceBaseImpl` by
Spring via dependency injection. Since our `EventLocalServiceImpl` class extends
`EventLocalServiceBaseImpl`, we can access this `CounterLocalService` instance.
See `EventLocalServiceBaseImpl` for a list of all the beans that Spring makes
available for use. These include the following:

- `eventLocalService`
- `eventPersistence`
- `locationLocalService`
- `locationPersistence`
- `counterLocalService`
- `resourceLocalService`
- `resourceService`
- `resourcePersistence`
- `userLocalService`
- `userService`
- `userPersistence`

It's a best practice to use the injected class's `increment` method rather than
calling Liferay's `CounterLocalService`'s `increment` method since using the
injected class does not require an extra database transaction. Calling Liferay's
`CounterLocalServiceUtil` class, on the other hand, does require an extra
database transaction. 

    long eventId = counterLocalService.increment(Event.class.getName());

We use the generated `eventId` as the ID for the new Event:

    event.setEventId(eventId);

Next, we set the `createDate` and `modifiedDate` of our Event to the current
time. Lastly, we return the Event created by the generated `addEvent` method of
`EventLocalServiceBaseImpl`.

Note: Our `addEvent` method only sets the creation date and modified date of the
Event. It doesn't populate its name and description, or associate a location
with it. We could overload `addEvent` by creating another `addEvent` method that
takes name, description, and a location ID as parameters. In this method, we
could call the `eventPersistence` object's `create` method to create a new
Event. Then we could use the method's parameters to populate the new Event's
name, description, and a location ID fields and return the Event.
(`eventPersistence` is one of the Spring beans injected into
`EventLocalServiceBaseImpl` by Service Builder.) To see this implementation of
`addEvent`, please examine the complete `EventListingServiceImpl` class included
in the *Dev Guide SDK* which you can download from our Developer's Guide page at
[http://www.liferay.com/documentation/liferay-portal/6.1/development](http://www.liferay.com/documentation/liferay-portal/6.1/development).

Before you can use any custom methods that you added to `EventLocalServiceImpl`
class, you must add their signatures to the `EventLocalService` interface by
running Service Builder again.

***Using Developer Studio:*** As we did before, open your `service.xml` file and
make sure you are in the *Overview* mode. Then, select *Build Services*.

***Using the terminal:*** Navigate to the root directory of your portlet in the
terminal and run:

    ant build-service

Service Builder looks through `EventLocalServiceImpl` and automatically copies
the signatures of each method into the interface. You can now add a new Event to
the database by making the following call:

    EventLocalServiceUtil.addEvent(event);

Of course, we need to create an event before we can make this call. This is one
reason to replace or overload the `addEvent` method we added to
`EventLocalServiceImpl`, which we noted as an option above. In addition to all
of the Java classes, Service Builder also generates a `service.properties` file
which we'll examine later in this chapter. Next, let's call our newly
implemented local service. 

## Calling Local Liferay Services

Once Service Builder has generated our portlet project's services, we can call
them in our project's `-Portlet` classes. We can call any methods of our
`EventLocalServiceUtil` or `LocationLocalServiceUtil` static utility classes
from within our `EventListingPortlet` and `LocationListingPortlet` classes. For
our example, we'd like the Event Listing portlet to be able to perform create,
read, update, and delete (CRUD) operations on Events and our Location Listing
portlet to be able to perform CRUD operations on Locations. To this end, we'll
create the following methods for our `EventListingPortlet` class and similar
ones for our `LocationListingPortlet` class:

- `addEvent`
- `updateEvent`
- `deleteEvent`

Replace the contents of your `EventListingPortlet.java` file with the following:

    package com.nosester.portlet.eventlisting;

	import com.liferay.portal.kernel.util.CalendarFactoryUtil;
    import com.liferay.portal.kernel.util.ParamUtil;
    import com.liferay.portal.kernel.util.WebKeys;
    import com.liferay.portal.theme.ThemeDisplay;
    import com.liferay.util.bridges.mvc.MVCPortlet;

    import com.nosester.portlet.eventlisting.model.Event;
    import com.nosester.portlet.eventlisting.model.impl.EventImpl;
    import com.nosester.portlet.eventlisting.service.EventLocalServiceUtil;

    import java.util.Calendar;
    import java.util.Date;

    import javax.portlet.ActionRequest;
    import javax.portlet.ActionResponse;
    public class EventListingPortlet extends MVCPortlet {

        public void addEvent(ActionRequest request, ActionResponse response)
                throws Exception {

            EventImpl event = new EventImpl();

            event.setName(ParamUtil.getString(request, "name"));
            event.setDescription(ParamUtil.getString(request, "description"));
            event.setLocationId(ParamUtil.getLong(request, "locationId"));

            event.setDate(_extractDate(request));

            ThemeDisplay themeDisplay = (ThemeDisplay)request
                    .getAttribute(WebKeys.THEME_DISPLAY);

            event.setUserId(themeDisplay.getUserId());
            event.setCompanyId(themeDisplay.getCompanyId());
            event.setGroupId(themeDisplay.getScopeGroupId());

            EventLocalServiceUtil.addEvent(event);

            sendRedirect(request, response);
        }

        public void deleteEvent(ActionRequest request, ActionResponse response)
            throws Exception {

            long eventId = ParamUtil.getLong(request, "eventId");

            EventLocalServiceUtil.deleteEvent(eventId);

            sendRedirect(request, response);
        }

        public void updateEvent(ActionRequest request, ActionResponse response)
            throws Exception {

            long eventId = ParamUtil.getLong(request, "eventId");

            Event event = EventLocalServiceUtil.fetchEvent(eventId);

            event.setEventId(ParamUtil.getLong(request, "eventId"));
            event.setName(ParamUtil.getString(request, "name"));
            event.setDescription(ParamUtil.getString(request, "description"));
            event.setLocationId(ParamUtil.getLong(request, "locationId"));

            event.setDate(_extractDate(request));

            ThemeDisplay themeDisplay = (ThemeDisplay)request
                    .getAttribute(WebKeys.THEME_DISPLAY);

            event.setUserId(themeDisplay.getUserId());
            event.setCompanyId(themeDisplay.getCompanyId());
            event.setGroupId(themeDisplay.getScopeGroupId());

            EventLocalServiceUtil.updateEvent(event);

            sendRedirect(request, response);
        }

        private Date _extractDate(ActionRequest request) {
            int year = ParamUtil.getInteger(request, "dateYear");
            int month = ParamUtil.getInteger(request, "dateMonth");
            int day = ParamUtil.getInteger(request, "dateDay");
            int hour = ParamUtil.getInteger(request, "dateHour");
            int minute = ParamUtil.getInteger(request, "dateMinute");
            int amPm = ParamUtil.getInteger(request, "dateAmPm");

            if (amPm == Calendar.PM) {
                hour += 12;
            }

            Calendar dateCal = CalendarFactoryUtil.getCalendar();
            dateCal.set(year, month, day, hour, minute);
            Date date = dateCal.getTime();
            return date;
        }

    }

Your Event Listing portlet's `addEvent`, `updateEvent`, and `deleteEvent`
methods now call the appropriate methods of `EventLocalServiceUtil`. The getter
methods of Liferay's `ParamUtil` class such as `getLong` and `getString` return
default values like `0` or `""` if the specified request parameter is not
available from the portlet request. When adding a new event, for example, no
event ID is available so `ParamUtil.getLong("request", "eventId")` returns `0`.
The Event portlet's `addEvent` method calls `EventLocalServiceUtil`'s `addEvent`
method. The event ID for the new event is generated at the service layer in the
`addEvent` method that we added to the `EventLocalServiceImpl` class. The
`EventLocalServiceUtil` generated for us by Service Builder contains various
CRUD methods including the following:

- `createEvent`
- `addEvent`
- `deleteEvent`
- `updateEvent`
- `fetchEvent`
- `getEvent`

The methods listed in the following figure are all generated by Service Builder
and are available to be called by our `EventListingPortlet` class.

![Figure 4.8: Our `EventListingPortlet` class can access these methods of `EventLocalServiceUtil`, many of which are CRUD operations.](../../images/local-service-util-outline.png)

Portlet classes should only have access to the `-LocalServiceUtil` classes. The
`-LocalServiceUtil` classes, in turn, call their injected `-LocalServiceImpl`
classes. Notice in the figure above that the `EventLocalServiceUtil` utility
class has a private instance variable called `_service`. The `_service` instance
variable, of type `EventLocalService`, gets an instance of
`EventLocalServiceImpl` at runtime via dependency injection. So all the methods
of the `EventLocalServiceUtil` utility class internally call corresponding
methods of the `EventLocalServiceImpl` class at runtime.

<!-- Consider removing or moving the section below. - Jim -->

## Calling Liferay Services

Each service provides a local interface to clients running in the same JVM as
Liferay Portal. There are two ways to invoke a service API's methods: 

- Spring injection: If your application uses Spring and has access to the portal
context. 

- `-ServiceUtil` classes: These classes mask the complexity of service
implementations. This is a good option if you're not familiar with Spring. 

Let's invoke a service using its `-ServiceUtil` class. The following JSP code
snippet demonstrates how to get a list of the most recent bloggers from an
organization. 

    <%@ page import="com.liferay.portlet.blogs.service.BlogsStatsUserLocalServiceUtil" %>
    <%@ page import="com.liferay.portlet.blogs.util.comparator.StatsUserLastPostDateComparator" %>
    ...
    <%@
    List statsUsers = BlogsStatsUserLocalServiceUtil.getOrganizationStatsUsers(
        organizationId, 0, max, new StatsUserLastPostDateComparator());
    %>

This JSP code invokes the static method `getOrganizationStatsUsers()` from the
`-LocalServiceUtil` class `BlogsStatsUserLocalServiceUtil`. 

In addition to the services you create using Service Builder, your portlets can
also access a variety of services built into Liferay. These include the
following services:

- `UserService` - for accessing, adding, authenticating, deleting, and updating
  users. 
- `OrganizationService` - for accessing, adding, deleting, and updating
  organizations. 
- `GroupService` - for accessing, adding, deleting, and updating groups.
- `CompanyService` - for accessing, adding, checking, and updating companies.
- `ImageService` - for accessing images.
- `LayoutService` - for accessing, adding, deleting, exporting, importing, and
  updating layouts. 
- `PermissionService` - for checking permissions.
- `UserGroupService` - for accessing, adding, deleting, and updating user
  groups. 
- `RoleService` - for accessing, adding, unassigning, checking, deleting, and
  updating roles. 

For more information on these services, see the Liferay Portal CE Javadocs at
[http://docs.liferay.com/portal/6.1/javadocs/](http://docs.liferay.com/portal/6.1/javadocs/)
or the Liferay Portal EE Javadocs included in the Liferay Portal EE
Documentation `.zip` file that you can download from the Customer Portal on
[http://www.liferay.com](http://www.liferay.com). 

Next, we'll show you how to give Liferay portal instructions, or *hints*, on how
to present your entity models in your portlet's view. 

## Using Model Hints 

Now that you've created your model entities and implemented your business logic
to create and modify those entities, I'm sure you have some ideas on how to help
users input valid model entity data. For example, considering the Nose-ster
project we've been working on throughout this chapter, you'd want users to
create social events for the future, not for the past. And it would be nice to
give the user a nice text area and editor to fill in their description. Wouldn't
it be great to specify these customizations from a single place in your portal
project? Good news! Service Builder lets you specify this information as *model
hints* in a single file called `portlet-model-hints.xml` found in your project's
`docroot/WEB-INF/src/META-INF` folder. Liferay calls them *model hints* because
they suggest how the entities should be presented to the users and the size of
the database columns that should be used to store the entities. 

<!-- Commenting this out for now. It's good information to know, but I found it
distracting in the first paragraph and thought it was more important to focus on
introducing model hints. Perhaps this can be moved somewhere else in this
section or the chapter. - Jim

Service Builder generates a
number of XML configuration files in your project's
`docroot/WEB-INF/src/META-INF` folder. Service Builder uses most of these files
to manage Spring and Hibernate configurations. Don't modify the Spring or
Hibernate configuration files; your changes will be overwritten the next time
Service Builder runs. However, you can safely edit the
`portlet-model-hints.xml` file. 
-->

Model hints let you to configure how model fields are displayed by the AlloyUI
taglib called `aui`. As Liferay Portal displays forms in your portlet for users
to input values for model entities, portal first checks the model hints you
specified and customizes your form's input fields based on these hints. For
example, if you want to limit users to selecting dates in the future, you'd set
a `year-range-past` hint to `false` for that field in your
`portlet-model-hints.xml` file. It would look like the following tag:

    <field name="date" type="Date">
        <hint name="year-range-past">false</hint>
    </field>

Let's take a look at the model hints file that Service Builder generated for
your Event Listing portlet. Examine your project's
`docroot/WEB-INF/src/META-INF/portlet-model-hints.xml` file. If you've been
following along in the previous sections, Service Builder should have created
the `portlet-model-hints.xml` file with the following contents:

    <?xml version="1.0"?>

    <model-hints>
        <model name="com.nosester.portlet.eventlisting.model.Event">
            <field name="eventId" type="long" />
            <field name="companyId" type="long" />
            <field name="groupId" type="long" />
            <field name="userId" type="long" />
            <field name="createDate" type="long" />
            <field name="modifiedDate" type="long" />
            <field name="name" type="String" />
            <field name="description" type="String" />
            <field name="date" type="Date />
            <field name="locationId" type="long" />
        </model>
        <model name="com.nosester.portlet.eventlisting.model.Location">
            <field name="locationId" type="long" />
            <field name="companyId" type="long" />
            <field name="groupId" type="long" />
            <field name="userId" type="long" />
            <field name="createDate" type="long" />
            <field name="modifiedDate" type="long" />
            <field name="name" type="String" />
            <field name="description" type="String" />
            <field name="streetAddress" type="String" />
            <field name="city" type="String" />
            <field name="stateOrProvince" type="String" />
            <field name="country" type="String" />
        </model>
    </model-hints>

<!-- Should the above model elements include the uuid String field? - Jim -->

The root-level element is `model-hints`. Within it are all of your model
entities represented by `model` elements. Each `model` element must have a
`name` attribute specifying the full-qualified class name of the model class.
Each model has `field` elements that represent their model entity's columns. 
Lastly, each `field` element must have a name and a type. The names and types of
each `field` element correspond to the names and types specified for each
entity's columns in your project's `service.xml` file. Service Builder generates
all of these elements for you, based on your `service.xml` file. 

To add hints to a field, add a `hint` tag inside of its `field` tag. For
example, you can add a `display-width hint` to specify the pixel width that
should be used in displaying the field. The default pixel width for a field is
350. To display a String field with 50 pixels, we could nest a
`hint` element named `display-width` in it and give it a value of `50` for 50
pixels. Here's an example of using the `display-width` hint in a `field`
element: 

    <field name="name" type="String">
        <hint name="display-width">50</hint>
    </field>

In order to see the effect of a hint on a field, you have to run Service Builder
again and redeploy your portlet project. Changing the `display-width` doesn't
actually limit the number of characters that can be entered into the `name`
field; it's just a way to control the width of the field in the AlloyUI input
form. 

To configure the maximum size of a model
field's database column (i.e., the maximum number of characters that can be
saved for the field), use the `max-length` hint. The default `max-length` value
is 75 characters. If you wanted the `name` field to be able to persist up to 100
characters, you could add a `max-length` hint to that field: 

    <field name="name" type="String">
        <hint name="display-width">50</hint>
        <hint name="max-length">100</hint>
    </field>

Remember to run Service Builder and redeploy your portlet project after updating
your `portlet-model-hints.xml` file. 

So, we've mentioned a few different hints. It's about time we listed the portlet
hints available to you. The following table describes the portlet model hints.

**Model Hint Values and Descriptions**

Name                | Value Type | Description | Default
:-----------------: | :--------: | :---------- | :-----:
`auto-escape`       | boolean | sets whether text values should be escaped via `HtmlUtil.escape` | true
`autoSize`          | boolean | displays the field in a for scrollable text area | false
`day-nullable`      | boolean | allows the day to be null in a date field | false
`default-value`     | String  | sets the default value for a field | (empty String)
`display-height`    | integer | sets the display height of the form field rendered using the aui taglib | 15
`display-width`     | integer | sets the display width of the form field rendered using the aui taglib | 350
`editor`            | boolean | sets whether to provide an editor for the input | false
`max-length`        | integer | sets the maximum column size for SQL file generation | 75
`month-nullable`    | boolean | allows the month to be null in a date field | false
`secret`            | boolean | sets whether hide the characters input by the user | false
`show-time`         | boolean | sets whether to show inlcude time along with the date | true
`upper-case`        | boolean | converts all characters to upper case | false
`year-nullable`     | boolean | allows the year to be null in a date field | false
`year-range-delta`  | integer | specifies the number of years to display from today's date in a date field rendered with the aui taglib | 5
`year-range-future` | boolean | sets whether to include future dates | true
`year-range-past`   | boolean | sets whether to include past dates | true

<!--
I wanted to get a complete list of model hints used in portal. I found the
ModelHints interface and then searched for where (*.java, *.js, *.jsp) it was
referenced. It led me to portal-web/docroot/html/taglib/ui/input_field/page.jsp.
I found some more hints and added them to the table above.

I'm not sure what the `check-tab` hint does. Would be worth asking the UI team.

- Jim -->

Liferay Portal has its own model hints XML configuration file called
`portal-model-hints.xml` which you can find in Liferay's
`portal-impl/classes/META-INF` folder. Liferay's model hints configuration file
contains many hint examples so you can refer to it for reference when
customizing your `portlet-model-hints.xml` file. 

You can use the `default-hints` element to define a list of hints to be applied
to every field of a model. For example, adding the following element inside of a
model element applies a `display-width` of 300 to each element of the model:

    <default-hints>
        <hint name="display-width">300</hint>
    </default-hints>

You can define `hint-collection` elements inside of the `model-hints` root-level
element to define a list of hints to be applied together. A hint collection
must be specified with a name. For example, Liferay's `portal-model-hints.xml`
defines the following hint collections:

    <hint-collection name="CLOB">
        <hint name="max-length">2000000</hint>
    </hint-collection>
    <hint-collection name="URL">
        <hint name="max-length">4000</hint>
    </hint-collection>
    <hint-collection name="TEXTAREA">
        <hint name="display-height">105</hint>
        <hint name="display-width">500</hint>
        <hint name="max-length">4000</hint>
    </hint-collection>
    <hint-collection name="SEARCHABLE-DATE">
        <hint name="month-nullable">true</hint>
        <hint name="day-nullable">true</hint>
        <hint name="year-nullable">true</hint>
        <hint name="show-time">false</hint>
    </hint-collection>

You can apply a hint collection to a model field by referring to the hint
collection's name. For example, if you define a `SEARCHABLE-DATE` collection
like the one above in your `model-hints` element, you can apply it to your Event
model's date field by using a `hint-collection` element that references the
collection by its name:

    <field name="date" type="Date">
        <hint-collection name="SEARCHABLE-DATE" />
    </field>

As always, remember to run Service Builder and redeploy your project after
updating your `portlet-model-hints.xml` file.

Let's use a couple of model hints in our Event Listing portlet and Location
Listing Portlet. We'll start by giving the user an editor for filling in their
description fields. Since we want to apply the same hint to both the event and
location entities, we'll define it as a hint collection. Then we'll reference
the hint collection in them. 

Define the following hint collection just below your `model-hints` root element
in your `portlet-model-hints.xml` file:

	<hint-collection name="DESCRIPTION-TEXTAREA">
		<hint name="editor">true</hint>
		<hint name="max-length">250</hint>
	</hint-collection>

Then replace the description fields of your event and location entities with the
each description field referencing the hint collection, as demonstrated below:

	<field name="description" type="String">
		<hint-collection name="DESCRIPTION-TEXTAREA" />
	</field>

The last hint we'll introduce for our Event Listing portlet is one that makes
sure the user has no option to select a year from the past. Replace the event
entity's date field with the following date field as specified below:

	<field name="date" type="Date">
		<hint name="year-range-past">false</hint>
	</field>

Great! Now rebuild your service using Service Builder, redeploy your portlet
project, and add or edit an event using the portlet. The following figure shows
the portlet displaying the input fields as we specified.
	
![Figure 4.10: Customizing string input fields to use editors and customizing date fields to filter-out past years are just a couple examples of the many things you can do with Liferay model hints.](../../images/service-builder-edit-event.png)

Well, you've learned the art of persuasion through Liferay's model hints. Now,
you can not only influence how your model's input fields are displayed but you
can also set their database table column sizes. You can also choose how to apply
hints. You can insert individual hints directly into your fields, you can apply
a set of default hints to all of a model's fields, or you can define collections
of hints to apply at either of those scopes. Looks like you've picked up on the
"hints" on how Liferay model hints help portlet data! 

Next, let's find out how you can invoke Liferay's service APIs remotely. 

## Writing the Remote Service Class 

Many default Liferay services are available as web services. Liferay exposes its
web services via SOAP and JSON web services. If you're running Liferay locally
on port 8080, visit the following URL to browse Liferay's default SOAP web
services:

    http://localhost:8080/api/axis

To browse Liferay's default JSON web services, visit this URL:

    http://localhost:8080/api/jsonws/

These web services APIs can be accessed by many different kinds of clients,
including non-portlet and even non-Java clients. You can use Service Builder to
generate similar remote services for your projects' custom entities. When you
run Service Builder with the `remote-service` attribute set to `true` for an
entity, all the classes, interfaces, and files required to support both SOAP and
JSON web services are generated for that entity. Service Builder generates
methods that call existing services, but it's up to you to *implement* the
methods that are exposed remotely. Let's use Service Builder to generate remote
services for the Nose-ster Event Listing portlet example project that we've
been developing throughout this chapter. We'll implement a few methods for the
Event Listing portlet that can be called remotely via SOAP and JSON web
services. 

We implemented our local service methods in `EventLocalServiceImpl`. Similarly,
we'll implement our remote service methods in `EventServiceImpl`. Add the
following methods to your `EventServiceImpl` class:

    public Event addEvent(Event event) throws SystemException {
        
        long eventId = counterLocalService.increment(Event.class.getName());
        event.setEventId(eventId);
        
        Date now = new Date();
        event.setCreateDate(now);
        event.setModifiedDate(now);
        
        return eventLocalService.addEvent(event);
    }
    
    public Event update(Event event) throws SystemException {
        
        Date now = new Date();
        event.setModifiedDate(now);
        
        return eventLocalService.updateEvent(event);
    }	
    
    public Event delete(Event event) throws SystemException {
        
        return eventLocalService.deleteEvent(event);
    }	

    public Event deleteEvent(long eventId) throws PortalException, SystemException {
            
        return eventLocalService.deleteEvent(eventId);
    }

Notice the calls to the `eventLocalService` field's `addEvent`, `updateEvent`,
and `deleteEvent` methods. The `eventLocalService` field holds a Spring bean of
type `EventLocalServiceImpl` that's injected into `EventServiceBaseImpl` by
Service Builder. See `EventServiceBaseImpl` for a complete list of the Spring
beans available in `EventServiceImpl`. These include the following:

- `counterLocalService`
- `eventLocalService`
- `eventService`
- `eventPersistence`
- `locationLocalService`
- `locationPersistence`
- `locationService`
- `resourceLocalService`
- `resourcePersistence`
- `resourceService`
- `userLocalService`
- `userPersistence`
- `userService`

Notice also that we overloaded the `deleteEvent` method of the
`EventServiceImpl` class. We did this to simplify our example of calling remote
web services in the next section; it's much more efficient to pass an event ID
as a parameter over web services than to pass an entire event object.

After you finish adding imports to `EventServiceImpl`, save the class and run
Service Builder again. 

Liferay uses Apache Axis to make SOAP web services available. Axis requires a
Web Service Deployment Descriptor (WSDD) to be generated in order to make the
SOAP web services available. Liferay provides a `build-wsdd` Ant target which
generates the WSDD. In Liferay IDE or Developer Studio, when viewing your
`service.xml` file in Overview mode, there's a button in the top-right
corner of the screen that's a shortcut for calling the Build WSDD target.
Liferay Portal makes your service's Web Services Definition Language (WSDL)
available after you've built its WSDD and deployed your portlet project. Let's
learn how to call your remote services next. 

### Calling Remote Liferay Services 

After you've built your portlet project's WSDDs and deployed the project as a
plugin, its services are available on the portal server. If you've been
implementing the Nose-ster Event Listing example portlet used throughout this
chapter, you'll be anxious to try out its remote web services. First, let's see
what remote web services it has available.

It's easy to list your Nose-ster Event Listing plugin's SOAP web services. You
have two options: you can access them from within Liferay IDE or you can open your
browser to the URL for its web services.

To access your SOAP web services from inside Liferay IDE, right-select your
portlet from under it's Liferay server in the *Servers* view. Then select *Test
Liferay Web Services...*.

If you'd rather view your SOAP web services from a browser, go to the following
URL: 

    http://localhost:8080/event-listing-portlet/api/axis

Liferay portal lists the services available for all of your entities and
provides links to each of their WSDL documents. Clicking on the WSDL link for
your Event service takes you to the following URL: 

    http://localhost:8080/event-listing-portlet/api/axis/Plugin_Event_EventService?wsdl 

This WSDL document lists all of the Event entity's SOAP web services. Once your
SOAP web service's WSDL is available, any SOAP web service client can access it.
In production, make sure to integrate your applications with Liferay's
permissions system to perform security checks for all your remote service
methods.

You can also call your plugin's remote Service Builder-generated web services
using the JSON-based protocol. To view the JSON web services available for your
Event Listing plugin, visit the following URL:

    http://localhost:8080/event-listing-portlet/api/jsonws

Each entity's available operations are listed on the plugin's JSON web services
API page. You can invoke JSON web services directly from your browser. For
example, to bring up a test form for your Event entity's *delete-event*
operation, visit the above URL and click on its *delete-event* link. 

![Figure 4.9: You'll see a page displaying the name of the service method, its required parameters, its return type, possible exceptions it can throw, and a form for entering its parameters.](../../images/invoking-sb-generated-json-ws.png)

The only parameter required for the `delete-event` operation is an event ID.
Since we haven't covered creating the UI for adding our Event entities, you
probably don't have any events in your database. But if you did, you could check
for an event's ID in your `Event_Event` database table. Then you could enter the
value into the *eventId* field in the *Execute* section of the test page and
click *Invoke* to delete that event. Liferay returns feedback from each
invocation.

Finding a portlet's web services is easy with Liferay's SOAP and JSON web
service. Invoking a portlet's web services via Liferay's JSON web service
interface is a great way to test them. You can also examine alternate equivalent
methods of calling the SOAP and JSON web services via JavaScript, Curl, and
URLs. Next, we'll consider how to implement custom SQL queries in your portlet,
so you can easily leverage information from multiple entity types. 

## Developing Custom SQL Queries 

Service Builder's finder methods facilitate searching for entities by their
attributes--their column values. You simply add the column as a parameter for
the finder in your `service.xml` file, run Service Builder, and it generates the
finder method in your persistence layer and adds methods to your service layer
that invoke the finder. But what if you'd like to do more complicated searches
that incorporate columns from multiple entities? 

For example, consider the Nose-ster Event Listing portlet we've been developing
in this chapter. Suppose we want to find an event based on its name,
description, and location name. If you recall, our event entity refers to its
location by the location's ID, not its name. That is, the event entity table,
`Event_Event`, refers to an event's location by its long integer ID in the
table's `locationId` column. But we need to access the *name* of the event's
location. Of course, with SQL we can join the event and location tables for our
search to include the location name. But how would we incorporate custom SQL
into our portlet? And how would we invoke the SQL from our service? We'll
specify the SQL as *Liferay custom SQL* and invoke it in our service via a
*custom finder method*.

Liferay custom SQL is a Service Builder-supported method for performing complex
and custom queries against the database. Invoking custom SQL from a finder
method in your persistence layer is straightforward. And Service Builder helps
you generate the interfaces to your finder method. We'll show you just how easy
it is following these steps: 

1. Specify your custom SQL. 

2. Implement your finder method. 

3. Access your finder method from your service. 

Let's follow these steps to create and invoke custom SQL in our Event Listing
portlet. 

### Step 1: Specify Your Custom SQL

After you've tested your SQL, you must specify it in a place in a particular
file for Liferay to access it. Liferay's `CustomSQLUtil` class looks up custom
SQL from a file called `default.xml` in your portlet project's
`docroot/WEB-INF/src/custom-sql/` folder. You must create the `custom-sql`
folder and create the `default.xml` file in that `custom-sql` folder. 

Here's the custom SQL we'll use to return an event location's name, along with
the event's name and event's description, for our Event Listing portlet: 

    SELECT
        COUNT(*) AS COUNT_VALUE
    FROM
        Inventory_PurchaseOrder
    WHERE
        (Inventory_PurchaseOrder.partId = ?) AND
        (Inventory_PurchaseOrder.closed = 0)

The `default.xml` file must adhere to the following format: 

    <custom-sql>
        <sql id="[fully-qualified class name + method]">
        SQL query wrapped in <![CDATA[...]]>
        No terminating semi-colon
        </sql>
    </custom-sql>

You can add a `custom-sql` element for every custom SQL query you'd like for
your portlet, as long as each query has a unique ID. The convention we recommend
using for the ID value is the fully-qualified class name of the finder followed
by a dot "`.`" character and the name of the finder method. We'll go into more
detail on the finder class and finder methods in Step 2.  

For our example, we'll use the following ID value for our query. Note that we
used a backslash "`\`" character to break the line in order to fit the ID value
on this page:

    com.nosester.portlet.eventlisting.service.persistence.\
    EventFinder.findByEventNameEventDescriptionLocationName

Custom SQL must be wrapped in character data (`CDATA`) for the `sql`
element. Importantly, the SQL must *not* be terminated with a semi-colon. 

Following these rules, let's specify the custom SQL for our Event Listing
portlet by replacing the contents of the `default.xml` file with the following
code: 

    <?xml version="1.0" encoding="UTF-8"?>
    <custom-sql>
        <sql id="com.nosester.portlet.eventlisting.service.persistence.EventFinder.\
    findByEventNameEventDescriptionLocationName">
            SELECT Event_Event.*
            FROM Event_Event
            INNER JOIN 
                Event_Location ON Event_Event.locationId = Event_Location.locationId
            WHERE
                (Event_Event.name LIKE ?) AND
                (Event_Event.description LIKE ?) AND
                (Event_Location.name LIKE ?)
        </sql>
    </custom-sql>

Make sure to delete the backslash "`\`" character from the end of the ID so that
the finder method name `findByEventNameEventDescriptionLocationName` immediately
follows the package path specified below:

    com.nosester.portlet.eventlisting.service.persistence.` 
  
Now that we've specified our custom SQL, let's implement the finder method.
We'll use the method name of the ID we just specified for our `sql` element. 

### Step 2: Implement Your Finder Method

Let's implement our *finder method* to invoke our custom SQL query. Since our
finder method will invoke our query on the database, we'll implement the method
in our service's persistence layer. And we'll rely on Service Builder to
generate the interface for it. Let's get started implementing the finder method
for the custom SQL we created. 

Let's create a `-FinderImpl` class in the service persistence package of our
portlet. Create a class called `EventFinderImpl` in our portlet's
`com.nosester.portlet.eventlisting.service.persistence.impl` package. Then, make
the class extend `BasePersistenceImpl<Event>`. Your `EventFinderImpl.java` file
should now have the following contents: 

    package com.nosester.portlet.eventlisting.service.persistence;

    import com.liferay.portal.service.persistence.impl.BasePersistenceImpl;
    import com.nosester.portlet.eventlisting.model.Event;

    public class EventFinderImpl extends BasePersistenceImpl<Event> {

    }

Run Service Builder to generate the `-Finder` interface and the `-Util` class
for our finder. Service Builder generates the `EventFinder` interface and the
`EventFinderUtil` utility class based on our `EventFinderImpl` class. Modify
your `EventFinderImpl` class to have it implement the `EventFinder` interface
you just generated: 

    public class EventFinderImpl extends BasePersistenceImpl<Event>
        implements EventFinder {

    }

Now we can create our finder method in our `EventFinderImpl` class. Add
the following finder method and static field to the `EventFinderImpl` class:

    public List<Event> findByEventNameEventDescriptionLocationName(
        String eventName, String eventDescription, String locationName,
        int begin, int end) {
                
        Session session = null;
        try {
            session = openSession();

            String sql = CustomSQLUtil.get(
                FIND_BY_EVENTNAME_EVENTDESCRIPTON_LOCATIONNAME);
            
            SQLQuery q = session.createSQLQuery(sql);
            q.setCacheable(false);
            q.addEntity("Event_Event", EventImpl.class);

            QueryPos qPos = QueryPos.getInstance(q);  
            qPos.add(eventName);
            qPos.add(eventDescription);
            qPos.add(locationName);
            
            return (List<Event>) QueryUtil.list(q, getDialect(), begin, end);
        } catch (Exception e) {
            try {
                throw new SystemException(e);
            } catch (SystemException se) {
                se.printStackTrace();
            }
        } finally {
            closeSession(session);
        }

        return null;
    }	

    public static final String FIND_BY_EVENTNAME_EVENTDESCRIPTON_LOCATIONNAME =
        EventFinder.class.getName() +
            ".findByEventNameEventDescriptionLocationName";

The custom finder method opens a new Hibernate session and use Liferay's
`CustomSQLUtil`'s `get(String id)` method to get the custom SQL to use for the
database query. The `FIND_BY_EVENTNAME_EVENTDESCRIPTON_LOCATIONNAME` static
field contains the ID of the custom SQL query. We define the
`FIND_BY_EVENTNAME_EVENTDESCRIPTON_LOCATIONNAME` string based on the
fully-qualified class name of the our `-Finder` interface (`EventFinder`) and
the name of our finder method (`findByEventNameEventDescriptionLocationName`). 

Awesome! Our custom SQL is in place and our finder method is implemented. Next,
we'll access our finder method from our service.  

### Step 3: Access Your Finder Method from Your Service

So far, we have created a `-FinderImpl` class and generated a `-FinderUtil`
utility class. However, our portlet class should not use the finder utility
class directly; only a local or remote *service* implementation (i.e.,
`-LocalServiceImpl` or `-ServiceImpl`) in our plugin project should invoke the
`-FinderUtil` class. This encourages a proper separation of concerns: the
portlet classes invoke business logic of the services and the services in turn
access the data model using the persistence layer's finder classes. So we'll add
a method in our `-LocalServiceImpl` class that invokes the finder method
implementation via the `-FinderUtil` class. Then we'll provide our portlet and
JSPs access to this service method by rebuilding the service.

Add the following method to the `EventLocalServiceImpl` class of our Event
Listing portlet: 

    public List<Event> findByEventNameEventDescriptionLocationName(String eventName,
        String eventDescription, String locationName, int begin, int end)
    throws SystemException {
		
        return EventFinderUtil.findByEventNameEventDescriptionLocationName(
            eventName, eventDescription, locationName, begin, end);
    }	

Run Service Builder after adding this method to generate the interface and make
this finder method available in the `EventLocalServiceUtil` class. 

Now we can indirectly call the finder method from our portlet class or from a
JSP in our portlet by calling
`EventLocalServiceUtil.findByEventNameEventDescriptionLocationName(...)`. It's
just that easy! 

<!-- Note, the view JSP currently does not call
EventLocalServiceUtil.findByEventNameEventDescriptionLocationName(...). We may
want to include this in the solution. - Jim --> 

Congratulations on following the 3 step process in developing a custom SQL query
and custom finder for your portlet! 

Next we'll take a tour through the `service.properties` file that Service
Builder generates. We'll explain the significance of its properties and how you
can override them, as necessary. 

<!--
## Leveraging Dynamic Query 
-->

## Configuring `service.properties` 

Service Builder generates a `service.properties` file in your project's
`docroot/WEB-INF/src` folder. Liferay Portal uses the properties in this file to
alter your service's database schema and load Spring configuration files to
support deployment of your service. You should not modify this file, but rather
make any necessary overrides in a `service-ext.properties` file in that same
folder.

The only property that you may need to override from this file is
`build.auto.upgrade`. Setting `build.auto.upgrade=false` in your
`service-ext.properties` file prevents Liferay from trying to automatically
apply any changes to the database model, when a new version of the plugin is
deployed. This is needed in projects in which it is preferred to manually manage
the changes to the database or in which the SQL schema has intentionally been
modified manually after generation by Service Builder.

Here are the other properties included in the `service.properties` file:

- `build.namespace`: This is the namespace you defined in
  `docroot/WEB-INF/service.xml`. Liferay distinguishes different plugins from
  each other using their namespaces.
- `build.number`: Liferay distinguishes different builds of your plugin. Each
  time a distinct build of your plugin is deployed to Liferay, Liferay
  increments this number.
- `build.date`: This is the time of the latest build of your plugin.
- `spring.configs`: This is a comma-delimited list of Spring configurations.
- `include-and-override`: The default value of this property defines
  `service-ext.properties` as an override file for `service.properties`.

<!-- Missing transition. --> 

## Summary

We've covered a lot of ground in this chapter. We showed you how to map out your
data model as entities to use in services. We used Service Builder and Liferay
IDE's powerful editing modes to create services, relate service entities, and
generate our implementation stubs. We implemented our business logic and re-ran
Service Builder to generate the corresponding interfaces. We found out that
integrating our own SQL queries in our services was easy and that model hints
enable us to specify service entity data limitations and to make entity display
customizations. We also briefly discussed remote services but we merely
scratched the surface of this topic. 

In the next chapter, we'll show you how to find and invoke other remote Liferay
services. We'll take an in-depth look at Liferay's service security layer. And
last but not least, we'll dive deep into SOAP web services and JSON web
services. So hold on tight, you're about to get served a big helping of Liferay
services. 
