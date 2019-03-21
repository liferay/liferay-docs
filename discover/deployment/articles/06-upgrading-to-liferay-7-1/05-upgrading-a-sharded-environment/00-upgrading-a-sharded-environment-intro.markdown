# Upgrading a Sharded Environment [](id=upgrading-sharded-environment)

Since @product@ 7.0, Liferay removed its own physical partitioning
implementation (also known as sharding) in favor of the capabilities provided
natively by database vendors. Upgrading a sharded installation to @product-ver@
requires migrating it to as many non-sharded @product@ installations (servers)
as you have shards. 

+$$$

**Note:** Liferay continues to support its logical partitioning capabilities 
(also known as instances) for the foreseeable future. 

$$$

Here are the upgrade steps:

1.  If you're on Liferay Portal 6.1 or lower,
    [upgrade to Liferay Portal 6.2](/discover/deployment/-/knowledge_base/6-2/upgrading-liferay). 

2.  [Remove unneeded objects from your portal](/discover/deployment/-/knowledge_base/7-1/pre-upgrade-speed-up-the-process). 

3.  [Prepare for upgrading to @product-ver@](/discover/deployment/-/knowledge_base/7-1/preparing-an-upgrade-to-liferay-7). 

4.  [Run the upgrade process for the default shard](/discover/deployment/-/knowledge_base/7-1/running-the-upgrade-process).
    The upgrade tool copies the control table entries from the default shard
    database to the non-default shard databases. You will need to include the jdbc connections for all shards in your portal-upgrade-database.properties. If you have 3 shards, something like this:

        jdbc.default.driverClassName=com.mysql.jdbc.Driver
        jdbc.default.url=jdbc:mysql://localhost/lportal?characterEncoding=UTF-8&dontTrackOpenResources=true&holdResultsOpenOverStatementClose=true&useFastDateParsing=false&useUnicode=true
        jdbc.default.username=
        jdbc.default.password=

        jdbc.one.driverClassName=com.mysql.jdbc.Driver
        jdbc.one.url=jdbc:mysql://localhost/lportal_one?characterEncoding=UTF-8&dontTrackOpenResources=true&holdResultsOpenOverStatementClose=true&useFastDateParsing=false&useUnicode=true
        jdbc.one.username=
        jdbc.one.password=

        jdbc.two.driverClassName=com.mysql.jdbc.Driver
        jdbc.two.url=jdbc:mysql://localhost/lportal_two?characterEncoding=UTF-8&dontTrackOpenResources=true&holdResultsOpenOverStatementClose=true&useFastDateParsing=false&useUnicode=true
        jdbc.two.username=
        jdbc.two.password=

5.  [Prepare a @product-ver@ server](/discover/deployment/-/knowledge_base/7-1/deploying-product)
    for each non-default shard database.

6.  [Run the upgrade process for every shard](/discover/deployment/-/knowledge_base/7-1/running-the-upgrade-process)
    for the @product-ver@ servers associated with each non-default shard
    database. You need to define each server's JDBC default properties in the portal-upgrade-database.properties:

    -   Add the original JDBC properties for the respective non-default shard 
        database. For example, shard `one`'s original properties might start with `jdbc.one.`:

            jdbc.one.driverClassName=com.mysql.jdbc.Driver
            jdbc.one.url=jdbc:mysql://localhost/lportal_one?characterEncoding=UTF-8&dontTrackOpenResources=true&holdResultsOpenOverStatementClose=true&useFastDateParsing=false&useUnicode=true
            jdbc.one.username=
            jdbc.one.password=

    -   Rename the properties to start with `jdbc.default.`. For example, 

            jdbc.default.driverClassName=com.mysql.jdbc.Driver
            jdbc.default.url=jdbc:mysql://localhost/lportal_one?characterEncoding=UTF-8&dontTrackOpenResources=true&holdResultsOpenOverStatementClose=true&useFastDateParsing=false&useUnicode=true
            jdbc.default.username=
            jdbc.default.password=

7.  Create a `portal-ext.properties` on each new server an define the each server's JDBC default properties as before.
    
8.  Remove the non-default shard JDBC properties from the original
    `portal-ext.properties` file (on the default shard server), leaving only
    the default shard database `jdbc.default.*` properties. For example,

    Old JDBC properties: 

        jdbc.default.driverClassName=com.mysql.jdbc.Driver
        jdbc.default.url=jdbc:mysql://localhost/lportal?characterEncoding=UTF-8&dontTrackOpenResources=true&holdResultsOpenOverStatementClose=true&useFastDateParsing=false&useUnicode=true
        jdbc.default.username=
        jdbc.default.password=

        jdbc.one.driverClassName=com.mysql.jdbc.Driver
        jdbc.one.url=jdbc:mysql://localhost/lportal_one?characterEncoding=UTF-8&dontTrackOpenResources=true&holdResultsOpenOverStatementClose=true&useFastDateParsing=false&useUnicode=true
        jdbc.one.username=
        jdbc.one.password=

        jdbc.two.driverClassName=com.mysql.jdbc.Driver
        jdbc.two.url=jdbc:mysql://localhost/lportal_two?characterEncoding=UTF-8&dontTrackOpenResources=true&holdResultsOpenOverStatementClose=true&useFastDateParsing=false&useUnicode=true
        jdbc.two.username=
        jdbc.two.password=

    New JDBC properties:

        jdbc.default.driverClassName=com.mysql.jdbc.Driver
        jdbc.default.url=jdbc:mysql://localhost/lportal?characterEncoding=UTF-8&dontTrackOpenResources=true&holdResultsOpenOverStatementClose=true&useFastDateParsing=false&useUnicode=true
        jdbc.default.username=
        jdbc.default.password=

Congratulations! You've migrated your original shards to separate @product-ver@
servers: 

-   One @product-ver@ server for the default shard
-   A @product-ver@ server for each non-default shard

For further assistance with sharding contact
[Liferay Global Services](https://www.liferay.com/consulting). 
