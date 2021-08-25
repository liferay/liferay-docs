---
header-id: tuning-guidelines
---

# Tuning Guidelines

[TOC levels=1-4]

<aside class="alert alert-info">
  <span class="wysiwyg-color-blue120">This document has been updated and ported to <a href="https://learn.liferay.com/dxp/latest/en/installation-and-upgrades/setting-up-liferay/tuning-liferay.html">Liferay Learn</a> and is no longer maintained here.</span>
</aside>

Although setting names may differ, these concepts apply to most application
servers. To keep things simple, Tomcat is used as the example. For other
application servers, consult the provider's documentation for specific settings.

Here are the tuning topics:

- Database Connection Pool
- Deactivating Development Settings in the JSP Engine
- Thread Pool

## Database Connection Pool

The database connection pool should be roughly 30-40% of the thread pool size.
It provides a connection whenever @product@ needs to retrieve data from the
database (e.g., user login). If the pool size is too small, requests queue in
the server waiting for database connections. If the size is too large, however,
idle database connections waste resources. As with thread pools, monitor
these settings and adjust them based on your performance tests.

In Tomcat, the connection pools are configured in the Resource elements in
`$CATALINA_HOME/conf/Catalina/localhost/ROOT.xml`. Liferay Engineering tests
with this configuration:

    <Resource auth="Container"         
        description="Digital Enterprise DB Connection"   
        driverClass="[place the driver class name here]"   
        maxPoolSize="75"   
        minPoolSize="10"           
        acquireIncrement="5"   
        name="jdbc/LiferayPool"  
        user="[place your user name here]"   
        password="[place your password here]"           
        factory="org.apache.naming.factory.BeanFactory"
        type="com.mchange.v2.c3p0.ComboPooledDataSource"
        jdbcUrl="[place the URL to your database here]"/>

This configuration starts with 10 threads and increments by 5 as needed to a
maximum of 75 connections in the pool.

There are a variety of database connection pool providers, including DBCP, C3P0,
HikariCP, and Tomcat. You may also configure the Liferay JDBC settings in your
[`portal-ext.properties`](https://docs.liferay.com/ce/portal/7.2-latest/propertiesdoc/portal.properties.html)
file. For example JDBC connection values, please see [Database Templates](/docs/7-2/deploy/-/knowledge_base/d/database-templates)

## Deactivating Development Settings in the JSP Engine

Many application servers' JSP Engines are in development mode by default.
Deactivate these settings prior to entering production:

**Development mode:** This makes the JSP container poll the file system for
changes to JSP files. Since you won't change JSPs on the fly like this in
production, turn off this mode.

**Mapped File:** Generates static content with one print statement versus one
statement per line of JSP text.

To disable these in Tomcat, for example, update the
`$CATALINA_HOME/conf/web.xml` file's JSP servlet configuration to this:

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

Development mode and mapped files are disabled.

## Thread Pool

Each request to the application server consumes a worker thread for the duration
of the request. When no threads are available to process requests, the request
is queued to wait for the next available worker thread. In a finely tuned
system, the number of threads in the thread pool are balanced with the total
number of concurrent requests. There should not be a significant amount of
threads left idle to service requests.

Use an initial thread pool setting of 50 threads and then monitor it within your
application server's monitoring consoles. You may wish to use a higher number
(e.g., 250) if your average page times are in the 2-3 second range. Too few
threads in the thread pool might queue excessive requests; too many threads can
cause excessive context switching.

In Tomcat, the thread pools are configured in the
`$CATALINA_HOME/conf/server.xml` file's `Connector` element. The
[Apache Tomcat documentation](https://tomcat.apache.org/tomcat-9.0-doc/config/http.html)
provides more details. Liferay Engineering tests with this configuration:

    <Connector maxThreads="75" minSpareThreads="50"
        maxConnections="16384" port="8080"     
        connectionTimeout="600000" redirectPort="8443"
        URIEncoding="UTF-8"  socketBuffer="-1"     
        maxKeepAliveRequests="-1" address="xxx.xxx.xxx.xxx"/>

Additional tuning parameters around Connectors are available, including the
connector types, the connection timeouts, and TCP queue. Consult your
application server's documentation for further details.
