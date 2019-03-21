---
header-id: copying-and-moving-entities
---

# Copying and Moving Entities

Although the Documents and Media API can copy and move entities, these 
operations have some important caveats and limitations. Keep these things in 
mind when copying entities: 

-   There's no way to copy files---you can only copy folders. However, copying a 
    folder also copies its contents, which can include files. 
-   Folders can only be copied within their current repository. 

The move operation doesn't have these restrictions. It's possible to move files 
and folders between different repositories. In general, however, the move 
operation is a bit more complicated than the copy operation. For example, the 
API's behavior changes depending on whether you move entities to a different 
repository or within the same one. 

Here, you'll learn about the following: 

-   [Copying Folders](#copying-folders)
-   [Moving Folders and Files](#moving-folders-and-files)

## Copying Folders

The Documents and Media API can copy folders within a repository. You can't, 
however, copy a folder between different repositories. Note that copying a 
folder also copies its contents. 

To copy a folder, use the `DLAppService` method `copyFolder`: 

    copyFolder(long repositoryId, long sourceFolderId, long parentFolderId, String name, 
            String description, ServiceContext serviceContext)

For a full description of the method and its parameters, see its 
[Javadoc](@platform-ref@/7.2-latest/javadocs/portal-kernel/com/liferay/document/library/kernel/service/DLAppService.html#copyFolder-long-long-long-java.lang.String-java.lang.String-com.liferay.portal.kernel.service.ServiceContext-). 

For step-by-step instructions on using this method, see 
[Copying Folders](/developer/frameworks/-/knowledge_base/7-2/copying-folders). 

## Moving Folders and Files

The move operation is more flexible than the copy operation. Copying only works 
with folders, and you can't copy between repositories. The move operation, 
however, works with files and folders within or between repositories. 

+$$$

**Note:** Depending on the repository implementation, you may get unexpected 
behavior when moving folders between repositories. Moving a folder also moves 
its contents via separate move operations for each item in the folder. In some
repository implementations, if any move sub-operation fails, the parent move
operation also fails. In other repository implementations, the results of
successful sub-operations remain even if others fail, which leaves a partially
complete move of the whole folder. 

$$$

To move a folder, use the `DLAppService` method `moveFolder`: 

    moveFolder(long folderId, long parentFolderId, ServiceContext serviceContext)

For a full description of this method and its parameters, see its 
[Javadoc](@platform-ref@/7.2-latest/javadocs/portal-kernel/com/liferay/document/library/kernel/service/DLAppService.html#moveFolder-long-long-com.liferay.portal.kernel.service.ServiceContext-). 
This method is similar to `copyFolder`, but it can't change the folder's name or 
description, and it can move folders between repositories. Folder contents are 
moved with the folder. 

The operation for moving a file is almost identical to moving a folder. To move 
a file, use the `DLAppService` method `moveFileEntry`: 

    moveFileEntry(long fileEntryId, long newFolderId, ServiceContext serviceContext)

For a full description of this method and its parameters, see its 
[Javadoc](@platform-ref@/7.2-latest/javadocs/portal-kernel/com/liferay/document/library/kernel/service/DLAppService.html#moveFileEntry-long-long-com.liferay.portal.kernel.service.ServiceContext-). 

For step-by-step instructions on using `moveFolder` and `moveFileEntry`, see 
[Moving Folders and Files](/developer/frameworks/-/knowledge_base/7-2/moving-folders-and-files). 
