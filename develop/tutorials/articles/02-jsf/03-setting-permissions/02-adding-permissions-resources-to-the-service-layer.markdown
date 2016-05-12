# Adding Permissions Resources to the Service Layer [](id=adding-permissions-resources-to-the-service-layer)

Up to this point, you've successfully configured permissions checking for your
Guestbook portlet. However, you're portlet is not currently able to store
permissions to the database. This means, in the future, if you'd like to
configure permissions for each of your portlet's entities, you'll need to add
permissions resources to your service layer when these entries are created. 

In Liferay's back end, permissions are referred to as *resources*. Liferay
provides an API for managing resources, which you can take advantage of. Since
you're using Service Builder for your portlet already, this API is already
injected into your implementation classes automatically. Now all you need to do
is use it. 

1. In your `GuestbookLocalServiceImpl` class, find and replace the
   `addGuestbook` method with the following new method: 

        @Override
        public Guestbook addGuestbook(Guestbook guestbook, long userId) throws PortalException, SystemException {
            long guestbookId = counterLocalService.increment(Guestbook.class.getName());
            guestbook.setGuestbookId(guestbookId);

            guestbook = super.addGuestbook(guestbook);

            resourceLocalService.addResources(guestbook.getCompanyId(), guestbook.getGroupId(), userId,
                Guestbook.class.getName(), guestbookId, false, true, true);

            return guestbook;
        }

2. In your `EntryLocalServiceImpl` class, find and replace the `addEntry` method
   with the following new method: 

        @Override
        public Entry addEntry(Entry entry, long userId) throws PortalException, SystemException {
            long entryId = counterLocalService.increment(Entry.class.getName());
            entry.setEntryId(entryId);

            entry = super.addEntry(entry);

            resourceLocalService.addResources(entry.getCompanyId(), entry.getGroupId(), userId, Entry.class.getName(),
                entryId, false, true, true);

            return entry;
        }

You'll notice in both of these updated methods, you call the `addResources(...)`
method from the `resourceLocalService` class. Visit the
[Adding Permissions Resources to Your Service Layer](/develop/learning-paths/mvc/-/knowledge_base/6-2/adding-permissions-resources-to-your-service-layer)
section of the MVC portlet-based learning path for more information on this new
method call, and adding resources to your service layer. 

+$$$

**Note:** Your `-LocalServiceImpl` classes now have errors showing. To take care
of these errors, and propagate these changes to your service layer, re-build
your services by right-clicking your project and selecting *Liferay* &rarr;
*Build Services*. 

$$$

Now you'll need to edit your managed beans where these two updated methods are
referenced, since a new `userId` parameter was added to the method signatures
for the `addGuestbook` and `addEntry` methods. 

Open the `GuestbookBacking` bean and, in the `save()` method, replace the
`guestbook = GuestbookLocalServiceUtil.addGuestbook(guestbook);` code with the
following: 

    guestbook = GuestbookLocalServiceUtil.addGuestbook(guestbook,
        liferayFacesContext.getUserId());

Similarly, open the `EntryBacking` bean and, in the `save()` method, replace the
`EntryLocalServiceUtil.addEntry(entry);` code with the following: 

    EntryLocalServiceUtil.addEntry(entry, liferayFacesContext.getUserId());

If you decide to check permissions for each of your portlet's created entities,
you'll now be able to do so. In the next learning path for creating action
buttons, accessing permissions for an individual entity relies on adding
resources in your portlet's service layer. 

Next, you'll learn how to extend your permissions scheme to wrapper classes. 



## Updating the Portlet's UI with Extended Permissions Scheme [](id=updating-the-portlets-ui-with-extended-permissions-scheme)

Now that you have your permissions scheme configured to handle individual
entities, it's time to add a permissions method that can be called from your
`master` view. You'll create a method that checks a user's permissions for the
`VIEW` action key, or the authorization to view an entity. You'll begin with
implementing this permission check in the guestbook. 

1. In the `com.liferay.docs.guestbook.wrappers.Guestbook` class, add the
   following variable and property: 

        private static final String MODEL = "com.liferay.docs.guestbook.model.Guestbook";

        private Boolean viewable;

    The `MODEL` variable represents the model resource and its permissions
    properties you set in the `resource-actions/default.xml` file for
    `Guestbook`. The `viewable` property will be used in your permissions
    method, and called in the `master` view. 

