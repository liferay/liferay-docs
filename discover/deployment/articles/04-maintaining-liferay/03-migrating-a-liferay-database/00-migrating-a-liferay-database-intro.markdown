# Migrating a @product@ Database [](id=migrating-a-product-database)

If you want to use @product@ on a database from a different vendor, you must
carefully migrate your @product@ data to it. The target database vendor may
provide or suggest data migration tools. If the vendor doesn't, or if the tools
are too expensive, you can migrate the data manually. Both approaches involve
these steps:

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
database has no foreign keys, you can load tables in any order---this helps
manual data migration go faster than you might expect. 

Considering any tools available to you, migrating using tools is covered first. 
