# Organizations [](id=organizations)

An *Organization* is a way to group
[*Users*](/discover/deployment/-/knowledge_base/7-1/users)
hierarchically. For example, each of your company's departments (Human Resources
and Customer Support, for example) could be an organization. Often times,
organizations have their own site. The *how-to* portion of managing
organizations is in the next article, 
[Managing Organizations](/discover/deployment/-/knowledge_base/7-1/managing-organizations).
This article contains important conceptual information on what organizations are
and when they're needed.

Many simple portal designs don't use organizations at all; they only use sites.
The main purpose of organizations is to enable distributed user management.
Using organizations, portal administrators can delegate some user management
responsibilities to organization administrators. If you don't anticipate
needing to delegate user management responsibilities, your portal design
probably doesn't need to include organizations. 

+$$$

**User Groups and Organizations:** It's easy to confuse User Groups (covered in
a separate article) with Organizations since they both group users. User Groups
are usually an ad hoc collection of users, organized for a specific function in
the portal. In the Lunar Resort, if you wanted a group of bloggers, for example,
it wouldn't make sense to assign the Sales Department the role of blogging (see
the article on roles if you're not sure what they are). The Sales Department
users would be able to blog whenever a new t-shirt design became available in
the Lunar Resort store, but they probably wouldn't be as diligent about
announcing the new Rover Racing schedule. Instead, creating a user group
containing one individual from each department who is responsible for blogging
would make more sense. Read the article on User Groups to learn more about how
to use them in your portal.

$$$

### When to Use Organizations [](id=when-to-use-organizations)

To decide whether your portal design should include organizations, think about
your portal's function. A simple photo-sharing web site could be powered by
sites only. On the other hand, organizations are useful for corporations or
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
Resort, its departments, and divisions within each department.

- Lunar Resort--The top-level Organization.
	- Physical Plant Department--Department of users that keep the place running.
		- Grounds Crew--Users that maintain the grounds.
		- Janitorial Crew--Users who keep the resort clean.
		- Mechanical Crew--Users who fix stuff, like lunar rovers.
	- Recreation Department--A department that makes sure much fun
	  is had by guests of the Lunar Resort.
        - Golf Instructors--Teach guests how to golf on the moon.
        - Rover Race Instructors--Teach guests how to drive the lunar rovers.
        - Lunar Sherpas--Lead guests on moon hikes.
    - Sales Department--A department of users who sell things to Lunar
      Resort guests.
        - Up-sale Group--Make sure guests know how easy it is to improve
          their stay by spending more money.
        - Souvenir and Memorabilia Group--Peddle souvenirs to Lunar Resort guests.
        - Retail Group--Maintain the Lunar Resort store, which
          contains basic necessities, since guests are coming all the way from Earth.
    - Sentient Organism Resources Department--Department of users that hire, fire and
        regulate intra-company relationships. We'd call it human resources, but
        what's stopping Martian's from applying? Nothing!

Each department is a sub-organization of the resort, and each division is a
sub-organization of the department.

### What can Organization Administrators Do? [](id=what-can-organization-administrators-do)

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

Organizations and sub-organization hierarchies can be created to unlimited
levels. Users can be members of one or many organizations. The rights of an
Organization Administrator apply both to his/her organization and to any child
organizations. Members of child organizations are implicit members of their
parent organizations. This means, for example, that members of child
organizations can access the private pages of their parent organizations. This
behavior can be customized in your portal's `portal-ext.properties`
configuration file. There's an `Organizations` 
[section of the portal.properties](https://docs.liferay.com/portal/7.1-latest/propertiesdoc/portal.properties.html#Organizations)
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

<!-- REPLACE [Figure 1: The My Organizations application lets Organization Administrators manage their organizations in their personal site.](../../images/organizations-my-organizations.png)-->

### Organization Roles and Permissions [](id=organization-roles-and-permissions)

A huge time-saving benefit of including organizations into your portal design is
that organization administrators can assign organization-scoped roles to members
of the entire organization. For example, consider an IT Security group in a
corporate setting. You could have a sub-organization of your IT organization
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

Of course, this is only one way to set up your portal. If you have more complex
requirements for permissions within an organization, you can create custom
organization-scoped roles to assemble the permissions you wish to grant to
particular users. Alternatively, attach a site to your organization and use
site teams to assemble the sets of permissions (see below). See the 
[Roles and Permissions article](https://dev.liferay.com/discover/portal/-/knowledge_base/7-1/roles-and-permissions) 
for more detail.

### Organization Sites [](id=organization-sites)

Does your organization need to have its own site? If an organization has an
attached site, the organization's administrators are treated as the site
administrators of the attached site. This means that they can manage the pages,
portlets, and content of the site as well as the users of the organization.
Members of an organization with an attached site are treated as members of the
organization's site. This means that they can access the private pages of the
organization's site, along with any portlets or content there. The capability of
attaching sites to organizations allows portal administrators to use
organizations to facilitate distributed portal administration, not just
distributed user administration. 

That's a lot of information on organizations. Next, learn how to create and
manage users and organizations. 

