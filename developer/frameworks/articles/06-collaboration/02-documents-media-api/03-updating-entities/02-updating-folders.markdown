---
header-id: updating-folders
---

# Updating Folders

[TOC levels=1-4]

To update a folder with the Documents and Media API, you must use the 
`updateFolder` method discussed in 
[Updating Entities](/docs/7-2/frameworks/-/knowledge_base/frameworks/updating-entities). 
The steps here show you how. For general information on using the API, see 
[Documents and Media API](/docs/7-2/frameworks/-/knowledge_base/frameworks/documents-and-media-api). 

Follow these steps to update a folder: 

1.  Get a reference to `DLAppService`: 

        @Reference
        private DLAppService _dlAppService;

2.  Get the data needed to populate the `updateFolder` method's arguments. Since 
    it's common to update a folder with data submitted by the end user, you can 
    extract the data from the request. This example does so via 
    `javax.portlet.ActionRequest` and 
    [`ParamUtil`](@platform-ref@/7.2-latest/javadocs/portal-kernel/com/liferay/portal/kernel/util/ParamUtil.html), 
    but you can get the data any way you wish: 

        long folderId = ParamUtil.getLong(actionRequest, "folderId");
        String name = ParamUtil.getString(actionRequest, "name");
        String description = ParamUtil.getString(actionRequest, "description");

        ServiceContext serviceContext = ServiceContextFactory.getInstance(
                    DLFolder.class.getName(), actionRequest);

    For more information on `ServiceContext`, see the tutorial 
    [Understanding ServiceContext](/docs/7-2/frameworks/-/knowledge_base/frameworks/understanding-servicecontext). 

3.  Call the service reference's `updateFolder` method with the data from the 
    previous step: 

        _dlAppService.updateFolder(folderId, name, description, serviceContext);

You can find the full code for this example in the `updateFolder` method of 
@product@'s 
[`EditFolderMVCActionCommand`](https://github.com/liferay/liferay-portal/blob/master/modules/apps/document-library/document-library-web/src/main/java/com/liferay/document/library/web/internal/portlet/action/EditFolderMVCActionCommand.java) 
class. This class uses the Documents and Media API to implement almost all the 
`Folder` actions that the Documents and Media app supports. Also note that 
this `updateFolder` method, as well as the rest of `EditFolderMVCActionCommand`, 
contains additional logic to suit the specific needs of the Documents and Media 
app. 

## Related Topics

[Creating Folders](/docs/7-2/frameworks/-/knowledge_base/frameworks/creating-folders)

[Deleting Folders](/docs/7-2/frameworks/-/knowledge_base/frameworks/deleting-folders)

[Copying Folders](/docs/7-2/frameworks/-/knowledge_base/frameworks/copying-folders)

[Moving Folders and Files](/docs/7-2/frameworks/-/knowledge_base/frameworks/moving-folders-and-files)
