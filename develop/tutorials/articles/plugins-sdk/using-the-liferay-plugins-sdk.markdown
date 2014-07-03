# Using the Plugins SDK [](id=using-the-plugins-sdk-lp-6-2-develop-tutorial)
 
In this tutorial, we'll explain how to set up a Plugins SDK. We'll also discuss
its file structure and available Ant targets and share some best practices to
help you get the most out of the Plugins SDK. 

Setting up the Plugins SDK is easy. Let's get to it. 

## Installing the SDK [](id=installing-the-sdk-lp-6-2-develop-tutorial)

The first thing you should do is install Liferay Portal. If you haven't already
installed a Liferay bundle, follow the instructions in the [Installation and
Setup](https://www.liferay.com/documentation/liferay-portal/6.2/user-guide/-/ai/installation-and-setup-liferay-portal-6-2-user-guide-15-en)
chapter of *Using Liferay Portal 6.2*. Many people use the Tomcat bundle for
development, as it's small, fast, and takes up fewer resources than most other
servlet containers. Although you can use any application server supported by
Liferay Portal for development, our examples use the Tomcat bundle. 

---

 ![Note](../../images/tip-pen-paper.png) **Note:** In Liferay Developer Studio,
 the SDK is already installed and ready to use. Liferay Portal Enterprise
 Edition (EE) comes with Liferay Developer Studio and much more (see [CE vs EE](http://www.liferay.com/downloads/liferay-portal/overview)). Download a
 [free trial](http://www.liferay.com/c/portal/register_trial_license?redirect=/products/liferay-portal/ee/30-day-trial)
 of Liferay Portal EE today. 

---

Installation steps:

1.   Download The Plugins SDK from our web site at
     [http://www.liferay.com](http://www.liferay.com). 

     Click the *Developers* &rarr; *Downloads* link at the top of the page. 

     From the *Community* section, select the *Plugins SDK* option. 

     Click *Download*. 

2.  Unzip the archive to a folder of your choosing. Because some operating
    systems have trouble running Java applications from folders with names
    containing spaces, avoid using spaces when naming your folder.

    On Windows, to build a plugin's services (see the [Service Builder](http://www.liferay.com) learning path), the Plugins SDK and Liferay
    Portal instance must be on the same drive. E.g., if your Liferay Portal
    instance is on your `C:\` drive, your Plugins SDK must also be on your `C:\`
    drive in order for Service Builder to be able to run successfully. 

---

 ![Tip](../../images/tip-pen-paper.png) **Tip**: By default, Liferay Portal
 Community Edition comes bundled with many plugins. It's common to remove them
 to optimize the server's startup time. Just navigate to the
 `liferay-portal-[version]/tomcat-[tomcat-version]/webapps` directory and remove
 any plugins that you don't need. Don't delete `ROOT` since `ROOT` is the
 Liferay application itself. Also, don't delete `marketplace-portlet` since you
 can use the Marketplace portlet to download any plugins that you decide to use
 in the future. 

---

Now that you've installed the Plugins SDK, let's configure Apache Ant for use in
developing your plugins. 

### Ant Configuration [](id=ant-configuration-lp-6-2-develop-tutorial)

Building projects in the Plugins SDK requires that you install Ant (version 1.7
or higher) on your machine. Download the latest version of Ant from
[http://ant.apache.org/](http://ant.apache.org/). Extract the archive's contents
into a folder of your choosing. 

Now that Ant is installed, create an `ANT_HOME` environment variable to capture
your Ant installation location. Then add Ant's `bin` directory (e.g.,
`$ANT_HOME/bin`) to your path. We'll demonstrate how to do this on Unix-like
systems (Unix, Linux, or Mac OS X) and Windows. 

On Unix-like systems, if your Ant installation directory is
`/java/apache-ant-[version]` and your shell is Bash, set `ANT_HOME` and adjust
your path by specifying the following in `.bash_profile` or from your terminal: 

    export ANT_HOME=/java/apache-ant-[version]
    export PATH=$PATH:$ANT_HOME/bin

On Windows, if your Ant installation folder is `C:\Java\apache-ant-[version]`,
set your `ANT_HOME` and path environment variables appropriately in your system
properties: 

1.  Select *Start*, then right-select *Computer* &rarr; *Properties*. 

2.  In the *Advanced* tab, click *Environment Variables...*. 

3.  In the *System variables* section, click *New...*. 

4.  Set the `ANT_HOME` variable:
    - **Variable name:** *ANT_HOME*
    - **Variable value:** `[Ant installation path]` (e.g.,
    `C:\Java\apache-ant-[version]`)

    Click *OK*. 

5.  Also in the *System variables* section, select your path variable and click
    *Edit...*. 

6.  Insert `%ANT_HOME%\bin;` after `%JAVA_HOME%\bin;` and click *OK*. 

7.  Click *OK* to close all system property windows. 

8.  Open a new command prompt for your new environment variables to take affect. 

To verify Ant is in your path, execute `ant -version` from your terminal to make
sure your output looks similar to this: 

    Apache Ant(TM) version [version] compiled on [date]

If the version information doesn't display, make sure your Ant installation is
referenced in your path. 

Now that Ant is configured, let's set up your Plugins SDK environment. 

### Plugins SDK Configuration [](id=plugins-sdk-configuration-lp-6-2-develop-tutorial)

Now we have the proper tools set up. Next, we need to configure our Plugins SDK.
It needs to know the location of our Liferay installation so it can compile
plugins against Liferay's JAR files and can deploy plugins to your Liferay
instance. The Plugins SDK contains a `build.properties` file that contains the
default settings about the location of your Liferay installation and your
deployment folder. You can use this file as a reference, but you shouldn't
modify it directly (In fact, you will see the message "DO NOT EDIT THIS FILE" at
the top if you open it). In order to override the default settings, create a new
file named `build.[username].properties` in the same folder, where `[username]`
is your user ID on your machine. For example, if your user name is jbloggs, your
file name would be `build.jbloggs.properties`. 

Edit this file and add the following lines: 

    app.server.type=[the name build.properties uses for your application server type]
    app.server.parent.dir=[the directory containing your Liferay bundle]
    app.server.tomcat.dir=[the directory containing your application server]

If you are using Liferay Portal bundled with Tomcat 7.0.42 and your
bundle is in your `C:/liferay-portal-6.2` folder, you'd specify the
following lines:

    app.server.type=tomcat
    app.server.parent.dir=C:/liferay-portal-6.2
    app.server.tomcat.dir=${app.server.parent.dir}/tomcat-7.0.42

Since we're using the Tomcat application server, we specified `tomcat` as our
app server type and we specified the `app.server.tomcat.dir` property. See the
Plugins SDK's `build.properties` for the name of the app server property that
matches your app server. 

Save the file. Next, let's consider the structure of the Plugins SDK. 

## Structure of the SDK [](id=structure-of-the-sdk-lp-6-2-develop-tutorial)

Each folder in the Plugins SDK contains scripts for creating new plugins of that
type. Here is the directory structure of the Plugins SDK: 

- `liferay-plugins-[version]/` - Plugins SDK root directory. 
    - `clients/` - client applications directory. 
    - `dist/` - archived plugins for distribution and deployment. 
    - `ext/` - Ext plugins directory. See the [Ext Plugins tutorial](http://www.liferay.com) for details. 
    - `hooks/` - hook plugins directory. See the [Hook Plugins tutorial](http://www.liferay.com) for details. 
    - `layouttpl/` - layout templates directory. See the [Layout Templates tutorial](http://www.liferay.com) for details.  
    - `lib/` - commonly referenced libraries. 
    - `misc/` - development configuration files. Example, a source code
      formatting specification file. 
    - `portlets/` - portlet plugins directory. See the [Portlets tutorial](http://www.liferay.com) for details. 
    - `themes/` - themes plugins directory. See the [Themes tutorial](http://www.liferay.com) for details. 
    - `tools/` - plugin templates and utilities. 
    - `webs/` - web plugins directory. 
    - `build.properties` - default SDK properties. 
    - `build.[username].properties` - (optional) override SDK properties. 
    - `build.xml` - contains targets to invoke in the SDK. 
    - `build-common.xml` - contains common targets and properties referenced
      throughout the SDK. 
    - `build-common-plugin.xml` - contains common targets and properties
      referenced by each plugin. 
    - `build-common-plugins.xml` - contains common targets and properties
      referenced by each plugin type. 

New plugins are placed in their own subdirectory of the appropriate plugin type.
For instance, a new portlet called *greeting-portlet* would reside in
`liferay-plugins-[version]/portlets/greeting-portlet`. 

There's an Ant build file called `build.xml` in each of the plugins
directories. Here are some Ant targets you'll commonly use in developing your
plugins: 

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

Next, let's create a plugin using Liferay SDK from a terminal environment. 

## Creating Plugins with Liferay SDK [](id=creating-plugins-with-liferay-sdk-lp-6-2-develop-tutorial)

Liferay IDE makes it easy to create and deploy Liferay plugin projects since it
includes a bundled Liferay SDK. If you don't want to use Eclipse, you can still
leverage the SDK to create your Liferay plugins. 

Navigate to the `portlets` folder of your Plugins SDK and follow these steps: 

1.  On Linux and Mac OS X, enter

        ./create.sh [portlet name] "[portlet title]"

    For example,

        ./create.sh my-new-portlet "My New Portlet"

2.  On Windows, enter

        create.bat [portlet name] "[portlet title]"

    For example,

        create.bat my-new-portlet "My New Portlet"

Your terminal displays a BUILD SUCCESSFUL message from Ant, and a new project
containing your portlet plugin's directory structure is created inside of the
`portlets` folder in your Plugins SDK. This is where you'll work to implement
your own functionality. Note that the Plugins SDK automatically appends
"-portlet" to the project name when creating its directory if the project name
you specified doesn't end in "-portlet".

![Figure 2.19: Creating A New Portlet.](../../images/02-terminal-build-success-plugins-sdk-project-create.png)

---

 ![Tip](../../images/tip-pen-paper.png) **Tip**: If you are using a source
 control system such as Subversion, CVS, Mercurial, Git, etc., this would be a
 good moment to do an initial check-in of your changes. After building the
 plugin for deployment, several additional files will be generated that should
 *not* be handled by the source control system. 

---

Now you have a Liferay portlet project. You still need to deploy the project to
your Liferay Server. Liferay IDE provides multiple options: you can drag and
drop your project onto the server or right click the server and select *Add and
Remove...*. It's almost as easy using an Ant target directly from the Plugins
SDK. Simply open a terminal window, navigate to your `portlets/[portlet name]`
directory in your Plugins SDK and enter

    ant deploy

A BUILD SUCCESSFUL message indicates that Ant built your portlet's WAR file and
that it's now being deployed. If you switch to the terminal window running
Liferay, within a few seconds you should see the message `1 portlet for
[portlet name] is available for use`.  If not, double-check your configuration. 

In your web browser, log in to the portal as explained earlier. Hover over *Add*
at the top of the page and click on *More*. Select the *Sample* category, and
then click *Add* next to *[portlet title]*. Your portlet appears in the page
below. 

Next, let's consider some best practices for developing plugins using the SDK. 

## Best Practices [](id=best-practices-lp-6-2-develop-tutorial)

The Plugins SDK can house all of your plugin projects enterprise-wide, or you
can have separate Plugins SDK projects for each plugin. For example, if you have
an internal Intranet using Liferay with some custom portlets, you can keep those
portlets and themes in their own Plugins SDK project in your source code
projects. Or, you can further separate your projects by having a different
Plugins SDK project for each portlet or theme project. 

It's also possible to use the Plugins SDK as a simple cross-platform project
generator. Create a plugin project using the Plugins SDK and then copy the
resulting project folder to your IDE of choice. You'll have to manually modify
the Ant scripts, but this process makes it possible to create plugins with the
Plugins SDK while conforming to the strict standards some organizations have for
their Java projects. 

## Next Steps [](id=next-steps-lp-6-2-develop-tutorial-11)

The Liferay Plugins SDK uses the Apache Ant build tool. If that makes you
uncomfortable, consider using Liferay's Apache Maven archetypes to build your
custom Liferay plugins. To learn about developing Liferay plugins with Maven,
and to have some fun with classic poetry while doing it, check out the
[Developing Liferay Plugins Using Maven](http://www.liferay.com) tutorial.
