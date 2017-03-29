# Development Setup Overview
One of the primary goals of Liferay's development tool is making this all easy 
to set up for the end users. The basic steps for installing @ide@ are:

*Download an @ide@ bundle. 
*Unzip the downloaded package to a location on your system. 
*@You're ready to start developing with @ide@. 

Once you have @ide@ installed, there are a few other housekeeping items that 
you need to take care of: 

*Set your Eclipse workspace location. This is the location where all of your 
cache and configuration will be stored. 
*@ide@ will need to download libraries from the internet. If you are behind a 
proxy or need any other special configuration, go to Eclipse's *Window* &rarr; 
*Preferences* &rarr; *General* &rarr; *Network Connections* 

Once that's all taken care of, we can start building a web application with 
Liferay. 

## Watch the video 
Video guide coming soon. 

## Installing @ide@ Bundle
Now that we covered the basics, let's go through the steps in detail:

1.  Download and install [Java](http://java.oracle.com). Liferay runs on Java, 
so
    you'll need it to run everything else. Because you'll be developing apps for
    Liferay Portal in Liferay @ide@, the Java Development Kit (JDK) is required.
    It is an enhanced version of the Java Environment used for developing new
    Java technology. You can download the Java SE JDK from the Java
    
[Downloads](http://www.oracle.com/technetwork/java/javase/downloads/index.html)
    page. 

2.  Download
    [Liferay 
@ide@](https://www.liferay.com/downloads/liferay-projects/liferay-ide).
    Installing it is easy: unzip it to a convenient location on your system. 

3. To run @ide@, execute the `eclipse` executable. 

## Creating a Liferay Workspace 

Liferay Workspace uses BLADE CLI and Gradle in the background to manage 
dependencies and organize your build environment. Using Liferay Workspace @ide@ 
provides a simplified, straightforward way to develop Liferay applications. You 
can only create one Liferay Workspace for each Eclipse Workspace, to avoid 
configuration issues. 

To create a Liferay Workspace in @ide@, select *File* &rarr; *New* &rarr; 
*Liferay Workspace Project*. 

![Figure 1: By selecting *Liferay Workspace*, you begin the process of creating 
a new workspace for your Liferay 
projects.](../../../images/selecting-liferay-workspace.png)

A New Liferay Workspace dialog appears, presenting several configuration
options. Follow the instructions below to create your workspace.

1. Give your workspace a name. 

2. Choose the location where you'd like your workspace to reside. Checking the
   *Use default location* checkbox places your Liferay Workspace in the Eclipse
   workspace you're working in.

3. Check the *Download Liferay bundle* checkbox if you'd like to auto-generate a
   Liferay instance in your workspace. When prompted, name the server 
"liferay-tomcat-bundle". This Liferay bundle is generated the same way as 
described in the previous section.


5. Click *Finish* to create your Liferay Workspace.

![Figure 2: Liferay @ide@ provides an easy-to-follow menu to create your 
Liferay Workspace.](../../../images/new-workspace-menu.png)

A dialog appears prompting you to open the Liferay Workspace perspective.
Click *Yes*, and your perspective will switch to Liferay Workspace.
