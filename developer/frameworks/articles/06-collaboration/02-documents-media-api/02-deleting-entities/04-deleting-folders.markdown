---
header-id: deleting-folders
---

# Deleting Folders

[TOC levels=1-4]

To delete a folder with the Documents and Media API, you must use one of the two 
`deleteFolder` methods discussed in 
[Deleting Entities](/developer/frameworks/-/knowledge_base/7-2/deleting-entities). 
The steps here show you how. For general information on using the API, see 
[Documents and Media API](/developer/frameworks/-/knowledge_base/7-2/documents-and-media-api). 

Follow these steps to delete a folder: 

1.  Get a reference to `DLAppService`: 

        @Reference
        private DLAppService _dlAppService;

2.  Get the data needed to populate the arguments of the `deleteFolder` method 
    you wish to use. Since it's common to delete a folder specified by the end 
    user, you can extract the data you need from the request. This example does 
    so via `javax.portlet.ActionRequest` and 
    [`ParamUtil`](@platform-ref@/7.2-latest/javadocs/portal-kernel/com/liferay/portal/kernel/util/ParamUtil.html), 
    but you can get the data any way you wish. Also note that this example gets 
    only the folder ID because the next step deletes the folder with 
    `deleteFolder(folderId)`: 

        long folderId = ParamUtil.getLong(actionRequest, "folderId");

    If you want to use the other `deleteFolder` method, you can also get the 
    repository ID, parent folder ID, and folder name from the request. 

3.  Call the service reference's `deleteFolder` method you wish to use with the 
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

## Related Topics

[Moving Entities to the Recycle Bin](/developer/frameworks/-/knowledge_base/7-2/moving-entities-to-the-recycle-bin)

[Creating Folders](/developer/frameworks/-/knowledge_base/7-2/creating-folders)

[Updating Folders](/developer/frameworks/-/knowledge_base/7-2/updating-folders)

[Copying Folders](/developer/frameworks/-/knowledge_base/7-2/copying-folders)

[Moving Folders and Files](/developer/frameworks/-/knowledge_base/7-2/moving-folders-and-files)

[Deleting Files](/developer/frameworks/-/knowledge_base/7-2/deleting-files)
