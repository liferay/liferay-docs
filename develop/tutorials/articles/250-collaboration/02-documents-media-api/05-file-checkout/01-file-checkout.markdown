# File Check-out [](id=file-checkout)

Here's what happens when you check out a file: 

-   A private working copy of the file is created that only you and 
    administrators can access. Until you check the file back in or cancel your 
    changes, any edits you make are stored in the private working copy. 

-   Other users can't change or edit any version of the file. This state remains 
    until you cancel or check in your changes. 

The main 
[`DLAppService`](@platform-ref@/7.1-latest/javadocs/portal-kernel/com/liferay/document/library/kernel/service/DLAppService.html) 
method for checking out a file is this `checkOutFileEntry` method: 

    checkOutFileEntry(long fileEntryId, ServiceContext serviceContext)

If this method throws an exception, then you should assume the checkout failed 
and repeat the operation. For a full description of the method and its 
parameters, see its 
[Javadoc](@platform-ref@/7.1-latest/javadocs/portal-kernel/com/liferay/document/library/kernel/service/DLAppService.html#checkOutFileEntry-long-com.liferay.portal.kernel.service.ServiceContext-). 

Follow these steps to use this method to check out a file: 

1.  Get a reference to `DLAppService`: 

        @Reference
        private DLAppService _dlAppService;

    For more information on this, see the section on 
    [getting a service reference](/develop/tutorials/-/knowledge_base/7-1/getting-started-with-the-documents-and-media-api#getting-a-service-reference) 
    in the getting started tutorial. 

2.  Get the data needed to populate the `checkOutFileEntry` method's arguments. 
    Since it's common to check out a file in response to an action by the end 
    user, you can extract the data from the request. This example does so via 
    `javax.portlet.ActionRequest` and 
    [`ParamUtil`](@platform-ref@/7.1-latest/javadocs/portal-kernel/com/liferay/portal/kernel/util/ParamUtil.html), 
    but you can get the data any way you wish: 

        long fileEntryId = ParamUtil.getLong(actionRequest, "fileEntryId");

        ServiceContext serviceContext = ServiceContextFactory.getInstance(actionRequest);

    For more information on `ServiceContext`, see the tutorial 
    [Understanding ServiceContext](/develop/tutorials/-/knowledge_base/7-1/understanding-servicecontext). 

3.  Call the service reference's `checkOutFileEntry` method with the data from 
    the previous step: 

        _dlAppService.checkOutFileEntry(fileEntryId, serviceContext);

You can find the full code for this example in the `checkOutFileEntries` method 
of @product@'s 
[`EditFileEntryMVCActionCommand`](https://github.com/liferay/liferay-portal/blob/master/modules/apps/document-library/document-library-web/src/main/java/com/liferay/document/library/web/internal/portlet/action/EditFileEntryMVCActionCommand.java) 
class. This class uses the Documents and Media API to implement almost all the 
`FileEntry` actions that the Documents and Media app supports. Also note that 
this `checkOutFileEntries` method, as well as the rest of 
`EditFileEntryMVCActionCommand`, contains additional logic to suit the specific 
needs of the Documents and Media app. 

## Fine-tuning Checkout [](id=fine-tuning-checkout)

You can control how the checkout is performed by setting the following 
attributes in the `ServiceContext` parameter: 

-   `manualCheckInRequired`: By default, the system automatically checks out/in 
    a file when a user edits it. Setting this attribute to `true` prevents this,
    therefore requiring manual check-out and check-in. 

-   `existingDLFileVersionId`: The system typically reuses the private working
    copy across different check-out/check-in sequences. There's little chance
    for conflicting edits because only one user at a time can access the private
    working copy. To force the system to create a new private working copy each
    time, omit this attribute or set it to `0`. 

-   `fileVersionUuid`: This is used by 
    [staging](/discover/portal/-/knowledge_base/7-1/staging-content-for-publication), 
    but can be ignored for normal use. Setting this attribute causes the system 
    to create the new private working copy version with the given UUID. 

To set these attributes, use the `ServiceContext` method 
[`setAttribute(String name, Serializable value)`](@platform-ref@/7.1-latest/javadocs/portal-kernel/com/liferay/portal/kernel/service/ServiceContext.html#setAttribute-java.lang.String-java.io.Serializable-). 
Here's an example of setting the `manualCheckInRequired` attribute to `true`: 

    serviceContext.setAttribute("manualCheckInRequired", Boolean.TRUE)

## Related Topics [](id=related-topics)

[File Check-in](/develop/tutorials/-/knowledge_base/7-1/file-checkin)

[Cancelling a Check-out](/develop/tutorials/-/knowledge_base/7-1/cancelling-a-checkout)

[Updating Files](/develop/tutorials/-/knowledge_base/7-1/updating-files)
