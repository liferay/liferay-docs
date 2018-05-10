# Document Repository Configuration [](id=document-repository-configuration)

There are several options available for configuring how @product@'s Documents
and Media library stores files. Each option is a *store* which can be configured
through the `portal-ext.properties` file by setting the
[`dl.store.impl=` property](@platform-ref@/7.1-latest/propertiesdoc/portal.properties.html#Document%20Library%20Service). 

By default, @product@ uses a document library store option called Simple File
Store to store documents and media files on a file system (local or mounted).
The store's default root folder is `[Liferay Home]/data/document_library`.
You can specify a different root directory from within
[System Settings](/discover/portal/-/knowledge_base/7-1/system-settings).
To access System Settings, open the *Menu*
(![Menu](../../../images/icon-menu.png))
and navigate to *Control Panel &rarr; Configuration &rarr; System Settings*.
From System Settings, navigate to *Platform* and then search for and click *File
Storage*
(![Folder](../../../images/icon-folder.png)).
For the store's *Root directory* value, specify a path relative to
[Liferay Home](/discover/deployment/-/knowledge_base/7-1/installing-product#liferay-home)
or an absolute path; then click the *Save* button. The document library store
switches immediately to the new folder. 

![Figure x: The File Storage page in System Settings lets you configure document repository storage.](../../../images/file-storage.png)

+$$$

**Note:** To propagate store configuration in a cluster, export your store 
settings from your store's *File Storage* screen in System Settings to a
`.config` file and copy the file to the `[Liferay-Home]/osgi/config` folder in
each node. To export the configuration, select *Export* from the options icon
![Options](../../../images/icon-options.png).

$$$

You can also use an entirely different method for storing documents and media
files:

**Simple File System Store**: uses the file system (local or a mounted share) to
store files.

**Advanced File System Store**: in addition to using the file system (local or a
mounted share) to store files, Advanced File System Store nests the files into
more folders by version, for faster performance and to store more files.

**CMIS Store (Content Management Interoperability Services)**: uses a system
separate from @product@ to store files. 

**JCR Store (Java Content Repository)**: stores files to a JSR-170 compliant
document repository. You can use any JCR client to access the files. The files
are stored to the server's file system by default. You can optionally configure
JCRStore to store files in a database. 

**S3 Store (Amazon Simple Storage)**: uses Amazon's cloud-based storage 
solution.

These articles explain details for each one.  
