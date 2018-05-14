# Point all Nodes to the Same @Product@ Database 

@product@ allows you to use two different data sources for reading and writing.
This enables you to split your database infrastructure into two sets: one
optimized for reading and one optimized for writing. Since all @product@'s
supported databases support replication, you can use your database vendor's
replication mechanism to keep the database nodes in sync.

Enabling a read-writer database is simple. In your `portal-ext.properties` file:

1.  Set the default database connection pool provider to `dbcp`, `tomcat`, or 
    `c3po`. Note, provider HikariCP does not support read/write splitting.
    Here's an example setting: 

        jdbc.default.liferay.pool.provider=dbcp

    All the portal JDBC configuration properties are documented
    [here](@platform-ref@/7.0-latest/propertiesdoc/portal.properties.html#JDBC).

2.  Configure two different data sources for @product@ to use, one for reading,
    and one for writing:

        jdbc.read.driverClassName=com.mysql.jdbc.Driver
        jdbc.read.url=jdbc:mysql://dbread.com/lportal?useUnicode=true&characterEncoding=UTF-8&useFastDateParsing=false
        jdbc.read.username=**your user name**
        jdbc.read.password=**your password**

        jdbc.write.driverClassName=com.mysql.jdbc.Driver
        jdbc.write.url=jdbc:mysql://dbreadwrite.com/lportal?useUnicode=true&characterEncoding=UTF-8&useFastDateParsing=false
        jdbc.write.username=**your user name**
        jdbc.write.password=**your password**

    To use the JNDI instead of the JDBC data sources, set the `*.username` and
    `*.password` properties above to your JNDI user name and password and set
    these additional properties:

        jdbc.read.jndi.name=**your read JNDI name**
        jdbc.write.jndi.name=**your read-write JNDI name**

3.  Avoid using the `default` data source, by setting this:

        counter.jdbc.prefix=jdbc.write.

    And if you're using a `dbcp` or `tomcat` database connection pool provider,
    set these:

        jdbc.default.validationQuery=
        jdbc.read.validationQuery=SELECT releaseId FROM Release_
        jdbc.write.validationQuery=SELECT releaseId FROM Release_

    These settings are related to issue [LPS-64624](https://issues.liferay.com/browse/LPS-64624).

4.  Enable the read-writer database configuration by uncommenting the following 
    Spring configuration files from the `spring.configs` and
    `spring.infrastructure.configs` properties:

        spring.configs=\
            [..]
            META-INF/dynamic-data-source-spring.xml,\
            [..]

        spring.infrastructure.configs=\
            [..]
            META-INF/dynamic-data-source-infrastructure-spring.xml,\
            [..]

    The Spring configuration portal properties are documented 
    [here](@platform-ref@/7.0-latest/propertiesdoc/portal.properties.html#Spring).

The next time you start @product@, it uses the two data sources you have
defined. Make sure to correctly set up your two databases for replication before
starting @product@.
