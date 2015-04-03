# Adding and Deleting Resources [](id=adding-and-deleting-resources)

Liferay provides a system that allows you to implement permissions for your
custom portlets. The first step in implementing permissions is to define all
resources and the actions that can be performed on them. Once you've done this,
you're ready to register these resource-action pairs in the permissions system.
In Liferay, a permission is represented as a resource-action pair. Registering
permissions with Liferay is also known as adding resources. This step is the
**R** in the **DRAC** acronym:

1. **D**efine all resources and their permissions.

2. **R**egister all defined resources in the permissions system.

3. **A**ssociate the necessary permissions with resources.

4. **C**heck permission before returning resources.

This tutorial explains how to both add and delete resources. The Guestbook
project is used as an example. You can find it in the Liferay Docs repository
here:
[Liferay Guestbook project](https://github.com/liferay/liferay-docs/tree/6.2.x/develop/learning-paths/mvc/code/learning-sdk/portlets/guestbook-portlet).
Read on to find out how to add and delete resources!

Before proceeding, make sure you understand these critical terms:

**Action**: An operation that can be performed by a portal user.

**Resource**: A generic representation of any portlet or entity in the portal on
which an action can be performed.

**Permission**: An action that can be performed on a resource.

For further explanation and examples, please see the
[Adding Permissions to Resources](/develop/tutorials/-/knowledge_base/6-2/using-portal-roles-in-a-portlet) tutorial.

## Adding a Resource [](id=adding-a-resource)

Resources should be added at the same time that entities are added to the
database. To do this, you must invoke Liferay service methods to manage Liferay
resources. Adding resources is as easy as calling the `addResources(...)` method
of Liferay's `ResourceLocalServiceUtil` class. Here's the signature of that
method: 

    public void addResources(
        long companyId, long groupId, long userId, String name,
        String primKey, boolean portletActions,
        boolean addGroupPermissions, boolean addGuestPermissions)

Here's an overview of the parameters of the `addResources` method:

`companyId`: the primary key of the entity's portal instance

`groupId`: the primary key of the entity's site

`userId`: the primary key of the user who's adding the entity

`name`: the fully qualified Java class name for the entity being added

`primKey`: the primary key of the entity

`portletActions`: a boolean parameter that should be set to `true` if you're
adding portlet action permissions

`addGroupPermissions`: a boolean parameter that should be set to `true` if the
default permissions should be added to the current group

`addGuestPermissions` a boolean parameter that should be set to `true` if the
default permissions should be added to the guest group

Any entity that should have permission-controlled actions must be added as a
resource. For example, every time a user adds a new guestbook, call the
`addResources(...)` method to add a corresponding resource to the resource
system. Here's the call from the Guestbook project's `GuestbookLocalServiceImpl`
class: 

    resourceLocalService.addResources(
        user.getCompanyId(), groupId, userId, Guestbook.class.getName(),
        guestbookId, false, true, true);

In this example, `portletActions` is `false` because a model resource, not
a portlet resource, is being added. `addGroupPermissions` and
`addGuestPermissions` are `true` since the default permissions should be
applied.

You can let your users choose whether to add the default group permissions
and/or the default guest permissions for your portlet resources: Liferay has a
JSP tag called `<liferay-ui:input-permissions />` that you can use to add that
functionality. To use it, insert the tag into the appropriate JSP, and the
checkboxes appear on that page. Make sure that the tag is inside the appropriate
`<form>` tags. 

To see an example of the `<liferay-ui:input-permissions />` tag in action,
access the form for adding a new web content folder:

Log in to your Liferay Portal instance as an administrator and click on *Admin*
&rarr; *Site Administration* &rarr; *Content*. Then click on *Web Content*. Then
click *Add* &rarr; *Folder*. This form appears:

![When you click *Add* &rrar; *Folder* to add a new web content folder, this form appears. The `<liferay-ui:input-permissions />` is used to add a permissions selector widget to the JSP that renders the form.](../../images/new-journal-folder.png)

The JSP fragment that's responsible for rendering the permissions selector is
this:

    <liferay-ui:input-permissions
        modelName="<%= JournalFolder.class.getName() %>"
    />

Notice that this `<liferay-ui:input-permissions />` tag appears within an
`<aui:form />` tag. The `<liferay-ui:input-permissions />` tag only makes sense
in the context of a form.

## Deleting a Resource [](id=deleting-a-resource)

When you remove an entity from the database, you should remove permissions that
were mapped directly to the entity. This prevents dead resources from taking up
space in the database. Remember to delete resources when deleting entities. To
do so, call `ResourceLocalServiceService`'s `deleteResource(...)` method.
Here's an example from the `GuestbookLocalServiceImpl`'s `deleteGuestbook(...)`
method: 

    resourceLocalService.deleteResource(serviceContext.getCompanyId(),
        Guestbook.class.getName(), ResourceConstants.SCOPE_INDIVIDUAL,
        guestbookId);

Great! Now you know how to work with resource permissions! 

## Related Topics [](id=related-topics)

[Customizing Liferay Portal](/tutorials/-/knowledge_base/6-2/customizing-liferay-portal)

[Plugin Security and PACL](/tutorials/-/knowledge_base/6-2/plugin-security-and-pacl)

[Developing Plugins with Liferay IDE](/tutorials/-/knowledge_base/6-2/liferay-ide)

[User Interfaces with AlloyUI](/tutorials/-/knowledge_base/6-2/alloyui)

[Liferay UI Taglibs](/tutorials/-/knowledge_base/6-2/liferay-ui-taglibs)
