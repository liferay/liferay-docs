# Configuration

Once Liferay is successfully installed, you can begin configuring it to
fit it to your environment and your particular portal project. You can
perform many of these configuration tasks through Liferay's
portlet-driven user interface.

You will want to customize your portal by configuring various settings
for it, such as email notifications, integration with services such as
LDAP, creating users, user groups, organizations, communities, and
roles, and readying your portal to have its content and applications
loaded by your developers. This chapter covers these activities:

-   *Liferay's User Interface:* How to navigate around Liferay and make
    use of the Control Panel.

-   *Liferay Administration:* How to administer a Liferay portal.

-   *Global Portal Settings:* Password policies, Settings, Monitoring,
    and more.

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
portlets that are to appear on any particular page and display them
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

![image](../../images/portal-admin-ch3_html_m5711b8a1.png)\
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

![image](../../images/portal-admin-ch3_html_531a7af.png)\
*Illustration 2: Add Menu from the Dockbar*

\
\

\
\

\
\

\
\

\
\

\
\

\
\

\
\

\
\

\
\

\
\

The next option you'll see is the *Manage* menu. From this menu, you can
access various settings for the current page and any of its subpages.
The items available are *Page, Page Layout Sitemap, and Settings.*
Clicking on *Page Layout *brings up a dialog box which enables you to
choose the layout template to use for the current page. The other
settings are the same as their counterparts in the *Control* *Panel*,
and are covered in detail later in this chapter. The last item in the
menu is *Control Panel; *clicking on it brings you to the Control Panel.

The next thing you'll see is a check box labeled *Toggle Edit Controls.
*This lets you turn on and off the edit controls in the top of the
portlet windows. This is helpful for administrators who want to look at
a page they're working on and see it the way a regular user would.

If you roll your mouse over *Go to*, the Dockbar** **will expand,
showing all of the places in the portal to which you have access.
Initially, the place you are on is highlighted. You will see that you
are in the *liferay.com*** **community, on the public pages. Liferay
allows for various configurations of pages for end users: you can
configure it so that some or all users have their own pages, public and
private (or both), upon which they can place the portlets they need to
use. The administrator account by default has its own pages. Because you
are logged in with an account that has Administrator privileges, you can
see everything in the portal.

One of the most important tools Liferay offers for managing your portal
is the aforementioned *Control Panel*. The Control Panel is composed of
administrative portlets that you can use to mange various aspects of the
portal.

### Navigating the Control Panel

The control panel is very easy to navigate. On the left side is a list
of headings with functions underneath them. The headings are in
alphabetical order, but the functions are in a logical order.

**User Name**: The first section is always the logged in user's personal
space. Here, you can change your account information and manage your own
personal pages.

![image](../../images/portal-admin-ch3_html_656838bb.png)*Illustration 3: Liferay's
Control Panel*

**Content:** The Content section contains links to all of Liferay's
content management functions. You can maintain web content, documents,
images, bookmarks, a calendar, administer a message board, configure a
wiki, and more. The title of this section appears as the name of the
community or organization whose content you are currently managing, and
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
or see the official guide to Liferay development, *Liferay in Action* .

## Portal Architecture

Before we dive into the user interface for adding and maintaining
various portal resources, it is best to go over the concepts Liferay
uses to organize a portal.

Portals are accessed by Users.

Users can be collected into User Groups.

Users can belong to Organizations.

Organizations can be grouped into hierarchies, such as Home Office →
Regional Office → Satellite Office.

Users, Groups, and Organizations can belong to Communities that have a
common interest.

Within Organizations and Communities, users can belong to Teams, which
are groupings of users for specific functions within a community or
organization.

The simplest way to think about this is that you have users and various
ways those users can be grouped together. Some of these groupings follow
an administratively organized hierarchy, and other groupings may be done
by the users themselves (such as different users from multiple
organizations starting a community called “Dog Lovers” that has a common
interest in dogs). And other groupings may be done administratively via
Roles for other functions that may cut across the portal (such as a
Message Board Administrators role made up of users from multiple
communities and organizations, allowing those users to administer any
message board in the portal).

This way of organizing portal concepts may be illustrated as shown on
the next page.

In the illustration below, each arrow may be read using the words “can
be a member of.” So this means that Organizations can be members of
Communities, Communities can be members of Roles, Users can be members
of anything, and so on. Though this seems very complex, it provides a
powerful mechanism for portal administrators to configure portal
resources and security in a consistent and robust manner. It is
important to note that the diagram illustrates only users and their
collections. Permissions do not flow through all of these collections:
permissions can be assigned to roles only.

![image](../../images/portal-admin-ch3_html_dc9cece.png)\
![image](../../images/portal-admin-ch3_html_dc9cece.png)\
*Illustration 4: Liferay permissions model. Don't worry, it's not as
complicated as it seems.*

Teams are inside individual organizations and communities, and are only
available as they are created within those organizations and
communities. Roles that appear inside organizations and communities are
roles that are scoped just for organizations and communities. This means
that though each organization and community in the portal has this role
with its configured permissions, membership in this role is different
for each organization and community.
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
be scoped by Portal, Organization, or Community.

### User Groups

User Groups are simple, arbitrary collections of users, created by
administrators. They can be members of communities or roles. Permissions
cannot be assigned to User Groups. Though User Groups do not have pages
like some of the other collections of users (such as Communities or
Organizations), they do have page templates which can be used to
customize users' personal sets of pages. This will be fully described
below.

### Roles

There are three kinds of roles:

-   Portal Roles

-   Organization Roles

-   Community Roles

These are called role *scopes*. Roles are used to define permissions
across their scopes: across the portal, across an organization, or
across a community. For example, consider a role which grants access to
create a Message Board category. A Portal role would grant that access
across the portal, wherever there was a Message Board portlet. A
Community role would grant that access only within a single community.
An Organization role would grant that access only within an
Organization.

Because Roles are used strictly for portal security, they also do not
have pages, like Communities and Organizations.

Users, User Groups, Communities, or Organizations can be members of a
role.

### Organizations

Organizations are hierarchical collections of Users. They are one of the
two types of portal resources that can have pages. There is also a
special type of Organization called a *location*, which can define where
users are specifically located.

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

