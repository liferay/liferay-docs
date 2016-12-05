# Document Repository Configuration [](id=document-repository-configuration)

By default, @product@ uses a document library store option called Simple File
Store to store documents and media files on the file system (local or mounted)
of the server @product@'s running on. The store's default root directory is
`[Liferay Home]/data/document_library`. You can specify a different root
directory from within [System Settings](/discover/portal/-/knowledge_base/7-0/system-settings).
To access System Settings, open the *Menu*
(![Menu](../../images/icon-menu.png)) and navigate to *Control Panel &rarr;
Configuration &rarr; System Settings*. From System Settings, navigate to
*Platform* and then search for and select the entry *Simple File System Store*.
For the store's *Root dir* value, specify a path relative to the [Liferay Home](/discover/deployment/-/knowledge_base/7-0/installing-liferay-portal#liferay-home)
or an absolute path; then click the *Update* button. The document library store
switches immediately to the new root dir. 

You can also use an entirely different method for storing documents and media
files. You can use any of the following documents and media library stores with
@product@:

**Simple File System Store**: uses the file system (local or a mounted share) to
store files.

**Advanced File System Store**: in addition to using the file system (local or a
mounted share) to store files, Advanced File System Store nests the files into
more directories by version, for faster performance and to store more files.

**CMIS Store (Content Management Interoperability Services)**: uses a system
separate from Liferay to store files. 

**DBStore (Database Storage)**: stores files in the @product@ database.

**JCRStore (Java Content Repository)**: stores files to a JSR-170 compliant
document repository. You can use any JCR client to access the files. The files
are stored to the server's file system by default. You can optionally configure
JCRStore to store files in a database. 

**S3Store (Amazon Simple Storage)**: uses Amazon's cloud-based storage solution.

For example, you can store documents and media files in your Liferay instance's
database using DBStore. To enable DBStore, add the following [`dl.store.impl`](https://docs.liferay.com/portal/7.0/propertiesdoc/portal.properties.html#Document%20Library%20Service)
portal property to a `portal-ext.properties` file in your [Liferay Home](/discover/deployment/-/knowledge_base/7-0/installing-liferay-portal#liferay-home):

    dl.store.impl=com.liferay.portal.store.db.DBStore

Remember to restart your @product@ server after updating your
`portal-ext.properties` file in order for your customizations to take effect.

There are properties related to document library stores that have been moved
from `portal-ext.properties` to OSGi configuration files. The following mapping
shows you how to configure those properties if needed:

#### CMIS [](id=cmis)

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

From `portal-ext.properties`: `dl.store.impl=com.liferay.portal.store.file.system.AdvancedFileSystemStore`

To `osgi/configs`: `com.liferay.portal.store.file.system.configuration.AdvancedFileSystemStoreConfiguration.cfg`
 
Property | Default | Required
---------|---------|---------
`rootDir` | `data/document_library` | `false`
 
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

**Note:** Amazon S3 requires a SAXParser from the application server to operate. For some application servers (e.g. Tomcat), it will be necessary to define a SAXParser in order to prevent errors while utilizing this store. This may be set in `portal-ext.properties`. For example:
    
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
