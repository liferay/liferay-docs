# Setting Permissions Using JSF

To this point, your guestbook application is able to create guestbooks and
guestbook entries and store them as data to the database. However, everyone that
visits your site has the ability to do this, which could present problems as an
administrator. Most of the time, you'll want some of the data in your guestbook
application protected from random users on the web. For instance, you may want
to enable some users to add guestbook entries, while preventing others from
doing so. 

In the first half of this learning path, you'll learn how to implement Liferay's
permissions system in your guestbook portlet following JSF best practices. The
default permissions scheme will allow users to view guestbooks and enter
guestbook entries, while preventing anonymous web-browsing users from doing
either. Also, regular users should not be allowed to add guestbooks, so you'll
implement permissions that only give administrators the ability to create new
guestbooks. 

In the second half of this learning path, you'll dive further into permissioning
with JSF portlets by bolstering a user's permissions options. This will include
adding resources to the Guestbook portlet's service layer and creating new Java
wrapper classes. 

Overall, you'll learn just how easy it is to implement security for a JSF
application in Liferay Portal, and transfer permissioning into your own
applications. Time to get started! 

## Configuring a Basic Permissions Scheme with JSF

The first thing you need to do is create a configuration file that defines
permissions you want inside your application. This main configuration file can
have any name, but as a best practice, is named `default.xml`. This file is the
engine that runs your permissions scheme. All the permission checks that you'll
use in your guestbook application derive their permission definitions from this
file. 

Conveniently, the process of creating your application's `default.xml` file is
identical to the MVC portlet's process. Therefore, you can refer to the learning
path for the MVC portlet to successfully create you configuration file. 

Navigate to the
[Configuring Your Permissions Scheme](/develop/learning-paths/-/knowledge_base/6-2/configuring-your-permissions-scheme)
learning path and follow the instructions beneath the *Defining Permissions For
Your Application*. Once you've completed the section, navigate back to this spot
in the document to continue configuring your permissions scheme for your JSF
guestbook portlet. 

One thing that will be slightly different in this tutorial will be the
permissions for the `com.liferay.docs.guestbook.model.Guestbook` model. In the
`default.xml` file, remove any `ADD_ENTRY` action keys from the Guestbook model
resource. 

Now that your `default.xml` file is created, it's time to begin using the
permission definitions you configured. 

### Updating Your Managed Beans with Permissioning Logic