Organizations can be members of Communities.

### Communities

Communities are collections of Users who have a common interest.
Liferay's default pages are part of a community named for the portal,
because everyone—whether they are anonymous or members of the portal—has
a common interest in the default, public pages of your site. There are
three types of Communities:

-   Open

-   Restricted

-   Hidden

An Open Community (the default) allows portal users to join and leave
the Community whenever they want to, using the Control Panel or a
Communities portlet added to a page which they can access. A Restricted
Community requires that users be added to the Community by a community
administrator. Users may use the Control Panel or the Communities
portlet to request membership. A Hidden community is just like a
Restricted community, with the added concept that it does not show up at
all in the Communities portlet or the Control Panel. Users will have to
be added to a hidden community by a community administrator.

### Teams

Teams are unique within a context of a Community or Organization. Teams
are essentially sets of users that can be created within a community.
This makes teams different from the Community and Organization Roles
because teams appear only in the specific community or organization in
which they are created. This is very useful if you need to create a team
of users for a specific purpose within a community or organization and
not for each community or organization in the portal.

Teams can also be essential for some use cases, because they can be
created by Community or Organization Administrators. Community and
Organization Administrators cannot create roles, so the ability to have
teams empowers them to manage permissions at a level they weren't
capable of previously.

## Using the Control Panel

The Portal section of the Control Panel is used for most administrative
tasks. You will find there an interface for the creation and maintenance
of

-   Users

-   Organizations

-   Communities

-   User Groups

-   Roles

Additionally, it allows you to configure many server settings,
including:

-   Password Policies

-   Authentication options, including Single Sign-On and LDAP
    integration

-   Default User Associations

-   Reserved Screen Names

-   Mail Host Names

-   Email Notifications

You will use the Portal section of the Control Panel to create your
portal structure, implement security, and administer your users. Note
that only users with the Administrator role—a portal scoped role—have
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

\
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

\
\

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

## Global Server Settings

Now that you have navigated in the Control Panel, you should be pretty
familiar with how it works, and that all the options appear in the left
navigation, their interfaces appear in the middle, and any sub-options
appear on the right. We have focused so far on the maintenance of users
and portal security. The remaining links in the *Portal* category focus
on various portal settings which cover how the portal operates and
integrates with other systems you may have.

### Password Policies

Password policies can help to enhance the security of your portal. Using
password policies, you can set password rules such as password strength,
frequency of password expiration, and more. Additionally, you can apply
different rule sets to different sets of portal users.

If you are viewing a page other than the Control Panel, go up to the
Dockbar and select *Control Panel*. Next, click on the *Password
Policies* link on the left side of the screen in the *Portal *category.
You will see that there is already a default password policy in the
system. You can edit this in the same manner as you edit other resources
in the portal: click *Actions* and then click *Edit* .

You will then see the Password Policy settings form:

**Changeable:** Selects whether a user can change his or her password.

**Change Required:** Selects whether a user must change his or her
password upon first log in.

**Minimum Age:** You can choose how long a password must remain in
effect before it can be changed.

**Reset ticket max age:** You can choose how long a password reset link
remains valid.

**Syntax Checking:** Allows you to choose whether dictionary words can
be in passwords as well as the minimum password length.

**Password History:** Keeps a history (with a defined length) of
passwords and won't allow users to change their passwords to one that
was previously used. You can enable it or disable it using the check
box.

**Password Expiration:** Lets you choose an interval where passwords can
be active before they expire. You can select the age, the warning time,
and a grace limit, and you can enable or disable it using the check box.

**Lockout:** Allows you to set the number of failed log in attempts
before a user's account becomes locked. You can choose whether an
administrator needs to unlock the account or if it becomes unlocked
after a specific duration. You can enable or disable it using the check
box.

From the list of password policies, you can perform several other
actions.

**Edit: **Brings you to the form above and allows you to modify the
password policy.

**Permissions: ** This allows you to define which Users** **, User
Groups** **, or Roles** **have permissions** **to edit the Password
Policy.

**Assign Members: **Takes you to a screen where you can search and
select users** **in the portal to be assigned to this password policy.
The password policy will be enforced for any users** **who are added
here.

**Delete: **This shows up for any password policies that you add beyond
the default policy. You cannot delete the default policy.

### Settings

The Settings link is where most of the global portal settings are.

**General:** This lets you configure global settings, such as the
company name, domain, the virtual host, a global portal logo, and more.

**Authentication:** Allows you to configure log in IDs, connection to
LDAP, and Single Sign-On.

**Users:** Has three tabs, labeled *Fields*, *Reserved Credentials*, and
*Default User Associations*. The Fields tab enables or disables some
user fields, such as birthday or terms of use. The Reserved Credentials
tab lets you reserve screen names and email addresses so that users
cannot register using them. You might use this to prevent users from
registering on the portal with user names that contain profanity or that
sound official, such as *admin* or *president*. The Default User
Associations tab lets you configure default membership to Roles, User
Groups, and Communities for new users, and provides a check box which
allows you to retroactively apply these to existing users.

**Mail** **Host Names:** You can add a list of other mail host names
that are associated with your organization. For example, your main
domain might be `mycompany.com`{.western}, but you might use
`mycompany-marketing.com`{.western} for your email newsletters. Any
domain names associated with your organization can go here.

**Email** **Notifications:** Liferay sends email notifications for
certain events, such as user registrations, password changes, etc. You
can customize those messages here.

We will go over these settings in detail below.

#### General

The *General* link allows you to set the name of the company /
organization / site which is running the portal. Setting the name here
will also define the name of your portal's default community. By default
it is *liferay.com* , so you will definitely want to set this to reflect
your organization. You can also set the virtual host, the mail domain,
and several other items of miscellaneous information about the
organization.

#### Authentication: General Settings

The *Authentication* link has several tabs under it. All of these are
used for configuring how users will authenticate to Liferay. Because
Liferay supports a number of authentication methods, there are settings
for each.

The general settings affect only Liferay functionality, and don't have
anything to do with any of the integration options on the other tabs.
This tab allows you to customize Liferay's out-of-box behavior regarding
authentication. Specifically, the *General* tab allows you to select
from several global authentication settings:

