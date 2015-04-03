# Checking Permissions [](id=checking-permissions)

When implementing permissions for a custom portlet, your last step is to ensure
that the configured permissions are enforced. You can achieve this by adding
permission checks to your application. For example, your business layer can
check for permission before deleting a resource, or your user interface can hide
a button that adds an entity (e.g. a guestbook or guestbook entry) if the user
doesn't have permission.

+$$$

**Note:** The Guestbook project is used in this tutorial to illustrate how to
run permissions checks. You can find it in the Liferay Docs repository here:
[Liferay Guestbook project](https://github.com/liferay/liferay-docs/tree/6.2.x/develop/learning-paths/mvc/code/learning-sdk/portlets/guestbook-portlet).

$$$

## Implementing Permissions Checking [](id=implementing-permissions-checking)

Once you've defined permissions in your
`docroot/WEB-INF/src/resource-actions/default.xml` file, you need to implement
permission checking. In the Guestbook portlet, one supported action is
`ADD_GUESTBOOK`. There are two places in the source code where you might want to
check for this permission: in the user interface and in the business logic. The
user interface is implemented in JSP files, while the business logic is
implemented at the portlet layer and service layer. For the JSP files, you want
to wrap certain elements in permission checks so they only appear for users with
permission to interact with those elements. For example, the presence of the Add
Guestbook button is contingent on whether the user has permission to add a
guestbook. Here's how the `ADD_GUESTBOOK` action is wrapped in a permission
check in `docroot/html/guestbook/view.jsp`: 

    <c:if test='<%= GuestbookModelPermission.contains(permissionChecker, scopeGroupId, "ADD_GUESTBOOK") %>'>
        // Display the Add Guestbook button
    </c:if>

The second place to check for the `ADD_GUESTBOOK` permission is in the business
logic. The `GuestbookPortlet.addGuestbook(...)` method invokes the
`GuestbookServiceUtil.addGuestbook(...)` method. Therefore, permission checking
only needs to be implemented at the service layer. The following code is used to
implement permission checking in the `GuestbookServiceImpl` class:

    GuestbookModelPermission.check(getPermissionChecker(),
        serviceContext.getScopeGroupId(), ActionKeys.ADD_GUESTBOOK);

If the check fails, a `PrincipalException` is thrown and the `ADD_GUESTBOOK`
request is aborted. `GuestbookModelPermission` is a helper class. Here are its
full contents:

    package com.liferay.docs.guestbook.service.permission;

    import com.liferay.portal.kernel.exception.PortalException;
    import com.liferay.portal.security.auth.PrincipalException;
    import com.liferay.portal.security.permission.PermissionChecker;

    public class GuestbookModelPermission {

        public static final String RESOURCE_NAME = "com.liferay.docs.guestbook.model";

        public static void check(PermissionChecker permissionChecker, long groupId,
            String actionId) throws PortalException {

            if (!contains(permissionChecker, groupId, actionId)) {
                throw new PrincipalException();
            }
        }

        public static boolean contains(PermissionChecker permissionChecker,
            long groupId, String actionId) {

            return permissionChecker.hasPermission(groupId, RESOURCE_NAME, groupId,
                actionId);
        }
    }

The `PermissionChecker` class is a Liferay class. It has a `hasPermission(...)`
method that checks whether a user attempting to perform an action on a resource
has the required permission. If the user isn't signed in (i.e., is a guest
user), it checks for guest permissions. Otherwise, it checks for user
permissions. Here's an overview of the parameters of this method: 

`groupId`: Represents the scope where the permission check is performed. In
Liferay, several scopes are available, including global (company) scope, group
(site) scope, group template scope, and individual scope. This is important
because a user may be allowed to add blog entries in one site, but not in
another. For resources that don't belong to a scope (extremely rare and
unlikely), set the value of this parameter to `0`. There are several ways you
can obtain the `groupId` of the current scope: 
  - In a JSP that uses the `<theme:defineObjects/>` tag, there's an implicit
    variable called `scopeGroupId`. 
  - In a business logic class, if you're using the `ServiceContext` pattern,
    you can obtain the `groupId` by using `serviceContext.getScopeGroupId()`.
    If you're not using the `ServiceContext` pattern, you can obtain the
    `groupId` from the theme display request object: 

          ThemeDisplay themeDisplay = (ThemeDisplay)
              request.getAttribute(WebKeys.THEME_DISPLAY);
          long scopeGroupId = themeDisplay.getScopeGroupId();

`name`: The name of the resource as specified in your
`docroot/WEB-INF/src/resource-actions/default.xml` file. 

`primKey`: The primary key of the resource. In Guestbook example, the resource
doesn't exist as an entry in the database, so the `groupId` is used again. If
you're checking for a permission on an existing guestbook, you'd use the
primary key of that guestbook instead. 

`actionId`: The name of the action as it appears in your `default.xml` file.
To simplify searching for usages, consider creating a helper class that has
constants for all the actions defined. 

The examples above, assume there's a variable called `permissionChecker` already
available. Liferay automatically creates a `PermissionChecker` instance that has
the necessary information from the user for every request. Liferay also caches
the security checks to boost performance. There are several ways to obtain a
permission checker: 

- In a JSP that uses the `<theme:defineObjects/>` tag, there's an implicit
  variable called `permissionChecker`. 
