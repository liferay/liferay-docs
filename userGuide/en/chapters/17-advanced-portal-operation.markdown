# Advanced Portal Operation [](id=lp-6-1-ugen17-advanced-portal-operation-0)

In this chapter we discuss several advanced features of Liferay Portal, including audit trails and portal maintainence, backup, and logging. Audit trails allow portal administrators to track the activities of portal users--this can be very useful for troubleshooting or figuring out who's resposible for certain actions that have taken place on your portal. It's generally not much more complicated to maintain a running Liferay instance than it is to maintain the application server upon which it's running. However, Liferay provides tools for logging, patching, and upgrading Liferay that you should know how to use. It's also important to follow secure backup procedures to protect your Liferay instance's source code, database, and properties files.

We'll discuss the following topics in this section:

-   Audit trails
-   Liferay Monitoring using Google Analytics
-   Backing Up a Liferay Installation
-   Changing Logging Levels
-   Patching Liferay
-   Upgrading Liferay

Let's get started with audit trails.

## Audit Trails [](id=using-audit-trails)

![EE Only Feature](../../images/ee-only-image/ee-feature-web.png)

You've just finished lunch and are ready to get back to work. You have a site in Liferay you use to manage your project, and before you left, you were about to create a folder in your Documents and Media library for sharing some requirements documentation. Sitting down at your desk, you navigate to the repository and attempt to create the folder. 

*You do not have permission to perform this action*, Liferay helpfully tells you. 

"*What?" you blurt accidentally in surprise. "This is *my* project!" 

"Ah, you too?" asks a co-worker helpfully from over the cube wall. "I lost access to a wiki I was updating just a few minutes ago. I was about to enter a support ticket for it." 

"Forget the ticket. Let's go see the portal admin now," you say. 

And off you go, two floors down, to the far end of the building where, as you approach, you can already hear stress in the portal admin's voice as he tries to reassure someone on the phone. 

"Yes, Mr. Jones. Yes, I'll fix it." (*Jones? The president of the company?* goes through your mind.) "I'll get on it right away, Mr. Jones. It was just a mistake; I'll fix it. Thank you, Mr. Jones," and he hangs up the phone. 

"Problems?" you ask the portal admin, whose name is Harry. He does look rather harried. 

"Yeah, Tom," he says. "Somebody changed a bunch of permissions in the portal--it wasn't me. I'm assuming you and Dick are here because of the same problem?" 

"Yup," you say. "I lost access to a document repository folder." 

"And I lost access to a wiki," Dick says helpfully. 

"It was probably due to some site membership change. Let's take a look at the audit portlet in the control panel and see what happened." 

When in the course of human events it becomes necessary to see what users are doing on your portal, you'll find Liferay makes this easy. If you're a Liferay Enterprise Edition customer, you have access to two plugins--a hook and a portlet--that, in combination with some settings in `portal-ext.properties`, enable you to see all the activity that occurs in your portal. Using this, you can quickly find out what changes were made and by whom. If you've delegated permission granting to any group of people, this is an essential feature you're likely to use. 

We'll come back to Tom, Dick and Harry's story later in the chapter. For now, let's look at how to install Liferay's audit plugins so you can do the same thing Harry's about to do. 

### Installing and configuring the audit plugins [](id=installing-and-configuring-the-audit-plugins)

Liferay's audit functionality is composed of two parts: a back-end piece that hooks into Liferay events and a front-end piece that gives you an interface to see what's happening. Both of these are available as EE-only plugins in the Customer Portal or Liferay Marketplace, and you'll need to install both to get audit functionality working (plugins installation is covered in chapter 13). 

Once installed, there are two properties in your `portal-ext.properties` file which you can use to tweak the settings. 

**com.liferay.portal.servlet.filters.audit.AuditFilter:** By default, this is set to `false`, because the audit plugins aren't installed by default. When you set it to `true`, the audit hook is able to capture more information about events, such as the client host and the client's IP address. 

**audit.message.com.liferay.portal.model.Layout.VIEW:** In the code, pages are *layouts*. Setting this to `true`, therefore, records audit events for page views. It's turned off by default because this may be too fine-grained for most installations. 

Once you've decided if you're going to use one or both of the two settings above, place them in your `portal-ext.properties` file and restart your Liferay server. Once it comes up, audit events are captured by Liferay, and you'll be able to use them to see what's happening in your portal. 

### Using audit events [](id=using-audit-events)

Now that you're capturing audit events, it's easy to use them to view activities in your portal. Navigate to the control panel and you'll find a new entry in the *Portal* section labeled *Audit Reports* (see figure 17.1). 

