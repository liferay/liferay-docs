# Defining Portlet Actions and Permissions [](id=defining-portlet-actions-and-permissions)

Now that your Guestbook Admin portlet has the appropriate service methods,
you'll now add portlet actions and permissions to your portlet. The action
methods will be used by each of your action buttons in the Guestbook Admin
portlet. The action methods you create will be similar to those you created in
previous learning paths for guestbook entries. The only difference is, you'll
use these action methods in your new Guestbook Admin portlet. The same goes for
your new portlet's permissions. Time to begin adding action and permissions
methods for your guestbook entities! 

## Adding Action Methods for Guestbook Entities [](id=adding-action-methods-for-guestbook-entities)

Your Guestbook Admin portlet should allow administrators to add, modify, delete,
and control permissions of guestbooks. You've already created the add
functionality, which is currently being displayed in the original Guestbook
portlet. This will be migrated to your new portlet later, but first, you'll
create the three action methods necessary for the action buttons: Edit, Delete,
and Permissions. You'll add your methods to the existing `GuestbookBacking` bean
and `Guestbook` wrapper class. 

1. Open the `GuestbookBacking` bean and add the following `delete` method: 

        public void delete(Guestbook guestbook) {
        
                entries = getEntries();
                deleteGuestbookEntries();

                try {
                    GuestbookLocalServiceUtil.deleteGuestbook(guestbook);
                    addGlobalSuccessInfoMessage();
                }
                catch (Exception e) {
                    addGlobalUnexpectedErrorMessage();
                    logger.error(e);
                }

                // Re-create the Main Guestbook if we just delete the Main Guestbook ...
                if (DEFAULT_GUESTBOOK_NAME.equals(guestbook.getName())) {
                    createMainGuestbook();
                }

                // We just deleted the selected Guestbook so ...
                this.selectedGuestbook = null;

                // Force Guestbooks and entries to reload
                setGuestbooks(null);
                setEntries(null);

                // Go back to the guestbook_actions view
                select(null);
        }

    This method uses the `deleteGuestbook(...)` method you created in your
    service layer to delete the selected guestbook. The method also ensures that
    the Guestbook portlet always has a guestbook displaying by recreating the
    *Main* guestbook if there are no guestbooks available to display. 

2. Add the following method to your guestbook bean: 

        public void deleteGuestbookEntries() {

            for (Entry entry : entries) {

                try {
                    EntryLocalServiceUtil.deleteEntry(entry);
                    addGlobalSuccessInfoMessage();
                }
                catch (Exception e) {
                    addGlobalUnexpectedErrorMessage();
                    logger.error(e);
                }
            }
        }

    This method uses the `deleteEntry(...)` method you created in the service
    layer to delete all guestbook entries when a guestbook is deleted. This
    avoids having orphaned entries stored in the database. 

3. Add the following `edit` method to your guestbook bean: 

        public void edit(Guestbook guestbook) {
            setSelectedGuestbook(guestbook);
            editGuestbook();
        }

    This method sets the selected guestbook and calls the `editGuestbook()`
    method, which redirects the portlet to the `guestbook` view to edit the
    guestbook. 

Terrific! You've created the necessary action methods necessary for the
Guestbook entity's Edit and Delete action buttons. Next, you'll create the
necessary permissions methods to allow for the Permissions button's
functionality. 

## Adding Permissions Methods for Guestbook Entities [](id=adding-permissions-methods-for-guestbook-entities)

The permissions action methods will be set up slightly differently than your
edit and delete action methods. As you did with your Entry entity's permissions,
you'll define the `permissionsUrl` property in the `Guestbook` wrapper class.
Also, you'll define permission checker methods that check if a user has
permissions for the Add Guestbook, Edit, Delete, and Permissions buttons, as
well as viewing the guestbook entity. Since these methods will reside in the
`Guestbook` wrapper class, each `Guestbook` entity defined in your
`GuestbookBacking` bean will be wrapped with these permissions. Once your
`Guestbook` wrapper has the permissions methods, you can call them from a
Guestbook Admin's `view.xhtml` file, which you'll create in the next section. 

1. Open the `com.liferay.docs.guestbook.wrappers.Guestbook` class and add the
   following variable and property: 

        private static final Logger logger = LoggerFactory.getLogger(Guestbook.class);
   
        private String permissionsUrl;

    This is similar to the `Entry` wrapper class' `logger` property. This is
    used in the managed beans to log error messages if an exception occurred.
    The `permissionsUrl` property will be used in your new method, and called in
    a view controlled by the Guestbook Admin portlet. 

2. Add the `getPermissionsUrl()` method to the class: 

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

    Like the `Entry` wrapper class' `getPermissionsUrl` method, this method
    grabs the underlying `HttpServletRequest`, `HttpServletResponse`, and
    `ELContext` to create a
    [`PageContextAdapter`](https://github.com/liferay/liferay-faces/blob/master/util/src/main/java/com/liferay/faces/util/jsp/PageContextAdapter.java),
    invokes the Liferay
    [`PermissionsURLTag`](https://github.com/liferay/liferay-portal/blob/master/util-taglib/src/com/liferay/taglib/security/PermissionsURLTag.java)
    class directly and sets the
    `PageContextAdapter` and various other resources to it, and then writes the
    URL tag string to the `permissionsUrl` property. 

    Next, you'll add three properties and corresponding methods that can be
    called from a Guestbook Admin view file that checks if the user has the
    appropriate permissions to access each of your action buttons. 

3. In the `com.liferay.docs.guestbook.wrappers.Entry` class, add the
   following properties: 

        private Boolean deleteable;
        private Boolean permissible;
        private Boolean updateable;

    These three properties will be used in your permissions methods, and called
    from a Guestbook Admin view file. 

4. Add the following permissions methods directly below your constructor method:

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

    These three methods' returned properties correspond to the three permissions
    that can be granted to users. For example, the `getUpdateable()` method
    checks if the current user has the appropriate permissions to use the Edit
    action button for the guestbook. The method uses the
    [`LiferayFacesContext`](https://github.com/liferay/liferay-faces/blob/master/portal/src/main/java/com/liferay/faces/portal/context/LiferayFacesContext.java)
    to grab the
    [`ThemeDisplay`](https://github.com/liferay/liferay-portal/blob/master/portal-service/src/com/liferay/portal/theme/ThemeDisplay.java),
    and then checks if the user has the appropriate permissions to access the
    button by calling Liferay's
    [`PermissionChecker`](https://github.com/liferay/liferay-portal/blob/master/portal-service/src/com/liferay/portal/security/permission/PermissionChecker.java).
    The `PermissionChecker` scans the Guestbook's model resource to see if the
    current user holds the `UPDATE` action key. If the user's role supports the
    action key, the Edit button is visible and usable; if not, the button is
    invisible to the user. 

    The other two methods work in a very similar way. The `getPermissible()`
    method checks for the Permissions button's permissions, and the
    `getDeleteable()` method checks for the Delete button's permissions. 

Your permissions methods are now in place. The only thing you have left to do is
create a user interface for your Guestbook Admin portlet and utilize the action
and permissions methods you've created. You'll do this next. 
