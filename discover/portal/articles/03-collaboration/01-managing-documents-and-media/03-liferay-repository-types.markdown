# Liferay Store Types [](id=liferay-repository-types)

There are several options available for configuring how Liferay's Documents and
Media library stores files. Each option is a *store* which can be configured
through the `portal-ext.properties` file by setting the `dl.store.impl=`
property. Configuring Liferay Documents and Media stores is covered in the 
[Document Repository Configuration](/discover/deployment/-/knowledge_base/7-0/document-repository-configuration) 
Deployment Guide. Let's consider the ramifications of the various store options. 

By default, Liferay Portal uses a document library store option called
Simple File Store to store documents and media files on the file system
(local or mounted) of the server Liferay Portal’s running on. The
store's default root folder is
`[Liferay Home]/data/document_library`. You can specify a different root
folder from within 
[System Settings](/discover/portal/-/knowledge_base/7-0/system-settings).
To access System Settings, open the *Menu*
(![icon-menu.png](../../../images/icon-menu.png))
and navigate to *Control Panel &rarr; Configuration &rarr; System Settings*. From
System Settings, navigate to *Platform* and then search for and select
the entry *Simple File System Store*. For the store’s *Root dir* value,
specify a path relative to the 
[Liferay Home](/discover/deployment/-/knowledge_base/7-0/installing-liferay-portal#liferay-home)
or an absolute path; then click the *Update* button. The document
library store switches immediately to the new root folder.

You can use a variety of methods for storing documents and media files:

**Simple File System Store**: uses the file system (local or a mounted
share) to store files.

**Advanced File System Store**: nests the files into more directories by
version, for faster performance and to store more files.

**CMIS Store (Content Management Interoperability Services)**: uses a
system separate from Liferay to store files.

**DBStore (Database Storage)**: stores files in the @product@ database.

**JCRStore (Java Content Repository)**: stores files to a JSR-170
compliant document repository. You can use any JCR client to access the
files. The files are stored to the server's file system by default. You
can optionally configure JCRStore to store files in a database.

**S3Store (Amazon Simple Storage)**: uses Amazon's cloud-based storage
solution. 

### Using the File System Store [](id=using-the-file-system-store)

This is the default store. It's a simple file storage implementation that uses a
local folder to store files. You can use the file system for your clustered
configuration, but you'd have to make sure the folder to which you point the
store can handle things like concurrent requests and file locking. For this
reason, you need to use a Storage Area Network or a clustered file system.

The file system store was the first store created for @product@ and is heavily
bound to its database. By default, documents are stored in a
`document_library` subfolder of the `data` folder in a bundle. Of
course, you can change this path to anything you want by using the
`dl.store.file.system.root.dir=` property. 

This store creates a folder structure based on primary keys in the Liferay
database. If, for example, you upload a presentation with the file name
`workflow.odp` into a folder called *stuff*, the file system store creates a
folder structure that looks like the figure below. 

![Figure 5.2: Liferay's file system store creates a folder structure based on primary keys in Liferay's database.](../../../images/enterprise-file-system-store.png)

The folder path used by Liferay for storing documents is this:

    /companyId/folderId/numericFileEntryName/versionNumber

The first folder name is the company ID to which the site belongs. The second
folder name is the  Documents and Media folder's ID where the document resides.
The third folder name is the document's numeric file entry name. Finally, the
fourth name is a version number used for storing multiple versions of the
document.

+$$$

**Note:** A document's numeric file entry name is distinct from the document ID;
be careful not to confuse the two! Each has an independent counter. The numeric
file entry name is used in the folder path for storing the document but the
document ID is not. The numeric file entry name can be found in the `name`
column of the `DLFileEntry` table in Liferay's database; the document ID can be
found in the `fileEntryId` column of the same table.

$$$

As you can see, the File System Store binds your documents very closely to
@product@ and may not be exactly what you want. If you've been using the
default settings for a while and need to migrate your documents, Liferay
provides a migration utility in the Control Panel &rarr; Configuration &rarr; 
*Server Administration* &rarr; *Data Migration*. Using this utility, you can 
move your documents very easily from one store implementation to another. 

Speaking of other store implementations, let's look at some others Liferay
provides. 

### Using the Advanced File System Store [](id=using-the-advanced-file-system-store)

The advanced file system store is similar to the default file system
store. Like that store, it saves files to the local file system--which, of
course, could be a remote file system mount. It uses a slightly different folder
structure to store files, pictured below. 

![Figure 5.3: The advanced file system store creates a more nested folder structure than the file system store.](../../../images/enterprise-adv-file-system-store.png)

So what makes the advanced file system store *advanced*? Several operating
systems have limitations on the number of files that can be stored in a
particular folder. The advanced file system store overcomes this limitation by
programmatically creating a structure that can expand to millions of files, by
alphabetically nesting the files in folders. This not only allows for more files
to be stored, but also improves performance as there are fewer files stored per
folder. 

The same rules apply to the advanced file system store as apply to the default
file system store. To cluster this, you must point the store to a network
mounted file system that all the nodes can access, and that networked file
system must support concurrent requests and file locking. Otherwise, you may
experience data corruption issues if two users attempt to write to the same file
at the same time from two different nodes. 

Follow the Deployment Guide instructions [here](/discover/deployment/-/knowledge_base/7-0/document-repository-configuration#using-the-advanced-file-system-store) 
to use the Advanced File System Store.

You may decide the advanced file system store for whatever reason doesn't serve
your needs. If this is the case, you can of course mount other file systems into
the documents and media library. In addition to this, you can also redefine the
Liferay store to use one of three other supported protocols. We'll look at these
next. 

### Using the CMIS Store [](id=using-the-cmis-store)

Though you can mount as many different CMIS (Content Management Interoperability
Services) repositories as you like in the Documents and Media library, you may
wish also to redefine the Liferay repository to point to a CMIS repository as
well. Why? Users might want to create a folder or upload content to the Liferay
repository. It would be nice if that Liferay repository was connected to a
clustered CMIS repository by the administrator without having to mount it
through the UI. The CMIS store allows you to do just that. 

+$$$

**Note:** CMIS Store is not suitable for production use and is deprecated as of 
Liferay Portal CE 7.0 and Liferay DXP. Because it can have performance issues 
with large repositories, it's recommended that you use one of the other 
configurations listed above, such as Advanced File System Store, to store your 
Documents and Media files. This deprecation does not affect the use of external 
repositories. You can still [connect to external repositories](/discover/portal/-/knowledge_base/7-0/using-external-repositories) 
using CMIS.

$$$

Follow the Deployment Guide instructions [here](/discover/deployment/-/knowledge_base/7-0/document-repository-configuration#using-the-cmis-store) 
to use the CMIS Store.

The Liferay repository is connected to CMIS via the CMIS store. As long as all 
nodes are pointing to your CMIS repository, everything in your Liferay cluster 
should be fine, as the CMIS protocol prevents multiple simultaneous file access 
from causing data corruption. 

### Using the JCR Store [](id=using-the-jcr-store)

Liferay Portal supports as a store the Java Content Repository standard. Under
the hood, Liferay uses Jackrabbit, a project from Apache, as its JSR-170
compliant document repository. By default, Jackrabbit is configured to store the
documents on the local file system where Liferay is installed, in the
`[Liferay Home]/liferay/jackrabbit` folder. Inside this folder is Jackrabbit's
configuration file, called `repository.xml`. 

+$$$

**Note:** JCR Store is deprecated as of Liferay DXP Fix Pack 14 and Liferay 
Portal CE 7.0 GA4.

$$$

Using the default settings, the JCR store is not very different from the file
system stores, except you can use any JCR client to access the files. You can,
however, modify Jackrabbit's configuration so it stores files in a database that
can be accessed by all nodes, and so that it operates as a cluster within
Liferay's cluster. 

Note that because of file locking issues, this isn't the best way to share
Jackrabbit resources, unless you're using a networked file system that can
handle concurrency and file locking. If you have two people logged in at the
same time uploading content, you could encounter data corruption using this
method, and because of this, we don't recommend it for a production system.
Instead, if you want to use the Java Content Repository in a cluster, you should
redirect Jackrabbit into your database of choice. You can use the Liferay
database or another database for this purpose. This requires editing
Jackrabbit's configuration file. This is covered in more detail [here](/discover/deployment/-/knowledge_base/7-0/document-repository-configuration#using-the-jcr-store).

Note that this configuration doesn't perform as well as the advanced file system
store, because you're storing documents in a database instead of on the file
system. But it does have the benefit of clustering well. 

#### Using Amazon Simple Storage Service [](id=using-amazon-simple-storage-service)

Amazon's simple storage service (S3) is a cloud-based storage solution that you
can use with @product@. All you need is an account, and you can store your
documents to the cloud from all nodes, seamlessly. 

When you sign up for the service, Amazon assigns you unique keys that link
you to your account. In Amazon's interface, you can create "buckets" of data
optimized by region. Once you've created these to your specifications, follow 
the instructions found [here](/discover/deployment/-/knowledge_base/7-0/document-repository-configuration#using-amazon-simple-storage-service) 
to connect your repository to @product@.

Consult the Amazon Simple Storage documentation for additional details on using
Amazon's service. 
