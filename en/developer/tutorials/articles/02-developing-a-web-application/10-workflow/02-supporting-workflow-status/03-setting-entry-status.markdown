---
header-id: setting-the-entry-workflow-status
---

# Setting the Entry Workflow Status

[TOC levels=1-4]

<div class="learn-path-step">
    <p>Supporting Workflow at the Service Layer<br>Step 2 of 3</p>
</div>

Now you'll set the status fields, introduce entries to the workflow framework,
and add the `updateStatus` method to `GuestbookEntryLocalServiceImpl`. It works
the same as it did for guestbooks.

1.  Add the following lines in the `addGuestbookEntry` method, immediately after
    the existing setter methods (e.g., `entry.setMessage(message)`):

    ```java
    entry.setStatus(WorkflowConstants.STATUS_DRAFT);
    entry.setStatusByUserId(userId);
    entry.setStatusByUserName(user.getFullName());
    entry.setStatusDate(serviceContext.getModifiedDate(null));
    ```

2.  Still in the `addGuestbookEntry` method, place the following code right
    before the `return` statement:

    ```java
    WorkflowHandlerRegistryUtil.startWorkflowInstance(entry.getCompanyId(), 
				entry.getGroupId(), entry.getUserId(), GuestbookEntry.class.getName(), 
				entry.getPrimaryKey(), entry, serviceContext);
    ```

    The `startWorkflowInstance` call eventually directs the workflow processing to
    your `GuestbookEntryWorkflowHandler` class, which you'll create later. That
    class is responsible for making sure the entity is updated in the database (via
    an `updateStatus` method), but it's best practice to make persistence calls in
    the service layer. 

3.  Add a corresponding `updateStatus` method here in
    `GuestbookEntryLocalServiceImpl`. Add this method to the bottom of the
    class:

     ```java
     public GuestbookEntry updateStatus(long userId, long guestbookId, long entryId, int status,
			ServiceContext serviceContext) throws PortalException,
			SystemException {

		User user = userLocalService.getUser(userId);
		GuestbookEntry entry = getGuestbookEntry(entryId);

		entry.setStatus(status);
		entry.setStatusByUserId(userId);
		entry.setStatusByUserName(user.getFullName());
		entry.setStatusDate(new Date());

		guestbookEntryPersistence.update(entry);

		if (status == WorkflowConstants.STATUS_APPROVED) {

			assetEntryLocalService.updateVisible(GuestbookEntry.class.getName(),
					entryId, true);

		} else {

			assetEntryLocalService.updateVisible(GuestbookEntry.class.getName(),
					entryId, false);
		}

		return entry;
	}
    ```

4.  As with Guestbooks, you must add a call  to `deleteWorkflowInstanceLinks` in
    the entry's delete method to avoid leaving orphaned database entries in the
    `workflowinstancelinks` table. First add the following `<reference>` tag to
    `service.xml`, this time in the `entry` entity section, below the existing
    reference tags:

    ```xml
    <reference entity="WorkflowInstanceLink" package-path="com.liferay.portal" />
    ```

5.  Add the following method call to the `deleteGuestbookEntry` method in
    `GuestbookEntryLocalServiceImpl`, right before the `return` statement:

    ```java
    workflowInstanceLinkLocalService.deleteWorkflowInstanceLinks(
        entry.getCompanyId(), entry.getGroupId(),
        GuestbookEntry.class.getName(), entry.getEntryId());
    ```

6.  Organize imports (*[CTRL]+[SHIFT]+O*), save your work, run Service
    Builder, and refresh the Gradle project.

Now both entities support the status of the entity and can handle it as it
enters the workflow framework and as it returns from the workflow framework.
There's one more update to make in the local service implementation classes:
adding getter methods that take the status as a parameter. Later you'll use
these methods in the view layer so you can display only approved guestbooks and
entries. 
