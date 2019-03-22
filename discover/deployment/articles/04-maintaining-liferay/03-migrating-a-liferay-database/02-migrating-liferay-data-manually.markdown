# Migrating @product@ Data Manually [](id=migrating-product-data-manually)

If a migration tool for your new database is not available, you can migrate your
data manually. Follow these high level steps. 

1.  Create the target database. 

2.  Configure a new @product@ installation to use the target database. 

3.  Start @product@. This initializes the database by creating @product@ tables,
    indexes, and more. 

4.  Use your favorite database admin tool to truncate the tables. 

5.  Export the current database as script of `INSERT` statements.

    +$$$

    **Note:** Since the @product@ database has no foreign key restraints, you 
    can load tables in any order. 

    $$$ 

6.  Run the script on the new database.

    +$$$

    **Note:** Database field size limitations vary between vendors. If the
    target field size is smaller, your data imports may cause errors that state
    `too large`. Resolve the issue by truncating or modifying the offending data
    values to fit the target field size. 

    $$$

7.  Stop the new @product@ installation. 

8.  [Back up your existing @product@ instance](/discover/deployment/-/knowledge_base/7-0/backing-up-a-liferay-installation). 

9.  Configure your @product@ instance's `portal-ext.properties` file to use the 
    target database. 

10. Re-start your application server. 

Following these high level steps should result in a successful database
migration. For detailed help, please contact Liferay Support.

**Related Topics** 

[Updating a Cluster](/discover/deployment/-/knowledge_base/7-2/updating-a-cluster)
