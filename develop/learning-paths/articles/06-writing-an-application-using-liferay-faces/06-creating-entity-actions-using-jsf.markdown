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

    You probably noticed there are a couple error markings. The
    `getDeleteable()` method will be created in your `Guestbook` wrapper class
    to check for `DELETE` permissions, and the `deleteGuestbookEntries()` method
    you'll create in the next step. 

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
    beans to log error messagees if an exception occured. It's used the same way
    here. The `permissionsUrl` property will be used in your new method, and
    called in the `master` view. 

2. Add the `getPermissionsUrl()` method to the class, above the `getViewable()`
   method. 

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

4. Add the `getPermissionsUrl()` method to the class, just below the constructor
   method. 

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

## Creating Action Buttons Using JSF

Now that you have the power to edit and delete entities, it's time to expose
that capability to your users through your JSF views. This will be a similar
process to when you created the Add Guestbook and Add Entry buttons. All of your
action buttons will display in the `master` view, so you'll only edit that
particular view. You'll begin with adding the guestbook action views. 

1. Open the `master.xhtml` file. Directly below the `<br />` tag under the
   command buttons for adding a guestbook and entry, add the following code to
   create your guestbook's action buttons: 

        <div id="guestbook_buttons" style="display: inline-block">
            <h:commandButton action="#{guestbookBacking.edit(guestbookBacking.selectedGuestbook)}" styleClass="btn btn-default" value=" #{i18n['edit']} ">
                <f:ajax render="@all" />
            </h:commandButton>
            <h:commandButton action="#{guestbookBacking.delete(guestbookBacking.selectedGuestbook)}" styleClass="btn btn-default" value=" #{i18n['delete']} "
                onclick="if (! confirm('#{i18n['are-you-sure-you-want-to-delete-this']}')) {return false;}" >
                <f:ajax render="@all" />
            </h:commandButton>
            <h:outputLink styleClass="btn btn-default" value="#{guestbookBacking.selectedGuestbook.permissionsUrl}">
                <h:outputText value=" #{i18n['permissions']} " />
            </h:outputLink>
		</div>

    All three of your action buttons for guestbooks are now available in your
    portlet. They are formatted as an `inline-block`. Also notice that each
    button calls the `GuestbookBacking` bean's `selectedGuestbook` property,
    which represents the current guestbook entity you've selected to perform an
    action on. 

    +$$$
    
    **Note:** Currently, the drop-down *Actions* button used in many MVC
    portlets is not supported in Liferay Faces. If your unfamiliar with MVC
    portlets' Actions button, visit the
    [Creating Action Buttons](/develop/learning-paths/-/knowledge_base/6-2/creating-action-buttons)
    learning path to learn more about its functionality. 

    $$$

2. Find the following `<h:column>...</h:column>` tag: 

        <h:column>
            <f:facet name="header"><h:outputText value="#{i18n['name']}" /></f:facet>
            <h:outputText value="#{entry.name}" />
        </h:column>

    Add the entry entity's action buttons directly below the above
    `<h:column>...</h:column>` tag: 

        <h:column>
            <f:facet name="header"><h:outputText value=" " /></f:facet>
            <h:commandButton action="#{entryBacking.edit(entry)}" styleClass="btn btn-default" value=" #{i18n['edit']} ">
                <f:ajax render="@all" />
            </h:commandButton>
            <h:commandButton action="#{entryBacking.delete(entry)}" styleClass="btn btn-default" value=" #{i18n['delete']} "
                onclick="if (! confirm('#{i18n['are-you-sure-you-want-to-delete-this']}')) {return false;}" >
                <f:ajax render="@all" />
            </h:commandButton>
            <h:outputLink styleClass="btn btn-default" value="#{entry.permissionsUrl}">
                <h:outputText value=" #{i18n['permissions']} " />
            </h:outputLink>
        </h:column>

    These buttons work in the exact same way as the guestbook entity action
    buttons, but are formatted a bit differently to fit with entries listed in a
    table. 

Your buttons are now ready for use! Redeploy your Guestbook portlet and give
them a shot! The Edit and Delete buttons should function as expected. The
Permissions button for your entities should display the Permissions menu, but
none of the permissions should work yet. 

You'll fix this by adding the remainder of the permissions methods to your
wrapper classes. 

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
   following properties: 

        private Boolean deleteable;
        private Boolean permissible;
        private Boolean updateable;

    These three properties will be used in your permissions methods, and called
    from the `master` view. 

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

3. Repeat step 1 for the `com.liferay.docs.guestbook.wrappers.Entry` class. The
   properties are identical for both wrapper classes. 

