# Permissions in JSPs
Liferay's user interface can be wrapped in permission checks pretty easily. In
this last step of this Learning Path, you'll learn how. 

As you've probably noticed, Liferay's coding style differs somewhat from the
mainstream, borrowing instead practices from other open source platforms like
PHP. For this reason, you see scriptlets in Liferay's JSPs, both in the product
itself and in various plugins you can check out from Github or download from
Marketplace. You can mitigate this somewhat by using the standard JSTL tags with
Liferay. We'll show you both the scriptlet method and the JSTL method, and that
way you can decide which style you prefer. 


Now the only thing you have left to do is declare the tag library in your
`init.jsp` file so it is made available to all your other JSPs: 

    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

Now you're ready to use JSTL in your Liferay JSPs. While you're here, though,
add some more imports that you'll need later: 

    <%@ page import="com.liferay.docs.guestbook.service.permission.GuestbookModelPermission" %>
    <%@ page import="com.liferay.docs.guestbook.service.permission.GuestbookPermission" %>
    <%@ page import="com.liferay.docs.guestbook.service.permission.EntryPermission" %>

Recognize those? They're the permissions helper classes you just created. Now
it's time to implement your permission checks. 

## Checking Permissions in the UI [](id=checking-permissions-in-the-ui)

Now that it comes to implementing permissions, you'll want to review exactly
what it is you want to protect. In the first part, you identified three areas: 

- The tabs across the top of your application

- The Add Guestbook button

- The Add Entry button

The first area you want to tackle, then, is the tabs across the top. Since this
is already implemented as a scriptlet, this is where you'll add the scriptlet
version of the permission check. 

1.  Open `view.jsp` and find the scriptlet that uses the `for` loop to process
    the list of `Guestbook` entities into tabs. Just below the `if` statement
    that checks to see if this is the active guestbook, add another `if`
    statement: 

		if (GuestbookPermission.contains(
			permissionChecker, curGuestbook.getGuestbookId(), "VIEW")) {

2.  Then, after the `<portlet:renderURL />` and `<aui:nav-item />` tags, modify
    the closing scriptlet to close both `if` statements: 

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
    button tag, add the closing tag for the `<c:if>` statement: 

        </c:if>

You've now implemented your permission check for the Add Guestbook button using
JSTL tags. Adding the check for the Add Entry button is very similar: 

1.  Just after your closing `</c:if>` statement, add another one that checks for
    the `ADD_ENTRY` permission: 

        <c:if test='<%= GuestbookPermission.contains(permissionChecker, guestbookId, "ADD_ENTRY") %>'>

2.  After this is the code that creates the URL and the button. After the button
    tag, add the closing tag for the `<c:if>` statement: 

        </c:if>

Excellent! You've now implemented all the permission checks you'd defined for
your application. Save the file and test your application with different users.
Administrative users see all the buttons, regular users see the Add Entry
button, and guests see no buttons at all (but can navigate). 

Ready to move on? The next Learning Path covers action buttons. 

view.jsp

    <c:if test='<%= GuestbookModelPermission.contains(permissionChecker, scopeGroupId, "ADD_GUESTBOOK") %>'>

	</c:if>

	<c:if test='<%= GuestbookPermission.contains(permissionChecker, guestbookId, "ADD_ENTRY") %>'>
	
	</c:if>

guestbook_actions.jsp (both)

	<c:if
	        test="<%= EntryPermission.contains(permissionChecker, entry.getEntryId(), ActionKeys.UPDATE) %>">
    </c:if>
    
	<c:if
	    test="<%=EntryPermission.contains(permissionChecker, entry.getEntryId(), ActionKeys.PERMISSIONS) %>">
    </c:if>
	
	<c:if
	        test="<%=EntryPermission.contains(permissionChecker, entry.getEntryId(), ActionKeys.DELETE) %>">_
    </c:if>
	