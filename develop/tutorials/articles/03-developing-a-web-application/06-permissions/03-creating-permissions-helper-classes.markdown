# Creating Permissions Helper Classes

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

1.  Right-click on your `guestbook-service` module and select *New* &rarr;
    *Package* name it `com.liferay.docs.guestbook.service.permission`.
    This is where you'll place your helper classes. 

2.  Right-click on the new package and select *New* &rarr; *Class* and name it 
    `GuestbookModelPermission`.

3.  Place the following code in this class: 

        package com.liferay.docs.guestbook.service.permission;
        
        import org.osgi.service.component.annotations.Component;
        import com.liferay.portal.kernel.exception.PortalException;
        import com.liferay.portal.kernel.security.auth.PrincipalException;
        import com.liferay.portal.kernel.security.permission.BaseResourcePermissionChecker;
        import com.liferay.portal.kernel.security.permission.PermissionChecker;


        @Component(
                immediate = true,
                property = {
                    "resource.name=" + GuestbookModelPermission.RESOURCE_NAME
                },
                service = GuestbookModelPermission.class
            )

         public class GuestbookModelPermission extends BaseResourcePermissionChecker  {

               public static final String RESOURCE_NAME = "com.liferay.docs.guestbook";

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

               @Override
               public Boolean checkResource(PermissionChecker permissionChecker, long classPK, String actionId) {
                       return contains(permissionChecker, classPK, actionId);
                    }
                }


This class is a component which extends `BaseResourcePermissionChecker` and 
defines two static methods (so  you don't have to instantiate the class) that 
encapsulate the model that you're checking permissions for. This makes it 
easier for you to include the proper permissions check later. It also contains 
a boolean method which checks your resources. Liferay's `PermissionChecker` 
class does most of the work: all you have to do is feed it the proper resource 
and and action, such as `ADD_ENTRY`, and it can determine whether the 
permission exists or not. 

There are three implementations here: a `check` method that throws an
exception if the user does not have permission, a `contains` method that
returns a boolean that's `true` if the user has permission and `false` if he
or she does not, and a `checkResource` method which calls the `contains` method.

Next, we'll create helpers for your two entities. 

1.  Create a class in the same package called `GuestbookPermission.java`. 

2.  Place the following code into this class: 

        package com.liferay.docs.guestbook.service.permission;

        import com.liferay.docs.guestbook.model.Guestbook;
        import com.liferay.docs.guestbook.service.GuestbookLocalServiceUtil;
        import com.liferay.portal.kernel.exception.PortalException;
        import com.liferay.portal.kernel.exception.SystemException;
        import com.liferay.portal.kernel.security.auth.PrincipalException;
        import com.liferay.portal.kernel.security.permission.PermissionChecker;

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
        import com.liferay.portal.kernel.security.auth.PrincipalException;
        import com.liferay.portal.kernel.security.permission.PermissionChecker;

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

Now that you created these files, you need to build services, and export the
permissions package so that other modules can access it.

1. From the build panel on the right side of the screen, run `buildService` for 
    the `guestbook-service` module.

2. Open the `bnd.bnd` file, found in the root folder of your `guestbook-service`
    module.

3. In the graphical view, under the *Export Packages* section, click the plus 
    button to add an export.
    
4. Select `com.liferay.docs.guestbook.service.permission` and click `OK`.

5. Save the file.

Congratulations! You've completed adding permissions resources to your entities,
and have created Java helper classes for your permissions. The only thing you
have left to do is to implement the permission checks in your user interface.
That's the topic of the next step. 
