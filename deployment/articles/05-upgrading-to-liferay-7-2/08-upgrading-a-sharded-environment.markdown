---
header-id: upgrading-a-sharded-environment
---

# Upgrading a Sharded Environment

[TOC levels=1-4]

Since @product@ 7.0, Liferay removed its own physical partitioning
implementation (also known as sharding) in favor of the capabilities provided
natively by database vendors. Upgrading a sharded installation to @product-ver@
requires migrating it to as many non-sharded @product@ installations (servers)
as you have shards. These steps guide you through configuring the new @product@
servers to use your formerly sharded data and upgrading the data to
@product-ver@. 

| **Note:** Liferay continues to support its logical partitioning capabilities 
| (also known as
| [virtual instances](/docs/7-2/user/-/knowledge_base/u/setting-up-a-virtual-instance))
| for the foreseeable future. 

| For any further assistance with sharding contact your Liferay account manager 
| or Liferay Support. 

Here are the upgrade steps:

1.  If you're on Liferay Portal 6.1 or lower,
    [upgrade to Liferay Portal 6.2](/docs/6-2/deploy/-/knowledge_base/d/upgrading-liferay). 

2.  [Plan for deprecated applications](/docs/7-2/deploy/-/knowledge_base/d/planning-for-deprecated-applications). 

3.  [Test upgrading a @product@ backup copy](/docs/7-2/deploy/-/knowledge_base/d/test-upgrading-a-product-backup-copy). 

4.  [Prepare to upgrade the @product@ database](/docs/7-2/deploy/-/knowledge_base/d/preparing-to-upgrade-the-product-database). 

4.  [Prepare a new @product@ server for the data upgrade](/docs/7-2/deploy/-/knowledge_base/d/preparing-a-new-product-server-for-data-upgrade). 

5.  [Upgrade the @product@ data](/docs/7-2/deploy/-/knowledge_base/d/upgrading-the-product-data)
    for the default shard. As part of the configuration, copy all of the shard
    JDBC connection properties from `portal-ext.properties` to
    `portal-upgrade-database.properties`. For example, JDBC connections for a
    default shard and two non-default shards might look like this:

    ```properties
    jdbc.default.driverClassName=com.mysql.jdbc.Driver
    jdbc.default.url=jdbc:mysql://database-server/lportal?characterEncoding=UTF-8&dontTrackOpenResources=true&holdResultsOpenOverStatementClose=true&useFastDateParsing=false&useUnicode=true
    jdbc.default.username=
    jdbc.default.password=

    jdbc.one.driverClassName=com.mysql.jdbc.Driver
    jdbc.one.url=jdbc:mysql://database-server/lportal_one?characterEncoding=UTF-8&dontTrackOpenResources=true&holdResultsOpenOverStatementClose=true&useFastDateParsing=false&useUnicode=true
    jdbc.one.username=
    jdbc.one.password=

    jdbc.two.driverClassName=com.mysql.jdbc.Driver
    jdbc.two.url=jdbc:mysql://database-server/lportal_two?characterEncoding=UTF-8&dontTrackOpenResources=true&holdResultsOpenOverStatementClose=true&useFastDateParsing=false&useUnicode=true
    jdbc.two.username=
    jdbc.two.password=
    ```

5.  [Prepare a @product-ver@ server](/docs/7-2/deploy/-/knowledge_base/d/preparing-a-new-product-server-for-data-upgrade)
    for each non-default shard.

6.  [Upgrade the @product@ data](/docs/7-2/deploy/-/knowledge_base/d/upgrading-the-product-data)
    for each non-default shard. The JDBC *default* connection properties in each
    server's `portal-upgrade-database.properties` must specify the associated
    shard. Here's how: 

    -   Add the original JDBC properties for the respective non-default shard 
        database. For example, shard `one`'s original properties might start with `jdbc.one`:

    ```properties
    jdbc.one.driverClassName=com.mysql.jdbc.Driver
    jdbc.one.url=jdbc:mysql://database-server/lportal_one?characterEncoding=UTF-8&dontTrackOpenResources=true&holdResultsOpenOverStatementClose=true&useFastDateParsing=false&useUnicode=true
    jdbc.one.username=
    jdbc.one.password=
    ```

    -   Rename the properties to start with `jdbc.default`. For example, 

    ```properties
    jdbc.default.driverClassName=com.mysql.jdbc.Driver
    jdbc.default.url=jdbc:mysql://database-server/lportal_one?characterEncoding=UTF-8&dontTrackOpenResources=true&holdResultsOpenOverStatementClose=true&useFastDateParsing=false&useUnicode=true
    jdbc.default.username=
    jdbc.default.password=
        ```

7.  In each server's `portal-ext.properties`, use the JDBC *default* properties
    you specified in the `portal-upgrade-database.properties` (see the previous
    step). 

8.  Remove the non-default shard JDBC properties from the default shard server's
    `portal-ext.properties` file, leaving only the default shard database
    `jdbc.default` properties. For example,

    Old JDBC properties: 

    ```properties
    jdbc.default.driverClassName=com.mysql.jdbc.Driver
    jdbc.default.url=jdbc:mysql://database-server/lportal?characterEncoding=UTF-8&dontTrackOpenResources=true&holdResultsOpenOverStatementClose=true&useFastDateParsing=false&useUnicode=true
    jdbc.default.username=
    jdbc.default.password=

    jdbc.one.driverClassName=com.mysql.jdbc.Driver
    jdbc.one.url=jdbc:mysql://database-server/lportal_one?characterEncoding=UTF-8&dontTrackOpenResources=true&holdResultsOpenOverStatementClose=true&useFastDateParsing=false&useUnicode=true
    jdbc.one.username=
    jdbc.one.password=

    jdbc.two.driverClassName=com.mysql.jdbc.Driver
    jdbc.two.url=jdbc:mysql://database-server/lportal_two?characterEncoding=UTF-8&dontTrackOpenResources=true&holdResultsOpenOverStatementClose=true&useFastDateParsing=false&useUnicode=true
    jdbc.two.username=
    jdbc.two.password=
    ```

    New JDBC properties:

    ```properties
    jdbc.default.driverClassName=com.mysql.jdbc.Driver
    jdbc.default.url=jdbc:mysql://database-server/lportal?characterEncoding=UTF-8&dontTrackOpenResources=true&holdResultsOpenOverStatementClose=true&useFastDateParsing=false&useUnicode=true
    jdbc.default.username=
    jdbc.default.password=
    ```

Congratulations! You have migrated off of a sharded environment to virtual
instances on separate @product@ servers. You have also upgraded to
@product-ver@. Your virtual instances are ready for action. 
