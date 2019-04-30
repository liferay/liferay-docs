# Assigning Permissions to Resources [](id=assigning-permissions-to-resources)

<div class="learn-path-step">
    <p>Implementing Permissions<br>Step 3 of 4</p>
</div>

You've now defined your permissions and registered them in the container and in
the database so permissions can be checked. Now you'll create a UI for users to
assign permissions along with helper classes to make it easy to check
permissions in the next step. 

Here's how it works. You have a permission, such as `ADD_ENTRY`, and a resource,
such as a `Guestbook`. For a user to add an entry to a guestbook, you must check
if that user has the `ADD_ENTRY` permission for that guestbook. Helper classes
make it easier to check permissions: 

1.  Right-click the `guestbook-web` module and select *New* &rarr;
    *Package*. To follow Liferay's practice, name the package
    `com.liferay.docs.guestbook.web.internal.security.permission.resource`. This
    is where you'll place your helper classes. 

2.  Right-click the new package and select *New* &rarr; *Class*. Name the class 
    `GuestbookPermission`. 

3.  Replace this class's contents with the following code: 

        package com.liferay.docs.guestbook.web.internal.security.permission.resource;

        import org.osgi.service.component.annotations.Component;
        import org.osgi.service.component.annotations.Reference;

        import com.liferay.docs.guestbook.constants.GuestbookConstants;
        import com.liferay.portal.kernel.security.permission.PermissionChecker;
        import com.liferay.portal.kernel.security.permission.resource.PortletResourcePermission;

        @Component(immediate=true)
        public class GuestbookPermission {

            public static boolean contains(PermissionChecker permissionChecker, long groupId, String actionId) {
                
                return _portletResourcePermission.contains(permissionChecker, groupId, actionId);
                
            }
            
            @Reference(
                    target="(resource.name=" + GuestbookConstants.RESOURCE_NAME + ")", 
                    unbind="-"
                    )
            protected void setPortletResourcePermission(PortletResourcePermission portletResourcePermission) {
                
                _portletResourcePermission = portletResourcePermission;
            }
            
            private static PortletResourcePermission _portletResourcePermission;

        }

This class is a component defining one static method (so you don't have to
instantiate the class) that encapsulates the model you're checking permissions
for. Liferay's `PermissionChecker` class does most of the work: give it the
proper resource and action, such as `ADD_ENTRY`, and it returns whether the
permission exists or not. 

There's only one method: a `check` method that throws an exception if the user
doesn't have permission. 

Next, you'll create helpers for your two entities: 

1.  Create a class in the same package called `GuestbookModelPermission.java`. 

2.  Replace this class's contents with the following code: 

            package com.liferay.docs.guestbook.web.internal.security.permission.resource;

            import org.osgi.service.component.annotations.Component;
            import org.osgi.service.component.annotations.Reference;

            import com.liferay.docs.guestbook.model.Guestbook;
            import com.liferay.portal.kernel.exception.PortalException;
            import com.liferay.portal.kernel.security.permission.PermissionChecker;
            import com.liferay.portal.kernel.security.permission.resource.ModelResourcePermission;

            @Component(immediate = true)
            public class GuestbookModelPermission {
                
                public static boolean contains(
                        PermissionChecker permissionChecker, Guestbook guestbook, String actionId) throws PortalException {
                    
                    return _guestbookModelResourcePermission.contains(permissionChecker, guestbook, actionId);
                }
                
                public static boolean contains(
                        PermissionChecker permissionChecker, long guestbookId, String actionId) throws PortalException {
                    
                    return _guestbookModelResourcePermission.contains(permissionChecker, guestbookId, actionId);
                }
                
                @Reference(
                        target = "(model.class.name=com.liferay.docs.guestbook.model.Guestbook)", 
                        unbind = "-")
                protected void setEntryModelPermission(ModelResourcePermission<Guestbook> modelResourcePermission) {
                    
                    _guestbookModelResourcePermission = modelResourcePermission;
                }
                
                private static ModelResourcePermission<Guestbook>_guestbookModelResourcePermission;

            }

