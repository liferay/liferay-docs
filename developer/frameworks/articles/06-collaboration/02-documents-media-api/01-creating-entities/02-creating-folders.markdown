---
header-id: creating-folders
---

# Creating Folders

To create folders (`Folder` entities) in the Documents and Media library, you 
must use the 
[`DLAppService`](@platform-ref@/7.2-latest/javadocs/portal-kernel/com/liferay/document/library/kernel/service/DLAppService.html) 
interface's `addFolder` method. The steps here show you how to do this. For more 
detailed information, see 
[Creating Files, Folders, and Shortcuts](/developer/frameworks/-/knowledge_base/7-2/creating-files-folders-and-shortcuts). 
For general information on using the API, see 
[Documents and Media API](/developer/frameworks/-/knowledge_base/7-2/documents-and-media-api). 

Follow these steps to create a folder with the `DLAppService` method 
`addFolder`: 

1.  Get a reference to `DLAppService`: 

        @Reference
        private DLAppService _dlAppService;

2.  Get the data needed to populate the `addFolder` method's arguments. Since 
    it's common to create a folder with data submitted by the end user, you can 
    extract the data from the request. This example does so via 
    `javax.portlet.ActionRequest` and 
    [`ParamUtil`](@platform-ref@/7.2-latest/javadocs/portal-kernel/com/liferay/portal/kernel/util/ParamUtil.html): 

        long repositoryId = ParamUtil.getLong(actionRequest, "repositoryId");
        long parentFolderId = ParamUtil.getLong(actionRequest, "parentFolderId");
        String name = ParamUtil.getString(actionRequest, "name");
        String description = ParamUtil.getString(actionRequest, "description");

        ServiceContext serviceContext = ServiceContextFactory.getInstance(
                    DLFolder.class.getName(), actionRequest);

    For more information on `ServiceContext`, see the tutorial 
    [Understanding ServiceContext](/developer/frameworks/-/knowledge_base/7-2/understanding-servicecontext). 

3.  Call the service reference's `addFolder` method with the data from the 
    previous step: 

        Folder folder = _dlAppService.addFolder(
                                repositoryId, parentFolderId, name, description, 
                                serviceContext);

    The method returns a `Folder` object, which this example sets to a variable 
    for later use. Note, however, that you don't have to do this. 

You can find the full code for this example in the `updateFolder` method of 
@product@'s 
[`EditFolderMVCActionCommand`](https://github.com/liferay/liferay-portal/blob/master/modules/apps/document-library/document-library-web/src/main/java/com/liferay/document/library/web/internal/portlet/action/EditFolderMVCActionCommand.java) 
class. This class uses the Documents and Media API to implement almost all the 
`Folder` actions that the Documents and Media app supports. Also note that 
this `updateFolder` method, as well as the rest of `EditFolderMVCActionCommand`, 
contains additional logic to suit the specific needs of the Documents and Media 
app. 

## Related Topics

[Updating Folders](/developer/frameworks/-/knowledge_base/7-2/updating-folders)

[Deleting Folders](/developer/frameworks/-/knowledge_base/7-2/deleting-folders)

[Copying Folders](/developer/frameworks/-/knowledge_base/7-2/copying-folders)

[Moving Folders and Files](/developer/frameworks/-/knowledge_base/7-2/moving-folders-and-files)
