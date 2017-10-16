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
            <entity local-service="true" name="Foo" table="testdata" data-source="yourDataSource"
                    remote-service="false" uuid="false">
   	            <column name="id" db-name="id" primary="true" type="long" />
   	            <column name="foo" db-name="foo" type="String" />
   	            <column name="bar" db-name="bar" type="long" />
            </entity>
        </service-builder>

    Note the following `<entity>` tag attributes from this example: 

    - `data-source`: The data source's name. You can choose whatever name you 
    want. You'll create a Spring bean with the same name that points to the JNDI 
    data source you want to use. 
    - `table`: The name of the database table that corresponds to your entity. 

    Also note that in each of your entity's `<column> tags`, you must set the 
    `db-name` attribute to the name of that entity's database column. 

2.  Create a Spring bean that points to the JNDI data source you want to use. To 
    do this, create a file called `ext-spring.xml` in your Service Builder 
    module's `src/main/resources/spring` folder. In this file, define the 
    following: 

    - A Spring bean for the JNDI data source you want to use. 
    - A @product@ data source bean that points to your JNDI data source bean. 
    - An alias for the @product@ data source bean. 

    Here's an example: 

        <?xml version="1.0" encoding="UTF-8"?>

        <beans default-destroy-method="destroy" default-init-method="afterPropertiesSet"
            xmlns="http://www.springframework.org/schema/beans" 
            xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
            xsi:schemaLocation="http://www.springframework.org/schema/beans 
                    http://www.springframework.org/schema/beans/spring-beans-3.0.xsd">

            <bean class="com.liferay.portal.dao.jdbc.spring.DataSourceFactoryBean"
   	            id="jndiDatasource">
   	            <property name="propertyPrefix" value="custom." />
   	            <property name="properties">
                    <props>
                        <prop key="custom.jndi.name">jdbc/externalDataSource</prop>
                    </props>
   	            </property>
            </bean>

            <bean class="org.springframework.jdbc.datasource.LazyConnectionDataSourceProxy"
                id="liferayDataSource">
   	            <property name="targetDataSource" ref="jndiDatasource" />
            </bean>

            <alias alias="extDataSource" name="liferayDataSource" />

        </beans>

3.  Run Service Builder. To do this via the command line for a Gradle project, 
    navigate to your Service Builder module project's root folder and run 
    `gradlew buildService`. Note that you can also run Service Builder from 
    within Liferay @ide@. For more information on this, see 
    [the tutorial on running Service Builder](/develop/tutorials/-/knowledge_base/7-0/running-service-builder-and-understanding-the-generated-code). 

4.  Now you can access the JNDI data source and use it however you wish. Here's 
    an example: 

        public void useJNDI() {

            Thread thread = Thread.currentThread();

            // Use Liferay's class loader
            ClassLoader origLoader = thread.getContextClassLoader();
            thread.setContextClassLoader(PortalClassLoaderUtil.getClassLoader());

            try {
                // Invoke JNDI Resource
                DataSource datasource = fooPersistence.getDataSource();

                Connection connection = datasource.getConnection();
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery("select fooId, name from foo");

                while (resultSet.next()) {
                    System.out.println("Record:");
                    String id = resultSet.getString("id");
                    System.out.println("ID: " + id);
                    String foo = resultSet.getString("foo");
                    System.out.println("Foo: " + foo);
                    String bar = resultSet.getString("bar");
                    System.out.println("Bar: " + bar);
                    System.out.println();
                }

                connection.close();
            }
            catch (SQLException sqle) {
                sqle.printStackTrace();
            }
            finally {
                // Switch back to the original class loader
                thread.setContextClassLoader(origLoader);
            }
        }

    Note the use of `fooPersistence.getDataSource()`. Invoking this method is 
    how to take advantage of Service Builder's data source integration. Service 
    Builder injects the Spring data source bean that you defined in your 
    `ext-spring.xml` file into your application's persistence layer. Of course, 
    replace the example data source code with your own. Here are the fully 
    qualified class names of the classes used in the example: 

        import com.liferay.example.service.base.FooLocalServiceBaseImpl;
        import java.sql.Connection;
        import java.sql.ResultSet;
        import java.sql.SQLException;
        import java.sql.Statement;
        import javax.sql.DataSource;

    In the example above, the `javax.naming` API comes from the application 
    server. @product@ lets its OSGi runtime access this API. Thus, to create a 
    context reflecting what @product@ can access, create a 
    `javax.naming.InititalContext` using @product@'s class loader. You can do 
    this by setting the thread's context class loader to @product@'s class 
    loader. 

Note that the technique shown here is very similar to that used in the tutorial 
[Connecting to Data Sources Using JNDI](/develop/tutorials/-/knowledge_base/7-0/connecting-to-data-sources-using-jndi). 
The main difference is that in a Service Builder module, the data source is 
built into the persistence layer. 

Also note that exactly how to define a JNDI data source depends on your 
application server. If you need help defining a JNDI data source, see the 
documentation for your application server. If you're running Tomcat, for 
example, see 
[Tomcat's JNDI Datasource HOW-TO](https://tomcat.apache.org/tomcat-8.0-doc/jndi-datasource-examples-howto.html). 

## Related Topics [](id=related-topics)

[Connecting to Data Sources Using JNDI](/develop/tutorials/-/knowledge_base/7-0/connecting-to-data-sources-using-jndi)

[Service Builder](/develop/tutorials/-/knowledge_base/7-0/service-builder)

[Running Service Builder and Understanding the Generated Code](/develop/tutorials/-/knowledge_base/7-0/running-service-builder-and-understanding-the-generated-code)
