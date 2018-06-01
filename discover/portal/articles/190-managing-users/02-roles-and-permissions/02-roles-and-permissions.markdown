# Defining Role Permissions [](id=defining-role-permissions)

Roles collect permissions, so when Users are given a Role, they receive all the
permissions defined by the Role. 

If you create a Role with permission to access something in the Control Panel,
keep in mind that the *View Control Panel Menu* permission is automatically
granted.

Consider a Role called User Group Manager. Define the permissions for the User
Group Manager Role so that assigned Users can add Users to or remove Users from
any User Group:

1.  Go to the Control Panel and then click on *Users* &rarr; *Roles*.

2.  On the Regular Roles screen, click *Add* (![Add](../../../images/icon-add.png)).

3.  After naming your Role, click *Save*.

4.  Click on the *Define Permissions* tab.

5.  Drill down in the menu on the left to *Control Panel* &rarr; *Users* &rarr;
    *User Groups*.

6.  Under the *General Permissions* heading, flag *Access in Control Panel* and
    *View*. This lets user group managers access the User Groups Control Panel
    portlet and view existing User Groups.

7.  Since you want User Group managers to be able to view User Groups and
    assign members to them, also check the *Assign Members* and *View*
    permissions under the *Resource Permissions* &rarr; *User Group* heading.

8.  There's one last necessary permission you might not think of in association
    with this Role. In *Control Panel* &rarr; *Users* &rarr; *Users and
    Organizations*, User Group managers need *View* permission on the User
    resource. Grant this permission.

9.  Click *Save*.

![Figure 1: When defining permissions on a role, the Summary view provides a list of permissions that have already been defined for the role. The area on the left side of the screen lets you drill down through various categories of permissions.](../../../images/roles-define-permissions.png)

Now the User Group Manager Role has all the permissions necessary for adding
Users to User Groups. After all, User Group managers can view User Groups,
assign members, and access User Groups in the Control Panel. The permission to
view Users in the Control Panel was necessary because you must view Users to
assign them as members of a Role. Without this permission, User Group managers
see an empty list if they try to add Users to a Role.

![Figure 2: Users assigned to the User Group Manager role can't find any users to add unless they have view permissions on the User resource.](../../../images/roles-no-users-found.png)

+$$$

**Note:** The Roles application in the Control Panel is not the only place where
permissions are configured. You can configure a Role's permissions on a resource
at a more granular level. For example, from a particular application instance,
click its *Options* (![Options](../../../images/icon-options.png)) menu and
select *Permissions*. There you can configure permissions for the resource that
overlap with those configured in the Control Panel's Roles application. However,
permissions granted or removed in the Control Panel override those made at the
more granular level.

$$$

There are three categories of permissions: *Control Panel*, *Site
Administration*, and *User*. By default, Users can manage their User accounts
via the permissions belonging to the User category. Site Administrators can
access the site administration tools belonging to the Site Administration
category. Portal Administrators can access the entire Control Panel. For custom
Roles, you can mix and match permissions from as many categories as you like.

The permissions in the Site Administration &rarr; Applications categories govern
the content that can be created by portlets such as the Wiki and Message Boards.
If you pick one of the portlets from this list, you see options for defining
permissions on its content. For example, if you pick Message Boards, you see
permissions for creating categories and threads or deleting and moving topics.

Site application permissions affect the application as a whole. Using the
Message Boards as an example, an application permission might define who can add
the Message Boards portlet to a page.

![Figure 3: You can fine-tune which actions are defined for a role within a specific application like the Message Boards.](../../../images/roles-message-board-content-permissions.png)

The Control Panel permissions affect how the Control Panel appears to the User
in the Control Panel. The Control Panel appears differently for different Users,
depending on their permissions. Some Control Panel portlets have a Configuration
button, and you can define who gets to see that. You can also fine-tune who gets
to see various applications in the Control Panel.

If you want to change the scope of a permission, click the *Change* link next to
the gear icon next to the permission and then choose a new scope. After you
click *Save*, you'll see a list of all permissions currently granted to the
Role. From the Summary view, you can add more permissions or go back to the Role
Application default view by clicking on the *Back*
(![Back](../../../images/icon-back.png)) icon.

Sometimes you might find that a certain permission grants more or less access
than what you expected---always test your permissions configurations! 

## Delegating Social Activities Configuration [](id=delegating-social-activities-configuration)

There's a permission that allows Site administrators to delegate responsibility
for configuring social activities to other Users. To add this permission to a
Role, click *Actions* next to the desired Role and select *Define Permissions*.
Find the *Site Administration* &rarr; *Configuration* &rarr; *Social Activity*
permissions category. Flag all of the permissions and then click *Save*:

- Access in Site Administration
- Configuration
- Permissions
- Preferences
- View

Once these permissions are granted, Role assignees can manage the site's Social
Activities.

Roles allow portal administrators to define various permissions in whatever
combinations they like. This gives you as much flexibility as possible to build
the Site you have designed.
