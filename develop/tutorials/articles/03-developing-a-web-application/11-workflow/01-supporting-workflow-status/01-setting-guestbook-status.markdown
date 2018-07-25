# Setting the Guestbook Status [](id=setting-the-guestbook-status)

<div class="learn-path-step">
    <p>Supporting Workflow at the Service Layer<br>Step 1 of 3</p>
</div>

Before now, you set the status of all added guestbooks to approved in the
service layer. Now you'll set it to draft and pass it to the workflow framework. 

1.  From `guestbook-service`, open `GuestbookLocalServiceImpl` and add the
    status fields below the existing setter methods in the `addGuestbook`
    method:

        guestbook.setStatus(WorkflowConstants.STATUS_DRAFT);
        guestbook.setStatusByUserId(userId);
        guestbook.setStatusByUserName(user.getFullName());
        guestbook.setStatusDate(serviceContext.getModifiedDate(null));

    This manually populates the status fields and sets the workflow status as
    a draft in the `GB_Entry` database table. At this point they're identical to
    the similarly named non-status counterparts (like `setUserId` and
    `setStatusByUserId`), but they'll be updated independently in the
    `updateStatus` method you write later.

2.  Still in the `addGuestbook` method, place the following code right before
    the `return` statement:

        WorkflowHandlerRegistryUtil.startWorkflowInstance(guestbook.getCompanyId(), 
                    guestbook.getGroupId(), guestbook.getUserId(), Guestbook.class.getName(), 
                    guestbook.getPrimaryKey(), guestbook, serviceContext);

    The call to `startWorkflowInstance` detects whether workflow is installed
    and enabled. If it isn't, the added entity is automatically marked as
    approved. The `startWorkflowInstance` call also calls your
    `GuestbookWorkflowHandler` class, which you'll create later. 

3.  Organize imports (*[CTRL]+[SHIFT]+O*), and save your work.

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

If this method is called, it's because your entity is returning from the
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

There's one more update to make in the `deleteGuestbook` method. When deleting,
you must clean up the workflow system's database tables to avoid leaving
orphaned entries when the backing entity is deleted. Before making the method
call, open `service.xml` and add the following tag below the existing
`<reference>` tags:

    <reference entity="WorkflowInstanceLink" package-path="com.liferay.portal" />

Save and run Service Builder. It injects the `WorkflowInstanceLinkLocalService`
service into a protected variable in `GuesbookLocalServiceBaseImpl`. Since
`GuestbookLocalServiceImpl` extends the base class, you can use it directly.
Back in `GuesbookLocalServiceImpl`, find the `deleteGuestbook` method and put
this method call right before the `return` statement:

    workflowInstanceLinkLocalService.deleteWorkflowInstanceLinks(
        guestbook.getCompanyId(), guestbook.getGroupId(),
        Guestbook.class.getName(), guestbook.getGuestbookId());

Save the file and run *Refresh Gradle Project*. Now the guestbook entity's 
service layer populates the status fields in the database, sends the entity 
into the workflow framework, and cleans up when it's deleted. You'll do the same thing for guestbook entries next.

