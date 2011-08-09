# Maintaining A Liferay Portal

Maintaining a running implementation of Liferay Portal is not much
different from maintaining the application server environment upon which
it is running. There are, however, several factors which administrators
should be aware of when they are responsible for a running instance of
Liferay. This chapter will cover these issues, outlining for system
administrators some specifics about keeping a running Liferay instance
stable and secure.

This chapter will cover the following topics:

-   Liferay Monitoring using Google Analytics

-   Backing Up a Liferay Installation

-   Changing Logging Levels

-   Upgrading Liferay

The discussion on back up will cover what parts of Liferay should be
backed up. We will not cover specific backup software or procedures;
generally, most organizations have standards for doing backups of their
systems, and Liferay as a Java EE application fits well into these
standards.

## Liferay Monitoring Using Google Analytics

Liferay includes built-in support for Google Analytics, allowing
administrators to make use of Google's tool set for analyzing site
traffic data. When you sign up for Google Analytics, a snippet of code
is provided which needs to be added to your web pages in order to allow
Google's system to register the page hit. It can be a tedious process to
add this code to every page on a site, especially if it is a large site
and there is a lot of user-generated content.

This problem can be solved in Liferay by putting Google's code into a
custom theme written especially for the site on which the portal is
running. Doing this, however, requires that a theme developer make
specific changes to the theme, and it prevents users from using the many
freely available themes that are available for Liferay “out of the box.”

Because of this, support for Google Analytics has been built into
Liferay, and can be turned on through a simple user interface. This
allows Liferay Administrators to make use of Google Analytics on a
community by community basis and turn it on and off when needed.

To enable Google Analytics support, go to the Manage Pages screen for
the community for which you want to enable support. You can do this
through the Control Panel by going to either the *Organizations* or
*Communities* link in the *Portal* section, and then clicking *Actions →
Manage Pages* for the community or organization you want to analyze.*
*Click the *Settings* tab.

![image](../../images/portal-admin-ch8_html_m3451b46c.png)\
*Illustration 1: Setting Up Google Analytics*

\
\
Click the **Monitoring** Tab. Put your Google Analytics ID (which should
have been provided to you when you signed up for the service) in the
field and click *Save*. All of the pages in the community you selected
will now have the Google Analytics code in them and will be tracked.

## Backing Up A Liferay Installation

Once you have an installation of Liferay Portal running, you will want
to have proper backup procedures in place in case of a catastrophic
failure of some kind. Liferay is not very different from any other
application that may be running in your application server, but there
are some specific components that need to be backed up in addition to
your regular backup procedures for your application server.

### Source Code

If you have extended Liferay or have written any plugins, they should be
stored in a source code repository such as Subversion, CVS, or Git. This
repository should be backed up on a regular basis to preserve your
ongoing work.

If you are extending Liferay with the Ext Plugin, you will want to make
sure that you also store the version of the Liferay source on which your
extension environment is based. This allows your developers convenient
access to all of the tools they need to build your extension and deploy
it to a server.

### Liferay's File System

Liferay's configuration file, `portal-ext.properties`{.western}, gets
stored in the *Liferay Home* folder, which is generally one folder up
from where your application server is installed (see Chapter 2 for
specific info for your application server). At a minimum, this file
should be backed up, but it is generally best to back up your whole
application server.

If you have followed the procedure in the previous chapter to modify
your Ehcache configuration, you will have cache configuration files in
the deploy location of Liferay. You will need to back up this location.

Liferay also stores configuration files, search indexes, cache
information, and the default Jackrabbit document repository in a folder
called `data`{.western}* *in Liferay Home. You should generally back up
all of the contents of your Liferay Home folder.

If you have modified the location where the Document Library stores
files, you should also back up this location.

### Database

Liferay's database is the central repository for all of the Portal's
information and is the most important component which needs to be backed
up. You can do this by either backing up the database live (if your
database allows this) or by exporting the database and then backing up
the exported file. For example, MySQL ships with a `mysqldump`{.western}
utility which allows you to export the entire database and data into a
large SQL file. This file can then be backed up. In case of a database
failure, it can be used to recreate the state of the database at the
time the dump was created.

