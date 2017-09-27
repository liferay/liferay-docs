# Updating the View Layer to Display Approved Entries 

Back in the `view.jsp`, find the following tag, which currently retrieves the
entries from the database for display in the search container:

	<liferay-ui:search-container-results
		results="<%=EntryLocalServiceUtil.getEntries(scopeGroupId,
						guestbook.getGuestbookId(), searchContainer.getStart(),
						searchContainer.getEnd())%>"
		total="<%=EntryLocalServiceUtil.getEntriesCount(scopeGroupId,
						guestbook.getGuestbookId())%>" />

Replace the above tag with this one:

	<liferay-ui:search-container-results
		results="<%=EntryLocalServiceUtil.getEntries(scopeGroupId,
						guestbook.getGuestbookId(), WorkflowConstants.STATUS_APPROVED, searchContainer.getStart(),
						searchContainer.getEnd())%>"
		total="<%=EntryLocalServiceUtil.getEntriesCount(scopeGroupId,
						guestbook.getGuestbookId(), WorkflowConstants.STATUS_APPROVED)%>" />

As you'd expect, the calls to `getEntries` and `getEntriesCount` now call the
updated methods you created in the local service implementation, so that the
search container is only populated with approved entries. 

Open `init.jsp` and add the following import:

    <%@ page import="com.liferay.portal.kernel.workflow.WorkflowConstants" %>

The `Entry` entity is now fully enabled for workflow. Do the same thing for
`Guestbook`s next.