-   Authenticate via email address (default), screen name, or user ID (a
    numerical ID auto-generated in the database—not recommended).

-   Enable / Disable automatic log in. If enabled, Liferay allows a user
    to check a box which will cause the site to “remember” the user's
    log in by placing a cookie on his or her browser. If disabled, users
    will have to log in manually always.

-   Enable / Disable forgotten password functionality.

-   Enable / Disable request password reset links.

-   Enable / Disable account creation by strangers. If you are running
    an Internet site, you will probably want to leave this on so that
    visitors can create accounts on your site.

-   Enable / Disable account creation by those using an email address in
    the domain of the company running the site (which you just set on
    the General tab). This is handy if you are using Liferay to host
    both internal and external web sites. You can make sure that all
    internal IDs have to be created by administrators, but external
    users can register for IDs themselves.

-   Enable / Disable email address verification. If you enable this,
    Liferay, will send users a verification email with a link back to
    the portal to verify that the email address they entered is a valid
    one they can access.

By default, all settings except for the last are enabled. One default
that is important is that users will authenticate by their email
address. Liferay defaults to this for several reasons:

1.  An email address is, by definition, unique to the user who owns it.

2.  People can generally remember their email addresses. If you have a
    user who hasn't logged into the portal for a while, it is possible
    that he or she will forget his or her screen name, especially if the
    user was not allowed to use his or her screen name of choice
    (because somebody else already used it).

3.  If a user changes his or her email address, it is more likely that
    the user will forget to update his or her email address in his or
    her profile, if that email address is not used to authenticate. If
    the user's email address is not updated, all notifications sent by
    the portal will fail to reach the user. So it is important to keep
    the email address at the forefront of a user's mind when he or she
    logs in to help the user keep it up to date.

For these reasons, Liferay defaults to using the email address as a user
name.

#### Authentication: LDAP

Connecting Liferay to an LDAP directory a straightforward process
through the Control Panel. There are still, however, two places in which
you can configure the LDAP settings: the
`portal-ext.properties`{.western} file (which will be covered in the
next chapter) and the Control Panel, where the settings will get stored
in the database. Note that if you use both, the settings in the database
will override the settings in `portal-ext.properties`{.western}. For
this reason, we recommend for most users that you use the Control Panel
to configure the LDAP settings—it's easier and does not require a
restart of Liferay. The only compelling reason to use the
`portal-ext.properties`{.western} file is if you have many Liferay nodes
which will be configured to run against the same LDAP directory. In that
case, for your initial deployment, it may be easier to copy the
`portal-ext.properties`{.western} file to all of the nodes so that the
first time they start up, the settings are correct. Regardless of which
method you use, the settings are the same.

The LDAP settings screen is where you configure the global values.

##### Enabled/Required

**Enabled: **Check this box to enable LDAP Authentication.

**Required:** Check this box if LDAP** **authentication is required.
Liferay will then not allow a user to log in unless he or she can
successfully bind to the LDAP** **directory first. Uncheck this box if
you want to allow users** **with Liferay accounts but no LDAP**
**accounts to log in to the portal.

##### LDAP Servers

This is where you add LDAP Servers. If you have more than one, you can
arrange the servers by order of preference using the up/down arrows.
When you add an LDAP Server, you will need to provide several pieces of
data so that Liferay can bind to that LDAP server and search it for user
records. Regardless of how many LDAP servers you add, each server has
the same configuration options:

##### Default Values

Several leading directory servers are listed here. If you are using one
of these, select it and the rest of the form will be populated with the
proper default values for that directory.

##### Connection

These settings cover the basic connection to LDAP.

**Base Provider URL:** This tells the portal where the LDAP server is
located. Make sure that the machine on which Liferay is installed can
communicate with the LDAP server. If there is a firewall between the two
systems, check to make sure that the appropriate ports are opened.

**Base DN: **This is the Base Distinguished Name for your LDAP
directory. It is usually modeled after your organization. For a
commercial organization, it may look something like:
`dc=companynamehere,dc=com`{.western}*. *

**Principal:** By default, the administrator ID is populated here. If
you have removed the default LDAP administrator, you will need to use
the fully qualified name of the administrative credential you do use.
You need an administrative credential because Liferay will be using this
ID to synchronize user accounts to and from LDAP .

**Credentials:** This is the password for the administrative user.

This is all you need in order to make a regular connection to an LDAP
directory. The rest of the configuration is optional: generally, the
default attribute mappings are sufficient data to synchronize back to
the Liferay database when a user attempts to log in. To test the
connection to your LDAP server, click the *Test LDAP* *Connection*
button.

If you are running your LDAP directory in SSL mode to prevent credential
information from passing through the network unencrypted, you will have
to perform extra steps to share the encryption key and certificate
between the two systems.

For example, assuming your LDAP directory happens to be Microsoft Active
Directory on Windows Server 2003, you would take the following steps to
share the certificate:

On the Windows 2003 Domain Controller, open the *Certificates* MMC
snapin. Export the Root Certificate Authority certificate by selecting
*Certificates (Local Computer) mmc snapin -\> Trusted Root Certification
Authorities -\> MyRootCACertificateName. *Right click this certificate
and select *All Tasks* -\> *export* -\> *select DER encoded binary X.509
.CER*. Copy the exported *.cer* file to your Liferay Portal server.

As with the CAS install (see the below section entitled **Single
Sign-On** ), you will need to import the certificate into the *cacerts
keystore*. The import is handled by a command like the following:

keytool -import -trustcacerts -keystore
/some/path/jdk1.5.0\_11/jre/lib/security/cacerts -storepass changeit
-noprompt

-alias MyRootCA -file /some/path/MyRootCA.cer

The *keytool* utility ships as part of the Java SDK.

Once this is done, go back to the LDAP page in the Control Panel. Modify
the LDAP URL in the **Base DN **field to the secure version by changing
the protocol to *https* and the port to 636 like the following:

ldaps://myLdapServerHostname:636

Save the changes. Your Liferay Portal will now use LDAP in secure mode
for authentication.

##### Users

This section contains settings for finding users in your LDAP directory.

