# Alternative File Repository Options [](id=alternative-file-repository-options)

By default, Liferay stores documents and media files on the file system of the
server on which it's running. You can choose a specific location for the
document library store's root directory by adding the following property to your
`portal-ext.properties` file and replacing the default path with your custom
path:

    dl.store.file.system.root.dir=${liferay.home}/data/document_library

You can also use an entirely different method for storing documents and media
files. You can use any of the following documents and media library stores with
Liferay:

- Advanced File System Store
- CMIS Store (Content Management Interoperability Services)
- DBStore (Database Storage)
- File System Store
- JCRStore (Java Content Repository)
- S3Store (Amazon Simple Storage)

For example, you can store documents and media files in your Liferay instance's
database using DBStore. To enable DStore, add the following line to your
`portal-ext.properties` file:

    dl.store.impl=com.liferay.portlet.documentlibrary.store.DBStore

Remember to restart your Liferay server after updating your
`portal-ext.properties` file in order for your customizations to take effect.
Please refer to the Document Library Portlet section of your `portal.properties`
file to find a complete list of supported customizations. You can customize
features such as the maximum allowed size of documents and media files, the list
of allowed file extensions, which types of files should be indexed, etc.
