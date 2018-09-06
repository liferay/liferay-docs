# Moving Folders and Files [](id=moving-folders-and-files)

The move operation is much more flexible than the 
[copy operation](/develop/tutorials/-/knowledge_base/7-1/copying-folders). 
Copying only works with folders, and you can't copy them between repositories. 
The move operation, however, works with files and folders within or between 
repositories. 

+$$$

**Note:** Depending on the repository implementation, you may get unexpected 
behavior when moving folders between different repositories. Moving a folder 
also moves its contents. Behind the scenes, this move operation consists of many 
smaller such move operations for each item in the folder. In some repository 
implementations, if any one of these sub-operations fail, then the parent move 
operation also fails. In other repository implementations, the results of any 
successful sub-operations remain even if others fail, which leaves you with a 
partially complete move of the whole folder. 

$$$

To move a folder, use the 
[`DLAppService`](@platform-ref@/7.1-latest/javadocs/portal-kernel/com/liferay/document/library/kernel/service/DLAppService.html) 
method `moveFolder`: 

    moveFolder(long folderId, long parentFolderId, ServiceContext serviceContext)

For a full description of this method and its parameters, see its 
[Javadoc](@platform-ref@/7.1-latest/javadocs/portal-kernel/com/liferay/document/library/kernel/service/DLAppService.html#moveFolder-long-long-com.liferay.portal.kernel.service.ServiceContext-). 
This method is similar to `copyFolder`, except it doesn't let you change the 
folder's name or description, and it can move folders between repositories. 
Also, the move operation is recursive---the folder's contents are moved as well. 

The operation for moving files is almost identical. To do this, use the 
`DLAppService` method `moveFileEntry`: 

    moveFileEntry(long fileEntryId, long newFolderId, ServiceContext serviceContext)

For a full description of this method and its parameters, see its 
[Javadoc](@platform-ref@/7.1-latest/javadocs/portal-kernel/com/liferay/document/library/kernel/service/DLAppService.html#moveFileEntry-long-long-com.liferay.portal.kernel.service.ServiceContext-). 

The Documents and Media Library's 
[`EditEntryMVCActionCommand`](https://github.com/liferay/liferay-portal/blob/master/modules/apps/document-library/document-library-web/src/main/java/com/liferay/document/library/web/internal/portlet/action/EditEntryMVCActionCommand.java) 
class contains an example of both these methods. This class's `moveEntries` 
method iterates over the IDs of several existing folders (`folderIds`), calling 
`moveFolder` to move each folder to a different folder (`newFolderId`). The same 
approach is then used with `moveFileEntry` to move several files 
(`fileEntryIds`) to the same folder: 

    protected void moveEntries(ActionRequest actionRequest) throws Exception {

            long newFolderId = ParamUtil.getLong(actionRequest, "newFolderId");

            ServiceContext serviceContext = ServiceContextFactory.getInstance(
                    DLFileEntry.class.getName(), actionRequest);

            long[] folderIds = ParamUtil.getLongValues(
                    actionRequest, "rowIdsFolder");

            // Move the folders
            for (long folderId : folderIds) {
                    _dlAppService.moveFolder(folderId, newFolderId, serviceContext);
            }

            long[] fileEntryIds = ParamUtil.getLongValues(
                    actionRequest, "rowIdsFileEntry");

            // Move the files
            for (long fileEntryId : fileEntryIds) {
                    _dlAppService.moveFileEntry(fileEntryId, newFolderId, serviceContext);
            }

            ...
    }

## Related Topics [](id=related-topics)

[Copying Folders](/develop/tutorials/-/knowledge_base/7-1/copying-folders)
