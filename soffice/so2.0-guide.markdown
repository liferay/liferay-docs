# Introduction

Liferay Social Office is a dynamic team collaboration solution for the enterprise that's designed to streamline communication, save time, and raise productivity. Organizations and groups can use Social Office to share resources, collaborate on projects, and raise the visisbility of important activity. Social Office features a robust suite of integrated, easy-to-use web applications. It's ready to use right out of the box. Once installed, a quick setup wizard will help you set up a team collaboration site so you can begin working immediately. Let's take a look at some of Social Office 2.0's new features. Then we'll explain how to install Social Office.

## New features

- Social Office 2.0 is deployable on top of Liferay Portal 6.1. This means that your Social Office 2.0 installation includes not only the following new applications but also all the new features of Liferay 6.1. Please see [http://www.liferay.com/web/james.falkner/blog/-/blogs/11927282](http://www.liferay.com/web/james.falkner/blog/-/blogs/11927282) for details about the new Liferay 6.1 features.

<!-- | TODO: Replace the link above with the official one once it's available | -->

- The Dashboard is the default private home page for Social Office users. It's designed to help you manage your personal tasks and communication. The Dashboard features some personal applications such as the microblogs portlet, activities portlet, upcoming tasks portlet, events portlet, site portlet, as well as links to other personal pages and applications.

- Social Office sites can be quickly created and set up based on the *Default Social Office Site* template. If you click on the *Add Site* button on the Dashboard, a wizard pops up to help you create a new Social Office site pre-built with pages and applications.

<!-- | TODO: Can other site templates be set up to use the wizard? Are there any other existing site templates for SO besides the default one? | -->

- Social Office users receive a *Notifications* button on the Dockbar that's integrated with the portal. The Nofications button displays how many unread notifications you have. You'll be notified upon receiving a private message, site invitation, social connection request, or event reminder.

<!-- | TODO: Try to find an exhaustive list, or a least more examples | -->

- The Contact Center can be very important for large organizations or groups using Social Office. It allows users to manage their social contacts the way that LinkedIn or Twitter users can. You can add users as connections, follow users, or even block users.

- The Microblogs portlet allows users to post Facebook-like status to a chosen scope: to all portal users, to connections only, or to followers only. It also contains a Timeline tab which displays all the microblog entries that you have permission to see.

<!-- | TODO: Figure out what the Mentions tab does | -->

- The Dashboard contains an improved Sites portet. Unlike the basic My Site portlet, the improved Sites portlet allows you to star various sites to customize your sites list. You can view choose to view all sites, sites you belong to, or your favorite (starred) sites. The option to star sites can be very convenient for large Social Office instances with web applications and content hosted across many different sites.

## Installation

Social Office is available as a plugin for Liferay Portal 6.1. It can be installed via Liferay Marketplace.

## Using the Dashboard

The Dashboard home page is only available to Social Office users. Note that Liferay Portal identifies users as Social Office users based on whether or not they have been assigned to the Social Office User role. We recommend that you add this role to your portal's default user associations configuration in the control panel. Use the *Go to* menu of the Dockbar to navigate to the Control Panel. Under the Portal heading, click on *Portal Settings*. Then click on *Users* from the right-hand navigation menu. Finally, open the *Default User Associations* tab and add the role *Social Office Users*. Now, newly created users will be assigned to the Social Office User role.

### The Dashboard home page

Social Office users receive a *Dashboard* link on their Dockbars, as well as a *Notifications* button. The Dashboard link connects to your private home page. The Dashboard home page displays a navigation menu and the Sites portlet embedded in the left column. 

![Figure X.x: The Dashboard navigation menu contains links to your public profile page and the other pages of your personal site.](../../images/to-do.png)

The Dashboard's Sites portlet view selector lets you select *All Sites* to display all the sites in the portal, *My Sites* to display all the sites you belong to, or *My Favorites* to display a custom list of sites. You can star various sites in the Sites portlet to add them to your custom list. There's also a search bar which you can use to display sites related to your search query. The *Add Site* button opens a wizard to guide you through the site creation process for a new site based on the *Default Social Office Site* template. This site comes pre-built with pages and applications designed for team collaboration. The *More Sites* button opens a site directory dialog box which you can use to join or leave sites, star sites. If you have the required permissions, you can also delete sites.

![Figure X.x: The Sites portlet lets you create customize your sites list and lets you create new Socical Office sites with a simple wizard.](../../images/to-do.png)

The other applications that you can access on the Dashboard home page, by default, include the Microblogs Status Update portlet, the activities portlet, the upcoming tasks portlet, and the events portlet. The Microblogs Status Update portlet lets you broadcast a Facebook-like status to the scope of your choice: to everyone, to your connections, or to your followers. Note that Social Office users also have access to a separate Microblogs page with a more powerful Microblogs portlet--this portlet can be used for more than just status updates.

![Figure X.x: The default Dashboard applications are designed to help you manage your personal tasks and communication.](../../images/to-do.png)

The Activities portlet can display portal activity from a chosen scope: from your connections, from users you're following, from sites you belong to, or from just yourself. The Upcoming Tasks portlet displays a list of tasks that have been created for you or that you've assigned to yourself. Note that there's also a separate Tasks page with a Tasks portlet that you can use to create tasks. The Events portlet integrates with the calendar portlet belonging to your scope.

### Other private pages

You can use the navigation menu in the left column of the Dashboard to navigate to the other private pages of your personal Social Office site. This left column is embedded into each private page of your personal site so you can use it to navigate back and forth between pages. The Contact Center is a important application for Social Office because it allows users to create LinkedIn-style connections and Twitter-style followers. This enables users to customize who they share social activity information with and whose activity they're interested in. The *Contacts Center* application displays a list of portal users. You can choose to display *All*, *Following*, or *Connections* and you can also seach for specific users. Select a user from the left-hand area of the Contacts Center portlet to view details about him or her. You can choose to add or remove a user as a connection, to follow or unfollow a user, or even to block or unblock a user. You can also export a user's vCard. vCard is a file format standard for electronic business cards.

![Figure X.x: Contacts Centers allows users to follow each other Twitter-style and set up Linked-In style connections.](../../images/to-do.png)

<!-- | TODO: What else do connections and followers do? What exactly does it mean for a user to be blocked? Just appears to you as though that user doesn't exist? No private messages from the blocked user, no activities information about that user? | -->

Social Office's Microblogs application allows you to broadcast your thoughts as a Facebook-style status to a chosen audience. You can choose for your status to be viewable by *All*, *Connections*, or *Followers*. The Timeline tab chronologically displays all the status posts that you have permission to see.

<!-- | TODO: What does the Mentions tab do? | -->

![Figure X.x: Microblogs allow users to broadcast their thoughts to a chosen audience.](../../images/to-do.png)

Social Office's private messaging system allows you send email-like messages to other users within the portal. The Private Messaging portlet displays an email-client like user interface. You can select messages to mark them as read or unread or mark them for deletion. Use the *New Message* button to compose a new message. Just like with an email, you can enter a subject, a message body, and add attachments. Open the dropdown menu next to the *To* field to select a user or users to send your message to. You can select any number of users to send your message to.

![Figure X.x: The Private Messaging Interface functions just like an email client for use within a Social Office instance.](../../images/to-do.png)

The Tasks system allows your team members to keep track of different tasks they're working on. Tasks related to specific projects can be tagged as such and you can use the *Filter* button at the top right corner of the portlet to filter tasks by tag or by site. Use the *Add Task* button at the top left to create new tasks. You can enter a description of the task, choose an assignee from your contacts (or assign to yourself), select a priority, and, optionally, enter a due date. It's also helpful, especially when there are large numbers of tasks, to tag your tasks. The *Permissions* button at the top left of the Tasks portlet lets you choose which roles grant permission to add new tasks or edit existing ones.

<!-- | TODO: Confirm that this is exactly what the Permissions button does | -->

<!-- | TODO: Is the Welcome page just a personal private default page from Liferay Portal that should not appear in Social Office? | -->

### Public pages

You can access your public profile page from the Dashboard by clicking on your name, which appears as the top menu item of the left-column navigation. Users' public pages are laid out and styled the same way as the Dashboard and the rest of the private pages. Users' default public sites contain three pages, accessible from the left-column navigation menu: Profile, Contacts, Microblogs. In addition to the navigation menu, the left column of each page of a Social Office user's public site contains a My Contacts portlet. This portlet allows viewers of a user's public site to see all the user's contacts and lets them navigate to any of their profile pages.

![Figure X.x: User's profile pages show relevant professional information, contacts, and status updates](../../images/to-do.png)

By default, the profile page displays three main pieces of information about a user: their profile, their lastest microblog status, and their recent activities. The profile portlet is designed to display relevant professional information about a user. It always displays the user's name and email address, two descriptive sections labed *About* and *Expertise*, and allows you to export the user's vCard. If you're viewing your own profile page, there's and *Edit Profile* button which opens up your *My Account* page of the control panel in a dialog box. If you're viewing another user's profile page, the *Edit Profile* button is replaced with buttons for adding or removing the user as a connection, following or unfollowing a user, and blocking or unblocking a user. In addition to the Profile, Microblogs Status Update, and Activites portlets, the profile page also contains Sites and Tags portlets to help with navigation. Note that when placed on a public page, the Microblogs Status Update portlet does not allow you to makes updates--it just just shows the lastest status update. You have to make status updates from your private site.

The public Contacts and Microblogs pages and portlets function the same way as the ones in a user's private site except that other users can only view, not edit them. However, the Timeline tab of the Microblogs portlet allows users to comment on entries.

## Using SO Sites

Social Office not only provides custom pages and applications for users' personal sites but also for general sites that can have many members. A portal administrator can use the *Add Site* button of the Sites portlet to add a new site to use for team collaboration. This button opens a wizard which guides you through the process of creating a site based on the *Default Social Office Site* template. Sites based on this template come pre-built with pages and applications designed for team collaboration, just like the default Social Office personal sites come with applications designed to facilitate user interaction. The default pages created by the wizard include *Home*, *Calendar*, *Documents*, *Forums*, *Blog*, *Wiki*, and *Members* pages. Links to these pages are embedded in the left-most column of each page that belongs to the site.

The Home page of the default Social Office site contains a number of userful portlets. You can use the Activities and Recent Downloads portlets to keep site members up to date about what's happening on the site and the Announcements and Events portlets to inform or remind them about important information. The Bookmarks portlets allows users to create a collection of useful links on your site and you can configure the RSS portlet to display an RSS feed of your choice. The Welcome portlet is just a WYSIWYG portlet with its title changed to *Welcome*.

The Calendar, Documents, Forums, Blog, and Wiki pages contain portlet applications that are automatically scoped for the site. This means that these applications will each use a single data set to serve requests from multiple members. So, while each Social Office member receives their own Calendar application and Documents and Media library on their personal sites, they share another Calendar and Documents and Media library for each site they belong to. For more information about these applications, please refer to Liferay's user guide, *Using Liferay Portal*, available at [http://www.liferay.com/documentation/liferay-portal/6.1/user-guide](http://www.liferay.com/documentation/liferay-portal/6.1/user-guide). 

In addition to the Blogs portlet, the Blogs page contains a Related Content portlet. This portlet is highly configurable. By default, it dynamically displays content belonging to the scope of the current site. The Members page just contains a members portlet and an invitation portlet which can be used to invite users to join the site. Next, we'll investigate how to intgrate our Social Office instance with other applications.

## Integrating Social Office with Existing Applications

Many users don't like to use web applications that don't integrate with the workflow they're used to. It's possible to configure existing applications to integrate with Social Office. For example, you don't have to use your browser and the Social Office web interface to access Documents and Media libraries. Social Office takes advantage of Liferay Portal's WebDAV integration to allow users with the appropriate permissions to access Documents and Media libraries. For further information, please see the Documents and Media section of chapter 5 of *Using Liferay Portal*. [http://www.liferay.com/documentation/liferay-portal/6.1/user-guide](http://www.liferay.com/documentation/liferay-portal/6.1/user-guide)

- Microsoft Office Desktop Integration

- Calendar integration

- Email Integration

- IM integration

- Integration of other applications?

- Examples of customization, extensibility of Social Office?
