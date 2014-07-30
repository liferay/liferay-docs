# Installing a bundle [](id=installing-a-bundle-lp-6-2-use-useportal)

Liferay bundles contain the same directory structure regardless of application
server. The top-level folder is named for the release of Liferay. This folder is
called *Liferay Home*, and we refer to it thoughout this documentation. 

![Figure 15.1: Bundle directory structure](../../images/02-bundle-directory-structure.png) 

Inside this folder, there are folders for various purposes:

**Data:** This folder is used to store the embedded HSQL database the bundles
use, as well as the configuration and data for the Jackrabbit JSR-170 content
repository and the Lucene search index. It also contains the base directory
where the OSGi framework can persist any of its operating files. 

**Deploy:** Plugins that can be copied into this folder, and Liferay then
deploys them. It is also used by Liferay Marketplace and Liferay's graphical
plugin installer utility. 

**[Application Server]:** The name of this folder is different depending on
the bundle you've downloaded. This folder contains the application server in
which Liferay has been installed.

In addition to Liferay Portal itself, bundles are shipped with a number of
plugins already installed: 

- [kaleo-web:](https://www.liferay.com/documentation/liferay-portal/6.2/user-guide/-/ai/using-workflow-liferay-portal-6-2-user-guide-11-en) Liferay's workflow engine

- [marketplace:](https://www.liferay.com/documentation/liferay-portal/6.2/user-guide/-/ai/leveraging-the-liferay-marketplace-liferay-portal-6-2-user-guide-14-en) Interface to Liferay Marketplace

- [notifications:](https://www.liferay.com/documentation/liferay-portal/6.2/user-guide/-/ai/touring-liferay-portals-user-interface-liferay-portal-6-2-user-guide-02-en) App that provides in-browser notifications for users

- [opensocial:](https://www.liferay.com/documentation/liferay-portal/6.2/development/-/ai/creating-and-integrating-with-opensocial-liferay-portal-6-2-dev-guide-08-en) Allows deploying OpenSocial gadgets to Liferay Portal

- [resources-importer-web:](https://www.liferay.com/documentation/liferay-portal/6.2/development/-/ai/importing-resources-with-your-themes-liferay-portal-6-2-dev-guide-09-en) Imports theme resources with theme plugins

- [sync-web:](https://www.liferay.com/documentation/liferay-portal/6.2/user-guide/-/ai/liferay-sync-liferay-portal-6-2-user-guide-05-en) Allows Liferay Sync to connect to this installation to keep
  Documents and Media folders synchronized. You must have this plugin installed
if you want to use Liferay Sync Mobile or Desktop. 

- web-forms: App that allows users to create forms users can fill out. The
  results are then emailed to a specified email address. 

- welcome-theme: A default website that gives new users a tour of what Liferay
  Portal has to offer. 

- [calendar:](https://www.liferay.com/documentation/liferay-portal/6.2/user-guide/-/ai/managing-events-and-calendar-resources-with-liferay-rsquo-s-calendar) Liferay's calendar application. This used to be built-in, and is now
  a plugin, so it's included by default.

If you are an enterprise subscriber, the welcome theme is not included, and you
receive a few more plugins: 

- [kaleo-designer-portlet:](https://www.liferay.com/documentation/liferay-portal/6.2/user-guide/-/ai/kaleo-designer-liferay-portal-6-2-user-guide-12-en) Subscriber plugin that offers a graphical interface
  for creating Kaleo workflows. 

- [kaleo-forms-portlet:](https://www.liferay.com/documentation/liferay-portal/6.2/user-guide/-/ai/make-it-pretty-creating-custom-displays-liferay-portal-6-2-user-guide-10-en) Subscriber plugin that allows attaching forms to
  workflows. 

Getting a Liferay bundle up and running is as easy as uncompressing the archive,
copying a JDBC driver, and then starting the application server. Let's use the
Tomcat bundle as an example:

1. Unzip the bundle to a location of your choice. 

2. If you're setting up Liferay to be an actual server, copy your database's
   JDBC driver `.jar` to `[Tomcat]/lib/ext` (see the setup wizard section
   below). If you're setting up Liferay for demo purposes, you can skip this
   step. 

3. Start Tomcat in the same way you would if you had downloaded it manually.
   Tomcat is launched by way of a script which is found in its `bin` folder. If you
   drop to a command prompt and go to this folder, you can launch Tomcat via the
   following command on Windows:

    startup

   or the following command on Linux/Mac/Unix:

    ./startup.sh

The Liferay/Tomcat bundle then launches. If you are on Windows, another command
prompt window appears with Tomcat's console in it. If you are on Linux, you can
see the Tomcat console by issuing the following command:

    tail -f ../logs/catalina.out

Once Tomcat has started, it automatically launches a web browser that displays
Liferay's setup wizard. If for some reason your browser doesn't load the wizard,
launch your web browser and then go to
[http://localhost:8080](http://localhost:8080). 

Liferay CE ships with a sample web site that showcases Liferay's features. It
contains many links describing the features of Liferay that we cover in detail
throughout this book. 

If you're installing Liferay on your own machine to explore its features, you
likely want to leave the sample site there so you can examine it. If, however,
you're installing Liferay on your server to run your own site, it's best to
start with a clean system. Before running the setup wizard, you should remove
the sample data from your Liferay installation. You must do this before
running the setup wizard to get a clean database, and it's as simple as
undeploying the application that installs the sample data.

There is one application included in the bundle that you need to remove:

- welcome-theme

To remove it, all you have to do is undeploy it. The method for doing this
differs by application server and that, of course, depends on the bundle you
have chosen. For example, on Tomcat you delete the application folder from the
`[Tomcat Home]/webapps` folder. On GlassFish, you use the administrative
console. 

If you forget to undeploy the sample application before you run through the
setup wizard and connect Liferay to your real database, the sample data is
created in your database, and there isn't an easy way to clean it out. Make sure
you get the sample data undeployed before setting up your server, or you'll have
to drop your database and re-create it. That's not such a bad thing to have to
do, since it's a brand new database. If you don't have a brand new database,
you're working with an existing installation of Liferay, and you should follow
the instructions on upgrading in chapter 18 instead of what's described here. 

If you're a Liferay Portal Enterprise subscriber, you don't have the sample
site, so you don't need to worry about this. The next step is to run through the
setup wizard, which we'll cover below. 

As you can see, bundles are the easiest way to get started with Liferay. They
come preconfigured with a running Liferay instance that can be used immediately
to explore all the things that Liferay can do. Bundles are the fastest way to
create full production-ready Liferay installations. If you're using a bundle,
skip to the section on the setup wizard below to continue your installation. 

Of course, it's not always possible to use a bundle. You may already have an
application server where Liferay should be installed. The bulk of this
chapter describes how to install Liferay on all the application servers it
supports, both open source and proprietary.
