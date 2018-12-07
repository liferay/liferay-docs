# Deleting File Shortcuts [](id=deleting-file-shortcuts)

The Documents and Media API also lets you delete file shortcuts. To do so, use 
the 
[`DLAppService`](@platform-ref@/7.1-latest/javadocs/portal-kernel/com/liferay/document/library/kernel/service/DLAppService.html) 
method 
[`deleteFileShortcut`](@platform-ref@/7.1-latest/javadocs/portal-kernel/com/liferay/document/library/kernel/service/DLAppService.html#deleteFileShortcut-long-) 
with the ID of the shortcut you want to delete: 

    deleteFileShortcut(long fileShortcutId)

Follow these steps to use this method to delete a file shortcut:

1.  Get a reference to `DLAppService`: 

        @Reference
        private DLAppService _dlAppService;

    For more information on this, see the section on 
    [getting a service reference](/develop/tutorials/-/knowledge_base/7-1/getting-started-with-the-documents-and-media-api#getting-a-service-reference) 
    in the getting started tutorial. 

2.  Get the file shortcut's ID. Since it's common to delete a file shortcut 
    specified by the end user, you can extract its ID from the request. This 
    example does so via `javax.portlet.ActionRequest` and 
    [`ParamUtil`](@platform-ref@/7.1-latest/javadocs/portal-kernel/com/liferay/portal/kernel/util/ParamUtil.html), 
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

## Related Topics [](id=related-topics)

[Moving Entities to the Recycle Bin](/develop/tutorials/-/knowledge_base/7-1/moving-entities-to-the-recycle-bin)

[Creating File Shortcuts](/develop/tutorials/-/knowledge_base/7-1/creating-file-shortcuts)

[Updating File Shortcuts](/develop/tutorials/-/knowledge_base/7-1/updating-file-shortcuts)
