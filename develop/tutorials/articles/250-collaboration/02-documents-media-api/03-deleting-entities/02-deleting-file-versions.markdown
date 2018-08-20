# Deleting File Versions

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

<!-- Add example -->

Note that it's best to get the file version for the `version` parameter from a 
valid `FileEntry` object. This ensures that the version is valid. You can do 
this with 
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

