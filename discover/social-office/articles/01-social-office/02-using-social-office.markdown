# Using Social Office [](id=using-social-office)

Social Office adds functionality to Liferay Portal for a specific
purpose: collaboration. We've designed it for work groups needing to collaborate
on documents and data in the office. The additional tools and defaults were
carefully chosen to provide users working within a group the ability to easily
communicate, collaborate, and coordinate their tasks.

Social Office allows you to quickly set up sites designed to facilitate 
collaboration. Each site is designed to provide a single group or team with a 
virtual shared workspace. Members of your organization who belong to multiple 
groups or teams in your organization can belong to multiple Social Office sites 
on the portal. Social Office also provides each user with customized sets of 
public and private pages for their personal sites. These personal sites serve as 
virtual individual workspaces. The applications they include help users manage 
their tasks, keep informed about each other's activities, and communicate with 
each other.

The Social Office theme is used to style Social Office pages, including the
private pages of users' personal sites, their public pages, and the pages of
Social Office sites. The Social Office theme is mobile-responsive so users using
smartphones or tablets can easily use the site. The Social Office theme adds a
convenient button to the top-right corners of Social Office pages. This button
allows you to expand the portlets on the page to fit the size of your browser
window. You can click the button again to return the portlets to their default
size. The Social Office theme provides two colors theme: Classic and Strata. The
Classic color theme provides a white background while the Strata color theme
provides some solid background colors. The Social Office welcome theme that was
included with Social Office 2.x has been replaced by the regular Social Office
theme in Social Office 3.x.

## The Social Office User Role

Social Office adds a special role to Liferay Portal: Social Office User.
Assigning users to the Social Office User role provides them with personal sites
pre-built with pages and applications designed to help them manage their work,
share information, and communicate with each other. Social Office users also
receive a user bar that appears at the top of all portal pages when they're
logged in (more on the user bar later).

After installing Social Office to your Liferay instance, you'll be directed to
the following screen when you log in as an administrator:

![Figure 1.1: The Social Office Configurations page of the Control Panel provides a quick and easy way to assign the Social Office User role to users, organizations, or user groups.](../../images/so-ee-configurations.png)

This page allows you to assign the Social Office User role to users,
organizations, or user groups. If you aren't immediately sure who should be
assigned to the Social Office User role, you can come back to this page later.
It's called Social Office Configurations and it appears at the bottom of the
Configuration section of the Control Panel. The Social Office Configurations 
portlet was an EE-only feature of Social Office 2.x but it's become a CE-feature 
as well in Social Office 3.x.

Assign yourself to the Social Office User role to see how it works.
Once your user account has been granted the Social Office User role, the user
bar appears above the Dockbar. The user bar contains links to all the private
pages of the user's personal site, including a link to the *Dashboard*, the
private home page of a Social Office user's personal site. The default personal 
pages provided by the Social Office user role include the private Dashboard page 
and the public Profile page. Once you've been assigned to the Social Office User 
role and you see the user bar, you can explore the extra features added to 
Liferay by Social Office.

If all your portal users will be using Social Office, you should add this role
to your portal's default user associations. Use the *Admin* menu of the Dockbar
to navigate to the Control Panel. Under the Configuration heading, click on 
*Portal Settings*. Then click on *Users* from the right-hand navigation menu. 
Finally, open the *Default User Associations*, add the role 
*Social Office User*, and click *Save*. Now, newly created users will be 
assigned to the Social Office User role. If you've been using Liferay but have 
just installed Social Office, you can check the *Apply to Existing Users* box to 
grant the Social Office User role to previously created users. These users will 
receive the role the next time they log in to the portal.

## The Social Office User Bar

When a user who has been assigned to the Social Office User role logs in, the 
user bar appears in place of the Dockbar.

![Figure 1.2: Social Office users receive a user bar that's accessible from all portal pages. The user bar appears in place of the Dockbar and allows for easy portal navigation, especially to the private pages of users' personal sites.](../../images/user-bar-dockbar.png)

