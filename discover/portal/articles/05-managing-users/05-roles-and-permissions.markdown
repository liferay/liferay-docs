# Roles and Permissions

If a *role* were to win a Grammy, or an Oscar, or some other ego-feeding
popularity contest, it better remember to thank all its *permissions* groupies
during the acceptance speech, because they're the ones doing the real work. The
role is just the pretty face, so to speak.

*Roles* collect *permissions* that define a particular function within
Liferay, according to a particular scope. Roles collect permissions, and users
are assigned to roles, either directly or through their association with a User
Group, an Organization, or a Site. 

Take a *Message Board Administrator* role, for example. A role with that name
is likely to have permissions relevant to the specific Message Board portlets
delegated to it. Users in this role inherit the permissions collected underneath
the umbrella of the role.

## Managing Roles in Liferay

Manage Liferay's roles in the *Control Panel* &rarr; *Users* &rarr; *Roles*.
There you'll find an application for creating roles, granting them permissions,
and assigning users to them. Roles can be scoped by portal, site, or organization.

![Figure x: The Roles application lets you add and manage roles for the global (*Regular*), *Site*, or *Organization* scope.](../../images/roles-app.png)

To create a role, click the scope you want a role for and then click the *Add*
(![Add](../../images/icon-add.png)) button. Enter a name, title, and description
for the role. The name field is required but the title and description are
optional. If you enter a name and a title, the title is displayed in the list of
roles on the Roles page of the Control Panel. If you do not enter a title, the
name is displayed. When you finish, click *Save*.

+$$$

**Note:** In addition to regular roles, site roles, and organization roles, there are also
teams. Teams can be created by site administrators within a specific site. The
permissions granted to a team are defined and applied only within the team's
site. The permissions defined by regular, site, and organization roles, by
contrast, are defined at the portal level, although they are applied to
different scopes. The differences between the four types of roles can be
described as follows:

- Regular role: Permissions are defined at the *portal* level and are applied at
  the *portal* level.

- Site role: Permissions are defined at the *portal* level and are applied to
  one *specific site*.

- Organization role: Permissions are defined at the *portal* level and are
  applied to one *specific organization*.

- Team: Permissions are defined within a *specific site* and are assigned within
  that *specific site*.

Read [here](/discover/portal/-/knowledge_base/6-2/creating-teams-for-advanced-site-membership-management) For more information about teams.

$$$

After you save, your role is added to the exiting list of roles. To see what
functions you can perform on your new role, click the *Actions* button.

**Edit:** lets you change the name, title or description of the role.

**Permissions:** allows you to define which users, user groups or roles have
permissions to edit the role.

**Define Permissions:** defines the permissions the role contains. 

<!-- **Assign Members:** lets you search and select users in the portal to be
assigned to this role. These users will inherit any permissions that have been
assigned to this role.

**View Users:** allows you to view the users who have been assigned to this
role.-->

**Delete:** permanently removes a role from the portal.

You might not need to create a role for a certain functionality. Liferay
provides many pre-configured roles for your convenience.

## Default Liferay Roles

In the Roles Application, you'll see a list of all the roles in Liferay, by
scope. These are some of the pre-configured roles:

- Guest: The Guest role is assigned to unauthenticated users and grants the
  lowest-level permissions within the portal.
- User: The User role is assigned to authenticated users and grants basic
  basic permissions within the portal (mostly *Add to Page* permissions for
applications).
- Power User: The Power User role grants more permissions than the User role.
  It's designed to be an extension point for distinguishing regular users from
more privileged users. For example, you can set up your portal so that only
Power Users have personal sites.
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
- Administrator: The administrator role grants the ability to manage the entire
  portal, including global portal settings and individual sites, organizations,
  and users.

+$$$

**Tip:** It's easy to overlook the differences between owner type roles and
administrator type roles for sites and organizations. Site and organization
administrators *cannot* remove the administrator or owner role from any other
administrator or owner, and they *cannot* appoint other users as site or
organization administrators or owners. 

In contrast, site and organization owners *can* do those things.

$$$

You can configure the permissions for existing or new roles.

## Defining Role Permissions

Roles collect permissions, so user is given a role, they receive all the
permissions defined by the role. So roles are only useful when they have users
and permissions.

![Figure x: When defining permissions on a role, the Summary view provides a
list of permissions that have already been defined for the role. The area on the
left side of the screen lets you drill down through various categories of portal
permissions.](../../images/roles-define-permissions.png)

To add permissions to a role, click on the *Actions*
(![Actions](../../images/icon-actions.png)) button for a regular role and select
*Define Permissions*. Find the permissions  you want to add by navigating the
categories of permissions on the left side of the screen and click on a specific
category (such as *Site Administration* &rarr; *Navigation* &rarr; *Site
Pages*). Select any permissions that you'd like to add the role, then click
*Save*. 

