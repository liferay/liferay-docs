# Preparing for Install [](id=preparing-for-install)

Installing @product@ is easy. But before you begin, you should answer a few
questions.

- Which version of Liferay will you install?
- On which application server will you install @product@?
- Can you install a @product@ bundle or do you need to install @product@ on an
  existing application server?
- Which database will you use with @product@?
- How do you plan to store your data?
- Can your network support @product@?
- Will you enable Liferay's PACL security feature?

Next, you'll answer these questions and learn the basic steps for installing
@product@.

## Obtaining @product@

Anyone can download @product@ from [https://www.liferay.com](https://www.liferay.com).
Click Platform &rarr; *Downloads*, and you'll be able to download either the
open source version of @product@ or a trial of the commercial version in several
different formats. These include our convenient bundles as well as `.war` files
for installing @product@ on your application server of choice.

Liferay enterprise subscribers can download @product@ from the
Customer Portal at
[https://www.liferay.com/group/customer](https://www.liferay.com/group/customer).
You have a choice of the same open source app server bundles as community
members, plus a few commercial alternatives, in addition to the `.war` files
for manual installation.

So what is a @product@ bundle anyway? A @product@ bundle is an application server
with @product@ pre-installed. Using a bundle is the easiest way to install
@product@. @product@ is bundled with a number of application servers; all you need
to do is choose the one that best fits your needs. If you don't currently have
an application server preference, consider starting with the Tomcat bundle.
Tomcat is one of the most lightweight and straightforward bundles to configure.
If you have an open source application server preference, choose the server you
prefer from the available @product@ bundles. You must have a JDK (Java
Development Kit) installed prior to launching @product@.

Please note that @product@ is not able to provide application server bundles for
proprietary application servers such as WebLogic or WebSphere, because the
licenses for these servers don't allow for redistribution. @product@'s commercial
offering, however, runs just as well on these application servers as it does on
the others. You'll need to follow our manual installation procedure using a
`.war` file to install @product@ on proprietary application servers.

Once you have @product@, you can plan out your installation. First, determine if
you need @product@ Security turned on. Second, prepare your database. Third, install @product@. Fourth, configure your network. Fifth, configure search. You can install @product@
either by using a bundle or by installing it manually on your existing
application server. Next, we'll go over the steps it takes to install @product@.

## @product@ Installation Steps [](id=liferay-installation-steps)

Before you begin installing @product@, you should review these basic
installation steps:

1. Choose a database server to use with @product@ and create a new database.
   Determine whether you want @product@ to manage your database connection or your
   application server to manage your database connection. We recommend
   that you let @product@ manage your database connection. @product@ can connect with several open source or enterprise level document repositories.

2. Gather mail credentials for sending email notifications to users. Determine
   whether you want @product@ to manage your mail session or your application
   server to manage your mail session. @product@ provides a built-in mail session
   but also supports a JNDI mail session. We recommend that you let @product@
   manage your mail session.

3. Install either a @product@ bundle or install @product@ on an existing
   application server (further instructions below).

4. Choose IPv4 or IPv6. Determine which address format is best for your network (further instructions below). 

5. Determine how you'll configure Elasticsearch. @product@'s default embedded
   configuration is not supported for production use, so you'll have to install
   Elasticsearch separately, either on the same infrastructure or on its own. 

6. Determine whether you'll use Liferay Marketplace or other third party
   applications. If you will, you should enable Liferay's Plugin Access Control
   List (PACL) security feature.

We'll go through the steps in order, so first we'll look at the @product@
database.

## Step 1: Choose a Database Server and Create a New Database [](id=step-1-choose-a-database-server-and-create-a-new-database)

The recommended way of setting up your @product@ database is also the simplest.
@product@ takes care of just about everything. You only need to take two
simple steps:

1. Create a blank database encoded with the character set UTF-8. @product@ is a
   multilingual application and needs UTF-8 encoding to display all of its
   supported character sets.

2. Create a database user for accessing this database. Grant this database user
   all rights, including the rights to create and drop tables, to the blank
   @product@ database.

@product@ will use this database user's credentials to connect to the @product@
database either directly or through its application server. During its initial
startup, @product@ creates the tables it needs in the database you just created.
It does this automatically, complete with indexes.

This is the recommended way to set up @product@. It enables @product@ to maintain
its database automatically during upgrades or when various @product@ plugins that
create database tables of their own are installed. This method is by far the
best way to set up your @product@ database.

If you choose to set up @product@'s database with the recommended permissions
described in this section, you can skip to the next section.

+$$$

**Warning:** The instructions below are not ideal for @product@
installations. This procedure is documented here so that enterprises with more
restrictive standards can install @product@ with stricter (but sub-optimal)
database settings. If it's at all possible, we recommend that you use the
method described in the previous section instead of the procedure outlined
below.

$$$

Even though @product@ can create its database automatically, some enterprises
prefer *not* to allow the database user configured in an application server to
have the database permissions necessary for @product@ and its plugins to maintain
their tables. For these organizations, Select, Insert, Update and Delete are
the only allowed permissions. Thus, in this section we explain how to set up
the database manually. If your organization *is* willing to grant the @product@
database user the permissions to create and drop tables in the database--and
this is the recommended configuration--then simply use the recommended
configuration described in the previous section.

1. Create a new, blank, database for @product@.

2. Grant full rights to do anything to the @product@ database to the @product@
   database user.

3. Install @product@ and start it so that it automatically populates its
   database.

4. Once the database has been populated with the @product@ tables, remove the
   permissions for creating and dropping tables from the @product@ database user.

There are some caveats to running @product@ like this. Many @product@ plugins
create new tables when they're deployed. Additionally, @product@ has an automatic
database upgrade function that runs when @product@ is upgraded. If the @product@
database user doesn't have enough rights to create/modify/drop tables in the
database, you must grant those rights to the ID before you deploy one of these
plugins or start upgrading @product@. Once the tables are created or the upgrade
is complete, you can remove those rights until the next deploy or upgrade.
Additionally, your own developers might create plugins that need to create their
own tables. These are just like @product@'s plugins that do the same thing, and
they cannot be installed if @product@ can't create database tables. If you wish
to install these plugins, you will need to grant rights to create tables in the
database each time before you attempt to install them.

@product@ offers several configurations to store Documents and Media files by setting the `dl.store.impl=` property. Available options are Simple File System Store, Advanced File System Store, CMIS Store, DBStore, JCRStore, and Amazon S3Store. In addition, @product@ can be connected to various open source and enterprise-level document repositories. All of the repositories are connected to @product@ through hooks available on Liferay Marketplace (see below). 

Once you have your database and document repository ready, you can install @product@ on your server.

## Step Two: Gather Your Mail Credentials [](id=step-two-gather-your-mail-credentials)

@product@ uses a mail server to send email notifications. As part of the install,
therefore, you will need to have credentials that @product@ can use to connect to
your mail server. Specifically, you'll need to have the following information: 

- Incoming POP Server and port
- POP User Name
- POP Password
- Outgoing SMTP Server and port
- SMTP User Name
- SMTP Password
- A list of JavaMail properties that would override a default configuration

Once you've gathered this information, you're ready to move on to the next step. 

## Step Three: Install [](id=step-three-install)

The next step is to install @product@. You can do this in one of two ways: by
installing @product@ bundled with an application server, or by installing
@product@ manually on an existing application server. 

By far the easiest way to get @product@ installed is to use a bundle. Though
bundles are pre-packaged for demo purposes, it is very easy to turn them into
full, production-ready instances of @product@. 

## Step Four: Network Configurations [](id=step-four-network)

@product@ supports both IPv4 and IPv6 address formats. By default, @product@ uses IPv4 addresses. If you are using IPv6, you will have to configure @product@. There are two simple steps. 

1. In the application server's environment settings, set `-Djava.net.preferIPv4Stack=false`. 

2. Create a `portal-ext.properties` file in your portal's Liferay Home directory (if one does not already exist) and set the `tunnel.servlet.hosts.allowed` property to the target hosts you want to allow (e.g., _0:0:0:0:0:0:0:1_). 

## Step Five: Configure Elasticsearch [](id=step-five-configure-elastic-search)

@product@ by default ships with an embedded version of Elasticsearch. While this
configuration works well for demo purposes, it is not supported in a production
installation. After you install @product@, you'll need to configure it to connect
to a standalone Elasticsearch server or cluster. Depending on the size of your
installation, this standalone instance of Elasticsearch can reside either on the
same machine you have @product@ on or a different machine. For performance
purposes, it is better to install it on a separate machine.

## Step Six: Liferay Marketplace and Portal Security [](id=step-six-liferay-marketplace-and-portal-security)

The Liferay Marketplace is an integral part of the @product@ experience.
The Marketplace plugin is required to be installed on @product@. The
Marketplace plugin enables a host of features that extend beyond just access to
the online Liferay Marketplace. Some of the key features the Marketplace plugin
enables are

- Liferay Marketplace: direct access to our online Marketplace
- App Manager: ability to install, uninstall, and update apps
- Bundled Apps: easily manage apps that may come bundled with your @product@
- Developer Apps: ability to manage apps that you're developing
- License Manager: streamlined license management for your @product@ and
  apps

You will want to make sure Portal Access Control Lists are enabled if you intend
to download and install apps from Marketplace. This is an additional layer of
security that defines various permissions that apps may or may not have. 

The portal installation process attempts to deploy and register
the Marketplace plugin automatically. If you're installing @product@ in an
environment that would prevent this from happening, you'll have to perform one
of several workarounds. 

Now that you know where you're headed, you can install @product@. If you have 
decided to install @product@ using a bundle, continue with the next section. 
If you're installing @product@ manually, skip to the section for your 
application server of choice. Once you have @product@ installed manually or 
via a bundle, you can move on to [configuring search](/discover/deployment/-/knowledge_base/7-0/installing-elasticsearch). 
