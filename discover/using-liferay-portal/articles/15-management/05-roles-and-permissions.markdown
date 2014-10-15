# Roles and Permissions [](id=roles-and-permissions)

Roles are used to collect permissions that define a particular function within
the portal, according to a particular scope. Roles can be granted permissions to
various functions within portlet applications. A roles is basically just a
collection of permissions that defines a function, such as Message Board
Administrator. A role with that name is likely to have permissions relevant to
the specific Message Board portlets delegated to it. Users who are placed in
this role will inherit these permissions.

The roles page of the control panel serves as a single interface which lets you
create roles, assign permissions to them and assign users to the roles. Roles
can be scoped by portal, site or organization. To create a role, click the
*Roles* link and then click the *Add* button. You can choose a Regular, Site or
Organization role. A regular role is a portal-scoped role. Make a selection and
then type a name for your role, a title and a description. The name field is
required but the title and description are optional. If you enter a name and a
title, the title will be displayed in the list of roles on the Roles page of the
control panel. If you do not enter a title, the name will be displayed. When you
have finished, click *Save*.

In addition to regular roles, site roles, and organization roles, there are also
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

For more information about teams, please refer to chapter 3.

![Figure 15.9: Roles Page and Role Actions Menu](../../images/01-roles-and-role-actions.png)

After you save, Liferay redirects you to the list of roles. To see what
functions you can perform on your new role, click the *Actions* button.

**Edit:** lets you change the name, title or description of the role.

**Permissions:** allows you to define which users, user groups or roles have
permissions to edit the role.

**Define Permissions:** defines what permissions this role grants. This is
outlined in the next section.

**Assign Members:** lets you search and select users in the portal to be
assigned to this role. These users will inherit any permissions that have been
assigned to this role.

**View Users:** allows you to view the users who have been assigned to this
role.

**Delete:** permanently removes a role from the portal.

Next, let's examine how to configure the permissions granted by different roles.

## Defining Permissions on a Role [](id=defining-permissions-on-a-role)

Roles serve as repositories of permissions to be assigned to users who belong to
them. So, to use a role, you need to assign members to it and define the
permissions you want to grant to members of the role.

![Figure 15.10: Defining Permissions on a Role](../../images/01-defining-permissions-on-a-role.png)

When you click the *Actions* button on portal-scoped role and select *Define
Permissions*, you will be shown a list of all the permissions defined for that
role. Click the *Add Permissions* drop-down menu to see a list of the
permissions that can be defined. As of Liferay version 6.1, these permissions
fall into seven categories: Portal, Site Content, Site Application, Control
Panel: Personal, Control Panel: Site, Control Panel: Portal and Control Panel:
Server. For non-portal scoped roles, you need to click on the *Options* link on
individual portlets, then *Configuration*, then *Permissions* to assign
permissions within the site or organization that owns the portlet. 

Portal permissions cover portal-wide activities that comprise several
categories, such as site, organization, location, password policy, etc. This
allows you to create a role that, for example, can create new sites within the
portal. This would allow you to grant users that particular permission without
making them overall portal administrators.

Site Content permissions cover the content the installed portlets create. If you
pick one of the portlets from this list, you'll get options for defining
permissions on its content. For example, if you pick Message Boards, you'll see
permissions for creating categories and threads or deleting and moving topics.

Site Application permissions affect the application as a whole. So, using our
Message Boards example, an application permission might define who can add the
Message Boards portlet to a page.

The control panel permissions affect how the portlet appears to the user in the
control panel. Some control panel portlets have a Configuration button, so you
can define who gets to see that, as well as who gets to see an application in
the control panel.

![Figure 15.11: Message Board Content Permissions](../../images/01-message-board-content-permissions.png)

Each possible action to which permissions can be granted is listed. To grant a
permission, check the box next to it. If you want to limit the scope of the
permission to a particular site, click the *Limit Scope* link and then choose
the site. Once you have chosen the permissions granted to this role, click
*Save*. For a portal-scoped Message Boards Administrator role, you might grant
content permissions for every action listed. After you click *Save*, you will
see a list of all permissions currently granted to this role. From here, you can
add more permissions or go back by clicking a link in the breadcrumb list or the
*Return to Full Page* link.

