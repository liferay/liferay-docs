# Creating Folders

To create folders (`Folder` entities) in the Documents and Media Library, you 
must use the 
[`DLAppService`](@platform-ref@/7.1-latest/javadocs/portal-kernel/com/liferay/document/library/kernel/service/DLAppService.html) 
interface's `addFolder` method: 

    addFolder(long repositoryId, 
            long parentFolderId, 
            String name, 
            String description, 
            ServiceContext serviceContext)

See 
[this method's Javadoc](@platform-ref@/7.1-latest/javadocs/portal-kernel/com/liferay/document/library/kernel/service/DLAppService.html#addFolder-long-long-java.lang.String-java.lang.String-com.liferay.portal.kernel.service.ServiceContext-) 
for a description of the parameters. Note that the `description` parameter is 
optional. 
<!-- Add example -->

## Folders and External Repositories

A site's default repository can include references to external repositories such 
as SharePoint. This occurs by effectively mounting the external repository in 
the default one. The mount process involves creating a folder that acts as a 
proxy for the remote repository. When users enter this special folder, they're 
directed to the remote repository. These folders are called *mount points*. You 
can create one via the API by setting the 
[Service Context's](/develop/tutorials/-/knowledge_base/7-1/understanding-servicecontext) 
`mountPoint` attribute to `true`, and then using that Service Context in the 
`addFolder` method: 

    serviceContext.setAttribute("mountPoint", true);

Note that the `repositoryId` of such a folder indicates the external repository 
the folder points to---not the repository that the folder exists in. Also, mount 
point folders can only exist in the default site repository. 
