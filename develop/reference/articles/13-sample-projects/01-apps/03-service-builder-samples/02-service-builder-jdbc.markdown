# Service Builder Application Using External Database via JDBC [](id=service-builder-application-using-external-database-via-jdbc)

## What does this sample do when it's deployed? [](id=what-does-this-sample-do-when-its-deployed)

This sample demonstrates how to connect a Liferay Service Builder application to
an external database via a JDBC connection. Here, an external database means any
database other than @product@'s database. For this sample to work correctly, you
must prepare such an external database and configure @product@ to use it. Follow
the steps below to make the required preparations before deploying the
application.

1.  Create the external database to which your Service Builder application will
    connect. For example, create a MariaDB database called `external`. Add a
    table to this database called `country` with a `BIGINT` column called `Id`
    and a `VARCHAR(255)` column called `Name`. Add at least one record to this
    table. Here are the MariaDB commands to accomplish this:

        create database external character set utf8;

        use external;

        create table country(id bigint not null primary key, name varchar(255));

        insert into country(id, name) values(1, 'Australia');

    Make sure that your database commands were successful: Running `select *
    from country;` should return the record you added.

2.  Create a `portal-ext.properties` file in your @product@ instance's
    `[LIFERAY_HOME]` folder (this folder should be marked by the presence of a
    `.liferay-home` file). In your `portal-ext.properties` file, define the
    details of your JDBC data source connection:

        jdbc.ext.driverClassName=org.mariadb.jdbc.Driver
        jdbc.ext.password=userpassword
        jdbc.ext.url=jdbc:mariadb://localhost/external?useUnicode=true&characterEncoding=UTF-8&useFastDateParsing=false
        jdbc.ext.username=yourusername

    Note that @product@'s primary data source is specified by the `jdbc.default`
    prefix. These details are often specified in a
    `portal-setup-wizard.properties` file. Here, we've chosen to use the
    `jdbc.ext` prefix for our alternate data source.

3.  Create a `com.liferay.blade.samples.jdbcservicebuilder.service-log4j-ext.xml`
    in your Liferay instance's `[LIFERAY_HOME]/osgi/log4` folder. Create this
    folder if it doesn't yet exist. Add this content to the XML file that you
    created:

        <?xml version="1.0"?>
        <!DOCTYPE log4j:configuration SYSTEM "log4j.dtd">

        <log4j:configuration xmlns:log4j="http://jakarta.apache.org/log4j/">
            <category name="com.liferay.blade.samples.jdbcservicebuilder.service.impl">
                <priority value="INFO" />
            </category>
        </log4j:configuration>

    This XML file defines the log level for the classes in the
    `com.liferay.blade.samples.jdbcservicebuilder.service.impl` package. The
    `com.liferay.blade.samples.jdbcservicebuilder.service.impl.CountryLocalServiceImpl`
    is the class that will produce log messages when the sample portlet is
    viewed.

Now your sample is ready for deployment! Make sure to build and deploy each of
the three modules that comprise the sample application:

- `jdbc-api`
- `jdbc-service`
- `jdbc-web`

After these modules have been deployed, add the `-web` portlet to a @product@
page.

![Figure 1: This sample prints out the values previously inputted into the database.](../../../../images/jdbc-sb-sample.png)

A sample table is printed in the portlet's view, representing the info inputted
into the database.

## What API(s) and/or code components does this sample highlight? [](id=what-apis-and-or-code-components-does-this-sample-highlight)

This sample demonstrates two ways to access data from an external database
defined by a JDBC connection:

- extract data directly from the raw data source by explicitly specifying a SQL
  query.
- read data using the helper methods that Service Builder generates in your
  application's persistence layer.

## How does this sample leverage the API(s) and/or code component? [](id=how-does-this-sample-leverage-the-apis-and-or-code-component)

Once you've added the `-web` portlet to a page, the
`CountryLocalService.useJDBC` method is invoked. This method accesses the
database defined by the JDBC connection you specified and logs information about
the rows in the `country` table to @product@'s log.

The first way of accessing data from the external database is to extract it
directly from the raw data source by explicitly specifying a SQL query. This
technique is demonstrated by the `CountryLocalServiceImpl.useJDBC` method. That
method obtains the Spring-defined data source that's injected into the
`countryPersistence` bean, opens a new connection, and reads data from the data
source. This is the technique used by the sample application to write the data
to @product@'s log.

The second way of accessing data from the external database is to read data
using the helper methods that Service Builder generates in your application's
persistence layer. This technique is demonstrated by the `UseJDBC.getCountries`
method which first obtains an instance of the `CountryLocalService` OSGi service
and then invokes `countryLocalService.getCountries`. The
`countryLocalService.getCountries` and `countryLocalService.getCountriesCount`
methods are two examples of the persistence layer helper methods that Service
Builder generates. This is the technique used by the sample application to
actually display the data. The portlet's `view.jsp` uses the
`<search-container>` JSP tag to display a list of results. The results are
obtained by the `UseJDBC.getCountries` method mentioned above.
