# Creating Permissions Helper Classes [](id=creating-permissions-helper-classes)

<div class="learn-path-step">
    <p>Implementing Permissions<br>Step 3 of 4</p>
</div>

You've now defined your permissions and made sure resources are added to the 
database so permissions can be checked. Now you'll create the helper classes 
needed to check permissions. 

Here's how it works. You have a permission, such as `ADD_ENTRY`, and a resource,
such as a `Guestbook`. For a user to add an entry to a guestbook, you must check 
whether that user has the `ADD_ENTRY` permission for that guestbook. Creating 
helper classes to check permissions on particular models and entities makes 
these checks more efficient. Creating such classes is therefore a best practice. 
Now you'll create these classes for the Guestbook application: 

1.  Right-click the `guestbook-service` module and select *New* &rarr; 
    *Package*. Name the package `com.liferay.docs.guestbook.service.permission`. 
    This is where you'll place your helper classes. 

2.  Right-click the new package and select *New* &rarr; *Class*. Name the class 
    `GuestbookModelPermission`. 

3.  Replace this class's contents with the following code: 

        package com.liferay.docs.guestbook.service.permission;

        import org.osgi.service.component.annotations.Component;

        import com.liferay.portal.kernel.exception.PortalException;
        import com.liferay.portal.kernel.security.auth.PrincipalException;
        import com.liferay.portal.kernel.security.permission.BaseResourcePermissionChecker;
        import com.liferay.portal.kernel.security.permission.PermissionChecker;
        import com.liferay.portal.kernel.security.permission.ResourcePermissionChecker;

        @Component(immediate = true, property = {
            "resource.name=" + GuestbookModelPermission.RESOURCE_NAME
        }, service = ResourcePermissionChecker.class)

        public class GuestbookModelPermission extends BaseResourcePermissionChecker {

            public static final String RESOURCE_NAME = "com.liferay.docs.guestbook";

            public static void check(
                PermissionChecker permissionChecker, long groupId, String actionId)
                throws PortalException {

                if (!contains(permissionChecker, groupId, actionId)) {
                    throw new PrincipalException.MustHavePermission(
                        permissionChecker, RESOURCE_NAME, groupId, actionId);
                }
            }

            public static boolean contains(
                PermissionChecker permissionChecker, long groupId, String actionId) {

                return permissionChecker.hasPermission(
                    groupId, RESOURCE_NAME, groupId, actionId);
            }

            @Override
            public Boolean checkResource(
                PermissionChecker permissionChecker, long classPK, String actionId) {

                return contains(permissionChecker, classPK, actionId);
            }
        }

