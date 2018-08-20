# Creating Teams to Empower Site Members [](id=creating-teams-for-advanced-site-membership-management)

If you have an *ad hoc* group of Users who perform the same set of tasks in
a Site, you can organize them into Site Teams, and then assign the team
permissions for various site-specific functions. Site Teams are the preferred
method for collecting permissions within a single Site. Some common functions to
assign a Site Team include

- Moderating site Wiki content
- Managing Message Boards threads
- Writing blogs
- Editing a specific page in the site

If your Site has Message Boards, you might want to enable a subset of the 
Site's members to moderate the categories and threads, and perhaps to ban
abusive/offensive posters. To do this, you could create a Site Team named
*Lunar Resort Message Board Moderators*, define the team's permissions in the
Message Boards application, and assign the desired Site members to the team. 

The permissions assigned to a Site Team only apply to that Site. The two key
features of Teams are that they are limited to their Sites and that they empower
Site Administrators to manage permissions for their Sites since Site
Administrators cannot create new Roles.

+$$$

**Note:** To create and apply permissions for a group of users to use across
multiple Sites or Organizations in your Liferay instance, consider aggregating
the Users into a [User Group](/discover/portal/-/knowledge_base/7-1/user-groups)
and assigning the User Group permissions via
[Roles](/discover/portal/-/knowledge_base/7-1/roles-and-permissions).

$$$

To create a team within a Site,

1.  Go to the Site Administration page of your Site.

2.  Select *Members* &rarr; *Site Teams*.

    It's important to note that configuring other Site membership groupings, 
    such as *Users*, *Organizations*, and *User Groups* can be done in the 
    *Site Memberships* app, which is also in the Members tab. You can 
    visit [User Management](/discover/portal/-/knowledge_base/7-1/user-management)
    for more information on how Site memberships work.

3.  Finally, click the *Add Team* icon (![Add Team](../../../../images/icon-add.png)).

![Figure 1: Creating teams within your site can foster teamwork and collaboration, as team permissions enable team members to access the same resources and perform the same types of tasks.](../../../../images/creating-a-team.png)

4.  Enter a name and a description and click *Save*. Your new team shows in the list.

5.  To add members, click on the team name link and then select *Add Team 
    Members*.

To manage a team's permissions, click the *Actions* icon
(![Actions](../../../../images/icon-actions.png)) and select *Permissions* for
that team. Setting permissions for the team assigns those permissions to all the
team's members. Only administrators who can edit/manage the team can manage team
permissions.

If you created a team whose task is to moderate the Message Boards, for example,
you'd want to give the team all the permissions they'd need.

1.  Go to *Site Administration* &rarr; *Content* &rarr; *Message Boards*.

2.  Select *Home Category Permissions* from the *Options* icon
    (![Options](../../../../images/icon-options.png)) in the top right of the 
    screen.

3.  Find the Team in the Role column and select the appropriate permissions.

![Figure 2: The Lunar Resort Message Board Moderators Site Team has unlimited permissions on the Message Boards application.](../../../../images/site-team-permissions-message-boards.png)

That's it! It's easy to give groups of Site Users permissions to perform their
tasks. 

These tutorials have introduced you to @product@ Site management. You've learned
how to use @product@ to create multiple Sites with different membership types.
You've also seen how easy it is to create and manage Sites and to create and
manage pages within Sites. Next, you'll begin working with web content.
