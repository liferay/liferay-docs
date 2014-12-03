# Managing Liferay with Liferay Cloud Services [](id=managing-liferay-with-liferay-cloud-services)

Liferay Cloud Services (LCS) is a set of tools and services that lets you manage 
and monitor your Liferay installations. While Liferay's patching tool lets you 
apply fix packs and other updates, the install process is still manual. LCS 
simplifies this process by automatically installing any fix packs that you 
*choose*. That last point is an important one--LCS won't install anything that 
you don't specifically choose for installation. You still have control over what 
gets applied to your Liferay installations. LCS just automates the process by 
enabling one-click downloading and updating. You can also use LCS to monitor the 
performance of your Liferay instances. This includes data on pages, portlets, 
memory usage, JVM performance, and much more. Even better, the features of LCS 
work regardless of whether your Liferay instance is on a single discreet server, 
or distributed across a node. 

Before going any further, you should make sure that your Liferay instances meet 
the requirements for LCS--you must be running Liferay Portal 6.1 GA 3, or 6.2 GA
1 or above. Using LCS to apply fix packs and other updates is an EE only 
feature. The monitoring features of LCS are available to both Liferay CE and EE.

Also, you should take note of a few key terms used throughout this guide: 

- *Project*: Represents a group of users belonging to a company or organization. 
  For example, a project can consist of all the users from a project team or 
  business unit, or it can include the entire company.
- *Environment*: Represents a physical cluster of servers, or a virtual or 
  logical aggregation of servers.
- *Server*: Describes a concrete portal instance. It can be a standalone server 
  or a cluster node.

As you go through this guide, you'll cover the following sections on LCS:

- LCS Account Setup
- Portal Preconfiguration (Liferay 6.1 GA3 only)
- Patching Tool Configuration (EE only)
- LCS Client Configuration
- Using LCS

Now go ahead and get started by setting up your LCS account.

## Setting up Your LCS Account [](id=setting-up-your-lcs-account)

