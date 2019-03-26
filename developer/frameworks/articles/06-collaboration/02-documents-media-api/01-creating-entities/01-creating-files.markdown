---
header-id: creating-files
---

# Creating Files

[TOC levels=1-4]

To create a file via the Documents and Media API, use one of the overloaded 
`addFileEntry` methods in 
[`DLAppService`](@platform-ref@/7.2-latest/javadocs/portal-kernel/com/liferay/document/library/kernel/service/DLAppService.html). 
The steps here show you how to do this, using the method that contains 
`InputStream` as an example. For detailed information on this and other 
`addFileEntry` methods, see 
[Creating Files, Folders, and Shortcuts](/developer/frameworks/-/knowledge_base/7-2/creating-files-folders-and-shortcuts). 
For general information on using the API, see 
[Documents and Media API](/developer/frameworks/-/knowledge_base/7-2/documents-and-media-api). 

Follow these steps to create a file via the Documents and Media API: 

1.  Get a reference to `DLAppService`: 

        @Reference
        private DLAppService _dlAppService;

2.  Get the data needed to populate the `addFileEntry` method's arguments. Since 
    it's common to create a file with data submitted by the end user, you can 
    extract the data from the request. This example does so via 
    [`UploadPortletRequest`](@platform-ref@/7.2-latest/javadocs/portal-kernel/com/liferay/portal/kernel/upload/UploadPortletRequest.html) 
    and 
    [`ParamUtil`](@platform-ref@/7.2-latest/javadocs/portal-kernel/com/liferay/portal/kernel/util/ParamUtil.html), 
    but you can get the data any way you wish: 

        long repositoryId = ParamUtil.getLong(uploadPortletRequest, "repositoryId");
        long folderId = ParamUtil.getLong(uploadPortletRequest, "folderId");
        String sourceFileName = uploadPortletRequest.getFileName("file");
        String title = ParamUtil.getString(uploadPortletRequest, "title");
        String description = ParamUtil.getString(uploadPortletRequest, "description");
        String changeLog = ParamUtil.getString(uploadPortletRequest, "changeLog");
        boolean majorVersion = ParamUtil.getBoolean(uploadPortletRequest, "majorVersion");

        try (InputStream inputStream = uploadPortletRequest.getFileAsStream("file")) {

            String contentType = uploadPortletRequest.getContentType("file");
            long size = uploadPortletRequest.getSize("file");

            ServiceContext serviceContext = ServiceContextFactory.getInstance(
                    DLFileEntry.class.getName(), uploadPortletRequest);
        }

    For more information on `ServiceContext`, see 
    [Understanding ServiceContext](/developer/frameworks/-/knowledge_base/7-2/understanding-servicecontext). 

3.  Call the service reference's `addFileEntry` method with the data from the 
    previous step. Note that this example does so inside the previous step's 
    try-with-resources statement: 

        try (InputStream inputStream = uploadPortletRequest.getFileAsStream("file")) {

            ...

            FileEntry fileEntry = _dlAppService.addFileEntry(
                                repositoryId, folderId, sourceFileName, contentType, title, 
                                description, changeLog, inputStream, size, serviceContext);
        }

    The method returns a `FileEntry` object, which this example sets to a 
    variable for later use. Note, however, that you don't have to do this. 

You can find the full code for this example in the `updateFileEntry` method of 
@product@'s 
[`EditFileEntryMVCActionCommand`](https://github.com/liferay/liferay-portal/blob/master/modules/apps/document-library/document-library-web/src/main/java/com/liferay/document/library/web/internal/portlet/action/EditFileEntryMVCActionCommand.java) 
class. This class uses the Documents and Media API to implement almost all the 
`FileEntry` actions that the Documents and Media app supports. Also note that 
this `updateFileEntry` method, as well as the rest of 
`EditFileEntryMVCActionCommand`, contains additional logic to suit the specific 
needs of the Documents and Media app. 

## Related Topics

[Updating Files](/developer/frameworks/-/knowledge_base/7-2/updating-files)

[Deleting Files](/developer/frameworks/-/knowledge_base/7-2/deleting-files)

[Moving Folders and Files](/developer/frameworks/-/knowledge_base/7-2/moving-folders-and-files)

[Creating Folders](/developer/frameworks/-/knowledge_base/7-2/creating-folders)

[Creating File Shortcuts](/developer/frameworks/-/knowledge_base/7-2/creating-file-shortcuts)
