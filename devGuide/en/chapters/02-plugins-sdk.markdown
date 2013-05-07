# The Plugins SDK

Java developers use a wide variety of tools and development environments.
Liferay makes every effort to remain tool agnostic, so you can choose the tools
that work best for you. To that end, we provide a Plugins Software Development
Kit (SDK) based on Apache Ant that can be used along with any editor or
Integrated Development Environment (IDE). Much of this guide uses the Plugins
SDK and a text editor, but use whatever tool you're most comfortable with. As an
alternative to the Plugins SDK, in Chapter 9 we discuss Liferay IDE, a plugin
for Eclipse that simplifies development for Liferay. 

---

 ![tip](../../images/tip-pen-paper.png)**Tip**: If you use Eclipse and intend to
 use it from the very beginning, you might want to check out Chapter 9 first
 before reading this chapter.

---

We discuss the following topics in this chapter: 

- Initial Setup

- Structure of the SDK

- Best Practices

Setting up the Plugins SDK is easy. Let's get to it. 

## Installing the SDK

The first thing you should do is install Liferay Portal. If you haven't already
installed a Liferay bundle, follow the instructions in the [Installation and
Setup](http://www.liferay.com/documentation/liferay-portal/6.1/user-guide/-/ai/installing-lifer-1)
chapter of *Using Liferay Portal*. Many people use the Tomcat bundle for
development, as it's small, fast, and takes up fewer resources than most other
servlet containers. Although you can use any application server supported by
Liferay Portal for development, our examples use the Tomcat bundle. 

---

 ![note](../../images/tip-pen-paper.png)**Note:** In Liferay Developer Studio,
 the SDK is already installed and ready to use. Liferay Portal Enterprise
 Edition (EE) comes with Liferay Developer Studio and much more (see [CE vs
 EE](http://www.liferay.com/downloads/liferay-portal/overview)). Download a
 [free trial](http://www.liferay.com/c/portal/register_trial_license?redirect=/products/liferay-portal/ee/30-day-trial)
 of Liferay Portal EE today. 

---

Installation steps:

1. Download The Plugins SDK from our web site at
[http://www.liferay.com](http://www.liferay.com). 

    1.1. Click the *Downloads* link at the top of the page. 

    1.2. From the *Liferay Portal 6.1 Community Edition* section, select the
    *Plugins SDK* option. 

    1.3. Click *Download*. 

2. Unzip the archive to a folder of your choosing. Because some operating
systems have trouble running Java applications from folders with names
containing spaces, avoid using spaces when naming your folder.

---

 ![tip](../../images/tip-pen-paper.png)**Tip**: By default, Liferay Portal
 Community Edition comes bundled with many plugins. It's common to remove them
 to speed up the server start-up. Just navigate to the
 `liferay-portal-[version]/tomcat-[tomcat-version]/webapps` directory and delete
 all its subdirectories except for `ROOT` and `tunnel-web`. 

---

Now that you've installed the Plugins SDK, let's configure Apache Ant for use in
developing your plugins. 

### Ant Configuration

Building projects in the Plugins SDK requires that you install Ant (version 1.7
or higher) on your machine. Download the latest version of Ant from
[http://ant.apache.org/](http://ant.apache.org/). Extract the archive's contents
into a folder of your choosing. 

Now that Ant is installed, set an `ANT_HOME` environment variable to your Ant
installation location. Then put Ant's `bin` directory (e.g., `$ANT_HOME/bin`) in
your path. We'll give you examples of doing this on Linux (Unix or Mac OS X) and
Windows. 

On Linux (Unix or Mac OS X), if your Ant installation directory is
`/java/apache-ant-<version>` and your shell is Bash, set `ANT_HOME` and adjust
your path by specifying the following in `.bash_profile` or from your terminal: 

    export ANT_HOME=/java/apache-ant-<version>
    export PATH=$PATH:$ANT_HOME/bin

On Windows, if your Ant installation folder is `C:\Java\apache-ant-<version>`,
set your `ANT_HOME` and path environment variables appropriately in your system
properties: 

1. Select *Start*, then right-select *Computer* &rarr; *Properties*. 

2. In the *Advanced* tab, click *Environment Variables...*. 

3. In the *System variables* section, click *New...*. 

4. Set the `ANT_HOME` variable:

    4.1. **Variable name:** `ANT_HOME`. 

    4.2. **Variable value:** *<Ant installation path>* (e.g.,
    `C:\Java\apache-ant-<version>`). 

    4.3. Click *OK*. 

5. Also in the *System variables* section, select your path variable and click
*Edit...*. 

6. Insert `%ANT_HOME%\bin;` after `%JAVA_HOME%\bin;` and click *OK*. 

7. Click *OK* to close all system property windows. 

8. Open a new command prompt for your new environment variables to take affect. 
     
To verify Ant is in your path, execute `ant -version` from your terminal to make
sure your output looks similar to this: 

    Apache Ant(TM) version <version> compiled on <date> 

If the version information doesn't display, make sure your Ant installation is
referenced in your path. 

Now that Ant is configured, let's set up your Plugins SDK environment. 

### Plugins SDK Configuration

Now we have the proper tools, but we need to configure the Plugins SDK to deploy
into your Liferay instance. The Plugins SDK contains a `build.properties` file
that contains the default settings about the location of your Liferay
installation and your deployment folder. You can use this file as a reference,
but you shouldn't modify it directly (In fact, you will see the message "DO NOT
EDIT THIS FILE" at the top if you open it). In order to override the default
settings, create a new file named `build.[username].properties` in the same
folder, where `[username]` is your user ID on your machine. For example, if your
user name is jsmith, your file name would be `build.jsmith.properties`. 

Edit this file and add the following lines: 

    #
    # Specify the paths to an unzipped Tomcat bundle.
    #

    app.server.type=tomcat
    app.server.dir=the directory containing your application server

If you use Liferay Portal bundled with Tomcat, `app.server.dir` should be the
absolute path to your directory (e.g.
`c:/liferay-portal-[version]/bundles/tomcat-[tomcat-version]`). We specify
`app.server.type` as it's a best practice to specify the application server type
along with the application server properties we are overriding. 

Save the file. 

Next, let's consider the structure of the Plugins SDK. 

## Structure of the SDK

Each folder in the Plugins SDK contains scripts for creating new plugins of that
type. Here is the directory structure of the Plugins SDK: 

- `liferay-plugins-<version>/` - Plugins SDK root directory. 

    - `clients/` - client applications directory. 

    - `dist/` - archived plugins for distribution and deployment. 

    - `ext/` - Ext plugins directory. See chapter 7 on Ext plugins. 

    - `hooks/` - hook plugins directory. See chapter 6 on hooks. 

    - `layouttpl/` - layout templates directory. See chapter 4 on creating Liferay
    Themes. 

    - `lib/` - commonly referenced libraries. 

    - `misc/` - development configuration files. Example, a source code
    formatting specification file. 

    - `portlets/` - portlet plugins directory. See chapter 3 on portlet
    development. 

    - `themes/` - themes plugins directory. See Chapter 4 on creating Liferay
    themes. 

    - `tools/` - plugin templates and utilities. 

    - `webs/` - web plugins directory. 

    - `build.properties` - default SDK properties. 

    - `build.<username>.properties` - (optional) override SDK properties. 

    - `build.xml` - contains targets to invoke in the SDK. 

    - `build-common.xml` - contains common targets and properties referenced
    throughout the SDK. 

    - `build-common-plugin.xml` - contains common targets and properties
    referenced by each plugin. 

    - `build-common-plugins.xml` - contains common targets and properties
    referenced by each plugin type. 

New plugins are placed in their own subdirectory of the appropriate plugin type.
For instance, a new portlet called "greeting-portlet" would reside in
`liferay-plugins-[version]/portlets/greeting-portlet`. 

There's an Ant build file (`build.xml`) in each of the plugins directories. Here
are some Ant targets you'll commonly use in developing your plugins:

- `build-service` - builds the service layer for a plugin, using Liferay Service
Builder. 

- `clean` - cleans the residual files created by the invocations of the
compilation, archiving, and deployment targets. 

- `compile` - compiles the plugin source code. 

- `deploy` - builds and deploys the plugin to your application server. 

- `format-source` - formats the source code per Liferay's source code
guidelines, informing you of violations that must be addressed. See the
[Development Sytle](http://www.liferay.com/community/wiki/-/wiki/Main/Development+Style#section-Development+Style-Format+Source)
community wiki page for details. 

- `format-javadoc` - formats the Javadoc per Liferay's Javadoc guidelines. See
the [Javadoc Guidelines](http://www.liferay.com/community/wiki/-/wiki/Main/Javadoc+Guidelines)
community wiki page for details.

Next, let's consider some best practices for developing plugins using the SDK. 

## Best Practices

The Plugins SDK can house all of your plugin projects enterprise-wide, or you
can have separate Plugins SDK projects for each plugin. For example, if you have
an internal Intranet using Liferay with some custom portlets, you can keep those
portlets and themes in their own Plugins SDK project in your source code
repository. If you also have an external instance of Liferay for your public
Internet web site, you can have a separate Plugins SDK that also has those
projects. Or, you can further separate your projects by having a different
Plugins SDK project for each portlet or theme project. 

It's also possible to use use the Plugins SDK as a simple cross-platform project
generator. Create a plugin project using the Plugins SDK and then copy the
resulting project folder to your IDE of choice. You'll have to manually modify
the Ant scripts, but this process makes it possible to create plugins with the
Plugins SDK while conforming to the strict standards some organizations have for
their Java projects.

If you've read this far and aren't sure you want to develop your plugins using
the Plugins SDK, you also have the option to use Maven.

As an alternative to developing plugins using the SDK, you can leverage the
Apache Maven build management framework. You'll be happy to know that we have
archetypes to help you build various types of plugins including Liferay
portlets, themes, and layout templates. 

To learn more about developing plugins using Maven, see Chapter 8. 

## Summary

Now your Plugins SDK is configured, you know the directory structure and
available targets, and you've learned some best practices. You're ready to start
developing plugins using Liferay's Plugins SDK! 

Next, in Chapter 3, we'll start developing portlets! 
