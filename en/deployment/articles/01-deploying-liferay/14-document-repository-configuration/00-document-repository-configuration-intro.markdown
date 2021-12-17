---
header-id: document-repository-configuration
---

# Document Repository Configuration

[TOC levels=1-4]

<aside class="alert alert-info">
  <span class="wysiwyg-color-blue120">This document has been updated and ported to <a href="https://learn.liferay.com/dxp/latest/en/system-administration/file-storage.html">Liferay Learn</a> and is no longer maintained here.</span>
</aside>

You can configure file storage in several ways. Each option is a *store* which
can be configured through the
[`portal-ext.properties`](/docs/7-2/deploy/-/knowledge_base/d/portal-properties)
file by setting the [`dl.store.impl=`
property](@platform-ref@/7.2-latest/propertiesdoc/portal.properties.html#Document%20Library%20Service). 

The default store is called Simple File Store. It stores [documents and
media](/docs/7-2/user/-/knowledge_base/u/managing-documents-and-media) files on
a file system (local or mounted). The store's default root folder is `[Liferay
Home]/data/document_library`. You can specify a different root directory from
within [System Settings](/docs/7-2/user/-/knowledge_base/u/system-settings).  

1.  Access System Settings by opening the *Menu*
    (![Menu](../../../images/icon-menu.png)) and navigating to *Control Panel
    &rarr; Configuration &rarr; System Settings*.

2.  In the *Platform* section, click *File Storage*. The File Storage page 
    appears. 

3.  Click *Simple File System Store*.

4.  For the store's *Root directory* value, specify its absolute path or its 
    path relative to [Liferay
    Home](/docs/7-2/deploy/-/knowledge_base/d/liferay-home).

5.  Click the *Save* button.

The document library store switches immediately to the new folder. 

![Figure 1: The File Storage page in System Settings lets you configure document repository storage.](../../../images/file-storage.png)

You can also use an entirely different method for storing documents and media
files:

**Simple File System Store**: uses the file system (local or a mounted share) to
store files.

**Advanced File System Store**: in addition to using the file system (local or a
mounted share) to store files, Advanced File System Store nests the files into
folders by version, for faster performance and to store more files.

**S3 Store (Amazon Simple Storage)**: uses Amazon's cloud-based storage 
solution. 

**DBStore (Database Storage)**: stores the files in the database. DBStore's file
(stored as a blob) size is 1 gigabyte. To store files larger than 1 gigabyte,
use Simple File System Store or Advanced File System Store. 

These articles explain details for each one. 
