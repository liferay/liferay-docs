# Creating Action Buttons [](id=creating-action-buttons)

Your final task is to create the user interface elements that give users access
to the functions you just created in both your controller and your service
layer. A common way to do this in Liferay, which makes your application operate
like the other ones that ship with Liferay, is to create action buttons. 

![Figure 1: A common Liferay user interface element is the action button.](../../images/entity-action-buttons.png)

Because action buttons are implemented using a tag, they're very easy to create.
You'll use the building blocks you've already used to test for permissions to
perform the tasks and to create portlet URLs, and then you'll use a new tag to
create the buttons themselves. 

Ready to get started? 

## Using A Separate JSP for Actions [](id=using-a-separate-jsp-for-actions)

The first thing you'll do is update `init.jsp` to contain the imports and
declarations you'll need. During regular development, you'd have this file open
next to the JSP you're working on, and you'd add the declarations there as you
need them. Since this is a learning path, we already know the declarations
you'll need, so we can supply them to you ahead of time. 

1.  Open `init.jsp`. In the taglib declarations at the top of the file, add the
    following new declaration: 

        <%@ taglib uri="http://liferay.com/tld/security" prefix="liferay-security" %>

2.  To the imports section, add the following new imports: 

        <%@ page import="com.liferay.portal.kernel.dao.search.SearchEntry" %>
        <%@ page import="com.liferay.portal.kernel.dao.search.ResultRow" %>
        <%@ page import="com.liferay.portal.security.permission.ActionKeys" %>
        <%@ page import="com.liferay.docs.guestbook.model.Entry" %>
        <%@ page import="com.liferay.docs.guestbook.util.WebKeys" %>

3.  Save the file. 

You've just added a new tag library declaration for the Liferay security tags.
This lets you use the permissions tag that forms the basis for one of your
action buttons. The others are required by a scriptlet you're about to write. 

The best practice is to use a separate JSP for your action buttons. This puts
all the code in one place and is easy to use in Search Container, where you can
just include the same JSP over and over with different parameters. 

1.  In `docroot/html/guestbook`, create a new JSP called
    `guestbook_actions.jsp`. 

2.  Place the following code in the file: 

        <%@include file="/html/init.jsp"%>

        <%
        String mvcPath = ParamUtil.getString(request, "mvcPath");

        ResultRow row = (ResultRow)request.getAttribute(WebKeys.SEARCH_CONTAINER_RESULT_ROW);

        Entry entry = (Entry)row.getObject(); 
        %>

    First, you include `init.jsp`, and then you have a small scriptlet. The
    first line retrieves the path of the current JSP. The second retrieves the
    result from the current search container row. The third casts that row object
    into an `Entry` object. Notice the call to `WebKeys`? The constant you're
    using here is inherited from the parent class. 

3.  Next, you'll add the first entry, *Edit*, in your action button: 

        <liferay-ui:icon-menu>

            <c:if
                test="<%= EntryPermission.contains(permissionChecker, entry.getEntryId(), ActionKeys.UPDATE) %>">
                <portlet:renderURL var="editURL">
                    <portlet:param name="entryId"
                        value="<%= String.valueOf(entry.getEntryId()) %>" />
                    <portlet:param name="mvcPath" value="/html/guestbook/edit_entry.jsp" />
                </portlet:renderURL>

                <liferay-ui:icon image="edit" message="Edit"
                    url="<% =editURL.toString() %>" />
            </c:if>

    The first tag creates the action button. Next is a permissions check just
    like the ones you used in the last learning path. The button only shows if the
    current user has permission to update the current entity. Next, you create a
    portlet URL. Since this is the edit button, this is a render URL that sends the
    user to the `edit_entry.jsp`. It includes a parameter for the `entryId` so the
    entity can be retrieved and then edited on that page. Finally, another tag is
    used to create the actual action button link using the URL you just created. 

4.  Next, add the entry for the permissions button: 

        <c:if
            test="<%=EntryPermission.contains(permissionChecker, entry.getEntryId(), ActionKeys.PERMISSIONS) %>">

            <liferay-security:permissionsURL
                modelResource="<%= Entry.class.getName() %>"
                modelResourceDescription="<%= entry.getMessage() %>"
                resourcePrimKey="<%= String.valueOf(entry.getEntryId()) %>"
                var="permissionsURL" />

            <liferay-ui:icon image="permissions" url="<%= permissionsURL %>" />

        </c:if>

    Again, you see the button is wrapped in a permissions check. This time,
    you're checking for the permission to change permissions. If the user has this
    permission, you can show this action. The tag library declaration you added to
    `init.jsp` enables you to create this action. This tag shows Liferay's
    permissions user interface, allowing your users to modify the permissions on
    specific guestbook entries. 

    After this is another `<liferay-ui:icon />` tag that creates the actual
    link. 

