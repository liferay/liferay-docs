# Adding Actions to Your Managed Beans [](id=adding-actions-to-your-managed-beans)

Just as you added action methods in the
[Creating Managed Beans to Use Services](/learning-paths/jsf/-/knowledge_base/6-2/creating-managed-beans-to-use-services)
learning path, you'll do the same for creating action methods for your action
buttons. You'll need to add methods that edit and delete entries. The
Permissions button will be configured slightly different, and won't require an
action method in a managed bean. You'll start with adding entry entity
related methods. 

1. Add the following `delete(Entry)` method to your `EntryBacking` bean: 

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

    This method uses the `deleteEntry(...)` method you created in your service
    layer to delete an entry. 

2. Add the following `edit(Entry)` method to your entry bean: 

        public void edit(Entry entry) {
            guestbookBacking.setSelectedEntry(entry);
            guestbookBacking.editEntry();
        }

    The method sets the selected entry and calls the `editEntry()` method, which
    redirects the portlet to the `entry` view to edit the entry. 

You've successfully edited your entry bean to support the Edit and Delete
action buttons for Entry entity. As you read earlier, the Permissions button's
functionality will be configured in the entry wrapper class. You'll do that
next. 

1. Open the `com.liferay.docs.guestbook.wrappers.Entry` class and add the
   following variable and property: 

        private static final Logger logger = LoggerFactory.getLogger(Entry.class);
   
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

    This method generates the permissions URL used when clicking the entry
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

All three of your action buttons' action methods are complete. Next, you'll edit
the `master` view to display the buttons in the Guestbook portlet's UI. 
