# File Checkout and Checkin [](id=file-checkout-and-checkin)

The Document Library lets users 
[check out files](/discover/portal/-/knowledge_base/7-1/checking-out-and-editing-files) 
for editing. Only the user who checked out the file can edit it. This prevents 
conflicting edits on the same file from multiple users. The Documents and Media 
API allows these checkin/checkout operations: 

-   File checkout. 
-   File checkin. 
-   Cancel a checkout, discarding any changes. 

## File Checkout [](id=file-checkout)

When you check out a file, the following occurs: 

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

The following example comes from @product@'s 
[`EditFileEntryMVCActionCommand`](https://github.com/liferay/liferay-portal/blob/master/modules/apps/document-library/document-library-web/src/main/java/com/liferay/document/library/web/internal/portlet/action/EditFileEntryMVCActionCommand.java) 
class. This class implements almost all the `FileEntry` actions that the 
Documents and Media UI supports. This class's `checkOutFileEntries` method 
checks out files. This method uses the request to get one or more `FileEntry` 
IDs and a `ServiceContext`. It then calls `checkOutFileEntry` with each 
`FileEntry` ID and the `ServiceContext`: 

    protected void checkOutFileEntries(ActionRequest actionRequest)
            throws Exception {

            long fileEntryId = ParamUtil.getLong(actionRequest, "fileEntryId");

            ServiceContext serviceContext = ServiceContextFactory.getInstance(
                    actionRequest);

            if (fileEntryId > 0) {
                    _dlAppService.checkOutFileEntry(fileEntryId, serviceContext);
            }
            else {
                    long[] fileEntryIds = ParamUtil.getLongValues(
                            actionRequest, "rowIdsFileEntry");

                    for (long curFileEntryId : fileEntryIds) {
                            _dlAppService.checkOutFileEntry(curFileEntryId, serviceContext);
                    }
            }
    }

### Fine-tuning Checkout [](id=fine-tuning-checkout)

You can control how the checkout is performed by setting the following 
attributes in the `ServiceContext` parameter: 

-   `manualCheckInRequired`: By default, the system automatically checks out/in 
    a file when a user edits it. Setting this attribute to `true` prevents this, 
    therefore requiring manual checkout and checkin. 

-   `existingDLFileVersionId`: The system typically reuses the private working 
    copy across different checkout/checkin sequences. There's little chance for 
    conflicting edits because only one user at a time can access the private 
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

## File Checkin [](id=file-checkin)

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

As with the file checkin example above, the following example comes from 
`EditFileEntryMVCActionCommand`. This class's `checkInFileEntries` method checks 
in files. The `FileEntry` IDs, `majorVersion`, `changeLog`, and `ServiceContext` 
are retrieved from the request. The `checkInFileEntry` method is then called to 
check in each `FileEntry`: 

    protected void checkInFileEntries(ActionRequest actionRequest)
            throws Exception {

            long fileEntryId = ParamUtil.getLong(actionRequest, "fileEntryId");

            boolean majorVersion = ParamUtil.getBoolean(
                    actionRequest, "majorVersion");
            String changeLog = ParamUtil.getString(actionRequest, "changeLog");

            ServiceContext serviceContext = ServiceContextFactory.getInstance(
                    actionRequest);

            if (fileEntryId > 0) {
                    _dlAppService.checkInFileEntry(
                            fileEntryId, majorVersion, changeLog, serviceContext);
            }
            else {
                    long[] fileEntryIds = ParamUtil.getLongValues(
                            actionRequest, "rowIdsFileEntry");

                    for (long curFileEntryId : fileEntryIds) {
                            _dlAppService.checkInFileEntry(
                                    curFileEntryId, majorVersion, changeLog, serviceContext);
                    }
            }
    }

## Cancelling a Checkout [](id=cancelling-a-checkout)

The Documents and Media API also lets you cancel a checkout. Use caution with 
this operation---it discards any edits made since checkout. If you're sure you 
want to cancel a checkout, do so with the 
[`DLAppService`](@platform-ref@/7.1-latest/javadocs/portal-kernel/com/liferay/document/library/kernel/service/DLAppService.html) 
method `cancelCheckOut`: 

    cancelCheckOut(long fileEntryId)

For a full description of this method and its parameter, see its 
[Javadoc](@platform-ref@/7.1-latest/javadocs/portal-kernel/com/liferay/document/library/kernel/service/DLAppService.html#cancelCheckOut-long-). 
If you invoke this method without error, you can safely assume that it discarded 
the private working copy and unlocked the file. Other users should now be able 
to check out and edit the file. 

Like the above examples, the following example comes from 
`EditFileEntryMVCActionCommand`. This class's `cancelFileEntriesCheckOut` method 
cancels the checkout of one or more files. This method gets `FileEntry` IDs from 
the request and then uses them to call `cancelCheckOut`: 

    protected void cancelFileEntriesCheckOut(ActionRequest actionRequest)
            throws Exception {

            long fileEntryId = ParamUtil.getLong(actionRequest, "fileEntryId");

            if (fileEntryId > 0) {
                    _dlAppService.cancelCheckOut(fileEntryId);
            }
            else {
                    long[] fileEntryIds = ParamUtil.getLongValues(
                            actionRequest, "rowIdsFileEntry");

                    for (long curFileEntryId : fileEntryIds) {
                            _dlAppService.cancelCheckOut(curFileEntryId);
                    }
            }
    }

## Related Topics [](id=related-topics)

[Checking Out and Editing Files](/discover/portal/-/knowledge_base/7-1/checking-out-and-editing-files)

[Updating Files](/develop/tutorials/-/knowledge_base/7-1/updating-files)
