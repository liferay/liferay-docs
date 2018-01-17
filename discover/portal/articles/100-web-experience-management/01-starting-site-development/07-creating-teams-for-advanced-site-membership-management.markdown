# Creating Teams for Advanced Site Membership Management [](id=creating-teams-for-advanced-site-membership-management)

If you have an ad hoc group of users who perform the same set of tasks in a
site, you can organize them into Site Teams. Site administrators can assign
these team's permissions for various site-specific functions. Site Teams are the
preferred method for collecting permissions within a single site. Some common
functions to assign a Site Team include:

- Moderating site Wiki content
- Managing Message Boards threads
- Writing blogs
- Editing a specific page in the site

For instance, if your site has Message Boards, you might want to enable a subset
of the site's members to moderate the categories and threads, and perhaps to ban
abusive/offensive posters. To do this, you could create a Site Team named
*Lunar Resort Message Board Moderators*, define the team's permissions in the
Message Boards application, and assign the desired site members to the team. 

The permissions assigned to a Site Team only apply to that site. Knowing that
a team's permissions don't impact other sites, site administrators can
concentrate on defining and applying permissions to their sites' teams. 

+$$$

**Note:** To create and apply permissions for a group of users to use across
multiple sites or organizations in your Liferay instance, consider aggregating
the users into a [User Group](/discover/portal/-/knowledge_base/7-0/user-groups)
and assigning the User Group permissions via
[Roles](/discover/portal/-/knowledge_base/7-0/roles-and-permissions).

<!-- Verify link above to 7.0, when available. -Cody -->

$$$

To create a team within a site, first navigate to the Site Administration page
of your site and select *Members* &rarr; *Site Teams*. It's important to note that
configuring other site membership groupings, such as *Users*, *Organizations*,
and *User Groups* can be done in the *Site Memberships* app, which is also
located in the Members tab. You can visit the 
[User Management](/discover/portal/-/knowledge_base/7-0/user-management)
chapter for more information on how these site memberships work. Finally, click
the *Add Team* icon (![Add Team](../../../images/icon-add.png)).

![Figure 1: Creating teams within your site can foster teamwork and collaboration, as team permissions enable team members to access the same resources and perform the same types of tasks.](../../../images/creating-a-team.png)

After you've clicked the *Add Team* button and entered a name and a description,
click *Save*. Your new team shows in the list. To add members, click on the
team name link and then select *Add Team Members*.

To manage a team's permissions, click on the *Actions* icon
(![Actions](../../../images/icon-actions.png)) and select *Permissions* for that
team. Setting permissions for the team assigns all of the team's members those
permissions. Only administrators with the ability to edit/manage the team have
the ability to manage team permissions.

If you created a team whose task is to moderate the Message Boards, for example,
you'd want to give the team all the permissions they'd need. To do this, 
navigate to *Site Administration* &rarr; *Content* &rarr; *Message Boards*
and select *Permissions* from the *Options* icon
(![Options](../../../images/icon-options.png)) in the top right of the screen. Find
the team in the Role column, and select the appropriate permissions.

![Figure 2: The Lunar Resort Message Board Moderators Site Team has unlimited permissions on the Message Boards application.](../../../images/site-team-permissions-message-boards.png)

That's it! It's easy to give groups of site users appropriate permissions to
perform their tasks. This chapter has provided an introduction to @product@ site
management. You’ve learned how to use @product@ to create multiple sites with
different membership types. You've also seen how easy it is to create and manage
sites and to create and manage pages within a site in @product@. Next, you'll
begin working with web content.
