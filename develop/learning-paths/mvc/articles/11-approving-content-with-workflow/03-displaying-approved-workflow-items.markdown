# Displaying Approved Workflow Items

To display only entites approved in the workflow, you'll add a new `finder`,
then a `getter` that uses it in your service layer. The new methods will serve
the purpose of getting only entities with the proper workflow status, and then
displaying those entities can be displayed properly in the portlet's search
container. Service Builder makes this easy.

## Adding a Finder for the Entry

If you look in `docroot/html/guestbook/view.jsp`, you'll see that entries are
retrieved from the database for display in the Search Container using the
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

The `getter` methods used here are those responsible for populating the Search
Container. They need to include the workflow status as a parameter, in addition
to the current list of parameters. `EntryLocalServiceImpl` needs these methods:

-`getEntriesByG_G_S`
-`getEntriesCountByG_G_S`

First, you need a `finder` method to expose. Open `service.xml` and add this
tag below the current list of finders for the `Entry` entity:

    <finder name="G_G_S" return-type="Collection">
        <finder-column name="groupId"></finder-column>
        <finder-column name="guestbookId"></finder-column>
        <finder-column name="status"></finder-column>
    </finder>

## Exposing the Entry's New Finder in the Service Layer

It's all well and good to have a finder, but the portlet's service layer needs
to expose it to be of any use. Open `EntryLocalServiceImpl` and delete the
following `getter` methods: 


	public List<Entry> getEntries(long groupId, long guestbookId, int start,
			int end) throws SystemException {
		return entryPersistence.findByG_G(groupId, guestbookId, start, end);
	}
	
	public int getEntriesCount(long groupId, long guestbookId) throws SystemException {
		return entryPersistence.countByG_G(groupId, guestbookId, WorkflowConstants.STATUS_APPROVED);
	}

Replace the getters you just deleted with these:

	public List<Entry> getEntries(long groupId, long guestbookId, int status, int start,
			int end) throws SystemException {
		return entryPersistence.findByG_G_S(groupId, guestbookId, WorkflowConstants.STATUS_APPROVED, start, end);
	}
	
	public int getEntriesCount(long groupId, long guestbookId, int status) throws SystemException {
		return entryPersistence.countByG_G_S(groupId, guestbookId, WorkflowConstants.STATUS_APPROVED);
	}

## Updating the View Layer to Display Approved Entries

Back in the `view.jsp`, find the following tag, which currently retrieves the
entries from the database for display in the Search Container.

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
with the updated methods we created in the local service implementation. 

Open `init.jsp` and add the following import:

    <%@ page import="com.liferay.portal.kernel.workflow.WorkflowConstants" %>

The `Entry` entity is now fully enabled for workflow. Finish up your work by doing the same thing for `Guestbook`s.

## Adding a Finder for the Guestbook

In the portlet's `view.jsp`, you can see the code that displays guestbooks in
the `<aui:nav cssClass="nav-tabs">` setion. Here's the call to the local
service implementation that gets the list of `Guestbook`s from the database:

     List<Guestbook> guestbooks = GuestbookLocalServiceUtil
                .getGuestbooks(scopeGroupId);

This call doesn't yet account for workflow status of the `Guestbook`s.

Open `service.xml` again and add the following finder, below the existing
finder tags:

    <finder name="G_S" return-type="Collection">
        <finder-column name="groupId"></finder-column>
        <finder-column name="status"></finder-column>
    </finder>

Run service builder. 

## Exposing the Guestbook's New Finder in the Service Layer

Open `GuestbookLocalServiceImpl`. Replace the current method of the
signature `public List<Guestbook> getGuestbooks(long groupId)` with this one:

	public List<Guestbook> getGuestbooks(long groupId, int status) throws SystemException {
		return guestbookPersistence.findByG_S(groupId, WorkflowConstants.STATUS_APPROVED);
	}

Now only `Guestbook`s that have been marked as approved will be reteived from the database. 

## Updating the View Layer to Display Approved Guestbooks

Open `view.jsp`, and find this method call:

     List<Guestbook> guestbooks = GuestbookLocalServiceUtil
                .getGuestbooks(scopeGroupId);

replace it with this one:

     List<Guestbook> guestbooks = GuestbookLocalServiceUtil
                .getGuestbooks(scopeGroupId, WorkflowConstants.STATUS_APPROVED);

All you did here was add the parameter that marks a `Guestbook`
as approved in the workflow.

Now both entities are enabled for workflow.

## Testing Workflow

To test that workflow is working properly for both entities, log in ot the
portal and go to *Admin* &rarr; *Control Panel* &rarr; *Configuration*
&rarr; *Workflow*. Click on *Defualt Configuration*, and select 
*Single Approver* for both *Entry* and Guestbook*.

![Figure 1: Enable workflow for entities in the Control Panel.](../../images/test-workflow-1.png)

After Saving your selections, navigate t a page with the Guestbook Portlet
added to it, and try to add a new Guestbook, and then a new entity. If you
are the portal administrator, you should receive notiications that you have
items waiting in the workflow. The entities should not appear in the portlet
until you assign them to yourself, then approve them in the workflow. There
are a couple of additional things to note:

-When workflow is disabled in the Control Panel, the `status` of the entities
is automatically set as approved, and will appear as soon as they are added, just like before you enabled them for workflow.
<!--I'm assuming this, need to confirm in the database or by debugging-->
-You didn't change the way guestbooks are displayed in the *Guestbook Admin*
portlet. This is a design decision, so that administrators will see all the
guestbooks in the database, regardless of their workflow status.
