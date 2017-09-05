# Setting the Workflow Fields in GuestbookLocalServiceImpl 

You need to repeat the above steps to enable the service layer of the
`Guestbook` entity to set the necessary status fields needed for workflow.
Open `GuestbookLocalServiceImpl` and add the following line in the
`addGuestbook` method, immediately after the current setter methods (e.g.,
`guestbook.setExpandoBridgeAttributes(serviceContext)`):

    guestbook.setStatus(WorkflowConstants.STATUS_DRAFT);

Still in the `addGuestbookEntry` method, place the following code right before
the `return` statement:

    WorkflowHandlerRegistryUtil.startWorkflowInstance(guestbook.getCompanyId(), 
				guestbook.getGroupId(), guestbook.getUserId(), Guestbook.class.getName(), 
				guestbook.getPrimaryKey(), guestbook, serviceContext);

Add these imports:

    import com.liferay.portal.kernel.workflow.WorkflowConstants;
    import com.liferay.portal.kernel.workflow.WorkflowHandlerRegistryUtil;

The `startWorkflowInstance` calls the `GuestbookWorkflowHandler` class that you
created in the previous step. 

The service layer must be able to update the workflow status fields you added to
`Guestbook` entity's database table. Add the following method to the bottom of
`GuestbookLocalServiceImpl`:

     public Guestbook updateStatus(long userId, long guestbookId, int status,
			ServiceContext serviceContext) throws PortalException,
			SystemException {

		User user = userLocalService.getUser(userId);
		Guestbook guestbook = getGuestbook(guestbookId);

		guestbook.setStatus(status);
		guestbook.setStatusByUserId(userId);
		guestbook.setStatusByUserName(user.getFullName());
		guestbook.setStatusDate(new Date());

		entryPersistence.update(entry);

		if (status == WorkflowConstants.STATUS_APPROVED) {

			assetEntryLocalService.updateVisible(Guestbook.class.getName(),
					guestbookId, true);

		} else {

			assetEntryLocalService.updateVisible(Guestbook.class.getName(),
					guestbookId, false);
		}

		return guestbook;
	}

Run Service Builder after saving the changes you made.

Now you're almost done. The status fields can be set appropriately and
persisted to the database. If you test the workflow by adding an `Entry` to one
of the Guestbooks, your portal's administrative user receives a
notification for reviewing the submission. The entity, however, is still visible
in the portlet's search container! Why even bother having a review process if
the entity gets published anyway? Taking workflow status into account while
displaying entities in the Guestbook Portlet is the final task of this Learning
Path. 
