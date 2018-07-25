# Development Setup Overview [](id=development-setup-overview)

<div class="learn-path-step">
    <p>Development Setup Overview<br>Step 1 of 1</p>
</div>

Liferay's development tools aim to help you get started fast. The basic steps 
for installing Liferay @ide@ are

* Download a Liferay @ide@ bundle. 

* Unzip the downloaded package to a location on your system. 

* Start @ide@. 

You'll follow these steps, and then generate an environment for developing your 
first @product@ application. 

## Installing a Liferay @ide@ Bundle [](id=installing-ide-bundle)

To install Liferay @ide@, follow these steps: 

1.  Download and install 
    [the Java Development Kit (JDK)](http://www.oracle.com/technetwork/java/javase/downloads/index.html). 
    @product@ runs on Java. The JDK is required because you'll be developing
    @product@ apps in Liferay @ide@. The JDK is an enhanced version of the Java
    Environment used for developing new Java technology. 

2.  Download 
    [Liferay @ide@](https://www.liferay.com/downloads/liferay-projects/liferay-ide).
    Installing it is easy: unzip it to a convenient location on your system. 

3. To run Liferay @ide@, execute the `eclipse` executable. 

The first time you start Liferay @ide@, it prompts you to select an Eclipse
workspace.  If you specify a folder where no workspace currently exists, Liferay
@ide@ creates a new  workspace in that folder. Follow these steps to create a
new workspace:

1.  When prompted, indicate your workspace's path. Name your new workspace 
    `guestbook-workspace` and click *OK*. 

2.  When Liferay @ide@ first launches, it presents a welcome page. Click the
    *Workbench* icon to continue. 

Nice job! Your development environment is installed and your workspace is set 
up. 

## Creating a Liferay Workspace [](id=creating-a-liferay-workspace)

Now you'll create another kind of workspace--a 
[Liferay Workspace](/develop/tutorials/-/knowledge_base/7-0/liferay-workspace). 
By holding and managing your @product@ projects, a Liferay Workspace provides a 
simplified, straightforward way to develop @product@ applications. In the 
background, a Liferay Workspace uses 
[Blade CLI](/develop/tutorials/-/knowledge_base/7-0/blade-cli) and 
[Gradle](https://gradle.org/) to manage dependencies and organize your build 
environment. Note that to avoid configuration issues, you can only create one 
Liferay Workspace for each Eclipse Workspace. 

Follow these steps to create a Liferay Workspace in Liferay @ide@:

1.  Select *File* &rarr; *New* &rarr; *Liferay Workspace Project*. Note: you may 
    have to select *File* &rarr; *New* &rarr; *Other*, then choose *Liferay 
    Workspace Project* in the *Liferay* category. 

    ![Figure 1: By selecting *Liferay Workspace*, you begin the process of creating a new workspace for your @product@ projects.](../../../images/selecting-liferay-workspace.png)

    A *New Liferay Workspace* dialog appears, which presents several 
    configuration options. 

2.  Give your workspace the name `com-liferay-docs-guestbook`. 

3.  Next, choose your workspace's location. Leave the default setting checked. 
    This places your Liferay Workspace inside your Eclipse workspace. 

4.  Check the *Download Liferay bundle* checkbox to automatically download and 
    unzip a @product@ instance in your workspace. When prompted, name the server 
    `liferay-tomcat-bundle`. 

5.  Click *Finish* to create your Liferay Workspace. This may take a while 
    because Liferay @ide@ downloads the @product@ bundle in the background. 

    ![Figure 2: Liferay @ide@ provides an easy-to-follow menu to create your Liferay Workspace.](../../../images/guestbook-workspace-menu.png)

A dialog appears prompting you to open the Liferay Workspace perspective. Click 
*Yes*, and your perspective switches to Liferay Workspace. 

Congratulations! Your development environment is ready! Next, you'll get started 
developing your first @product@ application. 
