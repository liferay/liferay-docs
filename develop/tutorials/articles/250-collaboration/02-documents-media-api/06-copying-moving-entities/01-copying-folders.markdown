# Copying Folders [](id=copying-folders)

The Documents and Media API lets you copy folders within a repository. You 
can't, however, copy a folder between different repositories. Also note that 
copying a folder also copies its contents. 

To copy a folder, use the 
[`DLAppService`](@platform-ref@/7.1-latest/javadocs/portal-kernel/com/liferay/document/library/kernel/service/DLAppService.html) 
method `copyFolder`: 

    copyFolder(long repositoryId, long sourceFolderId, long parentFolderId, String name, 
            String description, ServiceContext serviceContext)

For a full description of the method and its parameters, see its 
[Javadoc](@platform-ref@/7.1-latest/javadocs/portal-kernel/com/liferay/document/library/kernel/service/DLAppService.html#copyFolder-long-long-long-java.lang.String-java.lang.String-com.liferay.portal.kernel.service.ServiceContext-). 

Follow these steps to use this method to copy a folder: 

1.  Get a reference to `DLAppService`: 

        @Reference
        private DLAppService _dlAppService;

    For more information on this, see the section on 
    [getting a service reference](/develop/tutorials/-/knowledge_base/7-1/getting-started-with-the-documents-and-media-api#getting-a-service-reference) 
    in the getting started tutorial. 

2.  Get the data needed to populate the `copyFolder` method's arguments. You can 
    do this any way you wish. The copy operation in this example takes place in 
    the default site repository and retains the folder's existing name and 
    description. It therefore needs the folder's group ID (to specify the 
    default site repository), name, and description. 

    After 
    [`ParamUtil`](@platform-ref@/7.1-latest/javadocs/portal-kernel/com/liferay/portal/kernel/util/ParamUtil.html) 
    gets the folder's ID from the request (`javax.portlet.ActionRequest`), the 
    service reference's 
    [`getFolder`](/7.1-latest/javadocs/portal-kernel/com/liferay/document/library/kernel/service/DLAppService.html#getFolder-long-) 
    method gets the corresponding folder object. The folder's `getGroupId()`, 
    `getName()`, and `getDescription()` methods then get the folder's group ID, 
    name, and description, respectively: 

        long folderId = ParamUtil.getLong(actionRequest, "folderId");

        Folder folder = _dlAppService.getFolder(folderId);
        long groupId = folder.getGroupId();
        String folderName = folder.getName();
        String folderDescription = folder.getDescription();

        ServiceContext serviceContext = ServiceContextFactory.getInstance(
                    DLFolder.class.getName(), actionRequest);

    For more information on getting repository and folder IDs, see the 
    [getting started tutorial's](/develop/tutorials/-/knowledge_base/7-1/getting-started-with-the-documents-and-media-api) 
    sections on specifying repositories and folders. For more information on 
    `ServiceContext` see the tutorial 
    [Understanding ServiceContext](/develop/tutorials/-/knowledge_base/7-1/understanding-servicecontext). 

3.  Call the service reference's `copyFolder` method with the data from the 
    previous step. Note that this example uses 
    `DLFolderConstants.DEFAULT_PARENT_FOLDER_ID` to specify the repository's 
    root folder as the destination folder: 

        _dlAppService.copyFolder(
                groupId, folderId, DLFolderConstants.DEFAULT_PARENT_FOLDER_ID, 
                folderName, folderDescription, serviceContext);

Keep in mind that you can change any of these values to suit your copy 
operation. For example, if your copy takes place in a repository other than the 
default site repository, you would specify that repository's ID in place of the 
group ID. You could also specify a different destination folder, and/or change 
the new folder's name and/or description. 

## Related Topics [](id=related-topics)

[Getting Started with the Documents and Media API](/develop/tutorials/-/knowledge_base/7-1/getting-started-with-the-documents-and-media-api)

[Understanding Service Context](/develop/tutorials/-/knowledge_base/7-1/understanding-servicecontext)

[Creating Folders](/develop/tutorials/-/knowledge_base/7-1/creating-folders)

[Updating Folders](/develop/tutorials/-/knowledge_base/7-1/updating-folders)

[Deleting Folders](/develop/tutorials/-/knowledge_base/7-1/deleting-folders)

[Moving Folders and Files](/develop/tutorials/-/knowledge_base/7-1/moving-folders-and-files)
