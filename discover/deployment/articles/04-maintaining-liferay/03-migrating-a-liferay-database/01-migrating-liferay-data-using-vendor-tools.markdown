# Migrating @product@ Data Using Vendor Tools [](id=migrating-product-data-using-vendor-tools)

The vendor whose database you're migrating to might provide or suggest a tool
for migrating data to your database. Here are the migration steps:

1.  Create the target database. 

2.  Migrate the data to the target database using the vendor-
    supported or vendor-suggested tools. 

3.  Check that the target database DDL, tables, indexes, and more meet your 
    expectations. 

    +$$$

    **Tip:** Compare the migrated data on the target database with a new test 
    database, freshly initialized by @product@. Initialize test database by
    creating it, configuring a new @product@ installation to use it, and
    starting @product@. Compare the DDL, tables, indexes, and more from the
    target database to those in the test database. 

    $$$

4.  Adjust the database as desired.

5.  Back up your @product@ instance. 

6.  Configure your @product@ instance's `portal-ext` to use the target database.

7.  Re-start your application server. 

Congratulations! You've migrated your data to a new database.

**Related Topics** 

[Updating a Cluster](/discover/deployment/-/knowledge_base/7-0/updating-a-cluster)
