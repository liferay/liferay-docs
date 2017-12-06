# Setting the Entry Workflow Status [](id=setting-the-entry-workflow-status)

<div class="learn-path-step">
    <p>Supporting Workflow at the Service Layer<br>Step 2 of 3</p>
</div>

Now you'll set the status fields, introduce entries to the workflow framework,
and add the `updateStatus` method to `EntryLocalServiceImpl`. It works the
same as it did for guestbooks.

Add the following lines in the `addEntry` method, immediately after the
current setter methods (e.g., `entry.setMessage(message)`):

    entry.setStatus(WorkflowConstants.STATUS_DRAFT);
    entry.setStatusByUserId(userId);
    entry.setStatusByUserName(user.getFullName());
    entry.setStatusDate(serviceContext.getModifiedDate(null));

Still in the `addEntry` method, place the following code right before
the `return` statement:

    WorkflowHandlerRegistryUtil.startWorkflowInstance(entry.getCompanyId(), 
				entry.getGroupId(), entry.getUserId(), Entry.class.getName(), 
				entry.getPrimaryKey(), entry, serviceContext);

The `startWorkflowInstance` call eventually directs the workflow processing to
your `EntryWorkflowHandler` class, which you'll create later. That class is
responsible for making sure the entity is updated in the database (via an
`updateStatus` method), but it's best practice to make persistence calls in the
service layer. Thus you'll need a corresponding `updateStatus` method here in
`EntryLocalServiceImpl`. Add this method to the bottom of the class:

     public Entry updateStatus(long userId, long guestbookId, long entryId, int status,
			ServiceContext serviceContext) throws PortalException,
			SystemException {

		User user = userLocalService.getUser(userId);
		Entry entry = getEntry(entryId);

		entry.setStatus(status);
		entry.setStatusByUserId(userId);
		entry.setStatusByUserName(user.getFullName());
		entry.setStatusDate(new Date());

		entryPersistence.update(entry);

		if (status == WorkflowConstants.STATUS_APPROVED) {

			assetEntryLocalService.updateVisible(Entry.class.getName(),
					entryId, true);

		} else {

			assetEntryLocalService.updateVisible(Entry.class.getName(),
					entryId, false);
		}

		return entry;
	}

Organize imports (*[CTRL]+[SHIFT]+O*), save your work, and run Service Builder.

As with Guestbooks, you must add a call  to `deleteWorkflowInstanceLinks` in the
entry's delete method to avoid leaving orphaned database entries in the
`workflowinstancelinks` table. First add the following `<reference>` tag to
`service.xml`, this time in the `entry` entity section, below the existing
reference tags:

    <reference entity="WorkflowInstanceLink" package-path="com.liferay.portal" />

Save, run Service Builder, and then add the following method call to the
`deleteEntry` method in `EntryLocalServiceImpl`, right before the `return`
statement:

    workflowInstanceLinkLocalService.deleteWorkflowInstanceLinks(
        entry.getCompanyId(), entry.getGroupId(),
        Entry.class.getName(), entry.getEntryId());

Now both entities support the status of the entity and can handle it as it
enters the workflow framework and as it returns from the workflow framework.
There's one more update to make in the local service implementation classes:
adding getter methods that take the status as a parameter. Later you'll use
these methods in the view layer so you can display only approved guestbooks and
entries. 
