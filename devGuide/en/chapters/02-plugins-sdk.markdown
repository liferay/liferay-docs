
# The Plugins SDK [](id=the-plugins-s-3)

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

## Installing the SDK [](id=installing-the-sdk)

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

### Ant Configuration [](id=lp-6-1-dgen02-ant-configuration-0)

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

### Plugins SDK Configuration [](id=lp-6-1-dgen02-plugins-sdk-configuration-0)

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

## Structure of the SDK [](id=structure-of-the-s-3)

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

## Creating Plugins to extend Plugins [](id=lp-6-1-dgen02-creating-plugins-to-extend-plugins-0)

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

## Best Practices [](id=best-practices)

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

Next, we'll discuss an alternative solution for developing and managing plugins: Maven.

## Maven alternative [](id=maven-alternative)

As an alternative for developing plugins using Ant, you can leverage the Apache
Maven build management framework. Maven is a project management tool that offers
a superset of features found in a build tool. Besides just providing build
capabilities, Maven offers a project object model, a project life cycle, a
dependency management system, and logic for executing plugin goals at specific
phases in a life cycle. Maven can be most generally defined by the phrase
"Convention Over Configuration" by its use of core plugins for compiling source
code and packaging distributions. Maven's core installation is quite bare, but
the abundance of plugins allows for an easily customized framework.

