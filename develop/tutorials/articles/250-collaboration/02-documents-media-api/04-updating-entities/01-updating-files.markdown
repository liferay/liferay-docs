# Updating Files

Updating a file is a bit more complicated than 
[creating one](liferay.com). 
This is because the update operation handles a file's metadata and content 
differently. If you only want to modify a file's content, you must also supply 
the file's existing metadata. Otherwise, the update operation could corrupt or 
lose the metadata. The opposite, however, isn't true. You can modify a file's 
metadata without re-supplying the content. When you perform such an update, the 
file's content is automatically copied to the new version of the file. To make 
this easier to remember, follow these rules when performing file update 
operations: 

-   Always provide all metadata values.
-   Only provide the file's content when you want to change it. 

There are three `updateFileEntry` methods you can use to update a file. These 
methods only differ in the type used for the file's new content. Click each 
method to see its Javadoc, which contains a full description of its parameters: 

-   [`updateFileEntry(..., byte[] bytes, ...)`](@platform-ref@/7.1-latest/javadocs/portal-kernel/com/liferay/document/library/kernel/service/DLAppService.html#updateFileEntry-long-java.lang.String-java.lang.String-java.lang.String-java.lang.String-java.lang.String-boolean-byte:A-com.liferay.portal.kernel.service.ServiceContext-)

-   [`updateFileEntry(..., File file, ...)`](@platform-ref@/7.1-latest/javadocs/portal-kernel/com/liferay/document/library/kernel/service/DLAppService.html#updateFileEntry-long-java.lang.String-java.lang.String-java.lang.String-java.lang.String-java.lang.String-boolean-java.io.File-com.liferay.portal.kernel.service.ServiceContext-)

-   [`updateFileEntry(..., InputStream is, long size, ...)`](@platform-ref@/7.1-latest/javadocs/portal-kernel/com/liferay/document/library/kernel/service/DLAppService.html#updateFileEntry-long-java.lang.String-java.lang.String-java.lang.String-java.lang.String-java.lang.String-boolean-java.io.InputStream-long-com.liferay.portal.kernel.service.ServiceContext-)

Note that the `title` and `description` parameters are optional, even though the 
original file may have a title and description. To retain those values, you must 
provide them to `updateFileEntry` via those parameters. 