If you are using Liferay's Document Library with the Jackrabbit JSR-170
repository to store documents in a database, the Jackrabbit database
should be backed up also.

## Liferay's Logging System

Liferay uses Log4j extensively to implement logging for nearly every
class in the portal. If you need to debug something specific while a
system is running, you can use the Control Panel to set logging levels
by class dynamically.

To view the log levels, go to the Control Panel, click *Server
Administration* in the Server section, and then click the *Log Levels*
tab.

You will then see a paginated list of logging categories. These
categories correspond to Liferay classes that have log messages in them.
By default, all categories are set to display messages only if there is
an error that occurs in the class. This is why you see ERROR displayed
in all of the drop down list boxes on the right side of the portlet.

Each category is filtered by its place in the class hierarchy. For
example, if you wanted to see logging for a specific class that is
registered in Liferay, you would browse to that specific class and
change its log level to something that is more descriptive, such as
DEBUG. Once you click the *Save* button at the bottom of the list, you
will start seeing DEBUG messages from that class in your application
server's log file.

If you are not sure which class you want to see log messages for, you
can find a place higher up in the hierarchy and select the package name
instead of an individual class name. If you do this, messages for every
class lower in the hierarchy will be displayed in your application
server's log file.

![image](../../images/portal-admin-ch8_html_3579ac89.png)\
*Illustration 2: Changing Logging Levels*

\
\
Be careful when you do this. If you set the log level to DEBUG somewhere
near the top of the hierarchy (such as `com.liferay`{.western}, for
example), you may wind up with a lot of messages in your log file. This
could make it difficult to find the one you were looking for, and causes
the server to do more work writing messages to its log file.

If you are want to set the log level for one of your own classes in a
deployed plugin, you can register that class (so long as it uses Log4J
to do its logging) with Liferay so that you can control the log levels
more easily.

You will first need to implement Log4J logging in your class, with a
statement such as the following (taken from Liferay's
`JCRHook`{.western} class):

private static Log \_log = LogFactory.getLog(JCRHook.class);

You would then use this `_log`{.western} variable to create log messages
in your code for the various logging levels:

\_log.error("Reindexing " + node.getName(), e1);

To enable your logging messages to appear in your server's log file via
the Control Panel, click the *Add Category* tab on the same *Log Levels*
page.

![image](../../images/portal-admin-ch8_html_m559f0b07.png)\
*Illustration 3: Adding a Logging Category*

\
\
You will see that you can add a logging category. Simply put in the
fully qualified name of your class or of the package that contains the
classes whose log messages you want to view, choose a log level, and
then click the *Save* button. You will now start to see log messages
from your own class or classes in the server's log file.

## Upgrading Liferay

Liferay upgrades are fairly straightforward. A consistent set of steps
is all you need to follow to upgrade a standard Liferay installation.
Things do get more complicated if your organization has used the
extension environment to customize Liferay, as it will need to be
converted to an Ext Plugin for Liferay 6, and it is possible that API
changes in the new version will break your existing code. This, however,
is usually pretty easy for your developers to fix. Portlet plugins are
generally backwards compatible, as they are written to the Java
standard. This includes Portlet 1.0 (JSR-168) portlets, as the Portlet
2.0 (JSR-286) standard has also been designed to be
backwards-compatible. Theme plugins may require some modifications in
order to take advantage of new features. Much effort has been made to
make upgrades as painless as possible; however, this is not a guarantee
that everything will work without modification. Extension environment
code is the most complicating factor in an upgrade, so it is important
to test as much as possible.

