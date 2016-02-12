# Liferay Installation Overview [](id=liferay-installation-overview)

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
## Editions of Liferay [](id=editions-of-liferay)

Liferay ships in two editions: Liferay Community Edition (CE) and Liferay
Digital Experience Platform (DXP). Frequently updated and bursting with
features, Liferay CE is offered for free under the Lesser GNU public license, a
free software license with one important exception. This license gives you the
flexibility to link Liferay with your own code in your Liferay plugins, no
matter what license you use for your code. If, however, you modify Liferay
directly, those modifications must be released as free software under the terms
of the license. Of course, the best way to do this is to contribute your code
back to the Liferay community. This is really the best of both worlds: you have
the freedom use any license (or no license) if you write plugins, but if you
modify Liferay directly, the community receives the benefits of any
enhancements that you've made.

Liferay DXP is a supported version of Liferay Portal for the enterprise.
Liferay DXP includes additional features, such as clustering, that are not
available in Liferay CE. Liferay DXP's subscription and support package allows
organizations to build their portals on a stable version of the product that is
offered over an extended period of time. It's the best way for you to develop,
deploy, and maintain your Liferay solution. Liferay DXP includes:

- Liferay support
- Additional features
- Updates
- Fix Packs
- Cloud Services

Liferay DXP can be installed on proprietary application servers and can use
proprietary databases. Here are lists of the application servers and databases
supported by Liferay CE and Liferay DXP:

**Liferay CE-supported Application Servers:**

- Apache Tomcat 8.0.30
- Wildfly 10.0
- Caucho Resin 4.0

**Liferay CE-supported Databases:**

- HSQLDB 2 (only for demonstration, development, and testing)
- MySQL 5.6
- MariaDB 10
- PostgreSQL 9.3

**Liferay DXP-supported Application Servers:**

- All of the CE-supported application servers
- IBM Websphere 8.5
- JBoss EAP 6.4
- Mulesoft Tcat 7.1
- Oracle Weblogic Server 12c (12.1.x)
- Caucho Resin Pro 4.0
- tcServer 2.9

**Liferay DXP-supported Databases:**

- All of the CE-supported databases
- Oracle Database 12c Release 1
- Microsoft SQL Server 2012
- Sybase ASE 16

Next, let's learn about some differences between Liferay releases.

<!-- This section must be updated for 7.0. CE vs. EE terminology will need to
change. -->
## Understanding Liferay's Releases [](id=understanding-liferays-releases)

Which version of Liferay should you install? That's an easy question to answer:
if this is a new installation, we recommend you install the latest version
available. If you want to understand how Liferay releases work, read on.

Liferay's release process follows a prescribed structure that is consistent
from one release to the next. Each release has a specific number sequence
attached to it, signifying the type of release it is: major, minor or
maintenance release. Each release also has a term attached to it to indicate
its intended level of quality.

EE subscribers have access to additional maintenance releases, along with
specific *Fix Packs* and *Hot Fixes* that make applying updates to production
environments safer and faster.

Let's start with an explanation of Liferay's version structure. Liferay
versions are organized in a straightforward numerical system consisting of a
three digit number. For example, 6.1.2. These numbers represent the type of the
release: Major.Minor.Maintenance.

A change in the first or second digit of the version (e.g., 6.x to 7.x or 6.1
to 6.2) is a major or minor release. This means that there are changes in
functionality or new functionality based on high demand. There are new features
and bug fixes from prior releases. There are also architectural changes,
changes to APIs (as part of the deprecation process), or changes to internal
schema for frameworks such as Service Builder, because new features have been
added. Customizations may be affected when installing. Customers should
leverage the upgrade tools and documentation.

A change in the third digit of the version scheme (e.g, 6.2.1 to 6.2.2) is a
maintenance release. This means that each maintenance release provides an
improved level of security and reliability. Customizations are generally safe,
but we recommend doing a review. No new features are included.

These rules are relaxed when a minor or major release is still in beta quality.

Now let's delve into the evolution of versions.

### Release Process [](id=release-process)

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

### Liferay Installation Advice [](id=liferay-installation-advice)

