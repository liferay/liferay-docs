---
header-id: deleting-files
---

# Deleting Files

[TOC levels=1-4]

To delete a file with the Documents and Media API, you must use one of the two 
`deleteFileEntry*` methods discussed in 
[Deleting Entities](/docs/7-2/frameworks/-/knowledge_base/frameworks/deleting-entities). 
The steps here show you how. For general information on using the API, see 
[Documents and Media API](/docs/7-2/frameworks/-/knowledge_base/frameworks/documents-and-media-api). 

Follow these steps to delete a file: 

1.  Get a reference to `DLAppService`: 

        @Reference
        private DLAppService _dlAppService;

2.  Get the data needed to populate the arguments of the `deleteFileEntry*` 
    method you wish to use. Since it's common to delete a file specified by the 
    end user, you can extract the data you need from the request. This example 
    does so via `javax.portlet.ActionRequest` and 
    [`ParamUtil`](@platform-ref@/7.2-latest/javadocs/portal-kernel/com/liferay/portal/kernel/util/ParamUtil.html), 
    but you can get the data any way you wish. Also note that this example gets 
    only the file entry ID because it uses `deleteFileEntry`: 

        long fileEntryId = ParamUtil.getLong(actionRequest, "fileEntryId");

    If you want to use `deleteFileEntryByTitle` instead, you can also get the 
    repository ID, folder ID, and title from the request. 

3.  Call the service reference's `deleteFileEntry*` method you wish to use with 
    the data from the previous step. This example calls `deleteFileEntry` with 
    the file entry's ID: 

        _dlAppService.deleteFileEntry(fileEntryId);

You can find the full code for this example in the `deleteFileEntry` method of 
@product@'s 
[`EditFileEntryMVCActionCommand`](https://github.com/liferay/liferay-portal/blob/master/modules/apps/document-library/document-library-web/src/main/java/com/liferay/document/library/web/internal/portlet/action/EditFileEntryMVCActionCommand.java) 
class. This class uses the Documents and Media API to implement almost all the 
`FileEntry` actions that the Documents and Media app supports. Also note that 
this `deleteFileEntry` method, as well as the rest of 
`EditFileEntryMVCActionCommand`, contains additional logic to suit the specific 
needs of the Documents and Media app. 

## Related Topics

[Moving Entities to the Recycle Bin](/docs/7-2/frameworks/-/knowledge_base/frameworks/moving-entities-to-the-recycle-bin)

[Creating Files](/docs/7-2/frameworks/-/knowledge_base/frameworks/creating-files)

[Updating Files](/docs/7-2/frameworks/-/knowledge_base/frameworks/updating-files)

[Moving Folders and Files](/docs/7-2/frameworks/-/knowledge_base/frameworks/moving-folders-and-files)
