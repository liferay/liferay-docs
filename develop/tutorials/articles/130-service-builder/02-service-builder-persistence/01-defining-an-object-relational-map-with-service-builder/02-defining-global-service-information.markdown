## Step 2: Defining Global Service Information [](id=step-2-defining-global-service-information)

A service's global information applies to all of its entities, so it's a good
place to start. In Liferay @ide@, select the *Service Builder* node in the upper
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
