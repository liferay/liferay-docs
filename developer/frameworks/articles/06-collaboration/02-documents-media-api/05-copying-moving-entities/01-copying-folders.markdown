---
header-id: copying-folders
---

# Copying Folders

To copy a folder with the Documents and Media API, use the `copyFolder` method 
discussed in 
[Copying and Moving Entities](liferay.com). 
The steps here show you how. For general information on using the API, see 
[Documents and Media API](liferay.com). 

Follow these steps to use `copyFolder` to copy a folder: 

1.  Get a reference to `DLAppService`: 

        @Reference
        private DLAppService _dlAppService;

2.  Get the data needed to populate the `copyFolder` method's arguments. How you 
    do this depends on your use case. The copy operation in this example takes 
    place in the default Site repository and retains the folder's existing name 
    and description. It therefore needs the folder's group ID (to specify the 
    default site repository), name, and description. Also note that because the 
    destination folder in this example is the repository's root folder, the 
    parent folder ID isn't needed---@product@ supplies a constant for specifying 
    a repository's root folder. 

    In the following code, 
    [`ParamUtil`](@platform-ref@/7.2-latest/javadocs/portal-kernel/com/liferay/portal/kernel/util/ParamUtil.html) 
    gets the folder's ID from the request (`javax.portlet.ActionRequest`), and 
    the service reference's 
    [`getFolder`](@platform-ref@/7.2-latest/javadocs/portal-kernel/com/liferay/document/library/kernel/service/DLAppService.html#getFolder-long-) 
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

    For more information on `ServiceContext`, see the tutorial 
    [Understanding ServiceContext](/develop/tutorials/-/knowledge_base/7-2/understanding-servicecontext). 

3.  Call the service reference's `copyFolder` method with the data from the 
    previous step. Note that this example uses the 
    [`DLFolderConstants`](@platform-ref@/7.2-latest/javadocs/portal-kernel/com/liferay/document/library/kernel/model/DLFolderConstants.html) 
    constant `DEFAULT_PARENT_FOLDER_ID` to specify the repository's root folder 
    as the destination folder: 

        _dlAppService.copyFolder(
                groupId, folderId, DLFolderConstants.DEFAULT_PARENT_FOLDER_ID, 
                folderName, folderDescription, serviceContext);

Note that you can change any of these values to suit your copy operation. For 
example, if your copy takes place in a repository other than the default Site 
repository, you would specify that repository's ID in place of the group ID. You 
could also specify a different destination folder, and/or change the new 
folder's name and/or description. 

## Related Topics

[Getting Started with the Documents and Media API](liferay.com)

[Understanding Service Context](liferay.com)

[Creating Folders](liferay.com)

[Updating Folders](liferay.com)

[Deleting Folders](liferay.com)

[Moving Folders and Files](liferay.com)
