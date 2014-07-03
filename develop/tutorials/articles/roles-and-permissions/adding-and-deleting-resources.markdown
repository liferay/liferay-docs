# Adding and Deleting Resources 

Liferay provides a system that allows you to implement permissions for your 
custom portlets. The first step in implementing permissions is to define all 
resources and their permissions. Once you've done this, you're ready to register 
these resources in the permissions system. This is also known as adding 
resources.

This tutorial discusses both adding and deleting resources. Liferay's Blogs 
portlet is used as an example. You can find it in Liferay's source code on 
[Github](https://github.com/liferay/liferay-portal). Read on to find out how to 
add and delete resources!

---

 ![Note](../../images/tip-pen-paper.png) **Note:** *Resource* is a generic term 
  for any object represented in the portal. Examples of resources include 
  portlets (e.g. Message Boards, Calendar, etc.), Java classes (e.g. Message 
  Board Topics, Calendar Events, etc.), and files (e.g.  documents, images, 
  etc.). 

---

---

 ![Note](../../images/tip-pen-paper.png) **Note:** *Permission* is an action on 
  a resource. For example, the *view* action with respect to *viewing the 
  calendar portlet* is defined as a permission in Liferay.

---

## Adding a Resource 

Resources are added at the same time entities are added to the database. 
Managing resources follows the same pattern you've seen throughout Liferay: 
there's a service to use. Adding resources is as easy as calling the 
`addResources(...)` method of Liferay's `ResourceLocalServiceUtil` class. Here's 
the signature of that method: 

    public void addResources(
        long companyId, long groupId, long userId, String name,
        String primKey, boolean portletActions,
        boolean addGroupPermissions, boolean addGuestPermissions)

Each entity that requires access permission must be added as a resource every 
time it is stored. For example, every time a user adds a new entry to their 
blog, the `addResources(...)` method must be called to add the new entry object 
to the resource system. Here's an example of the call from the Blogs portlet's 
`BlogsEntryLocalServiceImpl` class: 

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

## Next Steps 

<!-- Change link once header id is generated -->

<!--
[Exposing the Permission Interface to Users](/tutorials/-/knowledge_base/exposing-the-permission-interface-lp-6-2-develop-tutorial) 
-->

[Service Builder](/tutorials/-/knowledge_base/service-builder-lp-6-2-develop-tutorial)

[Localization](/tutorials/-/knowledge_base/localization-lp-6-2-develop-tutorial)