+$$$

**Note:** The Roles application in the Control Panel is not the only place where
permissions are configured. You can configure the role permissions for a particular
application instance from its *Options*
(![Options](../../images/icon-options.png)) menu. However, permissions
granted or removed in the control Panel override those made at the more granular
level.

$$$

For Liferay 6.2, the permissions fall into the following hierarchy of
categories:

<img src="../../images/roles-heirarchy.png"
align="right">

- Control Panel
    - General Permissions
    - Users
        - Users and Organizations
        - User Groups
        - Roles
        - Password Policies
        - Monitoring
    - Sites
        - Sites
        - Site Templates
        - Page Templates
    - Apps
        - Store
        - Purchased
        - App Manager
        - Plugins Configuration
        - License Manager
    - Configuration
        - Portal Settings
        - Custom Fields
        - Server Administration
        - Portal Instances
- Site Administration
    - Pages
        - Site Pages
    - Content
        - Recent Content
        - Web Content
        - Documents and Media
        - Blogs
        - Message Boards
        - Wiki
        - Dynamic Data Lists
        - Bookmarks
        - Polls
        - Software Catalog
        - Tags
        - Categories
        - Recycle Bin
    - Users
        - Site Memberships
        - Site Teams
    - Configuration
        - Site Settings
        - Site Template Settings
        - Application Display Templates
        - Social Activity
        - Mobile Device Families
    - Applications
        - [too many to list]
- My Account
    - Account Settings
    - My Pages

The three basic categories of permissions are *Control Panel*, *Site
Administration*, and *User*. By default, any Liferay user can manage their
user account via the permissions belonging to the User category. Site
administrators can access the site administration tools belonging to the Site
Administration category. And portal administrators can access the entire Control
Panel. For custom roles, you can mix and match permissions from as many
categories as you like.

The permissions in the Site Administration &rarr; Applications categories govern
the content that can be created by core portlets such as the Wiki and Message
Boards. If you pick one of the portlets from this list, you'll get options for
defining permissions on its content. For example, if you pick Message Boards,
you'll see permissions for creating categories and threads or deleting and
moving topics.

Site application permissions affect the application as a whole. So, using the
Message Boards as an example, an application permission might define who can add
the Message Boards portlet to a page.

The Control Panel permissions affect how the Control Panel appears to the user
in the Control Panel. The Control Panel appears differently to different users,
depending on their permissions. Some Control Panel portlets have a Configuration
button and you can define who gets to see that. You can also fine-tune who gets
to see various applications in the Control Panel.

![Figure x: You can fine-tune which actions are defined for a role within a specific application like the Message Boards.](../../images/message-board-content-permissions.png)

If you want to change the scope of a permission, click the *Change* link next to
the gear icon next to the permission and then choose a new scope. After you
click *Save*, you'll see a list of all permissions currently granted to the
role. From the Summary view, you can add more permissions or go back by clicking
on the *Back* (![Back](../../images/icon-back.png)) icon.

Sometimes you might find that a certain permission grants more or less access
than what you expected--always test your permissions configurations! 

For example, suppose that you need to create a role called User Group Manager. You'd
like to define the permissions for the User Group Manager role so that users
assigned to this role can add users to or remove users from any user group. To
do this, you can take the following steps:

1. Click on *Admin* &rarr; *Control Panel* from the Dockbar and then click on
   *Roles*.
2. On the Roles screen, click *Add* &rarr; *Regular Role*.
3. After naming your role and entering a title, click *Save*.
4. Click on *Define Permissions* and drill down in the menu on the left to
   *Control Panel* &rarr; *Users* &rarr; *Users and Organizations*.
5. Under the *General Permissions* heading, flag *Access in Control Panel* and
   *View*. This lets user group managers access the User Groups Control Panel
   portlet and view existing user groups.
6. Since you'd like user group managers to be able to view user groups and
   assign members to them, you'd also check the *Assign Members* and *View*
   permissions under the *Resource Permissions* &rarr; *User Group* heading.
7. Click *Save*.

![Figure 16.12: Make sure to test the permissions you grant to custom roles.](../../images/user-group-manager-role.png)

You might expect that these permissions would be enough to allow users assigned
to the User Group Manager role to add or remove any users to or from any user
group. After all, we've granted user group managers permissions to view user
groups and assign members and we've granted them access to User Groups in the
Control Panel. However, we're forgetting an important permission. Can you guess
what it is? That's right: we haven't granted the User Group Manager role
permission to view users! Although user group managers can assign members to
user groups, they don't have permission to view users at the portal level. This
means that if they click *Assign Members* for a user group and click on the
*Available* tab, they'll see an empty list.