![Figure 17.1: Once the Audit Reports plugins are installed, an entry appears in the control panel.](../../images/control-panel-audit-reports.png)

Clicking the entry shows you a list of the events Liferay has already captured (see figure 17.2), along with an interface for searching for events. You can browse the list if you want, but it's likely you'll need to use the search to find what you're looking for. 

![Figure 17.2: Liferay captures and stores events as soon as the audit plugins are installed.](../../images/audit-list-events.png)

Figure 17.2 shows Stephen Professor logged in and did some things on the site. To see the detail of any of these events, all you need to do is click one to see more information. You'll then see something like figure 17.3. 

![Figure 17.3: Clicking an event in the list shows the details of that event. This event shows it must've been Stephen Professor's first time logging into the site, because he's accepting the terms of use.](../../images/audit-detail.png)

As you can see, depending on how many users you have in your portal, this list can get populated very quickly. For this reason, it's a good idea to keep the `audit.message.com.liferay.portal.model.Layout.VIEW` property set to `false`. This way, you don't clutter up your audit events with multiple page view events, which will definitely be the most often triggered event in your portal. 

Now that you know how to browse and view audit events, let's look at searching for specific events. 

### Viewing audit reports [](id=viewing-audit-reports)

Finding what you want in a big list of events is, to use the expression, like searching for a needle in a haystack. This is why the audit portlet gives you a robust searching mechanism. By default, it looks pretty simple: there's only a single field for searching. Clicking the *advanced* link, however, reveals a search dialog broken out by various fields you can use in your search. 

Let's look at the options we have for search. 

**Match:** You can match all fields you've specified or any single field. 

**User ID:** Specify the user ID you'd like to search for. This would be the user who performed some action in the portal you'd like to audit. 

**User Name:** Specify the user name you'd like to search for. This is often easier than searching for a user ID, especially if you don't have access to the Liferay database to find the user ID. 

**Resource ID:** Specify the ID of the resource that was modified or viewed in this audit record. 

**Resource Name:** Specify the name of the resource that was modified or viewed in this audit record. For example, you could search for User resources to see if someone modified a user's account. 

**Resource Action:** Specify an action that was performed on the resource. This could be any one of the following: `add`, `assign`, `delete`, `impersonate`, `login`, `login_failure`, `logout`, `unassign`, or `update`. 

**Session ID:** Specify the session ID to search for. You'd use this if you were correlating a session ID from your web server logs with activity in Liferay. 

**Client IP:** Specify the IP address of the client that performed the activity you wish to audit. 

**Client Host:** Specify the host name of the client that performed the activity you wish to audit. 

**Server Name:** Specify the server name upon which the activity occurred. If you're using a cluster, each member of the cluster can be individually queried. 

**Server Port:** Specify the server port upon which the activity occurred. You'd need this if you run a "vertical" cluster of multiple VMs on the same machine. 

**Start Date:** Specify the low end of the date range you wish to search. 

**End Date:** Specify the high end of the date range you wish to search. 

Using this form, if you wanted to check to see if someone in the portal unassigned a user from a particular role, you might search for a resource name of *user* and a resource action of *unassign*. The results of such a search might look something like figure 17.4. 

![Figure 17.4: Searching audit events is easy with the search form provided by the audit portlet. You can quickly drill down to find the types of events you're looking for.](../../images/audit-unassign-search.png)

Once you have the results of your search, you can click on any of the records returned to see the detail page for that record. Figure 17.5 shows, in this particular case, the default administrative user removed Stephen Professor from the role of Power User. 

![Figure 17.5: If you've delegated portal administration to multiple users, you can use the audit plugins to determine who made what change. And, of course, you'll never leave the default administrative user enabled in a production system, right?](../../images/audit-unassign-detail.png)

As you can see, Liferay's audit portlets give you a lot of power to see what's happening in your portal. You can use this information to troubleshoot problems, determine ownership of particular actions, or, as Harry is about to do, find out who made permission changes they weren't supposed to make. 

### Conclusion [](id=conclusion)

"Okay," says Harry, "let's fire up Liferay's audit system and see if we can figure out what happened." 

You and Dick stand behind Harry's chair and watch as he enters a query into a form on the audit portlet. Clicking *search*, the screen fills up with audit events. 

"Wow, that's a lot of unassign events." Harry says. "And look who the culprit is," he adds sarcastically. 

"Who's Melvin Dooitrong?" Dick asks. 

"That's my new intern," Harry says. "I'm gonna kill him." Harry pushes out his chair and walks down the row of cubes to the end, where a kid no more than 20 years old with disheveled hair sits, earbuds in his ears. 

"Hey Melvin," Harry says as Melvin turns around to face him. "Didn't I ask you to move that set of users from site membership to organization membership?" 

"Yeah," Melvin says, "I did that already." 

"How'd you do it?"

"It was going to take a while to do it manually, so I wrote a script and executed it in the scripting host," Melvin replies, matter-of-factly. 

"You did, did you? Well, guess what? Your script removed *everybody* from *all* sites."

"*What?*" 

"Yeah, and now you're going to start adding them back, one by one, manually, starting with Mr. Jones...." 

Tom and Dick back away slowly from Melvin's cube as Harry and Melvin continue to have their--let's call it a discussion. One thing is clear: they're having a better day than Melvin is.

Now that we've seen how you can use audit trails, let's look at some tools and best practices for maintaining your Liferay installation.

## Maintainence [](id=maintaining-a-liferay-port-5)

It's not much harder to maintain a running implementation of Liferay Portal than it is to maintain the application server environment upon which it is running. There are, however, several factors which administrators should be aware of when they are responsible for a running instance of Liferay. This section addresses these issues and outlines some specifics about how to keep a running Liferay instance stable and secure.

The discussion on backup covers what parts of Liferay should be backed up. We won't cover specific backup software or procedures; generally, most organizations have standards for doing backups of their systems, and Liferay as a Java EE application fits well into these standards.

### Liferay monitoring using Google Analytics [](id=liferay-monitoring-using-google-analyti-5)

Liferay includes built-in support for Google Analytics, allowing administrators to make use of Google's tool set for analyzing site traffic data. When you sign up for Google Analytics, a snippet of code is provided which needs to be added to your web pages to allow Google's system to register the page hit. It can be a tedious process to add this code to every page on a site, especially if it's a large site and there is a lot of user-generated content.

This problem can be solved in Liferay by putting Google's code into a custom theme written specifically for the web site on which the portal is running. Doing this, however, requires a theme developer to make specific changes to the theme and it prevents users from using the many themes that are freely available for Liferay "out of the box."

Because of this, support for Google Analytics has been built into Liferay, and can be turned on through a simple user interface. This allows Liferay administrators to make use of Google Analytics on a community by community basis and turn it on and off when needed.

To enable Google Analytics support, go to *Site Settings* in the control panel, and then select *Analytics* on the right. You'll see a very simple form, pictured below. 

![Figure 17.6: Setting up Google Analytics for your site is very easy: sign up for the ID and then enter it into this field. ](../../images/maintaining-google-analytics.png)

Put your Google Analytics ID (which should have been provided to you when you signed up for the service) in the field and click *Save*. All the pages in the community you selected will now have the Google Analytics code in them and will be tracked. 

This is a fairly simple procedure, and it gives you the ability to take advantage of some great tools to help you visualize who's coming to your site and from where. Next, we discuss some topics germane to maintaining your Liferay installation as it's used. Let's start with backup.

### Backing up a Liferay installation [](id=backing-up-a-liferay-installati-5)

Once you have an installation of Liferay Portal running, you'll want to have proper backup procedures in place in case of a catastrophic hardware failure of some kind. Liferay isn't very different from any other application that may be running on your application server. Nevertheless, there are some specific components you should include in your backup plan.

#### Backing up source code [](id=lp-6-1-ugen16-backing-up-source-code-0)

If you have extended Liferay or have written any plugins, they should be stored in a source code repository such as Git, Subversion, or CVS, unless you're Linus Torvalds, and then tarballs are okay too (that's a joke). Your source code repository should be backed up on a regular basis to preserve your ongoing work. This probably goes without saying in your organization, as nobody wants to lose source code that's taken months to produce, but we thought we should mention it anyway. 

If you're extending Liferay with an Ext plugin, you'll want to make sure you also store the version of the Liferay source on which your extension environment is based. This allows your developers convenient access to all the tools they need to build your extension and deploy it to a server.

Let's look at the items that need to be backed up in your Liferay installation. 

#### Backing up Liferay's file system [](id=lp-6-1-ugen16-backing-up-liferays-file-system-0)

Liferay's configuration file, `portal-ext.properties`, gets stored in the *Liferay Home* folder, which is generally one folder up from where your application server is installed (see chapter 14 for specific details for your application server). At a minimum, this file should be backed up, but it is generally best to back up your whole application server.

If you've followed the non-plugin procedure (see chapter 19) to modify your Ehcache configuration, you'll have cache configuration files in the deploy location of Liferay. You'll need to back up this location. If you're using the plugin procedure (i.e., the recommended procedure), your cache configuration settings are stored in your source code repository, which is backed up separately. 

Liferay stores configuration files, search indexes, and cache information in a folder called `data` in Liferay Home. If you're using the File System store or the Advanced File System store, the media repository is stored here (by default) too. You should always back up the contents of your Liferay Home folder.

If you've modified the location where the Document Library stores files, you should also back up this location.

That about covers the file system locations Liferay uses. Next, let's discuss how to back up Liferay's database.

#### Backing up Liferay's database [](id=lp-6-1-ugen16-backing-up-liferays-database-0)

Liferay's database is the central repository for all of the Portal's information and is the most important component that needs to be backed up. You can do this by backing up the database live (if your database allows this) or by exporting the database and then backing up the exported file. For example, MySQL ships with a `mysqldump` utility which allows you to export the entire database and data into a large SQL file. This file can then be backed up. In case of a database failure, this file can be used to recreate the state of the database at the time the dump was created.

If you're using Liferay's Documents and Media Library with the Jackrabbit JSR-170 repository to store documents in a database, the Jackrabbit database should be backed up also. If you've placed your search index into a database (not recommended; see chapter 19 for information on using Cluster Link or Solr), that database should be backed up as well. 

Search indexes can be backed up as well, if you wish to avoid reindexing your entire portal after you do your restore. This is easiest to do if you have a separate Solr environment upon which your index is stored. If you're in a clustered configuration and you're replicating indexes, you'll need to back up each index replica. 

Restoring your application server, your Liferay Home folder, the locations of any file system-based media repositories, and your database from a backup system should give you a functioning portal. Restoring search indexes should avoid the need to reindex when you bring your site back up after a catastrophic failure. Good, consistent backup procedures are key to successfully recovering from a hardware failure. 

But what about maintenance while your server is running? Liferay lets you view a lot of what is going on through its logging system. 

### Liferay's Logging System [](id=liferay-s-logging-syst-5)

Liferay uses Log4j extensively to implement logging for nearly every class in the portal. If you need to debug something specific while the system is running, you can use the control panel to set logging levels by class dynamically.

To view the log levels, go to the control panel, click *Server Administration* in the Server section, and then click the *Log Levels* tab.

A paginated list of logging categories appears. These categories correspond to Liferay classes that have log messages in them. By default, all categories are set to display messages only if there is an error that occurs in the class. This is why you see ERROR displayed in all of the drop-down list boxes on the right side of the portlet.

Each category is filtered by its place in the class hierarchy. For example, if you wanted to see logging for a specific class that is registered in Liferay, you would browse to that specific class and change its log level to something that is more descriptive, such as DEBUG. Once you click the *Save* button at the bottom of the list, you'll start seeing DEBUG messages from that class in your application server's log file.

If you're not sure which class you want to see log messages for, you can find a place higher up in the hierarchy and select the package name instead of an individual class name. If you do this, messages for every class lower in the hierarchy will be displayed in your application server's log file.

![Figure 17.7: Log levels can be dynamically changed at runtime, whenever you need to debug an issue. ](../../images/maintaining-log-levels.png) 

Be careful when you do this. If you set the log level to DEBUG somewhere near the top of the hierarchy (such as `com.liferay`, for example), you may wind up with a lot of messages in your log file. This could make it difficult to find the one you were looking for, and causes the server to do more work writing messages to the log. 

If you want to set the log level for one of your own classes in a deployed plugin, you can register that class with Liferay to can control the log levels more easily, so long as your class uses Log4J to do its logging.

You will first need to implement Log4J logging in your class, with a statement such as the following (taken from Liferay's `JCRStore` class):

	private static Log _log = LogFactory.getLog(JCRStore.class);

You would then use this `_log` variable to create log messages in your code for the various logging levels:

	_log.error("Reindexing " + node.getName(), e1);

To enable your logging messages to appear in your server's log file via the control panel, click the *Add Category* tab on the same *Log Levels* page.

![Figure 17.8: Adding your own logging classes is as simple as specifying it in this field.](../../images/maintaining-add-log-category.png) 

You'll see you can add a logging category. Put in the fully qualified name of your class or of the package that contains the classes whose log messages you want to view, choose a log level, then click the *Save* button. You will now start to see log messages from your own class or classes in the server's log file.

Logs are great for figuring out issues in production. But what if Liferay contacts you via its support channel with a bug fix or a security enhancement? Read on to learn how to patch Liferay. 

### Patching Liferay [](id=patching-liferay)

![EE Only Feature](../../images/ee-only-image/ee-feature-web.png)

While we strive for perfection with every release of Liferay Portal, the reality of the human condition dictates that releases of the product may not be as perfect as originally intended. But we've planned for that. Included with every Liferay bundle is a patching tool that can handle the installation of two types of patches: hot fixes and fix packs. 

A hot fix is provided to a customer when a customer contacts Liferay about an issue, and Liferay's support team--working with the customer--determines that the problem is indeed an issue with the product that needs to be fixed. Support fixes the bug and provides a hot fix to the customer immediately. This is a short-term fix that solves the issue for the customer as quickly as possible. 

On a regular schedule, these hot fixes are bundled together into fix packs. Fix packs are provided to all of Liferay's customers and are component-based. This means any issues with the content management system will be bundled together separately from issues with another component, such as the message boards. This lets you determine which patches are critical and which are not, based on your usage. Of course, if Liferay issues a security advisory, that's something you're always going to want to patch. 

Now that you know what patching is all about, let's check out the tool. 

#### Installing the patching tool [](id=lp-6-1-ugen16-installing-the-patching-tool-0)

If you're using a Liferay bundle, congratulations! The patching tool is already installed. Your job isn't done yet, however, because Liferay *might* have updated the patching tool. Always check the Customer Portal to see if the patching tool has been updated first. But even if you forget to check, the patching tool will tell you if it needs to be updated when you run it. A lot of planning and forethought has gone into the patching system to make it run as smoothly as possible.

You follow the same procedure whether you're installing or upgrading the patching tool. Once you've obtained it from the customer portal, unzip it to the Liferay Home folder. This is the folder where you've placed your `portal-ext.properties` file and where by default the `data` folder resides. This is generally one folder up from where your application server is installed, but some application servers are different. If you don't know where Liferay Home is on your system, check chapter 14 to see where this folder is for your specific application server.

If you're upgrading the patching tool, all you need to do is unzip the new version on top of the old version. Note that if you're doing this on LUM (Linux, Unix, Mac) machines, you'll need to make the `patching-tool.sh` script executable.

After the patching tool is installed, you need to let it auto-discover your Liferay installation. Then it will determine what your release level is and what your application server environment is. This is a simple command to run on LUM:

	./patching-tool.sh auto-discovery
	
or on Windows: 

	patching-tool auto-discovery
	
From here on, for brevity we'll use the LUM version of the command. Why? Because Liferay is open source; there's no open source variant of Windows (ReactOS is still in alpha, so it doesn't count); and therefore my (RS) unscientific impression is that more people will run Liferay on open source technology than not. If I'm wrong, I'm wrong, but there are still many other examples of documentation that defaults to Windows, so we still get to be different. 

If you've installed the patching tool in a non-standard location, you'll have to give this command another parameter to point it to your Liferay installation. For example, if you've installed a Liferay/Tomcat bundle in `/opt/Liferay`, you'd issue this command: 

	./patching-tool.sh auto-discovery /opt/Liferay/tomcat-7.0.21
	
In all, this is pretty simple. Now let's see how to use the patching tool to get your patches installed. 

#### Installing patches [](id=lp-6-1-ugen16-installing-patches-0)

The absolute first thing you must do when installing one or more patches is to shut down your server. On Windows operating systems, files that are in use are locked by the OS, and won't be patched. On LUM systems, you can generally replace files that are running, but of course that still leaves the old ones loaded in memory. So your best bet is to shut down the application server that's running Liferay before you install a patch. 

Liferay distributes patches as `.zip` files, whether they are hot fixes or fix packs. When you receive one, either via a LESA ticket (hot fix) or through downloading a fix pack from the customer portal, you'll need to place it in the `patches` folder, which is inside the patching tool's home folder. Once you've done that, it's a simple matter to install it. First, execute

	./patching-tool.sh info
	
This shows you a list of patches you've already installed, along with a list of patches that *can* be installed, from what's in the `patches` folder. To install the available patches, issue the following command: 

	./patching-tool.sh install
	
Your patches are now installed. You can verify this by using the `./patching-tool.sh info` command, which now shows your patch in the list of installed patches. Let's look now at how you'd manage your patches. 

##### Handling hot fixes and patches [](id=lp-6-1-ugen16-handling-hot-fixes-and-patches-0)

As stated above, hot fixes are short term fixes provided as quickly as possible and fix packs are larger bundles of hot fixes provided to all customers at regular intervals. If you already have a hot fix installed, and the fix pack which contains that hot fix is released, you can rest assured the patching tool will manage this for you. Fix packs always supercede hot fixes, so when you install your fix pack, the hot fix that it already contains is  uninstalled, and the fix pack version is installed in its place. 

Sometimes there can be a fix to a fix pack. This is also handled automatically. If a new version of a fix pack is released, you can use the patching tool to install it. The patching tool uninstalls the old fix pack and installs the new version in its place. 

##### Fix pack dependencies [](id=lp-6-1-ugen16-fix-pack-dependencies-0)

Some fix packs require other fix packs to be installed first. If you attempt to install a fix pack that depends on another fix pack, the patching tool will notify you of this so you can go to the customer portal and obtain the fix pack dependency. Once all the necessary fix packs are available in the `patches` folder, the patching tool will install them. 

The patching tool can also remove patches. 

#### Removing or reverting patches [](id=lp-6-1-ugen16-removing-or-reverting-patches-0)

Have you noticed that the patching tool only seems to have an `install` command? This is because patches are managed not by the command, but by what appears in the `patches` folder. You manage the patches you have installed by adding or removing patches from this folder. If you currently have a patch installed and you don't want it installed, remove it from the `patches` folder. Then run the `./patching-tool.sh install` command, and the patch is removed. 

If you want to remove all patches you've installed, use the `./patching-tool.sh revert` command. This removes all patches from your installation.

What we've described so far is the simplest way to use the patching tool, but you can also use the patching tool in the most complex, multi-VM, clustered environments. This is done by using profiles. 

#### Using profiles with the patching tool [](id=lp-6-1-ugen16-using-profiles-with-the-patching-tool-0)

When you ran the auto-discovery task after installing the patching tool, it created a default profile that points to the application server it discovered. This is the easiest way to use the patching tool, and is great for smaller, single server installations. But we realize many Liferay installations are sized accordingly to serve millions of pages per day, and the patching tool has been designed for this as well. So if you're running a small, medium, or large cluster of Liferay machines, you can use the patching tool to manage all of them using profiles. 

The auto-discovery task creates a properties file called `default.properties`. This file contains the detected configuration for your application server. But you're not limited to only one server which the tool can detect. You can have it auto-discover other runtimes, or you can manually create new profiles yourself. 

To have the patching tool auto-discover other runtimes, you'll need to use a few more command line parameters: 

	./patching-tool.sh [name of profile] auto-discovery [path/to/runtime]
	
This will run the same discovery process, but on a path you choose, and the profile information will go into a `[your profile name].properties` file. 

Alternatively, you can manually create your profiles. Using a text editor, create a `[profile name].properties` file in the same folder as the patching tool script. You can place the following properties in the file: 

**patching.mode:** This can be `binary` (the default) or `source`, if you're patching the source tree you're working with. Liferay patches contain both binary and source patches. If your development team is extending Liferay, you'll want to provide the patches you install to your development team so they can patch their source tree. 

**jdk.version:** Patches are compiled for both JDK 5 and JDK 6. Specify the one (either `jdk5` or `jdk6`) your application server is running against. 

**patches.folder:** Specify the location where you'll copy your patches. By default, this is `./patches`. 

**war.path:** No, no one's angry. This is a property for which you specify the location of the Liferay installation inside your application server. Alternatively, you can specify a .war file here, and you'll be able to patch a Liferay .war for installation to your application server. 

**global.lib.path:** Specify the location where .jar files on the global classpath are stored. If you're not sure, search for your `portal-service.jar` file; it's on the global classpath. This property is only valid if your `patching.mode` is `binary`.  

**source.path:** Specify the location of your Liferay source tree. This property is only valid if your `patching.mode` is `source`. 

You can have as many profiles as you want, and use the same patching tool to patch all of them. This helps to keep all your installations in sync. 

Now that you know how to patch an existing installation of Liferay, let's turn to how you'd upgrade Liferay from an older release to the current release. 

### Upgrading Liferay [](id=upgrading-lifer-5)

Liferay upgrades are fairly straightforward. A consistent set of steps is all you need to follow to upgrade a standard Liferay installation. Things do get more complicated if your organization has used Ext plugins to customize Liferay. It's possible that API changes in the new version will break your existing code. This, however, is usually pretty easy for your developers to fix. Portlet plugins which use Liferay APIs should be reviewed and their services rebuilt against the new release. Theme plugins may require some modifications to take advantage of new features, and if they're using Liferay APIs, they should be reviewed. Much effort has been made to make upgrades as painless as possible; however, this is not a guarantee everything will work without modification. Ext plugins are the most complicating factor in an upgrade, so it is important to test as much as possible.

As a general rule, you can upgrade from one major release to the next major release. For example, you can upgrade directly from Liferay 5.2.x to 6.0.x, but not from 5.2.x to 6.1.x. If you need to upgrade over several major releases, you'll need to run the upgrade procedure for each major release until you reach the release you want. This doesn't mean you need to run the procedure for every point release or service pack; you only need to run the procedure for the major releases. A good practice is to use the latest version of each major release to upgrade your system. 

Now that we've gotten the general philosophy of upgrading out of the way, let's outline the procedure you'll undergo for upgrading a Liferay 6.0 installation to a 6.1 installation. If you're running a previous version of Liferay and need to upgrade to 6.0 first, please see the instructions in the previous version of this document. 

#### Upgrading Liferay Portal 6.0 to Liferay Portal 6.1 [](id=lp-6-1-ugen16-upgrading-liferay-portal-60-to-liferay-portal-61-0)

There are a few things you'll want to prepare before you actually perform the upgrade. Specifically, you'll need to review your image gallery usage, review new Liferay 6.1 defaults, and catalog all the plugins you have installed. After you've performed these three tasks, you'll be ready to upgrade. 

Liferay 6.1 introduces a major change to how Liferay handles files. No longer do we have a separate Document Library and Image Gallery; instead, these have been combined into Documents and Media. If you were using Liferay's Image Gallery to store images, these can be migrated over during an upgrade, but you'll have to take some extra steps first. 

In Liferay 6.0, you had three ways you could store images in the Image Gallery. You could use the `DatabaseHook` and store them as BLOBs in the database; you could use the `DLHook` to store them in the Document Library, or you could use the `FileSystemHook` to store them in a folder on your server's file system. Before you upgrade, you'll need to set whichever property you were using in your 6.0 `portal-ext.properties` file, because by default, none of them are enabled in 6.1. Setting one of the properties triggers the migration during the upgrade process. Below are the three properties; you'll need to set only *one* of them (the one you were using). 

    image.hook.impl=com.liferay.portal.image.DatabaseHook
    image.hook.impl=com.liferay.portal.image.DLHook
    image.hook.impl=com.liferay.portal.image.FileSystemHook
    
The next thing you'll need to look at are the defaults that have changed from 6.0 to 6.1. These are preserved in `portal-legacy-6.0.properties` in the source. The 6.0 values are:  

    users.last.name.required=true
    layout.types=portlet,panel,embedded,article,url,link_to_layout
    editor.wysiwyg.portal-web.docroot.html.portlet.message_boards.edit_message.bb_code.jsp=bbcode
    setup.wizard.enabled=false
    discussion.subscribe.by.default=false
    message.boards.subscribe.by.default=false
    
The 6.1 values have changed to: 

    users.last.name.required=false
    layout.types=portlet,panel,embedded,url,link_to_layout
    editor.wysiwyg.portal-web.docroot.html.portlet.message_boards.edit_message.bb_code.jsp=ckeditor_bbcode
    setup.wizard.enabled=true
    discussion.subscribe.by.default=true
    message.boards.subscribe.by.default=true
    
If you don't like the defaults, you can change them back in one shot by adding a system property to your JVM's startup. This differs by application servers. In Tomcat, you'd modify `setenv.sh`/`setenv.bat` and append the option `-Dexternal-properties=portal-legacy-6.0.properties` to the environment variable JAVA_OPTS. The scripts `setenv.sh` or `setenv.bat` are not delivered with default Tomcat, but do exist in the bundles. If they're there, Tomcat uses them in the startup process, so it's a nice way to separate your own settings from Tomcat's default shell scripts. Alternatively, of course, you can override some or all of them in your `portal-ext.properties` along with your other overrides.  

Finally, you need to take note of any plugins you have installed. Liferay's plugins are usually version-specific, so you'll need to obtain new versions of them for the new release of Liferay. If you have custom plugins created by your development team, they'll need to build, test, and optionally modify them to work with the new release of Liferay. Don't attempt an upgrade without collecting all the plugins you'll need first. 

Once you've reviewed your properties and collected all the plugins you'll need, you're ready to follow the upgrade procedure. Remember to back up your system before you begin. 

There are two different procedures to upgrade Liferay. The first one, upgrading a Liferay bundle, is the most common. The second procedure is for upgrading a Liferay installation on an application server. We'll go over both.

In both cases, Liferay auto-detects whether the database requires an upgrade the first time the new version is started. When Liferay does this, it upgrades the database to the format required by the new version. To perform this task, Liferay *must* be accessing the database with an ID that can create, drop and modify tables. Make sure you have granted these permissions to the ID before you attempt to upgrade Liferay. And, of course, we'll run the risk of overly repeating ourselves: back up your database.  

Let's look at upgrading a bundle, which is the easiest upgrade path. 

##### Upgrading a bundle [](id=lp-6-1-ugen16-upgrading-a-bundle-0)

If you're running a Liferay bundle, the best way to do the upgrade is to follow the steps below. The new Liferay is installed in a newer version of your bundle runtime. For example, the Liferay/Tomcat bundle for 6.0 used Tomcat 6 by default; the 6.1 bundle uses Tomcat 7. Though there is a Tomcat 6 bundle of Liferay 6.1, that bundle also uses a newer release of Tomcat than the one from 6.0. This is the case for all runtimes Liferay supports. We generally recommend you use the latest version of your runtime bundle, as it will be supported the longest. 

   1. Obtain the new bundle. Unzip the bundle to an appropriate location on your system.
   2. Copy your `portal-ext.properties` file and your `data` folder to the new bundle. 
   3. Review your `portal-ext.properties` file as described above. If you were using the Image Gallery, make the necessary modifications so your files are migrated to Documents and Media. Review the new defaults and decide whether you want to use them. Review any other modifications you've made. 	
   4. Start your application server. Watch the console as Liferay starts: it should upgrade the database automatically.
   5. When the upgrade completes, install any plugins you were using in your old version of Liferay. Make sure you use the versions of those plugins that are designed for Liferay 6.1. If you have your own plugins, your development team will need to migrate the code in these ahead of time and provide .war files to you.  
   6. Browse around in your new installation and verify everything is working. Have your QA team test everything. If all looks good, you can delete the old application server with the old release of Liferay in it from the bundle directory. You have a backup of it anyway, right? 
   
As you can see, upgrading a bundle is generally pretty simple. But not everybody can use bundles: sometimes, specific application servers or application server versions are mandated by the environment you're in or by management. For this reason, Liferay also ships as an installable .war file that can be used on any supported application server.    

##### Upgrading using a .war file [](id=lp-6-1-ugen16-upgrading-using-a-war-file-0)

Running a manual upgrade is almost as easy as upgrading a bundle: 

   1. Verify your application server is supported by Liferay. You can do this by viewing the appropriate document on the Customer Portal (EE), in chapter 14 (because there are installation instructions for it), or on liferay.com (CE). If your application server isn't supported by Liferay 6.1, *do not continue!*  You'll need to upgrade or switch to a supported application server first. 
   2. Obtain the Liferay Portal .war file and the dependency .jars archive. 
   3. Copy your customized `portal-ext.properties` file to a safe place and review it as described above, making all the appropriate changes.  
   4. Undeploy the old version of Liferay and shut down your application server.
   5. Copy the new versions of Liferay's dependency .jars to a location on your server's class path, overwriting the ones you already have for the old version of Liferay. This location is documented for your application server in chapter 14. 
   6. Deploy the new Liferay .war file to your application server. Follow the deployment instructions in chapter 14.
   7. Start (or, if your app server has a console from which you've installed the .war, restart) your application server. Watch the console as Liferay starts: it should upgrade the database automatically. Verify your portal is operating normally, and then install any plugins you were using in your old version of Liferay. Make sure you use the versions of theose plugins designed for Liferay 6.1. If you have your own plugins, your development team will need to migrate the code in these ahead of time and provide .war files to you. 
   8. Browse around in your new installation and verify everything is working. Have your QA team test everything. If all looks good, you're finished. 

That's all there is to it. Most everything is handled by Liferay's upgrade procedure. Note as stated above, if you have to upgrade over several Liferay versions, you will need to repeat these steps for each major release. 

## Summary [](id=summ-37)

Liferay Portal is an easy environment to maintain. Backup procedures are simple and straightforward. Administrators have all the options they need to view and diagnose a running Liferay Portal server through its tunable logs. 

Patching Liferay is easy to do with Liferay's patching tool. It handles for you all the management of available patches, and makes it easy to install and uninstall them. 

Upgrading Liferay is also a snap, because Liferay does most of the work automatically. With easy migration tools and automated database upgrade scripts, you'll have your new version of Liferay Portal up and running in no time.
