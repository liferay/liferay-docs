# Social Office Features 

Liferay Portal is a full featured web portal, containing an industry-leading
suite of built-in applications and tools. It's highly customizable and
businesses use it to run major web sites all over the world. We've optimized
Liferay Portal for creating state-of-the art, robust web sites that serve
millions of users every day. 

Social Office adds functionality for a specific purpose: collaboration. We've
designed it for work groups needing to collaborate on documents and data in the
office. The additional tools and defaults were carefully chosen to provide users
working within a group the ability to easily communicate, collaborate, and
coordinate their tasks.

Social Office adds extra collaborative tools to Liferay and allows you to
quickly set up sites designed to facilitate collaboration. Each site is designed
to provide a single group or team with a virtual shared workspace. Members of
your organization who belong to multiple groups or teams in your organization
can belong to multiple Social Office sites on the portal. Social Office also
provides each user with customized sets of public and private pages for their
personal sites. These personal sites serve as virtual individual workspaces. The
applications they include help users manage their tasks, keep informed about
each other's activities, and communicate with each other.

Whether you're creating a public web site and need the full features of an
unparalleled, industry-leading engine or you're creating a small corporate
Intranet, Liferay Portal is the ideal solution for you. You should consider
installing the Social Office plugin if you'd like your site to facilitate
communication between people within specific groups and accomplish tasks using
shared virtual workspaces with minimal set up and administration.

The Social Office theme adds a convenient button to the top-right corners of
Social Office pages. This button allows you to expand the portlets on the page
to fit the size of your browser window. You can click the button again to return
the portlets to their default size. In addition to the standard Social Office
theme, there's another theme for Social Office's Welcome page.

## Using the Social Office User Role and Personal Sites 

The Social Office plugin adds a special role to Liferay Portal: Social Office
User. Assigning users to the Social Office User role provides them with personal
sites pre-built with pages and applications designed to help them manage their
work, share information, and communicate with each other. Assign yourself to the
Social Office User role to see how it works. Once your user account has been
granted the Social Office User role, a *Dashboard* link appears in the Dockbar.
Now you can explore the extra features added to Liferay by Social Office.

If all your portal users will be using Social Office, you should add this role
to your portal's default user associations. Use the *Go to* menu of the Dockbar
to navigate to the Control Panel. Under the Portal heading, click on *Portal
Settings*. Then click on *Users* from the right-hand navigation menu. Finally,
open the *Default User Associations* tab and add the role *Social Office User*.
Now, newly created users will be assigned to the Social Office User role. If
you've been using Liferay but have just installed Social Office, you can check
the *Apply to Existing Users* box to grant the Social Office User role to
previously created users. These users will receive the role the next time they
log in to the portal.

The default personal pages provided by the Social Office user role include the
private Dashboard page and the public Profile page. When a user who has been
assigned to the Social Office User role logs in, several changes are made to his
or her Dockbar. First, a *Dashboard* link appears to the right of the *Go to*
menu.  You can click this link from anywhere in the portal to go to your virtual
private workspace. Second, a *Notifications* button is added to the right of the
Dashboard link. The Notifications button displays how many unread notifications
you have. You'll be notified upon receiving a private message, an invitation to
join a site, a social connection request, or an event reminder. Lastly, the
right-most item of the Dockbar becomes a drop-down list containing a link to
your public *Profile* page as well as a link your *My Account* page of the
control panel and the *Sign Out* link.

### Using the Dashboard and Other Private Pages 

The private page sets of Social Office users serve as personal workspaces. Each
private page displays a navigation menu and the Sites portlet in the left column
so that you can navigate back and forth between the private pages of your
personal site, navigate to your public pages, or navigate to one of the other
sites you belong to. The private pages provided by Social Office include:

- Dashboard
- Contacts Center
- Microblogs
- Messages
- My Documents
- Tasks

![Figure 1.11: The Dashboard navigation menu contains links to each private page
of your personal site as well as a link to your public Profile page. Clicking
the Social Office logo takes you to your
Dashboard.](../../images/dashboard-menu.png)

