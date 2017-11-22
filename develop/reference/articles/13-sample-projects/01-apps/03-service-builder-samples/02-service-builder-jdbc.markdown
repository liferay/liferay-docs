# Service Builder Application Using External Database via JDBC

This BLADE sample demonstrates how to connect a Liferay Service Builder application to an external database via a JDBC connection. Here, an external database means any database other than Liferay's database. For this BLADE sample to work correctly, you must prepare such an external database and configure Liferay to use it. Follow the steps in the next section to make the required preparations before deploying the application.

## Deployment Preparation

1. Create the external database to which your Service Builder application will connect. For example, create a MariaDB database called `external`. Add a table to this database called `country` with a BIGINT column called `Id` and a VARCHAR(255) column called `Name`. Add at least one record to this table. Here are the MariaDB commands to accomplish this:

	create database external character set utf8;

	use external;

	create table country(id bigint not null primary key, name varchar(255));

	insert into country(id, name) values(1, 'Australia');

Make sure that your database commands were successful: Running `select * from country;` should return the record you added.

2. Create a `portal-ext.properties` file in your Liferay instance's `[LIFERAY_HOME]` folder. (This folder should be marked by the presence of a `.liferay-home` file.) In your `portal-ext.properties` file, define the details of your JDBC data source connection:

	jdbc.ext.driverClassName=org.mariadb.jdbc.Driver
	jdbc.ext.password=userpassword
	jdbc.ext.url=jdbc:mariadb://localhost/external?useUnicode=true&characterEncoding=UTF-8&useFastDateParsing=false
	jdbc.ext.username=yourusername

Note that Liferay's primary data source is specified by the `jdbc.default` prefix. These details are often specified in a `portal-setup-wizard.properties` file. Here, we've chosen to use the `jdbc.ext` prefix for our alternate data source.

3. Create a `com.liferay.blade.samples.jdbcservicebuilder.service-log4j-ext.xml` in your Liferay instance's `[LIFERAY_HOME]/osgi/log4` folder. Create this folder if it doesn't yet exist. Add this content to the XML file that you created:

	<?xml version="1.0"?>
	<!DOCTYPE log4j:configuration SYSTEM "log4j.dtd">

	<log4j:configuration xmlns:log4j="http://jakarta.apache.org/log4j/">
		<category name="com.liferay.blade.samples.jdbcservicebuilder.service.impl">
			<priority value="INFO" />
		</category>
	</log4j:configuration>

This XML file defines the log level for the classes in the `com.liferay.blade.samples.jdbcservicebuilder.service.impl` package. The `com.liferay.blade.samples.jdbcservicebuilder.service.impl.CountryLocalServiceImpl` is the class that will produce log messages when the BLADE sample portlet is viewed.

## Running the BLADE Sample

1. Build and deploy each of the three modules that comprise the BLADE sample application:

- `jdbc-api`
- `jdbc-service`
- `jdbc-web`

2. After these modules have been deployed, add the `-web` portlet to a Liferay page. When you view the page, the `CountryLocalService.useJDBC` method will be invoked. This method accesses the database defined by the JDBC connection you specified and logs information about the rows in the `country` table to Liferay's log. Check Liferay's log to make sure that the database connection is working correctly.

## Explanation

This BLADE sample demonstrates two ways to access data from an external database defined by a JDBC connection. The first way is to extract data directly from the raw data source by explicitly specifying a SQL query. This technique is demonstrated by the `CountryLocalServiceImpl.useJDBC` method. That method obtains the Spring-defined data source that's injected into the `countryPersistence` bean, opens a new connection, and reads data from the data source. This is the technique used by the sample application to write the data to Liferay's log.

The second way is to read data using the helper methods that Service Builder generates in your application's persistence layer. This technique is demonstrated by the `UseJDBC.getCountries` method which first obtains an instance of the `CountryLocalService` OSGi service and then invokes `countryLocalService.getCountries`. `countryLocalService.getCountries` and `countryLocalService.getCountriesCount` are two examples of the persistence layer helper methods that Service Builder generates. This is the technique used by the sample application to actually display the data. The portlet's `view.jsp` uses the `<search-container>` JSP tag to display a list of results. The results are obtained by the `UseJDBC.getCountries` method mentioned above.