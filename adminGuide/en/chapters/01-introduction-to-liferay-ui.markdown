# Introduction to Liferay

This chapter covers these activities:

-   *Liferay's User Interface:* How to navigate around Liferay and use the Control Panel.

-   *Liferay Administration:* How to administer a Liferay portal.

## Liferay's User Interface

Liferay is a *portal server*. This means that it is designed to be a
single environment where all of the applications a user needs can run,
and these are integrated together in a consistent and systematic way. If
an application lives outside of the portal, the portal should be able to
consume some resource of the application (such as an RSS feed or a
subset of functionality in a “dashboard” application) so that the end
user can see everything he or she interacts with at a glance.

To achieve this, all of the application functionality within Liferay
Portal is in fragments of the page called *portlets*. Portlets are web
applications that run in a portion of a web page. Liferay's core is a
portlet container, and the container's job is to aggregate the set of
portlets that should appear on any particular page and display them
properly to the user. In this way, one or many applications can reside
on a page, and the user can (at the administrator's discretion) arrange
them in the way that works best for the user.

Portlet applications, like servlet applications, have become a Java
standard which various portal server vendors have implemented. The Java
standard defines the portlet specification. A JSR-168 or JSR-286
standard portlet should be deployable on any portlet container which
supports those standards. Portlets are placed on the page in a certain
order by the end user and are served up dynamically by the portal
server.

Portal applications come generally in two flavors: 1) multiple portlets
can be written to provide small amounts of functionality and then are
aggregated by the portal server into a larger application, or 2) whole
applications can be written to reside in only one or a few portlet
windows. The choice is up to those designing the application. Developers
only have to worry about what happens inside of the portlet itself; the
portal server handles building out the page as it is presented to the
user.

Portlets are not difficult to build, and Java standard portlets can be
written by any Java developer with experience in writing web
applications. Liferay provides a Plugins Software Development Kit that
makes creating portlet projects easy. For further information about the
Plugins SDK, please see *Liferay in Action*, published by Manning
Publications, which is the official guide to Liferay development.

