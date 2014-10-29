# Adding Actions to Your Managed Beans

Just as you added action methods in the
[Creating Managed Beans to Use Services](/learning-paths/-/knowledge_base/6-2/creating-managed-beans-to-use-services)
learning path, you'll do the same for creating action methods for your action
buttons. You'll need to add methods that edit and delete guestbooks and entries,
respectively. The Permissions button will be configured slightly different, and
won't require an action method in the managed beans. You'll start with adding
guestbook entity related methods. 

1. Add the following `delete(Guestbook)` method to your `GuestbookBacking` bean. 

        public void delete(Guestbook guestbook) {

            if (guestbook.getDeleteable()) {

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

                // Go back to the master view
                select(null);
            }
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
    layer to delete all guestbook entries when a guestbook is deleted. 

3. Add the following `edit(Guestbook)` method to your guestbook bean: 

        public void edit(Guestbook guestbook) {
            setSelectedGuestbook(guestbook);
            editGuestbook();
        }

    This method sets the selected guestbook to the one you're viewing and calls
    the `editGuestbook()` method, which redirects the portlet to the `guestbook`
    view to edit the guestbook. 

    Those are the action methods related to your guestbook entities. Next,
    you'll need to add the appropriate methods for your guestbook entry
    entities. 

4. Add the `delete(Entry)` and `edit(Entry)` methods to your `EntryBacking`
   bean: 

        public void delete(Entry entry) {

            try {
                EntryLocalServiceUtil.deleteEntry(entry);
                addGlobalSuccessInfoMessage();
            }
            catch (Exception e) {
                addGlobalUnexpectedErrorMessage();
                logger.error(e);
            }

            guestbookBacking.select(guestbookBacking.getSelectedGuestbook());
        }

        public void edit(Entry entry) {
            guestbookBacking.setSelectedEntry(entry);
            guestbookBacking.editEntry();
        }

    Similar to your guestbook bean, the `delete(Entry)` method uses the
    `deleteEntry(...)` method from your service layer to delete an entry.
    Furthermore, the `edit(Entry)` method redirects the portlet to the
    `entry` view to edit the guestbook entry. 

You've successfully edited your managed beans to support the Edit and Delete
action buttons for both the Guestbook and Entry entities. As you read earlier,
the Permissions button's functionality will be configured in the wrapper
classes. You'll do that next. 

1. Open the `com.liferay.docs.guestbook.wrappers.Guestbook` class and add the
   following variable and property: 

        private static final Logger logger = LoggerFactory.getLogger(Guestbook.class);
   
        private String permissionsUrl;

    The `logger` variable may look familiar to you. This was used in the managed
    beans to log error messages if an exception occurred. It's used the same way
    here. The `permissionsUrl` property will be used in your new method, and
    called in the `master` view. 

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

    ![Figure 1: The permissions URL generates the Permissions menu for the guestbook.](../../images/jsf-permissions-url.png)

    In summary, this method grabs the underlying `HttpServletRequest`,
    `HttpServletResponse`, and `ELContext` to create a
    [`PageContextAdapter`](https://github.com/liferay/liferay-faces/blob/master/util/src/main/java/com/liferay/faces/util/jsp/PageContextAdapter.java),
    invokes the Liferay
    [`PermissionsURLTag`](https://github.com/liferay/liferay-portal/blob/master/util-taglib/src/com/liferay/taglib/security/PermissionsURLTag.java)
    class directly and sets the
    `PageContextAdapter` and various other resources to it, and then writes the
    URL tag string to the `permissionsUrl` property. 

3. Do the same thing for your entries by opening the
   `com.liferay.docs.guestbook.wrappers.Entry` class and adding the following
   variable and property: 

        private static final Logger logger = LoggerFactory.getLogger(Entry.class);
   
        private String permissionsUrl;

4. Add the `getPermissionsUrl()` method to the class: 

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
                permissionsURLTag.setResourcePrimKey(String.valueOf(getEntryId()));

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

All three of your action buttons' action methods are complete. Next, you'll edit
the `master` view to display the buttons in the Guestbook portlet's UI. 
