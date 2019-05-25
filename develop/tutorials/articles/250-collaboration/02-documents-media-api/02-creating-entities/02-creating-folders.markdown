---
header-id: creating-folders
---

# Creating Folders

[TOC levels=1-4]

To create folders (`Folder` entities) in the Documents and Media library, you 
must use the 
[`DLAppService`](@platform-ref@/7.1-latest/javadocs/portal-kernel/com/liferay/document/library/kernel/service/DLAppService.html) 
interface's `addFolder` method: 

    addFolder(long repositoryId, 
            long parentFolderId, 
            String name, 
            String description, 
            ServiceContext serviceContext)

See 
[this method's Javadoc](@platform-ref@/7.1-latest/javadocs/portal-kernel/com/liferay/document/library/kernel/service/DLAppService.html#addFolder-long-long-java.lang.String-java.lang.String-com.liferay.portal.kernel.service.ServiceContext-) 
for a description of the parameters. Note that the `description` parameter is 
optional. 

Follow these steps to create a folder with the `DLAppService` method 
`addFolder`: 

1.  Get a reference to `DLAppService`: 

        @Reference
        private DLAppService _dlAppService;

    For more information on this, see the section on 
    [getting a service reference](/docs/7-1/tutorials/-/knowledge_base/t/getting-started-with-the-documents-and-media-api#getting-a-service-reference) 
    in the getting started tutorial. 

2.  Get the data needed to populate the `addFolder` method's arguments. Since 
    it's common to create a folder with data submitted by the end user, you can 
    extract the data from the request. This example does so via 
    `javax.portlet.ActionRequest` and 
    [`ParamUtil`](@platform-ref@/7.1-latest/javadocs/portal-kernel/com/liferay/portal/kernel/util/ParamUtil.html): 

        long repositoryId = ParamUtil.getLong(actionRequest, "repositoryId");
        long parentFolderId = ParamUtil.getLong(actionRequest, "parentFolderId");
        String name = ParamUtil.getString(actionRequest, "name");
        String description = ParamUtil.getString(actionRequest, "description");

        ServiceContext serviceContext = ServiceContextFactory.getInstance(
                    DLFolder.class.getName(), actionRequest);

    For more information on getting repository and folder IDs, see the 
    [getting started tutorial's](/docs/7-1/tutorials/-/knowledge_base/t/getting-started-with-the-documents-and-media-api) 
    sections on specifying repositories and folders. For more information on 
    `ServiceContext`, see the tutorial 
    [Understanding ServiceContext](/docs/7-1/tutorials/-/knowledge_base/t/understanding-servicecontext). 

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

## Folders and External Repositories

By creating a folder that acts as a proxy for an external repository (e.g., 
SharePoint), you can effectively mount that repository inside a Site's default 
repository. When users enter this special folder, they see the external 
repository. These folders are called *mount points*. You can create one via the
API by setting the 
[Service Context's](/docs/7-1/tutorials/-/knowledge_base/t/understanding-servicecontext) 
`mountPoint` attribute to `true`, and then using that Service Context in the 
`addFolder` method: 

    serviceContext.setAttribute("mountPoint", true);

Note that the `repositoryId` of such a folder indicates the external repository 
the folder points to---not the repository the folder exists. Also, mount 
point folders can only exist in the default Site repository. 

## Related Topics

[Updating Folders](/docs/7-1/tutorials/-/knowledge_base/t/updating-folders)

[Deleting Folders](/docs/7-1/tutorials/-/knowledge_base/t/deleting-folders)

[Copying Folders](/docs/7-1/tutorials/-/knowledge_base/t/copying-folders)

[Moving Folders and Files](/docs/7-1/tutorials/-/knowledge_base/t/moving-folders-and-files)
