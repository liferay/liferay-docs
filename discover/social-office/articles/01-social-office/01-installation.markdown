# Installation [](id=installation)

Unlike previous versions of Social Office, Social Office 2.0 is available as an
application that can be installed on Liferay Portal 6.1. Liferay applications,
or apps, are packages of Liferay plugins that are grouped together with
descriptions, version information, and other metadata. You can identify
downloaded Liferay apps by their `.lpkg` file extensions. They're available from
Liferay Marketplace at
[http://www.liferay.com/marketplace](http://www.liferay.com/marketplace). Prior
to version 2.0, Social Office was provided as an independent product that was
based on, but separate from, Liferay Portal.

To install Social Office manually, you just need to deploy the Social Office
`.lpkg` file to your Liferay installation. If you're installing Social Office
CE, you can install it just like any other app from Liferay Marketplace.
Remember that there are two ways to access Liferay Marketplace: from
[http://www.liferay.com/marketplace](http://www.liferay.com/marketplace) or from
the Marketplace section of your Liferay instance's Control Panel. If you
purchase Social Office EE, you need to deploy the license order id that you'll
receive before you can install Social Office. 

To find Social Office EE on Liferay Marketplace, click on the *EE Marketplace*
link and look for the Social Office EE app. Once you've found it, click on it to
view some details about the app. If you'd like to install Social Office EE, you
can either purchase the app or you can obtain a free 30-day trial.

![Figure 1.1: If you'd like to try out Social Office EE, click on the *30-Day
Trial* button. If you'd like to purchase Social Office EE, click on the *Order*
button.](../../so-ee-order-trial.png)

Whichever option you choose, you'll be prompted to select a purchase type. If
you'd like to obtain the free 30-day trial, you'll need to agree to the End User
License Agreement and Terms of Service before you can click *Free* to obtain the
trial license.

![Figure 1.2: As with other apps on Liferay Marketplace, you can purchase Social
Office EE for your personal liferay.com account or for your
company.](../../so-ee-order-trial.png)

If you'd like to place an order to purchase Social Office EE, click *Next* after
selecting a purchase type. Then you'll need to choose a license type, provide
your name, address, and contact information, and agree to the terms of use.
Once you click *Order*, a Liferay sales representative will contact you to
fulfill your order.

![Figure 1.3: To deploy your Social Office EE license, enter your order id
license into the field provided, click *Query*, and select *Social Office
EE*.](../../so-ee-order-id-license.png)

Once you've obtained a Social Office EE license (trial or otherwise), you should
deploy the license to your Liferay instance. To do so, first click on *License
Manager* at the bottom of the Server Administration section of the Control
Panel. Then enter your order id into the field provided, click *Query*, and
select *Social Office EE*. Now you're able to install Social Office EE! Note: If
you already downloaded Social Office EE and attempted to install it before
deploying your order id license, you'll need to restart your Liferay server
after deploying the license in order for Social Office EE to deploy.

The Social Office `.lpkg` file is hot-deployable and includes the following
Liferay plugins. The plugins listed in italics belong only to either the CE or
EE versions of Social Office.

**CE Plugins:**

- chat-portlet
- contacts-portlet
- deploy-listener-hook
- events-display-portlet
- microblogs-portlet
- portal-compat-hook
- private-messaging-portlet
- *resource-importer-web*
- so-hook
- so-portlet
- so-theme
- *so-welcome-theme*
- tasks-portlet
- wysiwyg-portlet

**EE Plugins:**

- chat-portlet
- contacts-portlet
- deploy-listener-portlet
- *documentum-hook*
- events-display-portlet
- microblogs-portlet
- portal-compat-hook
- private-messaging-portlet
- *sharepoint-hook*
- *so-configurations*
- so-hook
- so-portlet
- so-theme
- tasks-portlet
- wysiwyg-portlet

After deploying Social Office, we recommend that you restart your application
server. Restarting may not be necessary in all cases but it can help you avoid
potential caching problems. For example, sending and accepting connection
invitations among Social Office users might not work without a server restart.
For additional information, see the section below on installation peculiarities
for specific application servers. If you want to avoid restarting, you should at
least re-index Liferay's search indexes and database cache. These options are
available from the Server Administration section of Liferay's Control Panel.

+$$$

**Warning:** Social Office 2.0 is incompatible with 7 Cogs, the sample website 
that comes pre-installed with Liferay bundles. You need to remove 7 Cogs from 
your Liferay installation before installing Social Office. This is very easy to 
do. For a Tomcat bundle, just navigate to 
`<Liferay Home>/tomcat-<version>/webapps` and remove the `seven-cogs-hook` 
folder. For other application server bundles, navigate to the directory where 
web applications are installed and remove the `seven-cogs-hook` folder. Please 
refer to chapter 11 of Using Liferay Portal at
[http://www.liferay.com/documentation/liferay-portal/6.1/user-guide/-/ai/installing-liferay-on-an-existing-application-server](http://www.liferay.com/documentation/liferay-portal/6.1/user-guide/-/ai/installing-liferay-on-an-existing-application-server)
if you're not sure where the web applications directory is on your application
server.

$$$

+$$$

**Warning:** When you start Liferay for the first time, you're presented with a 
setup wizard. The configuration you make in the setup wizard is saved in a 
`portal-setup-wizard.properties` file in your Liferay Home directory. However, 
if you specify a different Liferay Home directory than the default in the setup 
wizard, the `portal-setup-wizard.properties` file that's saved there will not be 
read upon restarting your server. To have Liferay read your
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

## Installation Peculiarities for Specific Apps Servers [](id=installation-peculiarities-for-specific-apps-servers)

After installing Social Office on Glassfish, you need to restart the server
before Social Office will be fully deployed. If you don't restart, Glassfish
doesn't recognize the so-hook and the hook won't be available. Once you've
restarted the server, the so-hook deployment completes as expected.

For JBoss, you have to restart the server in order to see the Dashboard link. If
you don't restart the server, the Dashboard link won't appear although you can
find other content added by Social Office if you use the *Go To* &rarr; *My
Public/My Private Pages* menu. To fix this issue, simply restart your server.

## Uninstalling Social Office [](id=uninstalling-social-office)

If you'd like to uninstall Social Office, you can do so from the *Purchased*
page of the control panel, under the Marketplace heading. However, after you've
uninstalled Social Office from Marketplace, you need to manually revert the
friendly URL of the Welcome page. 

![Figure 1.6: After uninstalling Social Office from Marketplace, you need to
manually revert the friendly URL of the Welcome
page.](../../images/site_pages_welcome_sologin.png)

Otherwise, the portal will always be looking for the `/so/login` page which no
longer exists since the hook is gone. This will produce a number of errors in
the log and once you log out, you won't be able to log in again.
