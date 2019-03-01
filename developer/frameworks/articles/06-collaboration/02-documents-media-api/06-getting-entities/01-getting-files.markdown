# Getting Files [](id=getting-files)

To get files with the Documents and Media API, use a method from the 
`getFileEntries` or `getGroupFileEntries` method families discussed in 
[Getting Entities](liferay.com). 
The steps here show you how, using this 
[`getFileEntries`](@platform-ref@/7.2-latest/javadocs/portal-kernel/com/liferay/document/library/kernel/service/DLAppService.html#getFileEntries-long-long-java.lang.String:A-int-int-com.liferay.portal.kernel.util.OrderByComparator-) 
method as an example: 

    List<FileEntry> getFileEntries(
            long repositoryId, 
            long folderId, 
            String[] mimeTypes, 
            int start, 
            int end, 
            OrderByComparator<FileEntry> obc
    )

For general information on using the Documents and Media API, see 
[Documents and Media API](liferay.com). 

Follow these steps to get a list of files. This example uses the above 
`getFileEntries` method to get all the PNG images from the root folder of a 
Site's default repository, sorted by title: 

1.  Get a reference to `DLAppService`: 

        @Reference
        private DLAppService _dlAppService;

2.  Get the data needed to populate the method's arguments. You can do this any 
    way you wish. As the next step describes, @product@ provides constants and a 
    comparator for all the arguments this example needs besides the group ID. 
    This example gets the group ID by using 
    [`ParamUtil`](@platform-ref@/7.2-latest/javadocs/portal-kernel/com/liferay/portal/kernel/util/ParamUtil.html) 
    with the request (`javax.portlet.ActionRequest`): 

        long groupId = ParamUtil.getLong(actionRequest, "groupId");

    It's also possible to get the group ID via the 
    [`ThemeDisplay`](@platform-ref@/7.2-latest/javadocs/portal-kernel/com/liferay/portal/kernel/theme/ThemeDisplay.html). 
    Calling the `ThemeDisplay` method `getScopeGroupId()` gets the ID of your 
    app's current site (group): 

        ThemeDisplay themeDisplay = (ThemeDisplay) request.getAttribute(WebKeys.THEME_DISPLAY);
        long groupId = themeDisplay.getScopeGroupId();

    For more information, see 
    [Data Scopes](/develop/tutorials/-/knowledge_base/7-2/data-scopes). 

3.  Use the data from the previous step to call the service reference method you
    want to use to get the files. This example calls the above `getFileEntries`
    method with the group ID from the previous step, and constants and a 
    comparator for the remaining arguments: 

        List<FileEntry> fileEntries = 
                _dlAppService.getFileEntries(
                        groupId, 
                        DLFolderConstants.DEFAULT_PARENT_FOLDER_ID, 
                        new String[] {ContentTypes.IMAGE_PNG}, 
                        QueryUtil.ALL_POS, 
                        QueryUtil.ALL_POS, 
                        new RepositoryModelTitleComparator<>()
                );

    Here's a description of the arguments used in this example: 

    `groupId`: Using the group ID as the repository ID specifies that the 
    operation takes place in the default site repository. 

    `DLFolderConstants.DEFAULT_PARENT_FOLDER_ID`: Uses the 
    [`DLFolderConstants`](@platform-ref@/7.2-latest/javadocs/portal-kernel/com/liferay/document/library/kernel/model/DLFolderConstants.html) 
    constant `DEFAULT_PARENT_FOLDER_ID` to specify the repository's root folder. 

    `new String[] {ContentTypes.IMAGE_PNG}`: Uses the 
    [`ContentTypes`](@platform-ref@/7.2-latest/javadocs/portal-kernel/com/liferay/portal/kernel/util/ContentTypes.html) 
    constant `IMAGE_PNG` to specify PNG images. 

    `QueryUtil.ALL_POS`: Uses the 
    [`QueryUtil`](@platform-ref@/7.2-latest/javadocs/portal-kernel/com/liferay/portal/kernel/dao/orm/QueryUtil.html) 
    constant `ALL_POS` for the start and end positions in the results. This 
    specifies all results, bypassing pagination. 

    `new RepositoryModelTitleComparator<>()`: Creates a new 
    [`RepositoryModelTitleComparator`](@platform-ref@/7.2-latest/javadocs/portal-kernel/com/liferay/document/library/kernel/util/comparator/RepositoryModelTitleComparator.html), 
    which sorts the results by title. 

Remember, this is just one of many `getFileEntries` and `getGroupFileEntries` 
methods. To see all such methods, see the `DLAppService` 
[Javadoc](@platform-ref@/7.2-latest/javadocs/portal-kernel/com/liferay/document/library/kernel/service/DLAppService.html). 

## Related Topics [](id=related-topics)

[Getting Started with the Documents and Media API](liferay.com)

[Getting Folders](liferay.com)

[Getting Multiple Entity Types](liferay.com)