As a general rule, you can upgrade from one major release to the next
major release. For example, you can upgrade directly from Liferay 5.1.x
to 5.2.x, but not from 5.1.x to 6.0.x. If you need to upgrade over
several major releases, you will need to run the upgrade procedure for
each major release until you reach the release you want. This doesn't
mean you need to run the procedure for every point release (i.e., 4.3.5
to 4.3.6 to 4.4.0 to 4.4.1, etc.); you only need to run the procedure
for the major releases. A good practice is to use the latest version of
each major release to upgrade your system.

### Liferay Upgrade Procedure

Liferay can auto-detect whether the database requires an upgrade the
first time the new version is started. When Liferay does this, it will
automatically upgrade the database to the format required by the new
version. In order to do this, Liferay *must* be accessing the database
with an ID that can create, drop, and modify tables. Make sure that you
have granted these permissions to the ID before you attempt to upgrade
Liferay. It is also a good idea to backup your database before
attempting an upgrade in case something goes wrong during the process.

![image](../../images/portal-admin-ch8_html_5c790363.png) **Tip:** Liferay versions
prior to 4.3.0 require that you manually run SQL scripts on your
database to perform an upgrade. If you need to upgrade from Liferay
4.1.x to 4.2.x in preparation for an upgrade to a current version of
Liferay, you can find these SQL scripts in the source code archive for
the version of Liferay you are running. They will be in the *SQL* folder
of the archive.

\
\

#### Upgrade Steps

It takes only five steps to upgrade a standard Liferay installation to
Liferay 6:

1.  Copy your customized `portal-ext.properties`{.western} file to a
    safe place, and then undeploy the old version of Liferay and shut
    down your application server.

2.  Copy the new versions of the dependency .jars to a location on your
    server's class path, overwriting the ones you already have for the
    old version of Liferay.

3.  Deploy the new Liferay .war file to your application server. Follow
    the deployment instructions in Chapter 2 .

4.  Modify your `portal-ext.properties     `{.western}file and set
    `permissions.user.check.algorithm=5`{.western}.

5.  Start (or restart) your application server. Watch the console as
    Liferay starts: it should upgrade the database automatically. Verify
    that your portal is operating normally, and then run the upgrade
    procedure to upgrade to permissions algorithm 6 (see below) and
    restart.

That's all there is to it. Everything else is handled by Liferay's
upgrade procedure. Note that as stated above, if you have to upgrade
over several Liferay versions, you will need to repeat these steps for
each major release. You can now deploy your own plugins to the system.

Once your upgrade is complete, you may wish to review the
`portal.properties`{.western} changes for this version of Liferay to see
whether the new defaults (see below) are appropriate for your
implementation.

What follows are instructions for upgrading for specific versions.

### Upgrading From Liferay 5.1 to Liferay 5.2

Always use the latest version of 5.2 available as it will have fixed all
the potential upgrade issues that may have been found.

#### Prerequisite

It's recommended to upgrade first at least to 5.1.2SE if you are running
any previous version.

#### Changes in configuration properties

##### How to keep the old values

The default values of some properties has been changed. In order to keep
the previous values you have to run Liferay passing the following system
property:

java ... -Dexternal-properties=portal-legacy-5.1.properties

Each application server has different methods to add this system
property. In Tomcat modify `setenv.sh/setenv.bat`{.western} and append
that option to the environment variable JAVA\_OPTS. The scripts
`setenv.sh`{.western} or `setenv.bat`{.western} are not delivered with
Tomcat but if they exist, Tomcat will use them in the startup process,
so it's a nice way to separate your own settings from tomcat's default
shell scripts.

Here are the complete contents of that file
(`portal-legacy-5.1.properties`{.western}) for reference:

resource.repositories.root=${user.home}/liferay

\
\

theme.portlet.sharing.default=true

\
\

organizations.country.required[regular]=true

organizations.assignment.auto=true

organizations.assignment.strict=false

organizations.membership.strict=true

\
\

lucene.dir=${resource.repositories.root}/lucene/

\
\

jcr.jackrabbit.repository.root=${resource.repositories.root}/jackrabbit

\
\

dl.hook.impl=com.liferay.documentlibrary.util.JCRHook

\
\

