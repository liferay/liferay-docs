# Updating File Shortcuts [](id=updating-file-shortcuts)

The Documents and Media API lets you update file shortcuts (`FileShortcut` 
entities). You can update a shortcut to change the file it points to or the 
folder it resides in. You can do this via the 
[`DLAppService`](@platform-ref@/7.1-latest/javadocs/portal-kernel/com/liferay/document/library/kernel/service/DLAppService.html) 
method `updateFileShortcut`: 

    updateFileShortcut(long fileShortcutId, long folderId, long toFileEntryId, ServiceContext serviceContext)

All of this method's parameters are mandatory. To retain any of the shortcut's 
original values, you must provide them to this method. For a full description of 
the parameters, see the method's 
[Javadoc](@platform-ref@/7.1-latest/javadocs/portal-kernel/com/liferay/document/library/kernel/service/DLAppService.html#updateFileShortcut-long-long-long-com.liferay.portal.kernel.service.ServiceContext-). 

Follow these steps to use this method to update a file shortcut:

1.  Get a reference to `DLAppService`: 

        @Reference
        private DLAppService _dlAppService;

    For more information on this, see the section on 
    [getting a service reference](/develop/tutorials/-/knowledge_base/7-1/getting-started-with-the-documents-and-media-api#getting-a-service-reference) 
    in the getting started tutorial. 

2.  Get the data for the `updateFileShortcut` method's arguments. Obviously, if 
    you want to call the method then you must populate its arguments. Since it's 
    common to update a file shortcut with data submitted by the end user, you 
    can extract those data from the request. This example does so via 
    `javax.portlet.ActionRequest` and 
    [`ParamUtil`](@platform-ref@/7.1-latest/javadocs/portal-kernel/com/liferay/portal/kernel/util/ParamUtil.html), 
    but you can get these data any way you wish: 

        long fileShortcutId = ParamUtil.getLong(actionRequest, "fileShortcutId");
        long folderId = ParamUtil.getLong(actionRequest, "folderId");
        long toFileEntryId = ParamUtil.getLong(actionRequest, "toFileEntryId");

        ServiceContext serviceContext = ServiceContextFactory.getInstance(
                    DLFileShortcutConstants.getClassName(), actionRequest);

    For more information on getting folder IDs, see the 
    [getting started tutorial's](/develop/tutorials/-/knowledge_base/7-1/getting-started-with-the-documents-and-media-api) 
    section on specifying folders. For more information on `ServiceContext` see 
    the tutorial 
    [Understanding ServiceContext](/develop/tutorials/-/knowledge_base/7-1/understanding-servicecontext). 

3.  Call the service reference's `updateFileShortcut` method with the data from 
    the previous step: 

        _dlAppService.updateFileShortcut(
                fileShortcutId, folderId, toFileEntryId, serviceContext);

You can find the full code for this example in the `updateFileShortcut` method 
of @product@'s 
[`EditFileShortcutMVCActionCommand`](https://github.com/liferay/liferay-portal/blob/master/modules/apps/document-library/document-library-web/src/main/java/com/liferay/document/library/web/internal/portlet/action/EditFileShortcutMVCActionCommand.java) 
class. This class uses the Documents and Media API to implement almost all the 
`FileShortcut` actions that the Documents and Media app supports. Also note that 
this `updateFileShortcut` method, as well as the rest of 
`EditFileShortcutMVCActionCommand`, contains additional logic to suit the 
specific needs of the Documents and Media app. 

## Related Topics [](id=related-topics)

[Creating File Shortcuts](/develop/tutorials/-/knowledge_base/7-1/creating-file-shortcuts)

[Deleting File Shortcuts](/develop/tutorials/-/knowledge_base/7-1/deleting-file-shortcuts)
