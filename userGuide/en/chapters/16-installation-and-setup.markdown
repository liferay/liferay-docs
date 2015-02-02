
#  Installation and Setup [](id=installation-and-setup-liferay-portal-6-2-user-guide-15-en)

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

## Editions of Liferay [](id=editions-of-liferay-liferay-portal-6-2-user-guide-15-en)

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

## Obtaining Liferay Portal [](id=obtaining-liferay-portal-liferay-portal-6-2-user-guide-15-en)

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

## Liferay installation overview [](id=liferay-installation-overview-liferay-portal-6-2-user-guide-15-en)

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

## Liferay's database [](id=liferays-database-liferay-portal-6-2-user-guide-15-en)

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

---

![Tip](../../images/01-tip.png) **Note:** The below instructions are not the
recommended set up for Liferay installations, but the procedure is documented
here so enterprises with more restrictive standards can install Liferay with
more strict--but suboptimal--database settings. If it's at all possible,
Liferay recommends that you use the automatic method as documented above instead
of the procedure outlined below.

---

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

## Liferay Marketplace [](id=liferay-marketplace-liferay-portal-6-2-user-guide-15-en)

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

### Server is Firewalled without Access to the Internet [](id=server-is-firewalled-without-access-to--liferay-portal-6-2-user-guide-15-en)

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

### Application Server Does Not Support Hot Deploy [](id=application-server-does-not-support-hot-liferay-portal-6-2-user-guide-15-en)

If your application server does not support hot deploy, you can't leverage
Liferay's auto deploy feature. You can, however, manually deploy the
plugin in two steps:

1. Use Liferay's tools to "pre-deploy" the file.

2. Then use your app server's tools to do the actual deployment.

