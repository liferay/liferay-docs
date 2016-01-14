# Liferay Installation Overview

Installing Liferay is easy. But before you begin, you should answer a few
questions.

- Which edition and version of Liferay will you install?
- On which application server will you install Liferay?
- Can you install a Liferay bundle or do you need to install Liferay on an
  existing application server?
- Which database will you use with Liferay?
- Will you enable Liferay's PACL security feature?

This article helps you answer these questions and describes the basic steps for
installing Liferay.

<!-- Liferay CE vs. EE terminology is changing for Liferay 7. Must update this
section once terminology has been finalized. -->
## Editions of Liferay

Liferay ships in two editions:
one for the community and one for enterprise subscribers. The community edition
is the same Liferay Portal that has been available for years: frequently
updated and bursting with the latest features, the Community Edition of Liferay
Portal is offered for free under the Lesser GNU public license, a free software
license with one important exception. This license gives you the flexibility to
link Liferay with your own code in your Liferay plugins, no matter what license
you use for your code. If, however, you modify Liferay directly, those
modifications must be released as free software under the terms of the license.
Of course, the best way to do this is to contribute your code back to the
Liferay community. This is really the best of both worlds: you have the freedom
use any license (or no license) if you write plugins, but if you modify Liferay
directly, the community receives the benefits of any enhancements that you've
made.

Liferay for enterprise subscribers is a supported version of Liferay Portal for
the enterprise. The subscription and support package allows organizations to
build their portals on a stable version of the product that is offered over an
extended period of time. It's the best way for you to develop, deploy, and
maintain your Liferay solution. It includes

- Liferay support
- Updates
- Fix Packs
- Cloud Services
- Liferay Portal Enterprise Edition

Next, let's learn how to get a copy of Liferay Portal.

## Obtaining Liferay Portal

