### Adding a Resource 

After defining resources and actions, it's time to add resources into the
permissions system. Resources are added at the same time entities are added to
the database. Managing resources follows the same pattern you've seen throughout
Liferay: there's a service to use. Adding resources is as easy as calling the
`addResources(...)` method of the `ResourceLocalServiceUtil` class. Here's the
signature of that method: 

    public void addResources(
        long companyId, long groupId, long userId, String name,
        String primKey, boolean portletActions,
        boolean addGroupPermissions, boolean addGuestPermissions)

Each entity that requires access permission must be added as a resource
every time it is stored. For example, every time a user adds a new
entry to her blog, the `addResources(...)` method must be called to add the
new entry object to the resource system. Here's an example of the call from the
`BlogsEntryLocalServiceImpl` class: 

    resourceLocalService.addResources(
        entry.getCompanyId(), entry.getGroupId(), entry.getUserId(),
        BlogsEntry.class.getName(), entry.getEntryId(), false,
        addGroupPermissions, addGuestPermissions);

In the `addResources(...)` method, the parameters `companyId`, `groupId`, and
`userId` correspond to the portal instance, the site in which the entity is
being saved, and the user who is saving it. Let's look more closely at the
remaining parameters: 

- The `name` parameter is the fully qualified Java class name for the 
  entity being added. 
- The `primKey` parameter is the primary key of the entity. 
- The `portletActions` parameter should be set to `true` if you're adding
  portlet action permissions. In our example, it's `false` because we're adding
  a model resource, which should be associated with permissions related to the
  model action defined in `blogs.xml`. 
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

When you remove entities from the database, it's good practice to remove
permissions mapped directly to the entity. To prevent dead resources from
taking up space in the `Resource_` database table, remember to remove them from
the `Resource_` table when the resource no longer applies. Do this by calling
the `deleteResource(...)` method of `ResourceLocalServiceService`. Here's an
example of a blogs entry being removed: 

    resourceLocalService.deleteResource(
        entry.getCompanyId(), BlogsEntry.class.getName(),
        ResourceConstants.SCOPE_INDIVIDUAL, entry.getEntryId());

Great! Now that you know how to work with resource permissions, we'll show you
how to provide a user interface for managing resource permissions. 
