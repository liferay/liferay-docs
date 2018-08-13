# Copying Folders

The Documents and Media API lets you copy folders within a repository. You 
can't, however, copy a folder between different repositories. 

To copy a folder, use the 
[`DLAppService`](@platform-ref@/7.1-latest/javadocs/portal-kernel/com/liferay/document/library/kernel/service/DLAppService.html) 
method `copyFolder`: 

    copyFolder(long repositoryId, long sourceFolderId, long parentFolderId, String name, 
            String description, ServiceContext serviceContext)

For a full description of the method and its parameters, see its 
[Javadoc](@platform-ref@/7.1-latest/javadocs/portal-kernel/com/liferay/document/library/kernel/service/DLAppService.html#copyFolder-long-long-long-java.lang.String-java.lang.String-com.liferay.portal.kernel.service.ServiceContext-). 

<!-- Add example --> 
