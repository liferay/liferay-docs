---
header-id: getting-entities
---

# Getting Entities

The Documents and Media API contains many methods for getting entities from a 
repository. Most methods in `DLAppService` get single entities (e.g., a file or 
folder), a collection of entities that match certain characteristics, or the 
number of such entities. Because there are so many similar methods for getting 
entities, they aren't all described here. You can find full descriptions for all 
`DLAppService` methods in its 
[reference documentation](@platform-ref@/7.2-latest/javadocs/portal-kernel/com/liferay/document/library/kernel/service/DLAppService.html). 

Here, you'll learn about getting these entities: 

-   [Files](#files)
-   [Folders](#folders)

## Files

Getting files is one of the most common tasks you'll perform with the Documents 
and Media API. There are two main method families for getting files: 

`getFileEntries`: Gets files from a specific repository. 

`getGroupFileEntries`: Gets files from a Site (group), regardless of repository. 

Since these method families are common, their methods share many parameters: 

`repositoryId`: The ID of the repository to get files from. To specify the 
default Site repository, use the `groupId` (Site ID). 

`folderId`: The ID of the folder to get files from. Note that these methods 
don't traverse the folder structure---they only get files directly from the 
specified folder. To specify the repository's root folder, use the constant 
`DLFolderConstants.DEFAULT_PARENT_FOLDER_ID`. 

`start` and `end`: Integers that specify the lower and upper bounds, 
respectively, of collection items to include in a page of results. If you don't 
want to use pagination, use `QueryUtil.ALL_POS` for these parameters. 

`obc`: The comparator to use to order collection items. Comparators are 
[`OrderByComparator`](@platform-ref@/7.2-latest/javadocs/portal-kernel/com/liferay/portal/kernel/util/OrderByComparator.html) 
implementations that sort collection items. 

`fileEntryTypeId`: The ID of the file type to retrieve. Use this to retrieve 
files of a specific type. 

`mimeTypes`: The MIME types of the files to retrieve. Use this to retrieve files 
of the specified MIME types. You can specify MIME types via the constants in 
[`ContentTypes`](@platform-ref@/7.2-latest/javadocs/portal-kernel/com/liferay/portal/kernel/util/ContentTypes.html). 

Note that the `obc` parameter must be an implementation of `OrderByComparator`. 
Although you can implement your own comparators, @product@ already contains a 
few useful implementations in the package 
[`com.liferay.document.library.kernel.util.comparator`](@platform-ref@/7.2-latest/javadocs/portal-kernel/com/liferay/document/library/kernel/util/comparator/package-summary.html): 

`RepositoryModelCreateDateComparator`: Sorts by creation date. 

`RepositoryModelModifiedDateComparator`: Sorts by modification date. 

`RepositoryModelReadCountComparator`: Sorts by number of views. 

`RepositoryModelSizeComparator`: Sorts by file size. 

`RepositoryModelTitleComparator`: Sorts by title. 

See 
[Getting Files](liferay.com) 
for step-by-step instructions on using the above method families. 

## Folders

The Documents and Media API can get folders in a similar way to getting files.
The main difference is that folder retrieval methods may have an additional
argument to tell the system whether to include *mount folders*. Mount folders
are mount points for external repositories (e.g. Alfresco or SharePoint) that
appear as regular folders in a Site's default repository. They let users 
navigate seamlessly between repositories. To account for this, some folder 
retrieval methods include the boolean parameter `includeMountFolders`. Setting 
this parameter to `true` includes mount folders in the results, while omitting 
it or setting it to `false` excludes them. 

For example, to get a list of a parent folder's subfolders from a repository, 
including any mount folders, use this 
[`getFolders`](@platform-ref@/7.2-latest/javadocs/portal-kernel/com/liferay/document/library/kernel/service/DLAppService.html#getFolders-long-long-boolean-) 
method: 

    getFolders(long repositoryId, long parentFolderId, boolean includeMountFolders)

Note that there are several other `getFolders` methods in `DLAppService`. Use 
the one that best matches your use case. See 
[Getting Folders](liferay.com) 
for step-by-step instructions on using these `getFolders` methods. 
