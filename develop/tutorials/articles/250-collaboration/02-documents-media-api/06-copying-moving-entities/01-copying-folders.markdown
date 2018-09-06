# Copying Folders [](id=copying-folders)

The Documents and Media API lets you copy folders within a repository. You 
can't, however, copy a folder between different repositories. Also note that 
copying a folder also copies its contents. 

To copy a folder, use the 
[`DLAppService`](@platform-ref@/7.1-latest/javadocs/portal-kernel/com/liferay/document/library/kernel/service/DLAppService.html) 
method `copyFolder`: 

    copyFolder(long repositoryId, long sourceFolderId, long parentFolderId, String name, 
            String description, ServiceContext serviceContext)

For a full description of the method and its parameters, see its 
[Javadoc](@platform-ref@/7.1-latest/javadocs/portal-kernel/com/liferay/document/library/kernel/service/DLAppService.html#copyFolder-long-long-long-java.lang.String-java.lang.String-com.liferay.portal.kernel.service.ServiceContext-). 

This example copies a folder to the default site repository's root folder: 

    dlAppService.copyFolder(
            groupId, folder.getFolderId(), DLFolderConstants.DEFAULT_PARENT_FOLDER_ID, 
            folder.getName(), folder.getDescription(), serviceContext);

Here's an explanation of the arguments: 

-   `groupId`: As the 
    [getting started tutorial](/develop/tutorials/-/knowledge_base/7-1/getting-started-with-the-documents-and-media-api) 
    explains, using the `groupId` as the repository ID specifies that the copy 
    takes place in the default site repository. 
-   `folder.getFolderId()`: The folder ID of the folder to copy. 
-   `DLFolderConstants.DEFAULT_PARENT_FOLDER_ID`: The ID of the destination 
    folder. As the getting started tutorial explains, this constant specifies 
    the repository's root folder. 
-   `folder.getName()` and `folder.getDescription()`: The name and description 
    of the destination folder. Note that this example uses the same values as 
    the source folder, which is typical for a copy operation. You can, however, 
    use different values if you wish. 
-   `serviceContext`: The 
    [Service Context](/develop/tutorials/-/knowledge_base/7-1/understanding-servicecontext). 

## Related Topics [](id=related-topics)

[Getting Started with the Documents and Media API](/develop/tutorials/-/knowledge_base/7-1/getting-started-with-the-documents-and-media-api)

[Understanding Service Context](/develop/tutorials/-/knowledge_base/7-1/understanding-servicecontext)

[Creating Folders](/develop/tutorials/-/knowledge_base/7-1/creating-folders)

[Updating Folders](/develop/tutorials/-/knowledge_base/7-1/updating-folders)

[Deleting Folders](/develop/tutorials/-/knowledge_base/7-1/deleting-folders)

[Moving Folders and Files](/develop/tutorials/-/knowledge_base/7-1/moving-folders-and-files)
