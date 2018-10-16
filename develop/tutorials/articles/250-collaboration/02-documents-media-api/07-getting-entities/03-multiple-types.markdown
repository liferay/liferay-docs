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

## Related Topics [](id=related-topics)

[Getting Started with the Documents and Media API](/develop/tutorials/-/knowledge_base/7-1/getting-started-with-the-documents-and-media-api)

[Creating Entities](/develop/tutorials/-/knowledge_base/7-1/creating-entities)

[Deleting Entities](/develop/tutorials/-/knowledge_base/7-1/deleting-entities)

[Updating Entities](/develop/tutorials/-/knowledge_base/7-1/updating-entities)

[Copying and Moving Entities](/develop/tutorials/-/knowledge_base/7-1/copying-and-moving-entities)
