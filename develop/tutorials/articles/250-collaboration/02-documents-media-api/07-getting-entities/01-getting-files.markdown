# Getting Files [](id=getting-files)

Getting files is one of the most common tasks you'll perform with the Documents 
and Media API. There are two main method families for getting files: 

-   `getFileEntries`: Gets files from a specific repository. 
-   `getGroupFileEntries`: Gets files from a site (group), regardless of 
    repository. 

Since these method families are common, their methods share many parameters: 

-   `repositoryId`: The ID of the repository to get files from. To specify the 
    default site repository, use the `groupId` (site ID). 
-   `folderId`: The ID of the folder to get files from. Note that these methods 
    don't traverse the folder structure---they only get files directly from the 
    specified folder. To specify the repository's root folder, use the constant 
    `DLFolderConstants.DEFAULT_PARENT_FOLDER_ID`. 
-   `start` and `end`: Integers that specify the lower and upper bounds, 
    respectively, of collection items to include in a page of results. If you 
    don't want to use pagination, use `QueryUtil.ALL_POS` for these parameters. 
-   `obc`: The comparator to use to order collection items. Comparators are 
    [`OrderByComparator`](@platform-ref@/7.1-latest/javadocs/portal-kernel/com/liferay/portal/kernel/util/OrderByComparator.html) 
    implementations that sort collection items. 
-   `fileEntryTypeId`: The ID of the file type to retrieve. Use this to retrieve 
    files of a specific type. 
-   `mimeTypes`: The MIME types of the files to retrieve. Use this to retrieve 
    files of the specified MIME types. You can specify MIME types via the 
    constants in 
    [`ContentTypes`](@platform-ref@/7.1-latest/javadocs/portal-kernel/com/liferay/portal/kernel/util/ContentTypes.html). 

Note that the `obc` parameter must be an implementation of `OrderByComparator`. 
Although you can implement your own comparators, @product@ already contains a 
few useful implementations in the package 
[`com.liferay.document.library.kernel.util.comparator`](@platform-ref@/7.1-latest/javadocs/portal-kernel/com/liferay/document/library/kernel/util/comparator/package-summary.html): 

-   `RepositoryModelCreateDateComparator`: Sorts by creation date. 
-   `RepositoryModelModifiedDateComparator`: Sorts by modification date. 
-   `RepositoryModelReadCountComparator`: Sorts by number of views. 
-   `RepositoryModelSizeComparator`: Sorts by file size. 
-   `RepositoryModelTitleComparator`: Sorts by title. 

As an example, this 
[`getFileEntries`](@platform-ref@/7.1-latest/javadocs/portal-kernel/com/liferay/document/library/kernel/service/DLAppService.html#getFileEntries-long-long-java.lang.String:A-int-int-com.liferay.portal.kernel.util.OrderByComparator-) 
method contains all the above parameters except `fileEntryTypeId` (it contains 
`mimeTypes` instead): 

    List<FileEntry> getFileEntries(
            long repositoryId, 
            long folderId, 
            String[] mimeTypes, 
            int start, 
            int end, 
            OrderByComparator<FileEntry> obc
    )

Follow these steps to use this method to get a list of files. Note that the 
example in these steps gets all the PNG images from the root folder of a Site's 
default repository, sorted by title: 

1.  Get a reference to `DLAppService`: 

        @Reference
        private DLAppService _dlAppService;

    For more information on this, see the section on 
    [getting a service reference](/develop/tutorials/-/knowledge_base/7-1/getting-started-with-the-documents-and-media-api#getting-a-service-reference) 
    in the getting started tutorial. 

2.  Get the data needed to populate the method's arguments. You can do this any 
    way you wish. As the next step describes, @product@ provides constants and a 
    comparator for all the arguments this example needs besides the group ID. 
    This example gets the group ID by using 
    [`ParamUtil`](@platform-ref@/7.1-latest/javadocs/portal-kernel/com/liferay/portal/kernel/util/ParamUtil.html) 
    with the request (`javax.portlet.ActionRequest`): 

        long groupId = ParamUtil.getLong(actionRequest, "groupId");

    It's also possible to get the group ID via the 
    [`ThemeDisplay`](@platform-ref@/7.1-latest/javadocs/portal-kernel/com/liferay/portal/kernel/theme/ThemeDisplay.html). 
    Calling the `ThemeDisplay` method `getScopeGroupId()` gets the ID of your 
    app's current site (group): 

        ThemeDisplay themeDisplay = (ThemeDisplay) request.getAttribute(WebKeys.THEME_DISPLAY);
        long groupId = themeDisplay.getScopeGroupId();

    For more information, see the 
    [Data Scopes tutorial](/develop/tutorials/-/knowledge_base/7-1/data-scopes). 

3.  Use the data from the previous step and any other values you want to provide 
    to call the service reference method you want to use to get the files. This 
    example calls the above `getFileEntries` method with the group ID from the 
    previous step, and constants and a comparator for the remaining arguments: 

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

    -   `groupId`: Using the group ID as the repository ID specifies that the 
        operation takes place in the default site repository. 
    -   `DLFolderConstants.DEFAULT_PARENT_FOLDER_ID`: Uses the 
        [`DLFolderConstants`](@platform-ref@/7.1-latest/javadocs/portal-kernel/com/liferay/document/library/kernel/model/DLFolderConstants.html) 
        constant `DEFAULT_PARENT_FOLDER_ID` to specify the repository's root 
        folder. 
    -   `new String[] {ContentTypes.IMAGE_PNG}`: Uses the 
        [`ContentTypes`](@platform-ref@/7.1-latest/javadocs/portal-kernel/com/liferay/portal/kernel/util/ContentTypes.html) 
        constant `IMAGE_PNG` to specify PNG images. 
    -   `QueryUtil.ALL_POS`: Uses the 
        [`QueryUtil`](@platform-ref@/7.1-latest/javadocs/portal-kernel/com/liferay/portal/kernel/dao/orm/QueryUtil.html) 
        constant `ALL_POS` for the start and end positions in the results. This 
        specifies all results, bypassing pagination. 
    -   `new RepositoryModelTitleComparator<>()`: Creates a new 
        [`RepositoryModelTitleComparator`](@platform-ref@/7.1-latest/javadocs/portal-kernel/com/liferay/document/library/kernel/util/comparator/RepositoryModelTitleComparator.html), 
        which sorts the results by title. 

Remember, this is just one of many `getFileEntries` and `getGroupFileEntries` 
methods. To see all such methods, see the 
[`DLAppService` Javadoc](@platform-ref@/7.1-latest/javadocs/portal-kernel/com/liferay/document/library/kernel/service/DLAppService.html). 

## Related Topics [](id=related-topics)

[Getting Started with the Documents and Media API](/develop/tutorials/-/knowledge_base/7-1/getting-started-with-the-documents-and-media-api)

[Getting Folders](/develop/tutorials/-/knowledge_base/7-1/getting-folders)

[Getting Multiple Entity Types](/develop/tutorials/-/knowledge_base/7-1/getting-multiple-entity-types)
