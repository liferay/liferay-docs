# Deleting Files [](id=deleting-files)

There are two methods you can use to delete files. Click each method to see its
Javadoc: 

-   [`deleteFileEntry(long fileEntryId)`](@platform-ref@/7.1-latest/javadocs/portal-kernel/com/liferay/document/library/kernel/service/DLAppService.html#deleteFileEntry-long-)

-   [`deleteFileEntryByTitle(long repositoryId, long folderId, String title)`](@platform-ref@/7.1-latest/javadocs/portal-kernel/com/liferay/document/library/kernel/service/DLAppService.html#deleteFileEntryByTitle-long-long-java.lang.String-)

These methods differ only in how they identify a file for deletion. The 
combination of the `folderId` and `title` parameters in `deleteFileEntryByTitle` 
uniquely identify a file because it's impossible for two files in the same 
folder to share a name. 

Follow these steps to delete a file: 

1.  Get a reference to `DLAppService`: 

        @Reference
        private DLAppService _dlAppService;

    For more information on this, see the section on 
    [getting a service reference](/develop/tutorials/-/knowledge_base/7-1/getting-started-with-the-documents-and-media-api#getting-a-service-reference) 
    in the getting started tutorial. 

2.  Get the data needed to populate the arguments of the `deleteFileEntry*` 
    method you wish to use. Since it's common to delete a file specified by the 
    end user, you can extract the data you need from the request. This example 
    does so via `javax.portlet.ActionRequest` and 
    [`ParamUtil`](@platform-ref@/7.1-latest/javadocs/portal-kernel/com/liferay/portal/kernel/util/ParamUtil.html), 
    but you can get the data any way you wish. Also note that this example gets 
    only the file entry ID because it uses `deleteFileEntry`: 

        long fileEntryId = ParamUtil.getLong(actionRequest, "fileEntryId");

    If you want to use `deleteFileEntryByTitle` instead, you can also get the 
    repository ID, folder ID, and title from the request. For more information 
    on getting repository and folder IDs, see the 
    [getting started tutorial's](/develop/tutorials/-/knowledge_base/7-1/getting-started-with-the-documents-and-media-api) 
    sections on specifying repositories and folders. 

3.  Call the service reference's `deleteFileEntry*` method you wish to use with 
    the data from the previous step. This example calls `deleteFileEntry` with 
    the file entry's ID: 

        _dlAppService.deleteFileEntry(fileEntryId);

You can find the full code for this example in the `deleteFileEntry` method of 
@product@'s 
[`EditFileEntryMVCActionCommand`](https://github.com/liferay/liferay-portal/blob/master/modules/apps/document-library/document-library-web/src/main/java/com/liferay/document/library/web/internal/portlet/action/EditFileEntryMVCActionCommand.java) 
class. This class uses the Documents and Media API to implement almost all the 
`FileEntry` actions that the Documents and Media app supports. Also note that 
this `deleteFileEntry` method, as well as the rest of 
`EditFileEntryMVCActionCommand`, contains additional logic to suit the specific 
needs of the Documents and Media app. 

## Related Topics [](id=related-topics)

[Moving Entities to the Recycle Bin](/develop/tutorials/-/knowledge_base/7-1/moving-entities-to-the-recycle-bin)

[Creating Files](/develop/tutorials/-/knowledge_base/7-1/creating-files)

[Updating Files](/develop/tutorials/-/knowledge_base/7-1/updating-files)

[Moving Folders and Files](/develop/tutorials/-/knowledge_base/7-1/moving-folders-and-files)
