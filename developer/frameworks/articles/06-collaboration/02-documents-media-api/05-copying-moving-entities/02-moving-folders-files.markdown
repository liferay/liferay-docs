---
header-id: moving-folders-and-files
---

# Moving Folders and Files

To move folders and files with the Documents and Media API, use the `moveFolder` 
and `moveFileEntry` methods discussed in 
[Copying and Moving Entities](/developer/frameworks/-/knowledge_base/7-2/copying-and-moving-entities). 
The steps here show you how. For general information on using the API, see 
[Documents and Media API](/developer/frameworks/-/knowledge_base/7-2/documents-and-media-api). 

Follow these steps to use `moveFolder` and `moveFileEntry` to move a folder and 
a file, respectively. This example does both to demonstrate the procedures: 

1.  Get a reference to `DLAppService`: 

        @Reference
        private DLAppService _dlAppService;

2.  Get the data needed to populate the method arguments. Since moving folders 
    and files is typically done in response to a user action, you can get the 
    data from the request. This example does so via 
    `javax.portlet.ActionRequest` and 
    [`ParamUtil`](@platform-ref@/7.2-latest/javadocs/portal-kernel/com/liferay/portal/kernel/util/ParamUtil.html), 
    but you can get the data any way you wish: 

        // Get the folder IDs
        long folderId = ParamUtil.getLong(actionRequest, "folderId");
        long newFolderId = ParamUtil.getLong(actionRequest, "newFolderId");

        // Get the file ID
        long fileEntryId = ParamUtil.getLong(actionRequest, "fileEntryId");

        ServiceContext serviceContext = ServiceContextFactory.getInstance(
                DLFileEntry.class.getName(), actionRequest);

    For more information on `ServiceContext`, see the tutorial 
    [Understanding ServiceContext](/developer/frameworks/-/knowledge_base/7-2/understanding-servicecontext). 

3.  Call the service reference's method(s). This example calls `moveFolder` to 
    move a folder (`folderId`) to a different folder (`newFolderId`). It then 
    calls `moveFileEntry` to move a file (`fileEntryId`) to the same destination 
    folder: 

        _dlAppService.moveFolder(folderId, newFolderId, serviceContext);

        _dlAppService.moveFileEntry(fileEntryId, newFolderId, serviceContext);

## Related Topics

[Copying Folders](/developer/frameworks/-/knowledge_base/7-2/copying-folders)
