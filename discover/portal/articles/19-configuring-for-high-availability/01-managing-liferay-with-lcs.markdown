# Managing Liferay with Liferay Connected Services [](id=managing-liferay-with-liferay-connected-services)

Liferay Connected Services (LCS) is a set of tools and services that lets you 
manage and monitor your Liferay installations. While Liferay's patching tool 
lets you apply fix packs and other updates, the install process is still manual. 
LCS simplifies this process by automatically installing any fix packs that you 
*choose*. That last point is an important one--LCS won't install anything that 
you don't specifically choose for installation. You still have control over what 
gets applied to your Liferay installations. LCS just automates the process by 
enabling one-click downloading and updating. You can also use LCS to monitor the 
performance of your Liferay instances. This includes data on pages, portlets, 
memory usage, JVM performance, and much more. Even better, the features of LCS 
work regardless of whether your Liferay instance is on a single discreet server 
or distributed across a node. 

Before going any further, you should make sure that your Liferay instances meet 
the requirements for LCS--you must be running Liferay Portal 6.1 GA 3, or 6.2 GA
1 or above. Using LCS to apply fix packs and other updates is an EE only 
feature. The monitoring features of LCS are available to both Liferay CE and EE.

Also, you should take note of a few key terms used throughout this guide: 

- *Project*: Represents a group of users belonging to a company or organization. 
  For example, a project can consist of all the users from a project team or 
  business unit, or it can include the entire company.
- *Environment*: Represents a physical cluster of servers or a virtual or 
  logical aggregation of servers.
- *Server*: Describes a concrete portal instance. It can be a standalone server 
  or a cluster node.

As you go through this guide, you'll cover the following sections on LCS:

- LCS Account Setup
- Portal Preconfiguration (Liferay 6.1 GA3 only)
- Patching Tool Configuration (EE only)
- LCS Client Configuration
- Using LCS

Next, you'll get started by setting up your LCS account.

## Setting up Your LCS Account [](id=setting-up-your-lcs-account)

