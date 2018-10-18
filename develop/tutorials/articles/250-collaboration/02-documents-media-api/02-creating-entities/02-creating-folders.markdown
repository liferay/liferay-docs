# Creating Folders [](id=creating-folders)

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
    [getting a service reference](/develop/tutorials/-/knowledge_base/7-1/getting-started-with-the-documents-and-media-api#getting-a-service-reference) 
    in the getting started tutorial. 

2.  Get the data needed to populate the `addFolder` method's arguments. Since 
    it's common to create a folder with data submitted by the end user, you can 
    extract the data from the request. This example does so via 
    `javax.portlet.ActionRequest` and 
    [`ParamUtil`](@platform-ref@/7.1-latest/javadocs/portal-kernel/com/liferay/portal/kernel/util/ParamUtil.html), 
    but you can get the data any way you wish: 

        long repositoryId = ParamUtil.getLong(actionRequest, "repositoryId");
        long parentFolderId = ParamUtil.getLong(actionRequest, "parentFolderId");
        String name = ParamUtil.getString(actionRequest, "name");
        String description = ParamUtil.getString(actionRequest, "description");

        ServiceContext serviceContext = ServiceContextFactory.getInstance(
                    DLFolder.class.getName(), actionRequest);

    For more information on getting repository and folder IDs, see the 
    [getting started tutorial's](/develop/tutorials/-/knowledge_base/7-1/getting-started-with-the-documents-and-media-api) 
    sections on specifying repositories and folders. For more information on 
    `ServiceContext`, see the tutorial 
    [Understanding ServiceContext](/develop/tutorials/-/knowledge_base/7-1/understanding-servicecontext). 

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

## Folders and External Repositories [](id=folders-and-external-repositories)

By creating a folder that acts as a proxy for an external repository (e.g., 
SharePoint), you can effectively mount that repository inside a Site's default 
repository. When users enter this special folder, they see the external 
repository. These folders are called *mount points*. You can create one via the
API by setting the 
[Service Context's](/develop/tutorials/-/knowledge_base/7-1/understanding-servicecontext) 
`mountPoint` attribute to `true`, and then using that Service Context in the 
`addFolder` method: 

    serviceContext.setAttribute("mountPoint", true);

Note that the `repositoryId` of such a folder indicates the external repository 
the folder points to---not the repository that the folder exists in. Also, mount 
point folders can only exist in the default Site repository. 

## Related Topics [](id=related-topics)

[Updating Folders](/develop/tutorials/-/knowledge_base/7-1/updating-folders)

[Deleting Folders](/develop/tutorials/-/knowledge_base/7-1/deleting-folders)

[Copying Folders](/develop/tutorials/-/knowledge_base/7-1/copying-folders)

[Moving Folders and Files](/develop/tutorials/-/knowledge_base/7-1/moving-folders-and-files)
