# Getting Folders

To get folders with the Documents and Media API, use one of the `getFolders` 
methods in `DLAppService`. This is discussed in more detail in 
[Getting Entities](liferay.com). 
The steps here show you how to call these `getFolders` methods. As an example, 
this method is used to get a parent folder's subfolders: 

    getFolders(long repositoryId, long parentFolderId, boolean includeMountFolders)

For general information on using the Documents and Media API, see 
[Documents and Media API](liferay.com). 

Follow these steps to call a `getFolders` method: 

1.  Get a reference to `DLAppService`: 

        @Reference
        private DLAppService _dlAppService;

2.  Get the data needed to populate the method's arguments any way you wish.
    This `getFolders` method needs a repository ID, a parent folder ID, and
    a boolean value that indicates whether to include mount folders in the
    results. To specify the default site repository, you can use the group ID
    as the repository ID. This example gets the group ID from the request
    (`javax.portlet.ActionRequest`) via 
    [`ParamUtil`](@platform-ref@/7.2-latest/javadocs/portal-kernel/com/liferay/portal/kernel/util/ParamUtil.html): 

        long groupId = ParamUtil.getLong(actionRequest, "groupId");

    It's also possible to get the group ID via the 
    [`ThemeDisplay`](@platform-ref@/7.2-latest/javadocs/portal-kernel/com/liferay/portal/kernel/theme/ThemeDisplay.html). 
    Calling the `ThemeDisplay` method `getScopeGroupId()` gets the ID of your 
    app's current Site (group). For more information, see 
    [Data Scopes](/develop/tutorials/-/knowledge_base/7-2/data-scopes). 

        ThemeDisplay themeDisplay = (ThemeDisplay) request.getAttribute(WebKeys.THEME_DISPLAY);
        long groupId = themeDisplay.getScopeGroupId();

    Note that getting the parent folder ID isn't necessary because this example 
    uses the root folder, for which @product@ provides a constant. Also, the 
    boolean value can be provided directly---it doesn't need to be retrieved 
    from somewhere. 

3.  Call the service reference's `getFolders` method with the data from the 
    previous step and any other values you want to provide. Note that this 
    example uses `DLFolderConstants.DEFAULT_PARENT_FOLDER_ID` to specify the 
    repository's root folder as the parent folder. It also uses `true` to 
    include any mount folders in the results: 

        _dlAppService.getFolders(groupId, DLFolderConstants.DEFAULT_PARENT_FOLDER_ID, true)

This is one of many methods you can use to get folders. The rest are listed in 
the `DLAppService` 
[Javadoc](@platform-ref@/7.2-latest/javadocs/portal-kernel/com/liferay/document/library/kernel/service/DLAppService.html). 

## Related Topics [](id=related-topics)

[Getting Started with the Documents and Media API](liferay.com)

[Getting Files](liferay.com)

[Getting Multiple Entity Types](liferay.com)