2. Add the following permissions method that checks for the `VIEW` permission: 

        public Boolean getViewable() {

            if (viewable == null) {
                LiferayFacesContext liferayFacesContext = LiferayFacesContext.getInstance();
                long scopeGroupId = liferayFacesContext.getScopeGroupId();
                viewable = liferayFacesContext.getThemeDisplay().getPermissionChecker().hasPermission(scopeGroupId,
                    MODEL, getGuestbookId(), ActionKeys.VIEW);
            }

            return viewable;
        }

    This method's returned `viewable` property corresponds to the `VIEW`
    permission that can be granted to users. The `getViewable()` method checks
    if the current user has the appropriate permissions to view the guestbook
    entity.
    
    The method uses the
    [`LiferayFacesContext`](https://github.com/liferay/liferay-faces/blob/master/portal/src/main/java/com/liferay/faces/portal/context/LiferayFacesContext.java)
    to grab the
    [`ThemeDisplay`](https://github.com/liferay/liferay-portal/blob/master/portal-service/src/com/liferay/portal/theme/ThemeDisplay.java),
    and then checks if the user has the appropriate permissions to view the
    guestbook by calling Liferay's
    [`PermissionChecker`](https://github.com/liferay/liferay-portal/blob/master/portal-service/src/com/liferay/portal/security/permission/PermissionChecker.java).
    The `PermissionChecker` scans the guestbook's model resource to see if the
    current user holds the `VIEW` action key. If the user's role supports the
    action key, the guestbook is visible; if not, the guestbook is invisible to
    the user. 

3. Now you'll need to check for the `viewable` permission in your `master` view.
   Open the `master.xhtml` file and, directly after the opening `<ui:repeat>`
   tag, insert `<h:panelGroup rendered="#{guestbook.viewable}">`. Then after the
   next closest closing `</span>` tag insert a closing `</h:panelGroup>` tag. 

    Each user that attempts to view a guestbook is now checked for the `VIEW`
    permission. Checking for the new `viewable` property and the
    `hasViewPermission` you added in the previous learning path for all
    guestbook tabs is excessive. Now that you're able to check for each
    guestbook's viewability, you no longer need to check for the outdated
    `hasViewPermission`, which makes one check for all the guestbook entities. 

4. Remove the `rendered="#{guestbookBacking.hasViewPermission}"` attribute from
   the opening `<ui:repeat>` tag. 

Fantastic! You've taken advantage of your extended permission scheme by checking
the `VIEW` permission for each guestbook entity. Now you'll provide the same
capability for each guestbook entry. 

1. In the `com.liferay.docs.guestbook.wrappers.Entry` class, add the
   following variable and property: 

        private static final String MODEL = "com.liferay.docs.guestbook.model.Entry";

        private Boolean viewable;

2. Add the following permissions method: 

        public Boolean getViewable() {

            if (viewable == null) {
                LiferayFacesContext liferayFacesContext = LiferayFacesContext.getInstance();
                long scopeGroupId = liferayFacesContext.getScopeGroupId();
                viewable = liferayFacesContext.getThemeDisplay().getPermissionChecker().hasPermission(scopeGroupId,
                    MODEL, getEntryId(), ActionKeys.VIEW);
            }

            return viewable;
        }

3. Now you'll need to check your `master` view for the `viewable` property. Open
   the `master.xhtml` file. Inside the `<h:dataTable>...</h:dataTable>` tags,
   find both `<h:outputText />` tags and surround both of them with
   `<h:panelGroup>...</h:panelGroup>` tags. For each `<h:panelGroup>` tag,
   add the `rendered="#{entry.viewable}"` attribute. Your data table should now
   look like the following: 

        <h:dataTable styleClass="table table-bordered table-hover table-striped" rowClasses="table-cell "
            value="#{guestbookBacking.entries}" var="entry">
            <h:column>
                <f:facet name="header"><h:outputText value="#{i18n['message']}" /></f:facet>
                <h:panelGroup rendered="#{entry.viewable}">
                    <h:outputText value="#{entry.message}" />
                </h:panelGroup>
            </h:column>
            <h:column>
                <f:facet name="header"><h:outputText value="#{i18n['name']}" /></f:facet>
                <h:panelGroup rendered="#{entry.viewable}">
                    <h:outputText value="#{entry.name}" />
                </h:panelGroup>
            </h:column>
        </h:dataTable>

Congratulations! Both your entities are now using the new extended permissions
scheme to check for user `VIEW` permissions. This permissions scheme becomes
even more powerful when you can take advantage of checking the permissions for
each entity by accessing each entity's permissions menu. You'll configure this
in the next learning path. 
