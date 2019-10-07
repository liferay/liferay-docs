---
header-id: displaying-guestbook-status
---

# Displaying Guestbook Status

[TOC levels=1-4]

<div class="learn-path-step">
    <p>Displaying Approved Workflow Items<br>Step 1 of 2</p>
</div>

The Guestbook Admin application's main view currently has a search container
with two columns: the guestbook name and the guestbook actions button. 

![Figure 1: The Guestbook Admin's main view currently shows the name of the guestbook and its actions button.](../../../../images/lp-workflow-admin-nostatus.png)

Now you'll add a third column between the two existing ones: *Status*.

1.  Open
    `guestbook-web/src/main/reosurces/META-INF/resources/guestbook_admin/view.jsp`.

2.  Find the existing `search-container-column` definitions:

        <liferay-ui:search-container-column-text property="name" />

        <liferay-ui:search-container-column-jsp align="right"
            path="/guestbook_admin/guestbook_actions.jsp" />

3.  Put the following new column between the existing columns: 

    ```markup
    <liferay-ui:search-container-column-status property="status" />
    ```

Save the file and wait for the `web` module to redeploy. With the addition of
one line in the JSP, the Guestbook Admin application now displays the
guestbook's workflow status.

![Figure 2: The Guestbook Admin's main view, displaying the status of each guestbook.](../../../../images/lp-workflow-admin-status.png)

Now you can move on to the Guestbook application's view layer.
