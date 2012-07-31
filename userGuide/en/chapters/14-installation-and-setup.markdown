# Installation and Setup [](id=installing-liferay)

Liferay Portal is one of the most flexible applications on the market today with regard to application server environments. You can install Liferay Portal on everything from a shared Tomcat installation to a multi-node cluster running a commercial application server and on everything in between. In fact, Liferay is used successfully in all of these scenarios every day.

You'll find that because Liferay is extremely flexible in its deployment options, it is easy to install as well. If you already have an application server, you can use the tools for deployment that came with your application server. If you don't have an application server, Liferay provides several application server bundles from which to choose. These are very easy to install and with a small amount of configuration can be made into production-ready systems.

## Editions of Liferay [](id=editions-of-lifer-4)

Liferay ships in two different editions: Liferay Portal Community Edition (CE) and Liferay Portal Enterprise Edition (EE). CE is the same Liferay Portal that has been available for years: frequently updated and bursting with the latest features, the Community Edition of Liferay Portal is offered for free under the Lesser GNU public license, an open source license. This license gives you the flexibility to link Liferay with your own code in your portlet, theme, hook, layout, Ext or web plugins, no matter what license you use for your code. If, however, you modify Liferay directly, those modifications need to be released as open source. This is really the best of both worlds: you have the freedom to do what you want with your code if you use plugins, but if you modify Liferay directly, the community receives the benefits of any enhancements that you've made. 

Liferay Portal EE is a supported version of Liferay Portal for the enterprise. Hardened for security and designed to be rock solid stable, EE is offered with a subscription and support package, allowing organizations to build their portals on a stable version of the product that is offered over an extended period of time.

Because the release cycle for EE is longer than that for CE, each enterprise release is supported for 4 years. All bug fixes in Liferay Portal are backported to your version of Liferay for the duration of your subscription. This gives organizations the peace of mind that comes from knowing that their Liferay-powered web sites are stable and will run for years to come, enabling them to build their sites on a proven, stable platform. Additionally, Liferay offers training and consulting on the Enterprise Edition to ensure long-term support and stability for our clients. 

## Obtaining Liferay Portal [](id=obtaining-lifer-5)

