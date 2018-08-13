# Moving Folders and Files

The move operation is much more flexible than the 
[copy operation](liferay.com). 
Copying only works with folders, and you can't copy them between repositories. 
The move operation, however, works with files and folders within or between 
repositories. 

To move a folder, use the 
[`DLAppService`](@platform-ref@/7.1-latest/javadocs/portal-kernel/com/liferay/document/library/kernel/service/DLAppService.html) 
method `moveFolder`: 

    moveFolder(long folderId, long parentFolderId, ServiceContext serviceContext)

For a full description of the method and its parameters, see its 
[Javadoc](@platform-ref@/7.1-latest/javadocs/portal-kernel/com/liferay/document/library/kernel/service/DLAppService.html#moveFolder-long-long-com.liferay.portal.kernel.service.ServiceContext-). 
This method is similar to `copyFolder`, except it doesn't let you change the 
folder's name or description, and can move folders between repositories. Also, 
the move operation is recursive---the folder's contents are moved as well. Note 
that when moving between repositories, the operation may be transactional 
depending on the repository implementation. 

