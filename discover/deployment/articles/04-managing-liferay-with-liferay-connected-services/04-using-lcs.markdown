# Using LCS [](id=using-lcs)

Once your LCS client is registered with your LCS account, you can get down to 
the business that LCS was designed for--managing and monitoring your Liferay 
instance. If you're not already there, log in with your account on 
[lcs.liferay.com](https://lcs.liferay.com). This is where you'll apply updates, 
view server metrics, manage environments, invite external users to your project, 
and more. 

This article's following sections each detail one or more of the features 
available in LCS: 

- [**What LCS Stores About Your Liferay Servers:**](/discover/deployment/-/knowledge_base/6-2/using-lcs#what-lcs-stores-about-your-liferay-servers)
  For LCS to work, the LCS servers must store certain information about your 
  Liferay servers. Sensitive data, however, isn't stored on the LCS servers. 
  Learn exactly what data LCS does and doesn't store. 

- [**Managing Your LCS Projects:**](/discover/deployment/-/knowledge_base/6-2/using-lcs#managing-your-lcs-projects)
  Learn how to create and request access to LCS projects, how LCS roles work, 
  and how to manage LCS users. 

- [**Using the Dashboard:**](/discover/deployment/-/knowledge_base/6-2/using-lcs#using-the-dashboard)
  Learn how to manage projects, environments, and servers in LCS. This includes 
  applying fix packs, monitoring server status, viewing server metrics, and 
  more. 

- [**Managing Liferay EE Subscriptions:**](/discover/deployment/-/knowledge_base/6-2/using-lcs#managing-liferay-ee-subscriptions)
  Learn how to view and manage your Liferay EE subscriptions for the servers in 
  your LCS project. This includes elastic subscriptions for auto-scaling 
  environments. Note that to use these features, your Liferay instances must be 
  running Liferay 6.2 EE Service Pack 15 or higher. 

- [**Managing Your LCS Account:**](/discover/deployment/-/knowledge_base/6-2/using-lcs#managing-your-lcs-account)
  Learn how to manage your LCS account. This includes configuring LCS to send 
  you notification emails when specific events occur in your LCS projects, 
  setting general account preferences, and managing your LCS web notifications. 

- [**Using Environment Tokens:**](/discover/deployment/-/knowledge_base/6-2/using-lcs#using-environment-tokens)
  Learn how to use environment tokens to automatically register your Liferay 
  servers with LCS. This is crucial in auto-scaling environments. 

First, you'll learn what information LCS stores about your Liferay servers.

## What LCS Stores About Your Liferay Servers [](id=what-lcs-stores-about-your-liferay-servers)

At this point, you might be wondering what information about your servers is 
stored on the LCS servers. Great question! In order to offer the best service 
possible, we store the following information about your servers: patches
installed on each server, `portal.properties` (except sensitive data), JVM
metrics, portal and portlet metrics, and cache and server metrics. Sensitive 
data is defined as any key-value pair that contains usernames or passwords. For 
example, the following properties are considered sensitive and are not stored on 
the LCS servers:

    omniadmin.users
    ldap.security.credentials.0, ldap.security.credentials.1, ldap.security.credentials.2 ...
    facebook.connect.app.secret
    auth.token.shared.secret
    auth.mac.shared.key
    captcha.engine.recaptcha.key.private
    amazon.secret.access.key
    tunneling.servlet.shared.secret
    microsoft.translator.client.secret
    dl.store.s3.secret.key
    auto.deploy.glassfish.jee.dm.passwd

Also, any properties that end in `.password` and are not stored, with the 
exception of the following non-sensitive properties:

    portal.jaas.plain.password
    portal.jaas.strict.password
    login.create.account.allow.custom.password

Now that you know what information is stored on the LCS servers, you're ready to 
learn how to manage your LCS projects. This includes renaming and creating 
projects, as well as requesting membership to projects you don't administer. 
You'll also learn how to manage the users in your LCS project and assign them to 
the correct LCS roles. 

## Managing Your LCS Projects [](id=managing-your-lcs-projects)

Each environment and server in LCS is part of an LCS project. LCS provides a 
simple UI for managing these projects. To access this UI, log in to LCS and then 
select *Manage Projects* from your user menu in the Dockbar. The following 
screenshot illustrates this.

![Figure 4.9: To manage your LCS projects, select *Manage Projects* from the user menu in LCS.](../../images/lcs-user-menu-manage-projects.png)

The *My Projects* tab is shown first, and shows a table that lists each of your 
LCS projects. This table also lists the administrator's email address for each 
project. If you're the administrator of a project, you can edit its name by 
clicking the blue pencil icon next to it in the table. You can also create a new 
project by clicking the *Create Project* button below the table. The following 
screenshot shows the My Projects tab.

![Figure 4.10: The My Projects tab lists your LCS projects and lets you create new ones.](../../images/lcs-my-projects.png)

The *Unlinked* tab shows your Liferay projects that aren't connected with LCS. 
For example, if you've submitted an app on Liferay Marketplace, then it's a 
Liferay project associated with your Liferay account. A Liferay project is an 
umbrella Liferay concept and can span over many different Liferay products. The 
Unlinked tab gives you the opportunity to connect those projects to LCS. The 
*Pending* tab shows the existing LCS projects you've requested access to. If you 
request access to an LCS project, but the administrator hasn't yet granted your 
request, the project appears in the Pending tab. The *Company* tab shows a table 
with a list of the LCS projects associated with the domain of your email 
address. This is typically the company your email address is associated with. 
For example, if your email address is `joebloggs@janesblogfactory.com`, the 
Company tab presents you with a list of existing LCS projects registered to 
users with the `@janesblogfactory.com` domain. The table also lists the 
administrator email address and a *Request Access* link for each project. This 
lets you quickly and easily join the LCS projects that are associated with your 
company. The following screenshot shows the Company tab (the administrator email 
addresses have been masked).

![Figure 4.11: The Company tab lets you find other LCS projects associated with the domain of your email address.](../../images/lcs-company-projects.png)

So what can an LCS Administrator do? Why did you mask out their email addresses 
in the previous screenshots? These are fantastic questions! The LCS 
Administrator role is very powerful (that's why their email addresses are masked 
in the screenshots). LCS Administrators can assign LCS roles to the rest of the 
users in their project. Each LCS user must have an assigned role. The following 
roles are available: 

- **LCS Administrator:** All LCS functionality is available to administrators. 
  This is the only role that can manage the roles of other users. 
- **LCS Environment Manager:** All LCS functionality is available in the scope 
  of an environment, with the exception of managing other users. 
- **LCS Environment Viewer:** Has read-only access in the scope of an 
  environment.

You should note that each of these LCS roles assume that the user already has 
the LCS User role in his or her Liferay.com account. The LCS User role is 
granted automatically the first time the user enters their LCS account. The 
actions that can be performed by each of the LCS roles are detailed in the below
permissions matrix. 

**LCS Permissions Matrix**

Action | &nbsp;LCS Administrator | &nbsp;LCS Environment Manager | &nbsp;LCS Environment Viewer |
------ | ----------------------- | ----------------------------- | ---------------------------- |
Access LCS | true | true | true |
Access Any Environment | true | false | false |
Access a Particular Environment | true | true | true |
Manage Users in Any Environment | true | false | false |
Manage Users in a Particular Environment | true | true | false |
Invite Users to LCS | true | false | false |
Create and Delete Environments | true | false | false |
Edit Any Environment | true | false | false |
Edit a Particular Environment | true | true | false |
Server Registration in Any Environment | true | false | false |
Server Registration in a Particular Environment | true | true | false |
Install Fix Packs in Any Environment | true | false | false |
Install Fix Packs in a Particular Environment | true | true | false |

Now that you know what roles are available in an LCS project, and what they do, 
you're ready to learn how to manage the users in LCS projects.

### Managing LCS Users in Your Project [](id=managing-lcs-users-in-your-project)

The Users section of LCS is where you manage the LCS users that are part of your 
project. It's here that you can grant or revoke LCS roles or invite others that 
aren't yet part of your project. To manage users, first click the *Users* tab 
just below the Dashboard tab on the upper-left of your screen. You're presented 
with a table of the users in your project. To the right of each is the Manage 
button. Clicking *Manage* lets you assign or revoke LCS roles for that user. 

![Figure 4.12: The Users tab lets you manage the LCS users in your project.](../../images/lcs-users.png)

To invite external users to your project, click the *Invite* button. Here you
can invite anyone with a valid email address. You can also search for
Liferay.com users to invite. Once you choose some users, the *Role* selection
box lets you preassign LCS roles for when they accept your invitation. You can
also use the Environment selection box to preassign them to an environment in
your project.

![Figure 4.13: You can invite users to your LCS project and even preassign them roles.](../../images/lcs-invite-users.png)

To view sent invitations, click the *Invitations* tab. A table displays
invitations, listing invited users' email addresses along with who invited them
and the date that the invitation was sent. The table also shoes the preassigned
LCS role and environment. You can cancel an invitation by clicking *Cancel* in 
the Action column of the invitation. 

![Figure 4.14: The Invitations tab lets administrators view and cancel invitations.](../../images/lcs-invitations.png)

Great! Now you know how to manage your LCS projects and the users in them. Now 
it's time to get to the heart of LCS: the Dashboard.

## Using the Dashboard [](id=using-the-dashboard)

The LCS Dashboard lets you view and manage a project's environments and servers. 
If you're not already at the Dashboard, click it near the upper left-hand corner 
of your LCS site. Clicking *Dashboard* takes you to the project view. From 
there, you can get to the environment view and the server view. Each of these 
views gives you a different look into certain aspects of your LCS project. 
You'll start with the project view. 

### Using the Project View [](id=using-the-project-view)

You can get to the project view at any time by clicking the *Dashboard* tab near 
the upper left-hand corner of your LCS site. The project is listed to the right 
of this tab, with a drop-down arrow that lets you switch between projects if you 
have more than one. You can also switch between projects from the user menu at 
the top right of the Dockbar. The project view provides you with an overview of 
your LCS project, including available fix packs, alerts, environments, and 
servers. Fix packs, if available for your servers, are displayed prominently in 
the Fix Packs table. Note that this table doesn't appear at all if your servers 
have no available fix packs. The Fix Packs table lists the status, environment, 
server, and location for each fix pack. If a server with an available fix pack 
is running, a *Download* column also appears in the table. You can download the 
fix pack by clicking its *Download* icon in that column. You can download 
several fix packs at once by checking the checkbox to the left of each and then 
clicking the *Download* button above the Fix Packs table. Once a fix pack 
download completes, an alert appears in the Alerts table. This alert tells you 
the download is finished and to restart your server. Restarting your server 
installs any downloaded fix packs. Note that you must start your server with the 
privileges required to write to the disk location where patches are stored and 
processed (the `patching-tool` folder). 

But what about using LCS to install fix packs across a cluster? Just follow the 
same procedure! LCS downloads and installs fix packs simultaneously across all 
nodes--you don't have to handle each separately. 

![Figure 4.15: The LCS project view shows an overview of your LCS project.](../../images/lcs-project-view.png)

In addition to displaying fix pack messages, the Alerts table also displays 
many other kinds of messages. For example, an alert appears whenever a 
server is offline. Other alerts appear when monitoring is unavailable, the 
patching tool is unavailable, or LCS detects other issues. Note that the Alerts 
table only appears when there are alerts to display. If there aren't any alerts, 
then a status message appears indicating that all the servers in your project 
are online and connected to LCS.

LCS lists the environments in your project on the left side of the screen. You 
can also create new environments here by clicking the Add Environment tab. Note 
that the icon to the left of each environment differs depending on the 
environment's type and status. The icon's color and type tells you something 
about that environment:

- **Red icon:** Indicates a problem or issue with one or more servers in that 
  environment.
- **Green icon:** Indicates that the servers in that environment are operating 
  properly.
- **Icon with a circle:** Indicates that the servers in that environment are in 
  a cluster.

If an environment has fix packs available, a badge icon to its right indicates 
how many. You can get more information about a specific environment by clicking 
it. This takes you to the environment view.

### Using the Environment View [](id=using-the-environment-view)

Clicking an environment on the left-hand side of the project view takes you to 
the environment view. The environment view lets you manage an environment in 
your LCS project. The UI is segmented into three tabs: Fix Packs, and Automatic 
Registration, and Environment Settings. When you enter environment view, the Fix 
Packs tab is selected by default. This tab displays the environment's fix packs 
and alerts in the Fix Packs and Alerts tables, respectively. Note that these 
tables behave exactly the same here as they do in project view, although their 
contents apply only to the environment. Next is the Automatic Registration tab. 
This tab lets you generate and view *environment tokens* that allow automatic 
configuration of LCS clients. See the section below for more information on 
environment tokens. The Environment Settings tab is last. You can use this tab 
to change the environment's name, location, and description. You can also see if 
your environment is part of a cluster. Your environment's subscription type, if 
any, is also displayed in the Environment Settings tab. For more information on 
subscriptions, see the below section on 
[managing Liferay EE subscriptions](/discover/deployment/-/knowledge_base/6-2/using-lcs#managing-liferay-ee-subscriptions). 
Click the *Save* button to save any changes you make in the Environment Settings 
tab. You can also delete the environment by clicking *Delete Environment*, next 
to the Save button. 

![Figure 4.16: The LCS environment view shows an overview of an LCS environment.](../../images/lcs-environment-view.png)

Regardless of the tab you're in, the left side of the screen displays a list of 
the environment's servers. Clicking on a server takes you to its server view. 

### Using the Server View [](id=using-the-server-view)

The server view provides detailed information about a server, including 
statistics and performance metrics. You can get to the server view by clicking a
server in the environment view or by clicking a server in the Fix Packs or 
Alerts tables. Server view is segmented into five tabs: 

- **Page Analytics:** Displays metrics on page views and load times.
- **Snapshot Metrics:** Displays application, JVM, and server metrics.
- **Fix Packs:** Displays the server's fix packs and alerts.
- **Portal Properties:** Displays your portal's properties and their settings.
- **Details:** Displays general information about your Liferay installation, 
  Java version, and hardware.
- **Server Settings:** View or change your server's name, location, and 
  description. You can also unregister the server from LCS.

Page Analytics is displayed by default when you enter server view. Page 
Analytics shows page views and load times for the selected time period. You can 
select a different time period in the *Period* and *Ending At* fields. The 
arrows next to the *Ending At* field move the selected time period up or down, 
respectively, by one period. For example, if you select *One Hour* in the 
*Period* field, then pressing the right arrow next to *Ending At* moves the 
selected time period up by one hour. Note that at the beginning of the current 
time period, it can take up to 15 minutes for data to become available. 

By default, load times and page views for all pages are plotted against time in 
separate graphs. Below these graphs, a table displays summary statistics of 
these data over the same time period, for each page. If you click a page in the 
table, the graphs change to plot the data for just that page. If you can't 
find the page you're looking for, you can search for it in the *Search* box at 
the top of the table. To plot data for all pages again, click the *All Pages* 
row at the bottom of the table. 

Load times are also color coded to indicate speed. The *Load Times* graph's 
background is red for values above 3,000 ms, orange for values from 2,000 to 
3,000 ms, and green for values less than 2,000 ms. Likewise, the table displays 
all load times greater than 3,000 ms in red text. 

![Figure 4.17: The Page Analytics interface in the LCS server view.](../../images/lcs-page-analytics.png)

To view other metrics and statistics of your server's performance, click the 
*Snapshot Metrics* tab near the top of the page. These metrics are broken down 
into three main categories: *Application*, *JVM*, and *Server*. Application is 
selected by default when you click the Snapshot Metrics button. 

The Application category also has three other categories: *Pages*, *Portlets*, 
and *Cache*. Pages lists the frequency that specific pages load, along with 
their average load time. Portlets lists the same statistics, but for specific 
portlets in your server. The Cache category lists Liferay Single VM metrics and 
Hibernate metrics. The following screenshot shows the statistics in the Portlets 
category.

![Figure 4.18: The LCS application metrics show portlet performance statistics, like frequency of use and average load time.](../../images/lcs-server-metrics-application-portlets.png)

The JVM category, as its name indicates, shows statistics about the JVM running 
on your server. This includes data on the garbage collector and memory. The 
number of runs, total time, and average time are listed for each garbage 
collector item. The memory metrics are presented in a bar chart that shows the 
usage of the PS Survivor Space, PS Old Gen, PS Eden Space, Code Cache, and PS 
Perm Gen.

![Figure 4.19: The LCS JVM metrics show performance data for memory and the garbage collector.](../../images/lcs-server-metrics-jvm.png)

Server is the third category in Snapshot Metrics. The Server category shows 
additional information about how your server is running. For example, a 
horizontal bar graph shows the number of current threads running on your server. 
Similarly, horizontal bar graphs are used to represent the JDBC connection 
pools. 

![Figure 4.20: The LCS server metrics show current threads and JDBC connection pools.](../../images/lcs-metrics-server.png)

To view your server's fix packs and alerts, click the Fix Packs tab near the top 
of the page. Although the Alerts table functions the same as it does in the 
other views, the Fix Packs table behaves a bit differently. Fix packs here are 
broken down into those that are available for installation and those that are 
already installed. You can access these through the Available and Installed tabs 
at the top of the Fix Packs table. 

![Figure 4.21: The Fix Packs tab displays your server's fix packs and alerts.](../../images/lcs-server-fix-packs.png)

LCS also lets you view your portal's property values. To do so, click the 
*Portal Properties* tab near the top of the page. Your portal's properties and 
their values are shown in a searchable table. This gives you a convenient 
display for seeing exactly what your portal properties are set to. The 
properties in this table are organized into the following categories: 

- **Default Values:** The default values for your portal's properties. 

- **Custom Values:** Any custom values you've set for your portal's properties. 
  This includes any property values you change via a `portal-ext.properties` 
  file.

- **Dynamic Properties:** Any property values set at runtime. For example, the 
  [Liferay Home](/discover/deployment/-/knowledge_base/6-2/liferay-home) 
  folder's location depends on your configuration. To always specify this folder 
  when setting any properties that require it, you can use `${liferay.home}` 
  instead of an absolute directory path. 

You can display any combination of these categories by selecting the 
corresponding checkboxes at the top of the table. For example, by checking the 
*Show Default Values* and *Show Custom Values* checkboxes, the table shows your 
portal's default and custom property values. To show only the custom values, 
check only the checkbox for *Show Custom Values*. 

![Figure 4.22: With only the *Show Custom Values* checkbox checked, this table shows only the portal property values that differ from default.](../../images/lcs-server-portal-properties.png)

To view general information about your Liferay installation, click the *Details* 
tab near the top of the screen. There are three tabs under Details: *Software*, 
*Java*, and *Hardware*. Each shows information, respectively, about your Liferay 
installation, Java installation, and hardware. This information is useful to the 
Liferay support team in the event that you need their assistance. 

![Figure 4.23: Clicking the Details button shows information about your Liferay installation's software and hardware.](../../images/lcs-server-details.png)

Lastly, click the *Server Settings* tab near the top of the screen to view and 
edit your server's name, location, and description. You can also use the Server 
Settings UI unregister your server from LCS. Note that unregistering your server 
doesn't change your actual Liferay server; it just removes it from LCS. 

As you can see, the LCS Dashboard is a powerful tool that greatly simplifies 
the update process and also gives you extensive information on how your servers 
are running. Next, you'll learn how to use LCS to manage your Liferay EE 
subscriptions. 

## Managing Liferay EE Subscriptions [](id=managing-liferay-ee-subscriptions)

LCS also lets you view and manage your Liferay EE subscriptions. You can view 
your project's subscriptions, see how they're being used, assign an environment 
to a subscription type, and more. You can access these features from the 
*Subscriptions* tab on the upper-left of the LCS site. Note that to use these 
features, your Liferay instances must be running Liferay 6.2 EE Service Pack 15 
or higher. 

![Figure 4.24: The *Subscriptions* tab in LCS lets you view and manage your Liferay EE subscriptions.](../../images/lcs-subscriptions.png)

LCS presents subscription information in the *Details* and *Elastic 
Subscriptions* tabs. When you select *Subscriptions*, the *Details* tab is shown 
first. This tab shows you basic information on your subscriptions and lets you 
assign subscription types to your LCS environments. In *Details*, the 
*Subscriptions* table shows you a list of Liferay EE subscriptions available for 
your LCS project. For each subscription, the table shows the following 
information:

- Start Date
- Expiration Date
- Support End Date
- Platform
- Product
- Processor Cores Allowed
- Servers Allowed
- Servers Used

Below this table is the *Subscriptions Usage* table. The *Subscriptions Usage* 
table shows you how you're currently utilizing your subscriptions. For each 
subscription type, this table shows the servers allowed, servers used, and 
servers available. The *Project Environments* table, below the *Subscriptions 
Usage* table, shows the subscription type you've assigned to each environment. 
You can also use this table to assign a subscription type to an environment. 
Note that all the servers in an environment must be shut down to assign a 
subscription type. These assignments are also reflected in the *Project Servers* 
table. This table shows the environment and subscription type for each server in 
your LCS project. 

The *Elastic Subscriptions* tab displays information about the elastic 
subscriptions in your project. For this tab to appear, you must have at least 
one environment in your project set to the elastic subscription type. Elastic 
subscriptions let you register an unlimited number of Liferay servers, unlike 
other subscriptions that limit you to a certain number of servers. This is 
invaluable in auto-scaling environments, where servers are automatically created 
and destroyed in response to server load. The following screenshot shows the 
*Elastic Subscriptions* tab. 

![Figure 4.25: The *Elastic Subscriptions* tab displays details about your servers registered under the elastic subscription type.](../../images/lcs-elastic-subscriptions.png)

This tab displays the number of online elastic servers and the uptime details 
for each. The number of online elastic servers per day is shown in a graph. 
Uptime statistics are displayed in a table that lists each elastic server's 
start time, end time, and duration. Below the table, the total duration for all 
elastic servers is also shown. Above the table, you can click the *Download 
Report* button to download a report of these data. Also, you can use the 
*Environment* and *Month* selectors above the graph to select the environment 
and month to show data from, respectively. The data in both the graph and the 
table reflect your selections here. 

Next, you'll learn how to manage your LCS account. 

## Managing Your LCS Account [](id=managing-your-lcs-account)

To manage your LCS account, select *My Account* from the user menu in the 
Dockbar. This takes you to a UI that contains the *Email Notifications* and 
*Preferences* tabs. The Email Notifications tab is selected by default. You can 
use this tab to configure LCS to send you notification emails when specific 
events occur in your projects. LCS notifications are configured by adding 
*rules*. The rules define what events trigger a notification. There are no 
notification rules by default. Click the *Add Rule* button to define one. 

![Figure 4.26: You can add rules to determine the events that trigger notifications.](../../images/lcs-add-notification-rule.png)

First specify the project, environment, and server for the notification. Note 
that you have the option of selecting all environments and servers in a
project. Then check the checkbox for each event that you want to trigger an 
email notification. For example, if you create a notification rule with *The 
server unexpectedly shuts down* selected for all servers and environments in 
your project, then LCS sends you an email when any server in your project goes 
offline without a normal shut down event. Click *Save* when you're done defining 
the notification rule. It then appears in a table along with any other existing 
rules. Each has an Actions button that lets you edit or delete it. 

To manage your LCS account's general preferences, click the *Preferences* tab 
near the top of the UI. This tab lets you change your account's language, time 
zone, and default LCS project. Your default LCS project is the one shown each 
time you log in to LCS. 

![Figure 4.27: You can change your LCS account's language, time zone, and default LCS project.](../../images/lcs-account-preferences.png)

LCS also displays web notifications that you can view by clicking the bell icon 
next to the user menu in the Dockbar. A red badge on the bell icon shows your 
unread notification count. Here, you can view notifications sent by Liferay 
Support, as well as notifications generated by LCS. For example, Liferay Support 
sends notifications regarding your Liferay EE subscription status, and LCS sends 
notifications when there's a new project available for use with your 
subscription. To mark a notification as read, click the small *x* icon next to 
it. To mark all notifications as read, click the *Mark All as Read* button. 
After marking one or more notifications as read, an *Undo* button appears. Click 
*Undo* to mark the notification as unread. Note that LCS also displays 
notifications about your subscriptions on the Subscriptions page. 

![Figure 4.28: LCS sends you web notifications that you can view by clicking the bell icon next to the user menu in the Dockbar.](../../images/lcs-user-web-notifications.png)

Great! Now you know how to manage your LCS account. The next section shows you 
how to use environment tokens to automatically register Liferay instances with 
LCS. 

## Using Environment Tokens [](id=using-environment-tokens)

Environment tokens allow clients to connect automatically to environments in LCS 
without requiring any user interaction. For example, when users connect to 
LCS, they must first manually configure the client portlet to connect to an 
environment. To bypass the need for manual client configuration, LCS
Administrators and Environment Managers can generate and distribute an
environment token file. This token contains all the information the client
needs to connect to that environment on LCS. It's important to note that each
environment can have only one token file. You should also use caution when
distributing it. Anyone with the token file can use it to connect to your
environment. Also, be careful when regenerating or otherwise removing a token
file from LCS. When this is done, clients using the old file can't connect
until receiving the new file. The only alternative is to use the
administrator's own credentials to connect the client manually. Once the client
reconnects, it's once again linked to the client's existing archive data in
LCS. 

So why bother with environment tokens at all? Besides the benefit of simplifying 
the setup process for your users, using environment tokens is valuable in 
auto-scaling environments where algorithms create and destroy servers 
automatically. In this situation, having clients that configure themselves is 
crucial.

+$$$

**Note**: If your auto-scaling environment creates new server nodes from a 
portal instance in a system image, that instance can't require human interaction 
during setup. When creating such an image, you must change any portal property 
settings that can prevent automatic portal setup. For example, Liferay's setup 
wizard requires human interaction to set up the portal. You must therefore set 
the portal's `setup.wizard.enabled` property to `false` if you want your 
auto-scaling environment to create new server nodes from this portal.

$$$

There are two places in LCS where you can generate and access environment 
tokens. If you read the previous sections of this article, then you already know 
one: the environment view. Navigate to an environment in LCS and click the 
*Automatic Registration* button. From here you can manage the environment's 
token. 

![Figure 4.29: Clicking the Automatic Registration button in the environment view shows the token for only that environment.](../../images/lcs-environment-token.png)

By default, there's no existing token. A table appears that contains only a 
*Generate* button. Click it to generate a token for the environment. The new 
token then appears in the table with information on who generated it and when. 
There's also an Actions button next to it for downloading or regenerating the
token. 

You can also access environment tokens from the Connection tab on the left side 
of LCS. Click the *Connection* tab here, and then click the *Automatic 
Registration* tab. The table shows the tokens for all the environments in your 
project. This provides a central location to manage all your environment tokens. 
Otherwise, the UI for managing them is exactly the same. 

![Figure 4.30: The Connection tab on the left lets you manage the environment tokens for your entire project.](../../images/lcs-environment-token-02.png)

Once you have an environment token, use the following steps to register a
previously unregistered Liferay instance with LCS:

1. Place the token file in your portal's `data` folder.

2. Deploy the LCS client portlet to your portal. If the portlet is bundled with 
   your portal installation, start up the portal.
   
3. Once deployment completes, the LCS client portlet connects automatically 
   to LCS. You should see this in your LCS project's environment view.
   
When using an environment token, minimal information (server name, location, 
etc...) is used to register a portal instance with LCS. You can change this 
information from the server view in LCS at any time. Also, since environment 
tokens connect using OAuth, it's important to note that using an environment 
token overrides the OAuth authorization cycle. If an LCS Administrator or 
Environment Manager has never registered portal instances with LCS, the first 
time they do so an OAuth authorization entry is created. This is visible in 
their LCS account's *My Account* &rarr; *OAuth Authorizations* menu. If they 
have previously registered portal instances with LCS, their existing
credentials are used when they create a token file. 

![Figure 4.31: The OAuth authorization entry in the portal instance using an environment token to connect to LCS.](../../images/lcs-token-oauth-entry.png)

What if your Liferay instance has already been manually registered with LCS,
but you want to switch to using an environment token? No problem! Follow these
steps:  

1. Shut down your Liferay instance and place the token file in the portal's 
   `data` folder. 

2. Restart your Liferay instance. The LCS client portlet uses the token file to 
   connect to LCS automatically. 

As you've now seen, LCS is a powerful tool that simplifies the management of 
your Liferay servers. You can apply fix packs with just a single click and a 
server restart--a process that even works across a cluster. You also get a one 
stop shop for monitoring the performance of your Liferay servers. Metrics like 
JVM performance, Liferay page and portlet load times, and number of current 
threads give you an inside look at how your server is running. What's more is 
that you can do all this collaboratively by inviting others to your project and 
giving them specific roles in LCS. 

Next, you'll learn about Liferay clustering.
