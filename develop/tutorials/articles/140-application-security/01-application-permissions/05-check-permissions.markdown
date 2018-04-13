# Checking Permissions [](id=checking-permissions)

Now that you've defined your permissions, registered resources in the database
and with the OSGi container, and enabled users to associate permissions with
resources, you're ready to add permission checks in the appropriate places in
your application. This takes three steps: 

1.  Add permission checks to your service calls. 

2.  Create permission helper classes in your web module. 

3.  Add permission checks to your web application. 

These things are covered next. 

## Add Permission Checks to Your Service Calls [](id=add-permission-checks-to-your-service-calls)

A best practice is to create methods in your `-ServiceImpl` classes that call
the same methods in your `-LocalServiceImpl` classes, but wrap those calls in
permission checks. If you expose your services as web services, then any client
calling those services must have permission to call the service. In this
way, you separate your business logic (contained in the `-LocalServiceImpl`
class) from your permissions logic (contained in the `-ServiceImpl` class). 

1.  Open your entity's `-ServiceImpl` class. 

2.  Use the `ModelResourcePermissionFactory` and the
    `PortletResourcePermissionFactory` to reference permission checkers that can
    check permissions as you've defined them in `default.xml`. Here's how the
    Blogs portlet does this: 

        private static volatile ModelResourcePermission<BlogsEntry>
            _blogsEntryFolderModelResourcePermission =
                ModelResourcePermissionFactory.getInstance(
                    BlogsEntryServiceImpl.class,
                    "_blogsEntryFolderModelResourcePermission", BlogsEntry.class);
        private static volatile PortletResourcePermission
            _portletResourcePermission =
                PortletResourcePermissionFactory.getInstance(
                    BlogsEntryServiceImpl.class, "_portletResourcePermission",
                    BlogsConstants.RESOURCE_NAME);

    You declare the class, the variable, and for the portlet resource, the
    resource name from `default.xml`. In the Blogs application,
    `BlogsConstants.RESOURCE_NAME` is a `String` with the value
    `com.liferay.blogs`. 

    You must use `ModelResourcePermissionFactory.getInstance()` in the service
    because Service Builder is wired with Spring, so `@Reference` can't be used.
    Make sure to use the correct service class and the name of the field that's
    being set (in this case `"_blogsEntryFolderModelResourcePermission"`),
    because it's set with reflection when the service is registered. If you get
    the field wrong, it'll be set wrong. The field must be `static` and
    `volatile`, and should never be used outside of `-ServiceImpl` classes. 

3.  Check permissions in the appropriate places. For example, adding a blog
    entry requires the `ADD_ENTRY` permission, so the Blogs application does
    this: 

        @Override
        public BlogsEntry addEntry(
                String title, String subtitle, String description, String content,
                int displayDateMonth, int displayDateDay, int displayDateYear,
                int displayDateHour, int displayDateMinute, boolean allowPingbacks,
                boolean allowTrackbacks, String[] trackbacks,
                String coverImageCaption, ImageSelector coverImageImageSelector,
                ImageSelector smallImageImageSelector,
                ServiceContext serviceContext)
            throws PortalException {

            _portletResourcePermission.check(
                getPermissionChecker(), serviceContext.getScopeGroupId(),
                ActionKeys.ADD_ENTRY);

            return blogsEntryLocalService.addEntry(
                getUserId(), title, subtitle, description, content,
                displayDateMonth, displayDateDay, displayDateYear, displayDateHour,
                displayDateMinute, allowPingbacks, allowTrackbacks, trackbacks,
                coverImageCaption, coverImageImageSelector, smallImageImageSelector,
                serviceContext);
        }

    The check throws an exception if it fails, preventing the local service call
    that adds the entry. A convention Liferay uses is to place the action keys
    from `default.xml` as constants in an `ActionKeys` class. If `ActionKeys`
    doesn't have an action key appropriate for your application, extend
    Liferay's class and add your own keys. 

Add permission checks where necessary to protect your application's functions at
the service level. Next, you'll learn how to create permission helper classes
for your web module. 

## Create Permission Helper Classes in Your Web Module [](id=create-permission-helper-classes-in-your-web-module)

A helper class can make it easier to check permissions in your portlet
application. You can create helper classes for both portlet permissions and 
model permissions. Here's how to create a portlet permission helper: 

1.  Create a package with the suffix
    `web.internal.security.permission.resource`. For example, the Blogs
    application has the package
    `com.liferay.blogs.web.internal.security.permission.resource`. 

