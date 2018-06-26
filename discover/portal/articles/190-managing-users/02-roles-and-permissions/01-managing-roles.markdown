# Managing Roles [](id=managing-roles)

You manage Roles and Permissions in the Control Panel (*Control Panel* &rarr;
*Users* &rarr; *Roles*). There you'll find an application for creating Roles,
granting them permissions, and assigning Users to them. Roles can be scoped by
portal, Site, or Organization.

Defining a Role's permissions is a topic deserving its own article. Read
[here](/discover/portal/-/knowledge_base/7-1/defining-role-permissions) about
defining a role's permissions.

![Figure 1: The Roles application lets you add and manage roles for the global (Regular), Site, or Organization scope.](../../../images/roles-app.png)

## Creating Roles [](id=creating-roles)

Determine the scope of the Role you must create. Roles can be scoped globally
(Regular Roles), to a specific Site (Site Roles), or to an Organization
(Organization Roles). 

To create a Role:

1.  Click the tab for the proper Role scope, then click the *Add*
    (![Add](../../../images/icon-add.png)) button. 

2.  Enter a title and description. The title field is required but the
    description is optional. 

3.  Enter a Key, if desired. This required field provides a key that can be
    used to refer to the Role programmatically. It's auto-populated with the
    title text, but you can override it if desired.

4.  Click *Save*.

Now the Role is present in the database and ready for further configuration.

## Assigning Users to a Role [](id=assigning-users-to-a-role)

Assign users to a Role in the Assignees tab of the Add/Edit Role
form. Roles are assigned to to Users, Sites, Organizations, or User Groups.
Here's how to assign the User Group Manager Role created in the last section to
Users:

1.  In the Assignees tab of the Add/Edit Role form, click the second level tab
    for *Users*.

2.  Click the Add button (![Add](../../../images/icon-add.png)).

3.  Select the Users you want to add to the Role and click *Add*.

If assigning a group, note that all Users assigned to that group inherit the
role as well. 

That's a good start, but your Role isn't worth the database row it occupies
without defining permissions for the Role. Read the next article to learn
how.
