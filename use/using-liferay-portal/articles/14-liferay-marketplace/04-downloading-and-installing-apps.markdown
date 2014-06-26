# Downloading and Installing Apps [](id=downloading-and-installing-apps-lp-6-2-use-useportal)
 
Once you've found an app you wish to download and install, click on the name of
the app to display its detailed information screen.

![Figure 14.11: Click on an app to read a description and view additional details.](../../images/marketplace-app-information.png)

This screen offers a number of items to help you learn more about the app. You
can find the primary information about the app on the left side of the screen,
below the app's icon. In the center display, you see a set of screenshots along
with a description of the app. Beneath the screenshots, you can find a
description of the app. Below that, you can read reviews and view the version
history of the app. Each app provides at least the following information:

- Author: The creator of the app. This can be either an individual or a company.

- Rating: The average rating of the app on a scale from zero to five stars. The
  number of ratings is also shown.

- Latest Version: The latest released version of this app

- Total Downloads: The number of times the app has been downloaded

- Supported By: Who to contact if you need support for this app

- Developer Website: A link to the developer's own website

- Purchase/Free: The button to click on to purchase the app. You must purchase
  an app before you can download it.

In the lower section, you will find Reviews and Version History tabs for this
app. Check this area to find out what other people are saying about this app. In
addition, on a separate tab, you will find the history of versions for this app,
where you can download other versions (for example, if you are using an older
version of the Liferay Platform, you may need to download a specific version of
this app that is compatible with the version of the Liferay Platform you are
using).

## Downloading and Installing

You've chosen an app, read the reviews and want to download and use the app!
There are two ways to install the app. Ultimately, both methods result in the
same outcome: the app you've chosen is installed onto your local running Liferay
instance.

## Liferay Hot Deploy

Apps on the Liferay Marketplace consist of individual Liferay Plugins (for
example: a portlet, a hook, or a collection of multiple plugins). Ultimately,
these apps must be installed on a running Liferay instance before they can be
used. Deploying an app to a running Liferay instance is automatically done
through the process of *hot deploy*. When using your Liferay portal's *Control
Panel* to install apps from the Marketplace, when you click *Purchase* or
*Install* to download and use a given app, it will be downloaded and hot
deployed to your local running Liferay instance.

For some Liferay installations, the hot deploy mechanism is disabled, in order
for the site administrator to manage (or prevent) the deployment of plugins. In
this case, the app will still be downloaded and stored in the hot deploy
directory (with a `.lpkg` extension in its filename), but must be manually
deployed using the custom process used for deploying other plugins.

Please see the later section *Installing Plugins Manually* to learn more about
hot deploy, its behavior on various app servers, and how to manually deploy
Marketplace apps in situations where hot deploy cannot be used.

## Installing through the Control Panel

The easiest way to install an app is to do so from your Liferay Control Panel.
This requires that you have already installed Liferay on your local machine and
that you can log in as an administrator. Once you are logged in as an
administrator, click the *Admin* menu from the Dockbar and choose *Control
Panel*.

![Figure 14.12: As an administrator, you can access the Marketplace interface from the Control Panel of a running Liferay portal.](../../images/marketplace-control-panel-entries.png)
 
Click on either the *Store* or the *Purchased* links beneath the Apps heading.
Before you can access Marketplace via the Control Panel, you need to associate
your `liferay.com` login credentials with your local administrator account.
Enter your `liferay.com` email address and password so your Liferay installation
can connect to the `liferay.com` Marketplace.

![Figure 14.13: Before you can access Marketplace via the Control Panel you need to link your `liferay.com` credentials with your Liferay instance's administrator account.](../../images/marketplace-login.png)

---
 
 ![Tip](../../images/01-tip.png) Tip: Any local user with administrative
 privileges can use the Marketplace to browse and install apps from the
 Marketplace, by entering their `liferay.com` credentials in the above login
 screen. This allows multiple administrators to manage the apps installed on the
 local Liferay instance. Once a link is established between a local
 administrator account and a `liferay.com` account, there is no way to undo
 this, short of re-installing Liferay.

---
 
Once you've successfully linked the accounts, you will be presented with the
same Marketplace screens that you'd see if you were accessing the Marketplace
webpage via your browser. You will be able to browse, search, and install apps
directly from the Marketplace. Click on the *Store* link under the Apps heading
in the Control Panel to browse the apps available from the `liferay.com`
Marketplace. Browse to the app you wish to install, click on the *Purchase* or
*Free* button, and then click on the appropriate button to confirm your purchase
on the next screen. The app will be downloaded and deployed to your local
Liferay installation.
 
You can view and manage all the apps you've purchased by clicking on the
*Purchased* link under the Apps heading in the Control Panel. Clicking on the
*Purchased* link shows you a list of those apps which you've downloaded in the
past, including apps you may have purchased/downloaded while using other Liferay
installations.

![Figure 14.14: Purchased Apps](../../images/marketplace-purchased-apps-control-panel.png)
 
The apps which you downloaded and installed on the currently running instance of
Liferay are listed as Installed. Apps which you have previously downloaded or
purchased on other Liferay instances that are incompatible with the current one
are listed as Not Compatible. You need to re-download/re-install the appropriate
version of these apps if you wish to use them on your running instance of
Liferay.
 
## Downloading through liferay.com

The second way to install an app is to download it first, then in a separate
step, deploy it to your running Liferay instance. This is especially useful in
situations where you do not wish to deploy the app directly to your production
environment, or in cases where the target Liferay instance that is to receive
the app is behind a corporate firewall or otherwise does not have direct access
to the Marketplace.
 
In this case, using your browser, you will find the app on the [Liferay
Marketplace](https://www.liferay.com/marketplace). Once found, click on the
*Purchase* or *Free* button when viewing the individual app. This will cause
the app to be placed in your *Purchased* list on your personal Home page.
Navigate to your Home page and click on *Apps*. Each app is listed; click on
the one you just purchased, or whatever app you want to download. You'll find a
list of app versions and Liferay versions. Choose the version of the app you
want, making sure the Liferay version corresponds to the version of the Liferay
installation where you'll install the app. Click *App* and the app is
downloaded to your local machine in the same way any other file would be
downloaded. This file can then be hot-deployed to Liferay by copying it to
Liferay's hot deploy directory.
