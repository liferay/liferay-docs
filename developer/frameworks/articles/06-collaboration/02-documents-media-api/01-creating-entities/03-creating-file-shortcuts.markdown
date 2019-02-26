# Creating File Shortcuts [](id=creating-file-shortcuts)

To create file shortcuts (`FileShortcut` entities) in the Documents and Media 
library, you must use the 
[`DLAppService`](@platform-ref@/7.2-latest/javadocs/portal-kernel/com/liferay/document/library/kernel/service/DLAppService.html) 
interface's `addFileShortcut` method. The steps here show you how to do this. 
For more detailed information, see 
[Creating Files, Folders, and Shortcuts](liferay.com). 
For general information on using the API, see 
[Documents and Media API](liferay.com). 

Follow these steps to create a file shortcut with the `DLAppService` method 
`addFileShortcut`: 

1.  Get a reference to `DLAppService`: 

        @Reference
        private DLAppService _dlAppService;

2.  Get the data needed to populate the `addFileShortcut` method's arguments. 
    Since it's common to create a file shortcut with data submitted by the end 
    user, you can extract the data from the request. This example does so via 
    `javax.portlet.ActionRequest` and 
    [`ParamUtil`](@platform-ref@/7.2-latest/javadocs/portal-kernel/com/liferay/portal/kernel/util/ParamUtil.html), 
    but you can get the data any way you wish: 

        long repositoryId = ParamUtil.getLong(actionRequest, "repositoryId");
        long folderId = ParamUtil.getLong(actionRequest, "folderId");
        long toFileEntryId = ParamUtil.getLong(actionRequest, "toFileEntryId");

        ServiceContext serviceContext = ServiceContextFactory.getInstance(
                            DLFileShortcutConstants.getClassName(), actionRequest);

    For more information on `ServiceContext`, see the tutorial 
    [Understanding ServiceContext](/develop/tutorials/-/knowledge_base/7-2/understanding-servicecontext). 

3.  Call the service reference's `addFileShortcut` method with the data from the 
    previous step: 

        FileShortcut fileShortcut = _dlAppService.addFileShortcut(
                                            repositoryId, folderId, toFileEntryId, 
                                            serviceContext);

    The method returns a `FileShortcut` object, which this example sets to a 
    variable for later use. Note, however, that you don't have to do this. 

You can find the full code for this example in the `updateFileShortcut` method 
of @product@'s 
[`EditFileShortcutMVCActionCommand`](https://github.com/liferay/liferay-portal/blob/master/modules/apps/document-library/document-library-web/src/main/java/com/liferay/document/library/web/internal/portlet/action/EditFileShortcutMVCActionCommand.java) 
class. This class uses the Documents and Media API to implement almost all the 
`FileShortcut` actions that the Documents and Media app supports. Also note that 
this `updateFileShortcut` method, as well as the rest of 
`EditFileShortcutMVCActionCommand`, contains additional logic to suit the 
specific needs of the Documents and Media app. 

## Related Topics [](id=related-topics)

[Deleting File Shortcuts](/develop/tutorials/-/knowledge_base/7-2/deleting-file-shortcuts)

[Updating File Shortcuts](/develop/tutorials/-/knowledge_base/7-2/updating-file-shortcuts)
