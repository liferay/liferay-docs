# Document Repository Configuration [](id=document-repository-configuration)

There are several options available for configuring how @product@'s Documents and
Media library stores files. Each option is a *store* which can be configured
through the `portal-ext.properties` file by setting the `dl.store.impl=`
property. 

By default, @product@ uses a document library store option called Simple File
Store to store documents and media files on a file system (local or mounted).
The store's default root folder is `[Liferay Home]/data/document_library`.
You can specify a different root directory from within
[System Settings](/discover/portal/-/knowledge_base/7-0/system-settings). To access
System Settings, open the *Menu*
(![Menu](../../images/icon-menu.png)) and navigate to *Control Panel &rarr;
Configuration &rarr; System Settings*. From System Settings, navigate to
*Platform* and then search for and select the entry *Simple File System Store*.
For the store's *Root dir* value, specify a path relative to
[Liferay Home](/discover/deployment/-/knowledge_base/7-0/installing-liferay-portal#liferay-home)
or an absolute path; then click the *Update* button. The document library store
switches immediately to the new folder. 

You can also use an entirely different method for storing documents and media
files:

**Simple File System Store**: uses the file system (local or a mounted share) to
store files.

**Advanced File System Store**: in addition to using the file system (local or a
mounted share) to store files, Advanced File System Store nests the files into
more directories by version, for faster performance and to store more files.

**CMIS Store (Content Management Interoperability Services)**: uses a system
separate from @product@ to store files. 

**DBStore (Database Storage)**: stores files in the @product@ database.

**JCRStore (Java Content Repository)**: stores files to a JSR-170 compliant
document repository. You can use any JCR client to access the files. The files
are stored to the server's file system by default. You can optionally configure
JCRStore to store files in a database. 

**S3Store (Amazon Simple Storage)**: uses Amazon's cloud-based storage solution.

Here are the details for each one. 

## Using the File System Store [](id=using-the-file-system-store)

This is the default store. It's a simple file storage implementation that uses a
local folder to store files. You can use the file system for your clustered
configuration, but you'd have to make sure the folder to which you point the
store can handle things like concurrent requests and file locking. For this
reason, you need to use a Storage Area Network or a clustered file system.

The file system store was the first store created for @product@ and is heavily
bound to the @product@ database. By default, documents are stored in a
`document_library` subfolder of the `data` folder in a @product@ bundle. Of
course, you can change this path to anything you want in System Settings. 

This store creates a folder structure based on primary keys in the @product@
database. If, for example, you upload a presentation with the file name
`workflow.odp` into a folder called *stuff*, the file system store creates a
folder structure that looks like the figure below. 

The folder path used by @product@ for storing documents is this:

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
column of the `DLFileEntry` table in @product@'s database; the document ID can be
found in the `fileEntryId` column of the same table.

$$$

As you can see, the File System Store binds your documents very closely to
@product@, and may not be exactly what you want. If you've been using the
default settings for a while and need to migrate your documents, @product@
provides a migration utility in the Control Panel in *Server Administration*
&rarr; *Data Migration*. Using this utility, you can move your documents very
easily from one store implementation to another. 

## Using the Advanced File System Store [](id=using-the-advanced-file-system-store)

@product@'s advanced file system store is similar to the default file system
store. Like that store, it saves files to the local file system--which, of
course, could be a remote file system mount. It uses a slightly different folder
structure to store files, which is pictured below. 

![Figure 1: The advanced file system store creates a more nested folder structure than the file system store.](../../images/enterprise-adv-file-system-store.png)

So what makes the advanced file system store *advanced*? Several operating
systems have limitations on the number of files which can be stored in a
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

+$$$

**Note:** Since Liferay DXP Fix Pack 14 and Liferay Portal 7.0 CE GA4, to 
configure the advanced file system store you must configure both the 
`portal-ext.properties` and `.config` files.

$$$

Create the following file inside your app server's `osgi/configs` folder: 

    com.liferay.portal.store.file.system.configuration.AdvancedFileSystemStoreConfiguration.cfg

+$$$

**Note:** you can also generate the config file by exporting the 
configurations from the Control Panel under *System Settings* &rarr; 
*Foundation* &rarr; *Advanced File System Store*.

$$$

Configure the property shown below:

Property | Default | Required
---------|---------|---------
`rootDir` | `data/document_library` | `true`

**You may need to update the** `rootDir` **property to correctly reflect where 
your document library is stored**. 

Next, configure `portal-ext.properties`: 

    dl.store.impl=com.liferay.portal.store.file.system.AdvancedFileSystemStore

With both the `.config` file and `portal-ext.properties` configured, you can 
start @product@. 

You may decide the advanced file system store for whatever reason doesn't serve
your needs. If this is the case, you can of course mount other file systems into
the documents and media library. In addition to this, you can also redefine the
@product@ store to use one of three other supported protocols. We'll look at these
next. 

## Using the CMIS Store [](id=using-the-cmis-store)

Though you can mount as many different CMIS (Content Management Interoperability
Services) repositories as you like in the Documents and Media library, you may
wish also to redefine the @product@ repository to point to a CMIS repository as
well. Why? Users might want to create a folder or upload content to the @product@
repository. It would be nice if that @product@ repository was connected to a
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

If you wish to use the CMIS store, follow the instructions [here](/discover/deployment/-/knowledge_base/7-0/document-repository-configuration#cmis)
to set it up. The @product@ repository is connected to CMIS via the CMIS store. As
long as all nodes are pointing to your CMIS repository, everything in your
@product@ cluster should be fine, as the CMIS protocol prevents multiple
simultaneous file access from causing data corruption. 

## Using the JCR Store [](id=using-the-jcr-store)

@product@ supports as a store the Java Content Repository standard. Under
the hood, @product@ uses Jackrabbit, a project from Apache, as its JSR-170
compliant document repository. By default, Jackrabbit is configured to store the
documents on the local file system where @product@ is installed, in the
`[Liferay Home]/liferay/jackrabbit` folder. Inside this folder is Jackrabbit's
configuration file, called `repository.xml`. 

+$$$

**Note:** JCR Store is deprecated as of Liferay DXP Digital Enterprise Fix Pack
14 and Liferay Portal CE 7.0 GA4.

$$$

Using the default settings, the JCR store is not very different from the file
system stores, except you can use any JCR client to access the files. You can,
however, modify Jackrabbit's configuration so it stores files in a database that
can be accessed by all nodes, and so that it operates as a cluster within
@product@'s cluster. 

To move the default repository location to a shared folder, you do not need to
edit Jackrabbit's configuration file. Instead, follow the instructions [here](/discover/deployment/-/knowledge_base/7-0/document-repository-configuration#jcr).
Change it to point to a shared folder that all the nodes can see. A new
Jackrabbit configuration file is then generated in that location, and you'll
have to edit that file to modify Jackrabbit's configuration. 

Note that because of file locking issues, this isn't the best way to share
Jackrabbit resources, unless you're using a networked file system that can
handle concurrency and file locking. If you have two people logged in at the
same time uploading content, you could encounter data corruption using this
method, and because of this, we don't recommend it for a production system.
Instead, if you want to use the Java Content Repository in a cluster, you should
redirect Jackrabbit into your database of choice. You can use the @product@
database or another database for this purpose. This requires editing
Jackrabbit's configuration file.

The default Jackrabbit configuration file has sections commented out for moving
the Jackrabbit configuration into the database. This has been done to make it as
easy as possible to enable this configuration. To move the Jackrabbit
configuration into the database, comment out the sections relating to the
file system and comment in the sections relating to the database. These by
default are configured for a MySQL database. If you are using another database,
you will need to modify the configuration, as there are changes to the
configuration file that are necessary for specific databases. For example, the
default configuration uses Jackrabbit's `DbFileSystem` class to mimic a file
system in the database. While this works well in MySQL, it doesn't work for all
databases. For example, an Oracle database requires the `OracleFileSystem`. 

Modify the JDBC database URLs so they point to your database. This, of course,
must be done on all nodes of the cluster. Don't forget to create the database
first and grant the user ID you are specifying in the configuration file access
to create, modify, and drop tables. After this, be sure to uncomment the
`<Cluster/>` section at the bottom of the file. For further information, it's
best to check out the [Jackrabbit documentation](http://jackrabbit.apache.org).

Once you've configured Jackrabbit to store its repository in a database, the
next time you bring up @product@, the necessary database tables are created
automatically. Jackrabbit, however, does not create indexes on these tables, and
so over time this can be a performance penalty. To fix this, you must manually
go into your database and index the primary key columns for all the Jackrabbit
tables.

Note that this configuration doesn't perform as well as the advanced file system
store, because you're storing documents in a database instead of on the file
system. But it does have the benefit of clustering well.
For example, you can store documents and media files in your @product@ instance's
database using DBStore. To enable DBStore, add the following [`dl.store.impl`](https://docs.liferay.com/portal/7.0/propertiesdoc/portal.properties.html#Document%20Library%20Service)
portal property to a `portal-ext.properties` file in your [Liferay Home](/discover/deployment/-/knowledge_base/7-0/installing-liferay-portal#liferay-home):

    dl.store.impl=com.liferay.portal.store.db.DBStore

Remember to restart your @product@ server after updating your
`portal-ext.properties` file in order for your customizations to take effect.


## Using Amazon Simple Storage Service [](id=using-amazon-simple-storage-service)

Amazon's simple storage service (S3) is a cloud-based storage solution that you
can use with @product@. All you need is an account, and you can store your
documents to the cloud from all nodes, seamlessly. 

When you sign up for the service, Amazon assigns you unique keys that link
you to your account. In Amazon's interface, you can create "buckets" of data
optimized by region. Once you've created these to your specifications, use [these instructions](/discover/deployment/-/knowledge_base/7-0/document-repository-configuration#s3) 
to connect your S3 account to @product@. 

If you are using Tomcat as your app server, it doesn't contain a `SAXParser`.
You must include this property in `system-ext.properties`: 

    org.xml.sax.driver=com.sun.org.apache.xerces.internal.parsers.SAXParser

Other app servers also need this configuration if they don't contain a
`SAXParser`. Remember to place your `system-ext.properties` file in a folder
that resides in your @product@ installation's class path  (e.g.,
`/WEB-INF/classes/`).

Consult the Amazon Simple Storage documentation for additional details on using
Amazon's service.

There are properties related to document library stores that have been moved
from `portal-ext.properties` to OSGi configuration files. The following mapping
shows you how to configure those properties if needed:

#### CMIS Store [](id=cmis)

From `portal-ext.properties`: `dl.store.impl=com.liferay.portal.store.cmis.CMISStore`

To `osgi/configs`: `com.liferay.portal.store.cmis.configuration.CMISStoreConfiguration.cfg`

Property | Default | Required
---------|---------|---------
`repositoryUrl` | `http://localhost:8080/alfresco/service/api/cmis` | `true`
`credentialsUsername` | none | `true`
`credentialsPassword` | none | `true`
`systemRootDir` | Liferay Home | `true`

#### File Store [](id=file-store)

From `portal-ext.properties`: `dl.store.impl=com.liferay.portal.store.file.system.FileSystemStore`

To `osgi/configs`: `com.liferay.portal.store.file.system.configuration.FileSystemStoreConfiguration.cfg`

Property | Default | Required
---------|---------|---------
`rootDir` | `data/document_library` | `false`

#### Advanced File Store [](id=advanced-file-store)

**Since Liferay DXP Fix Pack 14 and Liferay Portal 7.0 CE GA4, both the 
`portal-ext.properties` and `.config` files are required to configure the 
advanced file system store.**

From `portal-ext.properties`: `dl.store.impl=com.liferay.portal.store.file.system.AdvancedFileSystemStore`

To `osgi/configs`: `com.liferay.portal.store.file.system.configuration.AdvancedFileSystemStoreConfiguration.cfg`

Property | Default | Required
---------|---------|---------
`rootDir` | `data/document_library` | `true`

#### JCR [](id=jcr)

From `portal-ext.properties`: `dl.store.impl=com.liferay.portal.store.jcr.JCRStore`

To `osgi/configs`: `com.liferay.portal.store.jcr.configuration.JCRStoreConfiguration.cfg`

Property | Default | Required
---------|---------|---------
`initializeOnStartup` | `false`| `true`
`wrapSession` | `true` | `true`
`moveVersionLabels` | `false` | `true`
`workspaceName` | `liferay` | `true`
`nodeDocumentlibrary` | `documentlibrary` | `true`
`jackrabbitRepositoryRoot` | `data/jackrabbit` | `true`
`jackrabbitConfigFilePath` | `repository.xml` | `true`
`jackrabbitRepositoryHome` | `home` | `true`
`jackrabbitCredentialsUsername` | none | `true`
`jackrabbitCredentialsPassword` | none | `true`

#### S3 [](id=s3)

From `portal-ext.properties`: `dl.store.impl=com.liferay.portal.store.s3.S3Store`

To `osgi/configs`: `com.liferay.portal.store.s3.configuration.S3StoreConfiguration.cfg`

Property | Default | Required
---------|---------|---------
`accessKey` | | `false`
`secretKey` | | `false`
`s3Region` | `us-east-1` | `false`
`bucketName` | | `true`
`s3StorageClass` | STANDARD | `false`
`httpClientMaxConnections` | `50` | `false`
`cacheDirCleanUpExpunge` | `7` | `false`
`cacheDirCleanUpFrequency` | `100` | `false`

+$$$

**Note:** Amazon S3 requires a SAXParser from the application server to operate.
For some application servers (e.g. Tomcat), it's necessary to define a SAXParser
in order to prevent errors while utilizing this store. This may be set in
`system-ext.properties`. For example,

    org.xml.sax.driver=com.sun.org.apache.xerces.internal.parsers.SAXParser

**Warning:** If a database transaction rollback occurs in a Document Library
that uses a file system based store, file system changes that have occurred
since the start of the transaction won't be reversed. Inconsistencies between
Document Library files and those in the file system store can occur and may
require manual synchronization. All stores except DBStore are vulnerable to this
limitation.

$$$

Please refer to the [Document Library property reference](https://docs.liferay.com/portal/7.0/propertiesdoc/portal.properties.html#Document%20Library%20Portlet)
for a complete list of supported customizations. You can customize features such
as the maximum allowed size of documents and media files, the list of allowed
file extensions, which types of files should be indexed, and more.
