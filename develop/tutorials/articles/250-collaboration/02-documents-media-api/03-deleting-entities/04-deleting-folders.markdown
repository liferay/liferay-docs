# Deleting Folders [](id=deleting-folders)

Deleting folders is similar to 
[deleting files](/develop/tutorials/-/knowledge_base/7-1/deleting-files). 
There are two methods you can use to delete a folder. Click each method to see 
its Javadoc: 

-   [`deleteFolder(long folderId)`](@platform-ref@/7.1-latest/javadocs/portal-kernel/com/liferay/document/library/kernel/service/DLAppService.html#deleteFolder-long-) 

-   [`deleteFolder(long repositoryId, long parentFolderId, String name)`](@platform-ref@/7.1-latest/javadocs/portal-kernel/com/liferay/document/library/kernel/service/DLAppService.html#deleteFolder-long-long-java.lang.String-) 

Which method you use is up to you---they both delete a folder. Follow these 
steps to use one of these methods to delete a folder: 

1.  Get a reference to `DLAppService`: 

        @Reference
        private DLAppService _dlAppService;

    For more information on this, see the section on 
    [getting a service reference](/develop/tutorials/-/knowledge_base/7-1/getting-started-with-the-documents-and-media-api#getting-a-service-reference) 
    in the getting started tutorial. 

2.  Get the data needed to populate the arguments of the `deleteFolder*` method 
    you wish to use. Since it's common to delete a folder specified by the end 
    user, you can extract the data you need from the request. This example does 
    so via `javax.portlet.ActionRequest` and 
    [`ParamUtil`](@platform-ref@/7.1-latest/javadocs/portal-kernel/com/liferay/portal/kernel/util/ParamUtil.html), 
    but you can get the data any way you wish. Also note that this example gets 
    only the folder ID because the next step deletes the folder with 
    `deleteFolder(folderId)`: 

        long folderId = ParamUtil.getLong(actionRequest, "folderId");

    If you want to use the other `deleteFolder` method, you can also get the 
    repository ID, parent folder ID, and folder name from the request. For more 
    information on getting repository and folder IDs, see the 
    [getting started tutorial's](/develop/tutorials/-/knowledge_base/7-1/getting-started-with-the-documents-and-media-api) 
    sections on specifying repositories and folders. 

3.  Call the service reference's `deleteFolder*` method you wish to use with the 
    data from the previous step. This example calls `deleteFolder` with the 
    folder's ID: 

        _dlAppService.deleteFolder(folderId);

You can find the full code for this example in the `deleteFolders` method of 
@product@'s 
[`EditFolderMVCActionCommand`](https://github.com/liferay/liferay-portal/blob/master/modules/apps/document-library/document-library-web/src/main/java/com/liferay/document/library/web/internal/portlet/action/EditFolderMVCActionCommand.java) 
class. This class uses the Documents and Media API to implement almost all the 
`Folder` actions that the Documents and Media app supports. Also note that this 
`deleteFolders` method, as well as the rest of `EditFolderMVCActionCommand`, 
contains additional logic to suit the specific needs of the Documents and Media 
app. 

## Related Topics [](id=related-topics)

[Moving Entities to the Recycle Bin](/develop/tutorials/-/knowledge_base/7-1/moving-entities-to-the-recycle-bin)

[Creating Folders](/develop/tutorials/-/knowledge_base/7-1/creating-folders)

[Updating Folders](/develop/tutorials/-/knowledge_base/7-1/updating-folders)

[Copying Folders](/develop/tutorials/-/knowledge_base/7-1/copying-folders)

[Moving Folders and Files](/develop/tutorials/-/knowledge_base/7-1/moving-folders-and-files)

[Deleting Files](/develop/tutorials/-/knowledge_base/7-1/deleting-files)
