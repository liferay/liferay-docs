# Migrating @product@ Data Using Vendor Tools [](id=migrating-product-data-using-vendor-tools)

If the vendor whose database you're migrating to provides or suggests a
migration tool, consider using it. These are the high level data migration steps
when using a tool:

1.  Create the target database. 

2.  Migrate the data to the target database using a tool the vendor supports or 
    suggests. 

3.  Check that the target database DDL, tables, indexes, and more meet your 
    expectations. 

    +$$$

    **Tip:** The suggested way to check the migrated data is to compare it 
    on the target database to data on a new  database, freshly initialized by
    @product@. Here's how to initialize a new empty database: 
    
    1.  Create the database.
    2.  Configure a new @product@ installation to use it.
    3.  Start @product@ configured to the database.
    4.  Compare the DDL, tables, indexes, and more from the target database to
        those in the test database. 

    $$$

4.  Adjust the database as desired.

5.  [Back up your @product@ instance](/discover/deployment/-/knowledge_base/7-0/backing-up-a-liferay-installation). 

6.  Configure your @product@ instance's `portal-ext.properties` file to use the 
    target database.

7.  Re-start your application server. 

Following these high level steps should result in a successful migration to
a new database. If you need detailed help, please contact Liferay Support.

## Related Topics 

[Updating a Cluster](/discover/deployment/-/knowledge_base/7-0/updating-a-cluster)
