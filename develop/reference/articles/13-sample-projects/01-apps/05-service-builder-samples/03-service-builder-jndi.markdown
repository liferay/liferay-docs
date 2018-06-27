# Service Builder Application Using External Database via JNDI [](id=service-builder-application-using-external-database-via-jndi)

## What does this sample do when it's deployed? [](id=what-does-this-sample-do-when-its-deployed)

This sample demonstrates how to connect a Liferay Service Builder application to
an external database via a JNDI connection. Here, an external database means any
database other than @product@'s database. For this sample to work correctly, you
must prepare such an external database and configure @product@ to use it. Follow
the steps below to make the required preparations before deploying the
application.

1.  Create the external database to which your Service Builder application will
    connect. For example, create a MariaDB database called `external`. Add a
    table to this database called `region` with a `BIGINT` column called `Id`
    and a `VARCHAR(255)` column called `Name`. Add at least one record to this
    table. Here are the MariaDB commands to accomplish this:

        create database external character set utf8;

        use external;

        create table region(id bigint not null primary key, name varchar(255));

        insert into region(id, name) values(1, 'Tasmania');

    Make sure that your database commands were successful: Running `select *
    from region;` should return the record you added.

2.  Now you need to define a JNDI connection to your database. The way this is
    done depends on your application server. Here we demonstrate how to specify
    the JNDI connection for Tomcat. First, edit your
    `[LIFERAY_HOME]/tomcat-8.0.32/conf/server.xml` file and add this resource
    element inside of the `<GlobalNamingResources>` element:

        <Resource
            name="jdbc/externalDataSource"
            auth="Container"
            type="javax.sql.DataSource"
            factory="org.apache.tomcat.jdbc.pool.DataSourceFactory"
            driverClassName="org.mariadb.jdbc.Driver"
            url="jdbc:mariadb://localhost/external"
            username="yourusername"
            password="yourpassword"
            maxActive="20"
            maxIdle="5"
            maxWait="10000"
        />

    Replace the specified username and password with the correct values for your
    database.

3.  Edit your `[LIFERAY_HOME]/tomcat-8.0.32/conf/context.xml` file and add
    this resource link element inside of the `<Context>` element:

        <ResourceLink name="jdbc/externalDataSource" global="jdbc/externalDataSource" type="javax.sql.DataSource"/>

    Now your data source is defined at Tomcat's scope.

4.  Create a `com.liferay.blade.samples.jndiservicebuilder.service-log4j-ext.xml`
    in your @product@ instance's `[LIFERAY_HOME]/osgi/log4` folder. Create this
    folder if it doesn't yet exist. Add this content to the XML file that you
    created:

        <?xml version="1.0"?>
        <!DOCTYPE log4j:configuration SYSTEM "log4j.dtd">

        <log4j:configuration xmlns:log4j="http://jakarta.apache.org/log4j/">
            <category name="com.liferay.blade.samples.jndiservicebuilder.service.impl">
                <priority value="INFO" />
            </category>
        </log4j:configuration>

    This XML file defines the log level for the classes in the
    `com.liferay.blade.samples.jndiservicebuilder.service.impl` package. The
    `com.liferay.blade.samples.jndiservicebuilder.service.impl.RegionLocalServiceImpl`
    is the class that will produce log messages when the sample portlet is
    viewed.

Now your sample is ready for deployment! Make sure to build and deploy each of
the three modules that comprise the sample application:

- `jndi-api`
- `jndi-service`
- `jndi-web`

After these modules have been deployed, add the `jndi-web` portlet to a
@product@ page.

![Figure 1: This sample prints out the values previously inputted into the database.](../../../../images/jndi-sb-sample.png)

A sample table is printed in the portlet's view, representing the info inputted
into the database.

## What API(s) and/or code components does this sample highlight? [](id=what-apis-and-or-code-components-does-this-sample-highlight)

This sample demonstrates two ways to access data from an external database
defined by a JNDI connection:

- extract data directly from the raw data source by explicitly specifying a SQL
  query.
- read data using the helper methods that Service Builder generates in your
  application's persistence layer.

## How does this sample leverage the API(s) and/or code component? [](id=how-does-this-sample-leverage-the-apis-and-or-code-component)

Once you've added the `jndi-web` portlet to a page, the
`RegionLocalServiceUtil.useJNDI` method is invoked. This method accesses the
database defined by the JNDI connection you specified and logs information about
the rows in the `region` table to @product@'s log.

The first way of accessing data from the external database is to extract data directly from
the raw data source by explicitly specifying a SQL query. This technique is
demonstrated by the `RegionLocalServiceImpl.useJNDI` method. That method obtains
the Spring-defined data source that's injected into the `regionPersistence`
bean, opens a new connection, and reads data from the data source. This is the
technique used by the sample application to write the data to @product@'s log.

The second way of accessing data from the external database is to read data
using the helper methods that Service Builder generates in your application's
persistence layer. This technique is demonstrated by the `UseJNDI.getRegions`
method which first obtains an instance of the `RegionLocalService` OSGi service
and then invokes `regionLocalService.getRegions`. The
`regionLocalService.getRegions` and `regionLocalService.getRegionsCount` methods
are two examples of the persistence layer helper methods that Service Builder
generates. This is the technique used by the sample application to actually
display the data. The portlet's `view.jsp` uses the `<search-container>` JSP tag
to display a list of results. The results are obtained by the
`UseJNDI.getRegions` method mentioned above. 
