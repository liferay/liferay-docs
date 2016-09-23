# Configuring Liferay Marketplace [](id=configuring-liferay-marketplace)

Liferay Marketplace is more than just a store for Liferay applications. Under
the hood, it not only provides the store, it also provides @product@'s
application deployment features. For this reason, you must ensure that
Marketplace works well on your installed system. The first thing you should do
is make sure Marketplace can run and configure itself. The second thing you
should do is enable Liferay's Portal Access Control List, or PACL. 

First, you'll learn about some scenarios in which Marketplace fails to run, but
they can all be worked around. Next, you'll configure PACL. 

## Server is Firewalled without Access to the Internet [](id=server-is-firewalled-without-access-to-the-internet)

Your server might be behind a firewall that prevents access to the Internet. Or
your security policy might not allow direct download and installation from the
Internet. In these cases, you have two options:

1. From an Internet-enabled computer, download the Marketplace plugin from
   [https://www.liferay.com/marketplace/download](https://www.liferay.com/marketplace/download).
   Then allow Liferay to auto deploy it by dropping the downloaded `.lpkg` file
   into the Liferay deploy folder.

2. From an Internet-enabled computer, download the Marketplace plugin. Then use
   the Liferay App Manager to deploy the plugin.

## Application Server Does Not Support Hot Deploy [](id=application-server-does-not-support-hot-deploy)

If your application server does not support hot deploy, you can't leverage
Liferay's auto deploy feature. You can, however, manually deploy the plugin in
two steps:

1. Use Liferay's tools to pre-deploy the file.

2. Then use your app server's tools to do the actual deployment.

## Limited Database Access [](id=limited-database-access)

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

Please note that if you installed @product@ manually, there may be further
configuration you need to do to enable PACL. Please check the relevant
installation instructions for your app server for further information. 

Congratulations! Liferay is now installed and ready for production. 
