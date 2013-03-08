# The Plugins SDK

Java developers have a wide variety of tools and development environments.
Liferay makes every effort to remain tool agnostic, so that you can choose the
tools that work best for you. For that reason, we provide a Plugins Software
Development Kit (SDK) which is based on Apache Ant and may be used along with
any editor or Integrated Development Environment (IDE). Much of this guide uses
the Plugins SDK and a text editor, but you may use whatever tool you are
comfortable with. In a later chapter we also introduce Liferay IDE, a plugin for
Eclipse that simplifies development for Liferay. 

---

 ![tip](../../images/tip-pen-paper.png)**Tip**: If you are an Eclipse user and
 prefer to start working with it from the very beginning, you can read that
 chapter first before reading continuing to read this chapter. 

---

We discuss the following topics in this chapter: 

- **Initial Setup:** How to install the Plugins SDK and configure it for your
use with your Liferay Portal server 

- **Structure of the SDK:** Where to develop your plugin within the SDK and what
Apache Ant targets are available 

- **Best Practices:** Suggests helpful ways for organizing your plugin projects 

Setting up the Plugins SDK is easy. Let's get to it. 

## Installing the SDK

Setting up your environment for Liferay development is straightforward. First,
you need Liferay Portal. If you have yet to install Liferay with an application
server, follow instructions in the [Installation and
Setup](http://www.liferay.com/documentation/liferay-portal/6.1/user-guide/-/ai/installing-lifer-1)
chapter of *Using Liferay Portal*. Many people use the Tomcat application server
for development, as it is small, fast, and takes up less resources than most
other servlet containers. Although you can use any application server supported
by Liferay Portal for development, our examples use Tomcat. 

---

 ![note](../../images/tip-pen-paper.png)**Note:** In Liferay Developer Studio,
 the SDK is already installed and ready to use. Liferay Portal Enterprise
 Edition (EE) comes with Liferay Developer Studio and much more (see [CE vs
 EE](http://www.liferay.com/downloads/liferay-portal/overview)). Download a
 [free trial](http://www.liferay.com/c/portal/register_trial_license?redirect=/products/liferay-portal/ee/30-day-trial)
 of Liferay Portal EE today. 

---

Installation steps:

1. The Plugins SDK is downloadable from our web site at
[http://www.liferay.com](http://www.liferay.com). Download the Plugins SDK:

    1.1. Click the *Downloads* link at the top of the page. 

    1.2. Then, within the *Liferay Portal 6.1 Community Edition* section, select
    the *Plugins SDK* option. 

    1.3. Click *Download*. 

2. Unzip the archive to a folder of your choosing. Avoid using a folder name
that contains spaces because some operating systems have problems running Java
applications in folders with spaces in their name. 

---

 ![tip](../../images/tip-pen-paper.png)**Tip**: By default Liferay Portal
 Community Edition comes bundled with many plugins. It's common to remove them
 to speed up the server start-up. To do this, in the
 `liferay-portal-<version>/tomcat-<tomcat-version>/webapps` directory, delete
 all the directories except for `ROOT` and `tunnel-web.` 

---

Now that you have Plugins SDK installed, let's configure Apache Ant for use in
developing your plugins. 

### Ant Configuration

Building projects in the Plugins SDK requires that you have Ant (version 1.7 or
higher) installed on your machine. Download the latest version of Ant from
[http://ant.apache.org/](http://ant.apache.org/). Extract the archive's contents
into a folder of your choosing. 

Now that you have Ant installed, you should set an `ANT_HOME` environment
variable to your Ant installation location. Then put Ant's `bin` directory
(e.g., `$ANT_HOME/bin`) in your path. The following gives examples of doing this
on Linux (Unix or Mac OS X) and Windows. 

On Linux (Unix or Mac OS X), if your Ant installation directory is
`/java/apache-ant-<version>` and you are running in Bash, set your `ANT_HOME`
and adjust your path by specifying the following in your `.bash_profile` or from
your terminal: 

    export ANT_HOME=/java/apache-ant-<version>
    export PATH=$PATH:$ANT_HOME/bin

On Windows, if your Ant installation folder is
`C:\Java\apache-ant-<version>`, set your `ANT_HOME` and path environment
variables appropriately in your system properties: 

1. Select *Start*, then right-select *Computer* &rarr; *Properties*. 

2. In the *Advanced* tab, click *Environment Variables...*. 

3. In the *System variables* section, click *New...*. 

4. Enter ...

    **Variable name:** `ANT_HOME`

    **Variable value:** *<Ant installation path>* (e.g.,
`C:\Java\apache-ant-<version>`)

    ... and click *OK*. 

5. Also in the *System variables* section, select your path variable and click
*Edit...*. 

6. Insert `%ANT_HOME%\bin;` after `%JAVA_HOME%\bin;` and click *OK*. 

7. Click *OK* to close out all of the system property windows. 

8. Open a new command prompt for your new environment variables to take affect. 
     
To verify Ant is in your path, execute `ant -version` from your terminal to make
sure your output looks similar to this: 

    Apache Ant(TM) version <version> compiled on <date> 

If the version information doesn't display, make sure your Ant installation is
referenced in your path. 

Now that you have Ant configured, let's set up your Plugins SDK environment. 

### Plugins SDK Configuration

Now that all the proper tools are in place, we must configure the Plugins SDK to
be able to deploy into your Liferay instance. Notice the Plugins
SDK contains a `build.properties` file. This file contains the default
settings about the location of your Liferay installation and your deployment
folder. You can use this file as a reference, but you should not modify it
directly (In fact, you will see the message "DO NOT EDIT THIS FILE" at the top
if you open it). In order to override the default settings, create a new file
named `build.<username>.properties` in the same folder, where `<username>` is
your user ID on your machine. For example, if your user name is jsmith (for John
Smith), you would create a file named `build.jsmith.properties`. 

Edit this file and add the following lines: 

    #
    # Specify the paths to an unzipped Tomcat bundle.
    #

    app.server.type=tomcat
    app.server.dir=the directory containing your application server

In case you are using Liferay Portal bundled with Tomcat,
`app.server.dir` should be the absolute path to your directory (e.g.
`c:/liferay-portal-<version>/bundles/tomcat-<tomcat-version>`). We specify
`app.server.type` as it is a best practice to specify the application server
type along with the application server properties we are overriding. 

Save the file. 

Next, let's consider the structure of the Plugins SDK. 

## Structure of the SDK

Each folder in the Plugins SDK contains scripts for creating new plugins of that
type. Here is the directory structure of the Plugins SDK: 

- `liferay-plugins-<version>/` - Plugins SDK root directory 

    - `clients/` - client applications directory 

    - `dist/` - archived plugins for distribution and deployment 

    - `ext/` - Ext plugins directory. See chapter 7 on Ext plugins. 

    - `hooks/` - hook plugins directory. See chapter 6 on hooks. 

    - `layouttpl/` - layout templates directory. See chapter 4 on creating Liferay
    Themes. 

    - `lib/` - commonly referenced libraries 

    - `misc/` - development configuration files. Example, a source code
    formatting specification file 

    - `portlets/` - portlet plugins directory. See chapter 3 on portlet
    development. 

    - `themes/` - themes plugins directory. See chapter 4 on creating Liferay
    themes. 

    - `tools/` - plugin templates and utilities 

    - `webs/` - web plugins directory 

    - `build.properties` - default SDK properties 

    - `build.<username>.properties` - (optional) override SDK properties 

    - `build.xml` - contains targets to invoke in the SDK 

    - `build-common.xml` - contains common targets and properties referenced
    throughout the SDK 

    - `build-common-plugin.xml` - contains common targets and properties
    referenced by each plugin 

    - `build-common-plugins.xml` - contains common targets and properties
    referenced by each plugin type 

New plugins are placed in their own subdirectory of the appropriate plugin type.
For instance, a new portlet called "greeting-portlet" would reside in
`liferay-plugins-<version>/portlets/greeting-portlet`. 

As you may notice, there is an Ant build file (`build.xml`) in each of the
plugins directories. Here are some Ant targets you'll commonly use in developing
your plugins: 

- `build-service` - builds the plugin into a service using Liferay Service
Builder 

- `clean` - cleans the residual files created by the invocations of the
compilation, archiving, and deployment targets 

- `compile` - compiles the plugin source code 

- `deploy` - builds and deploys the plugin to your application server 

- `format-source` - formats the source code per Liferay's source code
guidelines, informing you of violations that must be addressed. See the
[Development Sytle](http://www.liferay.com/community/wiki/-/wiki/Main/Development+Style#section-Development+Style-Format+Source)
community wiki page for details. 

- `format-javadoc` - formats the Javadoc per Liferay's Javadoc guidelines. See
the [Javadoc Guidelines](http://www.liferay.com/community/wiki/-/wiki/Main/Javadoc+Guidelines)
community wiki page for details. 

Next, let's learn how to use the Plugins SDK to create a plugin that extends
another plugin. 

## Creating Plugins to extend Plugins

For Liferay plugins, it's possible to create a new plugin that extends an
existing one. This allows you to utilize all the features of the existing plugin
in your new plugin while keeping your changes/extensions separate from the
existing plugin's source code. 

In order to create a plugin which extends another, you just need to follow these
steps: 

1. Create a new empty plugin in the Plugins SDK. 

2. Remove all the auto-generated files except `build.xml` and the docroot folder
   which should be empty. 

3. Copy the original WAR file of the plugin you'd like to extend (for example,
   social-networking-portlet-6.1.10.1-ee-ga1.war) to the root folder of your new
   plugin. 

4. Add the following line to your `build.xml` inside of the `<project>` tag in
   order to reference the original WAR file you are going to extend: 

        <property name="original.war.file" value="social-networking-portlet-6.1.10.1-ee-ga1.war" />

5. Copy any files from the original plugin that you'd like to overwrite to your
   new plugin (using the same folder structure) and run the Ant target `merge`.
   Please note that the `merge` target is called whenever the plugin is
   compiled. All you have to do is to check the Ant output: 

        dsanz@host:~/sdk/portlets/my-social-networking-portlet$ ant war
        Buildfile: /home/dsanz/sdk/portlets/my-social-networking-portlet/build.xml
        
        compile:
        
        merge:
        [mkdir] Created dir: /home/dsanz/sdk/portlets/my-social-networking-portlet/tmp
        [mkdir] Created dir: /home/dsanz/sdk/portlets/my-social-networking-portlet/tmp/WEB-INF/classes
        [mkdir] Created dir: /home/dsanz/sdk/portlets/my-social-networking-portlet/tmp/WEB-INF/lib
        
        merge-unzip:
        [unzip] Expanding: /home/dsanz/sdk/portlets/my-social-networking-portlet/social-networking-portlet-6.1.10.1-ee-ga1.war into /home/dsanz/sdk/portlets/my-social-networking-portlet/tmp
        [copy] Copying 2 files to /home/dsanz/sdk/portlets/my-social-networking-portlet/tmp
        [mkdir] Created dir: /home/dsanz/sdk/portlets/my-social-networking-portlet/docroot/WEB-INF/classes
        
        ...

This generates a plugin (you can find the WAR file in the `/dist` folder of your
plugins SDK) which combines the original one with your changes. 

Next, let's consider some best practices for developing plugins using the SDK. 

## Best Practices

The Plugins SDK can house all of your plugin projects enterprise-wide, or you
can have separate Plugins SDK projects for each plugin. For example, if you have
an internal Intranet using Liferay with some custom portlets, you could keep
those portlets and themes in their own Plugins SDK project in your source code
repository. If you also have an external instance of Liferay for your public
Internet web site, you could have a separate Plugins SDK with those projects as
well. Or you could further separate your projects by having a different Plugins
SDK project for each portlet or theme project. 

It is also possible to use use the Plugins SDK as a simple cross-platform
project generator. You can create a plugin project using the Plugins SDK and
then copy the resulting project folder to your IDE of choice. This method
requires some manual modification of the Ant scripts, but it makes it possible
to conform to the strict standards some organizations have for their Java
projects. 

Next, we'll discuss an alternative solution for developing and managing plugins:
Maven. 

## Maven alternative

As an alternative to developing plugins using the SDK, you can leverage the
Apache Maven build management framework. You'll be happy to know that we have
archetypes to help you build various types of plugins including Liferay
portlets, themes, and layout templates. 

To find out more developing plugins using Maven, see chapter 8.  

## Summary

Now that you have your Plugins SDK configured, know the directory structure and
available targets, and have learned some best practices, you are ready to start
developing plugins using Liferay's Plugins SDK. 

In the next chapter, we'll start portlet development. 
