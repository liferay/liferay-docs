# Organizations [](id=organizations)

An *Organization* groups
[*Users*](/discover/deployment/-/knowledge_base/7-1/users) 
hierarchically. For example, you can model a company's departments (i.e., Human
Resources and Customer Support) with organizations. Organizations often have
their own sites. The *how-to* portion of managing Organizations is in the next
article, 
[Managing Organizations](/discover/deployment/-/knowledge_base/7-1/managing-organizations).
This article contains important conceptual information on what Organizations are
and when they're needed.

Many simple portal designs don't use Organizations at all; they only use sites.
The main purpose of Organizations is to enable distributed User management.
Portal administrators can delegate some user management responsibilities to
Organization administrators. If you don't anticipate needing to delegate User
management responsibilities, your portal design probably doesn't need to include
Organizations. 

+$$$

**User Groups and Organizations:** It's easy to confuse User Groups (covered in
a separate article) with Organizations since they both group Users. User Groups
are an ad hoc collection of Users, organized for a specific function. In the
Lunar Resort, if you wanted a group of bloggers, for example, it wouldn't make
sense to assign the Sales Department the role of blogging (see the article on
Roles if you're not sure what they are). The Sales Department users could blog
whenever a new T-shirt design became available in the Lunar Resort store, but
they probably wouldn't be as diligent about announcing the new Rover Racing
schedule. Instead, creating a User Group containing one individual from each
department who is responsible for blogging would make more sense. Read the
article on User Groups to learn more about how to use them in your portal.

$$$

### When to Use Organizations [](id=when-to-use-organizations)

To decide whether your portal design should include Organizations, think about
its function. A photo-sharing web site could be powered by Sites only. On the
other hand, Organizations are useful for corporations or educational
institutions since their users can be placed into a hierarchical structure.
Don't think that Organizations are only for large enterprises, though. Any group
hierarchy, from large government agencies all the way down to small clubs, can
be modeled with Organizations. Also, don't think that you must decide between an
Organization-based structure or a Site-based structure for assembling your
portal's Users. Users can belong both to Organizations and to independent Sites.
For example, a corporation or educational institution could create a social
networking site open to all Users, even ones from separate Organizations. 

To illustrate what an Organization is, consider a potential Organization of the
Lunar Resort's Intranet. The company hierarchy has three tiers: The Lunar
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
    - Sentient Organism Resources Department--Department of Users that hire, fire and
        regulate intra-company relationships. We'd call it Human Resources, but
        what's stopping Martians from applying? Nothing!

Each department is a sub-Organization of the resort, and each division is a
sub-Organization of the department.

### What can Organization Administrators Do? [](id=what-can-organization-administrators-do)

Whenever you have a collection of Users that fits into a hierarchical structure,
you can use Organizations to model those Users. Organization administrators can
manage all the Users in their Organization *and* in any sub-Organization.
Referring to the hierarchy above, for example, an Organization administrator of
the Lunar Resort could manage any Users belonging to the resort itself, to any
of the departments, or to any of a department's subdivisions. An Organization
Administrator of the Physical Plant Department can manage any Users belonging to
the Physical Plant Department itself, or to the Grounds Crew, the Janitorial
Crew, or the Mechanical Crew. However, an administrator of the Physical Plant
Department can't manage Users belonging to the Recreation Department or the
Retail Group organization.

Organizations and sub-Organization hierarchies can nest to unlimited levels.
Users can be members of one or many Organizations. The rights of an Organization
administrator apply both to his/her Organization and to any child Organizations.
Members of child Organizations are implicit members of their parent
Organizations. This means, for example, that members of child Organizations can
access the private pages of their parent Organizations. This behavior can be
customized in the 
`Organizations` 
[section of the portal-ext.properties](https://docs.liferay.com/portal/7.1-latest/propertiesdoc/portal.properties.html#Organizations)
file where the properties specific to organizations are listed. 

Since Organizations are designed for distributed user administration,
Organization Administrators have an entirely different set of privileges than
Site Administrators. Site Administrators are responsible for the pages,
portlets, content, and membership of their Sites. To this end, they can set the
membership type to Open, Restricted, or Private. They can also add Users to or
remove Users from their Sites but cannot manage the Users themselves. If an
Organization has a Site attached to it, the Organization Administrator has the
same rights as a Site Administrator for managing the Site's content, but an
Organization Site's members are the members of the Organization. Thus
Organization administrators have more user management permissions than Site
administrators: they can edit users belonging to their Organization or any
sub-Organization. They cannot add existing portal Users to their organization,
but they can create new Users within their Organization. Only portal
administrators can add existing users to an Organization.

Organization Administrators can't access the Control Panel by default, but it's
not necessary. In their personal Sites, Organization administrators can click 
the *My Organizations* link to gain access to any Organizations they manage.

![Figure 1: The My Organizations application lets Organization Administrators manage their organizations in their personal site.](../../../images/orgs-my-organizations.png)

### Organization Roles and Permissions [](id=organization-roles-and-permissions)

A huge time-saving benefit of including Organizations into your portal design is
that Organization administrators can assign Organization-scoped Roles to members
of the entire Organization. For example, consider an IT Security group in a
corporate setting. You could have a sub-Organization of your IT organization
that handles security for all applications company-wide. If you grant the IT
Security Organization the portal administrator Role, all members of the
Organization get administrative access to the entire system. Suppose further
that a User in this Organization was later hired by the Human Resources
department. The simple act of removing the User from the IT Security
Organization also removes the User's administrative privileges, since the
privilege came from the IT Security Organization's Role. By adding the User to
the HR Organization, any roles the HR Organization has (such as access to
a benefits system in the portal) are transferred to the User. In this manner,
you can design your portal to correspond with your existing organization chart
and Users' permissions are granted according to their positions in the chart.

Of course, this is only one way to set things up. If you have more complex
requirements for permissions within an Organization, you can create custom
Organization-scoped Roles to assemble the permissions you wish to grant to
particular Users. Alternatively, attach a Site to your Organization and use Site
Teams to assemble the sets of permissions (see below). See the 
[Roles and Permissions article](https://dev.liferay.com/discover/portal/-/knowledge_base/7-1/roles-and-permissions)
for more detail.

### Organization Sites [](id=organization-sites)

Does your Organization need to have its own Site? If an organization has an
attached Site, the Organization's administrators are treated as the Site
administrators. This means that they can manage the pages, portlets, and content
of the Site as well as the Users of the Organization. Members of an Organization
with an attached Site are treated as members of the Organization's Site. This
means that they can access the private pages of the Organization's Site, along
with any portlets or content there. Attaching Sites to Organizations allows
portal administrators to use Organizations to facilitate distributed portal
administration, not just distributed User administration. 

That's a lot of information on Organizations. Next, learn how to create and
manage Users and Organizations. 

