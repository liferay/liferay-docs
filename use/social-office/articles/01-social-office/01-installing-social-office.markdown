# Installing Social Office [](id=installing-social-office)

Social Office 3.0 is available as an application that can be installed on
Liferay Portal CE 6.2 GA1 or EE 6.2 GA1. Prior to version 2.0, Social Office was
provided as an standalone product that was essentially a customized version of
Liferay Portal. Liferay applications, or apps, are packages of Liferay plugins
that are grouped together with descriptions, version information, and other
metadata. You can identify downloaded Liferay apps by their `.lpkg` file
extensions. Individual Liferay plugins have the standard `.war` file extensions.

This section begins with a quick set of instructions on installing Social Office.
In the majority of cases, this is all you'll need. The parts after this present 
additional details for specific configurations and use cases.

- Social Office Quick Install
- Pre-installation Configuration
- Post-installation Configuration
- Validating Your Installation
- Uninstalling Social Office

## Social Office Quick Install 

There are two ways to install Social Office: you can download and
install Social Office directly from your Liferay instance's Control Panel
Marketplace interface (this is simplest) or you can deploy the `.lpkg` file manually.

To install Social Office via the Control Panel, navigate to the Control Panel
and click on *Marketplace* &rarr; *Purchased*. If you've already purchased
Social Office, you'll see Social Office listed in your purchased apps.
Otherwise, you'll have to browse Liferay Marketplace, find Social Office, and
purchase it before you can continue. For Social Office CE, click *Download* to
download the app. Once the app is downloaded, click on *Install* to start
installing Social Office.

