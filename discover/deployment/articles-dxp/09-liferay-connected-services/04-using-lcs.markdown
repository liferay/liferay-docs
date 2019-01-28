# Using LCS [](id=using-lcs)

Once your @product@ server is connected to LCS, you can get down to the business 
that LCS is designed for---managing and monitoring your servers. If you're not 
already there, log in with your account on 
[lcs.liferay.com](https://lcs.liferay.com). 
This is where you'll apply updates, view server metrics, manage environments, 
and more. 

This article's sections each detail one or more of LCS's features: 

-   [**What LCS Stores About Your Liferay DXP Servers:**](#what-lcs-stores-about-your-liferay-servers)
    For LCS to work, the LCS servers must store certain information about your 
    servers. Sensitive data, however, isn't stored on the LCS servers. This 
    section describes the data that LCS does and doesn't store. 

-   [**Managing LCS Users in Your Project:**](#managing-lcs-users-in-your-project)
    Learn how to manage your LCS project's users by assigning them roles. 

-   [**Using the Dashboard:**](#using-the-dashboard)
    Learn how to manage your LCS projects and access your environments and 
    servers in LCS. 

-   [**Managing LCS Environments:**](#managing-lcs-environments) 
    Learn how to create and manage your LCS project's environments. This 
    includes instructions on installing fix packs for an environment's servers. 

-   [**Managing LCS Servers:**](#using-the-server-view) 
    Learn how to manage your servers in LCS. This includes viewing server 
    metrics and editing server settings. 

-   [**Managing Your LCS Account:**](#managing-your-lcs-account)
    Learn how to manage your LCS account. This includes setting general account 
    preferences, managing LCS web notifications, and configuring LCS to send you 
    notification emails when specific events occur in your LCS projects. 

-   [**Managing Liferay DXP Subscriptions:**](#managing-liferay-dxp-subscriptions)
    Learn how to view and manage your @product@ subscriptions for the servers in 
    your LCS project. 

-   [**Understanding Environment Tokens:**](#using-environment-tokens)
    Learn about the environment tokens that you use to connect your servers to 
    LCS. 

## What LCS Stores About Your Liferay DXP Servers [](id=what-lcs-stores-about-your-liferay-servers)

To protect your users' privacy, LCS only stores system-specific data. LCS
doesn't gather or store data on your users. 

By default, LCS stores the following information about your server:

-   Portal build number and edition
-   Patching Tool Version
-   LCS Client Build Number 
-   Application Server Name
-   Database Name
-   File Encoding
-   OS Name and Version
-   Timezone
-   IP Address
-   Java Version and Java Options
-   Number of Processor Cores
-   File System Usage
-   Memory Usage 

The other data LCS stores depends on the services you enable in your environment 
token. For more information on this, see 
[Registering Your Liferay DXP Server with LCS](/discover/deployment/-/knowledge_base/7-0/registering-your-dxp-server-with-lcs).
When you enable the following services, LCS gathers and stores the data listed 
for each: 

-   **Portal analytics:** 

    -   Portal and portlet metrics
    -   JVM metrics
    -   Cache and server metrics 

-   **Fix pack management:**

    -   Patches installed on the server 

-   **Portal properties analysis:** 

    -   `portal.properties` (except sensitive data) 

Sensitive data is any key-value pair that contains user names or passwords. For
example, LCS doesn't store the following properties because they contain
sensitive data: 

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

LCS also doesn't store properties that end in `.password`, besides the following 
non-sensitive properties: 

    portal.jaas.plain.password
    portal.jaas.strict.password
    login.create.account.allow.custom.password

LCS also lets you prevent it from analyzing specific properties of your 
choosing. For more information on this, see 
[Registering Your Liferay DXP Server with LCS](/discover/deployment/-/knowledge_base/7-0/registering-your-dxp-server-with-lcs).

## Managing LCS Users in Your Project [](id=managing-lcs-users-in-your-project)

The Users section of LCS is where you manage the LCS users that are part of your 
project. It's here that you can grant or revoke LCS Roles. To manage users, 
first click the *Users* tab just below the Dashboard tab on the upper-left of 
your screen. 

+$$$

**Note:** You can't add users to your project via the LCS UI or the LCS client 
app. To add users to your project, you must contact Liferay support. 

$$$

![Figure 1: The Users tab lets you manage the LCS users in your project.](../../images-dxp/lcs-users.png)

The *Users* tab displays a list of the users in your project. This list includes 
each user's name, email, image, LCS Roles, and a *Manage Roles* button. Each LCS 
user must have an assigned Role. The following Roles are available: 

**LCS Administrator:** All LCS functionality is available to administrators.
This is the only Role that can manage other users' Roles. 

**LCS Environment Manager:** All LCS functionality is available in the scope 
of an environment, with the exception of managing other users. 

**LCS Environment Viewer:** Has read-only access in the scope of an 
environment.

You should note that each of these LCS Roles assume users already have the LCS
User Role in their Liferay.com accounts. The LCS User Role is granted
automatically the first time a user logs into LCS. The actions that can be
performed by each of the LCS Roles are detailed in the below permissions matrix. 

**LCS Permissions Matrix**

Action | &nbsp;LCS Administrator | &nbsp;LCS Environment Manager | &nbsp;LCS Environment Viewer |
------ | ----------------------- | ----------------------------- | ---------------------------- |
Access LCS | true | true | true |
Access Any Environment | true | false | false |
Access a Particular Environment | true | true | true |
Manage Users | true | false | false |
Create and Delete Environments | true | false | false |
Edit Any Environment | true | false | false |
Edit a Particular Environment | true | true | false |
Server Registration in Any Environment | true | false | false |
Server Registration in a Particular Environment | true | true | false |
Install Fix Packs in Any Environment | true | false | false |
Install Fix Packs in a Particular Environment | true | true | false |

Now that you know what Roles are available in an LCS project and what they do, 
you're ready to learn how to manage them. 

### Managing LCS Roles [](id=managing-lcs-roles)

Follow these steps to manage a user's LCS Roles:

1.  Click the user's *Manage Roles* button. 

2.  To revoke a Role, click *Revoke Role* for that Role. 

3.  To assign a Role, choose the Role (and environment, if applicable) and click 
    *Assign*. 

+$$$

**Note:** A user can't have an environment Role (e.g., LCS Environment Manager, 
LCS Environment Viewer) and the LCS Administrator Role at the same time. 

$$$

![Figure 2: You can assign or revoke a user's LCS Roles.](../../images-dxp/lcs-user-roles.png)

## Using the Dashboard [](id=using-the-dashboard)

The LCS Dashboard shows a project's environments and servers. If you're not
already at the Dashboard, click it near the upper left-hand corner of your LCS
site. Clicking *Dashboard* takes you to the project view. From there, you can
get to the environment view and the server view. Each of these views gives you
a different look into certain aspects of your LCS project. You'll start with the
project view. 

### Using the Project View [](id=using-the-project-view)

You can get to the project view at any time by clicking the *Dashboard* tab near
the upper left-hand corner of your LCS site. The project appears to the right of
this tab, with a drop-down arrow for switching between projects if you have more
than one. You can also switch between projects from the user menu at the top
right of the Dockbar. The project view contains a Status table that lists status
messages for each server in your project. For example, a status message appears
for a server when the server is offline. Status messages also appear for servers
when fix packs are available, monitoring is unavailable, the patching tool is
unavailable, or other events occur that relate to LCS. 

![Figure 3: The LCS project view shows an overview of your LCS project.](../../images-dxp/lcs-project-view.png)

LCS lists the environments in your project on the left side of the screen. You 
can also create new environments here by clicking the *Add Environment* tab 
(more on this shortly). To view an environment's settings, click the
environment's gear icon. Clicking an environment shows more information about
it. This takes you to the environment view. Also note that each environment's
icon indicates the environment's type and status: 

- **Red icon:** There's a problem with one or more of the environment's servers. 
- **Green icon:** The environment's servers are operating properly. 
- **Icon with a circle:** The environment's servers are clustered. 

## Managing LCS Environments [](id=managing-lcs-environments)

Environments are the key components of your LCS project. When you register a 
server in LCS, you do so in an environment. An environment is therefore the 
gateway to managing and monitoring your servers in LCS. 

### Creating Environments [](id=creating-an-environment)

The first time you log in to LCS, a wizard walks you through each step required 
to create your project's first environment. The 
[getting started article](/discover/deployment/-/knowledge_base/7-0/getting-started-with-lcs) 
explains this in detail. You can create additional environments via the same 
wizard or a simple form. 

To create an environment, click the *Add Environment* button from the Dashboard. 
This opens the New Environment form. Each section in this form corresponds to a 
step in the wizard. If you want to use the wizard instead, click the 
*Open Wizard* link at the top of the form. See the 
[getting started article](/discover/deployment/-/knowledge_base/7-0/getting-started-with-lcs) 
for a description of each setting in the form and wizard. 

+$$$

**Note:** When creating an environment, make your selections carefully for the 
*Subscription Type*, *Cluster*, and *Elastic* fields. You can't change them 
after creating the environment. 

$$$

![Figure 4: The New Environment form lets you create environments.](../../images-dxp/lcs-new-environment.png)

### Working with Environments [](id=using-the-environment-view)

Clicking an environment on the left-hand side of the Dashboard takes you to the
environment view, which lets you manage an environment in your LCS project. 

The UI is segmented into three tabs: 

1.  **Fix Packs:** View and apply fix packs for the environment's servers. This 
    tab only appears if a server is registered in the environment. A table 
    displays the following information for each fix pack: 

    -   **Name:** The fix pack's name.
    -   **Status:** The fix pack's status.
    -   **Server:** The server the fix pack can be applied to.
    -   **Size:** The fix pack's size. This only appears if the server is 
        running. 
    -   **Download:** A button to download the fix pack to the server. This only 
        appears if the server is running. 

    Once a fix pack downloads, LCS prompts you to restart your server, which 
    installs any downloaded fix packs. Note that you must start your server with 
    the privileges required to write to the disk location where patches are 
    stored and processed (the `patching-tool` folder). To use LCS to install fix 
    packs across a cluster, follow the same procedure. LCS downloads and
    installs fix packs simultaneously across all nodes---you don't have to
    handle each separately. 

2.  **Registration:** Generate and download 
    [*environment tokens*](#using-environment-tokens) 
    that connect your servers to LCS. 

3.  **Environment Settings:** Change the environment's name, location, and
    description. You can also see if the environment allows clustered servers
    and view the environment's subscription type. Click the *Save* button to
    save any changes you make in the Environment Settings tab. You can also
    delete the environment by clicking *Delete Environment*. 

![Figure 5: The LCS environment view shows an overview of an LCS environment.](../../images-dxp/lcs-environment-view.png)

## Managing LCS Servers [](id=using-the-server-view)

Clicking a server in the Dashboard or environment view takes you to the server 
view. Server view provides detailed information about a server, including 
statistics and performance metrics. To protect your users' privacy, LCS doesn't 
gather, store, or analyze user data. 

Server view is segmented into six tabs: 

**Page Analytics:** Displays metrics on page views and load times.

**Snapshot Metrics:** Displays application, JVM, and server metrics.

**Fix Packs:** Displays the server's available and installed fix packs.

**Portal Properties:** Displays your portal's properties and their settings.

**Details:** Displays general information about your @product@ installation, 
Java version, and hardware.
 
**Server Settings:** View or change your server's name, location, and 
description. You can also unregister the server from LCS. 

+$$$

**Note:** LCS only supports Snapshot Metrics for servers running on Tomcat or 
WebLogic. On other application servers you may see a console message indicating 
that LCS doesn't support server metrics for your application server. You may 
also see a benign `NullPointerException` for the LCS `TaskSchedulerServiceImpl` 
and `ScheduleTasksCommand`. 

$$$

### Page Analytics [](id=page-analytics)

Page Analytics appears by default when you enter server view. Page Analytics
shows page views and load times for the selected site and time period. By
default, all sites are selected. You can select a specific site from the *Site*
selector menu. You can also select a different time period in the *Period* and
*Ending At* fields. The arrows next to the *Ending At* field move the selected
time period up or down, respectively, by one period. For example, if you select
*One Hour* in the *Period* field, pressing the right arrow next to *Ending At*
moves the selected time period up by one hour. Note that at the beginning of the
current time period, it can take up to 15 minutes for data to become available.
Also note that data is available for three months from the time LCS collected
it. 

By default, load times and page views for all pages are plotted against time in 
separate graphs. Below these graphs, a table displays summary statistics of data
over the same time period, for each page. If you click a page in the table, the
graphs plot the data for just that page. If you can't find the page you're
looking for, you can search for it in the *Search* box at the top of the table.
To plot data for all pages again, click the *All Pages* row at the bottom of the
table. 

Load times are also color coded to indicate speed. The *Load Times* graph's 
background is red for values above 3,000 ms, orange for values from 2,000 to 
3,000 ms, and green for values less than 2,000 ms. Likewise, the table displays 
all load times greater than 3,000 ms in red text. 

![Figure 6: The Page Analytics interface in the LCS Server view.](../../images-dxp/lcs-page-analytics-01.png)

### Snapshot Metrics [](id=snapshot-metrics)

To view other metrics and statistics of your server's performance, click the 
*Snapshot Metrics* tab near the top of the page. These metrics are broken down 
into three main categories: *Application*, *JVM*, and *Server*. Application is 
selected by default when you click the Snapshot Metrics button. 

The Application category also has three other categories: *Pages*, *Portlets*, 
and *Cache*. Pages lists the frequency that specific pages load, along with
their average load times. Portlets lists the same statistics, but for specific
portlets in your server. The Cache category lists Liferay Single VM metrics and
Hibernate metrics. The following screenshot shows the statistics in the Portlets
category.

![Figure 7: The LCS application metrics show portlet performance statistics, like frequency of use and average load time.](../../images-dxp/lcs-server-metrics-application-portlets.png)

The JVM category, as its name indicates, shows statistics about the JVM running 
on your server. This includes data on the garbage collector and memory. The 
number of runs, total time, and average time are listed for each garbage 
collector item. The memory metrics are presented in a bar chart that shows the 
usage of the PS Eden Space, Code Cache, Compressed Class Space, PS Old Gen, PS 
Survivor Space, and Metaspace. 

![Figure 8: The LCS JVM metrics show performance data for memory and the garbage collector.](../../images-dxp/lcs-server-metrics-jvm.png)

Server is the third category in Snapshot Metrics. The Server category shows 
additional information about how your server is running. For example, horizontal 
bar graphs show the number of current threads running on your server, as well as 
the JDBC connection pools. 

![Figure 9: The LCS server metrics show current threads and JDBC connection pools.](../../images-dxp/lcs-metrics-server.png)

Note that in Snapshot Metrics, the application and garbage collector metrics are 
based on data collected by LCS from server registration to the present. Memory 
and server metrics, however, show only the current state. 

### Fix Packs [](id=fix-packs)

To view your server's fix packs, click the Fix Packs tab near the top of the 
page. The available and installed fix packs appear in separate tables. The 
available fix packs table functions exactly like the Fix Packs table in 
environment view for downloading and installing fix packs. 

![Figure 10: The Fix Packs tab displays your server's available and installed fix packs.](../../images-dxp/lcs-server-fix-packs.png)

### Portal Properties [](id=portal-properties)

The *Portal Properties* tab lets you view your portal's property values in
a searchable table. This gives you a convenient display for your portal property
settings. The properties in this table are organized into the following
categories: 

**Default Values:** The default values for your portal's properties. 

**Custom Values:** Any custom values you've set for your portal's properties. 
This includes any property values you change via a `portal-ext.properties` 
file.

**Dynamic Properties:** Any property values set at runtime. For example, the 
[Liferay Home](/discover/deployment/-/knowledge_base/7-0/installing-product#liferay-home) 
folder's location depends on your configuration. To specify this folder when
setting any properties that require it, use `${liferay.home}` instead of an
absolute directory path. 

You can display any combination of these categories by selecting the 
corresponding checkboxes from the gear icon next to the search box at the 
top-right of the table. For example, by checking the *Show Default Values* and 
*Show Custom Values* checkboxes, the table shows your portal's default and 
custom property values. To show only the custom values, select only *Show Custom 
Values*. 

![Figure 11: Click the gear icon to select the type of portal properties to show in the table.](../../images-dxp/lcs-server-portal-properties.png)

### Details [](id=details)

The *Details* tab shows general information about your server. There are three 
tabs under Details: *Software*, *Java*, and *Hardware*. Each shows information, 
respectively, about your @product@ installation, Java installation, and 
hardware. This information is useful to the Liferay Support team in the event 
you need their assistance. 

![Figure 12: The Details tab shows information about your server.](../../images-dxp/lcs-server-details.png)

### Server Settings [](id=server-settings)

Finally, the *Server Settings* tab lets you view and edit your server's name,
location, and description. You can also unregister your server from LCS. 

![Figure 13: You can use the Server Settings tab to give your server a fun name.](../../images-dxp/lcs-server-settings.png)

## Managing Your LCS Account [](id=managing-your-lcs-account)

To manage your LCS account, select *My Account* from the user menu in the
Dockbar. This takes you to a UI with four tabs: 

**Projects:** Displays your LCS projects in a searchable table that lists the 
administrator's email address for each project. 

**Email Notifications:** Configure LCS to send you emails when specific 
events occur in your LCS projects by adding rules that define what events
trigger a notification. There are no rules by default. Click the *Add Rule*
button to define one. 

First specify the project, environment, and server for the notification. 
Note that you have the option of selecting all environments and servers in a
project. Then check the checkbox for each event that you want to trigger an 
email notification. For example, if you create a notification rule with *The 
server unexpectedly shuts down* selected for all servers and environments in
your project, then LCS sends you an email if any server in your project goes
offline without a normal shut down event. Click *Save* when you're done defining
the notification rule. It then appears in a table along with other existing
rules. Each has Edit and Delete Action buttons. 

![Figure 14: You can add rules to determine the events that trigger notifications.](../../images-dxp/lcs-add-notification-rule.png)

**Notification History:** Displays your web notification history in a 
searchable table. You can also select the date range from which to display 
notifications. 

**Preferences:** Manage your LCS account's preferences. You can change your
account's language, time zone, and default LCS project. Your default LCS project
is the one shown each time you log in to LCS. 

![Figure 15: You can change your LCS account's general preferences.](../../images-dxp/lcs-account-preferences.png)

### Using Web Notifications [](id=using-web-notifications)

LCS also displays web notifications under the bell icon in the Dockbar. A red
badge on this icon shows your unread notification count. LCS and Liferay Support
send these notifications. For example, LCS generates notifications when a server
shuts down or some other event requiring your attention occurs. To mark
a notification as read, click the small *x* icon next to it. To mark all
notifications as read, click the *Mark All as Read* button. To mark
notifications as unread again, click the *Undo* button that appears. To see your
notification history, click the *Notifications History* button. You can also
access your notification history by selecting *My Account* from the user menu in
the Dockbar. 

![Figure 16: Web notifications let you know what's happening in your LCS projects.](../../images-dxp/lcs-user-web-notifications.png)

## Managing Liferay DXP Subscriptions [](id=managing-liferay-dxp-subscriptions)

LCS lets you use and view your @product@ subscriptions. Recall that when you 
[create an environment](#creating-an-environment), 
you assign its subscription type and choose whether LCS activates servers that 
connect to that environment. If you use LCS for activation, registering a server 
in that environment consumes an activation key from the environment's 
subscription type. You can also view your project's available activation keys 
and see how they're being used. 

Depending on your subscription agreement, LCS also lets you register servers via 
*elastic subscriptions*. Elastic subscriptions let you register an unlimited 
number servers. This is invaluable in auto-scaling environments, where servers 
are automatically created and destroyed in response to load. Note that to use 
elastic subscriptions, you must set the environment as elastic when you create 
it. Also note that LCS only uses elastic subscriptions for servers that exceed 
the number that the environment's subscription type allows. In other words, LCS 
uses the environment's regular subscriptions before any elastic subscriptions. 

You can access these features from the *Subscriptions* tab on the upper-left of 
the LCS site. This tab contains two other tabs: *Details* and *Elastic 
Subscriptions*. 

![Figure 17: LCS lets you view and manage your subscriptions.](../../images-dxp/lcs-subscriptions.png)

There are four tables in the *Details* tab: 

1.  **Subscriptions:** Shows a list of the available subscriptions in your LCS 
    project. For each subscription, this table shows the following information: 

    - Subscription Type
    - Start Date
    - Expiration Date
    - Support End Date
    - Platform
    - Product
    - Processor Cores Allowed
    - Activation Keys
    - Used Activation Keys

    Note that *Processor Cores Allowed* shows the number of processor cores that 
    the subscription allows for each server. 

2.  **Subscriptions Summary:** Shows how your subscriptions are currently used 
    in your project. For each subscription type, this table shows the number of 
    activation keys allowed, used, and available. 

3.  **Project Environments:** Shows your project's environments and their 
    assigned subscription types. Each environment must have a subscription type. 

4.  **Project Servers:** Shows the environment and subscription type for each 
    server in your LCS project. 

If any of the information in these tables is missing or incorrect, contact 
Liferay Support. 

+$$$

**Note:** If you don't use LCS for activating your servers, then you can 
register as many servers as you want in LCS. 

$$$

+$$$

**Note:** If you try to activate a server that exceeds the number of processor 
cores that your subscription allows per server, the activation fails and the 
server is locked down. A console error also indicates the server's core count. 
You can compare this with your subscription's processor cores allowed in LCS's 
Subscriptions table. To activate the server, you can either reduce the number of 
cores it uses (e.g., by deploying to different server hardware, or reducing the 
number of virtual processors in a VM or container), or contact Liferay Sales to 
increase the number of processor cores that your subscription allows per server. 

$$$

### Decommissioning Servers [](id=decommissioning-servers)

To decommission a server and free its activation key for reuse, select the 
server's environment on the left and then select the server. In the server's 
*Server Settings* tab, select *Unregister*. Also note that when you shut down a 
server normally, its activation key is immediately freed for reuse. If the 
server crashes or its shutdown is forced (e.g., kill), its activation key is 
freed for reuse within six minutes. 

### Elastic Subscriptions [](id=elastic-subscriptions)

Elastic subscriptions let you register an unlimited number of servers. This is 
crucial for auto-scaling environments where servers are created and destroyed 
automatically. You can view data on your elastic servers from the 
*Subscriptions* tab's *Elastic Subscriptions* tab. 

+$$$

**Note:** To register elastic servers in an environment, that environment must 
be set as elastic when it's created. For more information, see the 
[documentation on creating environments](#creating-an-environment).

$$$

![Figure 18: The *Elastic Subscriptions* tab shows details about your project's elastic servers.](../../images-dxp/lcs-elastic-subscriptions.png)

The *Elastic Subscriptions* tab displays the number of elastic servers online 
and the uptime details for each. A graph shows the number of elastic servers 
online each day, while a table lists each elastic server's start time, end time, 
and duration. The total duration for servers is below the table. To download a 
report of the table's data, click *Download Report*. Also, you can use the 
*Environment* and *Month* selectors above the graph to select the environment 
and month to show data from, respectively. The data in both the graph and the 
table reflect your selections here. 

## Understanding Environment Tokens [](id=using-environment-tokens)

To register a server in an environment, you must use that environment's token 
file. LCS Administrators and Environment Managers can generate and distribute 
this file. It contains all the information the LCS client app needs to register 
the server in the environment. When the server starts up, it uses the token to 
connect to LCS. If you use LCS for activation, the server automatically consumes 
an activation key from the environment's subscription upon connection. This 
makes it possible to activate servers automatically on startup with no 
interaction required. 

+$$$

**Note:** For instructions on using and managing your environment tokens, see 
the instructions on 
[registering your server with LCS](/discover/deployment/-/knowledge_base/7-0/registering-your-dxp-server-with-lcs). 

$$$

There are a few things to keep in mind when using environment tokens: 

-   Each environment can have only one token file. If you regenerate the token, 
    servers using the old file are disconnected from LCS and can't reconnect 
    until receiving the new file. If the server disconnects due to token 
    regeneration and is running version 4.0.2 or later of the LCS client app, 
    the server enters a 30-day grace period during which it functions normally. 
    This gives the administrator time to use the new token file to reconnect to 
    LCS. Servers running earlier versions of the LCS client app present users 
    with an error page until the administrator reconnects with the new token. 

-   Use caution when distributing the token file, as anyone can use it to 
    connect to your environment (and consume an activation key in your 
    subscription if you're using LCS for activation). 

-   Minimal information (server name, location, etc...) is used to register a 
    server with LCS. You can change this information from 
    [the server view in LCS](#using-the-server-view) 
    at any time. 

-   Environment tokens connect using OAuth. Using an environment token overrides 
    the OAuth authorization cycle. If LCS Administrators or Environment Managers 
    have never registered servers in LCS, the first time they do so an OAuth 
    authorization entry is created in LCS. If they've previously registered 
    servers in LCS, their existing credentials are used when they create a token 
    file. 

-   If the credentials of the LCS user who generated the token become invalid, 
    you must generate a new token and use it to reconnect to LCS. An LCS user's 
    credentials become invalid if the user leaves the LCS project or becomes an 
    LCS Environment Manager or LCS Environment Viewer in a different 
    environment. 

So why bother with environment tokens at all? Besides simplifying the LCS 
connection process, environment tokens are valuable in auto-scaling environments 
where algorithms create and destroy servers automatically. In this situation, 
having clients that activate and configure themselves is crucial. 

+$$$

**Note**: If your auto-scaling environment creates new server nodes from a 
server in a system image, that server can't require human interaction during 
setup. When creating such an image, you must change any portal property settings 
that prevent automatic setup. By default, @product@'s setup wizard requires 
human interaction. You must therefore set the `setup.wizard.enabled` property to 
`false` if you want your auto-scaling environment to create new nodes from the 
server. 

$$$
