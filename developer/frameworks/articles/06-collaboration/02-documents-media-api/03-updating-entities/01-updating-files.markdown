# Updating Files [](id=updating-files)

To update a file with the Documents and Media API, you must use one of the three 
`updateFileEntry` methods discussed in 
[Updating Entities](liferay.com). 
The steps here show you how. For general information on using the API, see 
[Documents and Media API](liferay.com). 

Note that the example in these steps uses the `updateFileEntry` method that 
contains `InputStream`, but you can adapt the example to the other methods if 
you wish: 

1.  Get a reference to `DLAppService`: 

        @Reference
        private DLAppService _dlAppService;

2.  Get the data needed to populate the `updateFileEntry` method's arguments. 
    Since it's common to update a file with data submitted by the end user, you 
    can extract the data from the request. This example does so via 
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
    [Understanding ServiceContext](/develop/tutorials/-/knowledge_base/7-2/understanding-servicecontext). 

3.  Call the service reference's `updateFileEntry` method with the data from the 
    previous step. Note that this example does so inside the previous step's 
    try-with-resources statement: 

        try (InputStream inputStream = uploadPortletRequest.getFileAsStream("file")) {

            ...

            FileEntry fileEntry = _dlAppService.updateFileEntry(
                                    fileEntryId, sourceFileName, contentType, title,
                                    description, changeLog, majorVersion, inputStream, size,
                                    serviceContext);
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

## Related Topics [](id=related-topics)

[Creating Files](liferay.com)

[Deleting Files](liferay.com)

[Moving Folders and Files](liferay.com)
