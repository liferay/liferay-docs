# Administering Liferay Portal

This chapter covers the following activities:

-   Using the control panel to manage users, organizations, sites, user groups, roles, and teams

-   Using the control panel to manage password policies, authentication settings, mail host names, email notifications, display settings, and monitoring

-   Using the control panel to manage server administration, portal instances, plugins, and updates

## Managing Users, User Groups, Organizations, Sites, Teams, and Roles from the Control Panel

The Portal section of the control panel is used for most administrative tasks. You will find there an interface for the creation and maintenance of

- Users, User Groups, and Organizations

- Sites and Teams

- Site Templates

- Page Templates

- Roles

Additionally, it allows you to configure many server settings, including:

- Password Policies

- Portal Settings

- Custom Fields

- Monitoring

- Plugins Configuration

You will use the Portal section of the control panel to create your portal structure, implement security, and administer your users. Configurable portal settings include mail host names, email notifications, and authentication options including single sign-on and LDAP integration. Note that only users with the administrator role, which is a portal scoped role, have permission to view this section of the control panel. You can, of course, grant permissions to one or more sections to custom roles.

### Adding Users

![Figure 12.1: The Add User Screen](../../images/01-add-user-screen.png)

Let's add a user account for yourself and configure this account so that it has the same administrative access as the default administrator account. Go up to the dockbar, mouse over *Manage* and click the control panel link, if you aren't there already. Click *Users and Organizations* under the *Portal* category. Click the *Add* button and select *User*. You will then be presented with the Add User form. Fill out the form using your name and email address. When you are finished, click *Save*.

After you submit the form, the page reloads with a message saying that the save was successful. An expanded form appears which allows you to fill out a lot more information about the user. You don't have to fill anything else out right now. Just note that when the user ID was created, a password was automatically generated and, if Liferay was correctly installed (see chapter 11), an email message with the password in it will have been sent to the user. This, of course, requires that Liferay can properly communicate with your SMTP mail server.

