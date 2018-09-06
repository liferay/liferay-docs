# Getting Entities [](id=getting-entities)

The Documents and Media API contains many methods for getting entities from a 
repository. Most methods in 
[`DLAppService`](@platform-ref@/7.1-latest/javadocs/portal-kernel/com/liferay/document/library/kernel/service/DLAppService.html) 
are for getting either single entities (e.g., a file or folder), a collection 
of entities that match certain characteristics, or the number of such entities. 
Because there are so many such methods, and because they're very similar, this 
tutorial doesn't describe them all in detail. Focus is instead placed on the 
most common methods and parameters. 

## Getting Files [](id=getting-files)

Getting files is one of the most common tasks you'll perform with the Documents 
and Media API. There are two main method families for getting files: 

-   `getFileEntries`: Get files from a specific repository. 
-   `getGroupFileEntries`: Get files from a site (group), regardless of 
    repository. 

Since these method families are common, their methods share many of the 
following parameters: 

-   `repositoryId`: The ID of the repository to get files from. Use the 
    `groupId` (site ID) to specify the default repository. 
-   `folderId`: The ID of the folder to get files from. Note that these methods 
    don't traverse the folder structure---they only get files directly from the 
    specified folder. To specify the repository's root folder, you can use the 
    constant `DLFolderConstants.DEFAULT_PARENT_FOLDER_ID`. 
-   `start` and `end`: Integers that specify the lower and upper bounds, 
    respectively, of collection items to include in a page of results. If you 
    don't want to use pagination, use `QueryUtil.ALL_POS` for these parameters. 
-   `obc`: The comparator to use to order collection items. Comparators are 
    implementations of the 
    [`OrderByComparator`](@platform-ref@/7.1-latest/javadocs/portal-kernel/com/liferay/portal/kernel/util/OrderByComparator.html) 
    class that sort collection items. 
-   `fileEntryTypeId`: The ID of the type of files to retrieve. Use this to only 
    retrieve files of a specific type. 
-   `mimeTypes`: The MIME types of the files to retrieve. Use this to only 
    retrieve files of the specified MIME types. You can specify MIME types via 
    the constants in 
    [`ContentTypes`](@platform-ref@/7.1-latest/javadocs/portal-kernel/com/liferay/portal/kernel/util/ContentTypes.html). 

Note that the `obc` parameter must be an implementation of `OrderByComparator`. 
Although you can implement your own comparators, @product@ already contains a 
few useful implementations in the package 
[`com.liferay.document.library.kernel.util.comparator`](@platform-ref@/7.1-latest/javadocs/portal-kernel/com/liferay/document/library/kernel/util/comparator/package-summary.html): 

-   `RepositoryModelCreateDateComparator`: Sorts by creation date. 
-   `RepositoryModelModifiedDateComparator`: Sorts by modification date. 
-   `RepositoryModelReadCountComparator`: Sorts by number of views. 
-   `RepositoryModelSizeComparator`: Sorts by file size. 
-   `RepositoryModelTitleComparator`: Sorts by title. 

