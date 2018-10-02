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

The following example comes from @product@'s 
[`EditFolderMVCActionCommand`](https://github.com/liferay/liferay-portal/blob/master/modules/apps/document-library/document-library-web/src/main/java/com/liferay/document/library/web/internal/portlet/action/EditFolderMVCActionCommand.java) 
class. This class implements almost all the `Folder` actions that the Documents 
and Media UI supports. This class's `updateFolder` method contains logic to add 
and update folders. This method uses the request to get the data it needs to 
add or update a folder. If there's no existing folder (`folderId <= 0`), it adds 
a new folder by calling the `addFolder` method with required data: 

    protected void updateFolder(ActionRequest actionRequest) throws Exception {
            long folderId = ParamUtil.getLong(actionRequest, "folderId");

            long repositoryId = ParamUtil.getLong(actionRequest, "repositoryId");
            long parentFolderId = ParamUtil.getLong(actionRequest, "parentFolderId");
            String name = ParamUtil.getString(actionRequest, "name");
            String description = ParamUtil.getString(actionRequest, "description");

            ServiceContext serviceContext = ServiceContextFactory.getInstance(
                    DLFolder.class.getName(), actionRequest);

            if (folderId <= 0) {

                    // Add folder

                    _dlAppService.addFolder(
                            repositoryId, parentFolderId, name, description,
                            serviceContext);
            }
            else {

                    // Update folder
                    ...
            }
    }

See the tutorial on 
[updating folders](/develop/tutorials/-/knowledge_base/7-1/updating-folders) 
for detailed information on that operation. 

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
