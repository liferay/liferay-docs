# Introduction to Liferay

This chapter covers the following activities:

-   *Liferay's User Interface:* How to navigate around Liferay

-   *Navigating the Control Panel:* An overview of Liferay's control panel

-   *Portal Architechture:* How Liferay organizes your portal

Liferay is a *portal server*. This means that it is designed to be a single environment where all of the applications a user needs can run, and these are integrated together in a consistent and systematic way. If an application lives outside the portal, the portal should be able to consume some resource of the application (such as an RSS feed or a subset of functionality in a "dashboard" application) so that the end user can see everything he or she interacts with at a glance.

To achieve this, all the application functionality within Liferay Portal resides in fragments of the page called *portlets*. Portlets are web applications that run in a portion of a web page. Liferay's core is a portlet container that aggregates portlets on particular pages and displays them to users. In this way, one or many applications can reside on a page, and users can (at the administrator's discretion) arrange them however they like.

Portlet applications, like servlet applications, are defined by a Java standard which various portal server vendors have implemented. The Java standard defines the portlet specification. A JSR-168 or JSR-286 standard portlet should be deployable on any portlet container which supports those standards. Portlets are placed on the page in a certain order by the end user and are served up dynamically by the portal server.

Portal applications generally come in two flavors: 1) multiple portlets which only provide small amounts of functionality individually, but are aggregated by the portal server into a larger application, or 2) whole applications which reside in only one application window. The choice is up to the application designers. Only developers have to worry about what happens inside the portlet itself. The portal server handles building out the page as it is presented to users.

Portlets are not difficult to build, and Java standard portlets can be written by any Java developer with experience in writing web applications. Liferay provides a Plugins Software Development Kit that makes it easy to design new portlet projects. For further information about the Plugins SDK, please see *Liferay Developer's Guide*, which is the companion volume to this one, or *Liferay in Action*, published by Manning Publications, which is the official guide to Liferay development.

Additionally, Liferay supports portlets written in other programming languages, such as PHP, Ruby, Groovy, or Python. Sample portlets written in these languages are available from Liferay's public plugin repository on Github at  [https://github.com/liferay/liferay-plugins](https://github.com/liferay/liferay-plugins).

## Navigating Liferay

Liferay initially presents a very simple interface. Unauthenticated users can navigate the public pages of the portal and see a *Sign In* link in the top right corner of the screen.

---
![tip](../../images/01-tip.png) Note: If your portal initially presents you with the demo web site, 7 Cogs, see chapter 12 in part 2 for removal instructions.
---

To sign into Liferay for the first time, click the *Sign In* link. You'll next see the *Sign In Portlet*. This portlet allows a user (or a prospective user) to do several things: sign in to Liferay, create a new account on the portal, or have a password reminder emailed to you. An OpenID sign-in option is also available. You should use the default administrator account to sign in to the portal for the first time. If you were to create a new account on the portal for yourself now, it would be created using Liferay's defaults. This would mean that the account wouldn't have access to the administrative functions in the control panel you'll need in order to set up Liferay for your organization. Instead of creating a new user now, log in with the default administrator account:

**User Name:** test@liferay.com

**Password:** test

![Figure 1.1: Logging into Liferay Portal](../../images/01-logging-into-liferay-portal.png)

By default, Liferay uses your email address, not a username or ID, for authentication. This can be changed later if you wish, but it's generally a good practice to keep it this way. Users' email addresses make good candidates for user IDs since they are hard to forget and they are unique to each user.

The first page that's displayed when a user logs in for the first time is the Terms of Use page. By default, users must agree to the terms of use before they can access the portal. Scroll to the bottom of the page and click *I Agree* to proceed. Note that as a portal administrator, you can customize the Terms of Use page or disable it altogether. Next, let's take a look at how to navigate in Liferay. 

### Using the Dockbar

Once you've logged in as the default administrative user, the dockbar appears across the top of the page. The dockbar is the primary tool logged-in users have for navigating the portal and accessing administrative functions from anywhere in the portal. Clicking on the red pin icon at the left side of the dockbar pins it in place so that it remains visible when you scroll down the page. 

