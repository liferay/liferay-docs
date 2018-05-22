# Connecting Service Builder to External Databases [](id=connecting-service-builder-to-external-databases)

Sometimes you want to use a database other than @product@'s. To do this, its
data source must be defined in `portal-ext.properties` or configured as a JNDI
data source on the app server. This tutorial shows how to connect
[Service Builder](/develop/tutorials/-/knowledge_base/7-1/service-builder) to
a data source. Here's how: 

1. [Specify the data source.](#step-1-specify-the-data-source)

2. [Create a Spring bean that points to the data source.](#step-2-create-a-spring-bean-that-points-to-the-data-source)

3. [Set your entity's data source to the `liferayDataSource` alias.](#step-3-set-your-entitys-data-source-to-the-liferaydatasource-alias)

4. [Run Service Builder.](#step-4-run-service-builder)

First, use portal properties to set your data source. 

## Step 1: Specify the Data Source [](id=step-1-specify-the-data-source)

If the application server defines the data source using JNDI, skip this  step.
Otherwise, specify the data source in a `portal-ext.properties` file.
Distinguish it from Liferay's default data source by giving it a prefix other
than `jdbc.default.`. This example uses prefix `jdbc.ext.`:

    jdbc.ext.driverClassName=org.mariadb.jdbc.Driver
    jdbc.ext.password=userpassword
    jdbc.ext.url=jdbc:mariadb://localhost/external?useUnicode=true&characterEncoding=UTF-8&useFastDateParsing=false
    jdbc.ext.username=yourusername

## Step 2: Create a Spring Bean that Points to the Data Source [](id=step-2-create-a-spring-bean-that-points-to-the-data-source)

To do this, create an `ext-spring.xml` file in your Service Builder module's
`src/main/resources/META-INF/spring` folder or in your traditional portlet's
`WEB-INF/src/META-INF` folder. Define the following elements: 

1.  A data source factory Spring bean for the data source. It's different based
    on the type.

    - **JNDI**: Specify an arbitrary property prefix and prepend the prefix to a 
    JNDI name property key. Here's an example:

            <bean class="com.liferay.portal.dao.jdbc.spring.DataSourceFactoryBean"
                id="liferayDataSourceFactory">
                <property name="propertyPrefix" value="custom." />
                <property name="properties">
                    <props>
                        <prop key="custom.jndi.name">jdbc/externalDataSource</prop>
                    </props>
                </property>
            </bean>

    - **Portal Properties**: Specify a property prefix that matches the prefix 
    (e.g., `jdbc.ext.`) you used in `portal-ext.properties`.

            <bean class="com.liferay.portal.dao.jdbc.spring.DataSourceFactoryBean"
                id="liferayDataSourceFactory">
                <property name="propertyPrefix" value="jdbc.ext." />
            </bean>

2.  A Liferay data source bean that refers to the data source factory Spring bean.

3.  An alias for the Liferay data source bean. 

Here's an example `ext-spring.xml` that points to a JNDI data source: 

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

The `liferayDataSourceFactory` above refers to a JNDI data source named
`jdbc/externalDataSource`. If the data source is in a `portal-ext.properties`
file, the bean requires only a `propertyPrefix` property that matches the data
source property prefix.

The data source bean `liferayDataSource` is overridden with one that refers to
the `liferayDataSourceFactory` bean. The override affects this bundle (module or
[Web Application Bundle](/develop/tutorials/-/knowledge_base/7-1/using-the-wab-generator)) 
only. 

The alias `extDataSource` refers to the `liferayDataSource` data source bean. 

+$$$

**Note**: To use an external data source in multiple Service Builder
bundles, you must override the `liferayDataSource` bean in each bundle. 

$$$

## Step 3: Set Your Entity's Data Source to the `liferayDataSource` Alias [](id=step-3-set-your-entitys-data-source-to-the-liferaydatasource-alias)

In your `service.xml` file, set your entity's data source to the
`liferayDataSource` alias you specified in your `ext-spring.xml` file. Here's
an example: 

    <?xml version="1.0"?>
    <!DOCTYPE service-builder PUBLIC "-//Liferay//DTD Service Builder 7.1.0//EN"
        "http://www.liferay.com/dtd/liferay-service-builder_7_1_0.dtd">

    <service-builder package-path="com.liferay.example" >
        <namespace>TestDB</namespace>
        <entity local-service="true" name="Foo" table="testdata" data-source="extDataSource"
                remote-service="false" uuid="false">
               <column name="id" db-name="id" primary="true" type="long" />
               <column name="foo" db-name="foo" type="String" />
               <column name="bar" db-name="bar" type="long" />
        </entity>
    </service-builder>

Note the example's `<entity>` tag attributes: 

*`data-source`*: The `liferayDataSource` alias `ext-spring.xml` specifies.

*`table`*: Your entity's database table. 

Also note that your entity's `<column>`s must have a `db-name` attribute set to 
the column name.

## Step 4: Run Service Builder [](id=step-4-run-service-builder)

[Run Service Builder](/develop/tutorials/-/knowledge_base/7-1/running-service-builder).
Now your Service Builder services use the data source. You can
[use the services in your business logic](/develop/tutorials/-/knowledge_base/7-1/business-logic-with-service-builder)
as you always have regardless of the underlying data source. 

+$$$

**Note**: Only one data source can be used in a Service Builder module. To use
three different external data sources with Service Builder, for example, you
must create three different Service Builder modules, one for each data source.

$$$

Congratulations! You've connected Service Builder to your external data source. 

## Related Topics [](id=related-topics)

[Connecting to JNDI Data Sources](/develop/tutorials/-/knowledge_base/7-1/connecting-to-data-sources-using-jndi)

[Service Builder](/develop/tutorials/-/knowledge_base/7-1/service-builder)

[Running Service Builder and Understanding the Generated Code](/develop/tutorials/-/knowledge_base/7-1/running-service-builder)

[Business Logic with Service Builder](/develop/tutorials/-/knowledge_base/7-1/business-logic-with-service-builder)
