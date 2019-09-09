# Modifying the Guestbook Admin Portlet to Display Workflow Status 

The Guestbook Admin portlet currently displays the `Guestbook`s for a site in a
Search Container, which is how all of Liferay's portlets work. Like those
portlets, you should include the workflow status as a column in the Search
Container.

![Figure 1: The Message Boards Admin portlet displays the workflow status of its entities.](../../../images/message-boards-admin.png)

Open `docroot/html/guestbookadmin/view.jsp` and find the section where both
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

You've added a new `<liferay-ui:search-container-column-text>` tag, defining a
*Status* column. The `<aui:workflow-status>` tag makes it easy to convert the
`int` value of `guestbook.getStatus()` into a `String` representing the status
label value (e.g., the int *02* becomes *Pending*). It's displayed with some
styling that matches that of Liferay's core portlets.

Note that you didn't change the `getter` method for one that only returns
`Guestbook`s marked as approved in the workflow. In the Guestbook Admin
Portlet, you're allowing all `Guestbook`s to be displayed with their workflow
status.

## Testing Workflow 

To test that workflow is working properly for both entities, log in to the
portal and go to *Admin* &rarr; *Control Panel* &rarr; *Configuration*
&rarr; *Workflow*. Click on *Default Configuration* and select 
*Single Approver* for both *Entry* and *Guestbook*.

![Figure 2: Enable workflow for entities in the Control Panel.](../../../images/test-workflow-1.png)

After saving your selections, navigate to the Guestbook Portlet and try to add a
new Guestbook and then a new Entry. If you are the portal administrator, you
will receive notifications that you have items waiting in the workflow. The
entities won't appear in the portlet until you assign them to yourself and then
approve them in the workflow. There are a couple of additional things to note:

- When workflow is disabled in the Control Panel, the `status` of the entities
  is automatically set as approved, and they appear as soon as they are added,
  just like before you enabled them for workflow.
- In the Guestbook Admin Portlet, the *Status* field is always displayed, even
  when workflow is disabled. You'll just see that added `Guestbook`s are always
  marked as *Approved*, with no review necessary and no workflow notifications
  sent.

![Figure 3: The Guestbook Admin portlet now displays the workflow status of each `Guestbook` in the site.](../../../images/guestbook-admin.png)

Your Guestbook Application now supports workflow!