4. In the `Entry` wrapper class, add the following permissions methods directly
   below your constructor method: 

        public Boolean getDeleteable() {

            if (deleteable == null) {
                LiferayFacesContext liferayFacesContext = LiferayFacesContext.getInstance();
                long scopeGroupId = liferayFacesContext.getScopeGroupId();
                deleteable = liferayFacesContext.getThemeDisplay().getPermissionChecker().hasPermission(scopeGroupId,
                    MODEL, getEntryId(), ActionKeys.DELETE);
            }

            return deleteable;
        }

        public Boolean getPermissible() {

            if (permissible == null) {
                LiferayFacesContext liferayFacesContext = LiferayFacesContext.getInstance();
                long scopeGroupId = liferayFacesContext.getScopeGroupId();
                permissible = liferayFacesContext.getThemeDisplay().getPermissionChecker().hasPermission(scopeGroupId,
                    MODEL, getEntryId(), ActionKeys.PERMISSIONS);
            }

            return permissible;
        }

        public Boolean getUpdateable() {

            if (updateable == null) {
                LiferayFacesContext liferayFacesContext = LiferayFacesContext.getInstance();
                long scopeGroupId = liferayFacesContext.getScopeGroupId();
                updateable = liferayFacesContext.getThemeDisplay().getPermissionChecker().hasPermission(scopeGroupId,
                    MODEL, getEntryId(), ActionKeys.UPDATE);
            }

            return updateable;
        }

4. Now that your wrapper classes hold the necessary permission check methods and
   properties, you'll check for them in your `master` view. First, for each of
   your guestbook buttons you just created, surround each individual
   `<h:commandButton>...</h:commandButton>` tag with the
   `<h:panelGroup>...</h:panelGroup>` tag. Each panel group tag should contain
   the `rendered` element, specifying the appropriate permission to check for
   each action button. Below is how you guestbook buttons should look after
   you're finished: 

        <div id="guestbook_buttons" style="display: inline-block">
            <h:panelGroup rendered="#{guestbookBacking.selectedGuestbook.updateable}">
                <h:commandButton action="#{guestbookBacking.edit(guestbookBacking.selectedGuestbook)}" styleClass="btn btn-default" value=" #{i18n['edit']} ">
                    <f:ajax render="@all" />
                </h:commandButton>
            </h:panelGroup>
            <h:panelGroup rendered="#{guestbookBacking.selectedGuestbook.deleteable}">
                <h:commandButton action="#{guestbookBacking.delete(guestbookBacking.selectedGuestbook)}" styleClass="btn btn-default" value=" #{i18n['delete']} "
                    onclick="if (! confirm('#{i18n['are-you-sure-you-want-to-delete-this']}')) {return false;}" >
                    <f:ajax render="@all" />
                </h:commandButton>
            </h:panelGroup>
            <h:panelGroup rendered="#{guestbookBacking.selectedGuestbook.permissible}">
                <h:outputLink styleClass="btn btn-default" value="#{guestbookBacking.selectedGuestbook.permissionsUrl}">
                    <h:outputText value=" #{i18n['permissions']} " />
                </h:outputLink>
            </h:panelGroup>
        </div>

5. Complete the same process for the entry action buttons. The entries are
   displayed slightly differently than the guestbooks, so you won't need to
   check permissions for the *selected* entry, but rather, just `entry`. Your
   updated entry action buttons code should appear like the code snippet below: 

        <h:column>
            <f:facet name="header"><h:outputText value=" " /></f:facet>
            <h:panelGroup rendered="#{entry.updateable}">
                <h:commandButton action="#{entryBacking.edit(entry)}" styleClass="btn btn-default" value=" #{i18n['edit']} ">
                    <f:ajax render="@all" />
                </h:commandButton>
            </h:panelGroup>
            <h:panelGroup rendered="#{entry.deleteable}">
                <h:commandButton action="#{entryBacking.delete(entry)}" styleClass="btn btn-default" value=" #{i18n['delete']} "
                    onclick="if (! confirm('#{i18n['are-you-sure-you-want-to-delete-this']}')) {return false;}" >
                    <f:ajax render="@all" />
                </h:commandButton>
            </h:panelGroup>
            <h:panelGroup rendered="#{entry.permissible}">
                <h:outputLink styleClass="btn btn-default" value="#{entry.permissionsUrl}">
                    <h:outputText value=" #{i18n['permissions']} " />
                </h:outputLink>
            </h:panelGroup>
        </h:column>

Congratulations! You've implemented custom actions for your entities. Users can
now manage an entity's full life cycle: adding, editing, modifying the
permissions of, and deleting an entity. 
