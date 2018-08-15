# Updating Permissions to Support Staging

The guestbook's current permission handlers do not account for staging. For
example, the current configuration would display the *Add Guestbook* and *Add
Entry* buttons on the live site. These options should only be available on the
staged site when staging is enabled.

First, edit the Guestbook app's permissions helper classes to provide permission
checks to leverage when staging is enabled.

1.  Open the `GuestbookModelPermission` class residing in the
    `guestbook-service`'s `com.liferay.docs.guestbook.service.permission`
    package. Replace the `contains(...)` method with the following code snippet:

        public static boolean contains(
            PermissionChecker permissionChecker, long groupId, String actionId) {

            return contains(
                permissionChecker, RESOURCE_NAME, GuestbookPortletKeys.GUESTBOOK,
                groupId, actionId);
        }

        public static boolean contains(
            PermissionChecker permissionChecker, String name, long classPK,
            String actionId) {

            Group group = GroupLocalServiceUtil.fetchGroup(classPK);

            if ((group != null) && group.isStagingGroup()) {
                classPK = group.getLiveGroupId();
            }

            return permissionChecker.hasPermission(
                classPK, name, classPK, actionId);
        }

        public static boolean contains(
            PermissionChecker permissionChecker, String name, String portletId,
            long classPK, String actionId) {

            Boolean hasPermission = StagingPermissionUtil.hasPermission(
                permissionChecker, classPK, name, classPK, portletId, actionId);

            if (hasPermission != null) {
                return hasPermission.booleanValue();
            }

            return contains(permissionChecker, name, classPK, actionId);
        }

    This adds two additional `contains` methods. The original `contains` method
    now redirects to a new method that instantiates a `hasPermission` field
    using the staging permission checker. If it returns as a non-null value
    (i.e., the app is rendered on the staged site), the appropriate boolean
    value is returned based on the user's permissions. If the `hasPermission`
    field is null (i.e., the app is rendered on the live site), the third
    `contains` method is invoked, which calls the permission checker with group
    info from the live site.

    Now you'll edit the permissions helper classes for your two entities. These
    are for the model/resource permissions, so you supply the primary key of
    the entity you're checking permissions for  (e.g., `guestbookId`).

2.  Open the `EntryPermission` class residing in the `guestbook-service`'s
    `com.liferay.docs.guestbook.service.permission` package. In the
    `contains(PermissionChecker, Entry, String)` method, add the following
    logic:

        Boolean hasPermission = StagingPermissionUtil.hasPermission(
            permissionChecker, entry.getGroupId(), Entry.class.getName(),
            entry.getEntryId(), GuestbookPortletKeys.GUESTBOOK, actionId);

            if (hasPermission != null) {
                return hasPermission.booleanValue();
            }

    If the new `hasPermission` field is returned as a non-null value (i.e., the
    app is rendered on the staged site), the appropriate boolean value is
    returned based on the staging context.

3.  Open the `GuestbookPermission` class and add the following code in the
    `contains(PermissionChecker, Guestbook, String)` method:

        Boolean hasPermission = StagingPermissionUtil.hasPermission(
            permissionChecker, guestbook.getGroupId(),
            Guestbook.class.getName(), guestbook.getGuestbookId(),
            GuestbookPortletKeys.GUESTBOOK, actionId);

        if (hasPermission != null) {
            return hasPermission.booleanValue();
        }

    This is similar to the logic added for the entry's permissions. 

Your Guestbook app can now display the proper functionality depending on its
staging context (i.e., staged site or live site).

The Guestbook's admin portlet requires additional modifications in its JSPs to
correctly display options based on staging context.

1.  In your `guestbook-web` module, open the
    `src/main/resources/META-INF/resources/guestbookadminportlet/guestbook_actions.jsp`
    file. Directly after the first `<liferay-ui:icon-menu>` tag, add the
    following `if` statement:

        <c:if test='<%= GuestbookPermission.contains(permissionChecker, guestbook.getGuestbookId(), ActionKeys.UPDATE) %>'>

    Close the `if` statement directly after the `<liferay-ui:icon />` tag. When
    completed, the `if` statement should look like this:

        <c:if test='<%= GuestbookPermission.contains(permissionChecker, guestbook.getGuestbookId(), ActionKeys.UPDATE) %>'>
            <portlet:renderURL var="editURL">
                <portlet:param name="guestbookId"
                    value="<%=String.valueOf(guestbook.getGuestbookId())%>" />
                <portlet:param name="mvcPath"
                    value="/guestbookadminportlet/edit_guestbook.jsp" />
            </portlet:renderURL>

            <liferay-ui:icon image="edit" message="Edit"
                url="<%=editURL.toString()%>" />
        </c:if>

    The new `if` statement hides the Guestbook's editing functionality if the
    user does not have the permissions to edit a guestbook. Since you added new
    staging permissions, those are verified too.

2.  Below the `</c:if>` statement, add another `if` statement:

        <c:if test='<%= GuestbookPermission.contains(permissionChecker, guestbook.getGuestbookId(), ActionKeys.DELETE) %>'>

    Close the `if` statement directly after the `<liferay-ui:icon-delete />`
    tag.When completed, the `if` statement should look like this:

        <c:if test='<%= GuestbookPermission.contains(permissionChecker, guestbook.getGuestbookId(), ActionKeys.DELETE) %>'>
            <portlet:actionURL name="deleteGuestbook" var="deleteURL">
                <portlet:param name="guestbookId"
                    value="<%=String.valueOf(guestbook.getGuestbookId())%>" />
            </portlet:actionURL>

            <liferay-ui:icon-delete url="<%=deleteURL.toString()%>" />
        </c:if>

    Similar to the previous `if` statement, this one hides the Guestbook's
    deletion functionality if the user does not have the permissions to delete a
    guestbook.

3.  Open the `.../guestbookadminportlet/view.jsp` file and wrap the
    `<aui:button-row />` tag with the following `if` statement:

        <c:if test='<%= GuestbookModelPermission.contains(permissionChecker, scopeGroupId, "ADD_GUESTBOOK") %>'>
            ...
        </c:if>

    When finished, it should look like this:

        <c:if test='<%= GuestbookModelPermission.contains(permissionChecker, scopeGroupId, "ADD_GUESTBOOK") %>'>
            <aui:button-row cssClass="guestbook-admin-buttons">
                <portlet:renderURL var="addGuestbookURL">
                    <portlet:param name="mvcPath"
                        value="/guestbookadminportlet/edit_guestbook.jsp" />
                    <portlet:param name="redirect" value="<%="currentURL"%>" />
                </portlet:renderURL>

                <aui:button onClick="<%= addGuestbookURL.toString() %>"
                    value="Add Guestbook" />
            </aui:button-row>
        </c:if>

    This `if` statement hides the Guestbook's *Add Guestbook* button if the user
    does not have the permissions to create a new guestbook.

Great! You've updated your Guestbook app's permissions to support Staging!
