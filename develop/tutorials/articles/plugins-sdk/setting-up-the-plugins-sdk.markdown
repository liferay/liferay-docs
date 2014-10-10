# Setting Up the Plugins SDK [](id=setting-up-the-plugins-sdk)

This tutorial explains how to install and configure the Liferay Plugins SDK and
its dependencies. 

The Plugins SDK requires Liferay Portal. If you haven't already installed a
Liferay bundle, follow the instructions in the
[Installation and Setup](/discover/deployment/-/knowledge_base/6-2/installation-and-setup)
chapter of the *Deployment* guide. Many people use the Tomcat bundle for
development, as it's small, fast, and takes up fewer resources than most other
servlet containers. Although you can use any application server supported by
Liferay Portal for development, our examples use the Tomcat bundle. 

+$$$

**Note:** In Liferay Developer Studio,
the SDK is already installed and ready to use. Liferay Portal Enterprise
Edition (EE) comes with Liferay Developer Studio and much more (see
[CE vs EE](http://www.liferay.com/downloads/liferay-portal/overview)).
Download a
[free trial](http://www.liferay.com/c/portal/register_trial_license?redirect=/products/liferay-portal/ee/30-day-trial)
of Liferay Portal EE today. 

$$$

To install the Plugins SDK, follow these steps:

1.   Download The Plugins SDK from our web site at <http://www.liferay.com>. 

     Click the *Developers* &rarr; *Downloads* link at the top of the page. 

     From the *Community* section, select the *Plugins SDK* option. 

     Click *Download*. 

2.  Unzip the archive to a folder of your choosing. Because some operating
    systems have trouble running Java applications from folders with names
    containing spaces, avoid using spaces when naming your folder.

    <!-- TODO include reference to learning path, when it is available.
    On Windows, to build a plugin's services (see the
    [Service Builder](http://www.liferay.com) learning path)
    -->

    On Windows, to build a plugin's services , the Plugins SDK and Liferay
    Portal instance must be on the same drive. For example, if your Liferay
    Portal instance is on your `C:\` drive, your Plugins SDK must also be on
    your `C:\` drive in order for Service Builder to be able to run
    successfully. 

+$$$

**Tip**: By default, Liferay Portal
Community Edition comes bundled with many plugins. It's common to remove them to
optimize the server's startup time. Just navigate to the
`liferay-portal-[version]/tomcat-[tomcat-version]/webapps` directory and remove
any plugins that you don't need. Don't delete `ROOT` since `ROOT` is the Liferay
application itself. Also, don't delete `marketplace-portlet` since you can use
the Marketplace portlet to download any plugins that you decide to use in the
future. 

$$$

Building projects in the Plugins SDK requires that you install Ant (version 1.7
or higher) on your machine. Download the latest version of Ant from
<http://ant.apache.org/>. Extract the archive's contents into a folder of your
choosing. 

Now that Ant is installed, create an `ANT_HOME` environment variable to capture
your Ant installation location. Then add Ant's `bin` directory (e.g.,
`$ANT_HOME/bin`) to your path. Doing this on Unix-like systems (Unix, Linux, or
Mac OS X) and Windows is demonstrated next. 

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

4.  Set the `ANT_HOME` variable to the path of your Apache Ant installation. 

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

Now that you've installed the Plugins SDK and you've configured Ant, it's time
to configure your Plugins SDK environment. 

Your Plugins SDK needs to know the location of our Liferay installation so it
can compile plugins against Liferay's JAR files and deploy plugins to your
Liferay instance. The Plugins SDK contains a `build.properties` file that
contains the default settings about the location of your Liferay installation
and your deployment folder. You can use this file as a reference, but you
shouldn't modify it directly (In fact, you will see the message "DO NOT EDIT
THIS FILE" at the top if you open it). In order to override the default
settings, create a new file named `build.[username].properties` in the same
folder, where `[username]` is your user ID on your machine. For example, if your
user name is jbloggs, your file name would be `build.jbloggs.properties`. 

Edit this file and add the following lines: 

    app.server.type=[the name build.properties uses for your application server type]
    app.server.parent.dir=[the directory containing your Liferay bundle]
    app.server.tomcat.dir=[the directory containing your application server]

If you're using Liferay Portal bundled with Tomcat 7.0.42 and your bundle is in
your `C:/liferay-portal-6.2` folder, you'd specify the following lines:

    app.server.type=tomcat
    app.server.parent.dir=C:/liferay-portal-6.2
    app.server.tomcat.dir=${app.server.parent.dir}/tomcat-7.0.42

Since the example properties above use the Tomcat application server, `tomcat`
is specified as the app server type and the `app.server.tomcat.dir` property is
specified. See the Plugins SDK's `build.properties` for the name of the app
server property that matches your app server. 

Save your `build.[username].properties` file. 

You're set to start using your Liferay Plugins SDK! 

