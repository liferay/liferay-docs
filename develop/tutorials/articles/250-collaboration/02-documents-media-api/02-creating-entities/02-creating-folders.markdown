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
`mountPoint` attribute to `true`: 

    serviceContext.setAttribute("mountPoint", true);

<!-- 
Add after clarification from Adolfo:

If you do this, the `repositoryId` property of the folder will no longer 
indicate to which repository the folder belongs, but to which external 
repository it is pointing to. One non-obvious implication of this is that only 
folders that belong to the default site repository may contain mount points; if 
you try to do this to a folder in a private (non default) repository, the folder 
will be lost forever. The reason for this is that the link between a folder and 
its repository is the `repositoryId` property of the folder; this is not a 
problem for the default site repository due to its special treatment. Use this 
feature with care. 
--> 