- With Service Builder, every service implementation class can access the
  `PermissionChecker` instance by using the method `getPermissionChecker()`. 
- If you're not using Service Builder, `PermissionChecker` can be obtained from
  the theme display request object: 

        ThemeDisplay themeDisplay = (ThemeDisplay)
            request.getAttribute(WebKeys.THEME_DISPLAY);
        PermissionChecker permissionChecker =
            themeDisplay.getPermissionChecker();

Next, you'll look at how to create helper classes for permission checking. Using
helper classes makes it easier to invoke permission checks. When using
permissions helper classes, you won't have to invoke the
`PermissionChecker.hasPermission(...)` method manually.

## Creating Helper Classes for Permission Checking [](id=creating-helper-classes-for-permission-checking)

Helper classes streamline your code. They encapsulate the use of
`permissionChecker` and the names of the resources for a specific portlet. This
is especially useful when there are complex parent-child relationships, or if
your permission logic calls for checking multiple action types.
`GuestbookPermission` and `GuestbookModelPermission` from the Guestbook project
are examples of permission helper classes. `GuestbookModelPermission` should be
used when checking permissions for top-level resource actions whereas
`GuestbookPermission` should be used for checking permissions on Guestbook model
resource actions. In the previous section, you saw the `GuestbookModelPermisson`
class and some examples of how it was used. Here, you'll examine the
`GuestbookPermission` helper class and use it to learn how to create
your own. To start, consider how `GuestbookPermission` is used in a JSP:

    <%
        if (GuestbookPermission.contains(
            permissionChecker, curGuestbook.getGuestbookId(), "VIEW")) {
        // show guestbook data
        }
    %>

Now take a look at how the `GuestbookServiceImpl` class uses the
`GuestbookPermission` helper class. In the method
`GuestbookServiceImpl.deleteGuestbook(...)`, a call is made to check whether the
incoming request has permission to delete a guestbook. The check is done using
the helper class `GuestbookPermission`. If the check fails, it throws a
`PrincipalException`, and the delete entry request aborts. 

    GuestbookPermission.check(getPermissionChecker(), guestbookId,
        ActionKeys.DELETE);

Here's the full contents of the `GuestbookPermission` helper class:

    package com.liferay.docs.guestbook.service.permission;

    import com.liferay.docs.guestbook.model.Guestbook;
    import com.liferay.docs.guestbook.service.GuestbookLocalServiceUtil;
    import com.liferay.portal.kernel.exception.PortalException;
    import com.liferay.portal.kernel.exception.SystemException;
    import com.liferay.portal.security.auth.PrincipalException;
    import com.liferay.portal.security.permission.PermissionChecker;

    public class GuestbookPermission {
        public static void check(PermissionChecker permissionChecker,
                long guestbookId, String actionId) throws PortalException,
                SystemException {

            if (!contains(permissionChecker, guestbookId, actionId)) {
                throw new PrincipalException();
            }
        }

        public static boolean contains(PermissionChecker permissionChecker,
                long guestbookId, String actionId) throws PortalException,
                SystemException {

            Guestbook guestbook = GuestbookLocalServiceUtil
                .getGuestbook(guestbookId);

            return permissionChecker
                .hasPermission(guestbook.getGroupId(),
                    Guestbook.class.getName(), guestbook.getGuestbookId(),
                    actionId);

        }
    }

Notice that both the `check` and `contains` methods take three parameters:

1. A `PermissionChecker` object

2. A `long` representing the primary key of the entity on which the action is to
   be performed

3. A `String` representing the action ID of the action to be performed on the
   entity.

Compare these parameters to the three parameters required by the `check` and
`contains` methods of `GuestbookModelPermission`:

1. A `PermissionChecker` object

2. A `long` representing the primary key of the group in which the action is to
   be performed

3. A `String` representing the action ID of the action to be performed in the
   group.

These parameters make it clear that `GuestbookModelPermission` should be used
for checking for top-level action permissions and `GuestbookPermission` should
be used for checking resource action permissions. For both kinds of permissions
helper classes, the `check` method should throw an exception when a permission
check fails, and the `contains` method should return a `boolean` indicating
whether the user has permission to perform the specified action on the specified
resource. When you're creating your own permissions helper classes, remember to
make one helper class for your portlet's top-level actions and one permissions
class per custom entity. Remember also that your helper classes should contain
`check` and `contains` methods similar to those described above.

Notice the parameters passed into the `check(...)` method. Remember that the
`getPermissionChecker()` method is readily available in all `ServiceImpl`
classes. The guestbook ID is available from the `serviceContext`. Using a
guestbook ID for the permission check indicates that the permission check is
against a guestbook resource. The action ID is used to indicate the action
requiring the permission check. You're encouraged to use custom portlet action
keys like the `ActionKeys.DELETE` string used above.

## Related Topics [](id=related-topics)

[Customizing Liferay Portal](/tutorials/-/knowledge_base/6-2/customizing-liferay-portal)

[Plugin Security and PACL](/tutorials/-/knowledge_base/6-2/plugin-security-and-pacl)

[Developing Plugins with Liferay IDE](/tutorials/-/knowledge_base/6-2/liferay-ide)

[User Interfaces with AlloyUI](/tutorials/-/knowledge_base/6-2/alloyui)

[Liferay UI Taglibs](/tutorials/-/knowledge_base/6-2/liferay-ui-taglibs)
