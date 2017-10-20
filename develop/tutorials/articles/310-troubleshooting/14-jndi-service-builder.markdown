# Using JNDI from Service Builder [](id=using-jndi-from-service-builder)

There may be cases in which @product@'s application server defines a JNDI data 
source that you want to use from a 
[Liferay Service Builder](/develop/tutorials/-/knowledge_base/7-0/service-builder) 
module. Follow these steps to use such a data source: 

1.  In your Service Builder module's `service.xml` file, specify a data source 
    for the entity. Here's an example: 

        <?xml version="1.0"?>
        <!DOCTYPE service-builder PUBLIC "-//Liferay//DTD Service Builder 7.0.0//EN"
            "http://www.liferay.com/dtd/liferay-service-builder_7_0_0.dtd">

        <service-builder package-path="com.liferay.example" >
            <namespace>TestDB</namespace>
            <entity local-service="true" name="Foo" table="testdata" data-source="extDataSource"
                    remote-service="false" uuid="false">
   	            <column name="id" db-name="id" primary="true" type="long" />
   	            <column name="foo" db-name="foo" type="String" />
   	            <column name="bar" db-name="bar" type="long" />
            </entity>
        </service-builder>

    Note the following `<entity>` tag attributes from this example: 

    -   `data-source`: The data source's name. You can choose whatever name you
        want. You'll create a Spring bean with the same name that points to the
        JNDI data source you want to use. 
    -   `table`: The name of the database table that corresponds to your
        entity. 

        Also note that in each of your entity's `<column> tags`, you must set
        the  `db-name` attribute to the name of that entity's database column. 

2.  Create a Spring bean that points to the JNDI data source you want to use. To
    do this, create a file called `ext-spring.xml` in your Service Builder
    module's `src/main/resources/META-INF/spring` folder. In this file, define
    the following: 

    -   A data source factory Spring bean for the JNDI data source you want to
        use. 
    -   A @product@ data source bean that refers to the data source factory
        Spring bean.  
    -   An alias for the @product@ data source bean. 

    Here's an example: 

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

    The XML code above overrides data source bean `liferayDataSource` with one
    that refers to a data source whose JNDI name is `jdbc/externalDataSource`.
    Alias `extDataSource` refers to the overridden data source bean
    `liferayDataSource`. The example `service.xml` file uses the alias to refer
    to the data source bean. 

3.  Run Service Builder. To do this via the command line for a Gradle project, 
    navigate to your Service Builder module project's root folder and run 
    `gradlew buildService`. Note that you can also run Service Builder from 
    within Liferay @ide@. For more information on this, see 
    [the tutorial on running Service Builder](/develop/tutorials/-/knowledge_base/7-0/running-service-builder-and-understanding-the-generated-code). 

Now the Liferay services use the external JNDI data source. You can implement
[business logic with the services](/develop/tutorials/-/knowledge_base/7-0/business-logic-with-service-builder)
the same way regardless of the data source. 

## Related Topics [](id=related-topics)

[Connecting to Data Sources Using JNDI](/develop/tutorials/-/knowledge_base/7-0/connecting-to-data-sources-using-jndi)

[Service Builder](/develop/tutorials/-/knowledge_base/7-0/service-builder)

[Running Service Builder and Understanding the Generated Code](/develop/tutorials/-/knowledge_base/7-0/running-service-builder-and-understanding-the-generated-code)

[Business Logic with Service Builder](/develop/tutorials/-/knowledge_base/7-0/business-logic-with-service-builder)
