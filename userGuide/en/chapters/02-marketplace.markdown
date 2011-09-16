# Liferay Marketplace

This chapter provides a quick tour of the new Liferay Marketplace.

## Introduction

Liferay Marketplace is an exciting new hub for sharing, browsing and downloading Liferay-compatible applications. As enterprises look for ways to build and enhance their existing platforms, developers and software vendors are searching for new avenues to reach this market. Marketplace leverages the entire Liferay ecosystem to release and share apps in a user-friendly, one-stop site.

In a nutshell, the Liferay Marketplace is a repository for applications built on the Liferay Platform.  You can find and download applications directly from the Marketplace on the web, or use an existing Liferay installation to access and install applications onto the running Liferay website.  Once installed, you can manage the applications through Liferay's administrative interfaces (such as the control panel).

## Accessing the Liferay Marketplace

### The Basics

There are two ways to access the Marketplace.

1. Via The Website - Using your favorite browser, you can navigate to the marketplace at [http://liferay.com/marketplace](http://liferay.com/marketplace).
2. Via Liferay - If you have a site up and running based on Liferay, you can use the Marketplace tab on the control panel to access Marketplace content.

No matter which method you choose, you will see the same content and apps.
 
If you are new to the Marketplace, the easiest way to access it is by using your browser to navigate to [http://liferay.com/marketplace](http://liferay.com/marketplace).  You will be presented with the Marketplace home page.

![Figure 2.1 Marketplace Home Page](../../images/marketplace-homepage.png) 
 
In the center of the page you will see a number of icons.  Each icon represents an individual app, and they are grouped into a couple of different areas:

* New To Marketplace - The latest apps that have been added to the marketplace
* Featured Products - Liferay features a different set of apps each month
* Top 10 - The top 10 apps as ranked by downloads, views, popularity, etc.

If you click on the title of an app, you can access details about the app, including a description, screenshots, price, and reviews and comments left by other users.
 
On the left, you'll see a list of App and Theme categories.  Clicking on the individual categories will allow you to browse apps in that category.

![Figure 2.2 Browsing Apps in Marketplace](../../images/marketplace-browsing-apps.png)

Below that are your search and filter options.  This allows you to narrowly search for apps with specific titles, compatibilities, or other search criteria.  Typing in search terms and clicking Search will perform the search.
 
At the bottom is a Tag Cloud.  Individual apps can be tagged with specific keywords or phrases for easier finding.  The Tag Cloud is a collection of these keywords.  The larger the keyword, the more popular the tag.  Clicking on a tag is the same as performing a search with the tag as the search criteria, and you will get a listing of apps using that tag.

![Figure 2.3 Marketplace Apps by Tag](../../images/marketplace-apps-by-tag.png)
 
### Logging In

You do not need a `liferay.com` account in order to browse the Marketplace.  Once you decide you wish to download an app (or if you are a developer and wish to create and upload new apps), you will need to establish a liferay`.com account, and agree to the Marketplace Terms of Use.  To get a new liferay.com` login, visit the register page and sign up!
 
Once you are signed in, you will be able to fully utilize the Marketplace to find and use Marketplace apps.
 
### Marketplace Profile

On your existing `liferay.com` Profile page, you will notice a new tab entitled "My Marketplace".  This tab shows you relevant information related to your usage of Marketplace.  There are three tabs that house information related to Marketplace
 
### My Apps

This page lists apps that you have authored and uploaded, showing you details such as the number of downloads, the current price, and other relevant information.  From here you can manage the apps that you have created (please see the Marketplace Developer Guide for detail on this topic).
 
![Figure 2.4 Purchased an App](../../images/marketplace-purchased-app.png)
 
This page lists the apps that you have downloaded and/or purchased.  From this screen, you can find information about the authors of the apps you have downloaded, and you can re-download the app (for example, if you lost your copy or have re-installed Liferay and wish to re-deploy the app).  This option is also useful for downloading apps and deploying them to offline instances of Liferay that do not have direct access to `liferay.com`.
 
![Figure 2.5 Upload an App](../../images/marketplace-upload-app.png)
 
This screen allows you to upload a new app and make it available in the marketplace.  Please see the Marketplace Developer Guide for more detail on authoring your own app.
 
## Finding Apps

There are many ways to search for and find apps you are interested in.

1. Browsing Categories - Click on a category (for example, "Communication" or "Social Networking") to see a list of interesting apps in that category.  Upon clicking a category, you are presented with a list of featured apps for that category, as well as a canonical listing of all apps.  Also, on the right, are various lists of "Top 10" apps within that category.

![Figure 2.6 Browsing Categories](../../images/marketplace-browsing-categories.png)
 
2. Searching.  To search for an app, type in search criteria in the search box on the left, and click Search.  Apps matching the specified search criteria are displayed.

![Figure 2.7 Marketplace Search Box](../../images/marketplace-search-box.png)

**A Note on Compatibility and Versioning**

### Versions

Apps are often updated to include new features or fix bugs.  Each time an app is updated, its version number is changed.  The version number is specified by the app developer, and often follows established norms, such as 1.0 -> 1.1 -> 2.0, or 1.0.1 -> 1.0.2 -> 1.2.0 and so on.  Generally, the "higher" the numbers, the younger the version.
 
When viewing individual apps, the list of versions of that app are displayed, along with a download button for each version.  In some cases, not all historical versions of apps are available, depending on the app. Usually you will want to download and install the latest available app for the version of Liferay you are using (See Compatibility below).
 
### Compatibility

Some apps are written to work across a wide range of Liferay Platform releases.  Others are dependent on a specific Liferay Platform release (or a handful of such releases).  When viewing individual apps, each version of the app that is available also describes the range of Liferay Platform versions the app is compatible with.  Make sure to choose a version of the app that is compatible with your Liferay Platform release.

## Downloading and Installing Apps
 
Once you have found an app you wish to download and use, click on the name of the app to display its detailed information screen.

![Figure 2.8 Detailed App Information](../../images/marketplace-app-information.png)

On this screen, there are a number of items to assist in learning more about the app.
 
In the center of the screen is the primary information about the app:

* Description - In the center display you see a set of screenshots along with a description of the app.  In addition, the set of tags associated with the app are displayed.
* Information - On the right, below the icon for the app, are a set of links to the Author's profile page, and a link to follow the author (and receive updates from this specific author when actions occur in the future).  In addition, small colored icons are shown, indicating what kinds of individual components make up this app: Portlets, Hooks, Themes, Layout Templates, Web Plugins, or EXT plugins.  

In addition to basic information, you will also find information on:

* Latest Version: The latest released version of this app
* Compatibility: The version(s) of the Liferay Platform that this app is compatible with (see Compatibility and Versioning for more detail)
* Support: Who to contact if you need support for this app
* Demo URL: If this app has an online demo available, you will find the link here.
* Developer Website: The main website where development or support for this app can be found
* Report issues/abuse: Helpful links to report additional issues related to this app

In the lower section, you will find Reviews and Ratings for this app.  Check here to find out what other people are saying about this app.  In addition, on a separate tab, you will find the history of versions for this app, where you can download other versions (for example, if you are using an older version of the Liferay Platform, you may need to download a specific version of this app that is compatible with the version of the Liferay Platform you are using).
 
![Figure 2.9 Detailed App Information](../../images/marketplace-app-version-history.png)

### Downloading and Installing

You've chosen an app, read the reviews, and want to download and use the app!  There are two ways to install the app.  Ultimately, both methods result in the same outcome: the app you've chose is installed onto your local running Liferay instance.
 
### Installing through Control Panel

The easiest way to install an app is to do so from your Liferay Administration console (*control panel*).  This requires that you have already setup a Liferay install on your local machine, and that you can login as an Administrator. 
 
Once you are logged in as an administrator, click the "Manage" menu at the top of the screen, and choose "Control Panel".  You are presented with the Liferay Administration console, a.k.a. Control Panel.

![Figure 2.10 Marketplace from the Control Panel](../../images/marketplace-control-panel.png)
 
Click on the "Marketplace" link at the upper-left.  Before you are able to access the Marketplace via the control panel, you will need to associate your `liferay.com` login credentials with your local administrator account.  You will be presented with a series of login screens.

![Figure 2.11 Marketplace Login Screen](../../images/marketplace-login.png)
 
**VERY IMPORTANT**: The local admin user you associate with your `liferay.com` account will be THE ONLY USER who will be able to access and install apps from the Marketplace.  Be sure you are logged into your local Liferay install using the account you wish to designate the Marketplace Administrator.  Once this linkage is established, there is no way to undo this, short of re-installing Liferay.
 
Once you've successfully linked the accounts, you will be presented with the same Marketplace screens as you would have if you were directly accessing the Marketplace.  You will be able to browse, search, and install directly from the Marketplace.  Browse to the app you wish to install, and click the INSTALL button.  The app will be downloaded and deployed to your local Liferay install.
 
All apps that you have installed are listed on the "Purchased" tab of the Marketplace Control Panel.  Clicking on the tab will show you a list of those apps which you have downloaded in the past, including apps you may have purchased/downloaded while using other Liferay installations.

![Figure 2.12 Purchased Apps](../../images/marketplace-purchased-apps.png)
 
For those apps which you downloaded and installed on the currently running instance of Liferay, these apps will show an Installed state.  For apps which you have previously downloaded or purchased on other Liferay instances, these will show as Purchased.  You will need to re-download/re-install these apps if you wish to use them on your currently running instance of Liferay.  Other options on this tab are related to the management of apps on they are installed, and are described in the later section "Managing Apps".
 
### Downloading through liferay.com

The second way to install an app is to download it first, then in a separate step, deploy it to your running Liferay instance.  This is especially useful in situations where you do not wish to deploy the app directly to your production environment, or in cases where the target Liferay instance that is to receive the app is behind a corporate firewall or otherwise does not have direct access to the Marketplace.
 
In this case, using your browser, you will find the app on `liferay.com/marketplace`.  Once found, click on the "Purchase/Free" button when viewing the individual app.  This will cause the app to be placed in your "Purchased" list on your Profile page.  Navigate to your profile page, click on "My Marketplace" and visit the "Purchased" tab.  Each app is listed.  Find the app that you just purchased and click Download.  The app is downloaded to your local machine in the same way that any other file would be downloaded.  This file can then be hot-deployed to Liferay by copying it to Liferay's hot deploy directory.  See the Administration Guide for more details on Hot Deploy.

## Managing Apps
 
Once you have installed one or more apps onto a Liferay instance, you can manage the apps using the same local Liferay account that was used to download and install Marketplace apps.  To see and manage the apps that you have installed, navigate to the control panel, click on the Marketplace tab, and then click on the "Purchased" tab.

![Figure 2.13 Additional Information for Purchased Apps](../../images/marketplace-purchased-apps-more-info.png)
 
This tab is similar to the "Purchased" tab on `liferay.com/marketplace`, except that additional options are now available for each app.
 
On the far right side of the listing, the "Status" column lists the current status for each installed app:
 

    Installed - The app has been successfully deployed and is now running.
    Failed - The app failed to deploy properly
    Disabled - The app has been successfully deployed, but has been disabled

If an app failed to deploy, check your log files and attempt to determine the cause.  To retry the install, click "Retry Install".
 
If a newer version of an app is available, you will see an "Update" tab - click this to update the app to the latest available version.
 
To disable an app, click "Disable".  The app will be disabled and any services it provides will not be available, but the app remains installed.  You can re-enable the app by clicking "Enable".
 
## Creating and Uploading Apps
 
Creating apps for the Liferay Marketplace is very easy and intuitive.  To find out more information about creating your own Liferay apps, visit the Liferay Marketplace Developer Guide and get started creating apps today!