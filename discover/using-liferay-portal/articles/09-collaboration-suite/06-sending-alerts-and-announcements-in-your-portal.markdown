# Sending Alerts and Announcements in Your Portal [](id=sending-alerts-and-announcements-in-your-portal)

Suppose you're running a portal with lots of users. Maybe you have thousands or
tens of thousands of users (or more!). Wouldn't it be nice to have an easy way
to periodically communicate important information to them? And wouldn't it be
even nicer to have easy ways to periodically send information to specific groups
of users? For example, maybe you'd like to send a reminder about a new policy to
all of the site administrators on your portal. Or maybe you'd like to send a
reminder to all the members of a certain site to submit an answer to this week's
poll question. Liferay provides two portlets that meet this need: the Alerts
portlet and the Announcements portlet.

![Figure 8.31: The Alerts portlet provides administrators with an easy way to communicate important information to appropriate groups of users.](../../images/alerts-portlet.png)

The Alerts and Announcements portlets let you broadcast important information to
different groups of users. Each alert or announcement can be created in a
specific scope so that you can manage which announcements are sent to which
users. Furthermore, each portal user can configure how they'd like to receive
announcements: click on *[User Name]* &rarr; *My Account* from the Dockbar and
then on *Announcements* in the Miscellaneous category. There's a menu for
customizing the delivery options for alerts and announcements. You can select a
different configuration for each type of alert or announcement: General, News,
or Test. Each kind of announcement that's broadcast to a user is viewable from
the Alerts or Announcements portlet, of course. But alerts and announcements can
also be sent via email or SMS (Short Message Service, i.e., text).

You can also create roles in Liferay to assign to users that should have access
to announcing general announcements. For instance, if you'd like an employee in
your site to have strict control over what is announced, you can assign them to
an Announcements role. To create a simple Announcement role, you'll need to
navigate to the *Control Panel* &rarr; *Roles* &rarr; *Add* &rarr; *Regular
Role*. Specify the name of your role as *Announcements*, click *Save*, and then
select the *Define Permissions* tab. For this particular role, you'll need to
grant two permissions:

- *Control Panel &rarr; General Permissions &rarr; Add General Announcements*
- *Site Administration &rarr; Applications &rarr; Announcements &rarr; Add
Entry*

You'll need to click *Save* after selecting each permission. Now you have a
simple Announcements role that can add an entry to the Announcements portlet and
add general announcements to your site! Of course, you can add additional
permissions to give the Announcements role more access, if you prefer.

![Figure 8.32: The Annoucements portlet displays a list of annoucements to regular users. It also provides administrators with an interface for managing and adding entries.](../../images/announcements-portlet.png)

Let's look at how to create announcements in the Announcements portlet. When you
view the Announcements portlet as an administrator, you'll see two tabs: Entries
and Manage Entries. Non-administrators only see the Entries tab which displays a
list of announcements. To add a new announcement, click on *Manage Entries* and
then select a *Distribution Scope*. The distribution scope determines the group
of users to which your announcement is sent. Announcements created in the
General distribution scope are sent to everyone. If the distribution scope is
set a particular site or role, only members of that site or role receive the
announcements.

Once you've selected a distribution scope, you'll see a list of all the
announcements that have already been created in that scope. You can click the
*Actions* button next to an announcement to edit or delete it. Click *Add* to
create a new announcement. In addition to the Distribution scope, you can
provide the following information:

**Title:** The title of the announcement. When the announcement is displayed in
the portlet, the full title always appears in bold. If a URL was entered, the
title serves as a link to the site specified in the URL field.

**URL:** The URL is optional. If entered, it must be a valid URL, starting with
*http://*. For example, an announcement about a news story could include a link
to the news article.

**Content:** The body of the announcement. You can use the familiar CK editor to
write the body of your announcement. Use the *Source* button to switch between
the editor view and the HTML view.

**Type:** Can be *General*, *News*, or *Test*. Each user can specify a different
delivery mechanism for each type of announcement. For example, a user can
specify that she'd like to receive general announcements via email and text,
news announcement via email only, and no special delivery mechanism for test
announcements. Each user can customize their delivery options by navigating to
*[User Name]* &rarr; *My Account* from the Dockbar and then clicking on
*Announcements* in the Miscellaneous category.

**Priority:** Can be either *Normal* or *Important*.

**Display Date:** This determines when the announcement will be sent to users
and will appear in the Announcements portlet. By default, the *Display
Immediately* box is flagged. This sets the display date to be equal to the
creation date. Unflag this box if you'd like to enter a custom display date. For
example, administrators can create announcements that won't be displayed until a
specified future date. This date can be days, weeks, months, or years in the
future. Once the *Display Immediately* box is unflagged, clicking on the Display
Date field opens the date-picker widget.

**Expiration Date:** This determines when the announcement expires. Once an
announcement expires, it's no longer displayed by the Announcements portlet to
any users. Clicking on the Expiration Date field opens the date-picker widget.

The Alerts portlet works the same way as the announcements portlet. It's a
different portlet because it's designed to be be used only for important
messages or message that may require users to take some action. The Alerts
portlet only displays alerts and the Announcements portlet only displays alerts.
The Alerts portlet displays alerts with a bold red box around each alert
message to draw attention to the displayed entries. Site administrators can
place the Alerts and Announcement portlets on different pages if they'd like to
separate important alerts from more mundane announcements.

Next, let's see what you can do with Liferay's Chat feature.
