# Displaying Guestbook Status

The Guestbook Admin application's main view currently has a search container
with two columns: the guestbook name and the guestbook actions dropdown. 

![Figure 1: The Guestbook Admin's main view vurrently shows the name of the guestbook and its actions button.](../../../../images/lp-workflow-admin-nostatus.png)

Add a third column, right between the two existing ones: call it *Status*.

Open

    guestbook-web/src/main/reosurces/META-INF/resources/guestbookadminportlet/view.jsp


Find the existing column definitions:

    <liferay-ui:search-container-column-text property="name" />

    <liferay-ui:search-container-column-jsp align="right"
        path="/guestbookadminportlet/guestbook_actions.jsp" />

Put the following new column in between them: 

    <liferay-ui:search-container-column-status property="status" />

With the addition of line in the JSP, your Guestbook Admin application now
displays guestbook workflow status.

![Figure 2: The Guestbook Admin's main view, displaying the status of each guestbook.](../../../../images/lp-workflow-admin-status.png)

