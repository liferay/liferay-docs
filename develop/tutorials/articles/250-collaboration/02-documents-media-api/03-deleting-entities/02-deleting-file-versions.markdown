# Deleting File Versions [](id=deleting-file-versions)

When a file is modified, @product@ creates a new file version and leaves the 
previous version intact. Over time, old versions of files can accumulate and 
consume precious storage space. Fortunately, you can use the Documents and Media 
API to delete file versions. Note, however, that there's no way to send file 
versions to the Recycle Bin---once you delete them, they're gone forever. 

You can delete file versions with the 
[`DLAppService`](@platform-ref@/7.1-latest/javadocs/portal-kernel/com/liferay/document/library/kernel/service/DLAppService.html) 
method `deleteFileVersion`: 

    deleteFileVersion(long fileEntryId, String version)

See 
[this method's Javadoc](@platform-ref@/7.1-latest/javadocs/portal-kernel/com/liferay/document/library/kernel/service/DLAppService.html#deleteFileVersion-long-java.lang.String-) 
for a description of the parameters. 

The following example comes from @product@'s 
[`EditFileEntryMVCActionCommand`](https://github.com/liferay/liferay-portal/blob/master/modules/apps/document-library/document-library-web/src/main/java/com/liferay/document/library/web/internal/portlet/action/EditFileEntryMVCActionCommand.java) 
class. This class implements almost all the `FileEntry` actions that the 
Documents and Media UI supports. It contains its own `deleteFileEntry` method, 
which calls the `deleteFileVersion` method. 

This method gets the file's ID and version from the request. After 
[validating](@platform-ref@/7.1-latest/javadocs/portal-kernel/com/liferay/portal/kernel/util/Validator.html) 
the version, it calls `deleteFileVersion` to delete that version: 

    protected void deleteFileEntry(
                    ActionRequest actionRequest, boolean moveToTrash)
            throws Exception {

            long fileEntryId = ParamUtil.getLong(actionRequest, "fileEntryId");

            if (fileEntryId == 0) {
                return;
            }

            String version = ParamUtil.getString(actionRequest, "version");

            if (Validator.isNotNull(version)) {
                    _dlAppService.deleteFileVersion(fileEntryId, version);

                    return;
            }

            ...
    }

Note that this is the same `deleteFileEntry` method from the example in the 
[tutorial on deleting files](/develop/tutorials/-/knowledge_base/7-1/deleting-files). 
This method contains logic for deleting both files and file versions, depending 
on what's in the request. 

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

## Related Topics

[Deleting Files](/develop/tutorials/-/knowledge_base/7-1/deleting-files)

[Deleting File Shortcuts](/develop/tutorials/-/knowledge_base/7-1/deleting-file-shortcuts)

[Deleting Folders](/develop/tutorials/-/knowledge_base/7-1/deleting-folders)

[Moving Entities to the Recycle Bin](/develop/tutorials/-/knowledge_base/7-1/moving-entities-to-the-recycle-bin)
