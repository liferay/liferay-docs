# Creating Teams for Advanced Site Membership Management [](id=creating-teams-for-advanced-site-membership-management)

Site Teams are ad hoc groups of a site's users, assigned to a team to perform a
specific task in the site. They allow site administrators to create various
sets of users and permissions for site-specific functions. Teams are the
preferred method for collecting permissions within a single site. Some common
uses for Site Teams include:
- Moderating site Wiki content, Message Boards threads, etc.
- Creating a group of bloggers
- Editing a specific page in the site

For instance, If your site uses a Message Boards portlet, allowing users to
post threads, you might want to enable a subset of the site's members to
moderate the categories and threads of the Message Boards, and perhaps even
allow team members to ban abusive or offensive posters. To do this you could
simply create a team *Web Moderators*, define the team's permissions on the
Message Boards portlet, and assign the desired users (who must be site members)
to the Site Team.

If you create a team for one site, the permissions defined for it are not
available to any other sites. In contrast, if you assigned a custom role to a
user group, the role would be available portal-wide even though the specific
permissions defined by it would only apply within the scope of a designated
site. Furthermore, team members, unlike user group members, are guaranteed to be
members of the desired site.

To create a team within a site, first navigate to the Site Administration page
of your site and select *Users* &rarr; *Site Teams*. It's important to note that
configuring other site membership groupings, such as *Users*, *Organizations*,
and *User Groups* can be found in the *Site Memberships* portlet, which is also
located in the Users tab. You can visit the *Management* chapter for more
information on how these site memberships. Finally, click the *Add Team* button.

![Figure 3.26: Creating teams within your site helps your users demonstrate teamwork and collaboration.](../../images/01-creating-a-team.png)

After you've clicked the *Add Team* button and entered a name and a description,
click *Save*. Your new team will appear in the list. To add members, simply
click on *Actions &rarr; Assign Members*.

Permission management for teams is configured by navigating to *Actions* &rarr;
*Permissions* for your individual site. Setting permissions for the team assigns
every member of the team with equal permissions. Only administrators with the
ability to edit/manage the team have the ability to manage team permissions.

If your team's task is to moderate the Message Boards portlet, you'll want to
give the team any permissions they should have for the Message Boards. Navigate
to *Site Administration* &rarr; *Content* &rarr; *Message Boards* &rarr;
*Permissions*, find your team in the Role column, and select the appropriate
permissions. 

![Figure 3.27: The Troll-busters Site Team has unlimited permissions on the Message Boards portlet.](../../images/site-team-permissions-message-boards.png)

That's it! Now your team is ready to perform their functions. Next, let's look
at how to configure Liferay for mobile devices.
