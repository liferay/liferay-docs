# Upgrading a Sharded environment [](id=upgrading-sharded-environment)

Liferay removed its own physical partitioning implementation (also known as
sharding) in favor of the capabilities provided natively by database vendors.
Upgrading a sharded installation to @product-ver@ requires migrating it to as
many non-sharded @product@ installations (servers) as you have shards. These
steps guide you through configuring the new @product@ servers to use your
formerly sharded data and upgrading the data to @product-ver@. 

+$$$

**Note:** Liferay continues to support its logical partitioning capabilities 
(also known as
[virtual instances](/discover/portal/-/knowledge_base/7-0/setting-up-a-liferay-instance))
for the foreseeable future. 

$$$

+$$$

For any further assistance with sharding contact your Liferay account manager or
Liferay Support. 

$$$

Here are the upgrade steps:

1.  If you're on Liferay Portal 6.1 or lower,
    [upgrade to Liferay Portal 6.2](/discover/deployment/-/knowledge_base/6-2/upgrading-liferay). 

2.  [Remove unneeded objects from your portal](/discover/deployment/-/knowledge_base/7-0/pre-upgrade-speed-up-the-process). 

3.  [Prepare for upgrading to @product-ver@](/discover/deployment/-/knowledge_base/7-0/preparing-an-upgrade-to-liferay-7). 

4.  [Run the upgrade process](/discover/deployment/-/knowledge_base/7-0/running-the-upgrade-process)
    for the default shard. As part of the configuration, copy all of the shard
    JDBC connection properties from  your `portal-ext.properties` to your
    `portal-upgrade-database.properties`. For example, JDBC connections for a
    default shard and two non-default shards might look like this:

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

5.  [Prepare a @product-ver@ server](/discover/deployment/-/knowledge_base/7-0/deploying-product)
    for each non-default shard.

6.  [Run the upgrade process](/discover/deployment/-/knowledge_base/7-0/running-the-upgrade-process)
    for each non-default shard. The JDBC *default* connection properties in each
    server's `portal-upgrade-database.properties` must specify the associated
    shard. Here's how: 

    -   Add the original JDBC properties for the respective non-default shard 
        database. For example, shard `one`'s original properties might start with `jdbc.one`:

            jdbc.one.driverClassName=com.mysql.jdbc.Driver
            jdbc.one.url=jdbc:mysql://localhost/lportal_one?characterEncoding=UTF-8&dontTrackOpenResources=true&holdResultsOpenOverStatementClose=true&useFastDateParsing=false&useUnicode=true
            jdbc.one.username=
            jdbc.one.password=

    -   Rename the properties to start with `jdbc.default`. For example, 

            jdbc.default.driverClassName=com.mysql.jdbc.Driver
            jdbc.default.url=jdbc:mysql://localhost/lportal_one?characterEncoding=UTF-8&dontTrackOpenResources=true&holdResultsOpenOverStatementClose=true&useFastDateParsing=false&useUnicode=true
            jdbc.default.username=
            jdbc.default.password=

7.  In each server's `portal-ext.properties`, use the JDBC *default* properties
    you specified in the `portal-upgrade-database.properties` (see the previous
    step). 

8.  Remove the non-default shard JDBC properties from the default shard server's
    `portal-ext.properties` file, leaving only the default shard database
    `jdbc.default` properties. For example,

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

Congratulations! You have migrated off of a sharded environment to virtual
instances on separate @product@ servers. You have also upgraded to
@product-ver@. Your virtual instances are ready for action. 