Many developers are switching to Maven because it offers a common interface for
project builds. Developers are able to build projects from other developers
easily because of Maven's universal structure. A developer must simply follow a
particular process and Maven will do the rest. However, the use of a common
interface also creates some disadvantages compared to a basic build tool like
Ant. Some of Maven's core opinions should not be challenged, which also makes it
less flexible. It is really up to you, the developer, to choose how you would
like to build and manage your Liferay projects. If you would like more in-depth
analysis on Maven's features and what it can do for you, visit [Maven: The
Complete Reference](http://www.sonatype.com/books/mvnref-book/reference/)
created by Sonatype, Inc. You can download Maven from
[http://maven.apache.org/download.html](http://maven.apache.org/download.html).
After downloading, we recommend putting the Maven executable in your system's
path to ensure a quick development process. Maven is a great alternative if it
fits your needs for building and managing project builds.

You'll be happy to know that we have archetypes to help you build various types
of plugins including Liferay portlets, themes, hooks, layouts, web plugins, and
many more. These plugins are built by defining the Project Object Model, which
we will discuss in the next section. You can also install and deploy Liferay
artifacts locally or to a remote repository. There is even a central repository
where Liferay's artifacts are updated daily. We will dive into all these topics
within this chapter, so buckle up and prepare to be mavenized!

### Defining the Project Object Model (POM)

Maven uses an XML file called `pom.xml` (Project Object Model) to describe the
software project being built. This single XML file describes a project's
required plugins, directories, build order, and dependencies on other external
components. In addition, the POM tells Maven what kind of project is being used
and how to modify default behavior to generate output from source. Because the
POM is the sole descriptive declaration of a project for Maven, you only need to
create the `pom.xml` file and Maven will do the rest! You can read more
documentation to get familiar with the POM by accessing Sonatype's [The Project
Object
Model](http://www.sonatype.com/books/mvnref-book/reference/pom-relationships.html)
chapter.

Before we deal with configuring the POM and developing plugins, let's learn a
little more about how to utilize a Maven repository.

### Utilizing a Maven repository

Wouldn't it be nice to have the ability to install and deploy your Liferay
artifacts to a repository? Great news! You can configure your own local and
remote Maven repositories to install and deploy your Liferay projects. Having a
Maven repository and proxy is very important, and sometimes crucial, when
working with enterprise projects. The ability to store and reference your
Liferay projects cannot be underestimated.

With Maven, a proxy is a server that proxies your requests to public Maven
repositories and caches the artifacts locally for faster and more reliable
access. Most Maven proxies can also host private repositories used for hosting
your private artifacts. If you're interested in running your repository behind a
proxy, you can visit [Sonatype's proxy
section](http://www.sonatype.com/books/nexus-book/reference/install-sect-proxy.html)
for more information.

Having a local proxy/repository makes your Maven builds faster and more reliable
than accessing remote repositories that might even sometimes be unavailable.

---

![note](../../images/tip-pen-paper.png)**Important:** If you're using EE
artifacts, you must have a local repository for the plugins to reference. For CE
artifacts, there is a public repository that your POM can reference, so a local
repository is not mandatory. We will talk more about this public repository
later.

---

<!-- TODO: It appears there needs to be some explanation on how to reference the
Central Repository when using the CE artifacts. When downloading the
liferay-portal-maven-<ce-version> bundle, the CE artifacts are installed to a
local repository and never reference the Central repo. We need to find out how
to reference Central. -->

However, it is sometimes useful to have a remote repository so you can access
your Liferay artifacts on multiple machines. Also, it is advantageous to allow
other people to have access to your projects. We will present guides that show
you how to do both, and how to get the most out of using Maven.

Let's dive in and set up a Maven repository!

#### Installing a Maven repository

First, lets set up a remote repository called Nexus. Nexus is an open source
Maven repository manager that can proxy to other repositories as well as host
repositories. If you would like to learn more about Nexus, visit Sonatype's
[Repository Management with
Nexus](http://www.sonatype.com/books/nexus-book/reference/install.html) chapter.
To create and configure a Nexus repository, follow the steps below:

1. Download the latest [Nexus OSS installation](http://www.sonatype.org/nexus/).

2. Follow the installation steps provided by Sonatype in [Installing
Nexus](http://www.sonatype.com/books/nexus-book/reference/_installing_nexus.html).

3. To begin using Nexus, open a command prompt (make sure to run as administrator) and
navigate to `${NEXUS_HOME}\bin\jsw\${OPERATING_SYSTEM}`. Make sure to replace
the `${NEXUS_HOME}` and `${OPERATING_SYSTEM}` variables with the appropriate
content (e.g. `E:\nexus\nexus-2.1.2\bin\jsw\windows-x86-64`).

4. Type `install-nexus` to install Nexus onto your computer. After the
installation process is complete, your command prompt appears like the
snapshot below:

	![Figure 2.1: When running `install-nexus`, your command prompt should confirm Nexus is installed.](../../images/maven-install-nexus.png)

5. Type `start-nexus` to start your Nexus repository. This process appears
similarly to the snapshot below:

	![Figure 2.2: Your command prompt should indicate that Nexus has started.](../../images/maven-start-nexus.png)

6. Access your Nexus repository by going to your browser and opening
[http://localhost:8081/nexus](http://localhost:8081/nexus).

7. Login as the administrator by using the following default credentials:

	`Username: admin`

	`Password: admin123`

8. Click on *Repositories* and then navigate to *Add...* &rarr; *Hosted
Repository*.

	![Figure 2.3: You need to add a custom Liferay repository for your artifacts to reside.](../../images/maven-nexus-create-repo.png)
	
	Note: To learn more about each type of repository, visit Sonatype's
	[Managing
	Repositories](http://www.sonatype.com/books/nexus-book/reference/confignx-sect-manage-repo.html)
	section.

9. Give the repository the following credentials:

	-	Repository ID: liferay-ee-releases
	-	Repository Name: Liferay EE Release Repository
	-	Provider: Maven2
	-	Repository Policy: Release

Note that a common practice is to have a *release* repository and a *snapshot*
repository to house releases and snapshots of your Liferay artifacts. For our
demonstration, we will only show the deployment for the release repository.

<!-- TODO: Do we need to have both releases and snapshots repositories? Is it OK
to just show deploying to the release repository? Mika creates both a release
and snapshot repository but only shows deploying to one. It seems from a little
research that releases is the more important repository. Is it a commmon
practice to include both?-->

You now have a configured repository ready for Liferay's Maven artifacts! The
next step is configuring Maven to upload your artifacts to your new repository.

#### Utilizing Liferay Maven artifacts

You may be wondering how to begin installing and deploying Maven artifacts. We
have the repository configured but how do we begin this Maven process? Liferay
makes it simple by providing a [Central
Repository](http://search.maven.org/#search|ga|1|liferay) where all of Liferay's
CE artifacts are stored and updated for use. This central repository holds the
latest and greatest features provided for Liferay CE Maven artifacts.

Furthermore, if you are interested in Liferay EE artifacts, Liferay offers a
package that includes a convenient script to install Liferay artifacts to your
local or remote repository. In fact, this package is also offered for CE
artifacts as well. You can download this package
(`liferay-portal-maven-<version>`) for CE by clicking
[here](http://sourceforge.net/projects/lportal/files/Liferay%20Portal/6.1.1%20GA2/liferay-portal-maven-6.1.1-ce-ga2-20120731132656558.zip/download).
The Liferay EE Maven package can be downloaded by visiting Liferay's Customer
Portal. To access the Liferay Customer Portal and begin downloading Liferay
Maven EE artifacts, follow the steps below:

1. Navigate to [www.liferay.com](https://www.liferay.com/) and sign in.

2. Go to *Places* &rarr; *Customer Portal*.

3. Select *Liferay Portal* from the *Downloads* panel

4. Inside the *Filter by:* application, enter the appropriate version and
*Development*.

5. Select *Download* listed under *Liferay Portal <Version> Maven*.

	![Figure 2.4: You can download the Liferay Maven EE artifacts by visiting the Liferay Customer Portal.](../../images/maven-customer-portal.png)

As you can see, Liferay offers many resources to help aid you in creating and
maintaining your Liferay artifacts using Maven. Next, let's install and deploy
your Liferay artifacts to a local repository and remote repository.

#### Installing and deploying Liferay artifacts to a Maven repository

Now that we have a Maven repository set up and have knowledge about the
different features Liferay offers with Maven, let's enable the capability to
upload our Liferay artifacts using Maven.

First, we'll upload the Liferay artifacts locally. If you have not already done
so, download the `liferay-portal-maven-<version>` CE package by clicking
[here](http://sourceforge.net/projects/lportal/files/Liferay%20Portal/6.1.1%20GA2/liferay-portal-maven-6.1.1-ce-ga2-20120731132656558.zip/download)
or download the EE package from Customer Portal. We will be using the Liferay
Maven EE artifacts package for our demonstration.

Now, using your command prompt, navigate to the `liferay-portal-maven-<version>`
directory and type `ant install`. Your console should begin installing the
artifacts from your package into a local repository which is located in the
`${USER_HOME}/.m2/repository` directory. You now have a local repository
containing Liferay artifacts on your machine. Now wasn't that easy?

---

![note](../../images/tip-pen-paper.png)**Important:** If you're developing EE
plugins, you must have a local repository containing Liferay Maven EE artifacts.

---

Next, let's configure the necessary settings so you can fire up and begin using
the remote Nexus repository we configured earlier. Follow the steps below:

1. Navigate to the `${USER_HOME}/.m2/settings.xml` file. If this file doesn't
exist on your machine, create it.

2. Input the following code into the XML file:

		<?xml version="1.0"?>
		<settings>
			<servers>
				<server>
					<id>liferay-ee-releases</id>
					<username>admin</username>
					<password>admin123</password>
				</server>
			</servers>
		</settings>

3. If you would like your artifacts to be signed, you will need to find your
`gpg.keyname` and `gpg.passphrase`. These values verify that the artifact is
your work and has not been altered by anyone else. You can visit the [How to
Generate PGP Signatures with
Maven](https://docs.sonatype.org/display/Repository/How+To+Generate+PGP+Signatures+With+Maven)
document to learn more about these values and how to find them. Although using a
GPG key and passphrase is optional, it is highly recommended to use them in a
production environment. We demonstrate specifying them for your project in the
next step.

4. Create a `build.${USER_NAME}.properties` file in your
`liferay.portal.maven-<version>` directory and input the following values:

		gpg.keyname=${YOUR_GPG_KEYNAME}
		gpg.passphrase=${YOUR_GPG_PASSPHRASE}
		lp.maven.repository.id=liferay-ee-releases
		lp.maven.repository.url=http://localhost:8081/nexus/content/repositories/liferay-ee-releases

	Note that the `build.${USER_NAME}.properties` file overrides the
	`build.properties` file.

5. In your command prompt, navigate to the `liferay-portal-maven-<version>`
directory. Then type `ant deploy`.

Your console will begin deploying the contents of the
`liferay-portal-maven-<version>` package into the repository you created on
Nexus. To verify that your artifacts deployed, first navigate to the
*Repositories* page and select the *Liferay EE Release Repository*.

![Figure 2.5: You can easily navigate to your new Liferay remote repository.](../../images/maven-select-repository2.png)

You will notice a window appears below and you can see that the Liferay
artifacts are now in your repository:

![Figure 2.6: Your remote repository now holds your Liferay Maven artifacts.](../../images/maven-verify-deployment2.png)

That's it! You now have a local repository and a remote repository storing your
Liferay Maven artifacts. Also, you are aware of Liferay's abundance of resources
that help you create and upkeep your Liferay Maven projects. Now that you have
your repositories set up, let's begin developing plugins!

### Developing plugins with Maven

Liferay offers many archetypes to help you build multiple types of plugin
projects which include portlets, themes, hooks, layouts, and web plugins. There
are also multiple archetype versions for each project. Furthermore, when you're
finished creating your plugin, you're able to package and deploy your project to
a specified Liferay instance. Remember, if you're developing EE plugins, you
must have a local repository installed with Liferay Maven EE artifacts.

We will go through some brief examples to demonstrate how Maven creates,
packages, and deploys plugins to Liferay Portal. First, let's create a portlet
plugin using Maven.

#### Creating portlet plugins

One of the plugins you can create using Maven is a portlet. We will demonstrate
how to create your `pom.xml`, generate archetypes, package, and deploy your
portlet plugin.

Since we are starting from scratch, we will create a new parent project for your
plugins and, then, add a portlet project to it. Follow the instructions below:

1. Create a new directory which will be your project root. If you were using
Ant, this is the place where you would extract Liferay plugins SDK. For this
example, we will name the project root `sample-parent-project`. Your parent
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
you will need to modify these settings. If you are following along with creating
this sample project, you will only need to replace the
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

5. Open the command prompt. Let's make sure Maven is correctly installed. Run
the following command:

		mvn -v

	If your output looks similar to the snapshot below, Maven is correctly
	installed and you're officially a Maven developer!

	![Figure 2.7: After running `mvn -v`, Maven's version information appears, indicating Maven is installed on your system.](../../images/maven-verify.png)

6. Inside the command prompt, navigate to your `sample-parent-project`
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

7. Choose the number that corresponds with
`com.liferay.maven.archetypes:liferay-portlet-archetype`. For the output listed
above, select number *41*. As an alternate way to select the portlet
archetype, you can type in `liferay-portlet-archetype` instead of the number
*41*. Then type the number *1* to select it from the new list.

8. The next prompt asks you to choose an archetype version. Choose the version
you prefer. The snapshot below illustrates this message:

	![Figure 2.8: You are asked to input the archetype version when setting up a portlet plugin.](../../images/maven-archetype-version.png)

	For this example, we will choose the number *9*, which corresponds with
	`6.1.20`.

---

![note](../../images/tip-pen-paper.png)**Note:** You're not required to select
the archetype version that corresponds with your Liferay instance. Older
archetype versions are compatible with updated Liferay bundles.

---
9. After entering your archetype version, you are be asked to provide your
*groupId*, *artifactId*, *version*, and *package* coordinates. Enter their
values accordingly:

		groupId: com.liferay.sample
		artifactId: sample-portlet
		version: 1.0-SNAPSHOT
		package: com.liferay.sample

    This process is illustrated in the snapshot below:

	![Figure 2.9: When creating your portlet plugin, you must enter your `groupId`, `artifactId`, `version`, and `package` properties.](../../images/maven-portlet-plugin-settings.png)

10. Type the letter `Y` to confirm these coordinates. This creates a separate
folder inside your `sample-parent-project` folder where your portlet plugin
resides. Also, a new `pom.xml` file is automatically generated within your new
portlet project. You don't need to modify this file. For more information on
defining these Maven coordinates, visit [Apache's POM
reference](http://maven.apache.org/pom.html#Maven_Coordinates).

11. If you have any customizations you would like to add to your `sample-portlet`,
add them in the `<portlet-plugin>/src/main/webapp` directory. For a breakdown of
the Maven portlet anatomy, reference the structure below:

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

If you'd like to view the anatomy of a portlet developed by Ant or would just like
to learn more about portlet structures, visit the [Anatomy of a
Portlet](https://www.liferay.com/documentation/liferay-portal/6.1/development/-/ai/anatomy-of-a-portl-4)
section in Liferay's Development Guide.

---


12. In the command prompt, navigate to the `sample-portlet` directory

13. Run the command:

		mvn package

	This compiles any classes and packages in the target directory. Also, the
	command creates the `sample-portlet`'s WAR file. The snapshot below
	illustrates the output you should expect after packaging your portlet
	plugin.
	
	![Figure 2.10: The command prompt gives useful feedback during the packaging process.](../../images/maven-package.png)

14. Deploy the portlet into your Liferay instance by running:

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

14. Start up your Liferay instance that you have used during this exercise.
Navigate to *Add* &rarr; *More...* &rarr; *sample-portlet*. Your
*sample-portlet* appears like the snapshot below:

	![Figure 2.11: The portlet plugin you created is now available in your Liferay Portal.](../../images/maven-sample-portlet.png)

<!-- TODO: This section may require some details on *how* to customize a
portlet, similar to what was done with the theme. This information was not given
in Mika's blog (probably because his portlet blog is a few years old), but I can
find out, if necessary. -->

Congratulations! You have successfully created a Liferay portlet plugin using
Maven! But wait, there's more! Let's run through a brief example for developing
a theme plugin using Maven.

#### Creating theme plugins

Imagine yourself sitting in a luxurious chair next to a bustling fire on a cold
winter's night (in front of your computer, of course). Now imagine yourself
sitting on a cold hard wooden chair inside a cubicle (again on your computer).
These two descriptions paint two very different pictures, but describe what
you're doing all the same. This "change in scenery" for your portal can really
set the mood for your users. Luckily, you can develop your own theme plugin
using Maven so your portal has a lasting effect on anyone who visits it.

The process of creating a theme plugin is similar to the previous portlet we
created. Our theme plugin demonstration will begin where step 5 left off on the
portlet plugin instructions. You do not need to create a new
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

		'groupId': com.liferay.sample
		'artifactId': sample-theme
		'version': 1.0-SNAPSHOT
		'package': com.liferay.sample

    Afterward, type the letter `Y` to confirm these values.

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

9. Deploy the theme into your Liferay bundle by running:

		mvn liferay:deploy
		
10. Start up your Liferay instance that you have used during this exercise. Then,
navigate to *Manage* &rarr; *Page* &rarr; *Look and Feel*.

11. Select the *Define a specific look and feel for this page* option and the
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

In the next chapter, we'll start portlet development.
