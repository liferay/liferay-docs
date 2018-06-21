# Application Server Tuning [](id=application-server-tuning)

Although the actual setting names may differ, these concepts are applicable
across most application servers. For brevity, we will use Tomcat as an example.
For other application servers, consult the application server provider's
documentation for additional specific settings.

## Database Connection Pool [](id=database-connection-pool)

The database connection pool is usually sized at roughly 30-40% of the thread
pool size. The connection pool provides a connection whenever @product@ needs to
retrieve data from the database (e.g. user login, etc.). If this size is too
small, requests queue in the server waiting for database connections. Too large
a setting, however, means wasting resources with idle database connections. 

As with thread pools, monitor these settings and adjust them based on your
performance tests.

In Tomcat, the connection pools are configured in the Resource elements in
``$CATALINA_HOME/conf/ Catalina/localhost/ROOT.xml``. Liferay Engineering uses
the following configuration during testing:

    <Resource auth="Container"         
        description="Digital Enterprise DB Connection"   
        driverClass="com.mysql.jdbc.Driver"   
        maxPoolSize="75"   minPoolSize="10"           
        acquireIncrement="5"   
        name="jdbc/LiferayPool"  
        user="XXXXXX"   
        password="XXXXXXXXX"           
        factory="org.apache.naming.factory.BeanFactory"
        type="com.mchange.v2.c3p0.ComboPooledDataSource"
        jdbcUrl="jdbc:mysql://someServer:3306/liferay_dxp?useUnicode=true
        &amp;characterEncoding=UTF-8&amp;useFastDateParsing=false"/>
 
In this configuration, we start with 10 threads and increment by 5 as needed to
a maximum of 75 connections in the pool.

You may choose from a variety of database connection pool providers, including
DBCP, C3P0, HikariCP, and Tomcat. You may also choose to configure the Liferay
JDBC settings in your portal.properties.

## Deactivate Development Settings in the JSP Engine [](id=deactivate-development-settings-in-the-jsp-engine)

Many application servers have their JSP Engines configured for development mode
by default. Liferay recommends deactivating these settings prior to entering
production:

**Development mode:** This makes the JSP container poll the file system for
changes to JSP files. Since you won't be making changes on the fly like this in
production, you should turn this off. 

**Mapped File:** Generates static content with one print statement versus one
statement per line of JSP text.

To do this in Tomcat, you modify the `$CATALINA_HOME/conf/web.xml` file.
Update the JSP servlet to look like the following configuration:

    <servlet>   
        <servlet-name>jsp</servlet-name>
        <servlet-class>org.apache.jasper.servlet.JspServlet</servlet-class>   
        <init-param>    
            <param-name>development</param-name>    
            <param-value>false</param-value>   
        </init-param>   
        <init-param>    
            <param-name>mappedFile</param-name>    
            <param-value>false</param-value>   
        </init-param>   
        <load-on-startup>3</load-on-startup> 
    </servlet>

## Thread Pool [](id=thread-pool)

Each incoming request to the application server consumes a worker thread for the
duration of the request. When no threads are available to process requests, the
request is queued to wait for the next available worker thread. In a finely
tuned system, the number of threads in the thread pool should be balanced with
the total number of concurrent requests. There should not be a significant
amount of threads left idle to service requests. 

Liferay Engineering recommends an initial setting of 50 threads and then
monitoring it within your application server's monitoring consoles. You may wish
to use a higher number (e.g., 250) if your average page times are in the 2-3
seconds range. Too few threads in the thread pool may lead to excessive request
queuing while too many threads may lead to excessive context switching.

In Tomcat, the thread pools are configured in the Connector element in
`$CATALINA_HOME/conf/server.xml`. Further information can be found in the
[Apache Tomcat documentation](https://tomcat.apache.org/tomcat-8.0-doc/config/http.html).
Liferay Engineering used the following configuration during testing:

    <Connector maxThreads="75" minSpareThreads="50" 
        maxConnections="16384" port="8080"     
        connectionTimeout="600000" redirectPort="8443" 
        URIEncoding="UTF-8"  socketBuffer="-1"     
        maxKeepAliveRequests="-1" address="xxx.xxx.xxx.xxx"/>
 
Additional tuning parameters around Connectors are available, including the
connector types,  the connection timeouts, and TCP queue. Consult the
appropriate Tomcat documentation for further details.
