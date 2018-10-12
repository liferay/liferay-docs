# Deleting File Versions [](id=deleting-file-versions)

When a file is modified, Documents and Media creates a new file version and 
leaves the previous version intact. Over time, old file versions can accumulate 
and consume precious storage space. Fortunately, you can use the Documents and 
Media API to delete them. Note, however, that there's no way to send file 
versions to the Recycle Bin---once you delete them, they're gone forever. 

You can delete file versions with the 
[`DLAppService`](@platform-ref@/7.1-latest/javadocs/portal-kernel/com/liferay/document/library/kernel/service/DLAppService.html) 
method `deleteFileVersion`: 

    deleteFileVersion(long fileEntryId, String version)

See 
[this method's Javadoc](@platform-ref@/7.1-latest/javadocs/portal-kernel/com/liferay/document/library/kernel/service/DLAppService.html#deleteFileVersion-long-java.lang.String-) 
for a description of the parameters. 

Follow these steps to use `deleteFileVersion` to delete a file version:

1.  Get a reference to `DLAppService`: 

        @Reference
        private DLAppService _dlAppService;

    For more information on this, see the section on 
    [getting a service reference](/develop/tutorials/-/knowledge_base/7-1/getting-started-with-the-documents-and-media-api#getting-a-service-reference) 
    in the getting started tutorial. 

2.  Get the file entry ID and version for the file you want to delete. Since 
    it's common to delete a file specified by the end user, you can extract 
    those data from the request. This example does so via 
    `javax.portlet.ActionRequest` and 
    [`ParamUtil`](@platform-ref@/7.1-latest/javadocs/portal-kernel/com/liferay/portal/kernel/util/ParamUtil.html), 
    but you can do this any way you wish: 

        long fileEntryId = ParamUtil.getLong(actionRequest, "fileEntryId");
        String version = ParamUtil.getString(actionRequest, "version");

3.  Use the service reference to call the `deleteFileVersion` method with the 
    file entry ID and version from the previous step: 

        _dlAppService.deleteFileVersion(fileEntryId, version);

You can find the full code for this example in the `deleteFileEntry` method of 
@product@'s 
[`EditFileEntryMVCActionCommand`](https://github.com/liferay/liferay-portal/blob/master/modules/apps/document-library/document-library-web/src/main/java/com/liferay/document/library/web/internal/portlet/action/EditFileEntryMVCActionCommand.java) 
class. This class uses the Documents and Media API to implement almost all the 
`FileEntry` actions that the Documents and Media app supports. Also note that 
this `deleteFileEntry` method, as well as the rest of 
`EditFileEntryMVCActionCommand`, contains additional logic to suit the specific 
needs of the Documents and Media app. 

## Identifying File Versions [](id=identifying-file-versions)

Since there may be many versions of a file, it's useful to programmatically 
identify old versions for deletion. You can do this with 
[`FileVersionVersionComparator`](@platform-ref@/7.1-latest/javadocs/portal-kernel/com/liferay/document/library/kernel/util/comparator/FileVersionVersionComparator.html). 

The following example creates such a comparator and uses its `compare` method to 
identify old versions of a file. The code does so by iterating through each 
[approved](/discover/portal/-/knowledge_base/7-1/workflow) 
version of the file (`fileVersion`). Each iteration uses the `compare` method to 
test that file version (`fileVersion.getVersion()`) against the same file's 
current version (`fileEntry.getVersion()`). If this comparison is greater than 
`0`, then the iteration's file version (`fileVersion`) is old and is deleted by 
`deleteFileVersion`: 

    FileVersionVersionComparator comparator = new FileVersionVersionComparator();

    for (FileVersion fileVersion: fileEntry.getVersions(WorkflowConstants.STATUS_APPROVED)) {

        if (comparator.compare(fileEntry.getVersion(), fileVersion.getVersion()) > 0) {

            dlAppService.deleteFileVersion(fileVersion.getFileEntryId(), fileVersion.getVersion());
        }
    }

## Related Topics [](id=related-topics)

[Deleting Files](/develop/tutorials/-/knowledge_base/7-1/deleting-files)

[Deleting File Shortcuts](/develop/tutorials/-/knowledge_base/7-1/deleting-file-shortcuts)

[Deleting Folders](/develop/tutorials/-/knowledge_base/7-1/deleting-folders)

[Moving Entities to the Recycle Bin](/develop/tutorials/-/knowledge_base/7-1/moving-entities-to-the-recycle-bin)
