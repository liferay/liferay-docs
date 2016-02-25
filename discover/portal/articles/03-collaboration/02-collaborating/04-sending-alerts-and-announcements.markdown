# Sending Alerts and Announcements [](id=sending-alerts-and-announcements)

Suppose you're running a Liferay instance with lots of users. Maybe you have 
thousands or tens of thousands of users (or more!). Wouldn't it be nice to have 
an easy way to periodically communicate important information to them? And 
wouldn't it be even nicer to have easy ways to periodically send information to 
specific groups of users? For example, maybe you'd like to send a reminder about 
a new policy to all of your Liferay instance's site administrators. Or maybe 
you'd like to send a reminder to all the members of a certain site to submit an 
answer to this week's poll question. Liferay provides two apps that meet this 
need: the Alerts app and the Announcements app. 

![Figure 1: The Alerts app provides administrators with an easy way to communicate important information to appropriate groups of users.](../../../images/alerts-app.png)

The Alerts and Announcements apps let you broadcast important information to
different groups of users. Each alert or announcement can be created in a
specific scope so that you can manage which announcements are sent to which
users. Furthermore, each user can configure how they'd like to receive 
announcements: click on *[User Name]* &rarr; *My Account* &rarr; *Account 
Settings* from the Menu and then on *Announcements* in the Miscellaneous 
category. This brings up a menu for customizing the delivery options for alerts 
and announcements. You can select a different configuration for each type of 
alert or announcement: General, News, or Test. For each type, you can enable 
delivery by email and SMS (text message). Note that the *Website* delivery 
option is checked and grayed out for each alert type. This means that each alert 
and announcement is always viewable in its respective app on a site. 

![Figure 2: Each user can choose how they receive alerts and announcements.](../../../images/announcements-delivery.png)

You can also create roles in Liferay for users to make general announcements. 
For instance, if you'd like an employee in your site to have strict control over 
what is announced, you can assign them to an Announcements role. To create a 
simple Announcements role, navigate to *Control Panel* &rarr; *Users* &rarr; 
*Roles*, click the *Add* button 
(![Add](../../../images/icon-add.png)), and select *Regular Role*. Name your 
role *Announcements*, give it a title and description, and click *Save*. Then 
click the *Actions* button 
(![Actions](../../../images/icon-actions.png)) next to your new role and select 
*Define Permissions*. For this particular role, you'll need to grant two 
permissions: 

- *Control Panel* &rarr; *General Permissions* &rarr; *Add General 
  Announcements*
- *Site Administration* &rarr; *Applications* &rarr; *Announcements* &rarr; *Add 
  Entry*

Click *Save* after selecting each permission. Now you have a simple 
Announcements role that can add an entry to the Announcements app and add 
general announcements to your site! Of course, you can add additional 
permissions to this role if you prefer. 

![Figure 3: The Announcements app displays a list of announcements to regular users. It also provides administrators with an interface for managing and adding entries.](../../../images/announcements-app.png)

Now you'll learn how to create announcements in the Announcements app. When you
view the Announcements app as an administrator, you'll see three tabs: New, 
Previous, and Manage Entries. The New tab displays non-expired announcements, 
and announcements you haven't marked as read. Once an announcement expires or 
you mark it as read, it moves to the Previous tab. Administrators can use the 
Manage Entries tab to create new announcements. Non-administrators only see the 
New and Previous tabs. To add a new announcement, click *Manage Entries* and 
then select a *Distribution Scope*. The distribution scope determines the group
of users to which your announcement is sent. Announcements created in the
General distribution scope are sent to everyone. If the distribution scope is
set a particular site or role, only members of that site or role receive the
announcements. 

Once you've selected a distribution scope, you'll see a list of that scope's 
existing announcements. Click an announcement's *Actions* button to edit or 
delete it. Click *Add Entry* to create a new announcement. In addition to the 
Distribution Scope, you can provide the following information: 

**Title:** The announcement's title. When the app displays the announcement, the 
full title always appears in bold. If you enter a URL in the URL field, the 
title serves as a link to the specified site. 

**URL:** The URL is optional. If entered, it must be valid and begin with 
*http://* or *https://*. For example, an announcement about a news story could 
include a link to the news article. 

**Content:** The announcement's body text. You can use the familiar CK editor to
write this. Use the *Source* button to switch between the editor and HTML views.

**Type:** The announcement's type. This can be *General*, *News*, or *Test*. As 
mentioned earlier, each user can specify a different delivery mechanism for each 
type of announcement. For example, a user could choose to receive General 
announcements via email and text,  News announcements via email only, and no 
special delivery mechanism for Test announcements. Each user can customize their 
delivery options by navigating to *[User Name]* &rarr; *My Account* &rarr; 
*Account Settings* from the Menu, and then selecting *Announcements* in the 
Miscellaneous category. 

**Priority:** The announcement's priority. This can be *Normal* or *Important*.

**Display Date:** The announcement's display date. This determines when the 
announcement is sent to users and appears in the Announcements app. By default, 
the *Display Immediately* box is checked. This sets the display date equal to 
the creation date. Uncheck this box to enter a custom display date. For example, 
administrators can create announcements for display on a later date. This date 
can be days, weeks, months, or years in the future. Once the *Display 
Immediately* box is unchecked, clicking the Display Date field opens the 
date-picker widget. 

**Expiration Date:** The date and time the announcement expires. Once an 
announcement expires, the Announcements app displays it in the Previous tab. 
Clicking the Expiration Date field opens the date-picker widget. 

The Alerts app works the same way as the Announcements app. Alerts is a separate 
app because it's designed to be used only for high priority messages or those 
that require users to take some action. The Alerts app only displays alerts, and 
the Announcements app only displays announcements. To draw attention to its 
alerts, the Alerts app displays a red *Important* tag next to each. To separate 
important alerts from more mundane announcements, site administrators can place 
the Alerts and Announcements apps on different pages. 