To use LCS, you first need to have an account at [Liferay.com](http://www.liferay.com/). 
You then need to set up an LCS account at [lcs.liferay.com](https://lcs.liferay.com). 
When creating an LCS account, you're taken through the steps of accepting the 
terms of service, setting your password, and setting your password reminder. 
You're then taken to the *Projects* screen where you can join an existing 
project or create a new one.

![Figure 19.1: When creating your LCS account, you can join an existing project or create a new one.](../../images/lcs-projects.png)

The list of existing projects, if present, is the list of projects associated 
with the domain of your email address. This is typically the company your email 
address is associated with. For example, if your email address is 
`joebloggs@janesblogfactory.com`, you're then presented with a list of existing 
projects registered to users with the `@janesblogfactory.com` domain. This lets 
you quickly and easily join the LCS projects that are associated with your 
company. To request access to a project, simply check its checkbox and click 
*Save*. Your request is then sent to the project administrator for approval. 
Note that it's possible for the project administrator to pre-assign you a role 
in a project (more on LCS roles in a moment). In this case, you won't have to 
wait for approval. For more information on user management in LCS, please see 
the *Managing LCS Users in Your Project* section of this guide.

Alternatively, you can create a new project and make yourself its administrator. 
To do so, click the checkbox next to 
*Create a project and become an administrator* in the New Project section of the 
Projects screen. The section then expands to let you name the project and choose 
to go to directly to the project's *Dashboard* (more on the Dashboard later). 
Give your project a name and then click *Save*. 

![Figure 19.2: You can also create a new project from the Projects screen.](../../images/lcs-new-project.png)

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
[how to install the patching tool](http://www.liferay.com/documentation/liferay-portal/6.2/user-guide/-/ai/patching-liferay-liferay-portal-6-2-user-guide-18-en). 
Once you have the patching tool installed, there are a few steps you need to 
complete before LCS can use it. Note that the commands below apply to Linux, 
Unix, and Mac systems. If you're running Windows, simply drop the `.sh` from 
each command that has it.

1. Navigate to the `patching-tool` directory on the command line. It's typically 
   located in the Liferay Home folder.

2. Make sure that you have version 10 or higher of the patching tool. To 
   display the version of your patching tool, run `patching-tool.sh info`.

3. Delete `default.properties` from the `patching-tool` directory.

4. Enable the patching tool by running `patching-tool.sh auto-discovery`.

5. Configure the patching tool by running `patching-tool.sh setup`.

6. Patches downloaded through LCS are installed by the patching tool agent on 
   server startup. For the agent to start with your server, you need to set the 
   `javaagent` property in the JVM options. Make sure that you specify the 
   correct file path to the `patching-tool-agent.jar`. Here's an example of 
   setting the `javaagent` property:

        -javaagent:../../patching-tool/lib/patching-tool-agent.jar

If your patching tool is installed in a location other than the Liferay Home 
folder, you must specify the path of the `patching-tool` folder as a JVM 
argument for the app server. This is done with the `patching.tool.home` 
property. For example:

        -Dpatching.tool.home=/home/bzz/workspace/fixpacks/test-agent/liferay-portal-6.1.20-ee-ga2/patching-tool/

Great! Now you're all set to deploy and configure the LCS client portlet.

## Configuring the LCS Client [](id=configuring-the-lcs-client)

You're now ready to configure the LCS client for use in your portal. If your 
server accesses the web through a proxy, then you need to set a couple of 
properties inside the WAR file of the LCS client portlet.

1. In the WAR file of the LCS client portlet, open the 
   `WEB-INF/classes/portlet-ext.properties` file.
   
2. At the end of the file, add the following properties and set them to the 
   appropriate values for your proxy.
   
        proxy.host.name=
        proxy.host.port=

3. Update LCS client WAR with the modified `portlet-ext.properties` file.
    
4. Deploy the LCS client WAR, or redeploy it if it's already deployed.

Once you deploy the LCS client portlet, you can use it to register your server 
with your LCS account. Access the portlet by clicking on 
*Liferay Cloud Services* under the *Server* section of the *Control Panel*.

![Figure 19.3: LCS appears in your Control Panel as Cloud Services, in the Apps section.](../../images/lcs-post-install-01.png)

After clicking *Authorize Access* on the screen that appears, you are then taken 
to the LCS website. Log in with your credentials and then click *Grant Access*.

![Figure 19.4: The LCS client portlet requires access to your LCS account.](../../images/lcs-auth-access.png)

Next, you need to register your server with LCS by filling out the following 
fields:

1. Cloud Services Project: Choose your project from the menu. 

2. Environment: Select an environment from the menu or create a new one by
   clicking the *Add New Environment* button. An environment should be a logical
   group of servers. A group of development servers or a cluster are good examples
   of environments. If you elect to create a new environment, a popup asks you to
   enter its *Name*, *Location*, and *Description*.

3. Server Name: Give your server a name that you can use to distinguish it in 
   your LCS account.

4. Server Location: This can be a city, location in an office building, or 
   anywhere else that lets you know where the server is physically located.

5. Server Description: Type in any description that you want to give your 
   server. You can use this field to help differentiate the servers you are 
   managing using LCS.

![Figure 19.5: The server registration screen.](../../images/lcs-register-server.png)

Upon clicking *Register*, your LCS client portlet shows your connection status. 
Note that it can take up to 10 or 20 seconds to perform the initial handshake 
with LCS and send the first batch of information to the cloud. If you don't see 
any change in status after 20 seconds, try refreshing the page. If you see a 
red symbol indicating no data is being transmitted, please contact the Liferay 
team for support.

![Figure 19.6: The server is connecting to LCS.](../../images/lcs-server-connecting.png)

Once a successful connection is established, some statistics and links are
displayed. Here's a description of what's displayed:

- Heartbeat Interval: The communication interval with LCS. For example, if this 
  value is `00:01:00`, then the portlet communicates with LCS once every minute.
- Message Task Interval: The message interval with LCS. Messages received from 
  LCS let the portlet know about any available updates. 
- Metrics Task Interval: The interval at which server statistics and metrics are 
  taken.
- Project: Clicking this link takes you to this server's registered project.
- Environment: Clicking this link takes you to this server's registered
  environment.
- Server: Clicking this link takes you to the server on LCS.
- Disconnect: Disconnects this Liferay instance from LCS.
- Reset Credentials: Removes the authorizing account from registration with LCS. 
  It's important to note that your credentials are not human readable in the 
  portal instance. This is because OAuth, which uses tokens, is used for 
  authentication.
 
![Figure 19.7: The server is connected to LCS.](../../images/lcs-server-connected.png)

Great! Now that you've registered your server with your LCS account, you can dig 
in to the features of LCS.

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
get to the heart of LCS--the *Dashboard*.

### Using the Dashboard [](id=using-the-dashboard)

The LCS Dashboard lets you view and manage your project, environments, and 
servers. If you're not already at the Dashboard, click it near the upper 
left-hand corner of your LCS site. Clicking *Dashboard* takes you to the 
project view. From there, you can get to the environment view and the 
server view. Each of these views gives you a different look into certain 
aspects of your LCS project. You'll start with the project view.

#### Using the Project View [](id=using-the-project-view)

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

![Figure 19.8: The LCS project view shows an overview of your LCS project.](../../images/lcs-project-view.png)

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

#### Using the Environment View [](id=using-the-environment-view)

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

![Figure 19.9: The LCS environment view shows an overview of an LCS environment.](../../images/lcs-environment-view.png)

#### Using the Server View [](id=using-the-server-view)

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

![Figure 19.10: The LCS server view shows an overview of a server registered with LCS.](../../images/lcs-server-view.png)

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

![Figure 19.11: The LCS application metrics show portlet performance statistics, like frequency of use and average load time.](../../images/lcs-server-metrics-application-portlets.png)

The JVM category, as its name indicates, shows statistics about the JVM running 
on your server. This includes data on the garbage collector and memory. The 
number of runs, total time, and average time are listed for each garbage 
collector item. The memory metrics are presented in a bar chart that shows the 
usage of the Code Cache, PS Eden Space, PS Old Gen, PS Perm Gen, and PS Survivor 
Space.

![Figure 19.12: The LCS JVM metrics show performance data for memory and the garbage collector.](../../images/lcs-server-metrics-jvm.png)

Server is the third category in the Application category. The Server category
shows additional information about how your server is running. For example, a
horizontal bar graph shows the number of current threads that are running on
your server. Similarly, horizontal bar graphs are used to represent the JDBC
connection pools.

![Figure 19.13: The LCS server metrics show current threads and JDBC connection pools.](../../images/lcs-metrics-server.png)

You can also view the settings for a server by clicking on the *Server Details* 
button, which is to the right of the Metrics button. The first tab under the 
Settings button is *LCS Server Settings*. This lets you view or edit your 
server's name, description, and location. You can also unregister your server 
from LCS. The second tab under the Settings button is *About Server*. This 
provides general information about your Liferay instance and hardware. This 
information is useful to the Liferay support team in the event that you need 
their assistance.

![Figure 19.14: You can view and edit the details of your server registered with LCS.](../../images/lcs-server-details-settings.png)

As you can see, the LCS Dashboard is a powerful tool that greatly simplifies 
the update process and also gives you extensive information on how your servers 
are running. Next you'll take a look at how to manage the users in your LCS 
project.

### Managing LCS Users in Your Project [](id=managing-lcs-users-in-your-project)

The Users section of LCS is where you manage the LCS users that are part of your 
project. It's here that you can grant or revoke LCS roles or invite others that 
aren't yet part of your project. To manage users, first click the *Users* icon 
just below the Dashboard icon on the upper-left of your screen. You're 
presented with a table of the users on your project. To the right of each is the 
Manage button. Clicking *Manage* lets you assign or revoke LCS roles for that 
user.

![Figure 19.15: The Users tab lets you manage the LCS users in your project.](../../images/lcs-users.png)

To invite external users to your project, click on the *Invite* button. The
*Invite User* pop up lets you invite anyone with a valid email address. You can 
also search for Liferay.com users to invite. Once you've chosen who to 
invite, the *Role* selection box lets you preassign LCS roles for when they 
accept your invitation.

![Figure 19.16: You can invite users to your LCS project, and even preassign them roles.](../../images/lcs-invite-users.png)

To view sent invitations, click the *Invitations* tab. A table shows
invitations, listing users' email addresses, as well as who invited them and the
date the invitation was sent. The preassigned LCS role and environment is
also listed. You can also cancel an invitation by clicking the red *Cancel*
button in the *Action* column of the invitation.

![Figure 19.17: The Invitations tab lets administrators view and cancel invitations.](../../images/lcs-invitations.png)

As you've now seen, LCS is a powerful tool that simplifies the management of 
your Liferay servers. You can apply fix packs with just a single click and a 
server restart--a process that even works across a cluster. You also get a one 
stop shop for monitoring the performance of your Liferay servers. Metrics like 
JVM performance, Liferay page and portlet load times, and number of current 
threads give you an inside look at how your server is running. What's more is 
that you can do all this collaboratively by inviting others to your project and 
giving them specific roles in LCS. 

Next, you'll learn about Liferay clustering.
