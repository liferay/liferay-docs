---
header-id: checking-for-permission-in-jsps
---

# Checking for Permission in JSPs

[TOC levels=1-4]

<div class="learn-path-step row">
    <p id="stepTitle">Implementing Permissions</p><p>Step 5 of 5</p>
</div>

You've already seen how user interface components can be wrapped in permission
checks pretty easily. In this step, you'll implement the rest. 

## Checking Permissions in the UI

Recall that you want to restrict access to three areas in your application: 

- The guestbook tabs across the top of your application
- The Add Guestbook button
- The Add Entry button

First, you'll check permissions for the guestbook tabs: 

1.  Open `/guestbook/view.jsp` and find the scriptlet that gets the 
    `guestbookId` from the request. Below this are the `<aui-nav>` tags that
    generate the tabs. Remove those tags and all the code between them. In its
    place, add the following code, which is the same thing with the addition of
    permission checks: 

    ```markup
    <aui:nav cssClass="nav-tabs">

        <%
            List<Guestbook> guestbooks = GuestbookLocalServiceUtil.getGuestbooks(scopeGroupId);

                for (int i = 0; i < guestbooks.size(); i++) {

                    Guestbook curGuestbook = guestbooks.get(i);
                    String cssClass = StringPool.BLANK;

                    if (curGuestbook.getGuestbookId() == guestbookId) {
                        cssClass = "active";
                    }

                    if (GuestbookModelPermission.contains(
                        permissionChecker, curGuestbook.getGuestbookId(), "VIEW")) {
                                            
        %>

        <portlet:renderURL var="viewPageURL">
            <portlet:param name="mvcPath" value="/guestbookwebportlet/view.jsp" />
            <portlet:param name="guestbookId"
                value="<%=String.valueOf(curGuestbook.getGuestbookId())%>" />
        </portlet:renderURL>

            
        <aui:nav-item cssClass="<%=cssClass%>" href="<%=viewPageURL%>"
            label="<%=HtmlUtil.escape(curGuestbook.getName())%>" />

        <%  
                    }
                
                }
        %>

    </aui:nav>
    ```

    This code gets a list of guestbooks from the database, iterates through 
    them, checks the permission for each against the current user's Roles, and 
    adds the guestbooks the user can access to a list of tabs. 

    You've now implemented your first permission check. As you can see, it's 
    relatively straightforward thanks to the static methods in your helper 
    classes. The code above shows the tab only if the current user has the 
    `VIEW` permission for the guestbook. 

    Next, you'll add permission checks to the Add Entry button. 

2.  Scroll down to the line that reads 
    `<aui:button-row cssClass="guestbook-buttons">`. Just below this line, add 
    the following line of code to check for the `ADD_ENTRY` permission: 

    ```markup
    <c:if test='<%= GuestbookPermission.contains(permissionChecker, scopeGroupId, "ADD_ENTRY") %>'>
    ```

3.  After this is the code that creates the `addEntryURL` and the Add Entry 
    button. After the `aui:button` tag and above the `</aui:button-row>` tag, 
    add the closing tag for the `<c:if>` statement: 

    ```markup
    </c:if>
    ```

    You've now implemented your permission check for the Add Entry button by 
    using JSTL tags. 

4.  Save the file. 

Next, you'll add permission checking to `entry_actions.jsp` to determine what
options appear for logged in users who can see the actions menu in the portlet.
Just like before, you'll wrap each `renderURL` in an `if` statement that checks
the permissions against available actions. To do this, follow these steps: 

1.  Open `src/main/resources/META-INF/resources/guestbook/entry_actions.jsp`. 

2.  Remove all the code from this file and replace it with what's below: 

    ```markup
    <%@include file="../init.jsp"%>

        <%
        String mvcPath = ParamUtil.getString(request, "mvcPath");

        ResultRow row = (ResultRow)request.getAttribute(WebKeys.SEARCH_CONTAINER_RESULT_ROW);

        GuestbookEntry entry = (GuestbookEntry)row.getObject(); 
        %>

        <liferay-ui:icon-menu>

            <c:if
                test="<%= GuestbookEntryPermission.contains(permissionChecker, entry.getEntryId(), ActionKeys.UPDATE) %>">
                <portlet:renderURL var="editURL">
                    <portlet:param name="entryId"
                        value="<%= String.valueOf(entry.getEntryId()) %>" />
                    <portlet:param name="mvcPath" value="/guestbook/edit_entry.jsp" />
                </portlet:renderURL>

                <liferay-ui:icon image="edit" message="Edit"
                    url="<%=editURL.toString() %>" />
            </c:if>

            <c:if
            test="<%=GuestbookEntryPermission.contains(permissionChecker, entry.getEntryId(), ActionKeys.PERMISSIONS) %>">

                <liferay-security:permissionsURL
                    modelResource="<%= GuestbookEntry.class.getName() %>"
                    modelResourceDescription="<%= entry.getMessage() %>"
                    resourcePrimKey="<%= String.valueOf(entry.getEntryId()) %>"
                    var="permissionsURL" />
            
                <liferay-ui:icon image="permissions" url="<%= permissionsURL %>" />

            </c:if>

            <c:if
                test="<%=GuestbookEntryPermission.contains(permissionChecker, entry.getEntryId(), ActionKeys.DELETE) %>">

                <portlet:actionURL name="deleteEntry" var="deleteURL">
                    <portlet:param name="entryId"
                        value="<%= String.valueOf(entry.getEntryId()) %>" />
                    <portlet:param name="guestbookId"
                        value="<%= String.valueOf(entry.getGuestbookId()) %>" />
                </portlet:actionURL>

                <liferay-ui:icon-delete url="<%=deleteURL.toString() %>" />
            </c:if>

        </liferay-ui:icon-menu>
        ```

3.  Save the file. 

This code updates each button with a permissions check. If the current user
can't perform the given action, the action doesn't appear. 

Excellent! You've now implemented all the permissions checks for the Guestbook 
portlet. 

## Testing the Application

Before testing the application, you must reset your database, because guestbook
entries you created without resources won't work with permissions. 

1.  If your server is running, shut it down. 

2.  Find your Liferay Workspace on your file system (it should be inside your
    Eclipse workspace). Inside the `bundles/data` folder is a `hypersonic`
    folder. 

3.  Remove everything from the `hypersonic` folder.

4.  Restart your server. 

Add new guestbooks and entries to test your application with different users.
Administrative users see all the buttons, regular users see the Add Entry
button, and guests see no buttons at all (but can navigate). 

Now see if you can do the same for the Guestbook Admin portlet. Don't worry if
you can't: at the end of this Learning Path is a link to the completed project
for you to examine. 

Great! The next step is to integrate search and indexing into your application.
This is a prerequisite for the much more powerful stuff to come. 
