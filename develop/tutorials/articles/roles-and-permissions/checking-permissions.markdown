# Checking Permissions 

The last major step toward implementing permissions for your custom portlet is
to ensure the configured permissions are enforced. You'll do this by adding
permission checks to your application. For example, your business layer can
check for permission before deleting a resource, or your user interface can hide
a button that adds an entity (e.g. a calendar event) if the user doesn't have
permission.

---

 ![Note](../../images/tip-pen-paper.png) **Note:** The Liferay Blogs portlet is 
  used in this tutorial to illustrate exposing permissions to users. You can 
  find it in Liferay's source code on [Github](https://github.com/liferay/liferay-portal).

---

## Implementing Permissions Checking

You need to implement checking of any custom permissions you defined in your
`resource-actions` XML file. In the Blogs portlet, one supported custom action
is `ADD_ENTRY`. There are two places in the source code to check for this
permission: in your JSP files and in the business logic. For the JSP files, you
want to wrap certain elements in permission checks, so they only appear for
users with the permission to perform those functions. For example, the presence
of the Add Entry button is contingent on whether the user has permission to add
a blog entry.  Here's the `ADD_ENTRY` action in a JSP file: 

    <%
    if (permissionChecker.hasPermission(
        scopeGroupId, "com.liferay.portlet.blogs.model",
        scopeGroupId, "ADD_ENTRY") {
        // Show add entry button
    }
    %>

The second place to check for the add entry permission is in the business logic.
If the check fails, a `PrincipalException` is thrown and the add entry request
is aborted:

    if (!permissionChecker.hasPermission(
        scopeGroupId, "com.liferay.portlet.blogs.model",
        scopeGroupId, "ADD_ENTRY")) {
            throw new PrincipalException();
    }

    blogsEntryLocalService.addEntry(...);

The `PermissionChecker` class has a method `hasPermission(...)` that checks
whether a user making a resource request has the necessary access permission.
If the user isn't signed in (guest user), it checks for guest permissions.
Otherwise, it checks for user permissions. Here's a quick review of the 
parameters of this method: 

-   `groupId`: Represents the scope where the permission check is performed. In
    Liferay, many scopes are available, including a specific site, organization,
    personal site of a user, or a page in a site. This is important because a
    user may be allowed to add blog entries in one site, but not in another. For
    resources that don't belong to a scope (extremely rare and unlikely), set
    the value of this parameter to `0`. There are several ways you can obtain
    the `groupId` of the current scope: 
    -   JSP that uses the `<theme:defineObjects/>` tag: there's an implicit
        variable called `scopeGroupId`. 
    -   Business logic class: If you're using the ServiceContext pattern, you
        can obtain the `groupId` by using `serviceContext.getScopeGroupId()`. If
        you're not using the `ServiceContext` pattern, your can obtain `groupId`
        from the theme display request object: 

            ThemeDisplay themeDisplay = (ThemeDisplay)
                request.getAttribute(WebKeys.THEME_DISPLAY);
            long scopeGroupId = themeDisplay.getScopeGroupId();

-   `name`: The name of the resource as specified in the XML file of the
    previous sections. 
-   `primKey`: The primary key of the resource. In this example the resource
    doesn't exist as an entry in the database, so we use the `groupId` again. If
    we were checking for a permission on a given blog entry, we'd use the
    primary key of that blog entry instead. 
-   `actionId`: The name of the action as it appears in the XML file. To
    simplify searching for usages, consider creating a helper class that has
    constants for all the actions defined. 

The examples above assume there's a variable called `permissionChecker` already 
available. Liferay automatically creates a `PermissionChecker` instance that has 
the necessary information from the user for every request. Liferay also caches 
the security checks to ensure good performance. There are several ways to obtain 
a permission checker: 

-   In a JSP that uses the `<theme:defineObjects/>` tag, there's an implicit
    variable called `permissionChecker`. 
-   With Service Builder, every service implementation class can access the
    `PermissionChecker` instance by using the method `getPermissionChecker()`. 
-   If you're not using Service Builder, `PermissionChecker` can be obtained
    from the theme display request object: 

        ThemeDisplay themeDisplay = (ThemeDisplay)
            request.getAttribute(WebKeys.THEME_DISPLAY);
        PermissionChecker permissionChecker =
            themeDisplay.getPermissionChecker();

Next, you'll take a look at how helper classes are implemented. Helper classes 
optimize permission checking and do most of the heavy lifting.

## Helper Classes for Permission Checking 

Helper classes streamline your code. They encapsulate the use of
`permissionChecker` and the names of the resources for a specific portlet. This
is especially useful when there are complex parent-child relationships, or if
your permission logic calls for checking multiple action types.
`BlogsPermission` is an example of a permission helper class. Here's how
`BlogsPermission` is used in a JSP: 

    <%
    if (BlogsPermission.contains(permissionChecker, scopeGroupId,
        ActionKeys.ADD_ENTRY)) {
        // show add entry button
        }
    %>

Now take a look at how a `ServiceImpl` class, `BlogsEntryServiceImpl`, uses the
`BlogsPermission` helper class. In the method
`BlogsEntryServiceImpl.addEntry(...)`, a call is made to check whether the
incoming request has permission to add an entry. The check is done using the
helper class `BlogsPermission`. If the check fails, it throws a
`PrincipalException` and the add entry request aborts. 

    public BlogsEntry addEntry(
        String title, String description, String content,
        int displayDateMonth, int displayDateDay, int displayDateYear,
        int displayDateHour, int displayDateMinute, boolean allowPingbacks,
        boolean allowTrackbacks, String[] trackbacks, boolean smallImage,
        String smallImageURL, String smallImageFileName,
        InputStream smallImageInputStream, ServiceContext serviceContext)
        throws PortalException, SystemException {

        BlogsPermission.check(
            getPermissionChecker(), serviceContext.getScopeGroupId(),
            ActionKeys.ADD_ENTRY);

        return blogsEntryLocalService.addEntry(
            getUserId(), title, description, content, displayDateMonth,
            displayDateDay, displayDateYear, displayDateHour, displayDateMinute,
            allowPingbacks, allowTrackbacks, trackbacks, smallImage,
            smallImageURL, smallImageFileName, smallImageInputStream,
            serviceContext);
    }

Check out the parameters passed into the `check(...)` method. Again, the
`getPermissionChecker()` method is readily available in all `ServiceImpl`
classes. The blogs entry ID is available in the `serviceContext`, indicating
that the permission check is against the Blogs portlet. `ActionKeys.ADD_ENTRY`
is a static string used to indicate the action requiring the permission check.
Likewise, you're encouraged to use custom portlet action keys. 

<!-- We're not ready for review yet. We haven't covered how to create the helper
class, which is what the reader is expecting out of this section. Certainly I
was. We've only seen how to use a helper class that already exists. -Rich --> 

## Next Steps 

<!-- Change link once header id is generated -->

<!--
[Checking Permissions](/tutorials/-/knowledge_base/checking-permissions-lp-6-2-develop-tutorial) 
-->

[Service Builder](/tutorials/-/knowledge_base/service-builder-lp-6-2-develop-tutorial)

[Localization](/tutorials/-/knowledge_base/localization-lp-6-2-develop-tutorial)
