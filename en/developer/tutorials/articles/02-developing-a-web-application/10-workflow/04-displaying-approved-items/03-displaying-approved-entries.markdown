---
header-id: displaying-approved-entries
---

# Displaying Approved Entries

[TOC levels=1-4]

<div class="learn-path-step">
    <p>Displaying Approved Workflow Items<br>Step 2 of 2</p>
</div>

The Guestbook application needs to be updated so that only guestbooks and
entries with a status of *approved* appear in the UI.

Change the getters used to retrieve both entities in the view layer.

1.  You need a new import, so first open
    `guestbook-web/src/main/resources/META-INF/resources/init.jsp` and add this
    line: 

    ```markup
    <%@ page import="com.liferay.portal.kernel.workflow.WorkflowConstants"%>
    ```

2.  Now open
    `guestbook-web/src/main/resources/META-INF/resources/guestbook/view.jsp`.
    Find the scriptlet that retrieves guestbooks:

        <%
            List<Guestbook> guestbooks = GuestbookLocalServiceUtil
                        .getGuestbooks(scopeGroupId);
                for (int i = 0; i < guestbooks.size(); i++) {
                    Guestbook curGuestbook = (Guestbook) guestbooks.get(i);
                    String cssClass = StringPool.BLANK;
                    if (curGuestbook.getGuestbookId() == guestbookId) {
                        cssClass = "active";
                    }
                    if (GuestbookPermission.contains(
                        permissionChecker, curGuestbook.getGuestbookId(), "VIEW")) {
                                            
        %>

    Change it so it calls the getter you added that takes workflow status into
    account. All you need to do is change this method call 

        List<Guestbook> guestbooks = GuestbookLocalServiceUtil
                    .getGuestbooks(scopeGroupId);

    to

    ```java
    List<Guestbook> guestbooks = GuestbookLocalServiceUtil
                .getGuestbooks(scopeGroupId, WorkflowConstants.STATUS_APPROVED);
    ```

    Save the file, and now only approved guestbooks are displayed in the
    Guestbook application. 
 
3.  Next, update the entry's UI in the same `view.jsp`.
    Find the tags that set the search container's total and its results:

        <liferay-ui:search-container total="<%=GuestbookEntryLocalServiceUtil.
                        getGuestbookEntriesCount()%>">
        <liferay-ui:search-container-results results=
                        "<%=GuestbookEntryLocalServiceUtil.getGuestbookEntries
                        (scopeGroupId.longValue(),
                        guestbookId, searchContainer.getStart(),
                        searchContainer.getEnd())%>" />

    Replace the getters to use the ones that take workflow status as a parameter,
    and pass `WorkflowConstants.STATUS_APPROVED` as the status. Here's what it looks
    like when you're finished:

    ```markup
    <liferay-ui:search-container total="<%=GuestbookEntryLocalServiceUtil.
                    getGuestbookEntriesCount(scopeGroupId.longValue(), 
                    guestbookId, WorkflowConstants.STATUS_APPROVED)%>">
    <liferay-ui:search-container-results results=
                    "<%=GuestbookEntryLocalServiceUtil.getGuestbookEntries(
                    scopeGroupId.longValue(), guestbookId, 
                    WorkflowConstants.STATUS_APPROVED, 
                    searchContainer.getStart(), searchContainer.getEnd())%>" />
    ```

Now only approved entries are displayed, and the search container's counter only
counts the approved entries. If you update the `getGuestbookEntries` call but
not the `getGuestbookEntriesCount` call, the count that's displayed includes
approved entries and entries with any other workflow status, and it won't match
the total that's displayed at the bottom of the search container.

![Figure 1: If you don't update the counter method to account for workflow status, it displays an incorrect count in the search container.](../../../../images/lp-workflow-entries-count.png)

Now Guestbooks and Guestbook Entries are now fully workflow enabled, to the
great relief of the Lunar Resort's site administrators. You've saved them a lot
of headaches dealing with inappropriate content, primarily submitted by visitors
from Mars. Those Martians really need some lessons in netiquette.
