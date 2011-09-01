# Introduction to Liferay

This chapter covers these activities:

-   *Liferay's User Interface:* How to navigate around Liferay and use the Control Panel.

-   *Liferay Administration:* How to administer a Liferay portal.

## Liferay's User Interface

Liferay is a *portal server*. This means that it is designed to be a single environment where all of the applications a user needs can run, and these are integrated together in a consistent and systematic way. If an application lives outside the portal, the portal should be able to consume some resource of the application (such as an RSS feed or a subset of functionality in a “dashboard” application) so that the end user can see everything he or she interacts with at a glance.

To achieve this, all the application functionality within Liferay Portal is in fragments of the page called *portlets*. Portlets are web applications that run in a portion of a web page. Liferay's core is a portlet container that aggregates portlets on particular pages and displays them to users. In this way, one or many applications can reside on a page, and users can (at the administrator's discretion) arrange them however they like.

Portlet applications, like servlet applications, are defined by a Java standard which various portal server vendors have implemented. The Java standard defines the portlet specification. A JSR-168 or JSR-286 standard portlet should be deployable on any portlet container which supports those standards. Portlets are placed on the page in a certain order by the end user and are served up dynamically by the portal server.

Portal applications generally come in two flavors: 1) multiple portlets can be written to provide small amounts of functionality and then are aggregated by the portal server into a larger application, or 2) whole applications can be written to reside in only one or a few portlet windows. The choice is up to the application designers. Only developers have to worry about what happens inside the portlet itself. The portal server handles building out the page as it is presented to users.

Portlets are not difficult to build, and Java standard portlets can be written by any Java developer with experience in writing web applications. Liferay provides a Plugins Software Development Kit that makes it easy to design new portlet projects. For further information about the Plugins SDK, please see *Liferay Developer's Guide*, which is the companion volume to this one, or *Liferay in Action*, published by Manning Publications, which is the official guide to Liferay development.