**Authentication Search Filter: **The search filter box can be used to
determine the search criteria for user log ins. By default, Liferay uses
the email** **address as a user log in name. If you have changed this
setting—which can be done on the *General*** **tab ** **that's next to
the *LDAP*** **tab in the *Settings-\>Authentication*** **section of the
Control Panel** **—you will need to modify the search filter here, which
has by default been configured to use the email** **address attribute
from LDAP** **as search criteria. For example, if you changed Liferay's
authentication method to use the screen name instead of the email**
**address, you would modify the search filter so that it can match the
entered log in name:

(cn=@screen\_name@)

**Import Search Filter: **Depending on the LDAP** **server, there are
different ways to identify the user. Generally, the default setting
`(`{.western}`objectClass=inetOrgPerson`{.western}`)`{.western} is fine,
but if you want to search for only a subset of users** **or users**
**that have different object classes, you can change this.

**User Mapping: **The next series of fields allows you to define
mappings from LDAP** **attributes to Liferay fields. Though your LDAP**
**user attributes may be different from LDAP** **server to LDAP**
**server, there are five fields that Liferay requires to be mapped in
order for the user to be recognized. You must define a mapping to the
corresponding attributes in LDAP** **for the following Liferay fields:

-   Screen Name

-   Password

-   Email Address

-   Full Name

-   First Name

-   Middle Name

-   Last Name

-   Job Title

-   Group

The Control Panel provides default mappings for commonly used LDAP
attributes. You can also add your own mappings if you wish.

**Test LDAP Users:** Once you have your attribute mappings set up (see
above), click the *Test LDAP*** ***Users*** **button, and Liferay will
attempt to pull LDAP** **users** **and match them up with their mappings
as a preview.

![image](../../images/portal-admin-ch3_html_3312b71c.jpg)\
*Illustration 17: Testing LDAP Users*

##### **Groups**

This section contains settings for mapping LDAP groups to Liferay.

**Import Search Filter:** This is the filter for finding LDAP** **groups
that you want to map to Liferay. Enter the LDAP** **group attributes
that you want retrieved for this mapping. The following attributes can
be mapped:

-   Group Name

-   Description

-   User

Test LDAP** **Groups: Click the *Test LDAP*** ***Groups*** **to display
a list of the groups returned by your search filter.

#### Export

**Users DN:** Enter the location in your LDAP** **tree where the users**
**will be stored. When Liferay does an export, it will export the
users** **to this location.

**User Default Object Classes:** When a user is exported, the user is
created with the listed default object classes. To find out what your
default object classes are, use an LDAP browser tool such as *JXplorer*
to locate a user and view the Object Class attributes that are stored in
LDAP for that user.

**Groups DN: **Enter the location in your LDAP** **tree where the groups
will be stored. When Liferay does an export, it will export the groups
to this location.

**Group Default Object Classes:** When a group is exported, the group is
created with the listed default object classes. To find out what your
default object classes are, use an LDAP browser tool such as *Jxplorer*
to locate a group and view the Object Class attributes that are stored
in LDAP for that group.

\
\

![image](../../images/portal-admin-ch3_html_e5994f7.jpg)\
*Illustration 18: Mapping LDAP Groups*

Once you've set all your options and tested your connection, click
*Save*. From here, you can add another LDAP server or set just a few
more options that apply to all of your LDAP server connections.
##### Import/Export

**Import Enabled:** Check this box to cause Liferay to do a mass import
from your LDAP** **directories. If you want Liferay to only synchronize
users** **when they log in, leave this box unchecked. Definitely leave
this unchecked if you are working in a clustered environment. Otherwise,
all of your nodes would try to do a mass import when each of them starts
up.

If you check the box, one more option becomes available.

**Import on Startup Enabled:** Check this box to have Liferay run the
import when it starts up. This box only appears if you check *Import
Enabled*** **above.

**Export Enabled:** Check this box to enable Liferay to export user
accounts from the database** **to LDAP** **. Liferay uses a listener to
track any changes made to the User object and will push these changes
out to the LDAP** **server whenever the User object is updated. Note
that by default on every log in, fields such as
`LastLoginDate`{.western}** **are updated. When export is enabled, this
has the effect of causing a user export every time the user logs** **in.
You can disable this by setting the following property in your
`portal-ext.properties`{.western}** **file:** **

users.update.last.login=false

**Use LDAP Password Policy:** Liferay uses its own password policy by
default. This can be configured on the *Password Policies*** **link in
the *Portal*** **section on the left side of the Control Panel** **. If
you want to use the password policies defined by your LDAP**
**directory, check this box. Once this is enabled, the *Password
Policies*** **tab will display a message stating that you are not using
a local password policy. You will now have to use your LDAP**
**directory's mechanism for setting password policies. Liferay does this
by parsing the messages in the LDAP** **controls that are returned by
your LDAP** **server. By default, the messages in the LDAP** **controls
that Liferay is looking for are the messages that are returned by the
Fedora Directory Server. If you are using a different LDAP** **server,
you will need to customize the messages in Liferay's
`portal-ext.properties`{.western}** **file, as there is not yet a GUI
for setting this. See below for instructions describing how to do this.

Once you have completed configuring LDAP, click the *Save* button.

##### LDAP Options Not Available in the GUI

Though most of the LDAP configuration can be done from the Control
Panel, there are several configuration parameters that are only
available by editing `portal-ext.properties`{.western}. These options
will be available in the GUI in future versions of Liferay Portal, but
for now they can only be configured by editing the properties file.

If you need to change any of these options, copy the LDAP section from
the `portal.properties`{.western} file into your
`portal-ext.properties`{.western} file. Note that since you have already
configured LDAP from the GUI, any settings from the properties file that
match settings already configured in the GUI will be ignored. The GUI,
which stores the settings in the database, always takes precedence over
the properties file.

ldap.auth.method=bind

\#ldap.auth.method=password-compare

Set either bind or password-compare for the LDAP authentication method.
Bind is preferred by most vendors so that you don't have to worry about
encryption strategies. Password compare does exactly what it sounds
like: it reads the user's password out of LDAP, decrypts it, and
compares it with the user's password in Liferay, syncing the two.

ldap.auth.password.encryption.algorithm=

ldap.auth.password.encryption.algorithm.types=MD5,SHA

