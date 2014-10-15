# Creating Teams for Advanced Site Membership Management [](id=creating-teams-for-advanced-site-membership-management)

Teams don't appear as a link in the Control Panel because they exist *within*
sites. Teams allow site administrators a greater degree of flexibility than was
possible using just user groups and roles. They allow site administrators to
create various sets of users and permissions for site-specific functions. Teams
are the preferred method for collecting permissions within a single site.

If you create a team for one site, the permissions defined for it are not
available to any other sites. In contrast, if you assigned a custom role to a
user group, the role would be available portal-wide even though the specific
permissions defined by it would only apply within the scope of a designated
site. Furthermore, team members, unlike user group members, are guaranteed to be
members of the desired site.

To create a team within a site, first navigate to the *Control Panel &rarr;
Sites* page then and then select *Actions &rarr; Manage Memberships* for the
site within which you want to create a team. Finally, click *View &rarr; Teams*
and click the Add Team button.

![Figure 3.26: Creating a Team within a Site](../../images/01-creating-a-team.png)

After you've clicked the *Add Team* button and entered a name and a description,
click *Save*. Your new team will appear in the list. To add members, simply
click on *Actions &rarr; Assign Members*.

Permission management for teams is handled at the individual portlet level,
using the *Options &rarr; Configuration &rarr; Permissions* tab of the portlet
itself. Remember the portlet options link is the wrench symbol at the top of a
portlet. This enables users who wouldn't have access to all of the necessary
options in the Control Panel to manage permissions through teams.

![Figure 3.27: Assigning Portlet Permissions to a Team](../../images/01-assigning-portlet-permissions-to-teams.png)

To give a team access to a particular portlet function, access the *Permissions*
tab of a portlet residing on a page, check the boxes corresponding to
permissions you want to assign to the teams, then click *Save*. That's it! Now
your team is ready to perform their functions. Next, let's look at how to
configure Liferay for mobile devices.
