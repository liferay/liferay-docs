# Updating Folders [](id=updating-folders)

Once a folder exists, you can copy or move it to a different location. These 
operations are covered in 
[other tutorials](liferay.com). 
The options for in-place folder updates, however, are limited. You can only 
update a folder's name and description. You do this with the 
[`DLAppService`](@platform-ref@/7.1-latest/javadocs/portal-kernel/com/liferay/document/library/kernel/service/DLAppService.html) 
method `updateFolder`: 

    updateFolder(long folderId, String name, String description, ServiceContext serviceContext)

All parameters except the description are mandatory. For a full description of 
this method and its parameters, see its 
[Javadoc](@platform-ref@/7.1-latest/javadocs/portal-kernel/com/liferay/document/library/kernel/service/DLAppService.html#updateFolder-long-java.lang.String-java.lang.String-com.liferay.portal.kernel.service.ServiceContext-). 

The following example comes from @product@'s 
[`EditFolderMVCActionCommand`](https://github.com/liferay/liferay-portal/blob/master/modules/apps/document-library/document-library-web/src/main/java/com/liferay/document/library/web/internal/portlet/action/EditFolderMVCActionCommand.java) 
class. This class implements almost all the `Folder` actions that the Documents 
and Media UI supports. This class defines its own `updateFolder` method that 
contains logic to add and update folders. Note that this is the same method from 
the example in the 
[tutorial on creating folders](liferay.com). The example here, however, focuses 
on the code that updates a folder.

This method gets the data from the request that it needs to add or update a 
folder. If there's no existing folder (`folderId <= 0`), it adds a new folder. 
If there's an existing folder, then it's updated by the `DLAppService` method 
`updateFolder`: 

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
            }
            else {

                    // Update folder
                    _dlAppService.updateFolder(
                            folderId, name, description, serviceContext);
            }
    }

## Related Topics

[Creating Folders](/develop/tutorials/-/knowledge_base/7-1/creating-folders)

[Deleting Folders](/develop/tutorials/-/knowledge_base/7-1/deleting-folders)

[Copying Folders](/develop/tutorials/-/knowledge_base/7-1/copying-folders)

[Moving Folders and Files](/develop/tutorials/-/knowledge_base/7-1/moving-folders-and-files)
