# Migrating @product@ Data Manually [](id=migrating-liferay-data-manually)

If your target database vendor doesn't provide any migration tool or suggest
any, or if the suggested tools are too expensive, you can migrated your data
manually. 

1.  Create the target database. 

2.  Configure a new @product@ installation to use the target database. 

3.  Start @product@. This initializes the database by creating @product@ tables,
    indexes, and more. 

4.  Use your favorite database admin tool to truncate the tables. 

5.  Export the current database as script of `INSERT` statements.

6.  Run the script on the new database.

    +$$$

    **Note:** The length for certain fields an slightly vary due to the different
    database limitations. If you get `too large` errors during the import, please
    edit or truncate those values to adjust them to the target field length.

    $$$

7.  Stop the new @product@ installation. 

8.  Back up your existing @product@ instance. 

9.  Configure your @product@ instance's `portal-ext` to use the target database.

10.  Re-start your application server. 

+$$$

**Note:** Migrating a @product@ database might be easier than you expect because
it doesn't have any foreign keys. Without any foreign key constraints, you can
load tables in any order. 

$$$

**Related Topics** 

[Updating a Cluster](/discover/deployment/-/knowledge_base/7-2/updating-a-cluster)
