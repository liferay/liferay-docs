# Creating File Shortcuts

To create file shortcuts (`FileShortcut` entities) in the Documents and Media 
Library, you must use the 
[`DLAppService`](@platform-ref@/7.1-latest/javadocs/portal-kernel/com/liferay/document/library/kernel/service/DLAppService.html) 
interface's `addFileShortcut` method. Click this method to see a description of 
its parameters: 

    [addFileShortcut(long repositoryId, long folderId, long toFileEntryId, ServiceContext serviceContext)](@platform-ref@/7.1-latest/javadocs/portal-kernel/com/liferay/document/library/kernel/service/DLAppService.html#addFileShortcut-long-long-long-com.liferay.portal.kernel.service.ServiceContext-)

Note that all this method's parameters are mandatory. 

Keep in mind the following when creating shortcuts: 

-   You can create a shortcut to a file in a different site, provided that the 
    shortcut and file are in the same portal instance. 
-   You can't create folder shortcuts. 
-   Shortcuts can only exist in the default (site) repository. If you try to 
    invoke `addFileShortcut` with the repository ID of an external repository 
    (e.g., a SharePoint repository), the operation will fail. Since different 
    repositories support different features, @product@ only supports the common 
    denominators for all repositories: files and folders. 

<!-- Add example of addFileShortcut --> 
