# Configuring Liferay Portal Security [](id=configuring-liferay-portal-security-lp-6-2-use-useportal)

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