Armed with this information, here's our advice for how to manage your Liferay
installation.

- When starting a new project, always use the latest stable version available.
  (This means the latest available GA.) At the time of writing, the most recent
  version is Liferay CE 6.2 GA1 (6.2.0) or Liferay EE 6.1 GA2 (6.2.00).

- Always update to the latest maintenance release available for the functional
  version (major or minor) that you are using. For example, if you started your
  project with Liferay 6.2.0 GA1, it is recommended that you switch to GA2
  if/when it becomes available to take advantage of bug fixes and improvements. If
  you have a subscription, you can benefit from the fixes faster by requesting
  fix packs and hot fixes from the support team.

- You are always welcome to use any preview, beta or release candidate. In
  fact, that's why they exist--so as many people as possible start using it and
  provide us their feedback. Please note, we do not recommend using
  pre-releases (milestones, betas, or release candidates) in production. You
  might not even want to use these releases during development if you have
  tight deadlines, since you may hit some unexpected bugs.

- Plugins that work in any GA or fix pack version will work in any later
  maintenance release. That is, a plugin developed for Liferay 6.2 GA1 will
  also work in Liferay 6.2 GA2 or a GA2 fix pack.

For more details on updating Liferay Portal, see the
[Upgrading Liferay](/discover/deployment/-/knowledge_base/6-2/upgrading-liferay)
article.

Liferay Portal is a very flexible application that runs well on several
different server environments. It's simple to install and follows a systematic
versioning system that makes it easy to keep current with the latest updates.
The strength of the Liferay community helps detect potential issues early that
are then reported through the forums and are later fixed in a series of
maintenance releases.

## Obtaining Liferay Portal [](id=obtaining-liferay-portal)

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

## Liferay Installation Steps [](id=liferay-installation-steps)

Before you begin installing Liferay, You should review these basic installation
steps:

1. Choose a database server to use with Liferay and create a new database.

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

## Setting Up Liferay's Database with the Recommended Permissions [](id=setting-up-liferays-database-with-the-recommended-permissions)

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

## Setting Up Liferay's Database with Restrictive Permissions [](id=setting-up-liferays-database-with-restrictive-permissions)

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
create new tables when they're deployed. Additionally, Liferay has an automatic
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
## Liferay Marketplace [](id=liferay-marketplace)

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

### Server is Firewalled without Access to the Internet [](id=server-is-firewalled-without-access-to-the-internet)

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

### Application Server Does Not Support Hot Deploy [](id=application-server-does-not-support-hot-deploy)

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

### Limited Database Access [](id=limited-database-access)

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

## Configuring Liferay Portal Security [](id=configuring-liferay-portal-security)

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

## Choosing IPv4 or IPv6 [](id=choosing-ipv4-or-ipv6)

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

## Liferay Home [](id=liferay-home)

Liferay Portal uses a special folder called *Liferay Home* to store resources.
This folder is usually the parent folder of the application server's folder.
This is why Liferay bundles place the application server inside of the bundle's
root folder.

Inside of the Liferay Home folder, there are folders for various purposes:

    - [Liferay Home]
        - `data`
        - `deploy`
        - `logs`
        - `osgi`
        - [Application Server]

`data`: This folder is used to store an embedded HSQL database, Liferay's
Jackrabbit JSR-170 file repository, and Liferay's search indexes. Liferay is
initially configured to use the embedded HSQL database but the HSQL database is
primarily intended for demonstration and trial purposes. Liferay's database
connection can be configured on the Basic Configuration page that appears on
Liferay's initial startup.

`deploy`: To deploy Liferay plugins, copy them to this folder. Legacy style
`.war` files, Liferay 7 style `.jar` files, and `.lpkg` files from Liferay
Marketplace are supported.

`logs`: This folder contains Liferay's log files. The information in Liferay's
log files can be quite valuable for system administrators, especially when
trying to diagnose a problem.

`osgi`: All of the JAR files and a few configuration files for Liferay's OSGi
runtime belong in this folder.

**[Application Server]:** The name of this folder is different depending on
the bundle you've downloaded. This folder contains the application server in
which Liferay has been installed.