The list of permissions that you can define for a role may seem overwhelming.
However, these permissions ensure that you can customize exactly which areas of
your portal you'd like different collections of users to be able to access.
Sometimes you might find that a certain permission grants more or less access
than what you expected--always test the permissions yourself! 

For example, suppose that you created a role called User Group Manager. You'd
like to define the permissions for the User Group Manager role so that users
assigned to this role can add users to or remove users from any user group. To
do this, you might click on *Go To* &rarr; *Control Panel* and then *Roles*
&rarr; *Add* &rarr; *Regular Role*. After naming your role, you'd click
*Actions* &rarr; *Define Permissions*. Then you'd click on the *Add Permissions*
dropdown menu and select *Users and Organizations* under the *Portal* heading.
Since you'd like user group managers to be able to view user groups and assign
members to them, you'd check the *Assign Members* and *View* permissions under
the *User Group* heading. Then you'd go back to the *Add Permissions* dropdown
menu and select *User Groups* under the *Control Panel: Portal* heading and
check the *Access in Control Panel* and *View* permissions so that user group
managers can manage user groups from the Control Panel.

![Figure 15.12: Make sure to test the permissions you grant to custom roles.](../../images/user-group-manager-role.png)

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

![Figure 15.13: Users assigned to the User Group Manager role can't find any users to add!](../../images/no-users-found.png)

To fix this, click *Go To* &rarr; *Control Panel*. Click on *Roles* and then
*Actions* &rarr; *Define Permissions*. Then click on the *Add Permissions*
dropdown list and select *Users and Organizations* under the *Portal* heading.
On this page, check the *View* permission under the *User* heading. Once you've
saved, users who've been assigned to the User Group Manager role will be able to
browse the portal's entire list of users when assigning users to a user group.

Roles are very powerful and allow portal administrators to define various
permissions in whatever combinations they like. This gives you as much
flexibility as possible to build the site you have designed.

## Note About Permissions for Deleting Containers [](id=note-about-permissions-for-deleting-containers)

Liferay Portal contains many types of portal resources upon which permissions
can be defined. These include both assets and asset containers. The term *asset*
refers to any kind of content in Liferay such as a web content article, blog
entry, wiki article, message board post, or Documents and Media document. Asset
containers are portal resources used for grouping specific kinds of assets. For
example, web content folders, wiki nodes, message board categories, and
Documents and Media folders are asset containers.

When configuring permissions for assets and asset containers, it's important to
note that the permission to delete an asset container includes the permission to
indirectly delete any assets in the container. This means that if a user has
permission to delete an asset container, the user can delete all of the assets
in that container even the user lacks permission to delete any of the assets in
the container individually. Granting permission to delete a folder but not any
of the contained assets is *not* a common use case. Nevertheless, it's important
to note that assets in a container can be indirectly deleted if their asset
container is deleted.

## Special Note About the Power Users Role [](id=special-note-about-the-power-users-role)

Prior to Liferay 6.0, the default configurations of many Liferay portlets
allowed power users, but not regular users, to access them. Liferay 6.0 and
subsequent versions grant the same default permissions to both power users and
regular users. This way, portal administrators are not forced to use the power
users role. However, Liferay encourages those who do to create their own custom
permissions for the role. 

+$$$

**Note:** Prior to Liferay version 6.0, Power Users and Users did *not* have the 
same default permissions. So if are using Liferay 5.2 or a previous version, 
it's dangerous to remove the Power Users role from the default user 
associations: this could remove certain permissions you expect to apply to all 
users. If you decide to remove the Power Users role from the default user 
associations anyway, you will probably want to modify the permissions on certain 
portlets to make them accessible to all users. To do this, see the section on 
Plugins Configuration below.

$$$

Now that we've seen how to use organizations and user groups to manage users and
how to use roles to define permissions, let's examine the general portal
settings you can configure.
