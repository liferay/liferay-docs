# Updating Entities [](id=updating-entities)

Like 
[creating](/develop/tutorials/-/knowledge_base/7-2/creating-files-folders-and-shortcuts) 
and 
[deleting](/develop/tutorials/-/knowledge_base/7-2/deleting-entities) 
entities, updating entities is a key task when working with Documents and Media. 
The methods in the Documents and Media API for creating and updating entities 
are similar. There are, however, a few important differences. 

Here, you'll learn about updating these entities: 

-   [Files](#files)
-   [Folders](#folders)
-   [File Shortcuts](#file-shortcuts)

## Files

Updating a file is a bit more complicated than 
[creating one](/develop/tutorials/-/knowledge_base/7-1/creating-files). 
This is due to the way the update operation handles a file's metadata and 
content. To update only a file's content, you must also supply the file's 
existing metadata. Otherwise, the update operation could lose the metadata. The 
opposite, however, isn't true. You can modify a file's metadata without 
re-supplying the content. In such an update, the file's content is automatically 
copied to the new version of the file. To make this easier to remember, follow 
these rules when updating files: 

-   Always provide all metadata. 
-   Only provide the file's content when you want to change it. 

[`DLAppService`](@platform-ref@/7.2-latest/javadocs/portal-kernel/com/liferay/document/library/kernel/service/DLAppService.html) 
has three `updateFileEntry` methods that you can use to update a file. These 
methods differ only in the file content's type. Click each method to see its 
Javadoc, which contains a full description of its parameters: 

-   [`updateFileEntry(..., byte[] bytes, ...)`](@platform-ref@/7.2-latest/javadocs/portal-kernel/com/liferay/document/library/kernel/service/DLAppService.html#updateFileEntry-long-java.lang.String-java.lang.String-java.lang.String-java.lang.String-java.lang.String-boolean-byte:A-com.liferay.portal.kernel.service.ServiceContext-)

-   [`updateFileEntry(..., File file, ...)`](@platform-ref@/7.2-latest/javadocs/portal-kernel/com/liferay/document/library/kernel/service/DLAppService.html#updateFileEntry-long-java.lang.String-java.lang.String-java.lang.String-java.lang.String-java.lang.String-boolean-java.io.File-com.liferay.portal.kernel.service.ServiceContext-)

-   [`updateFileEntry(..., InputStream is, long size, ...)`](@platform-ref@/7.2-latest/javadocs/portal-kernel/com/liferay/document/library/kernel/service/DLAppService.html#updateFileEntry-long-java.lang.String-java.lang.String-java.lang.String-java.lang.String-java.lang.String-boolean-java.io.InputStream-long-com.liferay.portal.kernel.service.ServiceContext-)

Keep these things in mind when using these methods: 

-   To retain the original file's title and description, you must provide those 
    parameters to `updateFileEntry`. Omitting them deletes any existing 
    title and description. 

-   If you supply `null` in place of the file's content (e.g., `bytes`, `file`, 
    or `is`), the update automatically uses the file's existing content. Do this
    only if you want to update the file's metadata. 

-   If you use `false` for the `majorVersion` parameter, the update increments 
    the file version by `0.1` (e.g., from `1.0` to `1.1`). If you use `true` for 
    this parameter, the update increments the file version to the next `.0` 
    value (e.g., from `1.0` to `2.0`, `1.1` to `2.0`, etc.). 

For a step-by-step guide on using these `updateFileEntry` methods, see 
[Updating Files](liferay.com). 

## Folders

You can use the Documents and Media API to 
[copy or move](/develop/tutorials/-/knowledge_base/7-2/copying-and-moving-entities) 
folders to a different location. Options for in-place folder updates, however, 
are limited. You can only update a folder's name and description. You can do 
this with the `DLAppService` method `updateFolder`: 

    updateFolder(long folderId, String name, String description, ServiceContext serviceContext)

All parameters except the description are mandatory. For a full description of 
this method and its parameters, see its 
[Javadoc](@platform-ref@/7.2-latest/javadocs/portal-kernel/com/liferay/document/library/kernel/service/DLAppService.html#updateFolder-long-java.lang.String-java.lang.String-com.liferay.portal.kernel.service.ServiceContext-). 
For step-by-step instructions on using this method, see 
[Updating Folders](liferay.com). 

## File Shortcuts

You can update a file shortcut (`FileShortcut` entities) to change the file it 
points to or the folder it resides in. Do this via the `DLAppService` method 
`updateFileShortcut`: 

    updateFileShortcut(long fileShortcutId, long folderId, long toFileEntryId, ServiceContext serviceContext)

All of this method's parameters are mandatory. To retain any of the shortcut's 
original values, you must provide them to this method. For a full description of 
the parameters, see the method's 
[Javadoc](@platform-ref@/7.2-latest/javadocs/portal-kernel/com/liferay/document/library/kernel/service/DLAppService.html#updateFileShortcut-long-long-long-com.liferay.portal.kernel.service.ServiceContext-). 
For step-by-step instructions on using this method, see 
[Updating File Shortcuts](liferay.com). 