To install Social Office manually, you need to download the Social Office
`.lpkg` file from [Liferay Marketplace](http://www.liferay.com/marketplace).
Once you have it, you just need to deploy the Social Office `.lpkg` file to your
Liferay instance's deploy folder.

## Pre-installation Configuration 

Out of the box, Social Office works with Liferay's default portal properties.
However, if certain properties have been modified by a portal administrator
prior to your Social Office installation, they might prevent Social Office from
working properly. Before installing Social Office, please check your Liferay
instance's `portal-ext.properties` file for the following and make sure that the
following properties are set properly.

After installing Social Office and assigning the Social Office roles to a user,
the user should have a private Dashboard page and a public Profile page. The
following properties ensure that users' Dashboard and Profile pages are created
once you assign them to Social Office User Role *or* after assigning them as a
Social Office User in the SO Configurations portlet:

    layout.user.private.layouts.enabled=true
    layout.user.private.layouts.auto.create=true
    layout.user.public.layouts.enabled=true
    layout.user.public.layouts.auto.create=true

You may set the following properties to either `true` or `false`. `true` means
that you also have to assign the Power User role to a user in order for the user
to maintain Dashboard and Profile pages:

    layout.user.private.layouts.power.user.required=
    layout.user.public.layouts.power.user.required=

The following properties cause complications to the Social Office Dashboard and
Profile creation if the properties are set to anything else. Make sure you leave
these blank if you intend to use Social Office:

    default.user.private.layouts.lar
    default.user.public.layouts.lar

## Post-installation Configuration 

After deploying Social Office, check your server's console log. There should not
be any console errors during installation. If you encounter any console errors,
please refer to the Validating a Social Office Installation section.

We recommend that you restart your application server after Social Office has
been deployed. Restarting may not be necessary in all cases but it can help you
avoid potential caching problems. In some cases, for example, sending and
accepting connection invitations among Social Office users might not work
without a server restart. For additional information, see the section below on
installation peculiarities for specific application servers. If you want to
avoid restarting, you should at least re-index Liferay's search indexes and
database cache. These options are available from the Server Administration
section of Liferay's Control Panel.

+$$$

**Warning:** When you start Liferay for
the first time, you're presented with a setup wizard. The configuration you make
in the setup wizard is saved in a `portal-setup-wizard.properties` file in your
Liferay Home directory. However, if you specify a different Liferay Home
directory than the default in the setup wizard, the
`portal-setup-wizard.properties` file that's saved there will not be read upon
restarting your server. To have Liferay read your
`portal-setup-wizard.properties` file, create a `portal-ext.properties` file in
your new Liferay Home directory and add the following line to it, where
`${liferay.home}` is the new Liferay Home directory that you chose:

    include-and-override=${liferay.home}/portal-setup-wizard.properties

Without this workaround, Liferay will not read the
`portal-setup-wizard.properties` file with your saved configuration when you
restart your server and you'll see the setup wizard again. Furthermore, Social
Office will not be redeployed and won't be functional. This means that the
Dashboard button on the Dockbar won't show up. If you manually navigate to the
dashboard URL, you see the pages but they'll be unthemed and the portlets will
be greyed out with a message stating that they've been undeployed. 

$$$

### Installation Peculiarities of Specific App Servers 

After installing Social Office on Glassfish, you need to restart the server
before Social Office will be fully deployed. If you don't restart, Glassfish
doesn't recognize the so-hook and the hook won't be available. Once you've
restarted the server, the so-hook deployment completes as expected.

For JBoss, you have to restart the server in order to see the Dashboard link. If
you don't restart the server, the Dashboard link won't appear although you can
find other content added by Social Office if you use the *Go to* &rarr; *My
Public/My Private Pages* menu. To fix this issue, simply restart your server.

Next, let's discuss how to validate that Social Office has been successfully
installed or uninstalled.

## Validating a Social Office Installation 

The Social Office 3.0 `.lpkg` file is hot-deployable and includes the following
Liferay plugins. After installing (or uninstalling) Social Office, you should
confirm that these plugins have been successfully deployed (or removed). Your
server's console log should indicate whether or each plugin has been
successfully deployed.

**CE Plugins:**

- calendar-portlet
- chat-portlet
- contacts-portlet
- deploy-listener-hook
- events-display-portlet
- microblogs-portlet
- notifications-portlet
- portal-compat-hook
- private-messaging-portlet
- so-activities-hook
- so-announcements-portlet
- so-configurations-portlet
- so-hook
- so-portlet
- so-theme
- tasks-portlet
- wysiwyg-portlet

If all the Social Office plugins have been successfully deployed, check that
you can access the following items in your portal, either in the Control Panel
or on a default Social Office site:

**Role**:

- Social Office User

**Site Templates**:

- Default Social Office Site
- Social Office User Home (Dashboard)
- Social Office User Profile

**Portlets**:

- Chat
- Contacts
- Events Display
- Microblogs
- Private Messaging
- Social Office Configurations
- Social Office Portlet
- Tasks
- Wysiwyg

**Hooks**:

- Deploy Listener
- Portal Compat Hook
- Social Office Hook

**Themes**:

- Social Office theme

To actually start using Social Office after it's been installed, you need to
grant the Social Office User role to one or more users. Please refer to the 
*Using Social Office* section for more details about the Social Office User role 
and the features provided by Social Office.

### What to do if Your Installation is Unsuccessful 

If your Social Office Installation was unsuccessful, please make sure that you
haven't made one of the following installation mistakes:

- Your Liferay database must use character set UTF-8 in order for Social Office
  to work.
- If you have the *plugin-deployment-1* patch installed, you need to install the
  following patches on your Liferay Portal instance:
    - Core-4 or above
    - hotfix-55
    - hotfix-446
- A previously failed installation of Social Office can produce a duplicate
  entry exception and cause the installation to fail.
    - In this case, it's best to uninstall Social Office through Marketplace
      since this operation performs data clean up. 
    - If uninstalling through Marketplace is not an option, you can delete the
      following components manually through the Control Panel and attempt the
      installation again.
        - Social Office User Role
        - Default Social Office Site Template
        - Social Office User Home Template
        - Social Office User Profile Template 

If the tips above do not solve your installation problem, you can seek further
assistance through the [Social Office Forum](http://www.liferay.com/community/forums/-/message_boards/category/1670998) or from [Liferay Support](http://www.liferay.com/group/customer/home).

## Uninstalling Social Office 

If you'd like to completely uninstall Social Office, you can navigate to the
*Marketplace* &rarr; *Purchased* page of the Control Panel and click on
*Uninstall*. 

Uninstalling Social Office reverts users' Profile and Dashboard pages to the
default Liferay public and private pages and reverts Social Office sites to
non-Social Office sites. However, no data will be deleted. Uninstalling only
uninstalls the plugins included in Social Office, the Social Office role, and
the site templates. After uninstalling, make sure that the components listed above
in the section on validating your installation are no longer available.

Next, let's discuss how Social Office can facilitate team collaboration.
