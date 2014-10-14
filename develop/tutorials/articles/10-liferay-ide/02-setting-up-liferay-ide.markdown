# Setting Up Liferay IDE [](id=setting-up-liferay-ide)

Now that you have Liferay IDE installed, either from a downloaded zip file or
from the update site appropriate for your Eclipse version, you need to perform
some basic setup. This section describes the setup steps to perform so you can
develop your Liferay portal and test your customizations. 

Before setting up Liferay IDE, let's make sure you have all the appropriate
software packages installed. 

## Requirements [](id=requirements)

Before setting up Liferay IDE, you need to have appropriate versions of Liferay
Portal, Liferay Plugins SDK and/or Maven, and Eclipse. Make sure you satisfy
these requirements before proceeding:

1.  Liferay Portal 6.0.5 or greater is downloaded and unzipped

2.  Liferay Plugins SDK 6.0.5 or greater is downloaded and unzipped, and/or any
    version of Maven is installed. If you're using the Plugins SDK, make sure
    the Plugins SDK version matches the Liferay Portal version.

3.  You've installed an appropriate Eclipse IDE version for Java EE Development,
    and the Liferay IDE extension--see the *Installation* section if you haven't
    already done this. 

+$$$

**Note:** Earlier versions of Liferay (e.g., 5.2.x) are not supported by Liferay
IDE. 

$$$

Let's set up your Liferay Plugins SDK. 

## Registering the Liferay Plugins SDK with Liferay IDE [](id=registering-the-liferay-plugins-sdk-with-liferay-ide)

Before you begin creating new Liferay plugin projects, a supported Liferay
Plugins SDK and/or Maven installation and Liferay Portal must be installed and
configured in your Liferay IDE. If you're thinking, "Wait a second, buster! I
thought the Plugins SDK and Maven could be used without Liferay IDE!", then
you're right. In the [The Plugins
SDK](/develop/tutorials/-/knowledge_base/6-1/the-plugins-sdk)
chapter, we'll explain how to use the Plugins SDK on its own, with a text
editor. Also, we'll explain how to use Maven on its own in the [Developing
plugins using
Maven](/develop/tutorials/-/knowledge_base/6-1/developing-plugins-using-maven)
chapter. Here, we explain the easiest way to use the Plugins SDK: by running it
from Liferay IDE. 

1.  In Eclipse, click *Window* &rarr; *Preferences* &rarr; *Liferay* &rarr;
    *Installed Plugin SDKs*.

2.  Click *Add* to bring up the *Add SDK* Dialog. 

3.  Browse to your Plugins SDK installation. The default name is the directory
    name; you can change it if you want. 

4.  Select *OK* and verify that your SDK was added to the list of *Installed
    Liferay Plugin SDKs*. 

+$$$

**Note:** You can have Multiple SDKs in your preferences. Set one as the default
SDK by checking its box in the list of *Installed Liferay Plugin SDKs*. 

$$$

Let's set up your Liferay Portal Tomcat runtime and server. 

## Liferay Portal Tomcat Runtime and Server Setup [](id=liferay-portal-tomcat-runtime-and-server-setup)

1.  In Eclipse, go to *Window* &rarr; *Preferences* &rarr; *Server* &rarr;
    *Runtime Environments*. 

    ![Figure 10.2: Choosing a runtime environment](../../images/ide-prefs-runtime-env.png)

2.  Click *Add* to add a new Liferay runtime; find *Liferay v6.1 (Tomcat 7)*
    under the *Liferay, Inc.* category and click *Next*.

3.  Click *Browse* and select your `liferay-portal-6.1.x directory`. 

4.  If you've selected the Liferay portal directory and a bundle JRE is present,
    it is automatically selected as the server's launch JRE. If no JRE bundle
    is present, then you must select the JRE to use for launch by clicking
    *Installed JREs...*. 

    ![Figure 10.3: Choosing the JRE bundle](../../images/ide-server-jre.png)

5.  Click *Finish*; you should see your Liferay portal runtime listed in
    *Preferences* &rarr; *Server* &rarr; *Runtime Environments*.

6.  Click *OK* to save your runtime preferences. 

7.  If you haven't created a server, create one now from the *Servers*
    view in Liferay IDE; then you can test the server. 

    ![Figure 10.4: Adding a runtime to the Servers view](../../images/ide-add-new-server.png)

8.  Scroll to the *Liferay, Inc* folder and select *Liferay v6... Server*.
    Choose the *Liferay v6...* runtime environment that you just created. 

Now your server is set up.  Let's launch it and perform some tests! 