To use LCS, you must have an account at [Liferay.com](http://www.liferay.com/). 
Once you've logged in with your Liferayuser credentials, you're taken to the 
*Projects* screen where you can join an existing project or create a new one. 

![Figure 19.1: When creating your LCS account, you can join an existing project or create a new one.](../../images/lcs-projects-01.png)

The list of existing projects, if present, is the list of projects associated 
with the domain of your email address. This is typically the company your email 
address is associated with. For example, if your email address is 
`joebloggs@janesblogfactory.com`, you're then presented with a list of existing 
projects registered to users with the `@janesblogfactory.com` domain. This lets 
you quickly and easily join the LCS projects that are associated with your 
company. 

Note that if you're already a member of any projects that don't have an 
administrator, you're given the option to become the administrator. These 
projects are listed in the *Available Projects Without an Administrator* 
section. To become the administrator for one of these projects, check the 
checkbox next to it and click *Save*.

Any other existing projects in your domain are listed in the 
*My Company Projects* section. To request access to a project, check its 
checkbox and click *Save*. Your request is then sent to the project 
administrator for approval. Note that it's possible for the project 
administrator to pre-assign you a role in a project (more on LCS roles in a 
moment). In this case, you won't have to wait for approval. For more information 
on user management in LCS, please see the *Managing LCS Users in Your Project* 
section of this guide.

Alternatively, you can create a new project and make yourself its administrator.
To do so, click the checkbox next to *Create a project and become an
administrator* in the *New Project* section of the Projects screen. The section
then expands to let you name the project and choose to go to directly to the
project's *Dashboard* (more on the Dashboard later). Give your project a name
and then click *Save*. 

![Figure 19.2: Check the checkbox and click *Save* to begin creating a new project.](../../images/lcs-projects-02.png)

![Figure 19.3: Give your new project a name and click *Save*.](../../images/lcs-new-project.png)

So what exactly can an LCS Administrator do? Fantastic question! As LCS 
Administrator, you can assign roles to the rest of the users in your project. 
Each user of LCS needs to have an assigned role. The following roles are 
available: 

- LCS Administrator: All LCS functionality is available to administrators. This 
  is the only role that can manage the roles of other users.
- LCS Environment Manager: All LCS functionality is available in the scope of 
  an environment, with the exception of managing other users.
- LCS Environment Viewer: Has read-only access in the scope of an environment.

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

Now that your LCS account has been set up and you have an understanding of the 
LCS roles, you can get your portal ready for LCS.

## Preconfiguring Your Portal for LCS [](id=preconfiguring-your-portal-for-lcs)

If you're running Liferay 6.2, then you can skip this step. If, however, you are 
running Liferay 6.1 GA3, then there's a small pre-configuration step that you 
need to take before using LCS: you need to update the `spring.configs` property 
in your `portal-ext.properties` file. This is a workaround for a bug in Liferay 
6.1 GA3, where the values for this property are listed in the wrong order. This 
causes the metrics service in LCS to not be initialized. To fix this, add the 
following configuration to your `portal-ext.properties` file:

    spring.configs=\
        META-INF/base-spring.xml,\
        \
        META-INF/hibernate-spring.xml,\
        META-INF/infrastructure-spring.xml,\
        META-INF/management-spring.xml,\
        \
        META-INF/util-spring.xml,\
        \
        META-INF/jpa-spring.xml,\
        \
        META-INF/executor-spring.xml,\
        \
        META-INF/audit-spring.xml,\
        META-INF/cluster-spring.xml,\
        META-INF/editor-spring.xml,\
        META-INF/jcr-spring.xml,\
        META-INF/ldap-spring.xml,\
        META-INF/messaging-core-spring.xml,\
        META-INF/messaging-misc-spring.xml,\
        META-INF/mobile-device-spring.xml,\
        META-INF/notifications-spring.xml,\
        META-INF/poller-spring.xml,\
        META-INF/rules-spring.xml,\
        META-INF/scheduler-spring.xml,\
        META-INF/scripting-spring.xml,\
        META-INF/search-spring.xml,\
        META-INF/workflow-spring.xml,\
        \
        META-INF/counter-spring.xml,\
        META-INF/mail-spring.xml,\
        META-INF/portal-spring.xml,\
        META-INF/portlet-container-spring.xml,\
        META-INF/staging-spring.xml,\
        META-INF/virtual-layouts-spring.xml,\
        \
        META-INF/monitoring-spring.xml,\
        \
        #META-INF/dynamic-data-source-spring.xml,\
        #META-INF/shard-data-source-spring.xml,\
        #META-INF/memcached-spring.xml,\
        \
        META-INF/ext-spring.xml

A server restart is required after updating `portal-ext.properties`. Next, you 
need to configure the patching tool.

## Configuring the Patching Tool [](id=configuring-the-patching-tool)

Liferay EE's patching tool is leveraged by LCS to apply its updates. If you're 
running a Liferay EE bundle, then you already have the patching tool installed. 
If you're not running a bundle, then please see the user guide section on 
[how to install the patching tool](/portal/-/knowledge_base/6-1/patching-liferay). 
Once you have the patching tool installed, there are a few steps you need to 
complete before LCS can use it. Note that the commands below apply to Linux, 
Unix, and Mac systems. If you're running Windows, simply drop the `.sh` from 
each command that has it.

1. Navigate to the `patching-tool` directory on the command line. It's typically 
   located in the Liferay Home folder.

2. Make sure that you have version 10 or higher of the patching tool. To 
   display the version of your patching tool, run `patching-tool.sh info`.
   
3. Let the patching tool discover your Liferay installation by running 
   `patching-tool.sh auto-discovery`.

4. Configure the patching tool by running `patching-tool.sh setup`.

5. Patches downloaded through LCS are installed by the patching tool agent on 
   server startup. For the agent to start with your server, you need to set the 
   `javaagent` property in the JVM options. Make sure that you specify the 
   correct file path to the `patching-tool-agent.jar`. Here's an example of 
   setting the `javaagent` property:

        -javaagent:../../patching-tool/lib/patching-tool-agent.jar

If your patching tool is installed in a location other than the Liferay Home 
folder, you must specify the path of the `patching-tool` folder as a JVM 
argument for the app server. This is done with the `patching.tool.home` 
property. For example:

        -Dpatching.tool.home=/opt/liferay-portal-6.1.20-ee-ga2/patching-tool/

Great! Now you're all set to deploy and configure the LCS client portlet.

## Configuring the LCS Client [](id=configuring-the-lcs-client)

To take advantage of the features offered by LCS, you need to install the LCS 
client in your Liferay instance. The LCS client connects your Liferay instance 
to LCS and provides a simple UI for managing that connection. You can also use 
the LCS client to directly access your LCS project, environment, and server from 
within your Liferay instance. 

## Downloading the LCS Client [](id=downloading-the-lcs-client)

There are multiple places in LCS where you can download the LCS client. This 
ensures that you have quick access whenever you need the client. The first and 
most prominent download location is presented after you sign in to LCS on 
[https://lcs.liferay.com](https://lcs.liferay.com). Choose your portal version 
and edition on the Welcome screen and then click *Download*. In the following 
screenshot, Liferay Portal 6.1 EE is selected. 

![Figure 19.4: You can download the LCS client from the LCS Welcome screen.](../../images/lcs-welcome-client-download.png)

You can also download the LCS client from your LCS project's Dashboard. To get 
to your Dashboard, click the *Go to My Dashboard* link on the Welcome screen, or 
click your project's link in a deployed and registered LCS client. By letting 
you view and manage the environments and servers in your project, the Dashboard 
is at the heart of LCS. The following screenshot shows the Dashboard with its 
LCS client download links highlighted in a red box. 

![Figure 19.5: The LCS Dashboard contains links to download the client.](../../images/lcs-dashboard-client-download.png)

The LCS client is also available for download from the User menu in your LCS 
account's Dockbar. To download it from here, click your name in the top-right 
corner of your LCS account and then click *Download Client* in the menu that 
appears. This is shown in the following screenshot. You're then shown a screen 
similar to the Welcome screen, where you select your portal's version, edition, 
and then click *Download*. 

![Figure 19.6: You can also download the LCS client from the User menu in your LCS account's Dockbar.](../../images/lcs-user-menu-client-download.png)

There's one final place where you can download the LCS client: when creating a 
new server in an environment. When you click the *Add Server* tab in an 
environment, you're presented with a screen similar to the Welcome screen. 
Select your portal's version and edition, and then click *Download*.

![Figure 19.7: Adding a new server in an environment also presents you with the LCS client download screen.](../../images/lcs-add-server-client-download.png)

Super! Now you know all the places where you can download the LCS client. Before 
you deploy it though, there are a few other things you should check. If you 
connect to the web through a proxy or need to fine-tune how the client connects 
to LCS, proceed to the next section. Otherwise, you can skip to the final 
section in this article for instructions on registering the client with LCS. 

## Preconfiguring the LCS Client [](id=preconfiguring-the-lcs-client)

In most cases, deploying and configuring the LCS client is simple. If, however,
you connect to the web through a proxy, or you want to change how the client 
communicates with LCS, there are some properties you need to set in the 
client's WAR file before deploying it. Specifically, you need to set these 
properties in the client's `portlet-ext.properties` file. Regardless of the 
properties you're setting, the procedure doing so is the same.

1. In the LCS client's WAR file, open the 
   `WEB-INF/classes/portlet-ext.properties` file.
 
2. Make your changes in the file.

3. Update the LCS client WAR with the modified `portlet-ext.properties` file.
 
4. Deploy the LCS client WAR or redeploy it if it's already deployed. 

To connect to LCS through a proxy, add the following properties at the end of 
`portlet-ext.properties` and set them to the appropriate values for your proxy.
   
        proxy.host.name=
        proxy.host.port=

If your proxy uses authentication, you should also add the following properties 
and set them to the appropriate values for your proxy.
   
        proxy.host.login=
        proxy.host.password= 

While the default values are sufficient in most cases, you can also set other 
properties in `portlet-ext.properties` to fine tune the client's communication 
with LCS. As with the proxy properties, you should set these prior to deploying 
the client. The communication properties are shown here with their default 
settings. The values are specified in milliseconds. 

- The Heartbeat Interval is the communication interval with LCS that keeps the 
  client's connection alive, even when there's nothing else to report. It's set 
  by the following property.
  
        communication.heartbeat.interval=60000

- The handshake properties control how the client handles its initial handshake 
  connection with LCS. The `communication.handshake.wait.time` property sets the 
  maximum amount of time the client can wait to receive a response from LCS. The 
  `communication.handshake.reply.reads` property sets the maximum number of 
  times the client can check for a response from LCS during that time. 

        communication.handshake.wait.time=60000
        communication.handshake.reply.reads=6        

- The LCS client depends on a gateway to connect with LCS. If it can't reach the 
  gateway, the client waits a predetermined amount of time before attempting the 
  connection again. This length of time is set by the following property.
  
        communication.lcs.gateway.unavailable.wait.time=60000

Great! Now you're ready to deploy the client and register your portal with LCS.

## Registering the Client with LCS [](id=registering-the-client-with-lcs)

Once you deploy the LCS client, you're ready to register your server with LCS. 
Access the client by clicking on *Liferay Connected Services* under the *Apps* 
section of the *Control Panel*. 

![Figure 19.8: LCS appears in your Control Panel as Liferay Connected Services, in the Apps section.](../../images/lcs-post-install-01.png)

After clicking *Authorize Access* on the screen that appears, you are then taken 
to the LCS website. Log in with your credentials and then click *Grant Access*.

![Figure 19.9: The LCS client requires access to your LCS account.](../../images/lcs-auth-access.png)

Next, you need to register your server with LCS by filling out the following 
fields:

1. Connected Services Project: Choose your project from the menu. 

2. Environment: Select an environment from the menu or create a new one by
   clicking the *Add New Environment* button. An environment should be a logical
   group of servers. A group of development servers or a cluster are good 
   examples of environments. If you elect to create a new environment, a popup 
   asks you to enter its *Name*, *Location*, and *Description*.

    ![Figure 19.10: The Add New Environment pop-up.](../../images/lcs-new-environment.png)

3. Server Name: Give your server a name that you can use to distinguish it in 
   your LCS account.

4. Server Location: This can be a city, location in an office building, or 
   anywhere else that lets you know where the server is physically located.

5. Server Description: Type in any description that you want to give your 
   server. You can use this field to help differentiate the servers you are 
   managing using LCS.

![Figure 19.11: The server registration screen.](../../images/lcs-register-server.png)

Upon clicking *Register*, your LCS client shows your connection status. Note 
that it can take up to 10 or 20 seconds to perform the initial handshake and 
send the first batch of information to LCS. If you don't see any change in 
status after 20 seconds, try refreshing the page. If you see a red symbol 
indicating no data is being transmitted, please contact the Liferay team for 
support. 

![Figure 19.12: The server is connecting to LCS.](../../images/lcs-server-connecting.png)

Once a successful connection is established, some statistics and links are
displayed. Here's a description of what's displayed:

- **Heartbeat Interval:** The interval of the communication that maintains the 
  connection with LCS. This regular communication keeps the client's LCS 
  connection alive, even when there's nothing else to report. The value is 
  listed in hours, minutes, and then seconds. For example, if this value is 
  `00:01:00`, the client communicates with LCS once every minute. 
- **Message Task Interval:** The interval at which the client checks LCS for new 
  messages. For example, LCS messages are used to instruct the client to 
  download new fix packs.
- **Metrics Task Interval:** The interval at which server statistics and metrics 
  are sent to LCS.
- **Last Message Received:** The time the latest message was received from LCS.
- **Connection Uptime:** The duration of the client's connection with LCS.
- **Project Home:** This link takes you to this server's registered 
  project.
- **Environment:** This link takes you to this server's registered 
  environment.
- **Server Dashboard:** This link takes you to the server on LCS.
- **Disconnect:** Disconnects this Liferay instance from LCS.
- **Reset Credentials:** Removes the authorizing account from registration with LCS. 
  It's important to note that your credentials are not human readable in the 
  portal instance. This is because OAuth, which uses tokens, is used for 
  authentication.
 
![Figure 19.13: The server is connected to LCS.](../../images/lcs-server-connected.png)

This article's previous section contains instructions for changing the heartbeat 
interval. Currently, the message and metrics task intervals are fixed and cannot 
be changed. 

Awesome! Now that you've registered your server with your LCS account, you can 
dig in to the features of LCS. 

## Using LCS [](id=using-lcs)

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

![Figure 19.14: The LCS project view shows an overview of your LCS project.](../../images/lcs-project-view.png)

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
edition. Once you download the client, you're presented with instructions for 
registering the server with LCS.

![Figure 19.15: The LCS environment view shows an overview of an LCS environment.](../../images/lcs-environment-view.png)

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

![Figure 19.16: The LCS server view shows an overview of a server registered with LCS.](../../images/lcs-server-view.png)

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

![Figure 19.17: The LCS application metrics show portlet performance statistics, like frequency of use and average load time.](../../images/lcs-server-metrics-application-portlets.png)

The JVM category, as its name indicates, shows statistics about the JVM running 
on your server. This includes data on the garbage collector and memory. The 
number of runs, total time, and average time are listed for each garbage 
collector item. The memory metrics are presented in a bar chart that shows the 
usage of the PS Survivor Space, PS Old Gen, PS Eden Space, Code Cache, and PS 
Perm Gen.

![Figure 19.18: The LCS JVM metrics show performance data for memory and the garbage collector.](../../images/lcs-server-metrics-jvm.png)

Server is the third category in the Application category. The Server category
shows additional information about how your server is running. For example, a
horizontal bar graph shows the number of current threads that are running on
your server. Similarly, horizontal bar graphs are used to represent the JDBC
connection pools.

![Figure 19.19: The LCS server metrics show current threads and JDBC connection pools.](../../images/lcs-metrics-server.png)

You can view the settings for a server by clicking on the *Server Details* 
button, which is to the right of the Metrics button. The first tab under the 
Settings button is Server Settings. This lets you view or edit your 
server's name, description, location, and environment. You can also unregister 
your server from LCS. The second tab under the Settings button is 
Server Properties. This provides general information about your Liferay 
installation and hardware. This information is useful to the Liferay support 
team in the event that you need their assistance.

![Figure 19.20: You can view and edit the details of your server registered with LCS.](../../images/lcs-server-details-settings.png)

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

![Figure 19.21: You can add rules to determine the events that trigger notifications.](../../images/lcs-add-notification-rule.png)

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

![Figure 19.22: The Users tab lets you manage the LCS users in your project.](../../images/lcs-users.png)

To invite external users to your project, click the *Invite* button. Here you
can invite anyone with a valid email address. You can also search for
Liferay.com users to invite. Once you choose some users, the *Role* selection
box lets you preassign LCS roles for when they accept your invitation. You can
also use the Environment selection box to preassign them to an environment in
your project.

![Figure 19.23: You can invite users to your LCS project and even preassign them roles.](../../images/lcs-invite-users.png)

To view sent invitations, click the *Invitations* tab. A table displays
invitations, listing invited users' email addresses along with who invited them
and the date that the invitation was sent. The table also shoes the preassigned
LCS role and environment. You can cancel an invitation by clicking *Cancel* in 
the Action column of the invitation. 

![Figure 19.24: The Invitations tab lets administrators view and cancel invitations.](../../images/lcs-invitations.png)

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

There are two places in LCS where you can generate and access environment 
tokens. If you read the previous sections of this article, then you already know 
one: the environment view. Navigate to an environment in LCS and click the 
*Environment Details* button. In environment details, click the *Subscriptions* 
tab. From here you can manage the environment's token. 

![Figure 19.25: The Subscriptions tab in Environment Details shows the token for only that environment.](../../images/lcs-environment-view-subscriptions.png)

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

![Figure 19.26: The Subscriptions tab on the left lets you manage the environment tokens for your entire project.](../../images/lcs-subscriptions.png)

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

![Figure 19.27: The OAuth authorization entry in the portal instance using an environment token to connect to LCS.](../../images/lcs-token-oauth-entry.png)

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
