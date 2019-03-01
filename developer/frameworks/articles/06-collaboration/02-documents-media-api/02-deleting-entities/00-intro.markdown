---
header-id: deleting-entities
---

# Deleting Entities

You can delete entities with the Documents and Media API. Note that the exact 
meaning of *delete* depends on the portal configuration and the delete operation 
you choose. This is because the 
[Recycle Bin](/discover/portal/-/knowledge_base/7-2/restoring-deleted-assets), 
which is enabled by default, can be used to recover deleted items. Deletions via 
[`DLAppService`](@platform-ref@/7.2-latest/javadocs/portal-kernel/com/liferay/document/library/kernel/service/DLAppService.html),
however, are permanent. To send items to the Recycle Bin, you must use the
Capabilities API. 

Here, you'll learn about deleting these entities: 

-   [Files](#files)
-   [File Versions](#file-versions)
-   [File Shortcuts](#file-shortcuts)
-   [Folders](#folders)

You'll also learn about using the 
[Recycle Bin](#recycle-bin). 

## Files

There are two `DLAppService` methods you can use to delete files: 

-   [`deleteFileEntry(long fileEntryId)`](@platform-ref@/7.2-latest/javadocs/portal-kernel/com/liferay/document/library/kernel/service/DLAppService.html#deleteFileEntry-long-)

-   [`deleteFileEntryByTitle(long repositoryId, long folderId, String title)`](@platform-ref@/7.2-latest/javadocs/portal-kernel/com/liferay/document/library/kernel/service/DLAppService.html#deleteFileEntryByTitle-long-long-java.lang.String-)

These methods differ only in how they identify a file for deletion. The 
combination of the `folderId` and `title` parameters in `deleteFileEntryByTitle` 
uniquely identify a file because it's impossible for two files in the same 
folder to share a name. For step-by-step instructions on using these methods, 
see 
[Deleting Files](/develop/tutorials/-/knowledge_base/7-2/deleting-files). 

## File Versions

When a file is modified, Documents and Media creates a new file version and 
leaves the previous version intact. Over time, old file versions can accumulate 
and consume storage space. Fortunately, you can use the Documents and Media API 
to delete them. Note, however, that there's no way to send file versions to the 
Recycle Bin---once you delete them, they're gone forever. 

You can delete file versions with the `DLAppService` method `deleteFileVersion`: 

    deleteFileVersion(long fileEntryId, String version)

See this method's 
[Javadoc](@platform-ref@/7.2-latest/javadocs/portal-kernel/com/liferay/document/library/kernel/service/DLAppService.html#deleteFileVersion-long-java.lang.String-) 
for a description of the parameters. For step-by-step instructions on using this 
method, see 
[Deleting File Versions](/develop/tutorials/-/knowledge_base/7-2/deleting-file-versions). 

### Identifying File Versions

Since there may be many versions of a file, it's useful to programmatically 
identify old versions for deletion. You can do this with 
[`FileVersionVersionComparator`](@platform-ref@/7.2-latest/javadocs/portal-kernel/com/liferay/document/library/kernel/util/comparator/FileVersionVersionComparator.html). 

The following example creates such a comparator and uses its `compare` method to 
identify old file versions. The code does so by iterating through each 
[approved](/discover/portal/-/knowledge_base/7-2/workflow) 
version of the file (`fileVersion`). Each iteration uses the `compare` method to 
test that file version (`fileVersion.getVersion()`) against the same file's 
current version (`fileEntry.getVersion()`). If this comparison is greater than 
`0`, then the iteration's file version (`fileVersion`) is old and is deleted by 
`deleteFileVersion`: 

    FileVersionVersionComparator comparator = new FileVersionVersionComparator();

    for (FileVersion fileVersion: fileEntry.getVersions(WorkflowConstants.STATUS_APPROVED)) {

        if (comparator.compare(fileEntry.getVersion(), fileVersion.getVersion()) > 0) {

            dlAppService.deleteFileVersion(fileVersion.getFileEntryId(), fileVersion.getVersion());
        }
    }

## File Shortcuts

To delete file shortcuts, use the `DLAppService` method 
[`deleteFileShortcut`](@platform-ref@/7.2-latest/javadocs/portal-kernel/com/liferay/document/library/kernel/service/DLAppService.html#deleteFileShortcut-long-) 
with the ID of the shortcut you want to delete: 

    deleteFileShortcut(long fileShortcutId)

For step-by-step instructions on using this method, see 
[Deleting File Shortcuts](/develop/tutorials/-/knowledge_base/7-2/deleting-file-shortcuts). 

## Folders

Deleting folders is similar to deleting files. There are two methods you can use 
to delete a folder. Click each method to see its Javadoc: 

-   [`deleteFolder(long folderId)`](@platform-ref@/7.2-latest/javadocs/portal-kernel/com/liferay/document/library/kernel/service/DLAppService.html#deleteFolder-long-) 

-   [`deleteFolder(long repositoryId, long parentFolderId, String name)`](@platform-ref@/7.2-latest/javadocs/portal-kernel/com/liferay/document/library/kernel/service/DLAppService.html#deleteFolder-long-long-java.lang.String-) 

Which method you use is up to you---they both delete a folder. For step-by-step 
instructions on using these methods, see 
[Deleting Folders](/develop/tutorials/-/knowledge_base/7-2/deleting-folders). 

## Recycle Bin

Instead of deleting entities, you can move them to the 
[Recycle Bin](/discover/portal/-/knowledge_base/7-2/restoring-deleted-assets). 
Note that the Recycle Bin isn't part of the Documents and Media API. Although 
you can use the Recycle Bin API directly, in the case of Documents and Media 
it's better to use the Capabilities API. This is because some third-party 
repositories (e.g., SharePoint) don't support Recycle Bin functionality. The 
Capabilities API lets you verify that the repository you're working in supports 
the Recycle Bin. It's therefore a best practice to always use the Capabilities 
API when moving entities to the Recycle Bin. 

For step-by-step instructions on this, see 
[Moving Entities to the Recycle Bin](/develop/tutorials/-/knowledge_base/7-2/moving-entities-to-the-recycle-bin). 
