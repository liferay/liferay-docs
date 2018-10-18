# Creating File Shortcuts [](id=creating-file-shortcuts)

To create file shortcuts (`FileShortcut` entities) in the Documents and Media 
library, you must use the 
[`DLAppService`](@platform-ref@/7.1-latest/javadocs/portal-kernel/com/liferay/document/library/kernel/service/DLAppService.html) 
interface's `addFileShortcut` method: 

    addFileShortcut(long repositoryId, 
                    long folderId, 
                    long toFileEntryId, 
                    ServiceContext serviceContext)

See 
[this method's Javadoc](@platform-ref@/7.1-latest/javadocs/portal-kernel/com/liferay/document/library/kernel/service/DLAppService.html#addFileShortcut-long-long-long-com.liferay.portal.kernel.service.ServiceContext-) 
for a description of the parameters. Note that all this method's parameters are 
mandatory. 

Keep in mind the following when creating shortcuts: 

-   You can create a shortcut to a file in a different Site, if that file and 
    its resulting shortcut are in the same portal instance. 
-   You can't create folder shortcuts. 
-   Shortcuts can only exist in the default Site repository. If you try to 
    invoke `addFileShortcut` with an external repository's ID (e.g.,
    a SharePoint repository), the operation fails. Since different
    repositories support different features, the Documents and Media API only 
    supports the common denominators for all repositories: files and folders. 

Follow these steps to create a file shortcut with the `DLAppService` method 
`addFileShortcut`: 

1.  Get a reference to `DLAppService`: 

        @Reference
        private DLAppService _dlAppService;

    For more information on this, see the section on 
    [getting a service reference](/develop/tutorials/-/knowledge_base/7-1/getting-started-with-the-documents-and-media-api#getting-a-service-reference) 
    in the getting started tutorial. 

2.  Get the data needed to populate the `addFileShortcut` method's arguments. 
    Since it's common to create a file shortcut with data submitted by the end 
    user, you can extract the data from the request. This example does so via 
    `javax.portlet.ActionRequest` and 
    [`ParamUtil`](@platform-ref@/7.1-latest/javadocs/portal-kernel/com/liferay/portal/kernel/util/ParamUtil.html), 
    but you can get the data any way you wish: 

        long repositoryId = ParamUtil.getLong(actionRequest, "repositoryId");
        long folderId = ParamUtil.getLong(actionRequest, "folderId");
        long toFileEntryId = ParamUtil.getLong(actionRequest, "toFileEntryId");

        ServiceContext serviceContext = ServiceContextFactory.getInstance(
                            DLFileShortcutConstants.getClassName(), actionRequest);

    For more information on getting repository and folder IDs, see the 
    [getting started tutorial's](/develop/tutorials/-/knowledge_base/7-1/getting-started-with-the-documents-and-media-api) 
    sections on specifying repositories and folders. For more information on 
    `ServiceContext`, see the tutorial 
    [Understanding ServiceContext](/develop/tutorials/-/knowledge_base/7-1/understanding-servicecontext). 

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

[Deleting File Shortcuts](/develop/tutorials/-/knowledge_base/7-1/deleting-file-shortcuts)

[Updating File Shortcuts](/develop/tutorials/-/knowledge_base/7-1/updating-file-shortcuts)
