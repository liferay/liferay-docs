# Permissions in JSPs

Liferay's user interface can be wrapped in permission checks pretty easily. In
this last step of this Learning Path, you'll learn how. 

First go to the `init.jsp` in your `guestbook-web` project.

1. Add the following imports to the file:

        <%@ page import="com.liferay.docs.guestbook.service.permission.GuestbookModelPermission" %>
        <%@ page import="com.liferay.docs.guestbook.service.permission.GuestbookPermission" %>
        <%@ page import="com.liferay.docs.guestbook.service.permission.EntryPermission" %>

Recognize those? They're the permissions helper classes you just created. Now
it's time to implement your permission checks. 

## Checking Permissions in the UI

Now that it comes to implementing permissions, you'll want to review exactly
what it is you want to protect. In the first part, you identified three areas: 

- The tabs across the top of your application

- The Add Guestbook button

- The Add Entry button

The first area you want to tackle, then, is the tabs across the top. Since this
is already implemented as a scriptlet, this is where you'll add the scriptlet
version of the permission check. 

1.  Open `/guestbookwebportlet/view.jsp` and find the scriptlet that uses the 
    `for` loop to process the list of `Guestbook` entities into tabs. Just 
    below the `if` statement that checks to see if this is the active 
    guestbook, and above the `%>` that closes the scriplet add another `if` 
    statement: 

        if (GuestbookPermission.contains(
            permissionChecker, curGuestbook.getGuestbookId(), "VIEW")) {

2.  Then, after the `<portlet:renderURL />` and `<aui:nav-item />` tags, add
    an additonal `}` to the closing scriptlet to close both `if` statements.
    The final version should look like this: 

        <%
              }
            }
        %>

You've now implemented your first permission check. As you can see, it's pretty
straightforward to do, thanks to the static methods in your helper classes. The
code above shows the tab if the current user has the `VIEW` permission for the
guestbook and omits it if the user does not. 

Next, you'll add permission checks to the Add Guestbook and Add Entry buttons. 

1.  Scroll down in the file to the line that reads `<aui:button-row
    cssClass="guestbook-buttons">`. Just below this line, add the following
    permissions check:

        <c:if test='<%= GuestbookModelPermission.contains(permissionChecker, scopeGroupId, "ADD_GUESTBOOK") %>'>

2.  After this is the code that creates the URL and the button. After the
    `<aui:button onClick="<%=addGuestbookURL.toString()%>" value="Add   Guestbook" />`
    tag, add the closing tag for the `<c:if>` statement: 

        </c:if>

You've now implemented your permission check for the Add Guestbook button using
JSTL tags. Adding the check for the Add Entry button is very similar: 

1.  Just after your closing `</c:if>` statement, add another one that checks for
    the `ADD_ENTRY` permission: 

        <c:if test='<%= GuestbookPermission.contains(permissionChecker, guestbookId, "ADD_ENTRY") %>'>

2.  After this is the code that creates the URL and the button. After the button
    tag, and above the `</aui:button-row>` tagadd the closing tag for the 
    `<c:if>` statement: 

        </c:if>

The last check we need to do is in the `entry_actions.jsp`. This will determine
what options appear for logged in users who can see the actions menu in the 
portlet. Just like before, you'll wrap each `renderURL` in a "if" statement
that checks the permissions against available actions.

1. Open `entry_actions.jsp`.

2. Just above the `<portlet:renderURL var="editURL">` add a tag to check for
    `UPDATE` permissions:
           
        <c:if test="<%= EntryPermission.contains(permissionChecker,
             entry.getEntryId(), ActionKeys.UPDATE) %>">

3. Close it off with a `</c:if>` just below `...url="<%=editURL.toString() %>" />`

4. Below that add this block, which checks for permission to change permissions
    and displays the *Permissions* link if the user has permission to use it.
    
        <c:if test="<%=EntryPermission.contains(permissionChecker, entry.getEntryId(),
            ActionKeys.PERMISSIONS) %>">
        
            <liferay-security:permissionsURL modelResource="<%= Entry.class.getName() %>"
              modelResourceDescription="<%= entry.getMessage() %>" resourcePrimKey="<%=
              String.valueOf(entry.getEntryId()) %>" var="permissionsURL" />
            <liferay-ui:icon image="permissions" url="<%= permissionsURL %>" />
        </c:if>

5. Add this block that checks for `DELETE` permissions above the 
    `<portlet:actionURL name="deleteEntry" var="deleteURL">` tag:
    
        <c:if test="<%=EntryPermission.contains(permissionChecker, entry.getEntryId(),
           ActionKeys.DELETE) %>">

6. And finally, close it off with a `</c:if>` just below the 
    `<liferay-ui:icon-delete url="<%=deleteURL.toString() %>" />` tag. 

Excellent! You've now implemented all the permission checks you'd defined for
your application. Save the file and test your application with different users.
Administrative users see all the buttons, regular users see the Add Entry
button, and guests see no buttons at all (but can navigate).
