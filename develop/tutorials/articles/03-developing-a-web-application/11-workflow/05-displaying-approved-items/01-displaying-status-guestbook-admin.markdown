# Displaying Guestbook Status

The Guestbook Admin application's main view currently has a search container
with two columns: the guestbook name and the guestbook actions dropdown. 

![Figure 1: The Guestbook Admin's main view currently shows the name of the guestbook and its actions button.](../../../../images/lp-workflow-admin-nostatus.png)

Add a third column, right between the two existing ones: call it *Status*.

Open

    guestbook-web/src/main/reosurces/META-INF/resources/guestbookadminportlet/view.jsp


Find the existing `search-container-column` definitions:

    <liferay-ui:search-container-column-text property="name" />

    <liferay-ui:search-container-column-jsp align="right"
        path="/guestbookadminportlet/guestbook_actions.jsp" />

Put the following new column between the existing columns: 

    <liferay-ui:search-container-column-status property="status" />

Save the file and wait for the `web` module to redeploy. With the addition of
one line in the JSP, the Guestbook Admin application now displays the
guestbook's workflow status.

![Figure 2: The Guestbook Admin's main view, displaying the status of each guestbook.](../../../../images/lp-workflow-admin-status.png)

Now move on to the Guestbook application's view layer.