As an example, this 
[`getFileEntries`](@platform-ref@/7.1-latest/javadocs/portal-kernel/com/liferay/document/library/kernel/service/DLAppService.html#getFileEntries-long-long-java.lang.String:A-int-int-com.liferay.portal.kernel.util.OrderByComparator-) 
method contains all the above parameters except for `fileEntryTypeId` (it 
contains `mimeTypes` instead): 

    List<FileEntry> getFileEntries(
            long repositoryId, 
            long folderId, 
            String[] mimeTypes, 
            int start, 
            int end, 
            OrderByComparator<FileEntry> obc
    )

You can use this method, for example, to get all the PNG images from the root 
folder of a site's default repository, sorted by title. Here's the call you 
would make to get these images without paginating the results: 

    List<FileEntry> fileEntries = 
            dlAppService.getFileEntries(
                    groupId, 
                    DLFolderConstants.DEFAULT_PARENT_FOLDER_ID, 
                    new String[] {ContentTypes.IMAGE_PNG}, 
                    QueryUtil.ALL_POS, 
                    QueryUtil.ALL_POS, 
                    new RepositoryModelTitleComparator<>()
            );

Using the `groupId` as the `repositoryId` specifies the site's default 
repository. The constants `DLFolderConstants.DEFAULT_PARENT_FOLDER_ID` and 
`ContentTypes.IMAGE_PNG` specify the root folder and PNG file MIME type, 
respectively. Also note that using `QueryUtil.ALL_POS` for both the `start` and 
`end` parameters skips pagination. And lastly, using 
`RepositoryModelTitleComparator` sorts the results by title. 

Remember, this is just one of many `getFileEntries` and `getGroupFileEntries` 
methods. To see all such methods, see the 
[`DLAppService` Javadoc](@platform-ref@/7.1-latest/javadocs/portal-kernel/com/liferay/document/library/kernel/service/DLAppService.html). 

## Getting Folders [](id=getting-folders)

The Documents and Media API can also retrieve folders. Getting folders is very 
similar to getting files. The main difference is that folder retrieval methods 
may have an additional argument to tell the system whether to include 
*mount folders*. Mount folders are mount points for external repositories (e.g. 
Alfresco or SharePoint). These folders appear in a site's default repository 
when an external repository is mounted, letting users navigate seamlessly 
between repositories. To account for this, some folder retrieval methods include 
the boolean parameter `includeMountFolders`. Setting this parameter to `true` 
includes mount folders in the results. Omitting the parameter or setting it to 
`false` excludes mount folders from the results. 

For example, to get a list of a parent folder's subfolders from a given 
repository, including any mount folders, you can use this 
[`getFolders`](@platform-ref@/7.1-latest/javadocs/portal-kernel/com/liferay/document/library/kernel/service/DLAppService.html#getFolders-long-long-boolean-) 
method: 

    getFolders(long repositoryId, long parentFolderId, boolean includeMountFolders)

This example retrieves such folders from the root folder of a site's default 
repository. Note the similarity to the previous example on retrieving files. In 
both examples, the `groupId` and `DEFAULT_PARENT_FOLDER_ID` specify the site's 
default repository and root parent folder, respectively. This example, however, 
sets `includeMountFolders` to `true` to include any mount folders in the 
results: 

    dlAppService.getFolders(groupId, DLFolderConstants.DEFAULT_PARENT_FOLDER_ID, true)

This is one of many methods you can use to get folders. The rest are listed in 
the 
[`DLAppService` Javadoc](@platform-ref@/7.1-latest/javadocs/portal-kernel/com/liferay/document/library/kernel/service/DLAppService.html). 

## Getting Multiple Entity Types [](id=getting-multiple-entity-types)

There are also methods in the Documents and Media API that retrieve lists of 
several entity types. These methods use many of the same parameters as those 
already described for retrieving files and folders. For example, 
[this method](@platform-ref@/7.1-latest/javadocs/portal-kernel/com/liferay/document/library/kernel/service/DLAppService.html#getFileEntriesAndFileShortcuts-long-long-int-int-int-) 
gets files and shortcuts from a given repository and folder. The `status` 
parameter specifies a 
[workflow](/discover/portal/-/knowledge_base/7-1/workflow) 
status. And as before, the `start` and `end` parameters control pagination of 
the entities: 

    getFileEntriesAndFileShortcuts(long repositoryId, long folderId, int status, int start, int end)

To see all such methods, see the 
[`DLAppService` Javadoc](@platform-ref@/7.1-latest/javadocs/portal-kernel/com/liferay/document/library/kernel/service/DLAppService.html). 

## Related Topics [](id=related-topics)

[Getting Started with the Documents and Media API](/develop/tutorials/-/knowledge_base/7-1/getting-started-with-the-documents-and-media-api)

[Creating Entities](/develop/tutorials/-/knowledge_base/7-1/creating-entities)

[Deleting Entities](/develop/tutorials/-/knowledge_base/7-1/deleting-entities)

[Updating Entities](/develop/tutorials/-/knowledge_base/7-1/updating-entities)

[Copying and Moving Entities](/develop/tutorials/-/knowledge_base/7-1/copying-and-moving-entities)
