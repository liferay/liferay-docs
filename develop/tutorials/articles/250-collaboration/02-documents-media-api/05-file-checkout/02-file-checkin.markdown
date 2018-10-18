# File Checkin [](id=file-checkin)

After checking out and editing a file, you must check it back in for other users 
to see the new version. Once you do so, you can't access the private working 
copy. The next time the file is checked out, the private working copy's contents 
are overwritten. 

The 
[`DLAppService`](@platform-ref@/7.1-latest/javadocs/portal-kernel/com/liferay/document/library/kernel/service/DLAppService.html) 
method for checking in a file is `checkInFileEntry`: 

    checkInFileEntry(long fileEntryId, boolean majorVersion, String changeLog, 
                    ServiceContext serviceContext)

For a full description of the method and its parameters, see its 
[Javadoc](@platform-ref@/7.1-latest/javadocs/portal-kernel/com/liferay/document/library/kernel/service/DLAppService.html#checkInFileEntry-long-boolean-java.lang.String-com.liferay.portal.kernel.service.ServiceContext-). 
This method uses the private working copy to create a new version of the file. 
As the 
[Updating Files tutorial](/develop/tutorials/-/knowledge_base/7-1/updating-files) 
explains, the `majorVersion` parameter's setting determines how the file's 
version number is incremented. 

Follow these steps to use `checkInFileEntry` to check in a file:

1.  Get a reference to `DLAppService`: 

        @Reference
        private DLAppService _dlAppService;

    For more information on this, see the section on 
    [getting a service reference](/develop/tutorials/-/knowledge_base/7-1/getting-started-with-the-documents-and-media-api#getting-a-service-reference) 
    in the getting started tutorial. 

2.  Get the data needed to populate the `checkInFileEntry` method's arguments. 
    Since it's common to check in a file in response to an action by the end 
    user, you can extract the data from the request. This example does so via 
    `javax.portlet.ActionRequest` and 
    [`ParamUtil`](@platform-ref@/7.1-latest/javadocs/portal-kernel/com/liferay/portal/kernel/util/ParamUtil.html), 
    but you can get the data any way you wish: 

        long fileEntryId = ParamUtil.getLong(actionRequest, "fileEntryId");
        boolean majorVersion = ParamUtil.getBoolean(actionRequest, "majorVersion");
        String changeLog = ParamUtil.getString(actionRequest, "changeLog");

        ServiceContext serviceContext = ServiceContextFactory.getInstance(actionRequest);

    For more information on `ServiceContext`, see the tutorial 
    [Understanding ServiceContext](/develop/tutorials/-/knowledge_base/7-1/understanding-servicecontext). 

3.  Call the service reference's `checkInFileEntry` method with the data from 
    the previous step: 

        _dlAppService.checkInFileEntry(
                fileEntryId, majorVersion, changeLog, serviceContext);

You can find the full code for this example in the `checkInFileEntries` method 
of @product@'s 
[`EditFileEntryMVCActionCommand`](https://github.com/liferay/liferay-portal/blob/master/modules/apps/document-library/document-library-web/src/main/java/com/liferay/document/library/web/internal/portlet/action/EditFileEntryMVCActionCommand.java) 
class. This class uses the Documents and Media API to implement almost all the 
`FileEntry` actions that the Documents and Media app supports. Also note that 
this `checkInFileEntries` method, as well as the rest of 
`EditFileEntryMVCActionCommand`, contains additional logic to suit the specific 
needs of the Documents and Media app. 

## Related Topics [](id=related-topics)

[File Checkout](/develop/tutorials/-/knowledge_base/7-1/file-checkout)

[Cancelling a Checkout](/develop/tutorials/-/knowledge_base/7-1/cancelling-a-checkout)

[Updating Files](/develop/tutorials/-/knowledge_base/7-1/updating-files)