dl.hook.file.system.root.dir=${resource.repositories.root}/document\_library

##### Important changes in the configuration of Database access and mail
integration

One very important aspect of the upgrade is that now the configuration
of the database parameters and those for mail integration are handled
through the `portal-ext.properties`{.western} file to unify the
configuration through all application servers.

It's still possible to use application server specific data sources and
pools if desired by using certain configuration properties. This is
documented in Chapter 2.

#### Theme Upgrade

Instructions for maintaining customized themes built in 5.1 without
redeploying with the new SDK :

-   Change the header of `/WEB-INF/liferay-plugin-package.xml`{.western}
    to:

<!DOCTYPE plugin-package PUBLIC "-//Liferay//DTD Plugin Package
5.2.0//EN"
"http://www.liferay.com/dtd/liferay-plugin-package\_5\_2\_0.dtd"\>

-   Change the header of `/WEB-INF/liferay-look-and-feel.xml`{.western}
    to:

<!DOCTYPE look-and-feel PUBLIC "-//Liferay//DTD Look and Feel 5.2.0//EN"
"[http://www.liferay.com/dtd/liferay-look-and-feel\_5\_2\_0.dtd"\>]

-   Upgrade compatibility version in
    `liferay-look-and-feel.xml`{.western}:

<compatibility\>

<version\>5.2.2+</version\>

</compatibility\>

-   In `portal.vm`{.western}, delete the following lines :

$theme.include($bottom\_ext\_include)

$theme.include($session\_timeout\_include)

$theme.include($sound\_alerts\_include)

If you don't remove these, you will see a blank page and an exception.

-   In order to display the control panel in the dock, add the following
    lines in `dock.vm`{.western}:

\#if ($show\_control\_panel)

<li class="control-panel"\>

<a href="$control\_panel\_url"\>$control\_panel\_text</a\>

</li\>

\#end

-   In `navigation.css`{.western}:

.lfr-dock li.control-panel a {

background-image: url(../images/dock/control\_panel.png);

}

-   Then copy `/images/dock/control_panel.png`{.western} from the
    classic theme (`ROOT/html/themes/classic`{.western}) into your
    theme.

-   In `WEB-INF/web.xml`{.western}, change the deprecated declaration
    `com.liferay.portal.servlet.filters.compression.CompressionFilter`{.western}
    into `com.liferay.portal.servlet.filters.gzip.GZipFilter`{.western}.

#### API Changes

##### Usage of ServiceContext in Liferay's Services Layer

The most significant API change in 5.2 is that most APIs of the service
layer have been adapted to use the Service Context Pattern. The Service
Context is an object that contains context information about a given API
call. All of the fields in this object are optional, although the
services that store any type of content will require you to specify at
least the `scopeGroupId`{.western}. Here is a simple example of how to
create a `Ser`{.western}`viceContext`{.western} instance and pass it to
a service API:

ServiceContext serviceContext = new ServiceContext();

serviceContext.setScopeGroupId(myGroupId);

\
\

BlogsEntryServiceUtil.addEntry(...., serviceContext);

If you are invoking the service from a servlet, a Struts action, or any
other front end class which has access to the
`portletRequest`{.western}, you can use a utility method that will
create the `ServiceContext`{.western} object and fill it with all the
necessary values automatically. In that case the above example should be
rewritten as follows:

ServiceContext serviceContext =
ServiceContextFactory.getInstance(BlogsEntry.class.getName(),
portletRequest);

\
\

BlogsEntryServiceUtil.addEntry(...., serviceContext);

### Upgrading From Liferay 5.2 to Liferay 6.0

Always use the latest version of 6.0 available as it will have fixed all
the potential upgrade issues that may have been found.

#### Prerequisite

It's recommended to upgrade first at least to 5.2.3 CE if you are
running any previous version.

#### Upgrading Your Permissions Algorithm

Liferay 6.0 introduces permissions algorithm 6. Algorithm 6 is an
enhancement to our permissions system which drastically improves
performance by reducing the number of queries necessary to determine
permissions for any individual asset within Liferay portal.

