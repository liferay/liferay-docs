# Adding and Deleting Resources [](id=adding-and-deleting-resources)

Liferay provides a system that allows you to implement permissions for your 
custom portlets. The first step in implementing permissions is to define all 
resources and their permissions. Once you've done this, you're ready to register 
these resources in the permissions system. This step is also known as adding
resources and is the **R** in the **DRAC** acronym:

1. **D**efine all resources and their permissions.

2. **R**egister all defined resources in the permissions system.

3. **A**ssociate the necessary permissions with resources.

4. **C**heck permission before returning resources.

This tutorial discusses both adding and deleting resources. Liferay's Calendar
portlet is used as an example. You can find it in the Liferay Plugins repository
here:
[Liferay Calendar portlet](https://github.com/liferay/liferay-plugins/tree/master/portlets/calendar-portlet).
Read on to find out how to add and delete resources!

Before proceeding, make sure you understand these critical terms:

- *Action*: An operation that can be performed by a portal user. E.g., actions
  that be performed on the Calendar portlet include `ADD_TO_PAGE`,
  `CONFIGURATION`, and `VIEW`. Actions that can be performed on a Calendar
  entity include `DELETE`, `UPDATE`, and `VIEW`. 

- *Resource*: A generic representation of any portlet or entity in the portal on
  which an action can be performed. Resources are used for permission checking.
  E.g., resources within a Liferay portal instance could include the RSS portlet
  with instance ID `hF5f`, a globally scoped Wiki page, a Calendar event of the
  site X, and the message board post with ID `5052`.

- *Permission*: An action that can be performed on a resource. In Liferay's
  database, resources and actions are saved in pairs. (Each entry in the
  `ResourceAction` table contains both the name of a portlet or entity and the
  name of an action.) For example, the `VIEW` action with respect to *viewing
  the Calendar portlet* is associated with the `1_WAR_calendarportlet` portlet
  ID. The `VIEW` actions with respect to *viewing a Calendar* or *viewing a
  Calendar event* are associated with the `com.liferay.calendar.model.Calendar`
  and `com.liferay.calendar.model.Calendar` entities, respectively.

## Adding a Resource 

Resources should be added at the same time entities are added to the database.
Due to Liferay's service-oriented architecture, you must invoke Liferay service
methods to manage Liferay resources. Adding resources is as easy as calling the
`addResources(...)` method of Liferay's `ResourceLocalServiceUtil` class. Here's
the signature of that method: 

    public void addResources(
        long companyId, long groupId, long userId, String name,
        String primKey, boolean portletActions,
        boolean addGroupPermissions, boolean addGuestPermissions)

Each entity that requires access permission must be added as a resource. For
example, every time a user adds a new blog entry, the `addResources(...)` method
must be called to add the new entry object to the resource system. Here's an
example of the call from the Blogs portlet's `BlogsEntryLocalServiceImpl` class: 

    resourceLocalService.addResources(
        entry.getCompanyId(), entry.getGroupId(), entry.getUserId(),
        BlogsEntry.class.getName(), entry.getEntryId(), false,
        addGroupPermissions, addGuestPermissions);

In the `addResources(...)` method, the parameters `companyId`, `groupId`, and
`userId` correspond to the portal instance, the site in which the entity is
being saved, and the user who is saving it. Now take a closer look at the 
remaining parameters: 

- The `name` parameter is the fully qualified Java class name for the 
  entity being added. 
- The `primKey` parameter is the primary key of the entity. 
- The `portletActions` parameter should be set to `true` if you're adding
  portlet action permissions. In this example, it's `false` because a model
  resource is being added, which should be associated with permissions related 
  to the model action defined in `blogs.xml`. 
- The `addGroupPermissions` and the `addGuestPermissions` parameters are inputs
  from the user. If set to `true`, `ResourceLocalService` adds the default
  permissions to the current group and the guest group for this resource,
  respectively. 

You can let your users choose whether to add the default site permission and/or
the guest permission for your custom portlet resources: Liferay has a custom
JSP tag `<liferay-ui:input-permissions />` that you can use to quickly add that
functionality. You just insert the tag into the appropriate JSP and the
checkboxes appear on that page. Make sure that the tag is inside the
appropriate `<form>` tags, and that's all there is to it. 

## Deleting a Resource 

When you remove entities from the database, it's good practice to remove 
permissions mapped directly to the entity. To prevent dead resources from
taking up space in the `Resource_` database table, remember to remove them from
the `Resource_` table when the resource no longer applies. Do this by calling
the `deleteResource(...)` method of `ResourceLocalServiceService`. Here's an
example of a blogs entry being removed: 

    resourceLocalService.deleteResource(
        entry.getCompanyId(), BlogsEntry.class.getName(),
        ResourceConstants.SCOPE_INDIVIDUAL, entry.getEntryId());

Great! Now you know how to work with resource permissions! 

## Related Topics

[Customizing Liferay Portal](/tutorials/-/knowledge_base/6-2/customizing-liferay-portal)

[Plugin Security and PACL](/tutorials/-/knowledge_base/6-2/plugin-security-and-pacl)

[Developing Plugins with Liferay IDE](/tutorials/-/knowledge_base/6-2/liferay-ide)

[User Interfaces with AlloyUI](/tutorials/-/knowledge_base/6-2/alloyui)

[Liferay UI Taglibs](/tutorials/-/knowledge_base/6-2/liferay-ui-taglibs)
