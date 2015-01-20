# Implementing Permission Checks

Now that your guestbook and guestbook entry web services are up and running,
it's time to implement permission checks for them. Implementing permission
checks for a web service ensures that only users with the correct permissions
can invoke the web service. To implement permission checks in your remote
services, you'll use the `GuestbookModelPermission`, `GuestbookPermission`, and
`EntryPermission` helper classes that you created in an earlier learning path.
These classes provide helper methods for checking permissions. The helper
methods in `GuestbookModelPermission` are for checking top level model
permissions. For example, if you need to check whether a user has permission to
add a new guestbook or guestbook entry, you can use `GuestbookModelPermission`s
helper methods. If, on the other hand, you need to check whether a user has
permission to update or delete an existing guestbook or guestbook entry, you can
use `GuestbookPermission` or `EntryPermission`. After you've secured your remote
services with permission checks, you need to update your portlet classes so that
they call the remote services instead of the local services.

## Implementing Permission Checks at the Service Layer

To implement permission checks for the guestbook-portlet project's remote
services, use the following steps:

1. Open `GuestbookServiceImpl` and replace the `addGuestbook`,
   `deleteGuestbook`, and `updateGuestbook` methods with the following ones:

        public Guestbook addGuestbook(long userId, String name,
                        ServiceContext serviceContext) throws SystemException,
                        PortalException {

                GuestbookModelPermission.check(getPermissionChecker(),
                                serviceContext.getScopeGroupId(), ActionKeys.ADD_GUESTBOOK);

                return GuestbookLocalServiceUtil.addGuestbook(userId, name,
                                serviceContext);
        }

        public Guestbook deleteGuestbook(long guestbookId,
                        ServiceContext serviceContext) throws PortalException,
                        SystemException {
                
                GuestbookPermission.check(getPermissionChecker(),
                                serviceContext.getScopeGroupId(), ActionKeys.DELETE);

                return GuestbookLocalServiceUtil.deleteGuestbook(guestbookId);
        }

        public Guestbook updateGuestbook(long userId, long guestbookId,
                        String name, ServiceContext serviceContext) throws PortalException,
                        SystemException {
                
                GuestbookPermission.check(getPermissionChecker(),
                                serviceContext.getScopeGroupId(), ActionKeys.UPDATE);

                return GuestbookLocalServiceUtil.updateGuestbook(userId, guestbookId,
                                name, serviceContext);
        }

    Here, you've added permission checks to the remote service methods by
    calling the `check` helper methods of `GuestbookModelPermission` and
    `GuestbookPermission`. The `check` methods take three parameters:

    - a `PermissionChecker` object
    - a `groupId`
    - an `actionId` string 

    `BaseServiceImpl` contains a `getPermissionChecker` method which you can
    invoke to get a `PermissionChecker` object. This is possible since
    `GuestbookServiceImpl` extends `GuestbookServiceBaseImpl`, which extends
    `BaseServiceImpl`. Getting a `groupId` can be easily done via the
    `getScopeGroupId` method of `serviceContext`. To specify the `actionId`
    string which determines the particular action for which you're checking
    permissions, you use a specific field of your `ActionKeys` class. Choosing a
    specific field of `ActionKeys` is less error prone than than manually typing
    the name of the string. Also, by using a string from `ActionKeys`, you avoid
    creating a duplicate string.

2. Open `EntryServiceImpl` and replace the `addEntry`, `deleteEntry`, and
   `updateEntry` methods with the following ones:

        public Entry addEntry(long userId, long guestbookId, String name,
                        String email, String message, ServiceContext serviceContext)
                        throws PortalException, SystemException {

                GuestbookModelPermission.check(getPermissionChecker(),
                                serviceContext.getScopeGroupId(), ActionKeys.ADD_ENTRY);

                return EntryLocalServiceUtil.addEntry(userId, guestbookId, name, email,
                                message, serviceContext);
        }

        public Entry deleteEntry(long entryId, ServiceContext serviceContext)
                        throws PortalException, SystemException {

                EntryPermission.check(getPermissionChecker(),
                                serviceContext.getScopeGroupId(), ActionKeys.DELETE);

                return EntryLocalServiceUtil.deleteEntry(entryId, serviceContext);
        }

        public Entry updateEntry(long userId, long guestbookId, long entryId,
                        String name, String email, String message,
                        ServiceContext serviceContext) throws PortalException,
                        SystemException {

                EntryPermission.check(getPermissionChecker(),
                                serviceContext.getScopeGroupId(), ActionKeys.UPDATE);

                return EntryLocalServiceUtil.updateEntry(userId, guestbookId, entryId,
                                name, email, message, serviceContext);
        }

    The permission checks in these methods work the same way as the permissions
    checks that you added to `GuestbookServiceImpl`. For `addEntry`, you use
    `GuestbookModelPermission.check` for the permission check since adding a
    guestbook entry is a top level model action. For `deleteEntry` and
    `updateEntry`, you use `EntryPermission.check` since these operations each
    require a specific permission on a specific entity.

Note that you only implemented permission checks for the `addGuestbook`,
`deleteGuestbook`, `updateGuestbook`, `addEntry`, `deleteEntry`, and
`updateEntry` remote service methods. You should decide whether or not to
implement permission checks for the `getGuestbooks`, `getGuestbooksCount`,
`getEntries`, and `getEntriesCount` remote service methods. Implementing
permission checks for these methods is not covered in this learning path.

## Securing Service Calls at the Portlet Layer

You've now secured your remote services but you still have some work to do. In a
previous learning path, you implemented portlet action methods such as
`addGuestbook`, `addEntry`, `deleteEntry`, etc. in your `GuestbookPortlet` and
`GuestbookAdminPortlet` classes. When you implemented such portlet action
methods, you ended up calling local service methods. For example, in the
`addEntry` method of `GuestbookPortlet`, you used the following call to add a
new guestbook entry:

    EntryLocalServiceUtil.addEntry(serviceContext.getUserId(),
        guestbookId, userName, email, message, serviceContext);

Your application's local services do not and should not contain permission
checks. Your remote services now contain permission checks. If the permission
check of a remote service is satisfied, then the remote service calls the local
service. Thus, to secure service calls at the portlet layer, all you have to do
is replace the local service calls with remote service calls.

+$$$

**Note:** An alternative approach to securing service calls at the portlet layer
could be to manually check permissions at the portlet layer. To do so, you could
obtain a `ThemeDisplay` from the `ActionRequest` (`ThemeDisplay themeDisplay =
(ThemeDisplay) request.getAttribute(WebKeys.THEME_DISPLAY);`) and obtain a
`PermissionChecker` from the `ThemeDisplay` (`PermissionChecker
permissionChecker = themeDisplay.getPermissionChecker();`). If the user passes
the permission check, then you could call the local service method. However,
it's best to avoid rewriting permission checks whenever possible. For this
reason, if you need to wrap a service call in a permission check, it's best to
implement that service method as a remote service and to add the permission
check to the remote service. This is the pattern that you followed in this
learning path.

$$$

Use the following steps to secure the service calls in your portlet action
methods:

1. Open your `GuestbookPortlet` class and 

Now that you've implemented permission checks at the portlet layer, users
without the proper permissions cannot add, update, or delete a guestbook or a
guestbook entry entity. Even if such a user manually entered a URL pointing to
one of your portlet action methods, the portlet action now calls a remote
service. The permission check in the remote service would fail and the user's
request would be aborted.
