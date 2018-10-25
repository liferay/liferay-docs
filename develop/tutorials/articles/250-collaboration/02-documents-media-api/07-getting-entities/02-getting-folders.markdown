# Getting Folders [](id=getting-folders)

The Documents and Media API can also get folders. This is very similar to 
getting files. The main difference is that folder retrieval methods may have an 
additional argument to tell the system whether to include *mount folders*. Mount 
folders are mount points for external repositories (e.g. Alfresco or 
SharePoint) that appear as regular folders in a site's default repository. They 
let users navigate seamlessly between repositories. To account for this, some 
folder retrieval methods include the boolean parameter `includeMountFolders`. 
Setting this parameter to `true` includes mount folders in the results, while 
omitting it or setting it to `false` excludes them. 

For example, to get a list of a parent folder's subfolders from a repository, 
including any mount folders, use this 
[`getFolders`](@platform-ref@/7.1-latest/javadocs/portal-kernel/com/liferay/document/library/kernel/service/DLAppService.html#getFolders-long-long-boolean-) 
method: 

    getFolders(long repositoryId, long parentFolderId, boolean includeMountFolders)

Follow these steps to use this method to get the folders from a parent folder. 
Note that the example in these steps gets the folders of the default Site 
repository's root folder: 

1.  Get a reference to `DLAppService`: 

        @Reference
        private DLAppService _dlAppService;

    For more information on this, see the section on 
    [getting a service reference](/develop/tutorials/-/knowledge_base/7-1/getting-started-with-the-documents-and-media-api#getting-a-service-reference) 
    in the getting started tutorial. 

2.  Get the data needed to populate the method's arguments. You can get these 
    data any way you wish. This `getFolders` method needs a repository ID, a 
    parent folder ID, and a boolean value that indicates whether to include 
    mount folders in the results. To specify the default site repository, you 
    can use the group ID as the repository ID. This example gets the group ID 
    from the request 
    (`javax.portlet.ActionRequest`) via 
    [`ParamUtil`](@platform-ref@/7.1-latest/javadocs/portal-kernel/com/liferay/portal/kernel/util/ParamUtil.html): 

        long groupId = ParamUtil.getLong(actionRequest, "groupId");

    It's also possible to get the group ID via the 
    [`ThemeDisplay`](@platform-ref@/7.1-latest/javadocs/portal-kernel/com/liferay/portal/kernel/theme/ThemeDisplay.html). 
    Calling the `ThemeDisplay` method `getScopeGroupId()` gets the ID of your 
    app's current site (group). For more information, see the 
    [Data Scopes tutorial](/develop/tutorials/-/knowledge_base/7-1/data-scopes). 

        ThemeDisplay themeDisplay = (ThemeDisplay) request.getAttribute(WebKeys.THEME_DISPLAY);
        long groupId = themeDisplay.getScopeGroupId();

    Note that getting the parent folder ID isn't necessary because this example 
    uses the root folder, for which @product@ provides a constant. Also, the 
    boolean value can be provided directly---it doesn't need to be retrieved 
    from somewhere. For more information on getting repository and folder IDs, 
    see the 
    [getting started tutorial's](/develop/tutorials/-/knowledge_base/7-1/getting-started-with-the-documents-and-media-api) 
    sections on specifying repositories and folders. 

3.  Call the service reference's `getFolders` method with the data from the 
    previous step and any other values you want to provide. Note that this 
    example uses `DLFolderConstants.DEFAULT_PARENT_FOLDER_ID` to specify the 
    repository's root folder as the parent folder. It also uses `true` to 
    include any mount folders in the results: 

        _dlAppService.getFolders(groupId, DLFolderConstants.DEFAULT_PARENT_FOLDER_ID, true)

Note that this is one of many methods you can use to get folders. The rest are 
listed in the 
[`DLAppService` Javadoc](@platform-ref@/7.1-latest/javadocs/portal-kernel/com/liferay/document/library/kernel/service/DLAppService.html). 

## Related Topics [](id=related-topics)

[Getting Started with the Documents and Media API](/develop/tutorials/-/knowledge_base/7-1/getting-started-with-the-documents-and-media-api)

[Getting Files](/develop/tutorials/-/knowledge_base/7-1/getting-files)

[Getting Multiple Entity Types](/develop/tutorials/-/knowledge_base/7-1/getting-multiple-entity-types)
