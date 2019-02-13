# Checking for Permission in JSPs [](id=checking-for-permission-in-jsps)

<div class="learn-path-step">
    <p>Implementing Permissions<br>Step 4 of 4</p>
</div>

You've already seen how user interface components can be wrapped in permission
checks pretty easily. In this step, you'll implement the rest. 

## Checking Permissions in the UI [](id=checking-permissions-in-the-ui)

Recall that you want to restrict access to three areas in your application: 

- The guestbook tabs across the top of your application
- The Add Guestbook button
- The Add Entry button

First, you'll create the guestbook tabs and check permissions for them: 

1.  Open `/guestbookwebportlet/view.jsp` and find the scriptlet that gets the 
    `guestbookId` from the request. Just below this, add the following code: 

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

        <c:if test='<%= GuestbookPermission.contains(permissionChecker, guestbookId, "ADD_ENTRY") %>'>

3.  After this is the code that creates the `addEntryURL` and the Add Entry 
    button. After the `aui:button` tag and above the `</aui:button-row>` tag, 
    add the closing tag for the `<c:if>` statement: 

        </c:if>

    You've now implemented your permission check for the Add Entry button by 
    using JSTL tags. 

Next, you'll implement an `entry_actions.jsp` that's much like the one in the
Guestbook Admin portlet. This determines what options appear for logged in users
who can see the actions menu in the portlet. Just like before, you'll wrap each
`renderURL` in a `if` statement that checks the permissions against available
actions. To do this, follow these steps: 

1.  In `src/main/resources/META-INF/resources/guestbookwebportlet`, create a 
    file called `entry_actions.jsp`. 

2.  In this file, add the following code: 

        <%@include file="../init.jsp"%>

            <%
            String mvcPath = ParamUtil.getString(request, "mvcPath");

            ResultRow row = (ResultRow)request.getAttribute(WebKeys.SEARCH_CONTAINER_RESULT_ROW);

            Entry entry = (Entry)row.getObject(); 
            %>

            <liferay-ui:icon-menu>

                <portlet:renderURL var="viewEntryURL">
                    <portlet:param name="entryId" value="<%= String.valueOf(entry.getEntryId()) %>" />
                    <portlet:param name="mvcPath" value="/guestbookwebportlet/view_entry.jsp" />
                </portlet:renderURL>

                <liferay-ui:icon
                    message="View"
                    url="<%= viewEntryURL.toString() %>"
                />

                <c:if
                    test="<%= GuestbookEntryPermission.contains(permissionChecker, entry.getEntryId(), ActionKeys.UPDATE) %>">
                    <portlet:renderURL var="editURL">
                        <portlet:param name="entryId"
                            value="<%= String.valueOf(entry.getEntryId()) %>" />
                        <portlet:param name="mvcPath" value="/guestbookwebportlet/edit_entry.jsp" />
                    </portlet:renderURL>

                    <liferay-ui:icon image="edit" message="Edit"
                        url="<%=editURL.toString() %>" />
                </c:if>

                <c:if
                test="<%=GuestbookEntryPermission.contains(permissionChecker, entry.getEntryId(), ActionKeys.PERMISSIONS) %>">

                    <liferay-security:permissionsURL
                        modelResource="<%= Entry.class.getName() %>"
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

    This code defines action buttons updating, setting permissions on, and 
    deleting entities. Each button is protected by a permissions check. If the 
    current user can't perform the given action, the action doesn't appear. 

5.  Finally, in `view.jsp`, you must add the `entry_actions.jsp` as the last
    column in the Search Container. Find the line defining the Search Container
    row. It looks like this: 

        <liferay-ui:search-container-row
            className="com.liferay.docs.guestbook.model.Entry" modelVar="entry">

    Below that line are two columns. After the second column, add a third: 

        <liferay-ui:search-container-column-jsp path="/guestbookwebportlet/entry_actions.jsp" align="right" />

6.  Save all JSP files. 

Excellent! You've now implemented all the permissions checks for the Guestbook 
portlet. 

When testing the application, remember that any guestbook entries you created
without resources won't work with permissions. Add new guestbooks and entries to 
test your application with different users. Administrative users see all the 
buttons, regular users see the Add Entry button, and guests see no buttons at 
all (but can navigate). 

+$$$

**Note:** You may see an error where the Guestbook portlet doesn't appear at 
all, and you see this error in the log: 

    Someone may be trying to circumvent the permission checker. 

This is because any data you currently have in the Guestbook application doesn't 
have resources. In this case, you must drop and re-create your database. To do
this, find your Liferay Workspace on your file system (it should be inside your
Eclipse workspace). Inside the `bundles/data` folder is a `hypersonic` folder.
Shut down @product@, remove everything from this folder, and then restart. After
adding guestbook to a page, the portlet will work normally. 

$$$

Now see if you can do the same for the Guestbook Admin portlet. Don't worry if
you can't: at the end of this Learning Path is a link to the completed project
for you to examine. 

Great! The next step is to integrate search and indexing into your application.
This is a prerequisite for the much more powerful stuff to come. 