With your permission definitions configured, its time to introduce those
permissions in your managed beans. To do this, you'll provide getter and setter
methods encapsulating permission properties using Liferay's
[PermissionChecker](https://github.com/liferay/liferay-portal/blob/master/portal-service/src/com/liferay/portal/security/permission/PermissionChecker.java).

Before beginning, review what permission checks you'll implement: 

- The Add Entry button should only be available to site members. 
- The Add Guestbook button should only be available to administrators. 
- The Guestbook tabs should be filtered by permissions in case administrators
  have limited who can see them. 

Since these three checks are related to the UI logic of the `Guestbook` and
`Entry` entities, you'll add your permission logic in the managed beans. Time to
get started! 

1. Open the `EntryBacking` bean and create the following property at the
   beginning of the class: 

        private Boolean hasAddPermission;

    This property will be used to check if a user has permissions to add an
    entry. 

2. Insert the following two methods into your entry bean: 

        public Boolean getHasAddPermission() {

            if (hasAddPermission == null) {
                LiferayFacesContext liferayFacesContext = LiferayFacesContext.getInstance();
                long scopeGroupId = liferayFacesContext.getScopeGroupId();
                hasAddPermission = liferayFacesContext.getThemeDisplay().getPermissionChecker().hasPermission(
                    scopeGroupId, GuestbookBacking.MODEL, scopeGroupId, "ADD_ENTRY"
                    );
            }

            return hasAddPermission;
        }

        public void setHasAddPermission(Boolean hasAddPermission) {
            this.hasAddPermission = hasAddPermission;
        }

    If the `hasAddPermission` property is `null`, the permission checker checks
    the `com.liferay.docs.guestbook.model` model resource you configured in your
    `default.xml` file for the `ADD_ENTRY` action key. It the user's role
    supports `ADD_ENTRY`, then the `hasAddPermission` is returned as `true`.
    Otherwise, the property is returned as `false`. In this case, only site
    members are allowed to add an entry. 

3. Now it's time to configure permissioning in regards to Guestbook interaction.
   Open the `GuestbookBacking` bean and create the following variable and two
   properties at the top of the class: 

        public static final String MODEL = "com.liferay.docs.guestbook.model";
   
        private Boolean hasAddPermission;
        private Boolean hasViewPermission;

    The first property assigns the model resource path to a variable. The last
    two properties will be used to check if a user has permission to add a
    guestbook and view guestbook tabs, respectively. 

4. Insert the following four methods into the `GuestbookBacking` bean: 

        public Boolean getHasAddPermission() {

            if (hasAddPermission == null) {
                LiferayFacesContext liferayFacesContext = LiferayFacesContext.getInstance();
                long scopeGroupId = liferayFacesContext.getScopeGroupId();
                hasAddPermission = liferayFacesContext.getThemeDisplay().getPermissionChecker().hasPermission(
                    scopeGroupId, MODEL, scopeGroupId, "ADD_GUESTBOOK"
                    );
            }

            return hasAddPermission;
        }
	
        public void setHasAddPermission(Boolean hasAddPermission) {
            this.hasAddPermission = hasAddPermission;
        }
	
        public Boolean getHasViewPermission() {

            if (hasViewPermission == null) {
                LiferayFacesContext liferayFacesContext = LiferayFacesContext.getInstance();
                long scopeGroupId = liferayFacesContext.getScopeGroupId();
                hasViewPermission = liferayFacesContext.getThemeDisplay().getPermissionChecker().hasPermission(
                    scopeGroupId, "com.liferay.docs.guestbook.model.Guestbook", scopeGroupId, "VIEW"
                    );
            }

            return hasViewPermission;
        }
	
        public void setHasViewPermission(Boolean hasViewPermission) {
            this.hasViewPermission = hasViewPermission;
        }

    Similar to the getter you added to the entry bean, the `getHasAddPermission`
    checks the `default.xml` file to see if the user's role has permission to
    add a guestbook. In this case, only administrators have this permission. 
    
    The `getHasViewPermission()` method checks a different permission, but its
    permission check is done very similarly to the previous two permission
    checks. In this case, the `VIEW` action key is searched in the
    `com.liferay.docs.guestbook.model.Guestbook` model resource. If the current
    user has the `VIEW` permission for the guestbook, the tab is displayed. 

Excellent! Now it's time to check for the boolean properties in the guestbook
portlet's UI. You'll do that next. 

### Checking for Basic Permissions in JSF Views

You've successfully created methods that can be called to return boolean
properties that distinguish whether or not a user has permissions to view or add
an entity. However, there is currently no way to check for these permissions in
the UI. Next, you'll fully implement the permission checks so your guestbook
portlets data is protected from unwanted viewers. 

1. Open the `docroot/views/master.xhtml` file and find the `<h:commandButton>`
   tags for the Add Guestbook and Add Entry buttons. 

2. For the `<h:commandButton>` tag specifying the Add Guestbook button, add the
   `rendered="#{guestbookBacking.hasAddPermission}"` attribute. The edited tag
   should look like the following: 

        <h:commandButton action="#{guestbookBacking.add}" rendered="#{guestbookBacking.hasAddPermission}"
            styleClass="btn btn-default" value=" #{i18n['add-guestbook']} ">
                <f:ajax render="@all" />
        </h:commandButton>

    The `rendered` attribute forces the button to only render when its boolean
    value is true. The guestbook bean's `hadAddPermission` property is called,
    which specifies whether the user has permission to view the button or not. 

3. For the other `<h:commandButton>` tag specifying the Add Entry button, add
   the `rendered="#{entryBacking.hasAddPermission}"` attribute. The edited
   tag should look like the following: 

        <h:commandButton action="#{entryBacking.add}" rendered="#{entryBacking.hasAddPermission}"
            styleClass="btn btn-default" value=" #{i18n['add-entry']} ">
                <f:ajax render="@all" />
        </h:commandButton>

    Adding this new `rendered` attibute does the exact same permission check as
    before, except it calls the boolean property for adding an entry. 

4. The last permission check you'll need to implement is for the Guestbook tabs.
   In the `docroot/views/master.xhtml` file, add the
   `rendered="#{guestbookBacking.hasViewPermission}"` attribute to the
   `<ui:repeat>` tag. The edited tag should look like the following: 

        <ui:repeat rendered="#{guestbookBacking.hasViewPermission}" value="#{guestbookBacking.guestbooks}" var="guestbook">
            ...
        </ui:repeat>

    This permission check calls the `hasViewPermission` property from the
    guestbook bean. The Guestbook tabs are only rendered if the user has the
    `VIEW` permission for the Guestbook model resource. 

Great! You've implemented all the permission checks in your application. You can
test the permissions by logging in as different users. Remember, administrative
users see all the buttons, regular users see the Add Entry button, and guests
see no buttons. 

In the next section, you'll extend this basic permissions scheme by adding
permissions resources to your service layer and creating new wrapper classes. 

## Extending Your Permissions Scheme with Further Options

Up to this point, you've successfully configured permissions checking for your
Guestbook portlet. However, you're portlet is not currently able to store
permissions to the database. This means, in the future, if you'd like to
configure permissions for each of your portlet's entities, you'll need to add
permissions resources to your service layer when these entries are created. 

Also, you'll extend the permissions scheme by adding methods into new wrapper
classes, as this is a JSF portlet best practice. By doing this, each entity of
your guestbook is wrapped with permissions you add to wrapper classes. This
enables you to easily create actions and permissions for each entity.  

You'll begin this section by adding permissions resources to the Guestbook
portlet's service layer. 

### Adding Permissions Resources to Your JSF Portlet's Service Layer

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
[Adding Permissions Resources to Your Service Layer](/develop/learning-paths/-/knowledge_base/6-2/adding-permissions-resources-to-your-service-layer)
section of the MVC portlet-based learning path for more information on this new
method call, and adding resources to your service layer. 

+$$$

**Note:** Your `-LocalServiceImpl` classes now have errors showing. To take care
of these errors, and propogate these changes to your service layer, re-build
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

### Extending the Permissions Scheme with Wrapper Classes

When extending a portlet's permissions scheme, it's best practice to create
wrapper classes that extend your model entity wrappers. This gives your
permissions methods a unique place to reside. 

You may have noticed when you were creating your guestbook and entry managed
beans that the `com.liferay.docs.guestbook.model.Guestbook` and
`com.liferay.docs.guestbook.model.Entry` objects were used to create and manage
your guestbook and entry entities. The wrapper classes you create in this
section will replace those classes, making permissions resources available in
your managed beans. Before updating your managed beans, you'll need to create
the wrapper classes first. You'll start with creating the `Guestbook` wrapper
class. 

1. In the Guestbook portlet's `docroot/WEB-INF/src` directory, add a new package
   named `com.liferay.docs.guestbook.wrappers`. 

2. Right-click the `com.liferay.docs.guestbook.wrappers` package and select
   *New* &rarr; *Class*. Give it the name `Guestbook` and in the *Superclass*
   field, browse for the `GuestbookWrapper` class. Then click *Finish*. 

    The `GuestbookWrapper` class was generated by Service Builder, and
    implements the guestbook model you were referring to previously. 

3. You'll need to define an explicit constructor since the implicit super
   constructor `GuestbookWrapper()` is undefined for the default constructor.
   Add the following constructor: 

        public Guestbook(com.liferay.docs.guestbook.model.Guestbook guestbook) {
            super(guestbook);
        }

4. Hover your mouse over the `Guestbook` class name and you'll notice a few
   options appear. Select the *Add generated serial version ID*. The following
   variable with a similar ID is added to your class: 

        private static final long serialVersionUID = -420986486105631030L;

    Since this class is *Serializable*, it's best practice to create a serial
    version ID. [Serialization](http://en.wikipedia.org/wiki/Serialization)
    translates the class' state into a format that can be stored and rebuilt on
    your local machine or across a network to other computer environments. 

Your `Guestbook` wrapper class is created. Although it is very bare bones at the
moment, this class gives a good starting place for additional
permissions-related methods to reside. Since you now want your guestbook bean to
use the `Guestbook` wrapper class instead of the `Guestbook` model class, you'll
need to make a few adjustments in your managed bean. 

1. In the `GuestbookBacking` bean, replace the `import
com.liferay.docs.guestbook.model.Guestbook;` statement with `import
com.liferay.docs.guestbook.wrappers.Guestbook;`. 

    You'll notice error markings appear throughout the class. Some error
    markings in your class are related to using a `-Util` class. These code
    statements need to be adjusted to work with your new wrapper class, which
    you'll do next. 

2. Wrap each error marked `-Util` call with `new Guestbook(...)`. For example,
   `GuestbookUtil.create(0L);` should look like `new
   Guestbook(GuestbookUtil.create(0L));`. 
   
    Once you finish doing this to all the `-Util` calls, you'll have two
    remaining errors markings, which will require similar changes. 

3. In the `getGuestbooks()` method, replace `guestbooks.add(guestbook);` with 
   `guestbooks.add(new Guestbook(guestbook));`. 

4. In the `getSelectedGuestbook()` method, replace the following: 

        selectedGuestbook = firstGuestbookByName;

    with:

        selectedGuestbook = new Guestbook(firstGuestbookByName);

Awesome! Your guestbook bean is now using your new `Guestbook` wrapper class!

Next, you'll create the `Entry` wrapper class, which will accomplish the same
things as the `Guestbook` wrapper class, but for entries. 

1. Right-click the `com.liferay.docs.guestbook.wrappers` package and select
   *New* &rarr; *Class*. Give it the name `Entry` and in the *Superclass*
   field, browse for the `EntryWrapper` class. Then click *Finish*. 

2. Define the explicit constructor, similarly to what you did in the `Guestbook`
   class: 

        public Entry(com.liferay.docs.guestbook.model.Entry entry) {
            super(entry);
        }

3. Generate the `serialVersionUID` by hovering your mouse over the class name
   and selecting *Add generated serial version ID*. 

4. Open the `EntryBacking` bean and replace the `import
   com.liferay.docs.guestbook.model.Entry;` statement with `import
   com.liferay.docs.guestbook.wrappers.Entry;`. Repeat this step for the
   `GuestbookBacking` bean, as well. 

5. Just as you did previously, you'll need to modify a few code statements to
   resolve errors dealing with switching to the `Entry` wrapper class from the
   model class. First, in the `EntryBacking` bean's `add()` method, replace
   `Entry entry = EntryUtil.create(0L);` with the following: 

        Entry entry = new Entry(EntryUtil.create(0L));

6. In the `getEntries()` method of the `GuestbookBacking` bean, replace
   `entries.add(entry);` with `entries.add(new Entry(entry));`. 

You've successfully migrated your backing beans from using the model entity to a
wrapper entity! Now each instance of your guestbook and entry entities will be
wrapped with any permissions configured in your new wrapper classes. To take
advantage of your new wrapper classes, you'll add permissions methods to them
relating to each entity. In the next section, you'll test out the extended
permissions scheme by adding a permission to your wrapper classes. 

### Updating the Portlet's UI with Extended Permissions Scheme

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