![Figure 1.2: The Dockbar](../../images/05-dockbar.png)

Depending on the logged-in users' roles and what sections of the portal they are viewing, they may see all or only some of the dockbar menus. For each menu, a list of options appears when you mouse over it. Let's look at what's available in the dockbar. 

### Add

As an administrator, the *Add* menu is the first item on the dockbar. This menu contains a list of items that you can add either to the page or to the current site.

![Figure 1.3 Add Menu from the Dockbar](../../images/01-add-menu-from-dockbar.png)

**Page:** adds a new page at the current navigation level. 

**Applications:** contains a list of common portlets that you can add to the page, such as web content display, asset publisher, documents and media display, and navigation. These portlets are covered in chapters 3 and 4. 

**More:** shows a list of all the currently deployed portlets, divided into categories. You can search for portlets in the list or use the search bar. To add a portlet to the current page, click the *Add* button next to a portlet, or drag the portlet from the menu to where you want it on the page.

The Add menu gives you a nice, quick way to build your pages. 

### Manage

The next item on the dockbar is the *Manage* menu. From here, you can access many settings for the current page and site. 

![Figure 1.4: Manage Menu from the Dockbar](../../images/01-manage-menu-from-dockbar.png)

**Page:** brings up a window containing controls for managing a specific page of the current site. These include the theme, layout, search engine optimization, and more.

**Page Layout:** lets you choose a layout template for the current page. Layout templates provide visual structure to page contents.

**Page Customizations:** enables you to directly select portions of the page that users can customize. Users with permission to customize this page can then customize the sections of the page you select. 

**Site Settings:** opens the site settings page of the control panel in a dialog box. Here, you can set the membership type of your site, apply site templates, manage staging, and more.

**Site Pages:** brings up a dialog box for viewing the page hierarchy of your site and customizing settings that apply to the entire site, not just to a single page. These include look and feel, site logos, and javascript.

**Site Memberships:** allows you to manage the users, user groups, organizations, and site roles associated with your site. You can switch the view between current members and teams using the View button.

**Site Content:** lets you view and manage content on your site such as documents, images, message board posts, calendar entries, wiki pages, blog entries, and more.

We'll cover these options in detail in chapter 3.

### Edit Controls

Next on the dockbar is a check box labeled *Edit Controls*. This toggles the edit controls that appear in the tops of portlet windows. This lets administrators to see a page the way a regular user would.

### Go to

The Go to menu is your main navigation tool within Liferay Portal. The control panel is the topmost item in this menu. Initially, your current location is highlighted. This shows you are in the public pages of the *liferay.com* site.

![Figure 1.5: Go To Menu from the Dockbar](../../images/01-go-to-menu-from-dockbar.png)

Liferay allows for various configurations of pages for end users: you can configure it so that some or all users have their own personal sites, with public and/or private pages, where they can place the portlets they need to use. The administrator account by default has its own pages. Because you are logged in with an account that has administrator privileges, you can see everything in the portal.

### My Account

