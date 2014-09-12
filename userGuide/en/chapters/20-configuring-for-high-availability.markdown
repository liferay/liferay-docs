# Configuring Liferay for High Availability [](id=configuring-liferay-for-high-availabili-liferay-portal-6-2-user-guide-20-en)

Liferay Portal is a robust, enterprise-ready portal solution. As such, it is
fully ready to support mission-critical, enterprise applications in an
environment configured for multiple redundancies and 24/7 uptimes. The product,
however, like other products of its kind, doesn't come configured this way out
of the box, so there are some steps that need to be taken to tune it for your
needs. 

This chapter covers these topics in detail. Liferay runs on so many different
Java EE application servers that we can't cover all the differences between
them. For this reason, we'll only discuss Liferay configurations. For example,
we'll look at how to configure Liferay to work in a clustered environment, but
not how to create the cluster in your application server. The documentation for
your particular application server is always a much better place to learn those
kinds of things. 

This chapter explains how to configure Liferay for a number of advanced
scenarios, such as

- Managing Liferay with Liferay Cloud Services

- Clustering 

- Distributed Caching

- Deploying Customized versions of Liferay

- Performance Testing and Tuning

During the discussion, we'll mention a number of other open source products upon
which Liferay relies for much of this functionality. These products all have
their own documentation which should be consulted for a fuller view of what
these products can do. For example, Liferay uses Ehcache for its caching
mechanism. We'll cover how to configure Ehcache to enable caches in Liferay, but
will refer you to that product's documentation for further information about
that product.

Sometimes Liferay supports multiple products which perform the same function.
There are, for example, several single sign-on implementations you can use with
Liferay. We'll leave it up to you to select which product best fits the needs of
your project without recommending one product over another.

With all of that said, let's get started configuring Liferay for the enterprise.

## Managing Liferay with Liferay Cloud Services

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
<!-- Add bullet point on Updating LCS Client, once Igor gives us the info for that section. -->

- LCS Account Setup
- Portal Preconfiguration (Liferay 6.1 GA3 only)
- Patching Tool Configuration (EE only)
- LCS Client Configuration
- Using LCS

Now go ahead and get started by setting up your LCS account.

### Setting up Your LCS Account

