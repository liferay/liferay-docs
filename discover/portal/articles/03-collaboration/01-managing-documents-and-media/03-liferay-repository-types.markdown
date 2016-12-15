# Liferay Repository Types

There are several options available for configuring how Liferay's Documents and
Media library stores files. Each option is a *store* which can be configured
through the `portal-ext.properties` file by setting the `dl.store.impl=`
property. Let's consider the ramifications of the various store options. 

### Using the File System Store [](id=using-the-file-system-store)

This is the default store. It's a simple file storage implementation that uses a
local folder to store files. You can use the file system for your clustered
configuration, but you'd have to make sure the folder to which you point the
store can handle things like concurrent requests and file locking. For this
reason, you need to use a Storage Area Network or a clustered file system.

The file system store was the first store created for Liferay and is heavily
bound to the Liferay database. By default, documents are stored in a
`document_library` subfolder of the `data` folder in a Liferay bundle. Of
course, you can change this path to anything you want by using the
`dl.store.file.system.root.dir=` property. 

This store creates a folder structure based on primary keys in the Liferay
database. If, for example, you upload a presentation with the file name
`workflow.odp` into a folder called *stuff*, the file system store creates a
folder structure that looks like the figure below. 

![Figure 5.2: Liferay's file system store creates a folder structure based on primary keys in Liferay's database.](../../images/enterprise-file-system-store.png)

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
Liferay, and may not be exactly what you want. If you've been using the
default settings for a while and need to migrate your documents, Liferay
provides a migration utility in the Control Panel in *Server Administration*
&rarr; *Data Migration*. Using this utility, you can move your documents very
easily from one store implementation to another. 

Speaking of other store implementations, let's look at some others Liferay
provides. 

### Using the Advanced File System Store [](id=using-the-advanced-file-system-store)

Liferay's advanced file system store is similar to the default file system
store. Like that store, it saves files to the local file system--which, of
course, could be a remote file system mount. It uses a slightly different folder
structure to store files, which is pictured below. 

![Figure 5.3: The advanced file system store creates a more nested folder structure than the file system store.](../../images/enterprise-adv-file-system-store.png)

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

If you wish to use the CMIS store, follow the instructions [here](/discover/deployment/-/knowledge_base/7-0/document-repository-configuration)
to set it up. The Liferay repository is connected to CMIS via the CMIS store. As
long as all nodes are pointing to your CMIS repository, everything in your
Liferay cluster should be fine, as the CMIS protocol prevents multiple
simultaneous file access from causing data corruption. 

### Using the JCR Store [](id=using-the-jcr-store)

Liferay Portal supports as a store the Java Content Repository standard. Under
the hood, Liferay uses Jackrabbit, a project from Apache, as its JSR-170
compliant document repository. By default, Jackrabbit is configured to store the
documents on the local file system where Liferay is installed, in the
`[Liferay Home]/liferay/jackrabbit` folder. Inside this folder is Jackrabbit's
configuration file, called `repository.xml`. 

Using the default settings, the JCR store is not very different from the file
system stores, except you can use any JCR client to access the files. You can,
however, modify Jackrabbit's configuration so it stores files in a database that
can be accessed by all nodes, and so that it operates as a cluster within
Liferay's cluster. 

To move the default repository location to a shared folder, you do not need to
edit Jackrabbit's configuration file. Instead, follow the instructions [here](/discover/deployment/-/knowledge_base/7-0/document-repository-configuration#JCR). 
Change it to point to a shared folder that all the nodes can see. A new
Jackrabbit configuration file is then generated in that location, and you'll
have to edit that file to modify Jackrabbit's configuration. 

Note that because of file locking issues, this isn't the best way to share
Jackrabbit resources, unless you're using a networked file system that can
handle concurrency and file locking. If you have two people logged in at the
same time uploading content, you could encounter data corruption using this
method, and because of this, we don't recommend it for a production system.
Instead, if you want to use the Java Content Repository in a cluster, you should
redirect Jackrabbit into your database of choice. You can use the Liferay
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
next time you bring up Liferay, the necessary database tables are created
automatically. Jackrabbit, however, does not create indexes on these tables, and
so over time this can be a performance penalty. To fix this, you must manually
go into your database and index the primary key columns for all the Jackrabbit
tables.

Note that this configuration doesn't perform as well as the advanced file system
store, because you're storing documents in a database instead of on the file
system. But it does have the benefit of clustering well. 

#### Using Amazon Simple Storage Service [](id=using-amazon-simple-storage-service)

Amazon's simple storage service (S3) is a cloud-based storage solution that you
can use with Liferay. All you need is an account, and you can store your
documents to the cloud from all nodes, seamlessly. 

When you sign up for the service, Amazon assigns you unique keys that link
you to your account. In Amazon's interface, you can create "buckets" of data
optimized by region. Once you've created these to your specifications, use [these instructions](/discover/deployment/-/knowledge_base/7-0/document-repository-configuration) 
to connect your S3 account to @product@. 

Consult the Amazon Simple Storage documentation for additional details on using
Amazon's service. 
