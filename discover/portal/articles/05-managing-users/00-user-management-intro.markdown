# User Management [](id=user-management)

You know how all these retailers advertise themselves as a "one stop shop" for
anything you want? The idea is they have so much stuff that whatever you're
looking for is probably there. Liferay's Control Panel is something like
this. If you want to create users, organizations, sites, configure permissions
and plugins and pretty much anything else, you can do it from the Control
Panel. When signed in to Liferay as an administrator, you can access the
Control Panel from the Product Menu by clicking *Control Panel*.

![Figure 1: Administrators can access the Control Panel from the Product
Menu.](../../images/usrmgmt-control-panel.png)

The Control Panel is divided into five main areas: Sites, Users, Apps,
Configuration, and System. The Users section lets you create and manage users,
organizations, user groups, roles, and password policies. If monitoring has been
enabled for your portal, you can also view all of the live portal sessions of
your users from this area of the Control Panel. If the Audit plugins have been
installed from Liferay Marketplace, the Audit section also appears.

The Users section of the Control Panel is used for most administrative tasks
involving user management. There, you'll find an interface for the creation and
maintenance of the following portal entities: users, organizations, user groups,
and roles. 

Managing sites, teams, site templates, and page templates is covered in detail
elsewhere. Remember that it's possible and sometimes simpler to use sites, site
memberships, and teams to organize users and manage permissions than it is to
use organizations, user groups, and custom roles.

As a portal administrator, you'll use the Users section of the Control Panel to
create users, organizations, and user groups, implement security via roles and
permissions, and administer your users. Only users with the administrator role,
which is a portal scoped role, have permission to view the Control Panel. You
can, however, grant permissions to view the Control Panel to existing or custom
roles.

In this set of articles you'll learn about managing users. While you could
probably get started learning about managing users in Liferay just by perusing
the Control Panel, there's so much User Management functionality in Liferay that
it's tough to wrap your head around it all. Maybe a limerick can help:

*There's a company in Spain that makes Hula Hoops*<br />
*They placed* ***Users*** *in* ***Organizations*** *and* ***User Groups***<br />
*They assigned them their* ***Roles***<br />
*And set them some goals*<br />
*Made* ***Password Policies*** *that can't be duped.*

<!-- Work Monitoring into the limerick? Delete the limerick and fire its author?
You be the judge. -->

![Figure 2: The Users section of the Control Panel. This screenshot shows a portal that doesn't have the EE Audit plugin installed.](../../images/usrmgmt-users.png)

Begin exploring Liferay's User Management functionality by reading about
adding and editing users. 

<!-- Perhaps make a table showing similarities and differences between the
parallel sites vs. portal-wide user functionality
-->
