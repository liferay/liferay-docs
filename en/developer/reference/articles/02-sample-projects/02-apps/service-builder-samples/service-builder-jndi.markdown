---
header-id: service-builder-application-using-external-database-via-jndi
---

# Service Builder Application Using External Database via JNDI

[TOC levels=1-4]

The `apps/service-builder/jndi` sample demonstrates how to connect a Liferay Service Builder application to
an external database via a JNDI connection configured on the application server. Here, an external database means any
database other than @product@'s database. For this sample to work correctly, you
must prepare such an external database and configure your application server to use it.

| **Important:** Connecting to an external data source using JNDI is broken in
| Portal CE 7.2 GA1 and GA2, and in DXP 7.2 releases prior to FP5/SP2. See
| [LPS-107733](https://issues.liferay.com/browse/LPS-107733) for details.

Follow the steps below to make the required preparations before deploying the
application.

1.  Create an external database based on sample application's `service.xml`.

    `service.xml`:

    ```xml
    <?xml version="1.0"?>
    <!DOCTYPE service-builder PUBLIC "-//Liferay//DTD Service Builder 7.2.0//EN" "http://www.liferay.com/dtd/liferay-service-builder_7_2_0.dtd">

    <service-builder package-path="com.liferay.blade.samples.jndiservicebuilder">
    	<namespace>REGION</namespace>
    	<!--<entity data-source="sampleDataSource" local-service="true" name="Foo" remote-service="false" session-factory="sampleSessionFactory" table="bar" tx-manager="sampleTransactionManager uuid="true"">-->
    	<entity
    		data-source="extDataSource"
    		local-service="true"
    		name="Region"
    		remote-service="false"
    		table="region"
    		uuid="false"
    	>
    		<column db-name="id" name="regionId" primary="true" type="long" />
    		<column db-name="name" name="regionName" type="String" />
    	</entity>
    </service-builder>
    ```

    The entity's data source name `extDataSource` is arbitrary but must be specified in the data source configuration in the application server (next step).

    Here are MariaDB commands to create the database:

    ```bash
    create database external character set utf8;

    use external;

    create table region(id bigint not null primary key, name varchar(255));

    insert into region(id, name) values(1, 'Tasmania');
    ```

    The database name is arbitrary; the data source configuration in your application server (next step), however, must specify this same database. The database table called `region` represents the service entity. The table has a `BIGINT` column called `Id` and a `VARCHAR(255)` column called `Name`.

    Add at least one record to this table. Running `select * from region;` should return the record you added.

1.  In your application server configuration, define a JNDI connection to your database and map it to the `data-source` name (i.e., `extDataSource`) that the sample `service.xml` entities specify.

    For example, if Tomcat is your application server, open your `[LIFERAY_HOME]/tomcat-version/conf/server.xml` file and add a `Resource` element like this one inside of the `<GlobalNamingResources>` element:

    ```xml
    <Resource
        name="jdbc/externalDataSource"
        auth="Container"
        type="javax.sql.DataSource"
        factory="org.apache.tomcat.jdbc.pool.DataSourceFactory"
        driverClassName="org.mariadb.jdbc.Driver"
        url="jdbc:mariadb://localhost/external?useUnicode=true&amp;characterEncoding=UTF-8&amp;useFastDateParsing=false"
        username="[place user name here]"
        password="[place password here]"
        maxActive="20"
        maxIdle="5"
        maxWait="10000"
    />
    ```

    Replace the user name and password values and see the [Database Templates](/docs/7-2/deploy/-/knowledge_base/d/database-templates) for the URL parameters to use for your database.

1.  If you are using Tomcat, open your `[LIFERAY_HOME]/tomcat-version/conf/context.xml` file and add this resource link element inside of the `<Context>` element:

    ```xml
    <ResourceLink name="jdbc/externalDataSource" global="jdbc/externalDataSource" type="javax.sql.DataSource"/>
    ```

    Now your data source is defined at Tomcat's scope.

1.  Create a `com.liferay.blade.samples.jndiservicebuilder.service-log4j-ext.xml`
    in your @product@ instance's `[LIFERAY_HOME]/osgi/log4` folder. Create this
    folder if it doesn't yet exist. Add this content to the XML file that you
    created:

    ```xml
    <?xml version="1.0"?>
    <!DOCTYPE log4j:configuration SYSTEM "log4j.dtd">

    <log4j:configuration xmlns:log4j="http://jakarta.apache.org/log4j/">
        <category name="com.liferay.blade.samples.jndiservicebuilder.service.impl">
            <priority value="INFO" />
        </category>
    </log4j:configuration>
    ```

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

## What API(s) and/or code components does this sample highlight?

This sample demonstrates two ways to access data from an external database
defined by a JNDI connection:

- extract data directly from the raw data source by explicitly specifying a SQL
  query.
- read data using the helper methods that Service Builder generates in your
  application's persistence layer.

## How does this sample leverage the API(s) and/or code component?

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

## Additional Information

* [Connecting to an External Data Source](/docs/7-2/appdev/-/knowledge_base/a/connecting-service-builder-to-an-external-database)