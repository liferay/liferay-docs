# Canceling a Check-out [](id=cancelling-a-checkout)

The Documents and Media API also lets you cancel a check-out. Use caution with 
this operation---it discards any edits made since check-out. If you're sure you 
want to cancel a check-out, do so with the 
[`DLAppService`](@platform-ref@/7.1-latest/javadocs/portal-kernel/com/liferay/document/library/kernel/service/DLAppService.html) 
method `cancelCheckOut`: 

    cancelCheckOut(long fileEntryId)

For a full description of this method and its parameter, see its 
[Javadoc](@platform-ref@/7.1-latest/javadocs/portal-kernel/com/liferay/document/library/kernel/service/DLAppService.html#cancelCheckOut-long-). 
If you invoke this method without error, you can safely assume that it discarded 
the private working copy and unlocked the file. Other users should now be able 
to check out and edit the file. 

Follow these steps to cancel a check-out: 

1.  Get a reference to `DLAppService`: 

        @Reference
        private DLAppService _dlAppService;

    For more information on this, see the section on 
    [getting a service reference](/develop/tutorials/-/knowledge_base/7-1/getting-started-with-the-documents-and-media-api#getting-a-service-reference) 
    in the getting started tutorial. 

2.  Get the ID of the file whose check-out you want to cancel. Since it's common 
    to cancel a check-out in response to a user action, you can extract the file 
    ID from the request. This example does so via `javax.portlet.ActionRequest` 
    and 
    [`ParamUtil`](@platform-ref@/7.1-latest/javadocs/portal-kernel/com/liferay/portal/kernel/util/ParamUtil.html), 
    but you can get it any way you wish: 

        long fileEntryId = ParamUtil.getLong(actionRequest, "fileEntryId");

3.  Call the service reference's `cancelCheckOut` method with the file's ID: 

        _dlAppService.cancelCheckOut(fileEntryId);

You can find the full code for this example in the `cancelFileEntriesCheckOut` 
method of @product@'s 
[`EditFileEntryMVCActionCommand`](https://github.com/liferay/liferay-portal/blob/master/modules/apps/document-library/document-library-web/src/main/java/com/liferay/document/library/web/internal/portlet/action/EditFileEntryMVCActionCommand.java) 
class. This class uses the Documents and Media API to implement almost all the 
`FileEntry` actions that the Documents and Media app supports. Also note that 
this `cancelFileEntriesCheckOut` method, as well as the rest of 
`EditFileEntryMVCActionCommand`, contains additional logic to suit the specific 
needs of the Documents and Media app. 

## Related Topics [](id=related-topics)

[File Check-out](/develop/tutorials/-/knowledge_base/7-1/file-checkout)

[File Check-in](/develop/tutorials/-/knowledge_base/7-1/file-checkin) 

[Updating Files](/develop/tutorials/-/knowledge_base/7-1/updating-files)