Additionally, Liferay supports portlets written in other programming languages, such as PHP, Ruby, Groovy, or Python. Sample portlets written in these languages are available on Github at  ([https://github.com/liferay/liferay-plugins](https://github.com/liferay/liferay-plugins)).

### Navigating Liferay

Liferay initially presents a very simple interface. Unauthenticated users can navigate the public pages of the portal and see a *Sign In* link in the top right corner of the screen.

---
![tip](../../images/01-tip.png)If your portal initially presents you with the demo web site, 7 Cogs, see Chapter 12 in Part 2 for removal instructions.
---

To sign into Liferay for the first time, you can click the *Sign In* link. You will then be presented with the **Sign In Portlet**. This portlet allows a user (or a prospective user) to do several things: sign in to Liferay, create a new account on the portal, or have a password reminder emailed if the user has forgotten his or her password. An OpenID sign-in option is also available. To sign in for the first time, don't create an account for yourself. We will do that later. If you were to create a new account on the portal for yourself now, it would be created using Liferay's defaults, which means the account would not have access to the administrative portlets you need in order to set up Liferay for your organization. For this reason, you will need to sign in as the default administrative user. This user's credentials are:

**User Name:** test@liferay.com

**Password:** test

![Logging into Liferay Portal](../../images/01-logging-into-liferay-portal.png)*Illustration 1: Logging into Liferay Portal*

Go ahead and sign in to your new portal using these credentials. As you can see, Liferay users' email addresses are by default used for their user IDs. This can be changed later if you don't like this functionality, but it is generally a good practice to keep it this way. Users' email addresses are not normally things they will forget, and they are unique to each user, so they make good candidates for user IDs.

The first page that will be displayed when a user logs in for the first time is the Terms of Use page. By default, users are required to agree to the terms of use before they can access the portal. Scroll to the bottom of the page and click *I Agree* to proceed. Note that as a portal administrator, you can customize the Terms of Use page or disable it altogether. 

Once you have logged in as the default administrative user, you will see that the Dockbar has now appeared across the top of the page. The Dockbar is the primary tool logged-in users have for navigating the portal and accessing administrative functions from anywhere on the web site. Depending on the logged-in users' roles and what sections of the website they are viewing, they may see all or only some of the options available in the Dockbar.

As an administrator, the first option you will see on the Dockbar is *Add* . Mousing over *Add* will reveal a list of items that you can add. You can add a new page at the current navigation level or add portlets to the current page. When you first pull down the menu, you will see a list of common portlets that you can click on to add to the page. You will also see a *More* option, which will show you all of the currently available portlets. From the expanded *More* menu, you can add portlets to the current page. If you want to add a portlet to the current page, you can click the *Add* button next to a portlet to add it to the first column in the page, or drag the portlet from the menu to where you want it on the page.

![Add Menu from the Dockbar](../../images/01-add-menu-from-dockbar.png)*Illustration 2: Add Menu from the Dockbar*

The next option you'll see is the *Manage* menu. From this menu, you can access various settings for the current page and site. The page settings available are *Page, Page Layout, and Page Customizations*. Clicking on *Page* brings up a dialog box which enables you to modify details about the current page. *Page Layout* is a shortcut to a tab of this dialog box which lets you choose a layout template for the current page. Layout templates provide visual structure to page contents. *Page Customizations* enables you to directly select portions of the page to be customizable. Users with permission to customize your page will then be able to customize the portlets you selected. The site settings are the same as their counterparts in the *Control Panel*, which is covered later in this chapter.

![Manage Menu from the Dockbar](../../images/01-manage-menu-from-dockbar.png)*Illustration 2: Manage Menu from the Dockbar*

The next thing you'll see is a check box labeled *Edit Controls*. This lets you turn on and off the edit controls in the top of the portlet windows. This is helpful for administrators who want to look at a page they're working on and see it the way a regular user would.

If you roll your mouse over *Go to*, the *Dockbar* will expand, showing all of the places in the portal to which you have access. If you have access to the *Control Panel*, this will be the topmost item in the menu. Initially, your current location will be highlighted. You will see that you are in the *liferay.com* community, on the public pages. Liferay allows for various configurations of pages for end users: you can configure it so that some or all users have their own personal sites, with public and/or private pages, where they can place the portlets they need to use. The administrator account by default has its own pages. Because you are logged in with an account that has administrator privileges, you can see everything in the portal.

![Go To menu from the Dockbar](../../images/01-go-to-menu-from-dockbar.png)*Illustration 2: Go To Menu from the Dockbar*

Your (or the default user's) name appears at the right side of the dockbar next to a sign-out link that logs you out of the portal. Your name itself is a link to a *Manage My Account* window. You can use the left menu to switch between viewing your account information and your personal site pages. You can configure both personal and portal information such as your screen name, password, email address, phone number, physical address, language, and time zone, as well as your organizations, sites, user groups, and roles. 

![My Account menu from the Dockbar](../../images/01-my-account-menu-from-dockbar.png)*Illustration 2: My Account Menu from the Dockbar*

One of the most important tools Liferay offers for managing your portal is the aforementioned *Control Panel*, accessible from the *Go To* menu of the dockbar. The *Control Panel* is composed of administrative pages that you can use to mange various aspects of the portal.

### Navigating the Control Panel

The control panel is very easy to navigate. On the left side is a list of headings with functions underneath them. The headings are in alphabetical order, but the functions are in a logical order.

![Liferay's Control Panel](../../images/01-liferay-control-panel.png)*Illustration 3: Liferay's Control Panel*

**User Name**: The first section is always the logged in user's personal space. Here, you can change your account information and manage the pages of your personal site.

**Content:** The Content section contains links to all of Liferay's content management functions. You can maintain web content, documents, images, bookmarks, a calendar, administer a message board, configure a wiki, and more. The title of this section appears in a context selector as the name of the site whose content you are currently managing. You can switch to another one at any time.

**Portal:** The Portal section allows portal administrators to set up and maintain the portal. This is where you can add and edit users, organizations, sites, roles, and configure the settings of the portal.

**Server:** The Server section contains administrative functions for configuring portal instances, plugins, updates and more.

All of the functions that you will need to maintain the portal or its content can be found in the control panel. Additionally, developers can write portlets which can also be added to the control panel. For further information about this, you can take Liferay's Portal Developer course or see the official guide to Liferay development, *Liferay in Action*.

## Portal Architecture

Before we dive into the user interface for adding and maintaining various portal resources, it is best to go over the concepts Liferay uses to organize a portal.

Portals are accessed by users.

Users can be collected into user groups.

Users can belong to organizations.

Organizations can be grouped into hierarchies, such as Home Office →
Regional Office → Satellite Office.

Sites can be created independently or can be attached to users or organizations.

Within sites, users can belong to teams, which are groupings of users for specific functions within the site.

Note that prior to Liferay 6.1, independent sites were called communities and both organizations and communities could have their own sets of public and private pages. Starting with Liferay 6.1, organizations cannot directly have their own sets of pages, only sites can. Organizations need to attach sites to themselves in order to have pages. This is just a reorganization of ideas intended to simplify how Liferay manages pages.

The simplest way to think about this is that you have users and various ways they can be grouped together. Some of these groupings may be organized hierarchically by an administrator and other groupings may be created by the users themselves. For example, users from different organizations could create an independent site called “Dog Lovers” inspired by a common interest in dogs. Groupings for other functions that cut across the portal may be administrated via roles. For example, a Message Boards Administrators role could be created granting permissions to administer any message board in the portal. Users from multiple organizations or sites could be assigned to to this role.

The following figure illustrates the relationships between these portal concepts. In the figure, each arrow may be read using the words “can be a member of.” So this means that organizations can be members of sites, sites can be members of roles, users can be members of anything, and so on. Though this seems very complex, it provides a powerful mechanism for portal administrators to configure portal resources and security in a consistent and robust manner. It is important to note that the diagram illustrates only users and their collections. Permissions do not flow through all of these collections: permissions can be assigned to roles only.

![Liferay permissions model](../../images/01-liferay-permissions-model.png)*Illustration 4: Liferay permissions model. Don't worry, it's not as complicated as it seems.*

Teams belong to individual sites. Roles that appear inside sites are scoped just for sites. This means that though each site in the portal has this role with its configured permissions, membership in this role is different for each Site.

### Users

Users represent physical users of the system. These are the user accounts that people use to log into the system. By default, users get their own private sites with public and private pages that they can manage themselves. Users' personal sites are important: they enable users to have their own public blog or their own private calendar, a place to store their documents, and more. However, if you don't want want users to have personal sites, this default behavior can be turned off by a portal administrator in the portal-ext.properties configuration file. Liferay uses site templates to control the default portlets that appear on the public and private pages of user's personal sites. Templates are covered later in this chapter.

Users can be collected in multiple ways. They can be members of organization hierarchies, such as Liferay, Inc. → Security → Internet Security. They can be collected into arbitrary user groups, such as Bloggers, which could be used to set apart users who get a Blog page in their personal space from users who do not. They can be members of independent sites which draw together common interests. And they can have roles which define their permissions in the system, and these roles can be scoped by portal, organization, or site.

### User Groups

User groups are simple, arbitrary collections of users, created by administrators. You can make a user group a member of a site. This makes each member of the user group a member of the site. Permissions cannot be directly assigned to user groups but roles can be assigned to them. If you assign a role to a user group then each member of the user group will be assigned that role (and hence the permissions defined by the role). Though user groups cannot have sites, they can have page templates which can be used to customize users' personal sites. We describe this in more detail below.

### Roles

There are three kinds of roles:

-   Portal Roles

-   Organization Roles

-   Site Roles

These are called role *scopes*. Roles are used to define permissions across their scopes: across the portal, across an organization, or across a site. For example, consider a role which grants access to create a Message Board category. A portal role would grant that access across the portal, wherever there was a Message Board portlet. An organization role would grant that access within any sites attached to the organization. A site role would grant that access only within one site.

Roles can be assigned to users, user groups, organizations, and sites. Note: It is very important to understand Liferay's portal architecture concepts before assigning roles. For example, assigning roles to an open site could create a security problem within your portal. An open site means that users are free to join and leave. So assigning a role to an open site essentially grants the permissions of that role to every user in the portal.

### Organizations

Organizations are hierarchical collections of Users. Organizations do not have pages directly associated with them. Instead, sites can be attached to organizations. There is also a special type of organization called a *location*, which cannot have any suborganizations. If you represent an organization by a tree diagram, locations would be leaf nodes.

Organizations in Liferay are meant to model organizations in real life. They are handy for defining where a user belongs in a particular hierarchy. For example, if you are implementing Liferay for a large organization, it might help to define user Joe Smith via his position in the organization chart. If Joe Smith is a Sales Engineer located in the New Jersey office, working in the North East division of the Sales department, he might be a member of the following organizations:

-   Sales

-   New Jersey Location

-   North East Division

Now imagine that you have placed an Asset Publisher portlet as a static portlet on every user's home page (via a user group page template) so that you can inform employees of various announcements via the content management system. If you tagged your content appropriately, you could ensure that Joe Smith gets any announcements that are meant for Sales, the North East Division, or the New Jersey location.

Sites need to be attached to organizations in order for them to have pages. If they have been granted the manage pages permission, individual users will be able to create and maintain pages within an organization. They can use the organization's public pages to include information or applications appropriate for guests or logged-in users who are not members of the organization. For example, a member of an IT organization might place a help desk ticket entry system portlet on a public page of the organization's site. The IT member could create a private page of the organization for applications for the organization's own use. For example, a back-end portlet of the ticket entry system portlet mentioned above could be placed here.

### Sites

Sites are simply collections of pages. Sites can contain public pages, which are accessible to all portal users, and private pages, which are only accessible to site members. Liferay's default pages are part of a site named for the portal because all portal users have a common interest in the default, public pages of your site. There are
three types of sites:

-   Open

-   Restricted

-   Private

An open site (the default) allows portal users to join and leave the site whenever they want to, using the control panel or a My Sites portlet added to a page which they can access. A restricted site requires that users be added to the site by a site administrator. Users can request membership in a restricted site using the control panel or the My Sites portlet. A private site is just like a restricted site except that it will be invisible to non-members in the control panel and in the My Sites portlet. Users can only be added to a private site by a site administrator.

### Teams

Teams are essentially sets of users that can be created within a site. Teams can be created both within sites that are attached to an organization and within independent sites. Teams are different from site roles since teams appear only in the site in which they are created. This is very useful if you need to create a team of users for a specific purpose within a single organization or site and not for each site in the portal.

Teams can also be essential for some use cases since they can be created by site administrators. Site administrators cannot create roles so the ability to have teams empowers them to manage permissions at a level they weren't capable of previously.

## Using the Control Panel

The Portal section of the Control Panel is used for most administrative tasks. You will find there an interface for the creation and maintenance of

-   Users and Organizations

-   Sites

-   Site Templates

-   Page Templates

-   Roles

Additionally, it allows you to configure many server settings, including:

-   Password Policies

-   Portal Settings, including mail host names, email notifications, and authentication options like including single sign-on and LDAP integration

-   Custom Fields

-   Monitoring

-   Plugins Configuration

You will use the Portal section of the control panel to create your portal structure, implement security, and administer your users. Note that only users with the administrator role—a portal scoped role—have permission to view this section of the Control Panel. You can, of course, grant permissions to one or more sections to roles that you create yourself.

### Adding Users

![The Add User Screen](../../images/01-add-user-screen.png)*Illustration 5: The Add User screen*

Let's begin by adding a user account for yourself. We will then configure this account so that it has the same administrative access as the default administrator account. Go up to the Dockbar, mouse over *Manage* and click the Control Panel link, if you aren't there already. Click *Users and Organizations* under the *Portal* category. Click the *Add* button and select *User*. You will then be presented with the Add User form. Fill out the form using your name and email address. When you are finished, click *Save*.

The page will then reappear with a message saying that the save was successful, and there will now be an expanded form which allows you to fill out a lot more information about the user. You don't have to fill anything else out right now, but one thing is important to note: when the user ID was created, a password was automatically generated and, if Liferay was correctly installed (see Chapter 2), an email message with the password in it will have been sent to the user. This, of course, requires that Liferay can properly communicate with your SMTP mail server.

![Liferay's User Account editor](../../images/01-user-account-editor.png)*Illustration 6: Liferay's User Account editor*

If you haven't yet set up your mail server, you'll need to use this screen to change the default password for the user ID to something you can remember. You can do this by clicking on the *Password* link in the box on the right, entering the new password in the two fields, and clicking Save. Next, you will want to give your user account the same administrative rights as the default administrator's account. This will allow you to perform administrative tasks with your own ID instead of having to use the default ID. And this allows you to make your portal more secure by deleting or disabling the default ID.

Click the *Roles* link. You will then be taken to a screen which shows the roles to which your ID is currently assigned. You should have one role: Power User. By default, all users are assigned the Power User role. You can give this role certain permissions if you wish or disable it altogether (we will see how to do this later). You can also define the default roles a new user receives; we will go over this later also.

To make yourself an Administrator, click the *Select* link. A window will pop up with a list of all the roles in the system. Select the Administrator role from the list and the window will disappear and you will see that the role has been added to the list of roles to which you are assigned. Next, click the *Save* button, which is at the bottom of the blue bar of links on the right. You are now an administrator of the portal. Log out of the portal and then log back in with your own user ID.

### User Management

If you click the *Users* link on the left of the Control Panel, you will see that there are now two users in the list of users. If you wanted to change something about a particular user, you can click the *Actions* button next to that user.

**Edit User:** This takes you back to the Edit User page, where you can modify anything about the user.

**Permissions**: This allows you to define which roles have permissions to edit the user.

**Manage Pages:** If the user has pages, this allows you to edit them.

**Impersonate User:** Opens another browser window which allows you to browse the site as though you were the user.

**Deactivate:** Clicking this will deactivate the user's account.

Note that most users will not be able to perform most of the above (in fact, they won't even have access to this section of the Control Panel). You can perform all of the above functions because you have administrative access.

### Organizations

Organizations can be used to represent hierarchical stuctures such as those of companies, non-profit organizations, churches, schools, and clubs. They have been used to represent a sports league, with various sports (soccer, baseball, basketball, etc.) and their teams as sub-organizations. If you have a collection of users that all belong to the same grouping, you may be able to model that as an organization.

Your portal might not need an organization or it might have one or several, depending on your portal's function. For example, a simple photo-sharing web site might not need any organizations. On the other hand, organizations would be very useful for corporations or educational institutions since their users can be easily placed into a hierarchical structure. In fact, organizations in Liferay can be used to model any group hierarchy, from those of government agencies all the way down to those of small clubs. Of course, your portal can use both organizations and independent sites. For example, a corporation or educational institutional could create a social networking site open to all portal users, even ones from separate organizations. 

Organizations and suborganizations can be created in a hierarchy to unlimited levels, and users can be members of one or many organizations. These organizations could all reside in a single hierarchy or cut across different hierarchies. Note that the rights of organization administrators apply not only to their organization but also to its suborganizations. By default, members of suborganizations are members of the parent organizations. This behavior can be configured in your portal's portal-ext.properties configuration file.

Additionally, Organizations can be associated with roles. One application of this in a corporate setting could be an IT Security group. You could have an organization within your IT organization that handles security for all of the applications company-wide. If you had users as members of this organization, you could grant the administrator role you just granted to your own ID to the whole organization, thereby giving the members of the IT Security organization administrative access to the portal. Suppose now that a user in this organization later was hired by the Human Resources department. The simple act of removing the user from the IT Security organization also removes the user's administrative privileges, since the privilege came from the IT Security group's role. By adding the user to the HR organization, any roles the HR organization has (such as access to a benefits system in the portal) would be transferred to the user. In this manner, you can design your portal to correspond with your existing organization chart, and have users' permissions reflect their positions in the chart.

Of course, this is only one way to design it. If you have more complex requirements, you can combine organizations with teams and scoped roles to assemble the sets of permissions you wish to grant to particular users.

To add an organization, click the *Users and Organizations* link on the left side of the Control Panel, then click the Add button and choose Organization. An organization needs to have a site attached to it in order for it to have pages. To attach a site when you create an organization, click the Organization Site tab at the right, and check the Create Site box.

![Adding an organization](../../images/01-add-organization-screen.png)*Illustration 7: Adding an organization*

**Name:** The name of the organization.

**Type:** Use this to choose whether this is a regular organization or a location. A location cannot have any suborganizations.

**Parent Organization:** Click the *Select* link to bring up a window which allows you to select the organization in the system that is the direct parent of the organization you are creating. Click the *Remove* button to remove the currently configured parent.

---
![tip](../../images/01-tip.png) **Tip:** Note that you are already a member of any organizations that you created. By creating an organization, you become both a member and receive the Organization Owner role, which gives you full rights to the organization.
---

Fill out the information for your organization and click *Save*. As before with users, the form reappears and you can enter more information about the organization. Organizations can have multiple email addresses, postal addresses, web sites, and phone numbers associated with them. The *Services* link can be used to indicate the operating hours of the organization, if any.

For now, click the *Back* button. This will take you back to the list of organizations.

Click the *Actions* button next to the new organization you have created. You will then see the many actions you can take to manipulate this organization.

**Edit:** Lets you edit the organization.

**Manage Site:** Lets you create and manage public and private pages for the organization's site.

**Manage Teams:** Lets you create teams within this organization, to which you can assign users and permissions.

**Assign Organization Roles:** Lets you assign organization-scoped roles to users. By default, Organizations are created with three roles: Organization Administrator, Organization User, and Organization Owner. You can assign one or more of these roles to users in the organization. All members of the organization automatically get the Organization User role so this role is hidden when you click **Assign Organization Roles**.

**Assign Users:** Takes you to a screen where you can search and select users in the portal to be assigned to this organization as members.

**Add User:** Adds a new user in the portal who will be a member of this organization.

**Add Regular Organization: **Lets you add a child organization to this organization. This is how you create hierarchies of organizations with parent-child relationships.

**Add Location: **Lets you add a child Location, which is a special type of organization that cannot have any children added to it.

**Delete: **Deletes this organization from the portal. You will have to ensure that the organization has no users in it first.

If you click the **View** button at the top of the **Users and Organizations** page and select **View Hierarchy** you can view both a list of users who are members of this organization and a list of all the suborganizations of this organization.

### Sites

Sites are not hierachical by themselves but they can be attached to organizations to create a hierarchical structure. Independent sites are stand-alone groups of pages and are not associated with any organization or hierarchy. Open sites can be joined by any user regardless of organization membership. You can use open independent sites, therefore, in any situation where you need to cut across the organizational structure of your portal, or where you are providing content that could be relevant to anybody.

For example, a corporate Intranet running Liferay may have sites for all the organizations in the company: Sales, Marketing, Information Technology, Human Resources, and so on. But what about the corporate health and fitness center? That's something that everybody in the company, regardless of organization, may want to join. That's a good candidate for an independent site. Using the same scenario, the home page for the Intranet is probably best placed in an independent site that any member of the portal can access.

For other kinds of web sites, you may want to use independent sites to bring people together who have a common interest. If you were building a photo sharing web site out of Liferay, you may have indpendent sites based on the types of photos people want to share. So those who enjoy taking pictures of landscapes can join a Landscapes site, and those who enjoy taking pictures of sunsets can join a Sunsets site. And if they lose interest, they can leave those sites too.

The default home page in Liferay is created in a site which by default is called *liferay.com* (you can rename it; see the tip below), and this is where you would put your public web site. As you can see, there are many scenarios where you would want to an independent site instead of an organization site, and this is why organizations and sites have distinct roles within Liferay Portal.

![tip](../../images/01-tip.png) **Tip:** Prior to Liferay 6.1, organizations were created with their own sets of pages and did not need to have sites attached to them. Independent sites were called communities and the default community where the public pages were located was called *Guest* . As of Liferay 6.0, the default public site's name changes dynamically based on the name of the portal. Any time you change the name of your portal, the name of this site will change. You can set the name in *Portal Settings*.

Sites can be created in two ways. The first is through the control panel, like every other user/page collection in Liferay. The second is through the My Sites portlet, which can be added to any page in Liferay. Why are there two ways? Because the My Sites portlet also doubles as a way to navigate from site to site, and allows users to browse the list of sites and select whether or not they want to join one (if it is open or restricted). This enables portal administrators to provide users with this functionality without giving them access to the control panel. 

Sites can also be managed from the Manage menu of the dockbar. **Site Settings** lets you modify basic information and details about your site and provides options for search engine optimization and staging. **Site Pages** displays the page hierarchy of your site and provides options for customizing its look and feel as well adding a logo and javascript code to its pages. **Site Memberships** can display either current members or teams. You can switch views using the **View** button. Teams can be created by clicking View, selecting Teams and then using the **Add Team** button. You can also add Users, Organizations, User Groups, and Site Roles within the **Site Memberships** window. The **Site Content** window allows you to create or modify content such as blog entries, calendar events, documents, or images on your site.

To add a site, click the *Sites* link on the left side of the Control Panel in the Portal section, and then click the *Add* button. There are a few options you can select to create a site pre-populated with pages and portlets for collaborative or personal use or you can select **Custom Site** to build your site from scratch.

![Adding a Site](../../images/01-add-site-screen.png)

**Name:** Enter the name of the site you wish to create.

**Description:** Describe what the site will be used for.

**Type:** There are three kinds of sites: Open, Restricted, and Private. An open site appears in the My Sites portlet and users can join and leave the site whenever they want. A restricted site is the same except users can only request membership. A site administrator must then explicitly grant or deny users' requests to join. A private site does not appear in the My Sites portlet and users must be added to it manually by a site administrator.

**Active:** Sites can be active or inactive. If a site is inactive, no data can be added to it.

**Tags:** You can use Liferay's tagging mechanism on the site. This is helpful if the site has an easily describable purpose within the portal.

Once you have created a site, it will appear in the list of sites in the control panel. Once the site has been created you can specify more details about the site, which fall under three main categories: Basic Information, Search Engine Optimization, and Advanced.

![Editing a Site](../../images/01-site-editor.png)

**Details:** Lets you edit the information you entered when you created the site.

**Pages:** Lets you create and manage the site's public and private pages.

**Site URL:** Lets you set friendly URLs and virtual hosts for your web site.

**Site Template:** Lets you choose a template to apply to your site.

**Sitemap:** Lets you use the sitemap protocol to notify search engines that your web site is available for crawling. 

**Robots:** Lets you use a robots.txt file to specify certain pages and links that you do **not** want to be indexed by search engines. You need to set a virtual host before you set a robots.txt file.

**Staging:** Lets you turn on either Local Live staging or Remote Live staging.

**Analytics:** Lets you set a Google Analytics ID that will be used for your site.

#### Site Templates

While we're on the subject of sites, it is important to mention Site Templates, which is several links down in the Portal category in the control panel. These allow you to create web sites within sites by selecting from pre-defined templates that you can make ahead of time. What this means is that you can create a template that has a pre-defined set of pages and portlets, and then use that template to quickly create multiple sites that are pre-populated with those pages and portlets.

![Site Templates](../../images/01-site-templates.png)
*Illustration 8: Site Templates*

You can create templates for open, restricted, and private sites. Additionally, you can create a default template that applies to all kinds of sites. As an example, we will create and apply a template designed for an educational institution's student sites. Go to the control panel and click *Site Templates*. Click the *Add* button and create a site template called *Students*. Check the Active box to make your template useable. Also check the Allow Modifications and Allow Layout Additions boxes. This will allow you to modify or remove the content introduced to your site from the template. Now you can select the *Open Site Template* link and begin adding content, pages, portlets and configuring the layouts. You'll learn more about how to do this in the next chapter. Once you're finished, return to the Site Templates portlet and select *Save.*

To create a site based on the new template, go to the control panel and click *Sites*. Click the *Add* button and create a site called *Freshmen*. In the Public Pages drop down menu, select *Student Template* and then click *Save*. The new site will have all the pages, content, and portlets you created in the template. This feature streamlines the site creation process for administrators, making it easy to quickly create sites.

### User Groups

User Groups are arbitrary groupings of users. These groups are created by portal administrators to group users together who don't have an obvious organizational or site-based attribute which brings them together. Groups cannot have permissions like roles, but user groups can be added to roles. Why, then, would you use user groups? They come into play when you have complex security requirements. They are also useful in conjunction with page templates, which we will discuss below.

Creating a user group is easy. Click the *Users and Organizations* link, then click the *Add* button and choose *User Group*. There are only two fields to fill out: Name (the name of the User Group) and Description (an optional description of what the group is for). Note that you have the option to create a page template for your user group. We will cover page templates for user groups below. Click *Save* and you will be redirected back to the *Users and Organizations* page. Click *View* and select *User Groups* to see the group you created.

As with the other resources in the portal, you can click the *Actions*
button to perform various operations on User Groups.

**Edit:** Allows you to modify the name or description of the user group.

**Permissions:** This allows you to define which users, user groups, or roles have permissions to edit the user group.

**Manage Pages:** Though user groups don't have pages of their own, you can create page templates for a group. When a user group has page templates, any users added to the group will have their personal pages poplulated by the content of the group's template. This allows you to do things like create a Bloggers user group with a page template that has the Blogs and Recent Bloggers portlets on it. The first time users who are added to this group log in to the portal, this page will get copied to their personal pages. They will then automatically have a blog page that they can use.

**Assign Members:** Takes you to a screen where you can search for and select users in the portal to be assigned to this User Group.

**View Users:** Lets you view the users who are in the User Group.

**Delete:** Deletes the User Group.

#### User Groups and Page Templates

Liferay allows users to each have a personal site consisting of public and private pages. Permissions can be granted to allow to allow users to customize their personal sites at will. The default configuration of those pages can be determined by the portal administrator through the portal-ext.properties file and optionally by providing the configuration in a LAR file. Though this has been a long-time feature of Liferay, it was not very flexible or easy to use.

Liferay version 5.1 introduced the concept of page templates which are tied to user groups. This enables administrators to provide the same configuration for the personal pages of all (or just a subset of) users, using Liferay's GUI instead of the properties file. In some cases you may want to provide a different configuration for each user depending on his or her profile. For example, in a portal for University students, staff and undergraduates would get different default pages and portlets in their personal space. You can also set it up so that different groups are combined together to create the desired default configuration. When a user is assigned to a user group, the configured page templates are copied directly to the user's personal pages.

##### User Group Page Templates: Defining page templates for a user group

A user group's page templates can be administered using the control panel. Click *View* and select *User Groups* from the *Users and Organizations* page of the Control Panel to see a list of existing user groups. Click *Actions* to see the list of actions to peform on them that was described above.

![Manage Pages action on a User Group](../../images/01-user-groups-manage-pages.png)*Illustration 9: Manage Pages action on a User Group*

You can use the Manage Pages action to access the common Liferay UI for creating pages and organizing them in a hierarchy. Note that it is possible to create both public and private pages. Each set will be used as templates to be copied to the user's personal public or private page sets respectively when the user becomes a member of the user group.

![Adding a Page Template](../../images/01-user-groups-adding-page-template.png)*Illustration 10: Adding a Page Template*

In the screen shot above, the administrator has created a new private page called *You are a student* within the Students user group. Since the page created is a portlet page, the administrator can now click the *View Pages* button to open the page. Any number of portlets may be added to the page and they can be configured as needed. Let's assume for our example that the Loan Calculator and Calendar portlets are added to the page.

##### Applying the page templates by assigning members to the user group

The next step is to assign an existing user to that group to verify that the page template is copied to the user's private page. To do this, click *Actions* → *Assign Members* in the list of available user groups. Click the *Available* tab to see a list of users that can be assigned to the group.

![Assigning Members to a User Group](../../images/01-adding-members-to-user-group.png)*Illustration 11: Assigning Members to a User Group*

From that list, one or more users can be selected to make them members of the user group. When the *Update Associations* button is clicked, the users become members of the group and copies of any public or private page templates which are configured for the user group are copied to their page sets.

![Template copied to a user's personal site](../../images/01-template-copied-to-personal-site.png)*Illustration 12: Template copied to a user's personal site*

For example, suppose that a user that already had an existing *Welcome* page on his or her personal site. That user will have an additional *You Are A Student* page once he or she has been added to the *Students* user group. The *You are a student* page will contain the Loan Calculator and Calendar portlets that you added to the template page.

##### Additional details

Once the template pages have been copied to a user's personal site, the copies will be owned by the user. The user will be able to modify the pages and their content if the required permissions have been assigned to the user and the template. When a user is removed from a user group the associated pages won't be removed: they have become part of the user's personal site. However, if a user is removed from a group and is then added back, the pages will not be copied to the user's site a second time.

If an administrator modifies page templates for a User group after users have already been added to the group, those changes will be used when new users are assigned to the user group. Since the pages are templates, however, the changes won't be applied to users that were already members of the user group.

##### Composing A Page Out of Several User Groups

Users can belong to many user groups. If you have templates defined for a number of groups, this may result in having many page templates copied to users' pages. To prevent this, you can combine pages from different user groups into a single page.

We could expand our previous example by dividing the *Students* user group into *First Year Students* and *Second Year Students*. For each of these types of students we want to have a page with the Loan Calculator and Calendar, but depending on which type, we may want other portlets to be on that page too. This can be achieved by using a naming convention for the pages. If two or more pages of different user groups have the same name, they will be
combined into a single page when they are copied to a user's personal site. 

For our example, we will only create a *First Year Students* user group but a *Second Year Students* group could be created the same way. First, navigate to the Site Template page of the control panel. Click *Add* and enter "First Year Students" for the name of the site template and an optional description: "for use by the First Year Students user group". Add a private page called *You are a Student* to this template and add the RSS portlet to this page. Then navigate to the *Users and Organizations* page of the control panel, click *View*, and select *User Groups*. Add a user group with the name "First Year Students" and, optionally, the description "freshmen". Lastly, add the same user who was already assigned to the *Students* group to the *First Year Students* group.

![Portlets combined on page from two user group templates](../../images/01-combined-portlets-from-two-user-groups.png)*Illustration 13: Portlets combined on page from two user group templates*

In our example, a user was added to a *Students* group which had a template with a private page called *You are a Student* and to a *First Year Students* group which had a template with an identically named private page. Thus the private page of the user received both the portlets from the *Students* template and from the *First Year Students* template. Note: If there are additional pages or portlets on your user's personal site, this means that your portal is configured to add default pages to the personal sites of new users.

##### Page Combination Rules

The following rules are used when composing a page by combining pages from different user groups:

-   If a user becomes a member of a user group that has a page template with the same name in the same set (public or private) as a page that the user already has, those pages will be combined.

-   If any of the pages has the name translated to several languages, only the default language is considered in the comparison.

-   The portlets on the new page will be copied to the bottom of the equivalent columns of the existing page.

-   If the existing and the new pages have different layout templates, the existing one is preserved.

-   If the new layout template has portlets in columns that do not exist in the existing page, those portlets will be automatically copied to the first column of the existing layout template.

As you can see, it is possible to have a very flexible configuration for the default pages of portal users. Furthermore, that configuration can be changed at any time using the standard Liferay UI that administrators are used to and then assigning users to new user groups.

While these examples are somewhat simple, the system allows for as many user groups as desired. By using the convention of matching the page names, it is possible to build any default page composition that you want for your users.

### Roles

Roles are groupings of users that share a particular function within the portal, according to a particular scope. Roles can be granted permissions to various functions within portlet applications. Think of a role as a description of a function, such as Message Board Administrators. A role with that name is likely to have permissions relevant to the specific Message Board portlets delegated to it. Users who are placed in this role will inherit these permissions.

The roles page of the control panel serves as a single interface which lets you create roles, assign permissions to them, and assign users to the roles. Roles can be scoped by portal, site, or organization. To create a role, click the *Roles* link, and then click the *Add* button. You can choose a Regular, Site, or Organization role. A regular role is a portal-scoped role. Make a selection and then type a name for your role, a title, and an optional description. The name field is required but the title is optional. If you enter a name and a title, the title will be displayed in the list of roles on the *Roles* page of the control panel. If you do not enter a title, the name will be displayed. When you have finished, click *Save*.

![Roles page and role actions menu](../../images/01-roles-and-role-actions.png)*Illustration 13: Roles page and role actions menu*

You will be back at the list of roles. To see what functions you can perform on your new role, click the *Actions* button.

**Edit:** This lets you change the name, title, or description of the role.

**Permissions:** This allows you to define which users, user groups, or roles have permissions to edit the role.

**Define Permissions:** Click this to define what permissions this role grants. This is outlined in the next section.

**Assign Members:** This lets you search and select users in the portal to be assigned to this role. These users will inherit any permissions that have been assigned to this role.

**View Users:** This allows you to view the users who have been assigned to this role.

**Delete:** This lets you permanently delete a role from the portal.

#### Defining Permissions on a Role

Roles exist as a bucket for granting permissions to the users who are members of them. So one of the main tasks you will be doing with a role is granting it the permissions that you want members of the role to have.

![defining permissions on a role](../../images/01-defining-permissions-on-a-role.png)*Illustration 14: Defining Permissions on a Role*

When you click the *Actions* button on portal-scoped role and select *Define Permissions*, you will be shown a list of all the permissions that have been defined for that role. Click the *Add Permissions* drop-down menu to see a list of the permissions that can be defined. As of Liferay version 6.1, these permissions fall into seven categories: Portal, Site Content, Site Application, Control Panel: Personal, Control Panel: Site, Control Panel: Portal, and Control Panel: Server. For non-portal scoped roles, you need to click on the *Options* link on individual portlets, then *Configuration*, then *Permissions* to assign permissions within the site or organization that owns the portlet. 

Portal permissions cover portal-wide activities that comprise several categories, such as site, organization, location, password policy, etc. This allows you to create a role that, for example, can create new site within the portal. This would allow you to grant users that particular permission without making them overall portal administrators.

Site Content permissions cover the content that the installed portlets create. If you pick one of the portlets from this list, you'll get options for defining permissions on its content. For example, if you pick Message Boards, you'll see permissions for creating categories or threads, or deleting and moving topics.

Site Application permissions affect the application as a whole. So, using our Message Boards example, an application permission might define who can add the Message Boards portlet to a page.

The Control Panel permissions affect how the portlet appears to the user in the control panel. Some Control Panel portlets have a Configuration button, so you can define who gets to see that, as well as who gets to see an application in the Control Panel.

![message board content permissions](../../images/01-message-board-content-permissions.png)*Illustration 15: Message board content permissions*

Each possible action to which permissions can be granted is listed. To grant a permission, check the box next to it. If you want to limit the scope of the permission to a particular site, click the *Limit Scope* link, and then choose the site. Once you have chosen the permissions granted to this role, click *Save*. For a portal-scoped Message Boards Administrator role, you might grant content permissions for every action listed. After you click *Save*, you will see a list of all permissions that are currently granted to this role. From here, you can add more permissions or go back by clicking a link in the breadcrumb list or the *Return to Full Page* link.

Roles are very powerful, and allow portal administrators to define various permissions in whatever combinations they like. This gives you as much flexibility as possible to build the site you have designed.

#### Special Note about the Power Users Role

Prior to Liferay 6.0, the default configurations of many Liferay portlets allowed power users, but not regular users, to access them. In Liferay 6.0 and subsequent versions, power users and regular users are granted the same default permissions. The idea is not to force any portal administrators to use the power users role, but to encourage those who do to create their own custom permissions for the role. 

Note: If you are using Liferay 5.2 or a previous verion and you decide to remove the Power Users role from the default user associations, you will need to modify the permissions on certain portlets. To do this, see the section on Plugins Configuration below.

### Teams

Teams don't appear as a link in the control panel because they exist *within* sites. Teams can be used when you need to create a bucket for permissions within a a single site. If you create a team for one site, it will not be available to any other sites. This is beneficial when you want a particular set of users and permissions for a site-specific function.

To create a team within a site, first naviagte to the *Control Panel → Sites* page then and then select *Actions → Manage Memberships* for the site within which you want to create a team. Finally, click *View → Teams* and click the Add Team button.

![creating a team within a site](../../images/01-creating-a-team.png)
*Illustration 16: Creating a team within a site*

After you've clicked the *Add Team* button and entered a name and a description, click *Save*. Your new team will appear in the list. To add members, simply click on *Actions → Assign Members*.

Permission management for teams is handled at the individual portlet level, using the *Options → Configuration → Permissions* tab of the portlet itself. Remember that the portlet options link is the wrench symbol at the top of a portlet. This enables users who wouldn't have access to all of the necessary options in the control panel to manage permissions through teams.

[assigning portlet permissions to a team](../../images/01-assigning-portlet-permissions-to-teams)*Assigning portlet permissions to a team*

To give a team access to a particular portlet function, access the *Permissions* tab of a portlet residing on a page, check the boxes corresponding to permissions you want to assign to the teams, and then click *Save*. Now your team is ready to work!
