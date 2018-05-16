# Defining Role Permissions [](id=defining-role-permissions)

Roles collect permissions, so when a user is given a role, they receive all the
permissions defined by the role. 

If you create a role with permission to access something in the Control Panel,
keep in mind that the *View Control Panel Menu* permission will be automatically
granted.

Consider a role called User Group Manager. Define the permissions for the User
Group Manager role so that assigned users can add users to or remove users from
any user group:

1.  Go to the Control Panel and then click on *Users* &rarr; *Roles*.

2.  On the Regular Roles screen, click *Add* (![Add](../../images/icon-add.png)).

3.  After naming your role, click *Save*.

4.  Click on the *Define Permissions* tab.

5.  Drill down in the menu on the left to *Control Panel* &rarr; *Users* &rarr;
    *User Groups*.

6.  Under the *General Permissions* heading, flag *Access in Control Panel* and
    *View*. This lets user group managers access the User Groups Control Panel
    portlet and view existing user groups.

7.  Since you'd like user group managers to be able to view user groups and
    assign members to them, you'd also check the *Assign Members* and *View*
    permissions under the *Resource Permissions* &rarr; *User Group* heading.

8.  There's one last necessary permission you might not think of in association
    with this role. In *Control Panel* &rarr; *Users* &rarr; *Users and
    Organizations*, user group managers need *View* permission on the User
    resource. Grant this permission.

9.  Click *Save*.

![Figure 2: When defining permissions on a role, the Summary view provides a list of permissions that have already been defined for the role. The area on the left side of the screen lets you drill down through various categories of permissions.](../../images/roles-define-permissions.png)

Now the User Group Manager role has all the permissions necessary for adding
users to user groups. After all, user group managers can view user groups,
assign members, and access User Groups in the Control Panel. The permission to
view users in the Control Panel was necessary because you must view users to
assign them as members of a role. Without this permission, user group managers
would see an empty list if they try to add users to a role.

![Figure 5: Users assigned to the User Group Manager role can't find any users to add unless they have view permissions on the User resource.](../../images/roles-no-users-found.png)

+$$$

**Note:** The Roles application in the Control Panel is not the only place where
permissions are configured. You can configure a role's permissions for a
particular application instance from its *Options*
(![Options](../../images/icon-options.png)) \
menu. However, permissions granted or removed in the Control Panel override
those made at the more granular level.

$$$

There are three basic categories of permissions: *Control Panel*, *Site
Administration*, and *User*. By default, any User can manage their
user account via the permissions belonging to the User category. Site
Administrators can access the site administration tools belonging to the Site
Administration category. Portal Administrators can access the entire Control
Panel. For custom roles, you can mix and match permissions from as many
categories as you like.

The permissions in the Site Administration &rarr; Applications categories govern
the content that can be created by core portlets such as the Wiki and Message
Boards. If you pick one of the portlets from this list, you'll get options for
defining permissions on its content. For example, if you pick Message Boards,
you'll see permissions for creating categories and threads or deleting and
moving topics.

Site application permissions affect the application as a whole. Using the
Message Boards as an example, an application permission might define who can add
the Message Boards portlet to a page.

![Figure 3: You can fine-tune which actions are defined for a role within a specific application like the Message Boards.](../../images/roles-message-board-content-permissions.png)

The Control Panel permissions affect how the Control Panel appears to the user
in the Control Panel. The Control Panel appears differently to different users,
depending on their permissions. Some Control Panel portlets have a Configuration
button and you can define who gets to see that. You can also fine-tune who gets
to see various applications in the Control Panel.

If you want to change the scope of a permission, click the *Change* link next to
the gear icon next to the permission and then choose a new scope. After you
click *Save*, you'll see a list of all permissions currently granted to the
role. From the Summary view, you can add more permissions or go back to the Role
Application default view by clicking on the *Back*
(![Back](../../images/icon-back.png)) icon.

Sometimes you might find that a certain permission grants more or less access
than what you expected--always test your permissions configurations! 

## Delegating Social Activities Configuration

There's a permission that allows site administrators to delegate responsibility
for configuring social activities to other users. To add this permission to a
role, click *Actions* next to the desired role and select *Define Permissions*.
Find the *Site Administration* &rarr; *Configuration* &rarr; *Social Activity*
permissions category. Flag all of the permissions and then click *Save*:

- Access in Site Administration
- Configuration
- Permissions
- Preferences
- View

Once these permissions are granted, role assignees can manage the site's Social
Activities.

Roles are very powerful and allow portal administrators to define various
permissions in whatever combinations they like. This gives you as much
flexibility as possible to build the site you have designed.