The *Dashboard* link appears as the left-most item of the user bar. Links to the 
other private pages of the user's personal site appear next. You can click on 
these links from anywhere in the portal to access your virtual private 
workspace. Also, the *My Sites* link is moved to the user bar, where it's 
renamed to *Go to*. Instead of simply allowing the user to navigate to one of 
the sites he or she belongs to, the *Go to* menu provides access to the Sites 
portlet. This means that Social Office users can not only use the *Go to* menu 
to navigate to sites they belong to but they can also search for sites, filter 
the sites in the list (*All Sites*, *My Sites*, *My Favorites*), add sites to 
their favorites list, access the Sites Directory, and access the Add Site 
wizard.

The Sites portlet is an improved version of the basic My Sites portlet. It
allows you to star various sites to customize your sites list. You can select
*All Sites* to display all the sites in the portal, *My Sites* to display all
the sites you belong to, or *My Favorites* to display your custom list of
starred sites. Beginning in Social Office 3.1, the Sites portlet also remembers 
your selection. This is very useful if you frequently use one of the selections. 
For example, if you have a favorite group of sites that you have starred, 
selecting *My Favorites* will make that the default selection in the Sites 
portlet as you navigate through Social Office. The option to star sites is also 
very useful for large Social Office instances with web applications and content 
hosted across many different sites. There's also a search bar which you can use 
to display sites related to your search query. If you have permission to add 
sites, you'll see an *Add Site* button which opens a wizard to guide you through 
the site creation process. This wizard will create a new site based on the 
*Default Social Office Site* template. This site comes pre-built with pages and 
applications designed for team collaboration. The *Sites Directory* button opens 
a site directory dialog box which you can use to join, leave sites, or star 
sites. If you have the required permissions, you can also delete sites.

![Figure 1.3: To access the Sites portlet, just click on *Go to* from the user oar. The Sites portlet lets you customize your sites list and create new Social Office sites with a simple wizard.](../../images/sites-portlet.png)

If you are an administrator, the next item on the user bar is the *Admin* menu. 
This is the same Admin menu that appears in the Dockbar of a regular Liferay 
Portal installation.

Next, a *Notifications* icon is added to the right of the *Go to* menu. The
Notifications icon displays how many unread notifications and requests you have. 
You'll be notified upon receiving a private message, an invitation to join a 
site, a social connection request, or an event reminder. Alerts and 
announcements created via the Alerts or Announcements portlets are also 
accessible via the Notifications icon. Clicking *Mark as Read* clears 
notifications from the list, but leaves each request for you to respond to 
individually. Clicking *Notifications* or *Requests* lets you view further 
details about each item you've received and lets you configure your preferences 
for receiving notifications and requests.

+$$$

**Note:** Prior to Social Office 3.1, requests and notifications were handled 
together. As of Social Office 3.1, they are handled separately.

$$$

