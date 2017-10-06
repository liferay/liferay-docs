# Setting the Guestbook Status 

Before now, the status of all added guestbooks was automatically set to
approved. Open `GuestbookLocalServiceImpl` and set the status to `STATUS_DRAFT`
below the existing setter methods in the `addGuestbook` method:

    guestbook.setStatus(WorkflowConstants.STATUS_DRAFT);
    guestbook.setStatusByUserId(userId);
    guestbook.setStatusByUserName(user.getFullName());
    guestbook.setStatusDate(serviceContext.getModifiedDate(null));

This manually sets the status of the workflow as a draft in the `GB_Entry`
database table, and populates the other status fields with the proper current
values. At this point they're identical to the similarly named non-status
counterparts (like `setUserId` and `setStatusByUserId`), but they'll be updated
independently in the `updateStatus` method you write later.

Still in the `addGuestbook` method, place the following code right before the
`return` statement:

    WorkflowHandlerRegistryUtil.startWorkflowInstance(guestbook.getCompanyId(), 
				guestbook.getGroupId(), guestbook.getUserId(), Guestbook.class.getName(), 
				guestbook.getPrimaryKey(), guestbook, serviceContext);

Organize imports (*[CTRL]+[SHIFT]+O*) and save your work.

The `startWorkflowInstance` method is where your entity enters the workflow
framework, but you're not finished yet. Just like you wouldn't drop your child
off at college and then change your number and move to a new address, you're
not going to abandon your `Guestbook` entity (yet). 

Exert control over how the status fields are updated in the database.
Create an `updateStatus` method in `GuestbookLocalServiceImpl`, immediately
following the `deleteGuestbook` method. Here's the first half of it:

     public Guestbook updateStatus(long userId, long guestbookId, int status,
			ServiceContext serviceContext) throws PortalException,
			SystemException {

		User user = userLocalService.getUser(userId);
		Guestbook guestbook = getGuestbook(guestbookId);

		guestbook.setStatus(status);
		guestbook.setStatusByUserId(userId);
		guestbook.setStatusByUserName(user.getFullName());
		guestbook.setStatusDate(new Date());

		guestbookPersistence.update(guestbook);

If this method is being called, it's because your entity is returning from the
workflow framework, and it's time to update the status values in the database.
Set the status fields, then persist the updated entity to the database. Before
saving, finish the method:

		if (status == WorkflowConstants.STATUS_APPROVED) {

			assetEntryLocalService.updateVisible(Guestbook.class.getName(),
					guestbookId, true);

		} else {

			assetEntryLocalService.updateVisible(Guestbook.class.getName(),
					guestbookId, false);
		}

		return guestbook;
	}

This `if` statement determines the visibility of the asset based on its workflow
status. If it's approved, the `assetEntryLocalService.updateVisible` method sets
the guestbook in question to `true` so it can be displayed in the Asset
Publisher and in the search results. Otherwise (`else`) it sets the visibility
to `false` to ensure that unapproved guestbooks aren't displayed to users in the
Asset Publisher or the Search portlet.

Organize imports (*[CTRL]+[SHIFT]+O*) and save your work. Then run the
`buildService` Gradle task.

Now the guestbook entity's service layer populates the status fields in the
database and sends the entity into the workflow framework. Do the same thing for
guestbook entries next.