![Figure 12.2: Liferay's User Account Editor](../../images/01-user-account-editor.png)

If you haven't yet set up your mail server, you'll need to use this page to change the default password for the user ID to something you can remember. You can do this by clicking on the *Password* link in the box on the right, entering the new password in the two fields, and clicking *Save*. Next, you will want to give your user account the same administrative rights as the default administrator's account. This will allow you to perform administrative tasks with your own ID instead of having to use the default ID. This will also allow you to make your portal more secure by deleting or disabling the default ID.

Click the *Roles* link. The Roles page of the control panel shows the roles to which your ID is currently assigned. You should have one role: Power User. By default, all users are assigned the Power User role. You can give this role certain permissions if you wish or disable it altogether. You can also define the default roles a new user receives. We'll see how to do this later.

To make yourself an Administrator, click the *Select* link. A dialog box will pop up with a list of all the roles in the system. Select the Administrator role from the list. The dialog box will disappear and you will see that the role has been added to the list of roles associated with your account. Next, click the *Save* button, which is at the bottom of the blue bar of links on the right. You are now an administrator of the portal. Log out of the portal and then log back in with your own user ID.

### User Management

If you click the *Users* link on the left menu of the control panel, you will see that there are now two users in the list of users. If you wanted to change something about a particular user, you can click the *Actions* button next to that user.

**Edit User:** takes you back to the Edit User page, where you can modify anything about the user.

**Permissions:** allows you to define which roles have permissions to edit the user.

**Manage Pages:** allows you to edit the personal pages of a user.

**Impersonate User:** opens another browser window which allows you to browse the site as if you were the user.

**Deactivate:** Clicking this will deactivate the user's account.

Note that most users will not be able to perform most of the above actions. In fact, most users won't even have access to this section of the control panel. You can perform all of the above functions because you have administrative access.

### Organizations

Organizations can be used to represent hierarchical structures such as those of companies, non-profit organizations, churches, schools, and clubs. They have been used to represent a sports league, with various sports (soccer, baseball, basketball, etc.) and their teams as sub-organizations. If you have a collection of users that all belong to the same grouping, you may be able to model that as an organization.

Your portal might not need an organization or it might have one or several, depending on your portal's function. For example, a simple photo-sharing web site might not need any organizations. On the other hand, organizations would be very useful for corporations or educational institutions since their users can be easily placed into a hierarchical structure. In fact, organizations in Liferay can be used to model any group hierarchy, from those of government agencies all the way down to those of small clubs. Of course, your portal can use both organizations and independent sites. For example, a corporation or educational institutional could create a social networking site open to all portal users, even ones from separate organizations. 

Organizations and suborganizations can be created in a hierarchy to unlimited levels, and users can be members of one or many organizations. These organizations could all reside in a single hierarchy or cut across different hierarchies. Note that the rights of organization administrators apply not only to their organization but also to its suborganizations. By default, members of suborganizations are members of the parent organizations. This behavior can be configured in your portal's `portal-ext.properties` configuration file.

Additionally, Organizations can be associated with roles. One application of this in a corporate setting could be an IT Security group. You could have a suborganizaton of your IT organization that handles security for all of the applications company-wide. If you had users as members of this organization, you could grant the administrator role you just granted to your own ID to the whole organization, thereby giving the members of the IT Security organization administrative access to the portal. Suppose now that a user in this organization later was hired by the Human Resources department. The simple act of removing the user from the IT Security organization also removes the user's administrative privileges, since the privilege came from the IT Security group's role. By adding the user to the HR organization, any roles the HR organization has (such as access to a benefits system in the portal) would be transferred to the user. In this manner, you can design your portal to correspond with your existing organization chart, and have users' permissions reflect their positions in the chart.

Of course, this is only one way to design it. If you have more complex requirements, you can combine organizations with teams and scoped roles to assemble the sets of permissions you wish to grant to particular users.

To add an organization, click the *Users and Organizations* link on the left side of the control panel, then click the *Add* button and choose *Regular Organization*. An organization needs to have a site attached to it in order for it to have pages. To attach a site when you create an organization, click the *Organization Site* tab at the right, and check the *Create Site* box.

![Figure 12.3: Adding an organization](../../images/01-add-organization-screen.png)

**Name:** is name of the organization.

**Type:** lets you choose whether this is a regular organization or a location. A location cannot have any suborganizations.

**Parent Organization:** lets you select an organization in the system to be the direct parent of the organization you are creating. Click the *Remove* button to remove the currently configured parent.

---
![tip](../../images/01-tip.png) **Tip:** Note that you are already a member of any organizations that you created. By creating an organization, you become both a member and receive the Organization Owner role, which gives you full rights to the organization.
---

Fill out the information for your organization and click *Save*. As before with users, the form reappears and you can enter more information about the organization. Organizations can have multiple email addresses, postal addresses, web sites, and phone numbers associated with them. The Services link can be used to indicate the operating hours of the organization, if any.

For now, click the *Back* button. This will take you back to the list of organizations.

Click the *Actions* button next to the new organization you have created. You will then see the actions you can take to manipulate this organization.

**Edit:** lets you specify details about the organization, including addresses, phone numbers, email addresses, and websites.

**Manage Site:** lets you create and manage public and private pages for the organization's site.

**Manage Teams:** lets you create teams within this organization, to which you can assign users and permissions.

**Assign Organization Roles:** lets you assign organization-scoped roles to users. By default, Organizations are created with three roles: Organization Administrator, Organization User, and Organization Owner. You can assign one or more of these roles to users in the organization. All members of the organization automatically get the Organization User role so this role is hidden when you click Assign Organization Roles.

**Assign Users:** takes you to a dialog box where you can search and select users in the portal to be assigned to this organization as members.

**Add User:** adds a new user in the portal who will be a member of this organization.

**Add Regular Organization:** lets you add a child organization to this organization. This is how you create hierarchies of organizations with parent-child relationships.

**Add Location:** lets you add a child Location, which is a special type of organization that cannot have any children added to it.

**Delete:** deletes this organization from the portal. You will have to ensure that the organization has no users in it first.

If you click the *View* button at the top of the Users and Organizations page and select *View Hierarchy* you can view both a list of users who are members of this organization and a list of all the suborganizations of this organization.

### Sites

Sites are not innately hierarchical but they can be attached to organizations to create hierarchical structures. Independent sites are stand-alone groups of pages and are not associated with an organizational hierarchy. Open sites can be joined by any user regardless of organization membership. You can use open independent sites, therefore, in any situation where you need to cut across the organizational structure of your portal, or where you are providing content that could be relevant to anybody.

For example, a corporate Intranet running Liferay might have sites for all the organizations in the company: Sales, Marketing, Information Technology, Human Resources, and so on. But what about the corporate health and fitness center? That's something that everybody in the company, regardless of organization, may want to join. This makes it a good candidate for an open and independent site. Similarly, the home page for a corporate intranet should probably be placed in an open independent site so that any member of the portal can access it.

For other kinds of web sites, you may want to use independent sites to bring people together who share a common interest. If you were building a photo sharing web site out of Liferay, you might have independent sites based on the types of photos people want to share. For example, those who enjoy taking pictures of landscapes could join a Landscapes site, and those who enjoy taking pictures of sunsets could join a Sunsets site. If they lost interest, they could leave those sites.

The default home page in Liferay is created in a site which by default is called *liferay.com* (you can rename it; see the tip below), and this is where you would put your public web site. As you can see, there are many scenarios where you would want to use an independent site instead of an organization site. This is why organizations and sites have distinct roles within Liferay Portal.

---
![tip](../../images/01-tip.png) **Tip:** Prior to Liferay 6.1, organizations were created with their own sets of pages and did not need to have sites attached to them. Independent sites were called communities and the default community where the public pages were located was called *Guest* . Also, as of Liferay 6.0, the default public site's name changes dynamically based on the name of the portal. Any time you change the name of your portal, the name of this site will change. You can set the name in *Portal Settings*.
---

Sites can be created in two ways. The first is through the control panel, like every other user/page collection in Liferay. The second is through the My Sites portlet, which can be added to any page in Liferay. Why are there two ways? The My Sites portlet also doubles as a way to navigate from site to site, and allows users to browse the list of sites and select one to join (if it is open or restricted). The My Sites portlet enables portal administrators to provide this functionality to users without giving them access to the control panel. Sites can also be administered from the Manage menu of the dockbar. We discussed this in chapter 3.

To add a site, click the *Sites* link on the left side of the control panel in the Portal section, and then click the *Add* button. There are a few options you can select to create a site pre-populated with pages and portlets for collaborative or personal use or you can select *Custom Site* to build your site from scratch.

![Figure 12.4: Adding a Site](../../images/01-add-site-screen.png)

**Name:** is the name of the site you wish to create.

**Description:** describes the site's intended function.

**Type:** can be open, restricted, and private. An open site appears in the My Sites portlet and users can join and leave the site whenever they want. A restricted site is the same except that users can only request membership. A site administrator must then explicitly grant or deny users' requests to join. A private site does not appear in the My Sites portlet and users must be added to it manually by a site administrator.

**Active:** determines whether a site is active or inactive. Inactive sites are inaccessible but can be activated whenever a site administrator chooses to active them.

**Tags:** implement Liferay's tagging mechanism on the site. Tags can be very helpful for sites that have an easily describable purpose within the portal.

Once you've created a site, it appears in the list on the Sites page of the control panel. Once the site has been created you can specify more details about the site, which fall under three main categories: Basic Information, Search Engine Optimization, and Advanced.

![Figure 12.5: Editing a Site](../../images/01-site-editor.png)

**Details:** lets you edit the information you entered when you created the site.

**Pages:** lets you create and manage the site's public and private pages.

**Site URL:** lets you set friendly URLs and virtual hosts for your web site.

**Site Template:** lets you choose a template to apply to your site.

**Sitemap:** lets you use the sitemap protocol to notify search engines that your web site is available for crawling. 

**Robots:** lets you use a `robots.txt` file to specify certain pages and links that you do *not* want to be indexed by search engines. You need to set a virtual host before you set a `robots.txt` file.

**Staging:** lets you turn on either Local Live staging or Remote Live staging.

**Analytics:** lets you set a Google Analytics ID that will be used for your site.

#### Site Templates

While we're on the subject of sites, we should mention Site Templates, which appear below the Sites link in the Portal category of the control panel. These allow you to create sites by selecting pre-defined templates that you can make beforehand. A site template consists of a pre-defined set of pages and portlets. You can use templates to quickly create multiple sites that are pre-populated with the pages and portlets defined by the templates.

![Figure 12.6: Site Templates](../../images/01-site-templates.png)

You can create templates for open, restricted, and private sites. Additionally, you can create a default template that applies to any type of site. As an example, we will create and apply a template designed for an educational institution's student sites. Go to the control panel and click *Site Templates*. Click the *Add* button and create a site template called *Students*. Check the Active box to make your template usable. Also check the Allow Modifications and Allow Layout Additions boxes. This will allow you to modify or remove the content introduced to your site from the template. Now you can select the *Open Site Template* link and begin adding content, pages, portlets and configuring the layouts. You'll learn more about how to do this in the next chapter. Once you're finished, return to the Site Templates portlet and select *Save.*

To create a site based on the new template, go to the control panel and click *Sites*. Click the *Add* button and create a site called *Freshmen*. In the Public Pages drop-down menu, select *Student Template* and then click *Save*. The new site will have all the pages, content, and portlets you created in the template. This feature streamlines the site creation process for administrators, making it easy to quickly create sites.

### User Groups

User Groups are arbitrary groupings of users. These groups are created by portal administrators to group users together who don't have an obvious organizational or site-based attribute which brings them together. Permissions can be granted to user groups by assigning them to roles. User groups come into play when you have complex security requirements. They are also useful in conjunction with page templates, which we discuss below.

Creating a user group is easy. Click the *Users and Organizations* link, then click the *Add* button and choose *User Group*. 

![Figure 12.7: Creating a New User Group](../../images/server-configuration-new-user-group.png)

There are only two fields to fill out: Name and Description. Note that you have the option to create a personal site template for your user group. When a user group has a site template, any users added to the group will have their personal site populated with the content of the group's template. This allows you to do things like create a Bloggers user group with a page template that has the Blogs and Recent Bloggers portlets on it. When users who have been added to this group log in to the portal, the user group's site template will be applied to their personal sites. Thus all users belonging to the Bloggers group automatically receive a blog page that they can use. Click *Save* and you will be redirected back to the *Users and Organizations* page. Click *View* and select *User Groups* to see the group you created.

As with the other resources in the portal, you can click the *Actions*
button to perform various operations on User Groups.

**Edit:** allows you to modify the name or description of the user group.

**Permissions:** lets you define which users, user groups, or roles have permissions to edit the user group.

**Manage Pages:** allows you to add, define, or remove page templates for a group.

**Assign Members:** lets you search for and select users in the portal to be assigned to this user group.

**View Users:** lets you view the users who are in the user group.

**Delete:** deletes the user group.

#### User Groups and Site Templates

Liferay allows users to each have a personal site consisting of public and private pages. Permissions can be granted to allow to allow users to customize their personal sites at will. The default configuration of those pages can be determined by the portal administrator through the `portal-ext.properties` file and optionally by providing the configuration in a LAR file. Though this has been a long-time feature of Liferay, it was not very flexible or easy to use.

Liferay version 5.1 introduced the concept of site templates that can be associated with user groups. This enables administrators to provide the same configuration for the personal sites of a group of users, using Liferay's GUI instead of the properties file. In some cases you might want to provide a different configuration for each user depending on his or her profile. For example, in a portal for University students, staff and undergraduates would get different default pages and portlets in their personal space. You can also set it up so that different groups are combined together to create the desired default configuration. When a user is assigned to a user group, the configured page templates are copied directly to the user's personal site.

##### User Group Site Templates: Defining Site Templates for a User Group

A user group's site templates can be administered from the control panel. Click *View* and select *User Groups* from the *Users and Organizations* page of the control panel to see a list of existing user groups. Click *Actions* to see the list of actions to perform on them that we described above.

![Figure 12.8: Manage Pages Action on a User Group](../../images/01-user-groups-manage-pages.png)

You can use the Manage Pages action to access the common Liferay UI for creating pages and organizing them in a hierarchy. Note that it is possible to create both public and private pages. Each set will be used as templates to be copied to the user's personal public or private page sets respectively when the user becomes a member of the user group.

![Figure 12.9: Adding a Page Template](../../images/01-user-groups-adding-page-template.png)

In the screen shot above, the administrator has created a new private page called *You are a student* within a user group called *Students*. Since the page created is a portlet page, the administrator can now click the *View Pages* button to open the page. Any number of portlets may be added to the page and they can be configured as needed. For our example, let's add the Loan Calculator and Calendar portlets to the template page.

##### Applying the Site Templates by Assigning Members to the User Group

The next step is to assign an existing user to the *Students* group to verify that the site template is copied to the user's personal site. To do this, click *Actions* &rarr; *Assign Members* in the list of available user groups. Click the *Available* tab to see a list of users that can be assigned to the group.

![Figure 12.10: Assigning Members to a User Group](../../images/01-adding-members-to-user-group.png)

From that list, one or more users can be selected to make them members of the user group. When the *Update Associations* button is clicked, the users become members of the group and copies of any public or private pages from the user group's site template are copied to users' personal sites. Note that content from the site template will be an addition to the users' personal sites but will not replace any existing content.

![Figure 12.11: Template Copied to a User's Personal Site](../../images/01-template-copied-to-personal-site.png)

For example, suppose that a user that already had an existing *Welcome* page on his or her personal site. That user will have an additional *You Are A Student* page once he or she has been added to the *Students* user group. The *You are a student* page will contain the Loan Calculator and Calendar portlets that you added to the template page.

##### Additional details

Once the template pages have been copied to a user's personal site, the copies will be owned by the user. The user will be able to modify the pages and their content provided that the required permissions have been assigned to the user and the template. When a user is removed from a user group the associated pages won't be removed: they have become part of the user's personal site. Moreover, if a user is removed from a group and is then added back, the pages will not be copied to the user's site a second time.

If an administrator modifies page templates for a user group after users have already been added to the group, those changes will only take effect when new users are assigned to the user group. The changes won't be applied to users that were already members of the user group.

##### Composing A Page Out of Several User Groups

Users can belong to many user groups. If you have templates defined for a number of groups, this may result in having many page templates copied to users' pages. To prevent this, you can combine pages from different user groups into a single page.

We could expand our previous example by dividing the *Students* user group into *First Year Students* and *Second Year Students*. For each of these types of students we want to have a page with the Loan Calculator and Calendar, but depending on which type, we may want other portlets to be on that page too. This can be achieved by using a naming convention for the pages. If two or more pages of different user groups have the same name, they will be combined into a single page when they are copied to a user's personal site. 

For our example, we will only create a *First Year Students* user group but a *Second Year Students* group could be created the same way. First, navigate to the Site Template page of the control panel. Click *Add* and enter *First Year Students* for the name of the site template and an optional description: *for use by the First Year Students user group*. Add a private page called *You are a Student* to this template and add the RSS portlet to this page. Then navigate to the *Users and Organizations* page of the control panel, click *View*, and select *User Groups*. Add a user group with the name *First Year Students* and, optionally, the description *freshmen*. Lastly, add the same user who was already assigned to the *Students* group to the *First Year Students* group.

![Figure 12.12: Portlets Combined on Page from Two User Group Templates](../../images/01-combined-portlets-from-two-user-groups.png)

In our example, a user was added to a *Students* group which had a template with a private page called *You are a Student* and to a *First Year Students* group which had a template with an identically named private page. Thus the *You are a Student* page of the user received both the portlets from the *Students* template and from the *First Year Students* template. 

---
![tip](../../images/01-tip.png) Note: If there are additional pages or portlets on your user's personal site than the ones in this example, this means that your portal is configured to add default pages to the personal sites of new users.
---

##### Page Combination Rules

The following rules are used when composing a page by combining pages from different user groups:

-   If a user becomes a member of a user group that has a page template with the same name in the same set (public or private) as a page that the user already has, those pages will be combined.

-   If any of the pages has the name translated to several languages, only the default language is considered in the comparison.

-   The portlets on the new page will be copied to the bottom of the equivalent columns of the existing page.

-   If the existing and the new pages have different layout templates, the existing one is preserved.

-   If the new layout template has portlets in columns that do not exist in the existing page, those portlets will be automatically copied to the first column of the existing layout template.

As you can see, it is possible to have a very flexible configuration for the default pages of portal users. Furthermore, that configuration can be changed at any time using the standard Liferay UI that administrators are used to and then assigning users to new user groups. While the examples we looked at are somewhat simple, the system allows for as many user groups as desired. By using the convention of matching the page names, it is possible to build any default page composition that you want for your users.

### Roles

Roles are groupings of users that share a particular function within the portal, according to a particular scope. Roles can be granted permissions to various functions within portlet applications. You can think of a role as a description of a function, such as Message Board Administrators. A role with that name is likely to have permissions relevant to the specific Message Board portlets delegated to it. Users who are placed in this role will inherit these permissions.

The roles page of the control panel serves as a single interface which lets you create roles, assign permissions to them, and assign users to the roles. Roles can be scoped by portal, site, or organization. To create a role, click the *Roles* link, and then click the *Add* button. You can choose a Regular, Site, or Organization role. A regular role is a portal-scoped role. Make a selection and then type a name for your role, a title, and a description. The name field is required but the title and description are optional. If you enter a name and a title, the title will be displayed in the list of roles on the *Roles* page of the control panel. If you do not enter a title, the name will be displayed. When you have finished, click *Save*.

![Figure 12.13: Roles Page and Role Actions Menu](../../images/01-roles-and-role-actions.png)

After you save, Liferay redirects you to the list of roles. To see what functions you can perform on your new role, click the *Actions* button.

**Edit:** lets you change the name, title, or description of the role.

**Permissions:** allows you to define which users, user groups, or roles have permissions to edit the role.

**Define Permissions:** defines what permissions this role grants. This is outlined in the next section.

**Assign Members:** lets you search and select users in the portal to be assigned to this role. These users will inherit any permissions that have been assigned to this role.

**View Users:** allows you to view the users who have been assigned to this role.

**Delete:** permanently removes a role from the portal.

#### Defining Permissions on a Role

Roles serve as repositories of permissions to be assigned to users who belong to them. So, to use a role, you need to assign members to it and define the permissions that you want to grant to members of the role.

![Figure 12.14: Defining Permissions on a Role](../../images/01-defining-permissions-on-a-role.png)

When you click the *Actions* button on portal-scoped role and select *Define Permissions*, you will be shown a list of all the permissions that have been defined for that role. Click the *Add Permissions* drop-down menu to see a list of the permissions that can be defined. As of Liferay version 6.1, these permissions fall into seven categories: Portal, Site Content, Site Application, Control Panel: Personal, Control Panel: Site, Control Panel: Portal, and Control Panel: Server. For non-portal scoped roles, you need to click on the *Options* link on individual portlets, then *Configuration*, then *Permissions* to assign permissions within the site or organization that owns the portlet. 

Portal permissions cover portal-wide activities that comprise several categories, such as site, organization, location, password policy, etc. This allows you to create a role that, for example, can create new sites within the portal. This would allow you to grant users that particular permission without making them overall portal administrators.

Site Content permissions cover the content that the installed portlets create. If you pick one of the portlets from this list, you'll get options for defining permissions on its content. For example, if you pick Message Boards, you'll see permissions for creating categories or threads, or deleting and moving topics.

Site Application permissions affect the application as a whole. So, using our Message Boards example, an application permission might define who can add the Message Boards portlet to a page.

The control panel permissions affect how the portlet appears to the user in the control panel. Some control panel portlets have a Configuration button, so you can define who gets to see that, as well as who gets to see an application in the control panel.

![Figure 12.15: Message Board Content Permissions](../../images/01-message-board-content-permissions.png)

Each possible action to which permissions can be granted is listed. To grant a permission, check the box next to it. If you want to limit the scope of the permission to a particular site, click the *Limit Scope* link, and then choose the site. Once you have chosen the permissions granted to this role, click *Save*. For a portal-scoped Message Boards Administrator role, you might grant content permissions for every action listed. After you click *Save*, you will see a list of all permissions that are currently granted to this role. From here, you can add more permissions or go back by clicking a link in the breadcrumb list or the *Return to Full Page* link.

Roles are very powerful, and allow portal administrators to define various permissions in whatever combinations they like. This gives you as much flexibility as possible to build the site you have designed.

#### Special Note about the Power Users Role

Prior to Liferay 6.0, the default configurations of many Liferay portlets allowed power users, but not regular users, to access them. Liferay 6.0 and subsequent versions grant the same default permissions to both power users and regular users. This way, portal administrators are not forced to use the power users role. However, Liferay encourages those who do to create their own custom permissions for the role. 

---
![tip](../../images/01-tip.png) Note: Prior to Liferay version 6.0, Power Users and Users did *not* have the same default permissions. So if are using Liferay 5.2 or a previous version, it's dangerous to remove the Power Users role from the default user associations: this could remove certain permissions that you expect to apply to all users. If you decide to remove the Power Users role from the default user associations anyway, you will probably want to modify the permissions on certain portlets to make them accessible to all users. To do this, see the section on Plugins Configuration below.
---

### Teams

Teams don't appear as a link in the control panel because they exist *within* sites. Teams can be used when you need to collect permissions within a a single site. If you create a team for one site, it will not be available to any other sites. Teams serve as a means to create a particular set of users and permissions for a site-specific function.

To create a team within a site, first naviagte to the *Control Panel &rarr; Sites* page then and then select *Actions &rarr; Manage Memberships* for the site within which you want to create a team. Finally, click *View &rarr; Teams* and click the Add Team button.

![Figure 12.16: Creating a Team within a Site](../../images/01-creating-a-team.png)

After you've clicked the *Add Team* button and entered a name and a description, click *Save*. Your new team will appear in the list. To add members, simply click on *Actions &rarr; Assign Members*.

Permission management for teams is handled at the individual portlet level, using the *Options &rarr; Configuration &rarr; Permissions* tab of the portlet itself. Remember that the portlet options link is the wrench symbol at the top of a portlet. This enables users who wouldn't have access to all of the necessary options in the control panel to manage permissions through teams.

[Assigning Portlet Permissions to a Team](../../images/01-assigning-portlet-permissions-to-teams)

To give a team access to a particular portlet function, access the *Permissions* tab of a portlet residing on a page, check the boxes corresponding to permissions you want to assign to the teams, and then click *Save*. Now your team is ready to work!

## Managing Portal Settings

After you have created users, user groups, organizations, roles, sites, and teams, your portal will be ready to host content and applications. You can configure Liferay's portal settings to fit your environment and your particular portal project. Many configurations can be performed through Liferay's portlet-driven user interface. This section covers how to configure portal settings such as password policies, authentication settings, mail host names, email notifications, display settings, and monitoring.

Now that you have been navigating in the control panel, you should be pretty familiar with how it works. All the options appear in the left navigation, their interfaces appear in the middle, and any sub-options appear on the right. We have focused so far on the maintenance of users and portal security. The remaining links in the *Portal* category focus on various portal settings which cover how the portal operates and integrates with other systems you may have.

### Password Policies

Password policies can enhance the security of your portal. Password policies can set requirements on password strength, frequency of password expiration, and more. Additionally, you can apply different password policies to different sets of portal users.

If you are viewing a page other than the control panel, select *Control Panel* from the *Go to* menu of the dockbar. Next, click on the *Password Policies* link on the left side of the screen under the *Portal* heading. You will see that there is already a default password policy in the system. You can edit this in the same manner as you edit other resources in the portal: click *Actions* and then click *Edit*.

The Password Policy settings form contains the following fields. Enabling specific settings via the check boxes prompts setting-specfic options to appear.

**Name:** requires you to enter a name for the password policy.

**Description:** lets you describe the password policy so that other administrators will know what it's for.

**Changeable:** determines whether or not a user can change his or her password.

**Change Required:** determines whether or not a user must change his or her password after logging into the portal for the first time.

**Minimum Age:** lets you choose how long a password must remain in effect before it can be changed.

**Reset Ticket Max Age:** determines how long a password reset link remains valid.

**Password Syntax Checking:** allows you to set a minimum password length and to choose whether or not dictionary words can be in passwords. You can also specify detailed requirements such as minimum numbers of alpha numeric characters, lower case letters, upper case letters, numbers, or symbols.

**Password History:** lets you keep a history (with a defined length) of passwords and prevents users from changing their passwords to one that was previously used.

**Password Expiration:** lets you choose how long passwords can remain active before they expire. You can select the age, the warning time, and a grace limit.

**Lockout:** allows you to set a number of failed log-in attempts that triggers a user's account to lock. You can choose whether an administrator needs to unlock the account or if it becomes unlocked after a specific duration.

From the list of password policies, you can perform several other actions.

**Edit:** brings you to the form above and allows you to modify the password policy.

**Permissions:** allows you to define which users, user groups, or roles have permission to edit the password policy.

**Assign Members:** takes you to a screen where you can search and select users in the portal to be assigned to this password policy. The password policy will be enforced for any users who are added here.

**Delete:** shows up for any password policies that you add beyond the default policy. You cannot delete the default policy.

### Portal Settings

Most global portal settings can be configured here. The Configuration heading contains the following links:

**General:** lets you configure global settings, such as the company name, domain, the virtual host, a global portal logo, and more.

**Authentication:** allows you to configure log in IDs, connection to LDAP, and Single Sign-On.

**Users:** has three tabs, labeled Fields, Reserved Credentials, and Default User Associations. The Fields tab enables or disables some user fields, such as birthday or terms of use. The Reserved Credentials tab lets you reserve screen names and email addresses so that users cannot register using them. You might use this to prevent users from registering on the portal with user names that contain profanity or that sound official, such as *admin* or *president*. The Default User Associations tab lets you configure default membership to roles, user groups, and sites for new users, and provides a check box which allows you to retroactively apply these to existing users.

**Mail Host Names:** lets you add a list of other mail host names to be associated with your organization. For example, your main domain might be `mycompany.com`, but you might use `mycompany-marketing.com` for your email newsletters. Any domain names associated with your organization can go here.

**Email Notifications:** allows you to configure Liferay to send email notifications for certain events, such as user registrations, password changes, etc. You can customize those messages here.

![Figure 12.17: Portal Settings Pages][../../images/server-configuration-portal-settings-pages.png]

We discuss these settings below.

#### General

The General link takes you to a page with three headings: Main Configuration, Navigation, and Additional Information. Under the Main Configuration heading, you can set the name of the company, organization, or site which is running the portal. This name also defines the name of your portal's default site. Its default name is `liferay.com`, so you will definitely want to set this to reflect your own company. You can also set the mail domain, virtual host, and content delivery network address here. Under the Navigation heading, you can set a home page for your portal here as well as default landing and logout pages. Under the Additional Information heading, you can specify a Legal name, ID, and type for your company, an SIC code, a ticker symbol, and industry, and a type.

#### Authentication

The Authentication page has several tabs: General, LDAP, CAS, Facebook, NTLM, OpenID, Open SSO, and SiteMinder. You can use any of these authentication methods to configure how users will authenticate to Liferay. Since Liferay supports quite a few authentication methods, there are different settings for each.

The settings on the General tab of the Authentication page affect only Liferay functionality, and don't have anything to do with the integration options on the other tabs. The General tab allows you to customize Liferay's standard authentication behavior. Specifically, the General tab allows you to select from several global authentication settings:

-   Authenticate via email address (default), screen name, or user ID (a numerical ID auto-generated in the database — not recommended).

-   Enable/Disable automatic log in. If enabled, Liferay allows a user to check a box which will cause the site to “remember” the user's log in by placing a cookie on his or her browser. If disabled, users will always have to log in manually.

-   Enable/Disable forgotten password functionality.

-   Enable/Disable request password reset links.

-   Enable/Disable account creation by strangers. If you are running an Internet site, you will probably want to leave this on so that visitors can create accounts on your site.

-   Enable/Disable account creation by those using an email address in the domain of the company running the site (which you just set on the General page of Portal Settings). This is handy if you are using Liferay to host both internal and external web sites. You can make sure that all internal IDs have to be created by administrators, but external users can register for IDs themselves.

-   Enable / Disable email address verification. If you enable this, Liferay will send users a verification email with a link back to the portal to verify that the email address they entered is a valid one they can access.

By default, all settings except for the last are enabled. User authentication by email address is an important default for the following reasons:

1.  An email address is, by definition, unique to the user who owns it.

2.  People can generally remember their email addresses. If you have  user who hasn't logged into the portal for a while, it is possible that he or she will forget his or her screen name, especially if the user was not allowed to use his or her screen name of choice (because somebody else already used it).

3.  If a user changes his or her email address, it is more likely that the user will forget to update his or her email address in his or her profile, if that email address is not used to authenticate. If the user's email address is not updated, all notifications sent by the portal will fail to reach the user. So it is important to keep the email address at the forefront of a user's mind when he or she logs in to help the user keep it up to date.

##### Authentication: LDAP

You can use the LDAP tab of the Authentication page to connect Liferay to an LDAP directory. There are two places for you to configure the LDAP settings: here in the control panel or in the `portal-ext.properties` file (which is covered in chapter 14). We recommend that you use the control panel since your configuration settings will be stored in the database. Note that if you use both, the settings in the database will override the settings in `portal-ext.properties`. Configuring the LDAP settings from the control panel is easier and does not require a restart of Liferay. The only compelling reason to use the `portal-ext.properties` file is if you have many Liferay nodes which will be configured to run against the same LDAP directory. In that case, for your initial deployment, it may be easier to copy the `portal-ext.properties` file to all of the nodes so that the first time they start up, the settings are correct. Regardless of which method you use, the available settings are the same.

You configure the global values from the LDAP tab of the Authentication page.

**Enabled:** Check this box to enable LDAP Authentication.

**Required:** Check this box if LDAP authentication is required. Liferay will then not allow a user to log in unless he or she can successfully bind to the LDAP directory first. Uncheck this box if you want to allow users with Liferay accounts but no LDA accounts to log in to the portal.

**LDAP Servers:** Liferay supports connections to multiple LDAP servers. You can you the Add button beneath this heading to add LDAP servers. We explain how to configure new LDAP servers below.

**Import/Export:** You can import and export user data from LDAP directories using the following options:

* *Import Enabled:* Check this box to cause Liferay to do a mass import from your LDAP directories. If you want Liferay to only synchronize users when they log in, leave this box unchecked. Definitely leave this unchecked if you are working in a clustered environment. Otherwise, all of your nodes would try to do a mass import when each of them starts up.

* *Import on Startup Enabled:* Check this box to have Liferay run the import when it starts up. Note: This box only appears if you check the *Import Enabled* box above.

* *Export Enabled:* Check this box to enable Liferay to export user accounts from the database to LDAP. Liferay uses a listener to track any changes made to the `User` object and will push these changes out to the LDAP server whenever the `User` object is updated. Note that by default on every login, fields such as `LastLoginDate` are updated. When export is enabled, this has the effect of causing a user export every time the user logs in. You can disable this by setting the following property in your `portal-ext.properties` file:

    users.update.last.login=false

**Use LDAP Password Policy:** Liferay uses its own password policy by default. This can be configured on the Password Policies page of the control panel. Check the *Use LDAP Password Policy* box if you want to use the password policies defined by your LDAP directory. Once this is enabled, the Password Policies tab will display a message stating that you are not using a local password policy. You will now have to use your LDAP directory's mechanism for setting password policies. Liferay does this by parsing the messages in the LDAP controls that are returned by your LDAP server. By default, the messages in the LDAP controls that Liferay is looking for are the messages that are returned by the Fedora Directory Server. If you are using a different LDAP server, you will need to customize the messages in Liferay's `portal-ext.properties` file, as there is not yet a GUI for setting this. See below for instructions describing how to do this.

Once you have completed configuring LDAP, click the *Save* button.

###### Adding LDAP Servers

The Add button beneath the LDAP servers heading allows you to add LDAP servers. If you have more than one, you can arrange the servers by order of preference using the up/down arrows. When you add an LDAP Server, you will need to provide several pieces of data so that Liferay can bind to that LDAP server and search it for user records. Regardless of how many LDAP servers you add, each server has the same configuration options.

**Server Name:** Enter a name for your LDAP server.

**Default Values:** Several leading directory servers are listed here. If you are using one of these, select it and click the Reset Values button. The rest of the form will be populated with the proper default values for that directory.

**Connection:** These settings cover the basic connection to LDAP.

* *Base Provider URL:* This tells the portal where the LDAP server is located. Make sure that the machine on which Liferay is installed can communicate with the LDAP server. If there is a firewall between the two systems, check to make sure that the appropriate ports are opened.

* *Base DN:* This is the Base Distinguished Name for your LDAP directory. It is usually modeled after your organization. For a commercial organization, it may look similar to this: `dc=companynamehere,dc=com`.

* *Principal:* By default, the administrator ID is populated here. If you have removed the default LDAP administrator, you will need to use the fully qualified name of the administrative credential that you use instead. You need an administrative credential because Liferay will be using this ID to synchronize user accounts to and from LDAP .

* *Credentials:* This is the password for the administrative user.

This is all you need in order to make a regular connection to an LDAP directory. The rest of the configuration is optional. Generally, the default attribute mappings provide enough data to synchronize back to the Liferay database when a user attempts to log in. To test the connection to your LDAP server, click the *Test LDAP Connection* button.

If you are running your LDAP directory in SSL mode to prevent credential information from passing through the network unencrypted, you will have to perform extra steps to share the encryption key and certificate between the two systems.

For example, assuming your LDAP directory happens to be Microsoft Active Directory on Windows Server 2003, you would take the following steps to share the certificate:

On the Windows 2003 Domain Controller, open the *Certificates* MMC snapin. Export the Root Certificate Authority certificate by selecting *Certificates (Local Computer) mmc snapin &rarr; Trusted Root Certification Authorities &rarr; MyRootCACertificateName*. Right click this certificate and select *All Tasks &rarr; export &rarr; select DER encoded binary X.509 .CER*. Copy the exported `.cer` file to your Liferay Portal server.

As with the CAS install (see the below section entitled Single Sign-On), you will need to import the certificate into the *cacerts keystore*. The import is handled by a command like the following:

    keytool -import -trustcacerts -keystore
    /some/path/jdk1.5.0_11/jre/lib/security/cacerts -storepass changeit
    -noprompt
    -alias MyRootCA -file /some/path/MyRootCA.cer

The *keytool* utility ships as part of the Java SDK.

Once this is done, go back to the LDAP page in the control panel. Modify the LDAP URL in the Base DN field to the secure version by changing the protocol to `https` and the port to `636` like the following:

    ldaps://myLdapServerHostname:636

Save the changes. Your Liferay Portal will now use LDAP in secure mode for authentication.

**Users:** This section contains settings for finding users in your LDAP directory.

* *Authentication Search Filter:* The search filter box can be used to determine the search criteria for user logins. By default, Liferay uses users' email addresses for their login names. If you have changed this setting, you will need to modify the search filter here, which has been configured to use the email address attribute from LDAP as a search criterion. For example, if you changed Liferay's authentication method to use screen names instead of the email addresses, you would modify the search filter so that it can match the entered log in name:

    (cn=@screen_name@)

* *Import Search Filter:* Depending on the **LDAP** server, there are different ways to identify the user. Generally, the default setting (`objectClass=inetOrgPerson`) is fine, but if you want to search for only a subset of users or users that have different object classes, you can change this.

* *User Mapping:* The next series of fields allows you to define mappings from LDAP attributes to Liferay fields. Though your LDAP user attributes may be different from LDAP server to LDAP server, there are five fields that Liferay requires to be mapped in order for the user to be recognized. You must define a mapping to the corresponding attributes in LDAP for the following Liferay fields:

    +   *Screen Name*

    +   *Password*

    +   *Email Address*

    +   *Full Name*

    +   *First Name*

    +   *Middle Name*

    +   *Last Name*

    +   *Job Title*

    +   *Group*

The control panel provides default mappings for commonly used LDAP attributes. You can also add your own mappings if you wish.

* *Test LDAP Users:* Once you have your attribute mappings set up (see above), click the *Test LDAP Users* button, and Liferay will attempt to pull LDAP users and match them with their mappings as a preview.

![Figure 12.18: Testing LDAP Users](../../images/server-configuration-testing-ldap-users.jpg)

**Groups:** This section contains settings for mapping LDAP groups to Liferay.

* *Import Search Filter:* This is the filter for finding LDAP groups that you want to map to Liferay. Enter the LDAP group attributes that you want retrieved for this mapping. The following attributes can be mapped:

    + Group Name

    + Description

    + User

* *Test LDAP Groups:* Click the **Test LDAP Groups** to display a list of the groups returned by your search filter.

**Export:** This section contains settings for exporting user data from LDAP.

* *Users DN:* Enter the location in your LDAP tree where the users will be stored. When Liferay does an export, it will export the users to this location.

* *User Default Object Classes:* When a user is exported, the user is created with the listed default object classes. To find out what your default object classes are, use an LDAP browser tool such as JXplorer to locate a user and view the Object Class attributes that are stored in LDAP for that user.

* *Groups DN:* Enter the location in your LDAP tree where the groups will be stored. When Liferay does an export, it will export the groups to this location.

* *Group Default Object Classes:* When a group is exported, the group is created with the listed default object classes. To find out what your default object classes are, use an LDAP browser tool such as *Jxplorer* to locate a group and view the Object Class attributes that are stored in LDAP for that group.

![Figure 12.19: Mapping LDAP Groups](../../images/server-configuration-mapping-ldap-groups.jpg)

Once you've set all your options and tested your connection, click *Save*. From here, you can add another LDAP server or set just a few more options that apply to all of your LDAP server connections.

###### LDAP Options Not Available in the GUI

Although most of the LDAP configuration can be done from the control panel, there are several configuration parameters that are only available by editing `portal-ext.properties`. These options will be available in the GUI in future versions of Liferay Portal, but for now they can only be configured by editing the properties file.

If you need to change any of these options, copy the LDAP section from the `portal.properties` file into your `portal-ext.properties` file. Note that since you have already configured LDAP from the GUI, any settings from the properties file that match settings already configured in the GUI will be ignored. The GUI, which stores the settings in the database, always takes precedence over the properties file.

    ldap.auth.method=bind
    #ldap.auth.method=password-compare

Set either bind or password-compare for the LDAP authentication method. Bind is preferred by most vendors so that you don't have to worry about encryption strategies. Password compare does exactly what it sounds like: it reads the user's password out of LDAP, decrypts it, and compares it with the user's password in Liferay, syncing the two.

    ldap.auth.password.encryption.algorithm=
    ldap.auth.password.encryption.algorithm.types=MD5,SHA

Set the password encryption to used to compare passwords if the property `ldap.auth.method` is set to `password-compare`.

    ldap.import.method=[user,group]

If you set this to `user`, Liferay will import all users from the specified portion of the LDAP tree. If you set this to `group`, Liferay will search all the groups and import the users in each group. If you have users who do not belong to any groups, they will not be imported.

    ldap.error.password.age=age
    ldap.error.password.expired=expired
    ldap.error.password.history=history
    ldap.error.password.not.changeable=not allowed to change
    ldap.error.password.syntax=syntax
    ldap.error.password.trivial=trivial
    ldap.error.user.lockout=retry limit

These properties are a list of phrases from error messages which can possibly be returned by the LDAP server. When a user binds to LDAP, the server can return *controls* with its response of success or failure. These controls contain a message describing the error or the information that is coming back with the response. Though the controls are the same across LDAP servers, the messages can be different. The properties described here contain snippets of words from those messages, and will work with Red Hat's Fedora Directory Server. If you are not using that server, the word snippets may not work with your LDAP server. If they don't, you can replace the values of these properties with phrases from your server's error messages. This will enable Liferay to recognize them.

##### Single Sign-On

Single Sign-On solutions allow you to provide a single login credential for multiple systems. This allows you to have people authenticate to the Single Sign-On product and they will be automatically logged in to Liferay and to other products as well.

Liferay supports several single sign-on solutions. Of course, if your product is not yet supported, you may choose to implement support for it yourself by use of the extension environment. Alternatively, your organization can choose to sponsor support for it. Please contact `sales@liferay.com` for more information about this.

##### Authentication: Central Authentication Service (CAS)

CAS is an authentication system that was originally created at Yale University. It is a widely-used open source single sign-on solution, and was the first SSO product to be supported by Liferay.

Please follow the documentation for CAS to install it on your application server of choice.

Your first step will be to copy the CAS client .jar file to Liferay's library folder. On Tomcat, this is in `[Tomcat Home]/webapps/ROOT/WEB-INF/lib`. Once you've done this, the CAS client will be available to Liferay the next time you start it.

The CAS Server application requires a properly configured Secure Socket Layer certificate on your server in order to work. If you wish to generate one yourself, you will need to use the `keytool` utility that comes with the JDK. Your first step is to generate the key. Next, you export the key into a file. Finally, you import the key into your local Java key store. For public, Internet-based production environments, you will need to either purchase a signed key from a recognized certificate authority (such as Thawte or Verisign) or have your key signed by a recognized certificate authority. For Intranets, you should have your IT department pre-configure users' browsers to accept the certificate so that they don't get warning messages about the certificate.

To generate a key, use the following command:

    keytool -genkey -alias tomcat -keypass changeit -keyalg RSA

Instead of the password in the example (`changeit`), use a password that you will be able to remember. If you are not using Tomcat, you may want to use a different alias as well. For First and Last names, enter `localhost`, or the host name of your server. It cannot be an IP address.

To export the key to a file, use the following command:
    
    keytool -export -alias tomcat -keypass changeit -file server.cert

Finally, to import the key into your Java key store, use the following command:

    keytool -import -alias tomcat -file %FILE_NAME% -keypass changeit
    -keystore $JAVA_HOME/jre/lib/security/cacerts

If you are on a Windows system, replace `$JAVA_HOME` above with `%JAVA_HOME%`. Of course, all of this needs to be done on the system on which CAS will be running.

Once your CAS server is up and running, you can configure Liferay to use it. This is a simple matter of navigating to the *Settings &rarr;  Authentication &rarr; CAS* tab in the control panel. Enable CAS authentication, and then modify the URL properties to point to your CAS server.

**Enabled:** Check this box to enable CAS single sign-on.

**Import from LDAP:** A user may be authenticated from CAS and not yet exist in the portal. Select this to automatically import users from LDAP if they do not exist in the portal.

The rest of the settings are various URLs, with defaults included. Change *localhost* in the default values to point to your CAS server. When you are finished, click *Save*. After this, when users click the *Sign In* link, they will be directed to the CAS server to sign in to Liferay.

##### Authentication: Facebook

Liferay Portal also enables users to log in using their Facebook accounts. To enable this feature, you simply need to select the *Enable* box and enter the Application ID and Application Secret which should have been provided to you by Facebook. Facebook SSO works by taking the primary Facebook email address and searching for the same email address in Liferay's `User_` table. If a match is found, the user is automatically signed on (provided that user clicked *allow* from the Facebook dialog). If there isn't a match, the user is prompted in Liferay to add a user from Facebook. Once selected, a new user is created by retrieving four fields from Facebook (first name, last name, email address, and gender).

##### Authentication: NTLM

NTLM is a Microsoft protocol that can be used for authentication through Microsoft Internet Explorer. Though Microsoft has adopted Kerberos in modern versions of Windows server, NTLM is still used when authenticating to a workgroup. Liferay Portal now supports NTLM v2 authentication. NTLM v2 is more secure and has a stronger authentication process than NTLMv1.

**Enabled:** Check this box to enable NTLM authentication.

**Domain Controller:** Enter the IP address of your domain controller. This is the server that contains the user accounts you want to use with Liferay.

**Domain:** Enter the domain / workgroup name.

**Service Account:** You need to create a service account for NTLM. This account will be a computer account, not a user account.

**Service Password:** Enter the password for the service account.

##### Authentication: OpenID

OpenID is a new single sign-on standard which is implemented by multiple vendors. The idea is that multiple vendors can implement the standard, and then users can register for an ID with the vendor they trust. The credential issued by that vendor can be used by all the web sites that support OpenID. Some high profile OpenID vendors are AOL [http://openid.aol.com/screenname](http://openid.aol.com/screenname), LiveDoor [http://profile.livedoor.com/username](http://profile.livedoor.com/username), and LiveJournal [http://username.livejournal.com](http://username.livejournal.com/). Please see the OpenID site [http://www.openid.net](http://www.openid.net/) for a more complete list.

A main benefit of OpenID for the user is that he or she no longer has to register for a new account on every site in which he or she wants to participate. Users can register on *one* site (the OpenID provider's site) and then use those credentials to authenticate to many web sites which support OpenID. Many web site owners often struggle to build communities because end users are reluctant to register for so many different accounts. Supporting OpenID makes it easier for site owners to build their communities because the barriers to participating (i.e., the effort it takes to register for and keep track of many accounts) are removed. All of the account information is kept with the OpenID provider, making it much easier to manage this information and keep it up to date.

Liferay Portal can act as an OpenID consumer, allowing users to automatically register and sign in with their OpenID accounts. Internally, the product uses OpenID4Java [http://code.google.com/p/openid4java/](http://code.google.com/p/openid4java/) to implement the feature.

OpenID is enabled by default in Liferay, but can be disabled here.

##### Atlassian Crowd

Atlassian Crowd is a web-based Single Sign-On product similar to CAS. Crowd can be used to manage authentication to many different web applications and directory servers.

Because Atlassian Crowd implements an OpenID producer, Liferay works and has been tested with it. Simply use the OpenID authentication feature in Liferay to log in using Crowd.

##### Authentication: OpenSSO

OpenSSO is an open source single sign-on solution that comes from the code base of Sun's System Access Manager product. Liferay integrates with OpenSSO, allowing you to use OpenSSO to integrate Liferay into an infrastructure that contains a multitude of different authentication schemes against different repositories of identities.

You can set up OpenSSO on the same server as Liferay or a different box. Follow the instructions at the OpenSSO site [http://opensso.dev.java.net](http://opensso.dev.java.net/) to install OpenSSO. Once you have it installed, create the Liferay administrative user in it. Users are mapped back and forth by screen names. By default, the Liferay administrative user has a screen name of *test*, so in OpenSSO, you would register the user with the ID of *test* and an email address of *test@liferay.com*. Once you have the user set up, log in to Open SSO using this user.

In the same browser window, go to the URL for your server running Liferay and log in as the same user, using the email address *test@liferay.com*. Go to the control panel and click *Settings &rarr; Authentication &rarr; OpenSSO*. Modify the three URL fields (Login URL, Logout URL, and Service URL) so that they point to your OpenSSO server (i.e., only modify the host name portion of the URLs), click the *Enabled* check box, and then click *Save*. Liferay will then redirect users to OpenSSO when they click the *Sign In* link.

##### Authentication: SiteMinder

SiteMinder is a single sign-on implementation from Computer Associates. Liferay 5.2 introduced built-in integration with SiteMinder. SiteMinder uses a custom HTTP header to implement its single sign-on solution.

To enable SiteMinder authentication in Liferay, check the *Enabled* box on the *SiteMinder* tab. If you are also using LDAP with Liferay, you can check the *Import from LDAP* box. If this box is checked, user authenticated from SiteMinder that do not exist in the portal will be imported from LDAP.

The last field defines the header SiteMinder is using to keep track of the user. The default value is already populated. If you have customized the field for your installation, enter the custom value here.

When you are finished, click *Save*.

#### Users

The Users page of Portal Settings has three tabs: Fields, Reserved Credentials, and Default User Associations.

The Fields tab allows you to enable/disable the following fields:

-   Enable/disable requiring the Terms of Use

-   Enable/disable user screen names autogeneration

-   Enable/disable requiring the last names

-   Enable/disable the birthday field

-   Enable/disable the gender field

The next tab is Reserved Credentials. You can enter screen names and email addresses here that you don't want others to use. Liferay will then prevent users from registering with these screen names and email addresses. You might use this feature to prevent users from creating IDs that look like administrative IDs or that have reserved words in their names.

The Default User Associations tab has three fields allowing you to list (one per line) sites, roles, and user groups that you want new users to become members of automatically. By default, Liferay assigns new users to both the Users role and the Power Users role.

If you have defined other user groups, sites, or roles that you want newly created users to be members of by default, enter them here. For example, you may have defined page templates in certain user groups to pre-populate end users' private pages. If there is a particular configuration that you want everyone to have, you may want to enter those user groups here.

#### Mail Host Names

The next page under Portal Settings is Mail Host Names. You can enter (one per line) other mail host names besides the one you configured on the General tab. This lets the portal know which mail host names are owned by your organization.

#### Email Notifications

There are five tabs under the Email Notifications page of Portal Settings. The Sender tab allows you to set the portal's administrative name and email address. By default, these are `Joe Bloggs` and `test@liferay.com`. You can change them to whatever you want. This name and email address will appear in the From field in all email messages sent by the portal.

The other four tabs are Account Created Notification, Email Verification Notification, Password Changed Notification, and Password Reset Notification. These tabs allow you to customize the email messages that are sent to users each time any of those four events occur.

![Figure 12.20: Automated Emails: Account Created Notification][../../images/server-configuration-account-created-notification.png]

![Figure 12.21: Definition of Terms for Automated Emails][../../images/server-configuration-definition-of-terms.png]

A list of tokens, entitled "Definition of Terms," is provided so that you can insert certain values (such as the portal URL or the user ID) when you are setting up the custom email messages.

#### Identification

The identification section has several links for addresses, phone numbers, and other information you can configure in your portal. This allows you to set up contact information for the organization that owns the portal. Developers can query for this information in their applications.

#### Miscellaneous: Display Settings

This section allows you to set the default portal language and the time zone. You can also set up a portal-wide logo which appears in the top left corners of portal pages. 

![Figure 12.22: Server Configuration Miscellaneous Display Settings][../../images/server-configuration-miscellaneous-display-settings.png]

Liferay's default theme is configured to display the portal logo. For custom themes, you can choose whether or not to display the logo. Be careful to choose an image file that fits the space. If you pick something that is too big, it might overlap with the navigation.

### Custom Fields

Custom fields are a way to add attributes to many types of assets in the portal. For example, if you're using Liferay Portal to create a site for rating books, you might assign the User object a custom field called Favorite Books. If you're using the wiki for book reviews, you might add fields for Book Title and Book Author.

To add a custom field, click on the *Custom Fields* link in the control panel. Then choose a resource, click on the *Edit* link next to it and select *Add Custom Field*.

![Figure 12.23: Custom Fields Resource List][../../images/server-configuration-custom-fields-resource-list.png]

From here you will need to add the custom field key. The key appears as the label for the field on the form. For some portal assets (like the User), custom fields are a separate section of the form. For others, as can be seen above, custom fields are integrated with the default fields on the form. Additionally, developers can access custom fields programatically through the `<liferay-ui:custom-attribute />` tag.

![Figure 12.24: Custom Fields Integrated with the Rest of the Fields on the Wiki Form](../../images/server-configuration-custom-fields-integrated.png)

You can create fields of many different types: text fields (indexed or secret), integers, selection of multiple values, and more. Once you've created a field, you cannot change its type.

### Monitoring

The next link on the left side of the control panel is for monitoring. You can use the Monitoring page to view all of the live sessions in the portal. For performance reasons, this setting is usually turned off in production.

### Plugins Configuration

The Plugins Configuration page contains tabs for three types of plugins: portlets, themes, and layouts. You can use these tabs to view which roles can add plugins to pages or you can make the plugins active or inactive.

Note that this is for basic configuration: if you want to view the existing permission configuration for a given portlet and/or modify that configuration for existing roles, this is where you can do that. If you need to add permissions to new roles for a given portlet, use the Roles section of the control panel and the *Actions &rarr; Define Permissions* button.

### Page Templates

The Page Templates page of the control panel allows you to create a custom page with the layout, portlets, and web content that you want to reuse. From this link you can also edit existing templates and modify their permissions.

![Figure 12.25: You can create page templates with predefined layouts and portlets that can be used over and over.](../../images/server-configuration-page-templates.png)

To add a page template click on the *Page Template* link in the control panel. From there select *Add* and then enter a name and description for your template. Below the description field is a check box that lets you make the template active or inactive, and below this is a link to open the page template so you can edit it. The template pictured above might be for returning university students. Clicking the *Open Page Template* link brings you to the Manage Pages interface, which we examined in chapter 3.

Once you're finished adding the content, layout, and portlets, return to the Page Template portlet (which is in another browser tab) and select *Save*.

When you want to use the new template to create a new page on a site, simply navigate to *Control Panel &rarr; Pages* and the page is available in the template drop down menu when you're adding a page.

### Site Templates

The Site Templates link allows you to define a set of predefined pages to include when creating new sites. You may define and edit site templates as well as their permissions.

![Figure 12.26: Site templates help you to quickly build many similar sites using the same pattern](../../images/server-configuration-site-templates.png)

To add a site template click on the *Site Template* link in the control panel. From there you can select *Add* and then enter a name and description for your template. Also, below the description field is where you can make this template active.

Once this is complete, click on *Actions &rarr; Manage Pages*. From here you can add pages to your template, configure the look and feel by managing themes or CSS files, and export the template as a `.lar` file. You can also import a `.lar` file from here as well.

On the pages you've created for the site template, you can add the portlets and content you want. Once you've added the pages, portlets, content, and themes, you can use this template when you create a new site. The template will appear the in the drop down menus for the Public Pages/Private Pages options when you are adding a site.

### Server Administration

The Server Administration page of the control panel lets you perform various tasks related to the portal server itself, as opposed to the resources in the portal. Clicking the link makes this clear: you're immediately presented with a graph showing the resources available in the JVM.

#### Resources

The first tab is called *Resources* . This tab contains the aforementioned graph plus several server wide actions that an administrator can execute. These are:

**Garbage collection:** You can send in a request to the JVM to begin the garbage collection task.

**Clearing VM caches:** You can send in a request to the JVM to clear a single VM cache.

**Clearing caches across the cluster:** You can send in a request to the JVM to clear content cached across the entire cluster.

**Clearing database caches:** You can send in a request to the JVM to clear the database cache.

**Reindex all search indexes:** You can send in a request to regenerate all search indexes. If you are not using a Solr search server this will impact portal performance, so try not to do this except at non-peak times.

**Reset Document Library preview and thumbnail files:** You can send in a request to reset the preview and thumbnail files for each item in your portal's Documents and Media libraries.

**Generate Thread Dump:** If you are performance testing, you can generate a thread dump which can be examined later to determine if there are any deadlocks and where they might be.

**Verify database tables of all plugins:** Checks all tables against their indexes for accuracy of data retrieval.

**Clean up Permissions:** This process removes the assignment of some permissions on the Guest, User and Power User roles in order to simplify the management of "User Customizable Pages". Notably, "Add To Page" permissions is removed from the Guest, and User role for all portlets. Likewise the same permission is reduced in scope for Power Users from portal wide to scoped to "User Personal Site".

#### Log Levels

The Log Levels tab of the Server Administration page allows you to dynamically modify the log levels for any class hierarchy in the portal. If you have custom code that you have deployed which isn't in the list, you can use the *Add Category* tab to add it. If you change the log level near the top of the class hierarchy (such as at `com.liferay`), all the classes under that hierarchy will have their log levels changed. If you are testing something specific, it is much better to be as specific as you can when you change log levels. Modifying them too high in the hierarchy generates a lot more log messages than you need.

#### Properties

Liferay and the JVM contain many settings which are defined as properties. There are two subtabs of the properties tab of the Server Administration page: one showing system properties and one showing portal properties.

The system properties tab shows an exhaustive list of system properties for the JVM, as well as many Liferay system properties. This information can be used for debugging purposes or to check the configuration of the currently running portal.

The portal properties tab tab shows an exhaustive list of the portal properties. These properties can be customized, as we saw in chapter 12. If you need to check the current value of a particular property, it can be viewed from this screen without having to shut down the portal or open any properties files.

#### Captcha

By default, Liferay ships with its own simple captcha service which is designed to thwart bots from registering for accounts on sites powered by Liferay. If you want to instead use Google's reCaptcha service, you can enable this setting from the Captcha tab of the Server Administration page.

Simply check the *Enable ReCaptcha* box and enter your public and private keys into the provided fields, and then click *Save*. Liferay Portal will then use reCaptcha instead of simple captcha.

#### Data Migration

If you are upgrading from a previous release of Liferay Portal or if you need to migrate your data from one system to another, the Data Migration tab helps you to do that without your developers having to write custom scripts.

The first section lets you copy your entire Liferay database from the current database under which it is running to the database you specify in this set of fields. You'll need to enter the driver class name (and the driver will need to be on Liferay's classpath), the JDBC URL of the database to which you'll be copying your data, and the credentials of a user that has access to that database. Once you have all of this information entered, click *Execute* to copy the data.

The next section helps you migrate your documents. If you want to move off of the Jackrabbit JSR-170 repository to the file system, or to the Jackrabbit repository from the file system, or to any of the other repositories supported by the documents and media library, you can do so very easily. Make sure you have already set up your `portal-ext.properties` file so that the hook is properly configured before running this migration. Select the Document Library hook that represents where you want your documents migrated, and click *Execute*. Your documents will be migrated to the new repository, and you can then shut down Liferay, make the new repository the default in the `portal-ext.properties` file, and then restart.

Similarly, you can migrate images from the Image Gallery in the same manner.

#### File Uploads

Since Liferay allows users to upload files in various places, you may want to lock down the type of files and the size of files users are allowed to upload. The File Uploads tab of the Server Configuration tab lets you set the overall maximum file size and then override that size for specific applications within Liferay. You can limit the allowed file extensions generally or by application. You have a lot of flexibility as to how you want files to be managed within your portal.

#### Mail

Rather than using the `portal-ext.properties` file as we did in the installation chapter, you can configure a mail server from the Mail tab of the Server Configuration tab. If the portal is to receive mail (see the Message Boards portlet in Chapter 4), you can connect a POP mail server. If the portal is to send mail, which it needs to do in order to send notifications to users, you can connect to an SMTP server here as well, and this is highly recommended.

Note that if you add your mail server settings here, they will override anything that is in your `portal-ext.properties` file.

#### External Services

Liferay Portal enables users to add content in many formats: web content, images, and files. This is done using the Web Content Management System, the Image Gallery, the Documents and Media Library, and in other portlets, both built-in and custom written. Sometimes, it is helpful to convert this content from its original format to one that is more convenient for the user browsing the content. Liferay Portal allows users to do this by integrating with OpenOffice and Xuggler.

OpenOffice is an open source office suite which usually runs in graphical mode to create documents, but it can also be run in “server” mode. When run in server mode, OpenOffice can be used to convert documents to and from all of the file types it supports. Liferay can then make use of this feature to automatically convert content on the fly. Xuggler provides video conversion functionality.

Use the External Services tab to tell Liferay how to connect to your running instance of OpenOffice or Xuggler. You can install OpenOffice on the same server upon which Liferay is running. Once you have it installed, you can start OpenOffice in server mode with the following command:

    soffice -headless -accept="socket,host=127.0.0.1,port=8100;urp;"
    -nofirststartwizard

As you can see, the command above specifies that OpenOffice will run on port 8100, which is the default port in the control panel . If you can use this port, all you need to do is check the *Enabled* box, and Liferay will be integrated with OpenOffice.

If you have something else running on this port, find a port that is open and specify it both in the command above and on the control panel's External Services configuration page. When you are finished, click *Save*.

#### Script

Liferay includes a scripting console which lets administrators execute migration or management code instantly. Several scripting languages are supported, including JavaScript, Groovy, Python, Ruby, and Beanshell. For further information about Liferay's APIs, see the JavaDoc, the Liferay Wiki ([http://wiki.liferay.com](http://wiki.liferay.com/)), or *Liferay in Action*.

#### Shutdown

If you ever need to shut down your Liferay Portal server while users are logged in, you can use the Shutdown tab to inform your logged-in users of the impending shutdown. You can define the number of minutes until the shutdown and a custom message that will be displayed.

Users will see your message at the top of their portal pages for the duration of time you specified. When the time expires, all portal pages will display a message saying the portal has been shut down. At this point, the server will need to be restarted to restore access.

### Portal Instances

Liferay Portal allows you to run more than one portal instance on a single server. The Portal Instances page of the control panel lets you manage these instances. Data for each portal instance are kept separate from every other portal instance. All portal data, however, is kept in the same database.

Each portal instance requires its own domain name. Liferay will direct users to the proper portal instance based on this domain name. So before you configure an instance, configure its domain name in your network first. When you're ready to add an instance, click the *Add* button here.

You'll be prompted for four fields and a check box:

**Web ID:** A general convention is to use the domain name for this. It's a user-generated ID for the instance.

**Virtual Host:** Put the domain name you configured in your network here. When **users** are directed to your Liferay server via this domain name, Liferay will then be able to send them to the proper portal instance.

**Mail Domain:** Enter the domain name for the mail host for this instance. Liferay will use this to send email notifications from the portal.

**Max Users:** Enter the maximum numbers of user accounts you would like your portal instance to support.

**Active:** Use this check box to choose whether to create an active or an inactive portal instance.

When you are finished filling out the form, click *Save*. Now navigate to the portal using your new domain name. You will see that you are brought to what looks like a clean install of Liferay. This is your new portal instance which can now be configured any way you like.

### Plugins Installation

The *Plugins Installation* page of the control panel shows all of the plugins that are currently installed. These are divided into tabs for portlets, themes, layout templates, hook plugins, and web plugins. If you want to install a new plugin, click the *Install More Portlets* button. You will then be brought to the Plugin Installer, where you can browse Liferay's repository of portlets or install your own plugins. We covered the plugins installer in chapter 2.

## Summary

In this chapter we described how to use Liferay's control panel to manage users, user groups, organizations, sites, and roles. We showed how to configure various server settings, such as authentication, LDAP integration, and single sign-on. We also learned how to associate users by default with different user groups, sites, and roles, and we saw how to reserve screen names and email addresses so that users cannot register in the portal with them.

Next, we saw how to view and configure overall server settings. We saw how to view the memory currently being used by the server, as well as how to initiate garbage collection, a thread dump, search engine re-indexing, and the clearing of various caches. We learned how to debug parts of the portal by changing log levels, and by viewing the various properties that are defined in the portal.

Finally, we learned how to properly notify users that the portal is about to shut down and how to set up exernal services like OpenOffice integration. We looked at how to create multiple portal instances on a single installation of Liferay and we showed how to view currently installed plugins.

We hope this information helps you become an effective Liferay Portal Administrator.
