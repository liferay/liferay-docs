<!-- I moved a lot of the intro material for Users and Organizations to the
intro of this article, trying to deal with the how-to aspects of managing
organizations and users in the subsections of the article. I wanted the readers
to be able to get to the steps quickly. Now I wonder if there's too much text in
the intro, before even getting to the how-to steps. Open to suggestions -->

<!-- Ripped Users out into a separate article. I think this got too long -->

# Users and Organizations

Users and Organizations are fundamental entities in Liferay. If your portal
requires people (even just a set of site administrators) to have an account that
they sign into to do anything in the portal, you need to know about Users. If
your users are at all divided hierarchically, like into departments, you might
find Organizations helpful.

If you already understand users and organizations in Liferay and simply want to
begin exploring Liferay's User Management functionality, skip the next two
sections and get to the "how-to" part of this article. Otherwise, read on to
understand what's meant when you hear the term User or, especially,
Organization.


## Adding and Managing Organizations

For some enterprises, particularly those that are organized in a hierarchical
structure, using organizations as a user management tool can make a lot of
sense, and can make the delegation of user management responsibilities much
easier than it would be otherwise. To understand what a hierarchical
organization looks like, consider Major League Baseball. There's the main
organization (MLB), The American and National Leagues, each League's Divisions
(for example, the American League's East Division), and the
Individual Teams of the Divisions. 

As with users, organizations are managed in the Control Panel. 

<!-- If at all possible, this information should be made more biref. If not,
maybe organizations and users should each have separate articles? -->

Organizations and suborganization hierarchies can be created to unlimited
levels. Users can be members of one or many organizations. The rights of an
organization administrator apply both to his/her organization and to any child
organizations. By default, members of child organizations are implicit members
of their parent organizations. This means, for example, that members of child
organizations can access the private pages of their parent organizations. This
behavior can be customized in your portal's `portal-ext.properties`
configuration file. There's an `Organizations` [section of the
portal.properties](https://docs.liferay.com/portal/7.0/propertiesdoc/portal.properties.html#Organizations)
file where the properties specific to organizations are listed. 

    ##
    ## Organizations
    ##

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

<!-- Does some sort of table make sense? There's a lot of text here --> 

Organization Administrators | Site Administrators
:-------------------------: | :-----------------:
Can add users to the site | Can add users to the site
Can manage all the users of the <br /> organization and any suborganizations | Cannot manage users

Many simple portal designs don't use organizations at all; they only use sites
(see the sections on Web content Management and Advanced Web Content Management
for more information on sites). Remember that the main purpose of organizations
is to allow for distributed user management. They allow portal administrators to
delegate some of their user management responsibilities to organization
administrators. If you don't anticipate needing to delegate user management
responsibilities, your portal design probably doesn't need to include
organizations. In order to decide whether or not your portal design should
include organization, think about your portal's function. A simple photo-sharing
web site, for example, could be powered by sites only. On the other hand,
organizations are useful for corporations or educational institutions since
their users can easily be placed into a hierarchical structure. In fact,
organizations in Liferay are designed to model any group hierarchy, from those
of government agencies all the way down to those of small clubs. Of course,
users can belong both to organizations and to independent sites. For example, a
corporation or educational institution could create a social networking site
open to all portal users, even ones from separate organizations. 

A huge time-saving benefit of including organizations into your portal design is
that organization administrators can assign organization-scoped roles to members
of their organization. For example, consider an IT Security group in a corporate
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

Of course, this is only one way to design it. If you have more complex
requirements for permissions within an organization, you can create custom
organization-scoped roles to assemble the permissions you wish to grant to
particular users. Alternatively, you could consider attaching a site to your
organization and using site teams to assemble the sets of permissions (see
below). Roles and permissions are discussed in more detail in a separate
article.

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

### What are Organizations?

An Organization is a way to group Liferay Users hierarchically. For example,
your company's Human Resources department could be an organization in Liferay.
Often times, organizations have their own Site. User Groups are covered in a
separate article, and are different from User Groups, which are more ad hoc
collections of Users.

To illustrate what and Organization is, consider a potential organization of the Lunar
Resort's intranet. The company can be organized into three tiers: The Lunar Resort,
its Departments, and divisions
within each department.

- Lunar Resort--The top-level Organization.
	- Physical Plant Department--Department of users that keep the place running.
		- Grounds Crew--Users that maintain the grounds.
		- Janitorial Crew--Users who keep the resort clean.
		- Mechanical Crew--Users who fix stuff, like Lunar rovers.
	- Recreation Department--A department that makes sure much fun
	  is had by guests of the Lunar Resort.
        - Golf Instructors--Teach guests how to golf on the moon.
        - Rover Race Instructors--Teach guests how to drive the Lunar Rovers.
        - Nature Sherpas--Lead guests on moon hikes.
    - Sales Department--A department of users who sell things to Lunar
      Resort Guests.
        - Up-sale Specialists--Make sure Guests know how easy it is to improve
          their stay by spending more money.
        - Souvenir Specialists--Peddle souvenirs to Lunar Resort Guests.
        - Necessities Specialists--Maintain the Lunar Resort store, which
          contains basic necessities, since guests are coming all the way from Earth.

Each department is a sub-organization of the resort, and each further division
is a sub-organization of the department.

Whenever you have a collection of users that fit into a hierarchical structure,
you can use organizations to model those users. Organization administrators can
manage all the users in their organization *and* in any sub-organization.
Referring to the hierarchy above, for example, an organization administrator of
the Lunar Resort could manage any users belonging to the resort itself, to any
of the departments, or to any of a department's subdivisions. An organization
administrator of the Physical Plant Department could manage any users belonging
to the Physical Plant Department itself, or to the Grounds Crew, the Janitorial
Crew, or the Mechanical Crew. However, an administrator of the Physical Plant
Department would not be able to manage users belonging to the Recreation
Department or to users in the Necessities Specialists organization.

<!-- Users and organizations tool tip form portal: Users and Organizations Users are individuals who perform tasks using the
portal. Administrators can create new users or deactivate existing users. Users
can join sites, be placed into organization hierarchies, be delegated
permissions in roles, or be collected into user groups. User groups can cross
organizational boundaries, and can be used to assign all members to other
collections, such as sites or roles. User groups may also be used to customize
personal site templates for members.

-->


## Adding Organizations


<!-- Yikes, these steps are buried in the above paragraphs -->

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

As when creating a new user, submit the form, a success message appears along
with a new form which lets you enter additional information about the
organization. Organizations can have multiple email addresses, postal addresses,
web sites, and phone numbers associated with them.  The Services link can be
used to indicate the operating hours of the organization, if any.

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

## Editing Organizations

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

- *Add Location* lets you add a child Location, which is a special type of
organization that cannot have any children added to it.

- *Delete* removes this organization from the portal. Make sure the
organization has no users in it first.

If you click the *View* button at the top of the Users and Organizations page
and select *View Hierarchy* you can view both a list of users who are members of
this organization and a list of all the suborganizations of this organization.

Users can join or be assigned to sites when they share a common interest. Users
can be assigned to organizations when they fit into a hierarchical structure.
Users groups provide a more ad hoc way to group users than sites and
organizations. Let's look at them next.


<!-- Note: Location is an organization type that cannot contain sub-organizations, or child
organizations (so, it can't be a parent). It can itself be a child though. -->