<!-- More info needed on account creation -->
If you are the first user on your project, you need to create and sign in to 
your LCS account at [lcs.liferay.com](https://lcs.liferay.com). On your first 
login, click the button *Make me a Project Administrator*. This gives you the 
LCS Administrator role. Note that it's possible that the LCS team already gave 
you the correct permissions as part of the account creation process. In this 
case, you won't see the *Make me a Project Administrator* button, and can 
proceed to the next step. As LCS Administrator, you can assign roles to the rest 
of the users in your project. Each user of LCS needs to have an assigned role. 
The following roles are available: 

- LCS Administrator: All LCS functionality is available to administrators. This 
  is the only role that can manage the roles of other users.
- LCS Environment Manager: All LCS functionality is available in the scope of 
  an environment, with the exception of managing other users.
- LCS Environment Viewer: Has read-only access in the scope of an environment.

Now that your LCS account has been set up and you have an understanding of the 
LCS roles, you can get your portal ready for LCS.

### Preconfiguring Your Portal for LCS

If you're running Liferay 6.2, then you can skip this step. If, however, you are 
running Liferay 6.1 GA3, then there's a small pre-configuration step that you 
need to take before using LCS: you need to update the `spring.configs` property 
in your `portal-ext.properties` file. Add the following configuration to your 
`portal-ext.properties` file:

<!-- From the configuration below, it's not clear what has been added. Can you
describe what exactly you're asking the user to do here? -Rich -->

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

### Configuring the Patching Tool 

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

### Configuring the LCS Client

Once you deploy the LCS client portlet, you can use it to register your server 
with your LCS account. Access the portlet by clicking on *Cloud Services* under 
the *Apps* section of the *Control Panel*.

![Figure 20.1: LCS appears in your Control Panel as Cloud Services, in the Apps section.](../../images/lcs-post-install-01.png)

After clicking *Authorize Access* on the screen that appears, you are then taken 
to the LCS website. Log in with your credentials and then click *Grant Access*.

![Figure 20.2: The LCS client portlet requires access to your LCS account.](../../images/lcs-auth-access.png)

Next, you need to register your server with LCS by filling out the following 
fields:

1. Cloud Services Project: Choose your project from the menu. 

2. Environment: Select an environment from the menu or create a new one by
   clicking the *Add New Environment* button. An environment should be a logical
   group of servers. A group of development servers or a cluster are good examples
   of environments. If you elect to create a new environment, a popup asks you to
   enter its *Name*, *Location*, and *Description*.
   
    ![Figure 20.3: The Add New Environment pop-up.](../../images/lcs-new-environment.png)

3. Server Name: Give your server a name that you can use to distinguish it in 
   your LCS account.

4. Server Location: This can be a city, location in an office building, or 
   anywhere else that lets you know where the server is physically located.

5. Server Description: Type in any description that you want to give your 
   server. You can use this field to help differentiate the servers you are 
   managing using LCS.

![Figure 20.4: The server registration screen.](../../images/lcs-register-server.png)

Upon clicking *Register*, your LCS client portlet shows your connection status. 
Note that it can take up to 10 or 20 seconds to perform the initial handshake 
with LCS and send the first batch of information to the cloud. If you don't see 
any change in status after 20 seconds, try refreshing the page. If you see a 
red symbol indicating no data is being transmitted, please contact the Liferay 
team for support.

![Figure 20.5: The server is connecting to LCS.](../../images/lcs-server-connecting.png)

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
 
![Figure 20.6: The server is connected to LCS.](../../images/lcs-server-connected.png)

Great! Now that you've registered your server with your LCS account, you can dig 
in to the features of LCS.

<!--
### Updating the LCS Client

pending instructions from Igor 
 -->

<!-- Hopefully we'll get these instructions before publication. :-) -Rich -->

### Using LCS

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
metrics, portal and portlet metrics, and cache and server metrics.

<!-- Sensitive data being database URLs, user names, and passwords? What about
Documents & Media repository information? I think we should spell out exactly
what sensitive data is *not* stored in order to give people peace of mind. -Rich
--> 

Now it's time to get to the heart of LCS--the *Dashboard*.

#### Using the Dashboard

The LCS Dashboard lets you view and manage your project, environments, and 
servers. If you're not already at the Dashboard, click it near the upper 
left-hand corner of your LCS site. Clicking *Dashboard* takes you to the 
project view. From there, you can get to the environment view and the 
server view. Each of these views gives you a different look into certain 
aspects of your LCS project. You'll start with the project view.

##### Using the Project View

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
with administrative privileges. This is because the patching tool agent requires
administrative access to install any patches.

<!-- This is unclear. Administrative access to what? In fact, what is
administrative access, as mentioned here? I assume you're talking about
permissions at the operating system level, but beyond that, I'm not sure what
permissions are needed exactly. Most people won't run their servers as root, as
that's a bad practice, so they'll want to know exactly what permissions are
necessary. -Rich -->

But what about using LCS to install fix packs across a cluster? Just follow the 
same procedure! LCS downloads and installs fix packs simultaneously across all 
nodes--you don't have to handle each one individually. 

![Figure 20.7: The LCS project view shows an overview of your LCS project.](../../images/lcs-project-view.png)

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

##### Using the Environment View

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

![Figure 20.8: The LCS environment view shows an overview of an LCS environment.](../../images/lcs-environment-view.png)

##### Using the Server View

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

![Figure 20.9: The LCS server view shows an overview of a server registered with LCS.](../../images/lcs-server-view.png)

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

![Figure 20.10: The LCS application metrics show portlet performance statistics, like frequency of use and average load time.](../../images/lcs-server-metrics-application-portlets.png)

The JVM category, as its name indicates, shows statistics about the JVM running 
on your server. This includes data on the garbage collector and memory. The 
number of runs, total time, and average time are listed for each garbage 
collector item. The memory metrics are presented in a bar chart that shows the 
usage of the Code Cache, PS Eden Space, PS Old Gen, PS Perm Gen, and PS Survivor 
Space.

![Figure 20.11: The LCS JVM metrics show performance data for memory and the garbage collector.](../../images/lcs-server-metrics-jvm.png)

Server is the third category in the Application category. The Server category
shows additional information about how your server is running. For example, a
horizontal bar graph shows the number of current threads that are running on
your server. Similarly, horizontal bar graphs are used to represent the JDBC
connection pools.

![Figure 20.12: The LCS server metrics show current threads and JDBC connection pools.](../../images/lcs-metrics-server.png)

You can also view the settings for a server by clicking on the *Server Details* 
button, which is to the right of the Metrics button. The first tab under the 
Settings button is *LCS Server Settings*. This lets you view or edit your 
server's name, description, and location. You can also unregister your server 
from LCS. The second tab under the Settings button is *About Server*. This 
provides general information about your Liferay instance and hardware. This 
information is useful to the Liferay support team in the event that you need 
their assistance.

![Figure 20.13: You can view and edit the details of your server registered with LCS.](../../images/lcs-server-details-settings.png)

As you can see, the LCS Dashboard is a powerful tool that greatly simplifies 
the update process and also gives you extensive information on how your servers 
are running. Next you'll take a look at how to manage the users in your LCS 
project.

<!-- You had "the LCS Dashboard is a formidable tool...." That struck me as
possibly close to what you wanted, but having a negative connotation, and indeed
the dictionary definition has four definitions: 

1.  causing fear, apprehension, or dread:
a formidable opponent.
2.  of discouraging or awesome strength, size, difficulty, etc.; intimidating:
a formidable problem.
3.  arousing feelings of awe or admiration because of grandeur, strength, etc.
4.  of great strength; forceful; powerful:
formidable opposition to the proposal.

Synonyms
1. dreadful, appalling, threatening, menacing, fearful, frightful, horrible.
Antonyms
1. pleasant.

I'm sure you meant definition three, but I'm guessing many readers will assume
the negative connotations of definitions one and two, as they're the more common
definitions (which the synonyms list makes clear). For that reason, I changed
"formidable" to "powerful." 

Just 'splaining. :-)

-Rich
--> 

#### Managing LCS Users in Your Project

The Users section of LCS is where you manage the LCS users that are part of your 
project. It's here that you can grant or revoke LCS roles or invite others that 
aren't yet part of your project. To manage users, first click the *Users* icon 
just below the Dashboard icon on the upper-left of your screen. You're 
presented with a table of the users on your project. To the right of each is the 
Manage button. Clicking *Manage* lets you assign or revoke LCS roles for that 
user.

![Figure 20.14: The Users tab lets you manage the LCS users in your project.](../../images/lcs-users.png)

To invite external users to your project, click on the *Invite* button. The
*Invite External Users* pop up lets you invite anyone with a valid email address.
You can also search for users of Liferay.com to invite. Once you've chosen who
to invite, the *Role* selection box lets you preassign LCS roles for when they
accept your invitation.

![Figure 20.15: You can invite external users to your LCS project, and even preassign them roles.](../../images/lcs-invite-users.png)

As you've now seen, LCS is a powerful tool that simplifies the management of 
your Liferay servers. You can apply fix packs with just a single click and a 
server restart--a process that even works across a cluster. You also get a one 
stop shop for monitoring the performance of your Liferay servers. Metrics like 
JVM performance, Liferay page and portlet load times, and number of current 
threads give you an inside look at how your server is running. What's more is 
that you can do all this collaboratively by inviting others to your project and 
giving them specific roles in LCS. 

Next, you'll learn about Liferay clustering.

## Liferay Clustering [](id=liferay-clustering-liferay-portal-6-2-user-guide-20-en)

Liferay Portal is designed to serve everything from the smallest to the largest
web sites. Out of the box, it's configured optimally for a single server
environment. If one server isn't sufficient to serve the high traffic needs of
your site, Liferay scales to the size you need. 

![Figure 20.16: Liferay is designed to scale to as large an installation as you need.](../../images/enterprise-configuration.png) 

Liferay works well in clusters of multiple machines (horizontal cluster) or in
clusters of multiple VMs on a single machine (vertical cluster), or any mixture
of the two. Once you have Liferay installed in more than one application server
node, there are several optimizations that need to be made. At a minimum,
Liferay should be configured in the following way for a clustered environment:

- All nodes should be pointing to the same Liferay database or database cluster. 

- Documents and Media repositories should be accessible to all nodes of the
  cluster. 

- Search should be configured for replication or should use a separate search
  server. 

- The cache should be replicating across all nodes of the cluster. 

- Hot deploy folders should be configured for each node if you're not using
  server farms. 

If you haven't configured your application server to use farms for deployment,
the hot deploy folder should be a separate folder for all the nodes, and plugins
will have to be deployed to all of the nodes individually. This can be done via
a script. If you do have farms configured, you can deploy normally to any node's
deploy folder, and your farm configuration should take care of syncing the
deployment to all nodes. 

Many of these configuration changes can be made by adding or modifying
properties in your `portal-ext.properties` file. Remember that this file
overrides the defaults in the `portal.properties` file. The original version of
this file can be found in the Liferay source code or can be extracted from the
`portal-impl.jar` file in your Liferay installation. You can also browse an
online version of Liferay's properties file:
[http://docs.liferay.com/portal/6.2/propertiesdoc/portal.properties.html](http://docs.liferay.com/portal/6.2/propertiesdoc/portal.properties.html).
It's a best practice to copy the relevant section you want to modify from
`portal.properties` into your `portal-ext.properties` file, and then modify the
values there.

---

 ![Tip](../../images/01-tip.png)**Note:** This chapter documents a
 Liferay-specific cluster configuration, without getting into specific
 implementations of third party software, such as Java EE application servers,
 HTTP servers, and load balancers. Please consult your documentation for those
 components of your cluster for specific details of those components. Before
 configuring Liferay in a cluster configuration, make sure your OS is not
 defining the hostname of your box to the local network at 127.0.0.1.

---

We'll discuss each of the points above one by one to present a clear picture of
how to cluster Liferay. 

### All Nodes Should Point to the Same Liferay Database [](id=all-nodes-should-point-to-the-same-life-liferay-portal-6-2-user-guide-20-en)

This is pretty self-explanatory. Each node should be configured with a data
source that points to one Liferay database (or a database cluster) that all the
nodes will share. This ensures all the nodes operate from the same basic data
set. This means, of course, Liferay cannot (and should not) use the embedded
HSQL database that is shipped with the bundles (but you already knew that,
right?). And, of course, it goes without saying that the database server is a
separate physical box from the server which is running Liferay. 

Beyond a database cluster, there are two more advanced options you can use to
optimize your database configuration: a read-writer database configuration, and
sharding. 

#### Read-Writer Database Configuration [](id=read-writer-database-configuration-liferay-portal-6-2-user-guide-20-en)

Liferay allows you to use two different data sources for reading and writing.
This enables you to split your database infrastructure into two sets: one that
is optimized for reading and one that is optimized for writing. Since all major
databases support replication in one form or another, you can then use your
database vendor's replication mechanism to keep the databases in sync in a much
faster manner than if you had a single data source which handled everything.

Enabling a read-writer database is simple. In your `portal-ext.properties` file,
configure two different data sources for Liferay to use, one for reading, and
one for writing:

    jdbc.read.driverClassName=com.mysql.jdbc.Driver
    jdbc.read.url=jdbc:mysql://dbread.com/lportal?useUnicode=true&characterEncoding=UTF-8&useFastDateParsing=false
    jdbc.read.username=**your user name**
    jdbc.read.password=**your password**
    jdbc.write.driverClassName=com.mysql.jdbc.Driver
    jdbc.write.url=jdbc:mysql://dbwrite.com/lportal?useUnicode=true&characterEncoding=UTF-8&useFastDateParsing=false
    jdbc.write.username=**your user name**
    jdbc.write.password=**your password**

Of course, specify the user name and password to your database in the above
configuration.

After this, enable the read-writer database configuration by uncommenting the
Spring configuration file which enables it in your `spring.configs` property
(line to uncomment is in bold):

    spring.configs=\
    META-INF/base-spring.xml,\
    META-INF/hibernate-spring.xml,\
    META-INF/infrastructure-spring.xml,\
    META-INF/management-spring.xml,\
    META-INF/util-spring.xml,\
    META-INF/editor-spring.xml,\
    META-INF/jcr-spring.xml,\
    META-INF/messaging-spring.xml,\
    META-INF/scheduler-spring.xml,\
    META-INF/search-spring.xml,\
    META-INF/counter-spring.xml,\
    META-INF/document-library-spring.xml,\
    META-INF/lock-spring.xml,\
    META-INF/mail-spring.xml,\
    META-INF/portal-spring.xml,\
    META-INF/portlet-container-spring.xml,\
    META-INF/wsrp-spring.xml,\
    META-INF/mirage-spring.xml,\
    **META-INF/dynamic-data-source-spring.xml,\**
    #META-INF/shard-data-source-spring.xml,\
    META-INF/ext-spring.xml

The next time you restart Liferay, it will now use the two data sources you have
defined. Be sure you have correctly set up your two databases for replication
before starting Liferay.

Next, we'll look at database sharding. 

#### Database Sharding [](id=database-sharding-liferay-portal-6-2-user-guide-20-en)

Liferay, starting with version 5.2.3, supports database sharding for different
portal instances. Sharding is a term used to describe an extremely high
scalability configuration for systems with massive amounts of users. In
diagrams, a database is normally pictured as a cylinder. Instead, picture it as
a glass bottle full of data. Now take that bottle and smash it onto a concrete
sidewalk. There will be shards of glass everywhere. If that bottle were a
database, each shard now is a database, with a subset of the data in each shard.

This allows you to split up your database by various types of data that might be
in it. For example, some implementations of sharding a database split up the
users: those with last names beginning with A to D go in one database; E to I go
in another; etc. When users log in, they are directed to the instance of the
application that is connected to the database that corresponds to their last
names. In this manner, processing is split up evenly, and the amount of data the
application needs to sort through is reduced.

By default, Liferay allows you to support sharding through different portal
instances, using the *round robin shard selector*. This is a class which serves
as the default algorithm for sharding in Liferay. Using this algorithm, Liferay
selects from several different portal instances and evenly distributes the data
across them. Alternatively, you can use the manual shard selector. In this case,
you'd need to use the UI provided in the Control Panel to configure your shards. 

Of course, if you wish to have your developers implement your own sharding
algorithm, you can do that. This is a great use of the Ext plugin. You can
select which algorithm is active via the `portal-ext.properties` file:

    shard.selector=com.liferay.portal.dao.shard.RoundRobinShardSelector
    #shard.selector=com.liferay.portal.dao.shard.ManualShardSelector
    #shard.selector=[your implementation here]

Enabling sharding is easy. You'll need to make sure you are using Liferay's data
source implementation instead of your application server's. Set your various
database shards in your `portal-ext.properties` file this way:

    jdbc.default.driverClassName=com.mysql.jdbc.Driver
    jdbc.default.url=jdbc:mysql://localhost/lportal?useUnicode=true&characterEncoding=UTF-8&useFastDateParsing=false
    jdbc.default.username=
    jdbc.default.password=
    jdbc.one.driverClassName=com.mysql.jdbc.Driver
    jdbc.one.url=jdbc:mysql://localhost/lportal1?useUnicode=true&characterEncoding=UTF-8&useFastDateParsing=false
    jdbc.one.username=
    jdbc.one.password=
    jdbc.two.driverClassName=com.mysql.jdbc.Driver
    jdbc.two.url=jdbc:mysql://localhost/lportal2?useUnicode=true&characterEncoding=UTF-8&useFastDateParsing=false
    jdbc.two.username=
    jdbc.two.password=
    shard.available.names=default,one,two

Once you do this, you can set up your DNS so several domain names point to your
Liferay installation (e.g., abc1.com, abc2.com, abc3.com). Next, go to the
Control Panel and click on *Portal Instances* under the Configuration heading.
Create two to three instances bound to the DNS names you have configured.

If you're using the RoundRobinShardSelector class, Liferay automatically enters
data into each instance one by one. If you're using the `ManualShardSelector`
class, you'll have to specify a shard for each instance using the UI.

![Figure 20.17: When creating a shard using the manual shard selector, specify the shard you want to use for that instance.](../../images/enterprise-sharding-portal-instance.png)

The last thing you need to do is modify the `spring.configs` section of your
`portal-ext.properties` file to enable the sharding configuration, which by
default is commented out. To do this, your `spring.configs` should look like
this (modified section is in bold):

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
        #META-INF/dynamic-data-source-spring.xml,\
        *META-INF/shard-data-source-spring.xml,\*
        #META-INF/memcached-spring.xml,\
        #META-INF/monitoring-spring.xml,\
        \
        classpath*:META-INF/ext-spring.xml

That's all there is to it. Your system is now set up for sharding. Now that
you've got your database set up and optimized for a large installation, let's
turn to clustering the Documents and Media Library. 

### Documents and Media Library Clustering [](id=documents-and-media-library-clustering-liferay-portal-6-2-user-guide-20-en)

Beginning with Liferay 6.1, Liferay's Documents and Media Library is capable of
mounting several repositories at a time while presenting a unified interface to
the user. By default, users can use the Liferay repository, which is already
mounted. This repository is built into Liferay Portal and can use one of several
different store implementations as its back-end. In addition to this, many
different kinds of third party repositories can be mounted. If you have a
separate repository you've mounted, all nodes of the cluster will point to this
repository. Your avenue for improving performance at this point is to cluster
your third party repository, using the documentation for the repository you have
chosen. If you don't have a third party repository, you can configure the
Liferay repository to perform well in a clustered configuration. 

The main thing to keep in mind is you need to make sure that every node of the
cluster has the same access to the file store as every other node. For this
reason, you'll need to take a look at your store configuration. 

There are several options available for configuring how Liferay's Documents and
Media library stores files. Each option is a *store* which can be configured
through the `portal-ext.properties` file by setting the `dl.store.impl=`
property. Let's consider the ramifications of the various store options. 

#### Using the File System Store [](id=using-the-file-system-store-liferay-portal-6-2-user-guide-20-en)

This is the default store. It's a simple file storage implementation that uses a
local folder to store files. You can use the file system for your clustered
configuration, but you'd have to make sure the folder to which you point the
store can handle things like concurrent requests and file locking. For this
reason, you need to use a Storage Area Network or a clustered file system.

The file system store was the first store created for Liferay and is heavily
bound to the Liferay database. By default, documents are stored in a
`document_library` subfolder of the `data` folder in a Liferay bundle. Of
course, you can change this path to anything you want by using the
`dl.store.file.system.root.dir=` property. 

This store creates a folder structure based on primary keys in the Liferay
database. If, for example, you upload a presentation with the file name
`workflow.odp` into a folder called *stuff*, the file system store creates a
folder structure which looks like the figure below. 

![Figure 20.18: Liferay's file system store creates a folder structure based on primary keys in Liferay's database.](../../images/enterprise-file-system-store.png)

The actual folder path that is used by Liferay for storing documents is this:

    /companyId/folderId/numericFileEntryName/versionNumber

The first folder name is the company ID to which the site belongs. The second
folder name is the ID of the Documents and Media folder within which the
document resides. The third folder name is the numeric file entry name of the
document itself. Finally, the fourth name is a version number which is used for
storing multiple versions of the document.

---

 ![Tip](../../images/01-tip.png)**Note:** The numeric file entry name of a
 document is distinct from the document ID; be careful not to confuse the two!
 Each has an independent counter. The numeric file entry name of a document is
 used in the folder path for storing the document but the document ID is not.
 The numeric file entry name of document can be found in the `name` column of
 the `DLFileEntry` table in Liferay's database; the document ID can be found in
 the `fileEntryId` column of the same table.

---

As you can see, the File System Store binds your documents very closely to
Liferay, and may not be exactly what you want. But if you've been using the
default settings for a while and need to migrate your documents, Liferay
provides a migration utility in the Control Panel in *Server Administration*
&rarr; *Data Migration*. Using this utility, you can move your documents very
easily from one store implementation to another. 

Speaking of other store implementations, let's look at some others Liferay
provides. 

#### Using the Advanced File System Store [](id=using-the-advanced-file-system-store-liferay-portal-6-2-user-guide-20-en)

Liferay's advanced file system store is similar to the default file system
store. Like that store, it saves files to the local file system--which, of
course, could be a remote file system mount. It uses a slightly different folder
structure to store files, which is pictured below. 

![Figure 20.19: The advanced file system store creates a more nested folder structure than the file system store.](../../images/enterprise-adv-file-system-store.png)

So what makes the advanced file system store *advanced*? Several operating
systems have limitations on the number of files which can be stored in a
particular folder. The advanced file system store overcomes this limitation by
programmatically creating a structure that can expand to millions of files, by
alphabetically nesting the files in folders. This not only allows for more files
to be stored, but also improves performance as there are less files stored per
folder. 

The same rules apply to the advanced file system store as apply to the default
file system store. To cluster this, you'll need to point the store to a network
mounted file system that all the nodes can access, and that networked file
system needs to support concurrent requests and file locking. Otherwise, you may
experience data corruption issues if two users attempt to write to the same file
at the same time from two different nodes. 

You may decide the advanced file system store for whatever reason doesn't serve
your needs. If this is the case, you can of course mount other file systems into
the documents and media library. In addition to this, you can also redefine the
Liferay store to use one of three other supported protocols. We'll look at these
next. 

#### Using the CMIS Store [](id=using-the-cmis-store-liferay-portal-6-2-user-guide-20-en)

Though you can mount as many different CMIS (Content Management Interoperability
Services) repositories as you like in the Documents and Media library, you may
wish also to redefine the Liferay repository to point to a CMIS repository as
well. Why? Users might want to create a folder or upload content to the Liferay
repository. It would be nice if that Liferay repository was connected to a
clustered CMIS repository by the administrator without having to mount it
through the UI. The CMIS store allows you to do just that. 

If you wish to use the CMIS store, all you need to do is set the following four
directives in your `portal-ext.properties` file: 

    dl.store.cmis.credentials.username=yourusername
    dl.store.cmis.credentials.password=yourpassword
    dl.store.cmis.repository.url=http://localhost:8080/url/to/your/cmis/repository
    dl.store.cmis.system.root.dir=Liferay Home

With the configuration above, the Liferay repository is connected to CMIS via
the CMIS store. As long as all nodes are pointing to your CMIS repository,
everything in your Liferay cluster should be fine, as the CMIS protocol prevents
multiple simultaneous file access from causing data corruption. 

From here, we'll move on to the JCR store. 

#### Using the JCR Store [](id=using-the-jcr-store-liferay-portal-6-2-user-guide-20-en)

Liferay Portal supports as a store the Java Content Repository standard. Under
the hood, Liferay uses Jackrabbit, a project from Apache, as its JSR-170
compliant document repository. By default, Jackrabbit is configured to store the
documents on the local file system upon which Liferay is installed, in the
`[Liferay Home]/liferay/jackrabbit` folder. Inside this folder is Jackrabbit's
configuration file, called `repository.xml`. 

Using the default settings, the JCR store is not very different from the file
system stores, except you can use any JCR client to access the files. You can,
however, modify Jackrabbit's configuration so it stores files in a database that
can be accessed by all nodes, and so that it operates as a cluster within
Liferay's cluster. 

To move the default repository location to a shared folder, you do not need to
edit Jackrabbit's configuration file. Instead, find the section in
`portal.properties` labeled **JCR** and copy/paste that section into your
`portal-ext.properties` file. One of the properties, by default, is the
following:

    jcr.jackrabbit.repository.root=${liferay.home}/data/jackrabbit

Change this property to point to a shared folder that all the nodes can see. A
new Jackrabbit configuration file is then generated in that location, and you'll
have to edit that file to modify Jackrabbit's configuration. 

Note that because of file locking issues, this isn't the best way to share
Jackrabbit resources, unless you're using a networked file system that can
handle concurrency and file locking. If you have two people logged in at the
same time uploading content, you could encounter data corruption using this
method, and because of this, we don't recommend it for a production system.
Instead, if you want to use the Java Content Repository in a cluster, you should
redirect Jackrabbit into your database of choice. You can use the Liferay
database or another database for this purpose. This requires editing
Jackrabbit's configuration file.

The default Jackrabbit configuration file has sections commented out for moving
the Jackrabbit configuration into the database. This has been done to make it as
easy as possible to enable this configuration. To move the Jackrabbit
configuration into the database, simply comment out the sections relating to the
file system and comment in the sections relating to the database. These by
default are configured for a MySQL database. If you are using another database,
you will likely need to modify the configuration, as there are changes to the
configuration file that are necessary for specific databases. For example, the
default configuration uses Jackrabbit's `DbFileSystem` class to mimic a file
system in the database. While this works well in MySQL, it doesn't work for all
databases. For example, if you're using an Oracle database, you'll need to
modify this to use `OracleFileSystem`. 

Modify the JDBC database URLs so they point to your database. This, of course,
must be done on all nodes of the cluster. Don't forget to create the database
first, and grant the user ID you are specifying in the configuration file access
to create, modify, and drop tables. After this, be sure to uncomment the
`<Cluster/>` section at the bottom of the file. For further information, it's
best to check out the Jackrabbit documentation. Please see the Jackrabbit
documentation at `http://jackrabbit.apache.org` for further information. 

Once you've configured Jackrabbit to store its repository in a database, the
next time you bring up Liferay, the necessary database tables are created
automatically. Jackrabbit, however, does not create indexes on these tables, and
so over time this can be a performance penalty. To fix this, you'll need to
manually go into your database and index the primary key columns for all the
Jackrabbit tables.

Note that this configuration doesn't perform as well as the advanced file system
store, because you're storing documents in a database instead of on the file
system. But it does have the benefit of clustering well. Next, we'll look at
Amazon's S3 store. 

##### Using Amazon Simple Storage Service [](id=using-amazon-simple-storage-service-liferay-portal-6-2-user-guide-20-en)

Amazon's simple storage service (S3) is a cloud-based storage solution which you
can use with Liferay. All you need is an account, and you can store your
documents to the cloud from all nodes, seamlessly. 

This is easy to set up. When you sign up for the service, Amazon assigns you
some unique keys which link you to your account. In Amazon's interface, you can
create "buckets" of data optimized by region. Once you've created these to your
specifications, all you need to do is declare them in `portal-ext.properties`: 

    dl.store.s3.access.key=
    dl.store.s3.secret.key=
    dl.store.s3.bucket.name=

Once you have these configured, set your store implementation to the `S3Store`: 

    dl.store.impl=com.liferay.portlet.documentlibrary.store.S3Store

Consult the Amazon Simple Storage documentation for additional details on using
Amazon's service. 

We have one more store to go over: the Documentum store. 

##### Using the Documentum Store [](id=using-the-documentum-store-liferay-portal-6-2-user-guide-20-en)


![EE Only Feature](../../images/ee-feature-web.png)

If you have a Liferay Portal EE license, you have access to the Documentum hook
which adds support for Documentum to Liferay's Documents and Media library.
The Documentum hook is included in the Documentum Connector EE app which you can
download and install from Liferay Marketplace. 

This hook doesn't add an option to make the Liferay repository into a Documentum
repository, as the other store implementations do. Instead, it gives you the
ability to mount Documentum repositories via the Documents and Media library UI. 

There's not really a lot to this; it's incredibly easy. Click *Add* &rarr;
*Repository*, and in the form that appears, choose *Documentum* as the
repository type. After that, enter a name for the repository, specify the
location of the Documentum repository and cabinet, and Liferay mounts the
repository for you. That's really all there is to it. If all your nodes are
pointing to a Documentum repository, you can cluster Documentum to achieve
higher performance. 

Now that we've covered the available ways you can configure Documents and Media
for clustering, let's move on to configuring search. 

### Clustering Search [](id=clustering-search-liferay-portal-6-2-user-guide-20-en)

You can configure search for clustering in one of two ways: you can use pluggable
enterprise search (recommended) or you can configure Lucene so indexes replicate
across the individual file systems of the nodes in the cluster. We'll look at
both ways to do this. 

#### Using Pluggable Enterprise Search [](id=using-pluggable-enterprise-search-liferay-portal-6-2-user-guide-20-en)

As an alternative to using Lucene, Liferay supports pluggable search engines.
The first implementation of this uses the open source search engine *Solr*, but
in the future there will be many such plugins for your search engine of choice.
This allows you to use a completely separate product for search, and this
product can be installed on another application server or cluster of servers.
Your search engine then operates completely independently of your Liferay Portal
nodes in a clustered environment, acting as a search service for all the nodes
simultaneously.

This makes it much easier to deal with search indexes. You no longer have to
maintain indexes on every node in your cluster, and you get to offload indexing
activity to a separate server, so your nodes can concentrate their CPU power on
serving pages. Each Liferay node sends requests to the search engine to update
the search index when needed, and these updates are then queued and handled
automatically by the search engine, independently. It's kind of like having an
army of robots ready and willing to do your bidding. 

First, you'll need to configure your Solr server, and then you need to install
Liferay's Solr plugin to redirect searches over to it. 

##### Configuring the Solr Search Server [](id=configuring-the-solr-search-server-liferay-portal-6-2-user-guide-20-en)

Since Solr is a standalone search engine, you'll need to download it and install
it first according to the instructions on the Solr web site
(`http://lucene.apache.org/solr`). Of course, it's best to use a server that is
separate from your Liferay installation, as your Solr server becomes responsible
for all indexing and searching for your entire cluster. You definitely don't
want both Solr and Liferay on the same box. Solr is distributed as a `.war` file
with several `.jar` files which need to be available on your application
server's classpath. Once you have Solr up and running, integrating it with
Liferay is easy, but it requires a restart of your application server.

The first thing you need to define on the Solr box is the location of your
search index. Assuming you're running a Linux server and you've mounted a file
system for the index at `/solr`, create an environment variable that points to
this folder. This environment variable needs to be called `$SOLR_HOME`. So for
our example, we would define:

    $SOLR_HOME=/solr

This environment variable can be defined anywhere you need: in your operating
system's start up sequence, in the environment for the user who is logged in, or
in the start up script for your application server. If you're using Tomcat to
host Solr, modify `setenv.sh` or `setenv.bat` and add the environment variable
there.

Once you've created the environment variable, you then can use it in your
application server's start up configuration as a parameter to your JVM. This is
configured differently per application server, but again, if you're using
Tomcat, edit `catalina.sh` or `catalina.bat` and append the following to the
`$JAVA_OPTS` variable:

    -Dsolr.solr.home=$SOLR_HOME

This takes care of telling Solr where to store its search index. After you have
Solr installed and the configuration finished, shut it down, as there is some
more configuration to do.

##### Installing the Solr Liferay Plugin [](id=installing-the-solr-liferay-plugin-liferay-portal-6-2-user-guide-20-en)

Next, you have a choice. If you have installed Solr on the same system upon
which Liferay is running (not recommended), you can simply go to the Liferay
Marketplace and install the *solr-web* plugin. This, however, defeats much of
the purpose of using Solr, because the goal is to offload search indexing to
another box to free up processing for your installation of Liferay. For this
reason, you really shouldn't run Liferay and your search engine on the same box.
Unfortunately, the configuration in the plugin is set exactly that way,
presumably to allow you to experiment with different search configurations. To
run them separately--as you would in a production environment--, you'll have to
make a change to a configuration file in the plugin before you install it so you
can tell Liferay where to send indexing requests. In this case, go to the
Liferay Marketplace and download the plugin to your system. 

Open or extract the plugin. Inside the plugin, you'll find a file called
`solr-spring.xml` in the `WEB-INF/classes/META-INF` folder. Open this file in a
text editor and you will see the entry which defines where the Solr server can
be found by Liferay:

	<bean class="com.liferay.portal.spring.context.PortletBeanFactoryPostProcessor" />

	<!-- Solr search engine -->

	<bean id="com.liferay.portal.search.solr.server.BasicAuthSolrServer" class="com.liferay.portal.search.solr.server.BasicAuthSolrServer">
		<constructor-arg type="java.lang.String" value="http://localhost:8080/solr" />
	</bean>
	
Modify this value so it points to the server where Solr is running. Then save
the file and put it back into the plugin archive in the same place it was
before.

Next, extract the file `schema.xml` from the plugin. It should be in the
`docroot/WEB-INF/conf` folder. This file tells Solr how to index the data coming
from Liferay, and can be customized for your installation. Copy this file to
`$SOLR_HOME/conf` on your Solr box (you may have to create the `conf`
directory).

Before you start Solr, you should provide Solr with a list of **synonyms** and
**stop words**. Synonyms are words that should be equivalent in search. For
example, if a user searches for *important information*, you may want to
show results for *required* *information* or *critical information*. You
can define these in `synonyms.txt`. Stop words are defined in `stopwords.txt`
and are words that should not be indexed: articles, pronouns, and other words
that have little value in a search. Place these files in your
`$SOLR_HOME/conf` folder. Examples for both of these files are found in the
Solr archive in the `solr-4.1.0/example/solr/collection1/conf` folder.
Additional Solr configuration options, most importantly `solrconfig.xml` and
`elevate.xml`, are in the `$SOLR_HOME/conf` folder. Now you can start Solr.
After Solr has started, hot deploy the `solr-web` plugin to all your nodes. See
the next section for instructions on hot deploying to a cluster.

Once the plugin is hot deployed, your Liferay server's search is automatically
upgraded to use Solr. It's likely, however, that initial searches will come up
with nothing: this is because you need to reindex everything using Solr.

Navigate to the Control Panel. Under the Configuration heading, click on *Server
Administration*. On the Resources tab, click the *Execute* button next to
*Reindex all search indexes*. When you click this button, Liferay begins sending
indexing requests to Solr for execution. Once Solr has indexed all your data,
you'll have a search server running independently of all your Liferay nodes.

Installing the plugin to your nodes has the effect of overriding any calls to
Lucene for searching. All Liferay's search boxes will now use Solr as the search
index. This is ideal for a clustered environment, as it allows all your nodes to
share one search server and one search index, and this search server operates
independently of all your nodes. If, however, you don't have the server hardware
upon which to install a separate search server, you can sync the search indexes
between all your nodes, as is described next. 

#### Clustering Lucene Indexes on All Nodes [](id=clustering-lucene-indexes-on-all-nodes-liferay-portal-6-2-user-guide-20-en)

Lucene, the search indexer which Liferay uses, can be configured to sync indexes
across each cluster node. This is the easiest configuration to implement, though
of course, it's not as "clean" a configuration as using pluggable enterprise
search. Sometimes, however, you just don't have another server to use for search
indexing, so you need a way to keep all your nodes in sync. By default, Liferay
provides a method called Cluster Link which can send indexing requests to all
nodes in the cluster to keep them in sync. This configuration doesn't require
any additional hardware, and it performs very well. It may increase network
traffic when an individual server reboots, since a full reindex will be needed.
But this should rarely happen, making it a good tradeoff if you don't have the
extra hardware to implement a Solr search server.

You can enable Cluster Link by setting the following property in your
`portal-ext.properties` file:

    cluster.link.enabled=true
    
To cluster your search indexes, you also need to set the following property:

    lucene.replicate.write=true
    
If you have `cluster.link.enabled=true` but `lucene.replicate.write=false`,
you'll enable cache replication but not index replication.

Of course, `cluster.link.enabled=true` and `lucene.replicate.write=true` need to
be set on all your nodes. That's all you need to do to sync your indexes. Pretty
easy, right? Of course, if you have existing indexes, you'll want to reindex as
described in the previous section once you have Cluster Link enabled on all your
nodes. 

### Distributed Caching [](id=distributed-caching-liferay-portal-6-2-user-guide-20-en)

Enabling Cluster Link automatically activates distributed caching. Distributed
caching enables some RMI (Remote Method Invocation) cache listeners that are
designed to replicate the cache across a cluster. 

Liferay uses [Ehcache](www.ehcache.org), which has robust distributed caching
support. This means that the cache can be distributed across multiple Liferay
nodes running concurrently. Enabling this cache can increase performance
dramatically. For example, suppose that two users are browsing the message
boards. The first user clicks a thread to read it. Liferay must look up that
thread from the database and format it for display in the browser. With a
distributed Ehcache running, this thread is stored in a cache for quick
retrieval, and that cache is then replicated to the other nodes in the cluster.
Suppose then that the second user who is being served by another node in the
cluster wants to read the same forum thread and clicks on it. This time, the
data is retrieved more quickly. Because the thread is in the cache, no trip to
the database is necessary. 

This is much more powerful than having a cache running separately on each node.
The power of *distributed* caching allows for common portal destinations to be
cached for multiple users. The first user can post a message to the thread he or
she was reading, and the cache is updated across all the nodes, making the new
post available immediately from the local cache. Without that, the second user
would need to wait until the cache was invalidated on the node he or she
connected to before he or she could see the updated forum post. 

Once you enable distributed caching, of course, you should do some due diligence
and test your system under a load that best simulates the kind of traffic your
system needs to handle. If you'll be serving up a lot of message board messages,
your script should reflect that. If web content is the core of your site, your
script should reflect that too. 

As a result of a load test, you may find that the default distributed cache
settings aren't optimized for your site. In this case, you'll need to tweak the
settings yourself. You can modify the Liferay installation directly or you can
use a plugin to do it. Either way, the settings you change are the same. Next,
we'll discuss a special EE-only optimization that can be made to the cache.
After that, we'll explain how to configure Liferay's caching settings.

#### Enhanced Distributed Cache Algorithm [](id=enhanced-distributed-cache-algorithm-liferay-portal-6-2-user-guide-20-en)

![EE Only Feature](../../images/ee-feature-web.png)

By default, Liferay's distributed cache uses the RMI replication mechanism,
which uses a point to point communication topology. As you can guess, this kind
of structure doesn't scale well for a large cluster with many nodes. Because
each node has to send the same event to other nodes `N - 1` times, network
traffic becomes a bottleneck when `N` is too large. Ehcache also has a
performance issue of its own, in that it creates a replication thread for each
cache entity. In a large system like Liferay Portal, it's very common to have
more than 100 cached entities. This translates to 100+ cache replication
threads. Threads are expensive, because they take resources (memory and CPU
power). Most of the time, these threads are sleeping, because they only need to
work when a cached entity has to talk to remote peers. 

![Figure 20.20: The default algorithm requires each node to create massive amounts of dispatch threads to update the cache for each node in the cluster.](../../images/19-ehcache-inefficient-algorithm.png)

Putting heap memory aside (because the amount of memory on the heap depends on
the application(s) running), consider the stack memory footprint of those 100+
threads. By default on most platforms, the thread stack size is 2 MB; for 100
threads, that's more than 200 MB. If you include the heap memory size, this
number can become as high as 500 MB for just one node. And that massive amount
of threads can also cause frequent context switch overhead, which translates to
increased CPU cycles.

For large installations containing many nodes, Liferay has developed an enhanced
algorithm for handling cache replication that can can fix both the `1` to `N -
1` network communication bottleneck, as well as the massive threads bottleneck.
The default implementation uses JGroups' UDP multicast to communicate. 

![Figure 20.21: Liferay's algorithm uses a single UDP multicast channel, so that
nodes don't have to create a thread for each other node in the cluster.](../../images/19-ehcache-efficient-algorithm.png)

To reduce the number of replication threads, we provide a small pool of
dispatching threads. These deliver cache cluster events to remote peers. Since
all cache entities' cluster events must go through our pool of dispatching
threads to communicate, this gives us a chance to coalesce events: if two
modifications to the same cache object happen at almost the same time, we can
combine the changes into one, and then we only need to notify remote peers once.
This reduces traffic on the network. We should also note that newer versions of
Ehcache support the JGroups replicator and can also fix the `1` to `N - 1`
network communication; however, they cannot fix the massive threads issue and
they cannot coalesce cache events.

For EE customers who are interested in this feature, all you have to do to
enable the enhanced algorithm is to install a plugin from the Liferay
Marketplace and set the following property in the `portal-ext.properties` files
of each of your nodes:

	ehcache.cluster.link.replication.enabled=true

Search Liferay Marketplace for the *Ehcache Cluster EE* plugin, which is free to
all EE customers, and install it on each of your nodes. The new algorithm is
immediately activated and you can reap the benefits right away.

Next, let's discuss how to modify your Ehache settings. As we've seen, it's easy
to use the default Ehcache settings just by enabling Cluster Link. If you need
to tweak the cache for your site, you have two options: you can modify Ehcache
settings with a plugin or you can modify them directly.

#### Modifying the Ehcache Settings With a Plugin [](id=modifying-the-ehcache-settings-with-a-p-liferay-portal-6-2-user-guide-20-en)

A benefit of working with plugins is that you can quickly install a plugin on
each node of your cluster without taking down the cluster. Modifying the Ehcache
settings with a plugin is recommended over modifying Liferay's Ehcache settings
directly. We'll discuss this method first. If you're not a developer, don't
worry--even though you'll create a plugin, you won't have to write any code. 

Since we're assuming you're an administrator and not a developer, we'll take the
easiest route, and use Liferay's graphical development tools, rather than the
command line Plugins SDK by itself. If you're a Liferay EE customer, download
Liferay Developer Studio from the Customer Portal. Set it up with all the
defaults from the first start wizard, and you're good to go (skip the next
paragraph). 

If you're not a Liferay EE customer, download Eclipse and install Liferay IDE
from the Eclipse Marketplace. Download the Plugins SDK for your edition of
Liferay from either the Customer Portal (EE) or the Downloads page on
liferay.com. Connect Liferay IDE to your Plugins SDK using the instructions
found in the *Liferay Developer's Guide*. 

Next, create a hook plugin by selecting *File* &rarr; *New* &rarr; *Liferay
Project*. Select *Hook* as the project type and give your project a name. Click
*Finish* and your project is created. 

In your project, create a text file called `portlet.properties` in the
`docroot/WEB-INF/src` folder. This file can override properties in your portal
just like `portal-ext.properties`. Into this file, add the following three
properties: 

	net.sf.ehcache.configurationResourceName=
	ehcache.single.vm.config.location=
	ehcache.multi.vm.config.location=
	
Liferay's configuration files are, of course, used by default. If you're
overriding these properties, it's because you want to customize the
configuration for your own site. A good way to start with this is to extract
Liferay's configuration files and then customize them. If you're running an
application server (such as Tomcat) that allows you to browse to the running
instance of Liferay, you can extract Liferay's configuration files from Liferay
itself. If you're not, you can extract them from Liferay's `.war` file or
Liferay's source code. In either place, you'll find the files in the
`portal-impl.jar` file, which is in Liferay's `WEB-INF/lib` folder. The files
you want are `hibernate-clustered.xml`, `liferay-single-vm.xml`, and
`liferay-multi-vm-clustered.xml`, and they'll be in the `/ehcache` folder in
this `.jar`. Once you have these, make a subfolder of the `docroot` folder in
your project. Place the files you extracted into this folder and then specify
this folder in the properties above. 

For example, if you created a folder called `custom_cache` in your project's
`docroot` folder, you'd copy the three XML configuration files
(`hibernate-clustered.xml`, `liferay-single-vm.xml`, and
`liferay-multi-vm-clustered.xml`) there. Then you'd edit your
`portlet.properties` and specify your configuration files in the three
properties above: 

	net.sf.ehcache.configurationResourceName=/custom_cache/hibernate-clustered.xml
	ehcache.single.vm.config.location=/custom_cache/liferay-single-vm.xml
	ehcache.multi.vm.config.location=/custom_cache/liferay-multi-vm-clustered.xml
	
Save the file and deploy the plugin (deploying plugins is covered in the
[Liferay Development Guide](https://www.liferay.com/documentation/liferay-portal/6.2/development)),
and the settings you've placed in those files will override the default Liferay
settings. In this way, you can tweak your cache settings so that your cache
performs optimally for the type of traffic generated by your site. The strength
of doing it this way is that you don't have restart your server to change the
cache settings. This is a great benefit, but beware: since Ehcache doesn't allow
for changes to cache settings while the cache is alive, reconfiguring a cache
while the server is running will flush the cache. 

There is, of course, another way to do this if you don't want to create a
plugin. It requires you to restart the server to enable the new cache settings,
but you don't have to work with any developer tools to do it. 

#### Modifying the Ehcache Settings Directly [](id=modifying-the-ehcache-settings-directly-liferay-portal-6-2-user-guide-20-en)

This method is pretty similar to the plugin method, except that you have to
modify the Liferay installation directly. You'll still need to extract Liferay's
configuration files as described in the previous section. Next, shut down your
server and find the location in the server where Liferay is installed (this may
not be possible on all application servers, and if this is the case, you'll need
to use the plugin method described above). For example, suppose you're running
Liferay on Tomcat. Tomcat stores the deployed version of Liferay in `[Tomcat
Home]/webapps/ROOT`. Inside this folder is the folder structure
`WEB-INF/classes`. You can create a new folder in here called `custom_cache` to
store the custom versions of the cache configuration files. Copy the files you
extracted from Liferay into this folder. 

You then need to modify the properties in `portal-ext.properties` that point to
these files. Copy/paste the *Hibernate* section of `portal.properties` into your
`portal-ext.properties` file and then modify the
`net.sf.ehcache.configurationResourceName` property to point to the clustered
version of the configuration file that is now in your custom folder:

    net.sf.ehcache.configurationResourceName=/custom_cache/hibernate-clustered.xml

Now that Liferay is pointing to your custom file, you can modify the settings in
this file to change the cache configuration for Hibernate.

Next, copy/paste the *Ehcache* section from the `portal.properties` file into
your `portal-ext.properties` file. Modify the properties so they point to the
files in your custom folder. For example:

    ehcache.multi.vm.config.location=/custom_cache/liferay-multi-vm-clustered.xml

You can now take a look at the settings in these files and tune them to fit your
environment and application. Let's examine how to do that next. 

#### Customizing Hibernate Cache Settings [](id=customizing-hibernate-cache-settings-liferay-portal-6-2-user-guide-20-en)

By default, Hibernate (Liferay's database persistence layer) is configured to
use Ehcache as its cache provider. This is the recommended setting. If you're
using the default settings using Cluster Link, you already have this enabled.
If, however, you need to customize the settings, you'll have to customize it in
one of the ways described above: either in a plugin or in the deployed instance
of Liferay. The first thing, of course, is to start off with the clustered
version of the file. Copy the `hibernate-clustered.xml` configuration file to
your plugin or to a place in Liferay's classpath (as described above) where you
can refer to it. Then change the following property to point to the file: 

    net.sf.ehcache.configurationResourceName=/path/to/hibernate-clustered.xml

Next, open this file in a text editor. You'll notice that the configuration is
already set up to perform distributed caching through a multi-cast connection.
The configuration, however, might not be set up optimally for your particular
application. Notice that by default, the only object cached in the Hibernate
cache is the User object (`com.liferay.``portal``.model.impl.UserImpl`). This
means that when a user logs in, his or her User object will go in the cache so
that any portal operation that requires access to it (such as permission
checking) can retrieve that object very quickly from the cache.

You may wish to add other objects to the cache. For example, a large part of
your application may be document management using the Documents and Media
portlet. In this case, you may want to cache media objects, such as
`DLFileEntryImpl` to improve performance as users access documents. To do that,
add another block to the configuration file with the class you want to cache:

	<cache
		eternal="false"
		maxElementsInMemory="10000"
		name="com.liferay.portlet.documentlibrary.model.impl.DLFileEntryImpl"
		overflowToDisk="false"
		timeToIdleSeconds="600"
	>
		<cacheEventListenerFactory
			class="com.liferay.portal.cache.ehcache.LiferayCacheEventListenerFactory"
			properties="replicatePuts=false,replicateUpdatesViaCopy=false"
			propertySeparator=","
		/>
		<bootstrapCacheLoaderFactory class="com.liferay.portal.cache.ehcache.LiferayBootstrapCacheLoaderFactory" />
	</cache>

Your site may use the message boards portlet, and those message boards may get a
lot of traffic. To cache the threads on the message boards, configure a block
with the `MBMessageImpl` class:

    <cache
		eternal="false"
		maxElementsInMemory="10000"
		name="com.liferay.portlet.messageboards.model.impl.MBMessageImpl"
		overflowToDisk="false"
		timeToIdleSeconds="600"
	>
		<cacheEventListenerFactory
			class="com.liferay.portal.cache.ehcache.LiferayCacheEventListenerFactory"
			properties="replicatePuts=false,replicateUpdatesViaCopy=false"
			propertySeparator=","
		/>
		<bootstrapCacheLoaderFactory class="com.liferay.portal.cache.ehcache.LiferayBootstrapCacheLoaderFactory" />
	</cache>

Note that if your developers have overridden any of these classes in an Ext
plugin, you'll have to specify the overridden versions rather than the stock
ones that come with Liferay Portal. You can customize the other ehcache
configuration files in exactly the same way. Refer to Ehcache's
[documentation](www.ehcache.org/documentation) for information on how to do
this. 

As you can see, it's easy to add specific data to be cached. Be careful,
however, as too much caching can actually reduce performance if the JVM runs out
of memory and starts garbage collecting too frequently. You'll likely need to
experiment with the memory settings on your JVM as well as the cache settings
above. You can find the specifics about these settings in the documentation for
Ehcache.

#### Configuring Liferay's Caching Settings [](id=configuring-liferays-caching-settings-liferay-portal-6-2-user-guide-20-en)

To understand how Liferay behaves with various cache configurations, let's
consider five different scenarios.

- Scenario 1: The portal administrator does not override the default cache
  configuration files (i.e., does not override
  `ehcache.single.vm.config.location`, `ehcache.multi.vm.config.location`, or
  `net.sf.ehcache.configurationResourceName`) but does set
  `cluster.link.enabled=true` and `ehcache.cluster.link.replication=true`. This
  is the recommended configuration for a Liferay cluster, as long as the Ehcache
  Cluster EE app, available from Liferay Marketplace, has been installed.
  **Important**: The Ehcache Cluster EE app must be installed or cache
  replication will *not* work with this configuration. In this scenario, Liferay
  automatically resets cache peer and cache event listers and replaces them with
  Cluster Link based listeners. This basically activates Cluster Link based
  cache replication. The configured LiferayCacheManagerPeerProviderFactory,
  RMICacheManagerPeerListenerFactory, and LiferayCacheEventListenerFactory
  classes are replaced with Cluster Link based implementations.

- Scenario 2: The portal administrator does not override the default cache
  configuration files, does set `cluster.link.enabled=true`, but does not set
  `ehcache.cluster.link.replication.enabled=true`. In this case, Liferay
  utilizes Ehcache's out-of-the-box replication mechanisms (Multicast for
  discovery and RMI for replication). Lots of replicator threads appear in the
  log with this configuration.

- Scenario 3: The portal administrator does not override the default cache
  configuration files and does not set `cluster.link.enabled=true`. In this case,
  Liferay does not activate any replication and operates with the assumption
  that there's no cluster.

- Scenario 4: The portal administrator overrides the default cache configuration
  files and sets `cluster.link.enabled=true` and
  `ehcache.cluster.link.replication=true`. In this case, Liferay uses Cluster
  Link based replication for any caches configured with the
  LiferayCacheEventListenerFactory classs. If the portal administrator
  configured a different CacheEventListener class for a specific cache (e.g.,
  JGroups), then Liferay uses that listener class. This a basically a hybrid
  mode that was useful prior to Liferay 6.1 before Liferay supported cached
  object replication (and only supported cached event replication). This is
  *not* a recommended configuration for Liferay 6.1 or later.

- Scenario 5: The portal administrator overrides the default cache configuration
  files and sets `cluster.link.enabled=true` but does not set
  `ehcache.cluster.link.replication=true`. In this case, Liferay uses the cache
  configurations specified in the custom cache configuration files. This is the
  recommended configuration when overriding the default cache configuration
  files.

As a general rule, we recommend that portal administrators *not* set custom
cache configuration files but to set `cluster.link.enabled=true` and
`ehcache.cluster.link.replication=true` (i.e., we recommend that portal
administrators set up the configuration in scenario 1). If it's necessary to
tune the cache configurations, it's better to do it via a plugin than to do it
directly. We explained how to do this in the section above called "Modifying the
Ehcache Settings With a Plugin."

Next, we'll show how to share indexes in a database. This is actually not a
recommended configuration, as it's slow (databases are always slower than file
systems), but for completeness, we'll go ahead and tell you how to do it anyway.
But you've been forewarned: it's far better to use one of the other methods of
clustering your search index. 

#### Sharing a Search Index (not recommended unless you have a file locking-aware SAN) [](id=sharing-a-search-index-not-recommended--liferay-portal-6-2-user-guide-20-en)

If you wish to have a shared index (and we really hope you don't), you'll need
to either share the index on the file system or in the database. This requires
changing your Lucene configuration.

The Lucene configuration can be changed by modifying values in your
`portal-ext.properties` file. Open your `portal.properties` file and search for
the text *Lucene*. Copy that section and then paste it into your
`portal-ext.properties` file.

If you wish to store the Lucene search index on a file system that is shared by
all of the Liferay nodes (not recommended: you've been warned), you can modify
the location of the search index by changing the `lucene.dir` property. By
default, this property points to the `lucene` folder inside the Liferay home
folder:

    lucene.dir=${liferay.home}/data/lucene/

Change this to the folder of your choice. You'll need to restart Liferay for the
changes to take effect. You can point all of the nodes to this folder and they
will use the same index.

Like Jackrabbit, however, this is not the best way to share the search index, as
it could result in file corruption if different nodes try reindexing at the same
time. We do not recommend this for a production system. A better way (though
still not great) is to share the index via a database, where the database can
enforce data integrity on the index. This is very easy to do; it is a simple
change to your `portal-ext.properties` file. Of course, we also don't recommend
this for a production system, as accessing the index from a database will be
slower than from a file system. If, however, you have no other option and want
to do this anyway, keep reading. 

There is a single property called `lucene.store.type`. By default this is set to
go to the file system. You can change this so that the index is stored in the
database by making it the following:

    lucene.store.type=jdbc

The next time Liferay is started, new tables are created in the Liferay
database, and the index is stored there. If all the Liferay nodes point to the
same database tables, they will be able to share the index. Again, performance
on this is not very good. Your DBAs may be able to tweak the database indexes a
bit to improve performance. For better performance, you should consider using a
separate search server or syncing the indexes on the nodes' file systems.

---

 ![Tip](../../images/01-tip.png)**Note:** MySQL users need to modify their JDBC
 connection string for this to work. Add the following parameter to your
 connection string:

    emulateLocators=true

---

Alternatively, you can leave the configuration alone, and each node will have
its own index. This ensures against collisions when multiple nodes update the
index. However, the indices will quickly get out of sync since they don't
replicate. For this reason, this is not a recommended configuration either.
Again, for a better configuration, replicate the indexes with Cluster Link or
use a separate search server (see the section on Solr above).

Now we can look at the last consideration when clustering Liferay: hot deploy. 

### Hot Deploy [](id=hot-deploy-liferay-portal-6-2-user-guide-20-en)

Plugins which are hot deployed will need to be deployed separately to all the
Liferay nodes. The best way to do this is to configure your application server
to support *farms*. This is a feature that enables you to deploy an application
on one node and then it replicates automatically to each of the other nodes.
This, of course, is configured differently for each application server, so
you'll need to consult your application server's documentation to learn how to
do this. It's by far the best way to handle hot deploy, and is the recommended
configuration. If you have this working, great! You can skip the rest of this
section completely. 

If for some reason your application server doesn't support this feature or you
can't use it, you'll need to come up with a way to deploy applications across
your cluster. Each node needs to have its own hot deploy folder. This folder
needs to be writable by the user under which Liferay is running, because plugins
are moved from this folder to a temporary folder when they are deployed. This is
to prevent the system from entering an endless loop, because the presence of a
plugin in the folder is what triggers the hot deploy process.

When you want to deploy a plugin to the entire cluster, copy that plugin to the
hot deploy folders of all of the Liferay nodes. Depending on the number of
nodes, it may be best to create a script to do this. Once the plugin has been
deployed to all of the nodes, you can then make use of it (by adding the portlet
to a page or choosing the theme as the look and feel for a page or page
hierarchy).

All of the above will get basic Liferay clustering working; however, the
configuration can be further optimized. We will see how to do this next.

## Performance Tuning [](id=performance-tuning-liferay-portal-6-2-user-guide-20-en)

Once you have your portal up and running, you may find a need to tune it for
performance, especially if your site winds up generating more traffic than you'd
anticipated. There are some definite steps you can take with regard to improving
Liferay's performance.

### Memory [](id=memory-liferay-portal-6-2-user-guide-20-en)

Memory is one of the first things to look at when you want to optimize
performance. If you have any disk swapping, you want to avoid it at all costs:
it has a serious impact on performance. Make sure your server has an optimal
amount of memory and your JVM is tuned to use it.

There are three basic JVM command switches that control the amount of memory in
the Java heap.

    -Xms
    -Xmx
    -XX:MaxPermSize

These three settings control the amount of memory available to the JVM
initially, the maximum amount of memory into which the JVM can grow, and the
separate area of the heap called Permanent Generation space.

The first two settings should be set to the same value. This prevents the JVM
from having to reallocate memory if the application needs more. Setting them to
the same value causes the JVM to be created up front with the maximum amount of
memory you want to give it.

    -Xms1024m -Xmx1024m -XX:MaxPermSize=256m

This is perfectly reasonable for a moderately sized machine or a developer
machine. These settings give the JVM 1024MB for its regular heap size and have a
PermGen space of 256MB. If you're having performance problems, and your profiler
is showing that there's a lot of garbage collection going on, the first thing
you might want to look at is increasing the memory available to the JVM. You'll
be able to tell if memory is a problem by running a profiler (such as Jprobe,
YourKit, or the NetBeans profiler) on the server. If you see Garbage Collection
(GC) running frequently, you definitely want to increase the amount of memory
available to the JVM.

Note that there is a law of diminishing returns on memory, especially with 64
bit systems. These systems allow you to create very large JVMs, but the larger
the JVM, the more time it takes for garbage collection to take place. For this
reason, you probably won't want to create JVMs of more than 2 GB in size. To
take advantage of higher amounts of memory on a single system, run multiple JVMs
of Liferay instead.

Issues with PermGen space can also affect performance. PermGen space contains
long-lived classes, anonymous classes and interned Strings (immutable String
objects that are kept around for a long time to increase String processing
performance). Hibernate--which Liferay uses extensively--has been known to make
use of PermGen space. If you increase the amount of memory available to the JVM,
you may want to increase the amount of PermGen space accordingly.

### Garbage Collection [](id=garbage-collection-liferay-portal-6-2-user-guide-20-en)

As the system runs, various Java objects are created. Some of these objects are
long-lived, and some are not. The ones that are not become *de-referenced*,
which means that the JVM no longer has a link to them because they have ceased
to be useful. These may be variables that were used for methods which have
already returned their values, objects retrieved from the database for a user
that is no longer logged on, or a host of other things. These objects sit in
memory and fill up the heap space until the JVM decides it's time to clean them
up.

Normally, when garbage collection (GC) runs, it stops all processing in the JVM
while it goes through the heap looking for dead objects. Once it finds them, it
frees the memory they were taking up, and then processing can continue. If this
happens in a server environment, it can slow down the processing of requests, as
all processing comes to a halt while GC is happening.

There are some JVM switches that you can enable which can reduce the amount of
time processing is halted while garbage collecting happens. These can improve
the performance of your Liferay installation if applied properly. As always, you
will need to use a profiler to monitor garbage collection during a load test to
tune the numbers properly for your server hardware, operating system, and
application server.

![Figure 20.22: Java uses generational garbage collection. If an object survives enough garbage collection events, it's promoted to a new memory pool. For example, an object could be promoted from the young generation memory pool to the old generation memory pool or from the old generation memory pool to the permanent generation memory pool.](../../images/portal-admin-ch7_html_518957a7.png)

The Java heap is divided into sections for the young generation, the old
generation, and the permanent generation. The young generation is further
divided into three sections: Eden, which is where new objects are created, and
two “survivor spaces, which we can call the *From* and *To* spaces. Garbage
collection occurs in stages. Generally, it's more frequently done in the young
generation, less frequently done in the old generation, and even less frequently
done in the permanent generation, where long-lived objects reside. When garbage
collection runs in the young generation, Eden is swept for objects which are no
longer referenced. Those that are still around are moved to the *To* survivor
space, and the *From* space is then swept. Any other objects in that space which
still have references to them are moved to the *To* space, and the *From* space
is then cleared out altogether. After this, the *From* and the *To* spaces swap
roles, and processing is freed up again until the next time the JVM determines
that garbage collection needs to run.

After a predetermined number of *generations* of garbage collection, surviving
objects may be moved to the old generation. Similarly, after a predetermined
number of *generations* of garbage collection in the old generation, surviving
objects may be moved to the permanent generation.

By default, the JDK uses a serial garbage collector to achieve this. This works
very well for a short-lived desktop Java application, but is not necessarily the
best performer for a long-lived, server-based application like Liferay. For this
reason, you may wish to switch to the Concurrent Mark-Sweep (CMS) collector.

In CMS garbage collection, rather than halting application processing
altogether, this garbage collector makes one short pause in application
execution to mark objects directly reachable from the application code. Then it
allows the application to run while it marks all objects which are reachable
from the set it marked. Finally, it adds another phase called the *remark* phase
which finalizes marking by revisiting any objects modified while the application
was running. It then sweeps through and garbage collects. Though it sounds more
complicated, this has the effect of greatly reducing the amount of time that
execution needs to be halted to clean out dead objects.

Just about every aspect of the way memory management works in Java can be tuned.
In your profiling, you may want to experiment with some of the following
settings to see if any of them can increase your performance.

**NewSize, MaxNewSize:** The initial size and the maximum size of the New or
Young Generation.

**+UseParNewGC:** Causes garbage collection to happen in parallel, using
multiple CPUs. This decreases garbage collection overhead and increases
application throughput.

**+UseConcMarkSweepGC:** Use the Concurrent Mark-Sweep Garbage Collector. This
uses shorter garbage collection pauses, and is good for applications that have a
relatively large set of long-lived data, and that run on machines with two or
more processors, such as web servers.

**+CMSParallelRemarkEnabled:** For the CMS GC, enables the garbage collector to
use multiple threads during the CMS remark phase. This decreases the pauses
during this phase.

**SurvivorRatio:** Controls the size of the two survivor spaces. It's a ratio
between the survivor space size and Eden. The default is 25. There's not much
bang for the buck here, but it may need to be adjusted.

**ParallelGCThreads:** The number of threads to use for parallel garbage
collection. Should be equal to the number of CPU cores in your server.

A sample configuration using the above parameters might look something like
this:

    JAVA_OPTS="$JAVA_OPTS -XX:NewSize=700m -XX:MaxNewSize=700m -Xms2048m
    -Xmx2048m -XX:MaxPermSize=128m -XX:+UseParNewGC -XX:+UseConcMarkSweepGC
    -XX:+CMSParallelRemarkEnabled -XX:SurvivorRatio=20
    -XX:ParallelGCThreads=8"

Again, you should always follow the procedure of adjusting the settings, then
testing under load, then adjusting again. Every system is different and these
are general guidelines to follow. Next, we'll see some modifications we can make
to Liferay's properties to help increase performance. 

### Properties File Changes [](id=properties-file-changes-liferay-portal-6-2-user-guide-20-en)

There are also some changes you can make to your `portal-ext.properties` file
once you are in a production environment.

Set the following to false to disable checking the last modified date on server
side CSS and JavaScript.

    last.modified.check=false

Set this property to true to load the theme's merged CSS files for faster
loading for production. By default it is set to false for easier debugging for
development. You can also disable fast loading by setting the URL parameter
`css_fast_load` to `0`.

    theme.css.fast.load=true

Set this property to true to load the combined JavaScript files from the
property `javascript.files` into one compacted file for faster loading for
production. 

    javascript.fast.load=true
 
These are various things the Liferay engineering team has done to increase
performance generally. If your developers make use of Liferay's tools and
platform, their JavaScript and themes can also take advantage of these
properties. 

Let's look at one final, general way of increasing Liferay's performance:
disabling unused servlet filters. 

### Disabling Unused Servlet Filters [](id=disabling-unused-servlet-filters-liferay-portal-6-2-user-guide-20-en)

Liferay comes by default with a number of servlet filters enabled and running.
It is likely that for your installation, you don't need them all. Since servlet
filters intercept the HTTP request and do some processing on it before Liferay
even has a chance to start building the page, you can increase performance by
disabling the ones you're not using. 

You can disable servlet filters you're not using by using your
`portal-ext.properties` file. Copy the *Servlet Filters* section from the
original `portal.properties` file into your customized file, and then go through
the list and see if there are any you can disable, by setting them to `false`. 

For example, if you are not using CAS for single sign-on, disable the CAS
Filter. If you are not using NTLM for single sign-ons, disable the NTLM Filter.
The fewer servlet filters you are running, the less processing power is needed
for each request.

As you can see, there are many things you can do to increase Liferay's
performance generally. But don't forget to load test your own applications! It
may be that a performance issue comes from a custom-built application that's
doing something it shouldn't do. Always load test your system before putting it
into production: that's the best way of finding out potential performance
problems, and that way, you'll find them during performance testing, and not
when your system is in production.

## Summary [](id=summary-liferay-portal-6-2-user-guide-20-en)

We've seen how good a fit Liferay Portal is for the enterprise. It can be scaled
linearly to grow to whatever size you need to serve your users. Clustering is a
snap, and Liferay harmonizes very well with whatever environment you may have.

Liferay Portal is also built for performance. You can take advantage of
read-writer database configurations, as well as database sharding. You can tune
it to support over 3300 concurrent users on a single server with mean login
times under half a second and maximum throughput of more than 79 logins per
second. We've seen some tips for tuning Liferay Portal, and we have to keep in
mind the adage about tuning: load test and profile, tune, repeat.

In all, Liferay Portal gives you all the options you need to build a
high-performance, robust environment that supports your enterprise.
