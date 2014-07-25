
# Leveraging the Liferay Marketplace [](id=leveraging-the-liferay-marketplace-liferay-portal-6-2-user-guide-14-en)

Liferay Marketplace is an exciting hub for sharing, browsing and downloading
Liferay-compatible applications. As enterprises look for ways to build and
enhance their existing platforms, developers and software vendors are searching
for new avenues to reach this market. Marketplace leverages the entire Liferay
ecosystem to release and share apps in a user-friendly, one-stop site.

This chapter covers the following topics:

- Users, Companies, and Apps

- Accessing Liferay Marketplace

- Finding, Downloading, and Installing Apps

- Managing Apps

- Plugins and Plugin Management

- Plugin Types: Portlets, Themes, Layout Templates, Hooks, Web Plugins, and OSGi
  Bundles

- Installing Plugins from Repositories

- Installing Plugins Manually

- Plugin Troubleshooting and Configuration Issues

In a nutshell, the Liferay Marketplace is a repository for applications built on
the Liferay Platform. You can find and download applications directly from the
Marketplace on the web or use an existing Liferay installation to access and
install applications onto the running Liferay web site. Once installed, you can
manage the applications through Liferay's Control Panel. 

## Marketplace Concepts: Users, Companies, and Apps [](id=marketplace-concepts-users-companies-an-liferay-portal-6-2-user-guide-14-en)