Anyone can download Liferay Portal from
[https://www.liferay.com](https://www.liferay.com). Click the *Developers*
&rarr; *Downloads* link at the top of the page, and you'll see multiple options
for downloading a copy of Liferay. These include our convenient bundles as well
as `.war` files for installing Liferay on your application server of choice.

Liferay enterprise subscribers can download Liferay from the Customer Portal at
[https://www.liferay.com/group/customer](https://www.liferay.com/group/customer).
You have a choice of the same open source app server bundles as community
members, plus a few commercial alternatives, in addition to the `.war` files
for manual installation.

So what is a Liferay bundle anyway? A Liferay bundle is an application server
with Liferay preinstalled. Using a bundle is the easiest way to install
Liferay. Liferay is bundled with a number of application servers; all you need
to do is choose the one that best fits your needs. If you don't currently have
an application server preference, consider starting with the Tomcat bundle.
Tomcat is one of the most lightweight and straightforward bundles to configure.
If you have an open source application server preference, choose the server you
prefer from the available Liferay Portal bundles. All the bundles ship with a
Java Runtime Environment for Windows; if you are using a different operating
system, you must have a JDK (Java Development Kit) installed prior to launching
Liferay Portal.

Please note that Liferay is not able to provide application server bundles for
proprietary application servers such as WebLogic or WebSphere, because the
licenses for these servers don't allow for redistribution. Liferay Portal,
however, runs just as well on these application servers as it does on the
others. You'll need to follow our manual installation procedure using a `.war`
file to install Liferay on proprietary application servers.

Once you have Liferay, you can plan out your installation. First, determine if
you need Liferay Portal Security turned on. Second, install Liferay Portal. You
can install Liferay either by using a bundle or by installing it manually on
your existing application server. Next, we'll go over the steps it takes to
install Liferay Portal.

## Liferay Installation Steps

Before you begin installing Liferay, You should review these basic installation
steps:

1. Choose a database to use with Liferay and create a new database in your
   database server.

2. Determine whether you want Liferay to manage your database connection or
   your application server to manage your database connection. We recommend
   that you let Liferay manage your database connection.

3. Gather mail credentials for sending email notifications to users. Determine
   whether you want Liferay to manage your mail session or your application
   server to manage your mail session. Liferay provides a built-in mail session
   but also supports a JNDI mail session. We recommend that you let Liferay
   manage your mail session.

4. Install either a Liferay bundle or install Liferay on an existing
   application server (further instructions below).

5. Determine whether you'll use Liferay Marketplace or other third party
   applications. If you will, you should enable Liferay's Plugin Access Control
   List (PACL) security feature.

We'll go through the steps in order, so first we'll look at the Liferay
database.

## Setting Up Liferay's Database with the Recommended Permissions

The recommended way of setting up your Liferay database is also the simplest.
Liferay Portal takes care of just about everything. You only need to take two simple steps:

1. Create a blank database encoded with the character set UTF-8. Liferay is a
   multilingual application and needs UTF-8 encoding to display all of its
   supported character sets.

2. Create a database user for accessing this database. Grant this database user
   all rights, including the rights to create and drop tables, to the blank
   Liferay database.

Liferay will use this database user's credentials to connect to the Liferay
database either directly or through its application server. During its initial
startup, Liferay creates the tables it needs in the database you just created.
It does this automatically, complete with indexes.

This is the recommended way to set up Liferay. This method allows you to take
advantage of Liferay's ability to automatically maintain its database during
upgrades or when various Liferay plugins are installed that create database
tables of their own. This method is by far the best way to set up your Liferay
database.

If you will set up Liferay's database with the recommended permissions
described in this section, you can skip the next section.

## Setting Up Liferay's Database with Restrictive Permissions

**Warning:** The instructions in this section are not ideal for Liferay
installations. This procedure is documented here so that enterprises with more
restrictive standards can install Liferay with stricter (but sub-optimal)
database settings. If it's at all possible, we recommend that you use the
method described in the previous section instead of the procedure outlined
here.

Even though Liferay can create its database automatically, some enterprises
prefer *not* to allow the database user configured in an application server to
have the database permissions necessary for Liferay and its plugins to maintain
their tables. For these organizations, Select, Insert, Update and Delete are
the only allowed permissions. Thus, in this section we explain how to set up
the database manually. If your organization *is* willing to grant the Liferay
database user the permissions to create and drop tables in the database--and
this is the recommended configuration--by all means, use the recommended
configuration described in the previous section.

1. Create a new, blank, database for Liferay.

2. Grant full rights to do anything to the Liferay database to the Liferay
   database user.

3. Install Liferay and start it so that it automatically populates its
   database.

4. Once the database has been populated with the Liferay tables, remove the
   permissions for creating and dropping tables from the Liferay database user.

There are some caveats to running Liferay like this. Many Liferay plugins
create new tables when they're deployed. Additonally, Liferay has an automatic
database upgrade function that runs when Liferay is upgraded. If the Liferay
database user doesn't have enough rights to create/modify/drop tables in the
database, you must grant those rights to the ID before you deploy one of these
plugins or start upgrading Liferay. Once the tables are created or the upgrade
is complete, you can remove those rights until the next deploy or upgrade.
Additionally, your developers might create plugins that need to create their
own tables. These are just like Liferay's plugins that do the same thing, and
they cannot be installed if Liferay can't create database tables. If you wish
to install these plugins, you will need to grant rights to create tables in the
database before you attempt to install them.

Once you have your database ready, you can install Liferay on your server.

<!-- Check the status of Liferay Marketplace for Liferay 7. Will it still be required? -->
## Liferay Marketplace

The Liferay Marketplace is an integral part of the Liferay Portal experience.
The Marketplace plugin is required to be installed on Liferay Portal. The
Marketplace plugin enables a host of features that extend beyond just access to
the online Liferay Marketplace. Some of the key features the Marketplace plugin
enables are:

- Liferay Marketplace: direct access to our online Marketplace
- App Manager: ability to install, uninstall, and update apps
- Bundled Apps: easily manage apps that may come bundled with your Liferay
  Portal
- Developer Apps: ability to manage apps that you're developing
- License Manager: streamlined license management for your Liferay Portal and
  apps

The portal installation process attempts to automatically deploy and register
the Marketplace plugin. If your environment supports hot deploy and allows full
database rights, the automatic deploy process takes care of itself. However,
many companies (especially in production environments), limit automated
processes and database access. Additionally, certain application servers (e.g.,
WebSphere) do not support hot deploy. So you may need to deploy the Marketplace
plugin manually. Depending on your environment's restrictions, you may need to
follow one or more of the steps below to install the Marketplace plugin.

### Server is Firewalled without Access to the Internet

Your server might be behind a firewall that prevents access to the Internet. Or
your security policy might not allow direct download and installation from the
Internet. In these cases, you have 2 options:

<!-- Need to update this instruction since Liferay 7 will not support the
deploy folder. -->
1. From an Internet-enabled computer, download the Marketplace plugin from
   [https://www.liferay.com/marketplace/download](https://www.liferay.com/marketplace/download).
   Then allow Liferay to auto deploy it by dropping the downloaded `.lpkg` file
   into the Liferay deploy folder.

2. From an Internet-enabled computer, download the Marketplace plugin. Then use
   the Liferay App Manager to deploy the plugin.

<!-- Need to update this link for Liferay 7. -->
Detailed instructions can be found under [Installing Plugins Manually](https://www.liferay.com/documentation/liferay-portal/6.2/user-guide/-/ai/plugin-management-liferay-portal-6-2-user-guide-14-en).

### Application Server Does Not Support Hot Deploy

If your application server does not support hot deploy, you can't leverage
Liferay's auto deploy feature. You can, however, manually deploy the plugin in
two steps:

1. Use Liferay's tools to pre-deploy the file.

2. Then use your app server's tools to do the actual deployment.

<!-- Need to update this link for Liferay 7. -->
These steps are necessary since Liferay makes deployment-time modifications to
the plugins right before they are actually deployed to the application server.
Detailed instructions can be found under
[Deploy Issues for Specific Containers](https://www.liferay.com/documentation/liferay-portal/6.2/user-guide/-/ai/plugin-management-liferay-portal-6-2-user-guide-14-en).

### Limited Database Access

Some production environments do not have the necessary database permissions for
Liferay and its plugins to maintain their tables. In these cases:

1. Grant the Liferay database user temporary full rights to the database.

2. Install Liferay and start it so that it populates its database.

3. Once the database is created, remove the permissions for creating tables and
   dropping tables from the Liferay database user.

See the Setting Up Liferay's Database with Restrictive Permissions section
above for more information. Note that many sophisticated Liferay apps--not just
the Marketplace plugin--require new tables when deployed. If your environment
restricts database access, you may need to repeat the above steps whenever you
deploy a new app to the Liferay Portal.

## Configuring Liferay Portal Security

Liferay Marketplace is an online store for obtaining applications that run on
the Liferay Portal platform. These applications are provided not only by
Liferay, but also by partners and independent developers who want you to
install and use their applications on your server. Many of these applications
are excellent and we recommend that you try them out for yourself.

However, because many of the applications on Marketplace are *not* provided by
Liferay, a question arises: how do you know these applications are doing what
they're advertised to do? There is a vetting process that they go through
before they're allowed on Marketplace, but if the source code is not provided,
there's no way for even Liferay to know if an app has been properly
represented. For this reason, Liferay Portal implements a security feature
known as the Portal Access Control List, or PACL.

PACL forces an application to declare up front the functions from Liferay's
APIs that it calls. Anything that's not declared is not allowed to run. It's
similar to what you might see on your mobile phone when you install an app: you
get to see the Liferay API functions the app uses, and then you can decide if
you want to install that app based on the permissions it requires. This way,
you see right away what portal data that app can access and the app can do
nothing else: you're protected--if you have PACL enabled. So if you plan to use
apps downloaded from Marketplace, it's important to make sure PACL is enabled.

By default, Liferay's bundles have PACL turned off. The reason for this is that
there is a small performance penalty for having PACL enabled. Since the only
reason to have PACL enabled is to install untrusted third party apps from
Marketplace (and not everybody does that), we decided to leave PACL turned off
by default. This way, your portal performs as fast as possible.

The bottom is line that if you intend to use Marketplace apps, you should
enable PACL. We provide manually installation documentation for all the app
servers supported by Liferay. Each of those sections has a subsection that
explains how to enable Java security for that app server, which is a
prerequisite for enabling PACL. Once you have Java security enabled, PACL can
be enabled by adding one line to your `portal-ext.properties` or
`portal-setup-wizard.properties` file:

    portal.security.manager.strategy=liferay

Save the file. If Liferay is running, restart it. Your portal is now configured
to check PACL-enabled Marketplace apps against their declared permissions.

Next, you'll make sure Liferay is configured properly for your network.

## Choosing IPv4 or IPv6

Liferay Portal supports both the IPv4 and IPv6 address formats. By default,
Liferay uses IPv4 addresses. If you're on an IPv6 network, you'll need to
change the configuration. If you'd like more information on the basics of these
protocols, you can check out the [reason](http://www.google.com/intl/en/ipv6/)
for using IPv6 addresses, and its [technical
details](http://en.wikipedia.org/wiki/IPv6).

To configure your portal to validate IPv6 addresses, you must complete a few
simple steps:

1. Assuming you're using the Tomcat app server for your portal, edit the
   `setenv.sh` or `setenv.bat` file in the `${TOMCAT_HOME}/bin` folder and set
   `-Djava.net.preferIPv4Stack=false` in `CATALINA_OPTS`.
2. Create a `portal-ext.properties` file in your portal's Liferay Home
   directory (if one does not already exist) and set the
   `tunnel.servlet.hosts.allowed` property to the target hosts you want to
   allow (e.g., *0:0:0:0:0:0:0:1*).

Next, let's learn about the folder structure of a Liferay installation.

## Liferay Home

Liferay Portal uses a special folder called *Liferay Home* to store resources.
This folder is usually the parent folder of the application server's folder.
This is why the bundles place the application server inside of the bundle's
root folder.

If Liferay is unable to create the resources it needs in this folder or if it
finds itself running on certain application servers, it creates a folder called
`liferay` in the home folder of the user that is running Liferay. In this case,
the `liferay` folder becomes Liferay Home.

The Liferay Home folder is important to the operation of Liferay. The `data`,
`logs`, and `osgi` folders belong  there, as well as Liferay's properties
configuration files.

We recommend that you use the setup wizard to configure your database
connection when you first configure Liferay. When you use the setup wizard, a
`portal-setup-wizard.properties` file is created which stores the settings that
you entered. When you begin customizing your portal's configuration, we
recommend you create a separate properties file for that, called
`portal-ext.properties`. This allows you to keep your server configuration
properties separate from core properties like your database and mail server
configurations. All the possible properties that can be placed in this file are
documented in
[our reference documentation](http://docs.liferay.com/portal/7.0/propertiesdoc).

+$$$

**Note:** To avoid using the setup wizard so you can configure everything
manually from a `portal-ext.properties` file, you must disable the setup wizard
by specifying `setup.wizard.enabled=false` in your `portal-ext.properties`
file. Also, note that property values in `portal-setup-wizard.properties` (the
file created in Liferay Home by the Setup Wizard) override property values in
`portal-ext.properties`.

$$$

Now that you understand all the prerequisites for installing Liferay Portal,
let's go ahead and do it! First, we'll explain how to install Liferay from a
bundle. After that, we'll provide instructions for installing Liferay manually
on all of its supported application servers.

