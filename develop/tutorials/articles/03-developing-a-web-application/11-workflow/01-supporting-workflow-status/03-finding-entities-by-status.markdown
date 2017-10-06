# Retrieving Guestbooks and Entries by Status 

The service implementation for both entities now supports adding the status
fields to the database tables. There's one more update to make in the service
layer, but to understand why, you actually need to think about the view layer.
When you display entries in the Guestbook portlet, you must make sure not to
display entries that haven't been approved. Currently, the entry's view layer is
using the following code to get guestbooks for display:

		List<Guestbook> guestbooks = GuestbookLocalServiceUtil
					.getGuestbooks(scopeGroupId);

There's a problem: the getter only takes the `scopeGroupId` as a parameter, so
there's no way to get guestbooks by their status.

Likewise, unapproved entries must not be displayed, but the view layer currently
gets entries like this:

        <liferay-ui:search-container total="<%=EntryLocalServiceUtil.getEntriesCount()%>">
        <liferay-ui:search-container-results
            results="<%=EntryLocalServiceUtil.getEntries(scopeGroupId.longValue(),
                            guestbookId, searchContainer.getStart(),
                            searchContainer.getEnd())%>" />

Right now, neither guestbooks nor entries have a getter that takes the `status`
field as a parameter, but that's what we need. Thankfully, Service Builder makes
it easy.

Open the `guestbook-service` module's `service.xml` file. 

For the guestbook entity, add this finder:

    <finder name="G_S" return-type="Collection">
        <finder-column name="groupId"></finder-column>
        <finder-column name="status"></finder-column>
    </finder>

For the entry entity, add this finder:

    <finder name="G_G_S" return-type="Collection">
        <finder-column name="groupId" />
        <finder-column name="guestbookId" />
        <finder-column name="status" />
    </finder>

Run service builder (double-click `guestbook-service/build/buildService` in the
Gradle Tasks pane of IDE). Service Builder generates finder methods in the
persistence layer that take the specified fields (for example, `status`) as
parameters.

Don't call the persistence layer directly in the application code. Instead
expose the new persistence methods in the service layer. 

Open `GuesbookLocalServiceImpl` and add this getter:

	public List<Guestbook> getGuestbooks(long groupId, int status)
		throws SystemException {
		
		return guestbookPersistence.findByG_S(
			groupId, WorkflowConstants.STATUS_APPROVED);
	}

This getter will be used to populate the guestbook portlet with approved
guestbooks. That's why you hardcode the workflow constant `STATUS_APPROVED` into
the status parameter when calling the persistence method. Now open
`EntryLocalServiceImpl` and add these two getters:

	public List<Entry> getEntries(
		long groupId, long guestbookId, int status, int start, int end)
		throws SystemException {

		return entryPersistence.findByG_G_S(
			groupId, guestbookId, WorkflowConstants.STATUS_APPROVED);
	}

	public int getEntriesCount(
		long groupId, long guestbookId, int status)
		throws SystemException {

		return entryPersistence.countByG_G_S(
			groupId, guestbookId, WorkflowConstants.STATUS_APPROVED);
	}

The `getEntries` method will replace the existing method in the view layer,
ensuring that only approved entries are displayed. The `getEntriesCount` method
will replace the current one from the view layer. This will ensure that only the
approved entries are counted so that the search container displays the correct
number of entries in a particular guestbook.

The work here relates to the UI updates you'll make later. Next, implement
workflow handlers so that you can call the `updateStatus` service method when
the entity returns from the workflow framework.