Anyone can browse the apps available on Liferay Marketplace at
[http://liferay.com/marketplace](http://liferay.com/marketplace), but a
`liferay.com` user account is required for purchasing and downloading apps.
You can browse, purchase, and download apps from the Marketplace website or
from a running Liferay portal. Accessing Liferay Marketplace from a running
Liferay portal makes it very easy to download and install apps. Once an app has
been purchased, it takes just one click to download the app and one click to
install it. Updating or uninstalling the app are also one-click processes. A
`liferay.com` user account is required regardless of whether you access Liferay
Marketplace via the browser or via an existing Liferay installation.

Many official Liferay apps, as well as some third party apps, are available free
of charge. Other apps require you to pay a fee in order to access them. When you
purchase an app, you can do so on your own behalf or on behalf of a company.
Apps purchased on your own behalf are associated with your personal
`liferay.com` user account. Apps purchased on behalf of a company are associated
with the company's account and can be accessed by any users who belong to the
company. Once you've purchased an app, you're free to download and install any
available version of the app whenever you like. We'll explain how to set up a
company account, manage company apps and join companies after we discuss how to
access Liferay Marketplace.

## Accessing the Liferay Marketplace [](id=accessing-the-liferay-marketplace-liferay-portal-6-2-user-guide-14-en)

There are two ways to access the Marketplace.

1. Via the website: Using your favorite browser, you can navigate to the
   marketplace at
   [http://liferay.com/marketplace](http://liferay.com/marketplace).

2. Via Liferay: If you have a Liferay portal up and running, you can use the
   Apps section of the Control Panel to access Marketplace content.

### The Basics [](id=the-basics-liferay-portal-6-2-user-guide-14-en)

No matter which method you choose, you will see the same content and apps.
 
If you are new to the Marketplace, the easiest way to access it is by using your
browser. Navigate to
[http://liferay.com/marketplace](http://liferay.com/marketplace) to access the
Marketplace home page.

![Figure 14.1: You can access and browse the Marketplace home page without a `liferay.com` account but you need an account in order to purchase and download apps.](../../images/marketplace-homepage.png) 
 
In the center of the page, you will see a number of icons. Each icon represents
an individual app, and apps are organized by category:

* New and Interesting: The latest apps added to the marketplace

* Featured apps: Liferay features a different set of apps each month

* Most Viewed: The top 10 most viewed apps

If you click on the title of an app, you can access details about the app,
including a description, the author's name, screenshots, price, latest version,
number of downloads, a link to the developer's website, a link to the license
agreement for the app, a link to report abuse, and a Purchase button. You'll
also be able to view version history, read reviews left by other users, or write
your own review. 

![Figure 14.2: Click on a Marketplace app to view details, ratings, and reviews of the app.](../../images/marketplace-app-details.png) 

If you click on the Purchase button, you'll be prompted to choose a purchase
type. You can purchase an app for your personal account or for your company. If
your company has already been registered, select it in the drop-down list. If
your company hasn't been registered, click the *Register Your Company* link to
register your company with Liferay Marketplace. Please see the *Creating a
Company* section below for details.
 
The left side of each page in the
[http://liferay.com/marketplace](http://liferay.com/marketplace) site contains a
Marketplace navigation menu. This menu contains links to various categories of
apps available from Marketplace. Clicking on the individual categories allows
you to browse the apps in that category. To view all the apps on Marketplace at
once, click the *Apps* link and then the *See All* link in the Apps portlet.

![Figure 14.3: Click on the *Apps* link in the menu on the left side of the Liferay Marketplace homepage and then on the *See All* link in the Apps portlet to browse all the Marketplace apps at once.](../../images/marketplace-browsing-apps.png) 

Below the navigation menu is the search bar. This search checks the titles and
descriptions of apps against your search terms.

### Logging In [](id=logging-in-liferay-portal-6-2-user-guide-14-en)

You do not need a `liferay.com` account in order to browse the Marketplace.
However, if you wish to purchase and download an app, you need to establish a
`liferay.com` account and agree to the Marketplace Terms of Use. To get a new
`liferay.com` account, visit [http://www.liferay.com](http://www.liferay.com),
click *Sign In*, then *Create Account*, and sign up! Once you are signed in,
you can fully utilize the Marketplace to find and use Marketplace apps.

If you are a developer and wish to create and upload new apps, you must also
register as a developer in Marketplace. Just go to
[http://liferay.com/marketplace](http://liferay.com/marketplace) and click
*Become a Developer* from the *Marketplace* menu.
 
![Figure 14.4: Click on the *Become a Developer* link in the menu on the left side of the Liferay Marketplace homepage to register a developer's account, either as an individual or a company.](../../images/marketplace-registration.png) 

Once you register as a developer, your *liferay.com* homepage will
have links for adding your apps to Marketplace, and managing them after they're
uploaded. 

### Marketplace Profile [](id=marketplace-profile-liferay-portal-6-2-user-guide-14-en)

Once you register a developer account with your `liferay.com` account, your
existing `liferay.com` Home page will contain a new section in the left
navigation menu entitled *Development*. You now have two *Apps* links: one under
the main navigation menu and one in the *Development* section. The *Apps* link
under your name is where you go when you want to vies and download yuor
purchased apps from the Marketplace. The *Apps* link under the *Development*
menu will let you manage existing apps and add new apps to the Marketplace. To
see information on your app's performance, use the *Metrics* link in the
Development menu. If you submit an app, it will be reviewed before appearing on
the `liferay.com` Marketplace.

---

 ![Tip](../../images/01-tip.png) Your `liferay.com` Home page is a private page.
 It's distinct from your public `liferay.com` Profile page.

---

### Managing Apps [](id=managing-apps-liferay-portal-6-2-user-guide-14-en)

The *Develoment* menu in your `liferay.com` home page is where you'll go to
manage and monitor your own marketplace apps, and the *Apps* link in your main
navigation menu lets you view and download apps you purchased from the
Marketplace.

The Apps link in the menu under your name lists your purchased apps.  From this
screen, you can find information about the authors of the apps you have
purchased and download or re-download the app (for example, if you lost your
copy or have re-installed Liferay and wish to re-deploy the app).  This option
is also useful for downloading apps and deploying them to offline instances of
Liferay that do not have direct access to `liferay.com`.

![Figure 14.5: On your `liferay.com` home page, the *Apps* link in the left navigation menu lets you view and download your purchased apps.](../../images/marketplace-purchased-apps.png)

If possible, it's best to log into your portal instance and install purchased
applications through the *Store* link in the Control Panel's *Apps* section.
This will provide your portal instance with automatic update notices should
they become available. If you download applications this way, they can be
hot-deployed.
 
The *Development* menu is where developers go to manage their Marketplace apps.
The Apps link lists apps you have authored and uploaded, and gives you an *Add
New App* link that let's you submit an app to be added to the Marketplace. The
Metrics link will show you details such as the number of downloads, the current
price and other relevant information. Please see the 
[Marketplace chapter of the Developer Guide](https://www.liferay.com/documentation/liferay-portal/6.2/development/-/ai/liferay-marketplace-liferay-portal-6-2-dev-guide-11-en) 
for details on this topic.
 
![Figure 14.6: This is part of the form you need to submit when submitting an app to Liferay Marketplace.](../../images/marketplace-upload-app.png)
 
Clicking on *Add an App* allows you to upload a new app and make it available in
the marketplace. Please see the [Marketplace chapter of the Developer Guide](https://www.liferay.com/documentation/liferay-portal/6.2/development/-/ai/liferay-marketplace-liferay-portal-6-2-dev-guide-11-en) 
for more detail on authoring your own app.

### Creating a Company [](id=creating-a-company-liferay-portal-6-2-user-guide-14-en)

To create and register a company with Liferay Marketplace, click the *Become a
Developer* link in the left-hand navigation menu of the Marketplace home page.
You can create an individual or a company developer account. If you'd like to
create an account on behalf of your company, your first step is to see if your
company already exists on Liferay Marketplace. Enter your company name into the
search box and check if it's already been registered. If someone else from your
company has already created a company account on Liferay Marketplace, you can
click the *Request to Join this Company* button. This will send an email
notification to your company's Marketplace admin (the one who created your
company's Marketplace account). Your company's Marketplace admin will then be
able to add you to the company. If the company name you'd like to use is
available, click the *Register Your Company* button to move on to the next step. 

![Figure 14.7: You can upload apps as an individual or on behalf of your company.](../../images/marketplace-creating-new-company.png)

Your second step is to fill out your company's information. The public
information you must provide includes a company logo, the company name, a
company description, a company email address and a homepage URL. The private
information you must provide includes a company address, your company's country,
region, city, postal code and phone number. Additional private information
required for validation includes a company email address and a legal tax
document. Once your company's Marketplace registration has been approved, you
will be your company's Marketplace admin! This means you'll be responsible for
handling Marketplace users' requests to join your company. Don't worry, you
don't have to be stuck with this responsibility. Once you've added other users
to your company, you can promote some of them to be company Marketplace admins
too.

## Finding Apps [](id=finding-apps-liferay-portal-6-2-user-guide-14-en)

There are several ways to search for and find apps you are interested in.

1. Browsing Categories: Click on a category (for example, *Communication* or
   *Productivity*) to see a list of interesting apps in that category. Upon
   clicking a category, you are presented with a list of featured apps for that
   category, as well as a canonical listing of all apps. Also, on the right, are
   lists of the Most Viewed apps within that category.

    ![Figure 14.8: When searching for apps for your portal, you can browse through the complete list of Marketplace apps or browse by category.](../../images/marketplace-browsing-categories.png)

2. Searching: To search for an app, type in search criteria in the search box
   under the navigation menu on the left and click *Search*. Apps matching the
   specified search criteria are displayed.

    ![Figure 14.9: You can search for apps using the search box in the navigation menu. Results are returned if your search terms match an app's title or description.](../../images/marketplace-search-box.png)

Next, let's talk about app versioning.

### Versions [](id=versions-liferay-portal-6-2-user-guide-14-en)

Apps are often updated to include new features or fix bugs. Each time an app is
updated, its version number is changed. The version number is specified by the
app developer and often follows established norms, such as 1.0 &rarr; 1.1 &rarr;
2.0 or 1.0.1 &rarr; 1.0.2 &rarr; 1.2.0 and so on. Generally, the higher the
numbers, the newer the version.
 
When viewing an app's details, click on the *Version History* tab to see a list
of versions of the app. In some cases, not all historical versions of apps are
available, depending on the app. Usually, you will want to download and install
the latest available app for the version of Liferay you are using (See
Compatibility below).
 
### Compatibility [](id=compatibility-liferay-portal-6-2-user-guide-14-en)

Some apps are written to work across a wide range of Liferay Platform releases.
Others are dependent on a specific Liferay Platform release (or a handful of
such releases). When viewing individual apps, each version of the app that is
available also describes the range of Liferay Platform versions the app is
compatible with. Make sure to choose a version of the app that is compatible
with your Liferay Platform release.

![Figure 14.10: Look at an app's supported framework versions to check if it's compatible with your version of Liferay.](../../images/marketplace-app-version-history.png)

To check if an app is compatible with your version of Liferay, click on the App
and then click on the *Version History* tab. The Version History tab displays
not only the list of versions of the app but also the app's supported framework
versions and the dates of each version of the app. The supported framework
version of the app tells you whether or not the app is compatible with your
version of Liferay.

## Downloading and Installing Apps [](id=downloading-and-installing-apps-liferay-portal-6-2-user-guide-14-en)
 
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

### Downloading and Installing [](id=downloading-and-installing-liferay-portal-6-2-user-guide-14-en)

You've chosen an app, read the reviews and want to download and use the app!
There are two ways to install the app. Ultimately, both methods result in the
same outcome: the app you've chosen is installed onto your local running Liferay
instance.

### Liferay Hot Deploy [](id=liferay-hot-deploy-liferay-portal-6-2-user-guide-14-en)

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

### Installing through the Control Panel [](id=installing-through-the-control-panel-liferay-portal-6-2-user-guide-14-en)

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
 
### Downloading through liferay.com [](id=downloading-through-liferay-com-liferay-portal-6-2-user-guide-14-en)

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

## Creating and Uploading Apps [](id=creating-and-uploading-apps-liferay-portal-6-2-user-guide-14-en)
 
Creating apps for the Liferay Marketplace is very easy and intuitive. To find
out more information about creating your own Liferay apps, visit the 
[Liferay Marketplace Developer Guide](https://www.liferay.com/documentation/liferay-portal/6.2/development/-/ai/liferay-marketplace-liferay-portal-6-2-dev-guide-11-en) and get started creating apps today!

Next, we'll discuss general Liferay plugin management. We'll explain the
differences between the various types of Liferay plugins and show how to
manually deploy plugins to Liferay.

## Plugin Management [](id=plugin-management-liferay-portal-6-2-user-guide-14-en)

One of the primary ways of extending the functionality of Liferay Portal is by
the use of plugins. *Plugin* is an umbrella term for installable portlet, theme,
layout template, hook, Ext and web module Java EE `.war` files. Though Liferay
comes bundled with a number of functional portlets, themes, layout templates,
hooks and web modules, plugins provide a means of extending Liferay to be able
to do almost anything.

### Portlets [](id=portlets-liferay-portal-6-2-user-guide-14-en)

Portlets are small web applications that run in a portion of a web page. The
heart of any portal implementation is its portlets, because all of the
functionality of a portal resides in its portlets. Liferay's core is a portlet
container. The container's job is to manage the portal's pages and to aggregate
the set of portlets that are to appear on any particular page. This means the
core doesn't contain application code. Instead, all of the features and
functionality of your portal application must reside in its portlets.

---

 ![Tip](../../images/tip.png) **Tip:** Liferay 4.4.2 and below support the
 Portlet 1.0 standard: JSR-168. Liferay 5.0 and above support the Portlet 2.0
 standard: JSR-286. You cannot run Portlet 2.0 portlets in Liferay 4.4.2, but
 because the Portlet 2.0 standard is backwards-compatible, portlets written to
 the 1.0 standard still run in Liferay 5.x and above.

---

Portlet applications, like servlet applications, have become a Java standard
which various portal server vendors have implemented. The JSR-168 standard
defines the portlet 1.0 specification and the JSR-286 standard defines the
portlet 2.0 specification. A Java standard portlet should be deployable on any
portlet container which supports the standard. Portlets are placed on the page
in a certain order by the end user and are served up dynamically by the portal
server. This means certain *givens* that apply to servlet-based projects, such
as control over URLs or access to the `HttpServletRequest` object, don't apply
in portlet projects, because the portal server generates these objects
dynamically. 

Portal applications come generally in two flavors: 1) portlets can be written to
provide small amounts of functionality and then aggregated by the portal server
into a larger application or 2) whole applications can be written to reside in
only one or a few portlet windows. The choice is up to those designing the
application. The developer only has to worry about what happens inside of the
portlet itself; the portal server handles building out the page as it is
presented to the user.

Most developers nowadays like to use certain frameworks to develop their
applications, because those frameworks provide both functionality and structure
to a project. For example, Struts enforces the Model-View-Controller design
pattern and provides lots of functionality, such as custom tags and form
validation, that make it easier for a developer to implement certain standard
features. With Liferay, developers are free to use all of the leading frameworks
in the Java EE space, including Struts, Spring MVC and Java Server Faces. This
allows developers familiar with those frameworks to more easily implement
portlets and also facilitates the quick porting of an application using those
frameworks over to a portlet implementation.

Additionally, Liferay allows for the consuming of PHP and Ruby applications as
portlets so you do not need to be a Java developer in order to take advantage of
Liferay's built-in features (such as user management, sites, organizations, page
building and content management). You can also use scripting languages such as
Groovy if you wish. You can use the Plugins SDK to deploy your PHP or Ruby
application as a portlet and it will run seamlessly inside of Liferay. We have
plenty of examples of this; to see them, check out the Plugins SDK from
Liferay's public code repository.

Does your organization make use of any Enterprise Planning (ERP) software that
exposes its data via web services? You could write a portlet plugin for Liferay
that can consume that data and display it as part of a dashboard page for your
users. Do you subscribe to a stock service? You could pull stock quotes from
that service and display them on your page, instead of using Liferay's built-in
Stocks portlet. Do you have a need to combine the functionality of two or more
servlet-based applications on one page? You could make them into portlet plugins
and have Liferay display them in whatever layout you want. Do you have existing
Struts, Spring MVC or JSF applications you want to integrate with your portal?
It is a straightforward task to migrate these applications into Liferay, then
they can take advantage of the layout, security and administration
infrastructure that Liferay provides.

### Themes [](id=themes-liferay-portal-6-2-user-guide-14-en)

![Figure 14.15: Envision Theme from Liferay's Theme Repository](../../images/marketplace-envision-theme.png)

Themes are hot deployable plugins which can completely transform the look and
feel of the portal. Most organizations have their own look and feel standards
which go across all of the web sites and web applications in the infrastructure.
Liferay makes it possible for a site designer to create a theme plugin which can
be installed, allowing for the complete transformation of the portal to whatever
look and feel is needed. There are lots of available theme plugins on Liferay's
web site and more are being added every day. This makes it easier for those who
wish to develop themes for Liferay, as you can now choose a theme which most
closely resembles what you want to do and then customize it. This is much easier
than starting a theme from scratch. You can learn more about theme development
in [*Liferay in Action*](http://manning.com/sezov) or the [Liferay Development
Guide](https://www.liferay.com/documentation/liferay-portal/6.2/development).

![Figure 14.16: Murali Theme from Liferay's Theme Repository](../../images/marketplace-murali-theme.png)

### Layout Templates [](id=layout-templates-liferay-portal-6-2-user-guide-14-en)

Layout Templates are ways of choosing how your portlets will be arranged on a
page. They make up the body of your page, the large area into which you can drag
and drop portlets. Liferay Portal comes with several built-in layout templates.
If you have a complex page layout (especially for your home page), you may wish
to create a custom layout template of your own. This is covered in [*Liferay in Action*](http://manning.com/sezov) and the [Liferay Development Guide](https://www.liferay.com/documentation/liferay-portal/6.2/development).

### Hook Plugins [](id=hook-plugins-liferay-portal-6-2-user-guide-14-en)

Hook plugins were introduced with Liferay 5.2. As the name implies, they allow
"hooking" into Liferay's core functionality. This means they enable developers
to override or replace functionality that is in the core of the system. You can
hook into the eventing system, model listeners and portal properties. You can
also override Liferay's core JSPs with your own. Hooks are very powerful and
have been designed to replace most of the reasons for using the extension
environment with something that is easier to use and hot deployable.

### Web Plugins [](id=web-plugins-liferay-portal-6-2-user-guide-14-en)

Web plugins are regular Java EE web modules designed to work with Liferay.
Liferay supports integration with various Enterprise Service Bus (ESB)
implementations, as well as Single Sign-On implementations, workflow engines,
and so on. These are implemented as web modules used by Liferay portlets to
provide functionality.

### OSGi Bundles

OSGi (Open Services Gateway initiative) is a framework for developing modular
Java applications. Liferay 6.2 introduced support for the OSGi module framework.
It hosts an OSGi runtime and allows administrators to deploy OSGi bundles to
Liferay. OSGi web application bundles are very similar to standard web
application archives except that they must include additional metadata so that
they can operate in an OSGi framework. The OSGi specification does not require a
specific file extension for web application bundles but they typically have a
`.jar` file extension. Sometimes a `.war` file extension is used instead.

---

 ![Tip](../../images/tip.png) **Note:** Liferay 6.2's OSGi runtime is
 experimental and unsupported. It should be considered a technology preview at
 this time. 

---

Deploying an OSGi bundle to Liferay is easy: just copy the bundle to your
`[Liferay Home]/data/osgi/modules` directory. Undeploying is just as easy: just
remove the bundle from the `[Liferay Home]/data/osgi/modules` directory. Note:
If you copy an OSGi bundle to your `[Liferay Home]/deploy` directory, Liferay
automatically copies it to your `[Liferay Home]/data/osgi/modules` directory.

To test Liferay's OSGi module framework and deploy mechanism, you can use the
`test-module-framework-shared` bundle from Liferay's plugin repository on
Github:
[test-module-framework-shared](https://github.com/liferay/liferay-plugins/tree/master/shared/test-module-framework-shared).
In order to deploy the module framework test plugin from a clone of the
liferay-plugins repository, you should first add the following lines to your
`build.[username].properties` file in your `liferay-plugins` directory. If this
file doesn't exist yet, create it.

    liferay.home=[path-to-your-liferay-installation]
    auto.deploy.dir=${liferay.home}/deploy

Then navigate to the `liferay-plugins/shared/test-module-framework-shared`
directory in a terminal and run `ant deploy`. Of course, Apache Ant must be
installed on your system for this to work. When the
`test-module-framework-shared` bundle has been deployed, the following message
appears in the console:

    Activate Test Component

When you remove the `test-module-framework-shared` bundle from the
`${LIFERAY_HOME}/data/osgi/deploy` directory, another message appears:

    Deactivate Test Component

If you'd like to customize your Liferay Portal instance's module framework
behavior, please refer to the Module Framework section of the
`portal.properties` file.  For example, you can set a comma-delimited list of
directories to scan for modules to deploy, specify how to often to scan the
directories for changes, etc. Remember not to make customizations to the
`portal.properties` file itself but to instead add customized properties to a
`portal-ext.properties` file in your Liferay Home directory. 

### Configuring Plugins [](id=configuring-plugins-liferay-portal-6-2-user-guide-14-en)

Liferay Portal has a section of the Control Panel called Plugins Configuration,
which you can find under the Apps heading of the Control Panel. The Plugins
Configuration section not only allows you to see what plugins are installed on
your portal, but also enables you to configure which portal roles can access
certain plugins. It also allows you to activate or deactivate portlet plugins.
From the Control Panel, click on *Plugins Configuration* to find an interface
which allows you to view and manage installed portlet, theme, and layout
template plugins.

The default view of the Plugins Configuration section shows which plugins are
already installed on the system. For portlet plugins, you can click on the
plugin's name to view whether or not it's active and to view or configure the
portlet's permissions. You don't have to uninstall a plugin to prevent users
from accessing the plugin's functionality; instead, you can deactivate the
plugin. To deactivate a plugin, click on the plugin's name in the Plugins
Configuration section of the Control Panel and remove the flag from the *Active*
checkbox. Then click *Save*. To reactive the plugin later, just flag the
*Active* checkbox again and click *Save*.

On the Portlet Plugins tab, you have to click on a specific portlet in order to
view the plugin's active/inactive status and to configure the portlet's
permissions. The active/inactive status doesn't apply to themes or layout
templates: if a theme or layout template has been installed, then it's available
for use. The Theme and Layout Template Plugins tabs of the Plugins Configuration
section directly display which portal roles can access them.

![Figure 14.17: Plugins Installation Portlet Tab Default View](../../images/marketplace-plugins-installation-portlet-tab.png)

It's easy to browse through the lists of installed plugins since the plugins are
listed alphabetically. You can also change the number of items per page and
navigate to a specific page if you know where a particular plugin appears in the
list. This is a standard feature of Liferay and you will see it in most of
Liferay's portlets.

Note that the Plugins Configuration section of the Control Panel is for basic
configuration: it allows you to active or deactivate portlet plugins, view the
existing permissions configuration for various plugins and/or modify the
configurations for existing roles. If you need to add permissions to new roles
for certain portlets, use the Roles section of the Control Panel and the
*Actions* &rarr; *Define Permissions* button. Next, let's learn how to install
plugins manually.

### Installing Plugins Manually [](id=installing-plugins-manually-liferay-portal-6-2-user-guide-14-en)

Installing plugins manually is not quite as easy as installing plugins via the
Marketplace interface but it's still quite simple. There are several scenarios
in which you would need to install plugins manually rather than from Liferay's
repositories:

-   Your server is firewalled without access to the Internet. This makes it
    impossible for your instance of Liferay to connect to the plugin
    repositories.

-   You are installing portlets which you have either purchased from a vendor,
    downloaded separately or developed yourself.

-   For security reasons, you do not want to allow portal administrators to
    install plugins from the Internet before they are evaluated.

You can still use the Control Panel to install plugins that are not available
from the online repositories. This is by far the easiest way to install plugins.

If your server is firewalled, you will not be able to install plugins directly
from the Liferay Marketplace. Instead, you will need to download the `.lpkg`
file (in the case of a Marketplace app) or `.war` file (in the case of an
individual plugin). Then navigate to the Control Panel and click on *App
Manager* under the Apps heading. Then click on *Install*. This gives you a
simple interface for installing an `.lpkg` or `.war` file containing an app or
plugin to your Liferay Portal. Use the *File Upload* option to browse to and
install from a local `.lpkg` or `.war` file. Use the *URL* option to install
from a remote `.lpkg` or `.war` file.

![Figure 14.18: Installing a Plugin Manually](../../images/marketplace-plugin-installer-upload-file.png)

That's all the information the App Manager needs in order to deploy your
portlet, theme, layout template, hook, or web plugin. Click the *Install* button
and your plugin will be uploaded to the server and deployed. If it is a portlet,
theme, or layout template you should see it in the appropriate tab of the
Plugins Configuration interface.

If you do not wish to use the App Manager to deploy plugins, you can also deploy
them at the operating system level. The first time Liferay starts, it creates a
*hot deploy* folder which is, by default, created inside the Liferay Home
folder. This folder generally resides one directory up from where your
application server is installed, though it may be elsewhere depending on which
application server you are running. To find out where the Liferay Home folder is
for your application server, please see the section on your server in this
guide's [Installation and
Setup](https://www.liferay.com/documentation/liferay-portal/6.2/user-guide/-/ai/installation-and-setup-liferay-portal-6-2-user-guide-15-en)
chapter. The first time Liferay is launched, it creates a folder structure in
Liferay Home to house various configuration and administrative data. One of the
folders it creates is called *deploy*. If you copy a portlet or theme plugin
into this folder, Liferay hot deploys it and makes it available for use just as
though you'd installed it via App Manager in the Control Panel. In fact, this is
what the App Manager does behind the scenes.

You can change the defaults for this directory structure so it is stored
anywhere you like by modifying the appropriate properties in your
`portal-ext.properties` file. Please see the above section on the
`portal-ext.properties` file for more information.

To have Liferay hot deploy a plugin, copy the plugin into your hot deploy
folder, which by default is in `[Liferay Home]/deploy`. If you are watching the
Liferay console, you should see messages like the following:

	16:11:47,616 INFO [PortletAutoDeployListener:71] Copying portlets for
	/Users/stephenkostas/java/liferay/bundles/liferay-portal-6.0.4/deploy/weather-portlet-6.0.4.1.war
	
	Expanding:
	/Users/stephenkostas/java/liferay/bundles/liferay-portal-6.0.4/deploy/weather-portlet-6.0.4.1.war
	into
	/Users/stephenkostas/java/liferay/bundles/liferay-portal-6.0.4/tomcat-6.0.26/temp/20100729161147694
	
	Copying 1 file to
	/Users/stephenkostas/java/liferay/bundles/liferay-portal-6.0.4/tomcat-6.0.26/temp/20100729161147694/WEB-INF
	
	Copying 1 file to
	/Users/stephenkostas/java/liferay/bundles/liferay-portal-6.0.4/tomcat-6.0.26/temp/20100729161147694/WEB-INF/classes
	
	Copying 1 file to
	/Users/stephenkostas/java/liferay/bundles/liferay-portal-6.0.4/tomcat-6.0.26/temp/20100729161147694/WEB-INF/classes
	
	Copying 1 file to
	/Users/stephenkostas/java/liferay/bundles/liferay-portal-6.0.4/tomcat-6.0.26/temp/20100729161147694/META-INF
	
	Copying 37 files to
	/Users/stephenkostas/java/liferay/bundles/liferay-portal-6.0.4/tomcat-6.0.26/webapps/weather-portlet
	
	Copying 1 file to
	/Users/stephenkostas/java/liferay/bundles/liferay-portal-6.0.4/tomcat-6.0.26/webapps/weather-portlet
	
	Deleting directory
	/Users/stephenkostas/java/liferay/bundles/liferay-portal-6.0.4/tomcat-6.0.26/temp/20100729161147694
	
	16:11:48,072 INFO [PortletAutoDeployListener:81] Portlets for
	/Users/stephenkostas/java/liferay/bundles/liferay-portal-6.0.4/deploy/weather-portlet-6.0.4.1.war
	copied successfully. Deployment will start in a few seconds.
	
	Jul 29, 2010 4:11:50 PM org.apache.catalina.startup.HostConfig
	deployDirectory
	
	INFO: Deploying web application directory weather-portlet
	
	16:11:50,585 INFO [PortletHotDeployListener:222] Registering portlets
	for weather-portlet
	
	16:11:50,784 INFO [PortletHotDeployListener:371] 1 portlet for
	weather-portlet is available for use

The *available for use* message means your plugin was installed correctly and is
available for use in the portal.

### Plugin Troubleshooting [](id=plugin-troubleshooting-liferay-portal-6-2-user-guide-14-en)

Sometimes plugins fail to install. There can be different reasons for
installation failure based on several factors, including

-   Liferay configuration

-   The container upon which Liferay is running

-   Changing the configuration options in multiple places

-   How Liferay is being launched

You can often tell whether or not you have a plugin deployment problem by
looking at the Liferay server console. If the hot deploy listener recognizes the
plugin, you'll see a *plugin copied successfully* message. If this message is
not followed up by an *available for use* message then you have an issue with
your plugin deployment configuration, probably due to one of the factors listed
above.

Let's take a look at each of these factors.

#### Liferay Configuration Issues [](id=liferay-configuration-issues-liferay-portal-6-2-user-guide-14-en)

---

 ![Tip](../../images/tip.png) **Tip:** This applies to Liferay versions prior to
 version 4.3.5. Liferay versions above 4.3.5 are able to auto detect the type of
 server it is running on, which makes things a lot easier. If you are running a
 newer version of Liferay, you can skip this section. If you are upgrading from
 one of these versions, continue reading.

---

Liferay by default comes as a bundle or as a `.war` file. Though every effort
has been made to make the `.war` file as generic as possible, sometimes the
default settings are inappropriate for the container upon which Liferay is
running. Most of these problems were resolved in Liferay 4.3.5 with the addition
of code that allows Liferay to determine which application server it is running
on and adjust the way it deploys plugins as a result. If you have upgraded from
one of these older versions, you may still have settings in your
`portal.ext.properties` file that are no longer needed. One of these settings is
the manual override of the default value of `auto.deploy.dest.dir`.

In versions of Liferay prior to 4.3.5, there is a property called
`auto.deploy.dest.dir` that defines the folder where plugins are deployed after
the hot deploy utilities have finished preparing them. This folder maps to a
folder the container defines as an auto-deploy or a hot deploy folder. By
default in older versions of Liferay, this property is set to `../webapps`. This
default value works for Tomcat containers (if Tomcat has been launched from its
`bin` folder) but will not work for other containers that define their hot
deploy folders in a different place. In newer versions of Liferay, this value is
automatically set to the default for the application server upon which Liferay
is running.

For example, Glassfish defines the hot deploy folder as a folder called
`autodeploy` inside of the domain folder in which your server is running. By
default, this is in `<Glassfish Home>/domains/domain1/autodeploy`. JBoss defines
the hot deploy folder as a root folder inside the particular server
configuration you are using. By default, this is in `<JBoss
Home>/server/default/deploy`. WebLogic defines this folder inside of the domain
directory. By default, this is in `<Bea Home>/user_projects/domains/<domain
name>/autodeploy`.

The best thing to do when upgrading to newer versions of Liferay Portal is to
remove this property altogether. It is not needed, as the autodetection of the
container handles the hot deploy location. If, for whatever reason, you need to
customize the location of the hot deploy folder, follow the instructions below.

You will first need to determine where the hot deploy folder is for the
container you are running. Consult your product documentation for this. Once you
have this value, there are two places in which you can set it: in the
`portal-ext.properties` file and in the Plugin Installer portlet.

To change this setting in the `portal-ext.properties` file, browse to where
Liferay was deployed in your application server. Inside of this folder should be
a `WEB-INF/classes` folder. Here you will find the `portal-ext.properties` file.
Open this file in a text editor and look for the property
`auto.deploy.dest.dir`. If it does not appear in the file, you can add it. The
safest way to set this property, as we will see later, is to define the property
using an absolute path from the root of your file system to your application
server's hot deploy folder. For example, if you are using Glassfish, and you
have the server installed in `/java/glassfish`, your `auto.deploy.dest.dir`
property would look like the following:

	auto.deploy.dest.dir=/java/glassfish/domains/domain1/autodeploy

Remember, if you are on a Windows system, use forward slashes instead of back
slashes, like so:

	auto.deploy.dest.dir=C:/java/glassfish/domains/domain1/autodeploy

Save the file and then restart your container. Now plugins should install
correctly.

If you are having hot deploy trouble in Liferay versions 4.3.5 and greater, it
is possible the administrator of your application server has changed the default
folder for auto deploy in your application server. In this case, you would want
to set `auto.deploy.dest.dir` to the customized folder location as you would
with older versions of Liferay. In Liferay 4.3.5 and greater, this setting still
exists but is blank. Add the property to your `portal-ext.properties` file and
set its value to the fully qualified path to the auto deploy folder configured
in your application server.

#### Deploy Issues for Specific Containers [](id=deploy-issues-for-specific-containers-liferay-portal-6-2-user-guide-14-en)

Some containers, such as WebSphere®, don't have a hot deploy feature.
Unfortunately, these containers do not work with Liferay's hot deploy system.
But this does not mean you cannot install plugins on these containers. You can
deploy plugins manually using the application server's deployment tools. Liferay
is able to pick up the portlet plugins once they get deployed to the container
manually, especially if you add it to the same Enterprise Application project
that was created for Liferay.

When Liferay hot deploys portlet and theme `.war` files, it sometimes makes
modifications to those files right before deployment. In order to successfully
deploy plugins using an application server vendor's tools, you will want to run
your plugins through this process before you attempt to deploy them.

Navigate back to the *Configuration* tab of the Plugin Installer. Enter the
location you would like plugin `.war` files to be copied to after they are
processed by Liferay's plugin installer process into the *Destination Directory*
field. You will use this as a staging directory for your plugins before you
install them manually with your server's deployment tools. When you are
finished, click *Save*.

Now you can deploy plugins using the Plugin Installer portlet or by dropping
`.war` files into your auto deploy directory. Liferay will pick up the files,
modify them and then copy the result into the destination directory you have
configured. You may then deploy them from here to your application server.

##### Example: WebSphere &#174; Application Server [](id=example-websphere-174-application-serve-liferay-portal-6-2-user-guide-14-en)

1.  If you don't have one already, create a `portal-ext.properties` file in the
    Liferay Home folder of your Liferay installation. Add the following
    directive to it:

	auto.deploy.dest.dir=${liferay.home}/websphere-deploy

2.  Create a folder called `websphere-deploy` inside your `$LIFERAY_HOME`
    folder. This is the folder where the Lucene index, Jackrabbit config and
    deploy folders are.

3.  Make sure the `web.xml` file inside the plugin you want to install has the
    following context parameter in it:

	<context-param\>
	
	<param-name\>com.ibm.websphere.portletcontainer.PortletDeploymentEnabled</param-name\>
	
	<param-value\>false</param-value\>
	
	</context-param\>

Liferay versions 5.2.2 and higher will automatically inject this into the
`web.xml` file on WebSphere containers.

4.  The WebSphere deploy occurs in two steps. You will first use Liferay's tools
    to "pre-deploy" the file and then use WebSphere's tools to do the actual
    deployment. This is because Liferay makes deployment-time modifications to
    the plugins right before they are actually deployed to the application
    server. For other application servers, this can usually be done in one step,
    because Liferay can make the modifications and then copy the resulting
    `.war` file into an autodeploy folder to have it actually deployed. Because
    WebSphere does not have an autodeploy feature, we need to separate these two
    steps.

5.  Deploy your .war file using Liferay's Plugin Installer or by copying it into
    `$LIFERAY_HOME/deploy`. Liferay will make its modifications, and because we
    changed the `auto.deploy.dest.dir` in the first step, it will copy the
    resulting `.war` file into `$LIFERAY_HOME/websphere-deploy`. You will see a
    *copied successfully* message in the log.

6.  Use WebSphere's tools to deploy the `.war` file. Make the context root for
    the `.war` file equal to the file name (i.e., `/my-first-portlet`). Once the
    `.war` file is deployed, save it to the master configuration.

7.  Go back to the *Applications &rarr; Enterprise Applications* screen in the
    WebSphere Admin Console. You will see your portlet is deployed but not yet
    started. Start it.

8.  Liferay will immediately recognize the portlet has been deployed and
    register it. The portlet will be automatically started and registered upon
    subsequent restarts of WebSphere.

Experienced WebSphere system administrators can further automate this by writing
a script which watches the `websphere-deploy` directory and uses `wsadmin`
commands to then deploy plugins automatically.

#### Changing the Configuration Options in Multiple Places [](id=changing-the-configuration-options-in-m-liferay-portal-6-2-user-guide-14-en)

Sometimes, especially during development when several people have administrative
access to the server at the same time, the auto deploy folder location may
inadvertently be customized in both the `portal-ext.properties` file and in the
Control Panel. If this happens, the value in the Control Panel takes precedence
over the value in the properties file. If you go into the Control Panel and
change the value to the correct setting, plugin deployment will start working
again.

## Summary [](id=summary-liferay-portal-6-2-user-guide-14-en)

In this chapter, we introduced Liferay Marketplace, your one-stop shop for
browsing and downloading Liferay-compatible applications. We looked at how to
browse, purchase, download, and install apps. You can do this either through
[liferay.com/marketplace](liferay.com/marketplace) or through Liferay Portal's
Control Panel. When you purchase apps, you can do so via your personal account
or on your company's behalf. For information about developing and uploading apps
to Liferay Marketplace, please see the Marketplace chapter of the Liferay
Developer guide at
[http://www.liferay.com/marketplace/developer-guide](http://www.liferay.com/marketplace/developer-guide).

After discussing Liferay Marketplace, we discussed general plugin management. We
covered Liferay portlet plugins as well as layout, theme, hook, Ext, and web
plugins. Finally, we looked at how to manually deploy plugins to Liferay and
discussed some configuration issues.