5.  Finally, add the entry for the delete button: 

            <c:if
                test="<%=EntryPermission.contains(permissionChecker, entry.getEntryId(), ActionKeys.DELETE) %>">

                <portlet:actionURL name="deleteEntry" var="deleteURL">
                    <portlet:param name="entryId"
                        value="<%= String.valueOf(entry.getEntryId()) %>" />
                    <portlet:param name="guestbookId"
                        value="<%= String.valueOf(entry.getGuestbookId()) %>" />
                </portlet:actionURL>

                <liferay-ui:icon-delete url="<%=deleteURL.toString() %>" />
            </c:if>

        </liferay-ui:icon-menu>

    The permission check that wraps this action is, of course the *delete*
    permission. The portlet URL you create here is an action URL that calls your
    new `deleteEntry()` method in your controller. You supply the `entryId` for
    the current entry, which is the one you want to delete. The `guestbookId`
    parameter is used so that the correct guestbook is displayed the portlet
    after the user has deleted an entry from a guestbook.

    The tag used to create the action link is the `<liferay-ui:icon-delete />`
    tag. This tag differs from the regular `<liferay-ui:icon />` tag in that when
    the user clicks it, a message pops up asking him or her to confirm the action.
    This helps ensure that entities aren't deleted accidentally, by requiring
    confirmation first. Finally, you close the icon menu, completing the action
    button definition. 

6.  Save the file. 

You might think you're finished, but you actually have one more JSP to modify:
the `edit_entry.jsp` file. You need to retro-fit this JSP so it can handle
editing entries as well as adding them. 

## Using the Same JSP for Editing and Adding [](id=using-the-same-jsp-for-editing-and-adding)

Because Liferay's tag libraries are so robust, there are only a few changes you
need to make to `edit_entry.jsp` to make it handle editing entries. 

1.  Open `edit_entry.jsp`. 

2.  Below the action URL definition, add the following scriptlet: 

        <%
        long entryId = ParamUtil.getLong(renderRequest, "entryId");

        Entry entry = null;

        if (entryId > 0) {
            
            entry = EntryLocalServiceUtil.getEntry(entryId);
            
        }

        long guestbookId = ParamUtil.getLong(renderRequest, "guestbookId");
        %>

    This scriptlet gets the `entryId` out of the request (remember, you just
    created the URL that calls this page from your action button, and you included
    the `entryId` as a parameter). It then checks its value: if it's `0`, which
    `ParamUtil` supplies if it doesn't find a value for this parameter, nothing
    happens. If, however, it has a value, the corresponding entry is retrieved and
    placed into the `entry` variable. This scriplet also gets the `guestbookId`
    out of the request. If a new guestbook entry is being created, the
    `guestbookId` from the request is used. If a guestbook entry is being
    edited, the entry's `guestbookId` is used instead.

3.  Add the following tag inside of the `<aui:form>` tag: 

        <aui:model-context bean="<%= entry %>" model="<%= Entry.class %>" />

    This tag sets a particular bean (in this case our `Entry` bean) as the
    context for an AUI form. This causes the AUI tags that define the form
    fields to show the value from the bean. 

4.  Finally, add one field to hold the `entryId`, so your update functionality
    knows which entry to update when the form is submitted: 

        <aui:input name="entryId" type="hidden" />

    You keep the field hidden, of course, because users don't need to know or
    worry about the primary key from the database, and it should definitely not be
    editable. 

    Also add a hidden input field for the `guestbookId` parameter that we
    defined in the scriptlet:

        <aui:input name="guestbookId" type="hidden" value='<%= entry == null ? guestbookId : entry.getGuestbookId() %>'/>

5.  Save the file. 

Congratulations! You've now implemented custom actions for your entities. Users
can now manage their full cycle: adding, editing, modifying permissions, and if
they so choose, deleting guestbook entries. 

Next, you'll want to consider integrating your application with the rest of
Liferay's services. For example, Liferay's social networking API lets you
publish guestbook entries as *activities* that can appear on users' profile
pages. Or you might want to add tags, categories, or even comments to guestbook
entries. To make any of these features work, you must asset-enable your
application. The next learning path covers that. 

## Next Steps [](id=next-steps)

[Adding a Portlet to the Control Panel](/develop/learning-paths/-/knowledge_base/6-2/adding-a-portlet-to-the-control-panel)
