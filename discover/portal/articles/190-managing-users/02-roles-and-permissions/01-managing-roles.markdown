# Managing Roles [](id=managing-roles)

Manage roles and permissions in the Control Panel (*Control Panel* &rarr;
*Users* &rarr; *Roles*). There you'll find an application for creating roles,
granting them permissions, and assigning users to them. Roles can be scoped by
portal, site, or organization.

Defining a role's permissions is a topic deserving its own article. Read
[here](/discover/portal/-/knowledge_base/7-1/defining-role-permissions) about
defining a role's permissions.

![Figure 1: The Roles application lets you add and manage roles for the global (Regular), Site, or Organization scope.](../../../images/roles-app.png)

## Creating Roles [](id=creating-roles)

Determine the scope of the role you must create. Roles can be scoped to the
whole portal (Regular roles), to a specific site (Site roles), or to an
organization (Organization roles). 

To create a role:

1.  Click the tab for the proper role scope, then click the *Add*
    (![Add](../../../images/icon-add.png)) button. 

2.  Enter a title and description for the role. The title field is required but
    the description is optional. 

3.  Enter a Key, if desired. This required field provides a key that can be
    used to refer to the role programmatically. It's auto-populated with the
    title text, but you can override it if desired.

4.  Click *Save*.

Now the role is present in the database, and ready for further configuration.

## Assigning Users to a Role [](id=assigning-users-to-a-role)

Assign users to a role in the Assignees tab of the Add/Edit Role
form. Roles are assigned to to Users, Sites, Organizations, or User Groups.
Here's how to assign the User Group Manager role created in the last section to
Users:

1.  In the Assignees tab of the Add/Edit Role form, click the second level tab
    for *Users*.

2.  Click the Add button (![Add](../../../images/icon-add.png)).

3.  Select the users you want to add to the role and click *Add*.

If assigning a group, note that all users assigned to that group will inherit
the role as well. 

That's a good start, but your role isn't worth the database row it occupies
without defining permissions for the role. Read the next article to learn
how.
