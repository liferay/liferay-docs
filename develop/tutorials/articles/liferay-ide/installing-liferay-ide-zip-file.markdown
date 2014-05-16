# Installing Liferay IDE From a ZIP File

Even if you're a grizzled veteran Java developer, if you're going to be doing a
lot of development for your Liferay Portal instance, consider using Liferay IDE.
When Liferay IDE is paired with the Plugins SDK or Maven and a Liferay runtime
environment, you have a one stop development environment where you can develop
your Liferay plugins, build them, and deploy them to your Liferay instance.

Liferay IDE is an extension for Eclipse IDE and supports development of plugin
projects for the Liferay Portal platform. The latest version of Liferay IDE
supports development of portlets, hooks, layout templates, themes, and Ext
plugins.

This tutorial shows how to install Liferay IDE from a `.zip` file. After you 
complete these steps you'll have a functional Liferay IDE installation. Let's 
get started!

## Installation Requirements

Make sure you have a supported Java JRE and Eclipse release: 

- Java 6.0 JRE or greater. 
- Liferay IDE must be run in one of the following Eclipse releases: 

    Eclipse Kepler Java EE (4.3.x)

    Eclipse Juno Java EE (4.2.x)

    Eclipse Indigo Java EE (3.7.x)

Next, we'll show you how to install Liferay IDE from a `.zip` file. 

## Installation Steps

To install Liferay IDE from a `.zip` file, follow these steps: 

1.  Install Eclipse Kepler, Juno, or Indigo from the
    [Eclipse](http://www.eclipse.org) website. 

2.  Download the IDE 2.1 `.zip` file from
    [http://sourceforge.net/projects/lportal/files/Liferay IDE/2.1.0
    GA1/](http://sourceforge.net/projects/lportal/files/Liferay%20IDE/2.1.0%20GA1/)
    for your operating system. 

3.  Run the Eclipse executable file (e.g., `eclipse.exe`).

4.  When Eclipse opens, go to *Help* &rarr; *Install New Software...*. 

5.  In the *Add Site* dialog, click the *Archive* button and browse to the
    location of the downloaded Liferay IDE `.zip` file.

6.  Liferay IDE features should be detected. Select them and click *Next*. 

7.  After calculating dependencies, click *Next*, accept the license agreement,
    and click *Finish* to complete the installation.

8.  After restarting Eclipse, go to *Help* &rarr; *About Eclipse*; if you see a
    Liferay IDE icon badge as in the screenshot below, it's properly installed.  

![Figure 1: Once you've installed Liferay IDE, you can find the Liferay IDE logo in Eclipse by clicking *Help* &rarr; *About Eclipse*.](../../images/ide-about-eclipse-liferay.png)

Alternatively, you can install both Liferay IDE and Eclipse at the same time 
from a single package installer. To do so, just download the zip file for your 
operating system from 
[http://sourceforge.net/projects/lportal/files/Liferay%20IDE/2.1.0%20GA1/](http://sourceforge.net/projects/lportal/files/Liferay%20IDE/2.1.0%20GA1/). 

Now that you have Liferay IDE installed, you need to perform some basic setup. 
Pointing Liferay IDE to a Plugins SDK installation is required before you can 
get started developing. Also, configuring a Liferay runtime environment is 
necessary to get the most out of Liferay IDE.
