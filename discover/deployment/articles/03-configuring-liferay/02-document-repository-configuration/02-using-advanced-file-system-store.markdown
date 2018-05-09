# Using the Advanced File System Store [](id=using-the-advanced-file-system-store)

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
