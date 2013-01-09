
# The Plugins SDK 

Java developers have a wide variety of tools and development environments.
Liferay makes every effort to remain tool agnostic, so that you can choose the
tools that works best for you. For that reason, we provide a Plugins Software
Development Kit (SDK) which is based on Apache Ant and may be used along with
any editor or Integrated Development Environment (IDE). The chapters of this
guide will use the Plugins SDK and a text editor, but you may use whatever tool
you are comfortable with. In a later chapter we also introduce Liferay IDE, a
plugin for eclipse that simplifies development for Liferay.

---

 ![tip](../../images/tip-pen-paper.png)**Tip**: If you are an Eclipse user and
 prefer to start working with it from be very beginning, you can read that
 chapter first before reading the rest of the guide.

---

We discuss the following topics in this chapter:

- *Initial Setup:* How to install the Plugins SDK and configure it for your use
with your Liferay Portal server.

- *Structure of the SDK:* Where to develop your plugin within the SDK and what
Apache Ant targets are available.

-	*Best Practices:* Suggests helpful ways for organizing your plugin projects.

- *Maven:* Refers to recent developments in using Apache Maven in developing
Liferay plugins.

Setting up the Plugins SDK is easy. Let's get to it.

## Installing the SDK 

Setting up your environment for Liferay development is straightforward. First,
you will need Liferay Portal. If you have yet to install Liferay with an
application server, follow instructions in the
[Installation and Setup](http://www.liferay.com/documentation/liferay-portal/6.1/user-guide/-/ai/installing-lifer-1)
chapter of *Using Liferay Portal 6.1*. Many people use the Tomcat application server
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

	I.	Click the *Downloads* link at the top of the page

	II. Then, within the *Liferay Portal 6.1 Community Edition* section, select
	the *Plugins SDK* option

	III.	Click *Download*

2. Unzip the archive to a folder of your choosing. Avoid using a folder name
that contains spaces because some operating systems have problems running Java
applications in folders with spaces in their name.

---

 ![tip](../../images/tip-pen-paper.png)**Tip**: By default Liferay Portal
 Community Edition comes bundled with many plugins. It's common to remove them
 to speed up the server start-up. To do this, in the
 `liferay-portal-[version]/tomcat-[tomcat-version]/webapps` directory, delete
 all the directories except for `ROOT` and `tunnel-web.`

---

Now that you have Plugins SDK installed, let's configure Apache Ant for use in
developing your plugins.

### Ant Configuration 

Building projects in the Plugins SDK requires that you have Ant (version 1.7 or
higher) installed on your machine. Download the latest version of Ant from
[http://ant.apache.org/](http://ant.apache.org/). Decompress the archive into a
folder of your choosing.

Next, set an environment variable called `ANT_HOME` which points to the folder
to which you installed Ant. Use this variable to add the binaries for Ant to
your `PATH` by adding $`ANT_HOME\bin` to your `PATH` environment variable.

You can do this on Linux or Mac OS X by modifying your `.bash_profile` file as
follows (assuming you installed Ant in `/java`):

	export ANT_HOME=/java/apache-ant-1.8.2
	export PATH=$PATH:$ANT_HOME/bin

Close and reopen your terminal window to make these settings take effect.

You can do this on Windows by going to *Start -\> Control Panel* , and
double-clicking the *System* icon. Go to *Advanced*, and then click the
*Environment Variables* button. Under *System Variables*, select *New*. Make the
Variable Name *ANT_HOME* and the Variable Value the path to which you installed
Ant (e.g. `c:\java\apache-ant-1.8.2`), and click *OK*.

Scroll down until you find the `PATH` environment variable. Select it and click
*Edit*. Add `%ANT_HOME%\bin` to the end of the *Variable Value*. Click *OK*, and
then click *OK* again. Open a command prompt and type `ant -version` and press
*Enter*. You should get output similar to this:

	Apache Ant(TM) version 1.8.2 compiled on December 20 2010

If not, check your environment variable settings and make sure the directory
where you unzipped Ant is referenced in your `PATH`.

Now that you have Apache Ant setup, let's configure the Plugins SDK for
environment - specifically your Liferay Portal and application server
environment.

### Plugins SDK Configuration 

Now that all the proper tools are in place, we must configure the Plugins SDK to
be able to deploy into your Liferay instance. You will notice that the Plugins
SDK contains a file called `build.properties`. This file contains the default
settings about the location of your Liferay installation and your deployment
folder. You can use this file as a reference, but you should not modify it
directly (In fact, you will see the message "DO NOT EDIT THIS FILE" at the top
if you open it). In order to override the default settings, create a new file in
the same folder called `build.[user.name].properties`, where `[user.name]` is
your user ID on your machine. For example, if your user name is jsmith (for John
Smith), you would create a file called `build.jsmith.properties`.

Edit this file and add the following lines:

    #
    # Specify the paths to an unzipped Tomcat bundle.
    #

	app.server.type=tomcat
	app.server.dir=the directory containing your application server

In the case that you are using Liferay Portal bundled with Tomcat,
`app.server.dir` should be the absolute path to your directory (e.g.
`c:/liferay-portal-[version]/bundles/tomcat-[tomcat-version]`). We specify
`app.server.type` as it is a best practice to specify the application server
type along with the application server properties we are overriding.

Save the file.

Next, let's consider the structure of the Plugins SDK.

## Structure of the SDK 

Each folder in the Plugins SDK contains scripts for creating new plugins of that
type. Here is the directory structure of the Plugins SDK:

- `liferay-plugins-[version]/` - Plugins SDK root directory.

	-	`clients/` - Client applications directory.

	-	`dist/` - Archived plugins for distribution and deployment.

	-	`ext/` - Ext plugins directory. See chapter *Ext plugins*.

	-	`hooks/` - Hook plugins directory. See chapter *Hooks*.

	- `layouttpl/` - Layout templates directory. See chapter *Creating Liferay
	Themes*.

	-	`lib/` - Commonly referenced libraries.

	- `misc/` - Development configuration files. Example, a source code
	formatting specification file.

	- `portlets/` - Portlet plugins directory. See chapter *Portlet
	Development*.

	- `themes/` - Themes plugins directory. See chapter *Creating Liferay
	Themes*.

	-	`tools/` - Plugin templates and utilities.

	-	`webs/` - Web plugins directory.

	-	`build.properties` - Default SDK properties.

	-	`build.[user.name].properties` (optional) - Override SDK properties.

	-	`build.xml` - Contains targets to invoke for the SDK.

	- `build-common.xml` - Contains common targets and properties referenced
	throughout the SDK.

	- `build-common-plugin.xml` - Contains common targets and properties
	referenced by each plugin.

	- `build-common-plugins.xml` - Contains common targets and properties
	referenced by each plugin type.

New plugins are placed in their own subdirectory of the appropriate plugin type.
For instance, a new portlet called "greeting-portlet" would reside in
`liferay-plugins-[version]/portlets/greeting-portlet`.

As you may notice, there is an Ant build file (`build.xml`) in each of the
plugins directories. Here are some Ant targets you'll commonly use in developing
your plugins:

- `build-service` - Builds the plugin into a service using Liferay Service
Builder.

- `clean` - Cleans the residual files created by the invocations of the
compilation, archiving and deployment targets.

-	`compile` - Compiles the plugin source code.

-	`deploy` - Builds and deploys the plugin to your application server.

- `format-source` - Formats the source code per Liferay's source code
guidelines.

-	`format-javadoc` - Formats the Javadoc per Liferay's Javadoc guidelines.

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

3. Copy the origin WAR file of the plugin you'd like to extend (for example,
   social-networking-portlet-6.1.10.1-ee-ga1.war) to the root folder of your new
   plugin.

4. Add the following line to your `build.xml` inside of the <project> tag in
   order to reference the original war file you are going to extend. 

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

## Developing plugins using Maven

As an alternative to developing plugins using Liferay's Ant-based Plugins SDK,
you can leverage the Apache Maven build management framework. To mention just a
few of its features, Maven offers a simple build process, a project object model
(POM), a project life cycle, and a dependency management system. Maven's core
installation is light-weight providing core plugins for compiling source code
and creating distributions, but the abundance of non-core plugins for Maven lets
you extend it and make easy customizations.

Many developers are switching from Ant to Maven because it offers a common
interface for project builds. Maven's universal directory structure facilitates
developers understanding and building each other's projects more quickly.
Developers follow a simple processes to build, install, and deploy the project
artifacts and Maven seemingly does the rest.

Maven provides a clear definition of a project's structure and manages a project
from a single piece of information--a project object model (POM). Understanding
a Maven project can be much more simple than, for instance, trying to understand
an Ant-based project's various build files. Maven enforces a standard way to
build projects, whereas with Ant projects, the way each project is built may
differ from project to project. Also, Maven provides an easy way to share
artifacts (e.g., JARs, WARs, ... etc.) across projects via shared repositories.

However, you may find the project structure too restrictive or you may not find
it worthwhile to reorganize your project to work with Maven. Maven is primarily
intended for Java-based projects, so you may find it difficult to manage your
project's non-Java source code. We encourage you to consider Maven's advantages
and disadvantes in deciding what is best to use in managing your projects. For
an in-depth look at Maven's features and what Maven can do for you, visit
*Maven: The Complete Reference* by Sonatype, Inc., at
[http://www.sonatype.com/books/mvnref-book/reference/](http://www.sonatype.com/books/mvnref-book/reference/).

You'll be happy to know Liferay provides Maven archetypes to help you build
various types of plugins including Liferay portlets, themes, hooks, layout
templates, web plugins, and much more. You can also install and deploy Liferay
artifacts to your repositories. We will dive into all these topics in this
chapter, so buckle up and prepare to be Mavenized!

### Installing Maven

You can download Maven from
[http://maven.apache.org/download.cgi](http://maven.apache.org/download.cgi) and
install it where you wish on your machine. We recommend putting your Maven
installation's `bin` directory in your system's path, so you can run the Maven
executable (`mvn`) easily from your terminal.

Next, let's learn about the types of repositories you can use with Maven
projects.

### Understanding Maven repositories

Wouldn't it be nice to install and deploy your Liferay artifacts to a
repository? Great news! With Maven, you can install your artifacts to your
machine's local repository and even deploy them to remote repositories for
sharing privately with your team or for releasing publicly. Your *local*
repository contains a cache of downloaded artifacts, and artifacts you build and
install to it. *Remote* repositories are for sharing artifacts either publicly
or privately within your development team. To learn more about using artifact
repositories see
[http://maven.apache.org/guides/introduction/introduction-to-repositories.html](http://maven.apache.org/guides/introduction/introduction-to-repositories.html).

With Maven, you can also configure a proxy server to mediate your requests to
public Maven repositories, and to cache artifacts locally for faster more
reliable access. Using a local proxy/repository helps you build projects faster
and more reliably, as accessing remote repositories is slower and remote
repositories may sometimes be unavailable. Most Maven proxies can also host
private repositories used for hosting your private artifacts. If you're
interested in running your repository behind a proxy, see
[http://www.sonatype.com/books/nexus-book/reference/install-sect-proxy.html](http://www.sonatype.com/books/nexus-book/reference/install-sect-proxy.html).

Now that you've been introduced to local and remote Maven repositories and proxy
servers, let's consider using a repository management server to create and
manage your Maven repositories.

### Using a repository management server

If you want to share Liferay plugins with teammates and/or need to install
Liferay CE/EE artifacts downloaded as a zip file, you'll need a hosted
repository. If you do not need to share the Liferay plugins you are developing
and are relying on Maven to download Liferay CE artifacts automatically from the
central repository, you can skip this section on creating a repository.

Let's create a Maven repository using a repository manager called Nexus.
You can download Nexus OSS from
[http://www.sonatype.org/nexus/](http://www.sonatype.org/nexus/) and follow
installation and start-up instructions at
[http://www.sonatype.com/books/nexus-book/reference/_installing_nexus.html](http://www.sonatype.com/books/nexus-book/reference/_installing_nexus.html).

To create a repository using Nexus, do the following:

1. Open your browser to the location of your
Nexus repository server (e.g., [http://localhost:8081/nexus](http://localhost:8081/nexus))
and log in.

2. Click on *Repositories* and navigate to *Add...* &rarr; *Hosted Repository*.

	![Figure 2.3: Adding a repository to hold your Liferay
	artifacts is easy with Nexus OSS.](../../images/maven-nexus-create-repo.png)

	Note: To learn more about each type of Nexus repository, read Sonatype's
	*Managing Repositories* at
	[http://www.sonatype.com/books/nexus-book/reference/confignx-sect-manage-repo.html](http://www.sonatype.com/books/nexus-book/reference/confignx-sect-manage-repo.html).

3. Give your repository appropriate properties based on the access you want to
provide to the artifacts to be hosted in it. Since we will be installing
artifacts into the repository, specify *Repository Policy:* `Release`. Below are
some example repository property values:

	-	*Repository ID:* `liferay-releases`
	-	*Repository Name:* `Liferay Release Repository`
	-	*Provider:* `Maven2`
	-	*Repository Policy:* `Release`

You now have a repository configured and ready for Liferay's Maven artifacts!
If you don't already have a Maven project for developing your your Liferay
plugins or if you are curious about using a parent project to organize your
Liferay plugin projects, we'll show you how. Otherwise, just follow along as
we'll use this parent project paradigm in demonstrating how to use Liferay
artifacts and how to develop Liferay plugins based on Liferay archetypes. 

### The Project Object Model (POM)

<!-- TODO Jim - move this into the next section -->

<!-- TODO Jim - edit below -->

Maven uses a *Project Object Model (POM)* to describe a software project. The
POM is specified as XML in a file named `pom.xml`. A project's POM describes its
directories, required plugins, build sequence, and dependencies. In addition,
the POM tells Maven what kind of project is being used and how to modify default
behavior to generate output from source. <!-- TODO Cody - By mentioning the
"kind of project" (above), do you mean the project's archetype? --> <!-- TODO
Cody - Please explain how the POM tells how to "modify default behavior" (above)
--> Maven only needs the POM as a project's sole descriptive declaration. The
`pom.xml` file is like a blueprint of your project. You create the `pom.xml`
file and Maven does the rest, downloading your project's inferred dependencies
and building your project artifacts! To get familiar with the Project Object
Model, read Sonatype's documentation for it at
[http://www.sonatype.com/books/mvnref-book/reference/pom-relationships.html](http://www.sonatype.com/books/mvnref-book/reference/pom-relationships.html).


### Using Liferay Maven artifacts

You may be wondering how to begin installing and deploying Maven artifacts. We
<!-- TODO revisit as I'm not sure we are emphasizing installing and deploying
artifacts to a repository per-se. Rather, we are building artifacts and then
deploying them on Liferay -->
have the repository configured but how do we begin the Maven process? Your
plugins require access to Liferay Portal's artifacts. There are two different
options for accessing the artifacts:

-	Download CE/EE artifacts from Liferay.
-	Retrieve CE artifacts from the *Central Repository* automatically.

<!-- TODO: *Building your own CE artifacts* is another option. Eventually need
to document. -->

Before we go into these different options, let's set up some general settings
you'll need to build plugins. We'll create a *parent* project, or project
*root*, to house plugin projects. The parent project's `pom.xml` file is used
across the plugin projects and contains all necessary information about your
parent project and the configuration of your plugins. Let's begin mavenizing!
<!-- TODO why mention the Liferay plugins SDK here?
Also, if you were using Ant, this is the place where you would extract
the Liferay plugins SDK.
-->
<!-- TODO not sure this is needed.
After you parent project is created, we'll insert a
simple `pom.xml` file for us to add to later on, depending on which option you
choose for accessing Liferay Maven artifacts. The `pom.xml` contains all
necessary information about your project and the configuration of your plugins.
Let's begin mavenizing!
-->

1. Create a new directory which will be your project root. For this
example, we'll name the project root `sample-parent-project`. Your parent
project can be placed anywhere on the file system and still has the ability to
interact with your configured Liferay instance.

2. Inside the `sample-parent-project` directory, create a `pom.xml` file and
insert the following code:

		<?xml version="1.0" encoding="UTF-8"?>
		<project xmlns="http://maven.apache.org/POM/4.0.0" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">
			<modelVersion>4.0.0</modelVersion>

			<groupId>com.liferay.sample</groupId>
			<artifactId>sample-parent-project</artifactId>
			<version>1.0-SNAPSHOT</version>
			<packaging>pom</packaging>

			<name>sample-parent-project</name>
			<url>http://www.liferay.com</url>

			<properties>
				<liferay.auto.deploy.dir>${liferay.auto.deploy.dir}</liferay.auto.deploy.dir>
				<liferay.version>${liferay.version}</liferay.version>
			</properties>
		</project>

---

![note](../../images/tip-pen-paper.png)**Note:** When creating your own project,
you'll need to modify these settings. If you're following along with creating
this sample project, you'll only need to replace the
`${liferay.auto.deploy.dir}` and `${liferay.version}` variables. Because you
specify your Liferay instance's deploy directory in the parent project's
`pom.xml`, the location of the parent project directory is irrelevant and will
work from anywhere on your file system.

---

3. The auto deploy directory is where your plugin is copied for Liferay to
deploy. Input your Liferay bundle's deploy directory inside the
`<liferay.auto.deploy.dir>...</liferay.auto.deploy.dir>` tags of the `pom.xml`.

4. Input your Liferay version number that you are using inside the
`<liferay.version>...</liferay.version>` tags. Your `<properties/>` element
should appear similarly to the code below:

		<properties>
			<liferay.auto.deploy.dir>E:\liferay-portal-6.1.20-ee-ga2\deploy</liferay.auto.deploy.dir>
			<liferay.version>6.1.20</liferay.version>
		</properties>

The basic configurations of Maven are complete. Of course, depending on which
way you access your Maven artifacts or your preferences for a local/remote
repository, these settings will change.

At this point, you will need to choose the route to take and we will offer road
signs for you to follow. First, we'll explore downloading CE/EE artifacts.

#### Downloading CE/EE artifacts manually

Liferay offers a package that includes a convenient script to install Liferay
artifacts to your local or remote repository. This is the only option available
for using the Liferay Maven EE artifacts. The EE artifacts package can be
downloaded by visiting Liferay's Customer Portal. To access the Liferay Customer
Portal and begin downloading Liferay Maven EE artifacts, follow the steps below:

1. Navigate to [www.liferay.com](https://www.liferay.com/) and sign in.

2. Go to *Places* &rarr; *Customer Portal*.

3. Select *Liferay Portal* from the *Downloads* panel

4. Inside the *Filter by:* application, enter the appropriate version and
*Development*.

5. Select *Download* listed under *Liferay Portal <Version> Maven*.

	![Figure 2.4: You can download the Liferay Maven EE artifacts by visiting the Liferay Customer Portal.](../../images/maven-customer-portal.png)

Furthermore, if you're interested in downloading Liferay Maven CE artifacts,
visit
[SourceForge](http://sourceforge.net/projects/lportal/files/Liferay%20Portal/).
Once you've arrived to SourceForge's *Liferay Portal* page, you can select the
specific version of Maven artifacts you're interested in. For example, if you
want Maven artifacts for the 6.1.1 CE GA2 Liferay version, select version
*6.1.1 GA2* and then select `liferay-portal-maven-6.1.1-ce-ga2`.

![Figure 2.5: After selecting the Liferay version, simply select the `liferay-portal-maven-<version>` file for downloading.](../../images/maven-select-download.png)

After installing and extracting the ZIP file, you're ready to move on to the
installing and deploying phase for your local/remote repository.

#### Retrieving CE artifacts automatically from Central Repository

Liferay offers another option for those interested in using Liferay Maven CE
artifacts. The CE artifacts are publicly available on Liferay's [Central
Repository](http://search.maven.org/#search|ga|1|liferay) and updated after each
release (e.g. 6.1.1, 6.1.0). When using the Central Repository, there is no need
to download anything because all of Liferay's CE artifacts are available via the
internet. Let's run through the process of accessing the CE artifacts from the
Central Repository.

To access the Central Repository, you only need to modify your `pom.xml` with
several dependencies. These dependencies will point your POM to the Central
Repository and reference its artifacts. Insert the following list of
dependencies between the `</properties>` and `</project>` tags of the original
`pom.xml` we configured:

    <dependencies>
	    <dependency>
            <groupId>com.liferay.portal</groupId>
            <artifactId>portal-client</artifactId>
            <version>6.1.1</version>
        </dependency>
        <dependency>
            <groupId>com.liferay.portal</groupId>
            <artifactId>portal-impl</artifactId>
            <version>6.1.1</version>
            <scope>provided</scope>
        </dependency>
        <dependency>
            <groupId>com.liferay.portal</groupId>
            <artifactId>portal-service</artifactId>
            <version>6.1.1</version>
            <scope>provided</scope>
        </dependency>
        <dependency>
            <groupId>com.liferay.portal</groupId>
            <artifactId>portal-web</artifactId>
            <version>6.1.1</version>
            <type>war</type>
            <scope>provided</scope>
        </dependency>
        <dependency>
            <groupId>com.liferay.portal</groupId>
            <artifactId>util-bridges</artifactId>
            <version>6.1.1</version>
        </dependency>
        <dependency>
            <groupId>com.liferay.portal</groupId>
            <artifactId>util-java</artifactId>
            <version>6.1.1</version>
        </dependency>
        <dependency>
            <groupId>com.liferay.portal</groupId>
            <artifactId>util-taglib</artifactId>
            <version>6.1.1</version>
        </dependency>
    </dependencies>

<!-- Later, when creating plugins, we'll modify the `pom.xml` to install and
deploy specific plugins to your local/remote repository. -->

As you can see, Liferay offers many resources to aid you in creating and
maintaining your Liferay artifacts using Maven. Next, let's install and deploy
your Liferay artifacts to a local repository and remote repository.

#### Installing Liferay artifacts to a Maven repository

A specific process is followed when using Liferay Maven artifacts. It is
important to understand this process as it will provide for easier setup while
also giving you the knowledge of what these modified settings and commands do.

Let's assume your project has one dependency named `liferay-panda`, which is
placed in your parent project's `pom.xml`. Here is the logic for how Maven
resolves your project's dependencies as part of a build:

1. Checks local repository for `liferay-panda`.
2. If found, continue with build.
3. If not found, check for `liferay-panda` in remote repository.
4. If found, download `liferay-panda` to local repository. Refer back to step 1.
5. If not found in remote repository, check for `liferay-panda` in Central
Repository
6. If found, download `liferay-panda` to local repository. Refer back to step 1.
7. If not found, the artifact does not exist or your settings are not correct.

As you can see, this logic explains that every artifact you'll use when
developing plugins must first be installed into your local repository. However,
you're able to specify what you want deployed into your remote repositories.
We'll go through how to set up your settings to specify these deployment
locations. We'll also describe how to deploy individual plugins. Let's begin by,
first, installing the downloaded ZIP file to a local repository.

Using your command prompt, navigate to the `liferay-portal-maven-<version>`
directory and type `ant install`. Your console begins installing the
artifacts from your package into a local repository which is located in the
`${USER_HOME}/.m2/repository` directory. You now have a local repository
containing Liferay artifacts on your machine. Now wasn't that easy?

---

![note](../../images/tip-pen-paper.png)**Important:** If you're developing EE
plugins, you must have a local repository containing Liferay Maven EE artifacts.

---

If you're using the Central Repository for Maven artifacts, you do not need to
manually install because this will be done for you when developing your plugins.
Now that we have a Maven local repository set, let's enable the capability to
deploy our Liferay artifacts using Maven.

Let's configure the necessary settings to enable deploying to your Nexus
repository. The steps below should be done for both methods (downloaded ZIP file
and Central Repo), unless explicitly stated otherwise. Follow the steps below:

1. Navigate to the `${USER_HOME}/.m2/settings.xml` file. If this file doesn't
exist on your machine, create it.

2. Input the settings of the repository you created for Nexus along with a
public mirror:

		<?xml version="1.0"?>
		<settings>
			<mirrors>
				<mirror>
					<id>local</id>
					<name>Local mirror repository</name>
					<url>http://localhost:8081/nexus/content/groups/public</url>
					<mirrorOf>*</mirrorOf>
				</mirror>
			</mirrors>
		</settings>
		<settings>
			<servers>
				<server>
					<id>liferay-ee-releases</id>
					<username>admin</username>
					<password>admin123</password>
				</server>
			</servers>
		</settings>

---

![note](../../images/tip-pen-paper.png)**Important:** You're only required to
have a server configured if you're installing Liferay CE/EE artifacts from a ZIP
file. Therefore, you don't need a server specified in your `settings.xml` if
you're using the Central Repository. Configuring a mirror in your `settings.xml`
is recommended for all use cases. For more information on mirrors and their
purpose, visit [Maven's Mirror
Guide](http://maven.apache.org/guides/mini/guide-mirror-settings.html). If
you're using the Central Repository, your basic settings are configured.

---

3. If you're using the downloaded ZIP file, create a
`build.${USER_NAME}.properties` file in your `liferay.portal.maven-<version>`
directory and input the following values referencing your specific Maven remote
repository:

		lp.maven.repository.id=liferay-ee-releases
		lp.maven.repository.url=http://localhost:8081/nexus/content/repositories/liferay-ee-releases

4. In your command prompt, navigate to the `liferay-portal-maven-<version>`
directory. Then type `ant deploy`.

Your console begins deploying the contents of the
`liferay-portal-maven-<version>` package into the repository you created on
Nexus. To verify that your artifacts deployed, first navigate to the
*Repositories* page and select your repository.

![Figure 2.6: You can easily navigate to your new Liferay remote repository.](../../images/maven-select-repository2.png)

Notice a window appears below and you can see that the Liferay
artifacts are now in your repository:

![Figure 2.7: Your remote repository now holds your Liferay Maven artifacts.](../../images/maven-verify-deployment2.png)

That's it! You now have a local repository and remote repository storing your
Liferay Maven artifacts. Also, you're aware of Liferay's abundance of resources
that help you create and upkeep your Liferay Maven projects. Now that you have
your repositories and a mirror configured, let's begin developing plugins!

### Creating plugins based on Liferay archetypes

Liferay offers many archetypes to help you build multiple types of plugin
projects which include portlets, themes, hooks, layouts, and web plugins. There
are also multiple archetype versions for each project. Furthermore, when you're
finished creating your plugin, you're able to package and deploy your project to
a specified Liferay instance. You're even able to install and deploy the
individual plugin to a remote repository.

We'll go through some brief examples to demonstrate how Maven creates,
packages, and deploys plugins to Liferay Portal. First, let's create a portlet
plugin using Maven.

#### Creating portlet plugins with Maven

One of the plugins you can create using Maven is a portlet. We will demonstrate
how to generate archetypes, package, and deploy your portlet plugin. Make sure
you have your parent project and `pom.xml` configured properly. If not, visit
the *Utilizing Liferay Maven artifacts* section for instructions. To begin
developing your portlet plugin, follow the steps below:

1. Open the command prompt. Let's make sure Maven is correctly installed. Run
the following command:

		mvn -v

	If your output looks similar to the snapshot below, Maven is correctly
	installed and you're officially a Maven developer!

	![Figure 2.8: After running `mvn -v`, Maven's version information appears, indicating Maven is installed on your system.](../../images/maven-verify.png)

2. Inside the command prompt, navigate to your `sample-parent-project`
directory. We will now create a portlet that will reside inside the
`sample-parent-project` directory using a Liferay portlet archetype. Run the
following command:

		mvn archetype:generate

	This command creates a list of available project archetypes which appears
	similarly to the output below:
	
		...
		39: remote -> com.liferay.maven.archetypes:liferay-hook-archetype
		(Provides an archetype to create Liferay hooks.)
		40: remote -> com.liferay.maven.archetypes:liferay-layouttpl-archetype
		(Provides an archetype to create Liferay layout templates.)
		41: remote -> com.liferay.maven.archetypes:liferay-portlet-archetype
		(Provides an archetype to create Liferay portlets.)
		42: remote -> com.liferay.maven.archetypes:liferay-portlet-icefaces-archetype
		(Provides an archetype to create Liferay ICEfaces portlets.)
		43: remote -> com.liferay.maven.archetypes:liferay-portlet-jsf-archetype
		(Provides an archetype to create Liferay JSF portlets.)
		...
		Choose a number or apply filter (format: [groupId:]artifactId, case sensitive contains):

3. Choose the number that corresponds with
`com.liferay.maven.archetypes:liferay-portlet-archetype`. For the output listed
above, select number *41*. As an alternate way to select the portlet
archetype, you can type in `liferay-portlet-archetype` instead of the number
*41*. Then type the number *1* to select it from the new list.

4. The next prompt asks you to choose an archetype version. Choose the version
you prefer. The snapshot below illustrates this message:

	![Figure 2.9: You are asked to input the archetype version when setting up a portlet plugin.](../../images/maven-archetype-version.png)

	For this example, we will choose the number *9*, which corresponds with
	`6.1.20`.

---

![note](../../images/tip-pen-paper.png)**Note:** You're not required to select
the archetype version that corresponds with your Liferay instance. Older
archetype versions are compatible with updated Liferay bundles.

---

5. After entering your archetype version, you are be asked to provide your
*groupId*, *artifactId*, *version*, and *package* coordinates. Enter their
values accordingly:

		groupId: com.liferay.sample
		artifactId: sample-portlet
		version: 1.0-SNAPSHOT
		package: com.liferay.sample

    This process is illustrated in the snapshot below:

	![Figure 2.10: When creating your portlet plugin, you must enter your `groupId`, `artifactId`, `version`, and `package` properties.](../../images/maven-portlet-plugin-settings.png)

6. Type the letter `Y` to confirm these coordinates. Notice the archetype is
downloaded automatically to your local repository (i.e.
`.m2/repository/com/liferay/maven/archetypes/<archetype>`). Likewise, because
you configured the mirror pointing to your public local repository on Nexus, the
plugin is downloaded there as well: 

	![Figure 2.11: Your archetype and its dependencies are now available on Nexus.](../../images/maven-public-plugin.png)

	Also, a separate folder is created inside your `sample-parent-project`
	folder where your portlet plugin resides. Furthermore, a new `pom.xml` file
	is automatically generated within your new portlet project. You don't need
	to modify this file. For more information on defining these Maven
	coordinates, visit [Apache's POM
	reference](http://maven.apache.org/pom.html#Maven_Coordinates).

7. If you have any customizations you would like to add to your
`sample-portlet`, add them in the `<portlet-plugin>/src/main/webapp` directory.
For a breakdown of the Maven portlet anatomy, reference the structure below:

	-	sample-portlet/
		-	src/
			-	main/
				-	java/
				-	resources/
				-	webapp/
					-	WEB-INF/
						-	liferay-display.xml
						-	liferay-plugin-package.properties
						-	liferay-portlet.xml
						-	portlet.xml
						-	web.xml
					-	css/
						-	main.css
					-	js/
						-	main.js
				-	icon.png
				-	view.jsp

	For example, your `main.css` file path would be
`<portlet-plugin>/src/main/webapp/css/main.css`.

---

![note](../../images/tip-pen-paper.png)**Note:** If you're familiar with
developing plugins with Ant, compare the different customization directory
locations:

Ant Customization directory: `<portlet-plugin>/docroot`

Maven Customization directory: `<portlet-plugin>/src/main/webapp`

If you'd like to view the anatomy of a portlet developed by Ant or would just
like to learn more about portlet structures, visit the [Anatomy of a
Portlet](https://www.liferay.com/documentation/liferay-portal/6.1/development/-/ai/anatomy-of-a-portl-4)
section in Liferay's Development Guide.

---

8. In the command prompt, navigate to the `sample-portlet` directory

9. Run the command:

		mvn package

	This compiles any classes and packages in the target directory. Also, the
	command creates the `sample-portlet`'s WAR file. The snapshot below
	illustrates the output you should expect after packaging your portlet
	plugin.
	
	![Figure 2.12: The command prompt gives useful feedback during the packaging process.](../../images/maven-package.png)

10. If you're interested in deploying the portlet plugin to a Nexus remote
repository individually, insert the following code into the `sample-portlet`'s
`pom.xml`:

		<distributionManagement>
			<snapshotRepository>
				<id>liferay-ce-plugin-snapshots</id>
				<name>Liferay CE Plugin Snapshots Repo</name>
				<url>http://localhost:8081/nexus/content/repositories/liferay-ce-pluginsnapshots</url>
			</snapshotRepository>
		</distributionManagement>

	Note, since we created the portlet as a snapshot, we must deploy it to a
	snapshot repository. If your plugin is not a snapshot, simply replace the
	`<snapshotRepository/>` tags with `<repository/>` tags. Also, modify the
	`<id/>`, `<name/>`, and `<url/>` tags to your repository's credentials. This
	step is only applicable if you have a server set up in your `settings.xml`.
	
11. To deploy the portlet to your specified repository, type `mvn deploy`. This
command automatically installs your plugin and its dependencies to your local
repository, and then deploys to your specified remote repository.

12. Deploy the portlet into your Liferay instance by running:

		mvn liferay:deploy

---

![note](../../images/tip-pen-paper.png)**Note:** If you get the following error
executing `mvn liferay:deploy`, check to see if you're executing the command
from your plugin's directory (e.g. `sample-portlet`).

`[ERROR] No plugin found for prefix 'liferay' in the current project and in the
plugin groups [org.apache.maven.plugins, org.codehaus.mojo] available from the
repositories [local (C:\Users\cdhoag\.m2\repository), central (http://repo.maven
.apache.org/maven2)] -> [Help 1]`

---

13. Start up your Liferay instance that you've used during this exercise.
Navigate to *Add* &rarr; *More...* &rarr; *sample-portlet*. Your
*sample-portlet* appears like the snapshot below:

	![Figure 2.13: The portlet plugin you created is now available in your Liferay Portal.](../../images/maven-sample-portlet.png)

---

![note](../../images/tip-pen-paper.png)**Important:** Note the three phases that
are completed during the development of plugins:

1. Maven's compile phase results in explicit dependencies being downloaded to
your local repository (i.e. `.m2\repository\com\liferay\portal`)
2. Maven's package phase results in the plugin's inferred dependency artifacts
being downloaded to your local repository. (`.m2\repository`).
3. Maven's install phase results in your plugin being installed to your local
repository. Since your local repository is also public (due to the mirror we
configured), others have access to your plugin via your Nexus server.

---

Congratulations! You have successfully created a Liferay portlet plugin using
Maven! But wait, there's more! Let's run through a brief example for developing
a theme plugin using Maven.

<!-- TODO: I added more details into what was going on for the portlet
development. If Maven becomes its own chapter, the extra details will have to be
carried over to the theme plugins. However, since they are in the same section,
it seems repetitive to repeat every detail for both plugins. -->

#### Creating theme plugins with Maven

Imagine yourself sitting in a luxurious chair next to a bustling fire on a cold
winter's night (in front of your computer, of course). Now imagine yourself
sitting on a cold hard wooden chair inside a cubicle (again on your computer).
These two descriptions paint two very different pictures, but describe what
you're doing all the same. This "change in scenery" for your portal can really
set the mood for your users. Luckily, you can develop your own theme plugin
using Maven so your portal has a lasting effect on anyone who visits it.

The process of creating a theme plugin is similar to the previous portlet we
created. Our theme plugin demonstration also assumes you have already created
the `sample-parent-project` and `pom.xml`. You don't need to create a new
`sample-parent-project` directory, but simply navigate to it and begin our theme
exercise.

1. In the command prompt, navigate to your `sample-parent-project` directory and
run the following command:

		mvn archetype:generate

	This command will create a list of available project templates which appears
	similarly to the output below:

		...
		45: remote -> com.liferay.maven.archetypes:liferay-portlet-richfaces-archetype 
		(Provides an archetype to create Liferay RichFaces portlets.)
		46: remote -> 	com.liferay.maven.archetypes:liferay-servicebuilder-archetype
		(Provides an archetype to create Liferay Service Builder portlets.)
		47: remote -> com.liferay.maven.archetypes:liferay-theme-archetype
		(Provides an archetype to create Liferay themes.)
		48: remote -> com.liferay.maven.archetypes:liferay-web-archetype
		(Provides an archetype to create Liferay webs.)
		49: remote -> com.lordofthejars.thymeleafarchetype:thymeleaf-spring-maven-archetype
		(Thymeleaf Spring Maven Archetype)
		...
		Choose a number or apply filter (format: [groupId:]artifactId, case sensitive contains):

2. Choose the number that corresponds with
`com.liferay.maven.archetypes:liferay-theme-archetype`. For the output listed
above, select number *47*. As an alternate way to select the theme
archetype, you can type in `liferay-theme-archetype` instead of the number *47*.
Then type the number *1* to select it from the new list.

3. The next prompt asks you to choose an archetype version. Choose the version
you prefer. For this example, choose the number *9*, which corresponds
with `6.1.20`.

4. After entering your archetype version, you will be asked to provide your
*groupId*, *artifactId*, *version*, and *package* properties. Enter their values
accordingly:

		groupId: com.liferay.sample
		artifactId: sample-theme
		version: 1.0-SNAPSHOT
		package: com.liferay.sample

    Afterward, type the letter `Y` to confirm these values. For information on
    where this plugin is downloaded, visit the *Creating portlet plugins with Maven* 
    section.

5. Navigate to the `sample-theme` folder that was created and open its
`pom.xml`. Notice that there are two additional properties when compared to the
sample portlet's `pom.xml`: `liferay.theme.parent` and `liferay.theme.type`.
These properties set the parent theme and theme template language, respectively.
We'll keep the default settings for the two theme properties, which look like
this:

		<properties>
			<liferay.theme.parent>_styled</liferay.theme.parent>
			<liferay.theme.type>vm</liferay.theme.type>
		</properties>

---

![note](../../images/tip-pen-paper.png)**Note:** For information on Liferay
themes and its settings, visit Liferay's [Creating Liferay
Themes](http://www.liferay.com/documentation/liferay-portal/6.1/development/-/ai/creating-liferay-them-7)
chapter of the Development Guide.

---

6. If you have any customizations you would like to add to your `sample-theme`,
add them in the `<theme-plugin>/src/main/webapp` directory. For a breakdown of
the Maven theme anatomy, reference the structure below:

	-	sample-theme/
		-	pom.xml
		-	src/
			-	main/
				-	resources/
				-	webapp/
					-	WEB-INF/
						-	liferay-plugin-package.properties
						-	web.xml
					-	css/ (optional for customizations)
					-	images/ (optional for customizations)
					-	js/ (optional for customizations)
					-	templates/ (optional for customizations)

	For example, your `custom.css` file path would be
	`<theme-plugin>/src/main/webapp/css/custom.css`.

---

![note](../../images/tip-pen-paper.png)**Note:** If you're familiar with
developing plugins with Ant, compare the different customization directory
locations:

Ant Customization directory: `<theme-plugin>/docroot/_diffs`

Maven Customization directory: `<theme-plugin>/src/main/webapp`

If you'd like to view the anatomy of a theme developed by Ant or would just like
to learn more about theme structures, visit the [Anatomy of a
Theme](http://www.liferay.com/documentation/liferay-portal/6.0/development/-/ai/anatomy-of-a-theme)
section in Liferay's Development Guide.

---

7. In the command prompt, navigate to the `sample-theme` directory.

8. Run the command:

		mvn package

	This will compile any classes and packages in the target directory. Also,
	the command will create the `sample-theme`'s WAR file.

<!-- TODO: In Mika's *Themes with Maven" blog:

http://www.liferay.com/web/mika.koivisto/blog/-/blogs/creating-liferay-themes-with-maven?_33_redirect=http%3A%2F%2Fwww.liferay.com%2Fweb%2Fmika.koivisto%2Fblog%3Fp_p_id%3D33%26p_p_lifecycle%3D0%26p_p_state%3Dnormal%26p_p_mode%3Dview%26p_p_col_id%3Dcolumn-2%26p_p_col_count%3D1

After the `mvn package` instruction, Mika explains how the thumbnail and
screenshot are generated by the parent theme. When I completed the mvn package
command, I had to manually put in the image files into the `.../src/main/webapp`
directory for the theme to have the image files. I'm not sure if we should set
up the example to where it completes this automatically, or if it is supposed to
do this and there is a bug. I have not attached snapshots of how the theme looks
inside of Liferay Portal because of this question. When I have not included the
image files, the sample theme has no thumbnail or snapshot. The answer to this
question may also offer more material to present when describing the
<liferay.parent.theme> tags. -->

9. If you're interested in deploying the theme plugin to a Nexus remote
repository individually, insert the following code into the `sample-theme`'s
`pom.xml`:

		<distributionManagement>
			<snapshotRepository>
				<id>liferay-ce-plugin-snapshots</id>
				<name>Liferay CE Plugin Snapshots Repo</name>
				<url>http://localhost:8081/nexus/content/repositories/liferay-ce-pluginsnapshots</url>
			</snapshotRepository>
		</distributionManagement>

	Note, since we created the theme as a snapshot, we must deploy it to a
	snapshot repository. If your plugin is not a snapshot, simply replace the
	`<snapshotRepository/>` tags with `<repository/>` tags. Also, modify the
	`<id/>`, `<name/>`, and `<url/>` tags to your repository's credentials. This
	step is only applicable if you have a server set up in your `settings.xml`.
	
10. To deploy the theme to your specified repository, type `mvn deploy`. This
command automatically installs your plugin and its dependencies to your local
repository, and then deploys to your specified remote repository.

11. Deploy the theme into your Liferay bundle by running:

		mvn liferay:deploy

---

![note](../../images/tip-pen-paper.png)**Note:** If you get the following error
executing `mvn liferay:deploy`, check to see if you're executing the command
from your plugin's directory (e.g. `sample-theme`).

`[ERROR] No plugin found for prefix 'liferay' in the current project and in the
plugin groups [org.apache.maven.plugins, org.codehaus.mojo] available from the
repositories [local (C:\Users\cdhoag\.m2\repository), central (http://repo.maven
.apache.org/maven2)] -> [Help 1]`

---
		
12. Start up your Liferay instance that you have used during this exercise.
Then, navigate to *Manage* &rarr; *Page* &rarr; *Look and Feel*.

13. Select the *Define a specific look and feel for this page* option and the
`sample-theme` is listed as an available theme.

You have successfully developed a Liferay theme using Maven. Great job!

This process can be completed for many other different kinds of plugins like
hooks, layouts, and web plugins. Developing plugins using Maven follows a
uniform process which includes generating the archetypes and selecting the
options you're interested in. Then, you can customize the archetype to your
liking. Using Maven to develop plugins offers an easy and effective way to
customize your Liferay Portal.

<!-- TODO: Maven may need to be its own chapter. -->

Now that you have your Plugins SDK configured, know the directory structure and
available targets, learned some best practices, and know the Maven alternative,
you are ready to start developing plugins using Liferay's Plugins SDK.

<!-- TODO Jim - Summarize Maven section/chapter -->

In the next chapter, we'll start portlet development.