The CE version of Liferay is freely downloadable from our web site at [http://www.liferay.com](http://www.liferay.com). Click the *Downloads* link at the top of the page and you are presented with multiple options for getting a copy of Liferay, including our convenient bundles or a `.war` package for installation on your application server of choice.

The EE version of Liferay is provided to you as a result of your support subscription. Everything you need is provided in the Customer Portal, including download links that allow you to obtain a copy of a Liferay bundle or a `.war` package for installation on your application server of choice.

So what is a bundle anyway? A bundle is an open source application server with Liferay preinstalled. This is the most convenient way to install Liferay. Liferay is bundled with a number of open source application servers; all you need to do is choose the one that best fits your needs. If you don't currently have an application server, you may want to start with the Tomcat bundle, as Tomcat is one of the smallest and most straightforward bundles to configure. If you have an open source application server preference, choose the server you prefer from the available Liferay Portal bundles. All of the bundles ship with a Java Runtime Environment for Windows; if you are using a different operating system, you will need to have a JDK (Java Development Kit) installed prior to launching Liferay.

Please note that Liferay is not able to provide application server bundles for proprietary application servers such as WebLogic or WebSphere, because the licenses for these servers don't allow for redistribution. Liferay Portal, however, runs just as well on these application servers as it does on open source application servers. A `.war` file and dependency `.jar`s are provided for proprietary application servers and you'll need to follow a procedure to install Liferay on them. 

First we'll go over installing Liferay from a bundle and after this we'll provide instructions for installing Liferay manually on all the application servers it supports. 

## Installing a bundle [](id=installing-a-bund-5)

Liferay bundles contain the same directory structure regardless of application server. The top-level folder is named for the release of Liferay. This folder is called *Liferay Home* and we refer to it thoughout this documentation. 

<!-- | TODO: This image needs to be updated to the 6.1 release when we have a build. | -->

![Figure 14.1: Bundle directory structure](../../images/02-bundle-directory-structure.png) 

Inside this folder, there are folders for various purposes:

**Data:** This folder is used to store the embedded HSQL database which the bundles use, as well as the configuration and data for the Jackrabbit JSR-170 content repository and the Lucene search index.

**Deploy:** Plugins which you wish to deploy to Liferay can be copied into this folder. It is also used by Liferay Marketplace and Liferay's graphical plugin installer utility. 

**[Application Server]:** The name of this folder is different depending on which bundle you have downloaded. This folder contains the application server in which Liferay has been installed.

Installing a bundle is as easy as uncompressing the archive, copying a JDBC driver and then starting the application server. Let's use the Tomcat bundle as an example:

1. Unzip the bundle to a location of your choice. 
2. If you're setting up Liferay to be an actual server, copy your database's JDBC driver `.jar` to `[Tomcat]/lib/ext` (see the setup wizard section below). If you're setting up Liferay for demo purposes, you can skip this step. 
3. Start Tomcat in the same way as you would if you had downloaded it manually. Tomcat is launched by way of a script which is found in its `bin` folder. If you drop to a command prompt and go to this folder, you can launch Tomcat via the following command on Windows:

    startup

or the following command on Linux/Mac/Unix:

    ./startup.sh

The Liferay/Tomcat bundle then launches. If you are on Windows, another command prompt window appears with Tomcat's console in it. If you are on Linux, you can see the Tomcat console by issuing the following command:

    tail -f ../logs/catalina.out

Once Tomcat has completed its start up, it automatically launches a web browser that displays Liferay's setup wizard. If for some reason your browser doesn't load the wizard, launch your web browser and then go to  [http://localhost:8080](http://localhost:8080). 

Liferay CE ships with a sample web site for a fictitious company called 7Cogs. This site demonstrates some of the things Liferay Portal can do. If you're installing Liferay on your own machine to explore its features, you likely want to leave the sample site there so you can examine it. If, however, you're installing Liferay on your server to run your own site, it's best to start with a clean system. Before running the setup wizard, you should remove the sample 7Cogs data from your Liferay installation. You must do this before running the setup wizard to get a clean database and it's as simple as undeploying the application that installs the 7Cogs data.

There is only one application included in the bundle that you need to remove. It's called *sevencogs-hook*. It's a Liferay plugin that copies the 7Cogs data into the database when Liferay is started. To remove it, all you have to do is undeploy it. The method for doing this differs by application server and that, of course, depends on the bundle you have chosen. For example, on Tomcat you delete the application from the `[Tomcat Home]/webapps` folder. On Glassfish, you use the administrative console to undeploy it. The other application related to 7Cogs is a theme, which you can leave installed if you wish.

If you forget to undeploy the *sevencogs-hook* application before you run through the setup wizard and connect Liferay to your real database, the sample data will be created in your database and may cause issues with your site. Make sure you get *sevencogs-hook* undeployed before setting up your server.

If you're using Liferay EE, you don't have the sample site so you don't need to worry about this. The next step is to run through the setup wizard. 

### Using Liferay's setup wizard [](id=using-liferay-s-setup-wizard)

To make it easy to configure Liferay optimally for your use, the first thing you see when browsing to your newly installed Liferay bundle is a setup wizard. This gives you a convenient way to configure Liferay for your purposes. 

There are three sections of the wizard: the portal, the adminstrator and the database. For the portal, you need to supply the following information: 

**Portal Name:** the name of the web site you're powering with Liferay. In this book, we'll build a social network for your nose. This site is called Nosester so we've supplied `Nosester` in the screenshot below. 

**Default Language:** choose the default locale where your site resides. 

For the adminstrator, you need to supply the following information: 

**First Name:** the first name of the user that has the administrator account. 

**Last Name:** the last name of the user that has the administrator account. 

**Email:** the email address of the user that has the administrator account. 

![Figure 14.2: Supply the information for your site and your site's administrative account in the setup wizard.](../../images/setup-wizard-1.png)

The database section can be a little bit technical. If you've downloaded Liferay for evaluation or demo purposes only, and you're not installing Liferay on server hardware to get it ready for your users, you can ignore the database section and click the *Finish Configuration* button. If, however, you're reading this section because you're installing Liferay for actual use, you'll need a separate database first.

Liferay supports just about all the leading databases today: 

* DB2
* Ingres
* MySQL
* Oracle
* PostgreSQL
* SQL Server
* Sybase

In addition to these, Liferay also supports a few embedded databases that are designed for development. We haven't listed these here because you're setting up a production Liferay server and you shouldn't use an embedded database with a production box. 

Before you fill out the database section of Liferay's setup wizard, go to your database software of choice and create a database for Liferay to use. This database must have UTF-8 as its character set, because Liferay is an internationalized application and needs the UTF-8 character set to display text in many different languages. Check the documentation for your database to see how to do this. 

Once you have your database secured, create a user which can do anything to the database, including create and drop tables. It's important that this user has complete rights over the Liferay database, because Liferay manages its own tables. Once you have your database and the credentials for this user, you can continue. 

Open the Database section of the wizard. From the select box, choose your database. You'll see a form which lets you specify the URL to the database, the driver class and the user credentials (see below). Most of this is filled out already; all you should need to do is supply the name of your database and the server it's running on, as well as the user credentials. 

![Figure 14.3: Fill out the information for your database. We've chosen MySQL in this example and have created a database called *nosester* to hold our Liferay data.](../../images/setup-wizard-2.png)

Once you've filled out the form, click *Finish Configuration*. You'll see a message stating that Liferay is being installed as it creates the tables and data it needs in its database. When it's finished, it tells you the location of the configuration file (`portal-setup-wizard.properties`) where it saved all of your settings. From here, you can go to your home page. 

Congratulations! You've just installed Liferay Portal!

---

![Tip](../../images/02-tip.png) The wizard is an extremely helpful tool, especially if you're setting up Liferay for the first time or creating a completely fresh portal instance. If you're a Liferay veteran and you already have your database information and various properties set up, you can skip the wizard by adding this line to your *portal-ext.properties* file:

    setup.wizard.enabled=false

---
    
As you can see, bundles are the easiest way to get started with Liferay. They come pre-configured with a running Liferay instance that can be used immediately to explore all of the things that Liferay can do. Bundles are the fastest way to create full production-ready Liferay installations. 

Of course, it's not always possible to use a bundle. You may already have an application server upon which you want to install Liferay. The bulk of this chapter describes how to install Liferay on all the application servers it supports, both open source and proprietary. 

## App servers [](id=installing-liferay-on-an-existing-application-server)

When it comes time to install Liferay Portal on your server, you'll find it's easiest to do this by starting with a bundle. But many enterprises can't do that. There may be an existing infrastructure into which you're installing Liferay or you may have standardized on a particular application server. You'll be happy to know that Liferay Portal has been designed to work well with all the leading application servers and that it's easy and straightforward to install. But before we get started, we need to go over a few concepts; namely, the Liferay Home folder, databases and Liferay's main configuration file. These were touched on in the section on bundles above but we'll look at them in more detail now. 

### Liferay Home [](id=lp-6-1-ugen11-liferay-home-0)

Liferay Portal uses a special folder defined as *Liferay Home*. This folder is one folder higher than the location of the application server itself. This is why the bundles place the application server one folder in from the bundle's root folder.

If Liferay is unable to create the resources it needs in this folder or if it finds itself running on certain application servers, it creates a folder called `liferay` in the home folder of the user ID that is running Liferay and that becomes Liferay Home.

As described above in the *Bundles* section, the home folder is very important to the operation of Liferay. The aforementioned folders (*data* and *deploy*) are created there and you can also put a special configuration file called `portal-ext.properties` there. This file is fully documented in chapter 20, a reference for Liferay properties.

![Note](../../images/tip.png) **Note:** To use database properties from a `portal-ext.properties` file you must disable the Setup Wizard by specifying `setup.wizard.enabled=false` in that `portal-ext.properties`.
Also, note that property values in `portal-setup-wizard.properties` (the file created in Liferay Home by the Setup Wizard) override property values in `portal-ext.properties`. 

Let's move on to examining the database. 

### Liferay's database [](id=lp-6-1-ugen11-liferays-database-0)

As stated above, if you create your database and grant a user ID full access to it, Liferay can use that user ID to create its indexes and tables automatically. This is the recommended way to set up Liferay, as it allows you to take advantage of Liferay's ability to automatically maintain its database during upgrades or through various plugin installs which may create tables of their own. It is by far the best way to set up your Liferay installation.

If you'll be setting up Liferay's database with the recommended permissions, you can skip to the next section.

![image](../../images/02-tip.png) **Note:** This is not the recommended set up for Liferay installations but is documented here so enterprises with more restrictive standards can install Liferay with more strict-—but suboptimal—-database settings. If it's at all possible, Liferay recommends that you use the automatic method as documented above instead of the procedure outlined below.

Even though Liferay can create its database automatically, some enterprises prefer *not* to allow the user ID configured in an application server to have the permissions over the database necessary for Liferay and its plugins to maintain their tables. For these organizations, Select, Insert, Update and Delete are the only permissions allowed so we will go over how to set up the database manually. If your organization *is* willing to grant the Liferay user ID permissions to create and drop tables in the database—-and this is the recommended configuration-—by all means, use the recommended configuration. 

Creating the database is simple: grant the ID Liferay uses to access the database full rights to do anything to the database. Then install Liferay and have it create the database. Once the database is created, remove the permissions for creating tables and dropping tables from the user ID.

There are some caveats to running Liferay like this. Many Liferay plugins create new tables when they're deployed. In addition to this, Liferay has an automatic database upgrade function which runs when the version of Liferay is upgraded to a new release. If the user ID that accesses the database doesn't have enough rights to create/modify/drop tables in the database, you must grant those rights to the ID before you deploy one of these plugins or start your upgraded Liferay for the first time. Once the tables are created or the upgrade is complete, you can remove those rights until the next deploy or upgrade. Additionally, your developers may create plugins that need to create their own tables. These are just like Liferay's plugins that do the same thing and they cannot be installed if Liferay can't create these tables automatically. If you wish to install these plugins, you will need to grant rights to create tables in the database before you attempt to install them. 

Once you have your database ready, you can install Liferay on your server. 

### Liferay installation overview [](id=lp-6-1-ugen11-liferay-installation-overview-0)

Before we begin, it's important to go over the various facets of the installation. They are: 

1. Create your database (see above). 
2. Determine whether you want to use the Liferay managed data source or a data source managed by your application server. 
3. Gather credentials for sending email notifications to users. Liferay supports a JNDI mail session as well as its built-in mail session. 
4. Create a `portal-ext.properties` file in the Liferay Home folder. This is a simple text properties file that you'll use to override Liferay's default properties (see below). 

Refer to the manual installation instructions below for further details on configuring the various application servers. There is no difference between the Liferay bundles and the regular distribution archives of the application servers as they are available from their own sites, with the exception that Liferay is pre-installed in them and the JVM settings may have been optimized for use with Liferay. 

The easiest way to install Liferay is to set up your database and then follow the instructions for your application server. This method uses the setup wizard to create a working configuration. If you're interested in the details of what the setup wizard does or if for some reason you need to set up Liferay manually, read on. Otherwise, feel free to skip ahead to the section on your particular application server. 

#### Using data sources [](id=lp-6-1-ugen11-using-data-sources-0)

Liferay comes bundled with its own built-in data source. It's configured by a number of properties which you set in a  properties file. By default, the setup wizard asks you for the necessary values and creates a configuration file that uses the built-in data source to connect to the database. 

Liferay always recommends that you use the built-in data source. Sometimes, however, organizations prefer to use the data source provided by their application server of choice. In this instance, a JNDI lookup provides a handle to the data source and the application server manages the connection pools. Liferay supports using your application server's data source if you wish to do that. 

To do this, you'll need to create your own configuration file and skip the setup wizard. Since you'd be creating this file *after* the wizard anyway, this isn't such a big deal. 

Since mail sessions are configured in a similar way to data sources, we'll look at them next. 

#### Using mail sessions [](id=lp-6-1-ugen11-using-mail-sessions-0)

Liferay's default configuration looks for a mail server on the same machine on which Liferay's running and it tries to send mail via SMTP to this server. If this is not your configuration, you'll need to modify Liferay's defaults. To do this, you'll use a `portal-ext.properties` file (see below). 

In a similar fashion to databases, you have two ways to configure your mail server:

-   Use your application server's mail session.
-   Use the built-in mail session.

To use your application server's mail session, you must create it in your application server and it should point to your mail server. Once you've done that, you're ready to point Liferay to it. You can do this through the configuration file or through Liferay's control panel after it's been installed. 

Let's look next at this configuration file and, if you're choosing not to use the setup wizard, show you how to get Liferay connected to your database and your mail server. 

#### The portal-ext.properties file [](id=lp-6-1-ugen11-the-portal-extproperties-file-0)

Liferay's properties files differ from the configuration files of most other products in that changing the default configuration file is discouraged. In fact, the file that contains all the defaults is stored inside of a `.jar` file, making it more difficult to customize. Why is it set up this way? Because Liferay uses the concept of *overriding* the defaults in a separate file, rather than going in and customizing the default configuration file. You put only the settings you want to customize in your own configuration file and then you have a file that contains only the settings you need. This makes it far easier to determine whether a particular setting has been customized and it makes the settings more portable across different installations of Liferay.

The default configuration file is called `portal.properties` and it resides inside of the `portal-impl.jar` file. This `.jar` file is in Liferay Portal's `WEB-INF/lib` folder. The file used to override the configuration is `portal-ext.properties`. This file should be created in your Liferay Home folder (please see chapter 11 for the location of this folder for your application server). You'll use this file throughout this book to change many of Liferay's settings. An exhaustive list of the configurable properties is provided in chapter 20. 

---

![Figure 14.4: Tip](../../images/tip.png) **Warning:** The configuration you choose in the setup wizard is saved in a `portal-setup-wizard.properties` file in your Liferay Home directory. In the setup wizard, however, if you specify a different Liferay Home directory than the default, the `portal-setup-wizard.properties` file that's saved there will not be read upon restarting your server. To have Liferay read your `portal-setup-wizard.properties` file, create a `portal-ext.properties` file in your new Liferay Home directory and add the following line to it, where `${liferay.home}` is the new Liferay Home directory that you chose:

    include-and-override=${liferay.home}/portal-setup-wizard.properties

Without this workaround, Liferay will not read the `portal-setup-wizard.properties` file with your saved configuration when you restart your server and you'll see the setup wizard again. 

---

For now, we're only concerned with your database and your mail server. Create a text file called `portal-ext.properties` in your Liferay Home folder. This file overrides default properties that come with Liferay. The first setting you'll override is the default configuration that points Liferay to the embedded HSQL database.

As stated above, there are two ways to set up the connection:

-   Use the built-in connection pool.
-   Use your application server's connection pool.

To use the built-in connection pool--based on *C3P0*--add the template <!--which is provided in Chapter 6-->for your particular database. The template for MySQL is provided as an example below.

<!-- The above chapter reference is wrong and has been commented out. Are the templates referred to still in the User Guide? -->
    # 
    # MySQL 
    # 
    jdbc.default.driverClassName=com.mysql.jdbc.Driver
    jdbc.default.url=jdbc:mysql://localhost/lportal?useUnicode=true&characterEncoding=UTF-8&useFastDateParsing=false
    jdbc.default.username=liferay
    jdbc.default.password=liferay

If the database name and username/password combination above don't match your database, provide the ones that do.

If you want to use your application server's connection pool, you will have to create one in your application server that points to your database. It should be called `jdbc/LiferayPool`. To cause Liferay to use this connection pool, add the following directive to your `portal-ext.properties` file:

	jdbc.default.jndi.name=jdbc/LiferayPool

For mail, you can use Liferay's control panel to create the configuration and this is the recommended way. Go to *Control Panel &rarr; Server Administration &rarr; Mail* and enter your settings for your mail session settings. If, however, you're setting up a lot of Liferay machines and they're all going to have similar mail configurations, it's easier to do the configuration once and then copy the configuration file to multiple machines. In this case, you'll want to use the `portal-ext.properties` file. To use the built-in mail session, use the following properties and customize their values for your environment: 

    mail.session.mail.pop3.host=localhost
    mail.session.mail.pop3.password=
    mail.session.mail.pop3.port=110
    mail.session.mail.pop3.user=
    mail.session.mail.smtp.auth=false
    mail.session.mail.smtp.host=localhost
    mail.session.mail.smtp.password=
    mail.session.mail.smtp.port=25
    mail.session.mail.smtp.user=
    mail.session.mail.store.protocol=pop3
    mail.session.mail.transport.protocol=smtp
    
To use your application server's mail session, create it first. Then specify it in the `portal-ext.properties` file: 
	
	mail.session.jndi.name=mail/MailSession

When you've finished, save the file.

Next, follow the instructions for installing Liferay on your particular application server in the section below. 

### Installing Liferay on an existing application server [](id=lp-6-1-ugen11-installing-liferay-on-an-existing-application-server-0)

This section contains detailed instructions for installing Liferay Portal using its .war file distribution. This allows system administrators to deploy Liferay in existing application server installations. It is recommended that you have a good understanding of how to deploy Java EE applications in your application server of choice.

#### Installing Liferay in five easy steps [](id=lp-6-1-ugen11-installing-liferay-in-five-easy-steps-0)

There are five generic steps to installing Liferay on an existing application server:

1. Obtain the Liferay `.war` file and the dependencies archive.

2. Make sure you don't have an application listening at the root (`/`) of your server. If you do, move it to a different context or undeploy it.

3. Shut your application server down.

4. Extract the dependencies to a location on your server's global classpath. This allows both Liferay and plugins to access these dependencies.

5. Start your application server, deploy the Liferay `.war` file and start it.

The instructions below are specific for each application server that Liferay supports. Liferay supports a wide combination of application servers and databases. Because of this, this section assumes MySQL as the database, that the database has already been created and that you're using the setup wizard. If you're not using the setup wizard, see the sections above for information on how to set up Liferay manually.

We also assume your application server is already installed and running successfully. If you still need to install your application server, please follow your vendor's instructions first.

The following instructions assume an installation on a local machine. When installing to a remote server, substitute `localhost` with the host name or IP of the server.

![image](../../images/02-tip.png) **Tip:** Note that Liferay *requires* JDK 5 or greater. Do not attempt to install Liferay 6.x on an application server that runs under Java 1.4 or lower; it will not work. If you are running an application server that ships with a JDK and that JDK is 1.4 or lower, you'll need to upgrade your application server to run current versions of Liferay Portal.

Without further ado, let's get to the application servers. The first one we'll cover is Mule Tcat. If you don't have an application server preference, and you want support all the way down to the application server from Liferay, then Mule Tcat is your solution. After we cover Mule Tcat, we'll look at all the supported application servers in alphabetical order. 

### Installing Liferay on Mule Tcat [](id=lp-6-1-ugen11-installing-liferay-on-mule-tcat-0)

Liferay Portal Tcat Edition is a combination of Liferay Portal, the leading open source portal, and Tcat Server, an enterprise grade administration console for the Apache Tomcat application server.

Tcat Server provides several tools to make Tomcat more administrator friendly, especially in organizations used to leveraging administration consoles like those found in other more complex JEE application servers. You may use the console to:

-   Monitor and control all Tomcat instances on multiple servers and across multiple environments 
-   Deploy and view applications across instances
-   Troubleshoot problems across all instances, including view JMX parameters, viewing remote logs and more
-   Provide granular entitlement controls for the above functions and more

This isn't a complete guide to Mule Tcat; it just covers Liferay installation. For full documentation, you'll find more detailed information here:  [http://www.mulesoft.org/documentation/display/TCAT/Home](http://www.mulesoft.org/documentation/display/TCAT/Home)

To obtain Liferay Portal Tcat Edition, you need access to the Liferay Customer Portal and the Mulesoft Tcat Server web site. If you are not a current Liferay customer and would like to try the product, please contact Liferay via email at sales@liferay.com.

For this section, we'll limit ourselves to the Windows and Linux 64-bit installation wizards. For more detailed installation instructions, please consult the Mulesoft Tcat Server installation documents located at:

[http://www.mulesoft.org/documentation/display/TCAT/Home](http://www.mulesoft.org/documentation/display/TCAT/Home)

-   [Installing Tcat Server on     Linux](http://www.mulesoft.org/documentation/display/TCAT/Installing+Tcat+Server+on+Linux)
-   [Installing Tcat Server on     Windows](http://www.mulesoft.org/documentation/display/TCAT/Installing+Tcat+Server+on+Microsoft+Windows)
-   [Installing Tcat Server on     Solaris](http://www.mulesoft.org/documentation/display/TCAT/Installing+Tcat+Server+on+Solaris)
-   [Installing Tcat Server on Mac OS     X](http://www.mulesoft.org/documentation/display/TCAT/Installing+Tcat+Server+on+Mac+OS+X)
-   [Adding Tcat Server Capabilities to an Existing Apache Tomcat     Install](http://www.mulesoft.org/documentation/display/TCAT/Add+Tcat+Server+Capabilities+to+an+Existing+Tomcat+Install)

First, download the Liferay Tcat bundle from the Liferay customer portal. You'll need two files:

-   **Liferay Portal 6.1 EE Tcat Admin:** contains the Tcat administration console and Liferay Portal EE and all appropriate plugins.
-   **Liferay Portal 6.1 EE Tcat Agent:** contains a vanilla Tomcat application server and the Tcat management agent.

Next, download the appropriate Tcat platform installation at: [http://www.mulesoft.com/download-tcat](http://www.mulesoft.com/download-tcat). You may select installation wizards for Windows (32 and 64-bit), Mac, Unix, Solaris and Linux (32 and 64-bit) as well as a manual installation zip.

After obtaining the software bundles, you can proceed with installation and configuration of the Administration Console.

#### Installing the Administration Console on Windows  [](id=lp-6-1-ugen11-installing-the-administration-console-on-windows--0)

For Windows, Tcat comes with an installer to assist with installation and configuration. After downloading, execute the installer, accepting the appropriate license agreement.

Upon accepting the license agreement, the system presents you with two installation choices, a "standard" or "custom" installation.

![Figure 14.5: Installation selection](../../images/tcat-html_2589582e.png)

You should select the "Custom" installation option to provide better control of where Tcat Server is installed.

![Figure 14.6: Installation component selection](../../images/tcat-html_m230ba580.png)

After selecting the custom installation option, the Tcat installer prompts you to select the desired components. 

-   **Tcat Server:** a version of Apache Tomcat that includes the appropriate management agents used by Tcat.
-   **Administration Console:** the administration console for monitoring and managing all available Tcat servers. This console contains tools used for application deployment, log access, server control and other administration tools.
-   **Tomcat Documentation:** the documentation that comes normally with an Apache Tomcat distribution. You do not need to select this option.
-   **Tomcat Examples:** the examples that comes normally with an Apache Tomcat distribution. You do not need to select this option.
-   **Tomcat Manager Application:** the manager application that comes normally with an Apache Tomcat distribution. You do not need to select this option.

For this step in the installation process, select the Administration Console in addition to Tcat Server.

![Figure 14.7: Installation location](../../images/tcat-html_261b594a.png)

After selecting Tcat Server and the Administration Console for installation, the installation wizard prompts you for an installation directory. Please select the desired installation directory for the Tcat Server.

![Figure 14.8: Port configurations](../../images/tcat-html_66274d0c.png)

After selecting the appropriate installation location, the installation wizard prompts you to specify the appropriate port numbers. If this is the first time installing Tcat and no other Apache Tomcat installations are present, then you may retain the above ports. However, if there are other installations, you will need to select new ports. For the purpose of this installation, we will assume the above ports are correct.

![Figure 14.9: Windows service installation](../../images/tcat-html_7936c58a.png)

To ensure the operating system starts the Tcat Server and Administrator Console, you must configure the Tcat Server process as a Windows service. The next step in the installation wizard helps with this process.

In the Windows service installation screen, please select "Install Tcat Server as a Windows service." You may choose an appropriate service name other than *TcatServer*.

![Figure 14.10: Start menu shortcuts](../../images/tcat-html_3f911eab.png)

The final step in the installation wizard is to configure Start Menu shortcuts. You may choose to customize the shortcut location or accept the default.

After configuring the shortcuts, the Tcat Server Windows installer performs the installation as previously configured.

#### Installing the Administraton Console on Linux (Ubuntu) [](id=lp-6-1-ugen11-installing-the-administraton-console-on-linux-ubuntu-0)

For Linux, Tcat comes with an installer to assist with installation and configuration. After downloading, execute the installer, accepting the appropriate license agreement. To execute the installer, make sure:

-   you have added execution permissions to the installer file (`chmod a+x [file name]`)
-   you execute it with root privileges (don't worry: the installed service is executed by a pre-defined system user with no root privileges)

Upon accepting the license agreement, the system presents you with two installation choices: a "standard" or "custom" installation.

<!-- The following two figures are difficult to read and should be replaced with ones having more readable text.  -->

![Figure 14.11: Installation type](../../images/tcat-html_766a1d6e.gif)

You should select the "Custom" installation option to provide better control of where Tcat Server is installed.

![Figure 14.12: Installation component selections](../../images/tcat-html_55494177.gif)

After selecting the custom installation option, the Tcat installer prompts you to select the desired components. 

-   **Tcat Server:** a version of Apache Tomcat that includes the appropriate management agents used by Tcat.

-   **Administration Console:** the administration console for monitoring and managing all available Tcat servers. This console contains tools used for application deployment, log access, server control and other administration tools.

-   **Tomcat Documentation:** the documentation that comes normally with an Apache Tomcat distribution. You do not need to select this option.

-   **Tomcat Examples:** the examples that comes normally with an Apache Tomcat distribution. You do not need to select this option.

-   **Tomcat Manager Application:** the manager application that comes normally with an Apache Tomcat distribution. You do not need to select this option.

For this step in the installation process, select the Administration Console in addition to Tcat Server, which is the default setting.

After selecting Tcat Server and the Administration Console for installation, the installation wizard prompts you for an installation directory.

![Figure 14.13: Installation location](../../images/tcat-html_7df6aabf.png)

Please select the desired installation directory for the Tcat Server. The offered default directory is different if the installation process has been started as root.

<!-- The following figure is difficult to read and should be replaced with a better one.  -->

![Figure 14.14: Port configuration](../../images/tcat-html_5b760cd.gif)

After selecting the appropriate installation location, the installation wizard prompts you to specify the appropriate port numbers. If this is the first time installing Tcat and no other Apache Tomcat installations are present, then you may retain the above ports. However, if there are other installations, you will need to select new ports. For the purpose of this installation, we will assume the above ports are correct.

![Figure 14.15: Configure Tcat system user](../../images/tcat-html_749e9b40.png)

For security considerations, Tcat runs as a non-root system user. If the username specified at this step does not exist, it's created as a system daemon user.

<!-- The following three figures are difficult to read and should be replaced with better ones.  -->

![Figure 14.16: Install service](../../images/tcat-html_mf7bb10c.gif)

To ensure the operating system starts the Tcat Server and Administrator Console, you must create a service startup script in `/etc/init.d`. The next step in the installation wizard helps with this process.

In the service installation screen, you may enter an appropriate service name or use the default. If you have multiple installations of Tcat, you should select a more appropriate name than what is supplied by default. 

![Figure 14.17: Symbolic link creation](../../images/tcat-html_m55bbff3f.gif)

The final configuration step before installation is the creation of a symbolic link so the Tcat executable can be accessed more easily. This step is optional and you may choose to not create the symbolic link.

![Figure 14.18: ](../../images/tcat-html_78e73740.gif)

Now that Tcat is installed, you can add Liferay to it. 

#### Adding Liferay Portal packages [](id=lp-6-1-ugen11-adding-liferay-portal-packages-0)

After completing the TcatServer Administration Console installation, you can configure the Liferay Portal packages for Tcat.

First, extract the previously downloaded Liferay Portal 6.1 EE Tcat Admin into a temporary directory. Once extracted, locate the file `tcat-init.groovy` and the directory `tcat\_init`.

![Figure 14.19: Liferay Portal Tcat packages](../../images/tcat-html_12074416.png)

The `tcat-init.groovy` file contains instructions for the TcatServer administration console to:

-   Pre-load the Liferay Portal and Plugins WAR files into the application repository
-   Load the appropriate Liferay application profile that should be applied to all other Tcat managed servers
-   Load the Liferay specific deployment manager

The `tcat\_init` folder contains the managed server profiles, Liferay WAR files and administration scripts.

Copy the `tcat-init.groovy` file and `tcat\_init` folder to the previously configured installation location.

![Figure 14.20: The installation directory after copying the `tcat\_init` folder and `tcat-init.groovy` into the TcatServer installation directory.](../../images/tcat-html_mf987314.png)

After successfully completing the installation on Windows, you should see a TcatServer entry in the Services console, similar to the screen shot below. 

![Figure 14.21: Windows services console](../../images/tcat-html_3b2f5fb4.png)

By default, the TcatServer service is inactive but is set to start automatically upon boot. Go ahead and choose to start the service.

If you're using Linux, you should see an entry for the Tcat service initialization script in `/etc/init.d`. The script name is the name you choose during the installation process.

<!--  We might want to replace this screen shot.  -->

![Figure 14.22: Tcat service startup on Linux](../../images/tcat-html_352642da.gif)

To start the Tcat Server Administration Console, execute the service script in `/etc/init.d`.

#### Tcat Server Managed Server Installation [](id=lp-6-1-ugen11-tcat-server-managed-server-installation-0)

The steps to install the Tcat Server managed server are quite similar to those for installing the Tcat Server Administration Console.

First, launch the wizard. During the installation component configuration step, unselect "Administration Console" from the list of components.

![Figure 14.23: Installation location](../../images/tcat-html_261b594a.png)

The next step is to specify an installation location. If you have other installations of Tcat on this machine, you should choose another destination. For example, if you have already installed the Tcat Server Administration Console to `C:\\TcatServer6`, you should perhaps install the managed server to `C:\\TcatServer6Managed`.

![Figure 14.24: Managed server port configurations](../../images/tcat-html_66274d0c.png)

During the managed server installation, as with the administration console installation, you will be prompted to configure the appropriate port numbers. If you do not have another Tcat Server instance (e.g. administration console or another managed server), you may choose the default ports. Otherwise, you should select non-conflicting ports. For instance:

-   HTTP port: 8180
-   HTTPS redirect port: 81443
-   Tcat Server shutdown port: 8105
-   AJP connector port: 8109
-   Secure Agent Port: 52443

To ensure the operating system starts the Tcat Server and Administrator Console, you must configure the Tcat process as a Windows service or Linux daemon. The next step in the installation wizard helps with this process.

The final step in the installation wizard is to configure Start Menu shortcuts. You may choose to customize the shortcut location or simply accept the default. Since this is for a managed server, you may wish to name it similar to the service name.

After configuring the shortcuts, the Tcat Server installer performs the installation as previously configured.

Once you have installed the managed server, there is one more step to perform on the managed server. You must modify the `catalina.properties` file located in the `conf` directory of your Tcat Server installation.

You will need to change the text:

	common.loader=${catalina.base}/lib,${catalina.base}/lib/\*.jar,${catalina.home}/lib,${catalina.home}/lib/\*.jar

To:

	common.loader=${catalina.base}/lib,${catalina.base}/lib/\*.jar,${catalina.home}/lib,${catalina.home}/lib/\*.jar,${catalina.home}/lib/ext,${catalina.home}/lib/ext/\*.jar

By modifying the `common.loader` property, you instruct Tcat Server to load everything in the `lib/ext` folder as part of the classpath.

#### Registering the managed server [](id=lp-6-1-ugen11-registering-the-managed-server-0)

After completing the installation process and starting the appropriate TcatServer processes, open a browser to `[SERVER NAME]:[PORT]/console`. Using the previous installation example, you should point your browser to `http://localhost:8080/console`. The browser should render the TcatServer Administration Console shown below. 

![Figure 14.25: Tcat Admin Console log in](../../images/tcat-html_m4bda7997.png)

The default login is: admin/admin.

Once you have logged into the administration console, you will be presented with a global dashboard that you may customize once you have dismissed the "Tcat Server - Quick Start" panel.

![Figure 14.26: Customizable Tcat Admin Console](../../images/tcat-html_5d43770f.png)

First make sure that you have started the previously installed managed server. Clicking on the *Servers* tab in the console, you will see a server listed as *Unregistered*.

Unregistered servers are servers that have the Tcat management agent installed but are not added to the administration console. You may add the unregistered server by selecting either *Register* or *Register & Add To Group*.

If you choose to use *Register & Add To Group*, the server is added to the desired server group (e.g. Development). For the purpose of this guide, we'll register the server to the Development group.

![Figure 14.27: Monitoring a registered server](../../images/tcat-html_34603e60.png)

After registering the server, you will have access to view its health status, log files and more. Feel free to walk through the console to examine its capabilities, like reviewing log files, current thread status and deployed web applications.

More information is available at  *[http://www.mulesoft.org/documentation/display/TCAT/Home](http://www.mulesoft.org/documentation/display/TCAT/Home).*

**Managing and Monitoring Servers**

-   [Using the Server Dashboard](http://www.mulesoft.org/documentation/display/TCAT/Server+Dashboard)

-   [Working with Server Groups](http://www.mulesoft.org/documentation/display/TCAT/Working+with+Servers)

-   [Monitoring Servers (View Apps, Threads,     etc)](http://www.mulesoft.org/documentation/display/TCAT/Monitoring+a+Server)

-   [Monitoring a JMX Agent](http://www.mulesoft.org/documentation/display/TCAT/JMX+Agent+Monitoring)

-   [Scripts to Save JMX Metrics to CSV     Files](http://www.mulesoft.org/documentation/display/TCAT/Saving+JMX+Metric+Data+to+CSV+Files)

**Managing Tcat Web Applications**

-   [Monitoring Applications](http://www.mulesoft.org/documentation/display/TCAT/Monitoring+Applications)

-   [Deploying Applications](http://www.mulesoft.org/documentation/display/TCAT/Deploying+Applications)

-   [Using the Repository](http://www.mulesoft.org/documentation/display/TCAT/Managing+the+Repository)

-   [Setting Security on Repository Artifacts](http://www.mulesoft.org/documentation/display/TCAT/Setting+Security)

#### Deploying Liferay Portal [](id=lp-6-1-ugen11-deploying-liferay-portal-0)

As part of the installation process, the current version of Liferay Portal and a number of Liferay Plugins have been provisioned into the Tcat repository. You can view them by clicking on the "Repository" tab.

![Figure 14.28: Tcat Repository profiles](../../images/tcat-html_7e61df5a.png)

There are two components in the Tcat Repository:

-   **Profiles:** application profiles to be applied to the Tcat managed servers. Contains configuration, shared libraries and more. 
-   **Applications:** deployable WAR files for web applications and Liferay Plugins. 

![Figure 14.29: Tcat Repository: Applications](../../images/tcat-html_m4af27eb5.png)

To begin Liferay deployment, we must first create a Liferay license profile to be deployed to the managed server:

![Figure 14.30: Creating a new workspace for Liferay license profile](../../images/tcat-html_m2d96341c.png)


1. First create a new workspace under *Profiles*. Select *Profiles* in the left navigation and then click the *New         Workspace* button. 

2. Within the new workspace *Liferay Portal Trial License*, create another folder called `deploy`. 

3. In the `deploy` folder, select *New Artifact* and upload a new license file.

After completing the above, you have successfully uploaded your Liferay Portal license file into the Tcat repository. The final step is to create a server profile from the *Administration* tab.

*Server Profiles* shows the list of available Server Profiles and the option to create a new profile.

![Figure 14.31: Viewing server profiles](../../images/tcat-html_m5c7a2b8c.png)

When creating a server profile for the Liferay Portal Trial License, you will need to select the workspace folder created in the previous step. This ensures that when you apply the profile, Tcat uses the latest version of the license file.

Once you've created the server profile for your Liferay Portal EE license, you may begin deploying Liferay to the managed server.

![Figure 14.32: Apply Liferay Portal trial license](../../images/tcat-html_53af6680.png)

The first step is to apply two server profiles, one for the license and the other for Liferay Portal. Applying the license profile does not require a restart of the server.

![Figure 14.33: Apply Liferay profile](../../images/tcat-html_m1f86eaab.png)

The second profile to be applied is the Liferay server profile. In the example shown we applied the profile for Liferay Portal EE 6.1.

The Liferay Portal server profile requires a server restart. If you have the managed server configured as a Windows or Linux service, you may restart the server directly from the console by selecting the server and clicking "Restart".

With both profiles applied, we can now deploy the Liferay Portal WAR to the appropriate server.

The *Deployments* tab contains tools that assist in creating a deployment and targeting it to specific servers. Once you create a deployment, you may target it to any number of servers or deployment groups.

![Figure 14.34: Choosing a web application for deployment](../../images/tcat-html_m54d58d30.png)

When creating a deployment, you may choose a WAR file already uploaded into the repository or you may choose to upload a new WAR file. We recommend using a WAR file from the repository for non-development deployments. This ensures you consistently deploy the correct version to your environments.

Since we are deploying Liferay Portal for the first time, we choose the ROOT.war file.

After selecting the appropriate web application, you may choose which Servers to deploy to. In this example, we deploy to the Tomcat instance labeled *Liferay Portal Instance 1*. 

![Figure 14.35: Completing Liferay Portal deployment](../../images/tcat-html_68a00002.png)

After choosing to deploy the application, the Tcat console informs you of the current deployment status (e.g. "Successful"). Assuming you followed the previous steps and the deployment successfully completes, you will be able to access Liferay Portal on the target Tomcat instance.

![Figure 14.36: Accessing Liferay Portal on Liferay Portal Instance 1](../../images/tcat-html_2b08ac2c.png)

#### Deploying Liferay plugins on Mule Tcat  [](id=lp-6-1-ugen11-deploying-liferay-plugins-on-mule-tcat--0)

Liferay Portal Tcat Edition works with all the appropriate Liferay EE Plugins, including:

-   Kaleo workflow engine
-   Knowledge base
-   Chat
-   Private messaging

You may choose to deploy these plugins to the appropriate servers by following the same steps as for deploying Liferay Portal, specifying the appropriate plugin WAR file instead of the ROOT.war.

Other plugins that may be downloaded and added to this repository include:

-   Audit
-   Report engine and console
-   Drools integration.

You may download these plugins from the Liferay Customer Portal and manually add them to the Tcat repository.

![Figure 14.37: Uploading new plugins into Tcat](../../images/tcat-html_19e9e6d6.png)

Mule Tcat provides an enterprise grade solution to managing Tomcat servers. If you need a complete stack, combining Liferay Portal with Mule Tcat gives you everything you need to run a fully supported and robust portal environment for your enterprise. 

#### Installing Liferay on GlassFish 3 [](id=lp-6-1-ugen11-installing-liferay-on-glassfish-3-0)

*Liferay Home* is three folders above your GlassFish domain folder.

For example, if your domain location is `/glassfish-3.1-web/glassfish3/glassfish/domains/domain1`, Liferay Home is `/glassfish-3.1-web/glassfish3/`.

If you don't already have an existing GlassFish server, we recommend that you download a Liferay/GlassFish bundle from [http://www.liferay.com/downloads/liferay-portal/available-releases](http://www.liferay.com/downloads/liferay-portal/available-releases). If you have an existing GlassFish server or would like to install Liferay on GlassFish manually, please follow the steps below.

Before you begin, make sure you have downloaded the latest Liferay `.war` file and Liferay Portal dependencies from [http://www.liferay.com/downloads/liferay-portal/additional-files](http://www.liferay.com/downloads/liferay-portal/additional-files). The Liferay `.war` file should be called `liferay-portal-6.1.x-<date>.war` and the dependencies file should be called `liferay-portal-dependencies-6.1.x-<date>.zip`.

These instructions assume that you have already configured a domain and server and that you have access to the GlassFish administrative console.

Let's start out by installing the JAR files you will need.

##### Dependency Jars [](id=lp-6-1-ugen11-dependency-jars-0)

Liferay depends on jar files found in the Liferay Dependencies Archive. You should also have installed your database driver.

1. Navigate to the folder that corresponds to the domain in which you'll be installing Liferay. Inside this folder is a sub-folder named `lib` (e.g. `/glassfish-3.1-web/glassfish3/glassfish/domains/domain1/lib`).

Unzip the Liferay dependencies archive so that its `.jar` files are extracted into this `lib` folder.

Note, on GlassFish 3.0.1, you will need to extract `commons-codec.jar` from the Liferay Portal WAR file, rename it to `commons-codec-repackaged.jar`, and copy it into `[Liferay Home]/glassfish/modules/`, overwriting GlassFish's version of the file.

2. Make sure the JDBC driver for your database is accessible to GlassFish as well. Obtain the JDBC driver for your version of the database server. In the case of MySQL, use `mysql-connector-java-{$version}-bin.jar`. You can download the latest MySQL JDBC driver from [http://www.mysql.com/products/connector/](http://www.mysql.com/products/connector/).
Extract the JAR file and copy it to `lib`.

3.	Next, you will need apply several patches to Glassfish `.jar` files due to the following known issues. The best way to get the appropriate versions of these files is to download the Liferay source code and get them from there. Once you have downloaded the Liferay source, unzip the source into a temporary folder. We'll refer to the location of the Liferay source as `$LIFERAY_SOURCE`.

	<!-- | TODO: We have to streamline this somehow, as these instructions aren't going to be acceptable to most large enterprises. | -->

	-	For issue [GLASSFISH-17242](http://java.net/jira/browse/GLASSFISH-17242) / [LPS-22813](http://issues.liferay.com/browse/LPS-22813)

		-	Copy

				$LIFERAY_SOURCE/tools/servers/glassfish/patches/GLASSFISH-17242/classes/org/glassfish/deployment/admin/InstanceDeployCommand.class

			into

				org/glassfish/deployment/admin/

			of

				$GLASSFISH_HOME/modules/deployment-admin.jar

		-	Copy

				$LIFERAY_SOURCE/tools/servers/glassfish/patches/GLASSFISH-17242/classes/com/sun/enterprise/deployment/deploy/shared/Util.class

			into

				com/sun/enterprise/deployment/deploy/shared/

			of

				$GLASSFISH_HOME/modules/deployment-common.jar

		-	Copy

				$LIFERAY_SOURCE/tools/servers/glassfish/patches/GLASSFISH-17242/classes/com/sun/enterprise/v3/server/SnifferManagerImpl$1.class

			*and*

				$LIFERAY_SOURCE/tools/servers/glassfish/patches/GLASSFISH-17242/classes/com/sun/enterprise/v3/server/SnifferManagerImpl.class

			into

				`com/sun/enterprise/v3/server/`

			of

				`$GLASSFISH_HOME/modules/kernel.jar`

	-	For issue [GRIZZLY-1060](http://java.net/jira/browse/GRIZZLY-1060):

		-	Copy

				$LIFERAY_SOURCE/tools/servers/glassfish/patches/GRIZZLY-1060/classes/com/sun/grizzly/util/OutputWriter.class

			into

				com/sun/grizzly/util/

			of

				$GLASSFISH_HOME/modules/grizzly-utils.jar

Terrific, you have your JAR files just where you'll need them. Next we'll configure your domain.

##### Domain Configuration [](id=lp-6-1-ugen11-domain-configuration-0)

There are a couple of modifications you need to make in your domain to use Liferay Portal.

1. Before starting GlassFish, modify your domain's configuration to do the following: 

	- Set the file encoding
	- Set the user time-zone
	- Set the preferred protocol stack
	- Prevent the application server from setting static fields (final or non-final) to `null`
	- Increase the default amount of memory available.

	Modify `/glassfish-3.1-web/glassfish3/glassfish/domains/domain1/config/domain.xml` merging in the following JVM options into the current list of JVM options within your `<java-config>` element:

		<jvm-options>-Dfile.encoding=UTF8</jvm-options> 
		<jvm-options>-Djava.net.preferIPv4Stack=true</jvm-options>
		<jvm-options>-Dorg.apache.catalina.loader.WebappClassLoader.ENABLE_CLEAR_REFERENCES=false</jvm-options>
		<jvm-options>-Duser.timezone=GMT</jvm-options>
		<jvm-options>-Xmx1024m</jvm-options>
		<jvm-options>-XX:MaxPermSize=512m</jvm-options>

	Be sure that any existing options with values such as `-Dfile.encoding`, `-Djava.net.preferIPv4Stack`, `-Dorg.apache.catalina.loader.WebappClassLoader.ENABLE_CLEAR_REFERENCES`, `-Duser.timezone` or `-XX:MaxPermSize` are replaced with the new values listed above.

	For example, replace: 

		<jvm-options>-Xmx256m</jvm-options>
	
	with this: 
	
		<jvm-options>-Xmx1024m</jvm-options>

2. Delete, rename or move the `domain1/docroot/index.html` file to another location to allow your Liferay Portal default page to be displayed.

Next, let's get your database configured.

##### Database Configuration [](id=lp-6-1-ugen11-database-configuration-0)

If you want to use GlassFish to manage your domain's data source, follow the instructions found in this section. If you want to use Liferay Portal to manage your data source, you can skip this section.

1. Startup your domain's application server, if it is not already running.

2.	Go to the GlassFish console URL: [http://localhost:4848](http://localhost:4848/). 

3. Under *Common Tasks*, navigate to *Resources* &rarr; *JDBC* &rarr; *JDBC Connection Pools*

![Figure 14.38: Navigate to JDBC Connection Pools](../../images/11-glassfish31-connection-pools.png)

4.	Click *New...*.

5. In the first screen (Step 1 of 2), give your connection pool the name `LiferayPool`, the resource type of `javax.sql.ConnectionPoolDataSource` and select your database driver vendor (e.g. `MySQL`) as follows:

![Figure 14.39: Glassfish JDBC Connection Pool](../../images/11-glassfish-31-jdbc-connection-pool.png)

6.	Click *Next* to advance to the next step in creating your JDBC connection pool.

7. On the this screen (Step 2 of 2), scroll down to the *Additional Properties* section.

![Figure 14.40: Glassfish JDBC Connection Pool Properties](../../images/11-glassfish-31-jdbc-connection-pool-props.png)  

8.	Replace or add the following properties ...

	- **URL:** the URL of your connection pool.

		For example,

			jdbc:mysql://localhost/lportal?useUnicode=true&amp;characterEncoding=UTF-8&amp;emulateLocators=true
	
		Note, if you are using the above example, you should specify the name of your database in place of `lportal`. Likewise, if your database is not on the same host as GlassFish, specify your the database server's host name in place of `localhost`. Lastly, specify your database type in place of `jdbc:mysql`.

	- **user:** the name of your database user.

	- **password:** your database user's password.

10. Click *Finish*.

	You should now see your `LiferayPool` connection pool listed under *Resources* &rarr; *JDBC* &rarr; *JDBC Connection Pools*

11. Test your connection by selecting your `LiferayPool` connection pool and clicking *Ping*.

	If you get a message stating  *Ping Succeeded*, you've succeeded in setting up a connection pool of your data source!

13. Now, you'll setup a JDBC resource to refer to the `LiferayPool` connection pool you just created.

14.	Navigate to *Resources* &rarr; *JDBC* &rarr; *JDBC Resources* to show the current JDBC resources listed by their JNDI names.

14. Click *New...*.

15. Set the JNDI name to `jdbc/LiferayPool` and select `LiferayPool` as the pool name.

16. Click *OK*.

Congratulations! You've now configured your domain's data source on GlassFish!

##### Mail Configuration [](id=lp-6-1-ugen11-mail-configuration-0)

If you want to use GlassFish to manage your mail session, follow GlassFish's documentation on configuring a JavaMail session with a JNDI name of `mail/MailSession`. If you want to use Liferay Portal to manage your mail session, you can skip this step.

##### Domain Configuration - Continued [](id=lp-6-1-ugen11-domain-configuration---continued-0)

Let's tie up some loose ends with regards to Liferay being able to access your database and mail session.

1.	Shutdown your domain's application server if it is currently running.

2. Create a `portal-ext.properties` file in the *Liferay Home* folder mentioned at the beginning of this GlassFish installation section.

3.	If you are using *Glassfish* to manage your data source, add the following to your `portal-ext.properties` file in your *Liferay Home* to refer to your data source:

		jdbc.default.jndi.name=jdbc/LiferayPool

	Otherwise, if you are using *Liferay Portal* to manage your data source, follow the instructions in the *Deploy Liferay* section for using the setup wizard.

4.	If want to use *Liferay Portal* to manage your mail session, you can configure the mail session within Liferay Portal. That is, after starting your portal as described in the *Deploy Liferay* section, go to *Control Panel &rarr; Server Administration &rarr; Mail* and enter the settings for your mail session.

	If you are using *GlassFish* to manage your mail session, add the following to your `portal-ext.properties` file to reference that mail session:

		mail.session.jndi.name=mail/MailSession

Liferay can now communicate with your database and mail session. So let's go ahead and deploy Liferay.

##### Deploy Liferay [](id=lp-6-1-ugen11-deploy-liferay-0)

Here are the steps you'll need to follow to deploy Liferay Portal to your domain's server. Before you deploy Liferay Portal, let's consider whether you want to also start the setup wizard.

-	**Start the setup wizard along with Liferay Portal** - Do this if you want to configure your portal, set up your site's administrative account and/or manage your database within Liferay.
		
	If this is your first time starting Liferay Portal 6.1, the setup wizard is automatically invoked. If you want to re-run the wizard, specify `setup.wizard.enabled=true` in your properties file (e.g. `portal-setup-wizard.properties`).

		setup.wizard.enabled=true

	The setup wizard is then invoked during server startup.

-	**Start Liferay Portal without invoking the setup wizard** - Do this if want to preserve your current portal settings.

	To startup the server without triggering the setup wizard, specify `setup.wizard.enabled=false` in your properties (e.g. `portal-setup-wizard.properties` or `portal-ext.properties` file).

		setup.wizard.enabled=false

	Once you run the setup wizard, the `portal-setup-wizard.properties` file it creates already has `setup.wizard.enabled=false` conveniently specified for you.

---

![Note](../../images/tip.png) Property values in `portal-setup-wizard.properties` override property values in `portal-ext.properties`.

---

1. Start your domain's application server.

2. Go to the GlassFish console URL: [http://localhost:4848](http://localhost:4848/)

3. Click *Applications* in the tree on the left.

4. Click *Deploy*.

5. Under *Packaged File to Be Uploaded to the Server*, click *Choose File* and browse to the location of the Liferay Portal `.war` file. Enter *Context Root:* `/`

6.	Enter *Application Name:* `liferay-portal`

7. Click *OK*.

<!-- This figure below seems to be missing   -->

![Figure 14.41: Deploying Liferay in GlassFish 3.1.x](../../images/11-deploying-liferay-in-glassfish-31.png)

-	If you disabled the setup wizard, your site's home page opens in your browser at [http://localhost:8080](http://localhost:8080).

-	Otherwise, the setup wizard opens in your browser.

See the section on the setup wizard above for how to use the setup wizard. 

Your installation of Liferay Portal on GlassFish is complete!

#### Installing Liferay on Jetty 7 [](id=lp-6-1-ugen11-installing-liferay-on-jetty-7-0)

**Liferay Home** is one folder above Jetty's install location.

For this section, we'll refer to Jetty server's installation location as `$JETTY_HOME`. If you do not already have an existing Jetty server, we recommend you download a Liferay/Jetty bundle from [http://www.liferay.com/downloads/liferay-portal/available-releases](http://www.liferay.com/downloads/liferay-portal/available-releases). If you have an existing Jetty server or would like to install Liferay on Jetty manually, please follow the steps below.

Before you begin, make sure you have downloaded the latest Liferay `.war` file and Liferay Portal dependencies from [http://www.liferay.com/downloads/liferay-portal/additional-files](http://www.liferay.com/downloads/liferay-portal/additional-files). The Liferay `.war` file should be called `liferay-portal-6.1.x-<date>.war` and the dependencies file should be called `liferay-portal-dependencies-6.1.x-<date>.zip`.

Now that you have all of your installation files, you're ready to start installing and configuring Liferay on Jetty.

##### Dependency Jars [](id=lp-6-1-ugen11-dependency-jars-1)

Let's work with the Liferay depenency jar files first.

1.	Create folder `$JETTY_HOME/lib/ext/liferay`.

2.	Unzip the jar files found in the Liferay Portal Dependencies zip file to your `$JETTY_HOME/lib/ext/liferay` folder. Take care to extract the zip file's `.jar` files directly into this folder.

3.	Next, you need several `.jar` files which are included as part of the Liferay source distribution. Many application servers ship with these already on the class path but Jetty does not. The best way to get the appropriate versions of these files is to download the Liferay source code and get them from there. Once you have downloaded the Liferay source, unzip the source into a temporary folder. We'll refer to the location of the Liferay source as `$LIFERAY_SOURCE`.

	Copy the following jars from `$LIFERAY_SOURCE/lib/development` to your `$JETTY_HOME/lib/ext/liferay` folder:
	
	- 	`activation.jar`
	-	`jta.jar`
	-	`mail.jar`
	-	`persistence.jar`

4.	Make sure the JDBC driver for your database is accessible to Jetty. Obtain the JDBC driver for your version of the database server. In the case of MySQL, use `mysql-connector-java-{$version}-bin.jar`. You can download the latest MySQL JDBC driver from [http://www.mysql.com/products/connector/](http://www.mysql.com/products/connector/).
Extract the JAR file and copy it to `$JETTY_HOME/lib/ext/liferay`.

Now that your `.jar` files are in place, let's configure your domain.

##### Jetty Configuration [](id=lp-6-1-ugen11-jetty-configuration-0)

To get Jetty ready for running Liferay Portal, you must make a number of modifications that involve configuration files, initialization files and run scripts.

1.	Create a `start.config` file to modify the behavior of Jetty's `start.jar`. It's best to base your `start.config` file on the default one found in `start.jar`.

	1.	Extract the default start config file `org/eclipse/jetty/start/start.config` from the `start.jar` into `$JETTY_HOME/etc` so you have the file `$JETTY_HOME/etc/start.config`.
	
	2.	Add the following property assignment to `$JETTY_HOME/etc/start.config` to specify where Jetty should write its logs:

			jetty.logs=$(jetty.home)/logs

	3.	Add the following directive to `$JETTY_HOME/etc/start.config` to load all of the `.jar` and `.zip` files found in your `$JETTY_HOME/lib/liferay` folder into your class path:

			$(jetty.home)/lib/liferay/*

	Now that your class loading is specified, let's create initialization files and run scripts that invoke these configuration directives during startup of Jetty

2.	Create initialization file: `$JETTY_HOME/bin/start.ini`

		START=../etc/start.config
		OPTIONS=Server,jsp,resources
		
		../etc/jetty.xml
		../etc/jetty-deploy.xml
		../etc/jetty-webapps.xml
		../etc/jetty-contexts.xml
		../etc/jetty-testrealm.xml

	This initialization file does the following:
	
	-	Sets `$JETTY_HOME/etc/start.config` as your starting configuration file.
	-	Sets your server options.
	-	Specifies a sequence of deployment descriptor files to be processed.

3.	Create a run script appropriate to your operating system:

	-	On Windows, create: `$JETTY_HOME/bin/run.bat`

			@echo off

			if "" == "%JAVA_HOME%" goto errorJavaHome

			set "JAVA_OPTS=-Djetty.version=7.5.4 -Djetty.version.date=20111024 -Dfile.encoding=UTF8 -Djava.io.tmpdir=../temp -Djava.net.preferIPv4Stack=true -Duser.timezone=GMT -Xmx1024m -XX:MaxPermSize=256m"

			"%JAVA_HOME%/bin/java" %JAVA_OPTS% -jar ../start.jar

			goto end

			:errorJavaHome
				echo JAVA_HOME not defined.

				goto end

			:end

	-	On Unix/Linux, create: `$JETTY_HOME/bin/run.sh`

			#!/bin/sh

			if [ ! $JAVA_HOME ]
			then
				echo JAVA_HOME not defined.
				exit
			fi

			export JAVA_OPTS="-Djetty.version=7.5.4 -Djetty.version.date=20111024 -Dfile.encoding=UTF8 -Djava.io.tmpdir=../temp -Djava.net.preferIPv4Stack=true -Duser.timezone=GMT -Xmx1024m -XX:MaxPermSize=256m"

			$JAVA_HOME/bin/java $JAVA_OPTS -jar ../start.jar

3.	Create a context file `$JETTY_HOME/contexts/root.xml` to specify the context, classpath and resource base of your web application:

		<?xml version="1.0"?>
		<!DOCTYPE Configure PUBLIC "-//Mort Bay Consulting//DTD Configure//EN" "http://jetty.mortbay.org/configure.dtd">

		<Configure class="org.eclipse.jetty.webapp.WebAppContext">
			<Set name="contextPath">/</Set>
			<Set name="extraClasspath"><SystemProperty name="jetty.home" />/lib/jetty-server-<SystemProperty name="jetty.version" />.v<SystemProperty name="jetty.version.date" />.jar,<SystemProperty name="jetty.home" />/lib/jetty-util-<SystemProperty name="jetty.version" />.v<SystemProperty name="jetty.version.date" />.jar,<SystemProperty name="jetty.home" />/lib/jetty-webapp-<SystemProperty name="jetty.version" />.v<SystemProperty name="jetty.version.date" />.jar</Set>
			<Set name="resourceBase"><SystemProperty name="jetty.home" />/webapps/root</Set>
		</Configure>

4.	Lastly, create the following folders:

	- `$JETTY_HOME/logs` - for log files

	- `$JETTY_HOME/temp` - for temporary files. Note, this folder is specified to our JVM as a temporary folder in the run script you created previously.

Now that your general Jetty startup files are set in place, let's consider how you will manage your data source. 

##### Database Configuration [](id=lp-6-1-ugen11-database-configuration-1)

If you want to manage your data source within Jetty, continue following the instructions in this section. If you want to use the built-in Liferay data source, you can skip this section.

1.	Management of databases in Jetty is done via the file `$JETTY_HOME/etc/jetty.xml`. Edit `jetty.xml` and insert the following text within the root element `<Configure>` to specify the data pool for your data source. Be sure to pass in value `jdbc/LiferayPool` as the second argument.

		<New id="LiferayPool" class="org.eclipse.jetty.plus.jndi.Resource">
			<Arg></Arg>
			<Arg>jdbc/LiferayPool</Arg>
			<Arg>
				<New class="com.mysql.jdbc.jdbc2.optional.MysqlConnectionPoolDataSource">
					<Set name="Url">jdbc:mysql://localhost/lportal?
		useUnicode=true&amp;characterEncoding=UTF-8</Set>
					<Set name="User">root</Set>
					<Set name="Password">root</Set>
				</New>
			</Arg>
		</New>

	Be sure to replace the URL database value (i.e. `lportal`), user value and password value with values specific to your database.

2.	Your data pool needs Jetty's JNDI and Jetty Plus libraries loaded to access those classes at runtime. Your `$JETTY_HOME/etc/start.config` file should have sections that load these libraries as long as `jndi` and `plus` *options* are specified at startup.

	To set these options, edit your `$JETTY_HOME/bin/start.ini` file and add `jndi` and `plus` as values for the `OPTIONS` variable:

		OPTIONS=Server,jsp,resources,jndi,plus

Super! Now you have your database specified and ready for use with Liferay on Jetty. Let's consider your mail session next.

##### Mail Configuration [](id=lp-6-1-ugen11-mail-configuration-1)

If you want to manage your mail session within Jetty, use the following instructions. If you want to use the built-in Liferay mail session, you can skip this section.

Management of mail sessions in Jetty is done via the configuration file `$JETTY_HOME/etc/jetty.xml`. Edit `jetty.xml` and insert the following text within the root element `<Configure>` to specify your mail session. Be sure to pass in value `mail/MailSession` as the first argument and to replace the mail session values with your own.

	<New id="MailSession" class="org.eclipse.jetty.plus.jndi.Resource">
		<Arg>mail/MailSession</Arg>
		<Arg>
			<New class="org.eclipse.jetty.jndi.factories.MailSessionReference">
				<Set name="user"></Set>
				<Set name="password"></Set>
				<Set name="properties">
					<New class="java.util.Properties">
						<Put name="mail.pop3.host">pop.gmail.com</Put>
						<Put name="mail.pop3.port">110</Put>
						<Put name="mail.smtp.host">smtp.gmail.com</Put>
						<Put name="mail.smtp.password">password</Put>
						<Put name="mail.smtp.user">user</Put>
						<Put name="mail.smtp.port">465</Put>
						<Put name="mail.transport.protocol">smtp</Put>
						<Put name="mail.smtp.auth">true</Put>
						<Put name="mail.smtp.starttls.enable">true</Put>
						<Put name="mail.smtp.socketFactory.class">javax.net.ssl.SSLSocketFactory</Put>
						<Put name="mail.imap.host">imap.gmail.com</Put>
						<Put name="mail.imap.port">993</Put>
						<Put name="mail.store.protocol">imap</Put>
					</New>
				</Set>
			</New>
		</Arg>
	</New>

Great! Now you'll be able to use this mail session with Liferay.

##### Configuring data sources and mail sessions [](id=lp-6-1-ugen11-configuring-data-sources-and-mail-sessions-0)

Let's revisit your configuration to make sure we'll be able to access your data source and mail session from Liferay Portal.

1. First, navigate to the *Liferay Home* folder, which is one folder above Jetty's install location. Create a file named `portal-ext.properties`.

2. If you are using *Jetty* to manage your data source, add the following to your `portal-ext.properties` file in your *Liferay Home* to refer to your data source:

		jdbc.default.jndi.name=jdbc/LiferayPool

	Otherwise, if you are using *Liferay Portal* to manage your data source, follow the instructions in the *Deploy Liferay* section for using the setup wizard.

3.	If want to use *Liferay Portal* to manage your mail session, you can configure the mail session within Liferay Portal. That is, after starting your portal as described in the *Deploy Liferay* section, go to *Control Panel &rarr; Server Administration &rarr; Mail* and enter the settings for your mail session.

	Otherwise, if you are using *Jetty* to manage your mail session, add the following to your `portal-ext.properties` file to reference that mail session:

		mail.session.jndi.name=mail/MailSession

Let's start your server and deploy Liferay Portal! 

##### Deploy Liferay [](id=lp-6-1-ugen11-deploy-liferay-1)

Liferay can be deployed as an exploded web archive within `$JETTY_HOME/webapps`.

1.	If you already have an application folder `$JETTY_HOME/webapps/root`, delete it or move it to a location outside of `$JETTY_HOME/webapps`.

2.	Then extract the contents of the Liferay portal `.war` file into `$JETTY_HOME/webapps/root`.

3.	Before you start Liferay Portal, let's consider whether you want to also start the setup wizard.

	-	**Start the setup wizard along with Liferay Portal** - Do this if you want to configure your portal, setup your site's administrative account and/or manage your database within Liferay.
		
		If this is your first time starting Liferay Portal 6.1, the setup wizard is invoked on server startup. If you want to re-run the wizard, specify `setup.wizard.enabled=true` in your properties file (e.g. `portal-setup-wizard.properties`).

			setup.wizard.enabled=true

		The setup wizard is invoked during server startup.

	-	**Start Liferay Portal without invoking the setup wizard** - Do this if want to preserve your current portal settings.

		To start the server without triggering the setup wizard, specify `setup.wizard.enabled=false` in your properties (e.g. `portal-setup-wizard.properties` or `portal-ext.properties` file).

			setup.wizard.enabled=false

		The `portal-setup-wizard.properties` file the setup wizard creates has `setup.wizard.enabled=false` conveniently specified for you.

	---

	![Note](../../images/tip.png) Property values in `portal-setup-wizard.properties` override property values in `portal-ext.properties`.

	---

	Now its time to launch Liferay Portal!

4.	Start Liferay Portal by executing `run.bat` (Windows) or `run.sh` (Unix/Linux) script from `$JETTY_HOME/bin`.

    -	If the setup wizard was disabled, your site's home page opens in your browser at [http://localhost:8080](http://localhost:8080).

    -	Otherwise, the setup wizard opens in your browser.

See the section on the setup wizard above for more information about the setup wizard. 

You've just installed and deployed Liferay Portal on Jetty - way to go!

#### Installing Liferay on JBoss 7 [](id=lp-6-1-ugen11-installing-liferay-on-jboss-7-0)

**Liferay Home** is one folder above JBoss's install location.

1. Download and install JBoss AS 7.0.x into your preferred directory. This directory is referred to as `$JBOSS_HOME` throughout this section.

2. Download the latest version of the Liferay Portal `.war` file.

3.	Download Liferay's Portal Dependencies.

Now that you have all of your installation files, you are ready to start installing and configuring Liferay on JBoss.

##### Dependency Jars [](id=lp-6-1-ugen11-dependency-jars-2)

Let's work with the dependency jar files first.

1.	Create folder `$JBOSS_HOME/modules/com/liferay/portal/main` and unzip the jar files found in the Liferay Portal Dependencies zip file to this folder. Make sure the zip file's jar files are extracted into the `$JBOSS_HOME/modules/com/liferay/portal/main` folder.

2.	Download your database driver `.jar` file and put it into the `$JBOSS_HOME/modules/com/liferay/portal/main` folder as well. For demonstration purposes, we'll download the MySQL Connector/J driver from [http://dev.mysql.com/downloads/connector/j/](http://dev.mysql.com/downloads/connector/j/) and put its `.jar` file into the `$JBOSS_HOME/modules/com/liferay/portal/main` folder.

3.	Create the file `module.xml` in the `$JBOSS_HOME/modules/com/liferay/portal/main` folder and insert the following contents.

		<?xml version="1.0"?>

		<module xmlns="urn:jboss:module:1.0" name="com.liferay.portal">
			<resources>
				<resource-root path="mysql-connector-java-5.1.18-bin.jar" />
				<resource-root path="portal-service.jar" />
				<resource-root path="portlet.jar" />
			</resources>
			<dependencies>
				<module name="javax.api" />
				<module name="javax.mail.api" />
				<module name="javax.servlet.api" />
				<module name="javax.servlet.jsp.api" />
				<module name="javax.transaction.api" />
			</dependencies>
		</module>

	If you're using a different database driver, replace the path of the MySQL resource root entry with that of your database driver.
		
Great! You have your Jar files ready for your domain.

##### Configuring JBoss [](id=lp-6-1-ugen11-configuring-jboss-0)

Let's make some adjustments in your configuration to support using Liferay.

You can specify the JBoss server instance's configuration in the XML file `$JBOSS_HOME/standalone/configuration/standalone.xml`. We'll refer to this file simply as `standalone.xml`. You must also make some modifications to your configuration and startup scripts found in the `$JBOSS_HOME/bin/` folder. But let's start with the changes to `standalone.xml`.

1.	Make the following modifications to `standalone.xml`.

	1. Disable the welcome root of the web subsystem's virtual server default host by specifying `enable-welcome-root="false"`.

			<subsystem xmlns="urn:jboss:domain:web:1.0" default-virtual-server="default-host">
				<connector name="http" scheme="http" protocol="HTTP/1.1" socket-binding="http"/>
				<virtual-server name="default-host" enable-welcome-root="false">
				   <alias name="localhost" />
				   <alias name="example.com" />
				</virtual-server>
			</subsystem>
			
	2.	Insert the following `<configuration>` element within the web subsystem element  `<subsystem xmlns="urn:jboss:domain:web:1.0" default-virtual-server="default-host">`.

			<configuration>
				<jsp-configuration development="true" />
			</configuration>

	3.	Add a timeout for the deployment scanner by setting `deployment-timeout="120"` as seen in the excerpt below.

			<subsystem xmlns="urn:jboss:domain:deployment-scanner:1.0">
				<deployment-scanner name="default" path="deployments" scan-enabled="true" scan-interval="5000" relative-to="jboss.server.base.dir" deployment-timeout="120"/>
			</subsystem>

	4.	Add the following JAAS security domain to the security subsystem `<security-domains>` defined in element `<subsystem xmlns="urn:jboss:domain:security:1.0">`.

			<security-domain name="PortalRealm">
				<authentication>
					<login-module code="com.liferay.portal.security.jaas.PortalLoginModule" flag="required"/>
				</authentication>
			</security-domain>

	Now it's time for some changes to your configuration and startup scripts.
		
2.	Make the following modifications to your standalone domain's configuration script file `standalone.conf` (`standalone.conf.bat` on Windows) found in your `$JBOSS_HOME/bin/` folder.

	The purpose of these modifications is to do the following:

	- Set the file encoding
	- Set the user time-zone
	- Set the preferred protocol stack
	- Increase the default amount of memory available.

	Make the following edits as applicable to your operating system:

	-	On Windows, comment out the initial `JAVA_OPTS` assignment as demonstrated in the following line:

			rem set "JAVA_OPTS=-Xms64M -Xmx512M -XX:MaxPermSize=256M

		Then add the following `JAVA_OPTS` assignment one line above the `:JAVA_OPTS_SET` line found at end of the file:

			set "JAVA_OPTS=%JAVA_OPTS% -Dfile.encoding=UTF-8 -Djava.net.preferIPv4Stack=true -Duser.timezone=GMT -Xmx1024m -XX:MaxPermSize=256m"


	-	On Unix, merge the following values into your settings for `JAVA_OPTS` replacing any matching attributes with the ones found in the assignment below:

			JAVA_OPTS="$JAVA_OPTS -Dfile.encoding=UTF-8 -Djava.net.preferIPv4Stack=true -Duser.timezone=GMT -Xmx1024m -XX:MaxPermSize=256m
		
The prescribed script modifications are now complete for your Liferay installation on JBoss. Next we'll consider the database and mail configuration. 

##### Database Configuration [](id=lp-6-1-ugen11-database-configuration-2)

If you want JBoss to manage your data source, follow the instructions in this section. If you want to use the built-in Liferay data source, you can skip this section.

Modify `standalone.xml` adding your data source and driver within the `<datasources>` element of your data sources subsystem.

1.	First, add your data source within the `<datasources>` element.

		<datasource jndi-name="java:/jdbc/LiferayPool" pool-name="LiferayPool" enabled="true" jta="true" use-java-context="true" use-ccm="true">
			<connection-url>
				jdbc:mysql://localhost/lportal
			</connection-url>
			<driver>
				mysql
			</driver>
			<security>
				<user-name>
					root
				</user-name>
				<password>
					root
				</password>
			</security>
		</datasource>

	Be sure to replace the URL database value (i.e. `lportal`), user value and password value with values specific to your database.

2.	Then add your driver to the `<drivers>` element also found within the `<datasources>` element.

		<drivers>
			<driver name="mysql" module="com.liferay.portal"/>
		</drivers>

	Your final data sources subsystem should look something like this:

		<subsystem xmlns="urn:jboss:domain:datasources:1.0">
			<datasources>
				<datasource jndi-name="java:/jdbc/LiferayPool" pool-name="LiferayPool" enabled="true" jta="true" use-java-context="true" use-ccm="true">
					<connection-url>
						jdbc:mysql://localhost/lportal
					</connection-url>
					<driver>
						mysql
					</driver>
					<security>
						<user-name>
							root
						</user-name>
						<password>
							root
						</password>
					</security>
				</datasource>
				<drivers>
					<driver name="mysql" module="com.liferay.portal"/>
				</drivers>
			</datasources>
		</subsystem>

Now that you've configured your data source, let's go over how to configure your mail session within JBoss.

##### Mail Configuration [](id=lp-6-1-ugen11-mail-configuration-2)

At the time this document was written, JavaMail was not yet supported in JBoss AS 7.0.1 - however, it was implemented in the JBoss AS 7.1 alpha (see [https://issues.jboss.org/browse/AS7-1177](https://issues.jboss.org/browse/AS7-1177). If you want JBoss to manage your mail session, use the following instructions which are based on the implementation found in JBoss AS 7.1 alpha. If you want to use the built-in Liferay mail session, you can skip this section.

Specify your mail subsystem  in `standalone.xml` as in the following example:

	<subsystem xmlns="urn:jboss:domain:mail:1.0">
		<mail-session jndi-name="java:/mail/MailSession" >
			<smtp-server address="smtp.gmail.com" port="465">
				   <login name="username" password="password"/>
			</smtp-server>
			<pop3-server address="pop.gmail.com" port="110"/>
			<imap-server address="imap.gmail.com" port="993">
				<login name="username" password="password"/>
			</imap-server>
	   </mail-session>
	</subsystem>

You've got mail! Next, we'll make sure Liferay is configured to properly connect with your new mail session and database.

##### Configuring data sources and mail sessions [](id=lp-6-1-ugen11-configuring-data-sources-and-mail-sessions-1)

Now that your data source and mail session are set up, you need to ensure Liferay Portal can access them.

1. First, navigate to the Liferay Home folder, which is one folder above JBoss's install location (i.e. `$JBOSS/..`).

2. If you're using *JBoss* to manage your data source, add the following to your `portal-ext.properties` file in your *Liferay Home* to refer to your data source:

		jdbc.default.jndi.name=java:jdbc/LiferayPool

	If you're using *Liferay Portal* to manage your data source, follow the instructions in the *Deploy Liferay* section for using the setup wizard.

3.	If you're using *Liferay Portal* to manage your mail session, this configuration is done within Liferay Portal. That is, after starting your portal as described in the *Deploy Liferay* section, go to *Control Panel &rarr; Server Administration &rarr; Mail* and enter the settings for your mail session.

	If you're using *JBoss* to manage your mail session, add the following to your `portal-ext.properties` file to reference that mail session:

		mail.session.jndi.name=java:mail/MailSession

You've completed the steps necessary for your deployment of Liferay so Liferay Portal can now communicate with your data source and mail session--way to go! Now you're ready to deploy Liferay Portal.

##### Deploy Liferay [](id=lp-6-1-ugen11-deploy-liferay-2)

1. If the folder `$JBOSS_HOME/standalone/deployments/ROOT.war` already exists in your JBoss installation, delete all of its subfolders and files. Otherwise, create a new folder `$JBOSS_HOME/standalone/deployments/ROOT.war`.

2. Unzip the Liferay `.war` file into the `ROOT.war` folder.

3.	To trigger deployment of `ROOT.war`, create an empty file named `ROOT.war.dodeploy` in  your `$JBOSS_HOME/standalone/deployments/` folder. On startup, JBoss detects the presence of this file and deploys it as a web application.

4.	Remove `eclipselink.jar` from  `$JBOSS_HOME/standalone/deployments/ROOT.war/WEB-INF/lib` to assure the Hibernate persistence provider is used instead of the one provided in the `eclipselink.jar`. Note, JBoss 7.0.2 has a known issue [http://community.jboss.org/thread/169944](http://community.jboss.org/thread/169944) in determining which persistence provider to use.

5.	Before you start Liferay Portal, let's consider whether you want to also start the setup wizard.

	-	**Start the setup wizard along with Liferay Portal** - Do this if you want to configure your portal, set up your site's administrative account and/or manage your database within Liferay.
		
		If this is your first time starting Liferay Portal 6.1, the setup wizard is invoked on server startup. If you want to re-run the wizard, specify `setup.wizard.enabled=true` in your properties file (e.g. `portal-setup-wizard.properties`).

			setup.wizard.enabled=true

		The setup wizard is invoked during server startup.

	-	**Start Liferay Portal without invoking the setup wizard** - Do this if want to preserve your current portal settings.

		To start the server without triggering the setup wizard, specify `setup.wizard.enabled=false` in your properties (e.g. `portal-setup-wizard.properties` or `portal-ext.properties` file).

			setup.wizard.enabled=false

		The `portal-setup-wizard.properties` file the setup wizard creates has `setup.wizard.enabled=false` conveniently specified for you.

	---

	![Note](../../images/tip.png) Property values in `portal-setup-wizard.properties` override property values in `portal-ext.properties`.

	---
	
	Now it's time to start Liferay Portal on JBoss!

6.	Start the JBoss application server.

	-	If the setup wizard was disabled, your site's home page opens automatically in your browser at [http://localhost:8080](http://localhost:8080).

	-	Otherwise, the setup wizard opens in your browser.

See the section on the setup wizard above for how to use Liferay's setup wizard. 

Now you are truly *the boss* when it comes to deploying Liferay Portal on JBoss!

#### Installing Liferay on Resin 4 [](id=lp-6-1-ugen11-installing-liferay-on-resin-4-0)

**Liferay Home** is one folder above Resin's install location.

For this section, we will refer to your Resin server's installation location as `$RESIN_HOME`. If you do not already have an existing Resin server, we recommend you download a Liferay/Resin bundle from [http://www.liferay.com/downloads/liferay-portal/available-releases](http://www.liferay.com/downloads/liferay-portal/available-releases). If you have an existing Resin server or would like to install Liferay on Resin manually, please follow the steps below.

Before you begin, make sure you have downloaded the latest Liferay `.war` file and Liferay Portal dependencies from [http://www.liferay.com/downloads/liferay-portal/additional-files](http://www.liferay.com/downloads/liferay-portal/additional-files). The Liferay `.war` file should be called `liferay-portal-6.1.x-<date>.war` and the dependencies file should be called `liferay-portal-dependencies-6.1.x-<date>.zip`.

Now that you have all of your installation files, you are ready to start installing and configuring Liferay on Resin.

##### Dependency Jars [](id=lp-6-1-ugen11-dependency-jars-3)

Let's work with the depenency jar files first.

1.	Unzip the jar files found in the Liferay Portal Dependencies zip file to your `$RESIN_HOME/ext-lib` folder. Take care to extract the zip file's `.jar` files directly into this folder.

2.	Next, you need several `.jar` files which are included as part of the Liferay source distribution. Many application servers ship with these already on the class path but Resin does not. The best way to get the appropriate versions of these files is to download the Liferay source code and get them from there. Once you have downloaded the Liferay source, unzip the source into a temporary folder. We'll refer to the location of the Liferay source as `$LIFERAY_SOURCE`.

	1. Go to `$LIFERAY_SOURCE/lib/development` and copy `saxpath.jar` into `$RESIN_HOME/lib`.

	2. Go to `$LIFERAY_SOURCE/lib/portal` and copy `log4j.jar`, `slf4j-api.jar` and `slf4j-log4j12.jar` into `$RESIN_HOME/lib`.

	3.	If folder `$RESIN_HOME/extlib` doesn't exist, create it.

3.	Make sure the JDBC driver for your database is accessible by Resin. Obtain the JDBC driver for the database you want to use. In the case of MySQL, use `mysql-connector-java-{$version}-bin.jar`. You can download the latest MySQL JDBC driver from [http://www.mysql.com/products/connector/](http://www.mysql.com/products/connector/).
Extract the JAR file and copy it to `$RESIN_HOME/extlib`.

Great! now you have your `.jar` files in place. Next, let's configure Resin for Liferay. 

##### Configuring Resin [](id=lp-6-1-ugen11-configuring-resin-0)

The primary file used in configuring your domain is `$RESIN_HOME/conf/resin.xml`. You need to make common modifications necessary to support Liferay Portal. You'll also create a run script and add a folder to hold Resin's logs. But let's start with the changes to `resin.xml`.

1.	Make the following modifications to your `resin.xml`. These modifications to your application cluster do the following:

	-	Set the file encoding.

	-	Set the preferred protocol stack.

	-	Set the user time-zone.

	-	Increase the default amount of memory available.

	To accomplish this, insert the following `<jvm-arg>` elements as server defaults for your main application cluster. Please see the following example:

			<cluster id="app-tier">
				...
				<server-default>
					...
					<jvm-arg>-Dfile.encoding=UTF-8</jvm-arg>
					<jvm-arg>-Djava.net.preferIPv4Stack=true</jvm-arg>
					<jvm-arg>-Duser.timezone=GMT</jvm-arg>
					<jvm-arg>-Xmx1024m</jvm-arg>
					<jvm-arg>-XX:MaxPermSize=256m</jvm-arg>
					...
				</server-default>
			</cluster>

2.	Create an appropriate script in `$RESIN_HOME/bin` to help you start Resin.

	-	If you're on Windows, create a batch script `$RESIN_HOME/bin/run.bat` and insert the following text in the script:

			..\resin.exe console

	-	If you're on Unix/Linux, create shell script `$RESIN_HOME/bin/run.sh` and insert the following text in the script:

			#!/bin/sh

			./resin.sh $

3.	Create the folder `$RESIN_HOME/log` if it doesn't already exist. As you run Resin, the server generates log files `access`, `jvm-default` and `watchdog-manager` in this folder.

Now that you've completed some important common configuration tasks to support Liferay, let's consider database configuration. 

##### Database Configuration [](id=lp-6-1-ugen11-database-configuration-3)

If you want to manage your data source within Resin, continue following the instructions in this section. If you want to use the built-in Liferay data source, you can skip this section.

Management of databases in Resin is done via the configuration file `$RESIN_HOME/conf/resin.xml`. Edit `resin.xml` and insert a `<database>` element for your database. Be sure to give it the JNDI name `jdbc/LiferayPool` and add it within the application tier cluster element as in the example below:

	<cluster id="app-tier">
		...
		<database>
			<jndi-name>jdbc/LiferayPool</jndi-name>
			<driver type="com.mysql.jdbc.Driver">
				<url>jdbc:mysql://localhost/lportal?useUnicode=true&amp;characterEncoding=UTF-8</url>
				<user>root</user>
				<password>root</password>
            </driver>
            <prepared-statement-cache-size>8</prepared-statement-cache-size>
            <max-connections>20</max-connections>
            <max-idle-time>30s</max-idle-time>
          </database>
          ...
	</cluster>

Be sure to replace the URL database value (i.e. `lportal`), user value and password value with values specific to your database. 

Resin is now managing your database connection. Let's consider next how to configure mail.

##### Mail Configuration [](id=lp-6-1-ugen11-mail-configuration-3)

If you want to manage your mail session within Resin, use the following instructions. If you want to use the built-in Liferay mail session, you can skip this section.

Management of mail sessions in Resin is done via the configuration file `$RESIN_HOME/conf/resin.xml`. Edit `resin.xml` and insert a `<mail>` element that specifies your mail session. Be sure to give it the JNDI name `mail/MailSession`. Add your mail element within the application tier cluster element. Use the example below, replacing its values with the values of your mail session.

	<cluster id="app-tier">
		...
		<mail jndi-name="mail/MailSession">
			<properties>
				mail.pop3.host=pop.gmail.com
				mail.pop3.port=110
				mail.pop3.user=
				mail.pop3.password=
				mail.smtp.host=smtp.gmail.com
				mail.smtp.password=password
				mail.smtp.user=user
				mail.smtp.port=465
				mail.transport.protocol=smtp
				mail.smtp.auth=true
				mail.smtp.starttls.enable=true
				mail.smtp.socketFactory.class=javax.net.ssl.SSLSocketFactory
				mail.imap.host=imap.gmail.com
				mail.imap.port=993
				mail.store.protocol=imap
			</properties>
		</mail>
          ...
	</cluster>

You can specify additional properties for your mail session as needed.

Now that your mail session is squared away, we'll make sure Liferay can access it.

##### Configuring data source and mail session [](id=lp-6-1-ugen11-configuring-data-source-and-mail-session-0)

Let's make sure Liferay's connected to your data source and mail session.

1. First, navigate to the *Liferay Home* folder, which is one folder above Resin's install location (i.e. `$RESIN_HOME/..`).

2. If you're using *Resin* to manage your data source, add the following to your `portal-ext.properties` file in your *Liferay Home* to refer to your data source:

		jdbc.default.jndi.name=jdbc/LiferayPool

	If you're using *Liferay Portal* to manage your data source, follow the instructions in the *Deploy Liferay* section for using the setup wizard.

3.	If want to use *Liferay Portal* to manage your mail session, configure the mail session within Liferay Portal. That is, after starting your portal as described in the *Deploy Liferay* section, go to *Control Panel &rarr; Server Administration &rarr; Mail* and enter the settings for your mail session.

	If you're using *Resin* to manage your mail session, add the following to your `portal-ext.properties` file to reference that mail session:

		mail.session.jndi.name=mail/MailSession

Great! Now Liferay can access your database and your mail session. Now, let's deploy Liferay.

##### Deploy Liferay [](id=lp-6-1-ugen11-deploy-liferay-3)

Liferay can be deployed as an exploded web archive within `$RESIN_HOME/webapps`.

1.	If you already have an application folder `$RESIN_HOME/webapps/ROOT`, delete it or move it to a location outside of `$RESIN_HOME/webapps`.

2.	Extract the contents of the Liferay portal `.war` file into `RESIN_HOME/webapps/ROOT`. The following files should now exist in your `RESIN_HOME/webapps/ROOT` folder:

	-	dtd (folder)
	-	errors (folder)
	-	html (folder)
	-	layouttpl (folder)
	-	META-INF (folder)
	-	wap (folder)
	-	WEB-INF (folder)
	-	index.jsp

3.	Before you start Liferay Portal, consider whether you want to also start the setup wizard.

	-	**Start the setup wizard along with Liferay Portal** - Do this if you want to configure your portal, set up your site's administrative account and/or manage your database within Liferay.
		
		If this is your first time starting Liferay Portal 6.1, the setup wizard is invoked on server start up. If you want to re-run the wizard, specify `setup.wizard.enabled=true` in your properties file (e.g. `portal-setup-wizard.properties`).

			setup.wizard.enabled=true

		The setup wizard is invoked during server startup.

	-	**Start Liferay Portal without invoking the setup wizard** - Do this if want to preserve your current portal settings.

		To start the server without triggering the setup wizard, specify `setup.wizard.enabled=false` in your properties (e.g. `portal-setup-wizard.properties` or `portal-ext.properties` file).

			setup.wizard.enabled=false

		The `portal-setup-wizard.properties` file the setup wizard creates has `setup.wizard.enabled=false` conveniently specified for you.

	---

	![Note](../../images/tip.png) Property values in `portal-setup-wizard.properties` override property values in `portal-ext.properties`.

	---

4.	Start Liferay Portal by executing your `run.bat` (Windows) or `run.sh` (Unix/Linux) script from `$RESIN_HOME/bin`.

    -	If the setup wizard was disabled, your site's home page opens in your browser at [http://localhost:8080](http://localhost:8080).

    -	Otherwise, the setup wizard opens in your browser.

Please see the section above describing how to use the setup wizard. 

Congratulations! You've installed Liferay Portal on Resin and have it up and running.

#### Installing Liferay on Tomcat 7 [](id=lp-6-1-ugen11-installing-liferay-on-tomcat-7-0)

**Liferay Home** is one folder above Tomcat's install location.

For this section, we will refer to your Tomcat server's installation location as `$TOMCAT_HOME`. If you do not already have an existing Tomcat server, we recommend you download a Liferay/Tomcat bundle from [http://www.liferay.com/downloads/liferay-portal/available-releases](http://www.liferay.com/downloads/liferay-portal/available-releases). If you have an existing Tomcat server or would like to install Liferay on Tomcat manually, please follow the steps below.

Before you begin, make sure you have downloaded the latest Liferay `.war` file and Liferay Portal dependencies from [http://www.liferay.com/downloads/liferay-portal/additional-files](http://www.liferay.com/downloads/liferay-portal/additional-files). The Liferay `.war` file should be called `liferay-portal-6.1.x-<date>.war` and the dependencies file should be called `liferay-portal-dependencies-6.1.x-<date>.zip`.

Next, let's get started by addressing Liferay's library dependencies.

##### Dependency Jars [](id=lp-6-1-ugen11-dependency-jars-4)

Liferay Portal needs to have the Liferay Portal Dependency JARs, an appropriate JDBC driver and a few other JARs installed.

1.	Create folder `$TOMCAT_HOME/lib/ext`.

2. Extract the Liferay dependencies file to `$TOMCAT_HOME/lib/ext`. If the files do not extract to this directory, you can copy the dependencies archive to this directory, extract them and then delete the archive.

3.	Next, you need several `.jar` files which are included as part of the Liferay source distribution. Many application servers ship with these already on the class path but Tomcat does not. The best way to get the appropriate versions of these files is to download the Liferay source code and get them from there. Once you have downloaded the Liferay source, unzip the source into a temporary folder. We'll refer to the location of the Liferay source as `$LIFERAY_SOURCE`.

	1.	Copy the following jars from `$LIFERAY_SOURCE/lib/development` to your `$TOMCAT_HOME/lib/ext` folder:

		- 	`activation.jar`
		-	`jms.jar`
		-	`jta.jar`
		-	`jutf7.jar`
		-	`mail.jar`
		-	`persistence.jar`

	2.	Copy the following jar from `$LIFERAY_SOURCE/lib/portal` to your `$TOMCAT_HOME/lib/ext` folder:

		- 	`ccpp.jar`

		*Note:* Tomcat 6 users should *not* copy the `ccpp.jar` file into their `$TOMCAT_HOME/lib/ext` folder and should delete it from this folder if it already exists.

	2.	Copy the following jars from `$LIFERAY_SOURCE/lib/development` to your `$TOMCAT_HOME/temp/liferay/com/liferay/portal/deploy/dependencies` folder:

		- 	`resin.jar`
		-	`script-10.jar`

4. Make sure the JDBC driver for your database is accessible by Tomcat. Obtain the JDBC driver for your version of the database server. In the case of MySQL, use `mysql-connector-java-{$version}-bin.jar`. You can download the latest MySQL JDBC driver from [http://www.mysql.com/products/connector/](http://www.mysql.com/products/connector/). Extract the JAR file and copy it to `$TOMCAT_HOME/lib/ext`.

5. Liferay requires an additional .jar on Tomcat installations to manage transactions. This is included in the bundle but you need to add it if you're installing Liferay manually. You may find this .jar here: [http://www.oracle.com/technetwork/java/javaee/jta/index.html](http://www.oracle.com/technetwork/java/javaee/jta/index.html). Place this file in Tomcat's lib/ext folder. 

Now that you have the necessary libraries in place, we'll move on to configuring your domain.

##### Domain Configuration [](id=lp-6-1-ugen11-domain-configuration-1)

The steps in this section focus on:

-	Setting environment variables
-	Creating a context for your web application
-	Modifying the list of classes/JARs to be loaded
-	Specifying URI encoding

Let's get started with our configuration tasks.

1. Create a `setenv.bat` (Windows) or `setenv.sh` file (Unix, Linux, Mac OS) in the `$TOMCAT_HOME/bin` directory. When you start Tomcat, Catalina calls `setenv.bat` or `setenv.sh`. Edit the file and populate it with following contents:

	setenv.bat:

		if exist "%CATALINA_HOME%/jre@java.version@/win" (
			if not "%JAVA_HOME%" == "" (
				set JAVA_HOME=
			)
		
			set "JRE_HOME=%CATALINA_HOME%/jre@java.version@/win"
		)

		set "JAVA_OPTS=%JAVA_OPTS% -Dfile.encoding=UTF8 -Djava.net.preferIPv4Stack=true -Dorg.apache.catalina.loader.WebappClassLoader.ENABLE_CLEAR_REFERENCES=false -Duser.timezone=GMT -Xmx1024m -XX:MaxPermSize=256m"

	setenv.sh:

		JAVA_OPTS="$JAVA_OPTS -Dfile.encoding=UTF8 -Dorg.apache.catalina.loader.WebappClassLoader.ENABLE_CLEAR_REFERENCES=false -Duser.timezone=GMT -Xmx1024m -XX:MaxPermSize=256m"

	This sets the character encoding to UTF-8, sets the time zone to Greenwich Mean Time and allocates memory to the Java virtual machine.

2. Create the directory `$TOMCAT_HOME/conf/Catalina/localhost` and create a `ROOT.xml` file in it. Edit this file and populate it with the following contents to set up a portal web application:

			<Context path="" crossContext="true">

				<!-- JAAS -->

				<!--<Realm
					className="org.apache.catalina.realm.JAASRealm"
					appName="PortalRealm"
					userClassNames="com.liferay.portal.kernel.security.jaas.PortalPrincipal"
					roleClassNames="com.liferay.portal.kernel.security.jaas.PortalRole"
				/>-->

				<!--
				Uncomment the following to disable persistent sessions across reboots.
				-->

				<!--<Manager pathname="" />-->

				<!--
				Uncomment the following to not use sessions. See the property
				"session.disabled" in portal.properties.
				-->

				<!--<Manager className="com.liferay.support.tomcat.session.SessionLessManagerBase" />-->
			</Context>
		
	Setting `crossContext="true"` allows multiple web apps to use the same class loader. In the content above you will also find commented instructions and tags for configuring a JAAS realm, disabling persistent sessions and disabling sessions in general.

3. Open `$TOMCAT_HOME/conf/catalina.properties` and replace the line:

		common.loader=${catalina.base}/lib,${catalina.base}/lib/*.jar,${catalina.home}/lib,${catalina.home}/lib/*.jar

	with:

		common.loader=${catalina.base}/lib,${catalina.base}/lib/*.jar,${catalina.home}/lib,${catalina.home}/lib/*.jar,${catalina.home}/lib/ext,${catalina.home}/lib/ext/*.jar
		
	This allows Catalina to access the dependency jars you extracted to `$TOMCAT_HOME/lib/ext`.

4. To ensure consistent use of UTF-8 URI Encoding, edit `$TOMCAT_HOME/conf/server.xml` and add the attribute `URIEncoding="UTF-8"` to your connector on port 8080. Below is an example of specifying this encoding on the connector:

		<Connector port="8080" protocol="HTTP/1.1" connectionTimeout="20000" redirectPort="8443" URIEncoding="UTF-8" />

5. Make sure there is no `support-catalina.jar` file in your `$TOMCAT_HOME/webapps` directory. If you find one, remove it.

Excellent work! Now let's consider configuration of your database.

##### Database Configuration [](id=lp-6-1-ugen11-database-configuration-4)

If you want Tomcat to manage your data source, use the following procedure. If you want to use Liferay's built-in data source, you can skip this section.

1.	Make sure your database server is installed and working. If it's installed on a different machine, make sure it's accessible from your Liferay machine.

2.	Add your data source as a resource in the context of your web application specified in `$TOMCAT_HOME/conf/Catalina/localhost/ROOT.xml`.

		<Context...>
			<Resource
				name="jdbc/LiferayPool"
				auth="Container"
				type="javax.sql.DataSource"
				driverClassName="com.mysql.jdbc.Driver"
				url="jdbc:mysql://localhost/lportal?useUnicode=true&amp;characterEncoding=UTF-8"
				username="root"
				password="root"
				maxActive="100"
				maxIdle="30"
				maxWait="10000"
			/>
		</Context>
	
	Note the above resource definition assumes your database name is *lportal* and your MySQL username and password are both *root*. You'll have to update these values with your own database name and credentials.

Your Tomcat managed data source is now configured. Let's move on to your mail session.

##### Mail Configuration [](id=lp-6-1-ugen11-mail-configuration-4)

If you want to manage your mail session within Tomcat, use the following instructions. If you want to use the built-in Liferay mail session, you can skip this section.

Create a mail session bound to `mail/MailSession`. Edit `$TOMCAT_
HOME/conf/Catalina/localhost/ROOT.xml` and configure a mail
session. Be sure to replace the mail session values with your own.

	<Context...>
		<Resource
			name="mail/MailSession"
			auth="Container"
			type="javax.mail.Session"
			mail.pop3.host="pop.gmail.com"
			mail.pop3.port="110"
			mail.smtp.host="smtp.gmail.com"
			mail.smtp.port="465"
			mail.smtp.user="user"
			mail.smtp.password="password"
			mail.smtp.auth="true"
			mail.smtp.starttls.enable="true"
			mail.smtp.socketFactory.class="javax.net.ssl.SSLSocketFactory"
			mail.imap.host="imap.gmail.com"
			mail.imap.port="993"
			mail.transport.protocol="smtp"
			mail.store.protocol="imap"
		/>
	</Context>

Super! Your mail session is configured. Next, we'll make sure Liferay will be able to access your mail session and database.

##### Configuring your database and mail session [](id=lp-6-1-ugen11-configuring-your-database-and-mail-session-0)

In this section we'll specify appropriate properties for Liferay to use in connecting to your database and mail session.

1.	If you are using *Tomcat* to manage your data source, add the following to your `portal-ext.properties` file in your *Liferay Home* to refer to your data source:

		jdbc.default.jndi.name=jdbc/LiferayPool

	Otherwise, if you are using *Liferay Portal* to manage your data source, follow the instructions in the *Deploy Liferay* section for using the setup wizard.

2.	If want to use *Liferay Portal* to manage your mail session, you can configure the mail session within Liferay Portal. That is, after starting your portal as described in the *Deploy Liferay* section, go to *Control Panel &rarr; Server Administration &rarr; Mail* and enter the settings for your mail session.

	Otherwise, if you are using *Tomcat* to manage your mail session, add the following to your `portal-ext.properties` file to reference that mail session:

		mail.session.jndi.name=mail/MailSession

It's just that easy! Now it's time to deploy Liferay Portal on your Tomcat server. 

##### Deploy Liferay [](id=lp-6-1-ugen11-deploy-liferay-4)

We'll deploy Liferay as an exploded web archive within your `$TOMCAT_HOME/webapps` folder.

1.	If you are manually installing Liferay on a clean Tomcat server, delete the contents of the `$TOMCAT_HOME/webapps/ROOT` directory. This undeploys the default Tomcat home page. Then extract the Liferay `.war` file to `$TOMCAT_HOME/webapps/ROOT`.

2.	Before you start Liferay Portal, consider whether you want to use the setup wizard.

	-	**Start the setup wizard along with Liferay Portal** - Do this if you want to configure your portal, set up your site's administrative account and/or manage your database within Liferay.
		
		If this is your first time starting Liferay Portal 6.1, the setup wizard is invoked on server startup. If you want to re-run the wizard, specify `setup.wizard.enabled=true` in your properties file (e.g. `portal-setup-wizard.properties`).

			setup.wizard.enabled=true

		The setup wizard is invoked during server startup.

	-	**Start Liferay Portal without invoking the setup wizard** - Do this if you want to preserve your current portal settings.

		To start the server without triggering the setup wizard, specify `setup.wizard.enabled=false` in your properties (e.g. `portal-setup-wizard.properties` or `portal-ext.properties` file).

			setup.wizard.enabled=false

		The `portal-setup-wizard.properties` file the setup wizard creates should have `setup.wizard.enabled=false` conveniently specified for you.

	---

	![Note](../../images/tip.png) Property values in `portal-setup-wizard.properties` override property values in `portal-ext.properties`.

	---

	I bet you can't wait to start Liferay Portal - let's do it!

3.	Start Tomcat by executing `$TOMCAT_HOME/bin/startup.bat` or `$TOMCAT_HOME/bin/startup.sh`.

	-	If the setup wizard was disabled, your site's home page opens in your browser at [http://localhost:8080](http://localhost:8080).

	-	Otherwise, the setup wizard opens in your browser.

To use the setup wizard, please see the section above. 

Congratulations on successfully installing and deploying Liferay on Tomcat!

#### Installing Liferay on JOnAS 5.2 [](id=lp-6-1-ugen11-installing-liferay-on-jonas-52-0)

JOnAS is somewhat unique among the open-source application servers for two reasons: it is built entirely on an OSGi core, allowing for dynamic deployment of simultaneous webapps and containers and allows the clean separation of the configuration directory from the server itself. In fact, the separation of the configuration is recommended by the developers of JOnAS as a way to cleanly deploy so you can revert to default settings later.

If you don't have an existing JOnAS installation, it is recommeded to use the available Liferay-JOnAS bundle, which can be downloaded from [http://www.liferay.com/ja/downloads/liferay-portal/available-releases](http://www.liferay.com/ja/downloads/liferay-portal/available-releases).

Given the unique nature of the server, there are a few steps to consider in the configuration stage. Otherwise, installing on JOnAS follows much the same pattern as other servers: configure, copy dependencies and deploy.

##### Configuring JOnAS for Liferay [](id=lp-6-1-ugen11-configuring-jonas-for-liferay-0)

A pristine JOnAS installation comes with a number of samples, tutorials and template configuration files. A lot of this is unnecessary and irrelevant for installing Liferay. In addition, the server already contains an application deployed to the root context, which you must remove prior to installing the Liferay `.war` package (and which you'd want to remove anyway for a production configuration). JOnAS allows you to decide where to place all the server configuration and deployment settings, also called `$JONAS_BASE`. The folder created by unzipping the JOnAS application (likely called `jonas-full-5.2.2` or similar) is referred to as `$JONAS_ROOT`. This allows a unique, clean separation between application and configuration. 

The structure of `$JONAS_BASE` is:

    /conf    -    configuration files
    /deploy    -    main deployment directory (Liferay is deployed here)
    /lib    -    used for extending the main server classloaders
    +----/ext    -    extensions for unbundled applications
    /logs    -    logs for the running instance
    /work    -    the working directory, used by containers such as Tomcat
    /repositories    -    contains OSGi bundles for deployment; not used for Liferay installation
    
By default, the `$JONAS_BASE` directory is the same as `$JONAS_ROOT`. Creating a new `$JONAS_BASE` is a simple process, outlined in the JOnAS Configuration Guide, found at [http://jonas.ow2.org/JONAS_5_2_2/doc/doc-en/html/configuration_guide.html](http://jonas.ow2.org/JONAS_5_2_2/doc/doc-en/html/configuration_guide.html).

To remove sample files and unneeded configuration:

1. Navigate to the directory you unpackaged *JOnAS* into, `$JONAS_BASE`.

2. Find the following sample directories and remove them:

        -    /examples

	-    /tutorial

3. Navigate to `$JONAS_BASE/conf` and remove the following files:

        -    db2.properties
        -    FirebirdSQL.properties
        -    HSQL1.properties
        -    jetty*.xml
        -    InstantDB1.properties
        -    InterBase1.properties
        -    MailMimePartDS1.properties
        -    MailSession1.properties
        -    McKoi1.properties
        -    MySQL.properties
        -    Oracle1.properties
        -    PostgreSQL1.properties
        -    spy.properties
        -    Sybase1.properties
		
      This disables the default settings for the databases available in JOnAS, as well as removing configuration for Jetty as a container to use for the webapp.
      
4. To remove the default application installed on the root context:

         a. Go to the `$JONAS_BASE/deploy` directory and remove:
	 
	     -    ctxroot.xml
	     -    doc.xml
	     -    jdbc-ds.xml
	     -    jonasAdmin.xml
	 
	 b. Go to the `$JONAS_ROOT/repositories` directory to remove the application by removing:
	 
	     -    org/mortbay/
	     -    org/ow2/jonas/documentation/
	     -    org/ow2/jonas/jonas-admin/
	     -    org/ow2/jonas/jonas-ctxroot/
	     
      This will fully remove the Maven deployment plan and artifact for the JOnAS default application, as well as the administration console from loading on the root context.
      
Now that JOnAS is prepared for configuring Liferay to run on the server as its root application, you can begin tuning the settings for Liferay. By default, JOnAS has its own deployment of Hypersonic it uses internally. This internal use of HSQL must be disabled, along with other JOnAS services, so they won't conflict with Liferay's.

To turn of HSQL and other JOnAS-level services:

1. Open the file `jonas.properties` in the directory `$JONAS_BASE/conf`.

2. Find the configuration section for the JOnAS database manager, starting around line 340:

            #
            ###################### JOnAS DBM Database service configuration
        
	    ...
	
	    jonas.service.dbm.datasources    hsql
	    
3. Change the datasources definition around line 353 to read: 

            jonas.service.dbm.datasources    
	    
      Thereby preventing the HSQL database from being used internally.
       
4. Find the services configuration around line 82:

            jonas.services    jtm,db,security,resource,ejb3,jaxws,web,ear,depmonitor
	   
5. Modify the services being loaded to read:

            jonas.services    jtm,resource,ejb3,jaxws,web,ear,validation,depmonitor
	    
      This prevents the internal `db` and `security` services from interfering with Liferay.
      
6. To put JOnAS into production mode for proper deployment of Liferay, find the property around line 71:

            jonas.development    true      
	    
      And change it to `false`:

            jonas.development    false

      This allows JOnAS to startup appropriately with Liferay installed.
            
##### Configuring Containers in JOnAS [](id=lp-6-1-ugen11-configuring-containers-in-jonas-0)

Now that the application server has all extraneous services and applications disabled, you can now tweak the configuration of the containers within JOnAS: Tomcat and OSGi. By default, the Tomcat container is set to listen on a different HTTP port and HTTPS port than Liferay uses by default.

To change the Tomcat ports for Liferay's use:

1. Open the file `tomcat6-server.xml` inside of `$JONAS_BASE/conf`.
2. Find the `Connector` definition around line 69:

            <Connector port="9000" protocol="HTTP/1.1"
                           connectionTimeout="20000"
                           redirectPort="9043" />
			   
      Change it to reflect the default ports:

            <Connector port="8080" protocol="HTTP/1.1"
                           connectionTimeout="20000"
                           redirectPort="8443" />

3. If you are using any other settings in Tomcat's server settings, you can adjust the ports if needed (such as changing the AJP port from `9009` to `8009`.

To modify the OSGI defaults to ensure required java packages are bootsrapped by the loader:

1. Open the file `defaults.properties` inside of `$JONAS_BASE/conf/osgi`.
2. Find the declaration for `javase-packages` around line 93:

            javase-packages ${javase-${javase.version}}
	    
      And add the following packages to make it read:
      
              javase-packages ${javase-${javase.version}}, com.sun.jmx.mbeanserver, com.sun.crypto.provider, org.apache.felix.framework
	      
      To ensure the required packages are loaded.
      
##### Starting JOnAS [](id=lp-6-1-ugen11-starting-jonas-0)

Once you have the required configuration in place, all that is left is to copy the portal dependencies and the Liferay `.war` file and start the server. JOnAS maintains libraries inside `$JONAS_BASE/lib/ext` and the application inside `$JONAS_BASE/deploy`.

To install `liferay-portal-dependencies-6.1.x-<date>.zip`:

1. Unzip the archive `liferay-portal-dependencies-6.1.x-<date>.zip` on your local filesystem.
2. Navigate to `$JONAS_BASE/lib/ext`.
3. Copy the `.jar` files from `liferay-portal-dependencies-6.1.x-<date>/` to `$JONAS_BASE/lib/ext`.
4. Install any additional libraries needed, such as database connectors.

To deploy the `liferay-portal-6.1.x-<date>.war` file:

1. Copy the `liferay-portal-6.1.x-<date>.war` file from its current directory.
2. Navigate to `$JONAS_BASE/deploy`.
3. Paste the `liferay-portal-6.1.x-<date>.war` file into the `deploy` directory.

Once the necessary files have been installed, all that is needed is to start JOnAS:

1. Navigate to `$JONAS_BASE/bin`.
2. Run the command `jonas.bat start` on Windows and `./jonas start` on UNIX-lixe systems.

JOnAS starts and Liferay opens a browser to `http://localhost:8080`.

#### Installing Liferay on WebLogic 10 [](id=lp-6-1-ugen11-installing-liferay-on-weblogic-10-0)

**Liferay Home** is one folder above the domain to which you will be installing Liferay. For example, if your domain location is `/Oracle/Middleware/user_projects/domains/base_domain`, then your Liferay Home is  `/Oracle/Middleware/user_projects/domains`.

For this section, we will refer to your WebLogic server's installation location as `$WEBLOGIC_HOME`. 

Before you begin, make sure you have downloaded the latest Liferay `.war` file and Liferay Portal dependencies from [http://www.liferay.com/downloads/liferay-portal/additional-files](http://www.liferay.com/downloads/liferay-portal/additional-files). The Liferay `.war` file should be called `liferay-portal-6.1.x-<date>.war` and the dependencies file should be called `liferay-portal-dependencies-6.1.x-<date>.zip`.

These instructions assume you have already configured a domain and server and that you have access to the WebLogic console.

---

![Note](../../images/tip.png) WebLogic 10.0 supports JDK 1.5 but does *not* support JDK 1.6.

---

Now that you have all of your installation files, you are ready to start installing and configuring Liferay on WebLogic.

##### Dependency Jars [](id=lp-6-1-ugen11-dependency-jars-5)

Liferay requires several `.jar` files including the Liferay Dependency JARs and a JAR file for your database driver. The following steps describe how to install these `.jar` files properly.

1. Navigate to the folder which corresponds to the domain to which you will be installing Liferay. Inside this folder is a `lib` folder. Unzip the Liferay dependencies archive to this folder so the dependency `.jar` files are extracted into the `lib` folder.

2. If WebLogic does not already have access to the JDBC driver for your database, copy the driver to your domain's `lib` folder as well.

3. You will also need the `xercesImpl.jar` copied to your domain's `lib` folder or you will get SAX parsing errors after you deploy Liferay. You may download this  from [http://xerces.apache.org](http://xerces.apache.org/).

4. Create a folder `$WEBLOGIC-HOME/jrockit_150_15/jre/lib/endorsed`. Then copy `commons-lang.jar, liferay-rhino.jar`, `serializer.jar` and `xalan.jar` to the endorsed folder you just created.

Now that you have your WebLogic installation is loaded up with JAR files for Liferay to use, let's consider how to configure your database.

##### Database Configuration [](id=lp-6-1-ugen11-database-configuration-5)

If you want WebLogic to manage your data source, use the following procedure. If you want to use Liferay's built-in data source, you can skip this section.

![Figure 14.42: WebLogic Data Sources](../../images/02-weblogic-data-sources.png)

1. Browse to your WebLogic Console. Click the *Lock & Edit* button above the Domain Structure tree on the left side of the page.

2. From the Domain Structure tree on the left, select *Data Sources*. Then click the *New* button on the right side of the screen.

3. Give the Data Source a name, such as `LiferayDataSource`.

4. Define the JNDI name as `jdbc/LiferayPool`.

5. Select your Database Type, the Driver class and then click the *Next* button.

6. Accept the defaults on the next screen by clicking *Next*.

7. On the next screen, put in your *Database Name*, *Host Name*, *Database User Name* and *Password*. If you have been following the defaults we have been using so far, you would use *lportal, localhost, root,* and no password as the values. Click *Next*.

8. The next screen allows you to test your database configuration. Click the *Test Configuration* button. If the test succeeds, you have configured your database correctly. Select the check box of the server to which you want to deploy this Data Source (`AdminServer` is the default). Click *Finish*.

9. Click the *Activate Changes* button on the left, above the Domain Structure tree.

Great work! Your data source can now be managed from within WebLogic. Next, let's consider the mail session for your domain.

##### Mail Configuration [](id=lp-6-1-ugen11-mail-configuration-5)

If you want WebLogic to manage your mail sessions, use the following procedure. If you want to use Liferay's built-in mail sessions, you can skip this section.

![Figure 14.43: WebLogic: Mail Sessions](../../images/02-weblogic-mail-sessions.png)

1. In the Domain Structure tree, select *Mail Sessions.* Then click the *Lock & Edit* button again to enable modifying these settings.

2. Click the *New* button which is now enabled on the right side of the screen.

3. Give the Mail Session a name, such as `LiferayMail`.

4. Select your new LiferayMail session from the list by clicking on it.

5. On the screen that appears, define the JNDI name as `mail/MailSession` and set your JavaMail properties. Click the *Save* button.

6. Click the *Targets* tab. Select the check box of the server to which you want deploy this Data Source to (AdminServer is the default).

7. Click the *Activate Changes* button on the left side of the screen, above the Domain Structure tree.

Now you have your mail session specified and ready for Liferay to use. 

##### Domain Configuration - Continued [](id=lp-6-1-ugen11-domain-configuration---continued-1)

Let's revisit domain configuration to make sure we'll be able to access your data source and mail session from Liferay Portal.

1. First, navigate to the *Liferay Home* folder.

2. Then, if you are using *WebLogic* to manage your data source, add the following to your `portal-ext.properties` file in your *Liferay Home* to refer to your data source:

		jdbc.default.jndi.name=jdbc/LiferayPool

	Otherwise, if you are using *Liferay Portal* to manage your data source, follow the instructions in the *Deploy Liferay* section for using the setup wizard.

3.	If want to use *Liferay Portal* to manage your mail session, you can configure the mail session within Liferay Portal. That is, after starting your portal as described in the *Deploy Liferay* section, go to *Control Panel &rarr; Server Administration &rarr; Mail* and enter the settings for your mail session.

	Otherwise, if you are using *WebLogic* to manage your mail session, add the following to your `portal-ext.properties` file to reference that mail session:

		mail.session.jndi.name=mail/MailSession

Liferay can now communicate with your data source and mail session. It's now time to deploy Liferay!

##### Deploy Liferay [](id=lp-6-1-ugen11-deploy-liferay-5)

Follow the instructions in this section to deploy Liferay Portal to your domain.

Before you deploy Liferay Portal, consider whether you want to use the setup wizard.

-	**Start the setup wizard along with Liferay Portal** - Do this if you want to configure your portal, set up your site's administrative account and/or manage your database within Liferay.
		
	If this is your first time starting Liferay Portal 6.1, the setup wizard is invoked on server startup. If you want to re-run the wizard, specify `setup.wizard.enabled=true` in your properties file (e.g. `portal-setup-wizard.properties`).

		setup.wizard.enabled=true

	The setup wizard is invoked during server startup.

-	**Start Liferay Portal without invoking the setup wizard** - Do this if want to preserve your current portal settings.

	To start the server without triggering the setup wizard, specify `setup.wizard.enabled=false` in your properties (e.g. `portal-setup-wizard.properties` or `portal-ext.properties` file).

		setup.wizard.enabled=false

	The `portal-setup-wizard.properties` file the setup wizard creates has `setup.wizard.enabled=false` conveniently specified for you.

---

![Note](../../images/tip.png) Property values in `portal-setup-wizard.properties` override property values in `portal-ext.properties`.

---

Now that you have enabled or disabled the setup wizard, let's move on to deployment of Liferay Portal.

![Figure 14.44: WebLogic Deployments](../../images/02-weblogic-deployments.png)

1. In the Domain Structure tree, select *Deployments.*  Then click the *Lock & Edit* button above the Domain Structure tree.

2. Click the *Install* button on the right side of the screen.

3. Click the *Upload your file(s)* link.

4. Browse to where you have stored the Liferay `.war` file, select it and then click *Next*.

5. Select the Liferay `.war` file from the list and click *Next*.

6. Leave *Install this deployment as an application* selected and click *Next*.

7. Give the application a name (the default name is fine). Leave the other defaults selected and then click *Finish*.

8. WebLogic deploys Liferay. When it finishes, a summary screen is displayed. Click the *Activate Changes* link on the left above the Domain Structure tree.

9. In the Deployments screen, select the Liferay application and click the *Start* button. Select *Servicing All Requests* in the pop up.

10. Click *Yes* to continue on the next screen to launch Liferay Portal.

    -	If the setup wizard was disabled, your site's home page opens in your browser at [http://localhost:7001](http://localhost:7001).

    -	Otherwise, the setup wizard opens in your browser.

For more information on how to use the setup wizard, please see the section above. 

Congratulations on your deployment of Liferay Portal on WebLogic 10!

#### Installing Liferay on Oracle WebLogic 10.3 [](id=lp-6-1-ugen11-installing-liferay-on-oracle-weblogic-103-0)

**Liferay Home** is one folder above the domain to which you will be installing Liferay.

For example, if your domain location is `/Oracle/Middleware/user_projects/domains/base_domain`, then your Liferay Home is `/Oracle/Middleware/user_projects/domains`.

For this section, we will use `$WEBLOGIC_HOME` to refer to your WebLogic server's installation `/Oracle/Middleware`. 

Before you begin, make sure you have downloaded the latest Liferay `.war` file and Liferay Portal dependencies from [http://www.liferay.com/downloads/liferay-portal/additional-files](http://www.liferay.com/downloads/liferay-portal/additional-files). The Liferay `.war` file should be called `liferay-portal-6.1.x-<date>.war` and the dependencies file should be called `liferay-portal-dependencies-6.1.x-<date>.zip`.

These instructions assume you have already configured a domain and server and that you have access to the WebLogic console.

If you still have the `mainWebApp` module installed, remove it first.

---

![Note](../../images/tip.png) There is a known issue with the Sun and JRockit JVMs bundled with WebLogic 10.3.2 (see [http://bugs.sun.com/view_bug.do?bug_id=6795561](issue)). To resolve, use Sun JVM 1.6.0_u24 or JRockit JVM 1.6.0_24.

---

Let's get started by installing the JAR files Liferay needs.

##### Dependency Jars [](id=lp-6-1-ugen11-dependency-jars-6)

Liferay needs the JAR files contained in the Liferay Dependencies Archive and the driver JAR file applicable for your database.

1. Navigate to the folder that corresponds to the domain to which you will be installing Liferay. Inside this folder is a `lib` folder. Unzip the Liferay Dependencies Archive to this folder so the dependency `.jar` files reside in the `lib` folder.

2. If WebLogic does not already have access to the JDBC driver for your database, copy the driver to your domain's `lib` folder as well.

So far so good. Your JAR files are in place and ready for Liferay.

Start Oracle WebLogic if you want to configure your database and/or mail session within Oracle WebLogic.

##### Database Configuration [](id=lp-6-1-ugen11-database-configuration-6)

If you want WebLogic to manage your data source, use the following procedure. If you want to use Liferay's built-in data source, you can skip this section.

![Figure 14.45: WebLogic Data Sources](../../images/02-weblogic-10-3-data-sources.png)

1. Select *Services &rarr; Data Sources.* Click *New &rarr; Generic Data Source*.

2. Give your data source a name, such as *Liferay Data Source*. The JNDI name should be `jdbc/LiferayPool`.

3. Choose the type of database and click *Next*. From the screenshot, you can see we have chosen MySQL. The database driver class is selected automatically.

![Figure 14.46: Creating a data source in WebLogic 10.3](../../images/02-creating-a-data-source-in-weblogic.png)

4. Click *Next* three times. You should be on the *Connection Properties* screen. Enter the database name, the host name, the port, the database user name and the password. WebLogic uses this information to construct the appropriate JDBC URL to connect to your database. Click *Next*.

5. WebLogic next confirms the information you provided. For MySQL, some additional parameters must be added to the URL. Modify the JDBC URL so it has the proper parameters. Provide your database name, host name, user name and password as the values. Click *Next*.

6. Click *Test Configuration* to make sure WebLogic can connect to your database successfully. When it does, click *Finish*.

7. You will be back to the list of data sources. Notice your new data source has no value in the *Target* column. Click on your data source to edit it.

8. Click the *Targets* tab and check off the server instance(s) to which you wish to deploy your data source. Then click *Save*.

Next, let's configure a mail session in WebLogic.

##### Mail Configuration [](id=lp-6-1-ugen11-mail-configuration-6)

If you want WebLogic to manage your mail sessions, use the following procedure. If you want to use Liferay's built-in mail sessions, you can skip this section.

![Figure 14.47: WebLogic Mail Sessions](../../images/02-weblogic-10-3-mail-sessions.png)

1. Select *Mail Sessions* and create a new mail session which points to your mail server.

2. Give it the name Liferay Mail and give it the JNDI name of `mail/MailSession` and click *Next*.

3. Choose your server and then click *Finish*.

Now let's make sure Liferay can access this mail session.

##### Domain Configuration - Continued [](id=lp-6-1-ugen11-domain-configuration---continued-2)

Let's revisit domain configuration to make sure we'll be able to access your data source and mail session from Liferay Portal.

1. Create a `portal-ext.properties` file in the Liferay Home folder, which is one folder up from your domain's home folder.

	If you are using *WebLogic* to manage your data source, add the following to your `portal-ext.properties` file in *Liferay Home* to refer to your data source:

		jdbc.default.jndi.name=jdbc/LiferayPool

	If you are using *Liferay Portal* to manage your data source, follow the instructions in the *Deploy Liferay* section for using the setup wizard.

	If want to use *Liferay Portal* to manage your mail session, you can configure the mail session in the Control Panel. After starting your portal as described in the *Deploy Liferay* section, go to *Control Panel &rarr; Server Administration &rarr; Mail* and enter the settings for your mail session.

	If you are using *WebLogic* to manage your mail session, add the following to your `portal-ext.properties` file to reference that mail session:

		mail.session.jndi.name=mail/MailSession

2. Lastly, you must provide WebLogic a reference to Java Server Faces (JSF) to use that library. Insert the following deployment descriptor within the `<weblogic-web-app>` element of `WEB-INF/weblogic.xml` found in your Liferay Portal `.war` :

		<library-ref>
			<library-name>jsf</library-name>
			<specification-version>1.2</specification-version>
			<implementation-version>1.2</implementation-version>
			<exact-match>false</exact-match>
		</library-ref>

Now its the moment you've been waiting for: Liferay deployment!

##### Deploy Liferay [](id=lp-6-1-ugen11-deploy-liferay-6)

This section provides instructions for deploying Liferay to your application server domain.

Before you deploy Liferay Portal, consider whether you want to use the setup wizard.

-	**Start the setup wizard along with Liferay Portal** - Do this if you want to configure your portal, set up your site's administrative account and/or manage your database within Liferay.
		
	If this is your first time starting Liferay Portal 6.1, the setup wizard is invoked on server startup. If you want to re-run the wizard, specify `setup.wizard.enabled=true` in your properties file (e.g. `portal-setup-wizard.properties`).

		setup.wizard.enabled=true

	The setup wizard is invoked during server startup.

-	**Start Liferay Portal without invoking the setup wizard** - Do this if want to preserve your current portal settings.

	To start the server without triggering the setup wizard, specify `setup.wizard.enabled=false` in your properties (e.g. `portal-setup-wizard.properties` or `portal-ext.properties` file).

		setup.wizard.enabled=false

	The `portal-setup-wizard.properties` file the setup wizard creates has `setup.wizard.enabled=false` conveniently specified for you.

---

![Note](../../images/tip.png) Property values in `portal-setup-wizard.properties` override property values in `portal-ext.properties`.

---

Now, let's deploy Liferay Portal.

1. Start WebLogic.

2. Select *Deployments* and click the  *Install* button. Upload `jsf-1.2.war` from WebLogic's common files directory and select *Install this deployment as a library.*

![Figure 14.48: WebLogic Deployments](../../images/02-weblogic-10-3-deployments.png)

3. After installing the JSF libraries, go back to deployments and select the Liferay `.war` file from the file system or click the *Upload Your File(s)* link to upload it and then click *Next*.

4. Select *Install this deployment as an application* and click *Next*.

5. If the default name is appropriate for your installation, keep it. Otherwise, give it a name of your choosing and click *Next*.

6. Click *Finish*. After the deployment finishes, click *Save*.

	Liferay launches in one of the following manners:

    -	If the setup wizard was disabled, your site's home page opens in your browser at [http://localhost:7001](http://localhost:7001).

    -	Otherwise, the setup wizard opens in your browser.

Please see the section above for how to use the setup wizard. 

---

![Note](../../images/tip.png) **Note:** After Liferay is installed, you may see an error initializing the Web Proxy portlet. Because the XSL parser configured by default within WebLogic cannot compile a style sheet in this portlet, Liferay disables it by default. To re-enable this portlet, extract `xalan.jar` and `serializer.jar` from the Liferay `.war` archive and copy them to your JDK's endorsed folder for libraries. If you are using JRockit, this folder may be `[$WEBLOGIC_HOME]/jrockit_160_05/jre/lib/ext`; if your are using Sun JDK,  this folder may be `[$WEBLOGIC_HOME]/jdk160_24/jre/lib/ext`.

---

Congratulations! You are now running Liferay on Oracle WebLogic.

#### Installing Liferay on WebSphere 8.0 [](id=lp-6-1-ugen11-installing-liferay-on-websphere-80-0)

![image](../../images/02-tip.png) **Tip:** Throughout this installation and configuration process, WebSphere prompts you to Click Save to apply changes to Master Configuration. Do so intermittently to save your changes.

**Liferay Home** is in a folder called `liferay` in the home folder of the user ID that is running WebSphere.

##### Preparing WebSphere for Liferay [](id=lp-6-1-ugen11-preparing-websphere-for-liferay-0)

When the application server binaries have been installed, start the **Profile Management Tool** to create a profile appropriate for Liferay. 

1. Click on *Create…*. Choose *Application Server*. Click *Next*. 

2. Click the Advanced profile creation option and then click *Next*. Why Advanced? You can specify your own values for settings such as the location of the profile and names of the profile, node and host. You can assign your own ports. You can optionally choose whether to deploy the administrative console and sample application and also add web-server definitions if you wish. Web server definitions are used with IBM HTTP Server. For more information about these options, please see the WebSphere documentation. 

![Figure 14.49: Choose the Advanced profile option to specify your own settings.](../../images/Websphere-8_html_5a8a0a4b.png)

3. Check the box *Deploy administrative console*. This gives you a web-based UI for working with your application server. Skip the default applications. You'd only install these on a development machine. Click *Next*. 

4. Set profile name and location. Ensure you specify a performance tuning setting other than *Development*, since you're installing a server for production use. Click *Next*. 

![Figure 14.50: Use a performance tuning setting other than Development. We've selected Standard here. Please see the WebSphere documentation for further information about performance tuning settings.](../../images/Websphere-8_html_m3feb4e9f.png)

5. Choose node and host names for your server. These will be specific to your environment. Click *Next*. 

![Figure 14.51: Choose node and host names appropriate to your environment.](../../images/Websphere-8_html_1327271c.png)

6. Administrative security in WebSphere is a way to restrict who has access to the administrative tools. For simplicity, we've disabled it but you may want to have it enabled in your environment. Please see WebSphere's documentation for further information. Click *Next*. 

![Figure 14.52: We've disabled administrative security but you may want to enable it.](../../images/Websphere-8_html_2eb820b.png)

7. Each profile needs a security certificate, which comes next in the wizard. If you don't have certificates already, choose the option to generate a personal certificate and a signing certficate and click *Next*. 

8. Once the certificates are generated, set a password for your keystore. Click *Next*. 

![Figure 14.53: Set a password for your keystore.](../../images/Websphere-8_html_m6b754c08.png)

9. Next, you can customize the ports this server profile uses. Be sure to choose ports that are open on your machine. When choosing ports, installation detects existing WebSphere installations and if it finds activity, it increments ports by one. 

![Figure 14.54: WebSphere gives you a nice user interface for customizing the ports your server uses.](../../images/Websphere-8_html_m2eef7200.png)

10. If you want WebSphere to start automatically when the machine is booted, you configure it next. This differs by operating system. When you're finished configuring this the way you want, click *Next*. 

11. WebSphere ships with IBM HTTP Server, which is a rebranded version of Apache. If you want to front your WebSphere server with IBM HTTP Server, you'd configure this next. Please see WebSphere's documentation for details on this. When finished, click *Next*. 

12. WebSphere then creates your profile and finishes with a message telling you the profile was created successfully. You're now ready to install Liferay! 

##### Copying portal dependencies [](id=lp-6-1-ugen11-copying-portal-dependencies-0)

Liferay ships with dependency .jars it needs to have on the global classpath. These should be copied to WebSphere's global folder provided for this purpose: 

	[Install Location]/WebSphere/AppServer/lib/ext

Once you've copied the .jars here, start the server profile you're planning to use for Liferay. Once it starts, you're ready to configure your database. 

##### Database Configuration [](id=lp-6-1-ugen11-database-configuration-7)

If you want WebSphere to manage the database connections, follow the instructions below. Note this is not necessary if you're planning on using Liferay's standard database configuration; in that case, skip this section. You'll set your database information in Liferay's setup wizard after the install. 

![Figure 14.55: WebSphere JDBC providers](../../images/02-websphere-jdbc-providers.png) 

1. Start WebSphere.

2. Open the Administrative Console and log in.

3. Click *Resources &rarr; JDBC Providers*.

4. Click *New*.

5. For name, enter the name of JDBC provider (e.g. *MySQL JDBC Provider*).

6. For Implementation class name, enter:

    com.mysql.jdbc.jdbc2.optional.MysqlConnectionPoolDataSource

7. Click *Next*.

8. Clear any text in classpath. You already copied the necessary `.jar`s to a location on the server's class path.

9. Click *Next*.

10. Click *Finish*.

11. Click *Data Sources* under *Additional Properties*.

12. Click *New*.

13. Enter a name: *liferaydatabasesource*.

14. Enter JNDI: `jdbc/LiferayPool`.

15. Everything else should stay at the default values. Save the data source.

16. When finished, go back into the data source and click *Custom Properties* and then click the *Show Filter Function* button. This is the second from last of the small icons under the *New* and *Delete* buttons.

17. Type *user* into the search terms and click *Go*.

![Figure 14.56: Modifying data source properties in WebSphere](../../images/02-modifying-data-source-properties-in-websphere.png) 

18. Select the user property and give it the value of the user name to your database. Click *OK* and save to master configuration.

19. Do another filter search for the url property. Give it a value that points to your database. For example, the MySQL URL would be: `jdbc:mysql://localhost/lportal?useUnicode=true&characterEncoding=UTF-8&useFastDateParsing=false`. Click *OK* and save to master configuration.

20. Do another filter search for the password property. Enter the password for the user ID you added earlier as the value for this property. Click *OK* and save to master configuration.

21. Go back to the data source page by clicking it in the breadcrumb trail. Click the *Test Connection* button. It should connect successfully.

Once you've set up your database, you can set up your mail session. 

##### Mail Configuration [](id=lp-6-1-ugen11-mail-configuration-7)

If you want WebSphere to manage your mail sessions, use the following procedure. If you want to use Liferay's built-in mail sessions, you can skip this section. 

1. Click *Resources &rarr; Mail &rarr; Mail Providers*.

2. Click the Built-In Mail Provider for your node and server.

3. Click *Mail Sessions* and then click the *New* button.

4. Give it a name of *liferaymail* and a JNDI name of `mail/MailSession`. Click *OK* and save to master configuration.

5. Click *Security &rarr; Global Security* and deselect *Use Java 2 security to restrict application access to local resources* if it is selected. Click *Apply*.

Great! Now you're ready to deploy Liferay. 

##### Deploy Liferay [](id=lp-6-1-ugen11-deploy-liferay-7)

1. Click *Applications &rarr; New Application &rarr; New Enterprise Application*.

2. Browse to the Liferay `.war` file and click *Next*.

3. Leave *Fast Path* selected and click *Next*, then click *Next* again.

4. Make sure your server is selected and click *Next*.

5. Keep the context root set to / and click *Next*.

6. Click *Finish*. When Liferay has installed, click *Save to Master Configuration*.

##### Start Liferay [](id=lp-6-1-ugen11-start-liferay-0)

1. If you plan to use Liferay's setup wizard, skip to the next step. If you wish to use WebSphere's data source and mail session, create a file called `portal-ext.properties` in your Liferay Home folder. Place the following text in the file: 

    jdbc.default.jndi.name=jdbc/LiferayPool
    mail.session.jndi.name=mail/MailSession
    setup.wizard.enabled=false

2. Select the Liferay application and click *Start*. 

![Figure 14.57: Starting Liferay on WebSphere.](../../images/Websphere-8_html_m7420ff51.png)

3. In the setup wizard, select and configure your database type. Click *Finish* when you're done. 

Liferay then creates the tables it needs in the database. 

Congratulations! You've installed Liferay on WebSphere! 

### Making Liferay Coexist with Other Java EE Applications [](id=lp-6-1-ugen11-making-liferay-coexist-with-other-java-ee-applications-0)

Liferay Portal by default is configured to sit at the root (i.e., `/`) of your application server. Dedicating your application server to running only Liferay Portal is a good practice, allowing for separation between your portal environment and your web application environment. This is generally a best practice for portals, which by definition are application development platforms in and of themselves. For that reason, your instance of Liferay is likely to be hosting many applications and even integrating several of them together on a single page. For this reason, you should design your system so your portal environment has all the resources it needs to do this. Configuring it so it is the sole consumer of any other `.war` files that get deployed to the application server helps to make sure your system performs optimally.

If, however, you want Liferay to share space on an application server with other applications, you can. In this instance, you may not want to make Liferay the default application in the root context of the server.

There are two steps to modifying this behavior:

1. Deploy Liferay in a context other than root (for example `/portal`).

2. Modify the `portal-ext.properties` file to tell Liferay the context to which it has been deployed.

To change the file, open it in a text editor. Place the `portal.ctx` property at the top of the file:

    portal.ctx=/

This default setting defines Liferay Portal as the application that sits at the root context. If you change it to something else, say `/portal`, for example, you can then deploy Liferay in that context and it will live there instead of at the root context.

A full discussion of the `portal-ext.properties` file appears in Chapter 14.

**Note for WebLogic Users:** WebLogic also requires that you modify the `weblogic.xml` file which is included with Liferay. In this file are tags for the context root:

    <context-root>/</context-root>

Change this so it matches the path you set in your `portal-ext.properties` file. You will have to modify the `weblogic.xml` file inside the Liferay `.war` before you deploy it. Extract the file from the `.war` file, modify it and then put it back in the `.war` file. Then deploy the modified Liferay `.war` file to the server in the proper context.

## Summary [](id=summ-32)

This chapter is a guide to everything about installing Liferay. Whether you choose a Liferay bundle or an existing application server, Liferay Portal integrates seamlessly with your enterprise Java environment. It is supported on more application servers than any other portal platform, allowing you to preserve your investment in your application server of choice or giving you the freedom to move to a different application server platform. Liferay is committed to providing you this freedom: we have 500 test servers certifying our builds with roughly 10,000 tests per version of Liferay Portal. Each of those tests are run on all of our different supported combinations of application servers, databases and operating systems. Because of this, you can be sure we are committed to supporting you on your environment of choice. You can feel safe knowing you have the freedom to use the software platform that is best for your organization and that Liferay Portal runs and performs well on it.
