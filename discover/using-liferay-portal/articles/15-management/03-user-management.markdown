# User Management [](id=user-management)

If you click the *Users* link on the left menu of the control panel, there are
now two users in the list of users. If you want to change something about a
particular user, you can click the *Actions* button next to that user.

**Edit User:** takes you back to the Edit User page where you can modify
anything about the user.

**Permissions:** allows you to define which roles have permissions to edit the
user.

**Manage Pages:** allows you to edit the personal pages of a user.

**Impersonate User:** opens another browser window which allows you to browse
the site as if you were the user.

**Deactivate:** deactivates the user's account.

Note most users can't perform most of the above actions. In fact, most users
won't even have access to this section of the control panel. You can perform all
of the above functions because you have administrative access.

Let's look next at how to manage organizations. 

## Organizations [](id=organizations)

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
administrator of the Atlantic Soccer Club would not be able to manage users
belonging to the Atlantic Baseball Association or to the Bethel Basketball Club.

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

To add an organization, click the *Users and Organizations* link on the left
side of the control panel. Then click the *Add* button and choose *Regular
Organization*. To attach a site when you create an organization, click the
*Organization Site* tab at the right and check the *Create Site* box. If you
don't know right now if your organization needs a site, that's fine. You can
always add one later if the need arises. 

![Figure 15.4: Adding an organization](../../images/01-add-organization-screen.png)

**Name:** Enter a name for the organization.

**Type:** Choose whether this is a regular organization or a location. A
location cannot have any suborganizations.

**Parent Organization:** Select an organization in the system to be the direct
parent of the organization you are creating. Click the *Remove* button to remove
the currently configured parent.

+$$$

**Tip:** Note that you're already a member of any organizations you create. By 
creating an organization, you become both a member and receive the Organization 
Owner role, which gives you full rights to the organization. You can, of course, 
add other users to this role to make them Organization Owners. 

$$$

Fill out the information for your organization and click *Save*. As before with
users, the form reappears and you can enter more information about the
organization. Organizations can have multiple email addresses, postal addresses,
web sites and phone numbers associated with them. The Services link can be used
to indicate the operating hours of the organization, if any.

For now, click the *Back* button. This takes you back to the list of
organizations.

Click the *Actions* button next to the new organization you created. This shows
the actions you can take to manipulate this organization.

**Edit:** lets you specify details about the organization, including addresses,
phone numbers, email addresses and websites.

**Manage Site:** lets you create and manage public and private pages for the
organization's site.

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