The Sites portlet is an improved version of the basic My Sites portlet. It
allows you to star various sites to customize your sites list. You can select
*All Sites* to display all the sites in the portal, *My Sites* to display all
the sites you belong to, or *My Favorites* to display your custom list of
starred sites. The option to star sites is very useful for large Social Office
instances with web applications and content hosted across many different sites.
There's also a search bar which you can use to display sites related to your
search query. If you have permission to add sites, you'll see an *Add Site*
button which opens a wizard to guide you through the site creation process. This
wizard will create a new site based on the *Default Social Office Site*
template. This site comes pre-built with pages and applications designed for
team collaboration. The *More Sites* button opens a site directory dialog box
which you can use to join, leave sites, or star sites. If you have the required
permissions, you can also delete sites.

![Figure 1.12: The Sites portlet lets you customize your sites list and create
new Social Office sites with a simple wizard.](../../images/sites-portlet.png)

The Dashboard page provides several portlet applications designed to keep you up
to date with various kinds of portal activity:

- Activities
- Upcoming Tasks
- Events
- Microblogs Status Update

Using the power of Liferay's Social API, the Activities portlet presents
information about different categories of users' recent activities. Use the tabs
to display portal activities from different scopes: *Connections* shows a list
of activities from users you have established a social connection with,
*Following* shows activities from users you're following, and *My Sites* shows
all the activities from sites you belong to. You can also select *Me* to view
just your own activities. The Upcoming Tasks portlet displays a list of tasks
that have been created for you or that you've assigned to yourself. The Events
portlet integrates with the calendar portlet belonging to your scope to show
upcoming events for the next few days. There's also a Microblogs Status Update
portlet on your Dashboard which lets you broadcast a Facebook-like status to the
scope of your choice: to everyone, to your connections, or to your followers.
Next, let's take a look at the applications provided on the other private pages
of Social Office users' personal sites.

![Figure 1.13: The default Dashboard applications are designed to inform you
about recent portal activities and upcoming events, and to help you manage your
personal tasks.](../../images/dashboard-applications.png)

The Contacts Center is an important application for Social Office because it
allows users to create LinkedIn-style connections and Twitter-style followers.
This enables users to customize who they share information about social
activities with and whose activity they're interested in. The Contacts Center
application displays a list of portal users. You can choose to display *All*,
*Connections*, or *Following* and you can also search for specific users. Select
a user from the left-hand area of the Contacts Center portlet to view details
about him or her. For each user, you'll have the following options:

- *Add as Connection* sends a connection request to the selected user.
- *Follow/Unfollow* subscribes and unsubscribes you to the selected user's
  activities stream.
- *Block/Unblock* allows you to block and unblock users from viewing your
  activities stream. As of Social Office 2.0, blocking a user only prevents that
  user from following you or adding you as a connection. A blocked user can
  still send messages to and view the public profile information of the blocking
  user. 
- *Export vCard* lets you export a user's vCard and save it as a VCF file. vCard
  is a file format standard for electronic business cards.
- *Go to Profile* is a link to the selected user's public profile page.

![Figure 1.14: Contacts Centers allows users to follow each other Twitter-style
and set up Linked-In style connections.](../../images/contacts-center.png)

Social Office's Microblogs application allows you to broadcast your thoughts as
a Facebook-like status to a chosen audience. You can choose for your status to
be viewable by *All*, *Connections*, or *Followers*. There are two special
symbols that you can use in your microblog posts. The hashtag symbol (`#`) turns
the following word into a hashtag. When viewing a microblog post, hashtags
appear as links. You can click on any hashtag to view a list of microblog posts
that reference the same hashtag. Note that hashtags are distinct from portal
tags--only portal tags are indexed and made available for search. The other
special symbol you can use is the mentions symbol (`@`). When you are writing
a microblog post, typing the `@` symbol brings up a list of connections to
select. Once you select a connection and publish your post, the connection's
username becomes a link to their Social Office profile page. This is the general
behavior of clicking on a username throughout Social Office. The Timeline tab of
the Microblogs portlet chronologically displays all the status posts that you
have permission to see. The Mentions tab displays all the posts in which you've
been mentioned.

