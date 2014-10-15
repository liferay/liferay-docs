# Installing a Bundle [](id=installing-a-bundle)

Liferay bundles contain the same directory structure regardless of application
server. The top-level folder is named for the release of Liferay. This folder is
called *Liferay Home* and we refer to it thoughout this documentation. 

![Figure 14.1: Bundle directory structure](../../images/02-bundle-directory-structure.png) 

Inside this folder, there are folders for various purposes:

**Data:** This folder is used to store the embedded HSQL database which the
bundles use, as well as the configuration and data for the Jackrabbit JSR-170
content repository and the Lucene search index.

**Deploy:** Plugins which you wish to deploy to Liferay can be copied into this
folder. It is also used by Liferay Marketplace and Liferay's graphical plugin
installer utility. 

**[Application Server]:** The name of this folder is different depending on
which bundle you have downloaded. This folder contains the application server in
which Liferay has been installed.

Installing a bundle is as easy as uncompressing the archive, copying a JDBC
driver and then starting the application server. Let's use the Tomcat bundle as
an example:

1. Unzip the bundle to a location of your choice. 

2. If you're setting up Liferay to be an actual server, copy your database's
   JDBC driver `.jar` to `[Tomcat]/lib/ext` (see the setup wizard section
   below). If you're setting up Liferay for demo purposes, you can skip this
   step. 

3. Start Tomcat in the same way as you would if you had downloaded it manually.
   Tomcat is launched by way of a script which is found in its `bin` folder. If
   you drop to a command prompt and go to this folder, you can launch Tomcat via
   the following command on Windows:

    	startup

    or the following command on Linux/Mac/Unix:

    	./startup.sh

The Liferay/Tomcat bundle then launches. If you are on Windows, another command
prompt window appears with Tomcat's console in it. If you are on Linux, you can
see the Tomcat console by issuing the following command:

    tail -f ../logs/catalina.out

Once Tomcat has completed its start up, it automatically launches a web browser
that displays Liferay's setup wizard. If for some reason your browser doesn't
load the wizard, launch your web browser and then go to
[http://localhost:8080](http://localhost:8080). 

Liferay CE ships with a sample web site that showcases Liferay's features. It
contains many links describing the features of Liferay that we'll cover in
detail throughout this book. 

If you're installing Liferay on your own machine to explore its features, you
likely want to leave the sample site there so you can examine it. If, however,
you're installing Liferay on your server to run your own site, it's best to
start with a clean system. Before running the setup wizard, you should remove
the sample data from your Liferay installation. You must do this before
running the setup wizard to get a clean database, and it's as simple as
undeploying the applications that install the sample data.

There are two applications included in the bundle that you need to remove:

- resources-importer-web

- welcome-theme

To remove them, all you have to do is undeploy them. The method for doing this
differs by application server and that, of course, depends on the bundle you
have chosen. For example, on Tomcat you delete the application folders from the
`[Tomcat Home]/webapps` folder. On Glassfish, you use the administrative console
to undeploy them. 

If you forget to undeploy the sample applications before you run through the
setup wizard and connect Liferay to your real database, the sample data is
created in your database, and you won't have a clean installation. Make sure you
get the sample data undeployed before setting up your server.

If you're using Liferay EE, you don't have the sample site so you don't need to
worry about this. The next step is to run through the setup wizard, which we'll
cover below. 
   
As you can see, bundles are the easiest way to get started with Liferay. They
come pre-configured with a running Liferay instance that can be used immediately
to explore all of the things that Liferay can do. Bundles are the fastest way to
create full production-ready Liferay installations. If you're using a bundle,
skip to the section on the setup wizard below to continue your installation. 

Of course, it's not always possible to use a bundle. You may already have an
application server upon which you want to install Liferay. The bulk of this
chapter describes how to install Liferay on all the application servers it
supports, both open source and proprietary. 
