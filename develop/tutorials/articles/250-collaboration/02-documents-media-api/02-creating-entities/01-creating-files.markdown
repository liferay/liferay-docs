# Creating Files

To create files (`FileEntry` entities) in the Documents and Media Library, you 
must use the 
[`DLAppService`](@platform-ref@/7.1-latest/javadocs/portal-kernel/com/liferay/document/library/kernel/service/DLAppService.html) 
interface's `addFileEntry` methods. There are three such methods, and they 
differ by the type used to create the file. Click each method's link to see a 
full description of the method and its parameters: 

-   [`FileEntry addFileEntry(..., byte[] bytes, ...)`](@platform-ref@/7.1-latest/javadocs/portal-kernel/com/liferay/document/library/kernel/service/DLAppService.html#addFileEntry-long-long-java.lang.String-java.lang.String-java.lang.String-java.lang.String-java.lang.String-byte:A-com.liferay.portal.kernel.service.ServiceContext-) 

-   [`FileEntry addFileEntry(..., File file, ...)`](@platform-ref@/7.1-latest/javadocs/portal-kernel/com/liferay/document/library/kernel/service/DLAppService.html#addFileEntry-long-long-java.lang.String-java.lang.String-java.lang.String-java.lang.String-java.lang.String-java.io.File-com.liferay.portal.kernel.service.ServiceContext-)

-   [`FileEntry addFileEntry(..., InputStream is, long size, ...)`](@platform-ref@/7.1-latest/javadocs/portal-kernel/com/liferay/document/library/kernel/service/DLAppService.html#addFileEntry-long-long-java.lang.String-java.lang.String-java.lang.String-java.lang.String-java.lang.String-java.io.InputStream-long-com.liferay.portal.kernel.service.ServiceContext-)

This tutorial uses the method that contains `InputStream`, but you can easily 
adapt the examples to the other methods if you wish. Note that the following 
arguments are optional: 

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