Set the password encryption to used to compare passwords if the property
`ldap.auth.method`{.western} is set to password-compare.

ldap.import.method=[user,group]

If you set this to `user`{.western}*, *Liferay will import all users
from the specified portion of the LDAP tree. If you set this to
`group`{.western}, Liferay will search all the groups and import the
users in each group. If you have users who do not belong to any groups,
they will not be imported.

ldap.error.password.age=age

ldap.error.password.expired=expired

ldap.error.password.history=history

ldap.error.password.not.changeable=not allowed to change

ldap.error.password.syntax=syntax

ldap.error.password.trivial=trivial

ldap.error.user.lockout=retry limit

These properties are a list of phrases from error messages which can
possibly be returned by the LDAP server. When a user binds to LDAP , the
server can return *controls* with its response of success or failure.
These controls contain a message describing the error or the information
that is coming back with the response. Though the controls are the same
across LDAP servers, the messages can be different. The properties
described here contain snippets of words from those messages, and will
work with Red Hat's Fedora Directory Server. If you are not using that
server, the word snippets may not work with your LDAP server. If they
don't, you can replace the values of these properties with phrases from
your server's error messages. This will enable Liferay to recognize
them.

#### Single Sign-On

Single Sign-On solutions allow you to provide a single log in credential
for multiple systems. This allows you to have people authenticate to the
Single Sign-On product and they will be automatically logged in to
Liferay and to other products as well.

Liferay at the time of this writing supports several single sign-on
solutions. Of course if your product is not yet supported, you may
choose to implement support for it yourself by use of the extension
environment—or your organization can choose to sponsor support for it.
Please contact sales@liferay.com for more information about this.

#### Authentication: Central Authentication Service (CAS)

CAS is an authentication system that was originally created at Yale
University. It is a widely-used open source single sign-on solution, and
was the first SSO product to be supported by Liferay.

Please follow the documentation for CAS to install it on your
application server of choice.

Your first step will be to copy the CAS client .jar file to Liferay's
library folder. On Tomcat, this is in
`[Tomcat Home]/webapps/ROOT/WEB-INF/lib`{.western}. Once you've done
this, the CAS client will be available to Liferay the next time you
start it.

The CAS Server application requires a properly configured Secure Socket
Layer certificate on your server in order to work. If you wish to
generate one yourself, you will need to use the `keytool`{.western}
utility that comes with the JDK. Your first step is to generate the key.
Next, you export the key into a file. Finally, you import the key into
your local Java key store. For public, Internet-based production
environments, you will need to either purchase a signed key from a
recognized certificate authority (such as Thawte or Verisign) or have
your key signed by a recognized certificate authority. For Intranets,
you should have your IT department pre-configure users' browsers to
accept the certificate so that they don't get warning messages about the
certificate.

To generate a key, use the following command:

keytool -genkey -alias tomcat -keypass changeit -keyalg RSA

Instead of the password in the example (*changeit*), use a password that
you will be able to remember. If you are not using Tomcat, you may want
to use a different alias as well. For First and Last name, enter
*localhost, * or the host name of your server. It cannot be an IP
address.

To export the key to a file, use the following command:

keytool -export -alias tomcat -keypass changeit -file server.cert

Finally, to import the key into your Java key store, use the following
command:

keytool -import -alias tomcat -file %FILE\_NAME% -keypass changeit
-keystore $JAVA\_HOME/jre/lib/security/cacerts

If you are on a Windows system, replace `$JAVA_HOME`{.western} above
with `%JAVA_HOME%`{.western}. Of course, all of this needs to be done on
the system on which CAS will be running.

Once your CAS server is up and running, you can configure Liferay to use
it. This is a simple matter of navigating to the *Settings* -\>
*Authentication* -\> *CAS* tab in the Control Panel. Enable CAS
authentication, and then modify the URL properties to point to your CAS
server.

**Enabled:** Set this to true to enable CAS single sign-on.

**Import**** from LDAP:** A user may be authenticated from CAS and not
yet exist in the portal. Select this to automatically import users from
LDAP if they do not exist in the portal.

The rest of the settings are various URLs, with defaults included.
Change *localhost* in the default values to point to your CAS server.
When you are finished, click *Save*. After this, when users click the
*Sign In* link, they will be directed to the CAS server to sign in to
Liferay.

#### Authentication: Facebook

Liferay Portal also enables users to log in using their Facebook
accounts. To enable this feature, you simply need to select the *Enable*
box and enter the Application ID and Application Secret which should
have been provided to you by Facebook. Facebook SSO works by taking the
primary Facebook email address and searching for the same email address
in Liferay's `User_`{.western} table. If a match is found, the user is
automatically signed on (provided that user clicked *allow* from the
Facebook dialog). If there isn't a match, the user is prompted in
Liferay to add a user from Facebook. Once selected, a new user is
created by retrieving four fields from Facebook (first name, last name,
email address, and gender).

#### Authentication: NTLM

NTLM is a Microsoft protocol that can be used for authentication through
Microsoft Internet Explorer. Though Microsoft has adopted Kerberos in
modern versions of Windows server, NTLM is still used when
authenticating to a workgroup. Liferay Portal now supports NTLM v2
authentication. NTLM v2 is more secure and has a stronger authentication
process than NTLMv1.

**Enabled:** Check the box to enable NTLM authentication.

**Domain Controller:** Enter the IP address of your domain controller.
This is the server that contains the user accounts you want to use with
Liferay.

**Domain:** Enter the domain / workgroup name.

#### Authentication: OpenID

