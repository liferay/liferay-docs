# Enabling Workflow at the Service Layer

In the learning path on assets, you learned that asset enabled entities are
added to the `AssetEntry` table. There's no special table for workflow
entities, but there are some additional database columns in the entity table
(e.g., `GB_Entry`)  that allow you to keep track of workflow status. The
necessary fields include *status*, *statusByUserName*, *statusByUserId*, and
*statusDate*. Add the following columns to `docroot/WEB-INF/service.xml`, in
both the `Guestbook` and `Entry` entity *Audit fields* sections:

    <column name="status" type="int" />
    <column name="statusByUserId" type="long" />
    <column name="statusByUserName" type="String" />
    <column name="statusDate" type="Date" />

Once you run service builder, the database tables for both entities contain the
proper fields for workflow. However, if you add an `Entry`, you'll see that the
new fields are not populated. The service implementation classes need some
modifications to set these fields. 


## Setting the Workflow Fields in `EntryLocalServiceImpl`

Open `EntryLocalServiceImpl` and add the following line in the
`addGuestbookEntry` method, immediately following the current setter methods
(e.g., `entry.setMessage(message)`):

    entry.setStatus(WorkflowConstants.STATUS_DRAFT);

This manually sets the status of the workflow as a draft; in the `GB_Entry`
database table, you'll now see the `status` field of an added Entry with the
value `2`. But you still haven't set the rest of the values.

Still in the `addGuestbookEntry` method, place the following code right before the `return` statement:

    WorkflowHandlerRegistryUtil.startWorkflowInstance(entry.getCompanyId(), 
				entry.getGroupId(), entry.getUserId(), Entry.class.getName(), 
				entry.getPrimaryKey(), entry, serviceContext);

Add these imports:

    import com.liferay.portal.kernel.workflow.WorkflowConstants;
    import com.liferay.portal.kernel.workflow.WorkflowHandlerRegistryUtil;

The call to `startWorkflowInstance` will detect whether workflow is installed
and enabled. If it isn't, the added entity is automatically marked as approved.
The `startWorkflowInstance` will call your custom `EntryWorkflowHandler` class,
which you'll create later in this learning path. The service layer needs to be
given the ability to update the workfflow status fields you added to
`service.xml`. For this purpose, add the following method to the bottom of
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

Make sure you run service builder.

The `updateStatus` method is responsible for setting the status fields, then
persisting the information to the database. The `if` block checks the workflow
status of the entity and does one of two things:

- If the entity has an approved status, it is marked as visible, and can be
  displayed in the Asset Publisher portlet.
- If the entity is not approved, it is marked as not visible, and can't be
  displayed in the Asset Publisher portlet.

## Setting the Woirkflow Fields in `GuestbookLocalServiceImpl`

<!--Do the same thing here -->

Wait a minute. The `updateStatus` methods created for each entity can set the
workflow fields appropriately, but how will they be called? You need to create
`-WorkflowHandler` classes to interact with the workflow API, and call your
`updateStatus` methods. 

