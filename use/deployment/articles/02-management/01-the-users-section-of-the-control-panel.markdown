# The Users Section of the Control Panel [](id=the-users-section-of-the-control-panel)

The Users section of the Control Panel is used for most administrative tasks
involving user management. There, you'll find an interface for the creation and
maintenance of the following portal entities: users, organizations, user groups,
and roles. 

![Figure 2.1: The Users section of the Control Panel allows portal administrators to manage users, organizations, user groups, and roles. It also allows administrators to monitor users' live portal sessions if monitoring has been enabled for the portal.](../../images/users-section-control-panel.png)

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

## Adding and Editing Users

Are you using Liferay's default administrative user, Test Test
(test@liferay.com)? If so, let's add a user account for yourself and configure
your new account so it has the same administrative access as the default
administrator account. From the Dockbar, click *Admin* and select *Control
Panel*. Then click on *Users and Organizations*. Click the *Add* button and
select *User*. Fill out the Add User form using your name and email address.
When you are finished, click *Save*.

![Figure 2.2: You need to at least enter a screen name, email address, and first name in order to create a new user account.](../../images/01-add-user-screen.png)

After you submit the form, the page reloads with a success message. An expanded
form appears that allows you to fill out a lot more information about the user.
You don't have to fill anything else out right now. Just note that when the user
account was created, a password was automatically generated. If Liferay was
correctly installed and a mail server was set up (see chapter 14), an email
message with the user's new password was sent to the user's email address. This,
of course, requires that Liferay can properly communicate with your SMTP mail
server.

![Figure 2.3: Once you've completed the basic form for creating a new user account, you'll see a much more detailed form for editing the user and adding additional information.](../../images/01-user-account-editor.png)

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

## User Management

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

## Organizations

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

![Figure 2.4: Adding a new organization is easy. Once you've clicked *Save* to create the organization, you can specify additional details about the organization.](../../images/01-add-organization-screen.png)

**Name:** Enter a name for the organization.

**Type:** Choose whether this is a regular organization or a location. A
location cannot have any suborganizations.

**Parent Organization:** Select an organization in the system to be the direct
parent of the organization you are creating. Click the *Remove* button to remove
the currently configured parent.

+$$$

**Tip:** By creating an organization, you
 automatically become both a member and receive the Organization Owner role,
 which gives you full administrative rights within the organization. This means
 that you can, for example, appoint other users to be organization
 administrators or organization owners. Organization owners are equivalent to
 organization administrators except that they can assign the Organization Owner
 and Organization Administrator roles to other users; they can also remove the
 memberships of other Organization Administrators or Owners. Organization
 administrators can't make these role assignments and can't manage the
 memberships of other Organization Administrators or Owners.

$$$

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
