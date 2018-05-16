# Roles and Permissions

If a *role* were to win a Grammy or an Oscar or some other ego-feeding
popularity contest, it better remember to thank all its *permissions* groupies
during the acceptance speech, because they're the ones doing the real work. The
role is just the pretty face, so to speak.

*Roles* collect *permissions* that define a particular function within
@product@, according to a particular scope. Roles collect permissions, and
users are assigned to roles, either directly or through their association with a
User Group, an Organization, or a Site. 

Take a *Message Board Administrator* role, for example. A role with that name
is likely to have permissions relevant to the specific Message Board portlets
delegated to it. Users with this role inherit the permissions collected underneath
the umbrella of the role.

In addition to regular roles, site roles, and organization roles, there are also
teams. Teams can be created by site administrators within a specific site. The
permissions granted to a team are defined and applied only within the team's
site. The permissions defined by regular, site, and organization roles, by
contrast, are defined at the portal level, although they are applied to
different scopes. 

Regular role
: Permissions are defined at the *portal* level and are applied at the *portal*
level.

Site role
: Permissions are defined at the *portal* level and are applied to one *specific
site*.

Organization role
: Permissions are defined at the *portal* level and are applied to one *specific
organization*.

Team
: Permissions are defined within a *specific site* and are assigned within that
*specific site*.

Read 
[here](/discover/portal/-/knowledge_base/7-1/creating-teams-for-advanced-site-membership-management) 
for more information about teams.

## Managing Roles in Liferay [](id=managing-roles-in-liferay)

Manage Liferay's roles in the Control Panel (*Control Panel* &rarr; *Users*
&rarr; *Roles*).  There you'll find an application for creating roles, granting
them permissions, and assigning users to them. Roles can be scoped by portal,
site, or organization.

replace screenshot
![Figure 1: The Roles application lets you add and manage roles for the global (*Regular*), *Site*, or *Organization* scope.](../../images/roles-app.png)

To create a role:

1.  Click the tab for the scope, then click the *Add*
    (![Add](../../images/icon-add.png)) button. 

2.  Enter a title and description for the role. The title field is required but
    the description is optional. 

3.  Enter a *Key*, if desired. This required field provides a key that can be
    used to refer to the role programmatically. It's auto-populated with the
    title text, but you can override it if desired.

3.  Click *Save*.

After creating a role, the first step is often to define its permissions.

## Defining Role Permissions [](id=defining-role-permissions)

Roles collect permissions, so when a user is given a role, they receive all the
permissions defined by the role. 

replace
![Figure 2: When defining permissions on a role, the Summary view provides a list of permissions that have already been defined for the role. The area on the left side of the screen lets you drill down through various categories of portal permissions.](../../images/roles-define-permissions.png)

To add permissions to a role: 

1.  Click on the *Define Permissions* tab in the Add Role form. If you're
    already navigated away form the Add/Edit Role Form, just click the role's title
    to open the form.

    FIGURE OF DEFINE PERMISSIONS SCREEN

2.  

(![Actions](../../images/icon-actions.png)) button for a regular role and select
*Define Permissions*. Find the permissions  you want to add by navigating the
categories of permissions on the left side of the screen and click on a specific
category (such as *Site Administration* &rarr; *Navigation* &rarr; *Site
Pages*). Select any permissions that you'd like to add the role, then click
*Save*. 

+$$$

**Note:** The Roles application in the Control Panel is not the only place where
permissions are configured. You can configure a role's permissions for a
particular application instance from its *Options*
(![Options](../../images/icon-options.png)) \
menu. However, permissions granted or removed in the Control Panel override
those made at the more granular level.

$$$

There are three basic categories of permissions: *Control Panel*, *Site
Administration*, and *User*. By default, any Liferay user can manage their
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

replace
![Figure 3: You can fine-tune which actions are defined for a role within a specific application like the Message Boards.](../../images/message-board-content-permissions.png)

Sometimes you might find that a certain permission grants more or less access
than what you expected--always test your permissions configurations! 

Suppose you need to create a role called *User Group Manager*. You'd like
to define the permissions for the User Group Manager role so that users assigned
to this role can add users to or remove users from any user group. To do this,
you can take the following steps:

1.  Go to the Control Panel and then click on *Users* &rarr; *Roles*.

2.  On the Regular Roles screen, click *Add* (![Add](../../images/icon-add.png)).

3.  After naming your role, click *Save*.

4.  Click on the *Define Permissions* tab.

5.  Drill down in the menu on the left to
   *Control Panel* &rarr; *Users* &rarr; *User Groups*.

6.  Under the *General Permissions* heading, flag *Access in Control Panel* and
    *View*. This lets user group managers access the User Groups Control Panel
    portlet and view existing user groups.

7.  Since you'd like user group managers to be able to view user groups and
    assign members to them, you'd also check the *Assign Members* and *View*
    permissions under the *Resource Permissions* &rarr; *User Group* heading.

7.  Click *Save*.

replace
![Figure 4: Make sure to test the permissions you grant to custom roles.](../../images/user-group-manager-role.png)

Once you create the role, assign it to its intended users. 

## Assigning Users to a Role

Assigning users to a role is done in the Assignees tab of the Add/Edit Role
form. Roles are assigned to to Users, Sites, Organizations, or User Groups.
Here's how to assign the User Group Manager role created in the last section to
Users:

1.  In the Assignees tab of the Add/Edit Role form, click the second level tab
    for *Users*.

2.  Click the Add button (![Add](../../images/icon-add.png)).

3.  Select the users you want to add to the role and click *Add*.

If assigning a group, note that all users assigned to that group will inherit
the role as well. 

You might expect that the User Group Manager role has all the permissions
necessary for adding users to user groups. After all, user group managers can
view user groups, assign members, and access User Groups in the Control Panel.
However, we're forgetting an important permission: the User Group Manager role
can't view users! This means that they see an empty list if they try to add
users to a role.

replace
![Figure 5: Users assigned to the User Group Manager role can't find any users to add!](../../images/no-users-found.png)

To fix this, define the missing permission on the role by drilling down to the
*Control Panel* &rarr; *Users* &rarr; *Users and Organizations* category and
flag the *View* permission under the *Resource Permissions* &rarr; *User*
heading. Once you've saved your permissions configuration, users who've been
assigned to the User Group Manager role will be able to browse the portal's
entire list of users when assigning users to a user group.

If you create a role with permission to access something in the Control Panel,
keep in mind that the *View Control Panel Menu* permission will be automatically
granted.

Roles are very powerful and allow portal administrators to define various
permissions in whatever combinations they like. This gives you as much
flexibility as possible to build the site you have designed.

## Permission for Delegating Social Activities Configuration [](id=permission-for-delegating-social-activities-configuration)

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

## Deleting Asset Containers [](id=deleting-asset-containers)

A Web Content Folder contains Web Content articles. The Web Content Folder is
an asset container, and the Web Content Article is an asset. It's possible to
give a role permission to delete an asset container without giving the role
permission to delete individual assets. In that case, beware: if a role assignee
deletes an asset container with individual assets in it, the individual assets
themselves *will* be deleted as well.

Besides Web Content Folders, examples of asset containers include Bookmarks
Folders, Message Boards Categories, Wiki Nodes, and Documents and Media Folders.

You might not need to create a role for a certain functionality. Liferay
provides many pre-configured roles for your convenience.

## Default Liferay Roles [](id=default-liferay-roles)

In the Roles Application, you'll see a list of all the roles in Liferay, by
scope. 

These are some of the pre-configured regular roles:
- Guest: The Guest role is assigned to unauthenticated users and grants the
  lowest-level permissions within the portal.
- User: The User role is assigned to authenticated users and grants basic
  permissions within the portal (mostly *Add to Page* permissions for
  applications).
- Power User: The Power User role grants more permissions than the User role.
  It's designed to be an extension point for distinguishing regular users from
  more privileged users. For example, you can set up your portal so that only
  Power Users have personal sites.
- Administrator: The administrator role grants the ability to manage the entire
  portal, including global portal settings and individual sites, organizations,
  and users.

These are some of the pre-configured site roles:
- Site Member: The Site Member role grants basic privileges within a site, such
  as the ability to visit the site's private pages.
- Site Administrator: The Site Administrator role grants the ability to manage
  *almost* all aspects of a site including site content, site memberships, and
  site settings. Site Administrators cannot delete the membership of or remove
  roles from other Site Administrators or Site Owners. They also *cannot* assign
  other users as Site Administrators or Site Owners.
- Site Owner: The Site Owner role is the same as the Site Administrator role
  except that it grants the ability to manage *all* aspects of a site, including
  the ability to delete the membership of or remove roles from Site
  Administrators or other Site Owners. They *can* assign other users as Site
  Administrators or Site Owners.

These are some of the pre-configured organization roles:
- Organization User: The Organization User role grants basic privileges within
  an organization. If the organization has an attached site, the Organization
  User role implicitly grants the Site member role within the attached site.
- Organization Administrator: The Organization Administrator role grants the
  ability to manage *almost* all aspects of an organization including the
  organization's users and the organization's site (if it exists). Organization
  Administrators cannot delete the membership of or remove roles from other
  Organization Administrators or Organization Owners. They also *cannot* assign
  other users as Organization Administrators or Organization Owners.
- Organization Owner: The Organization Owner role is the same as the
  Organization Administrator role except that it grants the ability to manage
  *all* aspects of an organization, including the ability to delete the
  membership of or remove roles from Organization Administrators or other
  Organization Owners. They *can* assign other users as Organization
  Administrators or Organization Owners.

+$$$

**Tip:** It's easy to overlook the differences between owner type roles and
administrator type roles for sites and organizations. Site and organization
administrators *cannot* remove the administrator or owner role from any other
administrator or owner, and they *cannot* appoint other users as site or
organization administrators or owners. 

In contrast, site and organization owners *can* do those things.

$$$

Roles, and the permissions granted with their assignment, are foundational
components in Liferay. Understanding their uses and configuration should enhance
your ability to configure @product@ to suit your organizational needs.

## Editing a Role

After saving a new role, it's added to the existing list of roles. Configuring
permissions and assigning users for a role are performed in the Add/Edit Role
form. Other management actions are accomplished by clicking the Actions button
(![Actions](../../../images/icon-actions.png)).

Edit 
: Lets you change the name, title or description of the role.

Permissions 
: Allows you to define which users, user groups or roles have permissions to
edit the role.

Delete
: Permanently removes a role from the portal.
