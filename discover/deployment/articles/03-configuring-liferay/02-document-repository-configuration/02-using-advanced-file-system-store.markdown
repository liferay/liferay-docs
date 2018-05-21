# Using the Advanced File System Store [](id=using-the-advanced-file-system-store)

The advanced file system store is similar to the simple file system
store (the default store). Like that store, it saves files to the local file
system---which, of course, could be a remote file system mount. It uses a
slightly different folder structure to store files, which is pictured below. 

![Figure 1: The advanced file system store creates a more nested folder structure than the file system store.](../../../images/enterprise-adv-file-system-store.png)

So what makes the advanced file system store *advanced*? Several operating
systems have limitations on the number of files that can be stored in a
particular folder. The advanced file system store overcomes this limitation by
programmatically creating a structure that can expand to millions of files, by
alphabetically nesting the files in folders. This not only allows for more files
to be stored, but also improves performance as there are fewer files stored per
folder. 

The same rules apply to the advanced file system store as apply to the simple
file system store. To cluster this, you must point the store to a network
mounted file system that all the nodes can access, and that networked file
system must support concurrent requests and file locking. Otherwise, you may
experience data corruption issues if two users attempt to write to the same file
at the same time from two different nodes.

To use the advanced file system store, follow these steps:

1.  Configure `portal-ext.properties` with this property: 

        dl.store.impl=com.liferay.portal.store.file.system.AdvancedFileSystemStore

2.  Restart @product@.

3.  In the Control Panel, navigate to *Configuration* &rarr; *System
    Settings* &rarr; *File Storage*. 

4.  In the *Advanced File System Store* screen, configure the store your way. 

To use advanced file system store in a multi-node environment, follow these
steps:

1.  Copy the `portal-ext.properties` to each node's
    [`[Liferay Home]` folder](/discover/deployment/-/knowledge_base/7-1/installing-product#liferay-home). 

2.  Export the configuration from the *Advanced File System Store* screen to a 
    [`.config` file](/discover/portal/-/knowledge_base/7-1/configuration-files). 

3.  Copy the `.config` file to each node's `[Liferay Home]/osgi/configs` folder. 

4.  Restart @product@ on the nodes.
 
@product@ is using the advanced file system store. 

+$$$

**Warning:** If a database transaction rollback occurs in the Document Library,
file system changes that have occurred since the start of the transaction
aren't reversed. Inconsistencies between Document Library files and those in
the file system store can occur and may require manual synchronization. 

$$$

You may decide the advanced file system store for whatever reason doesn't serve
your needs. If this is the case, you can of course mount other file systems into
the documents and media library. In addition to this, you can also redefine the
@product@ store to use one of the other supported protocols. The CMIS store is
next. 
