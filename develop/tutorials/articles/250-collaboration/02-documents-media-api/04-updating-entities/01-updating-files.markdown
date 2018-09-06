# Updating Files [](id=updating-files)

Updating a file is a bit more complicated than 
[creating one](/develop/tutorials/-/knowledge_base/7-1/creating-files). 
This is because of the way the update operation handles a file's metadata and 
content. If you only want to update a file's content, you must also supply the 
file's existing metadata. Otherwise, the update operation could corrupt or lose 
the metadata. The opposite, however, isn't true. You can modify a file's 
metadata without re-supplying the content. When you perform such an update, the 
file's content is automatically copied to the new version of the file. To make 
this easier to remember, follow these rules when performing file update 
operations: 

-   Always provide all metadata values. 
-   Only provide the file's content when you want to change it. 

[`DLAppService`](@platform-ref@/7.1-latest/javadocs/portal-kernel/com/liferay/document/library/kernel/service/DLAppService.html) 
has three `updateFileEntry` methods that you can use to update a file. These 
methods differ only in the type used for the file's content. Click each method 
to see its Javadoc, which contains a full description of its parameters: 

-   [`updateFileEntry(..., byte[] bytes, ...)`](@platform-ref@/7.1-latest/javadocs/portal-kernel/com/liferay/document/library/kernel/service/DLAppService.html#updateFileEntry-long-java.lang.String-java.lang.String-java.lang.String-java.lang.String-java.lang.String-boolean-byte:A-com.liferay.portal.kernel.service.ServiceContext-)

-   [`updateFileEntry(..., File file, ...)`](@platform-ref@/7.1-latest/javadocs/portal-kernel/com/liferay/document/library/kernel/service/DLAppService.html#updateFileEntry-long-java.lang.String-java.lang.String-java.lang.String-java.lang.String-java.lang.String-boolean-java.io.File-com.liferay.portal.kernel.service.ServiceContext-)

-   [`updateFileEntry(..., InputStream is, long size, ...)`](@platform-ref@/7.1-latest/javadocs/portal-kernel/com/liferay/document/library/kernel/service/DLAppService.html#updateFileEntry-long-java.lang.String-java.lang.String-java.lang.String-java.lang.String-java.lang.String-boolean-java.io.InputStream-long-com.liferay.portal.kernel.service.ServiceContext-)

Keep in mind the following when using these methods: 

-   The `title` and `description` parameters are optional, even though the 
    original file may have a title and description. To retain those values, you 
    must provide them to `updateFileEntry` via those parameters. 

-   If you supply `null` in place of the file's content (e.g., `bytes`, `file`, 
    or `is`), then the update automatically uses the file's existing content. 
    This is what you should do if you only want to update the file's metadata. 

-   If you use `false` for the `majorVersion` parameter, the update increments 
    the file version by `0.1` (e.g., from `1.0` to `1.1`). If you use `true` for 
    this parameter, the update increments the file version to the next `.0` 
    value (e.g., from `1.0` to `2.0`, `1.1` to `2.0`, etc.). 

The following example comes from @product@'s 
[`EditFileEntryMVCActionCommand`](https://github.com/liferay/liferay-portal/blob/master/modules/apps/document-library/document-library-web/src/main/java/com/liferay/document/library/web/internal/portlet/action/EditFileEntryMVCActionCommand.java) 
class. This class implements almost all the `FileEntry` actions that the 
Documents and Media UI supports. This class defines its own `updateFileEntry` 
method that contains the logic to add and update files. Note that this is the 
same method from the example in the 
[tutorial on creating files](/develop/tutorials/-/knowledge_base/7-1/creating-files). 
The example here, however, focuses on the code that updates the file. 

For the method to add or update a file, it first gets the data needed for those 
operations from the request. To update a file, it then calls the `DLAppService` 
method `updateFileEntry` with those data. Note that this example calls the 
`updateFileEntry` method that takes an `InputStream`: 

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

                fileEntry = _dlAppService.updateFileEntry(
                        fileEntryId, sourceFileName, contentType, title,
                        description, changeLog, majorVersion, inputStream, size,
                        serviceContext);

            ...

            return fileEntry;
        }

    }

## Related Topics [](id=related-topics)

[Creating Files](/develop/tutorials/-/knowledge_base/7-1/creating-files)

[Deleting Files](/develop/tutorials/-/knowledge_base/7-1/deleting-files)

[Moving Folders and Files](/develop/tutorials/-/knowledge_base/7-1/moving-folders-and-files)
