# Service Builder Application Using External Database via JNDI

# Service Builder Application Using External Database via JNDI

This sample demonstrates how to connect a Liferay Service Builder application to
an external database via a JNDI connection. Here, an external database means any
database other than @product@'s database. For this sample to work correctly,
you must prepare such an external database and configure @product@ to use it.
Follow the steps in the next section to make the required preparations before
deploying the application.

## Deployment Preparation

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
    [LIFERAY_HOME]/tomcat-8.0.32/conf/server.xml file and add this resource
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

    Of course, replace the specified username and password with the correct
    values for your database.

3.  Next, edit your [LIFERAY_HOME]/tomcat-8.0.32/conf/context.xml file and add
    this resource link element inside of the `<Context>` element:

        <ResourceLink name="jdbc/externalDataSource" global="jdbc/externalDataSource" type="javax.sql.DataSource"/>

    Now your data source is defined at Tomcat's scope.

4.  Create a `com.liferay.blade.samples.jndiservicebuilder.service-log4j-ext.xml`
    in your Liferay instance's `[LIFERAY_HOME]/osgi/log4` folder. Create this
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
`com.liferay.blade.samples.jndiservicebuilder.service.impl.CountryLocalServiceImpl`
is the class that will produce log messages when the BLADE sample portlet is
viewed.

## Running the BLADE Sample

1. Build and deploy each of the three modules that comprise the BLADE sample
application:

- `jndi-api`
- `jndi-service`
- `jndi-web`

2. After these modules have been deployed, add the `jndi-web` portlet to a
Liferay page. When you view the page, the `RegionLocalServiceUtil.useJDBC`
method will be invoked. This method accesses the database defined by the JNDI
connection you specified and logs information about the rows in the `region`
table to Liferay's log. Check Liferay's log to make sure that the database
connection is working correctly.

## Explanation

This BLADE sample demonstrates two ways to access data from an external database
defined by a JNDI data source. The first way is to extract data directly from
the raw data source by explicitly specifying a SQL query. This technique is
demonstrated by the `RegionLocalServiceImpl.useJDBC` method. That method obtains
the Spring-defined data source that's injected into the `regionPersistence`
bean, opens a new connection, and reads data from the data source. This is the
technique used by the sample application to write the data to Liferay's log.

The second way is to read data using the helper methods that Service Builder
generates in your application's persistence layer. This technique is
demonstrated by the `UseJNDI.getRegions` method which first obtains an instance
of the `RegionLocalService` OSGi service and then invokes
`regionLocalService.getRegions`. `regionLocalService.getRegions` and
`regionLocalService.getRegionsCount` are two examples of the persistence layer
helper methods that Service Builder generates. This is the technique used by the
sample application to actually display the data. The portlet's `view.jsp` uses
the `<search-container>` JSP tag to display a list of results. The results are
obtained by the `UseJNDI.getRegions` method mentioned above. 
