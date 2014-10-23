# Creating Entity Actions Using JSF

To this point, you've created a JSF guestbook application that uses a database
to store its entries. Also, the application is integrated with Liferay's
permissions mechanism, allowing you to control who can access different parts of
the portlet. But what about managing the guestbooks and entries themselves? There is
currently no way to edit, delete, or set permissions on individual entities. 

In this learning path, you'll create portlet actions to edit entities,
set permissions for entities, and delete entities. You'll use many of the same
concepts you've already been introduced to, like adding action methods to a
managed bean and adding buttons that trigger those methods using a JSF view. 

![Figure 1: In this learning path, you'll add the *Edit*, *Permissions*, and *Delete* buttons for your *Guestbook* and *Entry* entities.](../../images/edit-permissions-delete.png)

By providing entity actions, your users possess complete control over their
guestbook and entries they create. 

## Modifying Your Service Layer for Entity Actions

Just as you did in the previous learning path on setting permissions, you'll
need to modify your service layer. Your current Guestbook portlet is only
allowed to add guestbooks and their resources, but you have no way of deleting
them. To be able to create an action method to delete entities, you'll need to
provide a delete method in your service layer. You'll do this next by editing
your `*LocalServiceImpl` classes. 

1. Open your `EntryLocalServiceImpl` class and add the following method: 

        @Override
        public Entry deleteEntry(Entry entry) throws PortalException, SystemException {

            resourceLocalService.deleteResource(entry.getCompanyId(), Entry.class.getName(),
                ResourceConstants.SCOPE_INDIVIDUAL, entry.getPrimaryKey());

            return super.deleteEntry(entry);
        }

    Similar to your `addEntry(...)` method, this method deletes the entry's
    resources, and then deletes the entry. 

2. Open your `GuestbookLocalServiceImpl` class and add the `deleteGuestbook`
   method. 

        @Override
        public Guestbook deleteGuestbook(Guestbook guestbook) throws PortalException, SystemException {

            resourceLocalService.deleteResource(guestbook.getCompanyId(), Guestbook.class.getName(),
                ResourceConstants.SCOPE_INDIVIDUAL, guestbook.getPrimaryKey());

            return super.deleteGuestbook(guestbook);
        }

    Similar to the `deleteEntry(Entry) method, this method deletes guestbooks
    and their resources. 

3. Build your Guestbook portlet's services by right-clicking the project and
   selecting *Liferay* &rarr; *Build Services*. 

Now that your service layer has all the required methods for adding entity
actions, it's time to begin creating action methods in your managed beans that
your action buttons will use. 

## Adding Actions to Your Managed Beans

## Adding Permissions Methods to Your Wrapper Classes

Recall from the previous learning path when you created wrapper classes to hold
permissions methods. You also updated your managed beans to call those wrapped
entities. By doing this, the guestbook and entry entities you use in your
managed beans are wrapped with the permissions you create in the wrapper
classes. 

At the moment, you have bare wrapper classes with no permissions methods. You'll
change this predicament by adding three properties and corresponding methods
that can be called from your XHTML files that check if the user has the
appropriate permissions to access each of your action buttons. You'll also
provide an additional property and method that generates the permissions URL for
managing an entity's permissions. It's time to expand your Guestbook portlet's
permissioning! 

1. In the `com.liferay.docs.guestbook.wrappers.Guestbook` class, add the
   following variable and properties: 

        private static final Logger logger = LoggerFactory.getLogger(Guestbook.class);

        private Boolean deleteable;
        private Boolean permissible;
        private String permissionsUrl;
        private Boolean updateable;

    The `logger` variable may look familiar to you. This was used in the managed
    beans to log error messagees if an exception occured. It's used the same way
    here. Also, the four properties will be used in your permissions methods,
    and called from the `master` view. 

2. Add the following permissions methods directly below your constructor method:

        public Boolean getDeleteable() {

            if (deleteable == null) {
                LiferayFacesContext liferayFacesContext = LiferayFacesContext.getInstance();
                long scopeGroupId = liferayFacesContext.getScopeGroupId();
                deleteable = liferayFacesContext.getThemeDisplay().getPermissionChecker().hasPermission(scopeGroupId,
                    MODEL, getGuestbookId(), ActionKeys.DELETE);
            }

            return deleteable;
        }

        public Boolean getPermissible() {

            if (permissible == null) {
                LiferayFacesContext liferayFacesContext = LiferayFacesContext.getInstance();
                long scopeGroupId = liferayFacesContext.getScopeGroupId();
                permissible = liferayFacesContext.getThemeDisplay().getPermissionChecker().hasPermission(scopeGroupId,
                    MODEL, getGuestbookId(), ActionKeys.PERMISSIONS);
            }

            return permissible;
        }

        public Boolean getUpdateable() {

            if (updateable == null) {
                LiferayFacesContext liferayFacesContext = LiferayFacesContext.getInstance();
                long scopeGroupId = liferayFacesContext.getScopeGroupId();
                updateable = liferayFacesContext.getThemeDisplay().getPermissionChecker().hasPermission(scopeGroupId,
                    MODEL, getGuestbookId(), ActionKeys.UPDATE);
            }

            return updateable;
        }

    These three methods' returned properties correspond to three permissions
    that can be granted to users. For example, the `getDeleteable()` method
    checks if the current user has the appropriate permissions to use the Delete
    action button for the guestbook entity. The method uses the
    [`LiferayFacesContext`](https://github.com/liferay/liferay-faces/blob/master/portal/src/main/java/com/liferay/faces/portal/context/LiferayFacesContext.java)
    to grab the
    [`ThemeDisplay`](https://github.com/liferay/liferay-portal/blob/master/portal-service/src/com/liferay/portal/theme/ThemeDisplay.java),
    and then checks if the user has the appropriate permissions to access the
    button by calling Liferay's
    [`PermissionChecker`](https://github.com/liferay/liferay-portal/blob/master/portal-service/src/com/liferay/portal/security/permission/PermissionChecker.java).
    The `PermissionChecker` scans the Guestbook's model resource to see if the
    current user holds the `DELETE` action key. If the user's role supports the
    action key, the Delete button is visible and usable; if not, the button is
    invisible to the user. 

    The other two methods work in a very similar way. The `getPermissible()`
    method checks for the Permissions button's permissions, and the
    `getUpdateable()` method checks for the Edit button's permissions. 

3. You'll need to add one more method, which generates the permissions URL for
   the Permissions button: 

        public String getPermissionsUrl() {

            if (permissionsUrl == null) {

                LiferayFacesContext liferayFacesContext = LiferayFacesContext.getInstance();
                ExternalContext externalContext = liferayFacesContext.getExternalContext();
                long scopeGroupId = liferayFacesContext.getScopeGroupId();

                // Get the underlying HttpServletRequest and HttpServletResponse
                PortletRequest portletRequest = (PortletRequest) externalContext.getRequest();
                HttpServletRequest httpServletRequest = PortalUtil.getHttpServletRequest(portletRequest);
                PortletResponse portletResponse = (PortletResponse) externalContext.getResponse();
                HttpServletResponse httpServletResponse = PortalUtil.getHttpServletResponse(portletResponse);
                ELContext elContext = liferayFacesContext.getELContext();
                StringJspWriter stringJspWriter = new StringJspWriter();
                PageContextAdapter pageContextAdapter = new PageContextAdapter(httpServletRequest, httpServletResponse,
                    elContext, stringJspWriter);

                // Invoke the Liferay Tag class directly (rather than using the tag from a JSP).
                PermissionsURLTag permissionsURLTag = new PermissionsURLTag();

                permissionsURLTag.setPageContext(pageContextAdapter);
                permissionsURLTag.setModelResource(MODEL);
                permissionsURLTag.setModelResourceDescription(getName());
                permissionsURLTag.setRedirect("false");
                permissionsURLTag.setResourceGroupId(scopeGroupId);
                permissionsURLTag.setResourcePrimKey(String.valueOf(getGuestbookId()));

                // Set var to null if you want the tag to write out the url
                permissionsURLTag.setVar(null);

                try {
                    permissionsURLTag.doStartTag();
                    permissionsURLTag.doEndTag();
                    permissionsUrl = stringJspWriter.toString();
                }
                catch (Exception e) {
                    logger.error(e);
                }

            }

            return permissionsUrl;
        }

    This method generates the permissions URL used when clicking the guestbook
    entity's Permissions button. 

    ![Figure 2: The permissions URL generates the Permissions menu for the guestbook.](../../images/jsf-permissions-url.png)




















