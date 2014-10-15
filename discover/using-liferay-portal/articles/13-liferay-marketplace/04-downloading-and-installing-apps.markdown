# Downloading and Installing Apps [](id=downloading-and-installing-apps)

Once you've found an app you wish to download and use, click on the name of the
app to display its detailed information screen.

![Figure 13.10: Detailed App
Information](../../images/marketplace-app-information.png)

On this screen, there are a number of items to assist in learning more about the
app. You can find the primary information about the app on the left side of the
screen. In the center display, you see a set of screenshots along with a
description of the app. On the left, below the icon for the app, is some basic
information about the app.

* Author: The creator of the app

* Rating: The average rating of the app on a scale from zero to five stars. The
  number of ratings is also shown.

* Latest Version: The latest released version of this app

* Supported By: Who to contact if you need support for this app

* Users: The total number of users of the app

* Weekly Installs: The average number of installations per week

* Developer Profile: A link to the app developer's `liferay.com` profile page

* Developer Website: A link to the developer's own website

* Purchase: The button to click on to purchase the app. You must purchase an app
  before you can download it.

In the lower section, you will find Reviews and Version History tabs for this
app. Check here to find out what other people are saying about this app. In
addition, on a separate tab, you will find the history of versions for this app,
where you can download other versions (for example, if you are using an older
version of the Liferay Platform, you may need to download a specific version of
this app that is compatible with the version of the Liferay Platform you are
using).
 
![Figure 13.11: Detailed App
Information](../../images/marketplace-app-version-history-and-reviews.png)

## Downloading and Installing [](id=downloading-and-installing)

You've chosen an app, read the reviews and want to download and use the app!
There are two ways to install the app. Ultimately, both methods result in the
same outcome: the app you've chosen is installed onto your local running Liferay
instance.

## Liferay Hot Deploy [](id=liferay-hot-deploy)

Apps on the Liferay Marketplace consist of individual Liferay Plugins (for
example: a portlet, a hook, or a collection of multiple plugins).  Ultimately
these apps must be installed on a running Liferay instance before they can be
used.  Deploying an app to a running Liferay instance is automatically done
through the process of *hot deploy*.  When using *Control Panel* to install apps
from the Marketplace, when you click *Purchase* or *Install* to download and use
a given app, it will be downloaded and hot deployed to your local running
Liferay instance.

For some Liferay installations, the hot deploy mechanism is disabled, in order
for the site administrator to manage (or prevent) the deployment of plugins.  In
this case, the app will still be downloaded and stored in the hot deploy
directory (with a `.lpkg` extension in its filename), but must be manually
deployed using the custom process used for deploying other plugins.

Please see the later section *Installing Plugins Manually* To learn more about
hot deploy, its behavior on various app servers, and how to manually deploy
Marketplace apps in situations where hot deploy cannot be used.

## Installing Through the Control Panel [](id=installing-through-the-control-panel)

The easiest way to install an app is to do so from your Liferay control panel.
This requires that you have already installed Liferay on your local machine and
that you can log in as an administrator. Once you are logged in as an
administrator, click the *Go to* menu on the Dockbar and choose *Control Panel*.

![Figure 13.12: Marketplace from the Control
Panel](../../images/marketplace-control-panel-entries.png)
 
Click on either the *Store* or the *Purchased* link in the Marketplace category
at the upper-left. Before you can access Marketplace via the control panel, you
need to associate your `liferay.com` login credentials with your local
administrator account. Enter your `liferay.com` email address and password so
your Liferay installation can connect to the `liferay.com` Marketplace.

![Figure 13.13: Marketplace Login Screen](../../images/marketplace-login.png)
 
![Tip](../../images/01-tip.png) Any local user with administrative privileges
can use the Marketplace to browse and install apps from the Marketplace, by
entering their `liferay.com` credentials in the above login screen.  This allows
multiple administrators to manage the apps installed on the local Liferay
instance.  Once a link is established between a local administrator account and
a `liferay.com` account, there is no way to undo this, short of re-installing
Liferay.
 
Once you've successfully linked the accounts, you will be presented with the
same Marketplace screens as you would have if you were directly accessing the
Marketplace. You will be able to browse, search, and install directly from the
Marketplace. Click on the *Store* link under the Marketplace heading in the
control panel to browse the apps available from the `liferay.com` Marketplace.
Browse to the app you wish to install, click the *Purchase* button, and then the
*Buy* button on the next screen. The app will be downloaded and deployed to your
local Liferay installation.
 
All apps that you've bought are listed on the *Purchased* page of the
Marketplace control panel. Clicking on the *Purchased* link will show you a list
of those apps which you have downloaded in the past, including apps you may have
purchased/downloaded while using other Liferay installations.

![Figure 13.14: Purchased
Apps](../../images/marketplace-purchased-apps-control-panel.png)
 
The apps which you downloaded and installed on the currently running instance of
Liferay are listed as Installed. Apps which you have previously downloaded or
purchased on other Liferay instances that are incompatible with the current one
are listed as Not Compatible. You need to re-download/re-install the appropriate
version of these apps if you wish to use them on your running instance of
Liferay.
 
## Downloading Through liferay.com [](id=downloading-through-liferay-com)

The second way to install an app is to download it first, then in a separate
step, deploy it to your running Liferay instance. This is especially useful in
situations where you do not wish to deploy the app directly to your production
environment, or in cases where the target Liferay instance that is to receive
the app is behind a corporate firewall or otherwise does not have direct access
to the Marketplace.

In this case, using your browser, you will find the app on
`liferay.com/marketplace`. Once found, click on the *Purchase* button when
viewing the individual app. This will cause the app to be placed in your
*Purchased* list on your personal Home page. Navigate to your Home page, click
on *App Manager* and visit the *Purchased* tab. Each app is listed. Find the app
you just purchased and click *Downloads*. You'll find a list of app versions and
Liferay versions. Choose the version of the app you want, making sure the
Liferay version corresponds to the version of the Liferay installation into
which you'd like to install the app. Click *Download App* and the app is
downloaded to your local machine in the same way any other file would be
downloaded. This file can then be hot-deployed to Liferay by copying it to
Liferay's hot deploy directory.