Your (or the default user's) name appears at the right side of the dockbar next to a sign-out link that logs you out of the portal. Your name itself is a link to the *Manage My Account* dialog box. You can use the left menu to switch between viewing your account information and your personal site pages. You can configure both personal and portal information such as your screen name, password, email address, phone number, physical address, language, and time zone, as well as your organizations, sites, user groups, and roles. 

![Figure 1.6: My Account Menu from the Dockbar](../../images/01-my-account-menu-from-dockbar.png)

One of the most important tools Liferay offers for managing your portal is the aforementioned control panel, accessible from the *Go to* menu of the dockbar. The control panel is composed of administrative pages that you can use to mange various aspects of the portal.

## Navigating the Control Panel

The control panel is very easy to navigate. On the left side is a list of headings with functions underneath them. The headings are in alphabetical order, but the functions are in a logical order.

![Figure 1.7: Liferay's Control Panel](../../images/01-liferay-control-panel.png)

**User Name:** is always the logged in user's personal space. Here, you can change your account information and manage the pages of your personal site.

**Content:** contains links to all of Liferay's content management functions. You can maintain web content, documents, images, bookmarks, a calendar, administer a message board, configure a wiki, and more. The title of this section appears in a context selector as the name of the site whose content you are currently managing. You can switch to another one at any time.

**Portal:** allows portal administrators to set up and maintain the portal. This is where you can add and edit users, organizations, sites, roles, and configure the settings of the portal.

**Server:** contains administrative functions for configuring portal instances, plugins, updates and more.

All of the functions that you will need to maintain the portal or its contents can be found in the control panel. Additionally, developers can write portlets which can also be added to the control panel. For further information about this, you can take Liferay's Portal Developer course, refer to Liferay's *Portal Developer's Guide* or see the official guide to Liferay development, *Liferay in Action*.

## Portal Architecture

Before we dive into the user interface for adding and maintaining various portal resources, we should survey the concepts Liferay uses to organize a portal. Portals are accessed by users. Users can be collected into user groups. Users can belong to organizations. Organizations can be grouped into hierarchies, such as Home Office &rarr; Regional Office &rarr; Satellite Office. Sites can be created independently or can be attached to users or organizations. Within sites, users can belong to teams, which are groupings of users for specific functions within a site.

---
![tip](../../images/01-tip.png) Note: Prior to Liferay 6.1, independent sites were called communities. Both organizations and communities had their own sets of public and private pages. Starting with Liferay 6.1, organizations cannot directly have their own sets of pages, only sites can. Organizations need to have attached sites in order to have pages. This is just a reorganization of ideas intended to simplify how Liferay manages pages.
---

A simple way to think about this is that your portal has users and various ways to group them together. Some of these groupings may be organized hierarchically by an administrator. These are called organizations. An administrator can also create more ad hoc groupings of users called user groups. User groups can be composed of users who do not fit into a particular hierarchy or who belong to different organizations. Other groupings may be created by the users themselves. For example, users from different organizations could create a site called “Dog Lovers” and allow anyone to join. The site would not fit into an organizational hierarchy; it would just serve a common interest in dogs. Administrators can create user groups within organizations and sites. The following figure illustrates how users can be grouped in Liferay Portal: users can belong to sites, organizations, and user groups and user groups can belong to sites and organizations.

![Figure 1.8: Liferay's User Collection Model](../../images/user-permission-Collections\of\sUsers.png)

Liferay manages permissions for users and collections of users via roles. Roles can be scoped to grant permissions within a particular organization or site. They can also be scoped to customize permissions that cut across the entire portal. For example, a Message Boards Administrator role could be created that grants permissions to administer any message board in the portal. Users from multiple organizations or sites could be assigned to to this role. Roles provide a powerful mechanism for administrators to configure portal resources and security in a consistent and robust manner. The figure below illustrates permissions defined by roles for users and collections of users. Permissions cannot be directly assigned to users. Users receive their permissions from roles, either directly, or from an organization, user group, or site.

![Figure 1.8: Liferay's Permissions Model](../../images/01-liferay-permissions-model.png)

Teams belong to individual sites. Roles that appear inside sites are scoped just for sites. This means that although each site in the portal may have access to a particular role with its configured permissions, membership in this role can be different for each site and the granted permissions only apply within one site.

### Users

Users represent physical users of the system. These are the user accounts that people use to log into the system. By default, users get their own private sites with public and private pages that they can manage themselves. Users' personal sites are important: they enable users to have their own public blog or their own private calendar, a place to store their documents, and more. However, if you don't want want users to have personal sites, this default behavior can be turned off by a portal administrator in the portal-ext.properties configuration file. Liferay uses site templates to control the default portlets that appear on the public and private pages of user's personal sites. See chapter 3 for information about templates.

Users can be collected in multiple ways. They can be members of organization hierarchies, such as Liferay, Inc. &rarr; Security &rarr; Internet Security. They can be collected into arbitrary user groups, such as Bloggers, which could be used to set apart users who get a Blog page in their personal space from users who do not. Users can become members of independent sites which serve to draw together common interests. Users can have roles which define their permissions in the portal. These roles can be scoped by portal, organization, or site.

### User Groups

User groups are simple, arbitrary collections of users, created by administrators. You can make a user group a member of a site or organization. This makes each member of the user group a member of the site or organization. Permissions cannot be directly assigned to user groups, but user groups can be assigned to roles. If you assign a role to a user group then each member of the user group will be assigned that role (and thus the permissions defined by the role). Although user groups cannot have sites, they can have site templates which can be used to customize users' personal sites. We describe this in more detail below.

### Roles

There are three kinds of roles: 

- Portal Roles

- Organization Roles

- Site Roles

The adjectives used to describe the different kinds of roles are called role *scopes*. Roles are used to define permissions across their scopes: across the portal, across an organization, or across a site. For example, consider a role which grants access to create a Message Board category. A portal role would grant that access across the portal, wherever there was a Message Board portlet. An organization role would grant that access within a site attached to the organization. A site role would grant that access within a site.

Roles can be assigned to users, user groups, organizations, and sites. Note: It is important to understand Liferay's portal architecture concepts before assigning roles. For example, assigning roles to an open site could create a security problem within your portal. An open site means that users are free to join and leave. So assigning a role to an open site potentially grants the permissions defined by that role to every user in the portal. Open, restricted, and private sites are discussed in the *Sites* section.

### Organizations

Organizations are hierarchical collections of Users. Organizations cannot have pages directly associated with them. Instead, sites can be attached to organizations. There is also a special type of organization called a *location*, which cannot have any suborganizations. If you represent an organization by a tree diagram, locations would be leaf nodes.

Organizations in Liferay are meant to model organizations in real life. They are handy for defining where a user belongs in a particular hierarchy. For example, if you are implementing Liferay for a large organization, it might help to define user Joe Smith via his position in the organization chart. If Joe Smith is a Sales Engineer located in the New Jersey office, working in the North East division of the Sales department, he might be a member of the following organizations:

- Sales

- New Jersey Location

- North East Division

Now imagine that you have placed an Asset Publisher portlet as a static portlet on every user's home page (via a user group page template) so that you can inform employees of various announcements via the content management system. If you tagged your content appropriately, you could ensure that Joe Smith gets any announcements that are meant for Sales, the North East Division, or the New Jersey location.

Organizations need sites in order to have pages. If certain users have been granted the manage pages permission, they will be able to create and maintain pages within an organization. They can use the organization's public pages to include information or applications appropriate for guests or logged-in users who are not members of the organization. For example, a member of an IT organization might place a help desk ticket entry system portlet on a public page of the organization's site. The IT member could create a private page of the organization for applications for the organization's own use. For example, a back-end portlet of the aforementioned ticket entry system portlet could be placed here.

### Sites

Sites are simply collections of pages. Sites can contain public pages, which are accessible to all portal users, and private pages, which are only accessible to site members. Liferay's default pages are part of a site named for the portal because all portal users have a common interest in the default, public pages of your site. There are
three types of sites:

- Open

- Restricted

- Private

An open site (the default) allows portal users to join and leave the site whenever they want to, using the control panel or a My Sites portlet added to a page which they can access. A restricted site requires that users be added to the site by a site administrator. Users can request membership in a restricted site using the control panel or the My Sites portlet. A private site is just like a restricted site except that it will be invisible to non-members in the control panel and in the My Sites portlet. Users can only be added to a private site by a site administrator.

### Teams

Teams are essentially sets of users that can be created within a site. Teams can be created both within sites that are attached to an organization and within independent sites. Teams are different from site roles since teams appear only in the site in which they are created. This is very useful if you need to create a team of users for a specific purpose within a single organization or site and not for each site in the portal.

Teams can be essential for some use cases since they can be created by site administrators. Site administrators cannot create roles so the ability to have teams empowers them to manage permissions at a level they weren't capable of previously.

## Summary

So far, we've looked at Liferay's function as a portal server and learned how to navigate within Liferay using the dockbar and the control panel. We've also taken a look at look at Liferay's portal architechture and how Liferay uses the concepts of users, user groups, organizations, sites, teams, and roles to organize your portal. Next, we'll take a look at using Liferay Marketplace to browse and install applications for your portal.
