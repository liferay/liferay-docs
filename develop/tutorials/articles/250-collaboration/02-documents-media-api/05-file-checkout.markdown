# File Checkout and Checkin

The Document Library lets users 
[check out files](/discover/portal/-/knowledge_base/7-1/checking-out-and-editing-files) 
for editing. A checked-out file is only available for edit to the user who 
checked it out. This prevents conflicting edits on the same file from multiple 
users. The Documents and Media API allows the following checkin/checkout 
operations: 

-   File checkout. 
-   File checkin. 
-   Cancel a checkout, discarding any changes. 
-   File checkin and checkout, in a single operation. 

## File Checkout

When you check out a file, the following things happen: 

-   A private working copy of the file is created. Only you and administrators 
    can access this copy. Until you check the file back in or cancel your 
    changes, any edits you make are stored in the private working copy. 

-   Other users aren't allowed to change or edit any version of the file. This 
    state remains until you cancel or check in your changes. 

The main 
[`DLAppService`](@platform-ref@/7.1-latest/javadocs/portal-kernel/com/liferay/document/library/kernel/service/DLAppService.html) 
method for checking out a file is this `checkOutFileEntry` method: 

    checkOutFileEntry(long fileEntryId, ServiceContext serviceContext)

If calling this method succeeds, then the checkout succeeded. If it throws an 
exception, then you should assume the checkout failed and repeat the operation. 
For a full description of the method and its parameters, see its 
[Javadoc](@platform-ref@/7.1-latest/javadocs/portal-kernel/com/liferay/document/library/kernel/service/DLAppService.html#checkOutFileEntry-long-com.liferay.portal.kernel.service.ServiceContext-). 

Note, however, that you can control how the checkout is performed by setting the 
following attributes in the `ServiceContext` parameter: 

-   `manualCheckInRequired`: By default, the system automatically checks a file 
    out and back in if the user doesn't do so manually. Setting this attribute 
    to `true` prevents the system from doing this, therefore requiring manual 
    checkout and checkin. 

-   `existingDLFileVersionId`: The system typically reuses the private working 
    copy across different checkout/checkin sequences. There's little chance for 
    conflicting edits because only one user at a time can access the private 
    working copy. To force the system to create a new private working copy each 
    time, omit this attribute or give it a value of `0`. 

-   `fileVersionUuid`: This is used by staging, but can be ignored for normal 
    use. Setting this attribute causes the system to create the new private 
    working copy version with the given UUID. 

You can use the `ServiceContext` method 
[`setAttribute(String name, Serializable value)`](@platform-ref@/7.1-latest/javadocs/portal-kernel/com/liferay/portal/kernel/service/ServiceContext.html#setAttribute-java.lang.String-java.io.Serializable-) 
to set these attributes. Here's such an example of setting the 
`manualCheckInRequired` attribute to `true`: 

    serviceContext.setAttribute("manualCheckInRequired", Boolean.TRUE)

## File Checkin

After a file has been checked out and edited, you must check it back in for 
other users to see the new version. Once you do so, you can't access the private 
working copy. The next time the file is checked out, the private working copy's 
contents are overwritten. 

The 
[`DLAppService`](@platform-ref@/7.1-latest/javadocs/portal-kernel/com/liferay/document/library/kernel/service/DLAppService.html) 
method for checking in a file is `checkInFileEntry`: 

    checkInFileEntry(long fileEntryId, boolean majorVersion, String changeLog, 
                    ServiceContext serviceContext)

For a full description of the method and its parameters, see its 
[Javadoc](@platform-ref@/7.1-latest/javadocs/portal-kernel/com/liferay/document/library/kernel/service/DLAppService.html#checkInFileEntry-long-boolean-java.lang.String-com.liferay.portal.kernel.service.ServiceContext-). 
This method uses the private working copy to create a new version of the file. 
As explained in the 
[Updating Files tutorial](liferay.com), 
the `majorVersion` parameter's setting determines how the file's version number 
is incremented. 

## Cancelling a Checkout

The Documents and Media API also lets you cancel a checkout. Use caution with 
this operation---it discards any edits made since checkout. If you're sure you 
want to cancel a checkout, do so with the 
[`DLAppService`](@platform-ref@/7.1-latest/javadocs/portal-kernel/com/liferay/document/library/kernel/service/DLAppService.html) 
method `cancelCheckOut`: 

    cancelCheckOut(long fileEntryId)

For a full description of this method and its parameters, see its 
[Javadoc](@platform-ref@/7.1-latest/javadocs/portal-kernel/com/liferay/document/library/kernel/service/DLAppService.html#cancelCheckOut-long-). 
If you invoke this method without error, you can safely assume that it discarded 
the private working copy and unlocked the file. Other users should now be able 
to check out and edit the file. 
