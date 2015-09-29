# Using LCS [](id=using-lcs)

Once your LCS client is registered with your LCS account, you can get down to 
the business that LCS was designed for--managing and monitoring your Liferay 
instance. If you're not already there, log in with your account on 
[lcs.liferay.com](https://lcs.liferay.com). This is where you'll apply updates, 
view server metrics, manage environments, invite external users to your project, 
and more. 

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

Now that you know what information is stored on the LCS servers, it's time to 
get to the heart of LCS: the Dashboard. 

The LCS Dashboard lets you view and manage your project, environments, and
servers. If you're not already at the Dashboard, click it near the upper
left-hand corner of your LCS site. Clicking *Dashboard* takes you to the
project view. From there, you can get to the environment view and the server
view. Each of these views gives you a different look into certain aspects of
your LCS project. You'll start with the project view.

## Using the Project View [](id=using-the-project-view)

You can get to the project view at any time by clicking the *Dashboard* tab near 
the upper left-hand corner of your LCS site. The project is listed to the right 
of this tab, with a drop-down arrow that lets you switch between projects if you 
have more than one. You can also switch between projects from the user menu at 
the top right of the Dockbar. The project view provides you with an overview of 
your LCS project, including fix packs, alerts, environments, and servers. Fix 
packs are displayed prominently in a table in the middle of the page. Note that 
the status, environment, server, and location are listed for each fix pack. If 
the fix pack is available, you can download it by clicking the *Download* icon
to its right. You can download several fix packs at once by checking the
checkbox to the left of each and then clicking the *Download* button above the
Fix Packs table. Once a fix pack is finished downloading, an alert appears in
the Alerts table below the Fix Packs table. This notification tells you the
download is finished and to restart your server. Restarting your server
installs any downloaded fix packs. Note that you must start your server with
the privileges required to write to the disk location where patches are stored
and processed (the `patching-tool` folder). 

But what about using LCS to install fix packs across a cluster? Just follow the 
same procedure! LCS downloads and installs fix packs simultaneously across all 
nodes--you don't have to handle each one individually. 

![Figure 4.13: The LCS project view shows an overview of your LCS project.](../../images/lcs-project-view.png)

In addition to displaying fix pack messages, the Alerts table also displays 
many other kinds of messages. For example, an alert appears whenever a 
server is offline. Other alerts appear when monitoring is unavailable, the 
patching tool is unavailable, or other issues are detected.

To the left of the fix packs and alerts are the environments in your project. 
You can also create new environments here by clicking the Add Environment tab. 
Note that the icon to the left of each environment differs depending on the 
environment's type and status. In fact, the icon's color and type tells you 
something about that environment:

- **Red icon:** Indicates that there is some sort of problem or issue with one or 
  more servers in that environment.
- **Green icon:** Indicates that the servers in that environment are operating 
  properly.
- **Icon with a circle:** Indicates that the servers in that environment are in a 
  cluster.

If an environment has fix packs available, a badge icon to its right indicates 
how many. You can get more information about a specific environment by clicking 
it. This takes you to the environment view.

## Using the Environment View [](id=using-the-environment-view)

Clicking an environment on the left-hand side of the project view takes you to 
the environment view. The environment view, as its name implies, gives you more 
information about an environment in your LCS project. As with the project view, 
fix packs and alerts are displayed on the center of the screen. In fact, they 
behave exactly the same here as they do in the project view. The only difference 
is that they show fix packs and alerts for the environment instead of the 
project as a whole. Located above the Fix Packs table, the Environment Details 
button lets you edit or delete the environment. You can also use it to generate 
and view *environment tokens* that allow automatic configuration of LCS clients. 
See the section below for more information on environment tokens. To the left of 
the Fix Packs table is the list of servers in the environment. Clicking on a 
server takes you to its server view. You can also create a new server in the 
environment by clicking the *Add Server* tab. It's important to note that this 
doesn't directly add a server in the environment. Instead, you're presented with 
a screen that lets you download the LCS client for the new server's version and 
edition. Once you download the client, you're given instructions for registering
the server with LCS.

![Figure 4.14: The LCS environment view shows an overview of an LCS environment.](../../images/lcs-environment-view.png)

## Using the Server View [](id=using-the-server-view)

The server view provides you with detailed information about a server, including 
statistics and performance metrics. You can get to the server view by clicking a
server in the environment view or by clicking a server in the Fix Packs or 
Alerts tables. Fix packs and alerts are the first thing you see when you enter 
server view. While the Alerts table functions the same as it does in the other 
views, the Fix Packs table behaves a bit differently. Fix packs are broken down 
into those that are available for installation and those that are already 
installed. You can access these through the Available and Installed tabs at 
the top of the fix packs table. 

![Figure 4.15: The LCS server view shows an overview of a server registered with LCS.](../../images/lcs-server-view.png)

Above the Fix Packs table is the Notifications button. Clicking it provides a 
list of notifications generated for the server. So how do notifications differ 
from alerts? Fantastic question! LCS notifications are sent via email. Thus,
they're an active form of notification. Alerts are passive, only appearing in
the Alerts table of a project, environment, or server. Also, notifications are
disabled by default. Configuring them is covered later in the section on
notifications. 

To view metrics and statistics of your server's performance, click the *Metrics* 
button near the top of the page. The metrics are broken down into three main 
categories: *Application*, *JVM*, and *Server*. Application is selected by 
default when you click the Metrics button. 

The Application category also has three other categories: *Pages*, 
*Portlets*, and *Cache*. Pages lists the frequency that specific pages 
load, along with their average load time. Portlets lists the same statistics, 
but for specific portlets in your server. The Cache category lists Liferay 
Single VM metrics and Hibernate metrics. In the following screenshot, the 
statistics in the Portlets category are shown.

![Figure 4.16: The LCS application metrics show portlet performance statistics, like frequency of use and average load time.](../../images/lcs-server-metrics-application-portlets.png)

The JVM category, as its name indicates, shows statistics about the JVM running 
on your server. This includes data on the garbage collector and memory. The 
number of runs, total time, and average time are listed for each garbage 
collector item. The memory metrics are presented in a bar chart that shows the 
usage of the PS Survivor Space, PS Old Gen, PS Eden Space, Code Cache, and PS 
Perm Gen.

![Figure 4.17: The LCS JVM metrics show performance data for memory and the garbage collector.](../../images/lcs-server-metrics-jvm.png)

Server is the third category in the Application category. The Server category
shows additional information about how your server is running. For example, a
horizontal bar graph shows the number of current threads that are running on
your server. Similarly, horizontal bar graphs are used to represent the JDBC
connection pools.

![Figure 4.18: The LCS server metrics show current threads and JDBC connection pools.](../../images/lcs-metrics-server.png)

You can view the settings for a server by clicking on the *Server Details* 
button, which is to the right of the Metrics button. The first tab under the 
Server Details button is Server Settings. This lets you view or edit your 
server's name, description, location, and environment. Click the *pencil icon*
to the right of each field to edit it. You can also unregister your server from 
LCS. The second tab under the Server Details button is Server Properties. This 
provides general information about your Liferay installation and hardware. This 
information is useful to the Liferay support team in the event that you need 
their assistance. 

![Figure 4.19: You can view and edit the details of your server registered with LCS.](../../images/lcs-server-details-settings.png)

As you can see, the LCS Dashboard is a powerful tool that greatly simplifies 
the update process and also gives you extensive information on how your servers 
are running. Next, you'll learn how to configure notifications in LCS.

## Configuring LCS Notifications [](id=configuring-lcs-notifications)

LCS can be configured to send you notification emails when specific events occur 
in your projects. LCS notifications are configured by adding *rules*. The rules 
define what events trigger a notification. To access notification settings, 
click *Notifications* from the user menu in the upper-right corner of LCS. 
You're presented with the *Add Rule* button and a table that lists any existing
rules. There are no rules by default. Click *Add Rule* to define one. 

![Figure 4.20: You can add rules to determine the events that trigger notifications.](../../images/lcs-add-notification-rule.png)

First specify the project, environment, and server for the notification. Note
that you have the option of selecting all environments and servers in a
project. Then check the checkbox for each event that you want to trigger an
email notification. For example, the notification rule in the illustration
above tells LCS to send you an email whenever any server in any of the
project's environments unexpectedly goes offline. You should note that this
differs from normal shutdown events, which don't trigger email notifications.
During normal server shutdown, the server communicates this status to LCS. An
email notification is only generated when the connection with the server is 
abruptly terminated. Click *Save* when you're done defining the notification 
rule. It then appears in a table along with any other existing rules. Each has 
an Actions button that lets you edit or delete it. 

Great! Now you know how to set notification rules in LCS. The next section shows 
you how to manage your project's users. 

## Managing LCS Users in Your Project [](id=managing-lcs-users-in-your-project)

The Users section of LCS is where you manage the LCS users that are part of your 
project. It's here that you can grant or revoke LCS roles or invite others that 
aren't yet part of your project. To manage users, first click the *Users* tab 
just below the Dashboard tab on the upper-left of your screen. You're presented 
with a table of the users in your project. To the right of each is the Manage 
button. Clicking *Manage* lets you assign or revoke LCS roles for that user. 

![Figure 4.21: The Users tab lets you manage the LCS users in your project.](../../images/lcs-users.png)

To invite external users to your project, click the *Invite* button. Here you
can invite anyone with a valid email address. You can also search for
Liferay.com users to invite. Once you choose some users, the *Role* selection
box lets you preassign LCS roles for when they accept your invitation. You can
also use the Environment selection box to preassign them to an environment in
your project.

![Figure 4.22: You can invite users to your LCS project and even preassign them roles.](../../images/lcs-invite-users.png)

To view sent invitations, click the *Invitations* tab. A table displays
invitations, listing invited users' email addresses along with who invited them
and the date that the invitation was sent. The table also shoes the preassigned
LCS role and environment. You can cancel an invitation by clicking *Cancel* in 
the Action column of the invitation. 

![Figure 4.23: The Invitations tab lets administrators view and cancel invitations.](../../images/lcs-invitations.png)

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

**Note**: In cloud environment, where an machine instance image is used to
replicate new server nodes, it is expected that encapsulated portal instance is
configured and ready to run. Portal Administrator should make sure that all
portal properties (like `setup.wizard.enabled` property) that expect human
interaction or could prevent automatic startup are set correctly or disabled
(example `setup.wizard.enabled=false`) before making an permanent machine image.

$$$

There are two places in LCS where you can generate and access environment 
tokens. If you read the previous sections of this article, then you already know 
one: the environment view. Navigate to an environment in LCS and click the 
*Environment Details* button. In environment details, click the *Subscriptions* 
tab. From here you can manage the environment's token. 

![Figure 4.24: The Subscriptions tab in Environment Details shows the token for only that environment.](../../images/lcs-environment-view-subscriptions.png)

By default, there's no existing token. A table appears that contains only a 
*Generate* button. Click it to generate a token for the environment. The new 
token then appears in the table with information on who generated it and when. 
There's also an Actions button next to it for downloading or regenerating the
token. 

You can also access environment tokens from the Subscriptions tab on the left 
side of LCS. Clicking the *Subscriptions* tab here shows the tokens for all the 
environments in your project. This provides a central location to manage all 
your environment tokens. Otherwise, the UI for managing them is exactly the 
same. 

![Figure 4.25: The Subscriptions tab on the left lets you manage the environment tokens for your entire project.](../../images/lcs-subscriptions.png)

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

![Figure 4.26: The OAuth authorization entry in the portal instance using an environment token to connect to LCS.](../../images/lcs-token-oauth-entry.png)

What if your Liferay instance has already been manually registered with LCS,
but you want to switch to using an environment token? No problem! Follow these
steps:  

1. Use the LCS client portlet to disconnect and reset the credentials in the 
   Liferay instance. Then press the *Back* button to the left of the Control 
   Panel text in the Dockbar. Pressing *Back* deletes the current OAuth 
   credentials. 

2. Shut down your Liferay instance and place the token file in the portal's 
   `data` folder. 

3. Restart your Liferay instance. The LCS client portlet uses the token file to 
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
