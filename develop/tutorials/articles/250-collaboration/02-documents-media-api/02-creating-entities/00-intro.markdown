# Creating Files, Folders, and Shortcuts [](id=creating-files-folders-and-shortcuts)

Creating entities in the Documents and Media library is a key use case for the 
Documents and Media API. For example, you can use the API to create files, 
folders, and file shortcuts in the Documents and Media library. After all, what 
good would a Documents and Media API be if it couldn't create such entities? No 
good at all! 

If you're familiar with @product@ development, you'll likely be familiar with 
the code conventions for adding entities. In general, methods that do similar
things tend to have similar names. When you must create an entity (whatever it
is), you should look for methods that follow the pattern `add[ModelName]`, where
`[ModelName]` is the name of the entity's data model object. As the 
[getting started tutorial](/develop/tutorials/-/knowledge_base/7-1/getting-started-with-the-documents-and-media-api)
explains, you'll use
[`DLAppService`](@platform-ref@/7.1-latest/javadocs/portal-kernel/com/liferay/document/library/kernel/service/DLAppService.html)
to access the API. This service object contains the following methods for adding
entities: 

-   `addFileEntry`: Adds a file.
-   `addFolder`: Adds a folder.
-   `addFileShortcut`: Adds a shortcut to a file. 

The tutorials that follow show you how to use these methods. 

