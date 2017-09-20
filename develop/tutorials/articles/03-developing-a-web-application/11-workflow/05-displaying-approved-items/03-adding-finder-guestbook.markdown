# Adding a Finder for the Guestbook

In the portlet's `view.jsp`, you can see the code that displays `Guestbook`s in
the `<aui:nav cssClass="nav-tabs">` section. Here's the call to the local
service implementation that gets the list of `Guestbook`s from the database:

     List<Guestbook> guestbooks = GuestbookLocalServiceUtil
                .getGuestbooks(scopeGroupId);

This call doesn't yet account for the workflow status of the `Guestbook`s.

Open `service.xml` again and add the following finder, below the existing
finder tags for the `Guestbook`:

    <finder name="G_S" return-type="Collection">
        <finder-column name="groupId"></finder-column>
        <finder-column name="status"></finder-column>
    </finder>

Run service builder. 

## Exposing the Guestbook's New Finder in the Service Layer 

Open `GuestbookLocalServiceImpl`. Find the current method with the signature
`public List<Guestbook> getGuestbooks(long groupId)`. Delete it and add this
one in its place:

	public List<Guestbook> getGuestbooks(long groupId, int status) throws SystemException {
		return guestbookPersistence.findByG_S(groupId, WorkflowConstants.STATUS_APPROVED);
	}

Run Service Builder. Leave the other getter methods alone, even though they
don't account for workflow status. The Guestbook Admin portlet should display
all of a site's `Guestbook`s, regardless of workflow status, and these getters
are used to populate its search container. What you will do later is add a
column to the Guestbook Admin portlet's Search Container to display guestbooks'
statuses. 
