---
header-id: using-the-dbstore
---

# Using the DBStore

[TOC levels=1-4]

<aside class="alert alert-info">
  <span class="wysiwyg-color-blue120">This document has been updated and ported to <a href="https://learn.liferay.com/dxp/latest/en/system-administration/file-storage/other-file-store-types/dbstore.html">Liferay Learn</a> and is no longer maintained here.</span>
</aside>

You can store Documents and Media files in your @product@ database using
DBStore. DBStore's maximum file (stored as a blob) size is 1 gigabyte. To store
files larger than that, use Simple File System Store or Advanced File System
Store. 

Here are the DBStore configuration steps:

1.  Set the following property in a
    [`portal-ext.properties`](/docs/7-2/deploy/-/knowledge_base/d/portal-properties)
    file in your [Liferay
    Home](/docs/7-2/deploy/-/knowledge_base/d/liferay-home) folder: 

    ```properties
    dl.store.impl=com.liferay.portal.store.db.DBStore
    ```

2.  Restart @product@. 

Documents and Media now uses @product@'s database via DBStore. 
