# Setting the Workflow Fields in EntryLocalServiceImpl 

Open `EntryLocalServiceImpl` and add the following line in the
`addGuestbookEntry` method, immediately following the current setter methods
(e.g., `entry.setMessage(message)`):

    entry.setStatus(WorkflowConstants.STATUS_DRAFT);

This manually sets the status of the workflow as a draft; in the `GB_Entry`
database table, you'll now see the `status` field of an added `Entry` with the
value `2`. But you still haven't set the rest of the values.

Still in the `addGuestbookEntry` method, place the following code right before
the `return` statement:

    WorkflowHandlerRegistryUtil.startWorkflowInstance(entry.getCompanyId(), 
				entry.getGroupId(), entry.getUserId(), Entry.class.getName(), 
				entry.getPrimaryKey(), entry, serviceContext);

Add these imports:

    import com.liferay.portal.kernel.workflow.WorkflowConstants;
    import com.liferay.portal.kernel.workflow.WorkflowHandlerRegistryUtil;

The call to `startWorkflowInstance` detects whether workflow is installed and
enabled. If it isn't, the added entity is automatically marked as approved. The
`startWorkflowInstance` also calls your `EntryWorkflowHandler` class, which
you'll create later in this Learning Path. The service layer must also update
the workflow status fields you added to `service.xml`. For this purpose, add the
following method to the bottom of `EntryLocalServiceImpl`:

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

Run Service Builder.

The `updateStatus` method is responsible for setting the status fields, then
persisting the information to the database. The `if` block checks the entity's
workflow status and does one of two things:

- If the entity has an approved status, it is marked as visible and can be
  displayed in the Asset Publisher portlet.
- If the entity is not approved, it is marked as not visible and can't be
  displayed in the Asset Publisher portlet.

