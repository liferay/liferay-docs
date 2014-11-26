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
get to the heart of LCS--the *Dashboard*.

The LCS Dashboard lets you view and manage your project, environments, and 
servers. If you're not already at the Dashboard, click it near the upper 
left-hand corner of your LCS site. Clicking *Dashboard* takes you to the 
project view. From there, you can get to the environment view and the 
server view. Each of these views gives you a different look into certain 
aspects of your LCS project. You'll start with the project view.

## Using the Project View [](id=using-the-project-view)

You can get to the project view at any time by clicking the Dashboard tab 
near the upper left-hand corner of your LCS site. The project view provides you 
with an overview of your LCS project, including fix packs, alerts, environments, 
and servers. Fix packs are displayed prominently in a table on the middle of the 
page. Note that the status, environment, server, and location are listed for each 
fix pack. If the fix pack is available, you can download it by clicking the 
Download button to its right. Once a fix pack is finished downloading, an 
alert appears in the Alerts table below the fix packs table. This notification
tells you the download is finished and to restart your server. Restarting your
server installs any downloaded fix packs. Note that you must start your server
with the privileges required to write to the disk location where patches are 
stored and processed (the `patching-tool` folder). 

But what about using LCS to install fix packs across a cluster? Just follow the 
same procedure! LCS downloads and installs fix packs simultaneously across all 
nodes--you don't have to handle each one individually. 

![Figure 4.9: The LCS project view shows an overview of your LCS project.](../../images/lcs-project-view.png)

In addition to displaying fix pack messages, the Alerts table also displays 
many other kinds of messages. For example, an alert appears whenever a 
server is offline. Since this is an important message, it appears with a red 
dot next to it. Other alerts appear when monitoring is unavailable, the patching 
tool is unavailable, or other issues are detected.

To the left of the fix packs and alerts are the environments in your project. 
You can also create new environments here by clicking the blue plus icon. If you 
look at the icons next to each environment, you'll notice that they're not all 
the same. In fact, the color and type of the icon tells you something about that 
environment:

- Red icon: Indicates that there is some sort of problem or issue with one or 
  more servers in that environment.
- Green icon: Indicates that the servers in that environment are operating 
  properly.
- Icon with a circle: Indicates that the servers in that environment are in a 
  cluster.

You can get more information about a specific environment by clicking it. This 
takes you to the environment view.

## Using the Environment View [](id=using-the-environment-view)

Clicking an environment on the left-hand side of the project view takes you to 
the environment view. The environment view, as its name implies, gives you more 
information about an environment in your LCS project. As with the project view, 
fix packs and alerts are displayed on the center of the screen. In fact, they 
behave exactly the same here as they do in the project view. The only difference 
is that they show fix packs and alerts for the environment instead of the 
project as a whole. Located above the fix packs, the Environment Details 
button lets you edit or delete the environment. To the left of the fix packs is 
the list of servers in the environment. Clicking on one of the servers takes you 
to the server view for that server.

![Figure 4.10: The LCS environment view shows an overview of an LCS environment.](../../images/lcs-environment-view.png)

## Using the Server View [](id=using-the-server-view)

The server view provides you with detailed information about a server, including 
statistics and performance metrics. You can get to the server view by clicking a
server in the environment view or by clicking a server in the fix pack or alerts
tables. Fix packs and alerts are the first thing you see here, as the Fix Packs
Alerts button is selected by default when you enter server view. While the
alerts table functions the same as it does in the other views, the fix packs
table behaves a bit differently. Fix packs are broken down into those that are
available for installation and those that are already installed. You can access
these through the *Available* and *Installed* tabs at the top of the fix packs
table. 

![Figure 4.11: The LCS server view shows an overview of a server registered with LCS.](../../images/lcs-server-view.png)

To view metrics and statistics of your server's performance, click the *Metrics* 
button near the top of the page. The metrics are broken down into three main 
categories--*Application*, *JVM*, and *Server*. Application is selected by 
default when you click the Metrics button. 

