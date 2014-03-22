
# Advanced Portal Operation [](id=advanced-portal-operation-liferay-portal-6-2-user-guide-18-en)

In this chapter we discuss several advanced features of Liferay Portal,
including audit trails and portal maintenance, backup, and logging. We also
discuss how to remotely access Liferay's web services. Audit trails allow portal
administrators to track the activities of portal users--this can be very useful
for troubleshooting or figuring out who's responsible for certain actions that
have taken place on your portal. It's generally not much more complicated to
maintain a running Liferay instance than it is to maintain the application
server upon which it's running. However, Liferay provides tools for logging,
patching, and upgrading Liferay that you should know how to use.  It's also
important to follow secure backup procedures to protect your Liferay instance's
source code, database, and properties files.

We'll discuss the following topics in this section:

-   Audit Trails

-   Liferay Monitoring using Google Analytics

-   Backing Up a Liferay Installation

-   Changing Logging Levels

-   Patching Liferay

-   Upgrading Liferay

-   Sandboxing Portlets to Ensure Portal Resiliency

-   Using Web Services for Remote Portlets (WSRP)

-   Remotely Accessing Liferay Services

Let's get started with audit trails.

## Audit Trails [](id=audit-trails-liferay-portal-6-2-user-guide-18-en)

![EE Only Feature](../../images/ee-feature-web.png)

You've just finished lunch and are ready to get back to work. You have a site in
Liferay you use to manage your project, and before you left, you were about to
create a folder in your Documents and Media library for sharing some
requirements documentation. Sitting down at your desk, you navigate to the
repository and attempt to create the folder. 

*You do not have permission to perform this action*, Liferay helpfully tells
you. 

"*What?*" you blurt accidentally in surprise. "This is *my* project!" 

"Ah, you too?" asks a co-worker helpfully from over the cube wall. "I lost
access to a wiki I was updating just a few minutes ago. I was about to enter a
support ticket for it." 

"Forget the ticket. Let's go see the portal admin now," you say. 

And off you go, two floors down, to the far end of the building where, as you
approach, you can already hear stress in the portal admin's voice as he tries to
reassure someone on the phone. 

"Yes, Mr. Jones. Yes, I'll fix it." (*Jones? The president of the company?* goes
through your mind.) "I'll get on it right away, Mr. Jones. It was just a
mistake; I'll fix it. Thank you, Mr. Jones," and he hangs up the phone. 

"Problems?" you ask the portal admin, whose name is Harry. He does look rather
harried. 

"Yeah, Tom," he says. "Somebody changed a bunch of permissions in the portal--it
wasn't me. I'm assuming you and Dick are here because of the same problem?" 

"Yup," you say. "I lost access to a document repository folder." 

"And I lost access to a wiki," Dick says helpfully. 

"It was probably due to some site membership change. Let's take a look at the
audit portlet in the control panel and see what happened." 

When in the course of human events it becomes necessary to see what users are
doing on your portal, you'll find Liferay makes this easy. If you're a Liferay
Enterprise Edition customer, you have access to two plugins--a hook and a
portlet--that, in combination with some settings in `portal-ext.properties`,
enable you to see all the activity that occurs in your portal. Using this, you
can quickly find out what changes were made and by whom. If you've delegated
permission granting to any group of people, this is an essential feature you're
likely to use. 

We'll come back to Tom, Dick and Harry's story later in the chapter. For now,
let's look at how to install Liferay's audit plugins so you can do the same
thing Harry's about to do. 

### Installing and Configuring the Audit Plugins [](id=installing-and-configuring-the-audit-pl-liferay-portal-6-2-user-guide-18-en)

