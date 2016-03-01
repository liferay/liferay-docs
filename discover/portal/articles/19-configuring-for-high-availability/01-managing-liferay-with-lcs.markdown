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

- [Portal Preconfiguration (Liferay 6.1 GA3 only)](/discover/portal/-/knowledge_base/6-1/managing-liferay-with-liferay-connected-services#preconfiguring-your-portal-for-lcs)
- [Patching Tool Configuration (EE only)](/discover/portal/-/knowledge_base/6-1/managing-liferay-with-liferay-connected-services#configuring-the-patching-tool)
- [LCS Client Configuration](/discover/portal/-/knowledge_base/6-1/managing-liferay-with-liferay-connected-services#configuring-the-lcs-client)
- [Using LCS](/discover/portal/-/knowledge_base/6-1/managing-liferay-with-liferay-connected-services#using-lcs)

You'll get started with a few configuration steps that are required to use LCS 
with your Liferay portal instances.

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

### Downloading the LCS Client [](id=downloading-the-lcs-client)

To use LCS, you must have an account at
[Liferay.com](http://www.liferay.com/). When you first log in to 
[LCS](https://lcs.liferay.com), the Welcome screen prompts you to select your 
portal's version and edition. After doing so, the LCS client's version is listed 
next to a *Download* button. Click this button to download the client. The 
following screenshot shows the Welcome screen with Liferay Portal 6.2 EE 
selected.

![Figure 19.1: You can download the LCS client from the LCS Welcome screen.](../../images/lcs-welcome-client-download.png)

Note that you'll only see the Welcome screen the first time you log in to LCS. 
If you need to download the client again later, you can do so from the 
*Connection* tab inside LCS. When you click the *Connection* tab, the 
*Client Downloads* button is automatically selected. This presents the same 
screen as earlier, in which you can download the client. The following 
screenshot shows this: 

![Figure 19.2: You can also download the LCS client from the Connection tab inside of LCS.](../../images/lcs-client-download-connection.png)

Super! Now you know where to get the LCS client. Before you deploy it though, 
there are a few other things you should check. If you connect to the web through 
a proxy or need to fine-tune how the client connects to LCS, proceed to the next 
section. Otherwise, you can skip this section and move on to the section on 
registering the client with LCS. 

### Preconfiguring the LCS Client [](id=preconfiguring-the-lcs-client)

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

### Registering the Client with LCS [](id=registering-the-client-with-lcs)

Once you deploy the LCS client, you're ready to register your server with LCS. 
Access the client by clicking on *Liferay Connected Services* under the *Apps* 
section of the *Control Panel*. 

![Figure 19.3: LCS appears in your Control Panel as Liferay Connected Services, in the Apps section.](../../images/lcs-post-install-01.png)

The LCS client first asks you to choose the LCS services you want to enable for 
this portal instance. The *Enable All Services* checkbox is selected by default. 
This enables portal analytics, fix pack management (for EE portals), and portal 
property analysis. Unchecking this checkbox presents you with additional 
checkboxes for enabling each of those services. Note that the *Fix Packs 
Management* option is unavailable for Liferay CE. Also note that although 
[LCS doesn't access security sensitive properties](/discover/portal/-/knowledge_base/6-1/managing-liferay-with-liferay-connected-services#what-lcs-stores-about-your-liferay-servers), 
you may have additional properties you want to prevent LCS from analyzing. If 
you select *Portal Properties Analysis*, a text box appears for you to enter any 
properties you don't want LCS to analyze. Click *Next* when you finish enabling 
the LCS services you want to use. 

![Figure 19.4: Select the LCS services that you want to enable for your portal.](../../images/lcs-enable-services.png)

Next, LCS asks you to authorize access to your LCS account. After clicking 
*Authorize Access*, LCS takes you to the LCS website. Log in with your 
credentials and then click *Grant Access*. 

![Figure 19.5: The LCS client requires access to your LCS account.](../../images/lcs-auth-access.png)

Now you must register your server with LCS by filling out the following fields:

1. Connected Services Project: Choose an existing LCS project from the menu. If 
   you don't yet have an LCS project, you can use the default *My LCS Project* 
   that LCS creates for you (you can change this project's name later). 

2. Environment: An environment should be a logical group of servers. A group of 
   development servers or a cluster are good examples of environments. Select an 
   existing environment from the menu, or create a new one by clicking the *Add 
   New Environment* button. If you don't yet have an environment, you can use 
   the default *My Environment* that LCS creates for you (you can change this 
   environment's name later). If you elect to create a new environment, a popup 
   asks you to enter the new environment's *Name*, *Location*, and 
   *Description*. 

    ![Figure 19.6: The Add New Environment pop-up.](../../images/lcs-new-environment.png)

3. Server Name: Give your server a name that you can use to distinguish it in 
   your LCS account.

4. Server Location: This can be a city, location in an office building, or 
   anywhere else that lets you know where the server is physically located.

5. Server Description: Type in any description that you want to give your 
   server. You can use this field to help differentiate the servers you are 
   managing using LCS.

![Figure 19.7: The server registration screen.](../../images/lcs-register-server.png)

Upon clicking *Register*, your LCS client shows your connection status. Note 
that it can take up to 10 or 20 seconds to perform the initial handshake and 
send the first batch of information to LCS. If you don't see any change in 
status after 20 seconds, try refreshing the page. If you see a red symbol 
indicating no data is being transmitted, please contact the Liferay team for 
support. 

Once a successful connection is established, some statistics and links are
displayed. Here's a description of what's displayed:

- **Heartbeat Interval:** The interval of the communication that maintains the 
  connection with LCS. This regular communication keeps the client's LCS 
  connection alive, even when there's nothing else to report. The value is 
  listed in hours, minutes, and then seconds. For example, if this value is 
  `00:01:00`, the client communicates with LCS once every minute. This article's 
  previous section contains instructions for changing the heartbeat interval. 
- **Message Task Interval:** The interval at which the client checks LCS for new 
  messages. For example, LCS messages are used to instruct the client to 
  download new fix packs. Currently, this interval is fixed and can't be 
  changed. 
- **Metrics Task Interval:** The interval at which server statistics and metrics 
  are sent to LCS. Currently, this interval is fixed and can't be changed. 
- **Last Message Received:** The time the latest message was received from LCS.
- **Connection Uptime:** The duration of the client's connection with LCS.
- **Project Home:** This link takes you to this server's registered LCS project.
  The project home in LCS is also called the *dashboard*.
- **Environment:** This link takes you to this server's registered environment.
- **Server Dashboard:** This link takes you to the server on LCS.
- **Configure Services:** This link lets you change which LCS services are 
  enabled for your portal. Doing so triggers reconnection with the new settings. 
- **Disconnect:** Disconnects this Liferay instance from LCS.

![Figure 19.8: The server is connected to LCS.](../../images/lcs-server-connected.png)

Awesome! Now that you've registered your server with your LCS account, you can 
dig in to the features of LCS. 

## Using LCS [](id=using-lcs)

Once your LCS client is registered with your LCS account, you can get down to 
the business that LCS was designed for--managing and monitoring your Liferay 
instance. If you're not already there, log in with your account on 
[lcs.liferay.com](https://lcs.liferay.com). This is where you'll apply updates, 
view server metrics, manage environments, invite external users to your project, 
and more. 

This article's following sections each detail one or more of the features 
available in LCS: 

- [**What LCS Stores About Your Liferay Servers:**](/discover/portal/-/knowledge_base/6-1/managing-liferay-with-liferay-connected-services#what-lcs-stores-about-your-liferay-servers)
  For LCS to work, the LCS servers must store certain information about your 
  Liferay servers. Sensitive data, however, isn't stored on the LCS servers. 
  Learn exactly what data LCS does and doesn't store. 

- [**Managing Your LCS Projects:**](/discover/portal/-/knowledge_base/6-1/managing-liferay-with-liferay-connected-services#managing-your-lcs-projects)
  Learn how to create and request access to LCS projects, how LCS roles work, 
  and how to manage LCS users. 

- [**Using the Dashboard:**](/discover/portal/-/knowledge_base/6-1/managing-liferay-with-liferay-connected-services#using-the-dashboard)
  Learn how to manage projects, environments, and servers in LCS. This includes 
  applying fix packs, monitoring server status, viewing server metrics, and 
  more. 

- [**Configuring LCS Notifications:**](/discover/portal/-/knowledge_base/6-1/managing-liferay-with-liferay-connected-services#configuring-lcs-notifications)
  Learn how to configure LCS to send you notification emails when specific 
  events occur in your LCS projects.

- [**Using Environment Tokens:**](/discover/portal/-/knowledge_base/6-1/managing-liferay-with-liferay-connected-services#using-environment-tokens)
  Learn how to use environment tokens to automatically register your Liferay 
  servers with LCS. This is crucial in auto-scaling environments. 

First, you'll learn what information LCS stores about your Liferay servers.

### What LCS Stores About Your Liferay Servers [](id=what-lcs-stores-about-your-liferay-servers)

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

### Managing Your LCS Projects [](id=managing-your-lcs-projects)

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

#### Managing LCS Users in Your Project [](id=managing-lcs-users-in-your-project)

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

### Using the Dashboard [](id=using-the-dashboard)

The LCS Dashboard lets you view and manage a project's environments and servers. 
If you're not already at the Dashboard, click it near the upper left-hand corner 
of your LCS site. Clicking *Dashboard* takes you to the project view. From 
there, you can get to the environment view and the server view. Each of these 
views gives you a different look into certain aspects of your LCS project. 
You'll start with the project view. 

#### Using the Project View [](id=using-the-project-view)

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

#### Using the Environment View [](id=using-the-environment-view)

Clicking an environment on the left-hand side of the project view takes you to 
the environment view. The environment view lets you manage an environment in 
your LCS project. The UI is segmented into two parts: Overview, and Automatic 
Registration. You can switch between them by clicking their buttons at the top 
of the UI. When you enter environment view, Overview is selected by default. The 
Overview displays basic information about the environment, along with fix packs 
and alerts. As with the project view, fix packs and alerts are displayed in 
tables. These tables behave exactly the same here as they do in the project 
view. The only difference is that they show fix packs and alerts for the 
environment instead of the whole project. The Environment table displays details 
about the environment, such as its name, description, and location. You can edit 
these fields by clicking the blue pencil icon next to each. You can also delete 
the environment by clicking the Delete button below the Environment table. 
Clicking the Automatic Registration button lets you generate and view 
*environment tokens* that allow automatic configuration of LCS clients. See the 
section below for more information on environment tokens. 

![Figure 4.16: The LCS environment view shows an overview of an LCS environment.](../../images/lcs-environment-view.png)

On the left side of the screen, in either Overview or Automatic Registration, 
environment view displays a list of the environment's servers. Clicking on a 
server takes you to its server view. 

#### Using the Server View [](id=using-the-server-view)

The server view provides detailed information about a server, including 
statistics and performance metrics. You can get to the server view by clicking a
server in the environment view or by clicking a server in the Fix Packs or 
Alerts tables. Server view is segmented into five parts: 

- **Page Analytics:** Displays metrics on page views and load times.
- **Snapshot Metrics:** Displays application, JVM, and server metrics.
- **Overview:** Displays basic server information, available fix packs, and 
  alerts.
- **Details:** Displays general information about your Liferay installation, 
  Java version, and hardware.
- **Portal Properties:** Displays your portal's properties and their settings.

You can switch between these by clicking their buttons across the top of the UI. 
Page Analytics is the first thing you see when you enter server view. Page 
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
*Snapshot Metrics* button near the top of the page. These metrics are broken 
down into three main categories: *Application*, *JVM*, and *Server*. Application 
is selected by default when you click the Snapshot Metrics button. 

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

To view fix packs, alerts, and your server's basic information, click the 
*Overview* button near the top of the page. Although the Alerts table functions 
the same as it does in the other views, the Fix Packs table behaves a bit 
differently. Fix packs are broken down into those that are available for 
installation and those that are already installed. You can access these through 
the Available and Installed tabs at the top of the fix packs table. The Server 
table lists your server's name, description, and location. You can edit these 
values by clicking the pencil icon next to each. If you no longer want the 
server in LCS, click the *Unregister* button to remove it. Note that this 
doesn't alter your actual server; it just removes it from your LCS project. 

![Figure 4.21: The LCS server Overview lets you view and edit the basic information of a server registered with LCS.](../../images/lcs-server-overview.png)

To view general information about your Liferay installation, click the *Details* 
button near the top of the screen. There are three tabs under Details: 
*Software*, *Java*, and *Hardware*. Each shows information, respectively, about 
your Liferay installation, Java installation, and hardware. This information is 
useful to the Liferay support team in the event that you need their assistance. 

![Figure 4.22: Clicking the Details button shows information about your Liferay installation's software and hardware.](../../images/lcs-server-details.png)

LCS also lets you view your portal's property values. To do so, click the 
*Portal Properties* button near the top of the page. Your portal's properties 
and their values are shown in a searchable table. This gives you a convenient 
display for seeing exactly what your portal properties are set to. The 
properties in this table are organized into the following categories:

- **Default Values:** The default values for your portal's properties. 

- **Custom Values:** Any custom values you've set for your portal's properties. 
  This includes any property values you change via a `portal-ext.properties` 
  file.

- **Dynamic Properties:** Any property values set at runtime. For example, the 
  [Liferay Home](/discover/portal/-/knowledge_base/6-1/app-servers#liferay-home) 
  folder's location depends on your configuration. To always specify this folder 
  when setting any properties that require it, you can use `${liferay.home}` 
  instead of an absolute directory path. 

You can display any combination of these categories by selecting the 
corresponding checkboxes at the top of the table. For example, by checking the 
*Show Default Values* and *Show Custom Values* checkboxes, the table shows your 
portal's default and custom property values. To show only the custom values, 
check only the checkbox for *Show Custom Values*. 

![Figure 4.23: With only the *Show Custom Values* checkbox checked, this table shows only the portal property values that differ from default.](../../images/lcs-server-portal-properties.png)

As you can see, the LCS Dashboard is a powerful tool that greatly simplifies 
the update process and also gives you extensive information on how your servers 
are running. Next, you'll learn how to configure notifications in LCS.

### Configuring LCS Notifications [](id=configuring-lcs-notifications)

LCS can be configured to send you notification emails when specific events occur 
in your projects. So how do notifications differ from alerts? Fantastic 
question! LCS notifications are sent via email. Thus, they're an active form of 
notification. Alerts are passive, only appearing in the Alerts table of a 
project, environment, or server. Also, notifications are disabled by default. 

LCS notifications are configured by adding *rules*. The rules define what events 
trigger a notification. To access notification settings, click *Notifications* 
from the user menu in the upper-right corner of LCS. You're presented with the 
*Add Rule* button and a table that lists any existing rules. There are no rules 
by default. Click *Add Rule* to define one. 

![Figure 4.24: You can add rules to determine the events that trigger notifications.](../../images/lcs-add-notification-rule.png)

First specify the project, environment, and server for the notification. Note
that you have the option of selecting all environments and servers in a
project. Then check the checkbox for each event that you want to trigger an 
email notification. For example, if you create a notification rule with *The 
server unexpectedly shuts down* selected for all servers and environments in 
your project, then LCS sends you an email when any server in your project goes 
offline without a normal shut down event. Click *Save* when you're done defining 
the notification rule. It then appears in a table along with any other existing 
rules. Each has an Actions button that lets you edit or delete it. 

Great! Now you know how to set notification rules in LCS. The next section shows 
you how to use environment tokens to automatically register Liferay instances 
with LCS. 

### Using Environment Tokens [](id=using-environment-tokens)

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

![Figure 4.25: Clicking the Automatic Registration button in the environment view shows the token for only that environment.](../../images/lcs-environment-token.png)

By default, there's no existing token. A table appears that contains only a 
*Generate* button. Click it to generate a token for the environment. The new 
token then appears in the table with information on who generated it and when. 
There's also an Actions button next to it for downloading or regenerating the
token. 

You can also access environment tokens from the Connection tab on the left side 
of LCS. Click the *Connection* tab here, and then click the *Automatic 
Registration* button. The table shows the tokens for all the environments in 
your project. This provides a central location to manage all your environment 
tokens. Otherwise, the UI for managing them is exactly the same. 

![Figure 4.26: The Connection tab on the left lets you manage the environment tokens for your entire project.](../../images/lcs-environment-token-02.png)

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

![Figure 4.27: The OAuth authorization entry in the portal instance using an environment token to connect to LCS.](../../images/lcs-token-oauth-entry.png)

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
