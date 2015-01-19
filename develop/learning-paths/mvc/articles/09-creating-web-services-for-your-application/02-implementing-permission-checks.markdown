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
use `GuestbookPermission` or `EntryPermission`. To implement permission checks
in the guestbook-portlet project, use the following steps:

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
    the name of the string. By using `ActionKeys`, you also avoid having to
    create duplicate strings.

