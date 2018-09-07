# Updating File Shortcuts [](id=updating-file-shortcuts)

The Documents and Media API lets you update file shortcuts (`FileShortcut` 
entities). You can update a shortcut to change the file it points to or the 
folder it resides in. You can do this via the 
[`DLAppService`](@platform-ref@/7.1-latest/javadocs/portal-kernel/com/liferay/document/library/kernel/service/DLAppService.html) 
method `updateFileShortcut`: 

    updateFileShortcut(long fileShortcutId, long folderId, long toFileEntryId, ServiceContext serviceContext)

All of this method's parameters are mandatory. To retain any of the shortcut's 
original values, you must provide them to this method. For a full description of 
the parameters, see the method's 
[Javadoc](@platform-ref@/7.1-latest/javadocs/portal-kernel/com/liferay/document/library/kernel/service/DLAppService.html#updateFileShortcut-long-long-long-com.liferay.portal.kernel.service.ServiceContext-). 

The following example comes from @product@'s 
[`EditFileShortcutMVCActionCommand`](https://github.com/liferay/liferay-portal/blob/master/modules/apps/document-library/document-library-web/src/main/java/com/liferay/document/library/web/internal/portlet/action/EditFileShortcutMVCActionCommand.java) 
class. This class implements almost all the `FileShortcut` actions that the 
Documents and Media UI supports. This class defines its own `updateFileShortcut` 
method that contains logic to add and update file shortcuts. Note that this is 
the same method from the example in the 
[tutorial on creating file shortcuts](/develop/tutorials/-/knowledge_base/7-1/creating-file-shortcuts). 
The example here, however, focuses on updating the shortcut. 

This method uses the request to get the data it needs to add or update a 
shortcut. It adds a new shortcut if there's not an existing one 
(`fileShortcutId <= 0`). If there's an existing shortcut, then the 
`DLAppService` method `updateFileShortcut` updates it: 

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
            }
            else {

                    // Update file shortcut
                    _dlAppService.updateFileShortcut(
                            fileShortcutId, folderId, toFileEntryId, serviceContext);
            }
    }

## Related Topics [](id=related-topics)

[Creating File Shortcuts](/develop/tutorials/-/knowledge_base/7-1/creating-file-shortcuts)

[Deleting File Shortcuts](/develop/tutorials/-/knowledge_base/7-1/deleting-file-shortcuts)
