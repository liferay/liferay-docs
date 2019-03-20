---
header-id: obtaining-product
---

# Obtaining @product@

[TOC levels=1-4]

Before you begin, you should answer a few questions. 

-   Which version of @product@ will you install?
-   Is your installation for development purposes or are you preparing to run in
    production?
-   Are you installing @product@ in a clustered environment? 
-   Which application server do you want @product@ to run on?
-   Are you installing on an existing application server? 

Here you'll determine the installation that's best for you and download it. 

Anyone can download @product@ from
[liferay.com](https://www.liferay.com).
Clicking *Resources &rarr; Community Downloads* takes you to the
[Community Downloads page](https://www.liferay.com/downloads-community),
where you can access Liferay Portal CE or a trial of the enterprise supported
Liferay DXP. The installers are available in several different formats. The
formats include convenient bundles as well as `.war` files for installing
@product@ on your application server of choice. 

Liferay enterprise subscribers can download Liferay DXP from the
[Help Center](https://help.liferay.com/hc). 

@product@ runs on a wide variety of application servers (Check the
[Support page](https://help.liferay.com/hc/categories/360000894391-Product-Support)
for a complete listing). Here are the ways to install @product@:

-   [Install @product@ as a bundle](#product-bundles)
    (an application server with @product@ pre-installed).

-   [Install the @product@ `.war`](#installing-the-product-war)
    (and supporting libraries) onto an existing application server.

Since bundles is the easiest way, it's described first. 

## @product@ Bundles 

Bundles include an application server with @product@ pre-installed. There are
bundles for several application servers; choose the one that best fits your
needs. If you don't currently have an application server preference, consider
starting with the Tomcat bundle. Tomcat is one of the most lightweight and
straightforward bundles to configure. If you have an open source application
server preference, choose the server you prefer from the available @product@
bundles. 

| **Note:** Application server bundles for proprietary application servers such
| as WebLogic or WebSphere aren't available because the licenses for these
| servers don't allow for redistribution. @product@'s commercial offering,
| however, runs just as well on these application servers as it does on the
| others. 

Bundles are released as 7-Zip (`.7z`) and gzipped (`.tar.gz`) compressed file
archive formats. 
[Installing @product@](/deployment/-/knowledge_base/7-2/installing-product)
demonstrates installing @product@ from a bundle. Follow its instructions once
you've prepared for installing @product@ (see the next article). 

@product@ bundles might not be appropriate for you. Here are some reasons for
installing the @product@ `.war` manually instead of using a bundle. 

-   There is no @product@ bundle for the application server you want to use. 

-   You're installing @product@ in a clustered environment. 

-   You're installing to an existing application server.

Manual installation is described next. 

## Installing the @product@ WAR

Manual installation requires installing the @product@ `.war` and required JARs
onto the application server. There are specific configuration and script changes
required on each application.

After preparing for install (next), follow the @product@ installation steps for
your application server. The article titles follow this format, with
*[Application Server]* replaced by the application server name. 

*Installing @product@ on [Application Server]*

Once you've determined the install format that's right for you, download
@product@. Preparing for installation is next!
