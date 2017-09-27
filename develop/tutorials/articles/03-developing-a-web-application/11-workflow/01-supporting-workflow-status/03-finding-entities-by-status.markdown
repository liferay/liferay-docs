# 

The guestbook and entry's service implementations now support adding the status
fields to the guestbook and entry database tables. There's one more update to
make in the service layer, but to understand why, consider the view layer. When
you display entries in the Guestbook portlet, you must make sure not to display
entries that haven't been approved. Currently, the entry's view layer is using
the following code to get guestbooks for display:


		List<Guestbook> guestbooks = GuestbookLocalServiceUtil
					.getGuestbooks(scopeGroupId);

There's a problem: only approved guestbooks should be displayed in the
guestbook portlet, but the getter only takes the `scopeGroupId` as a parameter.

Likewise, unapproved entities must not be displayed, but the view layer
currently gets entries like this:

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

Run service builder (double-click `guestbook-service/build/buildService` in the Gradle Tasks pane of IDE). Service Builder generates finer methods in the persistence layer that take the specified fields (for example, `status`) as parameters. After, open the `GuestbookPersistence` class (in IDE click *CTRL+SHIFT+T* for the *Open Type* dialog and begin typing `GuestbookPersistence`).

Don't call the persistence layer directly in the application code. Instead
expose the new persistence methods in the service layer. 

Open `GuesbookLocalServiceImpl` and add this getter:

	public List<Guestbook> getGuestbooks(long groupId, int status)
		throws SystemException {
		
		return guestbookPersistence.findByG_S(
			groupId, WorkflowConstants.STATUS_APPROVED);
	}

This getter will be used to populate the guestbook portlet with approved
guestbooks. That's why you hardcode the workflow constant `STATUS_APPROVED` into the status parameter when calling the persistence method. Now open
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

<!-- DELETE THE UNUSED METHODS? The guestbook one is probably used in the
admin app so don't delete it. -->

The `getEntries` method will replace the existing method in the view layer,
ensuring that only approved entries are displayed.

The work here relates to the UI updates you'll make later. Next, implement
workflow handlers so that you can pass the entity to @product@'s workflow
framework.

<!-- MOVE TO GUESTBOOKSERVICEIMPL ARTICLE: Who's that raising their hand in the back of the class? Yes, what's your
question? What if workflow is disabled? Will your entities be marked as pending
in the workflow, and never be displayed? No. If workflow is disabled, @product@'s
workflow framework sets all entities as approved, so there's nothing to worry
about.-->
