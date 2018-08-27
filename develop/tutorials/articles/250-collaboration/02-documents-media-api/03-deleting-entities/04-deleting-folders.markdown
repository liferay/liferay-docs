# Deleting Folders [](id=deleting-folders)

Deleting folders is similar to 
[deleting files](liferay.com). 
There are two methods you can use to delete a folder. Click each method to see 
its Javadoc: 

-   [`deleteFolder(long folderId)`](@platform-ref@/7.1-latest/javadocs/portal-kernel/com/liferay/document/library/kernel/service/DLAppService.html#deleteFolder-long-) 

-   [`deleteFolder(long repositoryId, long parentFolderId, String name)`](@platform-ref@/7.1-latest/javadocs/portal-kernel/com/liferay/document/library/kernel/service/DLAppService.html#deleteFolder-long-long-java.lang.String-) 

Which method you use is up to you---they both delete a folder. 

The following example comes from @product@'s 
[`EditFolderMVCActionCommand`](https://github.com/liferay/liferay-portal/blob/master/modules/apps/document-library/document-library-web/src/main/java/com/liferay/document/library/web/internal/portlet/action/EditFolderMVCActionCommand.java) 
class. This class implements almost all the `Folder` actions that the Documents 
and Media UI supports. This class's `deleteFolders` method contains logic to 
delete one or more folders. 

This method first gets one or more folder IDs from the request. It then loops 
through each, moving the folder to the 
[Recycle Bin](/discover/portal/-/knowledge_base/7-1/restoring-deleted-assets) 
if the `moveToTrash` flag is `true`, or deleting the folder via the 
`DLAppService` method `deleteFolder(long folderId)` if the flag is `false`: 

    protected void deleteFolders(
                    ActionRequest actionRequest, boolean moveToTrash)
            throws Exception {

            long[] deleteFolderIds = null;

            long folderId = ParamUtil.getLong(actionRequest, "folderId");

            if (folderId > 0) {
                    deleteFolderIds = new long[] {folderId};
            }
            else {
                    deleteFolderIds = ParamUtil.getLongValues(
                            actionRequest, "rowIdsFolder");
            }

            ...

            for (long deleteFolderId : deleteFolderIds) {
                    if (moveToTrash) {
                            // Move the folder to the Recycle Bin
                    }
                    else {
                            _dlAppService.deleteFolder(deleteFolderId);
                    }
            }

            ...
    }

Note that you don't have to support the Recycle Bin in your delete operations. 
If you want to, however, see the tutorial on 
[moving entities to the Recycle Bin](liferay.com). 