The Application category also has three other categories within it--*Pages*, 
*Portlets*, and *Cache*. Pages lists the frequency with which specific pages are 
loaded, along with their average load time. Portlets lists the same statistics, 
but for specific portlets in your server. The Cache category lists Liferay 
Single VM metrics and Hibernate metrics. In the following screenshot, the 
statistics in the Portlet category are shown.

![Figure 4.12: The LCS application metrics show portlet performance statistics, like frequency of use and average load time.](../../images/lcs-server-metrics-application-portlets.png)

The JVM category, as its name indicates, shows statistics about the JVM running 
on your server. This includes data on the garbage collector and memory. The 
number of runs, total time, and average time are listed for each garbage 
collector item. The memory metrics are presented in a bar chart that shows the 
usage of the Code Cache, PS Eden Space, PS Old Gen, PS Perm Gen, and PS Survivor 
Space.

![Figure 4.13: The LCS JVM metrics show performance data for memory and the garbage collector.](../../images/lcs-server-metrics-jvm.png)

Server is the third category in the Application category. The Server category
shows additional information about how your server is running. For example, a
horizontal bar graph shows the number of current threads that are running on
your server. Similarly, horizontal bar graphs are used to represent the JDBC
connection pools.

![Figure 4.14: The LCS server metrics show current threads and JDBC connection pools.](../../images/lcs-metrics-server.png)

You can also view the settings for a server by clicking on the *Server Details* 
button, which is to the right of the Metrics button. The first tab under the 
Settings button is *LCS Server Settings*. This lets you view or edit your 
server's name, description, and location. You can also unregister your server 
from LCS. The second tab under the Settings button is *About Server*. This 
provides general information about your Liferay instance and hardware. This 
information is useful to the Liferay support team in the event that you need 
their assistance.

![Figure 4.15: You can view and edit the details of your server registered with LCS.](../../images/lcs-server-details-settings.png)

As you can see, the LCS Dashboard is a powerful tool that greatly simplifies 
the update process and also gives you extensive information on how your servers 
are running. Next you'll take a look at how to manage the users in your LCS 
project.

## Managing LCS Users in Your Project [](id=managing-lcs-users-in-your-project)

The Users section of LCS is where you manage the LCS users that are part of your 
project. It's here that you can grant or revoke LCS roles or invite others that 
aren't yet part of your project. To manage users, first click the *Users* icon 
just below the Dashboard icon on the upper-left of your screen. You're 
presented with a table of the users on your project. To the right of each is the 
Manage button. Clicking *Manage* lets you assign or revoke LCS roles for that 
user.

![Figure 4.16: The Users tab lets you manage the LCS users in your project.](../../images/lcs-users.png)

To invite external users to your project, click on the *Invite* button. The
*Invite User* pop up lets you invite anyone with a valid email address. You can 
also search for users of Liferay.com to invite. Once you've chosen who to 
invite, the *Role* selection box lets you preassign LCS roles for when they 
accept your invitation.

![Figure 4.17: You can invite users to your LCS project, and even preassign them roles.](../../images/lcs-invite-users.png)

To view any sent invitations, just click the *Invitations* tab. The invitations 
are shown in a table that lists the email address of the person invited, as well 
as who invited them and the date that the invitation was sent. The preassigned 
LCS role and environment is also listed. You can also cancel an invitation by 
clicking the red *Cancel* button in the *Action* column of the invitation.

![Figure 4.18: The Invitations tab lets administrators view and cancel invitations.](../../images/lcs-invitations.png)

As you've now seen, LCS is a powerful tool that simplifies the management of 
your Liferay servers. You can apply fix packs with just a single click and a 
server restart--a process that even works across a cluster. You also get a one 
stop shop for monitoring the performance of your Liferay servers. Metrics like 
JVM performance, Liferay page and portlet load times, and number of current 
threads give you an inside look at how your server is running. What's more is 
that you can do all this collaboratively by inviting others to your project and 
giving them specific roles in LCS. 

Next, you'll learn about Liferay clustering.
