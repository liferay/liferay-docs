---
header-id: updating-folders
---

# Updating Folders

[TOC levels=1-4]

The Documents and Media API lets you 
[copy or move](/docs/7-1/tutorials/-/knowledge_base/t/copying-and-moving-entities) 
folders to a different location. Options for in-place folder updates, however, 
are limited. You can only update a folder's name and description. You can do 
this with the 
[`DLAppService`](@platform-ref@/7.1-latest/javadocs/portal-kernel/com/liferay/document/library/kernel/service/DLAppService.html) 
method `updateFolder`: 

    updateFolder(long folderId, String name, String description, ServiceContext serviceContext)

All parameters except the description are mandatory. For a full description of 
this method and its parameters, see its 
[Javadoc](@platform-ref@/7.1-latest/javadocs/portal-kernel/com/liferay/document/library/kernel/service/DLAppService.html#updateFolder-long-java.lang.String-java.lang.String-com.liferay.portal.kernel.service.ServiceContext-). 

Follow these steps to use this method to update a folder: 

1.  Get a reference to `DLAppService`: 

        @Reference
        private DLAppService _dlAppService;

    For more information on this, see the section on 
    [getting a service reference](/docs/7-1/tutorials/-/knowledge_base/t/getting-started-with-the-documents-and-media-api#getting-a-service-reference) 
    in the getting started tutorial. 

2.  Get the data needed to populate the `updateFolder` method's arguments. Since 
    it's common to update a folder with data submitted by the end user, you can 
    extract the data from the request. This example does so via 
    `javax.portlet.ActionRequest` and 
    [`ParamUtil`](@platform-ref@/7.1-latest/javadocs/portal-kernel/com/liferay/portal/kernel/util/ParamUtil.html), 
    but you can get the data any way you wish: 

        long folderId = ParamUtil.getLong(actionRequest, "folderId");
        String name = ParamUtil.getString(actionRequest, "name");
        String description = ParamUtil.getString(actionRequest, "description");

        ServiceContext serviceContext = ServiceContextFactory.getInstance(
                    DLFolder.class.getName(), actionRequest);

    For more information on getting folder IDs, see the 
    [getting started tutorial's](/docs/7-1/tutorials/-/knowledge_base/t/getting-started-with-the-documents-and-media-api) 
    section on specifying folders. For more information on `ServiceContext`, see 
    the tutorial 
    [Understanding ServiceContext](/docs/7-1/tutorials/-/knowledge_base/t/understanding-servicecontext). 

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

[Creating Folders](/docs/7-1/tutorials/-/knowledge_base/t/creating-folders)

[Deleting Folders](/docs/7-1/tutorials/-/knowledge_base/t/deleting-folders)

[Copying Folders](/docs/7-1/tutorials/-/knowledge_base/t/copying-folders)

[Moving Folders and Files](/docs/7-1/tutorials/-/knowledge_base/t/moving-folders-and-files)