![Figure 1.4: You'll receive a Social Office notification upon receiving a private message, an invitation to join a site, a social connection request, or an event reminder.](../../images/so-notification.png)

The *My Account* icon is also moved from the Dockbar to the user bar. When you
click on your name or profile picture in the user bar, four options appear:

- *My Profile*: takes you to your public Profile page. The Profile page is the
  public home page of a Social Office user's personal site.
- *My Dashboard*: takes you to your Social Office Dashboard, where you can keep 
  up to date with various kinds of portal activity.
- *My Account*: takes you to the *My Account* section of the Control Panel where
  you can edit the details of your user account, such as your screen name, email
  address, password, etc.
- *Sign Out*: logs you out of the portal.

Now that we've learned how to use the Social Office user bar and seen how it
works with the Dockbar, let's learn how to use the Social Office Dashboard and
other private pages and their applications.

## Using the Dashboard and Other Private Pages 

The private page sets of Social Office users serve as personal workspaces. Each
private page displays a navigation menu in the left column so that you can
navigate back and forth between the private pages of your personal site or
navigate to your public pages. To access the My Sites portlet, just click on the
*Go to* menu in the user bar. The private pages provided by Social Office
include:

- Dashboard
- Contacts Center
- Microblogs
- Messages
- My Documents
- Tasks

![Figure 1.5: The Dashboard navigation menu contains links to each private page of your personal site as well as a link to your public Profile page. Clicking the Social Office logo takes you to your Dashboard.](../../images/dashboard-menu.png)

### The Dashboard

The Dashboard page provides several portlet applications designed to keep you up
to date with various kinds of portal activity:

- Announcements
- Activities
- Upcoming Tasks
- Events
- Microblogs Status Update

![Figure 1.6: The default Dashboard applications are designed to inform you about recent portal announcements, activities and upcoming events, and to help you manage your personal tasks.](../../images/dashboard-applications.png)

#### The Announcements Portlet

The Announcements portlet lets you broadcast important information to different
groups of users. Each announcement can be created in a specific scope so that
you can manage which announcements are sent to which users. For more information
about using the Announcements portlet, please refer to the Liferay [User Guide](https://www.liferay.com/documentation/liferay-portal/6.2/user-guide/-/ai/sending-alerts-and-announcements-in-you-liferay-portal-6-2-user-guide-08-en).
In Social Office, announcements created with the priority *Important* are
displayed in red while announcements with the *Normal* priority are displayed in
yellow. The default scoping of the displayed announcements in Social Office
depends on the site of the Announcements portlet. By default, the Announcements
portlet on the Dashboard shows an aggregated view of all general announcements.
An Announcements portlet on a page of a Social Office site shows site-specific
announcements as well as general announcements. Viewing announcements also
behaves a bit differently in Social Office than in a standard Liferay portal.
Clicking *Mark as Read* completely hides announcements from view. Clicking *Read
Entries* reveals them again.

#### The Activities Portlet

Using the power of Liferay's Social API, the Activities portlet presents
information about different categories of users' recent activities. Use the tabs
to display portal activities from different scopes:

- *All*: shows all activities.
- *Connections*: shows a list of activities of users with which you have
  established a social connection.
- *Following*: shows activities of users you're following.
- *My Sites*: shows all the activities from the sites of which you're a member.
- *Me*: shows only your own activities.

The Activities portlet consolidates related activities into a single activities
entry so the activities feeds aren't spammed with nearly identical messages. For
example, users often make several updates to an asset in quick succession, like
this:

    Bryan Cheung updated a wiki page in Sales Team. 1:48 PM
    Pricing
    Bryan Cheung updated a wiki page in Sales Team. 1:47 PM
    Pricing
    Bryan Cheung updated a wiki page in Sales Team. 1:47 PM
    Pricing
    Bryan Cheung updated a wiki page in Sales Team. 1:46 PM
    Pricing
    Bryan Cheung updated a wiki page in Sales Team. 1:46 PM
    Pricing

The Activities portlet consolidates entries like these so that only a single
message is displayed, like this:

    Bryan Cheung made 5 updates to a wiki page in Sales Team. 1:46 PM
    Pricing

For assets that allow commenting, such as blog entries, wiki articles, calendar
events, tasks, and documents and media files, the Activities portlet allows
users to comment on the assets directly in the activities feed. Such comments 
are translated directly as comments on the asset. Comments on *groups* of 
activities are not translated as comments on the individual assets; these 
comments appear only in the activities feed. Forum-related activities do not 
allow comments; users have to click the link to the thread to leave a response. 
Since activities feeds can quickly become heavily populated, users can simply 
scroll to the bottom of a page with the Activities portlet and the Activities 
portlet loads more activities.

![Figure 1.7: The Activities portlet shows activities from the user, sites, and other users.](../../images/activities-feed.png)

#### The Upcoming Tasks, Events, and Microblogs Status Update Portlets

The Upcoming Tasks portlet displays a list of tasks that have been created for
you or that you've assigned to yourself. The Events portlet integrates with the
Calendar portlet belonging to your scope to show upcoming events. By default, an
event appears only ef you've accepted the event and it is scheduled to occur
within one day. You can configure events to be displayed up to five days ahead
in the Calendar portlet configuration. You can also configure the maximum number
of events to show. Access the configuration by clicking the gear icon at the top
right of the portlet and then selecting *Configuration*.

![Figure 1.8: Upcoming tasks and events shows you what's on your plate.](../../images/upcoming-task-events.png)

There's also a Microblogs Status Update portlet on your Dashboard that lets you 
broadcast a Facebook-like status to the scope of your choice: to everyone, to 
your connections, or to your followers. Beginning with Social Offce 3.1, you can 
also control whether or not your latest status update appears on your Dashboard. 
To do so, first make sure the Edit Controls are turned on by clicking the *Gear* 
icon next to the search bar and toggling on the Edit Controls in the bar that 
appears on the left side of your screen. Then mouse over the Microblogs Status 
Update portlet, click the *Wrench* icon, and select *Configuration* from the 
drop-down menu. Uncheck the box for *Display Most Recent Status* if you want 
your most recent status update to be hidden on your dashboard. Also, users can 
interact with microblogs status updates wherever they're displayed, such as in 
an Activities portlet. There are a few possible microblogs interactions: you can 
click a link to view the full microblog post, comment on the post, or repost the 
microblog. Next, let's take a look at the applications provided on the other 
private pages of Social Office users' personal sites.

### The Contacts Center

The Contacts Center is an important application for Social Office because it
allows users to create LinkedIn-style connections and Twitter-style followers.
This enables users to customize who they share information about social
activities with and whose activities they're interested in. Note that connecting
with another user forms a two-way relationship whereas following a user forms a
one-way relationship. For example, if you're connected with another user then
you'll see that user's activities in your Activities feed and that user will see
your activities in their Activities feed. Since connections are two-way
relationships, initiating a connection requires approval from the other user. If
you're only following a user, then you can still see their activities in your
feed, but they will not see yours. Also, connections are reflected in other
portlets beyond the Activities feed. For example, the Tasks and Chat portlets
can be configured to only show those items that belong to your connections.

The Contacts Center application displays a list of portal users. You can choose 
to display *All*, *Connections*, *Following*, *Followers*, or *My Contacts*. If 
you are a member of a site, you can also choose to display only the members of 
that site. There's also a search bar so you can search for specific users. The 
Contacts Center can store contacts that aren't portal users, making it a great 
one stop shop for all of your contacts. To add a new external contact, just 
click on the *Add Contact* button at the top of the Contacts Center.

+$$$

**Note:** The option to view your followers in the Contacts Center was 
introduced in version 3.1 of Social Office. Earlier versions do not have this 
option.

$$$

To view details about a contact or user, click on the user from the left-hand
area of the Contacts Center portlet. For each user, you'll have the following
options:

- *Connect/Disconnect* sends a connection request to or disconnects you from
  the selected user.
- *Follow/Unfollow* subscribes and unsubscribes you to the selected user's
  activities stream.
- *Block/Unblock* allows you to block and unblock users from viewing your
  activities stream. Blocking a user only prevents that user from following you
  or adding you as a connection. A blocked user can still send messages to and
  view the public profile information of the blocking user. 
- *Message* lets you send a private message to the user.
- *vCard* lets you export a user's vCard and save it as a VCF file. vCard
  is a file format standard for electronic business cards.
  
Clicking the user's name takes you to their profile page.

![Figure 1.9: The Contacts Center allows you to search for users, follow users Twitter-style, and set up Linked-In style connections.](../../images/contacts-center2.png)

The Contacts Center also lets you select multiple users via the checkboxes to 
the left of each user. Doing so presents you with the same buttons listed above. 
This is a great time saver for when you want to manage your interactions with 
several users at once.

### Microblogs

Social Office's Microblogs application allows you to broadcast your thoughts as
a Facebook-like status to a chosen audience. You can choose for your status to
be viewable by *Everyone*, *Connections*, or *Followers*. There are two special
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

![Figure 1.10: Microblogs allow users to broadcast their thoughts to a chosen audience.](../../images/microblogs2.png)

Note that there are two different portlets for accessing the microblogs system:
the Microblogs portlet and the Microblogs Status Update portlet. When placed on
private pages, both portlets allow you to update your mircoblogs status. The
Microblogs portlet also allows you to view your Microblogs Timeline and Mentions
tabs. On public pages, these portlets display microblogs but don't allow you to
publish new posts. You wouldn't want to just anyone to publish microblogs under
your name, would you?

### Private Messaging

Social Office's private messaging system allows you to send email-like messages
to other users within the portal. Its interface is similar to that of an email
client. Messages are aggregated by thread, with the most recent message
displayed. You can select messages to mark them as read or unread or to delete
them. Use the *New Message* button to compose a new message. Just like with
email, you can enter a subject, a message body, and add attachments. Click in 
the *To* field and a list of users appears that lets you select who to send your 
message to. You can select any number of users.

![Figure 1.11: The Private Messaging Interface functions just like an email client for use within a Social Office instance.](../../images/private-messages-inbox.png)

By default, all portal users are available in the *To* field of the Private
Messaging portlet. You can filter the set of available users by social
connection status or site membership. However, this configuration must be done
by a server administrator since this configuration must be made to the Private
Messaging portlet before it's deployed.

To filter users, create a `portlet-ext.properties` file to override some
properties of your Private Messaging portlet's `portlet.properties` file. Don't
modify your Private Messaging portlet's `portlet.properties` file directly. It's
a best practice to override it instead of modifying it.

Before you deploy your Private Messaging portlet, extract it to a location of
your choosing on your file system. Create the `portlet-ext.properties` file in
the `private-messaging-portlet/WEB-INF/src` directory. It gets copied over to
your `private-messaging-portlet/WEB-INF/classes` directory upon deployment. When
you're finished making changes to your `portlet-ext.properties` file, zip the
directory structure back into a `.war` file. Once, you've zipped the Private
Messaging portlet back up, it's ready to be deployed. Note that the Private
Messaging portlet must be redeployed in order for the settings in the
`portlet-ext.properties` file to take effect. 

The property that allows you to refine the users that appear in the *To* field
of a message is `autocomplete.recipient.type`. The possible settings for this
property are listed here:

		autocomplete.recipient.type=all
		autocomplete.recipient.type=connection
		autocomplete.recipient.type=site

The default value is `all`. To only make social connections available in the
*To* field of a message, set the property to `connection`. Setting the property
to `site` restricts the users available in the *To* field of a message to fellow
site members. You can also further refine the `site` setting by using the
property `autocomplete.recipient.site.excludes`. This property allows you to
exclude specific sites in your portal from the
`autocomplete.recipient.type=site` setting. This is especially useful if you
have a default site that all portal users belong to, but you still want to
filter the users available in the *To* field of a message by site. For example,
if you want to exclude a site named *Default*, enter
`autocomplete.recipient.site.excludes=Default`. That site is then ignored when
determining the users that are available in the *To* field of a message.

### Private Document Library

Social Office provides a private document library to each Social Office user.
Users can navigate to it by clicking the *My Documents* link in the Dashboard
navigation menu. The Documents and Media portlet on the My Documents page is
exactly the same as the standard Documents and Media portlet that ships with
Liferay Portal. Please refer to [Liferay's User Guide](https://www.liferay.com/documentation/liferay-portal/6.1/user-guide/-/ai/document-manageme-6) for
information about document management.

![Figure 1.12: The Documents and Media portlet on the My Documents page is exactly the same as the standard Documents and Media portlet that ships with Liferay Portal. To optimize the Social Office user experience, set up your users with Liferay Sync.](../../images/so-document-library.png)

With their private document libraries, each of your Social Office users can
maintain a personal online repository of documents and files. Your users can use
all the features of Liferay's document library, including document previews, 
document types, metadata sets, and version history. However, your users can 
simplify the way they access their document repositories by installing Liferay 
Sync. Liferay Sync allows users to view and edit documents offline using their 
regular desktop or mobile environments. Users' documents are synced with your 
Liferay server at periodic intervals. You can read more about Liferay Sync in 
Liferay's User Guide here: [Liferay Sync](https://www.liferay.com/documentation/liferay-portal/6.1/user-guide/-/ai/lp-6-1-ugen04-liferay-sync-0).

### The Tasks Portlet

The Tasks system allows you and your team members to keep track of different
tasks that you're working on. Tasks related to specific projects can be tagged
as such and you can use the *Filter* button at the top right corner of the
portlet to filter tasks by tag or by site. Use the *Add Task* button at the top
left to create new tasks. You can enter a description of the task, choose an
assignee from your contacts (or assign to yourself), select a priority, and,
optionally, enter a due date. It's also helpful, especially when there are large
numbers of tasks, to tag your tasks. To resolve a task, select it, then click 
the *Resolve* button. A notification that the task has been resolved is sent to
the creator of the task. By default, only unresolved tasks will appear in the
Tasks portlet. Click the checkbox next to *Show Completed Tasks* to view all
tasks, regardless of the resolution status. The Permissions button at the top
left of the Tasks portlet lets you choose which roles grant permission to add
new tasks or to edit existing ones. This button only appears if you have
permission to set permissions for tasks.

![Figure 1.13: The Tasks portlet lets you create tasks and assign them to yourself or others. You can view tasks that have been assigned to you and tasks that you've created.](../../images/tasks-julie.png)

In order to create tasks for a site, a Tasks portlet must first be added to a
page of that site. Tasks created through a Tasks portlet on a site page are
automatically scoped to that site. When you view a task in the Tasks portlet,
its site is displayed.

## Using the Profile Page and Other Public Pages 

You can access your public Profile page from the Dashboard by clicking on your 
profile picture, which appears above the page navigation menu in the left 
column. Your public pages are laid out and styled the same way as the Dashboard 
and the rest of the private pages. Users' default public sites contain three 
pages, accessible from the left-column navigation menu:

- Profile
- Contacts
- Microblogs

![Figure 1.14: Users' Profile pages show relevant professional information, contacts, and status updates.](../../images/joe-profile.png)

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
and fill out your profile information. On your own Profile page, clicking on the
area to the right of your name and email address brings up the *Details* pop-up.
Here you can change your screen name, email address, first name, middle name,
last name, job title, and profile picture. You can also change your profile
picture by clicking the *Change* button that appears when you mouse-over your
picture on your profile page.

When viewing another user's Profile page, different options appear. You can add
or remove the user as a connection, follow or unfollow the user, and block or
unblock the user. In addition to the Profile, Microblogs Status Update, and
Activities portlets, the Profile page also contains Sites and Tags portlets to
help with navigation. Note that when placed on a public page, the Microblogs
Status Update portlet does not allow you to make updates--it just shows the
latest status update. You have to make status updates from your private site.

The public Contacts and Microblogs applications have their own public pages.
These function the same way as the ones in a user's private site except that
other users can only view, not edit them. However, the Timeline tab of the
Microblogs portlet allows users to comment on and repost entries. The public 
Contacts application shows users that the current user is connected to.

## Using Social Office Sites For Collaboration 

Social Office not only provides custom pages and applications for users'
personal sites but also for general sites that can have many members. To add a
new Social Office site to use for team collaboration, click on *Go to* from the
user bar to access the Sites portlet. Then click on the *Add Site* button. This
button opens a wizard which guides you through the process of creating a site
based on the *Default Social Office Site* template. Sites based on this template
come pre-built with pages and applications designed for team collaboration, just
like the default Social Office personal sites come with applications designed to
facilitate user interaction. The default pages created by the wizard include the
following:

- *Home*
- *Calendar*
- *Documents*
- *Forums*
- *Blogs*
- *Wiki*
- *Members*

The Add Site wizard lets you choose a site template to use to create your site's
default pages. You can also choose which of the default template pages you'd
like to include in your site. Links to these pages are embedded in the left-most
column of each page that belongs to the site. You can also select one of the
following four membership types:

- Open: The site is listed, pages are public and users are free to join and
  collaborate.
- Public Restricted: The site is listed, pages are public but users must request
  to join and collaborate.
- Private Restricted: The site is listed, pages are private and users must
  request to join and collaborate.
- Private: The site is unlisted (it's invisible to non-members), pages are
  private, and users must be invited to join and collaborate.

Note that the available site membership types of Social Office sites are
slightly different than those of regular Liferay sites. Regular Liferay sites
have only three available membership types: Open, Restricted, and Private. A
Restricted Liferay site has the same membership type as a private restricted
Social Office site. The Public Restricted Social Office site membership type has
no regular Liferay site equivalent. The default pages of an open Social Office
site are public, but those of restricted and private Social Office sites are
private.

![Figure 1.15: The Social Office site wizard lets you quickly and easily set up sites for team collaboration. You can choose a site template to use to create your site's default pages, choose which template pages to include, and choose the type of your new site.](../../images/new-site-wizard1.png)

Social Office 3.1 gives administrators more control over site creation by users. 
Administrators can control the types of sites that can be created by Social 
Office users, or whether users can create sites at all. These settings can be 
accessed in the *General* tab of Social Office Configurations, which is in the 
Configurations section of the Control Panel. Unchecking the box 
*Social Office users can add sites* prevents Social Office users from being able 
to add sites of any type. When this box is unchecked, the *Add Site* button in 
the Sites portlet doesn't appear to users.

![Figure 1.16: Administrators can control the types of sites that Social Office users can create, as well as their ability to create sites at all.](../../images/so-site-restrictions.png)

When visiting an open Social Office site, users can use the *Join* link at the
top of the page to join the site. This link, along with the *My Sites* portlet
on the user bar, makes it very easy for Social Office users to manage their
site memberships. This, in turn, helps them keep abreast of the activities and
content that they're interested in. The *Join* link is replaced by a *Request
Membership* link for restricted sites. When users have requested membership for
a restricted site, the *Request Membership* link is replaced by a *Membership
Requested* notification to inform users that their request is pending.

![Figure 1.17: The *Join* link appears next to the Social Office logo at the top of the page for Social Office sites.](../../images/join-site-link.png)

### Default Site Pages & Portlets

The Home page of the default Social Office site contains a number of useful
portlets. The Activities and Recent Downloads portlets keep site members up to
date about what's happening on the site and you can use the Announcements and
Events portlets to inform or remind site members about important information.
The Bookmarks portlet allows users to create a collection of useful links on
your site and you can configure the RSS portlet to display an RSS feed of your
choice. The Welcome portlet allows you to set up a custom welcome message for
your site. It's really just a WYSIWYG portlet with its title changed to
*Welcome*. Click on the gear icon of the portlet and then select *Configuration* 
to bring up the WYSIWYG editor. This is the same powerful editor that appears in 
Liferay Portal. You can read about it in the user guide 
[section on managing content](http://www.liferay.com/documentation/liferay-portal/6.2/user-guide/-/ai/features-of-liferays-wcm-liferay-portal-6-2-user-guide-02-en).

The Calendar, Documents, Forums, Blogs, and Wiki pages contain portlet
applications that are automatically scoped for the site. This means that these
applications will each use a single data set to serve requests from multiple
members. So, while each Social Office member receives a personal Calendar
application and Documents and Media library on his or her personal site, site
members share Calendar applications and Documents and Media libraries for each
site they belong to. For more information about these applications, please refer
to Liferay's user guide, *Using Liferay Portal*, available at
[http://www.liferay.com/documentation/liferay-portal/6.1/user-guide](http://www.liferay.com/documentation/liferay-portal/6.1/user-guide). 

![Figure 1.18: Social Office's sites come with pre-built pages and applications that facilitate team-based collaboration.](../../images/social-office-site.png)

In addition to the Blogs portlet, the Blogs page contains a Recent Blogs
portlet. These two portlets are designed to work together. The Blogs portlet
allows users with the appropriate permissions to publish new blog posts. By
default, it also displays the full content of 5 recent posts. The Recent Blogs
portlet, on the other hand, displays a list of recently published blog posts.
The Recent Blogs portlet doesn't show the full content of each post. Instead,
clicking on a post's title or on the *Read More* link in the Recent Blogs 
portlet opens the full content of the post in the Blogs portlet.

Prior to Social Office 2.0, Social Office provided a Related Assets portlet on
the Blogs page. This portlet is no longer provided on the Blogs page by default,
but can easily be added. Open the left menu by clicking the gear icon to the 
right of the search bar, then click *Add* &rarr; *Applications*. Search
for *Related Assets* and then click *Add* next to its name to add it to the 
page. Whenever the Blogs portlet displays the full content of a blog post, the 
Related Assets portlet lists any of the post's related assets. Click on the name 
of any related asset to view the asset. If there are no related assets, the 
Related Assets portlet won't appear at all.

The Members page contains a Members portlet and an Invitation portlet. The 
Invitation portlet is only visible to site administrators. It allows 
administrators to send invitations to users to join the site. Clicking the 
*Invite members to this site* button brings up the interface for sending out 
invitations. Beginning in Social Office 3.1, users that have already been 
invited but have not yet responded are marked as such. By default, the Members 
portlet displays a list of all the members of the site. Using the Members 
portlet's dropdown selector, users can filter the set of displayed site members. 
The available filters include *All*, *Administrators*, *Connections*, and 
*Following*. The *Administrators* filter provides site members with an easy 
way to display entries for site owners and site administrators. This is useful 
for site members who need to contact site administrators. To view a user's 
details, including their email address, click on the user in the Members 
portlet. After clicking on a user, you can send them connections requests, 
follow them, block them, send them private messages, or download their vCards. 
Please refer to the section on the Contacts portlet for more details. 

### A Case Study: The Sales Team

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
