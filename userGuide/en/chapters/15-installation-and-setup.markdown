
#  Installation and Setup [](id=installation-and-setup-liferay-portal-6-2-user-guide-15-en)

---

![Note](../../images/01-tip.png) This chapter is still being updated 
reflect the new features in Liferay 6.2. 

---

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

## Installing Liferay on Mule Tcat [](id=installing-liferay-on-mule-tcat-liferay-portal-6-2-user-guide-15-en)

---

![tip](../../images/01-tip.png)*Note:* This section has not yet been upgraded to
6.2.

---

Liferay Portal Tcat Edition is a combination of Liferay Portal, the leading open
source portal, and Tcat Server, an enterprise grade administration console for
the Apache Tomcat application server.

Tcat Server provides several tools to make Tomcat more administrator friendly,
especially in organizations used to leveraging administration consoles like
those found in other more complex JEE application servers. You may use the
console to:

-   Monitor and control all Tomcat instances on multiple servers and across
    multiple environments 
-   Deploy and view applications across instances
-   Troubleshoot problems across all instances, including view JMX parameters,
    viewing remote logs and more
-   Provide granular entitlement controls for the above functions and more

This isn't a complete guide to Mule Tcat; it just covers Liferay installation.
For full documentation, you'll find more detailed information here:
[http://www.mulesoft.org/documentation/display/TCAT/Home](http://www.mulesoft.org/documentation/display/TCAT/Home)

To obtain Liferay Portal Tcat Edition, you need access to the Liferay Customer
Portal and the Mulesoft Tcat Server web site. If you are not a current Liferay
customer and would like to try the product, please contact Liferay via email at
sales@liferay.com.

For this section, we'll limit ourselves to the Windows and Linux 64-bit
installation wizards. For more detailed installation instructions, please
consult the Mulesoft Tcat Server installation documents located at:

[http://www.mulesoft.org/documentation/display/TCAT/Home](http://www.mulesoft.org/documentation/display/TCAT/Home)

-   [Installing Tcat Server on
    Linux](http://www.mulesoft.org/documentation/display/TCAT/Installing+Tcat+Server+on+Linux)

-   [Installing Tcat Server on
    Windows](http://www.mulesoft.org/documentation/display/TCAT/Installing+Tcat+Server+on+Microsoft+Windows)

-   [Installing Tcat Server on
    Solaris](http://www.mulesoft.org/documentation/display/TCAT/Installing+Tcat+Server+on+Solaris)

-   [Installing Tcat Server on Mac OS
    X](http://www.mulesoft.org/documentation/display/TCAT/Installing+Tcat+Server+on+Mac+OS+X)

-   [Adding Tcat Server Capabilities to an Existing Apache Tomcat
    Install](http://www.mulesoft.org/documentation/display/TCAT/Add+Tcat+Server+Capabilities+to+an+Existing+Tomcat+Install)

First, download the Liferay Tcat bundle from the Liferay customer portal. You'll
need two files:

-   **Liferay Portal 6.1 EE Tcat Admin:** contains the Tcat administration
    console and Liferay Portal EE and all appropriate plugins.

-   **Liferay Portal 6.1 EE Tcat Agent:** contains a vanilla Tomcat application
    server and the Tcat management agent.

Next, download the appropriate Tcat platform installation at:
[http://www.mulesoft.com/download-tcat](http://www.mulesoft.com/download-tcat).
You may select installation wizards for Windows (32 and 64-bit), Mac, Unix,
Solaris and Linux (32 and 64-bit) as well as a manual installation zip.

After obtaining the software bundles, you can proceed with installation and
configuration of the Administration Console.

### Installing the Administration Console on Windows [](id=installing-the-administration-console-o-liferay-portal-6-2-user-guide-15-en)

For Windows, Tcat comes with an installer to assist with installation and
configuration. After downloading, execute the installer, accepting the
appropriate license agreement.

Upon accepting the license agreement, the system presents you with two
installation choices, a "standard" or "custom" installation.

![Figure 15.2: Installation selection](../../images/tcat-html_2589582e.png)

You should select the "Custom" installation option to provide better control of
where Tcat Server is installed.

![Figure 15.3: Installation component selection](../../images/tcat-html_m230ba580.png)

After selecting the custom installation option, the Tcat installer prompts you
to select the desired components. 

-   **Tcat Server:** a version of Apache Tomcat that includes the appropriate
    management agents used by Tcat.

-   **Administration Console:** the administration console for monitoring and
    managing all available Tcat servers. This console contains tools used for
    application deployment, log access, server control and other administration
    tools.

-   **Tomcat Documentation:** the documentation that comes normally with an
    Apache Tomcat distribution. You do not need to select this option.

-   **Tomcat Examples:** the examples that comes normally with an Apache Tomcat
    distribution. You do not need to select this option.

-   **Tomcat Manager Application:** the manager application that comes normally
    with an Apache Tomcat distribution. You do not need to select this option.

For this step in the installation process, select the Administration Console in
addition to Tcat Server.

![Figure 15.4: Installation location](../../images/tcat-html_261b594a.png)

After selecting Tcat Server and the Administration Console for installation, the
installation wizard prompts you for an installation directory. Please select the
desired installation directory for the Tcat Server.

![Figure 15.5: Port configurations](../../images/tcat-html_66274d0c.png)

After selecting the appropriate installation location, the installation wizard
prompts you to specify the appropriate port numbers. If this is the first time
installing Tcat and no other Apache Tomcat installations are present, then you
may retain the above ports. However, if there are other installations, you will
need to select new ports. For the purpose of this installation, we will assume
the above ports are correct.

![Figure 15.6: Windows service installation](../../images/tcat-html_7936c58a.png)

To ensure the operating system starts the Tcat Server and Administrator Console,
you must configure the Tcat Server process as a Windows service. The next step
in the installation wizard helps with this process.

In the Windows service installation screen, please select "Install Tcat Server
as a Windows service." You may choose an appropriate service name other than
*TcatServer*.

![Figure 15.7: Start menu shortcuts](../../images/tcat-html_3f911eab.png)

The final step in the installation wizard is to configure Start Menu shortcuts.
You may choose to customize the shortcut location or accept the default.

After configuring the shortcuts, the Tcat Server Windows installer performs the
installation as previously configured.

### Installing the Administraton Console on Linux (Ubuntu) [](id=installing-the-administraton-console-on-liferay-portal-6-2-user-guide-15-en)

For Linux, Tcat comes with an installer to assist with installation and
configuration. After downloading, execute the installer, accepting the
appropriate license agreement. To execute the installer, make sure:

-   you have added execution permissions to the installer file (`chmod a+x [file
    name]`)

-   you execute it with root privileges (don't worry: the installed service is
    executed by a pre-defined system user with no root privileges)

Upon accepting the license agreement, the system presents you with two
installation choices: a "standard" or "custom" installation.

<!-- The following two figures are difficult to read and should be replaced with
ones having more readable text.  -->

![Figure 15.8: Installation type](../../images/tcat-html_766a1d6e.png)

You should select the "Custom" installation option to provide better control of
where Tcat Server is installed.

![Figure 15.9: Installation component selections](../../images/tcat-html_55494177.png)

After selecting the custom installation option, the Tcat installer prompts you
to select the desired components. 

-   **Tcat Server:** a version of Apache Tomcat that includes the appropriate
    management agents used by Tcat.

-   **Administration Console:** the administration console for monitoring and
    managing all available Tcat servers. This console contains tools used for
    application deployment, log access, server control and other administration
    tools.

-   **Tomcat Documentation:** the documentation that comes normally with an
    Apache Tomcat distribution. You do not need to select this option.

-   **Tomcat Examples:** the examples that comes normally with an Apache Tomcat
    distribution. You do not need to select this option.

-   **Tomcat Manager Application:** the manager application that comes normally
    with an Apache Tomcat distribution. You do not need to select this option.

For this step in the installation process, select the Administration Console in
addition to Tcat Server, which is the default setting.

After selecting Tcat Server and the Administration Console for installation, the
installation wizard prompts you for an installation directory.

![Figure 15.10: Installation location](../../images/tcat-html_7df6aabf.png)

Please select the desired installation directory for the Tcat Server. The
offered default directory is different if the installation process has been
started as root.

<!-- The following figure is difficult to read and should be replaced with a
better one.  -->

![Figure 15.11: Port configuration](../../images/tcat-html_5b760cd.png)

After selecting the appropriate installation location, the installation wizard
prompts you to specify the appropriate port numbers. If this is the first time
installing Tcat and no other Apache Tomcat installations are present, then you
may retain the above ports. However, if there are other installations, you will
need to select new ports. For the purpose of this installation, we will assume
the above ports are correct.

![Figure 15.12: Configure Tcat system user](../../images/tcat-html_749e9b40.png)

For security considerations, Tcat runs as a non-root system user. If the
username specified at this step does not exist, it's created as a system daemon
user.

<!-- The following three figures are difficult to read and should be replaced
with better ones.  -->

![Figure 15.13: Install service](../../images/tcat-html_mf7bb10c.png)

To ensure the operating system starts the Tcat Server and Administrator Console,
you must create a service startup script in `/etc/init.d`. The next step in the
installation wizard helps with this process.

In the service installation screen, you may enter an appropriate service name or
use the default. If you have multiple installations of Tcat, you should select a
more appropriate name than what is supplied by default. 

![Figure 15.14: Symbolic link creation](../../images/tcat-html_m55bbff3f.png)

The final configuration step before installation is the creation of a symbolic
link so the Tcat executable can be accessed more easily. This step is optional
and you may choose to not create the symbolic link.

![Figure 15.15: ](../../images/tcat-html_78e73740.png)

Now that Tcat is installed, you can add Liferay to it. 

### Adding Liferay Portal packages [](id=adding-liferay-portal-packages-liferay-portal-6-2-user-guide-15-en)

After completing the TcatServer Administration Console installation, you can
configure the Liferay Portal packages for Tcat.

First, extract the previously downloaded Liferay Portal 6.1 EE Tcat Admin into a
temporary directory. Once extracted, locate the file `tcat-init.groovy` and the
directory `tcat\_init`.

![Figure 15.16: Liferay Portal Tcat packages](../../images/tcat-html_12074416.png)

The `tcat-init.groovy` file contains instructions for the TcatServer
administration console to:

-   Pre-load the Liferay Portal and Plugins WAR files into the application
    repository

-   Load the appropriate Liferay application profile that should be applied to
    all other Tcat managed servers

-   Load the Liferay specific deployment manager

The `tcat\_init` folder contains the managed server profiles, Liferay WAR files
and administration scripts.

Copy the `tcat-init.groovy` file and `tcat\_init` folder to the previously
configured installation location.

![Figure 15.17: The installation directory after copying the `tcat\_init` folder and `tcat-init.groovy` into the TcatServer installation directory.](../../images/tcat-html_mf987314.png)

After successfully completing the installation on Windows, you should see a
TcatServer entry in the Services console, similar to the screen shot below. 

![Figure 15.18: Windows services console](../../images/tcat-html_3b2f5fb4.png)

By default, the TcatServer service is inactive but is set to start automatically
upon boot. Go ahead and choose to start the service.

If you're using Linux, you should see an entry for the Tcat service
initialization script in `/etc/init.d`. The script name is the name you choose
during the installation process.

<!--  We might want to replace this screen shot.  -->

![Figure 15.19: Tcat service startup on Linux](../../images/tcat-html_352642da.png)

To start the Tcat Server Administration Console, execute the service script in
`/etc/init.d`.

### Tcat Server Managed Server Installation [](id=tcat-server-managed-server-installation-liferay-portal-6-2-user-guide-15-en)

The steps to install the Tcat Server managed server are quite similar to those
for installing the Tcat Server Administration Console.

First, launch the wizard. During the installation component configuration step,
unselect "Administration Console" from the list of components.

![Figure 15.20: Installation location](../../images/tcat-html_261b594a.png)

The next step is to specify an installation location. If you have other
installations of Tcat on this machine, you should choose another destination.
For example, if you have already installed the Tcat Server Administration
Console to `C:\\TcatServer6`, you should perhaps install the managed server to
`C:\\TcatServer6Managed`.

![Figure 15.21: Managed server port configurations](../../images/tcat-html_66274d0c.png)

During the managed server installation, as with the administration console
installation, you will be prompted to configure the appropriate port numbers. If
you do not have another Tcat Server instance (e.g. administration console or
another managed server), you may choose the default ports. Otherwise, you should
select non-conflicting ports. For instance:

-   HTTP port: 8180

-   HTTPS redirect port: 81443

-   Tcat Server shutdown port: 8105

-   AJP connector port: 8109

-   Secure Agent Port: 52443

To ensure the operating system starts the Tcat Server and Administrator Console,
you must configure the Tcat process as a Windows service or Linux daemon. The
next step in the installation wizard helps with this process.

The final step in the installation wizard is to configure Start Menu shortcuts.
You may choose to customize the shortcut location or simply accept the default.
Since this is for a managed server, you may wish to name it similar to the
service name.

After configuring the shortcuts, the Tcat Server installer performs the
installation as previously configured.

Once you have installed the managed server, there is one more step to perform on
the managed server. You must modify the `catalina.properties` file located in
the `conf` directory of your Tcat Server installation.

You will need to change the text:

	common.loader=${catalina.base}/lib,${catalina.base}/lib/\*.jar,${catalina.home}/lib,${catalina.home}/lib/\*.jar

To:

	common.loader=${catalina.base}/lib,${catalina.base}/lib/\*.jar,${catalina.home}/lib,${catalina.home}/lib/\*.jar,${catalina.home}/lib/ext,${catalina.home}/lib/ext/\*.jar

By modifying the `common.loader` property, you instruct Tcat Server to load
everything in the `lib/ext` folder as part of the classpath.

### Registering the managed server [](id=registering-the-managed-server-liferay-portal-6-2-user-guide-15-en)

After completing the installation process and starting the appropriate
TcatServer processes, open a browser to `[SERVER NAME]:[PORT]/console`. Using
the previous installation example, you should point your browser to
`http://localhost:8080/console`. The browser should render the TcatServer
Administration Console shown below. 

![Figure 15.22: Tcat Admin Console log in](../../images/tcat-html_m4bda7997.png)

The default login is: admin/admin.

Once you have logged into the administration console, you will be presented with
a global dashboard that you may customize once you have dismissed the "Tcat
Server - Quick Start" panel.

![Figure 15.23: Customizable Tcat Admin Console](../../images/tcat-html_5d43770f.png)

First make sure that you have started the previously installed managed server.
Clicking on the *Servers* tab in the console, you will see a server listed as
*Unregistered*.

Unregistered servers are servers that have the Tcat management agent installed
but are not added to the administration console. You may add the unregistered
server by selecting either *Register* or *Register & Add To Group*.

If you choose to use *Register & Add To Group*, the server is added to the
desired server group (e.g. Development). For the purpose of this guide, we'll
register the server to the Development group.

![Figure 15.24: Monitoring a registered server](../../images/tcat-html_34603e60.png)

After registering the server, you will have access to view its health status,
log files and more. Feel free to walk through the console to examine its
capabilities, like reviewing log files, current thread status and deployed web
applications.

More information is available at
*[http://www.mulesoft.org/documentation/display/TCAT/Home](http://www.mulesoft.org/documentation/display/TCAT/Home).*

**Managing and Monitoring Servers**

-   [Using the Server
    Dashboard](http://www.mulesoft.org/documentation/display/TCAT/Server+Dashboard)

-   [Working with Server
    Groups](http://www.mulesoft.org/documentation/display/TCAT/Working+with+Servers)

-   [Monitoring Servers (View Apps, Threads,
    etc)](http://www.mulesoft.org/documentation/display/TCAT/Monitoring+a+Server)

-   [Monitoring a JMX
    Agent](http://www.mulesoft.org/documentation/display/TCAT/JMX+Agent+Monitoring)

-   [Scripts to Save JMX Metrics to CSV
    Files](http://www.mulesoft.org/documentation/display/TCAT/Saving+JMX+Metric+Data+to+CSV+Files)

**Managing Tcat Web Applications**

-   [Monitoring
    Applications](http://www.mulesoft.org/documentation/display/TCAT/Monitoring+Applications)

-   [Deploying
    Applications](http://www.mulesoft.org/documentation/display/TCAT/Deploying+Applications)

-   [Using the
    Repository](http://www.mulesoft.org/documentation/display/TCAT/Managing+the+Repository)

-   [Setting Security on Repository
    Artifacts](http://www.mulesoft.org/documentation/display/TCAT/Setting+Security)

### Deploying Liferay Portal [](id=deploying-liferay-portal-liferay-portal-6-2-user-guide-15-en)

As part of the installation process, the current version of Liferay Portal and a
number of Liferay Plugins have been provisioned into the Tcat repository. You
can view them by clicking on the "Repository" tab.

![Figure 15.25: Tcat Repository profiles](../../images/tcat-html_7e61df5a.png)

There are two components in the Tcat Repository:

-   **Profiles:** application profiles to be applied to the Tcat managed
    servers. Contains configuration, shared libraries and more. 

-   **Applications:** deployable WAR files for web applications and Liferay
    Plugins. 

![Figure 15.26: Tcat Repository: Applications](../../images/tcat-html_m4af27eb5.png)

To begin Liferay deployment, we must first create a Liferay license profile to
be deployed to the managed server:

![Figure 15.27: Creating a new workspace for Liferay license profile](../../images/tcat-html_m2d96341c.png)


1. First create a new workspace under *Profiles*. Select *Profiles* in the left
   navigation and then click the *New Workspace* button. 

2. Within the new workspace *Liferay Portal Trial License*, create another
   folder called `deploy`. 

3. In the `deploy` folder, select *New Artifact* and upload a new license file.

After completing the above, you have successfully uploaded your Liferay Portal
license file into the Tcat repository. The final step is to create a server
profile from the *Administration* tab.

*Server Profiles* shows the list of available Server Profiles and the option to
create a new profile.

![Figure 15.28: Viewing server profiles](../../images/tcat-html_m5c7a2b8c.png)

When creating a server profile for the Liferay Portal Trial License, you will
need to select the workspace folder created in the previous step. This ensures
that when you apply the profile, Tcat uses the latest version of the license
file.

Once you've created the server profile for your Liferay Portal EE license, you
may begin deploying Liferay to the managed server.

![Figure 15.29: Apply Liferay Portal trial license](../../images/tcat-html_53af6680.png)

The first step is to apply two server profiles, one for the license and the
other for Liferay Portal. Applying the license profile does not require a
restart of the server.

![Figure 15.30: Apply Liferay profile](../../images/tcat-html_m1f86eaab.png)

The second profile to be applied is the Liferay server profile. In the example
shown we applied the profile for Liferay Portal EE 6.1.

The Liferay Portal server profile requires a server restart. If you have the
managed server configured as a Windows or Linux service, you may restart the
server directly from the console by selecting the server and clicking "Restart".

With both profiles applied, we can now deploy the Liferay Portal WAR to the
appropriate server.

The *Deployments* tab contains tools that assist in creating a deployment and
targeting it to specific servers. Once you create a deployment, you may target
it to any number of servers or deployment groups.

![Figure 15.31: Choosing a web application for deployment](../../images/tcat-html_m54d58d30.png)

When creating a deployment, you may choose a WAR file already uploaded into the
repository or you may choose to upload a new WAR file. We recommend using a WAR
file from the repository for non-development deployments. This ensures you
consistently deploy the correct version to your environments.

Since we are deploying Liferay Portal for the first time, we choose the ROOT.war
file.

After selecting the appropriate web application, you may choose which Servers to
deploy to. In this example, we deploy to the Tomcat instance labeled *Liferay
Portal Instance 1*. 

![Figure 15.32: Completing Liferay Portal deployment](../../images/tcat-html_68a00002.png)

After choosing to deploy the application, the Tcat console informs you of the
current deployment status (e.g. "Successful"). Assuming you followed the
previous steps and the deployment successfully completes, you will be able to
access Liferay Portal on the target Tomcat instance.

![Figure 15.33: Accessing Liferay Portal on Liferay Portal Instance 1](../../images/tcat-html_2b08ac2c.png)

### Deploying Liferay plugins on Mule Tcat [](id=deploying-liferay-plugins-on-mule-tcat-liferay-portal-6-2-user-guide-15-en)


Liferay Portal Tcat Edition works with all the appropriate Liferay EE Plugins,
including:

-   Kaleo workflow engine

-   Knowledge base

-   Chat

-   Private messaging

You may choose to deploy these plugins to the appropriate servers by following
the same steps as for deploying Liferay Portal, specifying the appropriate
plugin WAR file instead of the ROOT.war.

Other plugins that may be downloaded and added to this repository include:

-   Audit

-   Report engine and console

-   Drools integration.

You may download these plugins from the Liferay Customer Portal and manually add
them to the Tcat repository.

![Figure 15.34: Uploading new plugins into Tcat](../../images/tcat-html_19e9e6d6.png)

Mule Tcat provides an enterprise grade solution to managing Tomcat servers. If
you need a complete stack, combining Liferay Portal with Mule Tcat gives you
everything you need to run a fully supported and robust portal environment for
your enterprise. 

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

### Dependency Jars [](id=dependency-jars-liferay-portal-6-2-user-guide-15-en)

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

##### Domain Configuration [](id=domain-configuration-liferay-portal-6-2-user-guide-15-en)

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
        permission java.lang.reflect.ReflectPermission  "suppressAccessChecks";
        permission javax.security.auth.AuthPermission "doAsPrivileged";
    };

Delete, rename, or move the `domain1/docroot/index.html` file to another
location to allow your Liferay Portal default page to be displayed.

Next, let's get your database configured.

### Database Configuration [](id=database-configuration-liferay-portal-6-2-user-guide-15-en)

If you want to use GlassFish to manage your domain's data source, follow the
instructions found in this section. If you want to use Liferay Portal to manage
your data source, you can skip this section.

1. Start your domain's application server if it is not already running.

2. Go to the GlassFish console URL:
   [http://localhost:4848](http://localhost:4848/). 

3. Under *Common Tasks*, navigate to *Resources* &rarr; *JDBC* &rarr; *JDBC
   Connection Pools*

    ![Figure 15.35: In the GlassFish administration console, navigate to JDBC Connection Pools.](../../images/11-glassfish31-connection-pools.png)

4. Click *New...*.

5. On the first screen (Step 1 of 2), enter the name `LiferayPool` for your
   connection pool, select the `javax.sql.ConnectionPoolDataSource` resource
   type, and select your database driver vendor (e.g. `MySQL`). See the
   following figure:

    ![Figure 15.36: It's easy to configure a new Glassfish JDBC Connection Pool. Just enter a pool name, select a resource type, and specify a database driver vendor.](../../images/11-glassfish-31-jdbc-connection-pool.png)

6. Click *Next* to advance to the next step in creating your JDBC connection
   pool.

7. From the top of this screen (Step 2 of 2), scroll down to the *Additional
   Properties* section.

    ![Figure 15.37: GlassFish JDBC Connection Pool Properties](../../images/11-glassfish-31-jdbc-connection-pool-props.png)  

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

### Mail Configuration [](id=mail-configuration-liferay-portal-6-2-user-guide-15-en)

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

    // Tune for specific apps (these are generally required by Liferay plugins not using PACL)
    grant codeBase "file:${com.sun.aas.instanceRoot}${/}applications${/}-" {
            permission java.util.PropertyPermission "base.path", "write";
            permission java.util.PropertyPermission "*", "read";
            permission java.lang.reflect.ReflectPermission "suppressAccessChecks";
    };

    // Represents each webapp's ${javax.servlet.context.tempdir} directory
    grant codeBase "file:${com.sun.aas.instanceRoot}${/}generated${/}jsp${/}-" {
            permission java.util.PropertyPermission "base.path", "write";
            permission java.util.PropertyPermission "*", "read";
            permission java.lang.reflect.ReflectPermission "suppressAccessChecks";
    };

    // Since Liferay portal is a security provider it needs AllPermissions
    grant codeBase "file:${com.sun.aas.instanceRoot}${/}applications${/}liferay-portal${/}-" {
            permission java.security.AllPermission;
    };
    grant codeBase "file:${com.sun.aas.instanceRoot}${/}generated${/}jsp${/}liferay-portal${/}-" {
            permission java.security.AllPermission;
    };
    grant codeBase "file:${com.sun.aas.instanceRoot}${/}lib${/}portal-service.jar" {
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

![Figure 15.38: GlassFish provides an administrative console which you can use to deploy Liferay.](../../images/11-deploying-liferay-in-glassfish-4.0.png)

Once you have deployed Liferay via GlassFish's administrative console, restart
GlassFish.

Your installation of Liferay Portal on GlassFish is complete!

## Installing Liferay on JBoss 7.1 [](id=installing-liferay-on-jboss-7-liferay-portal-6-2-user-guide-15-en)

**Liferay Home** is one folder above JBoss's install location.

1. Download and install JBoss AS 7.1.x into your preferred directory. This
   directory is referred to as `$JBOSS_HOME` throughout this section.

2. Download the latest version of the Liferay Portal `.war` file.

3. Download Liferay's Portal Dependencies.

Now that you have all of your installation files, you are ready to start
installing and configuring Liferay on JBoss.

### Dependency Jars [](id=dependency-jars-liferay-portal-6-2-user-guide-15-en-1)

Let's work with the dependency jar files first.

1. Create folder `$JBOSS_HOME/modules/com/liferay/portal/main` and unzip the jar
   files found in the Liferay Portal Dependencies zip file to this folder. Make
   sure the zip file's jar files are extracted into the
   `$JBOSS_HOME/modules/com/liferay/portal/main` folder.

2. Download your database driver `.jar` file and put it into the
   `$JBOSS_HOME/modules/com/liferay/portal/main` folder as well. For
   demonstration purposes, we'll download the MySQL Connector/J driver from
   [http://dev.mysql.com/downloads/connector/j/](http://dev.mysql.com/downloads/connector/j/)
   and put its `.jar` file into the
   `$JBOSS_HOME/modules/com/liferay/portal/main` folder.

3. Download the `jtds-<JTDS_VERSION>.jar.` file and insert it into the
`$JBOSS_HOME/modules/com/liferay/portal/main` folder. You can download and learn
more about this JDBC driver at the jTDS home page:
[http://jtds.sourceforge.net/](http://jtds.sourceforge.net/).

4. Create the file `module.xml` in the
   `$JBOSS_HOME/modules/com/liferay/portal/main` folder and insert the following
   contents.

		<?xml version="1.0"?>

		<module xmlns="urn:jboss:module:1.0" name="com.liferay.portal">
			<resources>
				<resource-root path="hsql.jar" />
				<resource-root path="jtds-1.3.1.jar" />
				<resource-root path="mysql-connector-java-5.1.26-bin.jar" />
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

	If you're using a different database or JDBC driver, replace the paths of
	the MySQL and jTDS resource root entries with the correct paths.

5. Next, you'll need to include a patch from Liferay's source code for one of
JBoss' default `.jar` files. Once you've downloaded the Liferay source, unzip
the source into a temporary folder. We'll refer to the location of the Liferay
source as `$LIFERAY_SOURCE`.

6. Currently, there are bugs in the
`$JBOSS_HOME/modules/org/jboss/as/server/main/jboss-as-<$JBOSS_VERSION>.Final.jar`
file regarding the IBM JVM
([LPS-39705](http://issues.liferay.com/browse/LPS-39705) and
[JBPAPP-9353](http://issues.jboss.org/browse/JBPAPP-9353)) which requires
additional steps to ensure a successful deployment with Liferay. In summary,
you'll need to update the `ServerDependenciesProcessor.class` file in the
`jboss-as-<$JBOSS_VERSION>.Final.jar` file to specify the IBM JDK. The steps to
insert the patch can be referenced below.

    1. Cut and paste the `jboss-as-<$JBOSS_VERSION>.Final.jar` file from
    `$JBOSS_HOME/modules/org/jboss/as/server/main` to the
    `$LIFERAY_SOURCE/tools/servers/jboss/patches/JBPAPP-9353/classes` folder.
    
    2. Navigate to the
    `$LIFERAY_SOURCE/tools/servers/jboss/patches/JBPAPP-9353/classes` directory
    in a command prompt and enter the following statement:
    
    		jar uf jboss-as-server-<$JBOSS_VERSION>.Final.jar org/jboss/as/server/deployment/module/ServerDependenciesProcessor.class

    	This command inserts the `ServerDependenciesProcessor.class` file into
    	the `jboss-as-<$JBOSS_VERSION>.Final.jar` file's
    	`org/jboss/as/server/deployment/module` folder. You can reference the
    	official documentation for updating a JAR file at
    	[http://docs.oracle.com/javase/tutorial/deployment/jar/update.html](http://docs.oracle.com/javase/tutorial/deployment/jar/update.html).

    3. Cut and paste the `jboss-as-<$JBOSS_VERSION>.Final.jar` file back to its
    original `$JBOSS_HOME/modules/org/jboss/as/server/main` folder.

Great! You have your `.jar` files ready for your domain.

### Running Liferay on JBoss 7.1 in Standalone Mode vs. Domain Mode [](id=running-liferay-on-jboss-7-in-standalon-liferay-portal-6-2-user-guide-15-en)

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

---

![tip](../../images/01-tip.png) **Note:** This does not prevent Liferay from
running in a clustered environment on multiple JBoss servers. You can set up a
cluster of Liferay instances running on JBoss 7.1 servers running in standalone
mode. Please refer to the chapter of this guide on [Configuring Liferay for High
Availability](https://www.liferay.com/documentation/liferay-portal/6.2/user-guide/-/ai/configuring-liferay-for-high-availabili-liferay-portal-6-2-user-guide-20-en)
for information on setting up a Liferay cluster.

---

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

### Configuring JBoss [](id=configuring-jboss-liferay-portal-6-2-user-guide-15-en)

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

        set "JAVA_OPTS=%JAVA_OPTS% -Dfile.encoding=UTF-8 -Djava.net.preferIPv4Stack=true -Djava.security.manager -Djava.security.policy==$JBOSS_HOME/bin/server.policy -Djboss.home.dir=$JBOSS_HOME -Duser.timezone=GMT -Xmx1024m -XX:MaxPermSize=256m"

- On Unix, merge the following values into your settings for `JAVA_OPTS`
  replacing any matching attributes with the ones found in the assignment
  below:

	    JAVA_OPTS="$JAVA_OPTS -Dfile.encoding=UTF-8 -Djava.net.preferIPv4Stack=true -Djava.security.manager -Djava.security.policy==$JBOSS_HOME/bin/server.policy -Djboss.home.dir=$JBOSS_HOME -Duser.timezone=GMT -Xmx1024m -XX:MaxPermSize=256m

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
		</module>]]>

3. Navigate to the `$JBOSS_HOME/modules/sun/jdk/main/module.xml` file and insert
the following path names inside the &lt;paths&gt;...<\/paths> element:

		<path name="com/sun/crypto" />
		<path name="com/sun/crypto/provider" />
		<path name="com/sun/image/codec/jpeg" />
		<path name="com/sun/org/apache/xml/internal/resolver" />
		<path name="com/sun/org/apache/xml/internal/resolver/tools" />

Next we'll consider the database and mail configuration. 

### Database Configuration [](id=database-configuration-liferay-portal-6-2-user-guide-15-en-1)

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
			<driver name="mysql" module="com.liferay.portal.main"/>
		</drivers>

Your final data sources subsystem should look something like this:

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
					<driver name="mysql" module="com.liferay.portal.main"/>
				</drivers>
			</datasources>
		</subsystem>

Now that you've configured your data source, let's go over how to configure your
mail session within JBoss.

### Mail Configuration [](id=mail-configuration-liferay-portal-6-2-user-guide-15-en-1)

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

### Configuring data sources and mail sessions [](id=configuring-data-sources-and-mail-ses-liferay-portal-6-2-user-guide-15-en-0)

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

### Security Configuration [](id=security-configuration-liferay-portal-6-2-user-guide-15-en)

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

### Deploy Liferay [](id=deploy-liferay-liferay-portal-6-2-user-guide-15-en-1)

1. If the folder `$JBOSS_HOME/standalone/deployments/ROOT.war` already exists in
   your JBoss installation, delete all of its subfolders and files. Otherwise,
   create a new folder `$JBOSS_HOME/standalone/deployments/ROOT.war`.

2. Unzip the Liferay `.war` file into the `ROOT.war` folder.

3. To trigger deployment of `ROOT.war`, create an empty file named
   `ROOT.war.dodeploy` in  your `$JBOSS_HOME/standalone/deployments/` folder.
   On startup, JBoss detects the presence of this file and deploys it as a web
   application.

4. Remove `eclipselink.jar` from
   `$JBOSS_HOME/standalone/deployments/ROOT.war/WEB-INF/lib` to assure the
   Hibernate persistence provider is used instead of the one provided in the
   `eclipselink.jar`.

    Now it's time to start Liferay Portal on JBoss!

5.  Start the JBoss application server.
 
Now you are truly *the boss* when it comes to deploying Liferay Portal on JBoss!

## Installing Liferay on Tomcat 7 [](id=installing-liferay-on-tomcat-7-liferay-portal-6-2-user-guide-15-en)

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
The Liferay `.war` file should be called `liferay-portal-6.2.x-<date>.war` and
the dependencies file should be called
`liferay-portal-dependencies-6.2.x-<date>.zip`.

Next, let's get started by addressing Liferay's library dependencies.

### Dependency Jars [](id=dependency-jars-liferay-portal-6-2-user-guide-15-en-3)

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

### Tomcat Configuration [](id=tomcat-configuration-liferay-portal-6-2-user-guide-15-en)

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
				className="org.apache.catalina.realm.JAASRealm"
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

			<!--<Manager className="com.liferay.support.tomcat.session.SessionLessManagerBase" />
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

### Database Configuration [](id=database-configuration-liferay-portal-6-2-user-guide-15-en-3)

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
	
    ---

    ![note](../../images/01-tip.png) **Note:** The above resource definition
    assumes your database name is *lportal* and your MySQL username and password
    are both *root*. You'll have to update these values with your own database name
    and credentials.

    ---

Your Tomcat managed data source is now configured. Let's move on to your mail
session.

### Mail Configuration [](id=mail-configuration-liferay-portal-6-2-user-guide-15-en-3)

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

### Configuring your database and mail session [](id=configuring-your-database-and-mail-sess-liferay-portal-6-2-user-guide-15-en)

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

### Enabling PACL [](id=enabling-pacl-liferay-portal-6-2-user-guide-15-en)

To enable PACL for use with your Liferay Portal when running on Tomcat, you
need to enable the security manager and add some required permissions to the
server policy configuration file. This simply entails editing two files in
`$TOMCAT_HOME` you're already familiar with if you've been following along in
setting up Liferay with a Tomcat Application Server: 

- In `$TOMCAT_HOME/bin/setenv.sh` (if on Linux, Unix, or Mac OS) or
  `setenv.bat` (if on Windows) enable the security manager by inserting the
following code into the `CATALINA_OPTS` variable (inside the quotation marks):

    `-Djava.security.manager -Djava.security.policy=$CATALINA_BASE/conf/catalina.policy`

- In `$TOMCAT_HOME/conf/Catalina.policy`, add the required permissions:

        // Tune for specific apps (these are generally required by Liferay plugins not using PACL)
        grant codeBase "file:${catalina.home}${/}webapps${/}-" {
            permission java.util.PropertyPermission "base.path", "write";
            permission java.util.PropertyPermission "*", "read";
            permission java.lang.reflect.ReflectPermission "suppressAccessChecks";
        };
        // represents each webapp's ${javax.servlet.context.tempdir} directory
        grant codeBase "file:${catalina.home}${/}work${/}Catalina${/}localhost${/}-" {
            permission java.util.PropertyPermission "base.path", "write";
            permission java.util.PropertyPermission "*", "read";
            permission java.lang.reflect.ReflectPermission "suppressAccessChecks";
        };

        // Since Liferay portal is a security provider it needs AllPermissions
        grant codeBase "file:${catalina.home}${/}webapps${/}ROOT${/}-" {
            permission java.security.AllPermission;
        };
        grant codeBase "file:${catalina.home}${/}work${/}Catalina${/}localhost${/}_${/}-" {
            permission java.security.AllPermission;
        };
        grant codeBase "file:${catalina.home}${/}work${/}Catalina${/}localhost${/}ROOT${/}-" {
            permission java.security.AllPermission;
        };

Now you have PACL enabled and configured for your portal. Let's deploy Liferay!

### Deploy Liferay [](id=deploy-liferay-liferay-portal-6-2-user-guide-15-en-3)

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

## Installing Liferay on Oracle WebLogic 12c (12.1.x) [](id=install-liferay-on-weblogic-12c-liferay-portal-6-2-user-guide-15-en)

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

Next, let's configure a mail session in WebLogic.

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

### Security Configuration [](id=security-configuration-liferay-portal-6-2-user-guide-15-en-0)

When you are ready to start using other people's apps from Marketplace, you'll
want to protect your portal and your WebLogic server from security threats. To
do so, you can enable Java Security on your WebLogic server and specify a
security policy to grant your Liferay Portal access to your server. 

To enable security on your WebLogic server, add the `-Djava.security.manager`
Java option in your `startWebLogic.[cmd|sh]` file domain's folder. 

For now, in order to grant Liferay access to your server let's open up all
permissions--you can fine-tune your policy's permissions later. Create a
policy file named `weblogic.policy` in your `[wlserver]/server/lib` folder and
add the following contents:

    grant {
        permission java.security.AllPermission;
    };

For extensive information on Java SE Security Architecture see its specification
documents at
[http://docs.oracle.com/javase/7/docs/technotes/guides/security/spec/security-spec.doc.html](http://docs.oracle.com/javase/7/docs/technotes/guides/security/spec/security-spec.doc.html).
Also, see section [*Understanding Plugin Security
Management*](https://www.liferay.com/documentation/liferay-portal/6.2/development/-/ai/understanding-plugin-security-management-liferay-portal-6-2-dev-guide-11-en)
in Chapter 12 of the Developer's Guide to learn how to configure Liferay plugin
access to resources. 

Now its the moment you've been waiting for: Liferay deployment!

### Deploy Liferay [](id=deploy-liferay-liferay-portal-6-2-user-guide-15-en-4)

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

## Installing Liferay on WebSphere 8.5 [](id=installing-liferay-on-websphere-8-5-liferay-portal-6-2-user-guide-15-en)

![Tip](../../images/01-tip.png) **Tip:** Throughout this installation and
configuration process, WebSphere prompts you to Click Save to apply changes to
Master Configuration. Do so intermittently to save your changes.

**Liferay Home** is in a folder called `liferay` in the home folder of the user
ID that is running WebSphere.

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

    ![Figure 15.39: Choose the Advanced profile option to specify your own settings.](../../images/websphere-01-profile.png)

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

![Figure 15.40: The Summary page shows you what you selected, giving you the chance to go back and change something if it's not exactly what you want.](../../images/websphere-03-summary.png)

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

![Figure 15.41: WebSphere JDBC providers](../../images/02-websphere-jdbc-providers.png) 

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

    ![Figure 15.42: Modifying data source properties in WebSphere](../../images/02-modifying-data-source-properties-in-websphere.png) 

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

    ![Figure 15.43: Enabling security can be done by checking one box, but it still needs to be configured. ](../../images/websphere-05-liferay-enable-security.png)

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

    ![Figure 15.44: While Liferay is starting, WebSphere displays this spinny little graphic. Don't watch it too closely, or you might get hypnotized.](../../images/websphere-04-liferay-starting.png)

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
book, we build a social network for your nose. This site is called Nosester
so we've supplied `Nosester` in the screenshot below. 

**Default Language:** choose the default locale where your site resides. 

For the administrator, you need to supply the following information: 

**First Name:** the first name of the user that has the administrator account. 

**Last Name:** the last name of the user that has the administrator account. 

**Email:** the email address of the user that has the administrator account. 

![Figure 15.45: Supply the information for your site and your site's administrative account in the setup wizard.](../../images/setup-wizard-1.png)

Liferay supports just about all the leading databases today: 

* DB2

* Informix

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

![Figure 15.46: Fill out the information for your database. We've chosen MySQL in this example and have created a database called *nosester* to hold our Liferay data.](../../images/setup-wizard-2.png)

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
default application in the root context of the server.

There are two steps to modifying this behavior:

1. Deploy Liferay in a context other than root (for example `/portal`).

2. Modify the `portal-ext.properties` file to tell Liferay the context to which
   it has been deployed.

    To change the file, open it in a text editor. Place the `portal.ctx`
    property at the top of the file:

        portal.ctx=/

This default setting defines Liferay Portal as the application that sits at the
root context. If you change it to something else, say `/portal`, for example,
you can then deploy Liferay in that context and it will live there instead of at
the root context.

A full discussion of the `portal-ext.properties` file appears in Chapter 20.

**Note for WebLogic Users:** WebLogic also requires that you modify the
`weblogic.xml` file which is included with Liferay. In this file are tags for
the context root:

    <context-root>/</context-root>

Change this so it matches the path you set in your `portal-ext.properties` file.
You will have to modify the `weblogic.xml` file inside the Liferay `.war` before
you deploy it. Extract the file from the `.war` file, modify it and then put it
back in the `.war` file. Then deploy the modified Liferay `.war` file to the
server in the proper context.

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

