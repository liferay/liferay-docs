# Creating Files, Folders, and Shortcuts [](id=creating-files-folders-and-shortcuts)

The primary use case for the API is to create files, folders, and file shortcuts
in the Documents and Media library. 

If you've used other Liferay APIs, the Docs &amp; Media API follows the same
conventions. In general, methods that do similar things tend to have similar
names. When you must create an entity (whatever it is), look for methods that
follow the pattern `add[ModelName]`, where `[ModelName]` is the name of the
entity's data model object. As the 
[getting started tutorial](/develop/tutorials/-/knowledge_base/7-1/getting-started-with-the-documents-and-media-api)
explains, you'll use
[`DLAppService`](@platform-ref@/7.1-latest/javadocs/portal-kernel/com/liferay/document/library/kernel/service/DLAppService.html)
to access the API. This service object contains the following methods for adding
entities: 

-   `addFileEntry`: Adds a file.
-   `addFolder`: Adds a folder.
-   `addFileShortcut`: Adds a shortcut to a file. 

The tutorials that follow show you how to use these methods. 

