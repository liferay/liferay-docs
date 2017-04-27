# Development Setup Overview

A chief design goal for Liferay's development tools is to help you get started
fast. The basic steps for installing @ide@ are

* Download an @ide@ bundle. 

* Unzip the downloaded package to a location on your system. 

* Start @ide@. 

## Installing @ide@ Bundle

It's time to perform those steps. 

1.  Download and install [Java](http://java.oracle.com). Liferay runs on Java,
    so you'll need it to run everything else. Because you'll be developing apps
    for Liferay Portal in Liferay @ide@, the Java Development Kit (JDK) is
    required.  It is an enhanced version of the Java Environment used for
    developing new Java technology. You can download the Java SE JDK from the
    Java
    [Downloads](http://www.oracle.com/technetwork/java/javase/downloads/index.html)
    page. 

2.  Download
    [Liferay @ide@](https://www.liferay.com/downloads/liferay-projects/liferay-ide).
    Installing it is easy: unzip it to a convenient location on your system. 

3. To run @ide@, execute the `eclipse` executable. 

Nice job! Your development environment is installed. 

## Creating a Liferay Workspace 

Liferay Workspace uses BLADE CLI and Gradle in the background to manage 
dependencies and organize your build environment. Liferay Workspace 
provides a simplified, straightforward way to develop Liferay applications. You 
can only create one Liferay Workspace for each Eclipse Workspace, to avoid 
configuration issues. 

To create a Liferay Workspace in @ide@, select *File* &rarr; *New* &rarr; 
*Liferay Workspace Project*. Note: you may have to select *File* &rarr; *New*
&rarr; *Other*, then in the *Liferay* category, choose *Liferay Workspace
Project*. Eclipse can be weird that way. 

![Figure 1: By selecting *Liferay Workspace*, you begin the process of creating a new workspace for your Liferay projects.](../../../images/selecting-liferay-workspace.png)

A New Liferay Workspace dialog appears, presenting several configuration
options. Follow the instructions below to create your workspace.

1. Give your workspace the name *guestbook-workspace*. 

2. Next, you're asked to choose the location of your workspace. Leave the
   default setting checked; this places your Liferay Workspace inside your Eclipse
   workspace. 

3. Check the *Download Liferay bundle* checkbox to auto-generate a
   Liferay instance in your workspace. When prompted, name the server 
   *liferay-tomcat-bundle*. 

4. Click *Finish* to create your Liferay Workspace.

<!-- The instructions above leave too much up to the reader. This is a Learning
Path. Make all the decisions for them. I edited the instructions to give the
workspace a name and to definitely leave it in the default location. If you
would rather they do something else, feel free to change it. -Rich -->

![Figure 2: Liferay @ide@ provides an easy-to-follow menu to create your Liferay Workspace.](../../../images/new-workspace-menu.png)

A dialog appears prompting you to open the Liferay Workspace perspective.
Click *Yes*, and your perspective switches to Liferay Workspace.

Congratulations! Your development environment is ready! 

## Next Step


