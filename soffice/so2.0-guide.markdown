# Working Smarter with Liferay Social Office

## Preface

Liferay Social Office is Liferay's social collaboration product for the enterprise. Previously released as a separate product based on Liferay Portal, it's now available as a plugin for Liferay Portal. We are excited to bring both Social Office and this book to you, in the hope that you'll be able to use each to enable your teams to work together more efficiently. 

Some time ago, we at Liferay were brainstorming some common use cases which Liferay Portal does a good job solving. One of these is team-based collaboration. Many of our customers have used Liferay Portal both inside their enterprises and outside on the Internet to provide a better way of sharing documents and data with the people they work with everyday. Liferay Portal provides a very flexible solution which allows you not only to do this, but much, much more. Because Liferay Portal is so flexible, the path from the initial install to a robust web site that could be used for collaboration was not always straightforward to the new user. The Social Office plugin is designed to turn Liferay Portal into a product that business users can set up and use right away.

This was not the only reason for Social Office. Internally, we realized that we needed a product like this ourselves, and that we already had all the features in place to make it possible. We just needed to assemble them together in a way that optimally supported the scenario of social collaboration. We also wanted to make setup as easy as possible for new users. 

In this way, Social Office was born. We created the product to provide a simple, out of the box solution for team collaboration. Social Office provides you with a fantastic array of features that you don't have to spend a lot of time configuring. In fact, you can have a full production environment set up in less time than it takes to brew a pot of coffee.

Since Social Office is provided as a plugin for Liferay Portal, it inherits all the benefits of a mature, stable solution which has been in the marketplace for some time. You can start using Social Office's straightforward, out-of-the box collaborative features right away but you're also free to utilize any of the more advanced features of Liferay Portal at any time. 

<!-- This section needs revision
Additionally, we've had an early adopter program, and Social Office is already in production use with multiple customers. It has even received industry recognition: it's in the Gartner magic quadrant for social software, and has been mentioned as the only open source, Java-based solution for collaboration in a comparison review of products in this space. 

Since our early adopter program was so successful, we're excited to finally release the product to the general public. Customers who have already started using it have been exceptionally pleased with the results, and you gain the benefit from our collaboration with those early adopters. In fact, this is one of the great things about open source: we've received extensive feedback on the product, including bug fixes and feature requests. Our customers have told us whether things are intuitive or not, and we've incorporated that feedback right back into the product. 
-->

