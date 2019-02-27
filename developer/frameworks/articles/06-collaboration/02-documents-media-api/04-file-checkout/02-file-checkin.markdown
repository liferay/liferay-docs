# Checking In Files

To check in a file with the Documents and Media API, use the 
`checkInFileEntry` method discussed in 
[File Checkout and Checkin](liferay.com). 
The steps here show you how. For general information on using the API, see 
[Documents and Media API](liferay.com). 

Follow these steps to use `checkInFileEntry` to check in a file: 

1.  Get a reference to `DLAppService`: 

        @Reference
        private DLAppService _dlAppService;

2.  Get the data needed to populate the `checkInFileEntry` method's arguments. 
    Since it's common to check in a file in response to an action by the end 
    user, you can extract the data from the request. This example does so via 
    `javax.portlet.ActionRequest` and 
    [`ParamUtil`](@platform-ref@/7.2-latest/javadocs/portal-kernel/com/liferay/portal/kernel/util/ParamUtil.html), 
    but you can get the data any way you wish: 

        long fileEntryId = ParamUtil.getLong(actionRequest, "fileEntryId");
        boolean majorVersion = ParamUtil.getBoolean(actionRequest, "majorVersion");
        String changeLog = ParamUtil.getString(actionRequest, "changeLog");

        ServiceContext serviceContext = ServiceContextFactory.getInstance(actionRequest);

    For more information on `ServiceContext`, see the tutorial 
    [Understanding ServiceContext](/develop/tutorials/-/knowledge_base/7-2/understanding-servicecontext). 

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

[File Checkout](liferay.com)

[Canceling a Checkout](liferay.com)

[Updating Files](liferay.com)
