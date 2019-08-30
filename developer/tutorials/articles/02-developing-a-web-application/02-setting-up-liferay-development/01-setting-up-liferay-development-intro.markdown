---
header-id: setting-up-a-development-environment
---

# Setting Up a Development Environment

[TOC levels=1-4]

Liferay's development tools help you get started fast. All you need as
a prerequisite is a Java Development Kit version 8 ([JDK](http://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html)
or [OpenJDK](https://jdk.java.net/java-se-ri/8) is fine).
Once that's installed, there are only three steps. 

- Download a Liferay @ide@ bundle. 

- Unzip the downloaded package to a location on your system. 

- Start @ide@. 

You'll follow these steps and then generate a Liferay Workspace for developing
your first @product@ application. 

## Installing a Liferay @ide@ Bundle

Follow these steps: 

1.  Download and install 
    [Liferay @ide@](/docs/7-2/reference/-/knowledge_base/r/installing-liferay-dev-studio)
    Installing it is easy: run the installer.

2. To run Liferay @ide@, run the `DeveloperStudio` executable. On Windows, the
   installer doesn't create a menu entry, so you should add a shortcut to it to
   your desktop or task bar.

The first time you start Liferay @ide@, it prompts you to select an Eclipse
workspace.  If you specify an empty folder, Liferay @ide@ creates a new
workspace in that folder. Follow these steps to create a new workspace:

1.  When prompted, indicate your workspace's path. Name your new workspace 
    `guestbook-workspace` and click *OK*. Windows has path length limitations,
    so on that OS, you may want to create your workspace the root folder
    (`C:\`). 

2.  When Liferay @ide@ first launches, it presents a welcome page. Click the
    *Workbench* icon to continue. 

Nice job! Your development environment is installed and your Eclipse workspace
is set up. 

## Creating a Liferay Workspace

Now you'll create another kind of workspace: a 
[Liferay Workspace](/docs/7-2/reference/-/knowledge_base/r/liferay-workspace). 
Liferay Workspace helps manage Liferay projects by providing various build
scripts, properties, and configuration for you. Liferay Workspace uses 
[Blade CLI](/docs/7-2/reference/-/knowledge_base/r/blade-cli) and 
[Gradle](https://gradle.org/) to manage dependencies and organize your build 
environment. Note that to avoid configuration issues, you can only create one 
Liferay Workspace for each Eclipse Workspace. 

Follow these steps to create a Liferay Workspace in Liferay @ide@:

1.  Select *File* &rarr; *New* &rarr; *Liferay Workspace Project*. Note: you may 
    have to select *File* &rarr; *New* &rarr; *Other*, then choose *Liferay 
    Workspace Project* in the *Liferay* category. 

    ![Figure 1: By selecting *Liferay Workspace*, you begin the process of creating a new workspace for your @product@ projects.](../../../images/dev-studio-create-workspace.png)

    A *New Liferay Workspace* dialog appears, which presents several 
    configuration options. 

2.  Give your workspace the name `com-liferay-docs-guestbook`. 

3.  Next, choose your workspace's location. Leave the default setting checked. 
    This places your Liferay Workspace inside your Eclipse workspace. 
 
4.  For *Liferay Version*, 7.2 should already be selected. 

5.  Leave the rest of the defaults. 

6.  Check the *Download Liferay bundle* checkbox to download and 
	unzip a @product@ instance in your workspace automatically.  When prompted,
	name the server `liferay-tomcat-bundle`. 

6.  Click *Finish* to create your Liferay Workspace. This may take a while 
    because Liferay @ide@ downloads the @product@ bundle in the background. 

Congratulations! Your development environment is ready! Next, you'll get started 
developing your first @product@ application. 