This class is a component that extends `BaseResourcePermissionChecker` and 
defines two static methods (so you don't have to instantiate the class) that 
encapsulate the model you're checking permissions for. It also contains a 
boolean method that checks your resources. Liferay's `PermissionChecker` class 
does most of the work: you only need feed it the proper resource and action, 
such as `ADD_ENTRY`, and it returns whether the permission exists or not. 

There are three implementations here: a `check` method that throws an exception 
if the user doesn't have permission, a `contains` method that returns a boolean 
that's `true` if the user has permission and `false` if not, and a 
`checkResource` method that calls the `contains` method. 

Next, you'll create helpers for your two entities. Follow these steps to do so: 

1.  Create a class in the same package called `GuestbookPermission.java`. 

2.  Replace this class's contents with the following code: 

        package com.liferay.docs.guestbook.service.permission;

        import com.liferay.docs.guestbook.model.Guestbook;
        import com.liferay.docs.guestbook.service.GuestbookLocalService;
        import com.liferay.portal.kernel.exception.PortalException;
        import com.liferay.portal.kernel.exception.SystemException;
        import com.liferay.portal.kernel.security.auth.PrincipalException;
        import com.liferay.portal.kernel.security.permission.BaseModelPermissionChecker;
        import com.liferay.portal.kernel.security.permission.PermissionChecker;
        import org.osgi.service.component.annotations.Component;
        import org.osgi.service.component.annotations.Reference;

        @Component(
            immediate = true,
            property = {"model.class.name=com.liferay.docs.guestbook.model.Guestbook"}
        )
        public class GuestbookPermission implements BaseModelPermissionChecker {

            public static void check(
                PermissionChecker permissionChecker, long guestbookId, String actionId)
                throws PortalException, SystemException {

                if (!contains(permissionChecker, guestbookId, actionId)) {
                    throw new PrincipalException();
                }
            }

            public static void check(
                PermissionChecker permissionChecker, long groupId, long guestbookId,
                String actionId)
                throws PortalException {

                if (!contains(permissionChecker, groupId, actionId)) {
                    throw new PrincipalException.MustHavePermission(
                        permissionChecker, Guestbook.class.getName(), guestbookId,
                        actionId);
                }
            }

            public static boolean contains(
                PermissionChecker permissionChecker, long groupId, long guestbookId, String actionId) 
                    throws PortalException {

                Guestbook guestbook = _guestbookLocalService.getGuestbook(guestbookId);

                return GuestbookModelPermission.contains(permissionChecker, groupId, actionId);
            }

            public static boolean contains(
                PermissionChecker permissionChecker, long guestbookId, String actionId)
                throws PortalException, SystemException {

                Guestbook guestbook
                    = _guestbookLocalService.getGuestbook(guestbookId);
                return contains(permissionChecker, guestbook, actionId);
            }

            public static boolean contains(
                PermissionChecker permissionChecker, Guestbook guestbook, String actionId) 
                    throws PortalException, SystemException {

                return permissionChecker.hasPermission(
                guestbook.getGroupId(), Guestbook.class.getName(), guestbook.getGuestbookId(), actionId);

            }

            @Reference(unbind = "-")
            protected void setGuestbookLocalService(GuestbookLocalService guestbookLocalService) {
                _guestbookLocalService = guestbookLocalService;
            }

            private static GuestbookLocalService _guestbookLocalService;

            @Override
            public void checkBaseModel(
                PermissionChecker permissionChecker, long groupId, long guestbookId, String actionId) throws PortalException {
                    check(permissionChecker, guestbookId, actionId);
            }
        }

As you can see, this class is similar to `GuestbookModelPermission`. The 
difference is that `GuestbookPermission` is for the model/resource permission, 
so you supply the primary key of the entity you're checking permissions for 
(`guestbookId`). The `check` and `contains` methods in `GuestbookPermission` are 
also similar to those in `GuestbookModelPermission`. In both classes, the 
`check` method throws an exception if there's no permission, while the 
`contains` method returns a boolean denoting whether the current user has 
permission. The `contains` method in `GuestbookPermission`, however, also 
retrieves the entity to verify that it exists (if it doesn't, an exception is 
thrown). 

Your final class is almost identical to `GuestbookPermission`, but it's for the 
`Entry` entity. Follow these steps to create it: 

1.  Create a class in the same package called `EntryPermission.java`. 

2.  Replace this class's contents with the following code: 

        package com.liferay.docs.guestbook.service.permission;

        import com.liferay.docs.guestbook.model.Entry;
        import com.liferay.docs.guestbook.service.EntryLocalService;
        import com.liferay.portal.kernel.exception.PortalException;
        import com.liferay.portal.kernel.exception.SystemException;
        import com.liferay.portal.kernel.security.auth.PrincipalException;
        import com.liferay.portal.kernel.security.permission.BaseModelPermissionChecker;
        import com.liferay.portal.kernel.security.permission.PermissionChecker;
        import org.osgi.service.component.annotations.Component;
        import org.osgi.service.component.annotations.Reference;

        @Component(
            immediate = true,
            property = {"model.class.name=com.liferay.docs.guestbook.model.Entry"}
        )
        public class EntryPermission implements BaseModelPermissionChecker {

            public static void check(
                PermissionChecker permissionChecker, long entryId, String actionId)
                throws PortalException, SystemException {

                if (!contains(permissionChecker, entryId, actionId)) {
                    throw new PrincipalException();
                }
            }

            public static boolean contains(
                PermissionChecker permissionChecker, long entryId, String actionId)
                throws PortalException, SystemException {

                Entry entry = _entryLocalService.getEntry(entryId);

                return contains (permissionChecker, entry, actionId);

            }

            public static boolean contains(
                PermissionChecker permissionChecker, Entry entry, String actionId) throws
                PortalException, SystemException {

                return permissionChecker.hasPermission(entry.getGroupId(), Entry.class.getName(), entry.getEntryId(), actionId);
            }

            @Reference(unbind = "-")
            protected void setEntryLocalService (EntryLocalService entryLocalService) {

                _entryLocalService = entryLocalService;
            }

            private static EntryLocalService _entryLocalService; 

            @Override
            public void checkBaseModel(
                PermissionChecker permissionChecker, long groupId, long primaryKey, String actionId) throws PortalException {
                    check(permissionChecker, primaryKey, actionId);
            }
        }

This class is almost identical to `GuestbookPermission`. The only difference is 
that `EntryPermission` is for the `Entry` entity. 

Now that you have these classes, you must build services and export the 
permissions package so that other modules can access it. Follow these steps to 
do so: 

1.  Save the permissions helper classes you just created. From the Gradle Tasks 
    panel on the right side of Liferay @ide@, run `buildService` from the 
    `guestbook-service` module's `build` folder. 

2.  In the Project Explorer, open the `bnd.bnd` file from the root folder of the 
    `guestbook-service` module.

3.  In the graphical view, under the *Export Packages* section, click the plus 
    button to add an export. 

4.  Select `com.liferay.docs.guestbook.service.permission` and click `OK`.

5.  Save the file.

Congratulations! You've now created helper classes for your permissions. The 
only thing left is to implement permission checks in the application's view 
layer. You'll do this next. 
