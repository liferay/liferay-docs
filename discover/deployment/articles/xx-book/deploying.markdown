#  Installation and Setup [](id=installation-and-setup)

Liferay Portal is one of the most flexible applications on the market today with
regard to application server environments. It supports a wide variety of
application servers, freeing you to use the Java application server you think is
best. Liferay also scales very well: you can install Liferay Portal on
everything from a shared hosting account to a multi-node cluster running a
commercial application server and on everything in between. In fact, Liferay is
used successfully in all of these scenarios every day.

You'll find that because Liferay is extremely flexible in its deployment
options, it is also easy to install. If you already have an application
server, you can use the tools for deployment that came with your application
server. If you don't have an application server, Liferay provides several
application server bundles from which to choose. These are pre-configured
application servers with Liferay already installed in them. They're very easy to
install and with a small amount of configuration can be made into
production-ready systems.

The installation steps vary slightly according to your Liferay edition, so we'll
be sure to let you know when they are different. 

# Editions of Liferay [](id=editions-of-liferay)

Liferay ships in two editions: one for the community and one for enterprise
subscribers. The community edition is the same Liferay Portal that has been
available for years: frequently updated and bursting with the latest features,
the Community Edition of Liferay Portal is offered for free under the Lesser GNU
public license, a free software license with one important exception. This
license gives you the flexibility to link Liferay with your own code in your
portlet, theme, hook, layout, Ext or web plugins, no matter what license you use
for your code. If, however, you modify Liferay directly, those modifications
need to be released as free software under the terms of the license. The best
way, of course, to do this is to contribute it back to the Liferay community.
This is really the best of both worlds: you have the freedom use any license (or
no license) if you use plugins, but if you modify Liferay directly, the
community receives the benefits of any enhancements that you've made. 

Liferay for enterprise subscribers is a supported version of Liferay Portal for
the enterprise. The subscription and support package allows organizations to
build their portals on a stable version of the product that is offered over an
extended period of time. It's the best wayfor you to develop, deploy, and
maintain your Liferay solution. It includes 

- Liferay support
- Updates
- Fix Packs
- Cloud Services
- Liferay Portal Enterprise Edition 

Now let's learn how to get a copy of Liferay Portal.

# Obtaining Liferay Portal [](id=obtaining-liferay-portal)

The Liferay community can download Liferay Portal from our web site at
[http://www.liferay.com](http://www.liferay.com). Click the *Downloads* link at
the top of the page, and you'll see multiple options for getting a copy of
Liferay, including our convenient bundles or a `.war` package for installation
on your application server of choice.

Liferay enterprise subscribers can download Liferay from the Customer Portal.
You have a choice of the same open source app server bundles as community
members, plus a few commercial alternatives, in addition to the `.war` package
for manual installation. 

So what is a bundle anyway? A bundle is an application server with Liferay
preinstalled. This is the most convenient way to install Liferay. Liferay is
bundled with a number of application servers; all you need to do is choose the
one that best fits your needs. If you don't currently have an application
server, you may want to start with the Tomcat bundle, as Tomcat is one of the
smallest and most straightforward bundles to configure. If you have an open
source application server preference, choose the server you prefer from the
available Liferay Portal bundles. All the bundles ship with a Java Runtime
Environment for Windows; if you are using a different operating system, you must
have a JDK (Java Development Kit) installed prior to launching Liferay Portal.

Please note that Liferay is not able to provide application server bundles for
proprietary application servers such as WebLogic or WebSphere, because the
licenses for these servers don't allow for redistribution. Liferay Portal,
however, runs just as well on these application servers as it does on the
others. A `.war` file and dependency `.jar`s are provided for proprietary
application servers, and you'll need to follow a procedure to install Liferay on
them. 

Once you have Liferay, you can then plan out your installation. This is a
two-step process: first, determine if you need Liferay Portal Security turned
on, and second, install Liferay Portal, by using a bundle or by installing it
manually on your existing app server. Next, we'll go over the steps it takes to
install Liferay Portal.

# Liferay installation overview [](id=liferay-installation-overview)

Before we begin, it's important to go over the various facets of the
installation. They are: 

1. Create your database. 

2. Determine whether you want to use the Liferay managed data source or a data
   source managed by your application server. The Liferay managed data source is
   recommended. 

3. Gather credentials for sending email notifications to users. Liferay supports
   a JNDI mail session as well as its built-in mail session. 

4. Install a Liferay bundle or on an existing application server according to
   the instructions for your application server (see below). 

5. Determine whether you'll use Marketplace or other third party applications.
   If you will, you should enable Liferay's Plugin Access Control List (PACL). 

We'll go through the steps in order, so first we'll look at the Liferay
database.

# Liferay's database [](id=liferays-database)

The recommended way of setting up your Liferay database also happens to be the
simplest. Liferay Portal takes care of just about everything. The only thing you
need to do is create a blank database encoded for character set UTF-8. The
reason for this is that Liferay is a multilingual application, and needs UTF-8
encoding to display all the character sets it supports. 

Next, create an ID for accessing this database and grant it all
rights--including the rights to create and drop tables--to the blank Liferay
database. This is the ID you'll use to connect to the Liferay database, and
you'll configure it later either in your application server or in Liferay's
properties file so that Liferay can connect to it. 

One of the first things Liferay Portal does when you bring it up for the first
time is create the tables it needs in the database you just created. It does
this automatically, complete with indexes. 

If you create your database and grant a user ID full access to it, Liferay can
use that user ID to create its indexes and tables automatically.  This is the
recommended way to set up Liferay, as it allows you to take advantage of
Liferay's ability to maintain its database automatically during upgrades or
through various plugin installs that create tables of their own. It is by far
the best way to set up your Liferay installation.

If you'll be setting up Liferay's database with the recommended permissions, you
can skip to the next section.

+$$$

**Note:** The below instructions are not the
recommended set up for Liferay installations, but the procedure is documented
here so enterprises with more restrictive standards can install Liferay with
more strict--but suboptimal--database settings. If it's at all possible,
Liferay recommends that you use the automatic method as documented above instead
of the procedure outlined below.

$$$

Even though Liferay can create its database automatically, some enterprises
prefer *not* to allow the user ID configured in an application server to have
the permissions over the database necessary for Liferay and its plugins to
maintain their tables. For these organizations, Select, Insert, Update and
Delete are the only permissions allowed so we will go over how to set up the
database manually. If your organization *is* willing to grant the Liferay user
ID permissions to create and drop tables in the database--and this is the
recommended configuration--by all means, use the recommended configuration. 

Creating the database is simple: grant the ID Liferay uses to access the
database full rights to do anything to the database. Then install Liferay and
have it create the database. Once the database is created, remove the
permissions for creating tables and dropping tables from the user ID.

There are some caveats to running Liferay like this. Many Liferay plugins create
new tables when they're deployed. In addition to this, Liferay has an automatic
database upgrade function that runs when Liferay is upgraded. If the user ID
that accesses the database doesn't have enough rights to create/modify/drop
tables in the database, you must grant those rights to the ID before you deploy
one of these plugins or start your upgraded Liferay for the first time. Once the
tables are created or the upgrade is complete, you can remove those rights until
the next deploy or upgrade. Additionally, your developers may create plugins
that need to create their own tables. These are just like Liferay's plugins that
do the same thing, and they cannot be installed if Liferay can't create these
tables automatically. If you wish to install these plugins, you will need to
grant rights to create tables in the database before you attempt to install
them. 

Once you have your database ready, you can install Liferay on your server.

# Liferay Marketplace [](id=liferay-marketplace)

The Liferay Marketplace is an integral part of the Liferay Portal experience.
Starting with Liferay Portal 6.2, the Marketplace plugin is required to be
installed alongside Liferay Portal. The Marketplace plugin enables a host of
features that extend beyond just access to the online Liferay Marketplace. Some
of the key features the Marketplace plugin enables are:

- Liferay Marketplace:  direct access to our online Marketplace
- App Manager: ability to install, uninstall, and update apps
- Bundled Apps: easily manage apps that may come bundled with your Liferay
  Portal 
- Developer Apps: ability to manage apps that you’re developing
- License Manager: streamlined license management for your Liferay Portal and
  apps

The portal installation process attempts to deploy and register the Marketplace
plugin automatically. If your environment supports/allows 1) hot deploy and 2)
full database rights, the automatic deploy process takes care of itself.
Many companies (especially in a production environment), however, limit
automated processes and/or database access. Additionally, certain application
servers (eg., WebSphere) do not support hot deploy, so you may need to 
deploy the Marketplace plugin manually. Depending on your environment’s
restrictions, you may need to follow one or more of the steps below to 
install the Marketplace plugin properly. 

## Server is Firewalled without Access to the Internet [](id=server-is-firewalled-without-access-to-the-internet)

Your server may be behind a firewall that prevents access to the Internet, or
your security policy may not allow direct download and installation from the
Internet. In these cases, you have 2 options:

1. From an Internet-enabled computer, download the Marketplace plugin from
[here](https://www.liferay.com/marketplace/download).
Then allow Liferay to auto deploy it by dropping the downloaded .lpkg file into
the Liferay deploy folder. 

2. From an Internet-enabled computer, download the Marketplace plugin. Then use
the Liferay App Manager to deploy the plugin.

Detailed instructions can be found under [Installing Plugins Manually](https://www.liferay.com/documentation/liferay-portal/6.2/user-guide/-/ai/plugin-management-liferay-portal-6-2-user-guide-14-en). 

## Application Server Does Not Support Hot Deploy [](id=application-server-does-not-support-hot-deploy)

If your application server does not support hot deploy, you can't leverage
Liferay’s auto deploy feature. You can, however, manually deploy the
plugin in two steps:

1. Use Liferay's tools to pre-deploy the file.

2. Then use your app server's tools to do the actual deployment.

This is because Liferay makes deployment-time modifications to the plugins right
before they are actually deployed to the application server. Detailed
instructions can be found under [Deploy Issues for Specific Containers](https://www.liferay.com/documentation/liferay-portal/6.2/user-guide/-/ai/plugin-management-liferay-portal-6-2-user-guide-14-en). 

## Limited Database Access [](id=limited-database-access)

Some production environments do not have the necessary database permissions for
Liferay and its plugins to maintain their tables. In these cases:

1. Grant the ID Liferay uses to access the database temporary full rights to the
   database. 

2. Install Liferay and have it create the database. 

3. Once the database is created, remove the permissions for creating tables and
   dropping tables from the user ID.

Detailed instructions are available
[here](https://www.liferay.com/documentation/liferay-portal/6.2/user-guide/-/ai/liferays-database-liferay-portal-6-2-user-guide-15-en).
It should be noted that most sophisticated Liferay apps--not just the
Marketplace plugin--require new tables when deployed. If your environment
restricts database access, you may need to repeat the above steps whenever you
deploy a new app to the Liferay Portal.

# Liferay Home [](id=liferay-home)

Liferay Portal uses a special folder defined as *Liferay Home*. This folder is
usually one folder higher than the location of the application server itself.
This is why the bundles place the application server one folder in from the
bundle's root folder.

If Liferay is unable to create the resources it needs in this folder or if it
finds itself running on certain application servers, it creates a folder called
`liferay` in the home folder of the user ID that is running Liferay, and that
becomes Liferay Home.

The home folder is important to the operation of Liferay. The aforementioned
folders (`data` and `deploy`) are created there, and Liferay's properties
configuration files are also stored there. 

We recommend using the setup wizard to configure your database and mail sessions
when you first configure Liferay. This creates a
`portal-setup-wizard.properties` file where all the settings from the setup
wizard are stored. When you begin customizing your portal's configuration, we
recommend you create a separate properties file for that, called
`portal-ext.properties`. This allows you to keep your server configuration
properties separate from core properties like your database and mail server
configurations.  All the possible properties that can
be placed in this file are documented in [our reference documentation](http://docs.liferay.com/portal/6.2/propertiesdoc). 

+$$$

**Note:** To avoid using the setup wizard so you
can configure everything manually from a `portal-ext.properties` file, you must
disable the Setup Wizard by specifying `setup.wizard.enabled=false` in the
`portal-ext.properties`. Also, note that property values in
`portal-setup-wizard.properties` (the file created in Liferay Home by the Setup
Wizard) override property values in `portal-ext.properties`. 

$$$

Let's move on and discuss Liferay Portal security.

# Configuring Liferay Portal Security [](id=configuring-liferay-portal-security)

As you probably know, Liferay Marketplace is an online store for obtaining
applications that run on the Liferay Portal platform. These applications are
provided not only by Liferay, but also by partners and independent developers
who want you to install and use their applications on your server. Many of these
applications are excellent, and we recommend that you try them out for yourself. 

Because many of the applications on Marketplace are *not* provided by Liferay,
however, there's an issue of trust: how do you know these applications are doing
what they're advertised to do? There is a vetting process that they go through
before they're allowed on Marketplace, but if the source code is not provided,
there's no way for even Liferay to know if an app has been properly represented.
For this reason, Liferay Portal implements a security layer we call the Portal
Access Control List, or PACL. 

PACL forces an application to declare up front the functions from Liferay's APIs
that it calls. Anything that's not declared is not allowed to run. It's similar
to what you might see on your mobile phone when you install an app: you get to
see the Liferay API functions the app uses, and then you can decide if you want
to install that app based on the permissions it requires. This way, you see
right away what portal data that app can access, and the app can do nothing
else: you're protected--if you have PACL enabled. So if you plan to use apps
downloaded from Marketplace, it's important to make sure PACL is enabled. 

By default, Liferay's bundles have PACL turned off. The reason for this is that
there is a small performance penalty for having PACL enabled. Since the only
reason to have PACL enabled is to install untrusted third party apps from
Marketplace (and not everybody does that), we decided to leave PACL turned off
by default, so that your portal performs as fast as possible. 

All of this is to say: if you intend to use Marketplace apps, please enable
PACL. Later in this chapter, there are sections for all the app servers Liferay
supports. Each of those sections has a subsection that explains how to enable
Java security for that app server, which is a prerequisite for enabling PACL.
Once you have Java security enabled, PACL can be enabled by adding one line to
your `portal-ext.properties` or `portal-setup-wizard.properties` file: 

    portal.security.manager.strategy=liferay

Save the file and if Liferay is running, restart it. Your portal is now
configured to check PACL-enabled Marketplace apps against their declared
permissions. 

Next, you'll make sure Liferay is configured properly for your network. 

# Choosing IPv4 or IPv6 [](id=choosing-ipv4-or-ipv6)

Liferay Portal supports both the IPv4 and IPv6 address formats, though by
default, Liferay uses IPv4 addresses. If you're on an IPv6 network, you'll
need to change the configuration. If you'd like more information on the basics
of these protocols, you can check out the
[reason](http://www.google.com/intl/en/ipv6/) for using IPv6 addresses, and its
[technical details](http://en.wikipedia.org/wiki/IPv6).

To configure your portal to validate IPv6 addresses, you must complete a few
simple steps. First, assuming you're using the Tomcat app server for your
portal, navigate to the `${TOMCAT_HOME}/bin/setenv.bat` file and set
`-Djava.net.preferIPv4Stack=false` in `CATALINA_OPTS`. Then, create a
`portal-ext.properties` file in your portal's home directory (if necessary) and
set the `tunnel.servlet.hosts.allowed` property to the target hosts you want to
allow (e.g., *0:0:0:0:0:0:0:1*). 

Now that you understand all the prerequisites for installing Liferay Portal,
let's go ahead and get it done! First we'll go over installing Liferay from a
bundle, and after this we'll provide instructions for installing Liferay manually
on all the application servers it supports. 

# Installing a bundle [](id=installing-a-bundle)

Liferay bundles contain the same directory structure regardless of application
server. The top-level folder is named for the release of Liferay. This folder is
called *Liferay Home*, and we refer to it thoughout this documentation. 

![Figure 1.1: Bundle directory structure](../../images/02-bundle-directory-structure.png) 

Inside this folder, there are folders for various purposes:

**Data:** This folder is used to store the embedded HSQL database the bundles
use, as well as the configuration and data for the Jackrabbit JSR-170 content
repository and the Lucene search index. It also contains the base directory
where the OSGi framework can persist any of its operating files. 

**Deploy:** Plugins that can be copied into this folder, and Liferay then
deploys them. It is also used by Liferay Marketplace and Liferay's graphical
plugin installer utility. 

**[Application Server]:** The name of this folder is different depending on
the bundle you've downloaded. This folder contains the application server in
which Liferay has been installed.

In addition to Liferay Portal itself, bundles are shipped with a number of
plugins already installed: 

- [kaleo-web:](https://www.liferay.com/documentation/liferay-portal/6.2/user-guide/-/ai/using-workflow-liferay-portal-6-2-user-guide-11-en) Liferay's workflow engine

- [marketplace:](https://www.liferay.com/documentation/liferay-portal/6.2/user-guide/-/ai/leveraging-the-liferay-marketplace-liferay-portal-6-2-user-guide-14-en) Interface to Liferay Marketplace

- [notifications:](https://www.liferay.com/documentation/liferay-portal/6.2/user-guide/-/ai/touring-liferay-portals-user-interface-liferay-portal-6-2-user-guide-02-en) App that provides in-browser notifications for users

- [opensocial:](https://www.liferay.com/documentation/liferay-portal/6.2/development/-/ai/creating-and-integrating-with-opensocial-liferay-portal-6-2-dev-guide-08-en) Allows deploying OpenSocial gadgets to Liferay Portal

- [resources-importer-web:](https://www.liferay.com/documentation/liferay-portal/6.2/development/-/ai/importing-resources-with-your-themes-liferay-portal-6-2-dev-guide-09-en) Imports theme resources with theme plugins

- [sync-web:](https://www.liferay.com/documentation/liferay-portal/6.2/user-guide/-/ai/liferay-sync-liferay-portal-6-2-user-guide-05-en) Allows Liferay Sync to connect to this installation to keep
  Documents and Media folders synchronized. You must have this plugin installed
if you want to use Liferay Sync Mobile or Desktop. 

- web-forms: App that allows users to create forms users can fill out. The
  results are then emailed to a specified email address. 

- welcome-theme: A default website that gives new users a tour of what Liferay
  Portal has to offer. 

- [calendar:](https://www.liferay.com/documentation/liferay-portal/6.2/user-guide/-/ai/managing-events-and-calendar-resources-with-liferay-rsquo-s-calendar) Liferay's calendar application. This used to be built-in, and is now
  a plugin, so it's included by default.

If you are an enterprise subscriber, the welcome theme is not included, and you
receive a few more plugins: 

- [kaleo-designer-portlet:](https://www.liferay.com/documentation/liferay-portal/6.2/user-guide/-/ai/kaleo-designer-liferay-portal-6-2-user-guide-12-en) Subscriber plugin that offers a graphical interface
  for creating Kaleo workflows. 

- [kaleo-forms-portlet:](https://www.liferay.com/documentation/liferay-portal/6.2/user-guide/-/ai/make-it-pretty-creating-custom-displays-liferay-portal-6-2-user-guide-10-en) Subscriber plugin that allows attaching forms to
  workflows. 

Getting a Liferay bundle up and running is as easy as uncompressing the archive,
copying a JDBC driver, and then starting the application server. Let's use the
Tomcat bundle as an example:

1. Unzip the bundle to a location of your choice. 

2. If you're setting up Liferay to be an actual server, copy your database's
   JDBC driver `.jar` to `[Tomcat]/lib/ext` (see the setup wizard section
   below). If you're setting up Liferay for demo purposes, you can skip this
   step. 

3. Start Tomcat in the same way you would if you had downloaded it manually.
   Tomcat is launched by way of a script which is found in its `bin` folder. If you
   drop to a command prompt and go to this folder, you can launch Tomcat via the
   following command on Windows:

    startup

   or the following command on Linux/Mac/Unix:

    ./startup.sh

The Liferay/Tomcat bundle then launches. If you are on Windows, another command
prompt window appears with Tomcat's console in it. If you are on Linux, you can
see the Tomcat console by issuing the following command:

    tail -f ../logs/catalina.out

Once Tomcat has started, it automatically launches a web browser that displays
Liferay's setup wizard. If for some reason your browser doesn't load the wizard,
launch your web browser and then go to
[http://localhost:8080](http://localhost:8080). 

Liferay CE ships with a sample web site that showcases Liferay's features. It
contains many links describing the features of Liferay that we cover in detail
throughout this book. 

If you're installing Liferay on your own machine to explore its features, you
likely want to leave the sample site there so you can examine it. If, however,
you're installing Liferay on your server to run your own site, it's best to
start with a clean system. Before running the setup wizard, you should remove
the sample data from your Liferay installation. You must do this before
running the setup wizard to get a clean database, and it's as simple as
undeploying the application that installs the sample data.

There is one application included in the bundle that you need to remove:

- welcome-theme

To remove it, all you have to do is undeploy it. The method for doing this
differs by application server and that, of course, depends on the bundle you
have chosen. For example, on Tomcat you delete the application folder from the
`[Tomcat Home]/webapps` folder. On GlassFish, you use the administrative
console. 

If you forget to undeploy the sample application before you run through the
setup wizard and connect Liferay to your real database, the sample data is
created in your database, and there isn't an easy way to clean it out. Make sure
you get the sample data undeployed before setting up your server, or you'll have
to drop your database and re-create it. That's not such a bad thing to have to
do, since it's a brand new database. If you don't have a brand new database,
you're working with an existing installation of Liferay, and you should follow
the instructions on upgrading in chapter 18 instead of what's described here. 

If you're a Liferay Portal Enterprise subscriber, you don't have the sample
site, so you don't need to worry about this. The next step is to run through the
setup wizard, which we'll cover below. 

As you can see, bundles are the easiest way to get started with Liferay. They
come preconfigured with a running Liferay instance that can be used immediately
to explore all the things that Liferay can do. Bundles are the fastest way to
create full production-ready Liferay installations. If you're using a bundle,
skip to the section on the setup wizard below to continue your installation. 

Of course, it's not always possible to use a bundle. You may already have an
application server where Liferay should be installed. The bulk of this
chapter describes how to install Liferay on all the application servers it
supports, both open source and proprietary.

# App Servers [](id=app-servers)

When it comes time to install Liferay Portal on your server, you'll find it's
easiest to do this by starting with a bundle. But many organizations can't do
that. There may be an existing infrastructure into which you're installing
Liferay, or you may have standardized on a particular application server. You'll
be happy to know that Liferay Portal has been designed to work well with all the
leading application servers and that it's easy and straightforward to install.
But before we get started, we need to go over a few concepts; namely, data
sources and mail sessions. These were touched on in the section on bundles above
but we'll look at them in more detail now. 

## Using data sources [](id=using-data-sources)

Liferay comes bundled with its own built-in data source. It's configured by a
number of properties that are set in a properties file. By default, the setup
wizard asks you for the necessary values and creates a configuration file that
uses the built-in data source to connect to the database. 

Liferay always recommends that you use the built-in data source. Sometimes,
however, organizations prefer to use the data source provided by their
application server of choice. In this instance, a JNDI lookup provides a handle
to the data source and the application server manages the connection pools.
Liferay supports using your application server's data source if you wish to do
that. 

To do this, you'll need to create your own configuration file and skip the setup
wizard. Since you'd be creating this file *after* the wizard anyway, this isn't
such a big deal. We show you how to do that after all the sections on
application servers. 

Since mail sessions are configured in a similar way to data sources, we'll look
at them next. 

## Using mail sessions [](id=using-mail-sessions)

Liferay's default configuration looks for a mail server on the same machine on
which Liferay's running and it tries to send mail via SMTP to this server. If
this is not your configuration, you'll need to modify Liferay's defaults. To do
this, you'll use a `portal-ext.properties` file (see below). 

In a similar fashion to databases, you have two ways to configure your mail
server:

-   Use your application server's mail session.

-   Use the built-in mail session.

To use your application server's mail session, you must create it in your
application server and it should point to your mail server. Once you've done
that, you're ready to point Liferay to it. You can do this through the
configuration file or through Liferay's control panel after it's been installed. 

You now have all the background information you need. Next you need to make your
decision: will you use Liferay's built-in data source, or the one provided by
your application server? If you're planning to use the one provided by your
server, you can't use Liferay's installation wizard, and you'll have to follow
the instructions in the section below titled Manual Configuration. 

In either case, your next step is to install Liferay onto your application
server. What follows are sections for every application server that Liferay
supports. Once you have Liferay Portal installed, the first thing you see is the
setup wizard. To continue, follow the instructions for setting up Liferay on
your application server, and then skip down to the section on the setup wizard. 

Next, follow the instructions for installing Liferay on your particular
application server in the section below.

# Installing Liferay on an existing application server [](id=installing-liferay-on-an-existing-application-server)

This section contains detailed instructions for installing Liferay Portal using
its .war file distribution. This allows system administrators to deploy Liferay
in existing application server installations. It is recommended that you have a
good understanding of how to deploy Java EE applications in your application
server of choice.

## Installing Liferay in five easy steps [](id=installing-liferay-in-five-easy-steps)

There are five generic steps to installing Liferay on an existing application
server:

1. Obtain the Liferay `.war` file and the dependencies archive.

2. Shut your application server down.

3. Extract the dependencies to a location on your server's global classpath.
   This allows both Liferay and plugins to access these dependencies.

4. Start your application server. 

5. Deploy the Liferay `.war` file.

The instructions below are specific for each application server that Liferay
supports. Liferay supports a wide combination of application servers and
databases. Because of this, this section assumes MySQL as the database, that the
database has already been created, and that you're using the setup wizard. If
you're not using the setup wizard, see the sections above for information on how
to set up Liferay manually.

We also assume your application server is already installed and running
successfully. If you still need to install your application server, please
follow your vendor's instructions first.

Since Liferay uses the UTF-8 character encoding, make sure that your application
server has the Java `-Dfile.encoding=UTF-8` parameter set before you proceed. Note
that different application servers provide different means for setting this
parameter. For example, in Tomcat this parameter goes in the `setenv.sh` or
`setenv.bat` script. For other application servers, this parameter might go in a
different script or might have to be set through an administration console.

The following instructions assume an installation on a local machine. When
installing to a remote server, substitute `localhost` with the host name or IP
of the server.

+$$$

**Tip:** Note that Liferay *requires* JDK 6 or
greater. Do not attempt to install Liferay 6.2 on an application server that
runs under Java 5 or lower; it will not work. If you are running an
application server that ships with a JDK and that JDK is version 5 or lower,
you'll need to upgrade your application server to run current versions of
Liferay Portal.

$$$

Without further ado, let's get to the application servers. The first one we'll
cover is Mule Tcat. If you don't have an application server preference, and you
want support all the way down to the application server from Liferay, then Mule
Tcat is your solution. After we cover Mule Tcat, we'll look at all the supported
application servers in alphabetical order.

# Installing Liferay on Mulesoft Tcat [](id=installing-liferay-on-mulesoft-tcat)

For this section, we will refer to your Tcat server's installation location as
`[TCAT_HOME]`. If you don't already have an existing Tcat server, we
recommend you download a Liferay/Tcat bundle from
[http://www.liferay.com/downloads/liferay-portal/available-releases](http://www.liferay.com/downloads/liferay-portal/available-releases).
If you have an existing Tcat server on which you'd like to deploy Liferay
manually, please follow the steps below.

Your first step is to download the latest Liferay `.war` file and Liferay
Portal dependencies from
[http://www.liferay.com/downloads/liferay-portal/additional-files](http://www.liferay.com/downloads/liferay-portal/additional-files).
The Liferay `.war` file should be called `liferay-portal-6.2.x-[date].war` and
the dependencies file should be called
`liferay-portal-dependencies-6.2.x-[date].zip`.

Next, let's get started by addressing Liferay's library dependencies.

## Dependency Jars [](id=dependency-jars)

To run Liferay Portal on your Tcat server, you first need to make some JAR files
available on Tcat's global classpath. These include the Liferay Dependency JARs,
a JDBC driver for your database, and some other dependencies that Liferay Portal
requires. 

1. Create the folder `[TCAT_HOME]/lib/ext`.

2. Extract the Liferay dependencies file and copy the `.jars` to `[TCAT_HOME]/lib/ext`. 

3. Next, you need a few `.jar` files that are included as part of the
   Liferay Tcat bundle, but are not included with Tcat.  You'll have to download
   them yourself, so let's get started. Place these `.jar` files into
   `$TCAT_HOME/lib/ext`:

   - `jta.jar`: Support for Java transactions. You can get this `.jar`, which manages transactions, from
            [http://www.oracle.com/technetwork/java/javaee/jta/index.html](http://www.oracle.com/technetwork/java/javaee/jta/index.html)

   - `mail.jar`: Support for the Java Mail API. You can get this `.jar` from
            [http://www.oracle.com/technetwork/java/index-138643.html](http://www.oracle.com/technetwork/java/index-138643.html)

   - `persistence.jar`: Support for the Java Persistence API. You can get this
      `.jar` from
            [http://www.oracle.com/technetwork/java/javaee/tech/persistence-jsp-140049.html](http://www.oracle.com/technetwork/java/javaee/tech/persistence-jsp-140049.html)

   - `activation.jar`: This is an implementation of the Java Activation
      Framework. You can get this `.jar` from
        [http://www.oracle.com/technetwork/java/jaf11-139815.html](http://www.oracle.com/technetwork/java/jaf11-139815.html)

   - `ccpp.jar`: Enables Composite Capability/Preference Profiles. You can get this `.jar` from 
        [http://mvnrepository.com/artifact/javax.ccpp/ccpp/1.0](http://mvnrepository.com/artifact/javax.ccpp/ccpp/1.0)

   - `jms.jar`: The Java Messaging Service. You can get this `.jar` from
        [http://www.oracle.com/technetwork/java/docs-136352.html](http://www.oracle.com/technetwork/java/docs-136352.html)

   - `jutf7.jar`: Provides UTF-7 and Modified UTF-7 charsets for Java. You can get this `.jar` from 
          [http://sourceforge.net/projects/jutf7/](http://sourceforge.net/projects/jutf7/)

   - `junit.jar`: Optional: lets you run unit tests. You can get this `.jar` from 
        [http://sourceforge.net/projects/junit/](http://sourceforge.net/projects/junit/)

   Although you can get each `.jar` listed above separately, it may be more
   convenient to get them by downloading the Liferay source code and copying them
   from there. Once you have downloaded the Liferay source, unzip the source
   into a temporary folder. You can find the `.jar` files in
   `[LIFERAY_SOURCE]/lib/development`. 

4. Make sure the JDBC driver for your database is accessible by Tomcat. Obtain
   the JDBC driver for your version of the database server. In the case of
   MySQL, use `mysql-connector-java-[version]-bin.jar`. You can download the
   latest MySQL JDBC driver from
   [http://dev.mysql.com/downloads/connector/j/](http://dev.mysql.com/downloads/connector/j/).
   Extract the JAR file and copy it to `[TCAT_HOME]/lib/ext`.

Now that you have the necessary libraries in place, we'll move on to configuring
your domain.

## Tcat Configuration [](id=tcat-configuration)

If you're installing Liferay Portal onto an existing Tcat server, you should be
familiar with the Tcat Administration Console. The following instructions assume
you have a Tcat server with the Administration Console and a separate, managed
Tcat server instance where you'll deploy Liferay. To find information specific
to Tcat server installation and management, see [Mulesoft's Tcat Documentation](http://www.mulesoft.org/documentation/display/TCAT/Home). You have to do a few things to configure your managed Tcat server instance: 

- Set environment variables

- Create a context for your web application

- Modify the list of classes/JARs to be loaded

- Specify URI encoding

![Figure 1.2: You can log in to the Tcat Administration Console to manage your Tcat servers.](../../images/15-tcat-sign-in.png)

Next, you'll configure your managed Tcat instance. 

1. To set the `CATALINA_OPTS` environment variable, you need to add a server
profile. In the Tcat Administration Console, navigate to the *Administration*
tab and click *Server Profiles*. Name the profile appropriately (*Liferay 6.2*,
perhaps), provide a description if you wish, select the workspace where you'll
keep your profile (`/Profiles` is a logical choice), and click the *Add
Variable* button. Name it `CATALINA_OPTS` and give it the following value: 

        -Dfile.encoding=UTF8 -Dorg.apache.catalina.loader.WebappClassLoader.ENABLE_CLEAR_REFERENCES=false -Duser.timezone=GMT -Xmx1024m -XX:MaxPermSize=256m"

This sets the character encoding to UTF-8, sets the time zone to Greenwich
Mean Time and allocates memory to the Java virtual machine.

Apply the profile to the Tcat server where you're deploying Liferay. To do so,
go to the *Servers* tab, select the desired server, and select your profile from
the *Set Profile* dropdown menu. 

2. Create a file locally called `ROOT.xml`: 

    <Context path="" crossContext="true">

    <!-- JAAS -->

    <!--<Realm
        classNjame="org.apache.catalina.realm.JAASRealm"
        appName="PortalRealm"
        userClassNames="com.liferay.portal.kernel.security.jaas.PortalPrincipal"
        roleClassNames="com.liferay.portal.kernel.security.jaas.PortalRole"
    />-->

    <!--
    Uncomment the following to disable persistent sessions across reboots.
    -->

    <!--<Manager pathname="" />-->

    <!--
    Uncomment the following to not use sessions. See the property
    "session.disabled" in portal.properties.
    -->

    <!--<Manager className="com.liferay.support.tomcat.session.SessionLessManagerBase" />-->
    </Context>

<!-- Why do we have all that stuff up there commmented out? Wouldn't it be
better not to include it at all? -Rich -->

Click your server's name to edit its configuration. Using the *Files* tab,
navigate to the directory `[TCAT_HOME]/conf/Catalina/localhost` and upload your
ROOT.xml file.  Setting `crossContext="true"` allows multiple web apps to use the same class
loader. In the content above you will also find commented instructions and
tags for configuring a JAAS realm, disabling persistent sessions and
disabling sessions in general.

3. Still in your server's *Files* tab, open
`[TCAT_HOME]/conf/catalina.properties`, click the *Edit catalina.properties*
link, and replace the line:

        common.loader=${catalina.base}/lib,${catalina.base}/lib/*.jar,${catalina.home}/lib,${catalina.home}/lib/*.jar

with:

        common.loader=${catalina.base}/lib,${catalina.base}/lib/*.jar,${catalina.home}/lib,${catalina.home}/lib/*.jar,${catalina.home}/lib/ext,${catalina.home}/lib/ext/*.jar

This allows Catalina to access the dependency jars you extracted to
`[TCAT_HOME]/lib/ext`.

4. To ensure consistent use of UTF-8 URI Encoding, edit
   `[TCAT_HOME]/conf/server.xml` and add the attribute `URIEncoding="UTF-8"`
   where you see `redirectPort=8443`, in the definition of your connectors (HTTP
   and AJP). For example:

    <Connector port="8080" protocol="HTTP/1.1" connectionTimeout="20000" redirectPort="8443" URIEncoding="UTF-8" />

![Figure 1.3: You can edit your Tcat configuration files in the Administration Console.](../../images/15-tcat-server-files.png)

Excellent work! Now it's time to configure your database.

## Database Configuration [](id=database-configuration)

If you want Tcat to manage your data source, use the following procedure. If
you want to use Liferay's built-in data source, you can skip this section.

1. Make sure your database server is installed and working. If it's installed on
   a different machine, make sure it's accessible from your Liferay machine.

2. Using the Tcat Administration Console, add your data source as a resource in
the context of your web application specified in
`[TCAT_HOME]/conf/Catalina/localhost/ROOT.xml`:

        <Context...>
            <Resource
                name="jdbc/LiferayPool"
                auth="Container"
                type="javax.sql.DataSource"
                driverClassName="com.mysql.jdbc.Driver"
                url="jdbc:mysql://localhost/lportal?useUnicode=true&amp;characterEncoding=UTF-8"
                username="root"
                password="root"
                maxActive="100"
                maxIdle="30"
                maxWait="10000"
            />
        </Context>
 
Note the above resource definition assumes your database name is *lportal*
and your MySQL username and password are both *root*. You'll have to update
these values with your own database name and credentials.

Your Tcat managed data source is now configured. Let's move on to your mail
session.

## Mail Configuration [](id=mail-configuration)

If you want to manage your mail session within Tomcat, use the following
instructions. If you want to use the built-in Liferay mail session, you can skip
this section.

Create a mail session bound to `mail/MailSession`. In the Tcat Administration
Console, edit `[TCAT_ HOME]/conf/Catalina/localhost/ROOT.xml` and configure a
mail session. Be sure to replace the mail session values with your own.

    <Context...>
        <Resource
            name="mail/MailSession"
            auth="Container"
            type="javax.mail.Session"
            mail.pop3.host="pop.gmail.com"
            mail.pop3.port="110"
            mail.smtp.host="smtp.gmail.com"
            mail.smtp.port="465"
            mail.smtp.user="user"
            mail.smtp.password="password"
            mail.smtp.auth="true"
            mail.smtp.starttls.enable="true"
            mail.smtp.socketFactory.class="javax.net.ssl.SSLSocketFactory"
            mail.imap.host="imap.gmail.com"
            mail.imap.port="993"
            mail.transport.protocol="smtp"
            mail.store.protocol="imap"
        />
    </Context>

Super! Your mail session is configured. Next, you need to connect Liferay to the
mail session and database connections you just created. 

## Configuring your Database and Mail Session [](id=configuring-your-database-and-mail-session)

In this section you'll specify appropriate properties for Liferay to use in
connecting to your database and mail session.

1. If you are using Tcat to manage your data source, add the following line to
   the `portal-ext.properties` file in your *Liferay Home*. This points Liferay
   Portal to your data source: 

    jdbc.default.jndi.name=jdbc/LiferayPool

   Otherwise, if you are using *Liferay Portal* to manage your data source, follow
   the instructions in the *Deploy Liferay* section for using the setup wizard.

2. If you want to use Liferay Portal to manage your mail session, you can
   configure the mail session in Liferay Portal. After starting
   your portal as described in the *Deploy Liferay* section, go to *Control
   Panel &rarr; Server Administration &rarr; Mail* and enter the settings for
   your mail session.

   If you are using Tcat to manage your mail session, add the following to your
   `portal-ext.properties` file to reference that mail session:

    mail.session.jndi.name=mail/MailSession

Before you deploy Liferay Portal, let's look at configuring Portal Access
Control Lists (PACL) with Liferay on Tomcat. 

## Enabling PACL [](id=enabling-pacl)

To enable PACL, you need to enable the security manager and add some required
permissions to the server policy configuration file. This entails editing
the `CATALINA_OPTS` variable and editing the `catalina.policy` file:

In the *Administration* tab of the Tcat Administration Console, click *Server
Profiles* and click the profile applied to your Liferay Tcat server. Click the
*Value* field of the `CATALINA_OPTS` variable created earlier, and add the
following parameter to it:

    `-Djava.security.manager -Djava.security.policy==$CATALINA_BASE/conf/catalina.policy`

The double equals sign tells the app server to use this policy file on top of
any existing security policies. 

Edit `$TCAT_HOME/conf/catalina.policy` and add the required permissions:

        grant {
            permission java.security.AllPermission;
        };

Now you have PACL enabled and configured for your portal. Let's deploy Liferay!

## Deploying Liferay [](id=deploying-liferay)

It's time to deploy Liferay as an exploded web archive in your
`$TCAT_HOME/webapps` folder. The first step is to make sure your Tcat server is
running; then follow these steps to deploy and start Liferay.

1. If you are manually installing Liferay on a clean Tcat server, delete the
   contents of the `[TCAT_HOME]/webapps/ROOT` directory. This removes the default
   home page.

2. In the Tcat Administration Console, click the *Deployments* tab and select
   *New Deployment*. Select the server where you're deploying Liferay and
   click *Upload New Webapp*. Browse to the `liferay-portal-6.2.x-[date].war` file
   you downloaded. Make sure you select *Advanced Options* while uploading
   Liferay, and under the *Name* field, type `/` to put the extracted Liferay into
   `[TCAT_HOME]/webapps/ROOT`.

   ![Figure 1.4: Upload your Liferay Portal WAR file using the Deployments tab of the Tcat Administration Console.](../../images/15-tcat-upload-webapp.png)

3. Once you've entered all the deployment details, you can select *Deploy*.
   Once you see a *Successful* message in the Tcat Administration Console,
   you're ready to launch Liferay Portal on Tcat!

Now you can navigate to Liferay and follow the setup wizard. Congratulations
on successfully installing and deploying Liferay on Mule Tcat!

# Installing Liferay on GlassFish 4 [](id=installing-liferay-on-glassfish-4)

*Liferay Home* is three folders above your GlassFish domain folder.

For example, if your domain location is
`/glassfish-4.0-web/glassfish4/glassfish/domains/domain1`, Liferay Home is
`/glassfish-4.0-web/glassfish4/`.

If you don't already have an existing GlassFish server, we recommend that you
download a Liferay/GlassFish bundle from
[http://www.liferay.com/downloads/liferay-portal/available-releases](http://www.liferay.com/downloads/liferay-portal/available-releases).
If you have an existing GlassFish server or would like to install Liferay on
GlassFish manually, please follow the steps below.

Before you begin, make sure you have downloaded the latest Liferay `.war` file
and Liferay Portal dependencies from
[http://www.liferay.com/downloads/liferay-portal/additional-files](http://www.liferay.com/downloads/liferay-portal/additional-files).
The Liferay `.war` file should be called `liferay-portal-6.2.x-<date>.war` and
the dependencies file should be called
`liferay-portal-dependencies-6.2.x-<date>.zip`.

These instructions assume that you are running the latest supported version of
Glassfish (currently 4.0), have already configured a domain and server, and that
you have access to the GlassFish administrative console.

Let's start out by installing the JAR files you will need.

## Dependency Jars [](id=dependency-jars)

Liferay depends on jar files found in the Liferay Dependencies Archive. You
should also have installed your database driver.

1. Navigate to the folder that corresponds to the domain in which you'll be
   installing Liferay. Inside this folder is a sub-folder named `lib` (e.g.
   `/glassfish-3.1-web/glassfish3/glassfish/domains/domain1/lib`).

    Unzip the Liferay dependencies archive so that its `.jar` files are extracted
    into this `lib` folder.

2. Make sure the JDBC driver for your database is accessible to GlassFish as
   well. Obtain the JDBC driver for your version of the database server. In the
   case of MySQL, use `mysql-connector-java-{$version}-bin.jar`. You can
   download the latest MySQL JDBC driver from
   [http://www.mysql.com/products/connector/](http://www.mysql.com/products/connector/).
   Extract the JAR file and copy it to `lib`.

Terrific, you have your JAR files just where you need them. Next, we'll
configure your domain.

#### Domain Configuration [](id=domain-configuration)

There are a couple of modifications you need to make in your domain to use
Liferay Portal.

Before starting GlassFish, modify your domain's configuration to do the
following: 

- Set the file encoding
- Set the user time-zone
- Set the preferred protocol stack
- Prevent the application server from setting static fields (final or non-final)
  to `null`
- Increase the default amount of memory available.

Modify
`glassfish4/glassfish/domains/domain1/config/domain.xml`,
merging in the following JVM options into the current lists of JVM options
within any `<java-config>` element in the file: 

    <jvm-options>-Dfile.encoding=UTF8</jvm-options> 
    <jvm-options>-Djava.net.preferIPv4Stack=true</jvm-options>
    <jvm-options>-Dorg.apache.catalina.loader.WebappClassLoader.ENABLE_CLEAR_REFERENCES=false</jvm-options>
    <jvm-options>-Duser.timezone=GMT</jvm-options>
    <jvm-options>-Xmx1024m</jvm-options>
    <jvm-options>-XX:MaxPermSize=512m</jvm-options>

There may be multiple lists of JVM options in your `domain.xml` file. For
example, by default, GlassFish's
`glassfish4/glassfish/domains/domain1/config/domain.xml` file contains two lists
of JVM options. There's one list inside of the `<config name="server-config">`
element's `<java-config>` element and another inside of the `<config
name="default-config">` element's `<java-config>` element. In both lists of JVM
options, make sure that any existing options with values such as
`-Dfile.encoding`, `-Djava.net.preferIPv4Stack`,
`-Dorg.apache.catalina.loader.WebappClassLoader.ENABLE_CLEAR_REFERENCES`,
`-Duser.timezone`, or `-XX:MaxPermSize` are replaced with the new values listed
above.

For example, replace: 

    <jvm-options>-Xmx256m</jvm-options>

with this: 

    <jvm-options>-Xmx1024m</jvm-options>

Edit your `domain1/config/config/server-policy.xml` and append the following
lines to the end of the file:

    grant {
        permission java.security.AllPermission;
    };

Delete, rename, or move the `domain1/docroot/index.html` file to another
location to allow your Liferay Portal default page to be displayed.

Next, let's get your database configured.

## Database Configuration [](id=database-configuration)

If you want to use GlassFish to manage your domain's data source, follow the
instructions found in this section. If you want to use Liferay Portal to manage
your data source, you can skip this section.

1. Start your domain's application server if it is not already running.

2. Go to the GlassFish console URL:
   [http://localhost:4848](http://localhost:4848/). 

3. Under *Common Tasks*, navigate to *Resources* &rarr; *JDBC* &rarr; *JDBC
   Connection Pools*

    ![Figure 1.5: In the GlassFish administration console, navigate to JDBC Connection Pools.](../../images/11-glassfish31-connection-pools.png)

4. Click *New...*.

5. On the first screen (Step 1 of 2), enter the name `LiferayPool` for your
   connection pool, select the `javax.sql.ConnectionPoolDataSource` resource
   type, and select your database driver vendor (e.g. `MySQL`). See the
   following figure:

    ![Figure 1.6: It's easy to configure a new Glassfish JDBC Connection Pool. Just enter a pool name, select a resource type, and specify a database driver vendor.](../../images/11-glassfish-31-jdbc-connection-pool.png)

6. Click *Next* to advance to the next step in creating your JDBC connection
   pool.

7. From the top of this screen (Step 2 of 2), scroll down to the *Additional
   Properties* section.

    ![Figure 1.7: GlassFish JDBC Connection Pool Properties](../../images/11-glassfish-31-jdbc-connection-pool-props.png)  

8. Replace or add the following properties ...

    **Url:** the URL of your connection pool.

    For example,

        jdbc:mysql://localhost:3306/lportal?useUnicode=true&amp;characterEncoding=UTF-8&amp;emulateLocators=true

    Note, if you are using the above example, you should specify the name of
    your database in place of `lportal`. Likewise, if your database is not on
    the same host as GlassFish, specify your the database server's host name in
    place of `localhost`. Lastly, specify your database type in place of
    `jdbc:mysql` and use the correct port (3306 is for MySQL).

    **User:** the name of your database user.

    **Password:** your database user's password.

9. Click *Finish*.

    You should now see your `LiferayPool` connection pool listed under
    *Resources* &rarr; *JDBC* &rarr; *JDBC Connection Pools*

10. Test your connection by selecting your `LiferayPool` connection pool and
    clicking *Ping*.

    If you get a message stating *Ping Succeeded*, you've succeeded in setting
    up a connection pool of your data source!

11. Now, you'll setup a JDBC resource to refer to the `LiferayPool` connection
    pool you just created.

12. Navigate to *Resources* &rarr; *JDBC* &rarr; *JDBC Resources* to show the
    current JDBC resources listed by their JNDI names.

13. Click *New...*.

14. Set the JNDI name to `jdbc/LiferayPool` and select `LiferayPool` as the pool
    name.

15. Click *OK*.

Congratulations! You've now configured your domain's data source on GlassFish!

## Mail Configuration [](id=mail-configuration)

If you want to use GlassFish to manage your mail session, follow GlassFish's
documentation on configuring a JavaMail session with a JNDI name of
`mail/MailSession`. If you want to use Liferay Portal to manage your mail
session, you can skip this step.

## Domain Configuration - Continued [](id=domain-configuration-continued)

Let's tie up some loose ends with regards to Liferay being able to access your
database and mail session.

1. Shutdown your domain's application server if it is currently running.

2. If you are using *Glassfish* to manage your data source, create a
   `portal-ext.properties` file in the *Liferay Home* folder mentioned at the
   beginning of this GlassFish installation section and add the following to
   your `portal-ext.properties` file in your *Liferay Home* to refer to your
   data source:

        jdbc.default.jndi.name=jdbc/LiferayPool

    Otherwise, if you are using *Liferay Portal* to manage your data source,
    follow the instructions in the *Deploy Liferay* section for using the setup
    wizard.

3. If want to use *Liferay Portal* to manage your mail session, you can
   configure the mail session within Liferay Portal. That is, after starting
   your portal as described in the *Deploy Liferay* section, go to *Control
   Panel &rarr; Server Administration &rarr; Mail* and enter the settings for
   your mail session.

    Otherwise, if you are using *GlassFish* to manage your mail session, add the
    following to your `portal-ext.properties` file to reference that mail
    session:

        mail.session.jndi.name=mail/MailSession

Liferay will now be able to communicate with your database and mail session.

## PACL Configuration [](id=pacl-configuration)

To enable PACL on GlassFish, you need to make some security configurations.
First, enable the security manager by editing
`glassfish/domains/domain1/config/domain.xml` and make sure it contains the
following:

    <java-config ...>
        ...
        <jvm-options>-Djava.security.manager</jvm-options>
        ...
    </java-config>

Next, add the required permissions to the server policy configuration file:
`glassfish/domains/domain1/config/server.policy`. These include the following:

    grant {
        permission java.security.AllPermission;
    };

Now let's go ahead and deploy Liferay.

## Deploy Liferay [](id=deploy-liferay)

1. Start your domain's application server.

2. Go to the GlassFish console URL:
   [http://localhost:4848](http://localhost:4848/)

3. Click *Applications* in the tree on the left.

4. Click *Deploy*.

5. Under *Packaged File to Be Uploaded to the Server*, click *Choose File* and
   browse to the location of the Liferay Portal `.war` file. Enter *Context
   Root:* `/`

6. Enter *Application Name:* `liferay-portal`

7. Click *OK*.

![Figure 1.8: GlassFish provides an administrative console which you can use to deploy Liferay.](../../images/11-deploying-liferay-in-glassfish-4.0.png)

Once you have deployed Liferay via GlassFish's administrative console, restart
GlassFish.

Your installation of Liferay Portal on GlassFish is complete!

# Installing Liferay on JBoss 7.1 [](id=installing-liferay-on-jboss-7-1)

**Liferay Home** is one folder above JBoss's install location.

1. Download and install JBoss AS 7.1.x into your preferred directory. This
   directory is referred to as `$JBOSS_HOME` throughout this section.

2. Download the latest version of the Liferay Portal `.war` file.

3. Download Liferay's Portal Dependencies.

Now that you have all of your installation files, you are ready to start
installing and configuring Liferay on JBoss.

## Dependency Jars [](id=dependency-jars)

Let's work with the dependency jar files first.

1. Create folder `$JBOSS_HOME/modules/com/liferay/portal/main` and unzip the jar
   files found in the Liferay Portal Dependencies zip file to this folder. Make
   sure the zip file's jar files are extracted into the
   `$JBOSS_HOME/modules/com/liferay/portal/main` folder.

2. Download your database driver `.jar` file and put it into the
   `$JBOSS_HOME/modules/com/liferay/portal/main` folder as well. To use MySQL,
   we'll download the MySQL Connector/J driver from
   [http://dev.mysql.com/downloads/connector/j/](http://dev.mysql.com/downloads/connector/j/)
   and put its `.jar` file into the
   `$JBOSS_HOME/modules/com/liferay/portal/main` folder.

3. Create the file `module.xml` in the
   `$JBOSS_HOME/modules/com/liferay/portal/main` folder and insert the following
   contents.

        <?xml version="1.0"?>

        <module xmlns="urn:jboss:module:1.0" name="com.liferay.portal">
            <resources>
                <resource-root path="mysql-connector-java-[version]-bin.jar" />
                <resource-root path="portal-service.jar" />
                <resource-root path="portlet.jar" />
            </resources>
            <dependencies>
                <module name="ibm.jdk" />
                <module name="javax.api" />
                <module name="javax.mail.api" />
                <module name="javax.servlet.api" />
                <module name="javax.servlet.jsp.api" />
                <module name="javax.transaction.api" />
            </dependencies>
        </module>

    Make sure to replace `[version]` with the correct version of the MySQL JDBC
    driver. If you are using a different database, replace the MySQL jar with 
    the driver jar for your database. 

4. Next, you'll need to include a patch from Liferay's source code for one of
JBoss' default `.jar` files. Once you've downloaded the Liferay source, unzip
the source into a temporary folder. We'll refer to the location of the Liferay
source as `$LIFERAY_SOURCE`.

5. Currently, there are bugs in the
`$JBOSS_HOME/modules/org/jboss/as/server/main/jboss-as-<$JBOSS_VERSION>.Final.jar`
file regarding the IBM JVM
([LPS-39705](http://issues.liferay.com/browse/LPS-39705) and
[JBPAPP-9353](http://issues.jboss.org/browse/JBPAPP-9353)) which requires
additional steps to ensure a successful deployment with Liferay. In summary,
you'll need to update the `ServerDependenciesProcessor.class` file in the
`jboss-as-<$JBOSS_VERSION>.Final.jar` file to specify the IBM JDK. The steps to
insert the patch can be referenced below.

    1. Cut and paste the `jboss-as-[$JBOSS_VERSION].Final.jar` file from
    `$JBOSS_HOME/modules/org/jboss/as/server/main` to the
    `$LIFERAY_SOURCE/tools/servers/jboss/patches/JBPAPP-9353/classes` folder.
    
    2. Navigate to the
    `$LIFERAY_SOURCE/tools/servers/jboss/patches/JBPAPP-9353/classes` directory
    in a command prompt and enter the following statement:
    
            jar uf jboss-as-server-[$JBOSS_VERSION].Final.jar org/jboss/as/server/deployment/module/ServerDependenciesProcessor.class

        This command inserts the `ServerDependenciesProcessor.class` file into
        the `jboss-as-[$JBOSS_VERSION].Final.jar` file's
        `org/jboss/as/server/deployment/module` folder. You can reference the
        official documentation for updating a JAR file at
        [http://docs.oracle.com/javase/tutorial/deployment/jar/update.html](http://docs.oracle.com/javase/tutorial/deployment/jar/update.html).

    3. Cut and paste the `jboss-as-[$JBOSS_VERSION].Final.jar` file back to its
    original `$JBOSS_HOME/modules/org/jboss/as/server/main` folder.

Great! You have your `.jar` files ready for your domain.

## Running Liferay on JBoss 7.1 in Standalone Mode vs. Domain Mode [](id=running-liferay-on-jboss-7-1-in-standalone-mode-vs-domain-mode)

JBoss 7.1 can be launched in either *standalone* mode or *domain* mode. Domain
mode allows multiple application server instances to be managed from a single
control point. A collection of such application servers is known as a *domain*.
For more information on standalone mode vs. domain mode, please refer to the
section on this topic in the [JBoss 7.1 Admin
Guide](https://docs.jboss.org/author/display/AS71/Admin+Guide#AdminGuide-Operatingmodes).
Liferay fully supports JBoss 7.1 when it runs in standalone mode but not when it
runs in domain mode.

You can run Liferay on JBoss 7.1 in domain mode, but this method is not fully
supported. In particular, Liferay's hot-deploy does not work since JBoss 7.1
cannot deploy non-exploded `.war` files in domain mode. The `.war` files are
located in the `domain/data/content` directory. Deployments are only possible
using the command line interface. This prevents many Liferay plugins from
working as intended. For example, JSP hooks don't work on JBoss 7.1 running in
domain mode since Liferay's JSP override mechanism relies on the application
server reloading customized JSP files from the exploded plugin `.war` file
location. Other plugins, such as service or action hooks, should still work
properly since they don't require JBoss to access anything (such as JSP files)
from an exploded `.war` file on the file system. 

+$$$

**Note:** This does not prevent Liferay from
running in a clustered environment on multiple JBoss servers. You can set up a
cluster of Liferay instances running on JBoss 7.1 servers running in standalone
mode. Please refer to the chapter of this guide on [Configuring Liferay for High
Availability](https://www.liferay.com/documentation/liferay-portal/6.2/user-guide/-/ai/configuring-liferay-for-high-availabili-liferay-portal-6-2-user-guide-20-en)
for information on setting up a Liferay cluster.

$$$

For multi-server production environments, the choice of running a managed domain
versus standalone servers comes down to whether you want to use the centralized
management capabilities a managed domain provides. Running a standalone server
is better suited for most development scenarios. Any individual server
configuration that can be achieved in a managed domain can also be achieved in a
standalone server. Even if the application being developed will eventually run
in production on a managed domain installation, most development can be done
using a standalone server. Therefore, the best practice when running Liferay on
JBoss 7.1 is to use standalone mode.

<!-- An excellent document on JBoss' 7.1 domain mode (written by Igor Spasic)
can be found here:
https://support.liferay.com/secure/attachment/72257/JBoss%20AS7%20Domain%20Mode.pdf
-->

## Configuring JBoss [](id=configuring-jboss)

Let's make some adjustments in your configuration to support using Liferay.

You can specify the JBoss server instance's configuration in the XML file
`$JBOSS_HOME/standalone/configuration/standalone.xml`. We'll refer to this file
simply as `standalone.xml`. You must also make some modifications to your
configuration and startup scripts found in the `$JBOSS_HOME/bin/` folder.
Lastly, you'll need to make some modifications in your `$JBOSS_HOME/modules/`.
Let's start with the changes to `standalone.xml`.

Make the following modifications to `standalone.xml`:

1. Add the following system properties between the `</extensions>` and
`<management>` tags:

        <system-properties>
            <property name="org.apache.catalina.connector.URI_ENCODING" value="UTF-8" />
            <property name="org.apache.catalina.connector.USE_BODY_ENCODING_FOR_QUERY_STRING" value="true" />
        </system-properties>

2. Add a timeout for the deployment scanner by setting
`deployment-timeout="240"` as seen in the excerpt below.

        <subsystem xmlns="urn:jboss:domain:deployment-scanner:1.1">
            <deployment-scanner deployment-timeout="240" path="deployments" relative-to="jboss.server.base.dir" scan-interval="5000"/>
        </subsystem>

3. Add the following JAAS security domain to the security subsystem
`<security-domains>` defined in element `<subsystem
xmlns="urn:jboss:domain:security:1.1">`.

        <security-domain name="PortalRealm">
            <authentication>
                <login-module code="com.liferay.portal.security.jaas.PortalLoginModule" flag="required" />
            </authentication>
        </security-domain>

4. Disable the welcome root of the web subsystem's virtual server default host
by specifying `enable-welcome-root="false"`.

        <subsystem xmlns="urn:jboss:domain:web:1.1" default-virtual-server="default-host">
            <connector name="http" protocol="HTTP/1.1" scheme="http" socket-binding="http"/>
            <virtual-server name="default-host" enable-welcome-root="false">
               <alias name="localhost" />
               <alias name="example.com" />
            </virtual-server>
        </subsystem>        

5. Insert the following `<configuration>` element within the web subsystem
element `<subsystem xmlns="urn:jboss:domain:web:1.1"
default-virtual-server="default-host" native="false">`.

        <configuration>
            <jsp-configuration development="true" />
        </configuration>
 
Now it's time for some changes to your configuration and startup scripts.
 
Make the following modifications to your standalone domain's configuration
script file `standalone.conf` (`standalone.conf.bat` on Windows) found in your
`$JBOSS_HOME/bin/` folder.

These modifications change the following options: 
- Set the file encoding
- Set the user time-zone
- Set the preferred protocol stack
- Increase the default amount of memory available.

Make the following edits as applicable to your operating system:

- On Windows, comment out the initial `JAVA_OPTS` assignment as demonstrated in
  the following line:

        rem set "JAVA_OPTS=-Xms64M -Xmx512M -XX:MaxPermSize=256M"

Then add the following `JAVA_OPTS` assignment one line above the
`:JAVA_OPTS_SET` line found at end of the file:

        set "JAVA_OPTS=%JAVA_OPTS% -Dfile.encoding=UTF-8 -Djava.net.preferIPv4Stack=true -Djava.security.manager -Djava.security.policy=$JBOSS_HOME/bin/server.policy -Djboss.home.dir=$JBOSS_HOME -Duser.timezone=GMT -Xmx1024m -XX:MaxPermSize=256m"

- On Unix, merge the following values into your settings for `JAVA_OPTS`
  replacing any matching attributes with the ones found in the assignment
  below:

        JAVA_OPTS="$JAVA_OPTS -Dfile.encoding=UTF-8 -Djava.net.preferIPv4Stack=true -Djava.security.manager -Djava.security.policy=$JBOSS_HOME/bin/server.policy -Djboss.home.dir=$JBOSS_HOME -Duser.timezone=GMT -Xmx1024m -XX:MaxPermSize=256m

    Make sure you replace the `$JBOSS_HOME` references with the appropriate
    directory. You'll notice we've added some Java security options. We'll
    finish configuring the Java security options in the *Security Configuration*
    section.

The prescribed script modifications are now complete for your Liferay
installation on JBoss.

Lastly, let's make the necessary changes in your `$JBOSS_HOME/modules`
directory.

1. Create folder `$JBOSS_HOME/modules/ibm/jdk/main` and create and insert a new
`module.xml` file into that folder.

2. Insert the following contents into the
`$JBOSS_HOME/modules/ibm/jdk/main/module.xml` file:

        <?xml version="1.0"?>

        <module xmlns="urn:jboss:module:1.1" name="ibm.jdk">
            <dependencies>
                <system export="true">
                    <paths>
                        <path name="com/ibm" />
                        <path name="com/ibm/crypto/provider" />
                        <path name="com/ibm/jvm" />
                        <path name="com/ibm/jvm/io" />
                        <path name="com/ibm/jvm/util" />
                        <path name="com/ibm/match" />
                        <path name="com/ibm/misc" />
                        <path name="com/ibm/net" />
                        <path name="com/ibm/nio" />
                        <path name="com/ibm/nio/ch" />
                        <path name="com/ibm/security/auth" />
                        <path name="com/ibm/security/bootstrap" />
                        <path name="com/ibm/security/auth/module" />
                        <path name="com/ibm/security/util" />
                        <path name="META-INF/services" />
                    </paths>
                </system>
            </dependencies>
        </module>

3. Navigate to the `$JBOSS_HOME/modules/sun/jdk/main/module.xml` file and insert
the following path names inside the &lt;paths&gt;...<\/paths> element:

        <path name="com/sun/crypto" />
        <path name="com/sun/crypto/provider" />
        <path name="com/sun/image/codec/jpeg" />
        <path name="com/sun/org/apache/xml/internal/resolver" />
        <path name="com/sun/org/apache/xml/internal/resolver/tools" />

Next we'll consider the database and mail configuration. 

## Database Configuration [](id=database-configuration)

If you want JBoss to manage your data source, follow the instructions in this
section. If you want to use the built-in Liferay data source, you can skip this
section.

Modify `standalone.xml` adding your data source and driver within the
`<datasources>` element of your data sources subsystem.

1. First, add your data source within the `<datasources>` element.

        <datasource jndi-name="java:/jdbc/LiferayPool" pool-name="LiferayPool" enabled="true" jta="true" use-java-context="true" use-ccm="true">
            <connection-url>jdbc:mysql://localhost/lportal</connection-url>
            <driver>mysql</driver>
            <security>
                <user-name>root</user-name>
                <password>root</password>
            </security>
        </datasource>

    Be sure to replace the URL database value (i.e. `lportal`), user value and
    password value with values specific to your database.

2. Then add your driver to the `<drivers>` element also found within the
   `<datasources>` element.

        <drivers>
            <driver name="mysql" module="com.liferay.portal"/>
        </drivers>

Your final data sources subsystem should look like this:

        <subsystem xmlns="urn:jboss:domain:datasources:1.0">
            <datasources>
                <datasource jndi-name="java:/jdbc/LiferayPool" pool-name="LiferayPool" enabled="true" jta="true" use-java-context="true" use-ccm="true">
                    <connection-url>jdbc:mysql://localhost/lportal</connection-url>
                    <driver>mysql</driver>
                    <security>
                        <user-name>root</user-name>
                        <password>root</password>
                    </security>
                </datasource>
                <drivers>
                    <driver name="mysql" module="com.liferay.portal"/>
                </drivers>
            </datasources>
        </subsystem>

Now that you've configured your data source, let's go over how to configure your
mail session within JBoss.

## Mail Configuration [](id=mail-configuration)

If you want JBoss to manage your mail session, use the following instructions.
If you want to use the built-in Liferay mail session, you can skip this section.

Specify your mail subsystem  in `standalone.xml` as in the following example:

    <subsystem xmlns="urn:jboss:domain:mail:1.0">
        <mail-session jndi-name="java:/mail/MailSession" >
            <smtp-server ssl="true" outbound-socket-binding-ref="mail-smtp">
                   <login name="username" password="password"/>
            </smtp-server>
            <pop3-server outbound-socket-binding-ref="mail-pop">
                <login name="username" password="password"/>
            </pop3-server>
       </mail-session>
    </subsystem>
    ...
    <socket-binding-group name="standard-sockets" default-interface="public" port-offset="${jboss.socket.binding.port-offset:0}">
    ...
    <outbound-socket-binding name="mail-smtp">
            <remote-destination host="smtp.gmail.com" port="465"/>
        </outbound-socket-binding>
        <outbound-socket-binding name="mail-pop">
            <remote-destination host="pop.gmail.com" port="110"/>
        </outbound-socket-binding>
    </socket-binding-group>
    
You've got mail! Next, we'll make sure Liferay is configured to properly connect
with your new mail session and database.

## Configuring data sources and mail sessions [](id=configuring-data-sources-and-mail-sessions)

Now that your data source and mail session are set up, you need to ensure
Liferay Portal can access them.

1. First, navigate to the Liferay Home folder, which is one folder above JBoss's
   install location (i.e. `$JBOSS_HOME/..`).

2. If you're using *JBoss* to manage your data source, add the following to your
   `portal-ext.properties` file in your *Liferay Home* to refer to your data
   source:

        jdbc.default.jndi.name=java:jdbc/LiferayPool

    If you're using *Liferay Portal* to manage your data source, follow the
    instructions in the *Deploy Liferay* section for using the setup wizard.

3. If you're using *Liferay Portal* to manage your mail session, this
   configuration is done within Liferay Portal. That is, after starting your
   portal as described in the *Deploy Liferay* section, go to *Control Panel
   &rarr; Server Administration &rarr; Mail* and enter the settings for your
   mail session.

    If you're using *JBoss* to manage your mail session, add the following to your
    `portal-ext.properties` file to reference that mail session:

        mail.session.jndi.name=java:mail/MailSession

You've completed the steps necessary for your deployment of Liferay so Liferay
Portal can now communicate with your data source and mail session--way to go!
Before we deploy Liferay Portal on your JBoss app server, we'll teach you how to
enable and configure Java security so you can begin using Liferay's plugin
security manager with your downloaded Liferay applications.

## Security Configuration [](id=security-configuration)

When you're ready to begin using other people's apps from Marketplace, you'll
want to protect your portal and your JBoss server from security threats. To do
so, you can enable Java Security on your JBoss server and specify a security
policy to grant your portal access to your server.

Remember, we set the `-Djava.security.manager` and `-Djava.security.policy` Java
options in the `standalone.conf.bat` file earlier in the *Configuring JBoss*
section. The `-Djava.security.manager` Java option enables security on your
JBoss server. Likewise, the `-Djava.security.policy` Java option lists the
permissions for your server's Java security policy. If you have not set these
options, you'll need to do so before using Java security.

For now, in order to grant Liferay access to your server let's open up all
permissions. You can tune the permissions in your policy later. Create the
`$JBOSS_HOME/bin/server.policy` file and add the following contents:

    grant {
        permission java.security.AllPermission;
    };

For extensive information on Java SE Security Architecture, see its
specification documents at
[http://docs.oracle.com/javase/7/docs/technotes/guides/security/spec/security-spec.doc.html](http://docs.oracle.com/javase/7/docs/technotes/guides/security/spec/security-spec.doc.html).
Also, see section [*Understanding Plugin Security
Management*](https://www.liferay.com/documentation/liferay-portal/6.2/development/-/ai/understanding-plugin-security-management-liferay-portal-6-2-dev-guide-11-en)
in Chapter 12 of the Developer's Guide to learn how to configure Liferay plugin
access to resources.

Now you're ready to deploy Liferay Portal.

## Deploy Liferay [](id=deploy-liferay)

1. If the folder `$JBOSS_HOME/standalone/deployments/ROOT.war` already exists in
   your JBoss installation, delete all of its subfolders and files. Otherwise,
   create a new folder `$JBOSS_HOME/standalone/deployments/ROOT.war`.

2. Unzip the Liferay `.war` file into the `ROOT.war` folder.

3. In the `ROOT.war` file, open the `WEB-INF/jboss-deployment-structure.xml`
   file. In this file, replace the `<module name="com.liferay.portal" />`
   dependency with the following: 

        <module meta-inf="export" name="com.liferay.portal">
            <imports>
                <include path="META-INF" />
            </imports>
        </module>

    This allows OSGi plugins like Audience Targeting to work properly by
    exposing the Portal API through the OSGi container. 

4. In the same `jboss-deployment-structure.xml` file, find the
   `<jboss-deployment-structure>` tag and update the `1.0` number within the
   `xmlns` attribute to `1.1`. 

5. To trigger deployment of `ROOT.war`, create an empty file named
   `ROOT.war.dodeploy` in  your `$JBOSS_HOME/standalone/deployments/` folder.
   On startup, JBoss detects the presence of this file and deploys it as a web
   application.

6. Remove `eclipselink.jar` from
   `$JBOSS_HOME/standalone/deployments/ROOT.war/WEB-INF/lib` to assure the
   Hibernate persistence provider is used instead of the one provided in the
   `eclipselink.jar`.

    Now it's time to start Liferay Portal on JBoss!

7.  Start the JBoss application server.
 
Now you are truly *the boss* when it comes to deploying Liferay Portal on JBoss!

# Installing Liferay on Tomcat 7 [](id=installing-liferay-on-tomcat-7)

*Liferay Home* is one folder above Tomcat's install location.

For this section, we will refer to your Tomcat server's installation location as
`$TOMCAT_HOME`. If you do not already have an existing Tomcat server, we
recommend you download a Liferay/Tomcat bundle from
[http://www.liferay.com/downloads/liferay-portal/available-releases](http://www.liferay.com/downloads/liferay-portal/available-releases).
If you have an existing Tomcat server or would like to install Liferay on Tomcat
manually, please follow the steps below.

Before you begin, make sure you have downloaded the latest Liferay `.war` file
and Liferay Portal dependencies from
[http://www.liferay.com/downloads/liferay-portal/additional-files](http://www.liferay.com/downloads/liferay-portal/additional-files).
The Liferay `.war` file should be called `liferay-portal-6.2.x-[date].war` and
the dependencies file should be called
`liferay-portal-dependencies-6.2.x-[date].zip`.

Next, let's get started by addressing Liferay's library dependencies.

## Dependency Jars [](id=dependency-jars)

Liferay Portal depends on several `JAR` files found in the Liferay
Dependencies Archive. In addition to these, you need the proper driver for your
database. You can get the necessary dependencies by following these steps:

1. Create a folder named `ext` in `$TOMCAT_HOME/lib`.

2. Unzip the Liferay Dependencies file to `$TOMCAT_HOME/lib/ext` so that its
`.jar` files reside there. If the `.jar` files are nested in a
`liferay-portal-dependencies-6.2` directory after you extract them, copy them
into `$TOMCAT_HOME/lib/ext` and delete the empty folder.

3. Next, you need a few third party `.jar` files which are included as part of the
   Liferay source distribution, but are not automatically included with Tomcat.
   You'll have to download them yourself, so let's get started. Place these
   `.jar` files into `$TOMCAT_HOME/lib/ext`:

    - `jta.jar`: You can get this `.jar`, which manages transactions, from
            [http://www.oracle.com/technetwork/java/javaee/jta/index.html](http://www.oracle.com/technetwork/java/javaee/jta/index.html)
    - `mail.jar`: You can get this `.jar` from
            [http://www.oracle.com/technetwork/java/index-138643.html](http://www.oracle.com/technetwork/java/index-138643.html)
    - `persistence.jar`: You can learn about the Java Persistence API and how
      to download it from
            [http://www.oracle.com/technetwork/java/javaee/tech/persistence-jsp-140049.html](http://www.oracle.com/technetwork/java/javaee/tech/persistence-jsp-140049.html)

4. Make sure the JDBC driver for your database is accessible by Tomcat. In the
case of MySQL, use `mysql-connector-java-{$version}-bin.jar`. You can download
the latest MySQL JDBC driver from
[http://dev.mysql.com/downloads/connector/j/](http://dev.mysql.com/downloads/connector/j/).
Extract the JAR file and copy it to `$TOMCAT_HOME/lib/ext`.

5. There are a few other JARs that come with a typical Liferay bundle that you
   might want to download and place in your `$TOMCAT_HOME/lib/ext` folder. They
   include the following:

    - `activation.jar`: You can get this `.jar` from
        [http://www.oracle.com/technetwork/java/jaf11-139815.html](http://www.oracle.com/technetwork/java/jaf11-139815.html)
    - `ccpp.jar`: You can get this `.jar` from 
        [http://mvnrepository.com/artifact/javax.ccpp/ccpp/1.0](http://mvnrepository.com/artifact/javax.ccpp/ccpp/1.0)
    - `jms.jar`: You can get this `.jar` from
        [http://www.oracle.com/technetwork/java/docs-136352.html](http://www.oracle.com/technetwork/java/docs-136352.html)
    - `jutf7.jar`: You can get this `.jar` from 
          [http://sourceforge.net/projects/jutf7/](http://sourceforge.net/projects/jutf7/)
    - `junit.jar`: You can get this `.jar` from 
        [http://sourceforge.net/projects/junit/](http://sourceforge.net/projects/junit/)

    You can download each third partry `.jar` listed above from the provided
    websites, then place them into your `%TOMCAT_HOME/lib/ext` directory.  However,
    they're also available in the Liferay source code, so if you have access to the
    Liferay source or would like to download it for this purpose, feel free to copy
    the `.jar` files from there. If we refer to your local Liferay source
    directory as `$LIFERAY_SOURCE`, you can get all of the third party `.jar` files
    listed above from `$LIFERAY_SOURCE/lib/development`, with the exception of
    `ccpp.jar`, which is found in `$LIFERAY_SOURCE/lib/portal`.

Now that you have the necessary libraries in place, we'll move on to
configuring your domain.

## Tomcat Configuration [](id=tomcat-configuration)

There are several configuration steps you need to complete before Tomcat can
run Liferay. Let's get started.

1. First we'll need to set the `CATALINA_OPTS` environment variable. Create a
`setenv.bat` (Windows) or `setenv.sh` file (Unix, Linux, Mac OS) in the
`$TOMCAT_HOME/bin` directory. Populate it with following contents:

    - `setenv.bat`:

            if exist "%CATALINA_HOME%/jre@java.version@/win" (
                if not "%JAVA_HOME%" == "" (
                    set JAVA_HOME=
                )

                set "JRE_HOME=%CATALINA_HOME%/jre@java.version@/win"
            )

            set "CATALINA_OPTS=%CATALINA_OPTS% -Dfile.encoding=UTF8 -Djava.net.preferIPv4Stack=true -Dorg.apache.catalina.loader.WebappClassLoader.ENABLE_CLEAR_REFERENCES=false -Duser.timezone=GMT -Xmx1024m -XX:MaxPermSize=256m"

    - `setenv.sh`:

            CATALINA_OPTS="$CATALINA_OPTS -Dfile.encoding=UTF8 -Dorg.apache.catalina.loader.WebappClassLoader.ENABLE_CLEAR_REFERENCES=false -Duser.timezone=GMT -Xmx1024m -XX:MaxPermSize=256m"

    This sets the character encoding to UTF-8, sets the time zone to Greenwich
    Mean Time, and allocates memory to the Java Virtual Machine.

<!--Th setenv.bat and .sh are slightly different from 6.1, I took it from
setenv.sh and .bat in a built liferay 6.2.--> 

2. Let's create a context for Liferay. Create a `ROOT.xml` file in
`$TOMCAT_HOME/conf/Catalina/localhost`. Populate it with the following contents
to set up a portal web application:

        <Context path="" crossContext="true">

            <!-- JAAS -->

            <!--<Realm
                classNjame="org.apache.catalina.realm.JAASRealm"
                appName="PortalRealm"
                userClassNames="com.liferay.portal.kernel.security.jaas.PortalPrincipal"
                roleClassNames="com.liferay.portal.kernel.security.jaas.PortalRole"
            />-->

            <!--
            Uncomment the following to disable persistent sessions across reboots.
            -->

            <!--<Manager pathname="" />-->

            <!--
            Uncomment the following to not use sessions. See the property
            "session.disabled" in portal.properties.
            -->

            <!--<Manager className="com.liferay.support.tomcat.session.SessionLessManagerBase" />-->
        </Context>
        
    Setting `crossContext="true"` allows multiple web apps to use the same class
    loader. In the content above you will also find commented instructions and
    tags for configuring a JAAS realm, disabling persistent sessions and
    disabling sessions in general.

3. Let's make sure the libraries we added to `$TOMCAT_HOME/lib/ext` are loaded
when we start the server. Open `$TOMCAT_HOME/conf/catalina.properties` and
replace the line:

        common.loader=${catalina.base}/lib,${catalina.base}/lib/*.jar,${catalina.home}/lib,${catalina.home}/lib/*.jar

    with:

        common.loader=${catalina.base}/lib,${catalina.base}/lib/*.jar,${catalina.home}/lib,${catalina.home}/lib/*.jar,${catalina.home}/lib/ext,${catalina.home}/lib/ext/*.jar
        
    This allows Catalina to access the dependency jars you extracted to
    `$TOMCAT_HOME/lib/ext`.

4. We also need to ensure consistent use of UTF-8 URI Encoding. Edit
`$TOMCAT_HOME/conf/server.xml` and add the attribute `URIEncoding="UTF-8"`
where you see `redirectPort=8443`, in the definition of your connectors (HTTP
and AJP). For example:

        <Connector port="8080" protocol="HTTP/1.1" connectionTimeout="20000" redirectPort="8443" URIEncoding="UTF-8" />

5. Lastly, if you see a `support-catalina.jar` in your `$TOMCAT_HOME/webapps`
directory, delete it.

Now Tomcat is configured to run Liferay! If you want to use Liferay to manage
your database and mail session (and we recommend you do), you can skip the next
sections and move to the section titled *Enabling PACL*. Next we'll look at
configuring your database with Tomcat.

## Database Configuration [](id=database-configuration)

If you want Tomcat to manage your data source, use the following procedure. If
you want to use Liferay's built-in data source, you can skip this section.

1. Make sure your database server is installed and working. If it's installed
on a different machine, make sure it's accessible from the machine with
Liferay.

2. Add your data source as a resource in the context of your web application
   specified in `$TOMCAT_HOME/conf/Catalina/localhost/ROOT.xml`:

		<Context...>
			<Resource
				name="jdbc/LiferayPool"
				auth="Container"
				type="javax.sql.DataSource"
				driverClassName="com.mysql.jdbc.Driver"
				url="jdbc:mysql://localhost/lportal?useUnicode=true&amp;characterEncoding=UTF-8"
				username="root"
				password="root"
				maxActive="100"
				maxIdle="30"
				maxWait="10000"
			/>
		</Context>
	
+$$$

**Note:** The above resource definition
    assumes your database name is *lportal* and your MySQL username and password
    are both *root*. You'll have to update these values with your own database name
    and credentials.

$$$

Your Tomcat managed data source is now configured. Let's move on to your mail
session.

## Mail Configuration [](id=mail-configuration)

If you want to manage your mail session with Tomcat, use the following
instructions. If you want to use the built-in Liferay mail session, you can
skip this section.

Create a mail session bound to `mail/MailSession`. Edit `$TOMCAT_
HOME/conf/Catalina/localhost/ROOT.xml` and configure a mail session. Be sure to
replace the example mail session values with your own.

    <Context...>
        <Resource
            name="mail/MailSession"
            auth="Container"
            type="javax.mail.Session"
            mail.pop3.host="pop.gmail.com"
            mail.pop3.port="110"
            mail.smtp.host="smtp.gmail.com"
            mail.smtp.port="465"
            mail.smtp.user="user"
            mail.smtp.password="password"
            mail.smtp.auth="true"
            mail.smtp.starttls.enable="true"
            mail.smtp.socketFactory.class="javax.net.ssl.SSLSocketFactory"
            mail.imap.host="imap.gmail.com"
            mail.imap.port="993"
            mail.transport.protocol="smtp"
            mail.store.protocol="imap"
        />
    </Context>

Your mail session is configured. Next, we'll make sure Liferay will be able to
access your mail session and database.

## Configuring your database and mail session [](id=configuring-your-database-and-mail-session)

In this section we'll specify appropriate properties for Liferay to use in
connecting to your database and mail session.

1. If you are using *Tomcat* to manage your data source, add the following to
   your `portal-ext.properties` file in your *Liferay Home* to refer to your
   data source:

        jdbc.default.jndi.name=jdbc/LiferayPool

    Otherwise, if you are using *Liferay Portal* to manage your data source, follow
    the instructions in the *Deploy Liferay* section for using the setup wizard.

2. If want to use *Liferay Portal* to manage your mail session, you can
   configure the mail session within Liferay Portal. That is, after starting
   your portal as described in the *Deploy Liferay* section, go to *Control
   Panel &rarr; Server Administration &rarr; Mail* and enter the settings for
   your mail session.

    Otherwise, if you are using *Tomcat* to manage your mail session, add the
    following to your `portal-ext.properties` file to reference that mail session:

        mail.session.jndi.name=mail/MailSession

It's just that easy! Before we deploy Liferay Portal on your Tomcat server,
let's look at configuring Portal Access Control Language (PACL) with Liferay on
Tomcat. 

## Enabling PACL [](id=enabling-pacl)

To enable PACL for use with your Liferay Portal when running on Tomcat, you
need to enable the security manager and add some required permissions to the
server policy configuration file. This simply entails editing two files in
`$TOMCAT_HOME` you're already familiar with if you've been following along in
setting up Liferay with a Tomcat Application Server: 

- In `$TOMCAT_HOME/bin/setenv.sh` (if on Linux, Unix, or Mac OS) or
  `setenv.bat` (if on Windows) enable the security manager by inserting the
following code into the `CATALINA_OPTS` variable (inside the quotation marks):

    `-Djava.security.manager -Djava.security.policy=$CATALINA_BASE/conf/catalina.policy`

- In `$TOMCAT_HOME/conf/catalina.policy`, add the required permissions:

        grant {
            permission java.security.AllPermission;
        };

To enable the security manager on Tomcat, the server must be started with the
`-security` commandline options. Shutdown your Tomcat instance and restart it
with the following command: 

    ./startup.sh -security

Tomcat reports the message `Using Security Manager` to your terminal. 

Now you have PACL enabled and configured for your portal. Let's deploy Liferay!

## Deploy Liferay [](id=deploy-liferay)

It's time to deploy Liferay as an exploded web archive within your
`$TOMCAT_HOME/webapps` folder, configure our setup wizard behavior, and start
Liferay!

1. If you are manually installing Liferay on a clean Tomcat server, delete the
contents of the `$TOMCAT_HOME/webapps/ROOT` directory. This undeploys the
default Tomcat home page. Extract the Liferay `.war` file to
`$TOMCAT_HOME/webapps/ROOT`.

    Now its time to launch Liferay Portal on Tomcat!

3. Start Tomcat by executing `$TOMCAT_HOME/bin/startup.bat` or
`$TOMCAT_HOME/bin/startup.sh`.

Congratulations on successfully installing and deploying Liferay on Tomcat!

# Installing Liferay on Oracle WebLogic 12c (12.1.2 and higher) [](id=installing-liferay-on-oracle-weblogic-12c-12-1-2-and-h)

In this section, you'll learn how to install Liferay on Oracle WebLogic 12c.
Since you're using Oracle WebLogic, you may be curious of Liferay's support of
XA transactions. Liferay doesn't require XA transactions, but it supports XA.
Let's get acquainted with how Liferay fits in with your current WebLogic
domain. 

For demonstration purposes, we'll assume *Liferay Home* is one folder above the
domain to which you will be installing Liferay.

For example, if your domain location is
`/Oracle/Middleware/user_projects/domains/base_domain`, then your Liferay Home
is `/Oracle/Middleware/user_projects/domains`.

For this section, we will use `$WEBLOGIC_HOME` to refer to your WebLogic
server's installation `/Oracle/Middleware`. 

Before you begin, make sure you have downloaded the latest Liferay `.war` file
and Liferay Portal dependencies from
[http://www.liferay.com/downloads/liferay-portal/additional-files](http://www.liferay.com/downloads/liferay-portal/additional-files).
The Liferay `.war` file is called `liferay-portal-[version].war` and
the dependencies file is called `liferay-portal-dependencies-[version].zip`.

These instructions assume you have already configured a domain and server and
that you have access to the WebLogic console.

If you still have the `mainWebApp` module installed, remove it first.

Let's get started by installing the `.jar` files Liferay needs.

## Dependency Jars [](id=dependency-jars)

Liferay needs the `.jar` files contained in the Liferay Dependencies Archive and
the driver `.jar` file applicable for your database. We'll put them on your
domain's global classpath. 

1.  Navigate to the folder that corresponds to the domain to which you will be
    installing Liferay. Inside this folder is a `lib` folder. Unzip the Liferay
    Dependencies Archive to this folder so the dependency `.jar` files reside in
    the `lib` folder.

2.  If WebLogic does not already have access to the JDBC driver for your
    database, copy the driver to your domain's `lib` folder as well.

So far so good. Your `.jar` files are in place and ready for Liferay.

Let's proceed with configuring WebLogic.

## Configuring WebLogic [](id=configuring-weblogic)

Let's make the following adjustments in your configuration to support using
Liferay:
- Set WebLogic Server's memory arguments.
- Set the maximum size for Java's permanent generation space.
- Set the file encoding.
- Enable Lucene support.
- Enable AspectJ support.

You can set WebLogic Server's memory arguments in your `setDomainEnv.[cmd|sh]`
environment script file found in your domain's `bin` folder. For the Sun JVM,
we'll set the WLS memory arguments for 64 bit and 32 bit architectures to
`-Xms256m -Xmx1024m` at a minimum. For all other JVMs, we'll set the 64 bit and
32 bit WLS memory arguments to `-Xms512m -Xmx512m` respectively. 

We'll set the permanent generation space for 64 bit and 32 bit architectures
to `-XX:PermSize=256m`.

Lastly, we'll make sure to specify UTF-8 for Java's file encoding, by
including `-Dfile.encoding=UTF8` as a Java property.

If you're on Windows, for example, you'd edit your `setDomainEnv.cmd` file and
find the call to the `commEnv.cmd` script. After this call, you'd update your
memory arguments and permanent generation space settings to be like the
following code: 

    ...
    if "%JAVA_VENDOR"=="Sun" (
        set WLS_MEM_ARGS_64BIT=-Xms256m -Xmx1024m
        set WLS_MEM_ARGS_32BIT=-Xms256m -Xmx1024m
    ) else (
        set WLS_MEM_ARGS_64BIT=-Xms512m -Xmx512m
        set WLS_MEM_ARGS_32BIT=-Xms512m -Xmx512m
    )
    ...
    set MEM_PERM_SIZE_64BIT=-XX:PermSize=256m
    set MEM_PERM_SIZE_32BIT=-XX:PermSize=256m

Later in the `setDomainEnv.cmd` file's clustering support section, you'd set
the UTF-8 file encoding by appending `-Dfile.encoding=UTF8` to the front of the
list of Java property values, as done in the following line of code: 

    set JAVA_PROPERTIES=-Dfile.encoding=utf8 %JAVA_PROPERTIES% %CLUSTER_PROPERTIES%

Next, we'll need to specify some local environment settings to support Liferay's
memory requirements, its use of the [Apache
Lucene](http://en.wikipedia.org/wiki/Lucene) search engine library, and its use
of Aspect Oriented Programming (AOP) with
[AspectJ](http://en.wikipedia.org/wiki/AspectJ).

Open the `startWebLogic.[cmd|sh]` file from within your domain's folder--NOT
your server's `bin` folder. If you're on Windows, you'd add directives similar
to those listed below, after the `SETLOCAL` command:

    set "USER_MEM_ARGS=-Xmx1024m -XX:PermSize=512m"
    
    set "MW_HOME=D:\Oracle\Middleware\wlserver_12.1"
    
    set "JAVA_OPTIONS=%JAVA_OPTIONS% -da:org.apache.lucene... -da:org.aspectj..."

Make sure to set your `MW_HOME` value to your WebLogic server's location. On
Linux, you'd make similar changes replacing `%JAVA_OPTIONS%` with
`$JAVA_OPTIONS`. 

Next, if you want to configure your database and/or mail session within
WebLogic, start your WebLogic server.

## Database Configuration [](id=database-configuration)

If you want WebLogic to manage your data source, use the following procedure. If
you want to use Liferay's built-in data source, you can skip this section.

1.  Select *Services &rarr; Data Sources.* Click *New &rarr; Generic Data
    Source*.

2.  Give your data source a name, such as *Liferay Data Source*. The JNDI name
    should be `jdbc/LiferayPool`.

3.  Choose the type of database and click *Next*. 

4.  Click *Next* three times. You should be on the *Connection Properties*
    screen. Enter the database name, the host name, the port, the database user
    name and the password. WebLogic uses this information to construct the
    appropriate JDBC URL to connect to your database. Click *Next*.

5.  WebLogic prompts you to confirm the information you've specified for your
    data source. 

    Depending on the database you are using, you may need to specify additional
    parameters. For example, you may need to modify your JDBC URL to include
    additional parameters. If you need to access previous wizard pages to modify
    information, click *Back* to revisit those pages. 

    When you're done specifying your configuration, click *Next*. 

6.  Click *Test Configuration* to make sure WebLogic can connect to your
    database successfully. When it does, click *Finish*.

7.  You will be back to the list of data sources. Notice your new data source
    has no value in the *Target* column. Click on your data source to edit it.

8.  Click the *Targets* tab and check off the server instance(s) to which you
    wish to deploy your data source. Then click *Save*.

Next, let's configure a mail session in WebLogic.

## Mail Configuration [](id=mail-configuration)

If you want WebLogic to manage your mail sessions, use the following procedure.
If you want to use Liferay's built-in mail sessions, you can skip this section.

1.  Select *Mail Sessions* and create a new mail session which points to your
    mail server.

2.  Give it the name *Liferay Mail* and give it the JNDI name of
    `mail/MailSession` and click *Next*.

3.  Choose your server and then click *Finish*.

Now let's make sure Liferay can access this mail session.

## Domain Configuration - Continued [](id=domain-configuration-continued)

Let's revisit domain configuration to make sure we'll be able to access your
data source and mail session from Liferay Portal.

1.  Create a `portal-ext.properties` file in your Liferay Home folder.

    If you are using *WebLogic* to manage your data source, add the following to
    your `portal-ext.properties` file to refer to your data source:

        jdbc.default.jndi.name=jdbc/LiferayPool

    If you are using *Liferay Portal* to manage your data source, follow the
    instructions in the *Deploy Liferay* section for using the setup wizard.

    If want to use *Liferay Portal* to manage your mail session, you can
    configure the mail session in the Control Panel. After starting your portal
    as described in the *Deploy Liferay* section, go to *Control Panel &rarr;
    Server Administration &rarr; Mail* and enter the settings for your mail
    session.

    If you are using *WebLogic* to manage your mail session, add the following
    to your `portal-ext.properties` file to reference that mail session:

        mail.session.jndi.name=mail/MailSession

2.  Lastly, you must provide WebLogic a reference to a version of Java Server
    Faces (JSF)--either version 1.2 or 2.0--to use that library. Open the
    `WEB-INF/weblogic.xml` found in your Liferay Portal `.war` file. Insert the
    following deployment descriptor after the `<context-root>` element within
    the `<weblogic-web-app>` element. Make sure to specify the applicable JSF
    version:

        <library-ref>
            <library-name>jsf</library-name>
            <specification-version>1.2</specification-version>
            <implementation-version>1.2</implementation-version>
            <exact-match>false</exact-match>
        </library-ref>

Before we deploy Liferay, we'll teach you how to enable and configure Java
Security so that you can start using Liferay's plugin security manager with
the Liferay apps you download and install. 

## Security Configuration [](id=security-configuration)

When you are ready to start using other people's apps from Marketplace, you'll
want to protect your portal and your WebLogic server from security threats. To
do so, you can enable Java Security on your WebLogic server and specify a
security policy to grant your Liferay Portal access to your server. 

First, let's grant Liferay access to your server. For now, we'll open up all
permissions--you can fine-tune your policy's permissions later. Create a policy
file named `weblogic.policy` in your `$WEBLOGIC_HOME/wlserver/server/lib` folder
and add the following contents:

    grant {
        permission java.security.AllPermission;
    };

To enable security on your WebLogic server and direct the server to your policy
file, open the `setDomainEnv.[cmd|sh]` file in your domain's folder. Then set
the `-Djava.security.manager` Java option and set the property
`-Djava.security.policy==` to the location of your `weblogic.policy` file. You
can specify both settings on the same line like this:  

    -Djava.security.manager -Djava.security.policy==$WEBLOGIC_HOME/wlserver/ser\
    ver/lib

The double equals sign tells the app server to use this policy file on top of
any existing security policies. 

For extensive information on Java SE Security Architecture see its specification
documents at
[http://docs.oracle.com/javase/7/docs/technotes/guides/security/spec/security-spec.doc.html](http://docs.oracle.com/javase/7/docs/technotes/guides/security/spec/security-spec.doc.html).
Also, see section [*Understanding Plugin Security
Management*](https://www.liferay.com/documentation/liferay-portal/6.2/development/-/ai/understanding-plugin-security-management-liferay-portal-6-2-dev-guide-11-en)
in Chapter 12 of the Developer's Guide to learn how to configure Liferay plugin
access to resources. 

Now its the moment you've been waiting for: Liferay deployment!

## Deploy Liferay [](id=deploy-liferay)

This section provides instructions for deploying Liferay to your application
server domain.

Now, let's deploy Liferay Portal.

1.  Start your WebLogic server if it's not already started.

2.  Select *Deployments* and click the  *Install* button. Upload the desired JSF
    `.war` file--`jsf-1.2.war` or `jsf-2.0.war`--from WebLogic's common
    deployable libraries folder and select *Install this deployment as a
    library* to your domain's server that will be running Liferay. 

3.  After installing the JSF libraries, go back to deployments and select the
    Liferay `.war` file from the file system or click the *Upload Your File(s)*
    link to upload it and then click *Next*.

4.  Select *Install this deployment as an application* and click *Next*.

5.  If the default name is appropriate for your installation, keep it. Otherwise,
    give it a name of your choosing and click *Next*.

6.  Click *Finish*. After the deployment finishes, click *Save*.
    Liferay precompiles all the JSPs and Liferay launches.

Congratulations! You are now running Liferay on Oracle WebLogic 12c.

# Installing Liferay on WebSphere 8.5 [](id=installing-liferay-on-websphere-8-5)

+$$$

**Tip:** Throughout this installation and
configuration process, WebSphere prompts you to Click Save to apply changes to
Master Configuration. Do so intermittently to save your changes.

$$$

**Liferay Home** is in a folder called `liferay` in the home folder of the user
ID that is running WebSphere. 

To work correctly on WebSphere 8.5, IBM's PM90932 patch must be installed. You
can find more information about this patch
[here](http://www-01.ibm.com/support/docview.wss?uid=swg1PM90932). 

Please also note that the WebSphere Application Liberty Profile is not supported
by Liferay. 

## Preparing WebSphere for Liferay [](id=preparing-websphere-for-liferay)

When the application server binaries have been installed, start the **Profile
Management Tool** to create a profile appropriate for Liferay. 

1. Click on *Create…*. Choose *Application Server*. Click *Next*. 

2. Click the Advanced profile creation option and then click *Next*. Why
   Advanced? You can specify your own values for settings such as the location
   of the profile and names of the profile, node and host. You can assign your
   own ports. You can optionally choose whether to deploy the administrative
   console and sample application and also add web-server definitions if you
   wish. Web server definitions are used with IBM HTTP Server. For more
   information about these options, please see the WebSphere documentation. 

    ![Figure 1.9: Choose the Advanced profile option to specify your own settings.](../../images/websphere-01-profile.png)

3. Check the box *Deploy the administrative console*. This gives you a web-based
   UI for working with your application server. Skip the default applications.
   You'd only install these on a development machine. Click *Next*. 

4. Set profile name and location. Ensure you specify a performance tuning
   setting other than *Development*, since you're installing a server for
   production use. Please see the WebSphere documentation for further
   information about performance tuning settings. Click *Next*. 

5. Choose node, server, and host names for your server. These will be specific to your
   environment. Click *Next*. 

6. Administrative security in WebSphere is a way to restrict who has access to
   the administrative tools. You may want to have it enabled in your
   environment so that a user name and password are required to administer the
   WebSphere server. Please see WebSphere's documentation for further
   information. Click *Next*. 

7. Each profile needs a security certificate, which comes next in the wizard. If
   you don't have certificates already, choose the option to generate a personal
   certificate and a signing certficate and click *Next*. 

8. Once the certificates are generated, set a password for your keystore. Click
   *Next*. 

9. Next, you can customize the ports this server profile uses. Be sure to choose
   ports that are open on your machine. When choosing ports, the wizard
   detects existing WebSphere installations and if it finds activity, it
   increments ports by one. 

10. Choose whether you want this profile started when the machine starts. Click
    *Next*. 

11. WebSphere ships with IBM HTTP Server, which is a rebranded version of
    Apache. Choose whether you want a web server definition, so that this JVM
    receives requests forwarded from the HTTP server. Please see WebSphere's
    documentation for details on this. When finished, click *Next*. 

12. The wizard then shows you a summary of what you selected, enabling you to
    keep your choices or go back and change something. When you're satisfied, click
    *Next*. 

![Figure 1.10: The Summary page shows you what you selected, giving you the chance to go back and change something if it's not exactly what you want.](../../images/websphere-03-summary.png)

WebSphere then creates your profile and finishes with a message telling you the
profile was created successfully. You're now ready to install Liferay! 

## Copying portal dependencies [](id=copying-portal-dependencies)

Liferay ships with dependency .jars it needs to have on the global classpath.
These should be copied to WebSphere's global folder provided for this purpose: 

    [Install Location]/WebSphere/AppServer/lib/ext

If you have a JDBC database driver .jar, copy it to this location as well. 
Once you've copied the .jars, start the server profile you created for
Liferay. Once it starts, you're ready to configure your database. 

## Database Configuration [](id=database-configuration)

If you want WebSphere to manage the database connections, follow the
instructions below. Note this is not necessary if you're planning on using
Liferay's standard database configuration; in that case, skip this section.
You'll set your database information in Liferay's setup wizard after the
install. 

![Figure 1.11: WebSphere JDBC providers](../../images/02-websphere-jdbc-providers.png) 

1. Start WebSphere.

2. Open the Administrative Console and log in.

3. Click *Resources &rarr; JDBC Providers*.

4. Click *New*.

5. For name, enter the name of JDBC provider (e.g. *MySQL JDBC Provider*).

6. For Implementation Class Name, enter:

        com.mysql.jdbc.jdbc2.optional.MysqlConnectionPoolDataSource

7. Click *Next*.

8. Clear any text in classpath. You already copied the necessary `.jar`s to a
   location on the server's class path.

9. Click *Next*.

10. Click *Finish*.

11. Click *Data Sources* under *Additional Properties*.

12. Click *New*.

13. Enter a name: *liferaydatabasesource*.

14. Enter JNDI: `jdbc/LiferayPool`.

15. Everything else should stay at the default values. Save the data source.

16. When finished, go back into the data source and click *Custom Properties*
    and then click the *Show Filter Function* button. This is the second from
    last of the small icons under the *New* and *Delete* buttons.

17. Type *user* into the search terms and click *Go*.

    ![Figure 1.12: Modifying data source properties in WebSphere](../../images/02-modifying-data-source-properties-in-websphere.png) 

18. Select the user property and give it the value of the user name to your
    database. Click *OK* and save to master configuration.

19. Do another filter search for the url property. Give it a value that points
    to your database. For example, the MySQL URL would be:
    `jdbc:mysql://localhost/lportal?useUnicode=true&characterEncoding=UTF-8&useFastDateParsing=false`.
    Click *OK* and save to master configuration.

20. Do another filter search for the password property. Enter the password for
    the user ID you added earlier as the value for this property. Click *OK* and
    save to master configuration.

21. Go back to the data source page by clicking it in the breadcrumb trail.
    Click the *Test Connection* button. It should connect successfully.

Once you've set up your database, you can set up your mail session. 

## Mail Configuration [](id=mail-configuration)

If you want WebSphere to manage your mail sessions, use the following procedure.
If you want to use Liferay's built-in mail sessions, you can skip this section. 

1. Click *Resources &rarr; Mail &rarr; Mail Providers*.

2. Click the Built-In Mail Provider for your node and server.

3. Click *Mail Sessions* and then click the *New* button.

4. Give it a name of *liferaymail* and a JNDI name of `mail/MailSession`. Click
   *OK* and save to master configuration.

5. Click *Security &rarr; Global Security* and de-select *Use Java 2 security to
   restrict application access to local resources* if it is selected. Click
   *Apply*.

Great! Now you're ready to deploy Liferay. 

## Deploy Liferay [](id=deploy-liferay)

1. Click *Applications* &rarr; *New Application* &rarr; *New Enterprise
   Application*.

2. Browse to the Liferay `.war` file and click *Next*.

3. Leave *Fast Path* selected and click *Next*. Ensure that *Distribute
   Application* has been checked, and click *Next* again.

4. Choose the WebSphere runtimes and/or clusters where you want Liferay
   deployed. Click *Next*.

5. Map Liferay to the root context (/) and click *Next*. 

6. Ensure that you have made all the correct choices and click *Finish*. When
   Liferay has installed, click *Save to Master Configuration*.

You've now installed Liferay, but don't start it yet. If you wish to use PACL,
you have one more thing to configure. 

## Enabling Security for Portal Access Control Lists [](id=enabling-security-for-portal-access-control-lists)

In the administrative console, go to *Security* $rarr; *Global Security*.  Check
the box to enable Java 2 security, and click *Apply*. Save to the master
configuration. 

   ![Figure 1.13: Enabling security can be done by checking one box, but it still needs to be configured. ](../../images/websphere-05-liferay-enable-security.png)

Next, you need to configure security for the Liferay profile you created. This
requires editing a text file, which can be found nested several folders deep in
WebSphere's `profiles` directory. The exact path depends on how you've named
your profile, but it will be something like this: 

    [profile_root]/config/cells/[cell_name]/nodes/[node_name]/app.policy

First, in each existing `grant` section, replace the content with
`java.security.AllPermission;`. Then add the following lines to the bottom of
the file: 

    grant codeBase "file:${was.install.root}/lib/-" {
      permission java.security.AllPermission;
    };

    grant codeBase "file:${was.install.root}/plugins/-" {
      permission java.security.AllPermission;
    };

    grant codeBase "file:${server.root}/-" {
      permission java.security.AllPermission;
    };

Save the file. You should now stop the profile and restart it. Once it comes up,
you're ready to start Liferay. 

## Start Liferay [](id=start-liferay)

1. If you plan to use Liferay's setup wizard, skip to the next step. If you wish
   to use WebSphere's data source and mail session, create a file called
   `portal-ext.properties` in your Liferay Home folder. Place the following text
   in the file: 

        jdbc.default.jndi.name=jdbc/LiferayPool
        mail.session.jndi.name=mail/MailSession
        setup.wizard.enabled=false

2. Select the Liferay application and click *Start*. 

    ![Figure 1.14: While Liferay is starting, WebSphere displays this spinny little graphic. Don't watch it too closely, or you might get hypnotized.](../../images/websphere-04-liferay-starting.png)

3. In the setup wizard, select and configure your database type. Click *Finish*
   when you're done. 

    Liferay then creates the tables it needs in the database. 

Congratulations! You've installed Liferay on WebSphere!

# Using Liferay's setup wizard [](id=using-liferays-setup-wizard)

To make it easy to configure Liferay optimally for your use, the first thing you
see when browsing to your newly installed Liferay bundle is a setup wizard. This
gives you a convenient way to configure Liferay for your purposes. 

There are three sections of the wizard: the portal, the administrator and the
database. For the portal, you need to supply the following information: 

**Portal Name:** the name of the web site you're powering with Liferay. In this
book, we build a website for a lunar colony. This site is called Lunar Resort, 
so we've supplied `Lunar Resort` in the screenshot below. 

**Default Language:** choose the default locale where your site resides. 

For the administrator, you need to supply the following information: 

**First Name:** the first name of the user that has the administrator account. 

**Last Name:** the last name of the user that has the administrator account. 

**Email:** the email address of the user that has the administrator account. 

![Figure 1.15: Supply the information for your site and your site's administrative account in the setup wizard.](../../images/setup-wizard-1.png)

Liferay supports just about all the leading databases today: 

* DB2

* MySQL

* Oracle

* PostgreSQL

* SQL Server

* Sybase

In addition to these, Liferay also supports a few embedded databases that are
designed for development. We haven't listed these here because you're setting up
a production Liferay server, and you shouldn't use an embedded database with a
production box. 

Before you fill out the database section of Liferay's setup wizard, you should
already have created a database for Liferay to use. If you haven't, please see
the section above entitled *Liferay's Database*. 

Open the Database section of the wizard. From the select box, choose your
database. You'll see a form which lets you specify the URL to the database, the
driver class, and the user credentials (see below). Most of this is filled out
already; all you should need to do is supply the name of your database and the
server it's running on, as well as the user credentials. 

![Figure 1.16: Fill out the information for your database. We've chosen MySQL in this example and have created a database called *lunar_resort* to hold our Liferay data.](../../images/setup-wizard-2.png)

Once you've filled out the form, click *Finish Configuration*. You'll see a
message stating that Liferay is being installed as it creates the tables and
data it needs in its database. When it's finished, it tells you the location of
the configuration file (`portal-setup-wizard.properties`) where it saved all
your settings. From here, you can go to your home page. 

Congratulations! You've just installed Liferay Portal! The next thing you need
to do is set up your mail configuration, so Liferay can send email notifications
to users. This is covered in the Manual Configuration section below. 

+$$$

**Tip:** The wizard is an extremely helpful tool,
especially if you're setting up Liferay for the first time or creating a
completely fresh portal instance. If you're a Liferay veteran and you already
have your database information and various properties set up, you can skip the
wizard by adding this line to your *portal-ext.properties* file:

    setup.wizard.enabled=false

$$$

+$$$

**Tip:** In Liferay 6.2, the admin user test@liferay.com
is created by the setup wizard even when a different user is specified. This
means that two admin users are created: test@liferay.com and the specified user.
Unless you're just installing Liferay for testing purposes, you should
deactivate the test@liferay.com user after your database has been created.

$$$

# Manual Configuration [](id=manual-configuration)

You don't have to use the setup wizard to configure Liferay. The setup wizard
behind the scenes creates a configuration file that you can create manually.
Create a text file called `portal-ext.properties` in your Liferay Home folder.
This file overrides default properties that come with Liferay. The first setting
you'll override is the default configuration that points Liferay to the embedded
HSQL database.

As stated above, there are two ways to set up the connection:

-   Use the built-in connection pool.

-   Use your application server's connection pool.

Use the setup wizard if you're using the built-in connection pool. If you want
to use your application server's pool, continue with this procedure.

If you want to use your application server's connection pool, you will have to
create one in your application server that points to your database. The
connection pool should be called `jdbc/LiferayPool`. In the application server
sections above, there are instructions for how to do this on each application
server that Liferay supports. To tell Liferay to use your `jdbc/LiferayPool`
connection pool, add the following directive to your `portal-ext.properties`
file:

    jdbc.default.jndi.name=jdbc/LiferayPool

Next, install Liferay according to the instructions for your application server.
Once it's installed, you can set up the mail configuration. 

For mail, you can use Liferay's control panel to create the configuration and
this is the recommended way. Go to *Control Panel &rarr; Server Administration
&rarr; Mail* and enter your settings for your mail session settings. If,
however, you're setting up a lot of Liferay machines and they're all going to
have similar mail configurations, it's easier to do the configuration once and
then copy the configuration file to multiple machines. In this case, you'll want
to use the `portal-ext.properties` file. To use the built-in mail session, use
the following properties and customize their values for your environment: 

    mail.session.mail.pop3.host=localhost
    mail.session.mail.pop3.password=
    mail.session.mail.pop3.port=110
    mail.session.mail.pop3.user=
    mail.session.mail.smtp.auth=false
    mail.session.mail.smtp.host=localhost
    mail.session.mail.smtp.password=
    mail.session.mail.smtp.port=25
    mail.session.mail.smtp.user=
    mail.session.mail.store.protocol=pop3
    mail.session.mail.transport.protocol=smtp
 
To use your application server's mail session, create it first. Then specify it
in the `portal-ext.properties` file: 
    
    mail.session.jndi.name=mail/MailSession

When you've finished, save the file.

All the instructions above assumed that you wanted to install Liferay Portal at
the root context of your server. But what if that isn't the case? Next, you'll
see how to use a different context for Liferay Portal.

# Making Liferay Coexist with Other Java EE Applications [](id=making-liferay-coexist-with-other-java-ee-applications)

Liferay Portal by default is configured to sit at the root (i.e., `/`) of your
application server. Dedicating your application server to running only Liferay
Portal is a good practice, allowing for separation between your portal
environment and your web application environment. This is generally a best
practice for portals, which by definition are application development platforms
in and of themselves. For that reason, your instance of Liferay is likely to be
hosting many applications and even integrating several of them together on a
single page. For this reason, you should design your system so your portal
environment has all the resources it needs to do this. Configuring it so it is
the sole consumer of any other `.war` files that get deployed to the application
server helps to make sure your system performs optimally.

If, however, you want Liferay to share space on an application server with other
applications, you can. In this instance, you may not want to make Liferay the
default application in the root context of the server. If you want to install 
Liferay in a context other than the root context, follow the instructions from 
your app server vendor. No additional steps are necessary.

Now that you have Liferay installed in the context you wish, you'll want to
understand Liferay's releases and the process for keeping your installation up
to date. We'll spend a brief amount of time on that next.

# Understanding Liferay's Releases [](id=understanding-liferays-releases)

Which version of Liferay should you install? That's an easy question to answer:
if this is a new installation, we recommend you install the latest version
available. If you want to understand how Liferay releases work, read on.
Otherwise, you can skip this section for now and read it later. 

Liferay's release process follows a prescribed structure that is consistent from
one release to the next. Each release has a specific number sequence attached to
it, signifying the type of release it is: major, minor or maintenance release.
Each release also has a term attached to it to indicate its intended level of
quality.

EE subscribers have access to additional maintenance releases, along with 
specific *Fix Packs* and *Hot Fixes* that make applying updates to production
environments safer and faster.

Let's start with an explanation of Liferay's version structure. Liferay versions
are organized in a straightforward numerical system consisting of a three digit
number. For example, 6.1.2. These numbers represent the type of the release:
Major.Minor.Maintenance.

A change in the first or second digit of the version (e.g., 6.x to 7.x or 6.1 to
6.2) is a major or minor release. This means that there are changes in
functionality or new functionality based on high demand. There are new features
and bug fixes from prior releases. There are also architectural changes, changes
to APIs (as part of the deprecation process), or changes to internal schema for
frameworks such as Service Builder, because new features have been added.
Customizations may be affected when installing. Customers should leverage the
upgrade tools and documentation.

A change in the third digit of the version scheme (e.g, 6.2.1 to 6.2.2) is a
maintenance release. This means that each maintenance release provides an
improved level of security and reliability. Customizations are generally safe,
but we recommend doing a review. No new features are included. 

These rules are relaxed when a minor or major release is still in beta quality.

Now let's delve into the evolution of versions.

## Release Process [](id=release-process)

Each release of Liferay has a surname that specifies its expected quality.  This
is needed because pre-releases of Liferay look very much like maintenance
releases when viewed solely through their version numbers. The surname in
general replaces the third digit in the version, but is visible through the logs
and administration UIs. Here is a description of each surname and what it means:

- **Milestone** and **Beta:** (6.2 M1, 6.2 B1, 6.2 B2, ...) There can be zero or
  more of these types within each minor or major release.  These releases are
  meant for testing and to provide Liferay feedback through the beta testing
  category in the forums. There will likely be major changes in milestone
  releases, but beta releases are considered "feature complete" and should have
  only bug fixes. 

- **Release Candidates:** (6.2 RC1, 6.2 RC2) There can be zero, one, or more of
  these right after the beta releases. These releases appear near the end of the
  release process and are candidates for release. As such, they should have
  minimal to no bugs, but because they are very new, some minor bugs may have
  slipped by. 

- **General Availability:** (6.2 GA1, 6.2 GA2, ....) There can be one or more of
  these releases. A General Availability release is a re-label of the last
  release candidate, based on internal testing and feedback from beta
  testers. These releases are stable and are made available via Liferay's
  Downloads page for CE and on the Customer Portal for EE. 

Armed with this information, here's our advice for how to manage your Liferay
installation. 

- When starting a new project, always use the latest stable version available;
  that is, the latest available GA. At the time of writing, the most recent
  version is Liferay CE 6.2 GA1 (6.2.0) or Liferay EE 6.1 GA2 (6.2.00). 

- Always update to the latest maintenance release available for the functional
  version (major or minor) that you are using. For example, if you started your
  project with Liferay 6.2.0 GA1, it is recommended that you switch to GA2
  if/when it becomes available to take advantage of bug fixes and improvements. If
  you have a subscription, you can benefit from the fixes faster by requesting
  fix packs and hot fixes from the support team.

- You are always welcome to use any preview, beta or release candidate. In
  fact, that's why they exist--so as many people as possible start using it and
  provide us their feedback. Please note, we do not recommend using pre-releases
  (milestones, betas, or release candidates) in production. You may not want to
  use these releases even during development if you have tight deadlines, since
  you may hit some unexpected bugs. 

- Plugins that work in any GA or fix pack version will work in any later
  maintenance release. That is, a plugin developed for Liferay 6.2 GA1 will also
  work in Liferay 6.2 GA2 or a GA2 fix pack. 

For more details on updating Liferay Portal, see Upgrading Liferay in chapter
17.

Liferay Portal is a very flexible application that runs well on several
different server environments. It's simple to install and follows a systematic
versioning system that makes it easy to keep current with the latest updates.
The strength of the Liferay community helps detect potential issues early that
are then reported through the forums and are later fixed in a series of
maintenance releases.

# Summary [](id=summary-12)

This chapter is a guide to everything about installing Liferay. Whether you
choose a Liferay bundle or an existing application server, Liferay Portal
integrates seamlessly with your enterprise Java environment. It is supported on
more application servers than any other portal platform, allowing you to
preserve your investment in your application server of choice or giving you the
freedom to move to a different application server platform. Liferay is committed
to providing you this freedom: we have 500 test servers certifying our builds
with roughly 10,000 tests per version of Liferay Portal. Each of those tests are
run on all of our different supported combinations of application servers,
databases and operating systems. Because of this, you can be sure we are
committed to supporting you on your environment of choice. You can feel safe
knowing you have the freedom to use the software platform that is best for your
organization and that Liferay Portal runs and performs well on it.

# Advanced Portal Operation [](id=advanced-portal-operation)

In this chapter, you'll find several advanced features of Liferay Portal,
including portal maintenance, backup, and logging. You'll also learn how to
access Liferay's web services remotely. It's generally not much more complicated
to maintain a running Liferay instance than it is to maintain the application
server upon which it's running. However, Liferay provides tools for logging,
patching, and upgrading Liferay that you should know how to use.  It's also
important to follow secure backup procedures to protect your Liferay instance's
source code, database, and properties files.

We'll discuss the following topics in this section:

-   Backing Up a Liferay Installation
-   Changing Logging Levels
-   Patching Liferay
-   Upgrading Liferay
-   Sandboxing Portlets to Ensure Portal Resiliency
-   Using Web Services for Remote Portlets (WSRP)
-   Remotely Accessing Liferay Services

First, you'll learn how to back up a Liferay installation. 

# Backing up a Liferay Installation [](id=backing-up-a-liferay-installation)

Once you have an installation of Liferay Portal running, you'll want to have
proper backup procedures in place in case of a catastrophic hardware failure of
some kind. Liferay isn't very different from any other application that may be
running on your application server. Nevertheless, there are some specific
components you should include in your backup plan.

## Backing up Source Code [](id=backing-up-source-code)

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

## Backing up Liferay's File System [](id=backing-up-liferays-file-system)

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

## Backing up Liferay's Database [](id=backing-up-liferays-database)

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

# Liferay's Logging System [](id=liferays-logging-system)

Liferay's logging system uses Log4j extensively to implement logging for nearly
every class in the portal. If you need to debug something specific while the
system is running, you can use the control panel to set logging levels by class
dynamically.

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

![Figure 4.7: Log levels can be dynamically changed at runtime whenever you need to debug an issue. ](../../images/maintaining-log-levels.png) 

Be careful when you do this. If you set the log level to DEBUG somewhere near
the top of the hierarchy (such as `com.liferay`, for example), you may wind up
with a lot of messages in your log file. This could make it difficult to find
the one you were looking for, and causes the server to do more work writing
messages to the log. 

If you want to set the log level for one of your own classes in a deployed
plugin, you can register that class with Liferay to control the log levels
more easily, so long as your class uses Liferay's logging system to do its
logging.

You will first need to import interfaces and implement logging in your
class, with statements such as these (taken from Liferay's `S3Store`
class):

	import com.liferay.portal.kernel.log.Log;
	import com.liferay.portal.kernel.log.LogFactoryUtil;
	
	...

	private static Log _log = LogFactory.getLog(S3Store.class);

You would then use this `_log` variable to create log messages in your code
for the various logging levels:

	_log.error(s3se.getMessage());

To enable your logging messages to appear in your server's log file via the
control panel, click the *Add Category* tab on the same *Log Levels* page.

![Figure 4.8: Adding your own logging classes is easy. To add a logging class, just specify it in this field.](../../images/maintaining-add-log-category.png) 

You'll see you can add a logging category. Put in the fully qualified name of
your class or of the package that contains the classes whose log messages you
want to view, choose a log level, then click the *Save* button. You will now
start to see log messages from your own class or classes in the server's log
file.

Logs are great for figuring out issues in production. But what if Liferay
contacts you via its support channel with a bug fix or a security enhancement?
Read on to learn how to patch Liferay.

+$$$

**Note**: Logging changes within the control panel don't persist across
restarts. If you want to make your changes persistent, you can create a
`META-INF/portal-log4j-ext.xml` file and deploy it, using the `portal-log4j.xml`
file from Liferay's source as a guide. 

$$$

# Patching Liferay [](id=patching-liferay)

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

## Installing the patching tool [](id=installing-the-patching-tool)

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

## Installing patches [](id=installing-patches)

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

### Handling hot fixes and patches [](id=handling-hot-fixes-and-patches)

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

### Fix pack dependencies [](id=fix-pack-dependencies)

Some fix packs require other fix packs to be installed first. If you attempt to
install a fix pack that depends on another fix pack, the patching tool will
notify you of this so you can go to the customer portal and obtain the fix pack
dependency. Once all the necessary fix packs are available in the `patches`
folder, the patching tool will install them. 

The patching tool can also remove patches. 

## Removing or reverting patches [](id=removing-or-reverting-patches)

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

## Using profiles with the patching tool [](id=using-profiles-with-the-patching-tool)

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

# Upgrading Liferay [](id=upgrading-liferay)

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

## Preparing for an Upgrade [](id=preparing-for-an-upgrade)

Before you begin upgrading Liferay to a new version, consider your current
Liferay installation. If you're running Liferay EE, is it patched to the most
recent Service Pack level? If not, refer to the section on Patching Liferay
before you upgrade; the upgrade process is designed to occur on a fully patched
Liferay. If you're running Liferay CE, make sure you have the latest GA
release.

Now that Liferay is updated to the latest release of its current version, the
first upgrade task is to size up your situation. Do this by asking
yourself a few questions from the chart below. First: What version of Liferay
was the first version you installed? If it was 6.0 or 6.1, there are fewer
steps, because you won't have to worry about migrating your permission
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

![Figure 4.9: Use this flowchart to determine the steps to take for your upgrade.](../../images/upgrade-decisions.png)

The flowchart illustrates the procedure described above. Use it to determine
your course of action for the upgrade. Each step is described fully below so
that you can perform your upgrade as efficiently as possible. Be sure to test
the upgrade in a non-production environment before upgrading your production
Liferay instance. 

+$$$

**Note**: In Liferay 6.2, the Global repository that was used to store Web
Content and Documents and Media is now its own site with the reserved friendly
URL `/global`; upgrading to Liferay 6.2 will fail if any sites are using the
same URL. Before upgrading to Liferay 6.2, make sure no current friendly URL
violates the restriction. 

$$$

Let's look at the preparatory tasks you should perform one by one. 

## Migrate to Algorithm 6 [](id=migrate-to-algorithm-6)

If your Liferay installation has existed for a while, you might be on a
different permission algorithm than the one that's available in Liferay Portal
6.1. Permission algorithms 1-5 were deprecated in Liferay Portal 6.0 and
were removed in 6.1, which means you must migrate *before* you upgrade.

+$$$

**Important**: Before upgrading a Liferay instance
 that's using one of permissions algorithms 1-5, you *must* migrate to
 permissions algorithm 6 before attempting to upgrade to Liferay 6.2. You can't
 use the seamless upgrade feature to upgrade directly to 6.2 because Liferay's
 permissions migration tool is not included with Liferay 6.2. Follow the
 instructions in this section to migrate to permissions algorithm 6 before
 continuing with your upgrade.

$$$

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

![Figure 4.10: Update your permissions algorithm by clicking the *Execute* button.](../../images/17-convert-permissions-algorithm.png)

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

## Migrate Your Image Gallery Images [](id=migrate-your-image-gallery-images)

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

## Review the Liferay 6.2 Properties Defaults [](id=review-the-liferay-6-2-properties-defaults)

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

## Catalog All Installed Plugins [](id=catalog-all-installed-plugins)

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

## Upgrade Choices: Upgrade a Bundle or Upgrade Manually [](id=upgrade-choices-upgrade-a-bundle-or-upgrade-manually)

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

### Upgrading a Bundle [](id=upgrading-a-bundle)

If you're running a Liferay bundle, the best way to do the upgrade is to follow
the steps below. The new Liferay is installed in a newer version of your bundle
runtime. For example, the Liferay bundle for 6.1 uses a different version of
Tomcat than the Liferay bundle for 6.2. We generally recommend you use the
latest version of your runtime bundle, as it will be supported the longest. 

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
      are designed for Liferay 6.2. If you have your own plugins, your
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

### Upgrading Manually [](id=upgrading-manually)

Running a manual upgrade is almost as easy as upgrading a bundle: 

   1. Verify your application server is supported by Liferay. You can do this by
      viewing the appropriate document on the Customer Portal (EE), in chapter
      14 (because there are installation instructions for it), or on liferay.com
      (CE).  If your application server isn't supported by Liferay 6.2, *do not
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
      designed for Liferay 6.2. If you have your own plugins, your development
      team will need to migrate the code in these ahead of time and provide .war
      files to you. 

   8. Browse around in your new installation and verify everything is working.
      Have your QA team test everything. If all looks good, you're finished. 

That's all there is to it. Most everything is handled by Liferay's upgrade
procedure. Note as stated above, if you have to upgrade over several Liferay
versions, you will need to repeat these steps for each major release. 

## Post-Upgrade Tasks [](id=post-upgrade-tasks)

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

# Sandboxing Portlets to Ensure Portal Resiliency [](id=sandboxing-portlets-to-ensure-portal-resiliency)

![EE Only Feature](../../images/ee-feature-web.png)

The performance, health, and stability of a portal deployment is heavily
dependent upon the portlet modules deployed to it. If one portlet leaks memory
or is extremely slow, your entire portal can crash due to a dreaded
`OutOfMemoryError` or can slow to a crawl. 

Liferay Portal 6.2 introduces a sandboxing feature that enables you to run
troublesome portlets in their own container (or "sandbox"), reducing any adverse
impact they may have on the health and stability of your portal. The feature is
available in Liferay's Sandbox App. The app lets you create sandboxes to run
portlets in separate JVMs, freeing your portal's JVM from the resource
consumption of those portlets. We refer to the portal's JVM instance as the
*Master Portal Instance* (*MPI*) and the sandbox JVMs as *Slave Portal
Instances* (*SPIs*). Since SPIs run on the same host as the MPI, communication
between them is very fast. The fact that sandboxed portlets are running in SPIs
is transparent to your users. Portal users continue to use these portlets as
they normally do.

As a portal administrator, you'll be pleased to know that the app not only gives
you the ability to section off plugins into SPIs, but also gives you the
means to revive a SPI. The Liferay Sandbox App comes with a *SPI Administration*
UI that lets you create, start, stop, and restart SPIs. In addition, it lets you
configure options to restart SPIs that terminate unexpectedly, automatically. 

The sandboxing feature has two limitations. First, only portlet and web
plugins can be deployed on an SPI. Second, the portal ignores SPI portlet
implementation classes that are not remote-safe. Implementation classes (such as
asset renderers and pollers) that register with the portal fall into this
category and are ignored by the portal. Therefore, the sooner you test and
resolve any performance issues in such SPI portlets, the sooner you can deploy
them back onto the Master Portal Instance to leverage such implementation
classes in those portlets. 

The Liferay Sandbox App is available on the Liferay
[Marketplace](http://www.liferay.com/marketplace). You can purchase, install,
and deploy the app as described in this guide's chapter on [Leveraging the Liferay Marketplace](https://www.liferay.com/documentation/liferay-portal/6.2/user-guide/-/ai/leveraging-the-liferay-marketplace-liferay-portal-6-2-user-guide-14-en). 

Before creating and using sandboxes, we must enable the portal's resiliency
functionality and optimize the database connection settings for your sandboxes. 

## Configuring the Portal for Sandboxing [](id=configuring-the-portal-for-sandboxing)

The two types of portal properties you must modify for your portal to use
sandboxing are the [Portal Resiliency](http://docs.liferay.com/portal/6.2/propertiesdoc/portal.properties.html#Portal%20Resiliency) 
properties and [Database Connection](http://docs.liferay.com/portal/6.2/propertiesdoc/portal.properties.html#JDBC) 
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
sandbox app. Then navigate to the Control Panel to see the *SPI Administration*
link displayed in the *Configuration* section.

![Figure 4.11: The Sandboxing App comes with an *SPI Administration* UI that's accessible from the portal's Control Panel.](../../images/sandboxing-spi-admin-available-in-control-panel.png)

Click the *SPI Administration* link to start creating SPIs for running new or
troublesome portlets. 

## Creating an SPI [](id=creating-an-spi)

You can create and administer SPIs from the SPI Administration page accessible
in the Configuration section of the Control Panel.

To add a new SPI, simply click on the *Add SPI* button. 

![Figure 4.12: Click the *Add SPI* button to define a new "sandbox" (SPI).](../../images/sandboxing-add-spi-button.png)

The Add SPI panel divides the SPI's fields into General, SPI Configurations, and
Advanced Configurations sections. 

![Figure 4.13: You can name your SPI, describe it, and configure it from the *Add SPI* panel.](../../images/sandboxing-add-spi-general-details.png)

In the General section, you must provide a unique name for the SPI and describe
the SPI. 

The SPI Configurations contains some of the most important settings for the SPI.
It is broken into 4 sections: SPI Runtime, SPI Applications, Java Runtime,
and Recovery Options. 

![Figure 4.14: From the *SPI Runtime* section of your SPI, you can set its maximum number of working threads based the number of threads available to your portal that you'd like to designate for the SPI. You must also set a unique connector port for the SPI.](../../images/sandboxing-configure-spi-runtime.png)

Let's set the SPI's runtime options first. 

**Maximum Worker Threads:** Enter the maximum number of worker threads that the
SPI can use to process requests. By default, it is set to `100`. You should tune
this value, however, according to the number of threads allocated in the
application server hosting your portal. This parameter functions similarly to
the settings in most JEE application servers. 

**Connector Port:** Enter the port number on which the SPI listens for requests
from Liferay Portal. Each SPI runs on an embedded Apache Tomcat server instance.
Each of the portal's SPIs must use a unique port and you must ensure that no
other processes are using that port. 

+$$$

**Note**: The SPIs and MPI serialize parameters
 and return values passed between them. The sandboxing feature uses an IPC
 framework called
 [Intraband](http://docs.liferay.com/portal/6.2/propertiesdoc/portal.properties.html#Intraband),
 to support communication between the MPI and SPIs. 

$$$

Next, we'll pull those troublesome apps into the SPI. 

The SPI Applications panel provides a way to select applications to be hosted
in the SPI. All requests that call these applications are processed by the
SPI. 

![Figure 4.15: All the portlets and web plugins that you've installed on your portal are available for moving into a SPI.](../../images/sandboxing-configure-spi-apps.png)

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

The **JVM Arguments** panel allows you to specify arguments to be passed to the
Java Virtual Machine (JVM) running the SPI. These include memory settings,
debugger options, etc. If you do not specify any values, the system
automatically uses these values:

    -Xmx512m -XX:PermSize=200m

Consider the JVM's performance tuning recommendations when setting these
arguments. You can also consult the [*Liferay Deployment Checklist*](https://www.liferay.com/marketplace/isvs?p_p_auth=VgLAX0A7&_101_assetEntryId=35608452&p_p_lifecycle=0&p_p_id=101&p_p_state=maximized&_101_struts_action=%2Fasset_publisher%2Fview_content&redirect=https%3A%2F%2Fwww.liferay.com%2Fdocumentation%2Fliferay-portal%2F6.2%2Fuser-guide%3Fp_p_id%3D3%26p_p_lifecycle%3D0%26p_p_state%3Dmaximized%26p_p_mode%3Dview%26_3_entryClassName%3Dcom.liferay.portlet.documentlibrary.model.DLFileEntry%26_3_modifiedselection%3D0%26_3_documentsSearchContainerPrimaryKeys%3D19_PORTLET_35946202%252C19_PORTLET_35722876%252C19_PORTLET_35809176%252C19_PORTLET_35954267%252C19_PORTLET_35824382%252C19_PORTLET_35857084%252C19_PORTLET_35782527%252C19_PORTLET_35810672%252C19_PORTLET_35958625%252C19_PORTLET_35746448%252C19_PORTLET_35972499%252C19_PORTLET_35799656%252C19_PORTLET_35845480%252C19_PORTLET_35773034%252C19_PORTLET_35880698%252C19_PORTLET_35987674%252C19_PORTLET_35866420%252C19_PORTLET_35916979%252C19_PORTLET_35915417%252C19_PORTLET_35815297%26_3_keywords%3DLiferay%2BDeployment%2BChecklist%26_3_format%3D%26_3_modifiedfrom%3D%26_3_formDate%3D1395500651344%26_3_modified%3D%26_3_assetCategoryTitles%3D%26_3_groupId%3D14%26_3_modifiedto%3D%26_3_cur%3D1%26_3_struts_action%3D%252Fsearch%252Fsearch%26_3_assetTagNames%3D&p_p_mode=view&_101_type=document)
for guidance on setting the JVM arguments. 

In development, you may also choose to add debug settings to the JVM arguments. 

![Figure 4.16: You can specify Java runtime options optimal for your SPI's apps.](../../images/sandboxing-configure-spi-java-runtime.png)

In the event that the SPI terminates unexpectedly, you may want to consider
automatically restarting it and having the Sandboxing App send notifications to
you and other stakeholders. 

The Sandboxing App lets you configure email notifications for when an SPI crashes
and lets you configure for the app to automatically restart the SPI. 

![Figure 4.17: You can have the SPI notify you and others if the SPI's apps crash it. And you can configure the maximum number of times to automatically revive the SPI, before requiring manual intervention.](../../images/sandboxing-configure-spi-recovery.png)

**Use Default Notification Options:** Select this checkbox to use the globally
defined notification options. Otherwise the notification options specified in
this panel are used. 

**Notification Recipients:** Enter a set of comma-delimited email addresses of
people to be notified in the event that the SPI fails. This option is disabled
if the *Use Default Notification Options* checkbox is selected. 

**Use Default Restart Options:** Select this checkbox to use the globally
defined restart options. Otherwise the restart options specified in this panel
are used. 

**Maximum Restart Attempts:** Enter the maximum number of times to attempt
restarting the SPI in the event that the SPI fails. Once Liferay reaches the
maximum number of restart attempts for the SPI, it refrains from restarting the
SPI. At that point, manual operator intervention is required to restart it.
This option is disabled if the *Use Default Restart Options* checkbox is
selected. 

### Advanced Configurations [](id=advanced-configurations)

The Advanced Configurations section contains a series of optional parameters
that should rarely be modified. 

**Java Executable:** Enter the path to your JVM, specifically the path to your
Java executable file (i.e., the `java` or `java.exe` file). You should generally
not modify this value, assuming your Java executable is in your system's `PATH`. 

**SPI Ping Interval:** Enter the number of milliseconds to wait between pings
that the SPI sends to the portal to ensure the portal is alive. Pinging the
portal prevents SPIs from becoming "zombie" processes, in the event that the
portal terminates unexpectedly. 

**SPI Register Timeout:** Enter the number of milliseconds allotted for the SPI
to start. The default value is `300000` milliseconds (`300` seconds). This
should be ample time for a SPI to start. However, if you have a large number
of applications in the sandbox, or the server has an insufficient CPU or
insufficient memory resources, you may need to increase the amount of time. 

**SPI Shutdown Timeout:** Enter the maximum amount of time (in milliseconds)
that the SPI should need to gracefully shutdown. As with the SPI Register
Timeout, this value may need to be increased on slower or overloaded machines. 

You may also choose to allocate certain embedded Liferay functions (e.g., blogs,
bookmarks, etc.) to an SPI. This is generally not recommended, but is made
possible by the Sandboxing App. The SPI Core Applications panel lets you drag
apps onto the SPI in the same way you can with the SPI Applications panel. 

Now that you know how to add a SPI and configure it properly, let's learn how to
operate the SPI. 

## Starting, Stopping, and Modifying an SPI [](id=starting-stopping-and-modifying-an-spi)

The Portal automatically starts SPIs on startup. However, when you first add a
new SPI, you must start it manually. 

![Figure 4.18: When you first create a SPI, you'll need to start it manually. You can edit and delete SPIs that are not running.](../../images/sandboxing-start-stop-spi.png)

Once successfully started, you can stop or restart a SPI. You can't, however,
delete an SPI that is running. You must first stop the SPI. 

You can edit an SPI's configuration too. Configuration changes made to a running
SPI take effect after it's restarted. 

![Figure 4.19: SPI configuration modifications only take affect after the SPI has been restarted.](../../images/sandboxing-modify-spi.png)

As you can see, operating SPIs is straightforward and easy to do. 

If you have multiple SPIs, you may want to use global settings to configure the
default options for them. Let's consider how to configure global settings for
your SPIs next. 

## Configure Global Settings [](id=configure-global-settings)

The SPI Administration console allows you to configure a series of global
settings. You can access them by clicking on the configuration gear icon as
shown in the figure below. 

![Figure 4.20: Click on the global settings gear icon, in the upper right corner of the SPI Administration console, to set default configuration options for all of the portal's SPIs.](../../images/sandboxing-global-settings.png)

Once you've opened the configuration panel, the SPI Administration console
enables you to configure global notification and set restart options for your
SPIs. 

Note that option values explicitly configured in an SPI take precedence over
the global settings with respect to that SPI.

![Figure 4.21: You can set default notification and restart options for all of the portal's SPIs.](../../images/sandboxing-global-recovery-options.png)

Let's look at the global notification options first. 

The notification options allow you to configure both the notfication email
content and specify the recipients of the notification email. These values are
used by all defined SPIs. 

![Figure 4.22: Via the SPI Administration's global configuration panel, you can set specific email notification options, including the sender's address, the sender's name, default recipients, a default email subject template, and a default email body template.](../../images/sandboxing-global-notification-options.png)

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

The restart options section allows you to configure how many times each SPI
is restarted in the event that it terminates unexpectedly. In the example
below, all SPIs are restarted 3 times before requiring administrator
intervention to restart them. 

![Figure 4.23: You can set default restart options for your SPIs from the SPI Administration's global configuration panel.](../../images/sandboxing-global-restart-options.png)

Let's recap what Liferay's Sandboxing App does for you. It lets you isolate
portlets and web plugins that are known troublemakers or that you are simply
just unsure about. You put them in their own sandbox JVM (or SPI), so they can
still be used in your portal but are kept out of your portal's JVM. As an
administrator, you can group plugins into SPIs and configure each SPI's runtime,
notification, and recovery options. In addition, you can configure global
default settings for your portal's SPIs. With the Sandboxing App, you can ensure
your portal's resiliency while leveraging all the portlets (even leaky ones)
that your users require. 

Liferay Portal can serve portlets that are installed on the system, or it can
serve portlets installed on another portal server. This is called Web Services
for Remote Portlets. Have you ever wondered how to use WSRP in Liferay? We'll
cover this next!

# Using Web Services for Remote Portlets (WSRP) [](id=using-web-services-for-remote-portlets-wsrp)

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

## WSRP with Liferay [](id=wsrp-with-liferay)

Liferay provides a deployable WSRP portlet that supports the 1.0 and 2.0
specifications. The portlet is available from Liferay Marketplace as a CE or EE
app. Once you've downloaded and installed the WSRP app, you have instant access
to the portlet by navigating to the Control Panel and, under *Apps*, selecting
*WSRP*.

Liferay Portal can be used as a WSRP producer or consumer. As a producer, it
hosts portlets that are consumed by other portal servers (Liferay or
non-Liferay) acting as WSRP consumers. The image below illustrates WSRP
producers and consumers and how they interact.

![Figure 4.24: Portlets can interact with other portlets located on a different portal server using WSRP.](../../images/wsrp-illustration.png)

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

+$$$

**Tip**: If you're following along with this
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

$$$

To create a producer, go to the *Producers* tab and click *Add Producer*. Give
your producer a name and choose the appropriate version of WSRP to use. Liferay
displays a list of available portlets your producer can use. For demonstration
purposes, select the Hello World portlet and click the *Save* button. The portal
generates a WSDL document to define your producer. To view the WSDL document,
click the URL link provided.

![Figure 4.25: You can view the WSDL document for your producer by clicking the provided URL.](../../images/wsdl-url.png)

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

## Creating Custom Remote Portlets [](id=creating-custom-remote-portlets)

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

# Remotely Accessing Liferay Services [](id=remotely-accessing-liferay-services)

Liferay includes a utility called the *Service Builder* which is used to
generate all of the low level code for accessing resources from the portal
database. This utility is further explained in the [*Liferay Developer Guide*](http://www.liferay.com/documentation/liferay-portal/6.2/development) and
in [*Liferay in Action*](http://manning.com/sezov), but it is mentioned here
because of its feature which generates interfaces not only for Java code, but
also for web services and JavaScript. This means that the method calls for
storing and retrieving portal objects are all the same, and are generated in the
same step.

![Figure 4.26: Liferay SOA's first layer of security is its properties files.](../../images/liferay-soa-first-layer.png)

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

![Figure 4.27: Liferay SOA's second layer of security is its permissions system.](../../images/liferay-soa-second-layer.png)

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

## Accessing Liferay's JSON Web Services [](id=accessing-liferays-json-web-services)

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

## Accessing Liferay's WSDL [](id=accessing-liferays-wsdl)

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

# Summary [](id=summary-15)

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

# Using Scripting for Advanced Flexibility [](id=using-scripting-for-advanced-flexibility)

Liferay provides a robust script engine that can be used to interpret scripts in
Beanshell, Javascript, Groovy, Python, and Ruby. The script engine came out of
Liferay's involvement with the [Romulus project](http://www.ict-romulus.eu). It
was originally developed to support non Java-based portlets, but has now been
extended to handle a lot more. For example, when Liferay's workflow framework
was introduced, the script engine was leveraged to support the execution of
scripts from within a workflow module. A script console is now included in the
Server Administration portlet in the control panel. It allows system
administrators an easy way to execute scripts to perform repetitive user
maintenance operations, bulk manipulations using the Liferay API to ensure
consistency, or even system level operations.

This chapter helps you to understand Liferay's script engine and covers the
following topics: 

- Accessing Liferay's service layer from a script

- Running scripts from the script console

- Using the script engine with workflow

- Leveraging custom Java tools in the script engine

The most common thing you'll want to do is access Liferay's services. If you
have any familiarity with Liferay's developer tools and API, this will be a snap
for you. To access Liferay's scripting console, navigate to the Control Panel,
click on *Server Administration* under the Configuration heading, then open the
*Script* tab.

# Accessing Liferay Services [](id=accessing-liferay-services)

In many cases, you'll want to interact with one of Liferay's many services. This
is possible from all of the scripting languages supported, but the syntax is a
little different for each language. 

To illustrate the correct syntax for interacting with Liferay services, let's
look at a simple example that uses the `UserLocalService` to retrieve a list of
users and then prints their names to the log file. We'll initially implement the
example in Java pseudo-code. 

	import com.liferay.portal.model.User;
	import com.liferay.portal.service.UserLocalServiceUtil;
	import java.util.List;
				.
				.
				.
				
	int userCount = UserLocalServiceUtil.getUsersCount();
	List<User> users = UserLocalServiceUtil.getUsers(0, userCount);
	
	for (User user:users) {
		System.out.println("User Name: " + user.getFullName());
	}
	
				.
				.
				.
				
Let's see first how this would work in Beanshell, which is very similar to Java. 
				
## Beanshell [](id=beanshell)

Beanshell is a Java scripting language that's designed to run Java code with
little or no changes. In this example, we only have one small change to make
because Beanshell doesn't support the use of Java Generics. 

	import com.liferay.portal.model.User;
	import com.liferay.portal.service.UserLocalServiceUtil;
	import java.util.List;

	int userCount = UserLocalServiceUtil.getUsersCount();
	List users = UserLocalServiceUtil.getUsers(0, userCount);
	
	for (User user:users) {
		System.out.println("User Name: " + user.getFullName());
	}
	
Next, we'll show the same thing in Groovy, another scripting language designed
to be similar to Java. 

## Groovy [](id=groovy)

Groovy is also based on Java and is perhaps a little easier than Beanshell
because literally any code written in Java also runs in Groovy. This means we
can execute the exact same code from our Java example without any changes. 

	import com.liferay.portal.model.User;
	import com.liferay.portal.service.UserLocalServiceUtil;
	import java.util.List;

	int userCount = UserLocalServiceUtil.getUsersCount();
	List<User> users = UserLocalServiceUtil.getUsers(0, userCount);
	
	for (User user:users) {
		System.out.println("User Name: " + user.getFullName());
	} 
	
Of course, we could make this somewhat Groovier by simplifying the program as
follows: 

	import com.liferay.portal.service.UserLocalServiceUtil

	userCount = UserLocalServiceUtil.getUsersCount()
	users = UserLocalServiceUtil.getUsers(0, userCount)
	for (user in users){
		System.out.println("User Name: " + user.getFullName())
	}
	
The script engine supports more than just Java-like languages. Despite the name,
you should be aware that Javascript bears little resemblance to Java, but you
can still use it in Liferay's script engine. 

## Javascript [](id=javascript)

Liferay uses the Rhino Javascript Engine to provide Javascript support in the
script engine. The following code provides a Javascript version of our original
Java program. 

	userCount = Packages.com.liferay.portal.service.UserLocalServiceUtil.getUsersCount(); 
	users = new Packages.java.util.ArrayList;
	users = Packages.com.liferay.portal.service.UserLocalServiceUtil.getUsers(0, userCount);
	user = Packages.com.liferay.portal.service.UserLocalServiceUtil.createUser(0);

	for (i=0;i<users.size();i++) {
		Packages.java.lang.System.out.println(users.get(i).getFullName());
	}
	
You can see the Javascript example is compact. Ruby is even more compact. 

## Ruby [](id=ruby)

Ruby is supported through the use of JRuby and our previous example could be
implemented in Ruby as follows: 

	userCount = com.liferay.portal.service.UserLocalServiceUtil.getUsersCount();
	users = com.liferay.portal.service.UserLocalServiceUtil.getUsers(0, userCount);
	users.each{ |user| print user.getFullName() + "\n"}
	
Python users aren't left out either. 

## Python [](id=python)

Lastly, Liferay provides Python support based on Jython and the previous example
could be implemented with the following code. 

	from com.liferay.portal.service import UserLocalServiceUtil
	from com.liferay.portal.model import User

	userCount = UserLocalServiceUtil().getUsersCount()
	users = UserLocalServiceUtil().getUsers(0,userCount)

	for user in users:
		print user.getFullName()

As you can see, Liferay's services can be accessed from any of these languages.
Let's look at some practical examples of how you'd use this.

# Running Scripts from the Control Panel [](id=running-scripts-from-the-control-panel)

To see a very simple example of the script console in action, log into the
portal as an administrator and navigate to the *Control Panel* &rarr; *Server
Administration* &rarr; *Script*. Change the script type to Groovy and replace
the code in the scripting console with the following: 

    number = com.liferay.portal.service.UserLocalServiceUtil.getUsersCount(); 
    out.println(number); 

Click the *Execute* button and check the scripting console or the log for the
output.

Now let's implement a more realistic example. We'll retrieve some user
information from the database, make some changes and then update the database
with our changes. Our company has updated the terms of use and requires that
everyone be presented with the updated terms of use on the next log in. When
users agree to the terms of use, a boolean attribute called `agreedToTermsOfUse`
is set in their user records. As long as the boolean is `true`, Liferay will not
present the user with the terms of use. However, if we set this flag to `false`
for everyone, all users will have to agree to it again to use the site. 

We'll again use Groovy, so ensure that the script type is set to Groovy and
execute the following code to check the status of the `agreedToTermsOfUse`
user attribute:

	import com.liferay.portal.service.UserLocalServiceUtil

	userCount = UserLocalServiceUtil.getUsersCount()
	users = UserLocalServiceUtil.getUsers(0, userCount)

	for (user in users) {
		println("User Name: " + user.getFullName() + " -- " + user.getAgreedToTermsOfUse())
	}
	
The code above just prints the value of the `agreedToTermsOfUse` attribute for
each user. Next, we'll actually update each user in the system to set his or her
`agreedToTermsOfUse` attribute to `false`. We'll be sure to skip the default
user as the default user is not required to agree to the Terms of Use. We'll
also skip the admin user that's currently logged in and running the script. If
you're logged in as someone other than test@liferay.com, be sure to update the
following script before running it. 
	
        import com.liferay.portal.service.UserLocalServiceUtil

	userCount = UserLocalServiceUtil.getUsersCount()
	users = UserLocalServiceUtil.getUsers(0, userCount)

	for (user in users){
	
		if(!user.isDefaultUser() && 
			!user.getEmailAddress().equalsIgnoreCase("test@liferay.com")) {
			
				user.setAgreedToTermsOfUse(false)
				UserLocalServiceUtil.updateUser(user)
		
		}
		
	}
	
To verify the script has updated the records, run the first script again and you
should see that all users (except the default user and your user) have been
updated. 

That's all that's needed to run scripts and to access the Liferay service layer.
There are, however, some things to keep in mind when working with the script
console: 

- There is no undo

- There is no preview

- When using Local Services, no permissions checking is enforced

- Scripts are executed synchronously, so be careful with scripts that might take
  a long time to execute. 

For these reasons, you should use the script console with care. It's best to
test run your scripts on non-production systems before running them on
production. Of course, Liferay's script engine has uses beyond the script
console. Let's learn how to leverage Liferay's script engine for designing
workflows.

# Leveraging the Script Engine in Workflow [](id=leveraging-the-script-engine-in-workflow)

Liferay's Kaleo workflow engine provides a robust system for reviewing and
approving content in an enterprise environment. Even if you don't leverage
custom scripts, it's a powerful and robust workflow solution. Adding custom
scripts takes it to the next level.

Examine the default Single Approver workflow definition included with Kaleo for
an overview of how the feature works. The final step in the workflow runs a
script that makes content available for use. As you can see in the snippet
below, it uses JavaScript to access the Java class associated with the workflow
to set the status of the content to *approved*.

	<script>
	<![CDATA[Packages.com.liferay.portal.kernel.workflow.WorkflowStatusManagerUtil.updateStatus
		(Packages.com.liferay.portal.kernel.workflow.WorkflowConstants.toStatus("approved"),workflowContext);]]>
	</script>
	<script-language>javascript</script-language>

At virtually any point in a workflow, you can use Liferay's scripting engine to
access workflow APIs or other APIs outside of workflow. There are a lot of
different ways you could use this. Here are a few practical ones:

- Getting a list of users with a specific workflow-related role
- Sending an email to the designated content approver with a list of people to
  contact if he is unable to review the content
- Creating an alert to be displayed in the Alerts portlet for any user assigned
  to approve content

Of course, before you try any of this, you need to know the appropriate syntax
for inserting a script into a workflow. In an XML workflow definition, a script
can be used in any XML type that can contain an *actions* tag: those types are
`<state>`, `<task>`, `<fork>` and `<join>`. Inside of one of those types, format
your script like this:

	<actions>
		<action>
			<script>
				<![CDATA[*the contents of your script*]]>
			</script>
			<script-language>*your scripting language of choice*</script-language>
		</action>
		...
	</actions>

Here's an example of a workflow script created in Groovy. This one is designed
to be used with a `Condition` statement in Kaleo. It accesses Liferay's asset
framework to determine the category of an asset in the workflow. The script uses
the category to automatically determine the correct approval process. If the
category `legal` has been applied to the asset, the asset is sent to the `Legal
Review` task upon submission. Otherwise, the asset is sent to the `Default
Review` task.

        <script>
            <![CDATA[
                import com.liferay.portal.kernel.util.GetterUtil;
                import com.liferay.portal.kernel.workflow.WorkflowConstants;
                import com.liferay.portal.kernel.workflow.WorkflowHandler;
                import com.liferay.portal.kernel.workflow.WorkflowHandlerRegistryUtil;
                import com.liferay.portlet.asset.model.AssetCategory;
                import com.liferay.portlet.asset.model.AssetEntry;
                import com.liferay.portlet.asset.model.AssetRenderer;
                import com.liferay.portlet.asset.model.AssetRendererFactory;
                import com.liferay.portlet.asset.service.AssetEntryLocalServiceUtil;

                import java.util.List;

                String className = (String)workflowContext.get(
                    WorkflowConstants.CONTEXT_ENTRY_CLASS_NAME);

                WorkflowHandler workflowHandler =
                    WorkflowHandlerRegistryUtil.getWorkflowHandler(className);

                AssetRendererFactory assetRendererFactory =
                    workflowHandler.getAssetRendererFactory();

                long classPK =
                    GetterUtil.getLong((String)workflowContext.get
                    (WorkflowConstants.CONTEXT_ENTRY_CLASS_PK));

                AssetRenderer assetRenderer =
                    workflowHandler.getAssetRenderer(classPK);

                AssetEntry assetEntry = assetRendererFactory.getAssetEntry(
                    assetRendererFactory.getClassName(), assetRenderer.getClassPK());

                List<AssetCategory> assetCategories = assetEntry.getCategories();

                returnValue = "Default Review";

                for (AssetCategory assetCategory : assetCategories) {
                    String categoryName = assetCategory.getName();

                    if (categoryName.equals("legal")) {
                        returnValue = "Legal Review";

                        return;
                    }
                }
               ]]>
        </script>
        <script-language>groovy</script-language>

Within a workflow, the next task or state is chosen based on the return value.
For a complete example a workflow script that uses the above Groovy script,
please see the `legal-workflow-script.xml` file in the User Guide's code folder
on Github:
[https://github.com/liferay/liferay-docs/blob/master/userGuide/code/legal-workflow-script.xml](https://github.com/liferay/liferay-docs/blob/master/userGuide/code/legal-workflow-script.xml).

The combination of Liferay's scripting and workflow engines is incredibly
powerful. However, since it provides users with the ability to execute code, it
can be dangerous. When configuring your permissions, be aware of the potential
consequences of poorly, or maliciously, written scripts inside of a workflow
definition. For more information on creating workflow definitions with Kaleo
workflow, see [chapter
11](https://www.liferay.com/documentation/liferay-portal/6.2/user-guide/-/ai/using-workflow-liferay-portal-6-2-user-guide-11-en)
of this guide.

<!-- ## Script Based Portlets -->

<!-- TODO - Jonathon? -->

# Custom Java Tools in the Script Engine [](id=custom-java-tools-in-the-script-engine)

Users of the Script Engine face several challenges, including debugging and
logging challenges. One approach to overcome these challenges is to develop
custom Java utilities that can be called from your scripts. These utilities can
write to a custom log file or the Liferay log file. You can also place
breakpoints in your utility code and step through it using your favorite
debugger. 

Liferay's use of Spring and `PortletBeanLocatorUtil` makes calling these Java
utilities from your script easy, regardless of the scripting language you're
using. 

Let's begin by creating a Liferay Hook project. If you're using Liferay IDE or
Liferay Developer Studio, select *File* &rarr; *New* &rarr; *Liferay Project*.
Name the project *script-utils* and accept the display name generated by the
wizard. Be sure to select *Hook* for the Plugin Type and then select *Finish*. 

![Figure 5.1: Creating a new utilities project is easy if you use Liferay IDE or Liferay Developer Studio.](../../images/13-new-liferay-project.png)

You're using a Liferay Hook Plugin to deploy your utility, but you're not using
any of the typical hook features. You just need a way to make your code
available to the portal and the Hook Plugin is the least obtrusive way to do
this. This means you don't need to add anything to the `liferay-hook.xml` file.
Instead, you'll begin by adding your utility code. 

You'll be following the Dependency Injection design pattern so begin by creating
the interface. Right-click on the `docroot/WEB-INF/src` folder and select *New*
&rarr; *Interface*. You'll create your interface in the `com.liferay.sample`
package. Name it `ScriptUtil`. 

![Figure 5.2: Create a new Java interface which you'll implement in the next step.](../../images/13-new-interface.png)

Next, add two methods to the interface. 

	package com.liferay.sample;

	public interface ScriptUtil {

		public String operationOne(); 
	
		public String operationTwo(String name); 

	}

Next, create the implementation class. Right-click on the `docroot/WEB-INF/src`
folder and select *New* &rarr; *Class*. Create the class in the
`com.liferay.sample` package and name it `ScriptUtilImpl`. Be sure to select
`com.liferay.sample.ScripUtil` as the Interface. 

![Figure 5.3: Create a new Java Class that implements the interface you created earlier.](../../images/13-new-class.png)

Next, add implementations for the two methods. 

    package com.liferay.sample;

    import com.liferay.portal.kernel.log.Log;
    import com.liferay.portal.kernel.log.LogFactoryUtil;

    public class ScriptUtilImpl implements ScriptUtil {

        @Override
        public String operationOne() { 
        
            return "Hello out there!"; 
        } 

        @Override
        public String operationTwo(String name) { 

            _log.debug("Inside of Operation Two");
            
            return "Hello " + name + "!"; 
        }
        
        private static Log _log = LogFactoryUtil.getLog(ScriptUtilImpl.class); 

    }
	
Liferay makes extensive use of the Spring Framework and you'll be using it here
to inject your implementation class into the application. Spring needs a bean
definition which you'll declare in an XML file named `hook-spring.xml`. Create
a `docroot/WEB-INF/src/META-INF` directory, create the `hook-spring.xml` file in
this folder, and add the following code to `hook-spring.xml`: 

    <?xml version="1.0"?>

    <beans xmlns="http://www.springframework.org/schema/beans"
            xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
            default-destroy-method="destroy" default-init-method="afterPropertiesSet"
            xsi:schemaLocation="http://www.springframework.org/schema/beans http://www.springframework.org/schema/beans/spring-beans-3.0.xsd">
            <bean id="com.liferay.sample.ScriptUtil" class="com.liferay.sample.ScriptUtilImpl" />
    </beans>

Upon deployment, you'll need the portal to create a `BeanLocator` for your
plugin. The `BeanLocator` reads the bean definitions you provided. Create a
`docroot/WEB-INF/web.xml` file in your project and add the following code to it:

    <web-app version="2.4" xmlns="http://java.sun.com/xml/ns/j2ee" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xsi:schemaLocation="http://java.sun.com/xml/ns/j2ee http://java.sun.com/xml/ns/j2ee/web-app_2_4.xsd">
            <context-param>
                    <param-name>portalContextConfigLocation</param-name>
                    <param-value>/WEB-INF/classes/META-INF/hook-spring.xml</param-value>
            </context-param>
    </web-app>
	
If your project already contains a `docroot/WEB-INF/web.xml` file, you can
simply add the contents of the `<context-param>` element inside of the
`<web-app>` element. Save all of the changes you've made and deploy the hook.
Once the hook has been deployed successfully, the `ScriptUtil` can be used in
your script engine code. 

To see the `ScriptUtil` code in action, navigate back to the *Control Panel*
&rarr; *Server Administration* &rarr; *Script*. Change the script type to Groovy
and enter the following script: 

	myUtil = com.liferay.portal.kernel.bean.PortletBeanLocatorUtil.locate(
        "script-utils-hook", "com.liferay.sample.ScriptUtil")

	println(myUtil.operationOne())

	println(myUtil.operationTwo("Joe Bloggs"))
	
Click *Execute* and you should see the results of your script displayed right
under the script console.

# Summary [](id=summary-16)

In this chapter, we saw how Liferay's script engine opens up many exciting
possibilities for working with Liferay. You can write and execute scripts from
Liferay's Script console in the Control Panel using in a variety of languages
including Beanshell, Javascript, Groovy, Python, and Ruby. We learned how you
can leverage Liferay's Services Oriented Architecture (SOA) from any of the
popular scripting languages that Liferay supports. We also saw how those scripts
could be used to simplify administrative tasks by leveraging the Script console.
Next, we discovered how you could enhance workflow by using the power of
scripts. Lastly, we saw how you could overcome some of the limitations of
running scripts in Liferay by creating custom Java utilities that could be
executed from within your scripts.

As you can see, Liferay's script engine opens up many exciting possibilities for
working with Liferay regardless of your language of choice.

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

Next, you'll get started by setting up your LCS account.

# Setting up Your LCS Account [](id=setting-up-your-lcs-account)

To use LCS, you first need to set up an account at [lcs.liferay.com](https://lcs.liferay.com). 
When creating an account, you're taken through the steps of accepting the 
terms of service, setting your password, and setting your password reminder. 
You're then taken to the *Projects* screen where you can join an existing 
project or create a new one.

![Figure 6.1: When creating your LCS account, you can join an existing project or create a new one.](../../images/lcs-projects.png)

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

![Figure 6.2: You can also create a new project from the Projects screen.](../../images/lcs-new-project.png)

So what exactly can an LCS Administrator do? Fantastic question! As LCS 
Administrator, you can assign roles to the rest of the users in your project. 
Each user of LCS needs to have an assigned role. The following roles are 
available: 

- LCS Administrator: All LCS functionality is available to administrators. This 
  is the only role that can manage the roles of other users.
- LCS Environment Manager: All LCS functionality is available in the scope of 
  an environment, with the exception of managing other users.
- LCS Environment Viewer: Has read-only access in the scope of an environment.

Now that your LCS account has been set up and you have an understanding of the 
LCS roles, you can get your portal ready for LCS.

# Preconfiguring Your Portal for LCS [](id=preconfiguring-your-portal-for-lcs)

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

# Configuring the Patching Tool [](id=configuring-the-patching-tool)

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

# Configuring the LCS Client [](id=configuring-the-lcs-client)

Once you deploy the LCS client portlet, you can use it to register your server 
with your LCS account. Access the portlet by clicking on *Liferay Cloud Services* under 
the *Apps* section of the *Control Panel*.

![Figure 6.3: LCS appears in your Control Panel as Liferay Cloud Services, in the Apps section.](../../images/lcs-post-install-01.png)

After clicking *Authorize Access* on the screen that appears, you are then taken 
to the LCS website. Log in with your credentials and then click *Grant Access*.

![Figure 6.4: The LCS client portlet requires access to your LCS account.](../../images/lcs-auth-access.png)

Next, you need to register your server with LCS by filling out the following 
fields:

1. Cloud Services Project: Choose your project from the menu. 

2. Environment: Select an environment from the menu or create a new one by
   clicking the *Add New Environment* button. An environment should be a logical
   group of servers. A group of development servers or a cluster are good examples
   of environments. If you elect to create a new environment, a popup asks you to
   enter its *Name*, *Location*, and *Description*.
   
    ![Figure 6.5: The Add New Environment pop-up.](../../images/lcs-new-environment.png)

3. Server Name: Give your server a name that you can use to distinguish it in 
   your LCS account.

4. Server Location: This can be a city, location in an office building, or 
   anywhere else that lets you know where the server is physically located.

5. Server Description: Type in any description that you want to give your 
   server. You can use this field to help differentiate the servers you are 
   managing using LCS.

![Figure 6.6: The server registration screen.](../../images/lcs-register-server.png)

Upon clicking *Register*, your LCS client portlet shows your connection status. 
Note that it can take up to 10 or 20 seconds to perform the initial handshake 
with LCS and send the first batch of information to the cloud. If you don't see 
any change in status after 20 seconds, try refreshing the page. If you see a 
red symbol indicating no data is being transmitted, please contact the Liferay 
team for support.

![Figure 6.7: The server is connecting to LCS.](../../images/lcs-server-connecting.png)

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
 
![Figure 6.8: The server is connected to LCS.](../../images/lcs-server-connected.png)

Great! Now that you've registered your server with your LCS account, you can dig 
in to the features of LCS.

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

![Figure 6.9: The LCS project view shows an overview of your LCS project.](../../images/lcs-project-view.png)

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

![Figure 6.10: The LCS environment view shows an overview of an LCS environment.](../../images/lcs-environment-view.png)

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

![Figure 6.11: The LCS server view shows an overview of a server registered with LCS.](../../images/lcs-server-view.png)

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

![Figure 6.12: The LCS application metrics show portlet performance statistics, like frequency of use and average load time.](../../images/lcs-server-metrics-application-portlets.png)

The JVM category, as its name indicates, shows statistics about the JVM running 
on your server. This includes data on the garbage collector and memory. The 
number of runs, total time, and average time are listed for each garbage 
collector item. The memory metrics are presented in a bar chart that shows the 
usage of the Code Cache, PS Eden Space, PS Old Gen, PS Perm Gen, and PS Survivor 
Space.

![Figure 6.13: The LCS JVM metrics show performance data for memory and the garbage collector.](../../images/lcs-server-metrics-jvm.png)

Server is the third category in the Application category. The Server category
shows additional information about how your server is running. For example, a
horizontal bar graph shows the number of current threads that are running on
your server. Similarly, horizontal bar graphs are used to represent the JDBC
connection pools.

![Figure 6.14: The LCS server metrics show current threads and JDBC connection pools.](../../images/lcs-metrics-server.png)

You can also view the settings for a server by clicking on the *Server Details* 
button, which is to the right of the Metrics button. The first tab under the 
Settings button is *LCS Server Settings*. This lets you view or edit your 
server's name, description, and location. You can also unregister your server 
from LCS. The second tab under the Settings button is *About Server*. This 
provides general information about your Liferay instance and hardware. This 
information is useful to the Liferay support team in the event that you need 
their assistance.

![Figure 6.15: You can view and edit the details of your server registered with LCS.](../../images/lcs-server-details-settings.png)

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

![Figure 6.16: The Users tab lets you manage the LCS users in your project.](../../images/lcs-users.png)

To invite external users to your project, click on the *Invite* button. The
*Invite External Users* pop up lets you invite anyone with a valid email address.
You can also search for users of Liferay.com to invite. Once you've chosen who
to invite, the *Role* selection box lets you preassign LCS roles for when they
accept your invitation.

![Figure 6.17: You can invite external users to your LCS project, and even preassign them roles.](../../images/lcs-invite-users.png)

As you've now seen, LCS is a powerful tool that simplifies the management of 
your Liferay servers. You can apply fix packs with just a single click and a 
server restart--a process that even works across a cluster. You also get a one 
stop shop for monitoring the performance of your Liferay servers. Metrics like 
JVM performance, Liferay page and portlet load times, and number of current 
threads give you an inside look at how your server is running. What's more is 
that you can do all this collaboratively by inviting others to your project and 
giving them specific roles in LCS. 

Next, you'll learn about Liferay clustering.

b0VIM 7.4      �{>T�_ �"  rsezov                                  enterprise                              ~rsezov/Documents/liferay-docs/discover/deployment/articles/06-configuring-for-high-availability/01-liferay-cloud-services.markdown                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                          utf-8 3210    #"! U                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                 tp                                              C                            �                            �                     <       �                    =       �                    =       �                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                     ad  �  �            �  �  �  5  4  �  �  V    �  j    �  |  9  �  �  �      �
  �
  ]
  
  �	  �	  �	  U	  T	  H	  -	  	  �  �  �  u  t  _    �  �  i    �  �  }  |  +  �  �  ?  >    �  }  .  �  �  ;  �  �  R    �  �  L  K                                          *Create a project and become an administrator* in the New Project section of the  To do so, click t**Create a project and become an administrator* in the New Project section of the  *Create a project and become an administrator* in the New Project section of the*Create a project and become an administrator* in the New Project section of *Create a project and become an administrator* in the New Project section of*Create a project and become an administrator* in the New Project section of the*Create a project and become an administrator* in the New Project section of the  *Create a project and become an administrator* in the New Project section of th*Create a project and become an administrator* in the New Project section of the  *Create a project and become an administrator* in the New Project section of th*Create a project and become an administrator* in the New Project section of the*Create a project and become an administrator* in the New Project section of the  *Create a project and become an**Create a project and become an administrator* in the New Project section of the *Create a project and become an administrator* in the New Project section of the *Create a project and become an administrator* in the New Project section *Create a project and become an administrator* in the New Project section of the **Create a project and become an administrator* in the New Project section o**Create a project and become an administrator* in the New Project section of the  *Create a project and become an administrator* in the New Project section of t*Create a project and become an administrator* in the N*Create a project and become an administrator* in the New Project section of*Create a project and become an ad*Create a project and become an administrator* in the New Project section of the *Create a project and**Create a project and become an administrator* in the New Project section **Create a project an*Create a project and become an administrator* in*Create a project and become an administ*Create a project and becom*Create a pr**Create a project and become an administrator* in the New Pro*Create a project and become an**Create a project and become an administrator* in the New Project section of the  To do so, clic*Create a project and become an administrator* in the New Project section o*Create a project and become an administrator* in the New Project section of t*Create a project and become an administrator* in the New Project section o*Create a project and become **Create a project and become an administrator* in the New Project section of the  To do so, click the checkbox next to  Alternativel**Create a project and become an administrator* in the New Project section of th*Create a project and become an administrator* in the New Project section of the *Create a project and become an administrator* in the New Project s*Create a project and become an administrator* in the New Project section of the *Create a project and become an administrator* in the New Project section of the *Create a project and become an administrator* in the New Project section of*Create a project and become an administrator* in the New Project section of t*Create a project and become an administrator* in the New Project section of t*Create a project and become an administrator* in the New Project section of the*Create a project and become an administrator* in the New Project section of th*Create a project and become an administrator* in the New Project section of th*Create a project and become an administrator* in the New Projec**Create a project and become an administrator* in the New Project section of the  *Create a project and become an admini*Create a project and become an administrator* in the New Project section of the   ad  �  �     =       �  b  Y  X    �  �  �  _  H  �  �  �  i  B  A  8  �  �  �  �  �  4  �  �  �  �  �  �  �  �    ~  ,  �
  �
  C
  �	  �	  �	  �	  2	  1	  �  �  D  �  �  �  W  V    �  j    �  ~  -  	    �  �                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                             Next, you'll learn about Liferay clustering.  giving them specific roles in LCS.  that you can do all this collaboratively by inviting others to your project and  threads give you an inside look at how your server is running. What's more is  JVM performance, Liferay page and portlet load times, and number of current  stop shop for monitoring the performance of your Liferay servers. Metrics like  server restart--a process that even works across a cluster. You also get a one  your Liferay servers. You can apply fix packs with just a single click and a  As you've now seen, LCS is a powerful tool that simplifies the management of   ![Figure 6.17: You can invite external users to your LCS project, and even preassign them roles.](../../images/lcs-invite-users.png)  accept your invitation. to invite, the *Role* selection box lets you preassign LCS roles for when they You can also search for users of Liferay.com to invite. Once you've chosen who *Invite External Users* pop up lets you invite anyone with a valid email address. To invite external users to your project, click on the *Invite* button. The  ![Figure 6.16: The Users tab lets you manage the LCS users in your project.](../../images/lcs-users.png)  user. Manage button. Clicking *Manage* lets you assign or revoke LCS roles for that  presented with a table of the users on your project. To the right of each is the  just below the Dashboard icon on the upper-left of your screen. You're  aren't yet part of your project. To manage users, first click the *Users* icon  project. It's here that you can grant or revoke LCS roles or invite others that  The Users section of LCS is where you manage the LCS users that are part of your   ### Managing LCS Users in Your Project  -->  -Rich  Just 'splaining. :-)  "formidable" to "powerful."  definitions (which the synonyms list makes clear). For that reason, I changed the negative connotations of definitions one and two, as they're the more common I'm sure you meant definition three, but I'm guessing many readers will assume  1. pleasant. Antonyms 1. dreadful, appalling, threatening, menacing, fearful, frightful, horrible. Synonyms  formidable opposition to the proposal. 4.  of great strength; forceful; powerful: 3.  arousing feelings of awe or admiration because of grandeur, strength, etc. a formidable problem. 2.  of discouraging or awesome strength, size, difficulty, etc.; intimidating: a formidable opponent. 1.  causing fear, apprehension, or dread:  the dictionary definition has four definitions:  possibly close to what you wanted, but having a negative connotation, and indeed <!-- You had "the LCS Dashboard is a formidable tool...." That struck me as  project. are running. Next you'll take a look at how to manage the users in your LCS  the update process and also gives you extensive information on how your servers  ad  �  �            �  �  _  ^  	    �  �  Y  X    �  �  �  [  Z    �  �  w  $  �  �  �  H  G  ?  �
  �
  �
  �
  2
  �	  �	  `	  _	  4	  �  �  �  t  *  )  �  �  �  W    �  �  {  z  U    �  k      �  �  �  �  H  G  �  �  �  �  8  7    �  �  3  �  �                                                                          Note that it can take up to 10 or 20 seconds to perform the initial handshake  Upon clicking *Register*, your LCS client portlet shows your connection status.   ![Note that it can take up to 10 or 20 seconds to perform the initial handshake Note that it can take up to 10 or 20 seconds to perform the initial handshake Note that it can take up to 10 or 20 seconds to perform the initial handshake Note that it can take up tNNote that it can take up to 10 or 20 seconds to perform the initial handshake  UpoNote that it can take up to 10 or 20 seNNote that it can take up to 10 or 20 seconds to perform the initial handshNote that it can take up to 10 or 20 seconds to perform the initial handshaNNote that it can take up to 10 or 20 seconds to perform the initialNNote that it can take up to 10 or 20 seconds to perform the initial handshNNote that it can take up to 10 or 20 seconds to perform the initial NNote that it can take up to 10 or 20 seconds to perform the initial handshake  Note that it can take up to 10 or 20 seconds to perform the initial handshake  UpNote that it can take up to 10 or 20 seconds to perform the initial handshakNote that it can take up to 10 or 20 seconds to perform the initial handshakeNote that it can take up to 10 or 20 NNote that it can take up to 10 or 20 seconds to perform the initialNNote that it can take up to 10 or 20 seconds to perform the initial handshake Note that it can take up to 10 or 20 seconds to perform the initial handshNote that it can take up to 10 or 20 seconds to perform the initial handsNote that it can take uNNote that it can take up to 10 or 20 seconds to perform the initial handshake  Upon clicking *Register*, your LCSNNote that it can take up to 10 or 20 seconds to perform the initial handshNote that it can take up to 10NNote that it can take up to 10 or 20 seconds to perform the initial handshake  UNote that it can take up to 10 or 20 seconds to perform the initial handshake  UpNote that it can take up to 10 or 20 secondNNote that it can take up to 10 or 20 seconds to perform the initial handshake  Upon clicking *Register*, your LCS client portleNNote that it can take up to 10 or 20 seconds to perform the initial handshake  UpoNote that it can take up to 10 or 20 seconds to perform the initial handshake  UNNote that it can take up to 10 or 20 seconds to perform the initial handshake  Upon clicking *Register*, yourNNote that it can take up to 10 or 20 seconds to perform the initial handshake Note thaNNote that it can take up to 10 or 20 seconds to perform the iniNNote that it can take up to 10 or 20 seconds to perform the initial handshaNote that it can take up to 10 or 20 seconds to perform the initial handshake  UpNote that it can take up to 10 or 20 seconds to perform the initial handshake  UponNote that it can take up to 10 or 20 seconds to perform the initial handshake  UpoNote that it can take up to 10 or 20 seconds to perfNoteNote that it can take up to 10 or 20 seconds to perform the initial handshake  Upon clickNNote that it can take up to 10 or 20 seconds to perform the initial handshake  Note that it can takeNNote that it can take up to 10 or 20 seconds to perform the initial handshakNote that it can take up to 10 or 20 seconds to perform the initial handshakNNote that it can take up to 10 or 20 seconds to perform the initial handshaNote that it can take up to 10 or 20 seconds to perform the initial handshakeNote that it can take uNNote that it can take up to 10 or 20 seconds to perform the initial handshake  Upon cNNote that it can take up to 10 or 20 seconds to perform the initial handshake  UpNote that it can take up to 10 or 20 seconds to perform the initial handshake   ad  �  �            �  �  i  h  >  �  �  �  �  J  �  �  �  �  �  �  V    
  �  �  �  |  _  6      �  �  o  n  ]    �
  l
  !
  �	  �	  ;	  :	  /	  �  �  H  �  �  �  �  �  0  /  �  �  �  {  -  �  �  d  U    �  �  e  ,  �  �  :  9    �  �  `  _  M  �  �  ^  ]            servers. If you're not already at the Dashboard, click it near the upper  The LCSservers. If you're not already at the Dashboard, click it near the upper  The servers. If you're not already at the Dashboard, click it near the upper  The LCservers. If you'resservers. If you're not already at the Dashboard, click it near the upper  The LCS Dashbosservers. If you're not already at the Dashboard, click it near the upper  Tservers. If you're not already at the Dashboard, clicsservers. If you're not already at the Dashboard, click it near the upper  The LCSservers. If you're not already at the Dashboard, click it near the upper  The LCSservers. If you're not already at the Dashboard, click it near the upper  The LCservers. If you're not already at the Dashboard, click itservers. If you're not already at the Dashboard, click it near the upper  The LCS servers. servers. If you're not already at the Dashboard, click it near the upper  Theservers. If you're not already at the Dashboard, click it near the upperservers. If youservers. If you're not already at the Dashboard, click it neaservers. If you're not already at the Dashboard, click it servers. If you're not already at the Dashboard, click it near the upper  The LCS servers. If you're not already at the Dashboard, click it near the upper  The servers. If you're not already at the Dashboard, click it near the upper  servers. If you'reseservers. If you're not already at the Dashboard, click it near the upper  The LCS Dashsservers. If you're not already at the Dashboard, click it near the upper  The LCS servers. If you're not alreservers. If ysservers. If you're not already at the Dashboard, click it near the upper  The Lservers. If you're not already at the Dashboard, click it near the upper  The servers. If you're not already at the Dashboard, click it near the uservers. If you're not already at the Dashboard, click it near the upper  The LCSservers. If you're not already at the Dashboard, click it near the upper  The LCS servers. Ifsservers. If you're not already at the Dashboard, click it near the upper  The servers. If you're not already at the Dashboard, click it near the upper  The Lservers. If you're not already at the Dashboard, click it near the upper servers. If you're not already at the Dashboard, click it near the upper  Tservers. If you're not already at the Dashboard, click it near the upper  The servers. If you're not already at the Dashboard, click it near the upper  The LCSservers. If you're not already at the Dashboard, click it near the upper  The LCS servers. If you'rsservers. If you're nservers. If you're not already at the Dashboard, click it near the upper  The LCS Dashboard leservers. If you're not already aservers. If you're not alreadservers. If you're not aservers. If you're not already at the Dasservers. If you're not alreadservers. If you're not already at thservers. If you're not already at the Dservers. If you're not alreservers. If you're not already at the Dasservers. If you're not already at the Dashboard, click it near the upper  Tservers. If you're not already at the Dashboard, clicsservers. If you're not already servers. If you're not already aservers. If you're not already at the Dashboardsservers. If you're not already at the Dashboard, click it near the upper  The Lservers. If you're not already at the Dashboard, click it near the upper  The servers. If you're not already at the Dashboard, click it near the upper  The LCSservesservers. If you're not already at the Dashboard, click it near the upper  The Lservers. If you're not already at the Dashsservers. If you're not already at the Dashboard, click it near the upper  Thservers. If you're not already at the Dashboard, click it near the upper   ad  H   h     A       �  o  %  �  �  �  �  |  +  �  �  4  �  �  M  �  �  a  -  ,  �
  �
  P
   
  �	  �	  �	  M	  �  �  �  V  U    �  r     �  �  �  K  �  �  �  �  L  (  �  �  �  v  u  %      �  �  �  @  �  �  N    �  h  g                                                                          button lets you edit or delete the environment. To the left of the fix packs is  project as a whole. Located above the fix packs, the Environment Details  is that they show fix packs and alerts for the environment instead of the  behave exactly the same here as they do in the project view. The only difference  fix packs and alerts are displayed on the center of the screen. In fact, they  information about an environment in your LCS project. As with the project view,  the environment view. The environment view, as its name implies, gives you more  Clicking an environment on the left-hand side of the project view takes you to   ### Using the Environment View  takes you to the environment view. You can get more information about a specific environment by clicking it. This     cluster. - Icon with a circle: Indicates that the servers in that environment are in a    properly. - Green icon: Indicates that the servers in that environment are operating    more servers in that environment. - Red icon: Indicates that there is some sort of problem or issue with one or   environment: the same. In fact, the color and type of the icon tells you something about that  look at the icons next to each environment, you'll notice that they're not all  You can also create new environments here by clicking the blue plus icon. If you  To the left of the fix packs and alerts are the environments in your project.   tool is unavailable, or other issues are detected. dot next to it. Other alerts appear when monitoring is unavailable, the patching  server is offline. Since this is an important message, it appears with a red  many other kinds of messages. For example, an alert appears whenever a  In addition to displaying fix pack messages, the Alerts table also displays   ![Figure 6.9: The LCS project view shows an overview of your LCS project.](../../images/lcs-project-view.png)  nodes--you don't have to handle each one individually.  same procedure! LCS downloads and installs fix packs simultaneously across all  But what about using LCS to install fix packs across a cluster? Just follow the   necessary. -Rich --> that's a bad practice, so they'll want to know exactly what permissions are permissions are needed exactly. Most people won't run their servers as root, as permissions at the operating system level, but beyond that, I'm not sure what administrative access, as mentioned here? I assume you're talking about <!-- This is unclear. Administrative access to what? In fact, what is  stored and processed (the `patching-tool` folder).  with the privileges required to write to the disk location where patches are  server installs any downloaded fix packs. Note that you must start your server tells you the download is finished and to restart your server. Restarting your alert appears in the Alerts table below the fix packs table. This notification Download button to its right. Once a fix pack is finished downloading, an  fix pack. If the fix pack is available, you can download it by clicking the  page. Note that the status, environment, server, and location are listed for each  and servers. Fix packs are displayed prominently in a table on the middle of the  with an overview of your LCS project, including fix packs, alerts, environments,  near the upper left-hand corner of your LCS site. The project view provides you  You can get to the project view at any time by clicking the Dashboard tab   ### Using the Project View  aspects of your LCS project. You'll start with the project view. server view. Each of these views gives you a different look into certain  project view. From there, you can get to the environment view and the  left-hand corner of your LCS site. Clicking *Dashboard* takes you to the  ad     6     ?       �  �  �      �  �  �  Q     �  e    �  y  *  "  !  �  �  U    �
  �
  �
  @
  �	  �	  Q	  	  �  �      �  �  6  �  �  �  �      �  f    �  �  �  =  <  �  �  R    �  j    
  	  �  �  6  5                                As you can see, the LCS Dashboard is a powerful tool that greatly simplifies   ![Figure 6.15: You can view and edit the details of your server registered with LCS.](../../images/lcs-server-details-settings.png)  their assistance. information is useful to the Liferay support team in the event that you need  provides general information about your Liferay instance and hardware. This  from LCS. The second tab under the Settings button is *About Server*. This  server's name, description, and location. You can also unregister your server  Settings button is *LCS Server Settings*. This lets you view or edit your  button, which is to the right of the Metrics button. The first tab under the  You can also view the settings for a server by clicking on the *Server Details*   ![Figure 6.14: The LCS server metrics show current threads and JDBC connection pools.](../../images/lcs-metrics-server.png)  connection pools. your server. Similarly, horizontal bar graphs are used to represent the JDBC horizontal bar graph shows the number of current threads that are running on shows additional information about how your server is running. For example, a Server is the third category in the Application category. The Server category  ![Figure 6.13: The LCS JVM metrics show performance data for memory and the garbage collector.](../../images/lcs-server-metrics-jvm.png)  Space. usage of the Code Cache, PS Eden Space, PS Old Gen, PS Perm Gen, and PS Survivor  collector item. The memory metrics are presented in a bar chart that shows the  number of runs, total time, and average time are listed for each garbage  on your server. This includes data on the garbage collector and memory. The  The JVM category, as its name indicates, shows statistics about the JVM running   ![Figure 6.12: The LCS application metrics show portlet performance statistics, like frequency of use and average load time.](../../images/lcs-server-metrics-application-portlets.png)  statistics in the Portlet category are shown. Single VM metrics and Hibernate metrics. In the following screenshot, the  but for specific portlets in your server. The Cache category lists Liferay  loaded, along with their average load time. Portlets lists the same statistics,  *Portlets*, and *Cache*. Pages lists the frequency with which specific pages are  The Application category also has three other categories within it--*Pages*,   default when you click the Metrics button.  categories--*Application*, *JVM*, and *Server*. Application is selected by  button near the top of the page. The metrics are broken down into three main  To view metrics and statistics of your server's performance, click the *Metrics*   ![Figure 6.11: The LCS server view shows an overview of a server registered with LCS.](../../images/lcs-server-view.png)  table.  these through the *Available* and *Installed* tabs at the top of the fix packs available for installation and those that are already installed. You can access table behaves a bit differently. Fix packs are broken down into those that are alerts table functions the same as it does in the other views, the fix packs Alerts button is selected by default when you enter server view. While the tables. Fix packs and alerts are the first thing you see here, as the Fix Packs server in the environment view or by clicking a server in the fix pack or alerts statistics and performance metrics. You can get to the server view by clicking a The server view provides you with detailed information about a server, including   ### Using the Server View  ![Figure 6.10: The LCS environment view shows an overview of an LCS environment.](../../images/lcs-environment-view.png)  to the server view for that server. the list of servers in the environment. Clicking on one of the servers takes you  ad  �  �            �  �  �  b      �  �  z  y  Y  O  &  �  �  �  �  �  W  1    �  �  �  �  b  =    �  �  �  x  L    �
  �
  �
  �
  b
  >
  4
  
  
  �	  �	  �	  �	  �	  T	  ,	  "	  �  �  �  �  W  V    �  x  '  �  �  5  4    �  �  �  <  �  �  r  !       �  {  2  1  �  �  �  C  �  �                                                                                                                                   If you're not running a bundle, then please see the user guide section on  runningIf you're not running a bundle, then please see the user guide section on  runnIf you're not running a bundle, then please see tIIf you're not running a bundle, then please see the user guide section on  running a Liferay EE bundle, then IIf you're not running a bundle, then please see the user guide section onIf you're not running a bundle, then please see the user guide section on  runnIf you're not running a bundle, then please see the user guide section on If you're noIIf you're not running a bundle, then please see the user guide section on  runninIf you're not running a bundle, then please see the user guidIf you're not running a bundle, then please see the user guide section on  runnIf you're not running a bundle, then please see the user guideIf you're not running a bundle, then please see the user guide section on  runnIIf you're not running a bundle, then please see the user guide section on  runniIf you're not running a bundle, then please see thIf you're not running a bundle, then pIIf you're not running a bundle, then please see the user guide section on  runningIf you're not running a bundle, then please see the user guide section on  runnIf you're not running a bundle, then please see the user guide section on  runninIf you're not running a bundle, then please see the user guide section on  runninIf you're not running a bundle, then please see the user guide section on  runninIf you're not running a bundle, then please see the user guide section on  runnIf you're not running a bundle, then please see the user guideIIf you're not running a bundle, then please see the user guide section on  runnIf you're not running a bundle, then please see the user guide sectIIf you're not runningIf you're not running a bundle, theIf you're If you're not running a bundle, then pleIf you're not running a bundle, then please sIf you're not running a bundle, then pleaIf you're If you're not running a bundle, theIf you're If you're not running a bundle, thIf you're If you're not running a bundle, then plIf you're If you're not running a bundle, thenIf you're not running a bundle, then pIf you're not running a bundle, then If you're not running a bundle, thIf you're not running a bundle, theIf you're not running a bundle, then please sIf you're not running a bundle, then please sIf you're not running a bundle, then please If you're not running a bundle, then please If you're not running a bundle, then If you're not running a bundle, thenIf you're not running a bundle, then pleIf you're not running a bundle, then pleIf you're not running a bundle, then If you're not running a bundle, then plIf you're If you're not running a bundle, then pIf you're not running a bundle, theIf you're not running a bundle, then If you're not running a bundle, then please see If you're not running a bundle, then pIf you're not running a bundle, then please seIf you're If you're not running a bundle, then pleaIf you're If you're not running a bundle, then please see theIf you're not running a bundle, then please see tIf you're not running a bundle, then pleaIf you're If you're not running a bundle, IIf you're not running a bundle, then please see the user guide section on  runniIf you're not running a bundle, then If you're not running a bundle, thIIf you're not running a bundle, then please see the user guide section on  runniIf you're not running a bundle, then please see the user guide section on  runninIf you're not running a bundle, then please see the user guide section on    
# Configuring Liferay for High Availability [](id=configuring-liferay-for-high-availability)

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

- Content Delivery Network

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

# Liferay Clustering [](id=liferay-clustering)

Liferay Portal is designed to serve everything from the smallest to the largest
web sites. Out of the box, it's configured optimally for a single server
environment. If one server isn't sufficient to serve the high traffic needs of
your site, Liferay scales to the size you need. 

![Figure 6.16: Liferay is designed to scale to as large an installation as you need.](../../images/enterprise-configuration.png) 

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

+$$$

**Note:** This chapter documents a
 Liferay-specific cluster configuration, without getting into specific
 implementations of third party software, such as Java EE application servers,
 HTTP servers, and load balancers. Please consult your documentation for those
 components of your cluster for specific details of those components. Before
 configuring Liferay in a cluster configuration, make sure your OS is not
 defining the hostname of your box to the local network at 127.0.0.1.

$$$

We'll discuss each of the points above one by one to present a clear picture of
how to cluster Liferay. 

## All Nodes Should Point to the Same Liferay Database [](id=all-nodes-should-point-to-the-same-liferay-database)

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

### Read-Writer Database Configuration [](id=read-writer-database-configuration)

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

### Database Sharding [](id=database-sharding)

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

![Figure 6.17: When creating a shard using the manual shard selector, specify the shard you want to use for that instance.](../../images/enterprise-sharding-portal-instance.png)

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

## Documents and Media Library Clustering [](id=documents-and-media-library-clustering)

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

### Using the File System Store [](id=using-the-file-system-store)

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

![Figure 6.18: Liferay's file system store creates a folder structure based on primary keys in Liferay's database.](../../images/enterprise-file-system-store.png)

The actual folder path that is used by Liferay for storing documents is this:

    /companyId/folderId/numericFileEntryName/versionNumber

The first folder name is the company ID to which the site belongs. The second
folder name is the ID of the Documents and Media folder within which the
document resides. The third folder name is the numeric file entry name of the
document itself. Finally, the fourth name is a version number which is used for
storing multiple versions of the document.

+$$$

**Note:** The numeric file entry name of a
 document is distinct from the document ID; be careful not to confuse the two!
 Each has an independent counter. The numeric file entry name of a document is
 used in the folder path for storing the document but the document ID is not.
 The numeric file entry name of document can be found in the `name` column of
 the `DLFileEntry` table in Liferay's database; the document ID can be found in
 the `fileEntryId` column of the same table.

$$$

As you can see, the File System Store binds your documents very closely to
Liferay, and may not be exactly what you want. But if you've been using the
default settings for a while and need to migrate your documents, Liferay
provides a migration utility in the Control Panel in *Server Administration*
&rarr; *Data Migration*. Using this utility, you can move your documents very
easily from one store implementation to another. 

Speaking of other store implementations, let's look at some others Liferay
provides. 

### Using the Advanced File System Store [](id=using-the-advanced-file-system-store)

Liferay's advanced file system store is similar to the default file system
store. Like that store, it saves files to the local file system--which, of
course, could be a remote file system mount. It uses a slightly different folder
structure to store files, which is pictured below. 

![Figure 6.19: The advanced file system store creates a more nested folder structure than the file system store.](../../images/enterprise-adv-file-system-store.png)

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

### Using the CMIS Store [](id=using-the-cmis-store)

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

### Using the JCR Store [](id=using-the-jcr-store)

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

#### Using Amazon Simple Storage Service [](id=using-amazon-simple-storage-service)

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

#### Using the Documentum Store [](id=using-the-documentum-store)


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

## Clustering Search [](id=clustering-search)

You can configure search for clustering in one of two ways: you can use pluggable
enterprise search (recommended) or you can configure Lucene so indexes replicate
across the individual file systems of the nodes in the cluster. We'll look at
both ways to do this. 

### Using Pluggable Enterprise Search [](id=using-pluggable-enterprise-search)

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

#### Configuring the Solr Search Server [](id=configuring-the-solr-search-server)

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

#### Installing the Solr Liferay Plugin [](id=installing-the-solr-liferay-plugin)

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

### Clustering Lucene Indexes on All Nodes [](id=clustering-lucene-indexes-on-all-nodes)

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

## Distributed Caching [](id=distributed-caching)

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

### Enhanced Distributed Cache Algorithm [](id=enhanced-distributed-cache-algorithm)

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

![Figure 6.20: The default algorithm requires each node to create massive amounts of dispatch threads to update the cache for each node in the cluster.](../../images/19-ehcache-inefficient-algorithm.png)

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

![Figure 6.21: Liferay's algorithm uses a single UDP multicast channel, so that
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

### Modifying the Ehcache Settings With a Plugin [](id=modifying-the-ehcache-settings-with-a-plugin)

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

### Modifying the Ehcache Settings Directly [](id=modifying-the-ehcache-settings-directly)

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

### Customizing Hibernate Cache Settings [](id=customizing-hibernate-cache-settings)

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

### Configuring Liferay's Caching Settings [](id=configuring-liferays-caching-settings)

To understand how Liferay behaves with various cache configurations, let's
consider five different scenarios.

- Scenario 1: The portal administrator does not override the default cache
  configuration files (i.e., does not override
  `ehcache.single.vm.config.location`, `ehcache.multi.vm.config.location`, or
  `net.sf.ehcache.configurationResourceName`) but does set
  `cluster.link.enabled=true` and `ehcache.cluster.link.replication.enabled=true`. This
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
  `ehcache.cluster.link.replication.enabled=true`. In this case, Liferay uses
  Cluster Link based replication for any caches configured with the
  LiferayCacheEventListenerFactory classs. If the portal administrator
  configured a different CacheEventListener class for a specific cache (e.g.,
  JGroups), then Liferay uses that listener class. This a basically a hybrid
  mode that was useful prior to Liferay 6.1 before Liferay supported cached
  object replication (and only supported cached event replication). This is
  *not* a recommended configuration for Liferay 6.1 or later.

- Scenario 5: The portal administrator overrides the default cache configuration
  files and sets `cluster.link.enabled=true` but does not set
  `ehcache.cluster.link.replication.enabled=true`. In this case, Liferay uses the cache
  configurations specified in the custom cache configuration files. This is the
  recommended configuration when overriding the default cache configuration
  files.

As a general rule, we recommend that portal administrators *not* set custom
cache configuration files but to set `cluster.link.enabled=true` and
`ehcache.cluster.link.replication.enabled=true` (i.e., we recommend that portal
administrators set up the configuration in scenario 1). If it's necessary to
tune the cache configurations, it's better to do it via a plugin than to do it
directly. We explained how to do this in the section above called "Modifying the
Ehcache Settings With a Plugin."

Next, we'll show how to share indexes in a database. This is actually not a
recommended configuration, as it's slow (databases are always slower than file
systems), but for completeness, we'll go ahead and tell you how to do it anyway.
But you've been forewarned: it's far better to use one of the other methods of
clustering your search index. 

### Sharing a Search Index (not recommended unless you have a file locking-aware SAN) [](id=sharing-a-search-index-not-recommended-unless-you-have-a-file-locking-aware)

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

+$$$

**Note:** MySQL users need to modify their JDBC
 connection string for this to work. Add the following parameter to your
 connection string:

    emulateLocators=true

$$$

Alternatively, you can leave the configuration alone, and each node will have
its own index. This ensures against collisions when multiple nodes update the
index. However, the indices will quickly get out of sync since they don't
replicate. For this reason, this is not a recommended configuration either.
Again, for a better configuration, replicate the indexes with Cluster Link or
use a separate search server (see the section on Solr above).

Now we can look at the last consideration when clustering Liferay: hot deploy. 

## Hot Deploy [](id=hot-deploy)

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

# Performance Tuning [](id=performance-tuning)

Once you have your portal up and running, you may find a need to tune it for
performance, especially if your site winds up generating more traffic than you'd
anticipated. There are some definite steps you can take with regard to improving
Liferay's performance.

## Memory [](id=memory)

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

## Garbage Collection [](id=garbage-collection)

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

![Figure 6.22: Java uses generational garbage collection. If an object survives enough garbage collection events, it's promoted to a new memory pool. For example, an object could be promoted from the young generation memory pool to the old generation memory pool or from the old generation memory pool to the permanent generation memory pool.](../../images/portal-admin-ch7_html_518957a7.png)

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

## Properties File Changes [](id=properties-file-changes)

There are also some changes you can make to your `portal-ext.properties` file
once you are in a production environment.

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

## Disabling Unused Servlet Filters [](id=disabling-unused-servlet-filters)

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

Next, you'll learn how to configure a Content Delivery Network, which can also
help with your portal's performance. 

# Content Delivery Network [](id=content-delivery-network)

A Content Delivery Network (CDN) is an interconnected system of servers deployed
in multiple data centers that use geographical proximity as a criteria to
deliver content across the Internet. For more information on CDNs and their
general use cases and technical details, visit the following
[site](http://en.wikipedia.org/wiki/Content_delivery_network).

First, you'll discover the perks of using a CDN in Liferay and learn about
general guidelines for using a CDN in your Liferay Portal instance. Then, you'll
learn the steps to configure a CDN for your portal. It's time to expand your
Liferay content around the world! 

## Using CDN for Performance Enhancements [](id=using-cdn-for-performance-enhancements)

A CDN serves static content to users in a Liferay Portal instance. These static
resources (images, CSS, JavaScript, etc.) from the portal are stored on multiple
servers around the world, and when requested, are retrieved by the server nearest
to the user. 

The CDN functions as a caching proxy, meaning that once static content
is copied to a local server, it is stored in a cache for quick and easy
retrieval. This drastically improves latency time, because browsers can
download static resources from a local server down the street instead of
halfway around the world. A user's request to the CDN for content is directed to
a server machine using an algorithm. That algorithm attempts to use a server
closest to the user. The figure below shows a visual representation of using
geographical proximity to improve latency. 

![Figure 6.23: The red lines on the map represent the required distances traveled by requests from a server to the user. Using CDN allows a user to request static resources from a much closer local server, improving download times.](../../images/cdn-map.png)

Because of the reduced wait time for requests and reduced load on your
application server, a CDN is a great option to improve your portal's performance.
Using a CDN with Liferay, however, has some restrictions. 

Liferay only works with CDNs that can dynamically retrieve requested resources
from Liferay. For this reason, you should check with your CDN provider to make
sure you don't have to upload anything to them manually in order for it to work.
The CDN must fetch the content itself. 

There are several properties in Liferay that enable you to configure your CDN
and tweak it to your portal's needs. You'll cover how to do this next.

## Configuring Liferay to Use a CDN [](id=configuring-liferay-to-use-a-cdn)

Now that you have a general understanding of what a CDN accomplishes and how
it's used in Liferay, it's time to set one up for yourself. You can set your CDN
and its properties using two different methods: editing your portal's properties
file or using the Control Panel. 

To configure your CDN by properties file, you'll need to create a
`portal-ext.properties` file in your Liferay Home directory and set the
appropriate CDN properties. You can view the CDN properties and their
descriptions by visiting the [Content Delivery Network](http://docs.liferay.com/portal/6.2/propertiesdoc/portal.properties.html#Content%20Delivery%20Network)
section of the `portal.properties` HTML document. 

Once you configure your CDN host, static assets automatically get uploaded to
the CDN, and Liferay generates URLs to them that replace the old host with your
new CDN host.

To configure your CDN in the Control Panel, navigate to the Control Panel &rarr;
*Portal Settings*. In the main configuration, you'll notice three fields related
to CDNs: 

- *CDN Host HTTP* 
- *CDN Host HTTPS* 
- *CDN Dynamic Resources Enabled*

![Figure 6.24: The Control Panel lets you configure your portal's CDN.](../../images/cdn-control-panel.png)

These properties are exactly the same as the ones you can specify in your
`portal-ext.properties`. Make sure to visit the CDN section of the Properties
Document referenced previously if you don't know how to fill in the CDN fields.
Once you're finished, click *Save* and your old host is replaced with your new
CDN host for static content. 

As you can see, configuring a CDN is extremely easy, and can drastically reduce
latency time and improve your portal's performance. 

# Summary [](id=summary-17)

We've seen how good a fit Liferay Portal is for the enterprise. It can be scaled
linearly to grow to whatever size you need to serve your users. Liferay Cloud 
Services lets you manage and monitor your Liferay instances from a single 
interface. Clustering is also a snap, and Liferay harmonizes very well with 
whatever environment you may have.

Liferay Portal is also built for performance. You can take advantage of
read-writer database configurations, as well as database sharding. You can tune
it to support over 3300 concurrent users on a single server with mean login
times under half a second and maximum throughput of more than 79 logins per
second. We've seen some tips for tuning Liferay Portal, and we have to keep in
mind the adage about tuning: load test and profile, tune, repeat.

Configuring a Content Delivery Network is also advantageous when you want to 
increase your portal's performance. By accessing static resources from a local 
server, latency time and your application server's load are drastically reduced. 

In all, Liferay Portal gives you all the options you need to build a
high-performance, robust environment that supports your enterprise.

