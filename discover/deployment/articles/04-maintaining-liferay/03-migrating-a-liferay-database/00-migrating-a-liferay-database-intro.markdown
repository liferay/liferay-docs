# Migrating a @product@ Database [](id=migrating-a-product-database)

If you have an existing @product@ installation and must migrate it to
a different underlying database, the target database vendor may provide or
suggest data migration tools. Data migration is not an insignificant task, nor
is it exactly the same for everyone, so Liferay only provide guidance here. For
more specific help, please contact Liferay Support. 

The high level steps for migrating your Liferay database from one product to
another are

1.  Backing up your @product@ server. 

2.  Testing data migration in a test environment. 

3.  Comparing @product@ a database populated by fresh @product@ installation and
    a database populated with migrated data. 

4.  Switching the @product@ production server to use the target database 
    populated with the migrated data. 

[Vendor or suggested migration tools](/discover/deployment/-/knowledge_base/7-0/migrating-product-data-using-vendor-tools)
can help you avoid migration errors and migrate faster. 

[Manual data migration](/discover/deployment/-/knowledge_base/7-0/migrating-product-data-manually)
typically takes longer but gives you complete control. Since the @product@
database has no foreign keys, you can load tables in any order. This helps
manual data migration go faster than you might expect. 

Considering any tools available to you, migrating using tools is covered first. 
