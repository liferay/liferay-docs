# Store Types [](id=store-types)

You can change the file system (the *store*) that the Documents and Media 
library uses to store files. This is configured in the `portal-ext.properties` 
file by setting the `dl.store.impl=`property. Configuring stores is covered in 
[the Document Repository Configuration guide](/discover/deployment/-/knowledge_base/7-1/document-repository-configuration). 
Here, you'll consider the ramifications of different stores: 

-   **Simple File System Store:** Uses the file system (local or a mounted 
    share) to store files. This is the default store. 

-   **Advanced File System Store:** Nests files into directories by version, for 
    faster performance and to store more files. 

-   **DBStore (Database Storage)**: Stores files in the @product@ database. The 
    file (stored as a blob) size limit is 1 GB. Use the Simple File System Store 
    or Advanced File System Store to store larger files. 

-   **S3Store (Amazon Simple Storage)**: Uses Amazon's cloud-based storage 
    solution. 

If you must move your files from one store to another, use the migration utility
in *Control Panel* &rarr; *Configuration* &rarr; *Server Administration* &rarr;
*Data Migration*. 

## Simple File System Store [](id=using-the-file-system-store)

The Simple File System Store is the default store. It stores Documents and Media
files on the server's file system (local or mounted). This store is heavily
bound to @product@'s database. The store's default root folder is
`[Liferay Home]/data/document_library`. You can change this via the
`dl.store.file.system.root.dir=` property in a `portal-ext.properties` file, or
in the Control Panel. For instructions on this, see the 
[Document Repository Configuration guide](/discover/deployment/-/knowledge_base/7-1/document-repository-configuration).

The Simple File System Store uses a local folder to store files. You can use the
file system for your clustered configuration, but you must make sure the folder
you point the store at can handle things like concurrent requests and file
locking. You must therefore use a Storage Area Network or a clustered file
system. 

The Simple File System Store creates a folder structure based on primary keys in 
@product@'s database. If, for example, you upload a presentation with the file 
name `workflow.odp` to a folder named `stuff`, the store creates a folder 
structure like this: 

    /companyId/folderId/numericFileEntryName/versionNumber

-   `companyId`: The site's company ID.
-   `folderId`: The ID of the Documents and Media folder containing the 
    document.
-   `numericFileEntryName`: The document's numeric file entry name.
-   `versionNumber`: The document's version number.

![Figure 1: The Simple File System Store creates a folder structure based on primary keys in @product@'s database.](../../../images/enterprise-file-system-store.png)

+$$$

**Note:** Be careful not to confuse a document's numeric file entry name from 
its document ID. Each has an independent counter. The numeric file entry name is 
used in the folder path for storing the document, but the document ID is not. 
The numeric file entry name can be found in the `name` column of the 
`DLFileEntry` table in @product@'s database; the document ID can be found in the 
`fileEntryId` column of the same table. 

$$$

## Using the Advanced File System Store [](id=using-the-advanced-file-system-store)

The Advanced File System Store, like the Simple File System Store, saves files 
to the local file system. It uses a slightly different folder structure, 
however, and can overcome operating system limitations on the number of files 
stored in a particular folder by programmatically creating a structure that can
expand to millions of files. It alphabetically nests the files in folders. This
also improves performance, as there are fewer files stored per folder. 

![Figure 2: The Advanced File System Store creates a more nested folder structure than the Simple File System Store.](../../../images/enterprise-adv-file-system-store.png)

The same rules apply to the Advanced File System Store as apply to the Simple 
File System Store. To cluster it, you must point the store to a network mounted 
file system that all the nodes can access. That networked file system must also 
support concurrent requests and file locking. Otherwise, you may experience data 
corruption issues if two users attempt to write to the same file at the same 
time from two different nodes. 

See the 
[Document Repository Configuration guide](/discover/deployment/-/knowledge_base/7-1/using-the-advanced-file-system-store) 
for instructions on using the Advanced File System Store. 

## Using Amazon Simple Storage Service [](id=using-amazon-simple-storage-service)

Amazon's Simple Storage Service (S3) is a cloud-based storage solution that you
can use with @product@. It lets you store your documents to the cloud seamlessly 
from all nodes. 

When you sign up for the service, Amazon assigns you unique keys that link you 
to your account. In Amazon's interface, you can create *buckets* of data 
optimized by region. Once you create these to your specifications, follow 
[these instructions](/discover/deployment/-/knowledge_base/7-1/using-amazon-simple-storage-service) 
to connect your repository to @product@. 

Consult Amazon's S3 documentation for more information. 
