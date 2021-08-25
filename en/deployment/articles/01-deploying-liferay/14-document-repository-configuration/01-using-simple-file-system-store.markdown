---
header-id: using-the-simple-file-system-store
---

# Using the Simple File System Store

[TOC levels=1-4]

<aside class="alert alert-info">
  <span class="wysiwyg-color-blue120">This document has been updated and ported to <a href="https://learn.liferay.com/dxp/latest/en/system-administration/file-storage/other-file-store-types/simple-file-system-store.html">Liferay Learn</a> and is no longer maintained here.</span>
</aside>

The simple file storage implementation is the default store. It uses a local
folder to store files. You can use the file system for your
[clustered](/docs/7-2/deploy/-/knowledge_base/d/liferay-clustering)
configuration, but the folder you're pointing to must be shared by all nodes and
handle concurrent requests and file locking. For this reason, you need to use a
Storage Area Network or a clustered file system.

The file system store was the first store used in @product@ and is heavily bound
to the @product@ database. By default, documents are stored in a
`document_library` subfolder of the `data` folder. Of course, you can change
this path to anything you want in [System
Settings](/docs/7-2/user/-/knowledge_base/u/system-settings). 

The Simple File System store uses this folder path format for storing documents:

```bash
/companyId/folderId/numericFileEntryName/versionNumber
```

The first folder name is the site's company ID. The second folder name is the
Documents and Media folder's ID where the document resides. The third folder
name is the document's numeric file entry name. Finally, the fourth name is
a version number used for storing multiple versions of the document.

| **Note:** A document's numeric file entry name is distinct from the document 
| ID; don't confuse the two! Each has an independent counter. The numeric file
| entry name is used in the folder path for storing the document but the 
| document ID is not. The numeric file entry name is in the `name` column of the
| `DLFileEntry` table in @product@'s database; the document ID is in the
| `fileEntryId` column of the same table.

| **Warning:** If a database transaction rollback occurs in the Document 
| Library, file system changes that have occurred since the start of the
| transaction aren't reversed. Inconsistencies between Document Library files 
| and those in the file system store can occur and may require manual
| synchronization.

The Simple File System Store binds documents very closely to @product@, and may
not be exactly what you want. If you've been using the default settings for
a while and must migrate your documents, there's a migration utility in the
Control Panel in *Server Administration* &rarr; *Data Migration*. The utility
facilitates moving documents from one store implementation to another. 
