# Adding a Finder for the Entry 

If you look in `docroot/html/guestbook/view.jsp`, you'll see that entries are
retrieved from the database for display in the search container using the
following code:

    <liferay-ui:search-container>
        <liferay-ui:search-container-results
            results="<%=EntryLocalServiceUtil.getEntries(scopeGroupId,
                            guestbook.getGuestbookId(), searchContainer.getStart(),
                            searchContainer.getEnd())%>"
            total="<%=EntryLocalServiceUtil.getEntriesCount(scopeGroupId,
                            guestbook.getGuestbookId())%>" />
        ...
        </liferay-ui:search-container-row>
        <liferay-ui:search-iterator />
    </liferay-ui:search-container>

The getter methods used are those responsible for populating the Search
Container. They need to include the workflow status as an additional parameter. 

First, you need a finder method to expose. Open `service.xml` and add this
tag below the current list of finders for the `Entry` entity:

    <finder name="G_G_S" return-type="Collection">
        <finder-column name="groupId"></finder-column>
        <finder-column name="guestbookId"></finder-column>
        <finder-column name="status"></finder-column>
    </finder>

Run Service Builder.

## Exposing the Entry's New Finder in the Service Layer 

It's all well and good to have a finder, but the portlet's service layer needs
to expose it to be of any use. Open `EntryLocalServiceImpl` and delete these
getter methods: 

	public List<Entry> getEntries(long groupId, long guestbookId, int start,
			int end) throws SystemException {
		return entryPersistence.findByG_G(groupId, guestbookId, start, end);
	}
	
	public int getEntriesCount(long groupId, long guestbookId) throws SystemException {
		return entryPersistence.countByG_G(groupId, guestbookId, WorkflowConstants.STATUS_APPROVED);
	}

Add these methods:

	public List<Entry> getEntries(long groupId, long guestbookId, int status, int start,
			int end) throws SystemException {
		return entryPersistence.findByG_G_S(groupId, guestbookId, WorkflowConstants.STATUS_APPROVED, 
            start, end);
	}
	
	public int getEntriesCount(long groupId, long guestbookId, int status) throws SystemException {
		return entryPersistence.countByG_G_S(groupId, guestbookId, WorkflowConstants.STATUS_APPROVED);
	}

Since there's nowhere in the portlet where an `Entry`'s workflow status should
be ignored, it's a best practice to replace the current getters with appropriate
ones.
