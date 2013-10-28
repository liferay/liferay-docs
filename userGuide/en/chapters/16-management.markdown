
# User Management [](id=management-liferay-portal-6-2-user-guide-16-en)

You know how all these retailers advertise themselves as a "one stop shop" for
anything you want? The idea is they have so much stuff that chances are whatever
you're looking for is there. Liferay's Control Panel is something like this. If
you want to create users, organizations, sites, configure permissions and
plugins and pretty much anything else, you can do it from the Control Panel.
When signed in to Liferay as an administrator, you can access the Control Panel
from the Dockbar by clicking *Admin* &rarr; *Control Panel*.

The Control Panel is organized into four main areas: Users, Sites, Apps, and
Configuration. The Users section lets you create and manage users,
organizations, user groups, roles, and password policies. If monitoring has been
enabled for your portal, you can also view all of the live portal sessions of
your users from this area of the Control Panel. In this chapter, we cover the
Users section of the Control Panel and provide examples of some of the user
management concepts we discussed in chapter one: users, organizations, and user
groups. For information about the Sites section of the Control Panel and site
management, please refer to chapters 2 and 3 of this guide. For information
about the Apps section of the Control Panel and plugin managment, please refer
to chapter 14. Finally, for information about the Configuration section of the
Control Panel and managing portal settings, please refer to the next chapter,
chapter 17.

This chapter explains how to use the Control Panel to manage the following:

- Users
- Organizations
- User Groups
- Roles
- Password Policies
- Monitoring

Let's begin our examination of Liferay's Control Panel by looking at how to
manage users in Liferay Portal.

## The Users Section of the Control Panel [](id=the-portal-section-of-the-control-panel-liferay-portal-6-2-user-guide-16-en)

The Users section of the Control Panel is used for most administrative tasks
involving user management. There, you'll find an interface for the creation and
maintenance of the following portal entities: users, organizations, user groups,
and roles. 