Additionally, Liferay supports portlets written in other programming
languages, such as PHP, Ruby, Groovy, or Python. Sample portlets written
in these languages can be checked out from our Subversion repository
([http://svn.liferay.com/repos/public/plugins/trunk/portlets](http://svn.liferay.com/repos/public/plugins/trunk/portlets)).

### Navigating Liferay

Assuming that you've followed the instructions in Chapter 2 for removing
the demo web site (7 Cogs), Liferay initially presents a very simple
interface. Unauthenticated users can navigate the public pages of the
portal and will see a Sign In link in the top right corner of the
screen.

To sign into Liferay for the first time, you can click the *Sign In*
link. You will then be presented with the **Sign In Portlet**. This
portlet allows a user (or a prospective user) to do several things: sign
in to Liferay, create a new account on the portal, or have a password
reminder emailed if the user has forgotten his or her password. To sign
in for the first time, don't create an account for yourself. We will do
that later. If you were to create a new account on the portal for
yourself now, it would be created using Liferay's defaults, which means
the account would not have access to the administrative portlets you
need in order to set up Liferay for your organization. For this reason,
you will need to sign in as the default administrative user. This user's
credentials are:

**User Name: **test@liferay.com

**Password:** test** **

![image](../../images/03-logging-into-liferay-portal.png)\
*Illustration 1: Logging into Liferay Portal*

Go ahead and sign into your new portal using these credentials. As you
can see, Liferay by default uses one's email address as the user name.
This can be changed later if you don't like this functionality, but it
is generally a good practice to keep it this way. Users' email addresses
are not normally things they will forget, and they are unique to each
user, so they make good candidates for user IDs.

The first page that will be displayed when a user logs in for the first
time is the Terms of Use page. This page is displayed to the user before
he or she is allowed to access the portal. By default, your users will
have to agree to your terms of service before they will be allowed to
used the portal. This page can be customized to contain whatever text
you want, or the feature can be disabled altogether. To continue, you
will need to agree to the Terms of Service.

Once you log in as the administrative user, you will see that the
Dockbar has now appeared across the top of the page. The Dockbar is the
primary tool logged in users have for navigating the portal and
accessing administrative functions from anywhere on the web site.
Depending on the logged-in user's roles and what section of the website
the user is viewing, you may see all or only some of the options
available in the Dockbar.

As an administrator, the first option you will see on the Dockbar is
*Add* . Mousing over *Add* will reveal a list of things that you can
add. You can use this to add a new page at the current navigation level,
or add portlets to the current page. When you first pull down the menu,
you will see a list of common portlets that you can click on to add to
the page. You will also see a *More* option, which will show you all of
the currently available portlets. From the expanded *More* menu, you can
add portlets to the current page. If you want to add a portlet to the
current page, you can click the *Add* button next to a portlet to add it
to the first column in the page, or drag the portlet from the menu to
where you want it on the page.

![image](../../images/03-add-menu-from-dockbar.png)\
*Illustration 2: Add Menu from the Dockbar*

The next option you'll see is the *Manage* menu. From this menu, you can
access various settings for the current page and site. The page settings available are *Page, Page Layout, and Page Customizations*. Clicking on *Page* brings up a dialog box which enables you to modify details about the current page. *Page Layout* is a shortcut to a tab of this dialog box which lets you choose the layout template for the current page. *Page Customizations* enables you to directly select portions of the page to be customizable. Users with permission to customize your page will then be able to customize the portlets you selected. The site settings are the same as their counterparts in the *Control Panel*, and are covered in detail later in this chapter.

The next thing you'll see is a check box labeled *Toggle Edit Controls*. This lets you turn on and off the edit controls in the top of the
portlet windows. This is helpful for administrators who want to look at
a page they're working on and see it the way a regular user would.

If you roll your mouse over *Go to*, the *Dockbar* will expand,
showing all of the places in the portal to which you have access. If you have access to the *Control Panel*, this will be the topmost item in the menu.
Initially, your current location will be highlighted. You will see that you
are in the *liferay.com* community, on the public pages. Liferay
allows for various configurations of pages for end users: you can
configure it so that some or all users have their own pages, public and
private (or both), upon which they can place the portlets they need to
use. The administrator account by default has its own pages. Because you
are logged in with an account that has Administrator privileges, you can
see everything in the portal.

One of the most important tools Liferay offers for managing your portal
is the aforementioned *Control Panel*. The *Control Panel* is composed of
administrative portlets that you can use to mange various aspects of the
portal.

### Navigating the Control Panel

The control panel is very easy to navigate. On the left side is a list
of headings with functions underneath them. The headings are in
alphabetical order, but the functions are in a logical order.

**User Name**: The first section is always the logged in user's personal
space. Here, you can change your account information and manage your own
personal pages.

![image](../../images/03-liferay-control-panel.png)*Illustration 3: Liferay's Control Panel*

**Content:** The Content section contains links to all of Liferay's
content management functions. You can maintain web content, documents,
images, bookmarks, a calendar, administer a message board, configure a
wiki, and more. The title of this section appears as the name of the
site whose content you are currently managing, and
you can switch to another one at any time.

**Portal:** The Portal section allows portal administrators to set up
and maintain the portal. This is where you can add and edit users,
organizations, communities, roles, and configure the settings of the
portal.

**Server:** The Server section contains administrative functions for
configuring portal instances, plugins, and more.

All of the functions that you will need to maintain the portal or its
content can be found in the control panel. Additionally, developers can
write portlets which can also be added to the control panel. For further
information about this, you can take Liferay's Portal Developer course
or see the official guide to Liferay development, *Liferay in Action*.

## Portal Architecture

Before we dive into the user interface for adding and maintaining
various portal resources, it is best to go over the concepts Liferay
uses to organize a portal.

Portals are accessed by Users.

Users can be collected into User Groups.

Users can belong to Organizations.

Organizations can be grouped into hierarchies, such as Home Office →
Regional Office → Satellite Office.

Sites can be created independently or can be attached to Users and Organizations.

Within Sites, users can belong to Teams, which are groupings of users for specific functions within the Site.

The simplest way to think about this is that you have users and various
ways those users can be grouped together. Some of these groupings follow
an administratively organized hierarchy, and other groupings may be done
by the users themselves (such as different users from multiple
organizations starting a site called “Dog Lovers” that has a common
interest in dogs). And other groupings may be done administratively via
Roles for other functions that may cut across the portal (such as a
Message Board Administrators role made up of users from multiple
communities and organizations, allowing those users to administer any
message board in the portal).

This way of organizing portal concepts may be illustrated as shown on
the next page.

In the illustration below, each arrow may be read using the words “can
be a member of.” So this means that Organizations can be members of
Sites, Sites can be members of Roles, Users can be members
of anything, and so on. Though this seems very complex, it provides a
powerful mechanism for portal administrators to configure portal
resources and security in a consistent and robust manner. It is
important to note that the diagram illustrates only users and their
collections. Permissions do not flow through all of these collections:
permissions can be assigned to roles only.

![image](../../images/03-liferay-permissions-model.png)\
*Illustration 4: Liferay permissions model. Don't worry, it's not as
complicated as it seems.*

Teams belong to individual Sites. Roles that appear inside Sites are scoped just for Sites. This means that though each Site in the portal has this Role with its configured permissions, membership in this role is different for each Site.

### Users

Users represent physical users of the system. These are the user
accounts that people use to log into the system. By default, users get
their own private communities with public and private pages that they
can manage themselves, and this can be turned off or locked down by
administrators. But this personal space is important: it's what enables
users to have their own public blog or their own private calendar, a
place to store their documents, and more.

Users can be collected in multiple ways. They can be members of
organization hierarchies, such as Liferay, Inc. → Security → Internet
Security. They can be collected into arbitrary user groups, such as
Bloggers, which could be used to set apart users who get a Blog page in
their personal space from users who do not. They can be members of
communities which draw together common interests. And they can have
roles which define their permissions in the system, and these roles can
be scoped by Portal, Organization, or Site.

### User Groups

User Groups are simple, arbitrary collections of users, created by
administrators. They can be members of Sites or Roles. Permissions
cannot be assigned to User Groups. Though User Groups do not have Sites, they do have page templates which can be used to customize users' personal sets of pages. This will be fully described below.

### Roles

There are three kinds of roles:

-   Portal Roles

-   Organization Roles

-   Site Roles

These are called role *scopes*. Roles are used to define permissions
across their scopes: across the portal, across an organization, or
across a site. For example, consider a role which grants access to
create a Message Board category. A portal role would grant that access
across the portal, wherever there was a Message Board portlet. An organization role would grant that access within any sites attached to the organization. A site role would grant that access only within one site.

Roles do not have pages associated to them because they are used strictly for portal security.

Users, user Groups, and sites can be members of a Role.

### Organizations

Organizations are hierarchical collections of Users. Organizations do not have pages directly associated with them. Instead, sites can be attached to organizations. There is also a special type of organization called a *location*, which can define whereusers are specifically located.

Organizations are handy for defining where a user belongs in a
particular hierarchy. For example, if you are implementing Liferay for a
large organization, it may help to define user Joe Smith via his
position in the organization chart. If Joe Smith is a Sales Engineer
located in the New Jersey office, working in the North East division of
the Sales department, he might be a member of the following
organizations:

-   Sales

-   North East Division

-   New Jersey Location

Now say that you have placed an Asset Publisher portlet as a static
portlet on every user's home page (via a User Group page template) so
that you can inform employees of various announcements via the content
management system. If you tagged your content appropriately, you could
ensure that Joe Smith gets any announcements that are meant for Sales,
the North East Division, or the New Jersey location.

Sites can be attached to organizations.

### Sites

Sites are collections of users who have a common interest.
Liferay's default pages are part of a site named for the portal,
because everyone—whether they are anonymous or members of the portal—has
a common interest in the default, public pages of your site. There are
three types of sites:

-   Open

-   Restricted

-   Private

An open site (the default) allows portal users to join and leave
the site whenever they want to, using the control panel or a
My Sites portlet added to a page which they can access. A restricted site requires that users be added to the site by a site
administrator. Users may use the control panel or the My Sites
portlet to request membership. A private site is just like a
restricted site, with the added concept that it does not show up at
all in the My Sites portlet or the control panel. Users will have to
be added to a private site by a site administrator.

### Teams

Teams are unique within a context of a site. Teams are essentially sets of users that can be created within a site. This makes teams different from site roles because teams appear only in the site in which they are created. This is very useful if you need to create a team of users for a specific purpose within a single organization or site and not for each site in the portal.

Teams can also be essential for some use cases, because they can be
created by site administrators. Site administrators cannot create roles, so the ability to have teams empowers them to manage permissions at a level they weren't capable of previously.

## Using the Control Panel

The Portal section of the Control Panel is used for most administrative
tasks. You will find there an interface for the creation and maintenance
of

-   Users and Organizations

-   Sites

-   Site Templates

-   Page Templates

-   Roles

Additionally, it allows you to configure many server settings,
including:

-   Password Policies

-   Portal Settings, including mail host names, email notifications, and authentication options like including single sign-on and LDAP integration

-   Custom Fields

-   Monitoring

-   Plugins Configuration

You will use the Portal section of the control panel to create your
portal structure, implement security, and administer your users. Note
that only users with the administrator role—a portal scoped role—have
permission to view this section of the Control Panel. You can, of
course, grant permissions to one or more sections to roles that you
create yourself.

### Adding Users

![image](../../images/portal-admin-ch3_html_m369939f8.png)![image](../../images/portal-admin-ch3_html_m369939f8.png)*Illustration
5: The Add User screen. *

Let's begin by adding a user account for yourself. We will then
configure this account so that it has the same administrative access as
the default administrator account. Go up to the Dockbar, mouse over
*Manage* and click the Control Panel link, if you aren't there already.
Then under the *Portal* category, click *Users*. Click the *Add* button.
You will then be presented with the Add User form. Fill out the form
using your name and email address. When you are finished, click *Save*.

The page will then reappear with a message saying that the save was
successful, and there will now be an expanded form which allows you to
fill out a lot more information about the user. You don't have to fill
anything else out right now, but one thing is important to note: when
the user ID was created, a password was automatically generated and, if
Liferay has been correctly installed (see Chapter 2), an email message
with the password in it was sent to the user. This of course requires
that Liferay can properly communicate with your SMTP mail server in your
organization.

![image](../../images/portal-admin-ch3_html_m463070fd.png)*Illustration 6: Liferay's
User Account editor. *

If you haven't yet set up your mail server, you'll need to use this
screen to change the default password for the user ID to something you
can remember. You can do this by clicking on the *Password* link in the
box on the right, entering the new password in the two fields, and
clicking Save.
Next, you will want to give your user account the same administrative
rights as the default administrator's account. This will allow you to
perform administrative tasks with your own ID instead of having to use
the default ID. And this allows you to make your portal more secure by
deleting or disabling the default ID.

Click the *Roles* link. You will then be taken to a screen which shows
the roles to which your ID is currently assigned. By default, you should
have one role: Power User. By default, all users are also assigned the
Power User role. You can give this role certain permissions if you wish
or disable it altogether (we will see how to do this later). You can
also define the default roles a new user receives; we will go over this
later also.

To make yourself an Administrator, click the *Select* link. A window
will pop up with a list of all the roles in the system. Select the
Administrator role from the list and the window will disappear and you
will see that the role has been added to the list of roles to which you
are assigned. Next, click the *Save* button, which is at the bottom of
the blue bar of links on the right. You are now an administrator of the
portal. Log out of the portal and then log back in with your own user
ID.

### User Management

If you click the *Users* link on the left of the Control Panel, you will
see that there are now two users in the list of users. If you wanted to
change something about a particular user, you can click the *Actions*
button next to that user.

**Edit User:** This takes you back to the Edit User page, where you can
modify anything about the user.

**Permissions** **: **This allows you to define which Roles have
permissions to edit the user.

**Manage Pages:** If the user has pages, this allows you to edit them.

**Impersonate User:** Opens another browser window which allows you to
browse the site as though you were the user.

**Deactivate:** Clicking this will deactivate the user's account.

Note that most users will not be able to perform most of the above (in
fact, they won't even have access to this section of the Control Panel).
Because you have administrative access, you can perform all of the above
functions.

### Organizations

Organizations in Liferay are meant to model organizations in real life.
They can be used to represent different companies, non-profit
organizations, churches, schools, clubs, and so on. They have been used
to represent a sports league, with various sports (soccer, baseball,
basketball, etc.) and their teams as sub-organizations. If you have a
collection of users that all belong to the same grouping, you may be
able to model that as an organization.

Your portal may have only one organization or several, depending on what
kind of site you are building. For example, a corporate site may model
its own organization hierarchy in Liferay, while a social networking
site may have users from many separate organizations who access the
site. Organizations can be organized in a hierarchy to unlimited levels,
and Users can be members of one or many organizations—inside of a
hierarchy or across different hierarchies.

Additionally, Organizations can be associated with Roles. One
application of this in a corporate setting could be an IT Security
group. You may have an organization within your IT organization that
handles security for all of the applications company-wide. If you had
users as members of this organization, you could grant the Administrator
role you just granted to your own ID to the whole Organization, thereby
giving the members of the IT Security organization administrative access
to the portal. If a user in this organization later was hired by the
Human Resources department, the simple administrative act of moving the
user from the IT Security organization to the HR organization would
remove this privilege from the user, since the user would no longer be
in an organization that has the Administrator role. By adding the user
to the HR organization, any roles the HR organization has (such as
access to a benefits system in the portal) would be transferred to the
user. In this manner, you can design your portal to correspond with your
existing organization chart, and have users' permissions reflect their
positions in the chart.

Of course, this is only one way to design it. If you have more complex
requirements, you can combine Organizations with Teams and scoped Roles
to assemble the sets of permissions you wish to grant to particular
users.

Organizations are one of two types of Liferay resources (the other being
Communities) that can have its own pages. This allows members of the
organizations (if they are granted the Manage Pages permission) to
maintain their own pages. They can have a set of public pages which
include information and applications appropriate for guests or logged in
users who are not members of the Organization to make use of (such as a
help desk ticket entry system for an IT page), and they can have a set
of private pages with applications for the organization's own use (such
as the back-end portlets of the same ticketing system).

To add an organization, click the *Organizations* link on the left side
of the Control Panel, and then click the Add button.

![image](../../images/portal-admin-ch3_html_m48ff4527.png)![image](../../images/portal-admin-ch3_html_m48ff4527.png)*Illustration
7: Adding an organization. *

 **Name:** The name of the organization.
**Type:** Use this to choose whether this is a regular organization or a
location.

**Parent Organization:** Click the *Select* link to bring up a window
which allows you to select the organization in the system that is the
direct parent of the organization you are creating. Click the *Remove*
button to remove the currently configured parent.

![image](../../images/portal-admin-ch3_html_5c790363.png) **Tip:** Note that you are
already a member of the organization you created, because you created
it. By creating an organization, you become both a member and have the
Organization Owner role, which gives you full rights to the
organization.

Fill out the information for your organization and click *Save*.
As before with users, the form reappears and you can enter more
information about the organization. Organizations can have multiple
email addresses, postal addresses, web sites, and phone numbers
associated with them. The *Services* link can be used to indicate the
operating hours of the organization, if any.

For now, click the *Back* button. This will take you back to the list of
organizations.

Click the *Actions* button next to the new organization you have
created. You will then see the many actions you can take to manipulate
this organization.

**Edit:** Lets you edit the organization.

**Manage Pages:** Lets you create and manage public and private pages
for the Organization.

**Manage Teams:** Lets you create teams within this organization, to
which you can assign users and permissions.

**Assign User Roles:** Lets you assign Organization-scoped roles to
users. By default, Organizations are created with three roles:
Organization Administrator, Organization Member, and Organization Owner.
You can assign one or more of these roles to users in the organization.
All members of the Organization get the Organization Member role.

**Assign Members:** Takes you to a screen where you can search and
select users in the portal to be assigned to this organization as
members.

**Add User:** Adds a new user in the portal who will be a member of this
organization.

**View Users: **Shows a list of users who are members of this
organization.

**Add Regular Organization: **Lets you add a child organization to this
organization. This is how you create hierarchies of organizations with
parent-child relationships.

**Add Location: **Lets you add a child Location, which is a special type
of organization that cannot have any children added to it.

**View Sub** **organizations: **Shows a list of all the organizations
that are children of this organization.

**Delete: **Deletes this organization from the portal. You will have to
ensure that the organization has no users in it first.

### Communities

Communities are very much like Organizations except that they are not
hierarchical. They are designed instead to be islands to themselves
which anyone from any organization (or from no organization at all) can
join. You can use Communities, therefore, in any situation where you
need to cut across the organizational structure of your portal, or where
you have a site that would apply to almost anybody.

For example, a corporate Intranet running Liferay may have sites for all
the organizations in the company: Sales, Marketing, product groups,
Information Technology, Human Resources, and so on. But what about the
corporate health and fitness center? That's something that everybody in
the company—regardless of organization—potentially has an interest in,
and may want to join. That's a good candidate for a Community. Using the
same scenario, the home page for the Intranet is probably best placed in
a community that any member of the portal can access.

For other kinds of web sites, you may want to use communities to bring
people together who have a common interest. If you were building a photo
sharing web site out of Liferay, you may have communities based on the
types of photos people want to share. So those who enjoy taking pictures
of landscapes can join a Landscapes community, and those who enjoy
taking pictures of sunsets can join a Sunsets community. And if they
lose interest, they can leave those communities too.

The default home page in Liferay is in a community which by default is
called *liferay.com *(you can rename it; see the tip below), and this is
where you would put your public web site. As you can see, there are
several scenarios in which you would want to use something like a
community instead of an organization, and this is why they have distinct
roles within Liferay Portal.

![image](../../images/portal-admin-ch3_html_5c790363.png) **Tip:** In previous
versions of Liferay, the default community where the public pages were
located was called *Guest* . Starting with Liferay 6, the default public
community is named dynamically based on whatever the name of the portal
is. Any time you change the name of your site, the name of this
community will change. You can set the name in *Portal **→* *Settings* .

Communities can be created and managed in two ways. The first is through
the Control Panel, like every other user/page collection in Liferay. The
second is through the My Communities portlet, which can be added to any
page in Liferay. Why are there two ways? Because the My Communities
portlet also doubles as a way to navigate from community to community,
and allows users to browse the list of communities and select whether or
not they want to join one (if it is open or restricted). This enables
you as a portal administrator to provide users with this functionality
without giving them access to the Control Panel.

To add a community, click the *Communities* link on the left side of the
Control Panel in the Portal section, and then click the *Add* button.

**Name:** Enter the name of the community you wish to create.

**Description:** Enter some descriptive text about the community.

**Type:** There are three kinds of communities: Open, Restricted, and
Private. An open community appears in the My Communities portlet and
users can join and leave the community whenever they want. A restricted
community is the same except users can only request membership. A
community administrator must then explicitly grant or deny users'
requests to join. A private community does not appear in the My
Communities portlet and users must be added to it manually by a
community administrator.

**Active:** Communities can be active or inactive. If a community is
inactive, no data can be added to it.

**Tags:** You can use Liferay's tagging mechanism on the community. This
is helpful if the community has a specific, topical purpose within the
portal.

Once you have created a community, it will appear in the list of
communities in the Control Panel. The operations you can perform on it
are very similar to the operations you can perform on organizations.

**Edit:** Lets you edit the community.

**Manage Pages: **Lets you create and manage public and private pages
for the community.

**Manage Teams: **Lets you create and manage teams for the community.

**Assign User Roles:** Lets you assign community-scoped roles to users.
By default, communities are created with three roles: Community
Administrator, Community Member, and Community Owner. You can assign one
or more of these roles to users in the community. All members of the
community get the Community Member role.

**Assign Members: **Takes you to a screen where you can search and
select users in the portal to be assigned to this community as members.

**Join/Leave: **If you are not a member of the community, you will have
a Join or Request Membership option. If you are a member of the
community you will see an option to leave the community.

**Delete:** Users with administrative access to the portal or who are
owners of the community can delete it.

#### Site Templates

While we're on the subject of communities, it is important to mention
Site Templates, which is several links down in the Portal category in
the Control Panel. These allow you to create web sites within
communities by selecting from pre-defined templates that you can make
ahead of time. What this means is that you can create a template
community that has a pre-defined set of pages and portlets, and then use
that template to very quickly create multiple communities that are
pre-populated with those pages and portlets.

![image](../../images/portal-admin-ch3_html_mee5e058.png)\
*Illustration 8: Site Templates*

You can create templates for open, restricted, and private communities.
Additionally, you can create a default template that applies to all
kinds of communities. For our example, we will work with a template
designed for student communities.
Go to the Control Panel and click *Site Templates*. Click the *Add*
button and create a site template called *Students*. Make the template
Active. Now you can select the *Open Site Template* link and begin
adding content, pages, portlets and configuring the layouts. You'll
learn more about how to do this in the next chapter. Once you're
finished, return to the Site Templates portlet and select *Save.*

To create a community based on the new template, go to the Control Panel
and click *Communities*. Click the *Add* button and create a community
called *Freshmen*. In the Public Pages drop down menu, select *Student
Template* and then click *Save. *The new community will have all the
pages, content, and portlets you created in the template. This feature
streamlines the community creation process for administrators, making it
very easy to quickly create communities.

### User Groups

User Groups are arbitrary groupings of users. These groups are created
by portal administrators to group users together who don't have an
obvious organizational or community-based attribute or aspect which
brings them together. Groups cannot have permissions like roles, but
User Groups can be added to Roles. Why would you use User Groups, then?
They come into play when you have complex security requirements and for
page templates, which we will discuss below.

Creating a User Group is easy. Click the *User Groups* link and then
click the *Add* button. There are only two fields to fill out: Name (the
name of the User Group) and Description (an optional description of what
the group is for). Click *Save* and you will then be back to the list of
groups.

As with the other resources in the portal, you can click the *Actions*
button to perform various operations on User Groups.

**Edit:** Allows you to modify the name or description of the User
Group.

**Permissions:** This allows you to define which Users, User Groups, or
Roles have permissions to edit the User Group.

**Manage Pages:** Though User Groups don't have pages of their own, you
can create page templates for a group. When a User Group has page
templates, any users added to the group will have the group's pages
copied to their personal pages. This allows you to do things like create
a Bloggers user group with a page template that has the Blogs and Recent
Bloggers portlets on it. The first time users who are added to this
group log in to the portal, this page will get copied to their personal
pages. They will then automatically have a blog page that they can use.

**Assign Members:** Takes you to a screen where you can search for and
select users in the portal to be assigned to this User Group.

**View Users** **:** Lets you view the users who are in the User Group.

**Delete:** Deletes the User Group.

#### User Groups and Page Templates

Liferay allows users to have a personal set of public and private pages
that each user can customize at will. The default configuration of those
pages can be determined by the portal administrator through the
`portal-ext.properties`{.western} file and optionally by providing the
configuration in a LAR file. Though this has been a long-time feature of
Liferay, it was not very flexible or easy to use.

Liferay version 5.1 introduced the concept of page templates which are
tied to User Groups. This enables administrators to provide the same
configuration for the personal pages of all (or just a subset of) users,
using Liferay's GUI instead of the properties file. In some cases you
may want to provide a different configuration for each user depending on
his or her profile. For example, in a portal for University students,
staff and undergraduates would get different default pages and portlets
in their personal space. You can also set it up so that different groups
are combined together to create the desired default configuration. When
a user is assigned to a user group, the configured page templates are
copied directly to the user's personal pages.

##### User Group Page Templates: Defining page templates for a user
group

A User Group's page templates can be administered using the Control
Panel. The User Groups link lists all of the existing user groups and
allows you to perform several actions on each of them.

![image](../../images/portal-admin-ch3_html_ada3413.png)*Illustration 9: Manage Pages
action on a User Group*

By selecting the Manage Pages action the administrator can access the
common Liferay UI for creating pages and organizing them in a hierarchy.

![image](../../images/portal-admin-ch3_html_750e61ff.png)*Illustration 10: Adding a
Page Template*

Note that it is possible to create both public and private pages. Each
set will be used as templates to be copied to the user's personal public
or private page sets respectively when the user becomes a member of the
user group.
In the screen shot above, the administrator has created a new private
page called *You are a student* within the Students user group. Since
the page created is a portlet page, the administrator can now click the
*View Pages* button to open the page and add as many portlets as desired
to that page and configure them as needed. Let's assume for this example
that the Loan Calculator and Calendar portlets are selected.

##### Applying the page templates by assigning members to the user group

The next step is to assign an existing user to that group to verify that
the page template is copied as a user's private page. To do this, click
*Actions* → *Assign Members* in the list of available user groups.

![image](../../images/portal-admin-ch3_html_7a6ab4df.png)*Illustration 11: Assigning
Members to a User Group*

By clicking the *Available* tab in the next screen, a list of all
available users is shown. From that list, one or more users can be
selected to make them members of the user group. When the *Update
Associations* button is clicked, the users become members of the group
and copies of any public or private page templates which are configured
for the user group are copied to their page sets.

In the previous example, a user that already had an existing page called
*Welcome* will now have a new page called *You Are A Student* the next
time she accesses her personal space. That page will contain two
portlets: Loan Calculator and Calendar as configured by the User Group
administrator.

##### Additional details

Because the pages are copied to a user's set of pages, those pages are
now owned by the user and they can be changed at any time if the portal
is set up to allow users to edit their personal pages. When a user is
removed from a user group the associated pages won't be removed: they
have become that user's pages. The system is smart enough, however, to
detect when a user is added again to a group of which he or she was
already a part, and the pages are not added again.

![image](../../images/portal-admin-ch3_html_345cfef7.png)*Illustration 12: Template
copied to a user's page set*

If an administrator modifies page templates for a User group after users
have already been added to the group, those changes will be used when
new users are assigned to the user group. Since the pages are templates,
however, the changes won't be applied to users that were already members
of the user group.
##### Composing A Page Out of Several User Groups

Users can belong to many user groups. If you have templates defined for
a number of groups, this may result in having many page templates copied
to users' pages. To prevent this, you can combine pages from different
user groups into a single page.

Let's expand our previous example by dividing the Students into First
Year Students, Second Year Students, Third Year Students, International
Students, and Prospective Students. For each of these types of students
we want to have a page with the Loan Calculator and Calendar, but
depending on which type, we also want other different portlets to be on
that page too.

This can be achieved by using a naming convention for the pages. If two
or more pages of different user groups have the same name, they will be
combined into a single page when they are copied to a user's personal
pages set.

In the example above, a User was added to a Students group which had a
page called You are a Student. If the administrator creates a page
template with the same name (You are a Student) in the First Year
Students group and puts in it an RSS portlet pointing to information
interesting for them, that page would be combined with the You are a
Student page that's in the Students group, and the resulting page would
contain the portlets configured for both User Groups:

![image](../../images/portal-admin-ch3_html_21b9e024.png)*Illustration 13: Combined
portlet pages. *

##### Page Combination Rules

The following rules are used when composing a page by combining pages
from different user groups:

-   If a user becomes a member of a User Group that has a page template
    with the same name in the same set (public or private) as a page
    that the user already has, those pages will be combined.

-   If any of the pages has the name translated to several languages,
    only the default language is considered in the comparison.

-   The portlets on the new page will be copied to the bottom of the
    equivalent columns of the existing page.

-   If the existing and the new pages have different layout templates,
    the existing one is preserved.

-   If the new layout template has portlets in columns that do not exist
    in the existing page, those portlets will be automatically copied to
    the first column of the existing layout template.

As you can see, it is possible to have a very flexible configuration for
the default pages of portal users. Furthermore, that configuration can
be changed at any time using the UI administrators are used to and then
assigning users to new user groups.

While these examples are somewhat simple, the system allows for as many
user groups as desired. By using the convention of matching the page
names, it is possible to build any default page composition that you
want for your users.

### Roles

Roles are groupings of users that share a particular function within the
portal, according to a particular scope. Roles can be granted
permissions to various functions within portlet applications. Think of a
role as a description of a function, such as Message Board
Administrators. A role with that name is likely to have permissions to
functions of the Message Board portlet delegated to it. Users who are
placed in this role then inherit those permissions.

Roles are scoped by Portal, Organization, or Community. The Control
Panel makes it easy for you to assign users to Roles and to assign
permissions to Roles. You only have to go to one place: the Roles link.
From there, you can add roles scoped by Portal, Organization, or
Community from one interface.

To create a Role, click the *Roles* link, and then click the *Add*
button. Type a name for your role and an optional description. The drop
down box at the bottom of the form lets you choose whether this is a
Regular, Community, or Organization role. When you have finished, click
*Save*.

You will be back at the list of roles. To see what functions you can
perform on your new role, click the *Actions* button.

**Edit:** Click this action to edit the role. You can change its name or
description.

**Permissions** **:** This allows you to define which Users, User
Groups, or Roles have permissions to edit the Role.

**Define Permissions** **:** Click this to define what permissions this
role has. This is outlined in the next section.

**Assign Members:** Takes you to a screen where you can search and
select users in the portal to be assigned to this role. These users will
inherit any permissions given to the role.

**View Users** **:** Lets you view the users who are in the Role.

**Delete:** Deletes the Role.

#### Defining Permissions on a Role

Roles exist as a bucket for granting permissions to the users who are
members of them. So one of the main tasks you will be doing with a role
is granting it the permissions that you want members of the role to
have.

![image](../../images/portal-admin-ch3_html_38b1be0.png)*Illustration 14: Defining
Permissions on a Role*

When you click the *Define Permissions* action on a Portal scoped Role,
you are given a choice of four kinds of permissions that can be defined
for this role: Portal, Content, Applications, and Control Panel. For
other Roles, you need to use the *Permissions* link in individual
portlets to assign permissions for the community or organization in
which that portlet is placed.
Portal permissions cover portal-wide activities that are in several
categories, such as Community, Location, Organization, Password Policy,
etc. This allows you to create a Role that, for example, can create new
Communities in the portal. This would allow you to grant users that
particular permission without making them overall portal administrators.

Content permissions cover the content that the installed portlets
create. If you pick one of the portlets from this list, you'll get
options for defining permissions on its content. For example, if you
pick Message Boards, you'll see permissions for creating categories or
threads, or deleting and moving topics.

Application permissions affect the application as a whole. So if we
stick with our Message Boards example, an application permission might
define who can add the Message Boards to a page.

Control Panel permissions affect how the portlet appears to the user in
the Control Panel. Some Control Panel portlets have a Configuration
button, so you can define who gets to see that, as well as who gets to
see an application in the Control Panel.

![image](../../images/portal-admin-ch3_html_b072c1d.png)*Illustration 15: Message
board content permissions*

Each possible action to which permissions can be granted is listed. To
grant a permission, choose the permission. If you want to limit the
scope of the permission to a community or organization, click the *Limit
Scope* link, and then choose the community or organization that is in
scope.
Once you have chosen the permissions granted to this role, click *Save*.
For a Message Boards Admin role, you would likely grant Content
permissions to every action listed. After you click *Save*, you will see
a list of all permissions that are currently granted to this role. From
here, you can add more permissions or go back by clicking a link in the
breadcrumb list or the *Return to Full Page* link.

Roles are very powerful, and allow portal administrators to define
various permissions in whatever combinations they like. This gives you
as much flexibility as possible to build the site you have designed.

#### Special Note about the Power Users Role

By default, many portlets within Liferay are configured so that Power
Users have access to them, but regular users do not. If you decide to
remove the Power Users role from the default user associations, you will
need to modify the permissions on certain portlets. To do this, see the
section on Plugins Configuration below.

### Teams

Teams don't appear as a link in the Control Panel because they are user
groupings that are *inside* communities and organizations. They exist
because there will be times when you need to create a bucket for
permissions within a community or organization that only affect that
community or organization. That is when you create a team. Teams are a
part of the community or organization in which they are created. If you
create a team for one community, it is not available for another
community. This is beneficial when you want a particular set of people
and permissions for a specific one time function.

Creating a team for a community is simple. Go to *Control Panel →
Communities* and select the *Actions→ Manage Teams.*

![image](../../images/portal-admin-ch3_html_6e3991c1.png)\
*Illustration 16: Assigning permissions to a team*

Select the *Add Team* button and then type in a name and add a
description. Once you're finished, click *Save*. Your new team will
appear in the list. To add members, simply click on *Actions* → *Assign
Members.* That's it! Your team is ready.
Creating a team for the organization is just as simple. To create a team
for an organization go to *Control Panel → Organizations, *choose the
organization from the list, select *Actions → Manage Teams, *and perform
the same steps you would for a community.

Permission management for teams is handled at the individual portlet
level, using the *Permissions* button on the portlet itself. This
enables users who wouldn't have access to all of the necessary options
in the control panel to manage permissions through teams.

To give a team access to a particular portlet function, access the
configuration menu for that portlet where it resides on a page, click
the *Permissions* tab, check the boxes corresponding to permissions you
want to assign to these teams, and then click *Save*.

