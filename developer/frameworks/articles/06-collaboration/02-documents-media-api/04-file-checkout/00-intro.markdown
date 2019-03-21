---
header-id: file-checkout-and-checkin
---

# File Checkout and Checkin

Users can 
[check out files](/discover/portal/-/knowledge_base/7-2/checking-out-and-editing-files) 
from the Document Library for editing. Only the user who checked out the file 
can edit it. This prevents conflicting edits on the same file from multiple 
users. The Documents and Media API allows these checkin/checkout operations: 

-   [File Checkout](file-checkout) 
-   [File Checkin](file-checkin) 
-   [Canceling a Checkout](canceling-a-checkout) 

## File Checkout

Here's what happens when you check out a file: 

-   A private working copy of the file is created that only you and 
    administrators can access. Until you check the file back in or cancel your 
    changes, any edits you make are stored in the private working copy. 

-   Other users can't change or edit any version of the file. This state remains 
    until you cancel or check in your changes. 

The main `DLAppService` method for checking out a file is this 
`checkOutFileEntry` method: 

    checkOutFileEntry(long fileEntryId, ServiceContext serviceContext)

If this method throws an exception, then you should assume the checkout failed 
and repeat the operation. For a full description of the method and its 
parameters, see its 
[Javadoc](@platform-ref@/7.2-latest/javadocs/portal-kernel/com/liferay/document/library/kernel/service/DLAppService.html#checkOutFileEntry-long-com.liferay.portal.kernel.service.ServiceContext-). 
For step-by-step instructions on using this method, see 
[Checking Out Files](/developer/frameworks/-/knowledge_base/7-2/checking-out-files). 

### Fine-tuning Checkout

You can control how the checkout is performed by setting the following 
attributes in the `checkOutFileEntry` method's `ServiceContext` parameter: 

-   `manualCheckInRequired`: By default, the system automatically checks out/in 
    a file when a user edits it. Setting this attribute to `true` prevents this,
    therefore requiring manual checkout and checkin. 

-   `existingDLFileVersionId`: The system typically reuses the private working
    copy across different checkout/checkin sequences. There's little chance for 
    conflicting edits because only one user at a time can access the private 
    working copy. To force the system to create a new private working copy each
    time, omit this attribute or set it to `0`. 

-   `fileVersionUuid`: This is used by 
    [staging](/discover/portal/-/knowledge_base/7-2/staging-content-for-publication), 
    but can be ignored for normal use. Setting this attribute causes the system 
    to create the new private working copy version with the given UUID. 

To set these attributes, use the `ServiceContext` method 
[`setAttribute(String name, Serializable value)`](@platform-ref@/7.2-latest/javadocs/portal-kernel/com/liferay/portal/kernel/service/ServiceContext.html#setAttribute-java.lang.String-java.io.Serializable-). 
Here's an example of setting the `manualCheckInRequired` attribute to `true`: 

    serviceContext.setAttribute("manualCheckInRequired", Boolean.TRUE)

## File Checkin

After checking out and editing a file, you must check it back in for other users 
to see the new version. Once you do so, you can't access the private working 
copy. The next time the file is checked out, the private working copy's contents 
are overwritten. 

The `DLAppService` method for checking in a file is `checkInFileEntry`: 

    checkInFileEntry(long fileEntryId, boolean majorVersion, String changeLog, 
                    ServiceContext serviceContext)

For a full description of the method and its parameters, see its 
[Javadoc](@platform-ref@/7.2-latest/javadocs/portal-kernel/com/liferay/document/library/kernel/service/DLAppService.html#checkInFileEntry-long-boolean-java.lang.String-com.liferay.portal.kernel.service.ServiceContext-). 
This method uses the private working copy to create a new version of the file. 
As 
[Updating Files](/developer/frameworks/-/knowledge_base/7-2/updating-files) 
explains, the `majorVersion` parameter's setting determines how the file's 
version number is incremented. 

For step-by-step instructions on using this method, see 
[Checking In Files](/developer/frameworks/-/knowledge_base/7-2/checking-in-files). 

## Canceling a Checkout

You can also cancel a checkout. Use caution with this operation---it discards 
any edits made since checkout. If you're sure you want to cancel a checkout, do 
so with the `DLAppService` method `cancelCheckOut`: 

    cancelCheckOut(long fileEntryId)

For a full description of this method and its parameter, see its 
[Javadoc](@platform-ref@/7.2-latest/javadocs/portal-kernel/com/liferay/document/library/kernel/service/DLAppService.html#cancelCheckOut-long-). 
If you invoke this method without error, you can safely assume that it discarded 
the private working copy and unlocked the file. Other users should now be able 
to check out and edit the file. 

For step-by-step instructions on using this method, see 
[Canceling a Checkout](/developer/frameworks/-/knowledge_base/7-2/canceling-a-checkout). 