This book is for those who are setting up and administering an instance of Liferay Portal using Social Office. From installing Social Office to introducing you to its entire feature set, you'll see all of the things Social Office can do to help your team. We hope you'll use it as a tool to get Social Office up, running, and integrated into your enterprise. Since Social Office is provided as a plugin for Liferay Portal, you can find much more information about installation and administration in *Using Liferay Portal* [http://www.liferay.com/documentation/liferay-portal/6.1/user-guide](http://www.liferay.com/documentation/liferay-portal/6.1/user-guide).

<!-- This section can probably be removed
Conventions

Sections are broken up into multiple levels of headings, and these are designed to make it easy to find information. 
Source code and configuration file directives are presented like this. 
If source code goes multi-line, the lines will be \ 
separated by a backslash character like this.
Italics are used to represent links or buttons to be clicked on in a user interface and to indicate a label  or a name of a Java class. 
Bold is used to describe field labels and portlets. 
Page headers denote the chapters, and footers denote the particular section within the chapter. 
-->

## Publisher Notes

It's our hope that this book is valuable to you, and that it's an indispensable resource as you begin to administer a Social Office server. If you need any assistance beyond what's covered in this book, Liferay, Inc. offers training, consulting, and support services to fill any need that you might have. Please see [http://www.liferay.com/services](http://www.liferay.com/services) for further information about the services we can provide. 
As always, we welcome any feedback. If there is any way you think we could make this book better, please feel free to mention it on our forums. You can also use any of the email addresses on our Contact Us page [http://www.liferay.com/contact_us](http://www.liferay.com/contact_us). We are here to serve you, our users and customers, and to help make your experience using Social Office the best it can be. 

<!-- This sections needs updates
Author Notes

Special thanks are due to my co-authors, Stephen Kostas and Michelle Hoshi, who turned around their chapter assignments in record time while the product was changing under them. I also cannot underestimate the help of Ryan Parks in quickly answering our calls for help and in giving us further explanation of various features. And I must also make special mention of Stephen Wilburn, who copy edited this document. I thought the text was pretty clean, but he slogged through our hastily written prose with an attention to detail that made it look like monkeys had typed this thing. If you find this book to be readable, it's because of him. 
As always, we hope this book is useful to you as you begin your journey with Liferay Social Office! 
-->

## Introduction

“Help!” 

This has been the cry of information workers everywhere who have tried to manage complex projects through email and shared folders. You've probably had a similar experience: you're collaborating on a document with a team of people and are emailing it back and forth. Person A creates the initial document and Person B replies with some edits. Person C replies with additional edits at the same time Person A replies with the corrections from Person B. Person D takes this and rewrites a whole section of the document while Person B and C respond to Person A's update. Pretty soon, it becomes very difficult to determine which document is the latest document.

The same scenario plays itself out when working in shared folders. Person A creates a document and puts it in a network share. Person B edits the document and saves it under a different file name. Person C edits it and re-saves the original. Person A tries to reconcile the two, while Person D opens Person B's document and saves yet another version. Before you know it, you have the same problem you had with email: you can't tell which document is the latest document. Additionally, you have a worse problem: you don't have a clear history of how the document got to where it is in the first place. 

“Help!”

Liferay Social Office is a plugin for Liferay Portal that is designed to answer that call for help by meeting the needs of teams who work together on projects. It's easy to set up and makes it easier to facilitate collaboration for large and small businesses alike. With Liferay Social Office, you'll have the system and your workgroups up and running in no time, with all the tools necessary for them to work more efficiently and more easily than before.

## What can you do with Social Office?

Social Office gives you the social collaboration features you need to do virtual teamwork. You can coordinate calendars and schedules, write shared blog entries, work together on documents, discuss issues in forums, document everything in a wiki, and more. The underlying theme is that all discussion and collaboration is kept with the asset. What does this mean? It means that the asset, whether that be a document, a thread, a blog entry, or a wiki article is the star around which all of the discussion and collaboration orbits. 

Trying to collaborate on assets through email or server shares works up to a certain point--but then it fails because it's a backwards way of doing the work. The asset in that scenario becomes secondary, and the “wrapper”--email or the share--becomes the primary target of the work. So instead of concentrating on the asset which the team is trying to produce, the focus turns to the wrapper. In the case of a shared folder, you might manually rename documents according to a numbering scheme so everyone can tell which is the latest--that is, until someone forgets to do so, or until someone accidentally overwrites the latest document. In the case of email, users try to navigate huge threads while passing an asset back and forth--until someone forgets to “reply all” or until someone starts a new thread (and maybe forgets to put the whole list on the thread). Working in the mediums of shared folders or email puts the focus on working with the wrapper, not the asset. And so the asset gets lost because they're forced to focus on the wrapper instead of the asset. With Social Office, you'll be free to concentrate on the assets--the system gets out of your way so that it's a simple matter for your team to work together. 

## How is Social Office Different from Liferay Portal?
  
Social Office is a plugin for Liferay Portal that provides extra collaborative tools and allows you set up sites for collaboration very quickly. Each site is designed to provide a single group or team with a virtual shared workspace. Members of your organization who belong to multiple groups or teams in your organization can belong to multiple sites on the portal.

Liferay Portal is a full featured web portal, containing an industry-leading suite of built-in applications and tools. It's highly customizable and businesses use it to run major web sites all over the world. We've optimized Liferay Portal for creating state-of-the art, robust web sites that serve millions of users every day. 

The Social Office plugin adds functionality for a specific purpose: collaboration. We've designed it for work groups needing to collaborate on documents and data in the office. The included tools and defaults were carefully chosen to provide users working within a group the ability to easily communicate, collaborate, and coordinate their tasks. Additional applications were added to the product to support these goals. 

Whether you're creating a public web site and need the full features of an unparalleled, industry-leading engine or you're creating a small corporate Intranet, Liferay Portal is the ideal solution for you. You should consider installing the Social Office plugin if you'd like your site facilitate communication between people within specific groups and accomplish tasks using shared virtual workspaces with minimal set up and administration.

## Social Office concepts

Social Office is designed around the concept of users and their work. For this reason, everything in Social Office is centered around keeping you up to date on the activities of your teammates. This works not only within individual teams, but also across all the teams of which you're a member. To do this, Social Office gives each user his or her own personal site to serve as a virtual private workspace. Additionally, each team is designed to have its own site where its work is stored. Social Office provides a *default Social Office template* that allows you to quickly build such sites for your teams. Using the power of Liferay's social API, users' activities are tracked and reported to the rest of the team. (Note: the terms *Organization*, *Site*, *User Group*, and *Team*, and *Role* have specific meanings within Liferay Portal--please see *Using Liferay Portal* for details.)

For example, if a user on Team A shares a document, that activity is displayed not only in Team A's activities, but also in each team member's activities. This provides the ability for the rest of the team to know what work is being done as soon as possible, and then they can respond by making their own contributions. 

There are really only three concepts which Social Office uses to accomplish all of this: users, Applications, and sites. Let's take a closer look at each to see how you can use Social Office to help your teams work better. 

### Users

Users are at the core of Social Office. In order to interact with and use Social Office, you must have a user account. Users can register themselves or the system can be set up so that your account is created by an administrator. Administrators have full control over the entire site, can create and delete other users and sites, and can take care of the initial configuration. Administrators can also create additional users with administrative capabilities.

The Social Office plugin adds a special role to Liferay Portal: Social Office User. Assigning users to the Social Office role provides them with personal sites pre-built with pages and applications designed to help them manage their work, share information, and communicate with each other. The default personal sites provided by the Social Office user role includes the Dashboard home page.

Other users are the regular members of your team who use the system to get their day-to-day work done. They can view the Sites they to which they have membership and they can interact with files and documents on those Sites. 
  
### Applications

Users make use of applications every day in Social Office to get their work done. Each page on every site in Social Office is only as useful as the applications installed on it. The available applications range from utilities for online information such as weather forecasts or RSS feeds, to blog and wiki tools, and more. 

By default, each user has a number of applications installed on his or her Dashboard home page. Users have full control over their personal pages: they can add or remove applications from their pages and adjust the settings for available applications. This ability can be modified or removed altogether by administrators. 

When a new Social Office site is created, it's pre-populated with pages and applications on those pages. These pages and applications are defined by the default Social Office site template. In many cases, each application gets a page of its own; in other cases, applications are aggregated together on a single page to provide a unified place where users can see all the new information that their teammates have posted. 

Owners of sites are free to add or remove applications from pages on their sites. Regular users will not have the ability to do so. In this way, site owners can maintain the most optimal working environments for their users. 

### Sites

Sites are the structure that holds everything else in Social Office together. Every page contained in Social Office is part of a Site--including users' personal pages, which are just personal Sites. Sites are, at first glance, a collection of pages with specific functions, but they are also the basis of a sort of user “community.” Users can be members of certain Sites either by assignment or by invitation, and once they are members they can use the full capabilities of the Site, as well as receive notification of various activities.

Sites ultimately become the key to good collaboration in Social Office, because each Site contains its own Calendar and set of events, as well as its own Document Library, Wiki, Blog, and Announcements. These tools enable members of a Site to communicate effectively with each other. Personal Sites help individual users as they log in to see an aggregated list of activities. This list comes from the activities of all the teams with whom they are collaborating. This way, users can see at a glance everything that's happening with their work, and they can respond very easily to anything that needs attention across all of the Sites of which they are members. 

Here's an example of how it all comes together: Ryan, Jim, and Michael are all members of the Sales Site. Ryan schedules a meeting  for the whole Sales team about a new product launch and puts it on the Calendar. When Jim and Michael log in, they both receive a notification of the meeting. Jim can't make the meeting, but he's been maintaining the Sales Wiki for new products, so he asks Michael to update it for him after the meeting.

Michael updates the Wiki with the new information after the meeting, and Ryan posts a summary of what happened in the meeting in the Sales Blog. When Jim logs in the next day, he'll receive a notification of the new Blog post with the information he missed, as well as a notification about the updated Wiki. Additionally, he can  easily access those pages to catch up on what he missed. This works because Social Office automatically keeps everyone up to date on the activities of the teams they work with every day. 

Social Office is a dynamic team collaboration solution for the enterprise that's designed to streamline communication, save time, and raise productivity. Organizations and groups can use Social Office to share resources, collaborate on projects, and raise the visisbility of important activity. Social Office features a robust suite of integrated, easy-to-use web applications. It's ready to use right out of the box. Once installed, a quick setup wizard will help you set up a team collaboration site so you can begin working immediately. Let's take a look at some of Social Office 2.0's new features. Then we'll explain how to install Social Office.

## Installation

Unlike previous versions of Social Office, Social Office 2.0 is available as a plugin for Liferay Portal 6.1. It can be installed via Liferay Marketplace. Prior to version 2.0, Social Office was provided as an independent product that was based on, but separate from, Liferay Portal.

## New Social Office features

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

## Using Social Office personal sites

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

## Using Social Office collaborative sites

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