![Figure 1.15: Microblogs allow users to broadcast their thoughts to a chosen
audience.](../../images/microblogs.png)

Note that there are two different portlets for accessing the microblogs system:
the Microblogs portlet and the Microblogs Status Update portlet. When placed on
private pages, both portlets allow you to update your mircoblogs status. The
Microblogs portlet also allows you to view your Microblogs Timeline and Mentions
tabs. On public pages, these portlets display microblogs but don't allow you to
publish new posts. You wouldn't want to just anyone to publish microblogs under
your name, would you?

Social Office's private messaging system allows you to send email-like messages
to other users within the portal. Its interface is similar to that of an email
client. You can select messages to mark them as read or unread or mark them for
deletion. Use the *New Message* button to compose a new message. Just like with
email, you can enter a subject, a message body, and add attachments. Open the
dropdown menu next to the *To* field to select a user or users to send your
message to. You can select any number of users.

![Figure 1.16: The Private Messaging Interface functions just like an email
client for use within a Social Office
instance.](../../images/private-messaging.png)

Social Office provides a private document library to each Social Office user.
Users can navigate to it by clicking the *My Documents* link in the Dashboard
navigation menu. The Documents and Media portlet on the My Documents page is
exactly the same as the standard Documents and Media portlet that ships with
Liferay Portal. Please refer to chapter 4 of Liferay's User Guide for
information about document management: [Document
Management](https://www.liferay.com/documentation/liferay-portal/6.1/user-guide/-/ai/documents-and-med-1)

![Figure 1.17: The Documents and Media portlet on the My Documents page is
exactly the same as the standard Documents and Media portlet that ships with
Liferay Portal. To optimize the Social Office user experience, set up your users
with Liferay Sync.](../../images/so-document-library.png)

With their private document libraries, each of your Social Office users can
maintain a personal online repository of documents and files. Your users can use
all the features of Liferay 6.1's revamped document library, including document
previews, document types, metadata sets, and version history. However, your
users can simplify the way they access their document repositories by installing
Liferay Sync. Liferay Sync allows users to view and edit documents offline using
their regular desktop or mobile environments. Users' documents are synced with
your Liferay server at periodic intervals. You can read more about Liferay Sync
in chapter 4 of Liferay's User Guide here: [Liferay
Sync](https://www.liferay.com/documentation/liferay-portal/6.1/user-guide/-/ai/lp-6-1-ugen04-liferay-sync-0).

The Tasks system allows you and your team members to keep track of different
tasks that you're working on. Tasks related to specific projects can be tagged
as such and you can use the *Filter* button at the top right corner of the
portlet to filter tasks by tag or by site. Use the *Add Task* button at the top
left to create new tasks. You can enter a description of the task, choose an
assignee from your contacts (or assign to yourself), select a priority, and,
optionally, enter a due date. It's also helpful, especially when there are large
numbers of tasks, to tag your tasks. 

![Figure 1.18: The Tasks portlet lets you create tasks and assign them to
yourself or others. You can view tasks that have been assigned to you and tasks
that you've created.](../../images/tasks-portlet.png)

The *Permissions* button at the top left of the Tasks portlet lets you choose
which roles grant permission to add new tasks or edit existing ones.

### Using the Profile Page and Other Public Pages 

You can access your public Profile page from the Dashboard by clicking on your
name, which appears above the page navigation menu in the left column. Your
public pages are laid out and styled the same way as the Dashboard and the rest
of the private pages. Users' default public sites contain three pages,
accessible from the left-column navigation menu:

- Profile
- Contacts
- Microblogs

In addition to the navigation menu, the left column of each page of a Social
Office user's public site contains a My Contacts portlet. This portlet allows
viewers of a user's public site to see all the user's contacts and lets them
navigate to any of the contacts' Profile pages.

![Figure 1.19: Users' Profile pages show relevant professional information,
contacts, and status updates.](../../images/profile-page.png)

By default, the Profile page displays several pieces of information about
a user: their profile and profile picture, their latest microblog status, their
contacts, the sites they belong to, their tags, and their recent activities. The
Profile portlet is designed to display relevant professional information about
a user. A completed profile includes the following pieces of information:

- Introduction
- Phones
- Addresses
- Tags
- Email Address
- Websites
- Social Network
- SMS (Chat information)

There's also a button which allows you to export the user's vCard. Once you've
been granted a Social Office account, you need to access your own profile page
and fill out your profile information. If you're viewing your own Profile page,
there's an *Edit Profile* button which opens up your *My Account* page of the
control panel in a dialog box. Currently, you need to access your *My Account*
page of the control panel to update your profile picture.

If you're viewing another user's Profile page, the *Edit Profile* button is
replaced with buttons for adding or removing the user as a connection, following
or unfollowing a user, and blocking or unblocking a user. In addition to the
Profile, Microblogs Status Update, and Activities portlets, the Profile page also
contains Sites and Tags portlets to help with navigation. Note that when placed
on a public page, the Microblogs Status Update portlet does not allow you to
make updates--it just shows the latest status update. You have to make status
updates from your private site.

The public Contacts and Microblogs applications have their own public pages.
These function the same way as the ones in a user's private site except that
other users can only view, not edit them. However, the Timeline tab of the
Microblogs portlet allows users to comment on entries.

## Using Social Office Sites for Collaboration 

Social Office not only provides custom pages and applications for users'
personal sites but also for general sites that can have many members. A portal
administrator can use the *Add Site* button of the Sites portlet to add a new
site to use for team collaboration. This button opens a wizard which guides you
through the process of creating a site based on the *Default Social Office Site*
template. Sites based on this template come pre-built with pages and
applications designed for team collaboration, just like the default Social
Office personal sites come with applications designed to facilitate user
interaction. The default pages created by the wizard include the following:

- *Home*
- *Calendar*
- *Documents*
- *Forums*
- *Blog*
- *Wiki*
- *Members*

When creating a site, you can select a membership type: Open, Restricted, or
Private. Any portal user can join an open site. Portal users can request to join
a restricted site but must be approved by a site administrator. Private sites
are invisible to non-members; members must be invited by a site administrator.
By default, all the pages of an open Social Office site are public but
restricted and private Social Office site pages are private.

![Figure 1.20: The Social Office site wizard lets you quickly and easily set up
sites for team collaboration.](../../images/new-site-wizard1.png)

The Add Site wizard lets you choose which of default template pages you'd like
to include in your site. Links to these pages are embedded in the left-most
column of each page that belongs to the site.

![Figure 1.21: You can choose a site template to use to create your new site and
you can choose which template pages to
include.](../../images/new-site-wizard2.png)

When visiting an open Social Office site, users can use the *Join* link at the
top of the page to join the site. This link, along with the *My Sites* portlet
on the Dashboard, makes it very easy for Social Office users to manage their
site memberships. This, in turn, helps them keep abreast of the activities and
content that they're interested in. The *Join* link is replaced by a *Request
Membership* link for restricted sites. When users have requested membership for
a restricted site, the *Request Membership* link is replaced by a *Membership
Requested* notification to inform users that their request is pending.

![Figure 1.22: The *Join* link appears next to the Social Office logo at the top
of the page for Social Office sites.](../../images/join-site-link.png)

The Home page of the default Social Office site contains a number of useful
portlets. The Activities and Recent Downloads portlets keep site members up to
date about what's happening on the site and you can use the Announcements and
Events portlets to inform or remind site members about important information.
The Bookmarks portlet allows users to create a collection of useful links on
your site and you can configure the RSS portlet to display an RSS feed of your
choice. The Welcome portlet allows you to set up a custom welcome message for
your site. It's just a WYSIWYG portlet with its title changed to *Welcome*.

The Calendar, Documents, Forums, Blog, and Wiki pages contain portlet
applications that are automatically scoped for the site. This means that these
applications will each use a single data set to serve requests from multiple
members. So, while each Social Office member receives a personal Calendar
application and Documents and Media library on his or her personal site, site
members share Calendar applications and Documents and Media libraries for each
site they belong to. For more information about these applications, please refer
to Liferay's user guide, *Using Liferay Portal*, available at
[http://www.liferay.com/documentation/liferay-portal/6.1/user-guide](http://www.liferay.com/documentation/liferay-portal/6.1/user-guide). 

![Figure 1.23: Social Office's sites come with pre-build pages and applications
that facilitate team-based collaboration.](../../images/social-office-site.png)

In addition to the Blogs portlet, the Blogs page contains a Related Content
portlet. This portlet has a large number of possible configurations. By default,
it dynamically displays content belonging to the scope of the current site. The
Members page just contains a members portlet and an invitation portlet which can
be used to invite users to join the site.

Here's an example of how it all comes together: Ryan, Jim, and Michael are all
members of the Sales Site. Ryan schedules a meeting for the whole Sales team
about a new product launch and puts it on the Calendar. When Jim and Michael log
in, they both receive a notification of the meeting. Jim can't make the meeting,
but he's been maintaining the Sales Wiki for new products, so he asks Michael to
update it for him after the meeting.

Michael updates the Wiki with the new information after the meeting, and Ryan
posts a summary of what happened in the meeting in the Sales Blog. When Jim logs
in the next day, he'll receive a notification of the new Blog post with the
information he missed, as well as a notification about the updated Wiki.
Additionally, he can easily access those pages to catch up on what he missed.
This works because Social Office automatically keeps everyone up to date on the
activities of the teams they work with every day.

## Social Office EE Features 

Social Office EE can only be installed on a Liferay EE instance; it's not
possible to install Social Office EE on Liferay CE. Social Office EE provides
several useful features in addition to the ones provided by Social Office CE.
These additional features include the following:

- A Social Office User management tool that provides an easier way to assign
  users to the Social Office User role
- Enhanced auditing tools for the Documents and Media library
- The ability to mount Sharepoint and Documentum repositories to the Documents
  and Media library.
- The ability for Liferay Sync users to sync with multiple Social Office sites
- Bug fixes and usability improvements since Social Office 2.0 CE GA2
- Access to Liferay Support

After installing Social Office EE to your Liferay EE instance, you'll be
directed to the following screen when you log in as an administrator:

![Figure 1.24: The Social Office Configurations page of the Control Panel
provides a quick and easy way to assign the Social Office User role to users,
organizations, or user groups.](../../images/so-ee-configurations.png)

This page allows you to assign the Social Office User role to users,
organizations, or user groups. If you aren't immediately sure who should be
assigned to the Social Office User role, you can come back to this page later.
It's called Social Office Configurations and it appears at the bottom of the
Portal section of the Control Panel.

Other features provided by Social Office EE include auditing improvements to the
Documents and Media library. Once you've installed Social Office EE, you'll find
the option to specify either a major or a minor revision each time you edit or
check in a document. You'll also be prompted to enter a change log message.
Without Social Office EE, the version number will be automatically incremented
when a file is edited but there's no way to specify a major/minor revision or
enter a change log message.

![Figure 1.25: Social Office EE allows you to specify a major or minor revision
and enter a change log message each time you edit or check in a
document.](../../images/so-ee-doclib-checkin-screen.png)

Additionally, Social Office EE allows comments to be added to specific versions
of a document. Without Social Office EE, there's just a general comments area
for each document that's not specific to any version.

![Figure 1.26: Social Office EE allows you to comment on particular versions of
files in the Documents and Media
repository.](../../images/so-ee-doclib-comments.png)

If you've been using Social Office CE and upgrade to EE, any comments that were
added when you were using CE will appear in a section entitled *Previous
Comments*.

![Figure 1.27: Social Office EE allows you to specify a major or minor revision
and enter a change log message each time you edit or check in a
document.](../../images/so-ee-doclib-previous-comments.png)

Next, let's look at Social Office's upgrade process.
