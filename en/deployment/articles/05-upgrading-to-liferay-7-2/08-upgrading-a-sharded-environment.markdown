---
header-id: upgrading-a-sharded-environment
---

# Upgrading a Sharded Environment

[TOC levels=1-4]

<<<<<<< HEAD
<aside class="alert alert-info">
  <span class="wysiwyg-color-blue120">This document has been updated and ported to <a href="https://learn.liferay.com/dxp/latest/en/installation-and-upgrades/upgrading-liferay/other-upgrade-scenarios/upgrading-a-sharded-environment.html">Liferay Learn</a> and is no longer maintained here.</span>
</aside>

Since Liferay DXP 7.0, Liferay removed its own physical partitioning implementation (also known as sharding) in favor of the capabilities provided natively by database vendors. Upgrading a sharded installation to DXP 7.0 or higher requires migrating it to as many non-sharded Liferay DXP installations (servers) as you have shards. These steps guide you through configuring the new Liferay DXP servers to use your formerly sharded data.

| **Note:** Liferay continues to support its logical partitioning capabilities
| (also known as
| [virtual instances](/docs/7-2/user/-/knowledge_base/u/setting-up-a-virtual-instance))
| for the foreseeable future.

| For any further assistance with sharding contact your Liferay account manager
| or Liferay Support.

## Add Configurations Before the Data Upgrade

In addition to other configurations, you will need to set more properties to migrate your shards to virtual instances for your data upgrade.

Here is how to configure the upgrade to migrate from sharding:

1. Copy all of the shard JDBC connection properties from `portal-ext.properties` to`portal-upgrade-database.properties`. For example, JDBC connections for a default shard and two non-default shards might look like this:

    ```properties
    jdbc.default.driverClassName=[the database driver class name]
    jdbc.default.url=[the URL to the default database shard]
    jdbc.default.username=[the user name]
    jdbc.default.password=[the password]

    jdbc.one.driverClassName=[the database driver class name]
    jdbc.one.url=[the URL to database shard one]
    jdbc.one.username=[the user name]
    jdbc.one.password=[the password]

    jdbc.two.driverClassName=[the database driver class name]
    jdbc.two.url=[the URL to database shard two]
    jdbc.two.username=[the user name]
    jdbc.two.password=[the password]
    ```

1. Set the JDBC _default_ connection properties in each server's `portal-upgrade-database.properties` to specify the associated shard.

    * Add the original JDBC properties for the respective non-default shard database. For example, shard `one`'s original properties might start with `jdbc.one`:

    ```properties
    jdbc.one.driverClassName=[the database driver class name]
    jdbc.one.url=[the URL to database shard one]
    jdbc.one.username=[the user name]
    jdbc.one.password=[the password]
    ```

    * Rename the properties to start with `jdbc.default`. For example:

    ```properties
    jdbc.default.driverClassName=[the database driver class name]
    jdbc.default.url=[the URL to database shard one]
    jdbc.default.username=[the user name]
    jdbc.default.password=[the password]
    ```

## Upgrade and Update Properties

When you perform the database upgrade, upgrade the default shard first, and then each of the non-default shards. See [Using the Database Upgrade Tool](/docs/7-2/deploy/-/knowledge_base/d/upgrading-the-product-data) for more information on running the database upgrade.

After the database upgrade has been completed, make the following configuration changes to your application servers:

1. In each server's `portal-ext.properties`, use the JDBC _default_ properties you specified in the `portal-upgrade-database.properties` (see the _default_ properties above).

1. Remove the non-default shard JDBC properties from the default shard server's `portal-ext.properties` file, leaving only the default shard database `jdbc.default` properties. For example:

    Old JDBC properties:

    ```properties
    jdbc.default.driverClassName=[the database driver class name]
    jdbc.default.url=[the URL to the default database shard]
    jdbc.default.username=[the user name]
    jdbc.default.password=[the password]

    jdbc.one.driverClassName=[the database driver class name]
    jdbc.one.url=[the URL to database shard one]
    jdbc.one.username=[the user name]
    jdbc.one.password=[the password]

    jdbc.two.driverClassName=[the database driver class name]
    jdbc.two.url=[the URL to database shard two]
    jdbc.two.username=[the user name]
    jdbc.two.password=v[the password]
    ```

    New JDBC properties:

    ```properties
    jdbc.default.driverClassName=[the database driver class name]
    jdbc.default.url=[the URL to your database]
    jdbc.default.username=[the user name]
    jdbc.default.password=[the password]
    ```

Once you have completed all of these steps, you have migrated off of a sharded environment to virtual instances on separate Liferay DXP servers together with your DXP upgrade.

Congratulations! You have migrated off of a sharded environment to virtual
instances on separate @product@ servers. You have also upgraded to
@product-ver@. Your virtual instances are ready for action.