![Figure 16.13: Users assigned to the User Group Manager role can't find any users to add!](../../images/no-users-found.png)

To fix this, log in as an administrator and click *Admin* &rarr; *Control Panel*
from the Dockbar. Then click on *Roles* and then on *Actions* &rarr; *Define
Permissions* next to the *User Group Manager* role. Then, drill down to the
*Control Panel* &rarr; *Users* &rarr; *Users and Organizations* category and
flag the *View* permission under the *Resource Permissions* &rarr; *User*
heading.  *View*.  Click *Save*. Once you've saved your permissions
configuration, users who've been assigned to the User Group Manager role will be
able to browse the portal's entire list of users when assigning users to a user
group.

Roles are very powerful and allow portal administrators to define various
permissions in whatever combinations they like. This gives you as much
flexibility as possible to build the site you have designed.

## Permission for Delegating Social Activities Configuration

There's a permission that allows site administrators to delegate responsibility
for configuring social activities to other users. To dd this permission to a
role, click *Actions* next to the desired role and select *Define Permissions*.
Find the *Site Administration* &rarr; *Configuration* &rarr; *Social Activity*
permissions category. Flag all of the permissions and then click *Save*:

- Access in Site Administration
- Configuration
- Permissions
- Preferences
- View

Once these permissions are assigned, assignees can manage the site's Social
Activities.

## Deleting Asset Containers

A Web Content Folder contains Web Content articles. The Web Content Folder is
an asset container, and the Web Content Article is an asset. It's possible to
give a role permission to delete an asset container without giving the role
permission to delete individual assets. In that case, beware: if a role assignee
deletes an asset container with individual assets in it, the individual assets
themselves *will* be deleted as well.

Besides Web Content Folders, examples of asset containers include Bookmarks
Folders, Message Boards Categories, Wiki Nodes, and Documents and Media Folders.

## Note About the Power Users Role

Prior to Liferay 6.0, the default configurations of many Liferay portlets
allowed power users, but not regular users, to access them. Liferay 6.0 and
subsequent versions grant the same default permissions to both power users and
regular users. This way, portal administrators are not forced to use the power
users role. However, Liferay encourages those who do to create their own custom
permissions for the role. 

<!-- +$$$

**Note:** Prior to Liferay version 6.0, Power Users and Users did *not* have the
same default permissions. So if are using Liferay 5.2 or a previous version,
it's dangerous to remove the Power Users role from the default user
associations: this could remove certain permissions you expect to apply to all
users. If you decide to remove the Power Users role from the default user
associations anyway, you will probably want to modify the permissions on certain
portlets to make them accessible to all users. To do this, see the section on
Plugins Configuration below.

$$$
-->


Now that we've seen how to use organizations and user groups to manage users and
how to use roles to define permissions, let's learn how to configure portal
password policies.

## Password Policies

Password policies can enhance the security of your portal. You can set
requirements on password strength, frequency of password expiration, user
lockout, and more. Additionally, you can apply different password policies to
different sets of portal users. You define custom password policies or delegate
user authentication to an LDAP server.

If you are viewing a page other than the Control Panel, click on *Admin* &rarr;
*Control Panel* from the Dockbar. Next, click on the *Password Policies* link
under the *Users* heading. You'll see that there's already a default password
policy in the system. You can edit this in the same manner as you edit other
resources in the portal: click *Actions* and then click *Edit*.

The Password Policy settings form contains the following fields. Enabling
specific settings via the check boxes prompts setting-specific options to
appear.

**Name:** requires you to enter a name for the password policy.

**Description:** lets you describe the password policy so other administrators
will know what it's for.

**Changeable:** determines whether or not a user can change his or her password.

**Change Required:** determines whether or not a user must change his or her
password after logging into the portal for the first time.

**Minimum Age:** lets you choose how long a password must remain in effect
before it can be changed.

**Reset Ticket Max Age:** determines how long a password reset link remains
valid.

**Password Syntax Checking:** allows you to set a minimum password length and to
choose whether or not dictionary words can be in passwords. You can also specify
detailed requirements such as minimum numbers of alpha numeric characters, lower
case letters, upper case letters, numbers or symbols.

**Password History:** lets you keep a history (with a defined length) of
passwords and prevents users from changing their passwords to one that was
previously used.

**Password Expiration:** lets you choose how long passwords can remain active
before they expire. You can select the age, the warning time and a grace limit.

**Lockout:** allows you to set a number of failed log-in attempts that triggers
a user's account to lock. You can choose whether an administrator needs to
unlock the account or if it becomes unlocked after a specific duration.

From the list of password policies, you can perform several other actions.

**Edit:** brings you to the form above and allows you to modify the password
policy.

**Permissions:** allows you to define which users, user groups or roles have
permission to edit the password policy.

**Assign Members:** takes you to a screen where you can search and select users
in the portal to be assigned to this password policy. The password policy will
be enforced for any users who are added here.

**Delete:** shows up for any password policies you add beyond the default
policy. You cannot delete the default policy.
