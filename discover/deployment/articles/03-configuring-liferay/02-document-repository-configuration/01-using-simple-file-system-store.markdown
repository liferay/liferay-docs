# Using the Simple File System Store [](id=using-the-simple-file-system-store)

This is the default store. It's a simple file storage implementation that uses a
local folder to store files. You can use the file system for your clustered
configuration, but you'd have to make sure the folder to which you point the
store can handle things like concurrent requests and file locking. For this
reason, you need to use a Storage Area Network or a clustered file system.

The file system store was the first store created for @product@ and is heavily
bound to the @product@ database. By default, documents are stored in a
`document_library` subfolder of the `data` folder in a @product@ bundle. Of
course, you can change this path to anything you want in System Settings. 

@product@ uses this folder path format for storing documents:

    /companyId/folderId/numericFileEntryName/versionNumber

The first folder name is the company ID to which the site belongs. The second
folder name is the  Documents and Media folder's ID where the document resides.
The third folder name is the document's numeric file entry name. Finally, the
fourth name is a version number used for storing multiple versions of the
document.

+$$$

**Note:** A document's numeric file entry name is distinct from the document ID;
don't confuse the two! Each has an independent counter. The numeric file entry
name is used in the folder path for storing the document but the document ID is
not. The numeric file entry name is in the `name` column of the `DLFileEntry`
table in Portal's database; the document ID is in the `fileEntryId` column of
the same table.

$$$

+$$$

**Note:** To propagate store configuration in a multi-node environment, export
your store settings from your store's *File Storage* screen in System Settings
to a `.config` file and copy the file to the `[Liferay-Home]/osgi/config` folder
in each node. To export the configuration, select *Export* from the options icon
![Options](../../../images/icon-options.png).

$$$

The Simple File System Store binds documents very closely to @product@, and may
not be exactly what you want. If you've been using the default settings for a
while and need to migrate your documents, @product@ provides a migration utility
in the Control Panel in *Server Administration* &rarr; *Data Migration*. The
utility facilitates moving documents from one store implementation to another. 