2.  Create a component class with at least one static method for checking
    permissions. For example, here's the `BlogsPermission` class: 

        @Component(immediate = true)
        public class BlogsPermission {

            public static boolean contains(
                PermissionChecker permissionChecker, long groupId, String actionId) {

                return _portletResourcePermission.contains(
                    permissionChecker, groupId, actionId);
            }

            @Reference(
                target = "(resource.name=" + BlogsConstants.RESOURCE_NAME + ")",
                unbind = "-"
            )
            protected void setPortletResourcePermission(
                PortletResourcePermission portletResourcePermission) {

                _portletResourcePermission = portletResourcePermission;
            }

            private static PortletResourcePermission _portletResourcePermission;

        }

    Note the `@Reference` annotation that tells the OSGi container to supply an
    object via the permission registrar you created previously. The
    `_portletResourcePermission` field is static, while the setter method is an
    instance method: this is how Liferay avoids having service references in
    JSPs. 

The procedure for creating a model permission helper is similar: 

1.  In the same package, create a component class with at least one static
    method for checking permissions. For example, here's the
    `BlogsEntryPermission` class: 

        @Component(immediate = true)
        public class BlogsEntryPermission {

            public static boolean contains(
                    PermissionChecker permissionChecker, BlogsEntry entry,
                    String actionId)
                throws PortalException {

                return _blogsEntryFolderModelResourcePermission.contains(
                    permissionChecker, entry, actionId);
            }

            public static boolean contains(
                    PermissionChecker permissionChecker, long entryId, String actionId)
                throws PortalException {

                return _blogsEntryFolderModelResourcePermission.contains(
                    permissionChecker, entryId, actionId);
            }

            @Reference(
                target = "(model.class.name=com.liferay.blogs.model.BlogsEntry)",
                unbind = "-"
            )
            protected void setEntryModelPermission(
                ModelResourcePermission<BlogsEntry> modelResourcePermission) {

                _blogsEntryFolderModelResourcePermission = modelResourcePermission;
            }

            private static ModelResourcePermission<BlogsEntry>
                _blogsEntryFolderModelResourcePermission;

        }

    As you can see, this class is almost the same as the portlet permission
    class. The real difference is in the `@Reference` annotation that specifies
    the fully qualified class name of the model, rather than the resource name
    from `default.xml`. 

2.  Save both files. 

Now you're ready to use these helper classes to check permissions in your web
module. 

## Add Permission Checks to Your Web Application [](id=add-permission-checks-to-your-web-application)

You can use the permission helper classes to check for permissions before
displaying UI elements. If the element never appears, a user can't access it
(though you should also protect your services as described above). Here's how to
do that: 

1.  When you have a function you want to protect, wrap it in an `if` statement
    that uses the permission helper class. For example, the Blogs application
    has many functions protected by permissions, including `ADD_ENTRY` and
    `SUBSCRIBE`. Clearly, only blog owners should be able to add blog entries.
    The button for this, therefore, should only appear if a user has permission
    to add entries: 

        <c:if test="<%= BlogsPermission.contains(permissionChecker, scopeGroupId, ActionKeys.ADD_ENTRY) %>">
            <div class="button-holder">
                <portlet:renderURL var="editEntryURL" windowState="<%= WindowState.MAXIMIZED.toString() %>">
                    <portlet:param name="mvcRenderCommandName" value="/blogs/edit_entry" />
                    <portlet:param name="redirect" value="<%= currentURL %>" />
                </portlet:renderURL>

                <aui:button href="<%= editEntryURL %>" icon="icon-plus" value="add-blog-entry" />
            </div>
        </c:if>

2.  Do this for any function. For example, the Permissions function you added in
    [step 3](/develop/tutorials/-/knowledge_base/7-1/associating-permissions-with-resources)
    should definitely be protected by permissions: 

        <c:if test="<%= BlogsEntryPermission.contains(permissionChecker, entry, ActionKeys.PERMISSIONS) %>">
            <liferay-security:permissionsURL
                modelResource="<%= BlogsEntry.class.getName() %>"
                modelResourceDescription="<%= BlogsEntryUtil.getDisplayTitle(resourceBundle, entry) %>"
                resourceGroupId="<%= String.valueOf(entry.getGroupId()) %>"
                resourcePrimKey="<%= String.valueOf(entry.getEntryId()) %>"
                var="permissionsEntryURL"
                windowState="<%= LiferayWindowState.POP_UP.toString() %>"
            />

            <liferay-ui:icon
                label="<%= true %>"
                message="permissions"
                method="get"
                url="<%= permissionsEntryURL %>"
                useDialog="<%= true %>"
            />
        </c:if>

    This prevents anyone without the permission to set permissions from seeing
    the permissions button. Say that three times fast! 

That's all there is to it! You've now learned all the steps in *DRAC*: 

1.  Define permissions 

2.  Register permissions

3.  Associate permissions with resources

4.  Check permissions

Follow these steps, and your applications can take advantage of Liferay's
integrated and well-tested permissions system. 