As you can see, this class is similar to `GuestbookPermission`. The difference
is that `GuestbookModelPermission` is for the model/resource permission, so you
supply the entity or its primary key (`guestbookId`). 

Your final class is almost identical to `GuestbookModelPermission`, but it's for
the `Entry` entity. Follow these steps to create it: 

1.  Create a class in the same package called `GuestbookEntryPermission.java`. 

2.  Replace this class's contents with the following code: 

        package com.liferay.docs.guestbook.web.internal.security.permission.resource;

        import org.osgi.service.component.annotations.Component;
        import org.osgi.service.component.annotations.Reference;

        import com.liferay.docs.guestbook.model.Entry;
        import com.liferay.portal.kernel.exception.PortalException;
        import com.liferay.portal.kernel.security.permission.PermissionChecker;
        import com.liferay.portal.kernel.security.permission.resource.ModelResourcePermission;

        @Component(immediate = true)
        public class GuestbookEntryPermission {
            
            public static boolean contains(
                    PermissionChecker permissionChecker, Entry entry, String actionId) throws PortalException {
                
                return _guestbookEntryModelResourcePermission.contains(permissionChecker, entry, actionId);
            }
            
            public static boolean contains(
                    PermissionChecker permissionChecker, long entryId, String actionId) throws PortalException {
                
                return _guestbookEntryModelResourcePermission.contains(permissionChecker, entryId, actionId);
            }
            
            @Reference(
                    target = "(model.class.name=com.liferay.docs.guestbook.model.Entry)", 
                    unbind = "-")
            protected void setEntryModelPermission(ModelResourcePermission<Entry> modelResourcePermission) {
                
                _guestbookEntryModelResourcePermission = modelResourcePermission;
            }
            
            private static ModelResourcePermission<Entry>_guestbookEntryModelResourcePermission;

        }

This class is almost identical to `GuestbookModelPermission`. The only
difference is that `GuestbookEntryPermission` is for the `Entry` entity. 

Now you can expose the permissions UI to your users so they can assign
permissions: 

1.  Go to the `init.jsp` in your `guestbook-web` project. Add the following
    imports to the file:

        <%@ page import="com.liferay.docs.guestbook.web.internal.security.permission.resource.GuestbookModelPermission" %>
        <%@ page import="com.liferay.docs.guestbook.web.internal.security.permission.resource.GuestbookPermission" %>
        <%@ page import="com.liferay.docs.guestbook.web.internal.security.permission.resource.GuestbookEntryPermission" %>
        <%@ page import="com.liferay.portal.kernel.util.WebKeys" %>
        <%@ page import="com.liferay.portal.kernel.security.permission.ActionKeys" %>

    The first three are the permissions helper classes you just created.

2.  Open `guestbook_actions.jsp`. Add this code just after the
    `<liferay-ui:icon-delete>` tag: 

            <c:if
            test="<%=GuestbookModelPermission.contains(permissionChecker, guestbook.getGuestbookId(), ActionKeys.PERMISSIONS) %>">

                <liferay-security:permissionsURL
                    modelResource="<%= Guestbook.class.getName() %>"
                    modelResourceDescription="<%= guestbook.getName() %>"
                    resourcePrimKey="<%= String.valueOf(guestbook.getGuestbookId()) %>"
                    var="permissionsURL" />
            
                <liferay-ui:icon image="permissions" url="<%= permissionsURL %>" />

            </c:if>

3.  Save the file. 

You just added an action button that displays Liferay's permissions UI for
Guestbooks. On top of that, you used the permissions helper you just created to
test whether users can even see the action button. It only appears if users have
the *permissions* permission. 

You'll implement this for Guestbook entries in the next step. 

Congratulations! You've now created helper classes for your permissions, and
you've enabled users to associate permissions with their resources. The only
thing left is to implement permission checks in the application's view layer.
You'll do this next. 
