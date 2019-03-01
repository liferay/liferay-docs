---
header-id: creating-files-folders-and-shortcuts
---

# Creating Files, Folders, and Shortcuts

A primary use case for the Docs &amp; Media API is to create files, folders, and 
file shortcuts in the Documents and Media library. 

If you've used other Liferay APIs, the Docs &amp; Media API follows the same 
conventions. In general, methods that do similar things have similar names. When 
you must create an entity (whatever it is), look for methods that follow the 
pattern `add[ModelName]`, where `[ModelName]` is the name of the entity's data 
model object. As the 
[intro](/develop/tutorials/-/knowledge_base/7-2/documents-and-media-api)
explains, you'll use 
[`DLAppService`](@platform-ref@/7.2-latest/javadocs/portal-kernel/com/liferay/document/library/kernel/service/DLAppService.html) 
to access the API. This service object contains the methods for adding these 
entities: 

-   [Files](#files)
-   [Folders](#folders)
-   [File Shortcuts](#file-shortcuts)

## Files

To create files (`FileEntry` entities) in the Documents and Media library, you 
must use the 
[`DLAppService`](@platform-ref@/7.2-latest/javadocs/portal-kernel/com/liferay/document/library/kernel/service/DLAppService.html) 
interface's `addFileEntry` methods. There are three such methods, and they 
differ by the data type used to create the file. Click each method to see a 
full description of the method and its parameters: 

-   [`addFileEntry(..., byte[] bytes, ...)`](@platform-ref@/7.2-latest/javadocs/portal-kernel/com/liferay/document/library/kernel/service/DLAppService.html#addFileEntry-long-long-java.lang.String-java.lang.String-java.lang.String-java.lang.String-java.lang.String-byte:A-com.liferay.portal.kernel.service.ServiceContext-) 

-   [`addFileEntry(..., File file, ...)`](@platform-ref@/7.2-latest/javadocs/portal-kernel/com/liferay/document/library/kernel/service/DLAppService.html#addFileEntry-long-long-java.lang.String-java.lang.String-java.lang.String-java.lang.String-java.lang.String-java.io.File-com.liferay.portal.kernel.service.ServiceContext-)

-   [`addFileEntry(..., InputStream is, long size, ...)`](@platform-ref@/7.2-latest/javadocs/portal-kernel/com/liferay/document/library/kernel/service/DLAppService.html#addFileEntry-long-long-java.lang.String-java.lang.String-java.lang.String-java.lang.String-java.lang.String-java.io.InputStream-long-com.liferay.portal.kernel.service.ServiceContext-)

Note that the following arguments are optional: 

`sourceFileName`: This keeps track of the uploaded file. It infers the content 
type if that file has an extension. 

`mimeType`: Defaults to a binary stream. If omitted, Documents and Media tries 
to infer the type from the file extension. 

`description`: The file's description to display in the portal. 

`changeLog`: Descriptions for file versions. 

`is` and `size`: In the method that takes an `InputStream`, you can use `null` 
for the `is` parameter. If you do this, however, you must use `0` for the `size` 
parameter. 

For step-by-step instructions on creating files with `addFileEntry`, see 
[Creating Files](/develop/tutorials/-/knowledge_base/7-2/creating-files). 

## Folders

To create folders (`Folder` entities) in the Documents and Media library, you 
must use the 
[`DLAppService`](@platform-ref@/7.2-latest/javadocs/portal-kernel/com/liferay/document/library/kernel/service/DLAppService.html) 
interface's `addFolder` method: 

    addFolder(long repositoryId, 
            long parentFolderId, 
            String name, 
            String description, 
            ServiceContext serviceContext)

See this method's 
[Javadoc](@platform-ref@/7.2-latest/javadocs/portal-kernel/com/liferay/document/library/kernel/service/DLAppService.html#addFolder-long-long-java.lang.String-java.lang.String-com.liferay.portal.kernel.service.ServiceContext-) 
for a description of the parameters. Note that the `description` parameter is 
optional. 

For step-by-step instructions on creating folders with `addFolder`, see 
[Creating Folders](/develop/tutorials/-/knowledge_base/7-2/creating-folders). 

### Folders and External Repositories

By creating a folder that acts as a proxy for an external repository (e.g., 
SharePoint), you can effectively mount that repository inside a Site's default 
repository. When users enter this special folder, they see the external 
repository. These folders are called *mount points*. You can create one via the 
API by setting the 
[Service Context's](/develop/tutorials/-/knowledge_base/7-2/understanding-servicecontext) 
`mountPoint` attribute to `true`, and then using that Service Context in the 
`addFolder` method: 

    serviceContext.setAttribute("mountPoint", true);

Note that the `repositoryId` of such a folder indicates the external repository 
the folder points to---not the repository the folder exists in. Also, mount 
point folders can only exist in the default Site repository. 

## File Shortcuts

To create file shortcuts (`FileShortcut` entities) in the Documents and Media 
library, you must use the 
[`DLAppService`](@platform-ref@/7.2-latest/javadocs/portal-kernel/com/liferay/document/library/kernel/service/DLAppService.html) 
interface's `addFileShortcut` method: 

    addFileShortcut(long repositoryId, 
                    long folderId, 
                    long toFileEntryId, 
                    ServiceContext serviceContext)

See this method's 
[Javadoc](@platform-ref@/7.2-latest/javadocs/portal-kernel/com/liferay/document/library/kernel/service/DLAppService.html#addFileShortcut-long-long-long-com.liferay.portal.kernel.service.ServiceContext-) 
for a description of the parameters. Note that all this method's parameters are 
mandatory. 

Keep these things in mind when creating shortcuts: 

-   You can create a shortcut to a file in a different Site, if that file and 
    its resulting shortcut are in the same portal instance. 
-   You can't create folder shortcuts. 
-   Shortcuts can only exist in the default Site repository. If you try to 
    invoke `addFileShortcut` with an external repository's ID (e.g., 
    a SharePoint repository), the operation fails. Because not all repositories
    have the same features, the Documents and Media API only supports the
    common denominators for all repositories: files and folders. 

For step-by-step instructions on creating file shortcuts with `addFileShortcut`, 
see 
[Creating File Shortcuts](/develop/tutorials/-/knowledge_base/7-2/creating-file-shortcuts). 
