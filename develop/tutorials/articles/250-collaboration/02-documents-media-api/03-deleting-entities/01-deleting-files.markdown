# Deleting Files

Deleting files (`FileEntry` entities) via 
[`DLAppService`](@platform-ref@/7.1-latest/javadocs/portal-kernel/com/liferay/document/library/kernel/service/DLAppService.html) 
is straightforward. There are two methods you can use to delete files. Click 
each method to see its Javadoc: 

-   [`deleteFileEntry(long fileEntryId)`](@platform-ref@/7.1-latest/javadocs/portal-kernel/com/liferay/document/library/kernel/service/DLAppService.html#deleteFileEntry-long-)

-   [`deleteFileEntryByTitle(long repositoryId, long folderId, String title)`](@platform-ref@/7.1-latest/javadocs/portal-kernel/com/liferay/document/library/kernel/service/DLAppService.html#deleteFileEntryByTitle-long-long-java.lang.String-)

These methods differ only in how they identify a file for deletion. The 
combination of the `folderId` and `title` in `deleteFileEntryByTitle` uniquely 
identifies a file because it's impossible for two files in the same folder to 
have the same name. 
<!-- Pending answer from Adolfo on Recycle Bin behavior with these methods -->

<!-- Add examples -->
