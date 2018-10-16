# Moving Folders and Files [](id=moving-folders-and-files)

The move operation is much more flexible than the 
[copy operation](/develop/tutorials/-/knowledge_base/7-1/copying-folders). 
Copying only works with folders, and you can't copy them between repositories. 
The move operation, however, works with files and folders within or between 
repositories. 

+$$$

**Note:** Depending on the repository implementation, you may get unexpected 
behavior when moving folders between repositories. Moving a folder also moves 
its contents via separate move operations for each item in the folder. In some 
repository implementations, if any move sub-operation fails then the parent move 
operation also fails. In other repository implementations, the results of 
successful sub-operations remain even if others fail, which leaves a partially 
complete move of the whole folder. 

$$$

To move a folder, use the 
[`DLAppService`](@platform-ref@/7.1-latest/javadocs/portal-kernel/com/liferay/document/library/kernel/service/DLAppService.html) 
method `moveFolder`: 

    moveFolder(long folderId, long parentFolderId, ServiceContext serviceContext)

For a full description of this method and its parameters, see its 
[Javadoc](@platform-ref@/7.1-latest/javadocs/portal-kernel/com/liferay/document/library/kernel/service/DLAppService.html#moveFolder-long-long-com.liferay.portal.kernel.service.ServiceContext-). 
This method is similar to `copyFolder`, but it doesn't let you change the 
folder's name or description, and it can move folders between repositories. 
Also, the move operation is recursive---it also moves the folder's contents. 

The operation for moving files is almost identical. To move files, use the 
`DLAppService` method `moveFileEntry`: 

    moveFileEntry(long fileEntryId, long newFolderId, ServiceContext serviceContext)

For a full description of this method and its parameters, see its 
[Javadoc](@platform-ref@/7.1-latest/javadocs/portal-kernel/com/liferay/document/library/kernel/service/DLAppService.html#moveFileEntry-long-long-com.liferay.portal.kernel.service.ServiceContext-). 

Follow these steps to use `moveFolder` and `moveFileEntry` to move a folder and 
a file, respectively. Although this example does both, you can omit one 
operation if you don't need to perform it. 

1.  Get a reference to `DLAppService`: 

        @Reference
        private DLAppService _dlAppService;

    For more information on this, see the section on 
    [getting a service reference](/develop/tutorials/-/knowledge_base/7-1/getting-started-with-the-documents-and-media-api#getting-a-service-reference) 
    in the getting started tutorial. 

2.  Get the data you need to call the methods. Since moving folders and files is 
    typically done in response to a user action, you can get these data from the 
    request. This example does so via `javax.portlet.ActionRequest` and 
    [`ParamUtil`](@platform-ref@/7.1-latest/javadocs/portal-kernel/com/liferay/portal/kernel/util/ParamUtil.html), 
    but you can get these data any way you wish: 

        // Get the folder IDs
        long folderId = ParamUtil.getLong(actionRequest, "folderId");
        long newFolderId = ParamUtil.getLong(actionRequest, "newFolderId");

        // Get the file ID
        long fileEntryId = ParamUtil.getLong(actionRequest, "fileEntryId");

        ServiceContext serviceContext = ServiceContextFactory.getInstance(
                DLFileEntry.class.getName(), actionRequest);

3.  Call the service reference method(s). This example calls `moveFolder` to 
    move a folder (`folderId`) to a different folder (`newFolderId`). It then 
    calls `moveFileEntry` to move a file (`fileEntryId`) to the same destination 
    folder: 

        _dlAppService.moveFolder(folderId, newFolderId, serviceContext);

        _dlAppService.moveFileEntry(fileEntryId, newFolderId, serviceContext);

## Related Topics [](id=related-topics)

[Copying Folders](/develop/tutorials/-/knowledge_base/7-1/copying-folders)