This is because Liferay makes deployment-time modifications to the plugins right
before they are actually deployed to the application server. Detailed
instructions can be found under [Deploy Issues for Specific Containers](https://www.liferay.com/documentation/liferay-portal/6.2/user-guide/-/ai/plugin-management-liferay-portal-6-2-user-guide-14-en). 

### Limited Database Access [](id=limited-database-access-liferay-portal-6-2-user-guide-15-en)

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

## Liferay Home [](id=liferay-home-liferay-portal-6-2-user-guide-15-en)

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

---

![Tip](../../images/01-tip.png) **Note:** To avoid using the setup wizard so you
can configure everything manually from a `portal-ext.properties` file, you must
disable the Setup Wizard by specifying `setup.wizard.enabled=false` in the
`portal-ext.properties`. Also, note that property values in
`portal-setup-wizard.properties` (the file created in Liferay Home by the Setup
Wizard) override property values in `portal-ext.properties`. 

---

Let's move on and discuss Liferay Portal security. 

## Configuring Liferay Portal Security [](id=configuring-liferay-portal-security-liferay-portal-6-2-user-guide-15-en)

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

## Choosing IPv4 or IPv6 [](id=choosing-ipv4-or-ipv6-liferay-portal-6-2-user-guide-15-en)

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

## Installing a bundle [](id=installing-a-bundle-liferay-portal-6-2-user-guide-15-en)

Liferay bundles contain the same directory structure regardless of application
server. The top-level folder is named for the release of Liferay. This folder is
called *Liferay Home*, and we refer to it thoughout this documentation. 

![Figure 15.1: Bundle directory structure](../../images/02-bundle-directory-structure.png) 

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

## App Servers [](id=app-servers-liferay-portal-6-2-user-guide-15-en)

When it comes time to install Liferay Portal on your server, you'll find it's
easiest to do this by starting with a bundle. But many organizations can't do
that. There may be an existing infrastructure into which you're installing
Liferay, or you may have standardized on a particular application server. You'll
be happy to know that Liferay Portal has been designed to work well with all the
leading application servers and that it's easy and straightforward to install.
But before we get started, we need to go over a few concepts; namely, data
sources and mail sessions. These were touched on in the section on bundles above
but we'll look at them in more detail now. 

### Using data sources [](id=using-data-sources-liferay-portal-6-2-user-guide-15-en)

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

### Using mail sessions [](id=using-mail-sessions-liferay-portal-6-2-user-guide-15-en)

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

## Installing Liferay on an existing application server [](id=installing-liferay-on-an-existing-appli-liferay-portal-6-2-user-guide-15-en)

This section contains detailed instructions for installing Liferay Portal using
its .war file distribution. This allows system administrators to deploy Liferay
in existing application server installations. It is recommended that you have a
good understanding of how to deploy Java EE applications in your application
server of choice.

### Installing Liferay in five easy steps [](id=installing-liferay-in-five-easy-steps-liferay-portal-6-2-user-guide-15-en)

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

---

![Tip](../../images/01-tip.png) **Tip:** Note that Liferay *requires* JDK 6 or
greater. Do not attempt to install Liferay 6.2 on an application server that
runs under Java 5 or lower; it will not work. If you are running an
application server that ships with a JDK and that JDK is version 5 or lower,
you'll need to upgrade your application server to run current versions of
Liferay Portal.

---

Without further ado, let's get to the application servers. The first one we'll
cover is Mule Tcat. If you don't have an application server preference, and you
want support all the way down to the application server from Liferay, then Mule
Tcat is your solution. After we cover Mule Tcat, we'll look at all the supported
application servers in alphabetical order. 

## Installing Liferay on Mulesoft Tcat [](id=installing-liferay-on-mule-tcat-liferay-portal-6-2-user-guide-15-en)

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

### Dependency Jars [](id=dependency-jars-liferay-portal-6-2-user-guide-15-en)

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

### Tcat Configuration [](id=tcat-configuration-liferay-portal-6-2-user-guide-15-en)

If you're installing Liferay Portal onto an existing Tcat server, you should be
familiar with the Tcat Administration Console. The following instructions assume
you have a Tcat server with the Administration Console and a separate, managed
Tcat server instance where you'll deploy Liferay. To find information specific
to Tcat server installation and management, see [Mulesoft's Tcat Documentation](http://www.mulesoft.org/documentation/display/TCAT/Home). You have to do a few things to configure your managed Tcat server instance: 

- Set environment variables

- Create a context for your web application

- Modify the list of classes/JARs to be loaded

- Specify URI encoding

![Figure 15.2: You can log in to the Tcat Administration Console to manage your Tcat servers.](../../images/15-tcat-sign-in.png)

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

![Figure 15.3: You can edit your Tcat configuration files in the Administration Console.](../../images/15-tcat-server-files.png)

Excellent work! Now it's time to configure your database.

### Database Configuration [](id=database-configuration-liferay-portal-6-2-user-guide-15-en)

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

### Mail Configuration [](id=mail-configuration-liferay-portal-6-2-user-guide-15-en)

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

### Configuring your Database and Mail Session [](id=configuring-your-database-and-mail-sess-liferay-portal-6-2-user-guide-15-en)

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

### Enabling PACL [](id=enabling-pacl-liferay-portal-6-2-user-guide-15-en)

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

### Deploying Liferay [](id=deploying-liferay-liferay-portal-6-2-user-guide-15-en)

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

   ![Figure 15.4: Upload your Liferay Portal WAR file using the Deployments tab of the Tcat Administration Console.](../../images/15-tcat-upload-webapp.png)

3. Once you've entered all the deployment details, you can select *Deploy*.
   Once you see a *Successful* message in the Tcat Administration Console,
   you're ready to launch Liferay Portal on Tcat!

Now you can navigate to Liferay and follow the setup wizard. Congratulations
on successfully installing and deploying Liferay on Mule Tcat!

## Installing Liferay on GlassFish 4 [](id=installing-liferay-on-glassfish-3-liferay-portal-6-2-user-guide-15-en)

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

### Dependency Jars [](id=dependency-jars-liferay-portal-6-2-user-guide-15-en-0)

Liferay depends on jar files found in the Liferay Dependencies Archive. You
should also have installed your database driver.

1. Navigate to the folder that corresponds to the domain in which you'll be
   installing Liferay. Inside this folder is a sub-folder named `lib` (e.g.
   `/glassfish-4.0-web/glassfish4/glassfish/domains/domain1/lib`).

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

#### Domain Configuration [](id=domain-configuration-liferay-portal-6-2-user-guide-15-en)

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
`glassfish-4.0-web/glassfish4/glassfish/domains/domain1/config/domain.xml`,
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
`glassfish-4.0-web/glassfish4/domains/domain1/config/domain.xml` file contains two lists
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

Edit your `domain1/config/server.policy` and append the following
lines to the end of the file:

    grant {
        permission java.security.AllPermission;
    };

Delete, rename, or move the `domain1/docroot/index.html` file to another
location to allow your Liferay Portal default page to be displayed.

Next, let's get your database configured.

### Database Configuration [](id=database-configuration-liferay-portal-6-2-user-guide-15-en-0)

If you want to use GlassFish to manage your domain's data source, follow the
instructions found in this section. If you want to use Liferay Portal to manage
your data source, you can skip this section.

1. Start your domain's application server if it is not already running.

2. Go to the GlassFish console URL:
   [http://localhost:4848](http://localhost:4848/). 

3. Under *Common Tasks*, navigate to *Resources* &rarr; *JDBC* &rarr; *JDBC
   Connection Pools*

    ![Figure 15.5: In the GlassFish administration console, navigate to JDBC Connection Pools.](../../images/11-glassfish31-connection-pools.png)

4. Click *New...*.

5. On the first screen (Step 1 of 2), enter the name `LiferayPool` for your
   connection pool, select the `javax.sql.ConnectionPoolDataSource` resource
   type, and select your database driver vendor (e.g. `MySQL`). See the
   following figure:

    ![Figure 15.6: It's easy to configure a new Glassfish JDBC Connection Pool. Just enter a pool name, select a resource type, and specify a database driver vendor.](../../images/11-glassfish-31-jdbc-connection-pool.png)

6. Click *Next* to advance to the next step in creating your JDBC connection
   pool.

7. From the top of this screen (Step 2 of 2), scroll down to the *Additional
   Properties* section.

    ![Figure 15.7: GlassFish JDBC Connection Pool Properties](../../images/11-glassfish-31-jdbc-connection-pool-props.png)  

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

### Mail Configuration [](id=mail-configuration-liferay-portal-6-2-user-guide-15-en-0)

If you want to use GlassFish to manage your mail session, follow GlassFish's
documentation on configuring a JavaMail session with a JNDI name of
`mail/MailSession`. If you want to use Liferay Portal to manage your mail
session, you can skip this step.

### Domain Configuration - Continued [](id=domain-configuration-continued-liferay-portal-6-2-user-guide-15-en)

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

### PACL Configuration [](id=pacl-configuration-liferay-portal-6-2-user-guide-15-en)

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

### Deploy Liferay [](id=deploy-liferay-liferay-portal-6-2-user-guide-15-en)

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

![Figure 15.8: GlassFish provides an administrative console which you can use to deploy Liferay.](../../images/11-deploying-liferay-in-glassfish-4.0.png)

Once you have deployed Liferay via GlassFish's administrative console, restart
GlassFish.

Your installation of Liferay Portal on GlassFish is complete!

## Installing Liferay on JBoss 7.1 [](id=installing-liferay-on-jboss-7-1)

**Liferay Home** is one folder above JBoss's install location.

1. Download and install JBoss AS 7.1.x into your preferred directory. This
   directory is referred to as `$JBOSS_HOME` throughout this section.

2. Download the latest version of the Liferay Portal `.war` file.

3. Download Liferay's Portal Dependencies.

Now that you have all of your installation files, you are ready to start
installing and configuring Liferay on JBoss.

### Dependency Jars [](id=dependency-jars)

Let's work with the dependency jar files first.

1. Create the folder `$JBOSS_HOME/modules/com/liferay/portal/main`. Unzip the
   the Liferay Portal Dependencies zip file and copy the .jar files to this
   folder. 

2. Download your database driver `.jar` file and copy it into the
   same folder. For example, for MySQL, download the MySQL Connector/J driver
   from
   [http://dev.mysql.com/downloads/connector/j/](http://dev.mysql.com/downloads/connector/j/)
   and put its `.jar` file into the
   `$JBOSS_HOME/modules/com/liferay/portal/main` folder.

3. Create the file `module.xml` in the
   `$JBOSS_HOME/modules/com/liferay/portal/main` folder and insert the following
   contents:

        <?xml version="1.0"?>

        <module xmlns="urn:jboss:module:1.0" name="com.liferay.portal">
            <resources>
                <resource-root path="mysql-connector-java-[version]-bin.jar" />
                <resource-root path="portal-service.jar" />
                <resource-root path="portlet.jar" />
            </resources>
            <dependencies>
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

Great! You have your `.jar` files ready. 

### Running Liferay on JBoss 7.1 in Standalone Mode vs. Domain Mode [](id=running-liferay-on-jboss-7-1-in-standalone-mode-vs-domain-mode)

JBoss 7.1 can be launched in either *standalone* mode or *domain* mode. Domain
mode allows multiple application server instances to be managed from a single
control point. A collection of such application servers is known as a *domain*.
For more information on standalone mode vs. domain mode, please refer to the
section on this topic in the [JBoss 7.1 Admin Guide](https://docs.jboss.org/author/display/AS71/Admin+Guide#AdminGuide-Operatingmodes).
Liferay fully supports JBoss 7.1 when it runs in standalone mode but not when it
runs in domain mode.

You can run Liferay on JBoss 7.1 in domain mode, but this method is not fully
supported. In particular, Liferay's hot-deploy does not work, since JBoss 7.1
cannot deploy non-exploded `.war` files in domain mode. Instead, `.war` files
are in the `domain/data/content` directory. Deployments are only possible using
the command line interface. This prevents many Liferay plugins from working as
intended. For example, JSP hooks don't work on JBoss 7.1 running in domain mode, 
since Liferay's JSP override mechanism relies on the application server
reloading customized JSP files from the exploded plugin `.war` file location.
Other plugins, such as service or action hooks, should still work properly since
they don't require JBoss to access anything (such as JSP files) from an exploded
`.war` file on the file system. 

+$$$

**Note:** This does not prevent Liferay from running in a clustered environment
on multiple JBoss servers. You can set up a cluster of Liferay instances running
on JBoss 7.1 servers running in standalone mode. Please refer to the chapter of
this guide on
[Configuring Liferay for High Availability](https://www.liferay.com/documentation/liferay-portal/6.2/user-guide/-/ai/configuring-liferay-for-high-availabili-liferay-portal-6-2-user-guide-20-en)
for information on setting up a Liferay cluster.

$$$

### Configuring JBoss [](id=configuring-jboss)

Let's make some adjustments in your configuration to support using Liferay.

You can specify the JBoss server instance's configuration in the XML file
`$JBOSS_HOME/standalone/configuration/standalone.xml`. You must also make some
modifications to your configuration and startup scripts found in the
`$JBOSS_HOME/bin/` folder.  Lastly, you'll need to make some modifications in
your `$JBOSS_HOME/modules/`. Let's start with the changes to `standalone.xml`.

Make the following modifications to `standalone.xml`:

1.  Add the following system properties between the `</extensions>` and
`<management>` tags:

        <system-properties>
            <property name="org.apache.catalina.connector.URI_ENCODING" value="UTF-8" />
            <property name="org.apache.catalina.connector.USE_BODY_ENCODING_FOR_QUERY_STRING" value="true" />
        </system-properties>

2.  Add a timeout for the deployment scanner by setting
`deployment-timeout="240"` as seen in the excerpt below.

        <subsystem xmlns="urn:jboss:domain:deployment-scanner:1.1">
            <deployment-scanner deployment-timeout="240" path="deployments" relative-to="jboss.server.base.dir" scan-interval="5000"/>
        </subsystem>

3.  Add the following JAAS security domain to the security subsystem
`<security-domains>` defined in element `<subsystem
xmlns="urn:jboss:domain:security:1.1">`.

        <security-domain name="PortalRealm">
            <authentication>
                <login-module code="com.liferay.portal.security.jaas.PortalLoginModule" flag="required" />
            </authentication>
        </security-domain>

4.  Disable the welcome root of the web subsystem's virtual server default host
by specifying `enable-welcome-root="false"`.

        <subsystem xmlns="urn:jboss:domain:web:1.1" default-virtual-server="default-host">
            <connector name="http" protocol="HTTP/1.1" scheme="http" socket-binding="http"/>
            <virtual-server name="default-host" enable-welcome-root="false">
               <alias name="localhost" />
               <alias name="example.com" />
            </virtual-server>
        </subsystem>        

5.  Insert the following `<configuration>` element inside the web subsystem
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

On Windows, comment out the initial `JAVA_OPTS` assignment as demonstrated in
the following line:

        rem set "JAVA_OPTS=-Xms64M -Xmx512M -XX:MaxPermSize=256M"

Then add the following `JAVA_OPTS` assignment one line above the
    `:JAVA_OPTS_SET` line found at end of the file:

        set "JAVA_OPTS=%JAVA_OPTS% -Dfile.encoding=UTF-8 -Djava.net.preferIPv4Stack=true -Djava.security.manager -Djava.security.policy=$JBOSS_HOME/bin/server.policy -Djboss.home.dir=$JBOSS_HOME -Duser.timezone=GMT -Xmx1024m -XX:MaxPermSize=256m"

On Unix, merge the following values into your settings for `JAVA_OPTS`, 
replacing any matching attributes with the ones found in the assignment
below:

        JAVA_OPTS="$JAVA_OPTS -Dfile.encoding=UTF-8 -Djava.net.preferIPv4Stack=true -Djava.security.manager -Djava.security.policy=$JBOSS_HOME/bin/server.policy -Djboss.home.dir=$JBOSS_HOME -Duser.timezone=GMT -Xmx1024m -XX:MaxPermSize=256m

Make sure you replace the `$JBOSS_HOME` references with the appropriate
directory. You'll notice some Java security options. You'll finish configuring
the Java security options in the *Security Configuration* section. 

Lastly, navigate to the `$JBOSS_HOME/modules/sun/jdk/main/module.xml` file and
insert the following path names inside the `<paths>...</paths>` element: 

        <path name="com/sun/crypto" />
        <path name="com/sun/crypto/provider" />
        <path name="com/sun/image/codec/jpeg" />
        <path name="com/sun/org/apache/xml/internal/resolver" />
        <path name="com/sun/org/apache/xml/internal/resolver/tools" />

The added paths resolve issues with portal deployment exceptions and image
uploading problems on a Liferay Portal instance running on JBoss 7.1.x. 

The prescribed script modifications are now complete for your Liferay
installation on JBoss. Next you'll configure mail and the database. 

### Using the IBM JDK with JBoss [](id=using-the-ibm-jdk-with-jboss-liferay-portal-6-2-user-guide-16-en)

If you plan on using the IBM JDK with your JBoss application server, follow the
instructions in this section. If you plan on using another type of JDK, you can
skip this section. 

Currently, there are bugs in the
`$JBOSS_HOME/modules/org/jboss/as/server/main/jboss-as-<$JBOSS_VERSION>.Final.jar`
file regarding the IBM JVM
([LPS-39705](http://issues.liferay.com/browse/LPS-39705) and
[JBPAPP-9353](http://issues.jboss.org/browse/JBPAPP-9353)), which requires
additional steps to ensure a successful deployment with Liferay. 

Open the `$JBOSS_HOME/modules/com/liferay/portal/main/module.xml` file and
insert the following dependency within the `<dependencies>` element: 

    <module name="ibm.jdk" />

Next, you'll need to include a patch from Liferay's source code for one of
JBoss' default `.jar` files. This can be done by downloading the
`liferay-portal` repository's ZIP file by clicking *Download ZIP* on the
repository's [GitHub page](https://github.com/liferay/liferay-portal). Once
you've downloaded the Liferay source, unzip the source into a temporary folder.
This location will be referred to as `$LIFERAY_SOURCE`. 

In summary, you'll need to update the `ServerDependenciesProcessor.class` file
in the `jboss-as-[$JBOSS_VERSION].Final.jar` file to specify the IBM JDK. The
steps to insert the patch can be referenced below. 

1. Copy the `jboss-as-[$JBOSS_VERSION].Final.jar` file from
   `$JBOSS_HOME/modules/org/jboss/as/server/main` to the
   `$LIFERAY_SOURCE/tools/servers/jboss/patches/JBPAPP-9353/classes` folder. 
 
2. Navigate to the
   `$LIFERAY_SOURCE/tools/servers/jboss/patches/JBPAPP-9353/classes` directory
   in a command prompt and enter the following statement: 
    
        jar uf jboss-as-server-[$JBOSS_VERSION].Final.jar org/jboss/as/server/deployment/module/ServerDependenciesProcessor.class

    This command inserts the `ServerDependenciesProcessor.class` file into the
    `jboss-as-[$JBOSS_VERSION].Final.jar` file's
    `org/jboss/as/server/deployment/module` folder. You can reference the
    official documentation for updating a JAR file at
    [http://docs.oracle.com/javase/tutorial/deployment/jar/update.html](http://docs.oracle.com/javase/tutorial/deployment/jar/update.html). 

3. Copy the `jboss-as-[$JBOSS_VERSION].Final.jar` file back to its original
   `$JBOSS_HOME/modules/org/jboss/as/server/main` folder. 

Lastly, you'll need to make a few changes in your `$JBOSS_HOME/modules`
directory. 

1. Create the folder `$JBOSS_HOME/modules/ibm/jdk/main`. Create a new file
called `module.xml` in that folder.

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

Your JBoss application server is now configured to use the IBM JDK. Next, you'll
learn about managing a data source with JBoss. 

### Database Configuration [](id=database-configuration)

If you want JBoss to manage your data source, follow the instructions in this
section. If you want to use the built-in Liferay data source, you can skip this
section.

Modify `standalone.xml` and add your data source and driver in the
`<datasources>` element of your data sources subsystem.

1. First, add your data source inside the `<datasources>` element.

        <datasource jndi-name="java:/jdbc/LiferayPool" pool-name="LiferayPool" enabled="true" jta="true" use-java-context="true" use-ccm="true">
            <connection-url>jdbc:mysql://localhost/lportal</connection-url>
            <driver>mysql</driver>
            <security>
                <user-name>root</user-name>
                <password>root</password>
            </security>
        </datasource>

    Be sure to replace the database name (i.e. `lportal`), user, and
    password the appropriate values. 

2. Add your driver to the `<drivers>` element also found within the
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

Now that you've configured your data source, the mail session is next. 

### Mail Configuration [](id=mail-configuration)

If you want JBoss to manage your mail session, use the following instructions.
If you want to use the built-in Liferay mail session, you can skip this section.

Specify your mail subsystem in `standalone.xml` as in the following example:

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
 
You've got mail! Next, you'll make sure Liferay can connect using your new mail
session and database.

### Configuring data sources and mail sessions [](id=configuring-data-sources-and-mail-sessions)

Now that your data source and mail session are set up, you need to ensure
Liferay Portal can access them.

1.  First, navigate to the Liferay Home folder, which is one folder above JBoss's
    install location (i.e. `$JBOSS_HOME/..`).

2.  If you're using *JBoss* to manage your data source, add the following
    configuration to your `portal-ext.properties` file in your *Liferay Home* to
    refer to your data source:

        jdbc.default.jndi.name=java:jdbc/LiferayPool

    If you're using *Liferay Portal* to manage your data source, follow the
    instructions for using the setup wizard.

3.  If you're using *Liferay Portal* to manage your mail session, this
    configuration is done in Liferay Portal. That is, after starting your
    portal as described in the *Deploy Liferay* section, go to *Control Panel
    &rarr; Server Administration &rarr; Mail* and enter the settings for your
    mail session.

    If you're using *JBoss* to manage your mail session, add the following
    configuration to your `portal-ext.properties` file to reference that mail
    session:

        mail.session.jndi.name=java:mail/MailSession

Before you deploy Liferay Portal on your JBoss app server, you should enable and
configure Java security so you can use Liferay's plugin security manager
with your downloaded Liferay applications.

### Security Configuration [](id=security-configuration)

When you're ready to begin using other people's apps from Marketplace, you'll
want to protect your portal and your JBoss server from security threats. To do
so, you can enable Java Security on your JBoss server and specify a security
policy to grant your portal access to your server.

Remember, we set the `-Djava.security.manager` and `-Djava.security.policy` Java
options in the `standalone.conf.bat` file earlier in the *Configuring JBoss*
section. The `-Djava.security.manager` Java option enables security on 
JBoss. Likewise, the `-Djava.security.policy` Java option lists the
permissions for your server's Java security policy. If you have not set these
options, you'll need to do so before using Java security.

This configuration opens up all permissions. You can tune the permissions in
your policy later. Create the `$JBOSS_HOME/bin/server.policy` file and add the
following contents:

    grant {
        permission java.security.AllPermission;
    };

For extensive information on Java SE Security Architecture, see its
specification documents at
[http://docs.oracle.com/javase/7/docs/technotes/guides/security/spec/security-spec.doc.html](http://docs.oracle.com/javase/7/docs/technotes/guides/security/spec/security-spec.doc.html).
Also, see section [*Understanding Plugin Security Management*](https://www.liferay.com/documentation/liferay-portal/6.2/development/-/ai/understanding-plugin-security-management-liferay-portal-6-2-dev-guide-11-en)
in the Developer's Guide to learn how to configure Liferay plugin
access to resources.

### JSF Configuration [](id=jsf-configuration)

If you plan on using JSF applications in your application server, follow the
instructions below. In this section, you'll learn how to upgrade Mojarra and
Weld so your app server's versions are identical to the versions used by Liferay
Faces. 

#### Upgrading Mojarra [](id=upgrading-mojarra)

Some versions of JBoss 7.1.x are not bundled with the correct Mojarra version
necessary to use Liferay Faces. For example, JBoss AS 7.1.1 comes with Mojarra
2.1.7 in the global classpath. Since Liferay Faces uses Mojarra 2.1.21, you'll
need to download a newer version of the `jsf-api.jar` and `jsf-impl.jar`
artifacts. 

1. Download
[`jsf-api-2.1.21.jar`](http://search.maven.org/#artifactdetails%7Ccom.sun.faces%7Cjsf-api%7C2.1.21%7Cjar)
and copy it to the following location: 

        $JBOSS_HOME/modules/javax/faces/api/main/jsf-api-2.1.21.jar

2. Open the `$JBOSS_HOME/modules/javax/faces/api/main/module.xml` file and
   comment out the reference to the version of the JAR that comes with the
   server. For example: 

        <!-- <resource-root path="jboss-jsf-api_2.1_spec-2.0.1.Final.jar"/> -->

3. Add a reference to the new JAR in the same `module.xml` file: 

        <resource-root path="jsf-api-2.1.21.jar"/>

4. Add the following module to the `<dependencies>` section: 

        <dependencies>
            ...
            <module name="com.sun.jsf-impl"/>
        </dependencies>

5. Download
[`jsf-impl-2.1.21.jar`](http://search.maven.org/#artifactdetails%7Ccom.sun.faces%7Cjsf-impl%7C2.1.21%7Cjar)
and copy it to the following location: 

        $JBOSS_HOME/modules/com/sun/jsf-impl/main/jsf-impl-2.1.21.jar

6. Open the `$JBOSS_HOME/modules/com/sun/jsf-impl/main/module.xml` file and
   comment out the reference to the version of the JAR that comes with the
   server. For example: 

        <!-- <resource-root path="jsf-impl-2.1.7-jbossorg-2.jar"/> -->

7. Add a reference to the new JAR in the same `module.xml` file: 

        <resource-root path="jsf-impl-2.1.21.jar"/>

Congratulations! You've officially upgraded Mojarra! If you'd like to verify
that you're using the correct version of Mojarra at runtime, download the
following [demo portlet](http://www.liferay.com/community/liferay-projects/liferay-faces/demos#jsf2-portlet)
and add it to a portal page. You should see a bulleted list of version info at
the bottom of the portlet.

![Figure 1: Make sure the Mojarra version displayed is the one you configured during the upgrade: Mojarra 2.1.21.](../../images/jboss-mojarra-upgrade.png)

Next you'll learn how to upgrade Weld. 

#### Upgrading Weld [](id=upgrading-weld)

Some versions of JBoss 7.1.x are not bundled with the correct Weld version
necessary to use Liferay Faces. For example, JBoss AS 7.1.1 comes with Weld
1.1.5 in the global classpath. Since Liferay Faces uses Weld 1.1.10, you'll
need to download a newer version of the `weld-core.jar` artifact. 

1. Download
[`weld-core-1.1.10.Final.jar`](http://search.maven.org/#artifactdetails%7Corg.jboss.weld%7Cweld-core%7C1.1.10.Final%7Cjar)
and copy it to the following location: 

        $JBOSS_HOME/modules/org/jboss/weld/core/main/weld-core-1.1.10.Final.jar

2. Open the `$JBOSS_HOME/modules/org/jboss/weld/core/main/module.xml` file and
   comment out the reference to the version of the JAR that comes with the
   server. For example: 

        <!-- <resource-root path="weld-core-1.1.5.AS71.Final.jar"/> -->

3. Add a reference to the new JAR in the same `module.xml` file: 

        <resource-root path="weld-core-1.1.10.Final.jar"/>

Now you're ready to deploy Liferay Portal. 

### Deploy Liferay [](id=deploy-liferay)

1. If the folder `$JBOSS_HOME/standalone/deployments/ROOT.war` already exists in
   your JBoss installation, delete all of its subfolders and files. Otherwise,
   create a new folder `$JBOSS_HOME/standalone/deployments/ROOT.war`.

2. Unzip the Liferay `.war` file into the `ROOT.war` folder.

3. In the `ROOT.war` file, open the `WEB-INF/jboss-deployment-structure.xml`
   file. In this file, replace the `<module name="com.liferay.portal" />`
   dependency with the following configuration: 

        <module meta-inf="export" name="com.liferay.portal">
            <imports>
                <include path="META-INF" />
            </imports>
        </module>

    This allows OSGi plugins like Audience Targeting to work properly, by
    exposing the Portal API through the OSGi container. 

4. In the same `jboss-deployment-structure.xml` file, find the
   `<jboss-deployment-structure>` tag and update the `1.0` number within the
   `xmlns` attribute to `1.1`. 

5. To trigger deployment of `ROOT.war`, create an empty file named
   `ROOT.war.dodeploy` in  your `$JBOSS_HOME/standalone/deployments/` folder.
   On startup, JBoss detects the presence of this file and deploys it as a web
   application.

6. Remove `eclipselink.jar` from
   `$JBOSS_HOME/standalone/deployments/ROOT.war/WEB-INF/lib` to insure the
   Hibernate persistence provider is used instead of the one provided in the
   `eclipselink.jar`.

    Now it's time to start Liferay Portal on JBoss!

7.  Start the JBoss application server.
 
Now you are truly *the boss* when it comes to deploying Liferay Portal on JBoss!

## Installing Liferay on Tomcat 7 [](id=installing-liferay-on-tomcat-7)

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

### Dependency Jars [](id=dependency-jars-liferay-portal-6-2-user-guide-16-en)

Liferay Portal depends on several `JAR` files found in the Liferay
Dependencies Archive. In addition to these, you need the proper driver for your
database. You can get the necessary dependencies by following these steps:

1. Create a folder named `ext` in `$TOMCAT_HOME/lib`.

2. Unzip the Liferay Dependencies and copy the .jar files to
`$TOMCAT_HOME/lib/ext`. 

3. Download the `support-tomcat.jar` file from
   [http://search.maven.org/#artifactdetails|com.liferay.portal|support-tomcat|6.2.1|jar](http://search.maven.org/#artifactdetails|com.liferay.portal|support-tomcat|6.2.1|jar)
   and copy it into your `$TOMCAT_HOME/lib/ext` directory. This `JAR` provides
   classes that extend some Tomcat-specific classes in order to support
   Liferay's runtime.

4. Next, you need to download a few third party `.jar` files that are included
   as part of the Liferay source distribution, but are not automatically included
   with Tomcat. Place these `.jar` files into `$TOMCAT_HOME/lib/ext`:

    - `jta.jar`: You can get this `.jar`, which manages transactions, from
            [http://www.oracle.com/technetwork/java/javaee/jta/index.html](http://www.oracle.com/technetwork/java/javaee/jta/index.html)
    - `mail.jar`: You can get this `.jar` from
            [http://www.oracle.com/technetwork/java/index-138643.html](http://www.oracle.com/technetwork/java/index-138643.html)
    - `persistence.jar`: You can learn about the Java Persistence API and how
      to download it from
            [http://www.oracle.com/technetwork/java/javaee/tech/persistence-jsp-140049.html](http://www.oracle.com/technetwork/java/javaee/tech/persistence-jsp-140049.html)

5. Make sure the JDBC driver for your database is accessible by Tomcat. In the
case of MySQL, use `mysql-connector-java-{$version}-bin.jar`. You can download
the latest MySQL JDBC driver from
[http://dev.mysql.com/downloads/connector/j/](http://dev.mysql.com/downloads/connector/j/).
Extract the JAR file and copy it to `$TOMCAT_HOME/lib/ext`.

6. There are a few other JARs that come with a typical Liferay bundle that you
   might want to download and place in your `$TOMCAT_HOME/lib/ext` folder. They
   include these:

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

    You can download each third party `.jar` listed above from the provided
    websites, then place them into your `%TOMCAT_HOME/lib/ext` directory. However,
    they're also available in the Liferay source code, so if you have access to the
    Liferay source or would like to download it for this purpose, feel free to copy
    the `.jar` files from there. Assuming your local Liferay source
    directory is `$LIFERAY_SOURCE`, you can get all the third party `.jar` files
    listed above from `$LIFERAY_SOURCE/lib/development`, with the exception of
    `ccpp.jar`, which is found in `$LIFERAY_SOURCE/lib/portal`.

Now that you have the necessary libraries in place, we'll move on to
configuring your domain.

### Tomcat Configuration [](id=tomcat-configuration)

There are several configuration steps you need to complete before Tomcat can
run Liferay. Let's get started.

1. First, you'll need to set the `CATALINA_OPTS` environment variable. Create a
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

2. Next, create a context for Liferay. Create a `ROOT.xml` file in
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
    loader. In the configuration above you will also find commented instructions and
    tags for configuring a JAAS realm, disabling persistent sessions and
    disabling sessions in general.

3. Next, make sure the libraries you added to `$TOMCAT_HOME/lib/ext` are loaded
when you start the server. Open `$TOMCAT_HOME/conf/catalina.properties` and
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

### Database Configuration [](id=database-configuration-liferay-portal-6-2-user-guide-16-en)

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

**Note:** The above resource definition assumes your database name is *lportal*
and your MySQL username and password are both *root*. You'll have to update
these values with your own database name and credentials.

$$$

Your Tomcat managed data source is now configured. Next is your mail
session.

### Mail Configuration [](id=mail-configuration-liferay-portal-6-2-user-guide-16-en)

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

Your mail session is configured. Next, you'll make sure Liferay can 
access your mail session and database.

### Configuring your database and mail session [](id=configuring-your-database-and-mail-session)

In this section you'll specify appropriate properties for connecting to your
database and mail session.

1. If you are using *Tomcat* to manage your data source, add the following
   configuration to your `portal-ext.properties` file in your *Liferay Home* to
   refer to your data source:

        jdbc.default.jndi.name=jdbc/LiferayPool

    Otherwise, if you are using *Liferay Portal* to manage your data source, follow
    the instructions for using the setup wizard.

2. If want to use *Liferay Portal* to manage your mail session, you can
   configure the mail session in Liferay Portal. That is, after starting
   your portal as described in the *Deploy Liferay* section, go to *Control
   Panel &rarr; Server Administration &rarr; Mail* and enter the settings for
   your mail session.

    Otherwise, if you are using *Tomcat* to manage your mail session, add the
    following configuration to your `portal-ext.properties` file to reference
    that mail session:

        mail.session.jndi.name=mail/MailSession

It's just that easy! Before you deploy Liferay Portal, you should configure
Portal Access Control Language (PACL) with Liferay on Tomcat. 

### Enabling PACL [](id=enabling-pacl)

To enable PACL, you need to enable the security manager and add some required
permissions to the server policy configuration file. This entails editing
two files in `$TOMCAT_HOME` you've already edited: 

- In `$TOMCAT_HOME/bin/setenv.sh` (if on Linux, Unix, or Mac OS) or
  `setenv.bat` (if on Windows) enable the security manager by inserting the
following code into the `CATALINA_OPTS` variable (inside the quotation marks):

    `-Djava.security.manager -Djava.security.policy=$CATALINA_BASE/conf/catalina.policy`

- In `$TOMCAT_HOME/conf/catalina.policy`, add the required permissions:

        grant {
            permission java.security.AllPermission;
        };

To enable the security manager on Tomcat, the server must be started with the
`-security` command line options. Shutdown your Tomcat instance and restart it
with the following command: 

    ./startup.sh -security

Tomcat reports the message `Using Security Manager` to your terminal. 

Now you have PACL enabled and configured for your portal. 

### Adding Mojarra [](id=adding-mojarra)

If you'd like to use JSF applications in your Tomcat application server, you'll
need to add Mojarra. If you do not plan on using JSF applications in your
application server, you can skip this section. 

The typical binary ZIP version of Tomcat does not contain any JSF runtime JARs
like Mojarra or MyFaces. This is because Tomcat is not a Java EE Application
Server like Oracle GlassFish, Oracle WebLogic, JBoss AS, or IBM WebSphere. Also,
Tomcat is not a Java EE Web Profile Server like Apache TomEE or Caucho Resin. 

There are two ways to approach using Mojarra with Tomcat: upgrading Tomcat's
context classpath or upgrading Tomcat's global classpath. Both methods require
adding/editing two JARs, which can be downloaded below: 

- [`jsf-api`](https://maven.java.net/content/repositories/releases/com/sun/faces/jsf-api/2.1.21/)
- [`jsf-impl`](https://maven.java.net/content/repositories/releases/com/sun/faces/jsf-impl/2.1.21/)

The typical approach for using Mojarra with Tomcat is to include `jsf-api.jar`
and `jsf-impl.jar` in the `WEB-INF/lib` folder in each JSF project. You can do
this by specifying the `jsf-api` and `jsf-impl` artifacts without a scope, or
with the scope set as `compile` (the default) in each JSF project: 

    <dependency>
        <groupId>com.sun.faces</groupId>
        <artifactId>jsf-api</artifactId>
        <version>2.1.21</version>
        <scope>compile</scope>
    </dependency>
    <dependency>
        <groupId>com.sun.faces</groupId>
        <artifactId>jsf-impl</artifactId>
        <version>2.1.21</version>
        <scope>compile</scope>
    </dependency>

Although it is possible to install Mojarra in the Tomcat global classpath, it
will not work properly without some small modifications to the `jsf-impl.jar`
dependency. The problem stems from the fact that the Mojarra
[`ConfigureListener`](https://svn.java.net/svn/mojarra~svn/tags/2.1.21/jsf-ri/src/main/java/com/sun/faces/config/ConfigureListener.java)
class is automatically registered for all contexts under `tomcat/webapps`
because it is specified as a `<listener>` in the
[META-INF/jsf-jsf_core.tld](https://svn.java.net/svn/mojarra~svn/tags/2.1.21/jsf-ri/conf/share/jsf_core.tld)
descriptor inside the `jsf-impl.jar` dependency.
Additionally, the
[META-INF/services/javax.faces.ServletContainerInitializer](https://svn.java.net/svn/mojarra~svn/tags/2.1.21/jsf-ri/conf/share/javax.servlet.ServletContainerInitializer)
will cause the
[FacesInitializer](https://svn.java.net/svn/mojarra~svn/tags/2.1.21/jsf-ri/src/main/java/com/sun/faces/config/FacesInitializer.java)
class to auto-register the `ConfigureListener` as well. Consequently, every
request issued in all contexts invokes the Mojarra `ConfigureListener`. This
can be a potential performance problem in a webapp environment and causes
incompatibilities with a portlet environment. Therefore, it is necessary to
disable automatic registration of the Mojarra `ConfigureListener` by modifying
the contents of the `jsf-impl.jar` dependency. 

To upgrade Tomcat's global classpath, follow the steps below: 

1. Copy `jsf-api.jar` and `jsf-impl.jar` to the `tomcat/lib` folder. 

2. Open a terminal window and navigate to the `tomcat/lib` folder: 

        cd tomcat/lib

3. Create a temporary folder named `jsf-impl` and navigate into it: 

        mkdir jsf-impl
        cd jsf-impl

4. Extract the Mojarra `jsf-impl.jar` dependency into the temporary folder: 

        jar xf ../jsf-impl-2.1.21.jar

5. Open the `META-INF/jsf_core.tld` file and remove the following lines: 

        <listener>
            <listener-class>com.sun.faces.config.ConfigureListener</listener-class>
        </listener>

6. Remove the Mojarra servlet container initializer: 

        rm META-INF/services/javax.servlet.ServletContainerInitializer

7. Overwrite the Mojarra `jsf-impl.jar` dependency by creating a new archive: 

        jar cf ../jsf-impl-2.1.21.jar META-INF/ com/

8. Remove the temporary folder: 

        cd ../
        rm -rf jsf-impl/

9. Follow only *one* of the following sub-steps below, depending on preference. 

    9.1 Specify the `liferay-faces-init.jar` dependency in each JSF project in
    order for the Mojarra `ConfigureListener` to be automatically started by
    Tomcat:

        <dependency>
            <groupId>com.liferay.faces</groupId>
            <artifactId>liferay-faces-init</artifactId>
            <version>3.1.3-ga4</version>
        </dependency>

    9.2 Specify the Mojarra `ConfigureListener` as a listener in the
    `WEB-INF/web.xml` descriptor in each JSF project: 

        <listener>
            <listener-class>com.sun.faces.config.ConfigureListener</listener-class>
        </listener>

10. Specify the `jsf-api` and `jsf-impl` dependencies as provided in each JSF
   project: 

        <dependency>
            <groupId>com.sun.faces</groupId>
            <artifactId>jsf-api</artifactId>
            <version>2.1.21</version>
            <scope>provided</scope>
        </dependency>
        <dependency>
            <groupId>com.sun.faces</groupId>
            <artifactId>jsf-impl</artifactId>
            <version>2.1.21</version>
            <scope>provided</scope>
        </dependency>

You've officially added Mojarra to your application server. Now you can deploy
Liferay. 
 

### Deploy Liferay [](id=deploy-liferay-liferay-portal-6-2-user-guide-16-en)

It's time to deploy Liferay as an exploded web archive within your
`$TOMCAT_HOME/webapps` folder. 

1. If you are manually installing Liferay on a clean Tomcat server, delete the
   contents of the `$TOMCAT_HOME/webapps/ROOT` directory. This removes the default
   Tomcat home page. Extract the Liferay `.war` file to
   `$TOMCAT_HOME/webapps/ROOT`.

   Now its time to launch Liferay Portal on Tomcat!

3. Start Tomcat by executing `$TOMCAT_HOME/bin/startup.bat` or
   `$TOMCAT_HOME/bin/startup.sh`.

Congratulations on successfully installing and deploying Liferay on Tomcat!

## Installing Liferay on Oracle WebLogic 12c (12.1.2 and higher) [](id=install-liferay-on-weblogic-12c-liferay-portal-6-2-user-guide-15-en)

In this section, you'll learn how to install Liferay on Oracle WebLogic 12c.
Since you're using Oracle WebLogic, you may be curious of Liferay's support of
XA transactions. Liferay doesn't require XA transactions, but it supports XA.
Let's get acquainted with how Liferay fits in with your current WebLogic domain. 

For demonstration purposes, we'll assume *Liferay Home* is one folder above the
domain to which you will be installing Liferay.

For example, if your domain location is
`/Oracle/Middleware/Oracle_Home/user_projects/domains/base_domain`, then your
Liferay Home is `/Oracle/Middleware/Oracle_Home/user_projects/domains`.

For this section, we will use `$WL_HOME` to refer to your WebLogic
server's installation `/Oracle/Middleware/Oracle_Home/wlserver`. 

Before you begin, make sure you have downloaded the latest Liferay `.war` file
and Liferay Portal dependencies from
[http://www.liferay.com/downloads/liferay-portal/additional-files](http://www.liferay.com/downloads/liferay-portal/additional-files).
The Liferay `.war` file is called `liferay-portal-[version].war` and
the dependencies file is called `liferay-portal-dependencies-[version].zip`.

These instructions assume you have already configured a domain and server and
that you have access to the WebLogic console.

If you still have the `mainWebApp` module installed, remove it first.

Let's get started by installing the `.jar` files Liferay needs.

### Dependency Jars [](id=dependency-jars-liferay-portal-6-2-user-guide-15-en-4)

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

### Configuring WebLogic [](id=configuring-weblogic-liferay-portal-6-2-user-guide-15-en)

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

From within domain, open the script file that starts the server that will host
the Liferay application. To deploy Liferay to a managed server (recommended)
you'll want to edit the `startManagedWebLogic.[cmd|sh]` file. If you're on
Windows, you'd add directives similar to those listed below, after the
`SETLOCAL` command:

    set "USER_MEM_ARGS=-Xmx1024m -XX:PermSize=512m"
    
    set "MW_HOME=D:\Oracle\Middleware\Oracle_Home\wlserver"
    
    set "JAVA_OPTIONS=%JAVA_OPTIONS% -da:org.apache.lucene... -da:org.aspectj..."

Make sure to set your `MW_HOME` value to your WebLogic server's location. On
Linux, you'd make similar changes replacing `%JAVA_OPTIONS%` with
`$JAVA_OPTIONS`. 

If you want to use Liferay to manage your database and mail session
(recommend), you can skip the next sections and go to the section
*Security Configuration* below. But if you want to configure your database
and/or mail session within WebLogic, start your WebLogic server and follow the
instructions in the next section. 

### Database Configuration [](id=database-configuration-liferay-portal-6-2-user-guide-15-en-4)

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

Your data source is set up in WebLogic Server. You'll need to configure a mail
session too. If you want to use Liferay to manage your portal's mail, then you
can skip the next section. Otherwise, you can follow the instructions in the
next section to configure a mail session to manage in WebLogic. 

### Mail Configuration [](id=mail-configuration-liferay-portal-6-2-user-guide-15-en-4)

If you want WebLogic to manage your mail sessions, use the following procedure.
If you want to use Liferay's built-in mail sessions, you can skip this section.

1.  Select *Mail Sessions* and create a new mail session which points to your
    mail server.

2.  Give it the name *Liferay Mail* and give it the JNDI name of
    `mail/MailSession` and click *Next*.

3.  Choose your server and then click *Finish*.

Now let's make sure Liferay can access this mail session.

### Domain Configuration - Continued [](id=domain-configuration-continued-liferay-portal-6-2-user-guide-15-en-0)

At this point, you've prepared your WebLogic server environment for installing
Liferay as an application.

If you want to use Liferay to manage your mail session (recommended), you
can configure the mail session in the Control Panel. After starting your portal
as described in the
[Deploy Liferay](/discover/deployment/-/knowledge_base/6-2/installing-liferay-on-oracle-weblogic-12c-12-1-2-and-h#deploy-liferay)
section, you can go to *Control Panel &rarr; Server
Administration &rarr; Mail* and enter the settings for your mail session.

If however you've configured WebLogic to manage your your mail session, you must
configure additional portal properties. Similarly, if you've configured WebLogic
to manage your database, you must configure additional portal properties. To
configure Liferay to use a WebLogic data source or WebLogic mail session, create
a `portal-ext.properties` file in your Liferay Home folder.

If you're using WebLogic to manage your mail session, add the following
configuration to your `portal-ext.properties` file to reference that mail
session:

    mail.session.jndi.name=mail/MailSession

If you're using WebLogic to manage your data source, add the following property
setting to your `portal-ext.properties` file:

    jdbc.default.jndi.name=jdbc/LiferayPool

Before you deploy Liferay, regardless of whether you're using Liferay or
WebLogic to manage your database or mail session, you should enable and
configure Java Security. This will enable your portal to use Liferay's plugin
security manager with the Liferay apps that you download and install from
Liferay Marketplace. 

### Security Configuration [](id=security-configuration-liferay-portal-6-2-user-guide-15-en-0)

When you are ready to start using other people's apps from Marketplace, you'll
want to protect your portal and your WebLogic server from security threats. To
do so, you can enable Java Security on your WebLogic server and specify a
security policy to grant your Liferay Portal access to your server. 

First, let's grant Liferay access to your server. For now, we'll open up all
permissions--you can fine-tune your policy's permissions later. Create a policy
file named `weblogic.policy` in your `$WL_HOME/server/lib` folder
and add the following contents:

    grant {
        permission java.security.AllPermission;
    };

To enable security on your WebLogic server and direct the server to your policy
file, open the `setDomainEnv.[cmd|sh]` file in your domain's folder. Then set
the `-Djava.security.manager` Java option and set the property
`-Djava.security.policy==` to the location of your `weblogic.policy` file. You
can specify both settings on the same line like this:  

    -Djava.security.manager -Djava.security.policy==$WL_HOME/server/lib

The double equals sign tells the app server to use this policy file on top of
any existing security policies. 

For extensive information on Java SE Security Architecture see its specification
documents at
[http://docs.oracle.com/javase/7/docs/technotes/guides/security/spec/security-spec.doc.html](http://docs.oracle.com/javase/7/docs/technotes/guides/security/spec/security-spec.doc.html).
Also, see section
[*Understanding Plugin Security Management*](https://www.liferay.com/documentation/liferay-portal/6.2/development/-/ai/understanding-plugin-security-management-liferay-portal-6-2-dev-guide-11-en)
in Chapter 12 of the Developer's Guide to learn how to configure Liferay plugin
access to resources. 

Next you'll learn how to configure your WebLogic application server for JSF
applications. 

## JSF Configuration [](id=jsf-configuration-liferay-portal-6-2-user-guide-16-en)

If you'd like to deploy JSF applications on your WebLogic application server,
you'll need to complete a few extra steps in your configuration process. If you
do not plan on using JSF applications in your application server, you can skip
this section. This section assumes you're using JSF 2.1 portlets. 

Complete the first section to ensure JSF applications deploy successfully to
your WebLogic application server. 

### Configuration for Deploying JSF Portlets [](id=configuration-for-deploying-jsf-portlet-liferay-portal-6-2-user-guide-16-en)

1. To avoid a `ViewExpiredException` with Ajax, disable the Liferay Portal
`ETagFilter` by adding the following property in the `portal-ext.properties`
file: 

        com.liferay.portal.servlet.filters.etag.ETagFilter=false

    For more information on this exception, refer to
    [FACES-1591](https://issues.liferay.com/browse/FACES-1591).

2. You'll need to adjust your memory settings for your environment variables.
   For your memory settings to be permanently set, they need to be hard-coded in
   the `startWebLogic.sh` script. Just above the definition of your home domain,
   add the following lines:

        export MW_HOME=$HOME/Oracle/Middleware/Oracle_Home
        export USER_MEM_ARGS="-Xms512m -Xmx1024m -XX:CompileThreshold=8000 -XX:PermSize=128m -XX:MaxPermSize=256m"

    Note that if you have many portlet WAR modules, you may need to increase
    memory. For example, the following lines reserves double the amount of
    memory: 

        export MW_HOME=$HOME/Oracle/Middleware/Oracle_Home
        export USER_MEM_ARGS="-Xms1024m -Xmx2048m -XX:CompileThreshold=8000 -XX:PermSize=256m -XX:MaxPermSize=512m"

3. If you're running the JSR 329 Portlet Bridge TCK, you'll need to include the
   `trinidad-api.jar` dependency in the global classpath (within the `lib`
   folder). 

4. In order for JSF 2.1 portlets to deploy correctly in WebLogic, the
   `WEB-INF/weblogic.xml` descriptor must be configured to fine-tune how class
   loading takes place. For a working example, please refer to the
   [weblogic.xml](https://github.com/liferay/liferay-faces/blob/3.2.x/demos/bridge/jsf2-portlet/src/main/webapp/WEB-INF/weblogic.xml)
   descriptor from a demo JSF portlet. 

5. Due to a deficiency in the XML parser that ships with WebLogic, it is
   necessary to include a custom [Apache Xerces](http://xerces.apache.org/)
   parser as a dependency. In order to include it in the proper position within
   the WebLogic classpath, the Xerces JARs are included in the Mojarra Shared
   Library. Therefore, it is necessary to add Xerces as a dependency in the
   portlet's WEB-INF/lib folder. For example: 

        <dependencies>
            <dependency>
                <groupId>xerces</groupId>
                <artifactId>xercesImpl</artifactId>
                <version>2.11.0</version>
            </dependency>
        </dependencies>

6. If using ICEfaces, PrimeFaces, or RichFaces, all JARs related to these
   projects my exist in `WEB-INF/lib`. 

Next, you'll need to upgrade Mojarra for your WebLogic application server. 
   
### Upgrading Mojarra [](id=upgrading-mojarra-liferay-portal-6-2-user-guide-16-en)

Liferay Faces requires JSF 2.1.21. However, the version of Mojarra that comes
with WebLogic 12c is version 2.1.20. Therefore, it is necessary to upgrade
Mojarra in WebLogic by creating a new Shared Library WAR with the updated
dependencies. 

1. Make sure your `MW_HOME` environment variable is defined (completed in step 2
   of the previous section).
    
2. Build the patched version of Mojarra:

        cd liferay-faces/support
        mvn -P weblogic clean install

3. Copy the patched version of Mojarra over the version that was shipped
   out-of-the-box: 

        cp $HOME/.m2/repository/com/oracle/weblogic/glassfish.jsf_1.0.0.0_2-1-21/12.1.2-0-0/glassfish.jsf_1.0.0.0_2-1-21-12.1.2-0-0.jar $MW_HOME/wlserver/modules/glassfish.jsf_1.0.0.0_2-1-20.jar

    Since the Mojarra API and Implementation JARs are present in the global
    classpath, `jsf-api.jar` and `jsf-impl.jar` must not be included in
    `WEB-INF/lib`. 

Now its the moment you've been waiting for: Liferay deployment! 

### Deploy Liferay [](id=deploy-liferay-liferay-portal-6-2-user-guide-15-en-4)

Although you can deploy Liferay on a WebLogic Admin Server, we recommend
deploying Liferay to a managed server. That way, if Liferay needs to be rolled
back to a particular state, the Admin Server can do that. In a managed server,
you'll also be able to startup / shutdown Liferay quicker and you'll more easily
be able to extend Liferay into a cluster configuration. 

Regardlesss of which server you target to host Liferay in your WebLogic server
domain, here are steps for deploying Liferay: 

1.  Start your WebLogic server if it's not already started.

2.  Go to *Deployments* and select the Liferay `.war` file from the file system,
    or click the *Upload Your File(s)* link to upload it and then click *Next*.

3.  Select *Install this deployment as an application* and click *Next*.

4.  Target the server (e.g., a managed server) on which to deploy Liferay. 

5.  If the default name is appropriate for your installation, keep it.
    Otherwise, give it a name of your choosing and click *Next*.

6.  Click *Finish*. After the deployment finishes, click *Save*.
    Liferay precompiles all the JSPs, and Liferay launches.

Congratulations! You are now running Liferay on Oracle WebLogic 12c.

## Installing Liferay on WebSphere 8.5 [](id=installing-liferay-on-websphere-8-5-liferay-portal-6-2-user-guide-15-en)

![Tip](../../images/01-tip.png) **Tip:** Throughout this installation and
configuration process, WebSphere prompts you to Click Save to apply changes to
Master Configuration. Do so intermittently to save your changes.

**Liferay Home** is in a folder called `liferay` in the home folder of the user
ID that is running WebSphere. 

To work correctly on WebSphere 8.5, IBM's PM90932 patch must be installed. You
can find more information about this patch
[here](http://www-01.ibm.com/support/docview.wss?uid=swg1PM90932). 

Please also note that the WebSphere Application Liberty Profile is not supported
by Liferay. 

### Preparing WebSphere for Liferay [](id=preparing-websphere-for-liferay-liferay-portal-6-2-user-guide-15-en)

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

    ![Figure 15.9: Choose the Advanced profile option to specify your own settings.](../../images/websphere-01-profile.png)

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

![Figure 15.10: The Summary page shows you what you selected, giving you the chance to go back and change something if it's not exactly what you want.](../../images/websphere-03-summary.png)

WebSphere then creates your profile and finishes with a message telling you the
profile was created successfully. You're now ready to install Liferay! 

### Copying portal dependencies [](id=copying-portal-dependencies-liferay-portal-6-2-user-guide-15-en)

Liferay ships with dependency .jars it needs to have on the global classpath.
These should be copied to WebSphere's global folder provided for this purpose: 

    [Install Location]/WebSphere/AppServer/lib/ext

If you have a JDBC database driver .jar, copy it to this location as well. 
Once you've copied the .jars, start the server profile you created for
Liferay. Once it starts, you're ready to configure your database. 

### Database Configuration [](id=database-configuration-liferay-portal-6-2-user-guide-15-en-6)

If you want WebSphere to manage the database connections, follow the
instructions below. Note this is not necessary if you're planning on using
Liferay's standard database configuration; in that case, skip this section.
You'll set your database information in Liferay's setup wizard after the
install. 

![Figure 15.11: WebSphere JDBC providers](../../images/02-websphere-jdbc-providers.png) 

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

    ![Figure 15.12: Modifying data source properties in WebSphere](../../images/02-modifying-data-source-properties-in-websphere.png) 

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

### Mail Configuration [](id=mail-configuration-liferay-portal-6-2-user-guide-15-en-6)

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

### Deploy Liferay [](id=deploy-liferay-liferay-portal-6-2-user-guide-15-en-6)

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

### Enabling Security for Portal Access Control Lists [](id=enabling-security-for-portal-access-con-liferay-portal-6-2-user-guide-15-en)

In the administrative console, go to *Security* $rarr; *Global Security*.  Check
the box to enable Java 2 security, and click *Apply*. Save to the master
configuration. 

   ![Figure 15.13: Enabling security can be done by checking one box, but it still needs to be configured. ](../../images/websphere-05-liferay-enable-security.png)

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

### Start Liferay [](id=start-liferay-liferay-portal-6-2-user-guide-15-en)

1. If you plan to use Liferay's setup wizard, skip to the next step. If you wish
   to use WebSphere's data source and mail session, create a file called
   `portal-ext.properties` in your Liferay Home folder. Place the following text
   in the file: 

        jdbc.default.jndi.name=jdbc/LiferayPool
        mail.session.jndi.name=mail/MailSession
        setup.wizard.enabled=false

2. Select the Liferay application and click *Start*. 

    ![Figure 15.14: While Liferay is starting, WebSphere displays this spinny little graphic. Don't watch it too closely, or you might get hypnotized.](../../images/websphere-04-liferay-starting.png)

3. In the setup wizard, select and configure your database type. Click *Finish*
   when you're done. 

    Liferay then creates the tables it needs in the database. 

Congratulations! You've installed Liferay on WebSphere! 

## Using Liferay's setup wizard [](id=using-liferays-setup-wizard-liferay-portal-6-2-user-guide-15-en)

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

![Figure 15.15: Supply the information for your site and your site's administrative account in the setup wizard.](../../images/setup-wizard-1.png)

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

![Figure 15.16: Fill out the information for your database. We've chosen MySQL in this example and have created a database called *lunar_resort* to hold our Liferay data.](../../images/setup-wizard-2.png)

Once you've filled out the form, click *Finish Configuration*. You'll see a
message stating that Liferay is being installed as it creates the tables and
data it needs in its database. When it's finished, it tells you the location of
the configuration file (`portal-setup-wizard.properties`) where it saved all
your settings. From here, you can go to your home page. 

Congratulations! You've just installed Liferay Portal! The next thing you need
to do is set up your mail configuration, so Liferay can send email notifications
to users. This is covered in the Manual Configuration section below. 

---

![Tip](../../images/01-tip.png) **Tip:** The wizard is an extremely helpful tool,
especially if you're setting up Liferay for the first time or creating a
completely fresh portal instance. If you're a Liferay veteran and you already
have your database information and various properties set up, you can skip the
wizard by adding this line to your *portal-ext.properties* file:

    setup.wizard.enabled=false

---

---

![Tip](../../images/01-tip.png) **Tip:** In Liferay 6.2, the admin user test@liferay.com
is created by the setup wizard even when a different user is specified. This
means that two admin users are created: test@liferay.com and the specified user.
Unless you're just installing Liferay for testing purposes, you should
deactivate the test@liferay.com user after your database has been created.

---

## Manual Configuration [](id=manual-configuration-liferay-portal-6-2-user-guide-15-en)

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

## Making Liferay Coexist with Other Java EE Applications [](id=making-liferay-coexist-with-other-java--liferay-portal-6-2-user-guide-15-en)

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

## Understanding Liferay's Releases [](id=understanding-liferays-releases-liferay-portal-6-2-user-guide-15-en)

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

### Release Process [](id=release-process-liferay-portal-6-2-user-guide-15-en)

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

## Summary [](id=summary-liferay-portal-6-2-user-guide-15-en)

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

