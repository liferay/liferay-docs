# Setting Permissions Using JSF

To this point, your guestbook application is able to create guestbooks and
guestbook entries and store them as data to you database. However, everyone that
visits your site has the ability to do this, which could present problems as an
administrator. Most of the time, you'll want some of the data in your guestbook
application protected from random users on the web. For instance, you may want
to enable some users to add guestbook entries, while preventing others from
doing so. 

In this learning path, you'll learn how to implement Liferay's permissions
system in your guestbook portlet following JSF best practices. The default
permissions scheme will allow users to view guestbooks and enter guestbook
entries, while preventing anonymous web-browsing users from doing either. Also,
regular users should not be allowed to add guestbooks, so you'll implement
permissions that only give administrators the ability to create new guestbooks.
You'll learn just how easy it is to implement security for a JSF application in
Liferay Portal, and transfer permissioning into your own applications. 

## Configuring Your Permissions Scheme with JSF

The first thing you need to do is create a configuration file that defines
permissions you want inside your application. This main configuration file can
have any name, but as a best practice, is named `default.xml`. This file is the
engine that runs your permissions scheme. All the permission checks that you'll
use in your guestbook application derive their permission definitions from this
file. 

### Defining Permissions for Your JSF Application

Conveniently, the process of creating your application's `default.xml` file is
identical to the MVC portlet's process. Therefore, you can refer to the learning
path for the MVC portlet to successfully create you configuration file. 

Navigate to the [Configuring Your Permissions Scheme](/develop/learning-paths/-/knowledge_base/6-2/configuring-your-permissions-scheme)
learning path and follow the instructions beneath the *Defining Permissions For
Your Application*. Once you've completed the section, navigate back to this spot
in the document to continue configuring your permissions scheme for your JSF
guestbook portlet. 

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
`Entry` entities, you'll add your permission logic in the backing beans. Time to
get started! 

1. Open the `EntryBackingBean` and create the following property at the
   beginning of the class: 

        private Boolean hasAddPermission;

    This property will be used to check if a user has permissions to add an
    entry. 

2. Insert the following two methods directly after the `edit(...)` method: 

        public Boolean getHasAddPermission() {

            if (hasAddPermission == null) {
                LiferayFacesContext liferayFacesContext = LiferayFacesContext.getInstance();
                long scopeGroupId = liferayFacesContext.getScopeGroupId();
                hasAddPermission = liferayFacesContext.getThemeDisplay().getPermissionChecker().hasPermission(
                    scopeGroupId, "com.liferay.docs.guestbook.model", scopeGroupId, "ADD_ENTRY"
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
   Open the `GuestbookBackingBean` and create the following two properties at
   the top of the class: 

        private Boolean hasAddPermission;
        private Boolean hasViewPermission;

    These two properties will be used to check if a user has permission to add a
    guestbook and view Guestbook tabs, respectively. 

4. Insert the following four methods directly after the `edit(...)` method: 

        public Boolean getHasAddPermission() {

            if (hasAddPermission == null) {
                LiferayFacesContext liferayFacesContext = LiferayFacesContext.getInstance();
                long scopeGroupId = liferayFacesContext.getScopeGroupId();
                hasAddPermission = liferayFacesContext.getThemeDisplay().getPermissionChecker().hasPermission(
                    scopeGroupId, "com.liferay.docs.guestbook.model", scopeGroupId, "ADD_GUESTBOOK"
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

    Similar to the getter you added to the entry backing bean, the
    `getHasAddPermission` checks the `default.xml` file to see if the user's role has permission to add a
    guestbook. In this case, only administrators have this permission. 
    
    The `getHasViewPermission()` method checks a different permission, but its
    permission check is done very similarly to the previous two permission
    checks. In this case, the `VIEW` action key is searched in the
    `com.liferay.docs.guestbook.model.Guestbook` model resource. If the current
    user has the `VIEW` permission for the guestbook, the tab is displayed. 

Excellent! Now it's time to check for the boolean properties in the guestbook
portlet's UI. You'll do that next. 

## Checking for Permissions in JSF Views

You've successfully created methods that can be called to return boolean
properties that distinguish whether or not a user has permissions to view or add
an entity. However, there is currently no way to check for these permissions in
the UI. Next, you'll fully implement the permission checks so your guestbook
portlets data is protected from unwanted viewers. 

1. Open the `docroot/views/master.xhtml` file and find the `<h:commandButton>`
   tags for the Add Guestbook and Add Entry buttons. 

2. For the `<h:commandButton>` tag specifying the Add Guestbook button, add the
   `rendered="#{guestbookBackingBean.hasAddPermission}"` attribute. The edited
   tag should look like the following: 

        <h:commandButton action="#{guestbookBackingBean.add}" rendered="#{guestbookBackingBean.hasAddPermission}"
            styleClass="btn btn-default" value=" #{i18n['add-guestbook']} ">
                <f:ajax render="@all" />
        </h:commandButton>

    The `rendered` attribute forces the button to only render when its boolean
    value is true. The guestbook backing bean's `hadAddPermission` property is
    called, which specifies whether has user has permission to view the button
    or not. 

3. For the other `<h:commandButton>` tag specifying the Add Entry button, add
   the `rendered="#{entryBackingBean.hasAddPermission}"` attribute. The edited
   tag should look like the following: 

        <h:commandButton action="#{entryBackingBean.add}" rendered="#{entryBackingBean.hasAddPermission}"
            styleClass="btn btn-default" value=" #{i18n['add-entry']} ">
                <f:ajax render="@all" />
        </h:commandButton>

    Adding this new `rendered` attibute does the exact same permission check as
    before, except it calls the boolean property for adding an entry. 

4. The last permission check you'll need to implement is for the Guestbook tabs.
   In the `docroot/views/master.xhtml` file, add the
   `rendered="#{guestbookBackingBean.hasViewPermission}"` attribute to the
   `<ui:repeat>` tag. The edited tag should look like the following: 

        <ui:repeat rendered="#{guestbookBackingBean.hasViewPermission}" value="#{guestbookModelBean.guestbooks}" var="guestbook">
            ...
        </ui:repeat>

    This permission check calls the `hasViewPermission` property from the
    guestbook backing bean. The Guestbook tabs are only rendered if the user has
    the `VIEW` permission. 

Great! You've implemented all the permission checks in your application. You can
test the permissions by logging in as different users. Remember, administrative
users see all the buttons, regular users see the Add Entry button, and guests
see no buttons. 

Ready to keep learning about JSF portlets? Next, you'll create action buttons. 
