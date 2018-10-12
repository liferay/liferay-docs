# Creating Files [](id=creating-files)

To create files (`FileEntry` entities) in the Documents and Media library, you 
must use the 
[`DLAppService`](@platform-ref@/7.1-latest/javadocs/portal-kernel/com/liferay/document/library/kernel/service/DLAppService.html) 
interface's `addFileEntry` methods. There are three such methods, and they 
differ by the data type used to create the file. Click each method to see a 
full description of the method and its parameters: 

-   [`addFileEntry(..., byte[] bytes, ...)`](@platform-ref@/7.1-latest/javadocs/portal-kernel/com/liferay/document/library/kernel/service/DLAppService.html#addFileEntry-long-long-java.lang.String-java.lang.String-java.lang.String-java.lang.String-java.lang.String-byte:A-com.liferay.portal.kernel.service.ServiceContext-) 

-   [`addFileEntry(..., File file, ...)`](@platform-ref@/7.1-latest/javadocs/portal-kernel/com/liferay/document/library/kernel/service/DLAppService.html#addFileEntry-long-long-java.lang.String-java.lang.String-java.lang.String-java.lang.String-java.lang.String-java.io.File-com.liferay.portal.kernel.service.ServiceContext-)

-   [`addFileEntry(..., InputStream is, long size, ...)`](@platform-ref@/7.1-latest/javadocs/portal-kernel/com/liferay/document/library/kernel/service/DLAppService.html#addFileEntry-long-long-java.lang.String-java.lang.String-java.lang.String-java.lang.String-java.lang.String-java.io.InputStream-long-com.liferay.portal.kernel.service.ServiceContext-)

Note that the following arguments are optional: 

-   `sourceFileName`: This is used to keep track of the file being uploaded and 
    infer the content type if that file has an extension. 
-   `mimeType`: Defaults to a binary stream. If omitted, Documents and Media 
    tries to infer the type from the file extension. 
-   `description`: The file's description to display in the portal. 
-   `changeLog`: Descriptions for file versions. 
-   `is` and `size`: In the method that takes an `InputStream`, you can use 
    `null` for the `is` parameter. If you do this, however, you must use `0` for 
    the `size` parameter. 

Follow these steps to create a file via the `DLAppService` method 
`addFileEntry`. Note that these steps use the method that contains 
`InputStream`, but you can adapt the example to the other methods if you wish: 

1.  Get a reference to `DLAppService`: 

        @Reference
        private DLAppService _dlAppService;

    For more information on this, see the section on 
    [getting a service reference](/develop/tutorials/-/knowledge_base/7-1/getting-started-with-the-documents-and-media-api#getting-a-service-reference) 
    in the getting started tutorial. 

2.  Get the data for the `addFileEntry` method's arguments. Obviously, if you 
    want to call the method then you must populate its arguments. Since it's 
    common to create a file with data submitted by the end user, you can extract 
    those data from the request. This example does so via 
    [`UploadPortletRequest`](@platform-ref@/7.1-latest/javadocs/portal-kernel/com/liferay/portal/kernel/upload/UploadPortletRequest.html) 
    and 
    [`ParamUtil`](@platform-ref@/7.1-latest/javadocs/portal-kernel/com/liferay/portal/kernel/util/ParamUtil.html), 
    but you can get these data any way you wish: 

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

    For more information on getting repository and folder IDs, see the
    [getting started tutorial's](/develop/tutorials/-/knowledge_base/7-1/getting-started-with-the-documents-and-media-api) 
    sections on specifying repositories and folders. For more information on 
    `ServiceContext` see the tutorial 
    [Understanding ServiceContext](/develop/tutorials/-/knowledge_base/7-1/understanding-servicecontext). 

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

## Related Topics [](id=related-topics)

[Updating Files](/develop/tutorials/-/knowledge_base/7-1/updating-files)

[Deleting Files](/develop/tutorials/-/knowledge_base/7-1/deleting-files)

[Moving Folders and Files](/develop/tutorials/-/knowledge_base/7-1/moving-folders-and-files)

[Creating Folders](/develop/tutorials/-/knowledge_base/7-1/creating-folders)

[Creating File Shortcuts](/develop/tutorials/-/knowledge_base/7-1/creating-file-shortcuts)