Liferay 6 by default is configured to use algorithm 6. This is
appropriate for new installations, but requires additional configuration
for upgrades, because the table structure for this algorithm is
different from the structures for the previous algorithms. For this
reason, before you start Liferay 6 for the first time and run the
upgrade process, you need to tell Liferay 6 to use Algorithm 5, and then
run a separate conversion utility later, after you have verified that
the upgrade was successful. To temporarily switch your Liferay 6
installation to algorithm 5, add the following entry to your
`portal-ext.properties`{.western}:

permissions.user.check.algorithm=5

This will tell Liferay that you are still using algorithm 5. Next, start
Liferay and allow it to upgrade your database. Once the upgrade has
completed, verify that everything is working properly. You can now leave
the algorithm setting as is, or if you want to take advantage of the
performance of the new algorithm, you can upgrade to algorithm 6 by
going through a few simple steps in the Control Panel.

To upgrade to Algorithm 6, log in as an Administrator and navigate to
the Control Panel. Go to *Server Administration* and select *Data
Migration* from the menu along the top of the screen. You should see a
section entitled *Legacy Permissions Migration* at the bottom of the
page.

![image](../../images/portal-admin-ch8_html_m7242a796.png)\
*Illustration 4: Dialog to update your permissions algorithm to the
latest version.*

\
Algorithms 5 and 6 do not support adding permissions at the user level.
If you have permissions set to individual users, the converter can
simulate this for you by auto-generating roles with those permissions,
and assigning those roles to the users who have individualized
permissions. If you have a lot of these, you'll likely want to go
through and clean them up after the conversion process. To generate
these roles, check the *Generate Custom Roles* box. If you do not
generate the roles, all custom permissions set for individual users will
be discarded.
Click *Execute* to convert all existing users and roles to algorithm 6.

#### Upgrading EXT to EXT Plugins

With Liferay 6.0, the Ext environment no longer exists in its previous
form. Instead, Ext is now a plugin. If you are using the Ext Environment
to change core code, you will find that Ext Plugins handle core
customizations better than the Ext Environment did, so developers can
spend less time with deployments and maintaining changes than they did
previously. There is now only one SDK needed for Liferay development,
which also simplifies things for the developer.

Before using any code previously developed in Ext, you will need to
migrate your Ext environments to Ext plugins. If you are on a version of
Liferay prior to 5.2, you will also need to upgrade your existing Ext
environment to Liferay 5.2 before attempting to convert it to a plugin.
The upgrade process will not work on earlier versions of Liferay's Ext
environment.

In the Plugins SDK, under the `ext`{.western} directory you can find the
`build.xml`{.western} file to convert your existing Ext Environment to a
plugin. The script creates the shell of an Ext plugin with the
`ext.zip`{.western}* *file included with the Plugins SDK and merges your
existing Ext Environment into the new plugin.

To run the script, use the following command:

ant upgrade-ext -Dext.dir=[path to existing Ext] -Dext.name=[new plugin
name] -Dext.display.name=”[friendly name for new plugin]”

This will create a directory with the name you specified, with the
merged changes from your Ext Environment and the default .zip file. Both
`build-service`{.western} (Service Builder) and `build-db`{.western} (DB
Builder) have been ported to Ext Plugins to allow developers to
regenerate their services and SQL code in Ext Plugins. However, Service
Builder in Ext plugins will be deprecated in future versions, and custom
services should be migrated to portlet plugins. Try to migrate your
custom services to portlet plugins as soon as possible as this is the
recommended practice.

## Summary

Liferay Portal is an easy environment to maintain. Backup procedures are
simple and straightforward. Administrators have all the options they
need to view and diagnose a running Liferay Portal server through its
tunable logs.

Upgrading Liferay is also a snap, because Liferay does most of the work
automatically. With easy permissions migration tools and automated
database upgrade scripts, you'll have your new version of Liferay Portal
up and running in no time.

\
\

