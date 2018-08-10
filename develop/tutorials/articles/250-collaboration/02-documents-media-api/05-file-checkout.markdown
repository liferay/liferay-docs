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