Liferay's audit functionality is composed of two parts: a back-end piece that
hooks into Liferay events and a front-end piece that gives you an interface to
see what's happening. Both of these plugins are included in the Audit EE app
which is available on Liferay Marketplace. Please refer to this guide's chapter
on [Leveraging the Liferay
Marketplace](https://www.liferay.com/documentation/liferay-portal/6.2/user-guide/-/ai/leveraging-the-liferay-marketplace-liferay-portal-6-2-user-guide-14-en)
for information on installing plugins.

Once installed, you can set two properties in your `portal-ext.properties` file
to tweak the default settings. 

**com.liferay.portal.servlet.filters.audit.AuditFilter:** By default, this is
set to `false`, because the audit plugins aren't installed by default. When you
set it to `true`, the audit hook is able to capture more information about
events, such as the client host and the client's IP address. 

**audit.message.com.liferay.portal.model.Layout.VIEW:** In Liferay's code, pages
are referred to as *layouts*. Setting this to `true`, therefore, records audit
events for page views. It's turned off by default because this may be too
fine-grained for most installations. 

Once you've decided if you're going to use one or both of the two settings
above, place them in your `portal-ext.properties` file and restart your Liferay
server. Once it comes up, audit events are captured by Liferay, and you'll be
able to use them to see what's happening in your portal. 

### Using Audit Events [](id=using-audit-events-liferay-portal-6-2-user-guide-18-en)

Now that you're capturing audit events, it's easy to use them to view activities
in your portal. Navigate to the Control Panel and you'll find a new entry in the
Configuration section called *Audit Reports* (see the figure below). 

![Figure 18.1: Once the Audit EE app has been installed, an Audit Reports entry appears in the Control Panel.](../../images/control-panel-audit-reports.png)

Clicking on *Audit Reports* shows you a list of the events Liferay has already
captured (see the figure beblow), along with an interface for searching for
events. You can browse the list but you'll likely need to use the search to find
what you're looking for.

![Figure 18.2: Liferay captures and stores events as soon as the Audit EE app has been installed.](../../images/audit-list-events.png)

The figure above shows that Joe Bloggs logged in and performed some actions on
the site. To view details about any of these events, all you need to do is click
on an entry. You'll then see something like the figure below. 

![Figure 18.3: Clicking an event in the list shows the details of that event. This event shows that Joe Bloggs updated his user account. Specifically, it shows that he updated his `prefixId` from `0` to `11015`. The `prefixId` value represents a prefix for a real name like "Dr.", "Mr.", "Mrs.", or "Ms."](../../images/audit-detail.png)

As you can see, depending on how many users you have in your portal, this list
can get populated very quickly. For this reason, it's a good idea to keep the
`audit.message.com.liferay.portal.model.Layout.VIEW` property set to `false`.
This way, you don't clutter up your audit events with multiple page view events,
which will definitely be the most often triggered event in your portal. 

Now that you know how to browse and view audit events, let's learn how to search
for specific events.

### Viewing Audit Reports [](id=viewing-audit-reports-liferay-portal-6-2-user-guide-18-en)

Finding what you want in a big list of events is, to use the expression, like
searching for a needle in a haystack. This is why the audit portlet provides a
robust searching mechanism. By default, it looks pretty simple: there's only a
single field for searching. Clicking the *gear* icon next to the search bar,
however, reveals an advanced search dialog broken out by various fields you can
use in your search. 

Let's look at the options we have for search. 

**Match:** You can search for matches to *all* the fields you've specified or
*any* single field. 

**User ID:** Specify the user ID you'd like to search for. This would be the
user who performed some action in the portal you'd like to audit. 

**User Name:** Specify the user name you'd like to search for. This is often
easier than searching for a user ID, especially if you don't have access to the
Liferay database to find the user ID. 

**Resource ID:** Specify the ID of the resource that was modified or viewed in
this audit record. 

**Resource Name:** Specify the name of the resource that was modified or viewed
in this audit record. For example, you could search for User resources to see if
someone modified a user's account. 

**Resource Action:** Specify an action that was performed on the resource. This
could be any one of the following: `add`, `assign`, `delete`, `impersonate`,
`login`, `login_failure`, `logout`, `unassign`, or `update`. 

**Session ID:** Specify the session ID to search for. You'd use this if you were
correlating a session ID from your web server logs with activity in Liferay. 

**Client IP:** Specify the IP address of the client that performed the activity
you wish to audit. 

**Client Host:** Specify the host name of the client that performed the activity
you wish to audit. 

**Server Name:** Specify the server name upon which the activity occurred. If
you're using a cluster, each member of the cluster can be individually queried. 

**Server Port:** Specify the server port upon which the activity occurred. You'd
need this if you run a "vertical" cluster of multiple VMs on the same machine. 

**Start Date:** Specify the low end of the date range you wish to search. 

**End Date:** Specify the high end of the date range you wish to search. 

Using this form, if you wanted to check to see if someone in the portal
unassigned a user from a particular role, you might search for a resource name
of *user* and a resource action of *unassign*. The results of such a search
might look something like the figure below. 

<!-- Need to wait for UI of the Audit Portlet EE to be updated for 6.2 before
the screenshot below can be updated. -->

![Figure 18.4: Searching for audit events is easy with the advanced search form provided by the audit portlet. You can specify various search criteria to find the types of events you're looking for.](../../images/audit-unassign-search.png)

Once you have the results of your search, you can click on any of the records
returned to see the detail page for that record. The figure below shows, in this
case, that the default administrative user removed the Power User role from Joe
Bloggs.

![Figure 18.5: If you've delegated portal administration to multiple users, you can use the audit plugins to determine who made what change. And, of course, you'll never leave the default administrative user enabled on a production system, right?](../../images/audit-unassign-detail.png)

As you can see, Liferay's audit portlets give you a lot of power to see what's
happening in your portal. You can use this information to troubleshoot problems,
determine ownership of particular actions, or, as Harry is about to do, find out
who made permission changes they weren't supposed to make. 

### Conclusion of the Story [](id=conclusion-of-the-story-liferay-portal-6-2-user-guide-18-en)

"Okay," says Harry, "let's fire up Liferay's audit system and see if we can
figure out what happened." 

You and Dick stand behind Harry's chair and watch as he enters a query into a
form on the audit portlet. Clicking *search*, the screen fills up with audit
events. 

"Wow, that's a lot of unassign events." Harry says. "And look who the culprit
is," he adds sarcastically. 

"Who's Melvin Dooitrong?" Dick asks. 

"That's my new intern," Harry says. "I'm gonna kill him." Harry pushes out his
chair and walks down the row of cubes to the end, where a kid no more than 20
years old with disheveled hair sits, earbuds in his ears. 

"Hey Melvin," Harry says as Melvin turns around to face him. "Didn't I ask you
to move that set of users from site membership to organization membership?" 

"Yeah," Melvin says, "I did that already." 

"How'd you do it?"

"It was going to take a while to do it manually, so I wrote a script and
executed it in the scripting host," Melvin replies, matter-of-factly. 

"You did, did you? Well, guess what? Your script removed *everybody* from *all*
sites."

"*What?*" 

"Yeah, and now you're going to start adding them back, one by one, manually,
starting with Mr. Jones...." 

Tom and Dick back away slowly from Melvin's cube as Harry and Melvin continue to
have their--let's call it a discussion. One thing is clear: they're having a
better day than Melvin is.

Now that we've seen how you can use audit trails, let's look at some tools and
best practices for maintaining your Liferay installation.

## Liferay Monitoring using Google Analytics [](id=liferay-monitoring-using-google-analyti-liferay-portal-6-2-user-guide-18-en)

Liferay includes built-in support for Google Analytics, allowing administrators
to make use of Google's tool set for analyzing site traffic data. When you sign
up for Google Analytics, a snippet of code is provided which needs to be added
to your web pages to allow Google's system to register the page hit. It can be a
tedious process to add this code to every page on a site, especially if it's a
large site and there is a lot of user-generated content.

This problem can be solved in Liferay by putting Google's code into a custom
theme written specifically for the web site on which the portal is running.
Doing this, however, requires a theme developer to make specific changes to the
theme and it prevents users from using the many themes that are freely available
for Liferay "out of the box."

Because of this, support for Google Analytics has been built into Liferay, and
can be turned on through a simple user interface. This allows Liferay
administrators to make use of Google Analytics on a site by site basis and turn
it on and off when needed. You can sign up for Google Analytics at the Google
Analytics site here:
[http://www.google.com/analytics](http://www.google.com/analytics).

To enable Google Analytics support, navigate to *Site Administration* in the
Control Panel, expand the *Configuration* area in menu at the left side of the
screen, then click on *Site Settings*. Click on *Analytics* and you'll see a
very simple form, pictured below. 

![Figure 18.6: Setting up Google Analytics for your site is very easy: sign up for Google Analytics, receive an ID, and then enter it into the Google Analytics ID field.
](../../images/maintaining-google-analytics.png)

Enter your Google Analytics ID (which should have been provided to you when you
signed up for the service) in the field and click *Save*. All the pages in the
site you selected will now have the Google Analytics code in them and will be
tracked. 

This is a fairly simple procedure, and it gives you the ability to take
advantage of some great tools to help you visualize who's coming to your site
and from where. Next, we discuss some topics germane to maintaining your Liferay
installation as it's used. Let's start with backup.

## Backing up a Liferay Installation [](id=backing-up-a-liferay-installation-liferay-portal-6-2-user-guide-18-en)

Once you have an installation of Liferay Portal running, you'll want to have
proper backup procedures in place in case of a catastrophic hardware failure of
some kind. Liferay isn't very different from any other application that may be
running on your application server. Nevertheless, there are some specific
components you should include in your backup plan.

### Backing up Source Code [](id=backing-up-source-code-liferay-portal-6-2-user-guide-18-en)

If you have extended Liferay or have written any plugins, they should be stored
in a source code repository such as Git, Subversion, or CVS, unless you're Linus
Torvalds, and then tarballs are okay too (that's a joke). Your source code
repository should be backed up on a regular basis to preserve your ongoing work.
This probably goes without saying in your organization, as nobody wants to lose
source code that's taken months to produce, but we thought we should mention it
anyway. 

If you're extending Liferay with an Ext plugin, you'll want to make sure you
also store the version of the Liferay source on which your extension environment
is based. This allows your developers convenient access to all the tools they
need to build your extension and deploy it to a server.

Let's look at the items that need to be backed up in your Liferay installation. 

### Backing up Liferay's File System [](id=backing-up-liferays-file-system-liferay-portal-6-2-user-guide-18-en)

Liferay's configuration file, `portal-ext.properties`, gets stored in the
*Liferay Home* folder, which is generally one folder up from where your
application server is installed (see the [Installation and
Setup](https://www.liferay.com/documentation/liferay-portal/6.2/user-guide/-/ai/installation-and-setup-liferay-portal-6-2-user-guide-15-en)
chapter of this guide for specific details for your application server). At a
minimum, this file should be backed up, but it is generally best to back up your
whole application server.

If you've followed the non-plugin procedure (see chapter 19) to modify your
Ehcache configuration, you'll have cache configuration files in the deploy
location of Liferay. You'll need to back up this location. If you're using the
plugin procedure (i.e., the recommended procedure), your cache configuration
settings are stored in your source code repository, which is backed up
separately. 

Liferay stores configuration files, search indexes, and cache information in a
folder called `/data` in Liferay Home. If you're using the File System store or
the Advanced File System store, the media repository is stored here (by default)
too. You should always back up the contents of your Liferay Home folder.

If you've modified the location where the Document Library stores files, you
should also back up this location.

That covers the file system locations Liferay uses. Next, let's discuss how to
back up Liferay's database.

### Backing up Liferay's Database [](id=backing-up-liferays-database-liferay-portal-6-2-user-guide-18-en)

Liferay's database is the central repository for all of the Portal's information
and is the most important component that needs to be backed up. You can do this
by backing up the database live (if your database allows this) or by exporting
the database and then backing up the exported file. For example, MySQL ships
with a `mysqldump` utility which allows you to export the entire database and
data into a large SQL file. This file can then be backed up. In case of a
database failure, this file can be used to recreate the state of the database at
the time the dump was created.

If you're using Liferay's Documents and Media Library with the Jackrabbit
JSR-170 repository to store documents in a database, the Jackrabbit database
should be backed up also. If you've placed your search index into a database
(not recommended; see chapter 19 for information on using Cluster Link or Solr),
that database should be backed up as well. 

Search indexes can be backed up as well, if you wish to avoid reindexing your
entire portal after you do your restore. This is easiest to do if you have a
separate Solr environment upon which your index is stored. If you're in a
clustered configuration and you're replicating indexes, you'll need to back up
each index replica. 

Restoring your application server, your Liferay Home folder, the locations of
any file system-based media repositories, and your database from a backup system
should give you a functioning portal. Restoring search indexes should avoid the
need to reindex when you bring your site back up after a catastrophic failure.
Good, consistent backup procedures are key to successfully recovering from a
hardware failure. 

But what about maintenance while your server is running? Liferay lets you view a
lot of what is going on through its logging system. 

## Liferay's Logging System [](id=liferays-logging-system-liferay-portal-6-2-user-guide-18-en)

Liferay uses Log4j extensively to implement logging for nearly every class in
the portal. If you need to debug something specific while the system is running,
you can use the control panel to set logging levels by class dynamically.

To view the log levels, go to the control panel, click *Server Administration*
in the Server section, and then click the *Log Levels* tab.

A paginated list of logging categories appears. These categories correspond to
Liferay classes that have log messages in them. By default, all categories are
set to display messages only if there is an error that occurs in the class. This
is why you see ERROR displayed in all of the drop-down list boxes on the right
side of the portlet.

Each category is filtered by its place in the class hierarchy. For example, if
you wanted to see logging for a specific class that is registered in Liferay,
you would browse to that specific class and change its log level to something
that is more descriptive, such as DEBUG. Once you click the *Save* button at the
bottom of the list, you'll start seeing DEBUG messages from that class in your
application server's log file.

If you're not sure which class you want to see log messages for, you can find a
place higher up in the hierarchy and select the package name instead of an
individual class name. If you do this, messages for every class lower in the
hierarchy will be displayed in your application server's log file.

![Figure 18.7: Log levels can be dynamically changed at runtime whenever you need to debug an issue. ](../../images/maintaining-log-levels.png) 

Be careful when you do this. If you set the log level to DEBUG somewhere near
the top of the hierarchy (such as `com.liferay`, for example), you may wind up
with a lot of messages in your log file. This could make it difficult to find
the one you were looking for, and causes the server to do more work writing
messages to the log. 

If you want to set the log level for one of your own classes in a deployed
plugin, you can register that class with Liferay to can control the log levels
more easily, so long as your class uses Log4J to do its logging.

You will first need to implement Log4J logging in your class, with a statement
such as the following (taken from Liferay's `JCRStore` class):

	private static Log _log = LogFactory.getLog(JCRStore.class);

You would then use this `_log` variable to create log messages in your code for
the various logging levels:

	_log.error("Reindexing " + node.getName(), e1);

To enable your logging messages to appear in your server's log file via the
control panel, click the *Add Category* tab on the same *Log Levels* page.

![Figure 18.8: Adding your own logging classes is easy. To add a logging class, just specify it in this field.](../../images/maintaining-add-log-category.png) 

You'll see you can add a logging category. Put in the fully qualified name of
your class or of the package that contains the classes whose log messages you
want to view, choose a log level, then click the *Save* button. You will now
start to see log messages from your own class or classes in the server's log
file.

Logs are great for figuring out issues in production. But what if Liferay
contacts you via its support channel with a bug fix or a security enhancement?
Read on to learn how to patch Liferay. 

## Patching Liferay [](id=patching-liferay-liferay-portal-6-2-user-guide-18-en)

![EE Only Feature](../../images/ee-feature-web.png)

While we strive for perfection with every release of Liferay Portal, the reality
of the human condition dictates that releases of the product may not be as
perfect as originally intended. But we've planned for that. Included with every
Liferay bundle is a patching tool that can handle the installation of two types
of patches: hot fixes and fix packs. 

A hot fix is provided to a customer when a customer contacts Liferay about an
issue, and Liferay's support team--working with the customer--determines that
the problem is indeed an issue with the product that needs to be fixed. Support
fixes the bug and provides a hot fix to the customer immediately. This is a
short-term fix that solves the issue for the customer as quickly as possible. 

On a regular schedule, these hot fixes are bundled together into fix packs. Fix
packs are provided to all of Liferay's customers and are component-based. This
means any issues with the content management system will be bundled together
separately from issues with another component, such as the message boards. This
lets you determine which patches are critical and which are not, based on your
usage. Of course, if Liferay issues a security advisory, that's something you're
always going to want to patch. 

Now that you know what patching is all about, let's check out the tool. 

### Installing the patching tool [](id=installing-the-patching-tool-liferay-portal-6-2-user-guide-18-en)

If you're using a Liferay bundle, congratulations! The patching tool is already
installed. Your job isn't done yet, however, because Liferay *might* have
updated the patching tool. Always check the Customer Portal to see if the
patching tool has been updated first. But even if you forget to check, the
patching tool will tell you if it needs to be updated when you run it. A lot of
planning and forethought has gone into the patching system to make it run as
smoothly as possible.

You follow the same procedure whether you're installing or upgrading the
patching tool. Once you've obtained it from the customer portal, unzip it to the
Liferay Home folder. This is the folder where you've placed your
`portal-ext.properties` file and where by default the `data` folder resides.
This is generally one folder up from where your application server is installed,
but some application servers are different. If you don't know where Liferay Home
is on your system, check chapter 14 to see where this folder is for your
specific application server.

If you're upgrading the patching tool, all you need to do is unzip the new
version on top of the old version. Note that if you're doing this on LUM (Linux,
Unix, Mac) machines, you'll need to make the `patching-tool.sh` script
executable.

After the patching tool is installed, you need to let it auto-discover your
Liferay installation. Then it will determine what your release level is and what
your application server environment is. This is a simple command to run on LUM:

	./patching-tool.sh auto-discovery
	
or on Windows: 

	patching-tool auto-discovery
	
From here on, for brevity we'll use the LUM version of the command. Why? Because
Liferay is open source; there's no open source variant of Windows (ReactOS is
still in alpha, so it doesn't count); and therefore my (RS) unscientific
impression is that more people will run Liferay on open source technology than
not. If I'm wrong, I'm wrong, but there are still many other examples of
documentation that defaults to Windows, so we still get to be different. 

If you've installed the patching tool in a non-standard location, you'll have to
give this command another parameter to point it to your Liferay installation.
For example, if you've installed a Liferay/Tomcat bundle in `/opt/Liferay`,
you'd issue this command: 

	./patching-tool.sh auto-discovery /opt/Liferay/tomcat-7.0.21
	
In all, this is pretty simple. Now let's see how to use the patching tool to get
your patches installed. 

### Installing patches [](id=installing-patches-liferay-portal-6-2-user-guide-18-en)

The absolute first thing you must do when installing one or more patches is to
shut down your server. On Windows operating systems, files that are in use are
locked by the OS, and won't be patched. On LUM systems, you can generally
replace files that are running, but of course that still leaves the old ones
loaded in memory. So your best bet is to shut down the application server that's
running Liferay before you install a patch. 

Liferay distributes patches as `.zip` files, whether they are hot fixes or fix
packs. When you receive one, either via a LESA ticket (hot fix) or through
downloading a fix pack from the customer portal, you'll need to place it in the
`patches` folder, which is inside the patching tool's home folder. Once you've
done that, it's a simple matter to install it. First, execute

    ./patching-tool.sh info
	
This shows you a list of patches you've already installed, along with a list of
patches that *can* be installed, from what's in the `patches` folder. To install
the available patches, issue the following command: 

    ./patching-tool.sh install

Liferay copies files into the plugins in deployment time. If these files are
patched in the portal, they need to be updated in the plugins as well. In these
cases, the patching tool notifies you about the change. You can run the
following command to update these files automatically:

    ./patching-tool.sh update-plugins

If you do not wish to have the patching tool update the plugins, it's enough to
re-deploy them. If there are new indexes created by the patch, the patching tool
notifies you to update them. To get the list, run this command:

    ./patching-tool.sh index-info

As there's no database connection at patching time, the patches needed to be
created at portal startup. In order to get the indexes automatically created,
add the following line to the `portal-ext.properties` file if the server has
permissions to modify the indexes on the database:

    database.indexes.update.on.startup=true

Otherwise, you have to create the indexes manually. Check the output of the
`./patching-tool index-info` command for more details.

Once your patches have been installed, you can verify them by using the
`./patching-tool.sh info` command, which now shows your patch in the list of
installed patches. Next, let's look now at how to manage your patches. 

#### Handling hot fixes and patches [](id=handling-hot-fixes-and-patches-liferay-portal-6-2-user-guide-18-en)

As stated above, hot fixes are short term fixes provided as quickly as possible
and fix packs are larger bundles of hot fixes provided to all customers at
regular intervals. If you already have a hot fix installed, and the fix pack
which contains that hot fix is released, you can rest assured the patching tool
will manage this for you. Fix packs always supersede hot fixes, so when you
install your fix pack, the hot fix that it already contains is uninstalled, and
the fix pack version is installed in its place. 

Sometimes there can be a fix to a fix pack. This is also handled automatically.
If a new version of a fix pack is released, you can use the patching tool to
install it. The patching tool uninstalls the old fix pack and installs the new
version in its place. 

#### Fix pack dependencies [](id=fix-pack-dependencies-liferay-portal-6-2-user-guide-18-en)

Some fix packs require other fix packs to be installed first. If you attempt to
install a fix pack that depends on another fix pack, the patching tool will
notify you of this so you can go to the customer portal and obtain the fix pack
dependency. Once all the necessary fix packs are available in the `patches`
folder, the patching tool will install them. 

The patching tool can also remove patches. 

### Removing or reverting patches [](id=removing-or-reverting-patches-liferay-portal-6-2-user-guide-18-en)

Have you noticed that the patching tool only seems to have an `install` command?
This is because patches are managed not by the command, but by what appears in
the `patches` folder. You manage the patches you have installed by adding or
removing patches from this folder. If you currently have a patch installed and
you don't want it installed, remove it from the `patches` folder. Then run the
`./patching-tool.sh install` command, and the patch is removed. 

If you want to remove all patches you've installed, use the `./patching-tool.sh
revert` command. This removes all patches from your installation.

What we've described so far is the simplest way to use the patching tool, but
you can also use the patching tool in the most complex, multi-VM, clustered
environments. This is done by using profiles. 

### Using profiles with the patching tool [](id=using-profiles-with-the-patching-tool-liferay-portal-6-2-user-guide-18-en)

When you ran the auto-discovery task after installing the patching tool, it
created a default profile that points to the application server it discovered.
This is the easiest way to use the patching tool, and is great for smaller,
single server installations. But we realize many Liferay installations are sized
accordingly to serve millions of pages per day, and the patching tool has been
designed for this as well. So if you're running a small, medium, or large
cluster of Liferay machines, you can use the patching tool to manage all of them
using profiles. 

The auto-discovery task creates a properties file called `default.properties`.
This file contains the detected configuration for your application server. But
you're not limited to only one server which the tool can detect. You can have it
auto-discover other runtimes, or you can manually create new profiles yourself. 

To have the patching tool auto-discover other runtimes, you'll need to use a few
more command line parameters: 

	./patching-tool.sh [name of profile] auto-discovery [path/to/runtime]
	
This will run the same discovery process, but on a path you choose, and the
profile information will go into a `[your profile name].properties` file. 

Alternatively, you can manually create your profiles. Using a text editor,
create a `[profile name].properties` file in the same folder as the patching
tool script. You can place the following properties in the file: 

**patching.mode:** This can be `binary` (the default) or `source`, if you're
patching the source tree you're working with. Liferay patches contain both
binary and source patches. If your development team is extending Liferay, you'll
want to provide the patches you install to your development team so they can
patch their source tree. 

**jdk.version:** Patches are compiled for both JDK 5 and JDK 6. Specify the one
(either `jdk5` or `jdk6`) your application server is running against. 

**patches.folder:** Specify the location where you'll copy your patches. By
default, this is `./patches`. 

**war.path:** No, no one's angry. This is a property for which you specify the
location of the Liferay installation inside your application server.
Alternatively, you can specify a .war file here, and you'll be able to patch a
Liferay .war for installation to your application server. 

**global.lib.path:** Specify the location where .jar files on the global
classpath are stored. If you're not sure, search for your `portal-service.jar`
file; it's on the global classpath. This property is only valid if your
`patching.mode` is `binary`. 

**source.path:** Specify the location of your Liferay source tree. This property
is only valid if your `patching.mode` is `source`. 

You can have as many profiles as you want, and use the same patching tool to
patch all of them. This helps to keep all your installations in sync. 

Now that you know how to patch an existing installation of Liferay, let's turn
to how you'd upgrade Liferay from an older release to the current release. 

## Upgrading Liferay [](id=upgrading-liferay-liferay-portal-6-2-user-guide-18-en)

Liferay upgrades are fairly straightforward. A consistent set of steps is all
you need to follow to upgrade a standard Liferay installation. Things do get
more complicated if your organization has used Ext plugins to customize Liferay.
It's possible that API changes in the new version will break your existing code.
This, however, is usually pretty easy for your developers to fix. Portlet
plugins which use Liferay APIs should be reviewed and their services rebuilt
against the new release. Theme plugins may require some modifications to take
advantage of new features, and if they're using Liferay APIs, they should be
reviewed. Much effort has been made to make upgrades as painless as possible;
however, this is not a guarantee everything will work without modification. Ext
plugins are the most complicating factor in an upgrade, so it is important to
test as much as possible.

Prior to Liferay 6.1 SP2, you could upgrade only from one major release to the
next major release. For example, you could upgrade directly from Liferay 5.2.x
to 6.0.x, but not from 5.1.x to 6.0.x. If you needed to upgrade over several
major releases, you needed to run the upgrade procedure for each major release
until you reached the release you want. This doesn't mean you needed to run the
procedure for every point release or service pack; you only needed to run the
procedure for the major releases. A good practice was to use the latest version
of each major release to upgrade your system. 

Liferay introduced the *seamless upgrade* feature with Liferay 6.1. Seamless
upgrades allow Liferay to be upgraded more easily. In most cases, pointing the
latest version of Liferay to the database of the older version is enough. Of
course, before upgrading, you should test the upgrade in a non-production
environment. You should also always back up your database and other important
information and make all the other appropriate preparations that we'll discuss
in the section.

Now that we've discussed the general philosophy of upgrading, let's outline the
procedure for upgrading to Liferay 6.2.

### Preparing for an Upgrade [](id=preparing-for-an-upgrade-liferay-portal-6-2-user-guide-18-en)

The first thing you need to do is size up your situation. You can do this by
asking yourself a few questions from the chart below. First: What version of
Liferay was the first version you installed? If it was 6.0 or 6.1, there are
fewer steps, because you won't have to worry about migrating your permission
algorithm. If, however, you never upgraded to permissions algorithm 6 or you're
still running a 5.x Liferay, you need to migrate to algorithm 6 before
attempting to upgrade to Liferay 6.2. 

Next, if you're upgrading from a version of Liferay older than 6.1, you'll have
to migrate your image gallery over to Documents and Media. Finally, take note of
all the plugins you have installed. Every plugin must be updated to run on the
current release. This is easy to do with Marketplace: after you bring up
Liferay 6.2, install from Marketplace any of the plugins you had installed
previously. For custom plugins, have your development team update them to run on
the new version of Liferay. 

![Figure 18.9: Use this flowchart to determine the steps to take for your upgrade.](../../images/upgrade-decisions.png)

The flowchart illustrates the procedure described above. Use it to determine
your course of action for the upgrade. Each step is described fully below so
that you can perform your upgrade as efficiently as possible. Be sure to test
the upgrade in a non-production environment before upgrading your production
Liferay instance. Let's look at the preparatory tasks you should perform one by
one. 

### Migrate to Algorithm 6 [](id=migrate-to-algorithm-6-liferay-portal-6-2-user-guide-18-en)

If your Liferay installation has existed for a while, you might be on a
different permission algorithm than the one that's available in Liferay Portal
6.1. Permission algorithms 1-5 were deprecated in Liferay Portal 6.0 and
were removed in 6.1, which means you must migrate *before* you upgrade.

---

 ![Tip](../../images/tip.png) **Important**: Before upgrading a Liferay instance
 that's using one of permissions algorithms 1-5, you *must* migrate to
 permissions algorithm 6 before attempting to upgrade to Liferay 6.2. You can't
 use the seamless upgrade feature to upgrade directly to 6.2 because Liferay's
 permissions migration tool is not included with Liferay 6.2. Follow the
 instructions in this section to migrate to permissions algorithm 6 before
 continuing with your upgrade.

---

If you're on Liferay 5.2 or below, you must upgrade to the latest available
release of Liferay 6.0 first. Please follow the instructions in the [*Liferay
Portal Administrator's Guide*](https://www.liferay.com/documentation/liferay-portal/6.0/administration/-/ai/upgrading-lifer-4)
to do this. We will assume for the rest of this section that you have upgraded
to Liferay 6.0 but that's it's configured to use an older algorithm than
algorithm 6. 

The first thing you need to do, if this is not done already, is to upgrade your
Liferay installation to algorithm 5. If you've already done that, great! You can
skip the rest of this paragraph. If not, shut down your server, edit your
`portal-ext.properties` file, and modify/add the following property so that it
reads like this: 

	permissions.user.check.algorithm=5

Restart your server. As Liferay starts, it upgrades your permissions algorithm
to algorithm 5. Review your system to make sure that your permissions
configuration is working properly (it should be). 

Next, log in as an administrator and navigate to the Control Panel. Go to
*Server Administration* and select *Data Migration* from the menu along the top
of the screen. A section entitled *Legacy Permissions Migration* appears at the
bottom of the page.

![Figure 18.10: Update your permissions algorithm by clicking the *Execute* button.](../../images/17-convert-permissions-algorithm.png)

Algorithms 5 and 6 do not support adding permissions at the user level. If you
have permissions set for individual users, the converter can simulate this for
you. To do this, it auto-generates roles for each individual permission, and
then assigns those roles to the users who have individualized permissions. If
you have a lot of these, you'll likely want to go through and clean them up
after the conversion process. To generate these roles, check the *Generate
Custom Roles* box. If you do not generate the roles, all custom permissions set
for individual users are discarded. 

Click *Execute* to convert all existing users and roles to algorithm 6. When the
process completes, shut down your server. Edit your `portal-ext.properties` file
and modify the algorithm property to show that you're now using algorithm 6: 

	permissions.user.check.algorithm=6

Restart your server. Congratulations! You've successfully migrated your
installation to use the latest, highest performing permissions algorithm. Next,
might need to explicitly set your Image Gallery storage option. 

### Migrate Your Image Gallery Images [](id=migrate-your-image-gallery-images-liferay-portal-6-2-user-guide-18-en)

Liferay 6.1 introduced a major change to how Liferay handles files. Liferay 6.0
and previous versions had a separate Document Library and Image Gallery. In
Liferay 6.1 and 6.2, these are combined into the Documents and Media repository.
If you were using Liferay's Image Gallery to store images, these can be migrated
over during an upgrade, but you'll have to take some extra steps first. 

In Liferay 6.0, you had three ways you could store images in the Image Gallery.
You could use the `DatabaseHook` and store them as BLOBs in the database; you
could use the `DLHook` to store them in the Document Library, or you could use
the `FileSystemHook` to store them in a folder on your server's file system.
Before you upgrade, you'll need to set whichever property you were using in your
6.0 `portal-ext.properties` file, because by default, none of them are enabled
in 6.1. Setting one of the properties triggers the migration during the upgrade
process. Below are the three properties; you'll need to set only *one* of them
(the one you were using). 

    image.hook.impl=com.liferay.portal.image.DatabaseHook
    image.hook.impl=com.liferay.portal.image.DLHook
    image.hook.impl=com.liferay.portal.image.FileSystemHook

By default, Liferay 6.0 used the `FileSystemHook`. If you never customized this
property for your installation, you'd use the `FileSystemHook` property above.
If you customized the property, you should know which one you used, and it is
likely already in your `portal-ext.properties` file. 

The third thing you need to do to prepare for your upgrade is to review the new
property defaults. 

### Review the Liferay 6.2 Properties Defaults [](id=review-the-new-6-1-properties-defaults-liferay-portal-6-2-user-guide-18-en)

The next thing you'll need to look at are the defaults that have changed between
your old Liferay instance's version and Liferay 6.2. These are preserved in a
`portal-legacy-[version].properties` file in Liferay's `/WEB-INF/classes` folder
and in the `portal-impl/src` folder of Liferay's source code. For example, here
are some 6.1 legacy properties:

    hibernate.cache.use_query_cache=true
    hibernate.cache.use_second_level_cache=true
    locale.prepend.friendly.url.style=1
    passwords.encryption.algorithm.legacy=SHA
    mobile.device.styling.wap.enabled=true
    journal.articles.search.with.index=false

The `passwords.encryption.algorithm.legacy` and
`mobile.device.styling.wap.enabled` properties do not exist in 6.1. In 6.2, the
default values of some properties have changed and some new properties have been
added: 

    hibernate.cache.use_query_cache=false
    hibernate.cache.use_second_level_cache=false
    locale.prepend.friendly.url.style=3
    passwords.encryption.algorithm.legacy=
    mobile.device.styling.wap.enabled=false
    journal.articles.search.with.index=true

Please refer to the 6.1 and 6.2 versions of Liferay's `portal.properties` file
for explanations of each of these properties. This file can be found in the your
Liferay instance's `/WEB-INF/lib/portal-impl.jar` file. Online versions can also
be found at
[http://docs.liferay.com/portal/6.1/propertiesdoc/portal.properties.html](http://docs.liferay.com/portal/6.1/propertiesdoc/portal.properties.html)
and
[http://docs.liferay.com/portal/6.2/propertiesdoc/portal.properties.html](http://docs.liferay.com/portal/6.2/propertiesdoc/portal.properties.html).
Please also note the following changes in behavior:

1. By default, Liferay 6.1 used the DES encryption algorithm with a 56 bit key
   size for the company level encryption algorithm.
   
        company.encryption.algorithm=DES
        company.encryption.key.size=56
   
    By default, Liferay 6.2 uses the much stronger AES encryption algorithm with
    a 128 bit key size for the company level encryption algorithm. AES-128 is
    believed to be secure, is fast, and is a standard for symmetric key
    encryption. 
   
        company.encryption.algorithm=AES
        company.encryption.key.size=128
   
    However, the upgrade for the `company.encryption.algorithm` property is only
    performed if the value for this properties was not customized, i.e., if it
    was still set to DES. The upgrade doesn't make any changes if a different
    algorithm was explicitly selected. (Note that this does not affect password
    encryption which a different property handles:
    `passwords.encryption.algorithm`.)

2. By default, Liferay 6.1 used the SHA algorithm for password encryption.

        passwords.encryption.algorithm=SHA

    By default, Liferay 6.2 uses a stronger algorithm,
    PBKDF2WithHmacSHA1/160/128000, for password encryption. PBKDF2
    (Password-Based Key Derivation Function 2) is a key derivation function
    that's part of RSA's PKCS (Public-Key Cryptography Standards) series: PKCS
    \#5, version 2.0. It's also described in the IETF's [RFC
    2898](http://tools.ietf.org/html/rfc2898). The
    `PBKDF2WithHmacSHA1/160/128000` algorithm uses a keyed-hash message
    authentication code using SHA-1 and generates 160-bit hashes using 128,000
    rounds. One round is a single iteration of the key derivation function.
        
        passwords.encryption.algorithm=PBKDF2WithHmacSHA1/160/128000

    Performance is affected by password encryption during sign-in and password
    changes. In 2012, [OWASP](http://www.owasp.org), the Open Web Application
    Security Project, recommended to use 64,000 rounds and to double the number
    of rounds each year. If using PBKDF2 with 128,000 rounds is too expensive
    for the hardware on which you're running Liferay, you can downgrade your
    security algorithm to improve performance by choosing a smaller number. For
    example, you set the following:

        passwords.encryption.algorithm=PBKDF2WithHmacSHA1/160/64000

    If you'd like your upgrade to migrate your password encryption algorithm,
    you need to specify the legacy password encryption algorithm from which
    you're migrating. For example, if you were using the 6.1 default before your
    upgrade, you'd set the following property:

        passwords.encryption.algorithm.legacy=SHA

    Set this property before performing an upgrade so that both existing users'
    and new users' passwords are re-encrypted with the new algorithm.

3. After upgrading from Liferay 6.1 to Liferay 6.2, users must sign back in to
   the portal even if they were using the *Remember Me* feature of the Sign In
   portlet. After the upgrade, the *Remember Me* feature works correctly: users
   can log in to the portal, close their browser, open a new browser window,
   navigate to the portal, and still be logged in.

If you don't like the 6.2 default properties, you can change them back in one
shot by adding a system property to your JVM's startup. This differs by
application servers. In Tomcat, you'd modify `setenv.sh`/`setenv.bat` and append
the option `-Dexternal-properties=portal-legacy-[version].properties` to the
environment variable JAVA_OPTS. The scripts `setenv.sh` or `setenv.bat` are not
delivered with default Tomcat, but do exist in the bundles. If they're there,
Tomcat uses them in the startup process, so it's a nice way to separate your own
settings from Tomcat's default shell scripts. Alternatively, of course, you can
override some or all of them in your `portal-ext.properties` along with your
other overrides.

If you're not using Tomcat, check your application server's documentation to see
how to modify runtime properties. Your final task is to catalog all the plugins
you have installed, so you can install the new versions in your upgraded system. 

### Catalog All Installed Plugins [](id=catalog-all-installed-plugins-liferay-portal-6-2-user-guide-18-en)

Finally, you need to take note of any plugins you have installed. Liferay's
plugins are usually version-specific, so you'll need to obtain new versions of
them for the new release of Liferay. If you have custom plugins created by your
development team, they'll need to build, test, and optionally modify them to
work with the new release of Liferay. Don't attempt an upgrade without
collecting all the plugins you'll need first. 

For Liferay 6.2, the Web Content List portlet is deprecated. During the
deprecation period, the code will still be part of the product, but will be
disabled by default. To enable Web Content List, you'll need to modify the
`liferay-portlet.xml` file by setting the `<include>false</include>` tag to
`true`. However, all the functionality of this portlet is provided by the Asset
Publisher portlet. The Web Content List portlet is expected to be removed in the
next release.

Once you've upgraded your permissions algorithm, reviewed your properties, and
collected all the plugins you'll need, you're ready to follow the upgrade
procedure. Remember to back up your system before you begin. 

### Upgrade Choices: Upgrade a Bundle or Upgrade Manually [](id=upgrade-choices-upgrade-a-bundle-or-upg-liferay-portal-6-2-user-guide-18-en)

There are two different procedures to upgrade Liferay. The first one, upgrading
a Liferay bundle, is the most common. The second procedure is for manually
upgrading a Liferay installation on an application server. We'll discuss both.

In both cases, Liferay auto-detects whether the database requires an upgrade the
first time the new version is started. When Liferay does this, it upgrades the
database to the format required by the new version. To perform this task,
Liferay *must* be accessing the database with a database user account that can
create, drop and modify tables. Make sure you have granted these permissions to
the database user account before you attempt to upgrade Liferay. And, of course,
we'll run the risk of overly repeating ourselves: back up your database.

Let's look at upgrading a bundle, which is the easiest upgrade path. 

#### Upgrading a Bundle [](id=upgrading-a-bundle-liferay-portal-6-2-user-guide-18-en)

If you're running a Liferay bundle, the best way to do the upgrade is to follow
the steps below. The new Liferay is installed in a newer version of your bundle
runtime. For example, the Liferay/Tomcat bundle for 6.0 used Tomcat 6 by
default; the 6.1 bundle uses Tomcat 7. Though there is a Tomcat 6 bundle of
Liferay 6.1, that bundle also uses a newer release of Tomcat than the one from
6.0. This is the case for all runtimes Liferay supports. We generally recommend
you use the latest version of your runtime bundle, as it will be supported the
longest. 

   1. Obtain the new bundle. Unzip the bundle to an appropriate location on your
      system.

   2. Copy your `portal-ext.properties` file and your `data` folder to the new
      bundle. 

   3. Review your `portal-ext.properties` file as described above. Make sure
	  you're using permissions algorithm 6. If you were using the Image Gallery,
      make the necessary modifications so your files are migrated to Documents and
      Media. Review the new defaults and decide whether you want to use them. Review
      any other modifications you've made. 	

   4. Start your application server. Watch the console as Liferay starts: it
      upgrades the database automatically.

   5. When the upgrade completes, install any plugins you were using in your old
      version of Liferay. Make sure you use the versions of those plugins that
      are designed for Liferay 6.1. If you have your own plugins, your
      development team will need to migrate the code in these ahead of time and
      provide .war files for you.  

   6. Browse around in your new installation and verify everything is working.
      Have your QA team test everything. If all looks good, you can delete the
      old application server with the old release of Liferay in it from the
      bundle directory. You have a backup of it anyway, right? 
   
As you can see, upgrading a bundle is generally pretty simple. But not everybody
can use bundles: sometimes, specific application servers or application server
versions are mandated by the environment you're in or by management. For this
reason, Liferay also ships as an installable .war file that can be used on any
supported application server.    

#### Upgrading Manually [](id=upgrading-using-a--war-file-liferay-portal-6-2-user-guide-18-en)

Running a manual upgrade is almost as easy as upgrading a bundle: 

   1. Verify your application server is supported by Liferay. You can do this by
      viewing the appropriate document on the Customer Portal (EE), in chapter
      14 (because there are installation instructions for it), or on liferay.com
      (CE).  If your application server isn't supported by Liferay 6.1, *do not
      continue!* You'll need to upgrade or switch to a supported application
      server first. 

   2. Obtain the Liferay Portal .war file and the dependency .jars archive. 

   3. Copy your customized `portal-ext.properties` file to a safe place and
      review it as described above, making all the appropriate changes.  

   4. Undeploy the old version of Liferay and shut down your application server.

   5. Copy the new versions of Liferay's dependency .jars to a location on your
      server's class path, overwriting the ones you already have for the old
      version of Liferay. This location is documented for your application
      server in chapter 14. 

   6. Deploy the new Liferay .war file to your application server. Follow the
      deployment instructions in chapter 14.

   7. Start (or, if your app server has a console from which you've installed
      the .war, restart) your application server. Watch the console as Liferay
      starts: it should upgrade the database automatically. Verify your portal
      is operating normally, and then install any plugins you were using in your
      old version of Liferay. Make sure you use the versions of those plugins
      designed for Liferay 6.1. If you have your own plugins, your development
      team will need to migrate the code in these ahead of time and provide .war
      files to you. 

   8. Browse around in your new installation and verify everything is working.
      Have your QA team test everything. If all looks good, you're finished. 

That's all there is to it. Most everything is handled by Liferay's upgrade
procedure. Note as stated above, if you have to upgrade over several Liferay
versions, you will need to repeat these steps for each major release. 

### Post-Upgrade Tasks [](id=post-upgrade-tasks-liferay-portal-6-2-user-guide-18-en)

After upgrading to Liferay 6.2, you should reindex your portal's search indexes.
Liferay 6.2 indexes new information in many places, including Documents and
Media, Web Content, and Bookmarks. To reindex all search indexes, navigate to
the *Control Panel* &rarr; *Server Administration* and click on *Reindex all
search indexes*. This invokes each of your portal's indexer classes, ensuring
that your search indexes contain the updated data that 6.2 indexes. 

Do you have some troublesome required portlets running in your portal? Wouldn't
it be great if you could isolate them so they wouldn't affect the overall health
of your portal? We'll show you how to use Liferay's Sandboxing feature to pen up
those pesky portlets, next. 

## Sandboxing Portlets to Ensure Portal Resiliency

![EE Only Feature](../../images/ee-feature-web.png)

The performance, health, and stability of a portal deployment is heavily
dependent upon the portlet modules deployed to it. If one portlet leaks memory
or is extremely slow, your entire portal either crashes due to a dreaded
`OutOfMemoryError` or slows to a crawl. 

Liferay Portal 6.2 introduces a sandboxing feature that enables you to run new
and troublesome portlets in their own container (or "sandbox"), reducing any
adverse impact they may have on the health and stability of your portal. The
feature is available in Liferay's Sandbox App. The app lets you create sandboxes
to run portlets in separate JVMs, freeing your portal's JVM from the resource
consumption of those portlets. We refer to the portal's JVM instance as the
*Master Portal Instance* (*MPI*) and the sandbox JVMs as *Slave Portal
Instances* (*SPIs*). Since SPIs run on the same host as the MPI, communication
between them is very fast. The fact that sandboxed portlets are running in SPIs
is transparent to your users. Portal users continue to use these portlets as
they normally do.

As a portal administrator, you'll be pleased to know that the app not only gives
you the ability to section off plugins into SPIs, but it also gives you the
means to revive a SPI. The Liferay Sandbox App comes with a *SPI Administration*
UI that lets you create, start, stop, and restart SPIs. In addition, it lets you
configure options to automatically restart SPIs, that terminate unexpectedly. 

The sandboxing feature has a couple limitations. First, only portlet and web
plugins can be deployed on a SPI. Second, the portal ignores SPI portlet
implementation classes that are not remote-safe. Implementation classes (such as
asset rederers and pollers) that register with the portal fall into this
category and are ignored by the portal. So, the sooner you test and resolve any
performance issues in such SPI portlets, the sooner you can deploy them back
onto the Master Portal Instance to leverage such implementation classes in those
portlets.  

The Liferay Sandbox App is available on the Liferay
[Marketplace](http://www.liferay.com/marketplace). You can purchase, install,
and deploy the app as described in this guide's chapter on [Leveraging the
Liferay
Marketplace](https://www.liferay.com/documentation/liferay-portal/6.2/user-guide/-/ai/leveraging-the-liferay-marketplace-liferay-portal-6-2-user-guide-14-en). 

Before creating and using sandboxes, we must enable the portal's resiliency
functionality and optimize the database connection settings for your sandboxes.  

### Configuring the Portal for Sandboxing

The two types of portal properties you must modify for your portal to use
sandboxing are the [Portal
Resiliency](http://docs.liferay.com/portal/6.2/propertiesdoc/portal.properties.html#Portal%20Resiliency) 
properties and [Database
Connection](http://docs.liferay.com/portal/6.2/propertiesdoc/portal.properties.html#JDBC) 
properties. You can set these in your [`portal-ext.properties`](https://www.liferay.com/documentation/liferay-portal/6.2/user-guide/-/ai/manual-configuration-liferay-portal-6-2-user-guide-15-en)
file. 

You must enable Portal Resiliency by setting the `portal.resiliency.enabled`
property to `true`. In addition, you can optionally enable the portal to show
special footers in sandboxed portlets. The footers display at the bottom of each
sandboxed portlet, indicating the sandbox that is servicing the request. The
footer helps you verify that a portlet is sandboxed and which sandbox it's in.
To enable both of these resiliency properties, specify the following entries in
your `portal-ext.properties` file:

    portal.resiliency.enabled=true
    portal.resiliency.portlet.show.footer=true

If you hadn't previously configured your database connection pools using your
portal properties, you must do so in order to use the sandboxing feature. If
you've been using JNDI to configure data sources on your app server, please
convert to using Liferay's built-in data source by specifying it via
[JDBC](http://docs.liferay.com/portal/6.2/propertiesdoc/portal.properties.html#JDBC)
properties in your `portal-ext.properties` file. Note, if you attempt to create
or start a sandbox while having incorrect JDBC settings, the Sandbox
Administration console displays a warning. 

After you've configured your portal for sandboxing and restarted it, deploy the
sandbox app. Then, navigate to the Control Panel to see the *SPI Administration*
link displayed in the *Configuration* section.

![Figure 18.11: The Sandboxing App comes with a *SPI Administration* UI that's accessible from the portal's Control Panel.](../../images/sandboxing-spi-admin-available-in-control-panel.png)

Click on the *SPI Administration* link to start creating SPIs for running new or
troublesome portlets. 

### Creating a SPI

You can create and administer SPIs from the *SPI Administration* page accessible
in the *Configuration* section of the Control Panel.

To add a new SPI, simply click on the *Add SPI* button. 

![Figure 18.12: Click on the *Add SPI* button to define a new "sandbox" (SPI).](../../images/sandboxing-add-spi-button.png)

The *Add SPI* panel divides the SPI's fields into *General*, *SPI
Configurations*, and *Advanced Configurations* sections. 

![Figure 18.13: You can name your SPI, describe it, and configure it from the *Add SPI* panel.](../../images/sandboxing-add-spi-general-details.png)

#### General

In the General section, you must provide a unique name for the SPI and describe
the SPI. 

#### SPI Configurations

The SPI Configurations contains some of the most important settings for the SPI.
It is broken into 4 sections: *SPI Runtime*, *SPI Applications*, *Java Runtime*,
and *Recovery Options*. 

![Figure 18.14: From the *SPI Runtime* section of your SPI, you can set its maximum number of working threads based the number of threads available to your portal that you'd like to designate for the SPI. You must also set a unique connecter port for the SPI.](../../images/sandboxing-configure-spi-runtime.png)

Let's set the SPI's runtime options first. 

##### SPI Runtime

**Maximum Worker Threads:** Enter the maximum number of worker threads that the
SPI can use to process requests. By default, it is set to `100`. However, you
should tune this value according to the number of threads allocated in the
application server hosting your portal. This parameter functions similarly to
the settings in most JEE application servers. 

**Connector Port:** Enter the port number on which the SPI listens for requests
from Liferay Portal. Each SPI runs on an embedded Apache Tomcat server instance.
Each of the portal's SPIs must use a unique port and you must ensure that no
other processes are using that port. 

---

 ![Note](../../images/tip.png) **Note**: The SPIs and MPI serialize parameters
 and return values passed between them. The sandboxing feature uses an IPC
 framework called
 [Intraband](http://docs.liferay.com/portal/6.2/propertiesdoc/portal.properties.html#Intraband),
 to support communication between the MPI and SPIs. 

---

Next, we'll pull those troublesome apps into the SPI. 

##### SPI Applications

The *SPI Applications* panel provides a way to select applications to be hosted
in the SPI. All requests that utilize these applications are processed by the
SPI. 

![Figure 18.15: All of the portlets and web plugins that you've installed on your portal are available for moving into a SPI.](../../images/sandboxing-configure-spi-apps.png)

<!-- It's confusing that the wurfl-commercial-web plugin is listed. This is
either a bug or we need to explain that the UI can list "exlucded" portlets and
web plugins, but that they can't actually be moved into the SPI. Jim -->

The panel lists non-core portlets and web plugins that have been installed on
the portal. Since theme, layout template, hook, and Liferay EXT plugins are not
supported in the sandbox, they're excluded from this list. In addition, the
following applications are explicitly excluded: 

- `marketplace-portlet`
- `resource-importer-web`
- `spi-admin-portlet`
- `spi-provider-web`
- `wurfl-commercial-web`
- `wurfl-web`

Next, we'll set the Java runtime resources for the SPI's JVM. 

##### Java Runtime

The **JVM Arguments** panel allows you to specify arguments to be passed to the
Java Virtual Machine (JVM) running the SPI. These include memory settings,
debugger options, etc. If you do not specify any values, the system will
automatically use these values:

    -Xmx512m -XX:PermSize=200m

Consider the JVM's performance tuning recommendations when setting these
arguments. You can also consult the [*Liferay Deployment Checklist*](https://www.liferay.com/marketplace/isvs?p_p_auth=VgLAX0A7&_101_assetEntryId=35608452&p_p_lifecycle=0&p_p_id=101&p_p_state=maximized&_101_struts_action=%2Fasset_publisher%2Fview_content&redirect=https%3A%2F%2Fwww.liferay.com%2Fdocumentation%2Fliferay-portal%2F6.2%2Fuser-guide%3Fp_p_id%3D3%26p_p_lifecycle%3D0%26p_p_state%3Dmaximized%26p_p_mode%3Dview%26_3_entryClassName%3Dcom.liferay.portlet.documentlibrary.model.DLFileEntry%26_3_modifiedselection%3D0%26_3_documentsSearchContainerPrimaryKeys%3D19_PORTLET_35946202%252C19_PORTLET_35722876%252C19_PORTLET_35809176%252C19_PORTLET_35954267%252C19_PORTLET_35824382%252C19_PORTLET_35857084%252C19_PORTLET_35782527%252C19_PORTLET_35810672%252C19_PORTLET_35958625%252C19_PORTLET_35746448%252C19_PORTLET_35972499%252C19_PORTLET_35799656%252C19_PORTLET_35845480%252C19_PORTLET_35773034%252C19_PORTLET_35880698%252C19_PORTLET_35987674%252C19_PORTLET_35866420%252C19_PORTLET_35916979%252C19_PORTLET_35915417%252C19_PORTLET_35815297%26_3_keywords%3DLiferay%2BDeployment%2BChecklist%26_3_format%3D%26_3_modifiedfrom%3D%26_3_formDate%3D1395500651344%26_3_modified%3D%26_3_assetCategoryTitles%3D%26_3_groupId%3D14%26_3_modifiedto%3D%26_3_cur%3D1%26_3_struts_action%3D%252Fsearch%252Fsearch%26_3_assetTagNames%3D&p_p_mode=view&_101_type=document)
for guidance on setting the JVM arguments. 

In development, you may also choose to add debug settings to the JVM arguments. 

![Figure 18.16: You can specify Java runtime options optimal for your SPI's apps.](../../images/sandboxing-configure-spi-java-runtime.png)

In the event that the SPI terminates unexpectedly, you may want to consider
automatically restarting it and having the Sandboxing App send notifications to
your and other stakeholders. 

##### Recovery Options

The Sandboxing App lets you configure email notifications for when a SPI crashes
and lets you configure for the app to automatically restart the SPI. 

![Figure 18.17: You can have the SPI notify you and others if the SPI's apps crash it. And you can configure the maximum number of times to automatically revive the SPI, before requiring manual intervention.](../../images/sandboxing-configure-spi-recovery.png)

**Use Default Notification Options:** Select this checkbox to use the globally
defined notification options. Otherwise the notification options specified in
this panel are used. 

**Notification Recipients:** Enter a set of comma-delimited email addresses of
people to be notified, in the event that the SPI fails. This option is disabled
if the *Use Default Notification Options* checkbox is selected. 

**Use Default Restart Options:** Select this checkbox to use the globally
defined restart options. Otherwise the restart options specified in this panel
are used. 

**Maximum Restart Attempts:** Enter the maximum number of times to attempt
restarting the SPI, in the event that the SPI fails. Once Liferay reaches the
maximum number of restart attempts for the SPI, it refrains from restarting the
SPI. At that point, manual operator intervention is required to restart it.
This option is disabled if the *Use Default Restart Options* checkbox is
selected. 

#### Advanced Configurations

The Advanced Configurations section contains a series of optional parameters
that should rarely be modified. 

##### Advanced SPI Runtime

**Java Executable:** Enter the path to your JVM, specifically the path to your
Java executable file (i.e., the `java` or `java.exe` file). You should generally
not modify this value, assuming your Java executable is in your system's `PATH`. 

**SPI Ping Interval:** Enter the number of milliseconds to wait between pings
that the SPI sends to the portal, to ensure the portal is alive. Pinging the
portal prevents SPIs from becoming "zombie" processes, in the event that the
portal terminates unexpectedly. 

**SPI Register Timeout:** Enter the number of milliseconds allotted for the SPI
to initially start up. The default value is `300000` milliseconds (`300`
seconds). This should be ample time for a SPI to start up. However, if you have
a large number of applications in the sandbox, or if the server has an
insufficient CPU or insufficient memory resources, you may need to increase the
amount of time. 

**SPI Shutdown Timeout:** Enter the maximum amount of time (in milliseconds)
that the SPI should need to gracefully shutdown. As with the *SPI Register
Timeout*, this value may need to be increased on slower or overloaded machines. 

##### SPI Core Applications

You may also choose to allocate certain embedded Liferay functions (e.g., blogs,
bookmarks, etc.) to a SPI. This is generally not recommended, but is made
possible by the Sandboxing App. The *SPI Core Applications* panel lets you drag
apps onto the SPI, in the same manner that the *SPI Applications* panel lets
you. 

Now that you know how to add a SPI and configure it properly, let's learn how to
operate the SPI. 

### Staring, Stopping, and Modifying a SPI

The Portal automatically starts SPIs on startup. However, when you first add a
new SPI, you must start it manually. 

![Figure 18.18: When you first create a SPI, you'll need to start it manually. You can edit and delete SPIs that are not running.](../../images/sandboxing-start-stop-spi.png)

Once successfully started, you can stop or restart a SPI. Note, you can't delete
a SPI that is running. You must first stop the SPI. 

![Figure 18.19: You can restart a SPI in order to activate modifications you've made to it.](../../images/sandboxing-restart-spi.png)

You can edit a SPI's configuration, too. Configuration changes made to a running
SPI take effect after it's restarted. 

![Figure 18.20: SPI configuration modifications only take affect after the SPI has been restarted.](../../images/sandboxing-modify-spi.png)

As you can see, operating SPIs is straightforward and easy to do. 

If you have multiple SPIs, you may want to use global settings to configure the
default options for them. Let's consider how to configure global settings for
your SPIs, next. 

### Configure Global Settings

The SPI Administration console allows you to configure a series of global
settings. You can access them by clicking on the configuration gear icon as
shown in the figure below. 

![Figure 18.21: Click on the global settings gear icon, in the upper right corner of the SPI Administration console, to set default configuration options for all of the portal's SPIs.](../../images/sandboxing-global-settings.png)

Once you've opened the configuration panel, the SPI Administration console
enables you to configure global notification and set restart options for your
SPIs. 

Note, that option values explicitly configured in a SPI take precedence over
the global settings with respect to that SPI.

![Figure 18.22: You can set default notification and restart options for all of the portal's SPIs.](../../images/sandboxing-global-recovery-options.png)

Let's look at the global notification options first. 

#### Notification Options

The notification options allow you to configure both the nofication email
content and specify the recipients of the notification email. These values are
used by all defined SPIs. 

![Figure 18.23: Via the SPI Administration's global configuration panel, you can set specific email notification options, including the sender's address, the sender's name, default recipients, a default email subject template, and a default email body template.](../../images/sandboxing-global-notification-options.png)

**Notification Email From Address:** Enter a default origin email address to use
for notification emails sent from the SPIs. 

**Notification Email From Name:** Enter a default name to use for the sender of
the notification emails. 

**Notification Recipients:** Enter a default comma-delimited list of email
addresses to receive the notification emails. 

**Notification Email Subject:** Enter a subject template for the notification
emails. 

**Notification Email Body:** Enter a body template for the notification emails.

That's simple enough. Let's take a look at the restart options too. 

#### Restart Options

The restart options section allows you to configure how many times each SPI
will be restarted in the event that it terminates unexpectedly. In the example
below, all SPIs will be restarted 3 times before requiring administrator
intervention to restart them. 

![Figure 18.24: You can set default restart options for your SPIs, from the SPI Administration's global configuration panel.](../../images/sandboxing-global-restart-options.png)

Let's recap what Liferay's Sandboxing App does for you. It lets you isolate
portlets and web plugins that are known troublemakers or that you are simply
just unsure about. You put them in their own sandbox JVM (or SPI), so they can
still be used in your portal but are kept out of your portal's JVM. As an
administrator you can group plugins into SPIs and configure each SPI's runtime,
notification, and recovery options. In addition, you can configure global
default settings for your portal's SPIs. With the Sandboxing App, you can ensure
your portal's resiliency while leveraging all the portlets (even leaky ones)
that your users require. 

Liferay Portal can serve portlets that are installed on the system, or it can
serve portlets installed on another portal server. This is called Web Services
for Remote Portlets. Have you ever wondered how to use WSRP in Liferay? We'll
cover this next!

## Using Web Services for Remote Portlets (WSRP) [](id=using-web-services-for-remote-portlets--liferay-portal-6-2-user-guide-18-en)

The Web Services for Remote Portlets (WSRP) specification defines a web service
interface for accessing and interacting with presentation-oriented web services
in the form of portlets. What are presentation-oriented web services? These are
web services that send user interfaces over the wire, rather than raw data like
JSON objects or SOAP data envelopes. If an application is written as a portlet,
this is an easy way to expose that application to end users on a completely
different system, rather than sending just the data and having to craft an
application to present that data. WSRP's presentation-oriented web services 
allow portals to display remote portlets inside their pages, as if locally
deployed, without requiring any additional programming by developers.

Here are the two main components for WSRP:

*Producer:* A web service that exposes one or more portlets and is described
using a Web Services Description Language (WSDL) document.

*Consumer:* A web service client that receives the data from the Producer and
presents it to the user in a portlet window. 

Below, you'll see how the components interact with each other. So without
further ado, let's explore WSRP in Liferay!

### WSRP with Liferay [](id=wsrp-with-liferay-liferay-portal-6-2-user-guide-18-en)

Liferay provides a deployable WSRP portlet that supports the 1.0 and 2.0
specifications. The portlet is available from Liferay Marketplace as a CE or EE
app. Once you've downloaded and installed the WSRP app, you have instant access
to the portlet by navigating to the Control Panel and, under *Apps*, selecting
*WSRP*.

Liferay Portal can be used as a WSRP producer or consumer. As a producer, it
hosts portlets that are consumed by other portal servers (Liferay or
non-Liferay) acting as WSRP consumers. The image below illustrates WSRP
producers and consumers and how they interact.

![Figure 18.25: Portlets can interact with other portlets located on a different portal server using WSRP.](../../images/wsrp-illustration.png)

As we mentioned in the previous chapter, there are two main components of the
WSRP process: producers and consumers. Let's go through the basic process of how
producers and consumers work together to bring the end user a remote portlet.
First, the consumer portal server establishes a connection with its producer
portal server counterpart. This connection is made possible by giving the
consumer the producer portlet's URL. The consumer then uses the URL to
discover the producer's portlet and establish a connection. After the connection
is made, the consumer acquires the producer's information and creates a consumer
proxy portlet. The proxy portlet acts as an intermediary, relaying requests to
and from the end user and the producer portlet.

For example, you can compare the proxy portlet to a TV satellite box. If you
want to change the channel on your TV, you (end user) send the channel number
you desire to the TV's satellite box (consumer's proxy portlet) via your TV's
remote. When the satellite box receives the request to change the channel, it
relays the request to a TV satellite (producer's portlet) which then sends the
channel information back to the satellite box. Then, the satellite box displays
the new channel to you on your TV. In this simple example, you're not directly
requesting the TV satellite to change the channel, but rather, you're
communicating with the satellite box, which acts as an intermediary between you
and the satellite. This example directly relates to using WSRP with Liferay.
Although the end users are sending requests to the consumer portlet, they're not
receiving feedback from the consumer portlet itself, but rather its producer
portlet located remotely.

Now that you know a little bit about the WSRP process, let's begin configuring
WSRP on Liferay Portal. For this demonstration, we'll assume you have two portal
servers.

---

 ![Tip](../../images/tip.png) **Tip**: If you're following along with this
 example and don't have an additional portal server, you can download another
 instance of Liferay Portal and have it running at the same time as your current
 Liferay instance to simulate an additional portal server. Remember, typical use
 cases have WSRP producers and consumers linked on differing portal servers. To
 run two portal instances locally at the same time, you'll need to change one of
 your portal's server configurations. Navigate to one of your portal's
 `tomcat-[VERSION]\conf\server.xml` and change the `port=` designations to
 different values (e.g., change `8080` to `18080`). Also, you can specify the
 new port number for your browser launcher URL by adding
 `browser.launcher.url=http://localhost:18080` to your portal's
 `portal-ext.properties` file.

---

To create a producer, go to the *Producers* tab and click *Add Producer*. Give
your producer a name and choose the appropriate version of WSRP to use. Liferay
displays a list of available portlets your producer can use. For demonstration
purposes, select the Hello World portlet and click the *Save* button. The portal
generates a WSDL document to define your producer. To view the WSDL document,
click the URL link provided.

![Figure 18.26: You can view the WSDL document for your producer by clicking the provided URL.](../../images/wsdl-url.png)

Now that we've created a producer, let's create a consumer on your second portal
server. 

On your consumer portal server, navigate to the Consumers tab and select the
*Add Consumer* button. Give it a name and add the producer's WSDL URL in the
*URL* field. There are also additional fields:

*Forward Cookies:* Allows the WSRP consumer to forward specific cookies from
the user's browser session to the WSRP producer.

*Forward Headers:* Allows the WSRP consumer to forward specific HTTP headers
from the user's browser session to the WSRP producer.

*Markup Character Sets:* Markup character encodings supported for the consumer
are shown in a comma delimited list. UTF-8 is assumed and will be added
automatically as a supported encoding.

Leave these additional fields blank for our demonstration. Lastly, we need to
define the portlets that the end-user can use from this consumer. To do this, go
to *Actions* &rarr; *Manage Portlets* for your consumer. Add the remote portlets
that you've configured for your producer portal server. In this case, select the
*Hello World* remote portlet and give the new portlet an arbitrary name. Now end
users can "consume" or use the remote portlet just like any local portlet in the
portal.

Next, you'll learn how to create custom remote portlets.

### Creating Custom Remote Portlets [](id=creating-custom-remote-portlets-liferay-portal-6-2-user-guide-18-en)

With the demand for dynamic portlets by end users, sometimes a finite,
pre-selected list of remote portlets isn't enough. Because of this, Liferay
allows you to make custom developed portlets remotely accessible for WSRP.

To enable your custom portlet for WSRP, you'll need to add the
`<remoteable>true</remoteable>` tag in your portlet's
`docroot/WEB-INF/liferay-portlet.xml` file:

	<liferay-portlet-app>
		<portlet>
			<portlet-name>RemoteSamplePortlet</portlet-name>
			<remoteable>true</remoteable>
	...
		</portlet>
	...
	</liferay-portlet-app>

After editing your portlet's `liferay-portlet.xml` file, your custom portlet
will appear in the list of portlets available when creating a WSRP producer.
Congratulations! Now you can share all your portlets to end users using WSRP!

Next, we'll learn how to remotely access Liferay services.

## Remotely Accessing Liferay Services [](id=remotely-accessing-liferay-services-liferay-portal-6-2-user-guide-18-en)

Liferay includes a utility called the *Service Builder* which is used to
generate all of the low level code for accessing resources from the portal
database. This utility is further explained in the [*Liferay Developer Guide*](http://www.liferay.com/documentation/liferay-portal/6.2/development) and
in [*Liferay in Action*](http://manning.com/sezov), but it is mentioned here
because of its feature which generates interfaces not only for Java code, but
also for web services and JavaScript. This means that the method calls for
storing and retrieving portal objects are all the same, and are generated in the
same step.

![Figure 18.27: Liferay SOA's first layer of security is its properties files.](../../images/liferay-soa-first-layer.png)

Because the actual method calls for retrieving data are the same regardless of
how one gets access to those methods (i.e., locally or through web services),
Liferay provides a consistent interface for accessing portal data that few other
products can match. The actual interfaces for the various services are covered
in the [*Liferay Developer Guide*](http://www.liferay.com/documentation/liferay-portal/6.2/development) and
in [*Liferay in Action*](http://manning.com/sezov). Before these services can be
used, administrators need to enable users to access these services remotely.

In the default `portal.properties` file, there is a section called **Main
Servlet**. This section defines the security settings for all of the remote
services provided by Liferay. Copy this section and paste it into your custom
`portal-ext.properties` file. Then you can edit the default values to configure
the security settings for the Axis Servlet, the Liferay Tunnel Servlet, the
Spring Remoting Servlet, the JSON Tunnel Servlet, and the WebDAV servlet.

By default, a user connecting from the same machine Liferay is running on can
access remote services so long as that user has the permission to use those
services in Liferay's permissions system. Of course, you are not really "remote"
unless you are accessing services from a different machine. Liferay has two
layers of security when it comes to accessing its services remotely. Without
explicit rights to both layers, a remote exception will be thrown and access to
those services will not be granted.

The first layer of security that a user needs to get through in order to call a
method from the service layer is servlet security. The *Main Servlet* section of
the `portal-ext.properties` file is used to enable or disable access to
Liferay's remote services. In this section of the properties file, there are
properties for each of Liferay's remote services.

You can set each service individually with the security settings that you
require. For example, you may have a batch job which runs on another machine in
your network. This job looks in a particular shared folder on your network and
uploads documents to your site's Documents and Media portlet on a regular basis,
using Liferay's web services. To enable this batch job to get through the first
layer of security, you would modify the `portal-ext.properties` file and put the
IP address of the machine on which the batch job is running in the list for that
particular service. For example, if the batch job uses the Axis web services to
upload the documents, you would enter the IP address of the machine on which the
batch job is running to the `axis.servlet.hosts.allowed` property.  A typical
entry might look like this:

    axis.servlet.hosts.allowed=192.168.100.100, 127.0.0.1, SERVER_IP

If the machine on which the batch job is running has the IP address
`192.168.100.100`, this configuration will allow that machine to connect to
Liferay's web services and pass in user credentials to be used to upload the
documents.

![Figure 18.28: Liferay SOA's second layer of security is its permissions system.](../../images/liferay-soa-second-layer.png)

The second layer of security is Liferay's security model that it uses for every
object in the portal. The user account that accesses the services remotely must
have the proper permissions to operate on the objects it attempts to access.
Otherwise, a remote exception will be thrown. Portal administrators need to use
Liferay's permissions system to grant access to these resources to the
administrative user account that attempts to operate on them remotely. For
example, say that a Documents and Media folder called *Documents* has been set
up in a site. A role has been created called *Document Uploaders* which has the
rights to add documents to this folder. Your batch job will be accessing
Liferay's web services in order to upload documents into this folder. In order
for this to work, you have to call the web service using a user account to which
the *Document Uploaders* role has been assigned (or that has individual rights
to add documents to the folder). Otherwise, you will be prevented from using
the web service.

To call the web service using credentials, you would use the following URL
syntax:

    http://" + userIdAsString + ":" + password +
    "@<server.com\>:<port\>/tunnel-web/secure/axis/" + serviceName

The user ID is the user's ID from Liferay's database. This can be obtained by
logging in as the user and navigating to the *My Account* portlet in the Control
Panel. The user ID appears on the Details page of the My Account portlet in the
Control Panel.

For example, to get Organization data using a user that has the ID of
*2* with a password of *test*, you would use the following URL:

    http://2:test@localhost:8080/tunnel-web/secure/axis/Portal_OrganizationService

It is important to note here how *Password Policies* (covered in this guide's
chapter on [User
Management](https://www.liferay.com/documentation/liferay-portal/6.2/user-guide/-/ai/management-liferay-portal-6-2-user-guide-16-en)
can be used in combination with this feature. If you are enforcing password
policies on your users (requiring passwords to take a certain form, requiring
users to change their passwords on a periodic basis, etc.), any administrative
user account which accesses Liferay's web services in a batch job will have its
password expire too.

To prevent this from happening, you can add a new password policy which does not
enforce the password expiration and add your administrative user ID to it. Then
your batch job can run as many times as you need it to and the administrative
user account's password will never expire.

In summary, accessing Liferay remotely requires the successful passing
of two security checks:

1.  The IP address must be pre-configured in the server's
    `portal-ext.properties` file.  
2.  The user ID being used must have permission to access the resources it
    attempts to access.

### Accessing Liferay's JSON Web Services [](id=accessing-liferays-json-web-services-liferay-portal-6-2-user-guide-18-en)

To see which Liferay service methods are registered and available for use via
JSON web services, open your browser to the following address:

    http://localhost:8080/api/jsonws

The page lists the portal's registered and exposed service methods. Get each
method's details by clicking the method name. You can view the full signature of
each method, all its arguments, the exceptions that can be thrown, and its
Javadoc! Using a simple form from within your browser, you can even invoke the
service method for testing purposes.

To list registered services on a plugin (e.g. a custom portlet), don't forget to
use its context path in your URL:

    http://localhost:8080/[plugin-context]/api/jsonws

This lists the JSON Web Service API for the plugin. 

### Accessing Liferay's WSDL [](id=accessing-liferays-wsdl-liferay-portal-6-2-user-guide-18-en)

After configuring the security settings properly, your first step in obtaining
access to Liferay's remote SOAP web services is to access the WSDL. If you are
on a browser on the same machine Liferay is running on, you can do this by
accessing the following URL:

    http://localhost:<port number\>/tunnel-web/axis

If, for example, you are running on Tomcat on port 8080, you would specify this
URL:

    http://localhost:8080/tunnel-web/axis

If you are accessing a web service that was created as part of a portlet plugin,
the URL is similar, but uses the context of your application rather than the
tunnel-web servlet. You can get a list of your Service Builder-generated WSDL
documents by using the URL pattern below:

    http://localhost:8080/your-portlet/axis

If you are on a different machine from the Liferay server, you will need to pass
in your user credentials on the URL to access the WSDL:

    http://<user ID\>:<password\>@<server name\>:<port number\>/tunnel-web/axis

In any case, once you successfully browse to this URL, you will see the list of
web services. You can access the WSDL for each service by clicking on the *WSDL*
link next to the name of the service. There are many services; one for each of
the services available from the Liferay API.

Once you click on one of the *WSDL* links, the Web Service Definition Language
document will be displayed. This document can be used to generate client code in
any language that supports it. You can either save the document to your local
machine and then generate the client code that way, or use your tool to trigger
Liferay to generate the document dynamically by using one of the URLs above. For
further information about developing applications that take advantage of
Liferay's remote services, please see the [*Liferay Developer Guide*](http://www.liferay.com/documentation/liferay-portal/6.2/development) or
[*Liferay in Action*](http://manning.com/sezov).

## Summary [](id=summary-liferay-portal-6-2-user-guide-18-en)

Liferay Portal is an easy environment to maintain. Backup procedures are simple
and straightforward. Administrators have all the options they need to view and
diagnose a running Liferay Portal server through its tunable logs. Patching
Liferay is easy to do with Liferay's patching tool. It handles for you all the
management of available patches, and makes it easy to install and uninstall
them. Upgrading Liferay is also a snap, because Liferay does most of the work
automatically. With easy migration tools and automated database upgrade scripts,
you'll have your new version of Liferay Portal up and running in no time. It's
also easy and secure for Liferay administrators to remotely access both built-in
Liferay web services and custom services created using Service Builder.