![Figure 16.1: The Users section of the Control Panel allows portal administrators to manage users, organizations, user groups, and roles. It also allows administrators to monitor users' live portal sessions if monitoring has been enabled for the portal.](../../images/users-section-control-panel.png)

Since we explained how to manage sites, teams, site templates, and page
templates in chapters 2 and 3, we won't discuss them in detail here.
Nevertheless, remember that it's possible and sometimes simpler to use sites,
site memberships, and teams to organize users and manage permissions than it is
to use organizations, user groups, and custom roles.

As a portal administrator, you'll use the Users section of the Control Panel to
create users, organizations, and user groups, implement security via roles and
permissions, and administer your users. Note that only users with the
administrator role, which is a portal scoped role, have permission to view the
Control Panel. You can, of course, grant permissions to view one or more
sections, such as the Users section, to custom roles.

### Adding and Editing Users [](id=adding-users-liferay-portal-6-2-user-guide-16-en)

Are you using Liferay's default administrative user, Test Test
(test@liferay.com)? If so, let's add a user account for yourself and configure
your new account so it has the same administrative access as the default
administrator account. From the Dockbar, click *Admin* and select *Control
Panel*. Then click on *Users and Organizations*. Click the *Add* button and
select *User*. Fill out the Add User form using your name and email address.
When you are finished, click *Save*.

![Figure 16.2: You need to at least enter a screen name, email address, and first name in order to create a new user account.](../../images/01-add-user-screen.png)

After you submit the form, the page reloads with a success message. An expanded
form appears that allows you to fill out a lot more information about the user.
You don't have to fill anything else out right now. Just note that when the user
account was created, a password was automatically generated. If Liferay was
correctly installed and a mail server was set up (see chapter 14), an email
message with the user's new password was sent to the user's email address. This,
of course, requires that Liferay can properly communicate with your SMTP mail
server.

![Figure 16.3: Once you've completed the basic form for creating a new user account, you'll see a much more detailed form for editing the user and adding additional information.](../../images/01-user-account-editor.png)

If you haven't yet set up your mail server, you'll need to use this page to
change the default password for your user account to something you can remember.
You can do this by clicking on the *Password* link in the box on the right,
entering the new password in the two fields and clicking *Save*. Next, you
should give your user account the same administrative rights as the default
administrator's account. This allows you to perform administrative tasks with
your own account instead of having to use the default administrator account.
In production, you should always delete or disable the default administrator
account to secure your portal.

On the form for editing a user, click the *Roles* link in the menu on the right
side of the screen. This page shows the roles to which your account is currently
assigned. You should have one role: Power User. By default, all users are
assigned the Power User role. The Power User role doesn't grant any special
permissions. It can safely be ignored. Alternatively, it can be used to extend
the User role. If there are certain custom permissions that you'd like all of
your portal users to have, you can grant these custom permissions to the Power
User role. You can also customize the default roles a new user receives via
*Default User Associations*. We'll learn to do this when we look at Portal
Settings in the next chapter.

We want to assign the Administrator role to our user account. On the form for
editing a user, after having clicked on *Roles* at the right side of the screen,
click the *Select* link under Regular Roles. A dialog box pops up with a list of
all the regular (portal-scoped) roles in the portal. Select the Administrator
role from the list. The dialog box disappears and the role is added to the list
of roles associated with your account. Don't forget to click the *Save* button
which is at the bottom of the blue bar of links on the right side of the screen.
You are now a portal administrator. Log out of the portal and then log back in
with your own user account. Next, let's look at some other aspects of user
management. 

### User Management [](id=user-management-liferay-portal-6-2-user-guide-16-en)

If you click on *Users and Organizations* in the Control Panel, you should now
see your user account in the list of users. If you want to change something
about a particular user, you can click the *Actions* button next to that user.

**Edit:** takes you back to the Edit User page where you can modify any aspect
of the user account including the screen name, email address, first name, last
name, site and organization memberships, roles, etc.

**Permissions:** allows you to define which roles have permissions to edit the
user.

**Manage Pages:** allows you to edit the personal pages of a user.

**Impersonate User:** opens another browser window which allows you to browse
the site as if you were the user.

**Deactivate:** deactivates the user's account.

Note that most users can't perform most of the above actions. In fact, most
users won't even have access to the Control Panel. You can perform all of the
above functions because you have administrative access.

Next, let's look at how to manage organizations.

### Organizations [](id=organizations-liferay-portal-6-2-user-guide-16-en)

Organizations are used to represent hierarchical structures of users such as
those of companies, businesses, non-profit organizations, churches, schools, and
clubs. They are designed to allow distributed user administration. Organizations
can be used, for example, to represent a sports league. The league itself could
be modeled as a top-level organization and the various sports (soccer, baseball,
basketball, etc.) could be modeled as suborganizations. The teams belonging to
the various sports could be modeled as sub-organizations of the sports
organizations. So, for example, you could have an organization hierarchy that
looks like this:

- Atlantic Sports League
	- Atlantic Soccer Association
		- Midway Soccer Club
		- Fairview Soccer Club
		- Oak Grove Soccer Club
	- Atlantic Baseball Association
		- Five Points Baseball Club
		- Riverside Baseball Club
		- Pleasant Hill Baseball Club
	- Atlantic Basketball Association
		- Bethel Basketball Club
		- Centerville Basketball Club
		- New Hope Basketball Club

Whenever you have a collection of users that fit into a hierarchical structure,
you can use organizations to model those users. In Liferay, organization
administrators can manage all the users in their organization *and* in any
suborganization. Referring to the hierarchy above, for example, an organization
administrator of the Atlantic Sports League could manage any users belonging to
the league itself, to any of the associations, or to any of the associations'
clubs. An organization administrator of the Atlantic Soccer Association could
manage any users belonging to the Atlantic Soccer Association itself, or to the
Midway Soccer Club, Fairview Soccer Club, or Oak Grove Soccer Club. However, an
administrator of the Atlantic Soccer Association would not be able to manage
users belonging to the Atlantic Baseball Association or to the Bethel Basketball
Club.

Organizations and suborganization hierarchies can be created to unlimited
levels. Users can be members of one or many organizations. The rights of an
organization administrator apply both to his/her organization and to any child
organizations. By default, members of child organizations are implicit members
of their parent organizations. This means, for example, that members of child
organizations can access the private pages of their parent organizations. This
behavior can be customized in your portal's `portal-ext.properties`
configuration file.

Since organizations are designed for distributed user administration,
organization administrators have an entirely different set of privileges than
site administrators. Site administrators are responsible for the pages,
portlets, and content of their site. They are also responsible for managing the
membership of their site. To this end, they can set the membership type to Open,
Restricted, or Private. They can also add users to or remove users from their
site but cannot manage the users themselves. Organization administrators, on the
other hand, can edit users belonging to their organization or any
suborganization. They cannot add existing users to their organization but they
can create new users within their organization. Only portal administrators can
add existing users to an organization.

Many simple portal designs don't use organizations at all; they only use sites
(see chapters 2 and 3 for more information on sites). Remember that the main
purpose of organizations is to allow for distributed user management. They allow
portal administrators to delegate some of their user management responsibilities
to organization administrators. If you don't anticipate needing to delegate user
management responsibilities, your portal design need not include organizations.
In order to decide whether or not your portal design should include
organization, think about your portal's function. A simple photo-sharing web
site, for example, could be powered by sites only. On the other hand,
organizations are useful for corporations or educational institutions since
their users can easily be placed into a hierarchical structure. In fact,
organizations in Liferay are designed to model any group hierarchy, from those
of government agencies all the way down to those of small clubs. Of course,
users can belong both to organizations and to independent sites. For example, a
corporation or educational institution could create a social networking site
open to all portal users, even ones from separate organizations. 

Additionally, organization administrators can assign organization-scoped roles
to members of their organization. For example, consider an IT Security group in
a corporate setting. You could have a suborganizaton of your IT organization
that handles security for all of the applications company-wide. If you grant the
IT Security organization the portal administrator role, all the members of the
organization would have administrative access to the entire portal. Suppose
further that a user in this organization was later hired by the Human Resources
department. The simple act of removing the user from the IT Security
organization also removes the user's administrative privileges, since the
privilege came from the IT Security organization's role. By adding the user to
the HR organization, any roles the HR organization has (such as access to a
benefits system in the portal) are transferred to the user. In this manner, you
can design your portal to correspond with your existing organization chart and
users' permissions are granted according to their positions in the chart.

Of course, this is only one way to design it. If you have more complex
requirements for permissions within an organization, you can create custom
organization-scoped roles to assemble the permissions you wish to grant to
particular users. Alternatively, you could consider attaching a site to your
organization and using site teams to assemble the sets of permissions (see
below). We'll discuss roles and permissions in more detail later in this
chapter.

Does your organization need to have its own site? Many organizations don't, but
since some do, Liferay allows sites to be attached to organizations. If an
organization has an attached site, the organization's administrators are treated
as the site administrators of the attached site. This means that they can manage
the pages, portlets, and content of the site as well as the users of the
organization. Members of an organization with an attached site are treated as
members of the organization's site. This means that they can access the private
pages of the organization's site, along with any portlets or content there. The
capability of attaching sites to organizations allows portal administrators to
use organizations to facilitate distributed portal administration, not just
distributed user administration.  Next, let's learn how to create and manage
organizations. 

To add an organization, click the *Users and Organizations* link in the Control
Panel. Then click the *Add* button and choose *Regular Organization*. To attach
a site when you create an organization, click on *Organization Site* at the
right and check the *Create Site* box. If you don't know right now if your
organization needs a site, that's fine. You can always add one later if the need
arises. 

![Figure 16.4: Adding a new organization is easy. Once you've clicked *Save* to create the organization, you can specify additional details about the organization.](../../images/01-add-organization-screen.png)

**Name:** Enter a name for the organization.

**Type:** Choose whether this is a regular organization or a location. A
location cannot have any suborganizations.

**Parent Organization:** Select an organization in the system to be the direct
parent of the organization you are creating. Click the *Remove* button to remove
the currently configured parent.

---

 ![Tip](../../images/01-tip.png) **Tip:** By creating an organization, you
 automatically become both a member and receive the Organization Owner role,
 which gives you full administrative rights within the organization. This means
 that you can, for example, appoint other users to be organization
 administrators or organization owners. Organization owners are equivalent to
 organization administrators except that they can assign the Organization Owner
 and Organization Administrator roles to other users; they can also remove the
 memberships of other Organization Administrators or Owners. Organization
 administrators can't make these role assignments and can't manage the
 memberships of other Organization Administrators or Owners.

---

Fill out the information for your organization and click *Save*. As when
creating a new user, after you click *Save* to submit the form, a success
message appears along with a new form which lets you enter additional
information about the organization. Organizations can have multiple email
addresses, postal addresses, web sites, and phone numbers associated with them.
The Services link can be used to indicate the operating hours of the
organization, if any.

For now, click on the *Back* icon. This takes you back to the list of
organizations. Click the *Actions* button next to the new organization you
created. This shows a list of actions you can perform on this organization.

**Edit:** lets you specify details about the organization, including addresses,
phone numbers, email addresses and websites.

**Manage Site:** lets you create and manage the public and private pages of the
organization's site. This only appears for organizations that have attached
sites.

**Assign Organization Roles:** lets you assign organization-scoped roles to
users. By default, Organizations are created with three roles: Organization
Administrator, Organization User and Organization Owner. You can assign one or
more of these roles to users in the organization. All members of the
organization automatically get the Organization User role so this role is hidden
when you click Assign Organization Roles.

**Assign Users:** lets you search and select users in the portal to be assigned
to this organization as members.

**Add User:** adds a new user in the portal and assigns the user as a member of
this organization.

**Add Regular Organization:** lets you add a child organization to this
organization. This is how you create hierarchies of organizations with
parent-child relationships.

**Add Location:** lets you add a child Location, which is a special type of
organization that cannot have any children added to it.

**Delete:** removes this organization from the portal. Make sure the
organization has no users in it first.

If you click the *View* button at the top of the Users and Organizations page
and select *View Hierarchy* you can view both a list of users who are members of
this organization and a list of all the suborganizations of this organization.

Users can join or be assigned to sites when they share a common interest. Users
can be assigned to organizations when they fit into a hierarchical structure.
Users groups provide a more ad hoc way to group users than sites and
organizations. Let's look at them next.

## User Groups [](id=user-groups-liferay-portal-6-2-user-guide-16-en)

User Groups are designed to allow portal administrators to create groups of
users that traverse the organizations hierarchy. They can be used to create
arbitrary groupings of users who don't necessarily share an obvious hierarchical
attribute. Users can be assigned to multiple user groups. For example, consider
a software company with many offices and departments within each office. The
company's office/department structure could be modeled through organizations. In
this situation, it might make sense to create user groups for developers, office
managers, accountant, etc. User Groups are most often used to achieve one of the
following goals:

- To simplify the assignment of several roles to a group of users. For example,
  in a University portal, a user group could be created to group all teachers
  independently of their organizations to make it easier to assign one or
  several roles at once to all the teachers.

- To simplify membership to one or more sites by specifying a group of users.
  Using the previous example, all teachers could be members of the sites
  *University Employees* and *Students and Teachers Collaboration Site* by
  adding the *Teachers* user group as a member.

- To provide predefined public or private pages to the users who belong to the
  user group. For example, the *Teachers* user group could be created to ensure
  the home page on all teachers' personal sites has the same layout and
  applications.

Creating a user group is easy. Navigate to the Control Panel, click on the
*Users Groups* link and then click on the *Add* button. There's only one
required field: Name. It's usually best to enter a description as well. Click
*Save* and you will be redirected back to the *User Groups* page of the Control
Panel.

![Figure 16.5: When creating a new user group, you can select a site template for the public or private pages of the user group site. If you don't select a site template at creation time, you can edit the user group later to add one.](../../images/server-configuration-new-user-group.png)

Note in the figure above that a user group can have a site, with public and
private pages. A user group site is a special type of site that determines the
base pages of the personal sites of all the user group members. User group sites
work similarly to site templates, except that user group site pages are not
copied for each user. Instead, they are shown dynamically along with any custom
pages that the user may have on his/her personal site. For this reason, users
are not allowed to make any modifications to the pages that are *inherited* from
the user group. Optionally, the administrators of the user group can define
certain areas as customizable, just like they can for regular sites. This allows
users to decide which applications they want to place in certain areas of each
page, as well as customize the configurations of the applications.

![Figure 16.6: To edit the pages of a user group site, click *Actions* &rarr; *Manage Site Pages* next to the user group you'd like to edit.](../../images/01-editing-a-user-group.png)

As with the other resources in the portal, you can click the *Actions* button
next to a user group to perform various operations on that group.

**Edit:** allows you to modify the name or description of the user group.

**Permissions:** lets you define which roles have permissions to view, edit,
delete, assign members to the user group, etc.

**Site Permissions:** lets you define which roles have permissions to manage the
user group site, to specify who can administer its pages, export and import
pages and portlet content, manage archived setups and configure its
applications. 

**Manage Site Pages:** allows you to add pages to the user group site, import or
export pages, organize the page hierarchy, modify the look and feel of the
pages, add a logo or access other options from the Manage Site interface.

**Assign Members:** lets you search for and select users in the portal to be
assigned to this user group as well as view the users currently belonging to the
user group .

**Delete:** removes the user group.

If your user group has a site, two options named *Go to the Site's Public Pages*
and *Go to the Site's Private Pages* also appear as links in your user group's
Actions menu. Clicking one of these links opens the user group's site in a new
browser window. Any changes you make to the site are saved automatically. You
can safely close the browser window when you're done.

### Creating and Editing a User Group [](id=creating-and-editing-a-user-group-liferay-portal-6-2-user-guide-16-en)

A user group's site can be administered from the Control Panel. Click on *User
Groups* from the Control Panel to see a list of existing user groups. To edit a
user group, click on its name or description. You can also click on the
*Actions* button to see the full list of actions that can be performed on a user
group. When editing a user group, you can view its site, if it exists, by
clicking the *Open Pages* link under Public Pages or Private Pages (read below
for details on user group sites).

As an example of how user group sites can be used, let's create a user group
called *Bloggers* along with a simple site template. We'll call the site
template *Bloggers* too. It should contain a single *Blog* page with the Blogs
and Recents Bloggers portlets on it. First, navigate to the User Groups page of
the Control Panel. Then click *Add* and enter the name *Bloggers* for your user
group, and optionally, a description. Click *Save* to create your user group.

Our next step is to assign an existing user to the *Bloggers* group.

### Assigning Members to a User Group [](id=assigning-members-to-a-user-group-liferay-portal-6-2-user-guide-16-en)

Navigate to *Users and Organizations* and create a new user called *Joe Bloggs*.
Then navigate to the User Groups page of the Control Panel and click *Actions*
&rarr; *Assign Members* next to the Bloggers group. Click the *Available* tab to
see a list of users that can be assigned to the group.

![Figure 16.7: When assigning members to a user group, click on the *Available* tab to see a list of users able to be added to the user group.](../../images/01-adding-members-to-user-group.png)

From this list, one or more users can be assigned as members of the user group.
After the user group has been created and several users have been added to it,
you can add all those users at once as members of a site in one step from the
*Site Memberships* UI of the site. You can also use the user group when
assigning a role to users from the roles management UI. The next section
explains how to use user group sites.

### User Group Sites [](id=user-group-sites-liferay-portal-6-2-user-guide-16-en)

Liferay allows users to each have a personal site consisting of public and
private pages. Permissions can be granted to allow users to customize their
personal sites at will. Originally, the default configuration of those pages
could only be determined by the portal administrator through the
`portal-ext.properties` file and, optionally, by providing the configuration in
a LAR file. You can still configure it like this but it isn't very flexible or
easy to use.

By using User Group Sites, portal administrators can add pages to the personal
sites of all the users who belong to the site in an easy and centralized way.
All the user group site's public pages are shown as part of the user's public
personal site. All the user group site's private pages are shown as part of the
user's private site. If a user belongs to several user groups, all of its pages
are made part of his public and private site. In an educational institution's
portal, for example, teachers, staff and students could get different default
pages and applications on their personal sites.

The pages a user's personal site *inherits* from a User Group still belong to
the User Group and thus cannot be changed in any way by the user. What the user
group administrators can do is define certain areas of the pages as customizable
to allow the users to choose which applications and what configuration should be
shown in those areas. If a user has permission to add custom pages to his/her
personal site, besides those *inherited* from a user group, the custom pages are
always shown last.

Since the *inheritance* of pages is done dynamically, this system, which was
introduced in Liferay 6.1, can scale to hundreds of thousands of users or even
millions of them without an exponential impact in performance. Previous versions
of Liferay used a different technique that required user group pages be copied
to each user's personal site. For portals upgrading from previous versions of
Liferay, you can keep the old behavior but it has been left disabled by default.
You can enable it by adding the following line to your portal-ext.properties
file:

	user.groups.copy.layouts.to.user.personal.site=true

When this property is set to true, once the template pages have been copied to a
user's personal site, the copies may be modified by the user. Changes done to
the originals in the User Group will only affect new users added to the user
group. Users with administrative privileges over their personal sites can modify
the pages and their content if the *Allow Site Administrators to Modify the
Pages Associated with This Site Template* box has been checked for the template.
When a user is removed from a user group, the associated pages are removed from
the user's personal site. Moreover, if a user is removed from a group and is
subsequently added back, the group's template pages are copied to the user's
site a second time. Note that if a user group's site is based on a site template
and an administrator modifies the user group's site template after users have
already been added to the group, those changes only take effect if the *Enable
propagation of changes from the site template* box for the user group was
checked.

---

 ![Tip](../../images/01-tip.png) **Tip:** Prior to Liferay 6.1, pages from
 different user groups could be combined on users' personal sites by using a
 naming convention. Liferay 6.1 simplifies the way user groups' sites work by
 disallowing page combination. Set the property
 *user.groups.copy.layouts.to.user.personal.site* to true if you want to
 preserve the page combination functionality.

---

You can create a user group's site manually or base it on a site template. To
create a user group's site manually, use the *Actions* menu mentioned above and
choose *Manage Site Pages*. You can add a new public or private page by
selecting the appropriate tab and then clicking the *Add Page* button. Once the
user group has at least one public or private page in place, you can go back to
the *Actions* menu and click on the *Go to the Site's Public Pages* or *Go to
the Site's Private Pages* link to open the user group's site in a new browser
window. In the new window, you can add more pages and portlets and configure
site settings.

You can also base a user group's site on a template. When editing a user group,
use the Public Pages and Private Pages drop down lists to select a site
template. Leave the *Enable propagation of changes from the site template* box
checked to automatically update users' personal sites if the associated site
template changes. If you uncheck this box but recheck it later, the template
pages are copied to the users' sites, overwriting any changes they may have
made. You can allow users to make changes to the pages they receive from the
user group by enabling the customization options on each page.

This flexibility lets you achieve almost any desired configuration for a user's
personal site without having to modify it directly. When a user is assigned to a
user group, the configured site pages are copied directly to the user's personal
site.

Continuing with the example above, we will create a site for our sample user
group. Edit the *Bloggers* user group. Choose an existing Site Template from the
drop down menu for the user group's public pages and click *Save*. After the
page reloads you can click to see the pages and make any changes desired, add
additional pages, etc.

![Figure 16.8: You can select a site template to apply to a user group's public or private pages if the public or private page set is empty. If pages have already been added to the page set, you'll have to remove them before you can apply a site template the page.](../../images/server-configuration-new-user-group.png)

Also, try visiting the public site of one of the users who belongs to the user
group. You'll see that all of the pages in the user group appear as part of the
user site, including ones copied from the site template and ones added
afterwards.

## Roles and Permissions [](id=roles-and-permissions-liferay-portal-6-2-user-guide-16-en)

Roles are used to collect permissions that define a particular function within
the portal, according to a particular scope. Roles can be granted permissions to
various functions within portlet applications. A roles is basically just a
collection of permissions that defines a function, such as Message Board
Administrator. A role with that name is likely to have permissions relevant to
the specific Message Board portlets delegated to it. Users who are placed in
this role will inherit these permissions.

If you navigate to the Control Panel and click on *Roles*, you'll find a single
interface which lets you create roles, assign permissions to them, and assign
users to the roles. Roles can be scoped by portal, site, or organization. To
create a role, click the *Roles* link and then click the *Add* button. You can
choose a Regular, Site or Organization role. A regular role is a portal-scoped
role. Make a selection and then type a name for your role, a title and a
description. The name field is required but the title and description are
optional. If you enter a name and a title, the title will be displayed in the
list of roles on the Roles page of the Control Panel. If you do not enter a
title, the name will be displayed. When you have finished, click *Save*.

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

![Figure 16.9: To examine all the roles defined for your portal, navigate to the Control Panel and click on *Roles*.](../../images/01-roles-and-role-actions.png)

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

Next, let's learn about the difference between the (portal/site/organization)
administrator and owner roles that Liferay provides out-of-the-box.

### Out-of-the-box Liferay Roles [](id=out-of-the-box-liferay-roles-liferay-portal-6-2-user-guide-16-en)

If you navigate to the Control Panel and click on *Roles*, you'll see a list of
all the roles that have been created in your portal. This list includes roles
that Liferay provides out-of-the-box and any additional custom roles. These are
some of Liferay's out-of-the-box roles:

- Guest: The Guest role is assigned to unauthenticated users and grants the
  lowest-level permissions within the portal.
- User: The User role is assigned to authenticated users and grants basic
  basic permissions within the portal.
- Power User: By default, the Power User role grants the same permissions as the
  User role. It's designed to be an extension point for distinguishing regular
  users from more privileged users. For example, you can set up your portal so
  that only Power Users have personal sites.
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

---

 ![Tip](../../images/01-tip.png) **Tip:** It's easy to overlook the differences between
 site and organization owners and site and organization administrators. Remember
 that site and organization administrators *cannot* delete the membership of or
 remove the administrator or owner role from any other administrator or owner.
 They also *cannot* appoint other users as site or organization administrators
 or owners. Site and organization owners *can* delete the membership of or
 remove the administrator or owner roles from other site or organization
 administrators. They *can* appoint other users as site or organization
 administrators or owners.

---

Next, let's examine how to configure the permissions granted by different roles.

### Defining Permissions on a Role [](id=defining-permissions-on-a-role-liferay-portal-6-2-user-guide-16-en)

Roles serve as repositories of permissions. When a roles is assigned to a user,
the user receives all the permissions defined by the role. So, to use a role,
you need to assign members to it and define the permissions you want to grant to
members of the role.

![Figure 16.10: When defining permissions on a role, the Summary view provides a list of permissions that have already been defined for the role. The area on the left side of the screen lets you drill down through various categories of portal permissions.](../../images/01-defining-permissions-on-a-role.png)

When you click on the *Actions* button for a portal-scoped role and select
*Define Permissions*, you'll see a list of all the permissions that have been
defined for that role. To add permissions to a role, drill down through the
categories of permissions on the left side of the screen and click on a specific
category (such as *Site Administration* &rarr; *Pages* &rarr; *Site Pages*.
In the center of the screen, you'll see the permissions that belong to that
category. Flag the checkboxes next to the permissions that you'd like to add the
role, then click *Save*. For non-portal scoped roles, you need to click on the
*Options* link on individual portlets, then *Configuration*, then *Permissions*
to assign permissions within the site that owns the portlet. 

Portal permissions cover portal-wide activities that comprise several
categories, such as site, organization, location, password policy, etc. This
allows you to create a role that, for example, can create new sites within the
portal. This would allow you to grant users that particular permission without
making them overall portal administrators.

For Liferay 6.2, the permissions fall into the following hierarchy of
categories:

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

The three basic categories of permissions are Control Panel, Site
Administration, and My Account. By default, any portal user can manage their
user account via the permissions belonging to the My Account category. Site
administrators can access the site administration tools belonging to the Site
Administration category. And portal administrators can access the entire Control
Panel. For custom roles, you can mix and match permissions from as many
categories as you like.

The permissions in the Site Administration &rrar; Applications categories govern
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

![Figure 16.11: You can fine-tune which actions are defined for a role within a specific application like the Message Boards.](../../images/01-message-board-content-permissions.png)

Each possible action to which permissions can be granted is listed. To grant a
permission, flag the checkbox next to it. If you want to change the scope of a
permission, click the *Change* link next to the gear icon next to the permission
and then choose a new scope. After you finish defining permissions for a role,
click *Save*. For a portal-scoped Message Boards Administrator role, you might
want to grant content permissions for every Message Boards action listed. After
you click *Save*, you'll see a list of all permissions currently granted to the
role. From the Summary view, you can add more permissions or go back by clicking
on the *Back* icon.

The list of permissions that you can define for a role may seem overwhelming.
However, these permissions ensure that you can customize exactly which areas of
your portal you'd like different collections of users to be able to access.
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
flag the *View* permission under the *Resource Permissions* &rrar; *User*
heading.  *View*.  Click *Save*. Once you've saved your permissions
configuration, users who've been assigned to the User Group Manager role will be
able to browse the portal's entire list of users when assigning users to a user
group.

Roles are very powerful and allow portal administrators to define various
permissions in whatever combinations they like. This gives you as much
flexibility as possible to build the site you have designed.

### Permission for Delegating Social Activities Configuration [](id=permission-for-delegating-social-activi-liferay-portal-6-2-user-guide-16-en)

As of Liferay 6.2, there's a permission that allows site administrators to
delegate responsibility for configuring social activities to other users. To
assign this permission to a role, first navigate to the Control Panel and click
on *Roles*. If you'd like to add a new role, do so. Then click *Actions* next to
the role to which you'd like to add social activities configuration permissions
and select *Define Permissions*. Next, drill down to the *Site Administration*
&rarr; *Configuration* &rarr; *Social Activity* permissions category. Flag all
of the permissions and then click *Save*:

- Access in Site Administration
- Configuration
- Permissions
- View

Once these permissions have been assigned to the chosen role, any users assigned
to the role will be able to manage your site's Social Activities configuration.

### Note About Permissions for Deleting Containers [](id=note-about-permissions-for-deleting-con-liferay-portal-6-2-user-guide-16-en)

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

### Note About the Power Users Role [](id=note-about-the-power-users-role-liferay-portal-6-2-user-guide-16-en)

Prior to Liferay 6.0, the default configurations of many Liferay portlets
allowed power users, but not regular users, to access them. Liferay 6.0 and
subsequent versions grant the same default permissions to both power users and
regular users. This way, portal administrators are not forced to use the power
users role. However, Liferay encourages those who do to create their own custom
permissions for the role. 

---

 ![Tip](../../images/01-tip.png) Note: Prior to Liferay version 6.0, Power Users
 and Users did *not* have the same default permissions. So if are using Liferay
 5.2 or a previous version, it's dangerous to remove the Power Users role from
 the default user associations: this could remove certain permissions you expect
 to apply to all users. If you decide to remove the Power Users role from the
 default user associations anyway, you will probably want to modify the
 permissions on certain portlets to make them accessible to all users. To do
 this, see the section on Plugins Configuration below.

---

Now that we've seen how to use organizations and user groups to manage users and
how to use roles to define permissions, let's learn how to configure portal
password policies.

### Password Policies [](id=password-policies-liferay-portal-6-2-user-guide-16-en)

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

## Monitoring [](id=monitoring-liferay-portal-6-2-user-guide-17-en)

The last link in the Users section of the Control Panel is for monitoring. You
can use the Monitoring page to view all of the live sessions in the portal. For
performance reasons, this setting is usually turned off in production.

## Summary [](id=summary-liferay-portal-6-2-user-guide-16-en)

In this chapter, we learned how to manage users, organizations, user groups, and
roles via Liferay's Control Panel. Site memberships and teams aren't the only
way for portal administrators to group and manage users: organizations can be
used to arrange users into hierarchical structures and user groups are a
flexible way to collect groups of users that transcend organizational
hierarchies. You can create roles to define permissions and scope them for the
entire portal or for a particular site or organization. User groups can be
assigned to roles; in this case, each member of the user group is assigned to
the role. We also looked at how to configure password policies for users. We'll
continue our coverage of Liferay's Control Panel in the next chapter, where
we'll examine configurable portal and server settings.
