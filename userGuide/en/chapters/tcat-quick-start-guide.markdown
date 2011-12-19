# 1. Introduction

Liferay Portal TCat Edition is a combination of Liferay Portal, the leading open source portal, and TCat Server, an enterprise grade administration console for the Apache Tomcat application server.

Tcat Server provides several tools to make Tomcat more administrator friendly, especially in organizations used to leveraging administration consoles like those found in other more complex JEE application servers. You may use the console to:

-   Monitoring and control all Tomcat instances on multiple servers and     across multiple environments.

-   Easily deploy and view applications across instances

-   Troubleshoot problems across all instances, including view JMX     parameters, viewing remote logs, etc

-   Provides granular entitlement controls for the above functions and     more.

This guide serves as a quick start guide to help you get up and running with Liferay Portal and TCat Server. You may find more detailed information about each respective product at the following locations:

-   [http://www.mulesoft.org/documentation/display/TCAT/Home](http://www.mulesoft.org/documentation/display/TCAT/Home)

-   [http://www.liferay.com/documentation/liferay-portal](http://www.liferay.com/documentation/liferay-portal)

To obtain Liferay Portal TCat Edition, you will need access to the Liferay Customer Portal and the Mulesoft TCat Server website. If you are not a current Liferay customer and would like to try the product, please contact Liferay via email at sales@liferay.com.

# 2. Installation

This chapter describes how to install and configure TCat Server in preparation for use with Liferay Portal Enterprise Edition.

Since this is guide is meant as a quick start guide, it may not provide every detail for installation and configuration. We will also use the Windows and Linux 64-bit installation wizards.

For more detailed installation instructions, please consult the Mulesoft TCat Server installation documents located at:

[http://www.mulesoft.org/documentation/display/TCAT/Home](http://www.mulesoft.org/documentation/display/TCAT/Home)

-   [Installing Tcat Server on     Linux](http://www.mulesoft.org/documentation/display/TCAT/Installing+Tcat+Server+on+Linux)

-   [Installing Tcat Server on     Windows](http://www.mulesoft.org/documentation/display/TCAT/Installing+Tcat+Server+on+Microsoft+Windows)

-   [Installing Tcat Server on     Solaris](http://www.mulesoft.org/documentation/display/TCAT/Installing+Tcat+Server+on+Solaris)

-   [Installing Tcat Server on Mac OS     X](http://www.mulesoft.org/documentation/display/TCAT/Installing+Tcat+Server+on+Mac+OS+X)

-   [Adding Tcat Server Capabilities to an Existing Apache Tomcat     Install](http://www.mulesoft.org/documentation/display/TCAT/Add+Tcat+Server+Capabilities+to+an+Existing+Tomcat+Install)

\ \

## Step 1 - Obtaining the Software

First, download the Liferay TCat bundle from the Liferay customer portal. The links for Liferay Portal 6.0 EE SP2 TCat Edition can be found at: [http://www.liferay.com/group/customer/downloads/liferay-portal/6.0](http://www.liferay.com/group/customer/downloads/liferay-portal/6.0). You will need two files:

-   Liferay Portal 6.0 EE SP2 TCat Admin - contains the TCat     administration console and Liferay Portal EE and all appropriate     plugins.

-   Liferay Portal 6.0 EE SP2 TCat Agent - contains a vanilla Tomcat     application server and the TCat management agent.

Next, you will need to download the appropriate TCat platform installation at: [http://www.mulesoft.com/download-tcat](http://www.mulesoft.com/download-tcat). You may select installation wizards for Windows (32 and 64-bit), Mac, Unix, Solarix, and Linux (32 and 64-bit) as well as a manual installation zip.

## Step 2 - TCat Server Administration Console Installation

After obtaining the software bundles, we shall proceed with installation and configuration of the Administration Console.

### Installing the Administration Console

#### Windows

For Windows, TCat comes with an installer to assist with installation and configuration. After downloading, go ahead and execute the installer, accepting the appropriate license agreement.

Upon accepting the license agreement, the system will present you with two installation choices, a "standard" or "custom" installation.

![image](TCat%20Quick%20Start%20Guide_html_2589582e.png)

*Figure 1 - Installation Selection*

You should select the "Custom" installation option to provide better control of where TCat Server is installed.

\ \

![image](TCat%20Quick%20Start%20Guide_html_m230ba580.png)

*Figure 2 - Installation Component Selection*

After selecting the custom installation option, the TCat installer prompts you to select the desired components

-   TCat Server - a version of Apache Tomcat that includes the     appropriate management agents used by TCat.

-   Administration Console - the administration console for monitoring     and managing all available TCat servers. This console contains tools     used for application deployment, log access, server control, and     other administration tools.

-   Tomcat Documentation - the documentation that comes normally with an     Apache Tomcat distribution. You do not need to select this option.

-   Tomcat Examples - the examples that comes normally with an Apache     Tomcat distribution. You do not need to select this option.

-   Tomcat Manager Application - the manager application that comes     normally with an Apache Tomcat distribution. You do not need to     select this option.

For this step in the installation process, we will select the Administration Console in addition to TCat Server.

\ \

![image](TCat%20Quick%20Start%20Guide_html_261b594a.png)

*Figure 3 - Installation Location*

After select TCat Server and the Administration Console for installation, the installation wizard will prompt you for an installation directory. Please select the desired installation directory for the TCat Server.

\ \

![image](TCat%20Quick%20Start%20Guide_html_66274d0c.png)

*Figure 4 - Port Configurations*

After selecting the appropriate installation location, the installation wizard will prompt you to specify the appropriate port numbers. If this is the first time installing TCat and no other Apache Tomcat installations are present, then you may retain the above ports. However, if there are other installations, you will need to select new ports. For the purpose of this installation, we will assume the above ports are correct.

![image](TCat%20Quick%20Start%20Guide_html_7936c58a.png)

*Figure 5 - Windows Service Installation*

To ensure the operating system starts the TCat Server and Administrator Console, you must configure the TCat Server process as a Windows service. The next step in the installation wizard will help with this process.

In the Windows service installation screen, please select "Install TCat Server as a Windows service." You may choose an appropriate service name other than "TCatServer".

![image](TCat%20Quick%20Start%20Guide_html_3f911eab.png)

*Figure 6 - Start Menu Shortcuts*

The final step in the installation wizard is to configure Start Menu shortcuts. You may choose to customize the shortcut location or simply accept the default.

After configuring the shortcuts, the TCat Server Windows installer will perform the installation as previously configured.

#### Linux(Ubuntu)

For Linux, TCat comes with an installer to assist with installation and configuration. After downloading, go ahead and execute the installer, accepting the appropriate license agreement. To execute the installer, make sure:

-   you have added execution permissions to the installer file *(chmod     a+x <filename\>)*

-   you execute it with root privileges (the installed service will be     executed by a pre-defined syste user with no root privileges)

Upon accepting the license agreement, the system will present you with two installation choices, a "standard" or "custom" installation.

![image](TCat%20Quick%20Start%20Guide_html_766a1d6e.gif)

*Figure 7 - Installation Type*

You should select the "Custom" installation option to provide better control of where TCat Server is installed.

![image](TCat%20Quick%20Start%20Guide_html_55494177.gif)

*Figure 8 - Installation Component Selections*

After selecting the custom installation option, the TCat installer prompts you to select the desired components

-   TCat Server - a version of Apache Tomcat that includes the     appropriate management agents used by TCat.

-   Administration Console - the administration console for monitoring     and managing all available TCat servers. This console contains tools     used for application deployment, log access, server control, and     other administration tools.

-   Tomcat Documentation - the documentation that comes normally with an     Apache Tomcat distribution. You do not need to select this option.

-   Tomcat Examples - the examples that comes normally with an Apache     Tomcat distribution. You do not need to select this option.

-   Tomcat Manager Application - the manager application that comes     normally with an Apache Tomcat distribution. You do not need to     select this option.

For this step in the installation process, we will select the Administration Console in addition to TCat Server, which is the default setting.

After selecting TCat Server and the Administration Console for installation, the installation wizard will prompt you for an installation directory.

![image](TCat%20Quick%20Start%20Guide_html_7df6aabf.png)Figure 9 - Installation Location

Please select the desired installation directory for the TCat Server. The offered default directory will be different if the installation process has been started as root.

![image](TCat%20Quick%20Start%20Guide_html_5b760cd.gif)

*Figure 10 - Port Configuration*

After selecting the appropriate installation location, the installation wizard will prompt you to specify the appropriate port numbers. If this is the first time installing TCat and no other Apache Tomcat installations are present, then you may retain the above ports. However, if there are other installations, you will need to select new ports. For the purpose of this installation, we will assume the above ports are correct.

\ \

![image](TCat%20Quick%20Start%20Guide_html_749e9b40.png)

*Figure 11 - Configure TCat System User*

For security considerations, Tcat will run as a non-root system user. If the username specified at this stem does not exist, it will be created as a system daemon user.

\ \

![image](TCat%20Quick%20Start%20Guide_html_mf7bb10c.gif)

*Figure 12 - Install Service*

To ensure the operating system starts the TCat Server and Administrator Console, you must create a service startup script in /etc/init.d. The next step in the installation wizard will help with this process.

In the service installation screen, you may enter an appropriate service name or use the default. If you have multiple installations of TCat, you should select a more appropriate name.

![image](TCat%20Quick%20Start%20Guide_html_m55bbff3f.gif)

*Figure 13 - Symbolic Link Creation*

\ \

The final configuration step before installation is the creation of a symbolic link to more easily access the TCat executable. This step is optional and you may choose to not create the symbolic link.

![image](TCat%20Quick%20Start%20Guide_html_78e73740.gif)

\ \

### Adding Liferay Portal Packages

After completing the TCatServer Administration Console installation, we will proceed with configuring the Liferay Portal packages for TCat.

First, extract the previously downloaded Liferay Portal 6.0 EE SP2 TCat Admin into a temporary directory. Once extracted, locate the file tcat-init.groovy and the directory tcat\_init.

![image](TCat%20Quick%20Start%20Guide_html_12074416.png)

*Figure 7 - Liferay Portal TCat Packages*

The tcat-init.groovy file contains instructions for the TCatServer administration console to:

-   Pre-load the Liferay Portal and Plugins WAR files into the     application repository.

-   Load the appropriate Liferay application profile that should be     applied to all other TCat managed servers

-   Load the Liferay specific deployment manager.

The tcat\_init folder contains the managed server profiles, Liferay WAR files, and administration scripts.

You should copy the tcat-init.groovy file and tcat\_init folder to the previously configured installation location.

![image](TCat%20Quick%20Start%20Guide_html_mf987314.png)

*Figure 14 - TCat Installation Directory*

displays the installation directory after copying the tcat\_init folder and tcat-init.groovy into the TCatServer installation directory.

\ \

### Validating Your Installation

#### Windows

After successfully completing the installation, you should see a TCatServer entry in the Services console, similar to the screen shot in ****.

\ \

\ \

![image](TCat%20Quick%20Start%20Guide_html_3b2f5fb4.png)

*Figure 15 - Windows Services Console*

By default, the TCatServer service will be inactive but the operating system will automatically start it upon restart. Go ahead and choose to start the service.

#### Linux(Ubuntu)

After successfully completing the installation, you should see an entry for the TCat service initialization script in /etc/init.d/. The script name will be the name you choose during the installation process.

![image](TCat%20Quick%20Start%20Guide_html_352642da.gif)

*Figure 16 - TCat Service Startup*

To start the TCat Server Administration Console, you may execute the service script in /etc/init.d or use the script as in Figure 15.

\ \

## Step 3 - TCat Server Managed Server Installation

The steps to install the TCat Server managed server is quite similar to those for installing the TCat Server Administration Console.

#### Windows

![image](TCat%20Quick%20Start%20Guide_html_m26726042.png)

*Figure 17 - TCat Server Managed Server*

During the installation component configuration step, you should unselect "Administration Console" from the list of components.

\ \

![image](TCat%20Quick%20Start%20Guide_html_261b594a.png)

*Figure 18 - Installation Location*

The next step is to specify an installation location. If you have other installations of TCat on this machine, you should another destination. For example, if you have already installed the TCat Server Administration Console to "C:\\TCatServer6", you should perhaps install the managed server to "C:\\TCatServer6Managed".

\ \

\ \

\ \

![image](TCat%20Quick%20Start%20Guide_html_66274d0c.png)

*Figure 19 - Managed Server Port Configurations*

During the managed server installation, as with the administration console installation, you will be prompted to configure the appropriate port numbers. If you do not have another TCat Server instance (e.g. administration console or another managed server), you may choose the default ports. Otherwise, you should select non-conflicting ports. For instance:

-   HTTP port: 8180

-   HTTPS redirect port: 81443

-   TCat Server shutdown port: 8105

-   AJP connector port: 8109

-   Secure Agent Port: 52443

![image](TCat%20Quick%20Start%20Guide_html_7936c58a.png)

*Figure 20 - Windows Service Installation*

To ensure the operating system starts the TCat Server and Administrator Console, you must configure the TCat process as a Windows service. The next step in the installation wizard will help with this process.

In the Windows service installation screen, please select "Install TCat Server as a Windows service." You may choose an appropriate service name other than "TCatServer", for example "TcatServer Managed Instance".

\ \

![image](TCat%20Quick%20Start%20Guide_html_3f911eab.png)

*Figure 21 - Shortcut Creation*

The final step in the installation wizard is to configure Start Menu shortcuts. You may choose to customize the shortcut location or simply accept the default. Since this is for a managed server, you may wish to name it similar to the service name.

After configuring the shortcuts, the TCat Server Windows installer will perform the installation as previously configured.

#### Linux (Ubuntu)

Installing the Tcat managed server is similar to the steps for installing the Administration Console.

\ \

![image](TCat%20Quick%20Start%20Guide_html_m1207b5b0.png)

*Figure 22 - TCat Server Managed Server*

\ \

During the installation component configuration step, you should unselect "Administration Console" from the list of components.

\ \

![image](TCat%20Quick%20Start%20Guide_html_2d12c27d.png)

*Figure 23 - Managed Server Port Configurations*

\ \

During the managed server installation, as with the administration console installation, you will be prompted to configure the appropriate port numbers. If you do not have another TCat Server instance (e.g. administration console or another managed server), you may choose the default ports. Otherwise, you should select non-conflicting ports. For instance:

-   HTTP port: 8180

-   HTTPS redirect port: 81443

-   TCat Server shutdown port: 8105

-   AJP connector port: 8109

-   Secure Agent Port: 52443

![image](TCat%20Quick%20Start%20Guide_html_749e9b40.png)

*Figure 24 - Tcat User Configuration*

\ \

*Figure 25 - Configure TCat System User*

For security considerations, Tcat will run as a non-root system user. If the username specified at this stem does not exist, it will be created as a system daemon user.

\ \

![image](TCat%20Quick%20Start%20Guide_html_m3f143e4d.png)

*Figure 26 - TCat Server Service*

\ \

To ensure the operating system starts the TCat Server and Administrator Console, you must configure the TCat process as a Linux service. The next step in the installation wizard will help with this process. Please make sure you provide a unique name for this TCat Server service, one that does not conflict with the Administration Console server.

\ \

\ \

![image](TCat%20Quick%20Start%20Guide_html_2d9d8c9e.png)

*Figure 27 - Validating Installation*

\ \

Once the wizard completes, you may validate that both the Administration Console and the managed server are installed correctly.

\ \

\ \

\ \

### Configuring Managed Server For Liferay

Once you have installed the managed server, there is one more step to perform on the managed server. You will need to modify the catalina.properties file located in the conf directory of your TCat Server installation.

You will need to change the text:

*common.loader=${catalina.base}/lib,${catalina.base}/lib/\*.jar,${catalina.home}/lib,${catalina.home}/lib/\*.jar*

To:

*common.loader=${catalina.base}/lib,${catalina.base}/lib/\*.jar,${catalina.home}/lib,${catalina.home}/lib/\*.jar,${catalina.home}/lib/ext,${catalina.home}/lib/ext/\*.jar*

By modifying the common.loader property, you will have instructed TCat Server to load everything in the lib/ext folder as part of the classpath.

## Step 4 - Managed Server Registration

#### Logging In

After completing the installation process in steps 2 and 3 and starting the appropriate TCatServer processes, you can open a browser to <SERVER\_NAME\>:<PORT\>/console. Using previous installation example, you should point your browser to http://localhost:8080/console. The browser should render the TCatServer Administration Console shown in .

![image](TCat%20Quick%20Start%20Guide_html_m4bda7997.png)

*Figure 28 - TCat Admin Console Login*

The default login is: admin/admin.

Once you have logged into the administration console, you will be presented with a global dashboard that you may customize once you have dismissed the "Tcat Server - Quick Start" panel.

\ \

\ \

![image](TCat%20Quick%20Start%20Guide_html_5d43770f.png)

*Figure 29 - Customizable TCat Admin Console*

\ \

#### Registering Managed Servers

First make sure that you have started the previously installed managed server. server. Clicking on the "Servers" tab in the console, you will see a server listed as "Unregistered".

\ \

\ \

![image](TCat%20Quick%20Start%20Guide_html_799ab00c.png)

*Figure 30 - Unregistered TCat Servers*

\ \

Unregistered servers are servers that have the TCat management agent installed but are not added to the administration console. You may add the unregistered server by selecting either "Register" or "Register & Add To Group".

If you choose to use "Register & Add To Group", the server will be added to the desired server group (e.g. Development). For the purpose of this guide, we will choose to register the server to the Development group.

![image](TCat%20Quick%20Start%20Guide_html_34603e60.png)

*Figure 31 - Monitoring a Registered Server*

\ \

After registering the server, you will have access to view its health status, log files, etc. Feel free to walk through the console to examine its capabilities, like reviewing log files, current thread status, deployed web applications, and etc.

You will find additional information at *[http://www.mulesoft.org/documentation/display/TCAT/Home](http://www.mulesoft.org/documentation/display/TCAT/Home).*

Managing and Monitoring Servers

-   [Using the Server     Dashboard](http://www.mulesoft.org/documentation/display/TCAT/Server+Dashboard)

-   [Working with Server     Groups](http://www.mulesoft.org/documentation/display/TCAT/Working+with+Servers)

-   [Monitoring Servers (View Apps, Threads,     etc)](http://www.mulesoft.org/documentation/display/TCAT/Monitoring+a+Server)

-   [Monitoring a JMX     Agent](http://www.mulesoft.org/documentation/display/TCAT/JMX+Agent+Monitoring)

-   [Scripts to Save JMX Metrics to CSV     Files](http://www.mulesoft.org/documentation/display/TCAT/Saving+JMX+Metric+Data+to+CSV+Files)

Managing Tcat Web Applications

-   [Monitoring     Applications](http://www.mulesoft.org/documentation/display/TCAT/Monitoring+Applications)

-   [Deploying     Applications](http://www.mulesoft.org/documentation/display/TCAT/Deploying+Applications)

-   [Using the     Repository](http://www.mulesoft.org/documentation/display/TCAT/Managing+the+Repository)

-   [Setting Security on Repository     Artifacts](http://www.mulesoft.org/documentation/display/TCAT/Setting+Security)

\ \

After registering the server, you will have access to view its health status, log files, etc. Feel free to walk through the console to examine its capabilities, like reviewing log files, current thread status, deployed web applications, and etc.

\ \

## Step 5 - Liferay Portal Deployment

As part of the installation process, the current version of Liferay Portal and a number of Liferay Plugins have been provisioned into the TCat repository. You can view them by clicking on the "Repository" tab.

\ \

![image](TCat%20Quick%20Start%20Guide_html_7e61df5a.png)

*Figure 32 - TCat Repository: Profiles*

\ \

There are two components in the TCat Repository:

-   Profiles - application profiles to be applied to the TCat managed     servers. Contains configuration, shared libraries, etc

-   Applications - deployable WAR files for web applications and Liferay     Plugins

![image](TCat%20Quick%20Start%20Guide_html_m4af27eb5.png)

*Figure 33 - TCat Repository: Applications*

In the examples shown in ** and **, the TCat Repository has been preloaded with the profile and applications for Liferay Portal 6.0 EE SP2.

\ \

### Creating Liferay License Profile

To being Liferay deployment, we must first create a Liferay license profile to be deployed to the managed server:

![image](TCat%20Quick%20Start%20Guide_html_m2d96341c.png)

*Figure 34 - Creating a new workspace for Liferay License Profile*

\ \

1.  First create a new workspace under "Profiles"

    1.  Select Profiles in the left navigation and then click the "New         Workspace" button

\ \

![image](TCat%20Quick%20Start%20Guide_html_m331bb779.png)

*Figure 35 - folder within the Liferay Portal Trial License Workspace*

\ \

2.  Within the new workspace "Liferay Portal Trial License", create     another folder called "deploy"

\ \

![image](TCat%20Quick%20Start%20Guide_html_m1764f57b.png)

*Figure 36 - Uploading Liferay Portal License*

\ \

3.  In the "deploy" folder, select "New Artifact" and upload a new     license file.

\ \

After completing the above, you have successfully uploaded your Liferay Portal license file into the TCat repository. The final step is to create a server profile from the "Administration" tab.

s shows the list of available Server Profiles and the option to create a new profile.

![image](TCat%20Quick%20Start%20Guide_html_m5c7a2b8c.png)

*Figure 37 - Viewing Server Profiles*

\ \

![image](TCat%20Quick%20Start%20Guide_html_7f26dc4d.png)

*Figure 38 - Creating a new Server Profile*

When creating a server profile for the Liferay Portal Trial License, you will need to select the workspace folder created in the previous step. This will ensure when you apply the profile, TCat will use the latest version of the license file.

\ \

### Preparing to Deploy Liferay Portal

Once you have created the server profile for your Liferay Portal EE license, you may begin deploying Liferay to the managed server.

![image](TCat%20Quick%20Start%20Guide_html_53af6680.png)

*Figure 39 - Apply Liferay Portal Trial License*

\ \

The first step is to apply two server profiles, one for the license and the other for Liferay Portal. Applying the license profile does not require a restart of the server.

\ \

![image](TCat%20Quick%20Start%20Guide_html_m1f86eaab.png)

*Figure 40 - Apply Liferay Profile*

\ \

The second profile that must be applied is the Liferay server profile. In the example shown in , we applied the profile for Liferay Portal EE 6.0 SP2.

The Liferay Portal server profile requires a server restart. If you have the managed server configured as a Windows or Linux service, you may restart the server directly from the console by selecting the server and clicking "Restart".

\ \

### Deploying Liferay Portal

With both profiles applied, we can now deploy the Liferay Portal WAR to the appropriate server.

The "Deployments" tab contain tools that assist in creating a deployment and targeting it to specific servers. Once you create a deployment, you may targeted to any number of servers or deployment groups.

\ \

![image](TCat%20Quick%20Start%20Guide_html_m54d58d30.png)

*Figure 41 - Choosing a Web Application for Deployment*

\ \

When creating a deployment, you may choose a WAR file already uploaded into the repository or you may choose to upload a new WAR file. We recommend using a WAR file from the repository for non-development deployments. This will ensure you consistently deploy the correct version to your environments.

In **, since we are deploying Liferay Portal for the first time, we choose the ROOT.war from the 6\_0\_12 directory.

\ \

\ \

![image](TCat%20Quick%20Start%20Guide_html_m369dfb3a.png)

*Figure 42 - Selecting the Deployment Target*

\ \

After selecting the appropriate web application, you may choose which Servers to deploy to. In the example above, we will deploy to the Tomcat instance labeled as "Liferay Portal Instance 1."

\ \

\ \

![image](TCat%20Quick%20Start%20Guide_html_68a00002.png)

*Figure 43 - Completing Liferay Portal Deployment*

\ \

After choosing to deploy the application, the TCat console will inform you of the current deployment status (e.g. "Successful"). Assuming you have followed the previous steps and the deployment successfully completes, you will be able to access Liferay Portal on the target Tomcat instance.

\ \

\ \

![image](TCat%20Quick%20Start%20Guide_html_2b08ac2c.png)

*Figure 44 - Accessing Liferay Portal on Liferay Portal Instance 1*

## Step 6 - Liferay Plugins Deployment

Liferay Portal TCat Edition initializes with all the appropriate Liferay EE Plugins, including:

-   Kaleo workflow engine

-   Knowledge base

-   Chat

-   Private messaging

You may choose to deploy these plugins to the appropriate servers by following the same steps as for deploying Liferay Portal, specifying the appropriate plugin WAR file instead of the ROOT.war.

\ \

Other plugins that may be downloaded and added to this repository include:

-   Audit

-   Report engine and console

-   Drools integration.

You may download these plugins from the Liferay Customer Portal and manually add them to the TCat repository.

![image](TCat%20Quick%20Start%20Guide_html_19e9e6d6.png)

*Figure 45 - Uploading New Plugins into TCat*

\ \

# 3. Conclusion

\ \

This guide provided an introduction to installing and using Liferay TCat Edition, the combination of Liferay Portal and Mulesoft TCat Server. More advanced documentation on TCat Server can be found at:

-   [http://www.mulesoft.org/documentation/display/TCAT/Home](http://www.mulesoft.org/documentation/display/TCAT/Home)

\ \

For more information about clustering Liferay Portal, configuring LDAP, and other more advanced features, you may consult the Liferay Portal User Guide at:

-   [http://www.liferay.com/documentation/liferay-portal](http://www.liferay.com/documentation/liferay-portal)

\ \ 
