# Adding and Managing Organizations [](id=adding-and-managing-organizations)

<!-- Contains 6.2 links for now. Needs more links, and needs screen shots, when
applicable. -->

For some enterprises, particularly those that are organized in a hierarchical
structure, using organizations as a user management tool can make a lot of
sense, and can make the delegation of user management responsibilities much
easier than it would be otherwise. To understand what a hierarchical structure
looks like, consider Major League Baseball (MLB). There's the main MLB
organization, The American and National Leagues, and each League's Divisions
(for example, the American League's East Division). Finally, the Individual
Teams are under the Divisions. This structure could be matched in the MLB portal
using organizations.

As with users, organizations are managed in the Control Panel. The steps for
creating and managing organizations can be found after the next section, but if
you're not sure what an organization really is or whether you need organizations
in your portal, read this next section to help you wrap your head around it.

<!-- If at all possible, this next section should be made more brief.  -->

## What are Organizations? [](id=what-are-organizations)

An Organization is a way to group Liferay Users hierarchically. For example,
your company's departments (Human Resources, for example) could be an
organization in Liferay. Often times, organizations have their own site.

Many simple portal designs don't use organizations at all; they only use sites
(see the sections on [Web content Management](/discover/portal/-/knowledge_base/6-2/web-content-management) and
[Advanced Web Content Management](/discover/portal/-/knowledge_base/6-2/advanced-web-content-management)
for more information on sites). The main purpose of organizations is to allow
for distributed user management. Using organizations, portal administrators can
delegate some user management responsibilities to organization administrators.
If you don't anticipate needing to delegate user management responsibilities,
your portal design probably doesn't need to include organizations. In order to
decide whether or not your portal design should include organization, think
about your portal's function. A simple photo-sharing web site could be powered
by sites only. On the other hand, organizations are useful for corporations or
educational institutions since their users can easily be placed into a
hierarchical structure. Don't think that organizations are only for large
enterprises, though. Any group hierarchy, from large government agencies all the
way down to small clubs, can be modeled with organizations. Also, don't think
that you must decide between an organization based structure or a site based
structure for assembling your portal's users. Users can belong both to
organizations and to independent sites. For example, a corporation or
educational institution could create a social networking site open to all portal
users, even ones from separate organizations. 

To illustrate what an Organization is, consider a potential organization of the
Lunar Resort's intranet. The company hierarchy has three tiers: The Lunar
Resort, its Departments, and divisions within each department.

- Lunar Resort--The top-level Organization.
	- Physical Plant Department--Department of users that keep the place running.
		- Grounds Crew--Users that maintain the grounds.
		- Janitorial Crew--Users who keep the resort clean.
		- Mechanical Crew--Users who fix stuff, like Lunar rovers.
	- Recreation Department--A department that makes sure much fun
	  is had by guests of the Lunar Resort.
        - Golf Instructors--Teach guests how to golf on the moon.
        - Rover Race Instructors--Teach guests how to drive the Lunar Rovers.
        - Lunar Sherpas--Lead guests on moon hikes.
    - Sales Department--A department of users who sell things to Lunar
      Resort Guests.
        - Up-sale Group--Make sure Guests know how easy it is to improve
          their stay by spending more money.
        - Souvenir and Memorabilia Group--Peddle souvenirs to Lunar Resort Guests.
        - Retail Group--Maintain the Lunar Resort store, which
          contains basic necessities, since guests are coming all the way from Earth.

Each department is a sub-organization of the resort, and each further division
is a sub-organization of the department.

+$$$

**Note:** It's easy to confuse User Groups (covered in a separate article) and
Organizations. Both are used to group users, but User Groups are usually an ad
hoc collection of users, organized for a specific function in the portal. In the
Lunar Resort, if you wanted a group of bloggers, for example, it wouldn't make
sense to assign the Sales Department the role of blogging (again, see the
article on roles if you're not sure what they are). The Sales Department users
would be able to blog whenever a new t-shirt design became available in the
Lunar Resort store, but they probably wouldn't be as diligent about announcing
the new Rover Racing schedule. Instead, creating a user group containing one
individual from each department who is responsible for blogging would make more
sense. Read the article on User Groups to learn more about how to use them in
your portal.

$$$

Whenever you have a collection of users that fit into a hierarchical structure,
you can use organizations to model those users. Organization administrators can
manage all the users in their organization *and* in any sub-organization.
Referring to the hierarchy above, for example, an organization administrator of
the Lunar Resort could manage any users belonging to the resort itself, to any
of the departments, or to any of a department's subdivisions. An Organization
Administrator of the Physical Plant Department can manage any users belonging to
the Physical Plant Department itself, or to the Grounds Crew, the Janitorial
Crew, or the Mechanical Crew. However, an administrator of the Physical Plant
Department can't manage users belonging to the Recreation Department or users
in the Retail Group organization.

Organizations and suborganization hierarchies can be created to unlimited
levels. Users can be members of one or many organizations. The rights of an
Organization Administrator apply both to his/her organization and to any child
organizations. Members of child organizations are implicit members of their
parent organizations. This means, for example, that members of child
organizations can access the private pages of their parent organizations. This
behavior can be customized in your portal's `portal-ext.properties`
configuration file. There's an `Organizations` [section of the
portal.properties](https://docs.liferay.com/portal/6.2/propertiesdoc/portal.properties.html#Organizations)
file where the properties specific to organizations are listed. 

Since organizations are designed for distributed user administration,
Organization Administrators have an entirely different set of privileges than
Site Administrators. Site Administrators are responsible for the pages,
portlets, and content of their site. They are also responsible for managing the
membership of their site. To this end, they can set the membership type to Open,
Restricted, or Private. They can also add users to or remove users from their
site but cannot manage the users themselves. If an organization has a site
attached to it, the Organization Administrator has the same administrative
ability as a Site Administrator when it comes to managing the site's content,
but the site membership management is different. An organization site's members
will simply be the members of the organization. Organization Administrators have
more user management permissions than Site Administrators; they can edit users
belonging to their organization or any sub-organization. They cannot add
existing portal users to their organization, but they can create new users
within their organization. Only portal administrators can add existing users to
an organization.

Organization Administrators can't access the Control Panel by default, but they
don't need to. In their personal site, organization administrators can click on
the *My Organizations* link to gain access to any organizations they manage.

![Figure 1: The My Organizations application lets Organization Administrators manage their organizations in their personal site.](../../images/organizations-my-organizations.png)

A huge time-saving benefit of including organizations into your portal design is
that organization administrators can assign organization-scoped roles to members
of the organization. For example, consider an IT Security group in a corporate
setting. You could have a suborganizaton of your IT organization that handles
security for all of the applications company-wide. If you grant the IT Security
organization the portal administrator role, all the members of the organization
would have administrative access to the entire portal. Suppose further that a
user in this organization was later hired by the Human Resources department. The
simple act of removing the user from the IT Security organization also removes
the user's administrative privileges, since the privilege came from the IT
Security organization's role. By adding the user to the HR organization, any
roles the HR organization has (such as access to a benefits system in the
portal) are transferred to the user. In this manner, you can design your portal
to correspond with your existing organization chart and users' permissions are
granted according to their positions in the chart.

Of course, this is only one way to set up your portal. If you have more complex
requirements for permissions within an organization, you can create custom
organization-scoped roles to assemble the permissions you wish to grant to
particular users. Alternatively, you could consider attaching a site to your
organization and using site teams to assemble the sets of permissions (see
below). See the [Roles and Permissions article](https://dev.liferay.com/discover/portal/-/knowledge_base/6-2/roles-and-permissions) for more detail.

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
distributed user administration. 

That's a lot of information on organizations. Next, learn how to create and
manage organizations. 

## Adding Organizations [](id=adding-organizations)

Now add an organization to the portal (perhaps start by adding the *Physical
Plant Department* organization to the Lunar Resort): 

1.  Click the *Users and Organizations* link in the Control
Panel. 
2.  Click the *Add* button and choose *Regular Organization*. 
    To attach a site when you create an organization, click on *Organization
    Site* at the right and check the *Create Site* box. If you don't know right now
    if your organization needs a site, that's fine. You can always add one later. 
<!-- Add a screenshot showing the Add Org UI -->
    - Enter a Name for the organization.
    - Choose whether this is a regular organization or a location. A
location cannot have any suborganizations.
    - Select an organization in the system to be the direct parent of the
organization you are creating. Click the *Remove* button to remove the currently
configured parent.
    - Click *Save* when finished filling out the Add Orgnanization form.

As when creating a new user, once you submit the form a success message appears
and you have access to a new form which lets you enter additional information
about the organization. Organizations can have multiple email addresses, postal
addresses, web sites, and phone numbers associated with them.  The Services link
can be used to indicate the operating hours of the organization, if any.

<!-- Figure that shows a screenshot of the additional info for the org. -->

+$$$

**Tip:** After creating an organization, you should assign the desired user to
the Organization Owner Role. The Organization Owner can do everything that an
Organization Administrator can. In addition to their full administrative rights
within the organization, they can do these things:

- Appoint other users to be Organization Administrators 
- Appoint other users to be Organization Owners
- Remove the memberships of other Organization Administrators or Owners

Organization Administrators can't make these role assignments and can't manage
the memberships of other Organization Administrators or Owners.

$$$

## Editing Organizations [](id=editing-organizations)

To edit an organization go to the Users and Organizations section of the Control
Panel. You should see any active portal users and organizations listed.  Click
the *Actions* button (as usual, the vertical ellipsis icon) next to an
organization. This shows a list of actions you can perform on this organization.

<!-- Screenshot of Actions Button menu options -->
- *Edit* lets you specify details about the organization, including addresses,
phone numbers, email addresses and websites.

- *Manage Site* lets you create and manage the public and private pages of the
organization's site. This only appears for organizations that have attached
sites.

- *Assign Organization Roles* lets you assign organization-scoped roles to
users. By default, Organizations are created with three roles: Organization
Administrator, Organization User and Organization Owner. You can assign one or
more of these roles to users in the organization. All members of the
organization automatically get the Organization User role so this role is hidden
when you click Assign Organization Roles.

- *Assign Users* lets you search and select users in the portal to be assigned
to this organization as members.

- *Add User* adds a new user in the portal and assigns the user as a member of
this organization.

- *Add Regular Organization* lets you add a child organization to this
organization. This is how you create hierarchies of organizations with
parent-child relationships.

- *Add Location* lets you add a child organization of the type Location, which
  is a special type of organization that cannot have any children added to it.

- *Delete* removes this organization from the portal. Make sure the
organization has no users in it first.

If you click the *View* button at the top of the Users and Organizations page
and select *View Hierarchy* you can view both a list of users who are members of
this organization and a list of all the suborganizations of this organization.

<!-- See if a screenshot of this for the proposed Lunar Resort intranet
organizations could replace the textual hierarchy representation above -->

Users can join or be assigned to sites when they share a common interest. Users
can be assigned to organizations when they fit into a hierarchical structure.
Users groups provide a more ad hoc way to group users than sites and
organizations. Let's look at them next.

