# Creating Teams for Advanced Site Membership Management [](id=creating-teams-for-advanced-site-membership-management)

If you have an ad hoc group of users who perform the same set of tasks in a
site, you can organize them into Site Teams. Site administrators can assign
these teams permissions for various site-specific functions. Site Teams are the
preferred method for collecting permissions within a single site. Some common
functions to assign a Site Team include these:

- Moderating site Wiki content, Message Boards threads, etc.
- Writing blogs
- Editing a specific page in the site

For instance, if your site has Message Boards, you might want to enable a subset
of the site's members to moderate the categories and threads, and perhaps to ban
abusive/offensive posters. To do this, you could create a Site Team named
*Message Board Moderators*, define the team's permissions on the Message Boards
portlet, and assign the desired site members to the team. 

The permissions assigned to a Site Team only apply to that site. Knowing that
a team's permissions don't impact other sites, site administrators can
concentrate on defining and applying permissions to their sites' teams. 

+$$$

To create and apply permissions for a group of users to use across multiple
sites or organizations in your portal, consider aggregating the users into a
[User Group](/discover/portal/-/knowledge_base/6-2/user-groups) and assigning
the User Group permissions via
[Roles](/discover/portal/-/knowledge_base/6-2/roles-and-permissions). 

$$$

To create a team within a site, first navigate to the Site Administration page
of your site and select *Users* &rarr; *Site Teams*. It's important to note that
configuring other site membership groupings, such as *Users*, *Organizations*,
and *User Groups* can be done in the *Site Memberships* portlet, which is also
located in the Users tab. You can visit the 
[User Management](/discover/portal/-/knowledge_base/6-2/user-management)
chapter for more information on how these site memberships work. Finally, click the
*Add Team* button. 

![Figure 3.26: Creating teams within your site can foster teamwork and collaboration, as team permissions enable team members to access the same resources and perform the same types of tasks.](../../images/01-creating-a-team.png)

After you've clicked the *Add Team* button and entered a name and a description,
click *Save*. Your new team shows in the list. To add members, click on *Actions
&rarr; Assign Members*.

To manage a team's permissions, click on *Actions* &rarr; *Permissions* for that
team. Setting permissions for the team assigns all of the team's members those
permissions. Only administrators with the ability to edit/manage the team have
the ability to manage team permissions.

If you created team whose task is to moderate the Message Boards, for example,
you'd want to give the team all the permissions they'd need. To do this, 
Navigate to *Site Administration* &rarr; *Content* &rarr; *Message Boards*
&rarr; *Permissions*, find the team in the Role column, and select the
appropriate permissions.

![Figure 3.27: The Troll-busters Site Team has unlimited permissions on the Message Boards portlet.](../../images/site-team-permissions-message-boards.png)

That's it! It's easy to give groups of site users appropriate permissions to
perform their tasks. Next, let's look at how to configure Liferay for mobile
devices.
