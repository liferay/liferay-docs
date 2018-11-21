# Getting Multiple Entity Types [](id=getting-multiple-entity-types)

There are also methods in the Documents and Media API that retrieve lists 
containing several entity types. These methods use many of the same parameters 
as those already described for retrieving files and folders. For example, 
[this method](@platform-ref@/7.1-latest/javadocs/portal-kernel/com/liferay/document/library/kernel/service/DLAppService.html#getFileEntriesAndFileShortcuts-long-long-int-int-int-) 
gets files and shortcuts from a given repository and folder. The `status` 
parameter specifies a 
[workflow](/discover/portal/-/knowledge_base/7-1/workflow) 
status. As before, the `start` and `end` parameters control pagination of the 
entities: 

    getFileEntriesAndFileShortcuts(long repositoryId, long folderId, int status, int start, int end)

To see all such methods, see the 
[`DLAppService` Javadoc](@platform-ref@/7.1-latest/javadocs/portal-kernel/com/liferay/document/library/kernel/service/DLAppService.html). 

Follow these steps to use the above `getFileEntriesAndFileShortcuts` method. 
Note that the example in these steps gets all the files and shortcuts in the 
default Site repository's root folder: 

1.  Get a reference to `DLAppService`: 

        @Reference
        private DLAppService _dlAppService;

    For more information on this, see the section on 
    [getting a service reference](/develop/tutorials/-/knowledge_base/7-1/getting-started-with-the-documents-and-media-api#getting-a-service-reference) 
    in the getting started tutorial. 

2.  Get the data needed to populate the method's arguments any way you wish. To
    specify the default Site repository, you can use the group ID as the
    repository ID. This example gets the group ID from the request 
    (`javax.portlet.ActionRequest`) via 
    [`ParamUtil`](@platform-ref@/7.1-latest/javadocs/portal-kernel/com/liferay/portal/kernel/util/ParamUtil.html): 

        long groupId = ParamUtil.getLong(actionRequest, "groupId");

    Getting the parent folder ID, workflow status, and start and end parameters 
    isn't necessary because @product@ provides constants for them. The next step 
    shows this in detail. 

3.  Call the service reference method with the data from the previous step and 
    any other values you want to provide. This example calls 
    `getFileEntriesAndFileShortcuts` with the group ID from the previous step 
    and constants for the remaining arguments: 

        _dlAppService.getFileEntriesAndFileShortcuts(
                groupId, 
                DLFolderConstants.DEFAULT_PARENT_FOLDER_ID, 
                WorkflowConstants.STATUS_APPROVED, 
                QueryUtil.ALL_POS, 
                QueryUtil.ALL_POS
        )

    Here's a description of the arguments used in this example: 

    -   `groupId`: Using the group ID as the repository ID specifies that the 
        operation takes place in the default site repository. 
    -   `DLFolderConstants.DEFAULT_PARENT_FOLDER_ID`: Uses the 
        [`DLFolderConstants`](@platform-ref@/7.1-latest/javadocs/portal-kernel/com/liferay/document/library/kernel/model/DLFolderConstants.html) 
        constant `DEFAULT_PARENT_FOLDER_ID` to specify the repository's root 
        folder. 
    -   `WorkflowConstants.STATUS_APPROVED`: Uses the 
        [`WorkflowConstants`](@platform-ref@/7.1-latest/javadocs/portal-kernel/com/liferay/portal/kernel/workflow/WorkflowConstants.html) 
        constant `STATUS_APPROVED` to specify only files/folders that have been 
        approved via workflow. 
    -   `QueryUtil.ALL_POS`: Uses the 
        [`QueryUtil`](@platform-ref@/7.1-latest/javadocs/portal-kernel/com/liferay/portal/kernel/dao/orm/QueryUtil.html) 
        constant `ALL_POS` for the start and end positions in the results. This 
        specifies all results, bypassing pagination. 

## Related Topics [](id=related-topics)

[Getting Started with the Documents and Media API](/develop/tutorials/-/knowledge_base/7-1/getting-started-with-the-documents-and-media-api)

[Getting Files](/develop/tutorials/-/knowledge_base/7-1/getting-files)

[Getting Folders](/develop/tutorials/-/knowledge_base/7-1/getting-folders)
