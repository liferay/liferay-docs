# Creating File Shortcuts [](id=creating-file-shortcuts)

To create file shortcuts (`FileShortcut` entities) in the Documents and Media 
library, you must use the 
[`DLAppService`](@platform-ref@/7.1-latest/javadocs/portal-kernel/com/liferay/document/library/kernel/service/DLAppService.html) 
interface's `addFileShortcut` method: 

    addFileShortcut(long repositoryId, 
                    long folderId, 
                    long toFileEntryId, 
                    ServiceContext serviceContext)

See 
[this method's Javadoc](@platform-ref@/7.1-latest/javadocs/portal-kernel/com/liferay/document/library/kernel/service/DLAppService.html#addFileShortcut-long-long-long-com.liferay.portal.kernel.service.ServiceContext-) 
for a description of the parameters. Note that all this method's parameters are 
mandatory. 

Keep in mind the following when creating shortcuts: 

-   You can create a shortcut to a file in a different site, if that file and 
    its resulting shortcut are in the same portal instance. 
-   You can't create folder shortcuts. 
-   Shortcuts can only exist in the default site repository. If you try to 
    invoke `addFileShortcut` with the repository ID of an external repository 
    (e.g., a SharePoint repository), the operation will fail. Since different 
    repositories support different features, the Documents and Media API only 
    supports the common denominators for all repositories: files and folders. 

The following example comes from @product@'s 
[`EditFileShortcutMVCActionCommand`](https://github.com/liferay/liferay-portal/blob/master/modules/apps/document-library/document-library-web/src/main/java/com/liferay/document/library/web/internal/portlet/action/EditFileShortcutMVCActionCommand.java) 
class. This class implements almost all the `FileShortcut` actions that the 
Documents and Media UI supports. This class's `updateFileShortcut` method 
contains logic to add and update file shortcuts. This method uses the request to 
get the data it needs to add or update a shortcut. If there's no existing 
shortcut (`fileShortcutId <= 0`), it adds a new one by calling the 
`addFileShortcut` method with required data: 

    protected void updateFileShortcut(ActionRequest actionRequest)
            throws Exception {

            long fileShortcutId = ParamUtil.getLong(actionRequest, "fileShortcutId");

            long repositoryId = ParamUtil.getLong(actionRequest, "repositoryId");
            long folderId = ParamUtil.getLong(actionRequest, "folderId");
            long toFileEntryId = ParamUtil.getLong(actionRequest, "toFileEntryId");

            ServiceContext serviceContext = ServiceContextFactory.getInstance(
                    DLFileShortcutConstants.getClassName(), actionRequest);

            if (fileShortcutId <= 0) {

                    // Add file shortcut

                    _dlAppService.addFileShortcut(
                            repositoryId, folderId, toFileEntryId, serviceContext);
            }
            else {

                    // Update file shortcut
                    ...
            }
    }

See the tutorial on 
[updating file shortcuts](/develop/tutorials/-/knowledge_base/7-1/updating-file-shortcuts) 
for detailed information on that operation. 

## Related Topics [](id=related-topics)

[Deleting File Shortcuts](/develop/tutorials/-/knowledge_base/7-1/deleting-file-shortcuts)

[Updating File Shortcuts](/develop/tutorials/-/knowledge_base/7-1/updating-file-shortcuts)
