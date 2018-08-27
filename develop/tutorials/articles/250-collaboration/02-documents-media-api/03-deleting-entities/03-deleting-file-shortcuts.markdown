# Deleting File Shortcuts [](id=deleting-file-shortcuts)

The Documents and Media API also lets you delete file shortcuts. To do so, use 
the 
[`DLAppService`](@platform-ref@/7.1-latest/javadocs/portal-kernel/com/liferay/document/library/kernel/service/DLAppService.html) 
method 
[`deleteFileShortcut`](@platform-ref@/7.1-latest/javadocs/portal-kernel/com/liferay/document/library/kernel/service/DLAppService.html#deleteFileShortcut-long-) 
with the ID of the shortcut you want to delete: 

    deleteFileShortcut(long fileShortcutId)

The following example comes from @product@'s 
[`EditFileShortcutMVCActionCommand`](https://github.com/liferay/liferay-portal/blob/master/modules/apps/document-library/document-library-web/src/main/java/com/liferay/document/library/web/internal/portlet/action/EditFileShortcutMVCActionCommand.java) 
class. This class implements almost all the `FileShortcut` actions that the 
Documents and Media UI supports. This class defines its own `deleteFileShortcut` 
method that calls that of `DLAppService`. 

The `EditFileShortcutMVCActionCommand` class's `deleteFileShortcut` method first 
gets the file shortcut's ID from the request. If the `moveToTrash` flag is 
`true`, the method moves the shortcut to the 
[Recycle Bin](/discover/portal/-/knowledge_base/7-1/restoring-deleted-assets). 
If the flag is `false`, the method deletes the shortcut by calling the 
`DLAppService` method `deleteFileShortcut` with the shortcut's ID. 

    protected void deleteFileShortcut(
                    ActionRequest actionRequest, boolean moveToTrash)
            throws Exception {

            long fileShortcutId = ParamUtil.getLong(
                    actionRequest, "fileShortcutId");

            if (moveToTrash) {
                    // Move file shortcut to Recycle Bin
            }
            else {
                    _dlAppService.deleteFileShortcut(fileShortcutId);
            }
    }

Note that you don't have to support the Recycle Bin in your delete operations. 
If you want to, however, see the tutorial on 
[moving entities to the Recycle Bin](liferay.com). 
