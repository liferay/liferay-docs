---
header-id: using-the-dbstore
---

# Using the DBStore

[TOC levels=1-4]

You can store Documents and Media files in your @product@ database using
DBStore. DBStore's maximum file (stored as a blob) size is 1 gigabyte. To store
files larger than that, use Simple File System Store or Advanced File System
Store. 

Here are the DBStore configuration steps:

1.  Set the following property in a `portal-ext.properties` file in your
    [Liferay Home](/docs/7-1/deploy/-/knowledge_base/d/installing-liferay#liferay-home)
    folder: 

        dl.store.impl=com.liferay.portal.store.db.DBStore

2.  Restart @product@. 

Documents and Media now uses @product@'s database via DBStore. 
