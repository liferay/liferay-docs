# Liferay Marketplace

Liferay Marketplace is an exciting new hub for sharing, browsing and downloading Liferay-compatible applications. As enterprises look for ways to build and enhance their existing platforms, developers and software vendors are searching for new avenues to reach this market. Marketplace leverages the entire Liferay ecosystem to release and share apps in a user-friendly, one-stop site.

This chapter covers the following topics:

- Users, Companies, and Apps
- Accessing Liferay Marketplace
- Finding, Downloading, and Installing Apps
- Managing Apps
- Plugins and Plugin Management
- Portlets, Themes, Layout Templates, Hooks, and Web Plugins
- Installing Plugins from Repositories
- Installing Plugins Manually
- Plugin Troubleshooting and Configuration Issues
- The Software Catalog

In a nutshell, the Liferay Marketplace is a repository for applications built on the Liferay Platform. You can find and download applications directly from the Marketplace on the web, or use an existing Liferay installation to access and install applications onto the running Liferay web site. Once installed, you can manage the applications through Liferay's control panel. 

## Marketplace Concepts: Users, Companies, and Apps

Anyone can browse the apps available on Liferay Marketplace at [http://liferay.com/marketplace](http://liferay.com/marketplace) but a `liferay.com` user account is required for purchasing and downloading apps, whether from the Marketplace website or from an existing Liferay installation. Many official Liferay apps, as well as some third party apps, are available free of charge. Other apps require you to pay a fee in order to access them. When you purchase an app, you can do so on your own behalf or on behalf of a company. Apps purchased on your own behalf are associated with your personal `liferay.com` user account. Apps purchased on behalf of a company are associated with that company's account and can be accessed by any users that belong to that company. Once you've purchased an app, you're free to download and install any available version of the app whenever you like. We'll explain how to set up a company account, manage company apps, and join companies after we discuss how to access Liferay Marketplace.

## Accessing the Liferay Marketplace

There are two ways to access the Marketplace.

1. Via the website--Using your favorite browser, you can navigate to the marketplace at [http://liferay.com/marketplace](http://liferay.com/marketplace).

2. Via Liferay--If you have a site up and running based on Liferay, you can use the Marketplace section of the control panel to access Marketplace content.

### The Basics

No matter which method you choose, you will see the same content and apps.
 
If you are new to the Marketplace, the easiest way to access it is by using your browser to navigate to [http://liferay.com/marketplace](http://liferay.com/marketplace). You will be presented with the Marketplace home page.

![Figure 9.1: Marketplace Home Page](../../images/marketplace-homepage.png) 
 
In the center of the page, you will see a number of icons. Each icon represents an individual app and they are grouped into a couple of different areas:

* New To Marketplace: The latest apps that have been added to the marketplace
* Featured Products: Liferay features a different set of apps each month
* Most Viewed: The top 10 most viewed apps

If you click on the title of an app, you can access details about the app, including a description, the author's name, screenshots, price, latest version, number of users, number of weekly installs, a link to the developer's `liferay.com` profile page, a link to the developer's own website, a link to report abuse, and a Purchase button. You'll also be able to view version history, read reviews left by other users, or write your own review. 

![Figure 9.x: Marketplace App Details](../../images/marketplace-app-details.png) 

If you click on the Purchase button, you'll be prompted to choose a purchase type. You can purchase an app for your personal account or for your company. If your company has already been registered, select it in the drop-down list. If you'd like to purchase an app for your company, but it hasn't been registered yet, you can click the *Register Your Company* link to register your company with Liferay Marketplace. Please see the *Creating a Company* section below for details.
 
The left side of each page in the [http://liferay.com/marketplace](http://liferay.com/marketplace) site contains a Marketplace navigation menu. This menu contains links to various categories of apps and themes that are available from Marketplace. Clicking on the individual categories will allow you to browse apps in that category. To view all apps or themes at once, click the *Apps* or *Themes* link and then the *See All* link in the Apps or Themes portlet.

![Figure 9.2: Browsing Apps in Marketplace](../../images/marketplace-browsing-apps.png)

Below the navigation menu is the search bar. This allows you to search for apps with specific titles. Type in search terms and click *Search* to perform the search.
 
### Logging In

You do not need a `liferay.com` account in order to browse the Marketplace. Once you decide you wish to purchase and download an app (or if you are a developer and wish to create and upload new apps), you will need to establish a `liferay.com` account, and agree to the Marketplace Terms of Use. To get a new `liferay.com` login, visit the register page and sign up! Once you are signed in, you will be able to fully utilize the Marketplace to find and use Marketplace apps.
 
### Marketplace Profile

On your existing `liferay.com` Home page, you'll notice a new link entitled *App Manager*. The App Manager allows you to access information about the apps you've purchased and apps you've uploaded to Marketplace. It also provides a mechanism for uploading new apps to Marketplace. Once you submit your app, it will be reviewed before appearing on the `liferay.com` Marketplace.

---

![tip](../../images/01-tip.png) Note: Your `liferay.com` Home page is a private page. It's distinct from your public `liferay.com` Profile page.

---

### Managing Apps

The App Manager shows you relevant information related to your usage of Marketplace. You'll find three tabs to help you manage your Marketplace apps: *Purchased*, *Apps*, and *Add an App*.

The Purchased tab lists the apps that you have purchased. From this screen, you can find information about the authors of the apps you have purchased. You can also download or re-download the app (for example, if you lost your copy or have re-installed Liferay and wish to re-deploy the app). This option is also useful for downloading apps and deploying them to offline instances of Liferay that do not have direct access to `liferay.com`.

![Figure 9.4: Purchased Apps](../../images/marketplace-purchased-apps.png)

If possible, it's best to log into your portal instance and install purchased applications through the My Marketplace link in the control panel. This will provide your portal instance with automatic update notices should they become available. If you download applications this way, they can be hot-deployed.
 
The Apps tab lists apps that you have authored and uploaded, showing you details such as the number of downloads, the current price, and other relevant information. From here you can manage the apps that you have created (please see the Marketplace chapter of the Developer Guide for details on this topic).
 
![Figure 9.5: Upload an App](../../images/marketplace-upload-app.png)
 
Clicking on *Add an App* allows you to upload a new app and make it available in the marketplace. Please see the Marketplace Developer Guide for more detail on authoring your own app.

### Creating a Company

To create and register a company with Liferay Marketplace, click the *Create a Company* link in the left-hand navigation menu. Your first step is check to if your company already exists on Liferay Marketplace. Enter your company name into the search box and check if it's already been registered. If someone else from your company has already created a company account on Liferay Marketplace, you can click the *Request to Join this Company* button. This will send an email notification to your company's Marketplace admin (the one who created your company's Marketplace account). Your company's Marketplace admin will then be able to add you to the company. If the company name you'd like to use is available, click the *Register Your Company* button to move on to the next step. 

![Figure 9.5: Creating a Company](../../images/marketplace-creating-new-company.png)

Your second step is to fill out your company's information. The public information you must provide includes a company logo, the company name, a company description, a company email address, and a homepage URL. The private information you must provide includes a company address, your company's country, region, city, postal code, and phone number. Additonal private information required for validation includes a company email address and a legal tax document. Once your company's Marketplace registration has been approved, you be your company's Marketplace admin! This means that you'll be responsible for handling Marketplace users' requests to join your company. Don't worry, you don't have to be stuck with this responsibility. Once you've added other users to your company, you can promote some of them to be company Marketplace admins too.

## Finding Apps

There are several ways to search for and find apps you are interested in.

1. Browsing Categories. Click on a category (for example, *Communication* or *Productivity*) to see a list of interesting apps in that category. Upon clicking a category, you are presented with a list of featured apps for that category, as well as a canonical listing of all apps. Also, on the right, are lists of the Most Viewed apps within that category.

![Figure 9.6: Browsing Categories](../../images/marketplace-browsing-categories.png)
 
2. Searching. To search for an app, type in search criteria in the search box under the navigation menu on the left, and click *Search*. Apps matching the specified search criteria are displayed.

![Figure 9.7: Marketplace Search Box](../../images/marketplace-search-box.png)

### Versions

Apps are often updated to include new features or fix bugs. Each time an app is updated, its version number is changed. The version number is specified by the app developer, and often follows established norms, such as 1.0 &rarr; 1.1 &rarr; 2.0, or 1.0.1 &rarr; 1.0.2 &rarr; 1.2.0 and so on. Generally, the higher the numbers, the younger the version.
 
When viewing an app's details, the click on the *Version History* tab to see a list of versions of the app. In some cases, not all historical versions of apps are available, depending on the app. Usually you will want to download and install the latest available app for the version of Liferay you are using (See Compatibility below).
 
### Compatibility

Some apps are written to work across a wide range of Liferay Platform releases. Others are dependent on a specific Liferay Platform release (or a handful of such releases). When viewing individual apps, each version of the app that is available also describes the range of Liferay Platform versions the app is compatible with. Make sure to choose a version of the app that is compatible with your Liferay Platform release.

![Figure 9.7: Marketplace App Version History](../../images/marketplace-app-version-history.png)

To check if an app is compatible with your version of Liferay, click on the App and then click on the *Version History* tab. The Version History tab displays not only the list of versions of the app but also the app's supported framework versions and the dates of each version of the app. The supported framework version of the app tells you whether or not the app is compatible with your version of Liferay.

## Downloading and Installing Apps
 
Once you've found an app you wish to download and use, click on the name of the app to display its detailed information screen.

![Figure 9.8: Detailed App Information](../../images/marketplace-app-information.png)

On this screen, there are a number of items to assist in learning more about the app. You can find the primary information about the app on the left side of the screen. In the center display, you see a set of screenshots along with a description of the app. On the left, below the icon for the app, is some basic information about the app.

* Author: The creator of the app
* Rating: The average rating of the app on a scale from zero to five stars. The number of ratings is also shown.
* Latest Version: The latest released version of this app
* Supported By: Who to contact if you need support for this app
* Users: The total number of users of the app
* Weekly Installs: The average number of installations per week
* Developer Profile: A link to the app developer's `liferay.com` profile page
* Developer Website: A link to the developer's own website
* Purchase: The button to click on to purchase the app. You must purchase an app before you can download it.

In the lower section, you will find Reviews and Version History tabs for this app. Check here to find out what other people are saying about this app. In addition, on a separate tab, you will find the history of versions for this app, where you can download other versions (for example, if you are using an older version of the Liferay Platform, you may need to download a specific version of this app that is compatible with the version of the Liferay Platform you are using).
 
![Figure 9.9: Detailed App Information](../../images/marketplace-app-version-history-and-reviews.png)

### Downloading and Installing

You've chosen an app, read the reviews, and want to download and use the app! There are two ways to install the app. Ultimately, both methods result in the same outcome: the app you've chosen is installed onto your local running Liferay instance.
 
### Installing through the Control Panel

The easiest way to install an app is to do so from your Liferay control panel. This requires that you have already installed Liferay on your local machine, and that you can log in as an administrator. Once you are logged in as an administrator, click the *Go to* menu on the Dockbar and choose *Control Panel*.

![Figure 9.10: Marketplace from the Control Panel](../../images/marketplace-control-panel-entries.png)
 
Click on either the *Store* or the *Purchased* link in the Marketplace category at the upper-left. Before you can access Marketplace via the control panel, you need to associate your `liferay.com` login credentials with your local administrator account. Enter your `liferay.com` email address and password so that your Liferay installation can connect to the `liferay.com` Marketplace.

![Figure 9.11: Marketplace Login Screen](../../images/marketplace-login.png)
 
**VERY IMPORTANT**: The local admin user you associate with your `liferay.com` account will be THE ONLY USER who will be able to access and install apps from the Marketplace. Be sure you are logged into your local Liferay installation using the account you wish to designate the Marketplace Administrator. Once this linkage is established, there is no way to undo this, short of re-installing Liferay.
 
Once you've successfully linked the accounts, you will be presented with the same Marketplace screens as you would have if you were directly accessing the Marketplace. You will be able to browse, search, and install directly from the Marketplace. Click on the *Store* link under the Marketplace heading in the control panel to browse the apps available from the `liferay.com` Marketplace. Browse to the app you wish to install, click the *Purchase* button, and then the *Buy* button on the next screen. The app will be downloaded and deployed to your local Liferay installation.
 
All apps that you've bought are listed on the *Purchased* page of the Marketplace control panel. Clicking on the *Purchased* link will show you a list of those apps which you have downloaded in the past, including apps you may have purchased/downloaded while using other Liferay installations.

![Figure 9.12: Purchased Apps](../../images/marketplace-purchased-apps-control-panel.png)
 
The apps which you downloaded and installed on the currently running instance of Liferay are listed as Installed. Apps which you have previously downloaded or purchased on other Liferay instances that are incompatible with the current one are listed as Not Compatible. You need to re-download/re-install the appropriate version of these apps if you wish to use them on your running instance of Liferay.
 
### Downloading through liferay.com

The second way to install an app is to download it first, then in a separate step, deploy it to your running Liferay instance. This is especially useful in situations where you do not wish to deploy the app directly to your production environment, or in cases where the target Liferay instance that is to receive the app is behind a corporate firewall or otherwise does not have direct access to the Marketplace.
 
In this case, using your browser, you will find the app on `liferay.com/marketplace`. Once found, click on the *Purchase* button when viewing the individual app. This will cause the app to be placed in your *Purchased* list on your personal Home page. Navigate to your Home page, click on *App Manager* and visit the *Purchased* tab. Each app is listed. Find the app that you just purchased and click *Downloads*. You'll find a list of app versions and Liferay versions. Choose the version of the app that you want, making sure the that Liferay version corresponds to the version of the Liferay installation into which you'd like to install the app. Click *Download App* and the app is downloaded to your local machine in the same way that any other file would be downloaded. This file can then be hot-deployed to Liferay by copying it to Liferay's hot deploy directory.

## Creating and Uploading Apps
 
Creating apps for the Liferay Marketplace is very easy and intuitive. To find out more information about creating your own Liferay apps, visit the Liferay Marketplace Developer Guide and get started creating apps today!

## Summary

In this chapter we introduced Liferay Marketplace, the portal's system for managing Liferay applications. We looked at how to browse, purchase, download, and install apps. You can do this either through [liferay.com/marketplace](liferay.com/marketplace) or through Liferay Portal's control panel. When you purchase apps, you can do so via your personal account or on your company's behalf. For information about developing and uploading apps to Liferay Marketplace, please see the Marketplace chapter of the Liferay Developer guide at [http://www.liferay.com/marketplace/developer-guide](http://www.liferay.com/marketplace/developer-guide).
