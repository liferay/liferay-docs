---
header-id: deleting-file-shortcuts
---

# Deleting File Shortcuts

To delete a file shortcut with the Documents and Media API, you must use the 
`deleteFileShortcut` method discussed in 
[Deleting Entities](/developer/frameworks/-/knowledge_base/7-2/deleting-entities). 
The steps here show you how. For general information on using the API, see 
[Documents and Media API](/developer/frameworks/-/knowledge_base/7-2/documents-and-media-api). 

Follow these steps to delete a file shortcut: 

1.  Get a reference to `DLAppService`: 

        @Reference
        private DLAppService _dlAppService;

2.  Get the file shortcut's ID. Since it's common to delete a file shortcut 
    specified by the end user, you can extract its ID from the request. This 
    example does so via `javax.portlet.ActionRequest` and 
    [`ParamUtil`](@platform-ref@/7.2-latest/javadocs/portal-kernel/com/liferay/portal/kernel/util/ParamUtil.html), 
    but you can do this any way you wish: 

        long fileShortcutId = ParamUtil.getLong(actionRequest, "fileShortcutId");

3.  Use the service reference to call the `deleteFileShortcut` method with the 
    file shortcut ID from the previous step: 

        _dlAppService.deleteFileShortcut(fileShortcutId);

You can find the full code for this example in the `deleteFileShortcut` method 
of @product@'s 
[`EditFileShortcutMVCActionCommand`](https://github.com/liferay/liferay-portal/blob/master/modules/apps/document-library/document-library-web/src/main/java/com/liferay/document/library/web/internal/portlet/action/EditFileShortcutMVCActionCommand.java) 
class. This class uses the Documents and Media API to implement almost all the 
`FileShortcut` actions that the Documents and Media app supports. Also note that 
this `deleteFileShortcut` method, as well as the rest of 
`EditFileShortcutMVCActionCommand`, contains additional logic to suit the 
specific needs of the Documents and Media app. 

## Related Topics

[Moving Entities to the Recycle Bin](/developer/frameworks/-/knowledge_base/7-2/moving-entities-to-the-recycle-bin)

[Creating File Shortcuts](/developer/frameworks/-/knowledge_base/7-2/creating-file-shortcuts)

[Updating File Shortcuts](/developer/frameworks/-/knowledge_base/7-2/updating-file-shortcuts)
