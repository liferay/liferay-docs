# Writing Permissions Java Code [](id=writing-permissions-java-code)

As you've seen, permissions in Liferay Portal are defined using a configuration
file. The permissions themselves, however, must be stored in the database in
order to be checked, and this means that you need to make a change to your
service layer. Thankfully, Service Builder already knows you want to do this and
has provided everything for you. 

Once you make this change, it is a best practice to create permissions helper
classes to aid you in making permissions checks. While not strictly necessary,
these classes make checking permissions much more straightforward by reducing
the amount of code in your JSPs. 

Your next task is to modify your service layer to account for permissions. 

## Adding Permissions Resources to your Service Layer [](id=adding-permissions-resources-to-your-service-layer)

In the back end, permissions are called *resources*. Liferay already provides a
complete API for managing resources, and it's very easy to use. If you're using
Service Builder, this API is injected into your implementation classes
automatically, so all you need to do is use it. Here's how. 

1.  Open `GuestbookLocalServiceImpl.java` and find the `addGuestbook` method. 

2.  Just before the last line of the method that returns the added `Guestbook`,
    add the following code: 

		resourceLocalService.addResources(user.getCompanyId(), groupId, userId,
				Guestbook.class.getName(), guestbookId, false, true, true);

    Notice that the `resourceLocalService` object is already there, ready for
    you to use. This is one of several utilities that are automatically injected
    by Service Builder. You'll see the rest in future Learning Paths. 

    This code adds a resource to Liferay's database to correspond with your
    entity (notice that the `guestbookId` is included in the call). The three
    booleans at the end are settings. The first is whether to add portlet action
    permissions. This should only be true if the permission is for a portlet
    resource. Since this permission is for a model resource (an entity), it's
    false. The other two are settings for adding group permissions and adding
    guest permissions. If you set these to `true` as has been done here, you'll
    add the default permissions configured in the permissions configuration file
    that you created in the previous step. Since you definitely want to do this,
    these booleans are set to `true`. 

3.  Save the file and then open `EntryLocalServiceImpl.java`. 

4.  Add a line of code that adds resources for this entity: 

		resourceLocalService.addResources(user.getCompanyId(), groupId, userId,
				Entry.class.getName(), entryId, false, true, true);

    As you can see, this does the same thing for the `Entry` entity. 

That's all it takes to add permissions resources. Note that at this point, any
entities you've already added to your Guestbook don't have corresponding
resources, and thus can't be protected by permissions. It's probably wise to
delete them, which at this point, you'll have to do via SQL commands in the
database, since you haven't implemented delete functionality in your application
yet (though that's coming). Future entities added to the database are fully
permissions-enabled. 

Your next task is to create permissions helper classes to make it easier to
check permissions.

## Creating Permissions Helper Classes [](id=creating-permissions-helper-classes)

Permission checking in Liferay is a straightforward process. You have a
permission, such as `ADD_ENTRY`, and a resource, such as a `Guestbook`.
Determining whether a particular user has the permission to perform a particular
task is a simple matter of checking whether the particular permission exists for
a particular user on a particular entity. Because at a minimum you have to pass
these three things to a method, it can be easier to create helper classes to
handle permissions for particular models and particular entities. In fact,
Liferay does it all the time, so it's become a best practice, and we would be
remiss if we didn't cover it. For this reason, the next thing you need to do is
create these helper classes so that you can use them later to make your actual
permission checks. 

1.  Create a package called `com.liferay.docs.guestbook.service.permission`.
    This is where you'll place your helper classes. 

2.  Create a class called `GuestbookModelPermission`. The usual naming
    convention for your model class would normally be the name of the model, but
    in this case the name of the model (guestbook) is also the name of a
    particular entity. To distinguish the model permission from the entity
    permission you'll create later, this class is called
    `GuestbookModelPermission`. 

3.  Place the following code in this class: 

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

    This class defines two static methods (so you don't have to instantiate the
    class) that encapsulate the model that you're checking permissions for. This
    makes it easier for you to include the proper permissions check later. As
    you can see, Liferay's `PermissionChecker` class does most of the work: all
    you have to do is feed it the proper resource and and action, such as
    `ADD_ENTRY`, and it can determine whether the permission exists or not. 

    There are two implementations here: a `check` method that throws an
    exception if the user does not have permission, and a `contains` method that
    returns a boolean that's `true` if the user has permission and `false` if he
    or she does not.

Next, you'll do exactly the same thing for your two entities. 

1.  Create a class in the same package called `GuestbookPermission.java`. 

2.  Place the following code into this class: 

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

    As you can see, this class is similar to the previous class. The difference
    is, of course, that you supply the primary key of the entity you're checking
    permissions for, which in this case is the `guestbookId`. You have the same
    two methods, one of which throws an exception if there is no permission,
    while the other returns a boolean denoting whether the current user has
    permission. Note also that you have to retrieve the entity to verify that it
    exists (if it doesn't, an exception is thrown). 

Your final class is almost identical to this one, except you'll supply the
primary key of the `Entry` entity. 

1.  Create a class in the same package called `EntryPermission.java`. 

2.  Place the following code in this class: 

        package com.liferay.docs.guestbook.service.permission;

        import com.liferay.docs.guestbook.model.Entry;
        import com.liferay.docs.guestbook.service.EntryLocalServiceUtil;
        import com.liferay.portal.kernel.exception.PortalException;
        import com.liferay.portal.kernel.exception.SystemException;
        import com.liferay.portal.security.auth.PrincipalException;
        import com.liferay.portal.security.permission.PermissionChecker;

        public class EntryPermission {
            public static void check(PermissionChecker permissionChecker,
                    long entryId, String actionId) throws PortalException,
                    SystemException {

                if (!contains(permissionChecker, entryId, actionId)) {
                    throw new PrincipalException();
                }
            }

            public static boolean contains(PermissionChecker permissionChecker,
                    long entryId, String actionId) throws PortalException,
                    SystemException {

                Entry entry = EntryLocalServiceUtil
                        .getEntry(entryId);

                return permissionChecker
                        .hasPermission(entry.getGroupId(),
                                Entry.class.getName(), entry.getEntryId(),
                                actionId);

            }
        }

    This class is identical to the class that handles permissions for the
    `Guestbook` entity: the only difference is that it's for the `Entry` entity. 

Congratulations! You've completed adding permissions resources to your entities,
and have created Java helper classes for your permissions. The only thing you
have left to do is to implement the permission checks in your user interface.
That's the topic of the next step. 
