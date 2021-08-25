---
header-id: connecting-the-data-source-using-spring-beans
---

# Connecting the Data Source Using Spring Beans

[TOC levels=1-4]

Sometimes you want to use a database other than @product@'s. To do this, its
data source must be defined in `portal-ext.properties` or configured as a JNDI
data source on the app server. Here you'll connect
[Service Builder](/docs/7-2/appdev/-/knowledge_base/a/service-builder)
to a data source using Spring XML files. This approach only works with Service
Builder modules that use the `spring`
[dependency injection option](/docs/7-2/appdev/-/knowledge_base/a/defining-global-service-information#dependency-injector).
Here are the steps:

1. Specify your database and a data source name in your `service.xml`.

1. Create the database manually.

1. Define the data source.

1. Create a Spring bean that points to the data source.

1. Run Service Builder.

| **Note:** All entities defined in a Service Builder module's
| [`service.xml`](/docs/7-2/appdev/-/knowledge_base/a/creating-the-service-xml-file)
| file are bound to the same data source. Binding different entities to
| different data sources requires defining the entities in separate Service
| Builder modules and configuring each of the modules to use a different data
| source.

| **Warning:** If your Service Builder services require nested transactions,
| using an external data source may not be appropriate for you. Transactions
| between separate data sources cannot be fully nested. Rollbacks may not
| propagate between a module that uses an external data source and @product@
| services (or another app's services) that use a different data source.

| **Important:** Connecting to an external data source using JNDI is broken in
| Portal CE 7.2 GA1 and GA2, and in DXP 7.2 releases prior to FP5/SP2. See
| [LPS-107733](https://issues.liferay.com/browse/LPS-107733) for details.

## Specify Your Database and a Data Source Name in Your `service.xml`

In your `service.xml` file, specify the same arbitrary data source name for all of the entities, a unique table name for each entity, and  a database column name for each column. Here's an example:

```xml
<?xml version="1.0"?>
<!DOCTYPE service-builder PUBLIC "-//Liferay//DTD Service Builder 7.2.0//EN"
    "http://www.liferay.com/dtd/liferay-service-builder_7_2_0.dtd">

<service-builder dependency-injector="spring" package-path="com.liferay.example" >
    <namespace>TestDB</namespace>
    <entity local-service="true" name="Foo" table="testdata" data-source="extDataSource"
            remote-service="false" uuid="false">
           <column name="id" db-name="id" primary="true" type="long" />
           <column name="foo" db-name="foo" type="String" />
           <column name="bar" db-name="bar" type="long" />
    </entity>
</service-builder>
```

Note the example's `<entity>` tag attributes:

*`data-source`*: The `liferayDataSource` alias `ext-spring.xml` specifies.

*`table`*: Your entity's database table.

Also note that your entity's `<column>`s must have a *`db-name`* attribute set to the column name.

## Create the Database Manually

[Create the database](https://learn.liferay.com/dxp/latest/en/installation-and-upgrades/installing-liferay/configuring-a-database.html) per the database specification in your `service.xml`.

Next, use portal properties to set your data source.

## Define the Data Source

If the application server defines the data source using JNDI, skip this step.
Otherwise, specify the data source in a
[`portal-ext.properties` file](/docs/7-2/deploy/-/knowledge_base/d/portal-properties).
Distinguish it from Liferay's default data source by giving it a prefix other
than `jdbc.default.`. This example uses prefix `jdbc.ext.`:

```properties
jdbc.ext.driverClassName=org.mariadb.jdbc.Driver
jdbc.ext.password=userpassword
jdbc.ext.url=jdbc:mariadb://localhost/external?useUnicode=true&characterEncoding=UTF-8&useFastDateParsing=false
jdbc.ext.username=yourusername
```

Restart your server if you defined your data source using portal properties.

## Connect Your Service Builder Module to the Data Source Via a Spring Bean

To do this, create a parent context extension (e.g.,`ext-spring.xml`) in your
`*-service` module's `src/main/resources/META-INF/spring` folder or in
your traditional portlet's `WEB-INF/src/META-INF` folder. Create this
folder if it doesn't exist already.

Define the following elements:

1.  A data source factory Spring bean for the data source. It's different based
    on the type.

    -   **JNDI**: Specify an arbitrary property prefix and prepend the prefix
        to a JNDI name property key. Here's an example:

    ```xml
    <bean class="com.liferay.portal.dao.jdbc.spring.DataSourceFactoryBean"
        id="liferayDataSourceFactory">
        <property name="propertyPrefix" value="custom." />
        <property name="properties">
            <props>
                <prop key="custom.jndi.name">jdbc/externalDataSource</prop>
            </props>
        </property>
    </bean>
    ```

    -   **Portal Properties**: Specify a property prefix that matches the
        prefix (e.g., `jdbc.ext.`) you used in `portal-ext.properties`.

    ```xml
    <bean class="com.liferay.portal.dao.jdbc.spring.DataSourceFactoryBean"
        id="liferayDataSourceFactory">
        <property name="propertyPrefix" value="jdbc.ext." />
    </bean>
    ```

2.  A Liferay data source bean that refers to the data source factory Spring
    bean.

3.  An alias for the Liferay data source bean. Name the alias after the data source name you specified in the `service.xml`.

    Here's an example `ext-spring.xml` that points to a JNDI data source:

    ```xml
    <?xml version="1.0"?>

    <beans default-destroy-method="destroy" default-init-method="afterPropertiesSet"
       xmlns="http://www.springframework.org/schema/beans" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xsi:schemaLocation="http://www.springframework.org/schema/beans http://www.springframework.org/schema/beans/spring-beans-3.0.xsd">

       <!-- To define an external data source, the liferayDataSource Spring bean
           must be overridden. Other default Spring beans like liferaySessionFactory
           and liferayTransactionManager may optionally be overridden.

           liferayDataSourceFactory refers to the data source configured on the
           application server. -->
       <bean class="com.liferay.portal.dao.jdbc.spring.DataSourceFactoryBean"
           id="liferayDataSourceFactory">
           <property name="propertyPrefix" value="custom." />
           <property name="properties">
               <props>
                   <prop key="custom.jndi.name">jdbc/externalDataSource</prop>
               </props>
           </property>
       </bean>

       <!-- The data source bean refers to the factory to access the data source.
       -->
       <bean
           class="org.springframework.jdbc.datasource.LazyConnectionDataSourceProxy"
           id="liferayDataSource">
           <property name="targetDataSource" ref="liferayDataSourceFactory" />
       </bean>

       <!-- In service.xml, we associated our entity with the extDataSource. To
           associate the extDataSource with our overridden liferayDataSource, we define
           this alias. -->
       <alias alias="extDataSource" name="liferayDataSource" />
    </beans>
    ```

The `liferayDataSourceFactory` above refers to a JNDI data source named
`jdbc/externalDataSource`. If the data source is in a `portal-ext.properties`
file, the bean requires only a `propertyPrefix` property that matches the data
source property prefix.

The data source bean `liferayDataSource` is overridden with one that refers to
the `liferayDataSourceFactory` bean. The override affects this bundle (module or
[Web Application Bundle](/docs/7-2/customization/-/knowledge_base/c/deploying-wars-wab-generator))
only.

The alias `extDataSource` refers to the `liferayDataSource` data source bean.

| **Important:** The `alias` element's `alias` attribute value must match the
| data source name specified in the `service.xml`. For example, the alias
| attribute value above is `extDataSource`.

| **Note**: To use an external data source in multiple Service Builder
| bundles, you must override the `liferayDataSource` bean in each bundle.

## Run Service Builder

[Run Service Builder](/docs/7-2/appdev/-/knowledge_base/a/running-service-builder) and [deploy](/docs/7-2/reference/-/knowledge_base/r/deploying-a-project) your `-service` module.
Now your Service Builder services use the data source. You can
[use the services in your business logic](/docs/7-2/appdev/-/knowledge_base/a/business-logic-with-service-builder)
as you always have regardless of the underlying data source.

Congratulations! You've connected Service Builder to your external data source.

## Related Topics

[Sample Service Builder Application Using External Database via JNDI](/docs/7-2/reference/-/knowledge_base/r/service-builder-application-using-external-database-via-jndi)

[Sample Service Builder Application Using External Database via JDBC](/docs/7-2/reference/-/knowledge_base/r/service-builder-application-using-external-database-via-jdbc)

[Service Builder](/docs/7-2/appdev/-/knowledge_base/a/service-builder)

[Business Logic with Service Builder](/docs/7-2/appdev/-/knowledge_base/a/business-logic-with-service-builder)
