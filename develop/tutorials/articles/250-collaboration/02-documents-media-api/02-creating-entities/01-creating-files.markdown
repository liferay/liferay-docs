# Creating Files [](id=creating-files)

To create files (`FileEntry` entities) in the Documents and Media Library, you 
must use the 
[`DLAppService`](@platform-ref@/7.1-latest/javadocs/portal-kernel/com/liferay/document/library/kernel/service/DLAppService.html) 
interface's `addFileEntry` methods. There are three such methods, and they 
differ by the type used to create the file. Click each method to see a 
full description of the method and its parameters: 

-   [`addFileEntry(..., byte[] bytes, ...)`](@platform-ref@/7.1-latest/javadocs/portal-kernel/com/liferay/document/library/kernel/service/DLAppService.html#addFileEntry-long-long-java.lang.String-java.lang.String-java.lang.String-java.lang.String-java.lang.String-byte:A-com.liferay.portal.kernel.service.ServiceContext-) 

-   [`addFileEntry(..., File file, ...)`](@platform-ref@/7.1-latest/javadocs/portal-kernel/com/liferay/document/library/kernel/service/DLAppService.html#addFileEntry-long-long-java.lang.String-java.lang.String-java.lang.String-java.lang.String-java.lang.String-java.io.File-com.liferay.portal.kernel.service.ServiceContext-)

-   [`addFileEntry(..., InputStream is, long size, ...)`](@platform-ref@/7.1-latest/javadocs/portal-kernel/com/liferay/document/library/kernel/service/DLAppService.html#addFileEntry-long-long-java.lang.String-java.lang.String-java.lang.String-java.lang.String-java.lang.String-java.io.InputStream-long-com.liferay.portal.kernel.service.ServiceContext-)

Note that the following arguments are optional: 

-   `sourceFileName`: This is only used to keep track of the file being 
    uploaded, and also to infer the content type if that file name has an 
    extension. 
-   `mimeType`: Defaults to a binary stream. If not provided, Documents and 
    Media tries to infer the type from the file extension. 
-   `description`: This informative file description is only used for display. 
-   `changeLog`: Used for optional file versioning descriptions. 
-   `is` and `size`: In the method that takes an `InputStream`, you can use 
    `null` for the `is` parameter. If you do this, however, you must use `0` for 
    the `size` parameter. 

This tutorial uses the method that contains `InputStream`, but you can easily 
adapt the examples to the other methods if you wish. 

The following example comes from @product@'s 
[`EditFileEntryMVCActionCommand`](https://github.com/liferay/liferay-portal/blob/master/modules/apps/document-library/document-library-web/src/main/java/com/liferay/document/library/web/internal/portlet/action/EditFileEntryMVCActionCommand.java) 
class. This class implements almost all the `FileEntry` actions that the 
Documents and Media UI supports. This class's `updateFileEntry` method contains 
logic to add and update files. 

This method uses the request to get the data the `addFileEntry` method needs. It 
then calls the `addFileEntry` method with those data.

    protected FileEntry updateFileEntry(
            PortletConfig portletConfig, ActionRequest actionRequest, 
            ActionResponse actionResponse, UploadPortletRequest uploadPortletRequest)
        throws Exception {

        ...

        long repositoryId = ParamUtil.getLong(uploadPortletRequest, "repositoryId");
        long folderId = ParamUtil.getLong(uploadPortletRequest, "folderId");
        String sourceFileName = uploadPortletRequest.getFileName("file");
        String title = ParamUtil.getString(uploadPortletRequest, "title");
        String description = ParamUtil.getString(uploadPortletRequest, "description");
        String changeLog = ParamUtil.getString(uploadPortletRequest, "changeLog");
        boolean majorVersion = ParamUtil.getBoolean(uploadPortletRequest, "majorVersion");

        ...

        try (InputStream inputStream =
                uploadPortletRequest.getFileAsStream("file")) {

            String contentType = uploadPortletRequest.getContentType("file");
            long size = uploadPortletRequest.getSize("file");

            ...

            ServiceContext serviceContext = ServiceContextFactory.getInstance(
                    DLFileEntry.class.getName(), uploadPortletRequest);

            ...

                fileEntry = _dlAppService.addFileEntry(repositoryId, folderId, sourceFileName, 
                    contentType, title, description, changeLog, inputStream, size, serviceContext);

            ...

            return fileEntry;
        }

    }

## Related Topics

[Updating Files](/develop/tutorials/-/knowledge_base/7-1/updating-files)

[Deleting Files](/develop/tutorials/-/knowledge_base/7-1/deleting-files)

[Moving Folders and Files](/develop/tutorials/-/knowledge_base/7-1/moving-folders-and-files)

[Creating Folders](/develop/tutorials/-/knowledge_base/7-1/creating-folders)

[Creating File Shortcuts](/develop/tutorials/-/knowledge_base/7-1/creating-file-shortcuts)