If Liferay is unable to create the resources it needs in the Liferay Home
folder or if it finds itself running on certain application servers, it creates
a folder called `liferay` in the home folder of the operating system user that
is running Liferay. In this case, the `liferay` folder becomes Liferay Home.
For example, if the operating system user's name was jbloggs, the Liferay Home
folder could be `/home/bloggs/liferay` or `C:\Users\jbloggs\liferay`.

# Using Liferay's Setup Wizard [](id=using-liferays-setup-wizard)

Liferay's Setup Wizard runs when you start Liferay for the first time. If
you're installing a Liferay bundle or manually installing Liferay on an
existing application server, please refer to the appropriate documentation and
then come back here. Once you've started Liferay, come back here. To make it
easy to configure Liferay, the first thing you see when browsing to your newly
installed Liferay bundle is a setup wizard. The title of the setup wizard page
is Basic Configuration. This page provides a convenient way to make an initial
Liferay configuration.

There are three sections of the wizard: the portal, the administrator and the
database. For the portal, you need to supply the following information:

**Portal Name:** the name of the portal you're powering with Liferay.

**Default Language:** choose the default locale of your portal.

For the administrator, you need to supply the following information:

**First Name:** the first name of the default administrator user

**Last Name:** the last name of the default administrator user

**Email:** the email address of the default administrator user

![Figure 1: Supply the information for your portal and your portal's default administrator user on the Basic Configuration page.](../../images/basic-configuration1.png)

In addition to these, Liferay also supports a few embedded databases that are
designed for development. We haven't listed these here because you're setting up
a production Liferay server, and you shouldn't use an embedded database with a
production box.

Before you fill out the database section of Liferay's setup wizard, you should
already have created a database for Liferay to use. If you haven't, create a
new UTF-8 encoded database for Liferay.

Open the Database section of the wizard. From the select box, choose your
database. You'll see a form which lets you specify the JDBC URL to the
database, the driver class, and the user credentials (see below). Most of this
is filled out already; all you should need to do is supply the name of your
database, the server it's running on, and the database user credentials.

![Figure 2: Fill out the information for your database. We've chosen MySQL in this example and have created a database called *lportal* to hold our Liferay data.](../../images/basic-configuration2.png)

The Basic Configuration page also includes a checkbox labeled *Add Sample
Data*. If you check this box, sample data is added to Liferay's database. This
data includes users, sites, and organizations. The sample data allows many
Liferay features to be showcased. If you're installing Liferay on your own
machine to explore its features, the sample data will probably be useful. If,
however, you're installing Liferay on a real server, you should start with a
clean system.

Once you've filled out the form, click *Finish Configuration*. You'll see a
message stating that Liferay is being installed as it creates the tables and
data it needs in its database. The setup wizard creates a
`portal-setup-wizard.properties` file which stores the settings that you
entered. When you begin customizing your portal's configuration, we recommend
you create a separate properties file for that, called `portal-ext.properties`.
This allows you to keep your server configuration properties separate from core
properties like your database and mail server configurations. All the possible
properties that can be placed in this file are documented in [our reference
documentation](http://docs.liferay.com/portal/7.0/propertiesdoc).

+$$$

**Tip:** The wizard is an extremely helpful tool, especially if you're setting
up Liferay for the first time. If you're a Liferay veteran and you already have
your database information and various properties set up, you can disable the
setup wizard. If you disable the setup wizard, you must manually configure
everything manually from a `portal-ext.properties` file. To disable the setup
wizard, enter `setup.wizard.enabled=false` in your `portal-ext.properties`
file. Note that property values in `portal-setup-wizard.properties` (the file
created in Liferay Home by the setup wizard) override property values in
`portal-ext.properties`.

$$$

After you've entered the information requested by the Basic Configuration page,
you'll be prompted to restart Liferay. After restarting Liferay, you should set
up your mail configuration. For more information, see the Using Mail Sessions
section above.

Now that you've reviewed the basic Liferay installation process, it's time to
actually install Liferay! To learn how to install Liferay from a bundle, please
see the [Installing a Liferay Bundle]() article. If you want to manually
install Liferay on one of its supported application servers, please see the
appropriate article.
