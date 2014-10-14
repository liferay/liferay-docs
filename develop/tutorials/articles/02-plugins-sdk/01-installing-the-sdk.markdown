# Installing the SDK [](id=installing-the-sdk)

The first thing you should do is install Liferay Portal. If you haven't already
installed a Liferay bundle, follow the instructions in the [Installation and
Setup](/discover/portal/-/knowledge_base/6-1/installation-and-setup)
chapter of *Using Liferay Portal*. Many people use the Tomcat bundle for
development, as it's small, fast, and takes up fewer resources than most other
servlet containers. Although you can use any application server supported by
Liferay Portal for development, our examples use the Tomcat bundle. 

+$$$

**Note:** In Liferay Developer Studio, the SDK is already installed and ready to
use. Liferay Portal Enterprise Edition (EE) comes with Liferay Developer Studio
and much more (see
[CE vs EE](http://www.liferay.com/downloads/liferay-portal/overview)).
Download a
[free trial](http://www.liferay.com/c/portal/register_trial_license?redirect=/products/liferay-portal/ee/30-day-trial)
of Liferay Portal EE today. 

$$$

Installation steps:

1.   Download The Plugins SDK from our web site at
     [http://www.liferay.com](http://www.liferay.com). 

     Click the *Downloads* link at the top of the page. 

     From the *Liferay Portal 6.1 Community Edition* section, select the
     *Plugins SDK* option. 

     Click *Download*. 

2.  Unzip the archive to a folder of your choosing. Because some operating
    systems have trouble running Java applications from folders with names
    containing spaces, avoid using spaces when naming your folder.

    On Windows, to build a plugin's services (see Chapter 4 on Service Builder),
    the Plugins SDK and Liferay Portal instance must be on the same drive. For
    example, if your Liferay Portal instance is on your `C:\` drive, your
    Plugins SDK must also be on your `C:\` drive in order for Service Builder to
    be able to run successfully.

+$$$

**Tip**: By default, Liferay Portal Community Edition comes bundled with many
plugins. It's common to remove them to speed up the server start-up. Just
navigate to the `liferay-portal-[version]/tomcat-[tomcat-version]/webapps`
directory and delete all its subdirectories except for `ROOT` and `tunnel-web`. 

$$$

Now that you've installed the Plugins SDK, let's configure Apache Ant for use in
developing your plugins. 

## Ant Configuration [](id=ant-configuration)

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

1.  Select *Start*, then right-select *Computer* &rarr; *Properties*. 

2.  In the *Advanced* tab, click *Environment Variables...*. 

3.  In the *System variables* section, click *New...*. 

4.  Set the `ANT_HOME` variable:

    - **Variable name:** *ANT_HOME* 

    - **Variable value:** `<Ant installation path>` (e.g.,
    `C:\Java\apache-ant-<version>`) 

    Click *OK*. 

5.  Also in the *System variables* section, select your path variable and click
    *Edit...*. 

6.  Insert `%ANT_HOME%\bin;` after `%JAVA_HOME%\bin;` and click *OK*. 

7.  Click *OK* to close all system property windows. 

8.  Open a new command prompt for your new environment variables to take affect. 
     
To verify Ant is in your path, execute `ant -version` from your terminal to make
sure your output looks similar to this: 

    Apache Ant(TM) version <version> compiled on <date> 

If the version information doesn't display, make sure your Ant installation is
referenced in your path. 

Now that Ant is configured, let's set up your Plugins SDK environment. 

## Plugins SDK Configuration [](id=plugins-sdk-configuration)

Now we have the proper tools, but we need to configure the Plugins SDK to deploy
into your Liferay instance. The Plugins SDK contains a `build.properties` file
that contains the default settings about the location of your Liferay
installation and your deployment folder. You can use this file as a reference,
but you shouldn't modify it directly (In fact, you will see the message "DO NOT
EDIT THIS FILE" at the top if you open it). In order to override the default
settings, create a new file named `build.[username].properties` in the same
folder, where `[username]` is your user ID on your machine. For example, if your
user name is jbloggs, your file name would be `build.jbloggs.properties`. 

Edit this file and add the following lines: 

    app.server.type=the name build.properties uses for your application server type
    app.server.parent.dir=the directory containing your Liferay bundle
    app.server.tomcat.dir=the directory containing your application server

If you are using Liferay Portal bundled with Tomcat 7.0.40 and your
bundle is in your `c:/liferay-portal-6.1.30-ee-ga3` folder, you'd specify the
following lines:

    app.server.type = tomcat
    app.server.parent.dir = c:/liferay-portal-6.1.30-ee-ga3
    app.server.tomcat.dir = ${app.server.parent.dir}/tomcat-7.0.40 

Since we're using the Tomcat app server, we specified `tomcat` as our app server
type and we specified the `app.server.tomcat.dir` property. See the Plugins
SDK's `build.properties` for the name of the app server property that matches
your app server.

Save the file. 

Next, let's consider the structure of the Plugins SDK. 
