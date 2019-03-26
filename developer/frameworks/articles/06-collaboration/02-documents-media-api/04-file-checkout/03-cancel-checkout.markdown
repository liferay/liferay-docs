---
header-id: canceling-a-checkout
---

# Canceling a Checkout

[TOC levels=1-4]

To cancel a checkout with the Documents and Media API, use the 
`cancelCheckOut` method discussed in 
[File Checkout and Checkin](/developer/frameworks/-/knowledge_base/7-2/file-checkout-and-checkin). 
The steps here show you how. For general information on using the API, see 
[Documents and Media API](/developer/frameworks/-/knowledge_base/7-2/documents-and-media-api). 

Follow these steps to cancel a checkout: 

1.  Get a reference to `DLAppService`: 

        @Reference
        private DLAppService _dlAppService;

2.  Get the ID of the file whose checkout you want to cancel. Since it's common 
    to cancel a checkout in response to a user action, you can extract the file 
    ID from the request. This example does so via `javax.portlet.ActionRequest` 
    and 
    [`ParamUtil`](@platform-ref@/7.2-latest/javadocs/portal-kernel/com/liferay/portal/kernel/util/ParamUtil.html), 
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

## Related Topics

[Checking Out Files](/developer/frameworks/-/knowledge_base/7-2/checking-out-files)

[Checking In Files](/developer/frameworks/-/knowledge_base/7-2/checking-in-files)

[Updating Files](/developer/frameworks/-/knowledge_base/7-2/updating-files)
