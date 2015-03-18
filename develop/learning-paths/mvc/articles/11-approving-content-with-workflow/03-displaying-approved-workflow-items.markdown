# Displaying Approved Workflow Items

To display only entities approved in the workflow, you'll add a new *finder*,
then a *getter* that uses it in your service layer. The new methods will serve
the purpose of getting only entities with the proper workflow status, and then
displaying those entities in the portlet's search
container. Service Builder makes this easy.

## Adding a Finder for the Entry

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
Container. They need to include the workflow status as a parameter, in addition
to their current parameters. 

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
be ignored, it's a best practice to simply replace the current getters with
appropriate ones.

## Updating the View Layer to Display Approved Entries

Back in the `view.jsp`, find the following tag, which currently retrieves the
entries from the database for display in the search container.

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

As you'd expect, the calls to `getEntries` and `getEntriesCount` are replaced
with the updated methods we created in the local service implementation, so
that the search container is only populated with approved entries. 

Open `init.jsp` and add the following import:

    <%@ page import="com.liferay.portal.kernel.workflow.WorkflowConstants" %>

The `Entry` entity is now fully enabled for workflow. Do the same thing for
`Guestbook`s next.

## Adding a Finder for the Guestbook

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
one in it's place:

	public List<Guestbook> getGuestbooks(long groupId, int status) throws SystemException {
		return guestbookPersistence.findByG_S(groupId, WorkflowConstants.STATUS_APPROVED);
	}

Run Service Builder. Leave the other getter methods alone, even though they
don't account for workflow status. The Guestbook Admin portlet should display
all of a site's `Guestbook`s, regardless of workflow status, and these getters
are used to populate it's search container.

## Updating the View Layer to Display Approved Guestbooks

Open `view.jsp`, and find this method call:

     List<Guestbook> guestbooks = GuestbookLocalServiceUtil
                .getGuestbooks(scopeGroupId);

Replace it with this one:

     List<Guestbook> guestbooks = GuestbookLocalServiceUtil
                .getGuestbooks(scopeGroupId, WorkflowConstants.STATUS_APPROVED);

All you did here was add the parameter that marks a `Guestbook`
as approved in the workflow.

Now both entities are enabled for workflow, and the view layer of the Guestbook
Portlet is modified accordingly. But what about the Guestbook Admin Portlet
that's accessed through the portal's Site Administration section? What should
the strategy be there?

## Modifying the Guestbook Admin Portlet to Display Workflow Status

The Guestbook Admin portlet currently displays the `Guestbook`s for a site in a
search container, which is consistent with Liferay's core portlets, such as the
Message Boards Admin portlet. Like those portlets, you should include the
workflow status as a column in the search container.

![Figure 1: The Message Boards Admin portlet displays the workflow status of its entities.](../../images/message-boards-admin.png)

Open `docroot/html/guestbookadmin/view.jsp`, and find the section where both
columns of the search container are populated:

		<liferay-ui:search-container-column-text property="name" href="<%= viewGuestbook %>" />

		<liferay-ui:search-container-column-jsp
	            path="/html/guestbookadmin/guestbook_actions.jsp"
	            align="right" />

You need a column in between the *Name* column and the column containing the
*Actions* button, so replace the above columns with these ones:

		<liferay-ui:search-container-column-text property="name" href="<%= viewGuestbook %>" />

		<liferay-ui:search-container-column-text name="status" >
            <aui:workflow-status showIcon="<%= false %>" showLabel="<%= false %>"
                status="<%= guestbook.getStatus() %>" />
        </liferay-ui:search-container-column-text>

		<liferay-ui:search-container-column-jsp
	            path="/html/guestbookadmin/guestbook_actions.jsp"
	            align="right" />

There's a new `<liferay-ui:search-container-column-text>` tag, defining a
*Status* column. The `<aui:workflow-status>` tag makes it easy to convert the
`int` value of `guestbook.getStatus()` into a `String` representing the status
label value (e.g., the int *02* becomes *Pending*). It's displayed with some
styling that matches that of Liferay's core portlet's.

Note that you didn't change the `getter` method for one that only returns
`Guestbook`s marked as approved in the workflow. In the Guestbook Admin
Portlet, you're allowing all `Guestbook`s to be displayed with their workflow
status.

## Testing Workflow

To test that workflow is working properly for both entities, log in to the
portal and go to *Admin* &rarr; *Control Panel* &rarr; *Configuration*
&rarr; *Workflow*. Click on *Defualt Configuration*, and select 
*Single Approver* for both *Entry* and *Guestbook*.

![Figure 2: Enable workflow for entities in the Control Panel.](../../images/test-workflow-1.png)

After saving your selections, navigate to a page with the Guestbook Portlet
added to it, and try to add a new Guestbook, and then a new Entry. If you
are the portal administrator, you should receive notifications that you have
items waiting in the workflow. The entities should not appear in the portlet
until you assign them to yourself, then approve them in the workflow. There
are a couple of additional things to note:

- When workflow is disabled in the Control Panel, the `status` of the entities
is automatically set as approved, and they will appear as soon as they are
added, just like before you enabled them for workflow.
- In the Guestbook Admin Portlet, the *Status* field is always displayed, even
when workflow is disabled. You'll just see that added `Guestbook`s are always
marked as *Approved*, with no review necessary and no workflow notifications
sent.

![Figure 3: The Guestbook Admin portlet now displays the workflow status of each `Guestbook` in the site.](../../images/guestbook-admin.png)

The entities of the Guestbook Application now support workflow!