OpenID is a new single sign-on standard which is implemented by multiple
vendors. The idea is that multiple vendors can implement the standard,
and then users can register for an ID with the vendor they trust. The
credential issued by that vendor can be used by all the web sites that
support OpenID. Some high profile OpenID vendors are AOL
([http://openid.aol.com/screenname](http://openid.aol.com/screenname)),
LiveDoor
([http://profile.livedoor.com/username](http://profile.livedoor.com/username)),
and LiveJournal
([http://username.livejournal.com](http://username.livejournal.com/)).
Please see the OpenID site
([http://www.openid.net](http://www.openid.net/)) for a more complete
list.

The obvious main benefit of OpenID for the user is that he or she no
longer has to register for a new account on every site in which he or
she wants to participate. Users can register on *one *site (the OpenID
provider's site) and then use those credentials to authenticate to many
web sites which support OpenID. Many web site owners often struggle to
build communities because end users are reluctant to register for so
many different accounts. Supporting OpenID makes it easier for site
owners to build their communities because the barriers to participating
(i.e., the effort it takes to register for and keep track of many
accounts) are removed. All of the account information is kept with the
OpenID provider, making it much easier to manage this information and
keep it up to date.

Liferay Portal can act as an OpenID consumer, allowing users to
automatically register and sign in with their OpenID accounts.
Internally, the product uses OpenID4Java
([http://code.google.com/p/openid4java/](http://code.google.com/p/openid4java/))
to implement the feature.

OpenID is enabled by default in Liferay, but can be disabled here.

##### Atlassian Crowd

Atlassian Crowd is a web-based Single Sign-On product similar to CAS.
Crowd can be used to manage authentication to many different web
applications and directory servers.

Because Atlassian Crowd implements an OpenID producer, Liferay works and
has been tested with it. Simply use the OpenID authentication feature in
Liferay to log in using Crowd.

#### Authentication: OpenSSO

OpenSSO is an open source single sign-on solution that comes from the
code base of Sun's System Access Manager product. Liferay integrates
with OpenSSO, allowing you to use OpenSSO to integrate Liferay into an
infrastructure that contains a multitude of different authentication
schemes against different repositories of identities.

You can set up OpenSSO on the same server as Liferay or a different box.
Follow the instructions at the OpenSSO site
([http://opensso.dev.java.net](http://opensso.dev.java.net/)) to install
OpenSSO. Once you have it installed, create the Liferay administrative
user in it. Users are mapped back and forth by screen names. By default,
the Liferay administrative user has a screen name of *test*, so in
OpenSSO, you would register the user with the ID of *test* and an email
address of *test@liferay.com*. Once you have the user set up, log in to
Open SSO using this user.

In the same browser window, go to the URL for your server running
Liferay and log in as the same user, using the email address
*test@liferay.com*. Go to the Control Panel and click *Settings -\>
Authentication -\> OpenSSO*. Modify the three URL fields (Login URL,
Logout URL, and Service URL) so that they point to your OpenSSO server
(i.e., only modify the host name portion of the URLs), click the
*Enabled* check box, and then click *Save.* Liferay will then redirect
users to OpenSSO when they click the *Sign In* link.

#### Authentication: SiteMinder

SiteMinder is a single sign-on implementation from Computer Associates.
Liferay 5.2 now has built-in integration with SiteMinder. SiteMinder
uses a custom HTTP header to implement its single sign-on solution.

To enable SiteMinder authentication in Liferay, check the *Enabled* box
on the *SiteMinder* tab. If you are also using LDAP with Liferay, you
can check the *Import from LDAP* box. If this box is checked, user
authenticated from SiteMinder that do not exist in the portal will be
imported from LDAP.

The last field defines the header SiteMinder is using to keep track of
the user. The default value is already populated. If you have customized
the field for your installation, enter the custom value here.

When you are finished, click *Save*.

#### Users

The *Users* link has three tabs: *Fields, Reserved Credentials, and
Default User Associations. *

The *Fields *tab allows you to enable/disable the following fields:

-   Enable/disable requiring Terms of Use

-   Enable/disable user screen names autogeneration

-   Enable/disable the birthday field

-   Enable/disable the gender field

The next tab is *Reserved Credentials*. You can enter screen names and
email addresses here that you don't want others to use. Liferay will
then prevent users from registering with these screen names and email
addresses. You might use this feature to prevent users from creating IDs
that look like administrative IDs or that have reserved words in their
names.

The *Default User Associations* tab has three fields allowing you to
list (one per line) communities, roles, and user groups that you want
new users to be members of by default. Liferay's default is to have new
users be members of both the Users role and the Power Users role.

The Power Users role has the rights to use most of the portlets that
ship with Liferay, so it is enabled by default. If you remove this role
from the list, you may find that you need to grant users via a different
role the ability to use some portlets (such as the Wiki or Message
Boards).

If you have defined other user groups, communities, or roles that you
want newly created users to be members of by default, enter them here.
For example, you may have defined page templates in certain user groups
to pre-populate end users' private pages. If there is a particular
configuration that you want everyone to have, you may want to enter
those user groups here.

#### Mail Host Names

The next link is *Mail* *Host Names*. You can enter (one per line) other
mail host names besides the one you configured on the General tab. This
lets the portal know which mail host names are owned by your
organization.

#### Email Notifications

There are four tabs under the *Email* *Notifications* link. The *Sender*
tab allows you to set the portal's administrative name and email
address. By default, this is *Joe Bloggs* and *test@liferay.com*. You
can change it to anything you want. This name and address will appear in
the **From** field in all email messages sent by the portal.

The other three tabs (*Account Created Notification, Password Changed
Notification, and Password Reset Notification*) allow you to customize
the email messages that are sent to users on those three events. A list
of tokens for inserting certain values (such as the portal URL) is given
if you wish to use those.

#### Identification

The identification section has several links for addresses, phone
numbers, and other information you can configure in your portal. This
allows you to set up contact information for the organization that is
running the portal. Developers can query for this information in their
applications.

#### Miscellaneous: Display Settings

This section allows you to set the default portal language and the time
zone. You can also set the portal-wide logo which appears in the top
left corner of themes that are configured to display it. Be careful when
using this option to choose an image file that fits the space. If you
pick something that is too big, it will mess up the navigation.

### Custom Fields

Custom fields are a way to add attributes to many types of assets in the
portal. For example, if you are using Liferay Portal to create a site
for rating books, you might add custom fields to several assets in the
portal. You might give the User object a custom field called Favorite
Books. If you're using the wiki for book reviews, you might add fields
for Book Title and Book Author.

To add a custom field simply click on the *Custom Fields* link in the
Control Panel then click on the *Edit *link and then select *Add Custom
Field*.

![image](../../images/portal-admin-ch3_html_m12b2fefd.png)\
*Illustration 19: The Wiki shows custom fields integrated with the rest
of the fields on the form. *

\
\
From here you will need to add the custom field key. The key appears as
the label for the field on the form. For some portal assets (like the
User), custom fields are a separate section of the form. For others, as
can be seen above, custom fields are integrated with the default fields
on the form. Additionally, developers can access custom fields
programatically through the `<liferay-ui:custom-attribute />`{.western}
tag.

You can create fields of many different types: text fields (indexed or
secret), integers, selection of multiple values, and more. Once you've
created a field, you cannot change its type.

### Monitoring

The next link on the left side of the Control Panel is for monitoring.
Using this, you can see all of the live sessions in the portal. For
performance reasons, this setting is generally turned off in production,
but if you have it turned on, you can view the active sessions here.

### Plugins Configuration

The Plugins Configuration link contains tabs for three types of plugins:
portlets, themes, and layouts. You can use these tabs to view which
roles can add plugins to pages or you can make the plugins active or
inactive.

Note that this is for basic configuration: if you want to view the
existing permission configuration for a given portlet and/or modify that
configuration for existing roles, this is where you can do that. If you
need to add permissions to new roles for a given portlet, use the Roles
section of the Control Panel and the *Actions → Define Permissions*
button.

### Page Templates

The Page Templates link allows you to create a custom page with the
layout, portlets and web content you will want to reuse. From this link
you can also edit existing templates and modify their permissions.

![image](../../images/portal-admin-ch3_html_m2e4371b8.png)\
*Illustration 20: You can create page templates with predefined layouts
and portlets that can be used over and over. *

\
\
To add a page template click on the *Page Template* link in the Control
Panel. From there select *Add* and then enter a name and description for
your template. Below the description field is a toggle that lets you
make the template active or inactive, and below this is a link to open
the page template so you can edit it. The template pictured above in a
university portal might be for returning students. Clicking the *Open
Page Template* link brings you to the Manage Pages interface, which
we'll see in the next chapter.

Once you're finished adding the content, layout, and portlets, return to
the Page Template portlet (which is in another browser tab) and select
*Save.*

When you want to use the new template to create a new page on a site,
simply navigate to *Control Panel **→* *Pages *and the page is available
in the template drop down menu when you're adding a page.

### Site Templates

The Site Templates link allows you to define a set of predefined pages
to include when creating new sites for communities and organizations.
You may define and edit site templates as well as their permissions.

![image](../../images/portal-admin-ch3_html_4e601928.png)\
*Illustration 21: Site templates help you to quickly build many similar
sites using the same pattern. *

\
\
To add a site template click on the *Site Template* link in the Control
Panel. From there you can select *Add* and then enter a name and
description for your template. Also, below the description field is
where you can make this template active.

Once this is complete, click on *Actions* → *Manage Pages*. From here
you can add pages to your template, configure the look and feel by
managing themes or CSS files, and export the template as a .lar file.
You can also import a .lar file from here as well.

On the pages you've created for the site template, you can add the
portlets and content you want. Once you've added the pages, portlets,
content, and themes, you can use this template when you create a new
community. The template will appear the in the drop down menus for the
Public Pages/Private Pages options when you are adding a community.

### Server Administration

The Server Administration link lets you perform various tasks relating
to administration of the overall portal server, as opposed to
administering resources in the portal. Clicking the link makes this
abundantly clear: you're immediately presented with a graph showing the
resources available in the JVM.

#### Resources

The first tab is called *Resources* . This tab contains the
aforementioned graph plus several server wide actions that an
administrator can execute. These are:

**Garbage collection:** You can send in a request to the JVM to begin
the garbage collection task.

**Clearing VM caches:** You can send in a request to the JVM to clear a
single VM cache.

**Clearing caches across the cluster:** You can send in a request to the
JVM to clear content cached across the entire cluster.

**Clearing database caches:** You can send in a request to the JVM to
clear the database cache.

**Reindex all search indexes: **You can send in a request** **to
regenerate all search indexes. If you are not using a Solr** **search
server (see Chapter 7 for further information). This will impact portal
performance, so try not to do this except at non-peak times.

**Generate Thread Dump: **If you are performance testing, you can
generate a thread dump which can be examined later to determine if there
are any deadlocks and where they might be.

**Verify database tables of all plugins:** Checks all tables against
their indexes for accuracy of data retrieval.

#### Log Levels

Here you can dynamically modify the log levels for any class hierarchy
in the portal. If you have custom code that you have deployed which
isn't in the list, you can use the *Add Category* tab to add it. If you
change the log level near the top of the class hierarchy (such as at
`com.liferay`{.western}), all the classes under that hierarchy will have
their log levels changed. If you are testing something specific, it is
much better to be as specific as you can when you change log levels.
Modifying them too high in the hierarchy generates a lot more log
messages than you need.

#### Properties

Liferay and the JVM contain many settings which are defined as
properties. There are two tabs here: one showing system properties and
one showing portal properties.

The system properties tab shows an exhaustive list of system properties
for the JVM, as well as many Liferay system properties. This information
can be used for debugging purposes or to check the configuration of the
currently running portal.

The portal properties tab tab shows an exhaustive list of the portal
properties. These properties can be customized, as will be seen in the
next chapter. If you need to check the current value of a particular
property, it can be viewed from this screen without having to shut down
the portal or open any properties files.

#### Captcha

By default, Liferay ships with its own simple captcha service which is
designed to thwart bots from registering for accounts on sites powered
by Liferay. If you want to instead use Google's reCaptcha service, you
can do that.

Simply check the *Enable ReCaptcha* box and enter your public and
private keys into the text fields provided, and then click *Save*.
Liferay Portal will then use reCaptcha instead of simple captcha.

#### Data Migration

If you are upgrading from a previous release of Liferay Portal or if you
need to migrate your data from one system to another, this section helps
you to do that without your developers having to write custom scripts.

The first section lets you copy your entire Liferay database from the
current database under which it is running to the database you specify
in this set of fields. You'll need to enter the driver class name (and
the driver will need to be on Liferay's classpath), the JDBC URL of the
database to which you'll be copying your data, and a user name and
password that has access to that database. Once you have all of this
information entered, click *Execute* to copy the data.

The next section helps you migrate your documents. If you want to move
off of the Jackrabbit JSR-170 repository to the file system, or to the
Jackrabbit repository from the file system, or to any of the other
repositories supported by the document library, you can do this very
easily. Make sure you have already set up your
`portal-ext.properties`{.western}file so that the hook is properly
configured before running this migration. Select the Document Library
hook that represents where you want your documents migrated, and click
*Execute*. Your documents will be migrated to the new repository, and
you can then shut down Liferay, make the new repository the default in
the `portal-ext.properties`{.western} file, and then restart.

Similarly, you can migrate images from the Image Gallery in the same
manner.

#### File Uploads

Since Liferay allows users to upload files in various places, you may
want to lock down the type of files and the size of files users are
allowed to upload. Here, you can set the overall maximum file size and
then override that size for specific applications within Liferay. You
can limit the allowed file extensions generally or by application. You
have a lot of flexibility as to how you want files to be managed within
your portal.

#### Mail

Rather than using the `portal-ext.properties`{.western} file as we did
in the installation chapter, you can configure a mail server here. If
the portal is to receive mail (see the Message Boards portlet in Chapter
5), you can connect a POP mail server. And of course, if the portal is
to send mail—which it needs to do in order to send notifications to
users—you can connect an SMTP server here as well, and this is highly
recommended.

Note that if you add your mail server settings here, they will override
anything that is in your `portal-ext.properties`{.western} file.

#### OpenOffice

Liferay Portal enables users to add content in many formats: web
content, images, and files. This is done using the Web Content
Management System, the Image Gallery, the Document Library, and in other
portlets, both built-in and custom written. Sometimes, it is helpful to
convert this content from whatever format it is in to a format that is
more convenient for the user browsing the content. Liferay Portal allows
users to do this by integrating with OpenOffice.org.

OpenOffice.org is an open source office suite which is normally run in
graphical mode to create documents, but it can be run in “server” mode.
When run in server mode, OpenOffice.org can be used to convert documents
to and from all of the file types it supports. Liferay can then make use
of this feature to automatically convert content on the fly.

Use this tab to tell Liferay how to connect to your running instance of
OpenOffice.org. You can install OpenOffice.org on the same server upon
which Liferay is running. Once you have it installed, you can start
OpenOffice.org in server mode with the following command:

soffice -headless -accept="socket,host=127.0.0.1,port=8100;urp;"
-nofirststartwizard

As you can see, the command above specifies that OpenOffice.org will run
on port 8100, which is the default port in the Control Panel . If you
can use this port, all you need to do is check the *Enabled* box, and
Liferay will be integrated with OpenOffice.org.

If you have something else running on this port, find a port that is
open and specify it both in the command above and on the Control Panel's
OpenOffice.org con figuration page. When you are finished, click *Save*.

#### Script

Liferay includes a scripting console which lets administrators execute
migration or management code instantly. Several scripting languages are
supported, including JavaScript, Groovy, Python, Ruby, and Beanshell.
For further information about Liferay's APIs, see the JavaDoc, the
Liferay Wiki ([http://wiki.liferay.com](http://wiki.liferay.com/)), or
*Liferay in Action*.

#### Shutdown

If you ever need to shut down your Liferay Portal server while users are
logged in, you can use the Shutdown tab to inform your logged-in users
of the impending shutdown. You can define the number of minutes until
the shutdown and a custom message that will be displayed.

Users will see your message at the top of their portal pages for the
duration of time you specified. When the time expires, all portal pages
will display a message saying the portal has been shut down. At this
point, the server will need to be restarted to restore access.

### Portal Instances

Liferay Portal allows you to run more than one portal instance on a
single server. Data for each portal instance are kept separate from
every other portal instance. All portal data, however, is kept in the
same database.

Each portal instance requires its own domain name. Liferay will direct
users to the proper portal instance based on this domain name. So before
you configure an instance, configure its domain name in your network
first. When you're ready to add an instance, click the *Add* button
here.

You'll be prompted for three fields:

**Web ID: **A general convention is to use the domain name for this.
It's a user-generated ID for the instance.

**Virtual Host: **Put the domain name you configured in your network
here. When users** **are directed to your Liferay server via this domain
name, Liferay will then be able to send them to the proper portal
instance.

**Mail Domain: **Enter the domain name for the mail** **host for this
instance. Liferay will use this to send email** **notifications from the
portal.

When you are finished filling out the form, click *Save*** **. Now
navigate to the portal using your new domain name. You will see that you
are brought to what looks like a clean install of Liferay. This is your
new portal instance which can now be configured any way you like.

### Plugins Installation

The *Plugins Installation* link shows all of the plugins that are
currently installed. These are divided into tabs for portlets, themes,
layout templates, Hook plugins, and Web plugins. If you want to install
a new plugin, click the *Install More Portlets* button. You will then be
brought to the **Plugin** **Installer**, where you can browse Liferay's
repository of portlets or install your own plugins. The Plugin Installer
will be covered in Chapter 6.

## Summary

This chapter has described the resources in Liferay Portal that can be
configured to build the foundation of your web site. We have seen how to
navigate Liferay's user interface so that you can get anywhere you need
to in the portal. We have also looked at overall portal architecture and
how you might go about designing your site using Liferay.

Next, we went in-depth through Liferay's Control Panel. Using the
Control Panel, we learned how to manage users, organizations, user
groups, and roles. We also learned how to configure various server
settings, such as authentication, LDAP integration, and single sign-on.
We also learned how to associate users by default with different user
groups, communities, and roles, and we saw how to reserve screen names
and e mail addresses so that users cannot register in the portal with
them.

Next, we saw how to view and configure overall server settings. We saw
how to view the memory currently being used by the server, as well as
how to initiate garbage collection, a thread dump, search engine
re-indexing, and the clearing of various caches. We learned how to debug
parts of the portal by changing log levels, and by viewing the various
properties that are defined in the portal.

Finally, we learned how to properly notify users that the portal is
about to shut down and how to enable the OpenOffice.org integration. The
ability to run multiple portal instances on one installation of Liferay
was covered, and we saw how to view the plugins that are currently
installed.

All of this information was designed to put you on the path to becoming
a seasoned Liferay Portal Administrator.

\

