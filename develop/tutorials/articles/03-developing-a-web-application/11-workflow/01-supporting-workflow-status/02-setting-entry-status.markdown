# Setting the Entry Workflow Status

Set the status fields, introduce entries to the workflow framework, and add the
`updateStatus` method to the `EntryLocalServiceImpl`. It works the same as it
did for guestbooks.

Add the following line in the `addGuestbookEntry` method, immediately following
the current setter methods (e.g., `entry.setMessage(message)`):

    entry.setStatus(WorkflowConstants.STATUS_DRAFT);
    entry.setStatusByUserId(userId);
    entry.setStatusByUserName(user.getFullName());
    entry.setStatusDate(serviceContext.getModifiedDate(null));

Still in the `addGuestbookEntry` method, place the following code right before
the `return` statement:

    WorkflowHandlerRegistryUtil.startWorkflowInstance(entry.getCompanyId(), 
				entry.getGroupId(), entry.getUserId(), Entry.class.getName(), 
				entry.getPrimaryKey(), entry, serviceContext);

The call to `startWorkflowInstance` detects whether workflow is installed and
enabled. If it isn't, the added entity is automatically marked as approved. The
`startWorkflowInstance` also calls your `EntryWorkflowHandler` class, which
you'll create later. Make the service layer update when they return from the
workflow framework by adding the following method to the bottom of
`EntryLocalServiceImpl`:

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

Now both entities support the status of the entity. There's one more update to
make in the local service implementation classes: adding getter methods that
take the status as a parameter. Later you'll use these methods in the view layer
so you can display only approved guestbooks and entries. 
